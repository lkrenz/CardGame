import java.util.ArrayList;

public class Player {

    private String name;
    private int points;
    private final ArrayList<Card> hand;
    private int total;
    private int numHighAce;

    //Constructs a player with only a name
    public Player(String name)
    {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

    public int getTotal() {
        return total;
    }


    //Constructs player with name and hand
    public Player (String name, ArrayList<Card> hand)
    {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        this.hand.addAll(hand);
    }


    //Removes a player's cards and resets total and numHighAce
    public void reset() {
        if (!hand.isEmpty())
            hand.clear();
        total = 0;
        numHighAce = 0;
    }


    //Adds a card to the player's hand and checks if they busted
    //Returns true if they bust, false if not
    public boolean addCard(Card card)
    {
        hand.add(card);
        //If they receive an ace it is automatically set to 11
        if (card.getValue() == 1)
        {
            total += 11;
            numHighAce += 1;
        }
        if (isOut())
        {
            //If they bust but have an ace at 11, it is set to 1
            if (numHighAce > 0)
            {
                total -= 10;
                numHighAce--;
                System.out.println("Your ace is now worth 1");
                return false;
            }
            return true;
        }
        total += card.getValue();
        return false;
    }

    public boolean isOut()
    {
        return (total > 21);
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

    public String toString()
    {
        return name + " has " + points + " points/n" + name + "'s cards are: " + hand;
    }

    //Prints out a player's hand and total
    public void printStatus()
    {
        System.out.println(name + " your current total is: " + total);
        System.out.print("WIth cards: ");
        for (Card c : hand)
        {
            System.out.print(c.getRank() + ", ");
        }
    }
}
