package attachments

import types.Audio

class AudioAttachment(
        val audio: Audio
): Attachment {
    override val type: String = "Audio"
}