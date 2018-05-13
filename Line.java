/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: Fillable Shape Super Class
 */
import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape{
    public Line(){
        super();
    }
    public Line(int x1, int y1, int x2, int y2, Color color){
        super(x1, y1, x2, y2, color);
        
    }
    public void draw(Graphics g){
        g.setColor(super.getColor());
        g.drawLine( super.getx1(), super.gety1(), super.getx2(), super.gety2() );
    }
}