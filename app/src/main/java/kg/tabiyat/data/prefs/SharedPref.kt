package kg.tabiyat.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

const val SHARED_PREFS = "settings"
const val USER_TOKEN = "user_token"

class SharedPref(val context: Context) {
    val lanquage: String?
        get() = sharedPref.getString("lang", " ")

    fun saveLang(s: String) {
        sharedPref.edit()?.putString("lang", s)?.apply()
    }

    val sharedPref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    fun saveIsIntroShown() {
        sharedPref.edit().putBoolean("isIntroShown", true).apply()
    }

    fun isIntroShown(): Boolean {
        return sharedPref.getBoolean("isIntroShown", false)
    }

    fun <T> saveUser(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        sharedPref.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> getUser(key: String): T? {
        val value = sharedPref.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    fun saveAuthToken(userToken: String){
        sharedPref.edit().putString(USER_TOKEN,userToken).apply()
    }

    fun getAuthToken():String?{
        return sharedPref.getString(USER_TOKEN,null)
    }
}