import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame{
    private Game game;
    private Image[] cardImages;
    private Image background;
    private boolean showInstructions;
    private static final int    WINDOW_WIDTH = 800,
                                WINDOW_HEIGHT = 600,
                                CARD_WIDTH = 50;


    public GameView(Game game)
    {
        this.showInstructions = true;
        this.game = game;
        cardImages = new Image[53];
        for (int i = 0; i < 51; i++)
        {
            cardImages[i] = new ImageIcon("Resources/"+ i + 1 + ".png").getImage();
        }
        cardImages[52] = new ImageIcon("Resources/back.png").getImage();
        this.background = new ImageIcon("Resources/Background.png").getImage();
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Black Jack");
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        if (showInstructions) {
            Image instructions = new ImageIcon("Resources/Instructions.png").getImage();
            g.drawImage(instructions, -5 , 11, 810, 600, this);
            this.showInstructions = false;
        }
        else {
            g.drawImage(background, -5, 11, 810, 600,this);
            drawPlayerCards(g);
        }
    }



    public void drawPlayerCards(Graphics g) {
        ArrayList<Card> playerCards = game.getPlayerHand();
        int startingX = (WINDOW_WIDTH / 2) - (playerCards.size() * 25);
        for (int i = 0; i < playerCards.size(); i++) {
            g.drawImage(cardImages[playerCards.get(i).getCardNum()], startingX + 50 * i, 500, 50, 75, this);
        }
    }
}
