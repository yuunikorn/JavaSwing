package cs260.turn_gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import cs260.game_model.TicTacToeGame;
import cs260.game_model.TicTacToeListener;

/**
 * @author cassa
 */
public class TurnIndicator
		extends JComponent
		implements TicTacToeListener
{
    private TicTacToeGame game;

    private static int WIDTH = 400;
    private static int HEIGHT = 80;
    private static int FONT_WEIGHT = 60;
		//public JFileChooser j = new JFileChooser();
		//public JFileChooser j = new JFileChooser("d:", FileSystemView.getFileSystemView());
		//public JFileChooser j = new JFileChooser();


    public TurnIndicator(TicTacToeGame game)
    {
				//j.showSaveDialog(null);

        this.game = game;

        Font myFont = new Font("TimesRoman", Font.PLAIN, 60);
        this.setFont(myFont);
        FontMetrics metrics = getFontMetrics(myFont);
        int height = metrics.getHeight();

        setSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        char player = game.getCurrentPlayer();

        if (game.gameIsWon(player)) {
            g.drawString(player + " WINS!!!", 0, HEIGHT);
        } else {
            g.drawString(player + "'s turn", 0, HEIGHT);
        }
				System.out.print("\npaintComponent event is called: TurnIndicator\n");
    }

	public void update()
	{
		repaint();
		System.out.print("\nUpdate is called: TicTacToeControl\n");
	}
}
