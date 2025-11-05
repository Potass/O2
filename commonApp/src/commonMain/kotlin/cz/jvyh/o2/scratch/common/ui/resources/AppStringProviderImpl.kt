package cz.jvyh.o2.scratch.common.ui.resources

import co.touchlab.kermit.Logger
import cz.jvyh.o2.scratch.common.Res.string
import cz.jvyh.o2.scratch.common.activation__destination_title
import cz.jvyh.o2.scratch.common.common__label_activate
import cz.jvyh.o2.scratch.common.common__label_ok
import cz.jvyh.o2.scratch.common.common__label_scratch
import cz.jvyh.o2.scratch.common.domain.AppStringKey
import cz.jvyh.o2.scratch.common.general__app_name
import cz.jvyh.o2.scratch.common.main__destination_title
import cz.jvyh.o2.scratch.common.preview__destination_title
import cz.jvyh.o2.scratch.common.scratch__destination_title
import cz.jvyh.o2.scratch.shared.common.domain.CommonStringKey
import cz.jvyh.o2.scratch.shared.common.domain.StringKey
import cz.jvyh.o2.scratch.shared.common.domain.StringListKey
import cz.jvyh.o2.scratch.shared.common.platform.BusyIndicatorController
import cz.jvyh.o2.scratch.shared.common.ui.resources.AppStringProvider
import org.jetbrains.compose.resources.StringArrayResource
import org.jetbrains.compose.resources.StringResource

class AppStringProviderImpl(
    private val logger: Logger,
    private val busyIndicatorController: BusyIndicatorController,
) : AppStringProvider {
    private val strings = mutableMapOf<StringKey, String>()
    private val stringsLists = mutableMapOf<StringListKey, List<String>>()

    override suspend fun load(force: Boolean) {
        if (strings.isEmpty() || force) {
            logger.i { "Loading all strings into memory..." }

            busyIndicatorController.coWith {
                strings[AppStringKey.ActivationDestinationTitle] = string.activation__destination_title.resolve()
                strings[AppStringKey.AppName] = string.general__app_name.resolve()
                strings[AppStringKey.MainDestinationTitle] = string.main__destination_title.resolve()
                strings[AppStringKey.ScratchDestinationTitle] = string.scratch__destination_title.resolve()
                strings[CommonStringKey.CommonLabelActivate] = string.common__label_activate.resolve()
                strings[CommonStringKey.CommonLabelOk] = string.common__label_ok.resolve()
                strings[CommonStringKey.CommonLabelScratch] = string.common__label_scratch.resolve()
                strings[CommonStringKey.PreviewDestinationTitle] = string.preview__destination_title.resolve()
            }
        }
    }

    private suspend fun StringResource.resolve(): String = org.jetbrains.compose.resources.getString(this)

    private suspend fun StringArrayResource.resolve(): List<String> = org.jetbrains.compose.resources.getStringArray(this)

    override fun getString(key: StringKey): String = strings[key].orMissing(key)

    override fun getString(key: StringKey, vararg args: Any): String = strings[key]?.with(args).orMissing(key)

    override fun getStringList(key: StringListKey): List<String> = stringsLists[key].orMissing(key)

    private fun String?.orMissing(key: StringKey) = this ?: MISSING_KEY_PLACEHOLDER.with(arrayOf(key))

    private fun List<String>?.orMissing(key: StringListKey) = this ?: listOf(MISSING_KEY_PLACEHOLDER.with(arrayOf(key)))

    private fun String.with(arg: Any) = with(arrayOf(arg))

    private fun String.with(args: Array<out Any>): String {
        return if (args.isEmpty()) {
            this
        } else {
            val formattedString = buildString {
                var i = 0
                while (i < this@with.length) {
                    if (this@with[i] == ARG_CHAR_PLACEHOLDER && i + 1 < this@with.length && this@with[i + 1].isDigit()) {
                        val number = this@with.substring(i + 1).takeWhile { it.isDigit() }
                        val index = number.toIntOrNull()
                        if (index != null) {
                            val arg = args.getOrNull(index - 1)
                            if (arg != null) {
                                append(arg.toString())
                            } else {
                                append("$ARG_CHAR_PLACEHOLDER$number")
                            }
                            i += number.length
                        } else {
                            append(this@with[i])
                        }
                    } else {
                        append(this@with[i])
                    }
                    i++
                }
            }
            formattedString
        }
    }

    private companion object {
        const val ARG_CHAR_PLACEHOLDER = '%'
        const val MISSING_KEY_PLACEHOLDER = "MISSING_KEY: ${ARG_CHAR_PLACEHOLDER}1"
    }
}