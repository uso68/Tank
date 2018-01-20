package Demo_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;


//����һ����ͼ����
public class MyPanel extends JPanel implements KeyListener,Runnable{
		
	Player player = null;
	//������˼���
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	Vector<Node> nodes = new Vector<Node>();
	//����ը������
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	//��������
	int etsSize = 5;
	EnemyTank a = null;
	//�������ֱ�ըͼƬ,����ͼƬ��˳����ƣ����ܻ�����ըЧ��
	Image im_small = null;
	Image im_middle = null;
	Image im_big = null;
	
	public MyPanel(String Flag)//����Flag�����ж��ǿ�ʼ����Ϸ���Ƕ�ȡ�浵
	{
		//�ָ��ϴ�ս����¼
		Recorder.getRecorder();
		player = new Player(100, 100);
		
		
		if(Flag.equals("newGame"))
		{
			//��ʼ������
			for (int i = 0; i < etsSize; i++) 
			{
				//����һ������
				EnemyTank et = new EnemyTank((i+1)*50,0);
				et.setColor(1);
				et.setDirection(1);
				//�õ���̹���ܹ���ȡ�����б����������ĵ���̹��
				et.getEts(ets);
				Thread t1 = new Thread(et);
				t1.start();
				//���������һ���ڵ�
				Ammo ammo = new Ammo(et.x+10, et.y+30, 2);
				//���ڵ����뵽�����ڵ�����
				et.ammos.add(ammo);
				//���ڵ��ŵ����̶�����
				Thread t2 = new Thread(ammo);
				t2.start();
				//�ѵ���̹�˼������̹�˼���
				ets.add(et);
			}
		}else {
			try {
				nodes = new Recorder().getNodesAndEneNum();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < nodes.size(); i++) 
			{
				Node node = nodes.get(i);
				//����һ������
				EnemyTank et = new EnemyTank(node.x,node.y);
				et.setColor(1);
				et.setDirection(node.direction);
				//�õ���̹���ܹ���ȡ�����б����������ĵ���̹��
				et.getEts(ets);
				Thread t1 = new Thread(et);
				t1.start();
				//���������һ���ڵ�
				Ammo ammo = new Ammo(et.x+10, et.y+30, 2);
				//���ڵ����뵽�����ڵ�����
				et.ammos.add(ammo);
				//���ڵ��ŵ����̶�����
				Thread t2 = new Thread(ammo);
				t2.start();
				//�ѵ���̹�˼������̹�˼���
				ets.add(et);
			}
			
		}
		
		//��ʼ����ըͼƬ
		im_small = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/small.png"));
		im_middle = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/middle.png"));
		im_big = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/big.png"));
	}
	
