package org.max.seminar;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.max.seminar.exception.DeckEmptyException;
import org.max.seminar.type.Ranks;
import org.max.seminar.type.Suits;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardsTest {

    static List<Card> cards;
    static Deck deck;
    static Card card;

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll");
        cards = new ArrayList<>();
        deck = new Deck(cards);
    }

    @BeforeEach
    void clearCards() {
        System.out.println("BeforeEach");
        card = new Card(Ranks.SEVEN, Suits.DIAMONDS);
        cards.add(card);
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
        cards.clear();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }

    @Test
    @Order(1)
    void emptyDeck() {
        cards.clear();
        System.out.println("emptyDeck");
        Assertions.assertThrows(DeckEmptyException.class, deck::getCard);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @Order(2)
    void nonEmptyDeck(int num) throws DeckEmptyException {
        System.out.println("nonEmptyDeck");
        addCards(num - 1);
        Card actual = deck.getCard();
        Assertions.assertEquals(card, actual);
        Assertions.assertEquals(num - 1, deck.getCards().size());
    }

    private void addCards(int num) {
        for (int index = 0; index < num; index++) {
            cards.add(new Card(Ranks.fromValue(num + 2), Suits.CLUBS));
        }
    }

}
