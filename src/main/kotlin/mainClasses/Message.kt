package mainClasses

class Message(
        val messageId: Int,
        val userId: Int,
        var text: String,
        var messageSeen: Boolean = false
)
