package kg.tabiyat.data.model

data class SignUpModel(
    var type: String,
    var identification: String,
    var password: String
)

data class LoginModel(
    var email: String,
    var password: String
)
