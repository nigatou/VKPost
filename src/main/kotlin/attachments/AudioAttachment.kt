package attachments

import types.Audio

class AudioAttachment(
        val audio: Audio,
        override val type: String = "Audio"
): Attachment