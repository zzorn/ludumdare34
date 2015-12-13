package org.ld34.core.map.generators

import org.flowutils.MathUtils
import org.flowutils.SimplexGradientNoise
import org.ld34.core.map.BlockType
import org.ld34.core.map.MapChunk
import org.ld34.core.map.Tile
import org.ld34.core.util.Int3

/**
 *
 */
class PillarGenerator(var pillarBlockType: BlockType,
                      var topBlockType: BlockType = pillarBlockType,
                      val scale: Double = 0.1,
                      val seedOffset: Double = 4233.3213) : MapGenerator {

    override fun populate(chunk: MapChunk) {
        val pos = Int3()
        for (y in chunk.start.y .. chunk.end.y)
           for (x in chunk.start.x .. chunk.end.x) {
               val noise = SimplexGradientNoise.sdnoise3(
                               x.toDouble() * scale,
                               y.toDouble() * scale,
                               seedOffset)
               val height = MathUtils.clamp((noise * noise * noise * chunk.size.z).toInt(), 0, chunk.size.z-1)

               for (z in 0 .. height) {
                   val blockType = if (z == height) topBlockType else pillarBlockType
                   pos.set(x,y,z)
                   if (!chunk.hasTileAt(pos)) {
                       chunk.set(pos, Tile(blockType))
                   }
               }
           }
    }
}