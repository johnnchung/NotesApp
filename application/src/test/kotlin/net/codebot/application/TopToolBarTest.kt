package net.codebot.application

import javafx.event.Event
import javafx.event.EventType
import javafx.scene.control.CheckMenuItem
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class TopToolBarTest {
    private val model: Model = Model()
    // Mock model and toptoolbar initialization
    val getNotes = model.get()
    val notesData = Json.decodeFromString<DataClass>(getNotes)
    private val topToolBarTest: TopToolBar = TopToolBar(model,notesData)

    @Test
    fun testDarkModeEnable(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        var darkModeItem = ellipseButton.items[3] as CheckMenuItem
        Assertions.assertNotNull(darkModeItem)

        // Set darkmode item before fire
        darkModeItem.isSelected = true
        darkModeItem.fire()
       Assertions.assertTrue(model.defaultDarkMode)
    }

    @Test
    fun testDarkModeDisable(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        var darkModeItem = ellipseButton.items[3] as CheckMenuItem
        Assertions.assertNotNull(darkModeItem)

        // Set darkmode item before fire
        darkModeItem.isSelected = false
        darkModeItem.fire()
        Assertions.assertFalse(model.defaultDarkMode)
    }

    @Test
    fun testAutoSaveEnable(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        var autoSaveItem = ellipseButton.items[4] as CheckMenuItem
        Assertions.assertNotNull(autoSaveItem)

        // Set save item before fire
        autoSaveItem.isSelected = true
        autoSaveItem.fire()
        Assertions.assertTrue(model.autoSave)
    }

    @Test
    fun testAutoSaveDisable(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        var autoSaveItem = ellipseButton.items[4] as CheckMenuItem
        Assertions.assertNotNull(autoSaveItem)

        // Set save item before fire
        autoSaveItem.isSelected = false
        autoSaveItem.fire()
        Assertions.assertFalse(model.autoSave)
    }

    @Test
    fun testGroupingEnable(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        var groupItem = ellipseButton.items[2] as CheckMenuItem
        Assertions.assertNotNull(groupItem)

        // Set grouping value before firing
        groupItem.isSelected = true
        groupItem.fire()
        Assertions.assertTrue(model.defaultgroup)
    }

    @Test
    fun testGroupingDisable(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        var groupItem = ellipseButton.items[2] as CheckMenuItem
        Assertions.assertNotNull(groupItem)

        // Set grouping value before firing
        groupItem.isSelected = false
        groupItem.fire()
        Assertions.assertFalse(model.defaultgroup)
    }

    @Test
    fun testSorting(){
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)
        var sortItem = ellipseButton.items[0]
        Assertions.assertNotNull(sortItem)

        // Test sort by Title
        sortItem.fire()
        var expectedSortState = "Title"
        var actualSortState = model.defaultsort
        Assertions.assertEquals(
            expectedSortState,
            actualSortState,
            "The sort state should be the Title."
        )

        sortItem = ellipseButton.items[1]
        Assertions.assertNotNull(sortItem)

        // Test sort by Date
        sortItem.fire()
        expectedSortState = "Date"
        actualSortState = model.defaultsort
        Assertions.assertEquals(
            expectedSortState,
            actualSortState,
            "The sort state should be the Date."
        )
    }

    @Test
    fun testSearchBar(){
        var searchQuery = topToolBarTest.getSearchBar()
        // First test to see if searchButton exists
        Assertions.assertNotNull(searchQuery)

        // Simulate typing
        searchQuery.text = "Hello"
     // Trigger the textProperty listener
        searchQuery.fireEvent(Event(EventType.ROOT))

        var expectedSearchQuery = "Hello"
        var actualSearchQuery = model.searchval
        Assertions.assertEquals(
            expectedSearchQuery,
            actualSearchQuery,
            "The search value should currently be Hello."
        )

        // Simulate resetting the search state
        searchQuery.text = ""
        // Trigger the textProperty listener
        searchQuery.fireEvent(Event(EventType.ROOT))

        expectedSearchQuery = ""
        actualSearchQuery = model.searchval
        Assertions.assertEquals(
            expectedSearchQuery,
            actualSearchQuery,
            "The search value should currently be Hello."
        )
    }

    @Test
    fun TestMultipleChecks(){
        // This test will see if we can have dark mode enabled + grouping enabled + sorting by title
        var ellipseButton = topToolBarTest.getEllipse()
        // First test to see if ellipseButton exists
        Assertions.assertNotNull(ellipseButton)

        // Retrieve button instances
        var sortItem = ellipseButton.items[0]
        var groupItem = ellipseButton.items[2] as CheckMenuItem
        var darkModeItem = ellipseButton.items[3] as CheckMenuItem

        // Trigger the events of each option
        groupItem.isSelected = true
        groupItem.fire()
        darkModeItem.isSelected = true
        darkModeItem.fire()
        sortItem.fire()

        var expectedSortState = "Title"
        var actualSortState = model.defaultsort
        Assertions.assertEquals(
            expectedSortState,
            actualSortState,
            "The sort state should be the Title."
        )
        Assertions.assertTrue(model.defaultgroup)
        Assertions.assertTrue(model.defaultDarkMode)
    }

}