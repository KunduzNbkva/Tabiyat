package kg.tabiyat.notification

data class NotificationData(
    val id: Int,
    val title: String,
    val shortMessage: String,
    val expandedText: String,
    val autoCancel: Boolean
)