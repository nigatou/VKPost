package services

import mainClasses.Chat
import mainClasses.Message

object ChatService {
    private var chats = emptyArray<Chat>()

    fun addChat(chat: Chat): Boolean {
        for (uniqueChat in chats) {
            if (uniqueChat.id == chat.id) {
                return false
            }
        }
        chats += chat
        return true
    }

    fun removeChat(chatId: Int) {
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
}