package cs260;

import javax.swing.JFrame;

import cs260.routemodel.RouteManager;

import cs260.gui.MainDisplay;

//import cs260.turn_gui.TurnIndicator;

//import cs260.javapaint.BasicPaint;

//import cs260.gui.CanvasDisplay;

public class RouteApp
{

    //Step 1: create main of aRouteApp
    public static void main(String [] args)
    {
        //Step 2: call main.start
        RouteApp main = new RouteApp();
        main.start();

        //calls a painter object
        //BasicPaint painter = new BasicPaint();
        //painter.main(args);

    }

    public void start(){
      System.out.print("\n Start() is called: RouteApp\n");

        //Step 3: create jframe called main frame
        JFrame mainFrame = new JFrame("Tic Tac Toe");

        //Step 4: create TTTgame called game with player x
        RouteManager game = new RouteManager('X');


        //Step 5: create gamedisplay for game
        MainDisplay gameDisplay = new MainDisplay(game); //<<<<<<

        //Step 6: create game.addListener for our game display
		    game.addListener(gameDisplay);//<<<<<<

        mainFrame.getContentPane().add(gameDisplay);//<<<<<<
        mainFrame.pack();
        mainFrame.setVisible(true);


        System.out.print("\nListener is added: RouteApp\n");

    }

}
