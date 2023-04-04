package PartIV;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollDice extends JFrame {

	private static final long serialVersionUID = 1L;
	int dice1Val, dice2Val, diceSum;
	JPanel GUI;
	ImagePanel dicePanel1, dicePanel2;
	JPanel resultPanel;
	JLabel resultLabel;
	JPanel tablePanel;
	JPanel controlPanel;
	JPanel gridPanel;
	JButton rollDice;
	JPanel dotPanel;
	String[] diceImage;
	int dotX;
	int dotY;
	boolean finished;

	public RollDice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupPanels();
		this.setSize(500, 800);
		this.setVisible(true);
	}

	private void setupPanels() {
		GUI = new JPanel();
		GUI.setLayout(null);

		// initialize the image array
		diceImage = new String[6];
		diceImage[0] = "die1.png";
		diceImage[1] = "die2.png";
		diceImage[2] = "die3.png";
		diceImage[3] = "die4.png";
		diceImage[4] = "die5.png";
		diceImage[5] = "die6.png";

		// dice1 and dice2 panel to display two dices
		dicePanel1 = new ImagePanel(diceImage[dice1Val]);
		dicePanel1.setSize(250, 250);
		dicePanel1.setLocation(0, 0);

		dicePanel2 = new ImagePanel(diceImage[dice2Val]);
		dicePanel2.setSize(250, 250);
		dicePanel2.setLocation(230, 0);

		// display the grid
		tablePanel = new JPanel();
		tablePanel.setSize(500, 400);
		tablePanel.setLocation(0, 250);

		gridPanel = new JPanel(new GridLayout(5, 5, 0, 0));
		gridPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

		// display the black dot in grid
		dotPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				int dotSize = 50 / 2;
				g.fillOval((70 - dotSize) / 2, (70 - dotSize) / 2, dotSize, dotSize);
			}
		};
		dotPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		dotPanel.setBackground(Color.WHITE);

		// display the black dot
		for (int i = 0; i < 25; i++) {

			if (i == 0) {
				JLabel label = new JLabel();
				label.setOpaque(true);
				label.setBackground(Color.white);
				label.setPreferredSize(new Dimension(70, 70));
				label.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
				gridPanel.add(label.add(dotPanel));

			} else {
				JLabel label = new JLabel();
				label.setOpaque(true);
				label.setBackground(Color.white);
				label.setPreferredSize(new Dimension(70, 70));
				label.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
				gridPanel.add(label);
			}

		}
		tablePanel.add(gridPanel, BorderLayout.CENTER);

		// display the sum result
		resultPanel = new JPanel();
		resultPanel.setSize(500, 100);
		resultPanel.setLocation(0, 700);
		resultLabel = new JLabel("Result: " + diceSum);
		resultPanel.add(resultLabel);

		// add the result to control Panel
		controlPanel = new JPanel();
		controlPanel.setSize(500, 100);
		controlPanel.setLocation(0, 730);

		// roll dice buttom
		rollDice = new JButton("Roll Dice");
		RollBoth r = new RollBoth();
		rollDice.addActionListener(r);
		controlPanel.add(rollDice);

		GUI.add(dicePanel1);
		GUI.add(dicePanel2);
		GUI.add(tablePanel, BorderLayout.CENTER);
		GUI.add(controlPanel, BorderLayout.SOUTH);
		GUI.add(resultPanel, BorderLayout.CENTER);
		this.add(GUI);

	}

	private void updateGrid(int x, int y) {
		tablePanel.removeAll();

		// display the grid
		tablePanel = new JPanel();
		tablePanel.setSize(500, 400);
		tablePanel.setLocation(0, 250);

		gridPanel = new JPanel(new GridLayout(5, 5, 0, 0));
		gridPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

		// display the black dot
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == x && j == y) {
//					System.out.println(i);
//					System.out.println(j);
					JLabel label = new JLabel();
					label.setOpaque(true);
					label.setBackground(Color.white);
					label.setPreferredSize(new Dimension(70, 70));
					label.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
					gridPanel.add(label.add(dotPanel));
				} else {
					JLabel label = new JLabel();
					label.setOpaque(true);
					label.setBackground(Color.white);
					label.setPreferredSize(new Dimension(70, 70));
					label.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
					gridPanel.add(label);
				}
			}
		}
		tablePanel.add(gridPanel, BorderLayout.CENTER);
		GUI.add(tablePanel);
	}

	private void displayResult(int diceSum) {

		JLabel finish = new JLabel();

//		tablePanel.removeAll();
//		tablePanel.setSize(500, 400);
//		tablePanel.setLayout(new GridLayout());
		finish.setText("Finished! The sum is " + diceSum);
		finish.setFont(new Font("Verdana", Font.BOLD, 25));
		finish.setLocation(250, 400);
		tablePanel.add(finish);
		tablePanel.revalidate();
		tablePanel.repaint();

	}

	// once roll the dice, update sum, images, and the grid
	private class RollBoth implements ActionListener {
		
		Random rand = new Random();
		int time = rand.nextInt(10);
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Timer timer = new Timer(100, new ActionListener() {
				
				int count = 0;
				
				@Override
		        public void actionPerformed(ActionEvent e) {
		        	
					if (count < time) {
						dice1Val = rand.nextInt(6);
			        	dice2Val = rand.nextInt(6);
			        	dicePanel1.updateImage(diceImage[dice1Val]);
			        	dicePanel1.repaint();
			        	dicePanel2.updateImage(diceImage[dice2Val]);
			        	dicePanel2.repaint();
			        	count++;
			        	
					}else {
						dice1Val = rand.nextInt(6);
			        	dice2Val = rand.nextInt(6);
			        	diceSum += dice1Val + dice2Val + 2;
			        	dicePanel1.updateImage(diceImage[dice1Val]);
			        	dicePanel1.repaint();
			        	dicePanel2.updateImage(diceImage[dice2Val]);
			        	dicePanel2.repaint();
			        	
			        	int i = diceSum / 5;
			        	int j = diceSum % 5;

			        	if (diceSum < 24) {
			        		// next position
			        		updateGrid(i, j);
			        	} else if (diceSum == 24) {
			        		// final position
			        		updateGrid(4, 4);
			        	} else {
			        		// display finished!
			        		updateGrid(4, 4);
			        		displayResult(diceSum);
			        	}

			        	resultLabel.setText("Result :" + diceSum);
			        	((Timer) e.getSource()).stop();
					}
		        }
			});
			timer.start();
			
		}	
	}
		

	public static void main(String[] args) {
		RollDice rollDice = new RollDice();
	}
}
