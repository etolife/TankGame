import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * 画布面板，重写Jpanel的paint函数，在paint函数中完成模型回执
 */
public class TankPannel extends JPanel implements KeyListener, Runnable, TankSize
{
    // HeroTank
    HeroTank heroTank = null;

    // EnemyTanks
    List<EnemyTank> enemyTankList = new ArrayList<>();

    int enemyNum = 3;

    Image bombImage = null;

    List<Bomb> bombs = new ArrayList<>();

    public TankPannel()
    {
        heroTank = new HeroTank(500, 600);

        for (int i = 0; i < enemyNum; i++) {
            EnemyTank enemyTank = new EnemyTank(100 + i * 100, 100);

            Thread thread = new Thread(enemyTank);
            thread.start();

            enemyTank.setDirect(Direct.DOWN);
            enemyTankList.add(enemyTank);

            // 给敌方坦克添加子弹
            Bullet bullet = new Bullet(enemyTank.getX() + TankSize.widthOfOutRect + TankSize.widthOfCenterRect / 2,
                    enemyTank.getY() + TankSize.heightOfOutRect, enemyTank.getColor(), enemyTank.getDirect());

            ArrayList<Bullet> bullets = new ArrayList<>();
            bullets.add(bullet);
            enemyTank.setBulletList(bullets);

            Thread bulletThread = new Thread(bullet);
            bulletThread.start();
        }

        // bombImage =
        // Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb.jpg"));
        bombImage = Toolkit.getDefaultToolkit().getImage("D:/Code/JavaProject/TankGame/src/bomb2.jpg");
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        graphics.setColor(Color.BLACK);

        graphics.fillRect(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        // 画我方坦克
        if (heroTank.isAlive()) {
            heroTank.createTank(graphics);
        }

        // 画敌方坦克
        if (!enemyTankList.isEmpty()) {
            for (int i = 0; i < enemyTankList.size(); i++) {

                if (enemyTankList.get(i).isAlive()) {
                    enemyTankList.get(i).createTank(graphics);

                    // 画敌方坦克子弹
                    for (int j = 0; j < enemyTankList.get(i).getBulletList().size(); j++) {
                        Bullet bullet = enemyTankList.get(i).getBulletList().get(j);
                        if (bullet.isLive()) {
                            graphics.setColor(bullet.getBulletColor());
                            graphics.fillOval(bullet.getX(), bullet.getY(), bullet.getBulletSize(),
                                    bullet.getBulletSize());

                        } else {
                            enemyTankList.get(i).getBulletList().remove(bullet);
                        }
                    }
                }
            }
        }

        // 画我方坦克子弹
        if (!heroTank.getBullets().isEmpty()) {

            for (int i = 0; i < heroTank.getBullets().size(); i++) {

                if (heroTank.getBullets().get(i) != null && heroTank.getBullets().get(i).isLive()) {
                    graphics.setColor(heroTank.getBullets().get(i).getBulletColor());
                    graphics.fillOval(heroTank.getBullets().get(i).getX(), heroTank.getBullets().get(i).getY(),
                            heroTank.getBullets().get(i).getBulletSize(), heroTank.getBullets().get(i).getBulletSize());
                }

                if (!heroTank.getBullets().get(i).isLive()) {

                    Bullet deadBullet = heroTank.getBullets().get(i);
                    heroTank.getBullets().remove(deadBullet);
                }
            }

            // 画爆炸效果
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);

                graphics.drawImage(bombImage, bombs.get(i).getX(), bombs.get(i).getY(), bombs.get(i).getBombWidth(),
                        bombs.get(i).getBombHeight(), this);

                bombs.remove(bomb);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            heroTank.setDirect(Direct.UP);
            heroTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            heroTank.setDirect(Direct.RIGHT);
            heroTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            heroTank.setDirect(Direct.DOWN);
            heroTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            heroTank.setDirect(Direct.LEFT);
            heroTank.moveLeft();
        } else {
            // return;
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {

            // 限制子弹个数
            if (heroTank.getBullets().size() < heroTank.getBulletNum()) {
                heroTank.shot();
            }

        }

        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void run()
    {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 取每个子弹和每个坦克进行判断，是否子弹击中坦克，如果击中则子弹和坦克消失
            for (int i = 0; i < heroTank.getBullets().size(); i++) {

                if (heroTank.getBullets().get(i).isLive()) {
                    for (int j = 0; j < enemyTankList.size(); j++) {

                        if (enemyTankList.get(j).isAlive()) {
                            heroTank.getBullets().get(i).hitTank(enemyTankList.get(j), bombs);
                        }
                    }
                }
            }

            // 当敌人子弹击中我方坦克
            for (int i = 0; i < enemyTankList.size(); i++) {
                EnemyTank enemyTank = enemyTankList.get(i);
                if (enemyTank.isAlive()) {
                    for (int j = 0; j < enemyTank.getBulletList().size(); j++) {

                        enemyTank.getBulletList().get(j).hitTank(heroTank, bombs);

                    }
                }
            }

            repaint();
        }
    }
}
