/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: This class defines the DrawFrame. This will create a paint program containing multiple widgets
 * and shapes that allow the user to interact with
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DrawFrame extends JFrame {
    private JLabel statusBar; 
    private MousePanel mousePanel;
    private JButton undo;
    private JButton redo;
    private JButton clear;
    private JButton colour;
    private String[] shapeTypes = { "Line","Rectangle", "Oval" };
    private JComboBox shapeList;
    private JCheckBox fill;
    
    public DrawFrame() {
        super( "Paint" );
        
        //status bar to track mouse movement
        statusBar = new JLabel( "(0,0)" ); 
        add( statusBar, BorderLayout.SOUTH );
       
        
        //Options menu
        JPanel menu = new JPanel(); 
        menu.setLayout( new GridLayout( 1, 6 ) );
        ActionListener buttonListener = new ButtonEventListener();
        //creating the buttons
        undo = new JButton( "Undo" );
        undo.addActionListener( buttonListener );
        redo = new JButton( "Redo" );
        redo.addActionListener( buttonListener );
        clear = new JButton( "Clear" );
        clear.addActionListener( buttonListener );
        colour = new JButton( "Colour"); 
        colour.addActionListener( buttonListener );
        //combobox
        shapeList = new JComboBox<String>(shapeTypes);
        shapeList.addItemListener(new ShapeChooserListener() );
        //defaults the combobox to be Line
        shapeList.setSelectedIndex(0);
  
        //checkbox for filled
        fill = new JCheckBox("Filled");
        //creating and adding an event listener for checkbox
        ItemListener checkBoxListener = new CheckBoxEventListener();
        fill.addItemListener(checkBoxListener);

        //adding the options into the menu layout
        menu.add(undo);
        menu.add(redo);
        menu.add(clear);
        menu.add(colour);
        menu.add(shapeList);
        menu.add(fill);
        //adding the menu to the top of the window
        add(menu,  BorderLayout.NORTH);
        
         // Add mouse panel to the frame to track mouse movement and draw accordingly
        mousePanel = new MousePanel(statusBar);
        add(mousePanel, BorderLayout.CENTER);
        
    } 
    
    class ButtonEventListener implements ActionListener {
        // We override the actionPerformed() method as required by the ActionListener Interface
        @Override 
        public void actionPerformed( ActionEvent e ) {
            JButton button = (JButton)e.getSource();
            //checking if user pressed the undo button
            if ( button == undo ){
                //calling the undo function
                mousePanel.undo();
            }
            //checking if user pressed the redo button
            if ( button == redo ){
                //calling the redo function
                mousePanel.redo();
            }
            //checking if user pressed the clear button
            if ( button == clear ){
                //calling the clear function
                mousePanel.clear();
            }
            //checking if user pressed the colour button
            if ( button == colour ){
                //using JColorChooser to allow user to select a colour
                //passes that colour into the setColour function
                mousePanel.setColour( JColorChooser.showDialog(null, "Choose a color", mousePanel.getColour()));
            }
        }         
    }
    
    //Event listener for shape chooser combo box
    class ShapeChooserListener implements ItemListener{
        // This method will be called automatically whenever a JComboBox event occurs.
        @Override
        public void itemStateChanged( ItemEvent e ) {
            mousePanel.setShape(shapeList.getSelectedIndex());
        }         
    }
    
    // Inner class for handling events on lockBox (JCheckBox)
    class CheckBoxEventListener implements ItemListener {
        // We override the itemStateChanged() method as required by the ActionListener Interface
        @Override 
        public void itemStateChanged( ItemEvent e ) {
            // When the user clicks the lockBox we enable or disable the userNameField
            if ( fill.isSelected() ) {
                mousePanel.setFill(true);
            } 
            else {
                mousePanel.setFill(false);
            }
        }         
    }
    
}