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
import javax.swing.*;//���ǣ�������µ��������
/**
 *���ڡ���塢���
 * ����ŵ�����ϣ������ӵ�������
 *
 */
public class CatchFish {
	public static void main(String[] args) throws IOException {
		new Windows();
	}

}

/**
 * ����һ������
 * JFrame:sun�ṩ�Ĵ�����
 * �Լ���������ͨ���ؼ���extends�̳�JFrame����ô�Լ�д
 * ����ͻ���һ�����ڣ������ܹ�ʹ�ø�������Ժͷ���
 * */
class Windows extends JFrame{
	//������-->��ʼ������
	public Windows() throws IOException{
		this.setTitle("���沶�����");//���ô��ڱ���
		this.setSize(700, 500);//���ô��ڴ�С
		//������������
		MyPanel p = new MyPanel();
		this.add(p);
		//���ô��ھ���
		this.setLocationRelativeTo(null);
		//�رմ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);//���ô��ڿɼ�
		p.action();
	}
}

/**
 *�̳���sun�ṩ��JPanel�������Լ�����MyPanel
 *����һ����� 
 *
 */
class MyPanel extends JPanel{
	BufferedImage bg;//��ű���ͼƬ
	int score;
	//Fish fish;
	//Fish fish1;
	Fish[]  fish;
	Net net;
	//������
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
	 * 1���˷����Ǹ���JPanel�е�һ�����Ʒ���������ֻҪ��д��
	 * �������ҵ���߼�����
	 * 2��Graphics��һ���������ͣ�ʹ�ô˻��ʣ������������
	 * ���⻭����
	 */
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.setColor(Color.red);
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("�÷֣�"+score, 40, 30);
		for(int i=1;i<fish.length;i++)
		   g.drawImage(fish[i].fishPic, fish[i].x, fish[i].y, null);
		//g.drawImage(fish1.fishPic, fish1.x, fish1.y, null);
		if(net.show){
			g.drawImage(net.netPic, net.x-net.width/2, net.y-net.height/2, null);
		}
	}
	
	/*ִ��*/
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
	
	//����¼�����
	public void mouseMove(){
		MouseAdapter ma = new MouseAdapter(){
		//�����봰��
			public void mouseEntered(MouseEvent e) {
				net.show = true;
			}
		//����뿪����
			public void mouseExited(MouseEvent e){
				net.show = false;
			}
		//�����
			public void mousePressed(MouseEvent e) {
				catchfish();
			}
		//����ƶ�
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
	//ץ��
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
	BufferedImage fishPic;//�洢��ͼƬ
	BufferedImage[] fishPics;//�洢һ�����һ��ͼƬ
	BufferedImage[] catchFishPics;//ץ�����һ��ͼƬ
	int x;//ͼƬ��x����
	int y;//ͼƬ��y����
	int width;//ͼƬ�Ŀ��
	int height;//ͼƬ�ĸ߶�
	Random r;//�����
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
				//ִ��ץ�����ͼƬ
				
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
	
	//���ζ�
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
 * ����
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
	//�����ƶ�
	public void netMove(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
	/**
	 * 
	 * @param f ��
	 * true��ץ��ɹ�
	 * false��ץ��ʧ��
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


















