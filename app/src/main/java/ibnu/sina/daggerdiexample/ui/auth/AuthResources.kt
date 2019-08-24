package ibnu.sina.daggerdiexample.ui.auth

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class AuthResources<T>(
    @NonNull val status: Status,
    @Nullable val data: T?,
    @Nullable val message: String?
) {

    companion object {

        @JvmStatic
        fun <T> authenticated(data: T?) = AuthResources(Status.SUCCESS, data, null)
        @JvmStatic
        fun <T> error(@NonNull msg: String, data: T?) = AuthResources(Status.ERROR, data, msg)
        @JvmStatic
        fun <T> loading(data: T?) = AuthResources(Status.LOADING, data, null)
        @JvmStatic
        fun <T> logout() = AuthResources<T>(Status.LOGOUT, null, null)
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, LOGOUT
    }
}
