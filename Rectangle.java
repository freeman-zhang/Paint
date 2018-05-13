/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: Rectangle class
 */
import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends FillableShape{
    public Rectangle(){
        super();
    }
    public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean fill){
        super(x1, y1, x2, y2, color, fill);
        
    }
    public void draw(Graphics g){
        g.setColor(super.getColor());
        if (super.getFill()){
            g.fillRect( super.getUpperLeftX(), super.getUpperLeftY(), super.getWidth(), super.getHeight() );
        }
        else{
            g.drawRect( super.getUpperLeftX(), super.getUpperLeftY(), super.getWidth(), super.getHeight() );
        }
    }
}