package net.codebot.application

import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class SideToolBarTest {
    private val model: Model = Model()
    private val sideBarTest: SideToolBar = SideToolBar(model)

    @Test
    fun mouseClickCreate() {
        val mouseClicked = MouseEvent(
            MouseEvent.MOUSE_CLICKED,
            0.0, 0.0, 0.0, 0.0,
            MouseButton.PRIMARY, 1,
            false, false,
            false, true, false,
            false, false, false,
            false, false, null
        )
        sideBarTest.getCreateButton().fireEvent(mouseClicked)
        var expectedCoursesize = 1
        var actualSavedState = model.getNotesList().size
        Assertions.assertEquals(
            expectedCoursesize,
            actualSavedState,
            "Testing to see if create note works when there is an empty note list."
        )
        // Fire once
        sideBarTest.getCreateButton().fireEvent(mouseClicked)

        // Test for  creating when there is more than one instance
        sideBarTest.getCreateButton().fireEvent(mouseClicked)
        expectedCoursesize = 3
        actualSavedState = model.getNotesList().size
        Assertions.assertEquals(
            expectedCoursesize,
            actualSavedState,
            "Testing to see if create note correctly updates the notes list " +
                    "when there is more than one instance present."
        )
    }
}