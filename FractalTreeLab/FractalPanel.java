
import java.awt.*;
import javax.swing.JPanel;
import java.lang.Math;

public class FractalPanel extends JPanel
{
   private final int PANEL_WIDTH = 800;
   private final int PANEL_HEIGHT = 1000;

   private double ratio = .8;

   private final int TOPX = 200, TOPY = 20;
   private final int LEFTX = 60, LEFTY = 300;
   private final int RIGHTX = 340, RIGHTY = 300;

   private int current; //current order
   public double increment;
   public double radians;
   

   //-----------------------------------------------------------------
   //  Sets the initial fractal order to the value specified.
   //-----------------------------------------------------------------
   public FractalPanel (int currentOrder, double angle1)
   {
      current = currentOrder;
      setBackground (Color.black);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
      this.increment = angle1;
   }

   //-----------------------------------------------------------------
   //  Draws the fractal recursively. Base case is an order of 1 for
   //  which a simple straight line is drawn. Otherwise three
   //  intermediate points are computed, and each line segment is
   //  drawn as a fractal.
   //-----------------------------------------------------------------
   public void drawFractal (int order, int x1, int y1, int x2, int y2, double angle,double ratio,
                            Graphics page)
   {
      int deltaX, deltaY,x3, y3;
      double newLength;
      
      if (order == 1)
         page.drawLine (x1, y1, x2, y2);
      else
      {
         deltaX = x2 - x1;  // distance between end points
         deltaY = y2 - y1;
         
         
         newLength = Math.pow((Math.pow(deltaX,2)+Math.pow(deltaY,2)), .5);
         newLength *= ratio;
         
         x1=x2;
         y1=y2;
         
         double LEFT_SIN_ANGLE = Math.cos(angle-increment)*.8;  //purposely not symmetric
         double RIGHT_SIN_ANGLE = Math.cos(angle+increment);
         double LEFT_COS_ANGLE = Math.sin(angle-increment);
         double RIGHT_COS_ANGLE = Math.sin(angle+increment);
         
         x2 = (int)(x1 - LEFT_SIN_ANGLE*(newLength));
         y2 = (int)(y1 - LEFT_COS_ANGLE*(newLength));
         
         page.drawLine(x1,y1,x2,y2);
         drawFractal(order - 1, x1,y1,x2,y2,angle-increment,ratio,page);
         
         x3 = (int)(x1 - RIGHT_SIN_ANGLE*(newLength));
         y3 = (int)(y1 - RIGHT_COS_ANGLE*(newLength));
         
         page.drawLine(x1,y1,x3,y3);
         
         drawFractal(order - 1, x1,y1,x3,y3,angle+increment,ratio,page);
         
         //System.out.println(angle);


      }
   }

   //-----------------------------------------------------------------
   //  Performs the initial calls to the drawFractal method.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);

      page.setColor (Color.cyan);
      
      page.drawLine(400,880,400,740);

      drawFractal (current, 400,880,400,740,Math.PI/2,ratio,page);
   }

   //-----------------------------------------------------------------
   //  Sets the fractal order to the value specified.
   //-----------------------------------------------------------------
   public void setOrder (int order)
   {
      current = order;
   }

   //-----------------------------------------------------------------
   //  Returns the current order.
   //-----------------------------------------------------------------
   public int getOrder ()
   {
      return current;
   }
   
   public void setAngle (double angle)
   {
       increment = angle;
    }
    
    public double getAngle()
    {
        return increment;
    }
    
    public void setRatio (double ratio)
   {
       this.ratio = ratio;
    }
    
    public double getRatio()
    {
        return ratio;
    }
}
