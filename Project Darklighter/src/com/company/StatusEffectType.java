package com.company;

public enum StatusEffectType {

    MINUS_PROGRESSIVE,          // Continual subtraction of stat
    PLUS_PROGRESSIVE,           // Continual addition to stat
    STATIC_MINUS,               // Stat set to specific lower value for number of turns
    STATIC_PLUS,                // Stat set to specific higher value for number of turns
    DISABLE;                    // Certain Character action is disabled for number of turns
}
