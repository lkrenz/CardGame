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

    public void playFullGame()
    {
        printInstructions();
        while (checkWin() == null)
        {
            deck.shuffle();
            for (Player p : players)
            {
                p.reset();
            }
            playGame();
        }
        System.out.println(checkWin().getName() + " Wins!");
    }

    public void playGame()
    {
        Player dealer = new Player();
        Scanner s = new Scanner(System.in);
        ArrayList<Player> round = new ArrayList<Player>();
        for (Player p : players)
        {
            round.add(p);
        }
        int numPlayers = players.size();
        while (round.size() > 0 && dealer.getTotal() < 21)
        {
            playRound(round);
        }
        addWinners(round);
    }

    public void playRound(ArrayList<Player> roundPlayers)
    {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < roundPlayers.size(); i++)
        {
            System.out.println("Your current score is: " + roundPlayers.get(i).getTotal());
            System.out.println(roundPlayers.get(i).getName() + " enter 1 for hit or 2 for stay: ");
            if (s.nextInt() == 1)
            {
                if (playerMove(roundPlayers.get(i)))
                {
                    System.out.println("You busted, score: " + roundPlayers.get(i).getTotal());
                    roundPlayers.remove(i);
                }
                System.out.println("Your new total is " + roundPlayers.get(i).getTotal());
                continue;
            }
            System.out.println("You're holding with a score of: " + roundPlayers.get(i).getTotal());
            roundPlayers.get(i).setOut(true);
        }
        dealerMove();
    }

    public void dealerMove()
    {
        if (dealer.getTotal() <= 15)
            dealer.takeCard(deck.deal().getValue());
    }


    public boolean playerMove(Player player)
    {
        if (player.takeCard(deck.deal().getValue()))
            return true;
        return false;
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

    public void addWinners(ArrayList<Player> winners)
    {
        for (Player p : winners)
        {
            p.addPoints(1);
        }
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
        Game g = new Game(1);
        g.playFullGame();
    }
}