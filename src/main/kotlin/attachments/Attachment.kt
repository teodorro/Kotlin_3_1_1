package attachments

sealed class Attachment {
    abstract val type: String
}

data class AudioAttachment(override val type: String, val audio: Audio) : Attachment()
data class VideoAttachment(override val type: String, val video: Video) : Attachment()
data class DocAttachment(override val type: String, val doc: Document) : Attachment()
data class PhotoAttachment(override val type: String, val photo: Photo) : Attachment()
data class NoteAttachment(override val type: String, val note: Note) : Attachment()