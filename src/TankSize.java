import java.awt.Dimension;
import java.awt.Toolkit;

public interface TankSize
{
  int heightOfOutRect = 50;
  int widthOfOutRect = 12;
  int heightOfCenterRect = 30;
  int widthOfCenterRect = 22;
  int radiusOfCircle = 20;
  float strokeOfGun = 2.5f;

  Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
}
