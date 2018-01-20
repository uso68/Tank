package Demo_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StartPanel extends JPanel implements Runnable{
	int times = 0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//此处表示times能够被偶数除的时候才绘出字体，如果是奇数就不绘制字体
		if(times%2==0)
		{
			g.setColor(Color.yellow);
			Font ft = new Font("华文新魏",Font.BOLD,30);
			g.setFont(ft);
			g.drawString("STAGE - 1", 130, 150);
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//用来做标记
			times++;
			//重画
			this.repaint();
		}
	}

}
