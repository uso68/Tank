package Demo_1;

public class Tank {
	//tank����x y
	int x = 0;
	int y = 0;
	public boolean islive = true;
	//��ɫ
	int color;
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
		//����tank���ٶ�
		int speed = 2;
	//��ȡ�ٶȣ������ٶ�
	public int getSpeed() {
			return speed;
	}
	public void setSpeed(int speed) {
			this.speed = speed;
	}
	//tank����,0��ʾ�ϣ�1��ʾ�£�2��ʾ��3��ʾ��
	int direction = 0;
	
	//��ȡ�������÷���
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	//�������꣬��ȡ����
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}	
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	//tank���캯��
	public Tank(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
