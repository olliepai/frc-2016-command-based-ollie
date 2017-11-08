package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.OI;
import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeArmWithControllers extends Command {
	public IntakeArmWithControllers() {
		requires(RobotMap.intakeArm);
		setInterruptible(true);
	}
	
	public void execute() {
		double pwm = OI.operator.getRawAxis(OI.XBOX_AXIS_LY);
		RobotMap.intakeArm.setManual(pwm);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
