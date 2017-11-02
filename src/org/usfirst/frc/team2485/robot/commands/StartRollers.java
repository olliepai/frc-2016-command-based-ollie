package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class StartRollers extends InstantCommand {
	public StartRollers(double lateralPWM, double intakePWM) {
		requires(RobotMap.intakeRoller);
	}
	
	public void initialize() {
		
	}
}
