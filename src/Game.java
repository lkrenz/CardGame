//Liam Krenz
//December 7th 2022


import java.util.Scanner;
public class Game
{
    Scanner s = new Scanner(System.in);
    private final Player player;
    private final Deck deck;

    private final Player dealer;



    //Establishes a new game
    public Game()
    {
        System.out.println("Input your name: ");
        player = new Player(s.nextLine());
        deck = new Deck();
        dealer = new Player("dealer");
        GameView window = new GameView();

    }


    //runs up the 9 rounds of the game and prints the winner
    public void playFullGame()
    {
        printInstructions();
        while (player.getPoints() < 5 && dealer.getPoints() < 5)
        {
            playGame();
        }
        if (player.getPoints() == 5)
        {
            System.out.println("You win!!!!");
        }
        else
        {
            System.out.println("The computer won :(");
        }

    }

    //Runs through rounds of the game until someone busts or the player holds
    public void playGame()
    {
        initiateRound();
        while (player.getTotal() <= 21 && dealer.getTotal() <= 21)
        {
            if (playRound())
                break;
        }
        //If a player busts the dealer wins
        if (player.getTotal() > 21)
        {
            dealer.addPoints(1);
            System.out.println("You busted so the dealer won.");
        }
        else if (dealer.getTotal() > 21)
        {
            //If the dealer busts the player wins
            player.addPoints(1);
            System.out.println("The dealer busted so you won!");
        }
        else if (player.getTotal() > dealer.getTotal())
        {
            //If nobody busted and the player has a higher hand, they win
            player.addPoints(1);
            System.out.println("You win this round!");
        }
        else if (dealer.getTotal() > player.getTotal())
        {
            //If the dealer has a higher hand they win
            dealer.addPoints(1);
            System.out.println("The dealer won this round");
        }
        else if (dealer.getTotal() == player.getTotal())
        {
            //If the hands are equal, it is a tie
            System.out.println("This round is a tie!");
        }
        printScore();
    }


    //Makes the moves for both player and computer, if either bust the round stops
    public boolean playRound()
    {
        if (playerMove())
            return true;
        if (dealerMove())
            return true;
        return false;
    }


    public void initiateRound()
    {
        deck.shuffle();
        player.reset();
        dealer.reset();
        startHand(player);
        startHand(dealer);
    }

    public void startHand(Player p)
    {
        p.addCard(deck.deal());
        p.addCard(deck.deal());
    }


    //Prints the user's hand and prompts them for their move
    public boolean playerMove()
    {
        clearScreen();
        player.printStatus();
        System.out.println("Enter 1 to hit, 2 to stand: ");
        if (s.nextInt() == 1)
        {
            Card card = deck.deal();
            System.out.println(card.getRank());
            if (player.addCard(card))
            {
                //if player busts it prints their score and exits
                System.out.println("You busted, total: " + player.getTotal());
                return true;
            }
            System.out.println("New total: " + player.getTotal());
            return false;
        }
        //If the player holds it returns true and exits
        return true;
    }

    //Performs the dealer's move and returns true if they bust
    public boolean dealerMove()
    {
        //The dealer will only draw if their total is 15 or less
        if (dealer.getTotal() <= 15)
        {
            dealer.addCard(deck.deal());
            if (dealer.getTotal() > 21)
                return true;
        }
        return false;
    }

    //Prints out the rounds won by each
    public void printScore()
    {
        System.out.println("Player: " + player.getPoints());
        System.out.println("Dealer: " + dealer.getPoints());
    }

    //Clears the screen
    public void clearScreen()
    {
        startMove();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    //Waits for the user to press enter to clear screen and beging their move
    public void startMove()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Press enter to begin your move");
        String answer = s.nextLine();
        while (!answer.equals(""))
        {
            System.out.println("Press enter to begin your move: ");
        }
    }




    public static void printInstructions()
    {
        System.out.println("The game is blackjack, each round, you are dealt 2 cards"
                + " \nthe goal is to get your card total as close to 21 as possible"
                + " \nby saying hit, you receive another card, but be careful. if "
                + " \nyou go over, you're out. Face cards are 10, and aces can be "
                + " \none or eleven. If you win five rounds before the dealer, you win");
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playFullGame();
    }
}