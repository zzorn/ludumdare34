package org.ld34.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.flowutils.rectangle.MutableRectangle
import org.flowutils.rectangle.Rectangle
import org.ld34.core.map.BlockType
import org.ld34.core.map.GameMap
import org.ld34.core.map.generators.GroundGenerator
import org.ld34.core.map.render.IsometricMapRenderer
import org.ld34.core.map.render.MapRenderer


class Main : ApplicationListener {
    private lateinit var batch: SpriteBatch
    internal var elapsed: Float = 0.toFloat()
    internal var ground: TextureRegion? = null
    internal var plant_stem: TextureRegion? = null
    internal var plant_leaf1: TextureRegion? = null
    internal var plant_leaf2: TextureRegion? = null

    private lateinit var mapRenderer: MapRenderer
    private lateinit var atlas: TextureAtlas
    private lateinit var mapArea: Rectangle

    override fun create() {
        println("Main.create test")

        atlas = TextureAtlas(Gdx.files.internal("textures/textures.atlas"))

        BlockType.init(atlas)

        ground = atlas.findRegion("small_brick_carved1")
        plant_stem = atlas.findRegion("plant_stem")
        plant_leaf1 = atlas.findRegion("plant_leaf1")
        plant_leaf2 = atlas.findRegion("plant_leaf2")

        // Create map
        val gameMap = GameMap(arrayListOf(GroundGenerator(BlockType.SMALL_BRICK_CARVED)))

        // Create map renderer
        mapArea = MutableRectangle(Gdx.graphics.width.toDouble(),
                                   Gdx.graphics.height.toDouble())
        mapRenderer = IsometricMapRenderer(gameMap)

        println("Main.create - pic loaded")
        batch = SpriteBatch()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun render() {
        elapsed += Gdx.graphics.deltaTime

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT)

        batch.begin()

        mapRenderer.render(batch, atlas, mapArea)

        /*
        val tileSize = 128f
        val tileW = 64f
        val tileH = 32f
        for (cy in 0..100) {
            for (cx in 0..100) {
                val x = cx * tileW + cy * tileH
                val y = cy * tileW + cx * tileH
                batch?.draw(BlockType.SMALL_BRICK_CARVED.getTexture(cx, cy), x, y, tileSize, tileSize)
            }
        }

        val random: RandomSequence = XorShift(3214)
        for (i in 0 .. 100) {
            //val x = 100 + 100 * Math.cos(elapsed.toDouble()).toFloat()
            //val y = 100 + 25 * Math.sin(elapsed.toDouble()).toFloat()

            random.setSeed(i.toLong())

            val x = random.nextFloat(10f, 800f)
            val y = random.nextFloat(10f, 800f)

            batch?.draw(plant_stem, x, y)
            for (l in 0 .. random.nextInt(1, 4)) {
                batch?.draw(plant_leaf2, x+ random.nextFloat(0f, 10f), y+random.nextFloat(0f, 60f))
                batch?.draw(plant_leaf2, x-40+ random.nextFloat(0f, 10f), y+random.nextFloat(0f, 60f))
            }
            for (l in 0 .. random.nextInt(1, 4)) {
                batch?.draw(plant_leaf1, x+ random.nextFloat(0f, 10f), y+random.nextFloat(0f, 60f))
                batch?.draw(plant_leaf1, x-40+ random.nextFloat(0f, 10f), y+random.nextFloat(0f, 60f))
            }
        }
        */

        batch.end()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
    }
}
