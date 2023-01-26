import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class StarPanel extends JPanel {

    private Star stars;
    int load;

    public StarPanel(int starUnit, int load) {

        stars = new Star(starUnit);
        this.load = load;
    }

    public void paint(final Graphics g) {

        // Set Background------------------------------------------------------
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,800);
        
        // Set Borders---------------------------------------------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 5);
        g.fillRect(0, 0, 5, 800);
        g.fillRect(781, 0, 5, 800);
        
        // Set Loading---------------------------------------------------------
        g.setColor(Color.GREEN);
        g.fillRect(0, 758,load, 5);

        // Set Star------------------------------------------------------------
        try {
            stars.draw((Graphics2D)g);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
