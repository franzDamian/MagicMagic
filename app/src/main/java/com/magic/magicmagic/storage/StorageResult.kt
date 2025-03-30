package com.magic.magicmagic.storage

sealed class StorageResult<out T> {
    data class Success<out T>(val data: T): StorageResult<T>()
    data object Loading: StorageResult<Nothing>()
    data class Error(val exception: Throwable): StorageResult<Nothing>()
}