package ru.practicum.android.diploma.features.common.domain

sealed class CustomException : Exception() {
    data object EmptyError : CustomException()
    data object NetworkError : CustomException()
    data object ServerError : CustomException()
}
