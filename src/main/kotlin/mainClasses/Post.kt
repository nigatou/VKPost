package mainClasses

data class Post(
        val ownerId: Int?,
        val fromId: Int?,
        val createdBy: Int?,
        var date: Int?,
        val text: String?,
        val replyOwnerId: Int?,
        val replyPostId: Int?,
        val friendsOnly: Boolean?,
        val comments: Array<String>?,
        val copyright: String?,
        val likes: Array<Int>?,
        val reposts: Array<Int>?,
        val views: Array<Int>?,
        val postType: String?,
        val signerId: Int?,
        val canPin: Boolean?,
        val canDelete: Boolean?,
        val canEdit: Boolean?,
        val isPinned: Boolean?,
        val markedAsAds: Boolean?,
        val isFavourite: Boolean?,
        val postponedId: Int?,
        var id: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post
        if (
                ownerId != other.ownerId ||
                fromId != other.fromId ||
                createdBy != other.createdBy ||
                date != other.date ||
                text != other.text ||
                replyOwnerId != other.replyOwnerId ||
                replyPostId != other.replyPostId ||
                friendsOnly != other.friendsOnly ||
                !comments.contentEquals(other.comments) ||
                copyright != other.copyright ||
                !likes.contentEquals(other.likes) ||
                !reposts.contentEquals(other.reposts) ||
                !views.contentEquals(other.views) ||
                postType != other.postType ||
                signerId != other.signerId ||
                canPin != other.canPin ||
                canDelete != other.canDelete ||
                canEdit != other.canEdit ||
                isPinned != other.isPinned ||
                markedAsAds != other.markedAsAds ||
                isFavourite != other.isFavourite ||
                postponedId != other.postponedId ||
                id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        var result = ownerId
        result = 31 * result!! + fromId!!
        result = 31 * result + createdBy!!
        result = 31 * result + date!!
        result = 31 * result + text.hashCode()
        result = 31 * result + replyOwnerId!!
        result = 31 * result + replyPostId!!
        result = 31 * result + friendsOnly.hashCode()
        result = 31 * result + comments.contentHashCode()
        result = 31 * result + copyright.hashCode()
        result = 31 * result + likes.contentHashCode()
        result = 31 * result + reposts.contentHashCode()
        result = 31 * result + views.contentHashCode()
        result = 31 * result + postType.hashCode()
        result = 31 * result + signerId!!
        result = 31 * result + canPin.hashCode()
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + isPinned.hashCode()
        result = 31 * result + markedAsAds.hashCode()
        result = 31 * result + isFavourite.hashCode()
        result = 31 * result + postponedId!!
        result = 31 * result + id
        return result
    }
}
