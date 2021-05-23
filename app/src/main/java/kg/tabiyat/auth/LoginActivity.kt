package kg.tabiyat.auth

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kg.tabiyat.R
import kg.tabiyat.R.*
import kg.tabiyat.base.showToastShort
import kg.tabiyat.data.model.LoginModel
import kg.tabiyat.data.model.Status
import kg.tabiyat.databinding.ActivityLoginBinding
import kg.tabiyat.ui.main.MainActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by inject<LoginViewModel>()
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var personName: String? = null
    private var personEmail: String? = null
    private var personId: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createGmailUser()
        observeProcess()
        checkForNull()
        changePage()
        enterToApp()
        signInBtn()
    }

    private fun changePage() {
        val text = (getString(string.no_acc_register)).toSpannable()
        text[23..34] = object : ClickableSpan() {
            override fun onClick(view: View) {
                openRegisterPage()
            }
        }
        binding.txtRegister.movementMethod = LinkMovementMethod()
        binding.txtRegister.text = text
    }

    private fun openRegisterPage() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun openMainPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun enterToApp() {
        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            val loginUser = LoginModel(email, password)
            viewModel.loginUser(loginUser)
        }

    }

    private fun checkForNull() {
        val editTexts = listOf(
            binding.loginEmail,
            binding.loginPassword,
        )
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    val et1 = binding.loginEmail.text.toString().trim()
                    val et2 = binding.loginPassword.text.toString().trim()

                    if (et1.isNotEmpty() && et2.isNotEmpty()) {
                        binding.loginBtn.setTextColor(Color.WHITE)
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun afterTextChanged(
                    s: Editable
                ) {
                }
            })
        }
    }

    private fun observeProcess() {
        viewModel.status.observe(this, {
            when (it) {
                Status.SUCCESS -> {
                    this.showToastShort(getString(string.success_login))
                    openMainPage()
                }
                Status.LOADING -> binding.loadingProgress.visibility = View.VISIBLE
                else -> this.showToastShort(getString(string.error_occurred))
            }
        })
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            this.showToastShort(getString(R.string.successfully))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private var loginResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            viewModel.createGmailUser("gmail",account.email)
            this.showToastShort(getString(string.successfully))
            Log.e("signIn","signInResult is success")
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("signIn", "signInResult:failed code=" + e.statusCode)
            this.showToastShort(getString(string.error_occurred))
        }

    }

    private fun signInBtn() {
        binding.gmail.setOnClickListener {
            val intent = mGoogleSignInClient!!.signInIntent
            loginResultLauncher.launch(intent)
        }
    }

    private fun createGmailUser() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(string.server_client_id))
                .requestEmail()
                .requestProfile()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

}