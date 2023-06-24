package dpsg.conduit.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import dpsg.conduit.databinding.ActivityLoginBinding

import dpsg.conduit.R
import dpsg.conduit.databinding.ToolbarLayoutBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        val changeType = binding.changeLoginType
        val email = binding.email

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
            if (loginState.emailError != null) {
                email.error = getString(loginState.emailError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                Log.d("LoginActivity", "loginResult.error: ${loginResult.error}")
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)

                //Complete and destroy login activity once successful
                setResult(Activity.RESULT_OK)
                finish()
            }
        })

        loginViewModel.loginType.observe(this@LoginActivity) { loginType ->
            if (loginType == LoginViewModel.LoginType.LOGIN) {
                login.text = getString(R.string.action_sign_in)
                changeType.text = getString(R.string.change_to_sign_up)
                email.visibility = View.GONE
            } else {
                login.text = getString(R.string.action_sign_up)
                changeType.text = getString(R.string.change_to_sign_in)
                email.visibility = View.VISIBLE
            }
        }

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        email.afterTextChanged {
            loginViewModel.emailChanged(
                email.text.toString(),
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (loginViewModel.loginType.value == LoginViewModel.LoginType.LOGIN) {
                    return@setOnEditorActionListener false
                }
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString(),
                            email.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString(), email.text.toString())
            }

            changeType.setOnClickListener {
                loginViewModel.changeLoginType()
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        val userAvatar = model.avatar

        val sharedPreferences = getSharedPreferences("dpsg.conduit", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("dpsg.conduit.token", model.token)
        editor.putString("dpsg.conduit.username", model.displayName)
        editor.putString("dpsg.conduit.avatar", model.avatar)
        editor.apply()

        var toolbarBinding = ToolbarLayoutBinding.inflate(layoutInflater)
        val avatarView = toolbarBinding.userAvatar

        Log.d("LoginActivity", "userAvatar: $userAvatar")
        Glide.with(this)
            .load(userAvatar)
            .circleCrop()
            .into(avatarView)
        avatarView.visibility = View.VISIBLE
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}