//import java.awt.Graphics2D;
//import java.awt.Rectangle2D;
//import java.awt.geom.Arc2D;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class LogSpiralPanel extends JPanel
{
   private static final double GOLDEN_MEAN =  (1 + Math.sqrt(5)) / 2;
   public class SpiralViewer{
      public class PaintComponent extends JComponent
   {
    public void paintComponent(Graphics g)
   {
      /* Your code goes here.
         1. Compute the dimensions of the goldenRectangle (you can use getHeight() 
            to obtain the side size)
         2. Draw the golden rectangle
         3. Call the recursive helper method "recursiveDraw" which will draw 
            the spiral.
      */
     
     Graphics2D g2 = (Graphics2D) g;
     g.drawRect(0,0,400,(int)(400*GOLDEN_MEAN));
     recursiveDraw(g2,0,0,400,0);
     
     
   }
  }
    public static void main(String[] args)
    {
        // create and configure the frame (window) for the program
        JFrame frame = new JFrame();
        
        frame.setSize(800 /* x */, 600 /* y */);
        frame.setTitle("Spiral");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the Cityscape component and add it to the frame
        PaintComponent component = new PaintComponent();
        frame.add(component);
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
    }
  
}
//    public void paintComponent(Graphics g)
//    {
//       /* Your code goes here.
//          1. Compute the dimensions of the goldenRectangle (you can use getHeight() 
//             to obtain the side size)
//          2. Draw the golden rectangle
//          3. Call the recursive helper method "recursiveDraw" which will draw 
//             the spiral.
//       */
//      
//      Graphics2D g2 = (Graphics2D) g;
//      g.drawRect(0,0,400,(int)(400*GOLDEN_MEAN));
//      recursiveDraw(g2,0,0,400,0);
//      
//      
//    }
//    
   /**
      Method that recursively draws a logarithmic spiral.
      @param x The upper left corner x-coordinate of the golden rectangle
      @param y The upper left corner y-coordinate of the golden rectangle
      @param side the smallest side size of the golden rectangle
      @param angle The angle (0, 90, 180 or 270) where the top of the 
      current golden rectangle is located. For the outermost golden 
      rectangle, the angle is 90.
   */
   private void recursiveDraw(Graphics2D g, int x, int y, int side, int angle)
   {
       // Recursion ending condition: when the side is very small
      if (side<1)
      {
          g.drawRect(x,y,side,side);
          this.drawArc(g,x,y,side,angle);
      }
       
      // Draw the current square and arc
      else
      {
         g.drawRect(x,y,side,side);
         this.drawArc(g,x,y,side,angle);
         int newX = this.calculateNewX(x,angle,side,(int)(side*GOLDEN_MEAN));
         int newY = this.calculateNewY(y,angle,side,(int)(side*GOLDEN_MEAN));
         recursiveDraw(g,newX,newY,(int)(side*GOLDEN_MEAN),angle+90);
         if (angle == 0)
         {
             x += side - (int)(side*GOLDEN_MEAN);
             y -= side;
            } 
         else if (angle ==90)
         {
             x += side;
            }
         else if (angle == 180)
         {
             y+= side;
            }
         else if (angle == 270)
         {
             x+= side;
             y -= side;
            }
            recursiveDraw(g,x,y,side,ang);
        }

      /* Continue drawing the spiral recursively: calculate the new side 
         size, calculate the new (x, y) coordinates and the new angle. Then, 
         call "recursiveDraw" again. */
      //. . .
   }
   
   /**
      Draws the arc of the current iteration.
      @param x The x-coordinate of the square's upper-left corner  
      @param y The y-coordinate of the square's upper-left corner
      @param side The size of the side of the square (or the arc's radius)
      @param angle The angle (0, 90, 180 or 270) where the top of the 
      current golden rectangle is located. For the outermost golden 
      rectangle, the angle is 90.
   */
   private void drawArc(Graphics2D g, double x, double y, double side, int angle)
   {
      double auxX = x;
      double auxY = y;
      if (angle == 0 || angle == 270 )
      {
         auxX = x - side;
      }
      if (angle == 270 || angle == 180)
      {
         auxY = y - side;
      }
      g.drawArc((int) auxX, (int) auxY, (int) side * 2, (int) side * 2, angle, 90);
   }   
   private int calculateNewX(int x, int angle, int side, int newSide)
   {
      if (angle == 0)
         x = x + side - newSide;
      else if (angle == 90)
         x = x + side;
      else if (angle == 270)
         x = x - newSide;
      return x;
   }

   private int calculateNewY(int y, int angle, int side, int newSide)
   {
      if (angle == 0)
         y = y + side;
      else if (angle == 180)
         y = y - newSide;
      else if (angle == 270)
         y = y + side - newSide;
      return y;
   }
}