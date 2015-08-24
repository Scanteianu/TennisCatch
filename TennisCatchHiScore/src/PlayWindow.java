import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class PlayWindow 
{
	public static void main(String[] args)
	{
		basket = new Basket();
		//game screen
		playScreen=new JFrame();
		playScreen.setSize(1000, 600);
		playScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playScreen.getContentPane().setBackground(Color.white);
		playScreen.setVisible(true);
		//start and score screen
		infoScreen=new JFrame();
		infoScreen.setSize(400, 300);
		infoScreen.getContentPane().setBackground(Color.black);
		infoScreen.setLayout(null);
		infoScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setup play screen
		playScreen.getContentPane().setBackground(Color.black);
		playScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playScreen.getContentPane().setBackground(Color.white);
		playScreen.setVisible(true);
		initBalls();
		gameLoop();
		infoBox=new JTextArea();
		infoBox.setBackground(Color.white);
		infoBox.setBounds(10,10, 280, 45);
		nameBox=new JTextArea();
		nameBox.setBackground(Color.white);
		nameBox.setBounds(10,60, 280, 30);
		infoBox.setText("Your Score: "+points+"\nYour Name( type below )?");
		nameBox.setText("");
		
		JButton submit=new JButton("Submit");
		submit.setLocation(10,100);
		submit.setBounds(10, 100,280, 80);
		submit.setBackground(Color.GREEN);
		//setting up actions for the clear purchase button
		submit.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	//making receipt file
            	try{
            	PrintWriter scorer = new PrintWriter(new FileWriter("Scores.csv",true));
            	scorer.write(nameBox.getText()+","+points+"\n");
            	infoBox.setText("Score Recorded");
            	infoScreen.revalidate();
            	infoScreen.repaint();
            	scorer.close();}
            	catch(IOException ex) {System.out.println("Exception");}
            	
            }
        });
		
		infoScreen.add(infoBox);
		infoScreen.add(nameBox);
		infoScreen.add(submit);
		infoScreen.setVisible(true);
		System.out.println(points);
	}

	public static void initBalls()
	{
		
		ball1=new Ball();
		ball1.setBounds(500,260);
		//10000 is arbitrary multiplier to seed Random with one of 10,000 things
		ball1.setPosition(100,0);
	}
	private static void basketAdd()
	{
		playScreen.remove(basket);
		if(playScreen.getMousePosition()!=null)
		mposX=playScreen.getMousePosition().getX()-30;
		basket.setPosition((int) mposX, basketPosY);
		basket.setScore(points);
		basket.setBallsLost(ball1.count1-points);
		playScreen.add(basket);
		
	}
	private static void gameLoop()
	{
		while(ball1.count1-3<points)
		{
			try{Thread.sleep(10);}
			catch(InterruptedException ex){Thread.currentThread().interrupt();}
			if(!isPaused)
				ball1Advance();	
			basketAdd();
			playScreen.revalidate();
			playScreen.repaint();
			playScreen.add(ball1);
			if(ball1.getY()>=250 && ball1.getX()>=mposX/2 && ball1.getX()<=mposX/2+30&&allowPoint)
			{
				points++;
				allowPoint=false;
			}
			if(ball1.getY()<=10)
			{
				allowPoint=true;
			}
		}
	}
	private static void ball1Advance()
	{
		
		playScreen.remove(ball1);
		ball1.move(ballVX+(int)((Math.random()-.5)*3), ballVY+points/10);
		playScreen.add(ball1);
		
	}
	private static JFrame playScreen;
	private static JFrame infoScreen;
	private static JTextArea infoBox;
	private static Basket basket;
	private static double mposX=0;
	private static final int basketPosY=500;
	private static boolean isPaused=false;
	private static Ball ball1;
	private static int ballVX=1;
	private static int ballVY=1;
	private static int points=0;
	private static JTextArea nameBox;
	private static boolean allowPoint = true;
}
