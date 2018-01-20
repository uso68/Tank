package Demo_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//用来记录，保存玩家的设置
public class Recorder {
	//敌人数量
	private static int enNum = 20;
	//玩家命数
	private static int myLife = 3;
	//消灭的敌人
	private static int killCount = 0;
	//从文件中恢复记录点
	static Vector<Node> nodes = new Vector<Node>();
	public Vector<Node> getNodesAndEneNum() throws IOException 
	{
		try {
			fr = new FileReader("E:\\myRecorder.txt");
			bufr = new BufferedReader(fr);
			String line = "";
			line = bufr.readLine();
			killCount = Integer.parseInt(line);
			while((line = bufr.readLine())!=null)
			{
				String[] xyz = line.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes.add(node);
				
			}
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufr.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return nodes;
	}
	private  Vector<EnemyTank> ets = new Vector<EnemyTank>();
	public Vector<EnemyTank> getEts() {
		return ets;
	}
	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
	public static int getKillCount() {
		return killCount;
	}
	public static void setKillCount(int killCount) {
		Recorder.killCount = killCount;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static void reduceEnNum() {
		enNum--;
	}
	
	
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	
	public static void killCount() {
		killCount++;
	}
	
	public static void reduceMyLife() {
		myLife--;
	}
	private static FileWriter fw = null;
	private static BufferedWriter bufw= null;
	private static FileReader fr = null;
	private static BufferedReader bufr = null;
	//把玩家的记录保存到文件中
	public static void keepRecorder() {
		
		try {
			fw = new FileWriter("E:\\myRecorder.txt");
			bufw = new BufferedWriter(fw);
			bufw.write(killCount+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public void keepkeepRecorderAll() {
		
		try {
			fw = new FileWriter("E:\\myRecorder.txt");
			bufw = new BufferedWriter(fw);
			bufw.write(killCount+"\r\n");
			//保存当前还活着的敌人坦克的坐标
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if(et.islive)
				{
					//保存坦克坐标与方向
					String pos = et.x+" "+et.y+" "+et.direction;
					bufw.write(pos+"\r\n");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void getRecorder(){
		try {
			fr = new FileReader("E:\\myRecorder.txt");
			bufr = new BufferedReader(fr);
			String line;
			try {
				line = bufr.readLine();
				killCount = Integer.parseInt(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufr.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}
