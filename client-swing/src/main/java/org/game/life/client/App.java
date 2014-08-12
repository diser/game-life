package org.game.life.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        JFrame frame = new JFrame("Game Life client");
        GamePanel p = new GamePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JLabel emptyLabel = new JLabel("hi");
        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.getContentPane().add(p, BorderLayout.CENTER);
        frame.setSize(500, 300);
        //frame.pack();
        frame.setVisible(true);
        
        while(true){
            p.invalidate();
            p.repaint();
//                       paintAlive(g2d);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
