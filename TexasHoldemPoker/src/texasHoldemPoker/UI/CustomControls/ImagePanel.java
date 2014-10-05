package texasHoldemPoker.UI.CustomControls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	  private Image img;  

    public ImagePanel(String imageFilename) {
        this(new ImageIcon(imageFilename).getImage());  
    }
    
    public ImagePanel(Image img) {  
        this.img = img;  
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));  
        setPreferredSize(size);  
        setMinimumSize(size);  
        setMaximumSize(size);  
        setSize(size);  
        setLayout(null);  
      }      

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null); // see javadoc for more info on the parameters            
    }

}