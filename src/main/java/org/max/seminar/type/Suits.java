package org.max.seminar.type;

/**
 * Масть
 */
public enum Suits {

    CLUBS ("Трефы", 1),
    DIAMONDS ("Буби", 2),
    HEARTS ("Черви", 3),
    SPADES ("Пики", 4);

    //Имя масти
    private String name;
    private Integer value;

    Suits(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public static Suits fromValue(int value) {
        for (Suits suits : Suits.values()) {
            if (value == suits.value) return suits;
        }
        throw new RuntimeException("Не известное value = " + value);
    }
}
