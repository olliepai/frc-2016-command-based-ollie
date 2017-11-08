package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class StartRollers extends InstantCommand {
	private double lateralPWM;
	private double intakePWM;
	
	public StartRollers(double lateralPWM, double intakePWM) {
		this.lateralPWM = lateralPWM;
		this.intakePWM = intakePWM;
		
		requires(RobotMap.intakeRollers);
	}
	
	public void initialize() {
		RobotMap.intakeRollers.startRollers(lateralPWM, intakePWM);
	}
}
