package com.example.tabiyat.data.prefs

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context){

    private val sharedPref: SharedPreferences =context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveIsIntroShown(){
        sharedPref.edit().putBoolean("isIntroShown", true).apply()
    }

    fun isIntroShown(): Boolean{
        return sharedPref.getBoolean("isIntroShown", false)
    }

    fun saveToken( userToken: String){
        sharedPref.edit().putString("userToken",userToken).apply()
    }



}