package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.user.CardPool;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("Player가 Dealer보다 값이 작으면 LOSE이다")
    void makePlayerRecordWhenPlayerLose() {
        CardPool playerCardPool = new CardPool(
                List.of(new Card(CardType.CLOVER, CardNumber.FIVE))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(new Card(CardType.CLOVER, CardNumber.EIGHT))
        );

        Player player = new Player("플레이어", playerCardPool);
        Dealer dealer = new Dealer("딜러", dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("Player가 Dealer와 값이 같으면 DRAW이다")
    void makePlayerrRecordWhenDraw() {
        CardPool playerCardPool = new CardPool(
                List.of(new Card(CardType.CLOVER, CardNumber.FIVE))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(new Card(CardType.CLOVER, CardNumber.FIVE))
        );

        Player player = new Player("플레이어", playerCardPool);
        Dealer dealer = new Dealer("딜러", dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("Player가 Dealer와 값이 크면 WIN이다")
    void makePlayerRecordWhenPlayerWin() {
        CardPool playerCardPool = new CardPool(
                List.of(new Card(CardType.CLOVER, CardNumber.SIX))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(new Card(CardType.CLOVER, CardNumber.FIVE))
        );

        Player player = new Player("플레이어", playerCardPool);
        Dealer dealer = new Dealer("딜러", dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("Dealer는 Player가 이기면 1패가 적립된다.")
    void makeDealerRecordWhenLose() {
        Player player = new Player("플레이어", new CardPool(Collections.emptyList()));
        Map<Player, GameResult> playerGameResult = new HashMap<>();

        playerGameResult.put(player, GameResult.WIN);

        assertThat(GameResult.makeDealerRecord(playerGameResult))
                .containsEntry(GameResult.LOSE, 1)
                .containsEntry(GameResult.WIN, 0)
                .containsEntry(GameResult.DRAW, 0);
    }

    @Test
    @DisplayName("Dealer는 Player가 지면 1승이 적립된다.")
    void makeDealerRecordWhenWin() {
        Player player = new Player("플레이어", new CardPool(Collections.emptyList()));
        Map<Player, GameResult> playerGameResult = new HashMap<>();

        playerGameResult.put(player, GameResult.LOSE);

        assertThat(GameResult.makeDealerRecord(playerGameResult))
                .containsEntry(GameResult.LOSE, 0)
                .containsEntry(GameResult.WIN, 1)
                .containsEntry(GameResult.DRAW, 0);
    }

    @Test
    @DisplayName("Dealer는 Player와 점수가 같으면 1무가 적립된다.")
    void makeDealerRecordWhenDraw() {
        Player player = new Player("플레이어", new CardPool(Collections.emptyList()));
        Map<Player, GameResult> playerGameResult = new HashMap<>();

        playerGameResult.put(player, GameResult.DRAW);

        assertThat(GameResult.makeDealerRecord(playerGameResult))
                .containsEntry(GameResult.LOSE, 0)
                .containsEntry(GameResult.WIN, 0)
                .containsEntry(GameResult.DRAW, 1);
    }
}
