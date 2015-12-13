package org.ld34.core.map

import com.badlogic.gdx.math.Vector3
import org.flowutils.MathUtils

/**
 *
 */
data class Int3(var x: Int = 0,
                var y: Int = 0,
                var z: Int = 0) {

    fun zero() {
        x = 0
        y = 0
        z = 0
    }

    fun set(x: Int = 0, y: Int = 0, z: Int = 0) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun add(other: Int3) {
        x += other.x
        y += other.y
        z += other.z
    }

    fun sub(other: Int3) {
        x -= other.x
        y -= other.y
        z -= other.z
    }

    operator fun plus(other: Int3): Int3 =
            Int3(x + other.x,
                    y + other.y,
                    z + other.z)

    operator fun minus(other: Int3): Int3 =
            Int3(x - other.x,
                    y - other.y,
                    z - other.z)

    operator fun times(other: Int3): Int3 =
            Int3(x * other.x,
                    y * other.y,
                    z * other.z)

    operator fun div(other: Int3): Int3 =
            Int3(x / other.x,
                    y / other.y,
                    z / other.z)

    operator fun times(scalar: Int): Int3 =
            Int3(x * scalar,
                    y * scalar,
                    z * scalar)

    operator fun div(scalar: Int): Int3 =
            Int3(x / scalar,
                    y / scalar,
                    z / scalar)

    fun gridOrigo(gridSize: Int3): Int3 {
        fun floorDiv(v: Int, size: Int): Int = (v / size) - if (v < 0) 1 else 0
        return Int3(floorDiv(x, gridSize.x),
                    floorDiv(y, gridSize.y),
                    floorDiv(z, gridSize.z))
    }

    fun inRange(ends: Int3): Boolean = inRange(endX = ends.x,
                                               endY = ends.y,
                                               endZ = ends.z)

    fun inRange(starts: Int3, ends: Int3): Boolean = inRange(
            starts.x, starts.y, starts.z,
            ends.x, ends.y, ends.z)

    fun inRange(startX: Int = 0,
                startY: Int = 0,
                startZ: Int = 0,
                endX: Int = 0,
                endY: Int = 0,
                endZ: Int = 0): Boolean =
            x in startX .. endX-1 &&
            y in startY .. endY-1 &&
            z in startZ .. endZ-1

    fun scaleSum(xScale: Int = 1, yScale: Int = 1, zScale: Int = 1): Int = x * xScale + y * yScale + z * zScale

    fun multiplyAll(xOffset: Int = 0, yOffset: Int = 0, zOffset: Int = 0): Int = (x+xOffset) * (y+yOffset) * (z+zOffset)

    fun toVector3(): Vector3 = Vector3(x.toFloat(), y.toFloat(), z.toFloat())

}

fun Vector3.toInt3Rounded(): Int3 =
        Int3(this.x.toInt(),
             this.y.toInt(),
             this.z.toInt())

fun Vector3.toInt3Floored(): Int3 =
        Int3(MathUtils.fastFloor(this.x),
             MathUtils.fastFloor(this.y),
             MathUtils.fastFloor(this.z))

