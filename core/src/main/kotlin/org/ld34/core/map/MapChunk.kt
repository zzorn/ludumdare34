package org.ld34.core.map

import com.badlogic.gdx.math.Vector3
import org.ld34.core.util.Int3
import org.ld34.core.util.toInt3Floored

/**
 *
 */
class MapChunk(val offset: Int3 = Int3(),
               val size: Int3 = Int3(128, 128, 9)) {

    operator fun get(pos: Int3): Tile? = tiles[indexOf(pos)]
    operator fun set(pos: Int3, tile: Tile) { tiles[indexOf(pos)] = tile }
    operator fun get(pos: Vector3): Tile? = tiles[indexOf(pos)]

    val start: Int3
       get() = offset

    val end: Int3 = offset + size - Int3(1, 1, 1)

    fun getOrCreateTile(pos: Int3, blockType: BlockType = BlockType.AIR): Tile {
        var tile = get(pos)
        if (tile == null) {
            tile = Tile(blockType)
            set(pos, tile)
        }

        return tile
    }

    fun hasTileAt(pos: Int3): Boolean = tiles[indexOf(pos)] != null

    /**
     * @param end end coordinates, exclusive
     */
    fun forTiles(start: Int3 = Int3(), end: Int3 = size, tileCalculator : (pos: Int3, tile: Tile) -> Unit) {
        val pos = Int3()
        for (z in start.z .. end.z-1) {
            for (y in start.y .. end.y-1) {
                for (x in start.x .. end.x-1) {
                    pos.set(x, y, z)
                    pos.add(offset)
                    tileCalculator(pos, getOrCreateTile(pos))
                }
            }
        }
    }


    private val tiles = Array<Tile?>(size.multiplyAll()) {
        i -> null
    }

    private fun indexOf(pos: Vector3): Int = indexOf(pos.toInt3Floored())

    private fun indexOf(pos: Int3): Int {
        // Convert to local coordinates
        val p = pos - offset

        // Check that we are inside the map section
        if (!p.inRange(size)) throw IllegalArgumentException("The position $pos (locally $p) is outside this MapSection ($this)")

        // Calculate index
        return p.scaleSum(1, size.x, size.x * size.y)
    }

    override fun toString(): String {
        return "MapChunk(offset: $offset, size: $size)"
    }
}