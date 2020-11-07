import attachments.Attachment
import attachments.AudioAttachment
import attachments.VideoAttachment
import mainClasses.Comment
import mainClasses.Post
import services.NoteService
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
}