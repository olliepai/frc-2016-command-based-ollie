package org.usfirst.frc.team2485.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static VictorSP[] rightDriveVictorSPs;
	public static VictorSP[] leftDriveVictorSPs;

	public static Encoder rightDriveEncoder;
	public static Encoder leftDriveEncoder;

	public static SpeedControllerWrapper rightDriveWrapper;
	public static SpeedControllerWrapper leftDriveWrapper;
	
	public static void init() {
		// TODO Auto-generated method stub
		rightDriveVictorSPs = new VictorSP[] {new VictorSP(5), new VictorSP(6), new VictorSP(7)};
		leftDriveVictorSPs = new VictorSP[] {new VictorSP(2), new VictorSP(3), new VictorSP(4)};
		
		rightDriveWrapper = new SpeedControllerWrapper(rightDriveVictorSPs);
		leftDriveWrapper = new SpeedControllerWrapper(leftDriveVictorSPs);
		
		rightDriveEncoder = new Encoder(4, 5);
		leftDriveEncoder = new Encoder(2, 3);

	}
}
