import mainClasses.Post
import org.junit.Test

import org.junit.Assert.*
import services.WallService
import kotlin.math.roundToInt

class WallServiceTest {

    private var posts = emptyArray<Post>()

    private val post = Post(
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

    private val post1 = post.copy(id = (Math.random() * 100000).roundToInt())
    private val post2 = post.copy(id = (Math.random() * 100000).roundToInt())
    private val post3 = post.copy(id = (Math.random() * 100000).roundToInt())
    private val post4 = post.copy(id = (Math.random() * 100000).roundToInt())
    private val post5 = post.copy(id = (Math.random() * 100000).roundToInt())
    private val post6 = post.copy(id = (Math.random() * 100000).roundToInt())

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
        WallService.update(post5)

        assertEquals(posts.last(), post2)
    }

    @Test
    fun update4Fail() {
        posts += post4
        WallService.update(post6)

        assertEquals(posts.last(), post3)
    }
}