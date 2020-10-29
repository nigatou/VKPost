import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    private var posts = emptyArray<Post>()

    private val post1 = Post(
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
    private val post2 = Post(
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
    val post3 = Post(
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
    val post4 = Post(
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
    val post5 = Post(
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
    val post6 = Post(
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

    @Test
    fun add1() {
        posts += post1

        assertEquals(posts.last(), post1)
    }

    @Test
    fun add2() {
        posts += post2

        assertEquals(posts.last(), post2)
    }

    @Test
    fun add3() {
        posts += post3

        assertEquals(posts.last(), post3)
    }

    @Test
    fun add4Fail() {
        posts += post4

        assertEquals(posts.last(), post2)
    }

    @Test
    fun update2() {
        posts += post2
        WallService.update(post2, post5)

        assertEquals(posts.last(), post2)
    }

    @Test
    fun update4Fail() {
        posts += post4
        WallService.update(post4, post6)

        assertEquals(posts.last(), post3)
    }
}