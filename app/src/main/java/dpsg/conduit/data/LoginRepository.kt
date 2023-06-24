package dpsg.conduit.data

import android.util.Log
import dpsg.conduit.data.model.LoggedInUser
import dpsg.conduit.ui.login.LoginViewModel

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, email: String, password: String, loginType: LoginViewModel.LoginType): Result<LoggedInUser> {
        Log.d("LoginRepository", "login: '$username' password: '$password' email: '$email'")
        // handle login
        val result =
            if (loginType == LoginViewModel.LoginType.LOGIN) dataSource.login(username, password)
            else dataSource.register(username, email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}