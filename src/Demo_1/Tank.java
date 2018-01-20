package Demo_1;

public class Tank {
	//tank坐标x y
	int x = 0;
	int y = 0;
	public boolean islive = true;
	//颜色
	int color;
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
		//设置tank的速度
		int speed = 2;
	//获取速度，设置速度
	public int getSpeed() {
			return speed;
	}
	public void setSpeed(int speed) {
			this.speed = speed;
	}
	//tank方向,0表示上，1表示下，2表示左，3表示右
	int direction = 0;
	
	//获取方向，设置方向
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	//设置坐标，获取坐标
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
	//tank构造函数
	public Tank(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
