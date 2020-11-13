import attachments.Attachment
import attachments.AudioAttachment
import attachments.VideoAttachment
import mainClasses.*
import services.ChatService
import services.NoteService
import services.UserService
import services.WallService
import types.Audio
import types.Video
import kotlin.math.roundToInt

fun main() {
    val post = Post(
            1,
            1,
            1,
            1,
            "hello",
            1,
            1,
            true,
            emptyArray(),
            "Hello",
            emptyArray(),
            emptyArray(),
            emptyArray(),
            "Hello",
            1,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = true,
            isFavourite = true,
            postponedId = 1,
    )

    val post1 = post.copy(id = (Math.random() * 100000).roundToInt())
    val post2 = post.copy(id = (Math.random() * 100000).roundToInt())
    val post3 = post.copy(id = (Math.random() * 100000).roundToInt())
    val post4 = post.copy(id = (Math.random() * 100000).roundToInt())
    val post5 = post.copy(id = (Math.random() * 100000).roundToInt())
    val post6 = post.copy(id = (Math.random() * 100000).roundToInt())

    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
    WallService.add(post4)
    WallService.update(post5)
    WallService.update(post6)

    var attachedPosts = emptyArray<Attachment>()
    val audio = Audio(1, "Audio",1)
    val video = Video(2, "Video",1)
    val audioAttachment = AudioAttachment(audio)
    val videoAttachment = VideoAttachment(video)
    attachedPosts += audioAttachment
    attachedPosts += videoAttachment

    val comment1 = Comment((Math.random() * 100000).roundToInt(), post1.id, "Hello!", emptyArray(), false)
    val comment2 = Comment((Math.random() * 100000).roundToInt(), post3.id, "Very nice!", emptyArray(), false)
    val comment3 = Comment((Math.random() * 100000).roundToInt(), post3.id, "Eeew", emptyArray(), false)
    val comment4 = Comment((Math.random() * 100000).roundToInt(), post4.id, "Original, very", emptyArray(), false)

    WallService.createComment(comment1)
    WallService.createComment(comment2)
    WallService.createComment(comment3)
    WallService.createComment(comment4)

    NoteService.add("Note 1", "Text of note 1")
    NoteService.add("Note 2", "Text of note 2")
    NoteService.add("Note 3", "Text of note 3")
    NoteService.add("Note 4", "Text of note 4")
    NoteService.add("Note 5", "Text of note 5")
    NoteService.add("Note 6", "Text of note 6")

    val note1Id = NoteService.get()[0].id
    val note2Id = NoteService.get()[1].id
    val note3Id = NoteService.get()[2].id
    val note4Id = NoteService.get()[3].id
    val note5Id = NoteService.get()[4].id
    val note6Id = NoteService.get()[5].id

    NoteService.createComment(note1Id, "Note 1 comment 1", emptyArray())
    NoteService.createComment(note1Id, "Note 1 comment 2", emptyArray())
    NoteService.createComment(note2Id, "Note 2 comment 1", emptyArray())
    NoteService.createComment(note2Id, "Note 2 comment 2", emptyArray())
    NoteService.createComment(note3Id, "Note 3 comment 1", emptyArray())
    NoteService.createComment(note3Id, "Note 3 comment 2", emptyArray())
    NoteService.createComment(note4Id, "Note 4 comment 1", emptyArray())
    NoteService.createComment(note4Id, "Note 4 comment 2", emptyArray())
    NoteService.createComment(note5Id, "Note 5 comment 1", emptyArray())
    NoteService.createComment(note5Id, "Note 5 comment 2", emptyArray())
    NoteService.createComment(note6Id, "Note 6 comment 1", emptyArray())
    NoteService.createComment(note6Id, "Note 6 comment 2", emptyArray())

    NoteService.delete(note1Id)
    NoteService.delete(note3Id)

    NoteService.deleteComment(NoteService.getComments(note5Id)[0].id!!)
    NoteService.deleteComment(NoteService.getComments(note6Id)[1].id!!)

    NoteService.edit(note1Id, "This is new Note 1", "This is new text of Note 1")
    NoteService.edit(note2Id, "This is new Note 2", "This is new text of Note 2")

    NoteService.editComment(NoteService.getComments(note4Id)[0].id!!, "This is new Note 4 comment 1", emptyArray())
    NoteService.editComment(NoteService.getComments(note4Id)[1].id!!, "This is new Note 4 comment 2", emptyArray())

    val notes = NoteService.get()

    NoteService.restoreComment(NoteService.getComments(note1Id)[0].id!!)
    NoteService.restoreComment(NoteService.getComments(note3Id)[1].id!!)
    NoteService.restoreComment(NoteService.getComments(note5Id)[1].id!!)

    for (note in notes) {
        NoteService.showNote(note.id)
    }

    val user1 = User(11, "User1")
    val user2 = User(12, "User2")
    val user3 = User(13, "User3")
    val user4 = User(14, "User4")
    val user5 = User(15, "User5")
    val user6 = User(16, "User6")

    UserService.addUser(user1)
    UserService.addUser(user2)
    UserService.addUser(user3)
    UserService.addUser(user4)
    UserService.addUser(user5)
    UserService.addUser(user6)

    val message12 = Message(112, 11, "Hello User2!")
    val message14 = Message(114, 11, "Hello User4!")
    val message21 = Message(121, 12, "Hello User1!")
    val message23 = Message(123, 12, "Hello User3!")
    val message32 = Message(132, 13, "Hello User2!")
    val message35 = Message(135, 13, "Hello User5!")
    val message41 = Message(141, 14, "Hello User1!")
    val message46 = Message(146, 14, "Hello User6!")
    val message53 = Message(153, 15, "Hello User3!")
    val message56 = Message(156, 15, "Hello User6!")
    val message64 = Message(164, 16, "Hello User4!")
    val message65 = Message(165, 16, "Hello User5!")

    val chat12 = Chat(112, user1.id, user2.id, arrayOf(message12, message21))
    val chat14 = Chat(114, user1.id, user4.id, arrayOf(message14, message41))
    val chat23 = Chat(123, user2.id, user3.id, arrayOf(message23))
    val chat35 = Chat(135, user3.id, user5.id, arrayOf(message35, message53))
    val chat46 = Chat(146, user4.id, user6.id, arrayOf(message46, message64))
    val chat56 = Chat(156, user5.id, user6.id, arrayOf(message56))

    ChatService.addChat(chat12)
    ChatService.addChat(chat14)
    ChatService.addChat(chat23)
    ChatService.addChat(chat35)
    ChatService.addChat(chat46)
    ChatService.addChat(chat56)

    ChatService.createMessage(123, message32)
    ChatService.createMessage(156, message65)

    println("------------------------------------------------------------------------------")

    println("You have " + ChatService.getUnreadChatsCount().toString() + " unread messages")

    ChatService.getChats()

    println("You have " + ChatService.getUnreadChatsCount().toString() + " unread messages")

    println("------------------------------------------------------------------------------")

    ChatService.getChats(11)

    println("------------------------------------------------------------------------------")

    ChatService.editMessage(message12, "Hello, User2! This is edited message")
    ChatService.editMessage(message46, "Hello, User6! This is edited message")

    ChatService.getChats()

    println("------------------------------------------------------------------------------")

    ChatService.removeChat(114)

    ChatService.getChats()

    println("------------------------------------------------------------------------------")

    ChatService.removeMessage(123)
    ChatService.removeMessage(164)

    ChatService.getChats()
}