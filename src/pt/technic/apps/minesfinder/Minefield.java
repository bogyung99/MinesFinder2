package pt.technic.apps.minesfinder;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Massadas
 */
public class Minefield {

    public static final int EMPTY = 0;
    // from 1 to 8 is the number of mines around
    public static final int COVERED = 9;
    public static final int QUESTION = 10;
    public static final int MARKED = 11;
    public static final int BUSTED = 12;

    public static boolean[][] mines;
    public static int[][] states;
    private static int width;
    private static int height;
    private int numMines;
    private Random random;
    private int one = 0;
    public static int isBUSTED = 0;

    private boolean firstPlay;
    public boolean playerDefeated;
    public boolean gameFinished;
    
    private long timeGameStarted;
    private long timeGameDuration;

    public Minefield(int width, int height, int numMines) {
        if(numMines<=0){
            throw new IllegalArgumentException("Mines nuumber must be bigger than 0");
        }
        
        this.width = width;
        this.height = height;
        this.numMines = numMines;
        mines = new boolean[width][height];
        states = new int[width][height];

        random = new Random();

        firstPlay = true;
        playerDefeated = false;
        gameFinished = false;
        
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                states[x][y] = COVERED;
            }
        }
    }

    public void revealGrid(int x, int y) {
        if (states[x][y] == COVERED && !gameFinished) {
        	GameWindow.isBomb = 1;
            if (firstPlay) {
                firstPlay = false;
                placeMines(x, y);
                timeGameStarted=System.currentTimeMillis();
            }

            if (mines[x][y]) {
                states[x][y] = BUSTED;
                GameWindow.isBomb = 2;
                isBUSTED += 1;
                if(one == 0) {
                	Dismantling dismantling = new Dismantling();
            		
                	if(Dismantling.isGameWin == 0) {
                		playerDefeated = false;
                		gameFinished = false;
                	}
                	else if(Dismantling.isGameWin == 1){
                		playerDefeated = true;            
                		gameFinished = true;
                	}             
                	one++;
                }
                else {
                	int ans=JOptionPane.showConfirmDialog(null,"지뢰를 밟으셨습니다. 게임을 종료하시겠습니까?", "Lost!", JOptionPane.YES_NO_OPTION);
        			
                	if(ans==0) {
                		playerDefeated = true;            
                		gameFinished = true;
                	}
                	else {
                		isPlayerContinue();
                	}
               
                }
                timeGameDuration=System.currentTimeMillis()-timeGameStarted;
                return;
            }

            int minesAround = countMinesAround(x, y);
            states[x][y] = minesAround;

            if (minesAround == 0) {
                revealGridNeighbors(x, y);
            }
            
            if(checkVictory()) {
                gameFinished=true;
                playerDefeated=false;
                timeGameDuration=System.currentTimeMillis()-timeGameStarted;
                return;
            }
        }
    }
    
    public static long getGameDuration(){
    	int a = TimerThread.min;
    	int b = TimerThread.sec;
    	int c = (a * 60) + b;
        
        return c;
    }

    //public void
    public void revealGridNeighbors(int x, int y) {
        for (int col = Math.max(0, x - 1); col < Math.min(width, x + 2); col++) {
            for (int line = Math.max(0, y - 1); line < Math.min(height, y + 2); line++) {
                revealGrid(col, line);
            }
        }
    }

    public void setMineMarked(int x, int y) {
        if (states[x][y] == COVERED || states[x][y] == QUESTION) {
            states[x][y] = MARKED;
        }
    }

    public void setMineQuestion(int x, int y) {
        if (states[x][y] == COVERED || states[x][y] == MARKED) {
            states[x][y] = QUESTION;
        }
    }

    public void setMineCovered(int x, int y) {
        if (states[x][y] == MARKED || states[x][y] == QUESTION) {
            states[x][y] = COVERED;
        }
    }

    public static boolean checkVictory() {
        boolean victory = true;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!mines[x][y]) {
                    victory = victory && states[x][y] >= 0 && states[x][y] < 9;
                }
            }
        }
        return victory;
    }

    private int countMinesAround(int x, int y) {
        int result = 0;
        for (int col = Math.max(0, x - 1); col < Math.min(width, x + 2); col++) {
            for (int line = Math.max(0, y - 1); line < Math.min(height, y + 2); line++) {
                if (mines[col][line]) {
                    result++;
                }
            }
        }
        return result - (mines[x][y] ? 1 : 0);
    }
    
    public int countMARKEDAround(int x, int y) { // !로 마크된 수 찾기
        int result = 0;
        for (int col = Math.max(0, x - 1); col < Math.min(width, x + 2); col++) {
            for (int line = Math.max(0, y - 1); line < Math.min(height, y + 2); line++) {
                if (states[col][line] == MARKED || states[col][line] == BUSTED) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean isPlayerDefeated() {
        return playerDefeated;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
    
    public void isPlayerContinue() {
    	playerDefeated=false;
    	gameFinished=false;
    }

    private void placeMines(int plX, int plY) {
        for (int i = 0; i < numMines; i++) {
            int x = 0;
            int y = 0;
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (mines[x][y] || (x == plX && y == plY));
            mines[x][y] = true;
        }
    }

    public int getGridState(int x, int y) {
        return states[x][y];
    }

    public boolean hasMine(int x, int y) {
        return mines[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumMines() {
        return numMines;
    }

}
