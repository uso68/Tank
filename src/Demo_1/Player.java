package Demo_1;

import java.util.Vector;

public class Player extends Tank{
	Vector<Ammo> Ammos = new Vector<Ammo>();
	Ammo ammo = null;
	public Player(int x,int y) {
		super(x,y);
	}
	//开火
	public void shotEnemy() {
		//子弹方向
		switch(this.direction) {
		//上
		case 0:
			//创建一颗炮弹
			ammo = new Ammo(x+10, y,0);
			//把炮弹加入集合中
			Ammos.add(ammo);
			break;
		//下
		case 1:
			ammo = new Ammo(x+10, y+30,1);
			Ammos.add(ammo);
			break;
		//左
		case 2:
			ammo = new Ammo(x, y+10,2);
			Ammos.add(ammo);
			break;
		//右
		case 3:
			ammo = new Ammo(x+30, y+10,3);
			Ammos.add(ammo);
			break;
		}
		//启动子弹线程
		Thread t = new Thread(ammo);
		t.start();
	}
	//坦克向上移动
	public void moveUp() {
		if(y>0)
		y -= speed;
	}
	//坦克向右移动
	public void moveRight() {
		if(x<370)
		x += speed;
	}
	//坦克向下移动
	public void moveDown() {
		if(y<270)
		y += speed;
	}
	//坦克向左移动
	public void moveLeft() {
		if(x>0)
		x -= speed;
	}
}
