package org.ld34.core.map.generators

import org.ld34.core.map.MapChunk

/**
 * Generates game maps, chunk by chunk
 */
interface MapGenerator {

    fun populate(chunk: MapChunk)


}