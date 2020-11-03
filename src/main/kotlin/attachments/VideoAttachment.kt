package attachments

import types.Video

class VideoAttachment(
        val audio: Video
): Attachment {
    override val type: String = "Video"
}