package Demo_1;

import java.util.Vector;

public class Player extends Tank{
	Vector<Ammo> Ammos = new Vector<Ammo>();
	Ammo ammo = null;
	public Player(int x,int y) {
		super(x,y);
	}
	//����
	public void shotEnemy() {
		//�ӵ�����
		switch(this.direction) {
		//��
		case 0:
			//����һ���ڵ�
			ammo = new Ammo(x+10, y,0);
			//���ڵ����뼯����
			Ammos.add(ammo);
			break;
		//��
		case 1:
			ammo = new Ammo(x+10, y+30,1);
			Ammos.add(ammo);
			break;
		//��
		case 2:
			ammo = new Ammo(x, y+10,2);
			Ammos.add(ammo);
			break;
		//��
		case 3:
			ammo = new Ammo(x+30, y+10,3);
			Ammos.add(ammo);
			break;
		}
		//�����ӵ��߳�
		Thread t = new Thread(ammo);
		t.start();
	}
	//̹�������ƶ�
	public void moveUp() {
		if(y>0)
		y -= speed;
	}
	//̹�������ƶ�
	public void moveRight() {
		if(x<370)
		x += speed;
	}
	//̹�������ƶ�
	public void moveDown() {
		if(y<270)
		y += speed;
	}
	//̹�������ƶ�
	public void moveLeft() {
		if(x>0)
		x -= speed;
	}
}
