package de.lecuutex.discordbot.utils;

import lombok.Getter;

/**
 * A class created by yi.dnl - 06.02.2022 / 20:36
 */

public enum Errors {
    INSUFFICIENT_PERMISSIONS("Dazu hast du keine Rechte!"),
    YOU_ARE_NOT_IN_VC("Du bist in keinem VoiceChannel!"),
    USER_IS_NOT_IN_VC("Der angegebene Nutzer ist in keinem VoiceChannel!"),
    IS_IN_YOUR_VC("Der angegebene Nutzer ist bereits in deinem VoiceChannel!"),
    CC_AMOUNT_MIN("Du musst mindestens zwei Nachrichten löschen!"),
    HAS_ALREADY_THIS_ROLE("Dieser Nutzer hat bereits die angegebene Rolle!"),
    HAS_NOT_THIS_ROLE("Dieser Nutzer hat diese Rolle nicht!");

    @Getter
    private final String error;

    Errors(String error) {
        this.error = error;
    }
}
