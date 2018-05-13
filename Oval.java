/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: Oval class
 */
import java.awt.Color;
import java.awt.Graphics;


public class Oval extends FillableShape{
    public Oval(){
        super();
    }
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean fill){
        super(x1, y1, x2, y2, color, fill);
        
    }
    public void draw(Graphics g){
        g.setColor(super.getColor());
        if (super.getFill()){
            g.fillOval( super.getUpperLeftX(), super.getUpperLeftY(), super.getWidth(), super.getHeight() );
        }
        else{
            g.drawOval( super.getUpperLeftX(), super.getUpperLeftY(), super.getWidth(), super.getHeight() );
        }
    }
}