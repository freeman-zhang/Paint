/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: Fillable Shape Super Class
 */
import java.awt.Color;

public abstract class FillableShape extends Shape{
    private boolean fill;
    
    //Sets default values if no value is chosen by the user
    public FillableShape(){
        super();
        setFill(false);
    }
    //Sets the values of the rectangle by calling the mutator classes
    public FillableShape(int x1, int y1, int x2, int y2, Color color, boolean fill){
        super(x1, y1, x2, y2, color);
        setFill(fill);
    }
    //sets fill
    public void setFill(boolean fill){
        this.fill = fill;
    }
    //return fill
    public boolean getFill(){
        return fill;
    }
    //return upper left x
    public int getUpperLeftX(){
        return (Math.min(super.getx1(), super.getx2()));
    }
    //return upper left y
    public int getUpperLeftY(){
        return (Math.min(super.gety1(), super.gety2()));
    }
    //return width
    public int getWidth(){
        return Math.abs(super.getx1() - super.getx2());
    }
    //return height
    public int getHeight(){
        return Math.abs(super.gety1() - this.gety2());
    }
}