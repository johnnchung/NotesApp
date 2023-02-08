import business.Model
import javafx.application.Application
import javafx.stage.Stage
import presentation.Presenter

class Main : Application() {
    override fun start(stage: Stage) {
        val model = Model()
        val presenter = Presenter(stage, model)
        presenter.start()
    }
}