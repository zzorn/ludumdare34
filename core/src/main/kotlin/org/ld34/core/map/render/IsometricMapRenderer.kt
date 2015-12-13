package org.ld34.core.map.render

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.ld34.core.map.GameMap

/**
 *
 */
class IsometricMapRenderer(override var gameMap: GameMap,
                           override var camera: MapCamera = MapCamera()) : MapRenderer {

    override fun render(batch: SpriteBatch, atlas: TextureAtlas) {

        // TODO: Render map

        throw UnsupportedOperationException()
    }
}