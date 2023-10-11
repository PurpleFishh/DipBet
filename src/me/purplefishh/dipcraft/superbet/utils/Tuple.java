package me.purplefishh.dipcraft.superbet.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Tuple<L, C, R> {
    @Getter
    private final L left;
    @Getter
    private final C center;
    @Getter
    private final R right;
}
