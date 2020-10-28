import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    private var posts = emptyArray<Post>()

    private val post1 = Post(
        1,
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
}