package todo.app

import kotlin.test.Test

internal class MainTest {

    @Test
    fun mainWithoutArgs() {
        main(arrayOf(""))
    }

    @Test
    fun mainWithArgs() {
        main(arrayOf("show"))
    }
}