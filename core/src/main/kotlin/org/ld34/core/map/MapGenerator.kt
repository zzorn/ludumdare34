package org.ld34.core.map

/**
 * Generates game maps, chunk by chunk
 */
interface MapGenerator {

    fun populate(chunk: MapChunk)

}