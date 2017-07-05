package com.tertis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;






public class Tetris extends JPanel {
	private final static int ROWS = 20;
	private final static int COLS = 10;
	/**格子的大小*/
	public static final int CELL_SIZE = 26;
	/**消灭的行*/
	private int lines;
	/**分数*/
	private int score;
	/**墙*/
	private Cell[][] wall = new Cell[20][10];
	/**正在下落的*/
	private Tetromino tetromino;
	/**下一个*/
	private Tetromino nextOne;
	
	public static Image background;
	public static Image gameOverImg;
	public static Image I;
	public static Image J;
	public static Image O;
	public static Image L;
	public static Image S;
	public static Image Z;
	public static Image T;
	static{
		//class类提供了一个方法 getResource()可以找到
		//package中的文件位置，利用这个位置可以读取图片文件到内存中的对象
		//Tetris.png 文件 与  Tetris.class在同一个包中
		try{
		Class cls = Tetris.class;
		background = ImageIO.read(cls.getResource("tetris.png"));
		gameOverImg = ImageIO.read(cls.getResource("game-over.png"));
		S = ImageIO.read(cls.getResource("S.png"));
		I = ImageIO.read(cls.getResource("I.png"));
		J = ImageIO.read(cls.getResource("J.png"));
		L = ImageIO.read(cls.getResource("L.png"));
		O = ImageIO.read(cls.getResource("O.png"));
		T = ImageIO.read(cls.getResource("T.png"));
		Z = ImageIO.read(cls.getResource("Z.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.drawImage(background, 0, 0,null);
		g.translate(15, 15);
		paintWall(g);
		paintTetromino(g);
		paintNextOne(g);
		paintScore(g);
		g.translate(-15, -15);
		if(gameOver){
			g.drawImage(gameOverImg,0,0,null);
		}
	}
	private void paintWall(Graphics g){
		for(int row = 0;row < wall.length; row++){
			Cell[] line = wall[row];
			for(int col = 0;col < line.length; col++){
			    Cell cell = line[col];
			    int x = col*CELL_SIZE;
			    int y = row*CELL_SIZE;
			    if(cell==null){
					g.setColor(new Color(0));//黑色
					g.drawRect(x,y,CELL_SIZE,CELL_SIZE);
				}else{
					g.drawImage(cell.getImage(), x-1, y-1, null);
				}
			}
		}
	}
	private void paintTetromino(Graphics g){
		if(tetromino == null){
			return;
		}
		Cell[] cells = tetromino.cells;
		for(int i = 0;i<cells.length;i++){
			Cell cell = cells[i];
			int x = cell.getCol()*CELL_SIZE;
			int y = cell.getRow()*CELL_SIZE;
			g.drawImage(cell.getImage(),x-1,y-1,null);
		}
		
	}
	private void paintNextOne(Graphics g){
		if(nextOne==null){
			return;
		}
		Cell[] cells = nextOne.cells;
		for(int i=0;i<cells.length;i++){
			Cell cell = cells[i];
			int x = (cell.getCol()+10)*CELL_SIZE;
			int y = (cell.getRow()+1)*CELL_SIZE;
			g.drawImage(cell.getImage(), x-1, y-1, null);
		}
	}
	public static final int FONT_COLOR = 0x667799;
	public static final int FONT_SIZE = 35;
	private void paintScore(Graphics g){
		int x = 289;
		int y = 170;
		g.setColor(new Color(FONT_COLOR));
		Font font = getFont();//获取系统文字
		font = new Font(font.getName(),Font.BOLD,FONT_SIZE);
		g.setFont(font);
		String str = "SCORE:"+score;
		g.drawString(str, x, y);
		y +=56;
	    str = "LINES:"+lines;
	    g.drawString(str, x, y);
	    y += 56;
	    str = "[P]pause";
	    if(pause){
	    	str = "[C]Countinue";
	    }
	    if(gameOver){
	    	str = "[S]Start";
	    }
	    g.drawString(str, x, y);
	}

	
	
	
	public void action(){
		startAction();
		KeyListener l = new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_Q){
				     System.exit(0);
				}
				if(pause){
					if(key == KeyEvent.VK_C){
						countinueAction();
					}
					return;//返回，不处理后续事件
				}
				if(gameOver){
					if(key == KeyEvent.VK_S){
						startAction();
					}
					return;
				}
				switch(key){
				case KeyEvent.VK_DOWN:
					softDropAction();
					break;
				case KeyEvent.VK_LEFT:
					moveLeftAction();
					break;
				case KeyEvent.VK_RIGHT:
					moveRightAction();
					break;
				case KeyEvent.VK_UP:
					rotateRightAction();
					break;
				case KeyEvent.VK_SPACE:
					hardDropAction();
					break;
				case KeyEvent.VK_P:
					pauseAction();
					break;
				}
				repaint();
			}
		};
		this.addKeyListener(l);//将键盘监听器添加到面板
		this.requestFocus();
		//不懂？没有这句键盘监听器没用
	}
	
