package org.ld34.core.util

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas

/**
 *
 */
interface Rendering {

    fun render(batch: SpriteBatch, atlas: TextureAtlas)

}