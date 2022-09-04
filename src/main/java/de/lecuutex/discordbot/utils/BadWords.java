package de.lecuutex.discordbot.utils;

import lombok.Getter;

/**
 * A class created by yi.dnl - 04.03.2022 / 22:31
 */

@Getter
public enum BadWords {
    HURENSOHN("hurensohn", 10),
    HALTSMAUL("halts maul", 5),
    HDF("hdf", 5),
    HALTDIEFRESSE("halt die fresse", 5),
    MISSGEBURT("missgeburt", 10),
    DUMM("dumm", 3),
    WICHSER("wichser", 10),
    HURE(" hure", 10),
    NUTTE("nutte", 10),
    FICK("fick", 10),
    FRESSE("fresse", 10),
    HUSO("huso", 10);

    private final String word;

    private final int amount;

    BadWords(String word, int amount) {
        this.word = word;
        this.amount = amount;
    }
}
