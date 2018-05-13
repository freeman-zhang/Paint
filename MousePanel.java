/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: This class defines the MousePanel class which controls the main drawing area of the paint program
 * This class will track mouse movement of the user and will store all the shapes the user draws and allows them to 
 * undo, redo and clear
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MousePanel extends JPanel{
    private Color colour = Color.BLACK;
    private int shapeChoice = 0;
    private Shape currentShape = null;
    private DynamicStack<Shape> drawn = new DynamicStack<Shape>();
    private DynamicStack<Shape> undo = new DynamicStack<Shape>();
    private DynamicStack<Shape> tempStack = new DynamicStack<Shape>(); //stack used to temporary hold the shapes to be redrawn
    private JLabel statusBar;
    private boolean fill = false;
    
    public MousePanel (JLabel statusBar){
       //setting its own status bar to the parameter status bar to update the mouse position
       this.statusBar = statusBar;
       tempStack = new DynamicStack<Shape>();
       setBackground(Color.WHITE);
        
        // Create and register listener for mouse and mouse motion events
        MouseEventListener mousePanelListener = new MouseEventListener(); 
        addMouseListener( mousePanelListener ); 
        addMouseMotionListener( mousePanelListener ); 
        
    }
    //defines the undo function
    public void undo(){
        //popping off the top shape in drawn and pushing it into the undo stack
        //will only undo if drawn stack is not empty
        if (!(drawn.isEmpty())){
            undo.push(drawn.pop());
        }
        // Tell JVM to call paintComponent( g )
        repaint();
    }
    //defines the redo function
    public void redo(){
        //popping off the top shape in undo and pushing it into the drawn stack
        //will only redo if undo stack is not empty
        if (!(undo.isEmpty())){
            drawn.push(undo.pop());
        }
        // Tell JVM to call paintComponent( g )
        repaint();
    }
    //defines the clear function
    public void clear(){
        //clearing all stacks
        drawn.clear();
        undo.clear();
        tempStack.clear();
        // Tell JVM to call paintComponent( g )
        repaint();
    }
    //sets colour
    public void setColour(Color c){
        colour = c;
    }
    //returns colour
    public Color getColour(){
        return colour;
    }
    //sets shape
    public void setShape(int choice){
        shapeChoice = choice;
    }
    //sets fill
    public void setFill(boolean f){
        fill = f;
    }
    

    // Inner class that extends MouseAdapter 
    class MouseEventListener extends MouseAdapter {
        // handle mouse move event
        @Override
        public void mouseMoved( MouseEvent event ) {
            statusBar.setText( String.format( "(%d, %d)", event.getX(), event.getY() ) );
        }
        // Mouse press indicates a new shape has been started
        @Override
        public void mousePressed( MouseEvent event ) {
            //checks which shape is chosen and creates a shape of that class
            if (shapeChoice == 0){
                currentShape = new Line( event.getX(), event.getY(), event.getX(), event.getY(), colour );
            }
            else if (shapeChoice == 1){
                currentShape = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), colour, fill );
            }
            else{
                currentShape = new Oval( event.getX(), event.getY(), event.getX(), event.getY(), colour, fill );
            }
            // Tell JVM to call paintComponent( g )
            repaint(); 
        }
        
        // Mouse release indicates the new shape is finished
        @Override
        public void mouseReleased( MouseEvent event ) {
            //will only draw if there is a current shape
            if (currentShape != null){
                // Update ending coordinates 
                currentShape.setx2( event.getX() );
                currentShape.sety2( event.getY() );
                //adds the shape to the drawn stack
                drawn.push(currentShape);
                // Tell JVM to call paintComponent( g )
                repaint();
                undo.clear();
                
                // Get ready for the next shape to be drawn
                currentShape = null;
            }    
        } 
        //Mouse Dragged indicates the user is holding left click and moving the mouse
        @Override
        public void mouseDragged( MouseEvent event ) {
            currentShape.setx2( event.getX() );
            currentShape.sety2( event.getY() );
            //update statusBar
            statusBar.setText( String.format( "(%d, %d)",  event.getX(), event.getY() ) );
            repaint(); 
        }
    }
    
    // This method will be called automatically when the window is drawn or redrawn.
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        redraw(g);
        // If a shape is being drawn have it drawn on the top.
        if ( currentShape != null ){
            currentShape.draw( g );
        }
        
    }
    // This method is called automatically by the JVM when the window needs to be redrawn.
    private void redraw(Graphics g){
        Shape tempShape;
        int size = drawn.size();
        //stores all the drawn shapes in tempStack 
        for (int i = 0 ; i < size ; i++ ){
            tempStack.push(drawn.pop());
        }
        //Pops the shapes out of tempStack to draw and to be put back into the drawn stack
        for ( int i = 0 ; i < size ; i++ ){
            tempShape = tempStack.pop();

            tempShape.draw(g);
            drawn.push(tempShape);
        }
        //ensures tempStack is clear for next time redraw is called
        tempStack.clear();
    }
}