package Demo_1;

public class Ammo implements Runnable{
	int x,y,direction;
	int speed = 4;
	boolean islive = true;
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
	public Ammo(int x,int y,int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(direction) {
			//��
			case 0:
				y -= speed;
				break;
			//��
			case 1:
				y += speed;
				break;
			//��
			case 2:
				x -= speed;
				break;
			//��
			case 3:
				x += speed;
				break;
			}
			//�ж��ӵ��Ƿ���磬�Ǿ���ʧ
			if(x<0||x>400||y<0||y>300) {
				this.islive = false;
				break;
			}
			
		}
	}
}
