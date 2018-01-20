package Demo_1;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
	//定义一个集合，可以访问到MyPanel上的所有敌人坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	//定义一个集合，用来存放敌人的炮弹
	int times = 0;
	Vector<Ammo> ammos = new Vector<Ammo>();
	//敌人添加炮弹，应当在刚刚创建坦克和敌人炮弹死亡后
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	//让某一个敌人坦克获取到MyPanel上的所有敌人坦克
	public void getEts(Vector<EnemyTank> vv)
	{
		this.ets = vv;
	}
	//判断是否撞到别的敌人坦克
	public boolean isTouchOtherEnemy()
	{
		boolean b = false;
		switch(this.direction)
		{
		//方向向上的时候判断
		case 0:
			//取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				//取出每一个敌人坦克
				EnemyTank et = ets.get(i);
				//判断是否是自己，例如a、b、c三个敌人，判断是否是自己还是其他坦克
				if(et!=this)
				{
					//如果敌人方向是向上或者是向下
					if(et.direction==0||et.direction==1)
					{
						//判断接触面的一个点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//判断接触面的另一个点
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人方向是向左或者是向右
					if(et.direction==2||et.direction==3)
					{
						//判断接触面的一个点
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//判断接触面的另一个点
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		//方向向下的时候判断
		case 1:
			//取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				//取出每一个敌人坦克
				EnemyTank et = ets.get(i);
				//判断是否是自己，例如a、b、c三个敌人，判断是否是自己还是其他坦克
				if(et!=this)
				{
					//如果敌人方向是向上或者是向下
					if(et.direction==0||et.direction==1)
					{
						//判断接触面左边一个点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//判断接触面的右边一个点
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人方向是向左或者是向右
					if(et.direction==2||et.direction==3)
					{
						//判断接触面的一个点
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						//判断接触面的另一个点
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		//方向向左的时候判断
		case 2:
			//取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				//取出每一个敌人坦克
				EnemyTank et = ets.get(i);
				//判断是否是自己，例如a、b、c三个敌人，判断是否是自己还是其他坦克
				if(et!=this)
				{
					//如果敌人方向是向上或者是向下
					if(et.direction==0||et.direction==1)
					{
						//判断接触面的上一个点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//判断接触面的下一个点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人方向是向左或者是向右
					if(et.direction==2||et.direction==3)
					{
						//判断接触面的一个点
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//判断接触面的另一个点
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		//方向向右的时候判断
		case 3:
			//取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				//取出每一个敌人坦克
				EnemyTank et = ets.get(i);
				//判断是否是自己，例如a、b、c三个敌人，判断是否是自己还是其他坦克
				if(et!=this)
				{
					//如果敌人方向是向上或者是向下
					if(et.direction==0||et.direction==1)
					{
						//判断接触面的一个点
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//判断接触面的另一个点
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					//如果敌人方向是向左或者是向右
					if(et.direction==2||et.direction==3)
					{
						//判断接触面的一个点
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//判断接触面的另一个点
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		}
		return b;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			
			//敌人行动方向
			switch(this.direction) {
			//上
			case 0:
				//让敌人随机到一个方向后先持续走一段距离，用循环做出来，然后再随机其他方向
				for (int i = 0; i < 30; i++) {
					//规定敌人往上走的范围
					if(y>0&&!this.isTouchOtherEnemy()) {
						y-=speed;
					}					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			//下
			case 1:
				for (int i = 0; i < 30; i++) {
					//规定敌人往下走的范围
					if(y<270&&!this.isTouchOtherEnemy()) {
						y+=speed;
					}					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			//左
			case 2:
				for (int i = 0; i < 30; i++) {
					//规定敌人往左走的范围
					if(x>0&&!this.isTouchOtherEnemy()) {
						x-=speed;
					}					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			//右
			case 3:
				for (int i = 0; i < 30; i++) {
					//规定敌人往上右的范围
					if(x<370&&!this.isTouchOtherEnemy()) {
						x+=speed;
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;				
			}
			this.times++;
			//不知道什么意思，好像是让炮弹延迟绘制，出现几颗的效果
			if(times%2==0)
			{
				if(islive)
				{
					//判断如果敌人炮弹数量小于1
					if(ammos.size()<3)
					{
						Ammo ammo = null;
						//没有子弹就添加
						switch(direction)
						{
						case 0:
							//创建一颗炮弹
							ammo = new Ammo(x+10, y,0);
							//把炮弹加入集合中
							ammos.add(ammo);
							break;
						//下
						case 1:
							ammo = new Ammo(x+10, y+30,1);
							ammos.add(ammo);
							break;
						//左
						case 2:
							ammo = new Ammo(x, y+10,2);
							ammos.add(ammo);
							break;
						//右
						case 3:
							ammo = new Ammo(x+30, y+10,3);
							ammos.add(ammo);
							break;
						}
						//启动敌人炮弹线程
						Thread t = new Thread(ammo);
						t.start();
					}
				}
			}
				
//			//判断是否需要给敌人加入新子弹
//			for (int i = 0; i < ets.size(); i++) {
//				EnemyTank et = ets.get(i);
//				//如果敌人是活着的才可以发炮弹
//				if(et.islive)
//				{
//					
//				}
//				
//			}
			//让敌人随机产生一个0-3的方向
			this.direction = (int)(Math.random()*4);
			//判断敌人是否死亡
			if(this.islive == false) {
				//敌人死亡就退出线程
				break;
			}
			
		}
	}
	
}
