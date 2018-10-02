import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * 坦克模型，基本的坦克屬性
 */
public class TankModel implements TankSize
{
    // 設置坦克的基本屬性，包括：
    // 位置（x，y坐標）
    // 坦克方向
    // 坦克移動速度
    // 坦克顔色
    // 坦克當前生命狀態（是否活著）
    private int x = 0;
    private int y = 0;
    private Direct direct = Direct.UP;
    private int speed = 5;
    private Color color = Color.CYAN;
    private boolean isAlive = true;

    public TankModel(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public Direct getDirect()
    {
        return direct;
    }

    public void setDirect(Direct direct)
    {
        this.direct = direct;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public void createTank(Graphics graphics)
    {
        graphics.setColor(color);
        Graphics2D graphics2D = (Graphics2D) graphics;

        switch (direct)
        {
        case UP:
            graphics.fill3DRect(x, y, widthOfOutRect, heightOfOutRect, false);

            graphics.fill3DRect(x + widthOfOutRect + widthOfCenterRect, y, widthOfOutRect, heightOfOutRect, false);

            graphics.fill3DRect(x + widthOfOutRect, y + (heightOfOutRect - heightOfCenterRect) / 2, widthOfCenterRect,
                    heightOfCenterRect, false);

            graphics.fillOval(x + widthOfOutRect, y + 2 * (heightOfOutRect - heightOfCenterRect) / 3, radiusOfCircle,
                    radiusOfCircle);

            graphics2D.setStroke(new BasicStroke(strokeOfGun));
            graphics2D.drawLine(x + widthOfOutRect + widthOfCenterRect / 2, y + heightOfOutRect / 2,
                    x + widthOfOutRect + widthOfCenterRect / 2, y);

            break;
        case RIGHT:
            graphics.fill3DRect(x, y, heightOfOutRect, widthOfOutRect, false);

            graphics.fill3DRect(x, y + widthOfOutRect + widthOfCenterRect, heightOfOutRect, widthOfOutRect, false);

            graphics.fill3DRect(x + (heightOfOutRect - heightOfCenterRect) / 2, y + widthOfOutRect, heightOfCenterRect,
                    widthOfCenterRect, false);

            graphics.fillOval(x + 2 * (heightOfOutRect - heightOfCenterRect) / 3, y + widthOfOutRect, radiusOfCircle,
                    radiusOfCircle);
            graphics2D.setStroke(new BasicStroke(strokeOfGun));
            graphics2D.drawLine(x + heightOfOutRect / 2, y + widthOfOutRect + widthOfCenterRect / 2,
                    x + heightOfOutRect, y + widthOfOutRect + widthOfCenterRect / 2);
            break;
        case DOWN:
            graphics.fill3DRect(x, y, widthOfOutRect, heightOfOutRect, false);

            graphics.fill3DRect(x + widthOfOutRect + widthOfCenterRect, y, widthOfOutRect, heightOfOutRect, false);

            graphics.fill3DRect(x + widthOfOutRect, y + (heightOfOutRect - heightOfCenterRect) / 2, widthOfCenterRect,
                    heightOfCenterRect, false);

            graphics.fillOval(x + widthOfOutRect, y + 2 * (heightOfOutRect - heightOfCenterRect) / 3, radiusOfCircle,
                    radiusOfCircle);
            graphics2D.setStroke(new BasicStroke(strokeOfGun));
            graphics2D.drawLine(x + widthOfOutRect + widthOfCenterRect / 2, y + heightOfOutRect / 2,
                    x + widthOfOutRect + widthOfCenterRect / 2, y + heightOfOutRect);
            break;
        case LEFT:
            graphics.fill3DRect(x, y, heightOfOutRect, widthOfOutRect, false);

            graphics.fill3DRect(x, y + widthOfOutRect + widthOfCenterRect, heightOfOutRect, widthOfOutRect, false);

            graphics.fill3DRect(x + (heightOfOutRect - heightOfCenterRect) / 2, y + widthOfOutRect, heightOfCenterRect,
                    widthOfCenterRect, false);

            graphics.fillOval(x + 2 * (heightOfOutRect - heightOfCenterRect) / 3, y + widthOfOutRect, radiusOfCircle,
                    radiusOfCircle);
            graphics2D.setStroke(new BasicStroke(strokeOfGun));
            graphics2D.drawLine(x + heightOfOutRect / 2, y + widthOfOutRect + widthOfCenterRect / 2, x,
                    y + widthOfOutRect + widthOfCenterRect / 2);
            break;
        default:
            break;
        }

    }

    public void moveUp()
    {
        if (y - speed > 0) {
            y -= speed;
        }
    }

    public void moveRight()
    {
        if (x + speed + heightOfOutRect < dimension.getWidth()) {
            x += speed;
        }
    }

    public void moveDown()
    {
        if (y + speed + heightOfOutRect + 70 < dimension.getHeight()) {
            y += speed;
        }
    }

    public void moveLeft()
    {
        if (x - speed > 0) {
            x -= speed;
        }
    }
}
