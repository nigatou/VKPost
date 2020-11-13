package services

import mainClasses.Chat
import mainClasses.Message
import mainClasses.User
import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {
    private var chats = emptyArray<Chat>()

    private fun addChat(chat: Chat): Boolean {
        for (uniqueChat in chats) {
            if (uniqueChat.id == chat.id) {
                return false
            }
        }
        chats += chat
        return true
    }

    private fun removeChat(chatId: Int) {
        for (i in chats.indices) {
            if (chats[i].id == chatId) {
                remove(chats, i)
                break
            }
        }
    }

    fun removeMessage(messageId: Int) {
        var isBreak = false
        for (chat in chats) {
            for (i in chat.messages.indices) {
                if (chat.messages[i].messageId == messageId) {
                    val user1Id = chat.user1Id
                    val user2Id = chat.user1Id
                    var isLastMessage = true
                    for (j in i until chat.messages.size) {
                        if (chat.messages[j].userId == user1Id || chat.messages[j].userId == user2Id) {
                            isLastMessage = false
                            break
                        }
                    }
                    if (isLastMessage) {
                        removeChat(chat.id)
                    } else {
                        chat.messages = remove(chat.messages, i)
                    }
                    isBreak = true
                    break
                }
            }
            if (isBreak) {
                break
            }
        }
    }

    private fun remove(arr: Array<Chat>, index: Int){
        val result = arr.toMutableList()
        result.removeAt(index)
        chats = result.toTypedArray()
    }

    private fun remove(arr: Array<Message>, index: Int): Array<Message> {
        if (index < 0 || index >= arr.size) {
            return arr
        }

        val result = arr.toMutableList()
        result.removeAt(index)
        return result.toTypedArray()
    }

    private fun getChat(chatId: Int) {
        for (chat in chats) {
            if (chat.id == chatId) {
                println(UserService.getUser(chat.user1Id)?.name + " messaged to " + UserService.getUser(chat.user2Id)?.name)
                println()
                for (message in chat.messages) {
                    message.messageSeen = true
                    println(UserService.getUser(message.userId)?.name)
                    println(message.text)
                    println()
                }
                break
            }
        }
    }

    fun getChats() {
        for (chat in chats) {
            println("----------------")
            getChat(chat.id)
        }
    }

    fun getChats(userId: Int) {
        for (chat in chats) {
            if (chat.user1Id == userId || chat.user2Id == userId) {
                println("----------------")
                getChat(chat.id)
            }
        }
    }

    fun getUnreadChatsCount(): Int {
        var unreadMessages = 0

        for (chat in chats) {
            for (message in chat.messages) {
                if (!message.messageSeen) {
                    unreadMessages++
                }
            }
        }
        return unreadMessages
    }

    fun createMessage(chatId: Int, message: Message) {
        for (chat in chats) {
            if (chat.id == chatId) {
                chat.messages = chat.messages.plus(message)
            }
        }
    }

    fun editMessage(message: Message, newText: String) {
        message.text = newText
    }

    @Test
    fun addChatPass() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        UserService.addUser(user1)
        UserService.addUser(user2)

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        addChat(chat12)

        assertEquals(chats.last(), chat12)
    }

    @Test
    fun addChatFail() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))
        val chat21 = Chat(121, user2.id, user1.id, arrayOf(message21, message12))

        addChat(chat12)

        assertEquals(chats.last(), chat21)
    }

    @Test
    fun removeChatPass() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))
        val chat21 = Chat(121, user2.id, user1.id, arrayOf(message21, message12))

        addChat(chat12)
        addChat(chat21)

        removeChat(chat21.id)

        assertEquals(chats.last(), chat12)
    }

    @Test
    fun removeChatFail() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))
        val chat21 = Chat(121, user2.id, user1.id, arrayOf(message21, message12))

        addChat(chat12)
        addChat(chat21)

        removeChat(chat21.id)

        assertEquals(chats.last(), chat21)
    }

    @Test
    fun removeMessagePass() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        removeMessage(112)

        assertEquals(chat12.messages.last(), message21)
    }

    @Test
    fun removeMessageFail() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        removeMessage(112)

        assertEquals(chat12.messages.last(), message12)
    }

    @Test
    fun createMessagePass() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")
        val newMessage = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        createMessage(112, newMessage)

        assertEquals(chat12.messages.last(), newMessage)
    }

    @Test
    fun createMessageFail() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")
        val newMessage = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        createMessage(112, newMessage)

        assertEquals(chat12.messages.last(), 21)
    }

    @Test
    fun editMessagePass() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        editMessage(message12, "Hello User2! This is edited message")

        assertEquals(message12.text, "Hello User2! This is edited message")
    }
    
    @Test
    fun editMessageFail() {
        val user1 = User(11, "User1")
        val user2 = User(12, "User2")

        val message12 = Message(112, 11, "Hello User2!")
        val message21 = Message(121, 12, "Hello User1!")

        val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))

        editMessage(message12, "Hello User2! This is edited message")

        assertEquals(message12.text, "Hello User2!")
    }
}