package edu.union.gui;

import javax.swing.JFrame;

import edu.union.gui.game_model.TicTacToeGame;

import edu.union.gui.guidisplay.MainDisplay;

import edu.union.bikeride.manager.*;
import edu.union.gui.gui_methods.*;

public class RouteApp
{

    //Step 1: create main of aRouteApp
    public static void main(String [] args)
    {
        //Step 2: call main.start
        RouteApp main = new RouteApp();
        main.start();

    }

    public void start(){
      System.out.print("\n Start() is called: RouteApp\n");

        //Step 3: create jframe called main frame
        JFrame mainFrame = new JFrame("Tic Tac Toe");

        //Step 4: create TTTgame called game with player x
        TicTacToeGame game = new TicTacToeGame('X');



        //Step 5: create gamedisplay for game


        RouteManager manager = new RouteManager();

        CSVHandler handler = new CSVHandler(manager);

        OpenSaveOptions options = new OpenSaveOptions(handler);
        MainDisplay gameDisplay = new MainDisplay(game, options); //<<<<<<
        manager.addObserver(gameDisplay);

        //Step 6: create game.addListener for our game display
		game.addListener(gameDisplay);//<<<<<<

        mainFrame.getContentPane().add(gameDisplay);//<<<<<<
        mainFrame.pack();
        mainFrame.setVisible(true);



        System.out.print("\nListener is added: RouteApp\n");

    }

}
