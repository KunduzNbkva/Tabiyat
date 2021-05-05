package com.example.tabiyat.auth

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.example.tabiyat.R
import com.example.tabiyat.data.model.SignUpModel
import com.example.tabiyat.databinding.ActivityRegisterBinding
import com.example.tabiyat.ui.main.MainActivity
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by inject<RegisterViewModel>()
    private var lang:String = String()
    private var name:String = String()
    private var email:String= String()
    private var phoneNumber:String = String()
    private var password:String= String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
        changePage()
        signUpButton()
    }


    private fun changePage() {
        val text = ("У вас уже есть аккаунт? Войти").toSpannable()
        text[24..29] = object : ClickableSpan() {
            override fun onClick(view: View) {
                openNewPage(MainActivity())
            }
        }
        binding.getInAcc.movementMethod = LinkMovementMethod()
        binding.getInAcc.text = text
    }

    private fun openNewPage(activity: Activity) {
        startActivity(Intent(this, activity::class.java))
        finish()
    }


    private fun validateEmail() {
        val gmailInput: TextInputEditText = binding.signupEmail
        val emailTxt = gmailInput.text.toString().trim()

        if (emailTxt.isEmpty()) {
            binding.txtInputLayout.error = "Field can't be empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
            binding.txtInputLayout.error = "Please enter a valid email address"
        } else {
            binding.txtInputLayout.error = null
        }
    }

    private fun signUpButton() {
        binding.signupBtn.setOnClickListener {
            checkForNull()
            validateEmail()
            name = binding.signupUsername.text.toString()
            email = binding.signupEmail.text.toString()
            phoneNumber = binding.signupNumber.text.toString()
            password =binding.signupPassword.text.toString()
            openNewPage(MainActivity())
            languageSwitch()
            val signUpModel = SignUpModel("email",email,password)
            viewModel.createUser(signUpModel)
        }
    }

    private fun observeData(){
        viewModel.signupResponse.observe(this, {
            it.fullName = name
        })
    }

    private fun checkForNull() {
        val editTexts = listOf(
            binding.signupEmail,
            binding.signupUsername,
            binding.signupPassword,
            binding.signupNumber
        )
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val et1 = binding.signupEmail.text.toString().trim()
                    val et2 = binding.signupUsername.text.toString().trim()
                    val et3 = binding.signupPassword.text.toString().trim()
                    val et4 = binding.signupNumber.text.toString().trim()
                    if (et1.isNotEmpty() && et2.isNotEmpty() && et3.isNotEmpty() && et4.isNotEmpty()) {
                        binding.signupBtn.setTextColor(Color.WHITE)
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

    private fun languageSwitch():String{
        binding.switchView.setOnCheckedChangeListener { _, checkedId ->
            lang = when(checkedId){
                R.id.ru_lang -> "ru"
                R.id.kg_lang -> "kg"
                else -> "ru"
            }
        }
        return lang
    }
}


