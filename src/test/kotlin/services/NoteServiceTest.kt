@file:Suppress("DEPRECATION")

package services

import attachments.Attachment
import junit.framework.Assert.assertEquals
import mainClasses.Comment
import mainClasses.Note
import org.junit.Test

import kotlin.math.floor
import kotlin.math.roundToInt

class NoteServiceTest {

    private var notes = emptyArray<Note>()
    private var comments = emptyArray<Comment>()

    private fun add(title: String, text: String) {
        notes += Note(floor(Math.random() * 100000).roundToInt(), title, text, false)
    }

    private fun createComment(noteId: Int, message: String, attachments: Array<Attachment>) {
        comments += Comment((Math.random() * 100000).roundToInt(), noteId, message, attachments, false)
    }

    private fun delete(noteId: Int) {
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

    private fun deleteComment(commentId: Int) {
        for (comment in comments) {
            if (comment.id == commentId) {
                comment.deleted = true
                return
            }
        }
    }

    private fun edit(noteId: Int, title: String, text: String) {
        for (note in notes) {
            if (note.id == noteId && !note.deleted) {
                note.title = title
                note.text = text
                return
            }
        }
    }

    private fun editComment(commentId: Int, message: String, attachments: Array<Attachment>) {
        for (comment in comments) {
            if (comment.id == commentId) {
                comment.message = message
                comment.attachments = attachments
            }
        }
    }

    private fun getById(noteId: Int): Note? {
        for (note in notes) {
            if (note.id == noteId) {
                return note
            }
        }
        return null
    }

    private fun restoreComment(commentId: Int) {
        for (comment in comments) {
            if (comment.id == commentId && !getById(comment.parentId!!)?.deleted!!) {
                comment.deleted = false
                return
            }
        }
    }

    @Test
    fun shouldAddPass() {
        add("Note 1", "Note 1 text")
        assertEquals(notes.last().title, "Note 1")
    }

    @Test
    fun shouldAddFail() {
        add("Note 2", "Note 2 text")
        assertEquals(notes.last().title, "Note 1")
    }

    @Test
    fun shouldDeletePass() {
        add("Note 1", "Note 1 text")
        delete(notes.last().id)
        assertEquals(notes.last().deleted, true)
    }

    @Test
    fun shouldDeleteFail() {
        add("Note 2", "Note 2 text")
        delete(notes.last().id)
        assertEquals(notes.last().deleted, false)
    }

    @Test
    fun shouldDeleteCommentPass() {
        add("Note 1", "Note 1 text")
        createComment(notes.last().id, "Message", emptyArray())
        deleteComment(comments.last().id!!)
        assertEquals(comments.last().deleted, true)
    }

    @Test
    fun shouldDeleteCommentFail() {
        add("Note 1", "Note 1 text")
        createComment(notes.last().id, "Message", emptyArray())
        deleteComment(comments.last().id!!)
        assertEquals(comments.last().deleted, false)
    }

    @Test
    fun shouldEditPass1() {
        add("Note 1", "Note 1 text")
        edit(notes.last().id, "New title 1", "New text 1")
        assertEquals(notes.last().title, "New title 1")
    }

    @Test
    fun shouldEditPass2() {
        add("Note 2", "Note 2 text")
        edit(notes.last().id, "New title 2", "New text 2")
        assertEquals(notes.last().title, "New title 2")
    }

    @Test
    fun shouldEditFail() {
        add("Note 1", "Note 1 text")
        edit(notes.last().id, "New title", "New text")
        assertEquals(notes.last().title, "Note 1")
    }

    @Test
    fun shouldEditCommentPass1() {
        add("Note 1", "Note 1 text")
        createComment(notes.last().id, "Note 1 comment", emptyArray())
        editComment(comments.last().id!!, "New comment 1", emptyArray())
        assertEquals(comments.last().message, "New comment 1")
    }

    @Test
    fun shouldEditCommentPass2() {
        add("Note 2", "Note 2 text")
        createComment(notes.last().id, "Note 2 comment", emptyArray())
        editComment(comments.last().id!!, "New comment 2", emptyArray())
        assertEquals(comments.last().message, "New comment 2")
    }

    @Test
    fun shouldEditCommentFail() {
        add("Note 1", "Note 1 text")
        createComment(notes.last().id, "Note 1 comment", emptyArray())
        editComment(comments.last().id!!, "New comment", emptyArray())
        assertEquals(comments.last().message, "Note 1 comment")
    }

    @Test
    fun shouldRestoreCommentPass() {
        add("Note 1", "Note 1 text")
        createComment(notes.last().id, "Note 1 comment", emptyArray())
        deleteComment(comments.last().id!!)
        restoreComment(comments.last().id!!)
        assertEquals(comments.last().deleted, false)
    }

    @Test
    fun shouldRestoreCommentFail() {
        add("Note 1", "Note 1 text")
        createComment(notes.last().id, "Note 1 comment", emptyArray())
        deleteComment(comments.last().id!!)
        restoreComment(comments.last().id!!)
        assertEquals(comments.last().deleted, true)
    }
}