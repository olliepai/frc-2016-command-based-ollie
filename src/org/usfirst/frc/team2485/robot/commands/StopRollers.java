package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopRollers extends InstantCommand{
	public StopRollers() {
		requires(RobotMap.intakeRollers);
	}
	
	public void initialize() {
		RobotMap.intakeRollers.stopRollers();
	}
}
