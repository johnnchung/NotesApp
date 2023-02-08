import org.junit.Test

internal class MainTest {

    @Test
    fun debugTest() {
        open("data.txt")
        debug("Unit test", 100)
        debug("Unit test", 200)
        debug("Unit test", 300)
        debug("Unit test", 400)
        debug("Unit test", 500)
        close()
    }
}