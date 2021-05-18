package com.example.tabiyat.data.prefs

import android.content.Context
import android.content.SharedPreferences

const val USER_TOKEN = "user_token"
class SharedPref (val context: Context){

    private val sharedPref: SharedPreferences =context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveIsIntroShown(){
        sharedPref.edit().putBoolean("isIntroShown", true).apply()
    }

    fun isIntroShown(): Boolean{
        return sharedPref.getBoolean("isIntroShown", false)
    }

    fun saveAuthToken(userToken: String){
        sharedPref.edit().putString(USER_TOKEN,userToken).apply()
    }

    fun getAuthToken():String?{
        return sharedPref.getString(USER_TOKEN,null)
    }



}