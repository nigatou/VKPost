import kotlin.math.roundToInt

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    private fun findPost(post: Post, posts: Array<Post>): Boolean {
        for (uniquePost in posts) {
            if (uniquePost.id == post.id) {
                return false
            }
        }
        return true
    }

    private fun findPostById(postId: Int, posts: Array<Post>): Boolean {
        for (uniquePost in posts) {
            if (uniquePost.id == postId) {
                return false
            }
        }
        return true
    }

    private fun findPostIndexById(post: Post, posts: Array<Post>): Int {
        for (i in posts.indices) {
            if (posts[i].id == post.id) {
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

    fun update(newPost: Post): Boolean {
        val index = findPostIndexById(newPost, posts)

        if (index != -1) {
            newPost.id = posts[index].id
            newPost.date = posts[index].date
            posts[index] = newPost
            return true
        }
        return false
    }

    fun createComment(comment: Comment) {
        if (findPostById(comment.postId!!, posts)) {
            comments += comment
        } else {
            val postNotFoundException = PostNotFoundException()
            postNotFoundException.throwException()
        }
    }
}