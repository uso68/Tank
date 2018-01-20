package Demo_1;

public class Bomb {
	//定义炸弹坐标
	int x,y;
	//定义炸弹的生命
	int life = 9;
	//生命状态
	boolean islive = true;
	public Bomb(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public void lifeDown() {
		if(life>0) {
			life--;
		}else {
			this.islive = false;
		}
		
	}
}
