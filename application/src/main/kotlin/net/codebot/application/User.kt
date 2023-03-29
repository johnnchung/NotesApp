package net.codebot.application

import net.codebot.application.Users.autoIncrement
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val title = varchar("title", Integer.MAX_VALUE)
    val group = varchar("groups", Integer.MAX_VALUE)
    val content = varchar("Content", Integer.MAX_VALUE)
    override val primaryKey = PrimaryKey(title)
}