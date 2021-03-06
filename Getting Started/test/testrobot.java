import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;

public class testrobot extends SampleRobot 
{
	CANJaguar frontLeft;
	CANJaguar backLeft;
	CANJaguar frontRight;
	CANJaguar backRight;
	RobotDrive mainrobot;
	Joystick joystick1;
	Compressor comp1;
	Joystick joystick2;
	Joystick controller;
	// CANTalon motor order is frontLeft, backLeft, frontRight, backRight
	TalonSRX shooter;
	Timer timer;
	
	public testrobot() 
	{
		comp1 = new Compressor(0);
		frontLeft = new CANJaguar(0);
		backLeft = new CANJaguar(1);
		frontRight = new CANJaguar(2);
		backRight = new CANJaguar(3);
		mainrobot = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
		// RobotDrive requires motor order of CANTalon
		joystick1 = new Joystick(0);
		joystick2 = new Joystick(0);
		controller = new Joystick(0);
		timer = new Timer();
		shooter = new TalonSRX(0);
		
	}

	public void operatorControl() 
	{

		boolean buttonFlag = false;
		//double X1 = 0;
		double Y1 = 0;
		double Z1 = 0;
		while (isOperatorControl() && isEnabled()) 
		{

			if (joystick1.getY() > 0.15) 
			{
				Y1 = (joystick1.getY() - 0.15) / 0.85;
			} 
			
			else if (joystick1.getY() < -0.15) 
			{
				Y1 = (joystick1.getY() + 0.15) / 0.85;
			}
			
			else 
			{
				Y1 = 0;
			}
			// Sets dead zone for y, scales rest of percentages

			if (joystick1.getZ() > 0.15) 
			{
				Z1 = (joystick1.getZ() - 0.15) / 0.85;
			} 
			
			else if (joystick1.getZ() < -0.15) 
			{
				Z1 = (joystick1.getZ() + 0.15) / 0.85;
			}
			
			else 
			{
				Z1 = 0;
			}
			// Sets dead zone for x, scales rest of percentages

			mainrobot.arcadeDrive(Y1, Z1, false);
			
			
			if (joystick1.getRawButton(1) == true) 
			{
				buttonFlag = !buttonFlag;
			}
			
			if (buttonFlag == true)
			{
				comp1.setClosedLoopControl(true);
			}
			
			if (buttonFlag == false)
			{
				comp1.setClosedLoopControl(false);
			}
			
			// Sets up pneumatics and a toggle for a button
		}
	}
}
