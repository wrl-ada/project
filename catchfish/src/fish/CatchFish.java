package fish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;//哥们，把你包下的类借我用
/**
 *窗口、面板、组件
 * 组件放到面板上，面板添加到窗口上
 *
 */
public class CatchFish {
	public static void main(String[] args) throws IOException {
		new Windows();
	}

}

/**
 * 创建一个窗口
 * JFrame:sun提供的窗口类
 * 自己创建的类通过关键字extends继承JFrame，那么自己写
 * 的类就会是一个窗口，子类能够使用父类的属性和方法
 * */
class Windows extends JFrame{
	//构造器-->初始化属性
	public Windows() throws IOException{
		this.setTitle("盗版捕鱼达人");//设置窗口标题
		this.setSize(700, 500);//设置窗口大小
		//窗口中添加面板
		MyPanel p = new MyPanel();
		this.add(p);
		//设置窗口居中
		this.setLocationRelativeTo(null);
		//关闭窗口
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);//设置窗口可见
		p.action();
	}
}

/**
 *继承了sun提供的JPanel面板类后，自己的类MyPanel
 *就是一个面板 
 *
 */
class MyPanel extends JPanel{
	BufferedImage bg;//存放背景图片
	int score;
	//Fish fish;
	//Fish fish1;
	Fish[]  fish;
	Net net;
	//构造器
	public MyPanel() throws IOException{
		bg = ImageIO.read(new File("pic/bg.jpg"));
		score = 100;
		 
		//fish = new Fish("fish05");
		//fish1 = new Fish("fish02");
		fish=new  Fish[] {
                   
     
				new Fish("fish01"),new Fish("fish02"),new Fish("fish03"),
				new Fish("fish06"),new Fish("fish07"),new Fish("fish06"),
				new Fish("fish08"),new Fish("fish08"),new Fish("fish09")
				
                  
		};
		net = new Net();
	}
	/**
	 * 1、此方法是父类JPanel中的一个绘制方法，子类只要重写此
	 * 方法里的业务逻辑即可
	 * 2、Graphics是一个画笔类型，使用此画笔，可以在面板上
	 * 随意画内容
	 */
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.setColor(Color.red);
		g.setFont(new Font("楷体", Font.BOLD, 30));
		g.drawString("得分："+score, 40, 30);
		for(int i=1;i<fish.length;i++)
		   g.drawImage(fish[i].fishPic, fish[i].x, fish[i].y, null);
		//g.drawImage(fish1.fishPic, fish1.x, fish1.y, null);
		if(net.show){
			g.drawImage(net.netPic, net.x-net.width/2, net.y-net.height/2, null);
		}
	}
	
	/*执行*/
	public void action(){
		mouseMove();
		for(int i=0;i<fish.length;i++){
			fish[i].start();
			//fish[i].start();
		}

	
		while(true){
			//fish.move();
			//fish1.move();
			repaint();
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
		}
	}
	
	//鼠标事件监听
	public void mouseMove(){
		MouseAdapter ma = new MouseAdapter(){
		//鼠标进入窗口
			public void mouseEntered(MouseEvent e) {
				net.show = true;
			}
		//鼠标离开窗口
			public void mouseExited(MouseEvent e){
				net.show = false;
			}
		//鼠标点击
			public void mousePressed(MouseEvent e) {
				catchfish();
			}
		//鼠标移动
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				net.netMove(x,y);
//				System.out.println(x+" "+y);
			}
		};
		this.addMouseListener(ma);
		this.addMouseMotionListener(ma);
	}
	//抓鱼
	public void catchfish(){
	for(int i=1;i<fish.length;i++){
		if(net.catchFish(fish[i])){
			score += fish[i].width/4;
			fish[i].isCatchFish=true;
			//fish.getOut();
			
		}
	}
	
}  





class Fish extends Thread{
	BufferedImage fishPic;//存储鱼图片
	BufferedImage[] fishPics;//存储一条鱼的一组图片
	BufferedImage[] catchFishPics;//抓到鱼的一组图片
	int x;//图片的x坐标
	int y;//图片的y坐标
	int width;//图片的宽度
	int height;//图片的高度
	Random r;//随机数
	int step;
	int index = 0;
	Boolean isCatchFish;
	
	public Fish(String name) throws IOException{
		isCatchFish=false;
		catchFishPics=new BufferedImage[2];
		fishPics = new BufferedImage[10];
		for (int i = 0; i < fishPics.length; i++) {
			fishPics[i] = 
				ImageIO.read(new File("pic/"+name+"_"+(i+1)+".png"));
			
		}
		for(int i=0;i<catchFishPics.length;i++){
			catchFishPics[i]=ImageIO.read(new File("pic/"+name+"_catch_"+(i+1)+".png"));
		}                                                             
		r = new Random();
		fishPic = fishPics[0];
		width = fishPic.getWidth();
		height = fishPic.getHeight();
		x = r.nextInt(700) - width;
		y = r.nextInt(500) - height;
		step = r.nextInt(5) + 1;
	}
	public void run(){
		while(true){
			if(isCatchFish){
				//执行抓到鱼的图片
				
				for(int i=0;i<2;i++){
					fishPic=catchFishPics[0];
					try {
					Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fishPic=catchFishPics[1];
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				getOut();
				isCatchFish=false;
		}else{
			fishMove();
			//getOut();
			}
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
	
	//鱼游动
	public void fishMove(){
		fishPic = fishPics[index++%fishPics.length];
		x = x -step;
		if(x <= -width){
			getOut();
		}
	}
	
	public void getOut(){
		x = 700;
		y = r.nextInt(500) - height;
		step = r.nextInt(5) + 1;
	}

	
	
}

/**
 * 鱼网
 */
class Net{
	BufferedImage netPic;
	int x;
	int y;
	int width;
	int height;
	boolean show;
	public Net() throws IOException{
		netPic = ImageIO.read(new File("pic/net09.png"));
		width = netPic.getWidth();
		height = netPic.getHeight();
		x = 0;
		y = 0;
		show = true;
	}
	//鱼网移动
	public void netMove(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
	/**
	 * 
	 * @param f 鱼
	 * true：抓鱼成功
	 * false：抓鱼失败
	 */
	public boolean catchFish(Fish f){
		int dx = x - f.x;
		int dy = y - f.y;
		if(dx >= 0 && dx < f.width &&
				dy >= 0 && dy < f.height){
			return true;
		}
		return false;
	}
}
}


















