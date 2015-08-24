import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import javax.swing.JComponent;
public class Basket extends JComponent//draws the rocket on the screen
{

	public void setPosition(int x, int y)
	{
		baseX=x;
		baseY=y;
	}
	public Basket()
	{
		
	}
    
    public void paintComponent(Graphics g)//this name can't change, since paintComponent is a built in method
    {
         Graphics2D g2 = (Graphics2D) g;//Recovering Graphics 2D
         //balls
         g2.setColor(Color.green);
         Ellipse2D ball1 = new Ellipse2D.Double(baseX+11,baseY+40, 9,9);
         Ellipse2D ball2 = new Ellipse2D.Double(baseX+26,baseY+40, 9,9);
         Ellipse2D ball3 = new Ellipse2D.Double(baseX+41,baseY+40, 9,9);
         g2.fill(ball1);
         g2.fill(ball2);
         g2.fill(ball3);
         g2.setColor(Color.black);
         g2.draw(ball1);
         g2.draw(ball2);
         g2.draw(ball3);
         //basket
         g2.setColor(Color.DARK_GRAY);
         g2.drawLine(baseX,baseY, baseX+60, baseY);//top line of basket
         g2.drawLine(baseX+10, baseY+50, baseX+50, baseY+50);// bottom line
         g2.drawLine(baseX, baseY, baseX+10, baseY+50);//left side line
         g2.drawLine(baseX+10, baseY, baseX+20, baseY+50);//left side line 2
         g2.drawLine(baseX+60, baseY, baseX+50, baseY+50);//right side line
         g2.drawLine(baseX+50, baseY, baseX+40, baseY+50);//right side line 2
         g2.drawLine(baseX+30, baseY, baseX+30, baseY+50);//middle line
         //Scores
         g2.drawString("Score: "+ score, 2, 10);
         Ellipse2D ball01 = new Ellipse2D.Double(2,15, 9,9);
         Ellipse2D ball02 = new Ellipse2D.Double(12,15, 9,9);
         Ellipse2D ball03 = new Ellipse2D.Double(22,15, 9,9);
         
         if(numBalls>=1)
         {
	         g2.setColor(Color.yellow);
	         g2.fill(ball01);
	         g2.setColor(Color.black);
	         g2.draw(ball01);
         }
         if(numBalls>=2)
         {
	         g2.setColor(Color.yellow);
	         g2.fill(ball02);
	         g2.setColor(Color.black);
	         g2.draw(ball02);
         }
         if(numBalls>=3)
         {
	         g2.setColor(Color.yellow);
	         g2.fill(ball03);
	         g2.setColor(Color.black);
	         g2.draw(ball03);
	         
         }
    }
    public void setBallsLost(int balls)
    {
    	numBalls=3-balls;
    }
    public void setScore(int points)
    {
    	score=points;
    }
	private int baseX=0;
	private int baseY=400;
	private int numBalls=3;
    private int score=0;
    private boolean giveExtra=true;
}
