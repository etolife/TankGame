import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 * 敵人坦克
 */
public class EnemyTank extends TankModel implements Runnable
{
    private List<Bullet> bulletList = new ArrayList<>();

    private int bulletNum = 5;

    public EnemyTank(int x, int y)
    {
        super(x, y, Color.BLUE);
    }

    @Override
    public void run()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        while (true) {

            switch (this.getDirect())
            {
            case UP:
                for (int i = 0; i < 30; i++) {
                    if (this.getY() - this.getSpeed() > 0) {
                        this.setY(this.getY() - this.getSpeed());
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < 30; i++) {
                    if (this.getX() + this.getSpeed() + widthOfOutRect < (int) dimension.getWidth()) {
                        this.setX(this.getX() + this.getSpeed());
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case DOWN:
                for (int i = 0; i < 30; i++) {
                    if (this.getY() + this.getSpeed() + widthOfOutRect + 70 < (int) dimension.getHeight())
                        this.setY(this.getY() + this.getSpeed());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < 30; i++) {
                    if (this.getX() - this.getSpeed() > 0) {
                        this.setX(this.getX() - this.getSpeed());
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }

            // 敌人坦克添加子弹

            List<Bullet> bullets = this.getBulletList();

            if (this.isAlive()) {
                if (this.getBulletList().size() < this.getBulletNum()) {
                    int xBullet = 0;
                    int yBullet = 0;
                    switch (this.getDirect())
                    {
                    case UP:
                        xBullet = this.getX() + widthOfOutRect + widthOfCenterRect / 2;
                        yBullet = this.getY();
                        break;
                    case RIGHT:
                        xBullet = this.getX() + heightOfOutRect;
                        yBullet = this.getY() + widthOfOutRect + widthOfCenterRect / 2;
                        break;
                    case DOWN:
                        xBullet = this.getX() + widthOfOutRect + widthOfCenterRect / 2;
                        yBullet = this.getY() + heightOfOutRect;
                        break;
                    case LEFT:
                        xBullet = this.getX();
                        yBullet = this.getY() + widthOfOutRect + widthOfCenterRect / 2;
                        break;
                    }
                    Bullet bullet = new Bullet(xBullet, yBullet, this.getColor(), this.getDirect());
                    bullets.add(bullet);

                    Thread thread = new Thread(bullet);
                    thread.start();

                }

                this.setBulletList(bullets);
            }

            // 随机产生方向
            int directValue = (int) (Math.random() * 4);
            this.setDirect(Direct.values()[directValue]);

            if (!this.isAlive()) {
                // 坦克死亡，退出线程
                break;
            }

        }
    }

    public List<Bullet> getBulletList()
    {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList)
    {
        this.bulletList = bulletList;
    }

    public int getBulletNum()
    {
        return bulletNum;
    }

    public void setBulletNum(int bulletNum)
    {
        this.bulletNum = bulletNum;
    }
}
