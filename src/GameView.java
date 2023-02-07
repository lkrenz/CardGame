import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame{
    private ArrayList<Card> playerCards;
    private Image[] cardImages;
    private Card dealerCard;
    private Game game;


    private static final int    WINDOW_WIDTH = 800,
                                WINDOW_HEIGHT = 600,
                                CARD_WIDTH = 50;


    public GameView()
    {
        cardImages = new Image[53];
        for (int i = 0; i < 53; i++)
        {
            cardImages[i] = new ImageIcon(i + ".png").getImage();
        }
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Black Jack");
        this.setVisible(true);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

    }

    public void drawPlayerCards(Graphics g)
    {
        int startingX = WINDOW_WIDTH - (playerCards.size() * 25);
        for (int i = 0; i < playerCards.size(); i++)
        {
            g.drawImage(cardImages[i], startingX + 50 * i, 650, this);
        }
    }
}
