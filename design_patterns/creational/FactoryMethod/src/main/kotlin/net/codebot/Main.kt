package net.codebot

/* Factory Method Design Pattern
 *
 * Based on "Factory Design Pattern in Kotlin"
 * https://www.youtube.com/watch?v=1VWYP3S12Do
 * Alexey Soshin & Sebastian Ainger
 *
 * Repurposed by Jeff Avery for CS 346, Sept 2022.
 */

fun main() {
    // represents state to restore
    val notation = listOf("pa3", "qc5")
    val list = generatePieces(notation)
    list.forEach {
        println("Name: ${it.position}")
    }
}

// Load state of Game
// Read chess positions from a chess file
// How do we build the state of our game from a data file?

sealed class Piece(val position: String)
class Pawn(position: String) : Piece(position)
class Queen(position: String) : Piece(position)

fun generatePieces(notation: List<String>): List<Piece> {
    return notation.map { piece ->
        val pieceType = piece.get(0)
        val position = piece.drop(1)
        when(pieceType) {
            'p' -> Pawn(position)
            'q' -> Queen(position)
            else -> error("Unknown piece")
        }
    }
}