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
}