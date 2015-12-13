package org.ld34.core.util

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.flowutils.rectangle.Rectangle

/**
 *
 */
interface AreaRendering {

    fun render(batch: SpriteBatch, atlas: TextureAtlas, screenArea: Rectangle)

}