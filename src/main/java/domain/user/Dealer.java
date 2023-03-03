package domain.user;

import domain.user.CardPool;
import domain.user.Player;

public class Dealer extends Player {

    private static final int DEALER_THRESHOLD = 16;

    public Dealer(String playerName, CardPool cardPool) {
        super(playerName, cardPool);
    }

    public boolean isHit() {
        return sumCardPool() <= DEALER_THRESHOLD;
    }
}
