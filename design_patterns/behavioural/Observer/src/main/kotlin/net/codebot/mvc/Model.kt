package net.codebot.mvc

import javafx.animation.AnimationTimer

class Model {
    val observers = mutableListOf<IView>()

    inner class ModelTimer : AnimationTimer()
    {
        @Override
        override fun handle(now: Long) {
            for (observer in observers) {
                observer.update(now)
            }
        }
    }

    init {
        val modelTimer = ModelTimer()
        modelTimer.start()
    }

    fun addObserver(observer: IView) {
        observers.add(observer)
    }
}