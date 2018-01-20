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


//定义一个绘图区域
public class MyPanel extends JPanel implements KeyListener,Runnable{
		
	Player player = null;
	//定义敌人集合
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	Vector<Node> nodes = new Vector<Node>();
	//定义炸弹集合
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	//敌人数量
	int etsSize = 5;
	EnemyTank a = null;
	//定义三种爆炸图片,三种图片按顺序绘制，才能画出爆炸效果
	Image im_small = null;
	Image im_middle = null;
	Image im_big = null;
	
	public MyPanel(String Flag)//参数Flag用来判断是开始新游戏还是读取存档
	{
		//恢复上次战绩记录
		Recorder.getRecorder();
		player = new Player(100, 100);
		
		
		if(Flag.equals("newGame"))
		{
			//初始化敌人
			for (int i = 0; i < etsSize; i++) 
			{
				//创建一个敌人
				EnemyTank et = new EnemyTank((i+1)*50,0);
				et.setColor(1);
				et.setDirection(1);
				//让敌人坦克能够获取到所有被创建出来的敌人坦克
				et.getEts(ets);
				Thread t1 = new Thread(et);
				t1.start();
				//给敌人添加一个炮弹
				Ammo ammo = new Ammo(et.x+10, et.y+30, 2);
				//把炮弹加入到敌人炮弹集合
				et.ammos.add(ammo);
				//将炮弹放到进程对象中
				Thread t2 = new Thread(ammo);
				t2.start();
				//把敌人坦克加入敌人坦克集合
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
				//创建一个敌人
				EnemyTank et = new EnemyTank(node.x,node.y);
				et.setColor(1);
				et.setDirection(node.direction);
				//让敌人坦克能够获取到所有被创建出来的敌人坦克
				et.getEts(ets);
				Thread t1 = new Thread(et);
				t1.start();
				//给敌人添加一个炮弹
				Ammo ammo = new Ammo(et.x+10, et.y+30, 2);
				//把炮弹加入到敌人炮弹集合
				et.ammos.add(ammo);
				//将炮弹放到进程对象中
				Thread t2 = new Thread(ammo);
				t2.start();
				//把敌人坦克加入敌人坦克集合
				ets.add(et);
			}
			
		}
		
		//初始化爆炸图片
		im_small = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/small.png"));
		im_middle = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/middle.png"));
		im_big = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/big.png"));
	}
	
