import kotlin.math.roundToInt

object WallService {
    private var posts = emptyArray<Post>()

    private fun findPost(post: Post, posts: Array<Post>): Boolean {
        for (uniquePost in posts) {
            if (uniquePost.id == post.id) {
                return false
            }
        }
        return true
    }

    private fun getPostIndex(post: Post): Int {
        for (i in posts.indices) {
            if (post == posts[i]) {
                return i
            }
        }
        return -1
    }

    fun add(post: Post): Post {
        var postIsUnique = false

        while (!postIsUnique) {
            postIsUnique = findPost(post, posts)
            if (!postIsUnique) {
                post.id = (Math.random() * 100000).roundToInt()
            }
        }

        posts += post
        return posts.last()
    }

    fun update(post: Post, newPost: Post): Boolean {
        if (findPost(post, posts)) {
            val id = post.id
            val date = post.date

            val index = getPostIndex(post)

            if (index != -1) {
                newPost.id = id
                newPost.date = date
                posts[index] = newPost
                return true
            }
        }
        return false
    }
}