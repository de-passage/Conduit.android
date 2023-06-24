package dpsg.conduit.data

import android.util.Log
import dpsg.conduit.data.model.LoggedInUser
import dpsg.conduit.api.apis.UserAndAuthenticationApi
import dpsg.conduit.api.models.CreateUserRequest
import dpsg.conduit.api.models.LoginRequest
import dpsg.conduit.api.models.LoginUser
import dpsg.conduit.api.models.NewUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        Log.d("LoginDataSource", "login: '$username' password: '$password'")
        val api = UserAndAuthenticationApi()
        return try {
            val response = api.login(LoginRequest(LoginUser(username, password)))
            val user = LoggedInUser(
                response.user.username,
                response.user.username,
                response.user.token,
                response.user.image)
            Log.d("LoginDataSource", "login: $response")
            Result.Success(user)
        } catch (e: Throwable) {

            Log.d("LoginDataSource", "login: $e")
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun register(username: String, email: String, password: String): Result<LoggedInUser> {
        val api = UserAndAuthenticationApi()
        return try {
            val response = api.createUser(CreateUserRequest(NewUser(username, email, password)))
            val loggedUser = LoggedInUser(
                response.user.username,
                response.user.username,
                response.user.token,
                response.user.image);
            Log.d("LoginDataSource", "register: $response")
            Result.Success(loggedUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}