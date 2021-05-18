package com.example.tabiyat.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.example.tabiyat.base.showToastShort
import com.example.tabiyat.data.model.LoginModel
import com.example.tabiyat.data.model.Status
import com.example.tabiyat.databinding.ActivityLoginBinding
import com.example.tabiyat.ui.main.MainActivity
import org.koin.android.ext.android.inject


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by inject<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeProcess()
        checkForNull()
        changePage()
        observeUser()
        enterToApp()
    }

    private fun changePage(){
        val text = ("У вас еще не аккаунта? Регистрация").toSpannable()
        text[23..34] = object: ClickableSpan(){
            override fun onClick(view: View) {
                openRegisterPage()
            }
        }
        binding.txtRegister.movementMethod = LinkMovementMethod()
        binding.txtRegister.text = text
    }

    private fun openRegisterPage(){
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun openMainPage(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    private fun observeUser(){
        viewModel.user.observe(this, {

        })
    }

    private fun enterToApp(){
        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            val loginUser = LoginModel(email,password)
            viewModel.loginUser(loginUser)
        }

    }

    private fun checkForNull()  {
        val editTexts = listOf(
            binding.loginEmail,
            binding.loginPassword,
        )
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val et1 = binding.loginEmail.text.toString().trim()
                    val et2 = binding.loginPassword.text.toString().trim()

                    if (et1.isNotEmpty() && et2.isNotEmpty() ) {
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

    private fun observeProcess(){
        viewModel.status.observe(this,  {
            when(it){
                Status.SUCCESS ->  {
                    this.showToastShort("Вы успешно вошли!")
                    openMainPage()
                }
                Status.LOADING -> binding.loadingProgress.visibility= View.VISIBLE
                else->     this.showToastShort("Произошла ошибка!")
            }
        })
    }





}