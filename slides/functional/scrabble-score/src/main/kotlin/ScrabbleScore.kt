object ScrabbleScore {
    fun scoreWord(word: String): Int {
        return word.uppercase().sumBy { c: Char ->
            when (c) {
                in "AEIOULNRST" -> 1
                in "DG" -> 2
                in "BCMP" -> 3
                in "FHVWY" -> 4
                in "K" -> 5
                in "JX" -> 8
                in "QZ" -> 10
                else -> 0
            }
        }
    }

//    val lookup: HashMap<String, Int> = hashMapOf(
//        "A" to 1, "E" to 1, "I" to 1, "O" to 1, "U" to 1, "L" to 1, "N" to 1, "R" to 1, "S" to 1, "T" to 1,
//        "D" to 2, "G" to 2,
//        "B" to 3, "C" to 3, "M" to 3, "P" to 3,
//        "F" to 4, "H" to 4, "V" to 4, "W" to 4, "Y" to 4,
//        "K" to 5,
//        "J" to 8, "X" to 8,
//        "Q" to 10, "Z" to 10
//    )
//
//    fun scoreLetter(c: Char): Int {
//        return lookup[c.uppercase()] ?: 0
//    }
//
//    fun scoreWord(word: String): Int {
//        var score = 0
//        for (c in word) {
//            score += scoreLetter(c)
//        }
//        return score
//    }
}