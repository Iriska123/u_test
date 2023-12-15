package org.max.seminar;

import org.junit.jupiter.api.*;
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

    @Test
    @Order(2)
    void nonEmptyDeck() throws DeckEmptyException {
        System.out.println("nonEmptyDeck");

        Card actual = deck.getCard();
        Assertions.assertEquals(card, actual);
        Assertions.assertEquals(0, deck.getCards().size());
    }

    @Test
    @Order(3)
    void nonEmptyDeckWithTwoCards() throws DeckEmptyException {
        System.out.println("nonEmptyDeckWithTwoCards");
        Card card2 = new Card(Ranks.TEN, Suits.CLUBS);
        cards.add(card2);

        Card actual = deck.getCard();
        Assertions.assertEquals(card, actual);
        Assertions.assertEquals(1, deck.getCards().size());
    }

}
