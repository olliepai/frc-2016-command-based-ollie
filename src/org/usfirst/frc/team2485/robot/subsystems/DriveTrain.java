package org.usfirst.frc.team2485.robot.subsystems;

import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private static final double THROTTLE_DEADBAND = 0.1;
	private static final double STEERING_DEADBAND = 0.1;
	
	public DriveTrain() {
		RobotMap.leftDriveEncoder.reset();
		RobotMap.rightDriveEncoder.reset();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static void setLeftRight(double leftPWM, double rightPWM) {
		RobotMap.leftDriveWrapper.set(leftPWM);
		RobotMap.rightDriveWrapper.set(rightPWM);
	}
	
	public static void driveWithController(double throttle, double steering) {
		if (Math.abs(throttle) <= THROTTLE_DEADBAND) {
			throttle = 0;
		}
		
		if (Math.abs(steering) <= STEERING_DEADBAND) {
			steering = 0;
		}
		
		double leftPWM = throttle * (1 + steering);
		double rightPWM = throttle * (1 - steering);
		
		if (leftPWM > 1) {
			rightPWM /= leftPWM;
			leftPWM /= leftPWM;
		}
		
		if (rightPWM > 1) {
			leftPWM /= rightPWM;
			rightPWM /= rightPWM;
		}
		
		setLeftRight(leftPWM, rightPWM);
		
	
	}

}
