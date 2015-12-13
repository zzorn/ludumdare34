package org.ld34.core.map.generators

import org.ld34.core.map.BlockType
import org.ld34.core.map.MapChunk
import org.ld34.core.util.Int3

/**
 *
 */
class GroundGenerator(var groundType: BlockType,
                      var groundHeightZ: Int = 0) : MapGenerator {

    override fun populate(chunk: MapChunk) {
        val end = Int3(chunk.size)
        end.z = groundHeightZ + 1
        chunk.forTiles(end = end) { pos, tile ->
            tile.blockType = groundType
        }
    }
}