import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

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