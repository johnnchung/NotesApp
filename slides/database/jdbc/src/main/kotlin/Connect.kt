import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Sample database from sqlitetutorial.net
 * JDBC sample code by Jeff Avery
 */
object Connect {

    @JvmStatic
    fun main(args: Array<String>) {
        val conn: Connection? = connect()
        query(conn)
        close(conn)
    }

    fun connect(): Connection? {
        var conn: Connection? = null
        try {
            val url = "jdbc:sqlite:chinook.db"
            conn = DriverManager.getConnection(url)
            println("Connection to SQLite has been established.")
        } catch (e: SQLException) {
            println(e.message)
        }
        return conn
    }

    fun query(conn:Connection?) {
        try {
            if (conn != null) {
                val sql = "select albumid, title, artistid from albums where albumid < 5"
                val query = conn.createStatement()
                val results = query.executeQuery(sql)
                println("Fetched data:");
                while (results.next()) {
                    val albumId = results.getInt("albumid")
                    val title = results.getString("title")
                    val artistId = results.getInt("artistid")
                    println(albumId.toString() + "\t" + title + "\t" + artistId)
                }
            }
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }

    fun close(conn:Connection?) {
        try {
            conn?.close()
            println("Connection closed.")
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }
}