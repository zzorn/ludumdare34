package org.ld34.core.util

import org.flowutils.Check
import org.flowutils.random.RandomSequence
import org.flowutils.random.XorShift
import java.util.*

/**
 * Weighted distribution of some objects, with functions to pick one randomly.
 */
class WeightedDistribution<T> {

    private var probabilitySum = 0f
    private val items = LinkedHashMap<T, Float>()
    private val ownRandom = XorShift()

    val size: Int
      get() = items.size

    /**
     * Adds an item, uses a decreasing relative probability for later items
     */
    fun add(item: T) {
        add(item, 1f / (1f + 0.5f * items.size))
    }

    /**
     * Adds an item with a specified relative probability to return it
     */
    fun add(item: T, relativeProbability: Float) {
        Check.positiveOrZero(relativeProbability.toDouble(), "relativeProbability")

        probabilitySum += relativeProbability

        val existingProbability = items.get(item) ?: 0f
        items.set(item, existingProbability + relativeProbability)
    }

    fun randomItem(seed: Long, vararg additionalSeeds: Long): T {
        ownRandom.setSeed(seed, *additionalSeeds)
        return randomItem(ownRandom)
    }

    fun randomItem(seed: Float, vararg additionalSeeds: Float): T {
        ownRandom.setSeed(seed, *additionalSeeds)
        return randomItem(ownRandom)
    }

    fun randomItem(random: RandomSequence): T {
        val itemIndex = random.nextFloat(0f, probabilitySum)
        var currentIndex = 0f
        for ((item, prob) in items.entries) {
            currentIndex += prob
            if (currentIndex >= itemIndex) return item
        }
        throw IllegalStateException("Should have found some item.  Number of items in distribution: ${items.size}")
    }
}