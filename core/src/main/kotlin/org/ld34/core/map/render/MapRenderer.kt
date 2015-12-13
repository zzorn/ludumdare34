package org.ld34.core.map.render

import org.ld34.core.map.GameMap
import org.ld34.core.util.AreaRendering

/**
 *
 */
interface MapRenderer : AreaRendering {

    val camera: MapCamera

    var gameMap: GameMap

}