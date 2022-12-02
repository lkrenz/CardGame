import java.sql.Array;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int cardsLeft;

    private static String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};

    private static String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

//    public Deck(String[] rank, String[] suit, int[] values)
//    {
//        deck = new ArrayList<Card>();
//        cardsLeft = rank.length;
//        for (int i = 0; i < suit.length; i++)
//        {
//            for (int j = 0; j < rank.length; j++)
//            {
//                deck.add(new Card(suit[i], rank[j], j + 1));
//            }
//        }
//    }
    public Deck()
    {
        deck = new ArrayList<Card>();
        cardsLeft = 52;
        for (int i = 0; i < suits.length; i++)
        {
            for (int j = 0; j < ranks.length; j++)
            {
                deck.add(new Card(suits[i], ranks[j], j + 1));
            }
        }
    }

    public boolean isEmpty()
    {
        if (cardsLeft > 0)
            return true;
        return false;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }

    public Card deal()
    {
        if (isEmpty())
            return null;
        cardsLeft--;
        return deck.get(cardsLeft);
    }

    public void shuffle()
    {
        for (int i = 0; i < deck.size(); i++)
        {
            int index = (int) (Math.random() * deck.size());
            deck.set(i, deck.set(index, deck.get(i)));
        }
        cardsLeft = deck.size();
    }

}
