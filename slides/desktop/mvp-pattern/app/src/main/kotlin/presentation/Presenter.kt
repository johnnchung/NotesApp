package presentation

import business.Model
import Settings
import javafx.scene.Scene
import javafx.stage.Stage

class Presenter(private val stage: Stage, model: Model) : IObserver {
    private val model: Model
    private var currentScene: Scene? = null

    // methods to manage data for the active view
    fun getValue(key: String?): String? {
        return model.getValue(key)
    }

    override fun update() {
        (currentScene as IObserver?)?.update()
    }

    // methods to control which view is active and visible
    fun start() {
        // Create starting scene
        if (currentScene == null) {
            currentScene = Scene1(this)
        }
        // Attach the scene to the stage and show it
        stage.scene = currentScene
        stage.show()
    }

    fun advance() {
        // Create new scene
        if (currentScene is Scene1) {
            currentScene = Scene2(this)
        } else if (currentScene is Scene2) {
            currentScene = Scene3(this)
        }

        // Attach the scene to the stage and show it
        stage.scene = currentScene
        stage.show()
    }

    fun rewind() {
        // Create new scene
        if (currentScene is Scene3) {
            currentScene = Scene2(this)
        } else if (currentScene is Scene2) {
            currentScene = Scene1(this)
        }

        // Attach the scene to the stage and show it
        stage.scene = currentScene
        stage.show()
    }

    init {
        stage.title = Settings.WINDOW_TITLE
        stage.isResizable = false
        this.model = model
    }
}