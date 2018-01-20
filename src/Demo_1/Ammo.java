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
			//上
			case 0:
				y -= speed;
				break;
			//下
			case 1:
				y += speed;
				break;
			//左
			case 2:
				x -= speed;
				break;
			//右
			case 3:
				x += speed;
				break;
			}
			//判断子弹是否出界，是就消失
			if(x<0||x>400||y<0||y>300) {
				this.islive = false;
				break;
			}
			
		}
	}
}
