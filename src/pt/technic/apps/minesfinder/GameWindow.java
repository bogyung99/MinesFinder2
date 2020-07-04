package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer; // 시간 ui

/**
 *
 * @author Gabriel Massadas
 */

public class GameWindow extends javax.swing.JFrame {

	// private javax.swing.JPanel GameBox;
	private ButtonMinefield[][] buttons;
	private Minefield minefield;
	private RecordTable record;
	private JPanel GameBox1 = new JPanel();
	private JPanel GameBox2 = new JPanel();
	private JPanel contain = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JButton Smile = new JButton();
	private TimerTest timerTest = new TimerTest();
	private boolean flag = false;
	private TimerThread time = new TimerThread(timerTest.secondLabel, timerTest.timerLabel);
	static int isBomb;
	public String name;
	private int scoreCur;
	private int onetime = 0;

	/**
	 * Creates new form GameWindow
	 */
	public GameWindow() {
		initComponents();
	}

	public GameWindow(Minefield minefield, RecordTable record) {
		initComponents();

		this.minefield = minefield;
		this.record = record;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		javax.swing.JLabel GameTitle; // 맨 위 라벨
		javax.swing.JLabel Time;

		getContentPane().add(mainPanel);
		GameBox1.setLayout(new GridLayout(1, 0));
		GameBox2.setLayout(new GridLayout(0, 3));
		GameBox2.setOpaque(false);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(GameBox1, "North");
		mainPanel.add(GameBox2, "Center"); // 프레임에 넣기
		mainPanel.add(contain, "South");
		GameTitle = new javax.swing.JLabel();
		Time = new javax.swing.JLabel();

		GameTitle.setBackground(new java.awt.Color(136, 135, 217));
		GameTitle.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
		GameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		GameTitle.setText("Find Mines!"); // 게임 상단 이름
		GameTitle.setOpaque(true);

		Time.setBackground(new java.awt.Color(90, 135, 217));// 136
		Time.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
		Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		if (MinesFinder.level == 1)
			Time.setText("Time Limit : 2 minite!"); // 게임 상단 이름
		else if (MinesFinder.level == 2)
			Time.setText("Time Limit : 4 minite!"); // 게임 상단 이름
		else if (MinesFinder.level == 3)
			Time.setText("Time Limit : 6 minite!"); // 게임 상단 이름

		Time.setOpaque(true);

		timerTest.setBackground(new java.awt.Color(95, 135, 217));

		Smile.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/웃음.png")));

		GameBox1.add(GameTitle);
		GameBox2.add(Time);
		GameBox2.add(Smile);
		GameBox2.add(timerTest);

		contain.setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));

		Smile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JButton Smile = (JButton) evt.getSource();

				RandomOpen();
				updateButtonsStates();
			}
		});

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				start();

				ButtonMinefield button = (ButtonMinefield) e.getSource();
				int x = button.getCol();
				int y = button.getLine();
				minefield.revealGrid(x, y);

				face();

				updateButtonsStates();

				if (minefield.isGameFinished()) {
					stop();
					if (minefield.isPlayerDefeated()) {

						Sound bomb = new Sound();
						try {
							bomb.bombPlay();

						} catch (Exception ex) {
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Congratulations. You managed to discover all the mines in "
										+ (minefield.getGameDuration()) + " seconds", // /1000
								"victory", JOptionPane.INFORMATION_MESSAGE);

						if (record != null) {
							long a = minefield.getGameDuration();
							long b = record.getScore();

							name = JOptionPane.showInputDialog("Enter your name");

							scoreCur = (int) minefield.getGameDuration();

							saveRankO();

							if (name != "") {
								record.setRecord(name, minefield.getGameDuration(), MinesFinder.tr);
							}
						}

					}
					setVisible(false);
				}
			}
		};

		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					ButtonMinefield botao = (ButtonMinefield) e.getSource();
					int x = botao.getCol();
					int y = botao.getLine();
					if (minefield.getGridState(x, y) == minefield.COVERED) {
						minefield.setMineMarked(x, y);
					} else if (minefield.getGridState(x, y) == minefield.MARKED) {
						minefield.setMineQuestion(x, y);
					} else if (minefield.getGridState(x, y) == minefield.QUESTION) {
						minefield.setMineCovered(x, y);
					}
					updateButtonsStates();
				}

				if (SwingUtilities.isLeftMouseButton(e) && SwingUtilities.isRightMouseButton(e)) {

					ButtonMinefield botao = (ButtonMinefield) e.getSource();
					int x = botao.getCol();
					int y = botao.getLine();

					if (minefield.getGridState(x, y) == minefield.countMARKEDAround(x, y)) {
						minefield.revealGridNeighbors(x, y);
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent me) {
			}

			@Override
			public void mouseReleased(MouseEvent me) {
			}

			@Override
			public void mouseEntered(MouseEvent me) {
			}

			@Override
			public void mouseExited(MouseEvent me) {
			}
		};

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				ButtonMinefield botao = (ButtonMinefield) e.getSource();
				int x = botao.getWidth();
				int y = botao.getHeight();
				if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
					buttons[x][y - 1].requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
					buttons[x - 1][y].requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN && y < minefield.getHeight() - 1) {
					buttons[x][y + 1].requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < minefield.getWidth() - 1) {
					buttons[x + 1][y].requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_M) {
					if (minefield.getGridState(x, y) == minefield.COVERED) {
						minefield.setMineMarked(x, y);
					} else if (minefield.getGridState(x, y) == minefield.MARKED) {
						minefield.setMineQuestion(x, y);
					} else if (minefield.getGridState(x, y) == minefield.QUESTION) {
						minefield.setMineCovered(x, y);
					}
					updateButtonsStates();
				}
			}

			@Override
			public void keyTyped(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		};

		// Create buttons for the player
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y] = new ButtonMinefield(x, y);
				buttons[x][y].addActionListener(action);
				buttons[x][y].addMouseListener(mouseListener);
				buttons[x][y].addKeyListener(keyListener);
				buttons[x][y].setPreferredSize(new Dimension(55, 55));
				buttons[x][y].setFont(new Font("Serif", Font.BOLD, 25));

				contain.add(buttons[x][y]);
			}
		}
		pack();

	}

	public void start() {
		if (flag == false) {

			time.start();
		}
		flag = true;
	}

	public void stop() {
		time.stop();
	}

	private void updateButtonsStates() {
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y].setEstado(minefield.getGridState(x, y));
			}
		}
	}

	private void RandomOpen() {
		if (onetime == 0) {
			int n1 = 0;
			int n2 = 0;

			if (MinesFinder.level == 1) {
				n1 = 9;
				n2 = 9;
			} else if (MinesFinder.level == 2) {
				n1 = 16;
				n2 = 16;
			} else if (MinesFinder.level == 3) {
				n1 = 16;
				n2 = 30;
			}

			int x = 0;
			int y = 0;
			x = (int) (Math.random() * n1);
			y = (int) (Math.random() * n2);

			for (; !(Minefield.states[x][y] == 9 && Minefield.mines[x][y] != true);) {

				x = (int) (Math.random() * n1);
				y = (int) (Math.random() * n2);

				System.out.println(x);
				System.out.println(y);
			}

			minefield.revealGrid(x, y);
			onetime++;
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Game");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(contain);

		contain.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1094, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 553, Short.MAX_VALUE));

		pack(); // 적절한 크기 맞춰주기
	}// </editor-fold>//GEN-END:initComponents

	public void saveRankO() {

		SaveRankI save = sortRank();

		try {
			File f = new File("saveRank.txt");
			BufferedWriter b = new BufferedWriter(new FileWriter(f));

			for (int i = 0; i < 3; i++) {
				b.write(save.EasyL.get(i).toString());
				b.write(" ");
				b.write(save.EasyN.get(i).toString());
				b.newLine();
			}
			b.write("--");
			b.newLine();
			for (int i = 0; i < 3; i++) {
				b.write(save.MediumL.get(i).toString());
				b.write(" ");
				b.write(save.MediumN.get(i).toString());
				b.newLine();
			}
			b.write("--");
			b.newLine();
			for (int i = 0; i < 3; i++) {
				b.write(save.HardL.get(i).toString());
				b.write(" ");
				b.write(save.HardN.get(i).toString());
				b.newLine();
			}

			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SaveRankI sortRank() {

		SaveRankI save = new SaveRankI();

		if (MinesFinder.level == 1) {
			if (save.EasyN.get(0).equals(name) || save.EasyN.get(0).equals(name + "-" + 1)
					|| save.EasyN.get(0).equals(name + "-" + 2))
				MinesFinder.tr++;
			else
				MinesFinder.tr = 1;

			int n1 = 0;
			int n2 = 0;
			int n3 = 0;
			int num = 0;

			for (int i = 0; i < 3; i++) {
				if (save.EasyN.get(i).equals(name)) {
					n1 = 1;
					num++;
				} else if (save.EasyN.get(i).equals(name + "-" + 1)) {
					n2 = 1;
					num++;
				} else if (save.EasyN.get(i).equals(name + "-" + 2)) {
					n3 = 1;
					num++;
				}
			}

			sortName(num, n1, n2, n3);

			save.EasyN.add(name);
			save.EasyL.add(scoreCur);

			for (int i = 0; i < 4; i++) {
				for (int j = i; j < 4; j++) {
					if (save.EasyL.get(i) > save.EasyL.get(j)) {
						int tmp = save.EasyL.get(i);
						String tmpStr = save.EasyN.get(i);
						save.EasyL.add(i, save.EasyL.get(j));
						save.EasyN.add(i, save.EasyN.get(j));
						save.EasyL.add(j, tmp);
						save.EasyN.add(j, tmpStr);
					}
				}
			}

		}
		if (MinesFinder.level == 2) {
			if (save.MediumN.get(0).equals(name) || save.MediumN.get(0).equals(name + "-" + 1)
					|| save.MediumN.get(0).equals(name + "-" + 2) || save.MediumN.get(0).equals(name + "-" + 3))
				MinesFinder.tr++;
			else
				MinesFinder.tr = 1;

			int n1 = 0;
			int n2 = 0;
			int n3 = 0;
			int num = 0;

			for (int i = 0; i < 3; i++) {
				if (save.EasyN.get(i).equals(name)) {
					n1 = 1;
					num++;
				} else if (save.EasyN.get(i).equals(name + "-" + 1)) {
					n2 = 1;
					num++;
				} else if (save.EasyN.get(i).equals(name + "-" + 2)) {
					n3 = 1;
					num++;
				}
			}

			sortName(num, n1, n2, n3);

			save.MediumL.add(scoreCur);
			save.MediumN.add(name);
			for (int i = 0; i < 4; i++) {
				for (int j = i; j < 4; j++) {
					if (save.MediumL.get(i) > save.MediumL.get(j)) {
						int tmp = save.MediumL.get(i);
						String tmpStr = save.MediumN.get(i);
						save.MediumL.add(i, save.MediumL.get(j));
						save.MediumN.add(i, save.MediumN.get(j));
						save.MediumL.add(j, tmp);
						save.MediumN.add(j, tmpStr);
					}
				}

			}
		}
		if (MinesFinder.level == 3) {
			if (save.HardN.get(0).equals(name) || save.HardN.get(0).equals(name + "-" + 1)
					|| save.HardN.get(0).equals(name + "-" + 2) || save.HardN.get(0).equals(name + "-" + 3))
				MinesFinder.tr++;
			else
				MinesFinder.tr = 1;

			int n1 = 0;
			int n2 = 0;
			int n3 = 0;
			int num = 0;

			for (int i = 0; i < 3; i++) {
				if (save.EasyN.get(i).equals(name)) {
					n1 = 1;
					num++;
				} else if (save.EasyN.get(i).equals(name + "-" + 1)) {
					n2 = 1;
					num++;
				} else if (save.EasyN.get(i).equals(name + "-" + 2)) {
					n3 = 1;
					num++;
				}
			}

			sortName(num, n1, n2, n3);

			save.HardL.add(scoreCur);
			save.HardN.add(name);
			for (int i = 0; i < 4; i++) {
				for (int j = i; j < 4; j++) {
					if (save.HardL.get(i) > save.HardL.get(j)) {
						int tmp = save.HardL.get(i);
						String tmpStr = save.HardN.get(i);
						save.HardL.add(i, save.HardL.get(j));
						save.HardN.add(i, save.HardN.get(j));
						save.HardL.add(j, tmp);
						save.HardN.add(j, tmpStr);
					}
				}

			}
		}

		return save;

	}

	public void face() {
		if (isBomb == 1)
			Smile.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/웃음.png")));
		else if (isBomb == 2) {
			Smile.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/으악.png")));
			isBomb = 1;
		}
	}

	public void sortName(int num, int n1, int n2, int n3) {
		if (num != 0) {
			if (num == 1) {
				if (n1 == 1)
					name += "-" + 1;
			}
			if (num == 2) {
				if (n1 == 1 && n2 == 1)
					name += "-" + 2;
				else if (n1 == 1 && n2 == 1)
					name += "-" + 1;
			}
			if (num == 3) {
				if (n3 == 0)
					name += "-" + 2;
				else if (n2 == 0)
					name += "-" + 1;
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
}
