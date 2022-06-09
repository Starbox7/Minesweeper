import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;

public class Game_Screen extends JFrame {
	private static String level;
	private JLabel la_Timer = new JLabel("Time");
	private int mineNum;
	private int randomNums[];
	private JButton bt[];
	private int i = 0;

	private void createMenu() {
		JMenuBar mb = new JMenuBar();

		GameMenuActionListener menuListener = new GameMenuActionListener();

		JMenu screenMenuA = new JMenu("Options");
		JMenuItem menuItemA = new JMenuItem("Restart Game");
		menuItemA.addActionListener(menuListener);
		screenMenuA.add(menuItemA);
		screenMenuA.addSeparator();

		JMenuItem menuItemB = new JMenuItem("End Game");
		menuItemB.addActionListener(menuListener);
		screenMenuA.add(menuItemB);
		screenMenuA.addSeparator();

		JMenuItem menuItemC = new JMenuItem("System Test");
		menuItemC.addActionListener(menuListener);
		screenMenuA.add(menuItemC);

		mb.add(screenMenuA);
		setJMenuBar(mb);
	}

	public void randomNums(int rn) {
		randomNums = new int[rn];
		Random r = new Random();

		for (int i = 0; i < rn; i++) {
			randomNums[i] = r.nextInt(rn * rn);
			for (int j = 0; j < i; j++) {
				if (randomNums[i] == randomNums[j]) {
					i--;
				}
			}
		}
		for (int i = 0; i < rn; i++)
			System.out.println(randomNums[i]); // 중복 없는 난수 생성 확인
	}

	public Game_Screen(String level) {
		this.level = level;
		if (level == "Easy")
			mineNum = 10;
		else if (level == "Normal")
			mineNum = 20;
		else if (level == "Hard")
			mineNum = 50;
		randomNums(mineNum);
		bt = new JButton[mineNum * mineNum];

		setTitle("Game Start!!");
		createMenu();
		setSize(650, 750);
		setVisible(true);

		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout());
		c.add(new NorthPane(), BorderLayout.NORTH);
		c.add(new CenterPane(), BorderLayout.CENTER);
	}

	class NorthPane extends JPanel {

		public NorthPane() {
			this.setBackground(Color.darkGray);
			la_Timer.setForeground(Color.WHITE);
			this.add(la_Timer);
		}
	}

	class CenterPane extends JPanel {
		public CenterPane() {
			this.setLayout(new GridLayout(mineNum, mineNum, 1, 1));
			this.setBackground(Color.GRAY);

			for (int i = 0; i < mineNum * mineNum; i++) {
				bt[i] = new JButton("");
				bt[i].setBackground(Color.LIGHT_GRAY);
				bt[i].addActionListener(new ButtonActionListener());
				this.add(bt[i]);
			}
			for (int i = 0; i < mineNum; i++) {
				System.out.println(randomNums[i]);
				bt[randomNums[i]].setText("Mine");
				bt[randomNums[i]].setForeground(Color.LIGHT_GRAY);
			}
			for (int i = 0; i < mineNum * mineNum; i++) {
				int buttonNum = mineNum * mineNum;
				int mineCount = 0;
				if (bt[i].getText() != "Mine") {
					if (i == 0) {
						if (bt[i + 1].getText() == "Mine")
							mineCount++;
						if (bt[i + mineNum].getText() == "Mine")
							mineCount++;
						if (bt[i + (mineNum + 1)].getText() == "Mine")
							mineCount++;
					} else {
						// ---------------------------------------------------------------------------------
						if (i != 0) {
							if (i > mineNum && (int) (i % mineNum) != 0 && bt[i - (mineNum + 1)].getText() == "Mine")
								mineCount++;
							if (i >= mineNum && bt[i - mineNum].getText() == "Mine")
								mineCount++;
							if (i >= mineNum && (int) (i % mineNum) != (mineNum - 1)
									&& bt[i - (mineNum - 1)].getText() == "Mine")
								mineCount++;
							if (i > 0 && (int) (i % mineNum) != 0 && bt[i - 1].getText() == "Mine")
								mineCount++;
						}
						if (i != (buttonNum - 1)) {
							if (i < buttonNum && (int) (i % mineNum) != (mineNum - 1) && bt[i + 1].getText() == "Mine")
								mineCount++;
							if (i < (buttonNum - (mineNum + 1)) && i != 0 && (int) (i % mineNum) != 0
									&& bt[i + (mineNum - 1)].getText() == "Mine")
								mineCount++;
							if (i < (buttonNum - mineNum) && bt[i + mineNum].getText() == "Mine")
								mineCount++;
							if (i < (buttonNum - (mineNum + 1)) && (int) (i % mineNum) != (mineNum - 1)
									&& bt[i + (mineNum + 1)].getText() == "Mine")
								mineCount++;

						}
					}
				}
				if (mineCount != 0) {
					bt[i].setText("" + mineCount);
					bt[i].setForeground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton actionBt = (JButton) e.getSource();
			if (actionBt.getText() == "Mine") {
				for (int i = 0; i < mineNum * mineNum; i++) {
					if (bt[i].getText() == "Mine")
						bt[i].setForeground(Color.RED);
				}
			} else if (actionBt.getText() == "") {
				for (i = 0; i < mineNum * mineNum; i++) {
					if (e.getSource() == bt[i]) {
						bt[i].setBackground(Color.GRAY);
						while (true) {
							// exploreMap();
							break;
						}
					}
				}
			} 
				else {
				actionBt.setBackground(Color.GRAY);
				if (actionBt.getText().equals("1")) {
					actionBt.setForeground(Color.BLUE);
				}
				else if (actionBt.getText().equals("2")) {
					actionBt.setForeground(Color.CYAN);
				} else {
					actionBt.setForeground(Color.GREEN);
				}
				System.out.println(actionBt.getText() == "2"); //깊은 복사, 얕은 복사 비교
			}
		}
	}

	class GameMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch (cmd) {
			case "Restart Game":

				break;
			case "End Game":
				System.exit(0);
				break;
			case "System Test":
				System.out.println(level);
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Game_Screen(level);
	}

}
