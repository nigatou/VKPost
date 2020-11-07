package services

import attachments.Attachment
import mainClasses.Comment
import mainClasses.Note
import kotlin.math.floor
import kotlin.math.roundToInt

object NoteService {
    private var notes = emptyArray<Note>()
    private var comments = emptyArray<Comment>()

    fun add(title: String, text: String) {
        notes += Note(floor(Math.random() * 100000).roundToInt(), title, text, false)
    }

    fun createComment(noteId: Int, message: String, attachments: Array<Attachment>) {
        comments += Comment((Math.random() * 100000).roundToInt(), noteId, message, attachments, false)
    }

    fun delete(noteId: Int) {
        for (comment in comments) {
            if (comment.parentId == noteId) {
                deleteComment(comment.id!!)
            }
        }
        for (note in notes) {
            if (note.id == noteId) {
                note.deleted = true
            }
        }

    }

    fun deleteComment(commentId: Int) {
        for (comment in comments) {
            if (comment.id == commentId) {
                comment.deleted = true
                return
            }
        }
    }

    fun edit(noteId: Int, title: String, text: String) {
        for (note in notes) {
            if (note.id == noteId && !note.deleted) {
                note.title = title
                note.text = text
                return
            }
        }
    }

    fun editComment(commentId: Int, message: String, attachments: Array<Attachment>) {
        for (comment in comments) {
            if (comment.id == commentId) {
                comment.message = message
                comment.attachments = attachments
            }
        }
    }

    fun get(): Array<Note> {
        return notes
    }

    private fun getById(noteId: Int): Note? {
        for (note in notes) {
            if (note.id == noteId) {
                return note
            }
        }
        return null
    }

    fun getComments(noteId: Int): Array<Comment> {
        var commentsOfNote = emptyArray<Comment>()

        for (comment in comments) {
            if (comment.parentId == noteId) {
                commentsOfNote += comment
            }
        }
        return commentsOfNote
    }

    fun restoreComment(commentId: Int) {
        for (comment in comments) {
            if (comment.id == commentId && !getById(comment.parentId!!)?.deleted!!) {
                comment.deleted = false
                return
            }
        }
    }

    fun showNote(noteId: Int) {
        for (note in notes) {
            if (note.id == noteId && !note.deleted) {
                println("Note title: " + note.title)
                println("Note text: " + note.text)
                println("")
                for (comment in getComments(noteId)) {
                    if (!comment.deleted!!) {
                        println("Comment text: " + comment.message)
                    }
                }
                println("")
            }
        }
    }
}