import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;

public class Start_Screen extends JFrame {
	private JLabel startLabel = new JLabel("START GAME!");
	private GuideDialog guideDialog = new GuideDialog(this, "Game Guide&Info");
	private LevelDialog levelDialog = new LevelDialog(this, "Change Game Level");
	private String level = "Easy";

	private void createMenu() {
		JMenuBar mb = new JMenuBar();

		MenuActionListener menuListener = new MenuActionListener();

		JMenu screenMenuA = new JMenu("Options");
		JMenuItem menuItemA = new JMenuItem("Change Game Level");
		menuItemA.addActionListener(menuListener);
		screenMenuA.add(menuItemA);
		screenMenuA.addSeparator();

		JMenuItem menuItemB = new JMenuItem("End Game");
		menuItemB.addActionListener(menuListener);
		screenMenuA.add(menuItemB);

		JMenu screenMenuB = new JMenu("Oders");
		JMenuItem menuItemC = new JMenuItem("Game Guide&Info");
		menuItemC.addActionListener(menuListener);
		screenMenuB.add(menuItemC);

		JMenuItem menuItemD = new JMenuItem("System Test");
		menuItemD.addActionListener(menuListener);
		screenMenuB.add(menuItemD);

		screenMenuB.addActionListener(menuListener);

		mb.add(screenMenuA);
		mb.add(screenMenuB);
		setJMenuBar(mb);
	}

	public Start_Screen() {
		setTitle("StarBox's MineSweeper Game");
		createMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(550, 300);
		setVisible(true);

		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout());
		startLabel.setHorizontalAlignment(JLabel.CENTER);
		startLabel.setBackground(Color.PINK);
		startLabel.setBorder(new LineBorder(Color.BLACK, 3));
		startLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Game_Screen(level);
			}
		});

		c.add(startLabel, BorderLayout.CENTER);
	}

	class GuideDialog extends JDialog {

		public GuideDialog(JFrame frame, String title) {
			super(frame, title);
			setLayout(new BorderLayout());
			JTabbedPane pane = createTabbedPane();
			add(pane, BorderLayout.CENTER);

			setSize(750, 120);
		}

		private JTabbedPane createTabbedPane() {
			JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
			pane.addTab("How", new JTextArea(
					"game start : Click Game Start button\ngame play : Click one cell, if cell is Mine game is end. The number mean how many Mine in around 8cell. Good Luck"));
			pane.addTab("Who", new JTextArea("Starbox7 make this game.  \ngithub link : https://github.com/Starbox7"));
			pane.addTab("Why", new JTextArea("This game make for java study and java term project"));
			return pane;
		}
	}

	class LevelDialog extends JDialog {
		private JPanel pane = new JPanel();
		LevelActionListener levelListener = new LevelActionListener();

		private LevelDialog(JFrame frame, String title) {
			super(frame, title);
			this.setLayout(new BorderLayout());
			this.setSize(250, 70);

			JButton bt_levelEasy = new JButton("Easy");
			bt_levelEasy.setBorder(new LineBorder(Color.BLACK, 2));
			bt_levelEasy.addActionListener(levelListener);
			// la_levelEasy.setHorizontalAlignment(JLabel.CENTER); //중앙정렬 작동안함
			JButton bt_levelNormal = new JButton("Normal");
			bt_levelNormal.setBorder(new LineBorder(Color.ORANGE, 2));
			bt_levelNormal.addActionListener(levelListener);
			// la_levelNormal.setHorizontalAlignment(JLabel.CENTER);
			JButton bt_levelHard = new JButton("Hard");
			bt_levelHard.setBorder(new LineBorder(Color.RED, 2));
			bt_levelHard.addActionListener(levelListener);
			// la_levelHard.setHorizontalAlignment(JLabel.CENTER);

			pane.setBackground(Color.WHITE);
			pane.add(bt_levelEasy);
			pane.add(bt_levelNormal);
			pane.add(bt_levelHard);

			this.add(pane, BorderLayout.CENTER);
		}
	}

	class LevelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch (cmd) {
			case "Easy":
				level = "Easy";
				startLabel.setBorder(new LineBorder(Color.BLACK, 3));
				levelDialog.setVisible(false);
				break;
			case "Normal":
				level = "Normal";
				startLabel.setBorder(new LineBorder(Color.ORANGE, 3));
				levelDialog.setVisible(false);
				break;
			case "Hard":
				level = "Hard";
				startLabel.setBorder(new LineBorder(Color.RED, 3));
				levelDialog.setVisible(false);
				break;
			}
		}
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch (cmd) {
			case "Change Game Level":
				levelDialog.setVisible(true);
				break;
			case "Game Guide&Info":
				guideDialog.setVisible(true);
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
		new Start_Screen();
	}
}
