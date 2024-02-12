package org.max.seminar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тест кейс для проверки инициализации новой игры
 */
public class GameTest extends AbstractTest {

    @Test
    void newDeckTest() {
        //given
        //when
        game.newDeck();
        //then
        Assertions.assertNotNull(game.getDeck());
        Assertions.assertEquals(52, game.getDeck().getCards().size());
        Object obj1 = null;
        Assertions.assertNull(obj1);
        Assertions.assertNotNull(obj1);
    }

    @Test
    void checkPlayerTest() {
        //given
        //when
        //then
        Assertions.assertNotNull(game.getGamer());
        Assertions.assertNotNull(game.getCasino());
    }

    @Test
    void addCardTest() {
        //given
        //when
        game.addCardToCasino();
        game.addCardToPlayer();
        //then
        Assertions.assertTrue(game.getGamer().getHandSumm() > 0);
        Assertions.assertTrue(game.getCasino().getHandSumm() > 0);

        Assertions.assertFalse(game.getGamer().getHandSumm() > 0);
    }
}
