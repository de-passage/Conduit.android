package dpsg.conduit.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import dpsg.conduit.data.LoginRepository
import dpsg.conduit.data.Result

import dpsg.conduit.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    enum class LoginType(val type: String) {
        LOGIN("login"),
        REGISTER("register")
    }

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _loginType = MutableLiveData<LoginType>(LoginType.LOGIN)
    val loginType: LiveData<LoginType> = _loginType

    fun login(username: String, password: String, email: String) {
        Log.d("LoginViewModel", "login: '$username' password: '$password' email: '$email'")
        // can be launched in a separate asynchronous job
        CoroutineScope(Dispatchers.IO).launch {
            val result = loginRepository.login(username, email, password, loginType.value!!)

            CoroutineScope(Dispatchers.Main).launch {
                if (result is Result.Success) {
                    _loginResult.value =
                        LoginResult(success =
                            LoggedInUserView(displayName = result.data.displayName,
                                token = result.data.token,
                                avatar = result.data.avatar))
                } else {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }
        }
    }

    fun changeLoginType() {
        if (_loginType.value == LoginType.LOGIN) {
            _loginType.value = LoginType.REGISTER
        } else {
            _loginType.value = LoginType.LOGIN
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun emailChanged(email: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return _loginType.value == LoginType.LOGIN || Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return true;
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}