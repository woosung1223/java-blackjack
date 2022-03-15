package blackJack.controller;

import blackJack.domain.BlackJackGame;
import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.WinDrawLose;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {
        final Participants participants = getParticipants();
        final Deck deck = Deck.create();
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck);
        blackJackGame.firstCardDispensing();
        OutputView.printInitCardResult(participants);

        final List<Player> players = playersTurn(participants, deck);
        final Dealer dealer = dealerTurn(blackJackGame);
        createBlackJackGameResult(players, dealer);
    }

    private Participants getParticipants() {
        try {
            final List<String> playerNames = InputView.inputPlayerNames();
            final List<Player> players = playerNames.stream()
                    .map(Player::new)
                    .collect(Collectors.toUnmodifiableList());
            return new Participants(new Dealer(), players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getParticipants();
        }
    }

    private List<Player> playersTurn(Participants participants, Deck deck) {
        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(player, deck);
        }
        return players;
    }

    private void doEachPlayerTurn(Player player, Deck deck) {
        while (player.hasNextTurn() && getOneMoreCard(player)) {
            player.receiveCard(deck.distributeCard());
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private boolean getOneMoreCard(Player player) {
        try {
            final String choice = InputView.inputOneMoreCard(player.getName());
            return YesOrNo.YES == YesOrNo.find(choice);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getOneMoreCard(player);
        }
    }

    private Dealer dealerTurn(BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.doDealerGame();
        OutputView.printDealerReceiveCardCount(dealer);
        return dealer;
    }

    private void createBlackJackGameResult(List<Player> players, Dealer dealer) {
        final BlackJackGameResult blackJackGameResult = new BlackJackGameResult(dealer, players);
        final Map<WinDrawLose, Integer> dealerResult = blackJackGameResult.calculateDealerResult();
        final Map<Player, WinDrawLose> playersResult = blackJackGameResult.calculatePlayersResult();
        OutputView.printGameResult(dealer, players);
        OutputView.printWinDrawLoseResult(dealer, dealerResult, playersResult);
    }
}