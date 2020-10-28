class Post(
        val id: Int,
        val ownerId: Int,
        val fromId: Int,
        val createdBy: Int,
        val date: Int,
        val text: String,
        val replyOwnerId: Int,
        val replyPostId: Int,
        val friendsOnly: Boolean,
        val comments: Array<String>,
        val copyright: String,
        val likes: Array<Int>,
        val reposts: Array<Int>,
        val views: Array<Int>,
        val postType: String,
        val signerId: Int,
        val canPin: Boolean,
        val canDelete: Boolean,
        val canEdit: Boolean,
        val isPinned: Boolean,
        val markedAsAds: Boolean,
        val isFavourite: Boolean,
        val postponedId: Int
)