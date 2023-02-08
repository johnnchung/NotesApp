package business

import presentation.IObserver

class Model {
    var settings = HashMap<String, String>()
    private var observers: ArrayList<IObserver> = ArrayList()

    // manager observer list
    fun addView(scene: IObserver) {
        observers.add(scene)
    }

    fun removeView(scene: IObserver) {
        observers.remove(scene)
    }

    fun update() {
        for (observer in observers) {
            observer.update()
        }
    }

    // fetch data on demand
    fun getValue(key: String?): String? {
        return settings[key]
    }

    override fun toString(): String {
        val s = StringBuilder()
        settings.forEach { (key: String?, value: String?) ->
            s.append("$key: $value \n")
        }
        return s.toString()
    }

    init {
        for (key in arrayOf(
            "java.vendor", "java.version", "java.home", "java.class.path",
            "os.name", "os.arch", "os.version"
        )) {
            settings[key] = System.getProperty(key)
        }
    }
}