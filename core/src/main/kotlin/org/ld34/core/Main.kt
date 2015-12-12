package org.ld34.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion


class Main : ApplicationListener {
    internal var texture: Texture? = null
    internal var batch: SpriteBatch? = null
    internal var elapsed: Float = 0.toFloat()
    internal var texture_region: TextureRegion? = null

    override fun create() {
        println("Main.create test")

        val atlas = TextureAtlas(Gdx.files.internal("textures/textures.atlas"))
        texture_region = atlas.findRegion("white_ball")

        println("Main.create - pic loaded")
        batch = SpriteBatch()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun render() {
        elapsed += Gdx.graphics.deltaTime
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT)
        batch?.begin()
        batch?.draw(texture_region, 100 + 100 * Math.cos(elapsed.toDouble()).toFloat(), 100 + 25 * Math.sin(elapsed.toDouble()).toFloat())
        batch?.end()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
    }
}
