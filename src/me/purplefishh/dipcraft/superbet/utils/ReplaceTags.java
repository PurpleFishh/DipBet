package me.purplefishh.dipcraft.superbet.utils;

import lombok.Getter;

/**
 * PlaceHolders from messages and names of items <br>
 * Used for easier access and in case of modification
 */
public enum ReplaceTags {
    BET_AMOUNT("%bet_amount%"),
    TIME("%time%"),
    SUM("%sum%"),
    MINUTES("%minutes%"),
    SECONDS("%seconds%");

    /**
     * The tag of the placeholder
     */
    @Getter
    private final String tag;

    ReplaceTags(String tag) {
        this.tag = tag;
    }
}
