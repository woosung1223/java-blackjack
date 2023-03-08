package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.game.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("카드가 한장 있을 때 첫번째 위치의 카드를 반환한다")
    void drawFirstTest() {
        Deck deck = new Deck((x) -> List.of(new Card(CardType.HEART, CardNumber.ACE)));

        Card drawnCard = deck.serve();

        assertThat(drawnCard.getType()).isEqualTo(CardType.HEART);
        assertThat(drawnCard.getNumber()).isEqualTo(CardNumber.ACE);
    }

    @Test
    @DisplayName("카드가 여러장 있을 때도 첫번째 위치의 카드를 반환한다")
    void drawLastTest() {
        Deck deck = new Deck((x) -> List.of(
                new Card(CardType.DIAMOND, CardNumber.KING),
                new Card(CardType.CLOVER, CardNumber.FIVE),
                new Card(CardType.SPADE, CardNumber.JACK)
        ));

        Card drawnCard = deck.serve();

        assertThat(drawnCard.getType()).isEqualTo(CardType.DIAMOND);
        assertThat(drawnCard.getNumber()).isEqualTo(CardNumber.KING);
    }
}