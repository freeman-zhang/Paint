/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: This is the main function that runs our paint program
 */

import javax.swing.JFrame;
 
public class SuperPaint {
    public static void main( String[] args ) { 
        DrawFrame appWindow = new DrawFrame();
        appWindow.setSize( 800, 500 ); 
        appWindow.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        appWindow.setVisible( true ); 
    } 
} 