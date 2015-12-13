package org.ld34.core.map

import com.badlogic.gdx.math.Vector3

/**
 *
 */
class MapChunk(val offset: Int3 = Int3(),
               val size: Int3 = Int3(128, 128, 9)) {

    fun get(pos: Int3): Tile? = tiles[indexOf(pos)]
    fun get(pos: Vector3): Tile? = tiles[indexOf(pos)]

    fun hasTileAt(pos: Int3): Boolean = tiles[indexOf(pos)] != null

    private val tiles = Array<Tile?>(size.multiplyAll()) {
        i -> null
    }

    private fun indexOf(pos: Vector3): Int = indexOf(pos.toInt3Floored())

    private fun indexOf(pos: Int3): Int {
        // Convert to local coordinates
        val p = pos - offset

        // Check that we are inside the map section
        if (p.inRange(size)) throw IllegalArgumentException("The position $pos is outside this MapSection ($this)")

        // Calculate index
        return p.scaleSum(1, size.x, size.x * size.y)
    }
}