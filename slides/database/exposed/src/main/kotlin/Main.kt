import StarWarsFilms.director
import StarWarsFilms.name
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction

// Example from JetBrains/Exposed Wiki
// https://github.com/JetBrains/Exposed/wiki/DSL#basic-crud-operations
    fun main() {
        Database.connect("jdbc:sqlite:starwars.db")

        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            // create a table that reflects the Cities class structure
            SchemaUtils.create (StarWarsFilms)

            // clear old values
            StarWarsFilms.deleteAll()

            // populate our table
            insertMovieData()

            // find prequels
            println("\nPrequel Trilogy")
            StarWarsFilms.select { StarWarsFilms.episode less 4 }.forEach {
                println(it[StarWarsFilms.name])
            }

            // find movies by J.J.Abrams
            println("\nJ. J. Abrams Movies")
            StarWarsFilms.select { StarWarsFilms.director eq "J. J. Abrams" }.forEach {
                println(it[StarWarsFilms.name])
            }

            // find my favorite movie
            println("\nMy favourite:")
            val film = StarWarsFilms.
                select() { StarWarsFilms.episode eq 5 }.
                withDistinct().map{ it[name] }
            println("${film}")
        }
    }

private fun insertMovieData(): InsertStatement<Number> {
    StarWarsFilms.insert {
        it[episode] = 1
        it[name] = "The Phantom Menace"
        it[director] = "George Lucas"
        it[year] = 1999
    }

    StarWarsFilms.insert {
        it[episode] = 2
        it[name] = "Attack of the Clones"
        it[director] = "George Lucas"
        it[year] = 2002
    }

    StarWarsFilms.insert {
        it[episode] = 3
        it[name] = "Revenge of the Sith"
        it[director] = "George Lucas"
        it[year] = 2005
    }

    StarWarsFilms.insert {
        it[episode] = 4
        it[name] = "Star Wars"
        it[director] = "George Lucas"
        it[year] = 1977
    }

    StarWarsFilms.insert {
        it[episode] = 5
        it[name] = "The Empire Strikes Back"
        it[director] = "George Lucas"
        it[year] = 1980
    }

    StarWarsFilms.insert {
        it[episode] = 6
        it[name] = "Return of the Jedi"
        it[director] = "George Lucas"
        it[year] = 1983
    }
    StarWarsFilms.insert {
        it[episode] = 7
        it[name] = "The Force Awakens"
        it[director] = "J. J. Abrams"
        it[year] = 2015
    }

    StarWarsFilms.insert {
        it[episode] = 8
        it[name] = "The Last Jedi"
        it[director] = "Ryan Johnson"
        it[year] = 2017
    }

    return StarWarsFilms.insert {
        it[episode] = 7
        it[name] = "The Rise of Skywalker"
        it[director] = "J. J. Abrams"
        it[year] = 2019
    }
}

object StarWarsFilms: IntIdTable() {
        val episode = integer("episode")
        val name = varchar("name", 50)
        val director = varchar("director", 50)
        val year = integer("year")
    }