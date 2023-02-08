package analog.clock

import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import kotlin.math.cos
import kotlin.math.sin

class ClockFace(private var cx: Double, private var cy: Double, radius: Double): Group() {
    // properties
    var MINUTES_LENGTH = radius - 15
    var HOURS_LENGTH = radius - 20
    var SECONDS_LENGTH = radius - 5
    val HANDS_WIDTH = 3f
    var frame = Circle()
    var minutes = Line()
    var hours = Line()
    var seconds = Line()

    // constructor
    init {
        // setup background frame
        frame.fill = Color.LIGHTGRAY
        frame.centerX = cx
        frame.centerY = cy
        frame.radius = radius

        // setup hands
        hours.stroke = Color.DARKBLUE
        hours.strokeWidth = HANDS_WIDTH.toDouble()
        hours.startX = cx
        hours.startY = cy

        minutes.stroke = Color.BLUE
        minutes.strokeWidth = HANDS_WIDTH.toDouble()
        minutes.startX = cx
        minutes.startY = cy

        seconds.stroke = Color.ALICEBLUE
        seconds.strokeWidth = HANDS_WIDTH.toDouble()
        seconds.startX = cx
        seconds.startY = cy

        this.children.addAll(
            frame,
            addCircleDecoration(2.0, 300.0),
            addCircleDecoration(2.0, 330.0),
            addTextDecoration("3", 0.0),
            addCircleDecoration(2.0, 30.0),
            addCircleDecoration(2.0, 60.0),
            addTextDecoration("6", 90.0),
            addCircleDecoration(2.0, 120.0),
            addCircleDecoration(2.0, 150.0),
            addTextDecoration("9", 180.0),
            addCircleDecoration(2.0, 210.0),
            addCircleDecoration(2.0, 240.0),
            addTextDecoration("12", 270.0),
            hours,
            minutes,
            seconds)
    }
    data class Point(var x: Double, var y: Double)

    // static elements
    fun addTextDecoration(caption: String, angle: Double): Text {
        val text = Text(caption)
        text.font = Font("Verdana", 18.0)
        text.textAlignment = TextAlignment.CENTER
        text.prefWidth(20.0)
        text.prefHeight(10.0)
        val point = findPointOnCircle(cx, cy, HOURS_LENGTH, angle)
        text.x = point.x - (5.0 * caption.length)
        text.y = point.y + 5.0
        return text
    }

    fun addCircleDecoration(radius: Double, angle: Double): Circle {
        val circle = Circle(radius)
        val point = findPointOnCircle(cx, cy, HOURS_LENGTH, angle)
        circle.centerX = point.x
        circle.centerY = point.y
        return circle
    }

    // dynamic elements
    fun setTime(h: Int, m: Int, s: Int) {
        var angle = findAngleInDegrees(h, 12)
        var end = findPointOnCircle(cx, cy, HOURS_LENGTH, angle)
        hours.endX = end.x
        hours.endY = end.y

        angle = findAngleInDegrees(m, 60)
        end = findPointOnCircle(cx, cy, MINUTES_LENGTH, angle)
        minutes.endX = end.x
        minutes.endY = end.y

        angle = findAngleInDegrees(s, 60)
        end = findPointOnCircle(cx, cy, SECONDS_LENGTH, angle)
        seconds.endX = end.x
        seconds.endY = end.y
    }

    // math to calculate position for hands
    private fun findPointOnCircle(x: Double, y: Double, l: Double, angle: Double): Point {
        val px = l * cos(Math.toRadians(angle)) + x
        val py = l * sin(Math.toRadians(angle)) + y
        return Point(px, py)
    }

    private fun findAngleInDegrees(value: Int, range: Int): Double {
        return (value * 360 / range - 90).toDouble() // e.g. 3:00 would return 3*360/12 or 90 degrees
    }
}