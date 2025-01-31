package ru.practicum.android.diploma.utils

import android.view.MotionEvent
import android.widget.EditText

private const val RIGHT_CORNER = 2
fun EditText.isRightDrawableClicked(event: MotionEvent): Boolean {
    val rightDrawable = compoundDrawables[RIGHT_CORNER]
    val drawableWidth = rightDrawable?.bounds?.width()
    if (event.action != MotionEvent.ACTION_UP || drawableWidth == null) return false

    return event.x >= width - paddingEnd - drawableWidth
}
