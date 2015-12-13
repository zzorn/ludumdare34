package org.ld34.core.map.render

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import org.flowutils.rectangle.Rectangle
import org.ld34.core.map.GameMap
import org.ld34.core.util.Int2
import org.ld34.core.util.Int3
import org.ld34.core.util.toInt3Rounded

/**
 *
 */
class IsometricMapRenderer(override var gameMap: GameMap,
                           override var camera: MapCamera = MapCamera()) : MapRenderer {

    val tileScreenScale = 2f
    val tileScreenSize = Vector3(32f * tileScreenScale, 16f * tileScreenScale, 32f * tileScreenScale)
    val tileBorder = 8f * tileScreenScale

    override fun render(batch: SpriteBatch, atlas: TextureAtlas, screenArea: Rectangle) {

        val worldPosAtCenter = camera.focus.toInt3Rounded()

        val screenSize = Int2((screenArea.sizeX.toFloat() / tileScreenSize.x).toInt() + 2,
                              (screenArea.sizeY.toFloat() / tileScreenSize.y).toInt() + 2)


        // Walk through visible area, lower layers first, back to front, left to right

        val worldPosDeltaRightInScreen = Int3(1, -1, 0)
        val worldPosDeltaDownInScreen = Int3(-1, -1, 0)
        val worldPosRowOffsetInScreen = Int3(0, -1, 0)

        val leftUpperCornerScreen = Int2(-screenSize.x / 2, -screenSize.y / 2)

        fun renderLayer(z: Int) {
            val rowStartScreenPos = Int2(leftUpperCornerScreen)
            val upperLeftWordPos = Int3(worldPosAtCenter.x, worldPosAtCenter.y, worldPosAtCenter.z + z)
            upperLeftWordPos.add(worldPosDeltaRightInScreen * rowStartScreenPos.x)
            upperLeftWordPos.add(worldPosDeltaDownInScreen * rowStartScreenPos.y)

            val worldPos = Int3(upperLeftWordPos)

            val screenPos = Vector2()

            for (y in screenSize.y+1 downTo -1) {

                fun renderRow() {
                    for (x in -1..screenSize.x+1) {
                        screenPos.x += tileScreenSize.x
                        worldPos.add(worldPosDeltaRightInScreen)

                        val texture = gameMap[worldPos]?.blockType?.getTexture(worldPos)
                        if (texture != null) {
                            val w = tileScreenSize.x + tileBorder * 2
                            val h = tileScreenSize.z + tileBorder * 2
                            batch.draw(texture,
                                    screenPos.x - w / 2f,
                                    screenPos.y - tileBorder,
                                    w,
                                    h)
                        }
                    }
                }

                // Normal row
                screenPos.set(-tileScreenSize.x, y * tileScreenSize.y + z * tileScreenSize.z)
                worldPos.set(upperLeftWordPos)
                worldPos.add(worldPosDeltaDownInScreen * y)
                renderRow()

                // Offset row
                screenPos.set(-tileScreenSize.x*0.5f, (y - 0.5f) * tileScreenSize.y + z * tileScreenSize.z)
                worldPos.set(upperLeftWordPos)
                worldPos.add(worldPosDeltaDownInScreen * y)
                worldPos.add(worldPosRowOffsetInScreen)
                renderRow()

            }
        }

        for (z in 0 .. gameMap.chunkSize.z-1)
            renderLayer(z - worldPosAtCenter.z)
    }

    //fun screenToWorld(screenPos: Int3, worldPos: Int3) {
    //}

}