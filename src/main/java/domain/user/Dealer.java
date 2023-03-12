package domain.user;

import domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int DEALER_THRESHOLD = 16;
    private static final int INDEX_OF_CARD_TO_SHOW = 0;

    public Dealer(final CardPool cardPool) {
        super(ReservedRole.DEALER, cardPool);
    }

    public boolean needsHit() {
        return sumCardPool() <= DEALER_THRESHOLD;
    }

    public CardPool getLimitedCardPool() {
        List<Card> originalCards = super.getCardPool().getCards();
        return new CardPool(List.of(originalCards.get(INDEX_OF_CARD_TO_SHOW)));
    }
}