	//����JPanel��paint()������Graphics�ǻ�ͼ����Ҫ�࣬�������Ϊһֻ����
	public void paint(Graphics g) 
	{
		//���ø��෽����ɳ�ʼ������һ�䲻����
		super.paint(g);
		Bomb bomb1 = new Bomb(10, 10);
		g.drawImage(im_small, bomb1.x, bomb1.y, 30, 30, this);
		g.drawImage(im_middle, bomb1.x, bomb1.y, 30, 30, this);
		g.drawImage(im_big, bomb1.x, bomb1.y, 30, 30, this);
		//����ͼ����
		g.fillRect(0, 0, 400, 300);
		//������ʾ��Ϣ
		this.showInfo(g);
		
		//�����һ����ţ��������̹��
		if(player.islive)
		{
			this.drawTank(player.getX(), player.getY(), this.player.direction, g, 0);
		}
		
		//��������ӵ�
		for (int i = 0; i < player.Ammos.size(); i++) 
		{
			
			Ammo MyAmmo = player.Ammos.get(i);
			//�ж��ӵ�״̬����Ϊ�ջ��߻��ţ�������
			if(MyAmmo != null && MyAmmo.islive == true) 
			{
				//�����Լ����ڵ�
				g.draw3DRect(MyAmmo.getX(), MyAmmo.getY(), 2, 2, false);
			}
			//�ж��ӵ�״̬�������ʹӼ������Ƴ�
			if(MyAmmo.islive == false) 
			{
				player.Ammos.remove(MyAmmo);
			}
		}
		//����ը��Ч��		
		for (int i = 0; i < bombs.size(); i++)
		{
			//����ը��
			Bomb bomb = bombs.get(i);
			//�ж�ը������ֵ����ͬ����ֵ��ʾ��ͬ��ըЧ��ͼ
			if(bomb.islive) 
			{
				switch(bomb.life) 
				{
				case 9:
				case 8:
				case 7:
					g.drawImage(im_small, bomb.x, bomb.y, 30, 30, this);
					break;
				case 6:
				case 5:
				case 4:
					g.drawImage(im_middle, bomb.x, bomb.y, 30, 30, this);
					break;
				case 3:
				case 2:
				case 1:
					g.drawImage(im_big, bomb.x, bomb.y, 30, 30, this);
					break;
				
				}
			}
			//��һ�ξ���ը��������ֵ����
			bomb.lifeDown();
			//���ը������ֵΪ0����Ӽ������Ƴ�ը��
			if(bomb.life==0) 
			{
				bombs.remove(bomb);
			}
				
		}
	
		//��������,ͬʱ�ѵ��˵��ڵ�������		
		for (int i = 0; i < ets.size(); i++) 
		{
			EnemyTank et = ets.get(i);
			if(et.islive) 
			{
				this.drawTank(et.getX(), et.getY(), et.getDirection(), g, et.getColor());
				//���������ӵ�
				for (int j = 0; j < et.ammos.size(); j++) 
				{
					//ȡ�������ӵ�
					Ammo enemyAmmo = et.ammos.get(j);
					if(enemyAmmo.islive)
					{
						//�������˵��ڵ�
						g.draw3DRect(enemyAmmo.x, enemyAmmo.y, 2, 2, false);
					}else {
						//���������������ô�ͽ����˵��ڵ��Ӽ������Ƴ�
						et.ammos.remove(enemyAmmo);
					}
				}
			}
			
		}
	}
	//������ʾ��Ϣ
	public void showInfo(Graphics g) {
		//����һ��̹��ͼ��,�����μ�ս��
		this.drawTank(80, 330, 0, g, 1);
		//�����ʻ�����������
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getEnNum()+"", 110, 350);
		//����һ�����̹��ͼ��,�����μ�ս��
		this.drawTank(140, 330, 0, g, 0);
		//�����ʻ����������
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getMyLife()+"", 170, 350);
		//��������ܳɼ�
		g.setColor(Color.BLACK);
		Font ft = new Font("����",Font.BOLD,20);
		g.setFont(ft);
		g.drawString("��ǰս��", 420, 30);
		this.drawTank(420, 60, 0, g, 1);
		g.setColor(Color.black);;
		g.drawString(""+Recorder.getKillCount(), 460, 80);
	}
	//�жϵ����ӵ��Ƿ������ҵĺ���
	public void hitPlayer()
	{
		for (int i = 0; i < ets.size(); i++) {
			//ÿһ������̹��
			EnemyTank et = ets.get(i);
			
			for (int j = 0; j < et.ammos.size(); j++) {
				//��ȡ��ÿһ���ӵ�����
				Ammo enemyAmmo = et.ammos.get(j);
				//player״̬Ϊ����ܱ�����
				if(player.islive)
				{
					this.hitTank(enemyAmmo, player);
				}
				
			}
		}
	}
	
	
	
	//�ж�����ӵ��Ƿ���е���
	public void hitEnemyTank()
	{
		//�ж��Ƿ���У����к���Ҫ��ʱ�жϣ����Էŵ��߳�������
		//ѭ���ж�ÿһ���ӵ��Ƿ��ÿһ��̹������
		for (int i = 0; i < player.Ammos.size(); i++) {
			//ȡ��ÿһ���ӵ�
			Ammo MyAmmo = player.Ammos.get(i);
			//�ж��ӵ������ֻ�л���ӵ����Ի��е���
			if(MyAmmo.islive) {
				//ȡ��ÿһ��̹�����ӵ������ж�
				for (int j = 0; j < ets.size(); j++) {
					//ȡ��ÿһ��̹��
					EnemyTank et = ets.get(j);
					//�ж�̹�������Ĳ��ܱ�����
					if(et.islive) {
						
						this.hitTank(MyAmmo, et);
					}
					
					
				}
			}
		}
	}	
	//ר���ж��ӵ��Ƿ���е���̹�˵ĺ���������һ����ը����
	public void hitTank(Ammo am,Tank et) 
	{
		switch(et.direction) 
		{
		//������˵ķ������ϻ�������
		case 0:			
		case 1:
			//�ж��ӵ��Ƿ������еķ�Χ()
			if(am.x>et.x&&am.x<et.x+20&&am.y>et.y&&am.y<et.y+30) 
			{
				//���к��ӵ���������������
				am.islive = false;
				et.islive = false;
				//�ж�������е��ǵ���
				if(et instanceof EnemyTank)
				{
					//���ٵ�������
					Recorder.reduceEnNum();
					//���ӻ�ɱ����
					Recorder.killCount();
				}	
				//������Լ�
				if(et instanceof Player)
				{
					Recorder.reduceMyLife();
				}
				//����һ��ը����������ը������
				Bomb bomb = new Bomb(et.x, et.y);
				bombs.add(bomb);
			}
			break;
		//������˵ķ��������������
		case 2:
		case 3:			
			//�ж��ӵ��Ƿ������еķ�Χ
			if(am.x>et.x&&am.x<et.x+20&&am.y>et.y&&am.y<et.y+20) {
				//���к��ӵ���������������
				am.islive = false;
				et.islive = false;
				//�ж�������е��ǵ���
				if(et instanceof EnemyTank)
				{
					//���ٵ�������
					Recorder.reduceEnNum();
					//���ӻ�ɱ����
					Recorder.killCount();
				}
				//������Լ�
				if(et instanceof Player)
				{
					Recorder.reduceMyLife();
				}
				//����һ��ը����������ը������
				Bomb bomb = new Bomb(et.x, et.y);
				bombs.add(bomb);
			}			
			break;
		}
	}
	//��ȡ��̹�˴��룬��װ��������
	public void drawTank(int x,int y,int direction,Graphics g,int type) {
//		x = player.getX();
//		y = player.getY();
		//�ж����ǻ��ǵ���
		switch(type) 
		{
		//�����ɫ
		case 0:
			g.setColor(Color.ORANGE);
			break;
		//������ɫ
		case 1:
			g.setColor(Color.GREEN);
			break;
		}
		//�ж��ڹܷ���
		switch(direction) {
		////̹���ڿ�����
		case 0:
			TankUp(x, y, g);
			break;
		//̹���ڿ�����	
		case 1:			
			TankDown(x, y, g);
			break;			
		//̹���ڿ�����	
		case 2:			
			TankLeft(x, y, g);
			break;			
		//̹���ڿ�����	
		case 3:
			TankRight(x, y, g);
			break;			
		}
	}
	//̹����̬����
	public void TankRight(int x, int y, Graphics g) {
		//�����ϰ��
		g.fill3DRect(x, y, 30, 5, false);
		//�����°��
		g.fill3DRect(x, y+15, 30, 5, false);
		//���м䳵��
		g.fill3DRect(x+5, y+5,20, 10, false);
		//������
		g.fillOval(x+10, y+5, 10, 10);
		//���ڹ�
		g.drawLine(x+15, y+10, x+30, y+10);
	}
	//̹����̬����
	public void TankLeft(int x, int y, Graphics g) {
		//�����ϰ��
		g.fill3DRect(x, y, 30, 5, false);
		//�����°��
		g.fill3DRect(x, y+15, 30, 5, false);
		//���м䳵��
		g.fill3DRect(x+5, y+5,20, 10, false);
		//������
		g.fillOval(x+10, y+5, 10, 10);
		//���ڹ�
		g.drawLine(x+15, y+10, x, y+10);
	}
	//̹����̬����
	public void TankDown(int x, int y, Graphics g) {
		//�����
		g.fill3DRect(x, y, 5, 30, false);
		//���ұ�
		g.fill3DRect(x+15, y, 5, 30, false);
		//���м䳵��
		g.fill3DRect(x+5, y+5,10, 20, false);
		//������
		g.fillOval(x+5, y+10, 10, 10);
		//���ڹ�
		g.drawLine(x+10, y+15, x+10, y+30);
	}
	//̹����̬����
	public void TankUp(int x, int y, Graphics g) {
		//��̹��			
		//�����			
		g.fill3DRect(x, y, 5, 30, false);
		//���ұ�
		g.fill3DRect(x+15, y, 5, 30, false);
		//���м䳵��
		g.fill3DRect(x+5, y+5,10, 20, false);
		//������
		g.fillOval(x+5, y+10, 10, 10);
		//���ڹ�
		g.drawLine(x+10, y+15, x+10, y);
	}
	
	//���̿��Ʒ���
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			//���÷���
			this.player.setDirection(1);
			this.player.moveDown();
			//��ȡ��ǰY���꣬��ʹ�ñ����洢
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
			//ԭ��ͬ��
			this.player.setDirection(0);
			this.player.moveUp();
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			this.player.setDirection(2);
			this.player.moveLeft();
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
			this.player.setDirection(3);
			this.player.moveRight();
			
			
		}
		//���¿����J 
		if(e.getKeyCode()==KeyEvent.VK_J) {
			if(this.player.Ammos.size()<=2) {
				if(player.islive)
				{
					this.player.shotEnemy();
				}
				
			}
			
		}
			
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		//ÿ100�����ػ�
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.hitEnemyTank();
//			
			this.hitPlayer();
			this.repaint();
		}
		
	}
	
	
	
}
