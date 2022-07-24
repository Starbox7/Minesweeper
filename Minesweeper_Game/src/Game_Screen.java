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
	private int temp[];
	private int tempNum = 0;

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

	private void randomNums(int rn) {
		if (rn == 30)
			randomNums = new int[rn * 7];
		else
			randomNums = new int[rn];
		Random r = new Random();
		if (rn == 30)
			for (int i = 0; i < rn * 7; i++) {
				randomNums[i] = r.nextInt(rn * rn);
				for (int j = 0; j < i; j++) {
					if (randomNums[i] == randomNums[j]) {
						i--;
					}
				}
			}
		else
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
	
	private void exploreNum(int n) {
		int buttonNum = mineNum * mineNum; //buttonNum-1 == 99 (Ex)
		
		if(n==0) {
			if(bt[n+1].getText()!=""&&bt[n+1].getText()!="B") {
				bt[n+1].setBackground(Color.GRAY);
				if (bt[n+1].getText().equals("1")) {
					bt[n+1].setForeground(Color.BLUE);
				} else if (bt[n+1].getText().equals("2")) {
					bt[n+1].setForeground(Color.CYAN);
				} else {
					bt[n+1].setForeground(Color.GREEN);
				}
			}
			if(bt[n+buttonNum].getText()!=""&&bt[n+buttonNum].getText()!="B") {
				bt[n+buttonNum].setBackground(Color.GRAY);
				if (bt[n+buttonNum].getText().equals("1")) {
					bt[n+buttonNum].setForeground(Color.BLUE);
				} else if (bt[n+buttonNum].getText().equals("2")) {
					bt[n+buttonNum].setForeground(Color.CYAN);
				} else {
					bt[n+buttonNum].setForeground(Color.GREEN);
				}
			}
			if(bt[n+buttonNum+1].getText()!=""&&bt[n+buttonNum+1].getText()!="B") {
				bt[n+buttonNum+1].setBackground(Color.GRAY);
				if (bt[n+buttonNum+1].getText().equals("1")) {
					bt[n+buttonNum+1].setForeground(Color.BLUE);
				} else if (bt[n+buttonNum+1].getText().equals("2")) {
					bt[n+buttonNum+1].setForeground(Color.CYAN);
				} else {
					bt[n+buttonNum+1].setForeground(Color.GREEN);
				}
			}
		}
		else if(n==buttonNum-1) {
			if(bt[n-1].getText()!=""&&bt[n-1].getText()!="B") {
				bt[n-1].setBackground(Color.GRAY);
				if (bt[n-1].getText().equals("1")) {
					bt[n-1].setForeground(Color.BLUE);
				} else if (bt[n-1].getText().equals("2")) {
					bt[n-1].setForeground(Color.CYAN);
				} else {
					bt[n-1].setForeground(Color.GREEN);
				}
			}
			if(bt[n-buttonNum].getText()!=""&&bt[n-buttonNum].getText()!="B") {
				bt[n-buttonNum].setBackground(Color.GRAY);
				if (bt[n-buttonNum].getText().equals("1")) {
					bt[n-buttonNum].setForeground(Color.BLUE);
				} else if (bt[n-buttonNum].getText().equals("2")) {
					bt[n-buttonNum].setForeground(Color.CYAN);
				} else {
					bt[n-buttonNum].setForeground(Color.GREEN);
				}
			}
			if(bt[n-buttonNum-1].getText()!=""&&bt[n-buttonNum-1].getText()!="B") {
				bt[n-buttonNum-1].setBackground(Color.GRAY);
				if (bt[n-buttonNum-1].getText().equals("1")) {
					bt[n-buttonNum-1].setForeground(Color.BLUE);
				} else if (bt[n-buttonNum-1].getText().equals("2")) {
					bt[n-buttonNum-1].setForeground(Color.CYAN);
				} else {
					bt[n-buttonNum-1].setForeground(Color.GREEN);
				}
			}
		}
		
		else {
			
		}
	}

	private void exploreMap(int j) {
		String buttonColor = "java.awt.Color[r=192,g=192,b=192]";
		int buttonNum = mineNum * mineNum;
		temp[tempNum] = j;
		// System.out.println("함수시작시 호출 " + j+ " temp " + temp[tempNum]+" tempNum
		// "+tempNum);
		if (j == 0) {
			if (bt[j + mineNum].getText() == "" && buttonColor.equals(bt[j + mineNum].getBackground().toString())) {
				j += mineNum;
				bt[j].setBackground(Color.GRAY);
				exploreNum(j);
				tempNum++;
				exploreMap(j);
				// System.out.println("0아래 " + j + " temp " + temp[tempNum-1]+" tempNum
				// "+tempNum);
			} else if (bt[j + 1].getText() == "" && buttonColor.equals(bt[j + 1].getBackground().toString())) {
				j += 1;
				bt[j].setBackground(Color.GRAY);
				exploreNum(j);
				tempNum++;
				exploreMap(j);
				// System.out.println("0오른" + j+ " temp " + temp[tempNum-1]+" tempNum
				// "+tempNum);
			} else {
				if (tempNum != 0) {
					tempNum -= 1;
					exploreMap(temp[tempNum]);
					// System.out.println("전" + j+ " temp " + temp[tempNum-1]+" tempNum "+tempNum);
				}
			}
		} else {
			// ---------------------------------------------------------------------------------
			if (j != 0 && j != (buttonNum - 1)) {
				if (j >= mineNum && bt[j - mineNum].getText() == ""
						&& buttonColor.equals(bt[j - mineNum].getBackground().toString())) {
					j -= mineNum;
					bt[j].setBackground(Color.GRAY);
					exploreNum(j);
					tempNum++;
					exploreMap(j);
					// System.out.println("위" + j+ " temp " + temp[tempNum-1]+" tempNum "+tempNum);
				} else if (j > 0 && (int) (j % mineNum) != 0 && bt[j - 1].getText() == ""
						&& buttonColor.equals(bt[j - 1].getBackground().toString())) {
					j -= 1;
					bt[j].setBackground(Color.GRAY);
					exploreNum(j);
					tempNum++;
					exploreMap(j);
					// System.out.println("왼" + j+ " temp " + temp[tempNum-1]+" tempNum "+tempNum);
				} else if (j < (buttonNum - mineNum) && bt[j + mineNum].getText() == ""
						&& buttonColor.equals(bt[j + mineNum].getBackground().toString())) {
					j += mineNum;
					bt[j].setBackground(Color.GRAY);
					exploreNum(j);
					tempNum++;
					exploreMap(j);
					// System.out.println("아래" + j+ " temp " + temp[tempNum-1]+" tempNum "+tempNum);
				} else if (j < buttonNum && (int) (j % mineNum) != (mineNum - 1) && bt[j + 1].getText().equals("")
						&& buttonColor.equals(bt[j + 1].getBackground().toString())) {
					j += 1;
					bt[j].setBackground(Color.GRAY);
					exploreNum(j);
					tempNum++;
					exploreMap(j);
					// System.out.println("오른" + j+ " temp " + temp[tempNum-1]+" tempNum "+tempNum);
				} else {
					if (tempNum != 0) {
						tempNum -= 1;
						exploreMap(temp[tempNum]);
						// System.out.println("전" + j+ " temp " + temp[tempNum]+" tempNum "+tempNum);
					}
				}
			} else if (j == (buttonNum - 1)) {
				if (j >= mineNum && bt[j - mineNum].getText() == ""
						&& buttonColor.equals(bt[j - mineNum].getBackground().toString())) {
					j -= mineNum;
					bt[j].setBackground(Color.GRAY);
					exploreNum(j);
					tempNum++;
					exploreMap(j);
					// System.out.println("99위" + j+ " temp " + temp[tempNum-1]+" tempNum
					// "+tempNum);
				} else if (j > 0 && (int) (j % mineNum) != 0 && bt[j - 1].getText() == ""
						&& buttonColor.equals(bt[j - 1].getBackground().toString())) {
					j -= 1;
					bt[j].setBackground(Color.GRAY);
					exploreNum(j);
					tempNum++;
					exploreMap(j);
					// System.out.println("99왼" + j+ " temp " + temp[tempNum-1]+" tempNum
					// "+tempNum);
				} else {
					if (tempNum != 0) {
						tempNum -= 1;
						exploreMap(temp[tempNum]);
						// System.out.println("전" + j+ " temp " + temp[tempNum-1]+" tempNum "+tempNum);
					}
				}
			}
		}
	}

	public Game_Screen(String level) {
		this.level = level;
		if (level == "Easy")
			mineNum = 10;
		else if (level == "Normal")
			mineNum = 20;
		else if (level == "Hard")
			mineNum = 30;
		randomNums(mineNum);
		temp = new int[mineNum * mineNum];
		bt = new JButton[mineNum * mineNum];
		setTitle("Game Start!!");
		createMenu();
		if (mineNum == 10)
			setSize(650, 750);
		else if (mineNum == 20)
			setSize(900, 1000);
		else
			setSize(1350, 1400);
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
			if (mineNum == 30) {
				for (int i = 0; i < mineNum * 7; i++) {
					System.out.println(randomNums[i]);
					bt[randomNums[i]].setText("B");
					bt[randomNums[i]].setForeground(Color.LIGHT_GRAY);
				}
			} else
				for (int i = 0; i < mineNum; i++) {
					System.out.println(randomNums[i]);
					bt[randomNums[i]].setText("B");
					bt[randomNums[i]].setForeground(Color.LIGHT_GRAY); // 폭탄 색깔
				}
			for (int i = 0; i < mineNum * mineNum; i++) {
				int buttonNum = mineNum * mineNum;
				int mineCount = 0;
				if (bt[i].getText() != "B") {
					if (i == 0) {
						if (bt[i + 1].getText() == "B")
							mineCount++;
						if (bt[i + mineNum].getText() == "B")
							mineCount++;
						if (bt[i + (mineNum + 1)].getText() == "B")
							mineCount++;
					} else {
						// ---------------------------------------------------------------------------------
						if (i != 0) {
							if (i > mineNum && (int) (i % mineNum) != 0 && bt[i - (mineNum + 1)].getText() == "B")
								mineCount++;
							if (i >= mineNum && bt[i - mineNum].getText() == "B")
								mineCount++;
							if (i >= mineNum && (int) (i % mineNum) != (mineNum - 1)
									&& bt[i - (mineNum - 1)].getText() == "B")
								mineCount++;
							if (i > 0 && (int) (i % mineNum) != 0 && bt[i - 1].getText() == "B")
								mineCount++;
						}
						if (i != (buttonNum - 1)) {
							if (i < buttonNum && (int) (i % mineNum) != (mineNum - 1) && bt[i + 1].getText() == "B")
								mineCount++;
							if (i < (buttonNum - (mineNum + 1)) && i != 0 && (int) (i % mineNum) != 0
									&& bt[i + (mineNum - 1)].getText() == "B")
								mineCount++;
							if (i < (buttonNum - mineNum) && bt[i + mineNum].getText() == "B")
								mineCount++;
							if (i < (buttonNum - (mineNum + 1)) && (int) (i % mineNum) != (mineNum - 1)
									&& bt[i + (mineNum + 1)].getText() == "B")
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
			if (actionBt.getText() == "B") {
				for (int i = 0; i < mineNum * mineNum; i++) {
					if (bt[i].getText() == "B")
						bt[i].setForeground(Color.RED);
				}
				
			} else if (actionBt.getText() == "") {
				for (i = 0; i < mineNum * mineNum; i++) {
					if (e.getSource() == bt[i]) {
						bt[i].setBackground(Color.GRAY);
						tempNum = 0;
						exploreMap(i);
						String testA = bt[i].getBackground().toString();
						System.out.println(testA + "///" + i); // 리턴 테스트
					}
				}
			} else {
				actionBt.setBackground(Color.GRAY);
				if (actionBt.getText().equals("1")) {
					actionBt.setForeground(Color.BLUE);
				} else if (actionBt.getText().equals("2")) {
					actionBt.setForeground(Color.CYAN);
				} else {
					actionBt.setForeground(Color.GREEN);
				}
				System.out.println(actionBt.getText() == "2"); // 깊은 복사, 얕은 복사 비교
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
