package Demo_1;

public class Bomb {
	//����ը������
	int x,y;
	//����ը��������
	int life = 9;
	//����״̬
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
