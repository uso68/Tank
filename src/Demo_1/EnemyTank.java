package Demo_1;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
	//����һ�����ϣ����Է��ʵ�MyPanel�ϵ����е���̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	//����һ�����ϣ�������ŵ��˵��ڵ�
	int times = 0;
	Vector<Ammo> ammos = new Vector<Ammo>();
	//��������ڵ���Ӧ���ڸոմ���̹�˺͵����ڵ�������
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	//��ĳһ������̹�˻�ȡ��MyPanel�ϵ����е���̹��
	public void getEts(Vector<EnemyTank> vv)
	{
		this.ets = vv;
	}
	//�ж��Ƿ�ײ����ĵ���̹��
	public boolean isTouchOtherEnemy()
	{
		boolean b = false;
		switch(this.direction)
		{
		//�������ϵ�ʱ���ж�
		case 0:
			//ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {
				//ȡ��ÿһ������̹��
				EnemyTank et = ets.get(i);
				//�ж��Ƿ����Լ�������a��b��c�������ˣ��ж��Ƿ����Լ���������̹��
				if(et!=this)
				{
					//������˷��������ϻ���������
					if(et.direction==0||et.direction==1)
					{
						//�жϽӴ����һ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�жϽӴ������һ����
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					//������˷������������������
					if(et.direction==2||et.direction==3)
					{
						//�жϽӴ����һ����
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//�жϽӴ������һ����
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		//�������µ�ʱ���ж�
		case 1:
			//ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {
				//ȡ��ÿһ������̹��
				EnemyTank et = ets.get(i);
				//�ж��Ƿ����Լ�������a��b��c�������ˣ��ж��Ƿ����Լ���������̹��
				if(et!=this)
				{
					//������˷��������ϻ���������
					if(et.direction==0||et.direction==1)
					{
						//�жϽӴ������һ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//�жϽӴ�����ұ�һ����
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					//������˷������������������
					if(et.direction==2||et.direction==3)
					{
						//�жϽӴ����һ����
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						//�жϽӴ������һ����
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		//���������ʱ���ж�
		case 2:
			//ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {
				//ȡ��ÿһ������̹��
				EnemyTank et = ets.get(i);
				//�ж��Ƿ����Լ�������a��b��c�������ˣ��ж��Ƿ����Լ���������̹��
				if(et!=this)
				{
					//������˷��������ϻ���������
					if(et.direction==0||et.direction==1)
					{
						//�жϽӴ������һ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�жϽӴ������һ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					//������˷������������������
					if(et.direction==2||et.direction==3)
					{
						//�жϽӴ����һ����
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//�жϽӴ������һ����
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		//�������ҵ�ʱ���ж�
		case 3:
			//ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {
				//ȡ��ÿһ������̹��
				EnemyTank et = ets.get(i);
				//�ж��Ƿ����Լ�������a��b��c�������ˣ��ж��Ƿ����Լ���������̹��
				if(et!=this)
				{
					//������˷��������ϻ���������
					if(et.direction==0||et.direction==1)
					{
						//�жϽӴ����һ����
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�жϽӴ������һ����
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					//������˷������������������
					if(et.direction==2||et.direction==3)
					{
						//�жϽӴ����һ����
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						//�жϽӴ������һ����
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
			
			//�����ж�����
			switch(this.direction) {
			//��
			case 0:
				//�õ��������һ��������ȳ�����һ�ξ��룬��ѭ����������Ȼ���������������
				for (int i = 0; i < 30; i++) {
					//�涨���������ߵķ�Χ
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
			//��
			case 1:
				for (int i = 0; i < 30; i++) {
					//�涨���������ߵķ�Χ
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
			//��
			case 2:
				for (int i = 0; i < 30; i++) {
					//�涨���������ߵķ�Χ
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
			//��
			case 3:
				for (int i = 0; i < 30; i++) {
					//�涨���������ҵķ�Χ
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
			//��֪��ʲô��˼�����������ڵ��ӳٻ��ƣ����ּ��ŵ�Ч��
			if(times%2==0)
			{
				if(islive)
				{
					//�ж���������ڵ�����С��1
					if(ammos.size()<3)
					{
						Ammo ammo = null;
						//û���ӵ������
						switch(direction)
						{
						case 0:
							//����һ���ڵ�
							ammo = new Ammo(x+10, y,0);
							//���ڵ����뼯����
							ammos.add(ammo);
							break;
						//��
						case 1:
							ammo = new Ammo(x+10, y+30,1);
							ammos.add(ammo);
							break;
						//��
						case 2:
							ammo = new Ammo(x, y+10,2);
							ammos.add(ammo);
							break;
						//��
						case 3:
							ammo = new Ammo(x+30, y+10,3);
							ammos.add(ammo);
							break;
						}
						//���������ڵ��߳�
						Thread t = new Thread(ammo);
						t.start();
					}
				}
			}
				
//			//�ж��Ƿ���Ҫ�����˼������ӵ�
//			for (int i = 0; i < ets.size(); i++) {
//				EnemyTank et = ets.get(i);
//				//��������ǻ��ŵĲſ��Է��ڵ�
//				if(et.islive)
//				{
//					
//				}
//				
//			}
			//�õ����������һ��0-3�ķ���
			this.direction = (int)(Math.random()*4);
			//�жϵ����Ƿ�����
			if(this.islive == false) {
				//�����������˳��߳�
				break;
			}
			
		}
	}
	
}
