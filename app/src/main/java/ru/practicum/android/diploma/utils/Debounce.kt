package ru.practicum.android.diploma.utils

import kotlinx.coroutines.*

fun <T> debounce(
    delayMillis: Long,
    coroutineScope: CoroutineScope,
    shouldCancelPrevious: Boolean = true,
    listener: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null

    return { data: T ->
        if (shouldCancelPrevious) {
            if (debounceJob?.isActive == true) {
                debounceJob?.cancel()
            }
        }

        if (shouldCancelPrevious || debounceJob?.isCompleted != false) {
            debounceJob = coroutineScope.launch {
                delay(delayMillis)
                listener.invoke(data)
            }
        }
    }
}
