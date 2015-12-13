package org.ld34.core.map

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.ld34.core.util.WeightedDistribution
import java.util.*

/**
 *
 */
data class BlockType(val textureBaseName : String? = null,
                     val sizeX: Int = 1,
                     val sizeY: Int = 1,
                     val sizeZ: Int = 1,
                     val solid: Boolean = true) {

    private val textureRegions = WeightedDistribution<TextureRegion>()

    fun getTexture(pos: Int3 = Int3()): TextureRegion? {
        if (textureRegions.size > 0) return textureRegions.randomItem(
                                                pos.x.toLong(),
                                                pos.y.toLong(),
                                                pos.z.toLong())
        else return null
    }

    fun init(atlas: TextureAtlas) {
        fun findAndAddRegion(regionName: String): TextureRegion? {
            val region = atlas.findRegion(regionName)
            if (region != null) textureRegions.add(region)
            return region
        }

        // Find textures if a texture name was provided
        val baseName = textureBaseName
        if (baseName != null) {
            findAndAddRegion(baseName)
            findAndAddRegion(baseName+ "0")

            var index = 1
            var region : TextureRegion?
            do {
                region = findAndAddRegion(baseName + (index++).toString())
            } while (region != null)

            if (textureRegions.size <= 0) throw IllegalStateException("Did not find any texture regions for $baseName!")
        }
    }

    companion object {
        val blockTypes = ArrayList<BlockType>()

        val AIR = add(BlockType(solid = false))
        val SMALL_BRICK = add(BlockType("small_brick"))
        val SMALL_BRICK_CARVED = add(BlockType("small_brick_carved"))
        val SMALL_BRICK_SMOOTH = add(BlockType("small_brick_smooth"))

        fun add(blockType: BlockType): BlockType {
            blockTypes.add(blockType)
            return blockType
        }

        fun init(atlas: TextureAtlas) {
            blockTypes.forEach { it.init(atlas) }
        }
    }

}