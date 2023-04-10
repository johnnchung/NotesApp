package net.codebot.application

import javafx.scene.control.TextField
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class NotesBlockTest {
    var model: Model = Model()


    @Test
    fun duplicateCourseTest(){
        // Add some notes to the model
        model.createNote("test1","test2","somerandomstuff")
        model.createNote("test1","test3","cs241 maximal munch")
        model.createNote("test2","test2","test4")

        var expectedNotesLength = 2
        var actualNotesLength = model.getNotesList().size
        Assertions.assertEquals(
            expectedNotesLength,
            actualNotesLength,
            "The length should be 2 since there was a duplicate added to the model."
        )


    }

    @Test
    fun createAndDeleteCourseTest(){
        // Add some notes to the model
        model.createNote("test1","test2","somerandomstuff")
        model.createNote("test1","test3","cs241 maximal munch")
        model.createNote("test2","test2","test4")

        // Grab note delete block for the first item
        var testNoteBlockChildren =  model.getNotesList()[0].getDeleteOpenBlock().children[0] as Glyph

        Assertions.assertNotNull(testNoteBlockChildren)

        val mouseClicked = MouseEvent(
            MouseEvent.MOUSE_CLICKED,
            0.0, 0.0, 0.0, 0.0,
            MouseButton.PRIMARY, 1,
            false, false,
            false, true, false,
            false, false, false,
            false, false, null
        )

        // Simulate delete click
        testNoteBlockChildren.fireEvent(mouseClicked)

        var expectedNotesLength = 1
        var actualNotesLength = model.getNotesList().size
        Assertions.assertEquals(
            expectedNotesLength,
            actualNotesLength,
            "The length should be 1 since we deleted an instance."
        )

        // This test is for deleting multiple items
        model.createNote("test1","test2","test4")
        model.createNote("test3","test1","HELLOWORLD")
        model.createNote("test4","test4","some joke related to the number 4")
        model.createNote("test5","test5","sfakjjksfljlksfaljkfsajkalkjsjk")

        Assertions.assertEquals(
            5,
            model.getNotesList().size,
            "The new length should be 5 since we inserted 4 items."
        )

        // Grab note  delete block for the first 3 items
        var testNoteBlockChildren1 =  model.getNotesList()[0].getDeleteOpenBlock().children[0] as Glyph
        var testNoteBlockChildren2 =  model.getNotesList()[1].getDeleteOpenBlock().children[0] as Glyph
        var testNoteBlockChildren3 =  model.getNotesList()[2].getDeleteOpenBlock().children[0] as Glyph
        // Trigger their deletes
        testNoteBlockChildren1.fireEvent(mouseClicked)
        testNoteBlockChildren2.fireEvent(mouseClicked)
        testNoteBlockChildren3.fireEvent(mouseClicked)

        expectedNotesLength = 2
        actualNotesLength = model.getNotesList().size
        Assertions.assertEquals(
            expectedNotesLength,
            actualNotesLength,
            "The length should be 2  since there are two instances left."
        )
    }

    @Test
    fun editAndDeleteCoursesTest(){
        // Add some notes to the model
        model.createNote("test1","test2","somerandomstuff")
        model.createNote("test4","test3","cs241 maximal munch")
        model.createNote("test2","test2","test4")

        // Grab note delete block for the second item
        var testNoteBlockChildren =  model.getNotesList()[1].getDeleteOpenBlock().children[0] as Glyph

        // Simulate mouse click
        val mouseClicked = MouseEvent(
            MouseEvent.MOUSE_CLICKED,
            0.0, 0.0, 0.0, 0.0,
            MouseButton.PRIMARY, 1,
            false, false,
            false, true, false,
            false, false, false,
            false, false, null
        )


        // Retrieve title edit block for first instance
        var testTitleEditBlock = model.getNotesList()[0].getCombinedTitleGroupBlock().children[0] as HBox
        var titleEditBlock =  testTitleEditBlock.children[1] as Glyph


        // Trigger edit and delete for seperate note blocks
        testNoteBlockChildren.fireEvent(mouseClicked)
        titleEditBlock.fireEvent(mouseClicked)

        // The delete and open icons should be disabled, edit for this block should be enabled
        var testDeleteIcon =  model.getNotesList()[0].getDeleteOpenBlock().children[0] as Glyph
        var testOpenIcon = model.getNotesList()[0].getDeleteOpenBlock().children[1] as Glyph
        var editableState = testTitleEditBlock.children[0] as TextField


        Assertions.assertTrue(editableState.isEditable)
        Assertions.assertTrue(testDeleteIcon.isDisable)
        Assertions.assertTrue(testOpenIcon.isDisable)

        // The length should have decreased by 1
        var expectedNotesLength = 2
        var actualNotesLength = model.getNotesList().size
        Assertions.assertEquals(
            expectedNotesLength,
            actualNotesLength,
            "The length should be 2 since we deleted an instance."
        )
    }
}