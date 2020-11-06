import attachments.Attachment
import attachments.AudioAttachment
import attachments.VideoAttachment
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

    val comment1 = Comment(1, post1.id, "Hello!", emptyArray())
    val comment2 = Comment(2, post3.id, "Very nice!", emptyArray())
    val comment3 = Comment(3, post3.id, "Eeew", emptyArray())
    val comment4 = Comment(4, post4.id, "Original, very", emptyArray())

    WallService.createComment(comment1)
    WallService.createComment(comment2)
    WallService.createComment(comment3)
    WallService.createComment(comment4)
}