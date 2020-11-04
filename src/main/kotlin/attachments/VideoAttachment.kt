package attachments

import types.Video

class VideoAttachment(
        val video: Video,
        override val type: String = "Video",
): Attachment