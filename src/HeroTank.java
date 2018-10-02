import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 英雄坦克
 */
public class HeroTank extends TankModel
{
    private List<Bullet> bullets = new ArrayList<>();

    private int bulletNum = 5;

    public HeroTank(int x, int y)
    {
        super(x, y, Color.RED);
    }

    public void shot()
    {

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

        Thread thread = new Thread(bullet);
        thread.start();

        bullets.add(bullet);
    }

    public List<Bullet> getBullets()
    {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets)
    {
        this.bullets = bullets;
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
