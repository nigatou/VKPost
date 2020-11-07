package mainClasses

import attachments.Attachment

class Comment(
        val id: Int?,
        val parentId: Int?,
        var message: String?,
        var attachments: Array<Attachment>,
        var deleted: Boolean?,
)