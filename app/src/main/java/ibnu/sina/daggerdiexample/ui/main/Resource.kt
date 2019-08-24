package ibnu.sina.daggerdiexample.ui.main

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class Resource<T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    companion object {

        @JvmStatic
        fun <T> authenticated(data: T?) = Resource(Status.SUCCESS, data, null)
        @JvmStatic
        fun <T> error(@NonNull msg: String, data: T?) = Resource(Status.ERROR, data, msg)
        @JvmStatic
        fun <T> loading(data: T?) = Resource(Status.LOADING, data, null)
        @JvmStatic
        fun <T> logout() = Resource<T>(Status.LOGOUT, null, null)
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, LOGOUT
    }
}