	public void countinueAction(){
		pause = false;
		timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				softDropAction();
				repaint();
			}
		},inteval, inteval);
	}
	
	public void pauseAction(){
		timer.cancel();//取消定时器
		pause = true;
		
	}
	public void hardDropAction(){
		while(canDrop()){
			tetromino.softDrop();
		}
		landToWall();
		destoryLines();
		checkOver();
		tetromino = nextOne;
		nextOne = tetromino.randomOne();
	}
	
	public void rotateRightAction(){
		tetromino.rotateRight();
		if(outOfBounds() || coincide()){
		     tetromino.rotateLeft();
		}
	}
	
	public void moveRightAction(){
		tetromino.moveRight();
		if(outOfBounds() || coincide()){
			tetromino.moveLeft();
		}
	}
	
	public void moveLeftAction(){
		tetromino.moveLeft();
		if(outOfBounds() || coincide()){
			tetromino.moveRight();
		}
	}
	private boolean coincide(){
		Cell[] cell = tetromino.cells;
		for(Cell cells:cell){
			int row = cells.getRow();
			int col = cells.getCol();
			if(row >=0 && row < ROWS && col>=0 && col<COLS
					&& wall[row][col] !=null){
				return true;
			}
		}
		return false;
	}
	
	private boolean outOfBounds(){
		Cell[] cell = tetromino.cells;
		for(Cell cells:cell){
			int row = cells.getRow();
			int col = cells.getCol();
			if(row < 0 || row >= ROWS || col < 0 || col>=COLS){
				return true;
			}
		}
		return false;
	}
	private boolean pause;
	private boolean gameOver;
	private Timer timer;
	private int inteval = 800;
	public void startAction(){
		pause = false;
		gameOver = false;
		score = 0;
		lines = 0;
		//清空墙
		for(Cell[] lines:wall ){
			Arrays.fill(lines, null);
		}
		tetromino = Tetromino.randomOne();
		nextOne = Tetromino.randomOne();
		TimerTask task = new TimerTask(){
			public void run(){
				softDropAction();
				repaint();
			}
		};
		timer = new Timer();
		timer.schedule(task, inteval, inteval);
	}
	public void softDropAction(){
		if(canDrop()){
			tetromino.softDrop();
		}else{
			landToWall();
			destoryLines();
			checkOver();
			tetromino = nextOne;
			nextOne = Tetromino.randomOne();
		}
	}
	private void checkOver(){
		if(wall[0][4]!=null){
			gameOver = true;
			timer.cancel();//取消定时器
			repaint();
		}
	}
	private int[] scoreTable = {0,1,20,50,200};
	//                          0 1  2  3  4
	private void destoryLines(){
		int lines = 0;
		for(int row = 0;row<ROWS;row++){
			if(fullCell(row)){
				deleteLine(row);
				lines++;
			}
		}
		this.lines += lines;
		this.score += scoreTable[lines];
	}
	
	private void deleteLine(int row){
    	for(int i = row ; i > 0 ; i--){
    		System.arraycopy(wall[i-1], 0, wall[i],0,COLS);
    	}
    	Arrays.fill(wall[0], null);//将0行的所有格子置为null
    }
    
	private boolean fullCell(int row){
		 Cell[] line = wall[row];
		 for(Cell cell:line){
			 if(cell == null){
				 return false;
			 }
		 }
		return true;
		
	}
	private void landToWall(){
		Cell[] cells = tetromino.cells;
		for(Cell cell:cells){
			int row = cell.getRow();
			int col = cell.getCol();
			wall[row][col] = cell;
		}
	}
	private boolean canDrop(){
		Cell[] cells = tetromino.cells;//4个格子的数组
		for(Cell cell:cells){//遍历每个格子
			int row = cell.getRow();
			if(row == ROWS-1){//下面是否越界
				return false;
			}
		}
		for(Cell cell:cells){
			int row = cell.getRow();
			int col = cell.getCol();
			if(wall[row+1][col] != null){//下面有格子
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("俄罗斯方块");
		Tetris tetris = new Tetris();
		frame.add(tetris);
		frame.setBackground(new Color(0x00ff00));
		frame.setSize(525,550);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		tetris.action();
	}

}
