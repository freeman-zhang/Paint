/* Name: Freeman Zhang
 * 
 * Date: May 2, 2018
 * 
 * Description: Fillable shape super class
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class Shape{
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    
    
    //Sets default values if no value is chosen by the user
    public Shape(){
        this(0, 0, 0, 0, Color.BLACK);
    }
    //Sets the values of the rectangle by calling the mutator classes
    public Shape(int x1, int y1, int x2, int y2, Color color){
        setx1(x1);
        sety1(y1); 
        setx2(x2);
        sety2(y2);
        setColor(color);
    }
    //sets the x1
    public void setx1(int x1){
        //making sure x1 is greater than 0
        if (x1 >= 0){
            this.x1 = x1;
        }
        else{
            this.x1 = 1;
        }
    }
    //sets the x2
    public void setx2(int x2){
        //making sure x2 is greater than 0
        if (x2 >= 0){
            this.x2 = x2;
        }
        else{
            x2 = 1;
        }
    }
    //sets the y1
    public void sety1(int y1){
        //making sure y1 is greater than 0
        if (y1 >= 0){
            this.y1 = y1;
        }
        else {
            y1 = 1;
        }
    }
    //sets the y2
    public void sety2(int y2){
        //making sure the y2 is greater than 0
        if (y2 >= 0){
            this.y2 = y2;
        }
        else{
            y2 = 1;
        }
    }  
    //return x1
    public int getx1(){
        return x1;
    }
    //return x2
    public int getx2(){
        return x2;
    }
    //return y1
    public int gety1(){
        return y1;
    }
    //return y2
    public int gety2(){
        return y2;
    }
    //sets color
    public void setColor(Color color){
        this.color = color;
    }
    //return color
    public Color getColor(){
        return color;
    }
    public abstract void draw(Graphics g);
    
}