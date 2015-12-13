package org.ld34.core.map

import org.ld34.core.map.generators.MapGenerator
import org.ld34.core.util.Int3
import java.util.*

/**
 *
 */
class GameMap(val generators: MutableList<MapGenerator> = ArrayList(),
              val chunkSize: Int3 = Int3(128, 128, 9)) {

    // TODO: Remove oldest chunks? Save objects in them?
    private val chunkCache: MutableMap<Int3, MapChunk> = LinkedHashMap()

    fun get(pos: Int3): Tile? = getMapChunkAtPos(pos)[pos]


    fun getMapChunkAtPos(pos: Int3): MapChunk {
        // Determine chunk 0,0,0 corner
        val chunkPos = pos.gridOrigo(chunkSize)

        // Get chunk if found
        var chunk = chunkCache.get(chunkPos)

        // Create chunk if needed
        if (chunk == null) {
            chunk = createChunk(chunkPos)
            chunkCache.put(chunkPos, chunk)
        }

        return chunk
    }

    private fun createChunk(chunkPos: Int3): MapChunk {
        val chunk = MapChunk(chunkPos, chunkSize)

        for (generator in generators) {
            generator.populate(chunk)
        }

        return chunk
    }
}