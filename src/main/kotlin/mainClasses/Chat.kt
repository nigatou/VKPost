package mainClasses

class Chat(
        val id: Int,
        val user1Id: Int,
        val user2Id: Int,
        var messages: Array<Message>
)