package net.codebot.application

import javafx.event.EventHandler
import javafx.geometry.*
import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.control.Tooltip
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.paint.*
import javafx.util.Duration

class ToolBarClass(private val model: Model): HBox(), ToolBarView {
    var buttonstate = "test"
    val currentDir = System.getProperty("user.dir")

    // icons for each of the buttons in the toolbar
    // private val boldIcon = Image("\\src\\util\\bold.png")
    // private val italicsIcon = Image("$currentDir\\src\\util\\italics.png")
    // private val underlineIcon = Image("$currentDir\\src\\util\\underline.png")
    // private val highlightIcon = Image("$currentDir\\src\\util\\highlight.png")
    // private val fontColorIcon = Image("$currentDir\\src\\util\\font-color.png")

    fun getBoldButton(): Button {
        return boldButton
    }

    fun getItalicsButton(): Button {
        return italicsButton
    }

    fun getUnderlineButton(): Button {
        return underlineButton
    }

    fun getHighlightButton(): Button {
        return highlightButton
    }

    fun getFontButton(): Button {
        return fontColorButton
    }

    fun getButtonState():String {
        return this.buttonstate
    }
    fun setButtonState(value:String) {
        this.buttonstate = value
    }


    private val boldButton = Button("", ImageView("bold.png")).apply {
        prefWidth = 10.0
        prefHeight = 10.0
        val tooltips = Tooltip("Bold Text")
        tooltip = tooltips
        tooltip.showDelay = Duration.ZERO
        onAction = EventHandler {
            setButtonState(tooltip.text)
        }
    }

    private val italicsButton = Button("", ImageView("italics.png")).apply {
        prefWidth = 20.0
        prefHeight = 20.0
        val tooltips = Tooltip("Italicize Text")
        tooltip = tooltips
        tooltip.showDelay = Duration.ZERO
        onAction = EventHandler {
            setButtonState(tooltip.text)
        }
    }

    private val underlineButton = Button("", ImageView("underline.png")).apply {
        prefWidth = 20.0
        prefHeight = 20.0
        val tooltips = Tooltip("Underline Text")
        tooltip = tooltips
        tooltip.showDelay = Duration.ZERO
        onAction = EventHandler {
            setButtonState(tooltip.text)
        }
    }

    private val highlightButton = Button("", ImageView("highlight.png")).apply {
        prefWidth = 20.0
        prefHeight = 20.0
        val tooltips = Tooltip("Highlight Text")
        tooltip = tooltips
        tooltip.showDelay = Duration.ZERO
        onAction = EventHandler {
            setButtonState(tooltip.text)
        }
    }

    private val fontColorButton = Button("", ImageView("font-color.png")).apply {
        prefWidth = 20.0
        prefHeight = 20.0
        val tooltips = Tooltip("Change Text Color")
        tooltip = tooltips
        tooltip.showDelay = Duration.ZERO
        onAction = EventHandler {
            setButtonState(tooltip.text)
        }
    }

    override fun updateToolBarView() {

    }

    init {
        val toolbar = ToolBar(boldButton, Region(), italicsButton, Region(), underlineButton, Region(),
            highlightButton, Region(), fontColorButton).apply {
            padding = Insets(10.0, 50.0, 10.0, 20.0)
            maxWidth = Double.MAX_VALUE
            prefHeight = 50.0
            setHgrow(this, Priority.ALWAYS)
        }
        this.children.addAll(toolbar)
        model.addToolBarView(this)
    }
}