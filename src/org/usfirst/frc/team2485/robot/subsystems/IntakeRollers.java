package org.usfirst.frc.team2485.robot.subsystems;

import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRollers extends Subsystem {
	
	public IntakeRollers() {
		
	}
	
	public void startRollers(double lateralValue, double intakeValue) {

		RobotMap.lateralVictorSP.set(lateralValue);
		RobotMap.intakeVictorSP.set(intakeValue);

	}
	
	public void stopRollers() {

		startRollers(0, 0);

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
