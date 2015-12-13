package org.ld34.core.map.render

import org.ld34.core.map.GameMap
import org.ld34.core.util.Rendering

/**
 *
 */
interface MapRenderer : Rendering {

    val camera: MapCamera

    var gameMap: GameMap

}