	//覆盖JPanel的paint()方法，Graphics是绘图的重要类，可以理解为一只画笔
	public void paint(Graphics g) 
	{
		//调用父类方法完成初始化，这一句不能少
		super.paint(g);
		Bomb bomb1 = new Bomb(10, 10);
		g.drawImage(im_small, bomb1.x, bomb1.y, 30, 30, this);
		g.drawImage(im_middle, bomb1.x, bomb1.y, 30, 30, this);
		g.drawImage(im_big, bomb1.x, bomb1.y, 30, 30, this);
		//画地图区域
		g.fillRect(0, 0, 400, 300);
		//画出提示信息
		this.showInfo(g);
		
		//如果玩家还活着，画出玩家坦克
		if(player.islive)
		{
			this.drawTank(player.getX(), player.getY(), this.player.direction, g, 0);
		}
		
		//画出多个子弹
		for (int i = 0; i < player.Ammos.size(); i++) 
		{
			
			Ammo MyAmmo = player.Ammos.get(i);
			//判断子弹状态，不为空或者活着，画出来
			if(MyAmmo != null && MyAmmo.islive == true) 
			{
				//画出自己的炮弹
				g.draw3DRect(MyAmmo.getX(), MyAmmo.getY(), 2, 2, false);
			}
			//判断子弹状态，死亡就从集合中移除
			if(MyAmmo.islive == false) 
			{
				player.Ammos.remove(MyAmmo);
			}
		}
		//画出炸弹效果		
		for (int i = 0; i < bombs.size(); i++)
		{
			//遍历炸弹
			Bomb bomb = bombs.get(i);
			//判断炸弹生命值，不同生命值显示不同爆炸效果图
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
			//画一次就让炸弹的生命值减少
			bomb.lifeDown();
			//如果炸弹生命值为0，则从集合中移除炸弹
			if(bomb.life==0) 
			{
				bombs.remove(bomb);
			}
				
		}
	
		//画出敌人,同时把敌人的炮弹画出来		
		for (int i = 0; i < ets.size(); i++) 
		{
			EnemyTank et = ets.get(i);
			if(et.islive) 
			{
				this.drawTank(et.getX(), et.getY(), et.getDirection(), g, et.getColor());
				//画出敌人子弹
				for (int j = 0; j < et.ammos.size(); j++) 
				{
					//取出敌人子弹
					Ammo enemyAmmo = et.ammos.get(j);
					if(enemyAmmo.islive)
					{
						//画出敌人的炮弹
						g.draw3DRect(enemyAmmo.x, enemyAmmo.y, 2, 2, false);
					}else {
						//否则敌人死亡，那么就将敌人的炮弹从集合中移除
						et.ammos.remove(enemyAmmo);
					}
				}
			}
			
		}
	}
	//画出提示信息
	public void showInfo(Graphics g) {
		//画出一个坦克图标,它不参加战斗
		this.drawTank(80, 330, 0, g, 1);
		//调画笔画出敌人数量
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getEnNum()+"", 110, 350);
		//画出一个玩家坦克图标,它不参加战斗
		this.drawTank(140, 330, 0, g, 0);
		//调画笔画出玩家数量
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getMyLife()+"", 170, 350);
		//画出玩家总成绩
		g.setColor(Color.BLACK);
		Font ft = new Font("宋体",Font.BOLD,20);
		g.setFont(ft);
		g.drawString("当前战绩", 420, 30);
		this.drawTank(420, 60, 0, g, 1);
		g.setColor(Color.black);;
		g.drawString(""+Recorder.getKillCount(), 460, 80);
	}
	//判断敌人子弹是否击中玩家的函数
	public void hitPlayer()
	{
		for (int i = 0; i < ets.size(); i++) {
			//每一个敌人坦克
			EnemyTank et = ets.get(i);
			
			for (int j = 0; j < et.ammos.size(); j++) {
				//再取出每一个子弹对象
				Ammo enemyAmmo = et.ammos.get(j);
				//player状态为活才能被击中
				if(player.islive)
				{
					this.hitTank(enemyAmmo, player);
				}
				
			}
		}
	}
	
	
	
	//判断玩家子弹是否击中敌人
	public void hitEnemyTank()
	{
		//判断是否击中，击中函数要随时判断，所以放到线程任务中
		//循环判断每一颗子弹是否和每一辆坦克相遇
		for (int i = 0; i < player.Ammos.size(); i++) {
			//取出每一发子弹
			Ammo MyAmmo = player.Ammos.get(i);
			//判断子弹的死活，只有活的子弹可以击中敌人
			if(MyAmmo.islive) {
				//取出每一辆坦克与子弹进行判断
				for (int j = 0; j < ets.size(); j++) {
					//取出每一辆坦克
					EnemyTank et = ets.get(j);
					//判断坦克死活，活的才能被击中
					if(et.islive) {
						
						this.hitTank(MyAmmo, et);
					}
					
					
				}
			}
		}
	}	
	//专门判断子弹是否击中敌人坦克的函数并生成一个爆炸对象
	public void hitTank(Ammo am,Tank et) 
	{
		switch(et.direction) 
		{
		//如果敌人的方向是上或者是下
		case 0:			
		case 1:
			//判断子弹是否进入击中的范围()
			if(am.x>et.x&&am.x<et.x+20&&am.y>et.y&&am.y<et.y+30) 
			{
				//击中后，子弹死亡，敌人死亡
				am.islive = false;
				et.islive = false;
				//判断如果击中的是敌人
				if(et instanceof EnemyTank)
				{
					//减少敌人数量
					Recorder.reduceEnNum();
					//增加击杀数量
					Recorder.killCount();
				}	
				//如果是自己
				if(et instanceof Player)
				{
					Recorder.reduceMyLife();
				}
				//创建一个炸弹，并放入炸弹集合
				Bomb bomb = new Bomb(et.x, et.y);
				bombs.add(bomb);
			}
			break;
		//如果敌人的方向是左或者是右
		case 2:
		case 3:			
			//判断子弹是否进入击中的范围
			if(am.x>et.x&&am.x<et.x+20&&am.y>et.y&&am.y<et.y+20) {
				//击中后，子弹死亡，敌人死亡
				am.islive = false;
				et.islive = false;
				//判断如果击中的是敌人
				if(et instanceof EnemyTank)
				{
					//减少敌人数量
					Recorder.reduceEnNum();
					//增加击杀数量
					Recorder.killCount();
				}
				//如果是自己
				if(et instanceof Player)
				{
					Recorder.reduceMyLife();
				}
				//创建一个炸弹，并放入炸弹集合
				Bomb bomb = new Bomb(et.x, et.y);
				bombs.add(bomb);
			}			
			break;
		}
	}
	//抽取画坦克代码，封装到函数中
	public void drawTank(int x,int y,int direction,Graphics g,int type) {
//		x = player.getX();
//		y = player.getY();
		//判断主角还是敌人
		switch(type) 
		{
		//玩家颜色
		case 0:
			g.setColor(Color.ORANGE);
			break;
		//敌人颜色
		case 1:
			g.setColor(Color.GREEN);
			break;
		}
		//判断炮管方向
		switch(direction) {
		////坦克炮口向上
		case 0:
			TankUp(x, y, g);
			break;
		//坦克炮口向下	
		case 1:			
			TankDown(x, y, g);
			break;			
		//坦克炮口向左	
		case 2:			
			TankLeft(x, y, g);
			break;			
		//坦克炮口向右	
		case 3:
			TankRight(x, y, g);
			break;			
		}
	}
	//坦克形态朝右
	public void TankRight(int x, int y, Graphics g) {
		//画出上半边
		g.fill3DRect(x, y, 30, 5, false);
		//画出下半边
		g.fill3DRect(x, y+15, 30, 5, false);
		//画中间车身
		g.fill3DRect(x+5, y+5,20, 10, false);
		//画炮塔
		g.fillOval(x+10, y+5, 10, 10);
		//画炮管
		g.drawLine(x+15, y+10, x+30, y+10);
	}
	//坦克形态朝左
	public void TankLeft(int x, int y, Graphics g) {
		//画出上半边
		g.fill3DRect(x, y, 30, 5, false);
		//画出下半边
		g.fill3DRect(x, y+15, 30, 5, false);
		//画中间车身
		g.fill3DRect(x+5, y+5,20, 10, false);
		//画炮塔
		g.fillOval(x+10, y+5, 10, 10);
		//画炮管
		g.drawLine(x+15, y+10, x, y+10);
	}
	//坦克形态朝下
	public void TankDown(int x, int y, Graphics g) {
		//画左边
		g.fill3DRect(x, y, 5, 30, false);
		//画右边
		g.fill3DRect(x+15, y, 5, 30, false);
		//画中间车身
		g.fill3DRect(x+5, y+5,10, 20, false);
		//画炮塔
		g.fillOval(x+5, y+10, 10, 10);
		//画炮管
		g.drawLine(x+10, y+15, x+10, y+30);
	}
	//坦克形态朝上
	public void TankUp(int x, int y, Graphics g) {
		//画坦克			
		//画左边			
		g.fill3DRect(x, y, 5, 30, false);
		//画右边
		g.fill3DRect(x+15, y, 5, 30, false);
		//画中间车身
		g.fill3DRect(x+5, y+5,10, 20, false);
		//画炮塔
		g.fillOval(x+5, y+10, 10, 10);
		//画炮管
		g.drawLine(x+10, y+15, x+10, y);
	}
	
	//键盘控制方向
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			//设置方向
			this.player.setDirection(1);
			this.player.moveDown();
			//获取当前Y坐标，并使用变量存储
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
			//原理同上
			this.player.setDirection(0);
			this.player.moveUp();
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			this.player.setDirection(2);
			this.player.moveLeft();
			
			
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
			this.player.setDirection(3);
			this.player.moveRight();
			
			
		}
		//按下开火键J 
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
		//每100毫秒重绘
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
