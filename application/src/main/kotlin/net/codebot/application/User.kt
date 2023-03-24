package net.codebot.application

import net.codebot.application.Users.autoIncrement
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val title = varchar("title", 50)
    val group = varchar("groups", 50)
    val content = varchar("Content", 300)
    override val primaryKey = PrimaryKey(title)
}