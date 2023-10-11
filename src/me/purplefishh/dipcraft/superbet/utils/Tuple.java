package me.purplefishh.dipcraft.superbet.utils;

/**
 * Tuple data structure for storing 3 data at the same time
 * @param left the left element of the tuple
 * @param center the center element of the tuple
 * @param right the right element of the tuple
 * @param <L> the left element type
 * @param <C> the center element type
 * @param <R> the right element type
 */
public record Tuple<L, C, R>(L left, C center, R right) {
}
