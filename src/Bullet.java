import java.awt.Color;
import java.awt.Toolkit;
import java.util.List;

public class Bullet implements Runnable, TankSize
{
    private int x = 0;
    private int y = 0;
    private Direct direct = Direct.UP;
    private int speed = 2;
    private boolean isLive = true;

    private int bulletSize = 5;

    private Color bulletColor = Color.WHITE;

    public Bullet(int x, int y, Color color, Direct direct)
    {
        this.x = x - bulletSize / 2;
        this.y = y - bulletSize / 2;
        this.bulletColor = color;
        this.direct = direct;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getBulletSize()
    {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize)
    {
        this.bulletSize = bulletSize;
    }

    public Color getBulletColor()
    {
        return bulletColor;
    }

    public void setBulletColor(Color bulletColor)
    {
        this.bulletColor = bulletColor;
    }

    @Override
    public void run()
    {

        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct)
            {

            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            }

            // 判断子弹是否碰到边沿
            if (x <= 0 || x > Toolkit.getDefaultToolkit().getScreenSize().width || y <= 0
                    || y > Toolkit.getDefaultToolkit().getScreenSize().height) {
                isLive = false;
                break;
            }
        }

    }

    public boolean isLive()
    {
        return isLive;
    }

    public void setLive(boolean live)
    {
        isLive = live;
    }

    public void hitTank(TankModel targetTank, List<Bomb> bombs)
    {
        // 判断坦克的方向
        switch (targetTank.getDirect())
        {
        case UP:
        case DOWN:
            if ((this.x >= targetTank.getX() && this.x <= targetTank.getX() + widthOfOutRect * 2 + widthOfCenterRect)
                    && (this.y >= targetTank.getY() && this.y <= targetTank.getY() + heightOfOutRect)) {
                this.isLive = false;
                targetTank.setAlive(false);

                Bomb bomb = new Bomb(targetTank.getX(), targetTank.getY());

                bombs.add(bomb);

            }
            break;
        case RIGHT:
        case LEFT:
            if (this.x >= targetTank.getX() && this.x <= targetTank.getX() + heightOfOutRect
                    && this.y >= targetTank.getY()
                    && this.y <= targetTank.getY() + widthOfOutRect * 2 + widthOfCenterRect) {

                this.isLive = false;

                targetTank.setAlive(false);

                Bomb bomb = new Bomb(targetTank.getX(), targetTank.getY());

                bombs.add(bomb);

            }
            break;

        }
    }
}
