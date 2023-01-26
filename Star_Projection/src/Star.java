import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Star {
    
    public Random random = new Random();
	public int stars[];
    public int starSize;
    public int starX;
    public int starY;

    public Star(int unit ) {

        stars = new int[unit];
        for(int i = 0 ; i<stars.length; i++) {
            stars[i] = 1;
        }
        starSize = 6;
    }

    public void draw(Graphics2D g) {
        try{
            for(int i = 0 ; i<stars.length; i++) {
                starX = random.nextInt(5,790);
                starY = random.nextInt(5,750);
                starSize = random.nextInt(3,7);
                if(stars[i] > 0) {
                    g.setColor(Color.white);
                    g.fillOval(starX, starY, starSize, starSize);
    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawOval(starX, starY, starSize, starSize);
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
