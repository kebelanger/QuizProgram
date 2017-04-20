/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Author: Kristen Belanger
 */
package quizprogram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author kristenbelanger
 */
public class BaseWindow extends JFrame {
    
    protected static DataHandler dataHandler;

    private static int X = 600;
    private static int Y = 400;

    protected static int WIDTH = 550;
    protected static int HEIGHT = 375;
    
    
    public BaseWindow() {
        BaseWindow thisWindow = this;
        
        http://stackoverflow.com/questions/33170623/how-to-change-background-color-at-jframe
        getContentPane().setBackground(new Color(255,255,153));
      
        
        
        // Whenever the screen is moved, update the x and y locations so the next call to setVisible will 
        // move the window to the correct location
        // Reference for listener magic
        // http://stackoverflow.com/questions/2427815/java-how-to-register-a-listener-that-listen-to-a-jframe-movement
        this.addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                // reference for getLocation()
                //http://stackoverflow.com/questions/7950726/find-the-location-position-of-jframe-in-the-window
                Point location = thisWindow.getLocation();
                X = location.x;
                Y = location.y;
            }
        });

        // reference for resize event listener
        //http://stackoverflow.com/questions/2303305/window-resize-event
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // reference for get size
                // fix for bug where it was getting taller by the border height each window change
                // http://stackoverflow.com/questions/5097301/jframe-get-size-without-borders
                Dimension dimension = thisWindow.getContentPane().getSize();
                HEIGHT = dimension.height;
                WIDTH = dimension.width;
                
                customOnResize();
            }
        });

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dataHandler.save("data.txt");
            }
        });

    }
    
    public void customOnResize() {
        
    }
    
    // Override the setvisible method so whenever it is called, the window is moved to the right location
    // and set to be the right size
    @Override
    public void setVisible(boolean visible) {
        moveAndResize();
        super.setVisible(visible);
    }

    // sets the size and location of the window to be HEIGHT, WIDTH, X, and Y
    protected void moveAndResize() {
        
        // reference for setting size + pack
        //http://stackoverflow.com/questions/2451252/swing-set-jframe-content-area-size
        this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        // reference for setting location
        // http://stackoverflow.com/questions/1685862/swing-how-to-position-jframe-n-pixels-away-from-the-center-of-the-screen-at-fir
        this.setLocation(X, Y);
        this.pack();
    }

}
