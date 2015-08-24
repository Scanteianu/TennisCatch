import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.util.Random;
import javax.swing.JComponent;
public class Ball extends JComponent//draws the balls
{
	public void setPosition(int x, int y)
	{
		baseX=x;
		baseY=y;
	}
	public void move(int x, int y)
	{
		
		if(baseX<=5)
			xMultiplier=xMultiplier*-1;
		if(baseX>=maxX-5)
			xMultiplier=xMultiplier*-1;
		baseX+=x*xMultiplier;
		if(baseY>=maxY)
			{
				baseY=0;
				count1++;
				baseX=random.nextInt(480);
			}
		baseY+=y;
	}
	public void setBounds(int x, int y)
	{
		maxX=x;
		maxY=y;
	}
	public int getX()
	{
		return baseX;
	}
	public int getY()
	{
		return baseY;
	}
	public Ball()
	{
		random=new Random(10000*(int)Math.random());
	}
    
    public void paintComponent(Graphics g)//this name can't change, since paintComponent is a built in method
    {
         Graphics2D g2 = (Graphics2D) g;//Recovering Graphics 2D
         //balls
         g2.setColor(Color.green);
         Ellipse2D ball1 = new Ellipse2D.Double(baseX,baseY, 9,9);
         g2.fill(ball1);
         g2.setColor(Color.black);
         g2.draw(ball1);
         //score/outs
         
    }

 
    private int baseX=0;
	private int baseY=0;
	private int maxX=1000;
	private int maxY=560;
	private int xMultiplier=1;
	public int count1=0;
	private Random random;
}

