package Demo_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class MyTankGame_1 extends JFrame implements ActionListener{
	StartPanel startPanel = null;
	MyPanel mp = null;
	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	JMenuItem jmi4 = null;
	Recorder recorder = null;
	public MyTankGame_1() {
		this.setTitle("坦克大战");
		jmb = new JMenuBar();
		jm1 = new JMenu("开始游戏(G)");
		//设置快捷键
		jm1.setMnemonic('G');
		
		jmi1 = new JMenuItem("开始新游戏(N)");
		jmi2 = new JMenuItem("退出游戏(E)");
		jmi3 = new JMenuItem("存盘退出游戏(C)");
		jmi4 = new JMenuItem("读取存档(R)");
		jmi2.setMnemonic('E');
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi2.setActionCommand("exit");
		jmi3.setActionCommand("saveExit");
		jmi4.setActionCommand("loadGame");
		
		startPanel = new StartPanel();
		Thread t = new Thread(startPanel);
		t.start();
		this.setJMenuBar(jmb);
		this.add(startPanel);
//		
		this.setLocation(500, 300);
		this.setSize(600, 500);		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//刷新面板
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("newgame"))
		{
			//进入游戏
			mp = new MyPanel("newGame");
			Thread t = new Thread(mp);
			t.start();
			this.remove(startPanel);
			this.add(mp);			
			this.addKeyListener(mp);
			//刷新面板
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")) {
			//Recorder.keepRecorder();
			System.exit(0);
		}else if(e.getActionCommand().equals("saveExit")) {
			recorder = new Recorder();
			recorder.setEts(mp.ets);
			recorder.keepkeepRecorderAll();
			System.exit(0);
		}else if(e.getActionCommand().equals("loadGame")) {
			//进入游戏
			mp = new MyPanel("load");
			
			Thread t = new Thread(mp);
			t.start();
			this.remove(startPanel);
			this.add(mp);			
			this.addKeyListener(mp);
			//刷新面板
			this.setVisible(true);
		}
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame_1 myTankGame_1 = new MyTankGame_1();
	}
	
	
	
}
