package net.codebot.application

import org.jetbrains.exposed.sql.Table

object screenData : Table() {
    val id = integer("id").autoIncrement()
    val widthVal = double("width")
    val heightVal = double("height")
    val xCoordVal = double("xCoord")
    val yCoordVal = double("yCoord")
    override val primaryKey = PrimaryKey(id, name="PK_ID")
}