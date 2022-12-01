import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private ArrayList<Player> players;
    private Player dealer;
    private Deck deck;

    public Game(int numPlayers){

        Scanner s = new Scanner(System.in);

        dealer = new Player();

        players = new ArrayList<Player>();

        this.deck = new Deck();

        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Enter player name: ");
            players.add(new Player(s.nextLine()));
        }
    }

    public int playFullGame()
    {
        printInstructions();
        while (checkWin() == null)
        {
            deck.shuffle();
            for (Player p : players)
            {
                p.reset();
            }
            playGame().addPoints(1);
        }
        System.out.println(checkWin().getName() + " Wins!");
    }

    public Player playGame()
    {
        ArrayList<Player> round = new ArrayList<Player>();
        for (Player p : players)
        {
            round.add(p);
        }
        int numPlayers = players.size();
    }

    public void playRound(ArrayList<Player> roundPlayers)
    {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < roundPlayers.size(); i++)
        {
            if (playerMove(roundPlayers.get(i)))
            {
                roundPlayers.remove(i);
            }
        }
    }

    public boolean dealerMove()
    {

    }


    public boolean playerMove(Player player)
    {
        player.takeCard(deck.deal().getValue())
    }

    public Player checkWin()
    {
        for (Player p : players)
        {
            if (p.getPoints() == 5)
            {
                return p;
            }
        }
        return null;
    }

    public static void printInstructions()
    {
        System.out.println("The game is blackjack, each round every player is dealt 2 cards"
                            + " the goal is to get your card total as close to 21 as possible"
                            + " by saying hit, you recieve another card, but be careful. if "
                            + " you go over, you're out. Face cards are 10, and aces can be "
                            + " one or eleven. First player to 5 round's won wins!");
    }




    public static void main(String[] args) {

    }
}