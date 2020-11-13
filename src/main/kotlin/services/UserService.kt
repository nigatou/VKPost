package services

import mainClasses.User

object UserService {
    private var users = emptyArray<User>()

    fun addUser(user: User): Boolean {
        for (uniqueUser in users) {
            if (uniqueUser.id == user.id) {
                return false
            }
        }
        users += user
        return true
    }

    fun getUser(userId: Int): User? {
        for (user in users) {
            if (user.id == userId) {
                return user
            }
        }
        return null
    }
}