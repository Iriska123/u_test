package org.max.seminar;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.max.seminar.exception.DeckEmptyException;
import org.max.seminar.type.Ranks;
import org.max.seminar.type.Suits;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardsTest {

    List<Card> cards = new ArrayList<>();
    Card card;
    Deck deck;

    @Test
    @Order(1)
    @DisplayName("Получение карты из колоды с одной картой.")
    void getCardFromDeckOneCard() throws DeckEmptyException {
        //given
        card = new Card(Ranks.SEVEN, Suits.CLUBS);
        cards.add(card);
        deck = new Deck(cards);
        //then
        Assertions.assertEquals(1, deck.getCards().size());
        //when
        Card carsFromDeck = deck.getCard();
        //then
        Assertions.assertEquals(0, deck.getCards().size());
        Assertions.assertEquals(card, carsFromDeck);
    }

    @Test
    @Order(2)
    @DisplayName("Получение карт из колоды с двумя картой.")
    void getCardFromDeckSeveralCards() throws DeckEmptyException {
        //given
        card = new Card(Ranks.SEVEN, Suits.CLUBS);
        cards.add(card);
        Card cardTwo = new Card(Ranks.ACE, Suits.DIAMONDS);
        cards.add(cardTwo);
        deck = new Deck(cards);
        //then
        Assertions.assertEquals(2, deck.getCards().size());
        //when
        Card carsFromDeck = deck.getCard();
        //then
        Assertions.assertEquals(1, deck.getCards().size());
        Assertions.assertEquals(card, carsFromDeck);

        //when
        carsFromDeck = deck.getCard();
        //then
        Assertions.assertEquals(0, deck.getCards().size());
        Assertions.assertEquals(cardTwo, carsFromDeck);
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Генерация колоды карт из параметров теста.")
    @ValueSource(ints = {1, 2, 5, 52})
    void getSomeCardsFromDeck(int num) throws DeckEmptyException {
        //given
        Card firstCard = generateDeck(num);
        //then
        Assertions.assertEquals(num, deck.getCards().size());
        //when
        Card cardFromDeck = deck.getCard();
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(num - 1, deck.getCards().size()),
                () -> Assertions.assertEquals(firstCard, cardFromDeck)
        );
    }

    private Card generateDeck(int num) {
        Random random = new Random();
        for (int idx = 0; idx < num; idx++) {
            Card card = new Card(Ranks.fromValue(2 + random.nextInt(9)), Suits.fromValue(1 + random.nextInt(3)));
            cards.add(card);
        }
        deck = new Deck(cards);
        return cards.get(0);
    }

}
