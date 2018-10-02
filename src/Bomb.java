public class Bomb
{
    private int x = 0;
    private int y = 0;

    private boolean isAlive = true;

    private int bombWidth = 70;

    private int bombHeight = 70;

    public Bomb(int x, int y)
    {
        this.x = x - 10;
        this.y = y - 10;
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

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public int getBombWidth()
    {
        return bombWidth;
    }

    public void setBombWidth(int bombWidth)
    {
        this.bombWidth = bombWidth;
    }

    public int getBombHeight()
    {
        return bombHeight;
    }

    public void setBombHeight(int bombHeight)
    {
        this.bombHeight = bombHeight;
    }
}
