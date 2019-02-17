package cs260;

import javax.swing.JFrame;


import cs260.game_model.TicTacToeGame;

import cs260.gui.FancyDisplay;

import cs260.turn_gui.TurnIndicator;
import cs260.openfile.FileOpener;


public class TicTacToeApp
{

    //Step 1: create main of aTicTacToeApp
    public static void main(String [] args)
    {
        //Step 2: call main.start
        TicTacToeApp main = new TicTacToeApp();
        main.start();
    }

    public void start()
    {
      System.out.print("\n Start() is called: TicTacToeApp\n");

        //Step 3: create jframe called main frame
        JFrame mainFrame = new JFrame("Tic Tac Toe");

        //Step 4: create TTTgame called game with player x
        TicTacToeGame game = new TicTacToeGame('X');

        ////// tictactoe openfile /////
        //JFrame fileChoose = new JFrame("Tic Tac Toe");
        FileOpener file = new FileOpener(game);
        game.addListener(file);
        ///////////////////////////////

        //Step 5: create gamedisplay for game
        FancyDisplay gameDisplay = new FancyDisplay(game);

        //Step 6: create game.addListener for our game display
		    game.addListener(gameDisplay);

        mainFrame.getContentPane().add(gameDisplay);
        mainFrame.pack();
        mainFrame.setVisible(true);

        JFrame turnFrame = new JFrame("Tic Tac Toe");
        TurnIndicator indicator = new TurnIndicator(game);

        game.addListener(indicator);
        turnFrame.getContentPane().add(indicator);

        turnFrame.pack();
        turnFrame.setVisible(true);

        System.out.print("\nListener is added: TicTacToeApp\n");

    }

}
