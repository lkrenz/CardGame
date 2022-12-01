import java.util.ArrayList;

public class Player {

    private String name;
    private int points;
    private ArrayList<Card> hand;
    private int total;
    private boolean isOut;
    public Player(String name)
    {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    public Player()
    {
        this.name = "dealer";
    }


    public Player (String name, ArrayList<Card> hand)
    {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        for (Card c : hand)
        {
            this.hand.add(c);
        }
    }

    public void reset() {
        isOut = false;
        hand.clear();

        total = 0;
    }

    public boolean takeCard(int value)
    {
        total += value;
        if (total > 21)
            return true;
        return false;
    }

    public String getName()
    {
        return name;
    }

    public int getPoints()
    {
        return points;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void addPoints(int points)
    {
        this.points += points;
    }

    public void addCard(Card card)
    {
        hand.add(card);
    }

    public String toString()
    {
        return name + " has " + points + " points/n" + name + "'s cards are: " + hand;
    }
}
