package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.OI;
import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithControllers extends Command {
	public DriveWithControllers() {
		requires(RobotMap.driveTrain);
		setInterruptible(true);
	}
	
	public void execute() {
		double throttle = OI.driver.getRawAxis(OI.XBOX_AXIS_LY);
		double steering = OI.driver.getRawAxis(OI.XBOX_AXIS_RX);
		
		RobotMap.driveTrain.driveWithController(throttle, steering);

	}	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
