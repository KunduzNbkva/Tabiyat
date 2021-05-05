package com.example.tabiyat.auth

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.example.tabiyat.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changePage()
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




}