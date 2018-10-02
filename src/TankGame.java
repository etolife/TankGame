import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * 主函數
 */
public class TankGame extends JFrame
{
    public static void main(String[] args)
    {
        TankGame tankGame = new TankGame();
    }

    public TankGame()
    {
        TankPannel tankPannel = new TankPannel();

        Thread thread = new Thread(tankPannel);
        thread.start();

        // this.setUndecorated(true);
        // this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.add(tankPannel);
        // 注册事件监听
        this.addKeyListener(tankPannel);

        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
