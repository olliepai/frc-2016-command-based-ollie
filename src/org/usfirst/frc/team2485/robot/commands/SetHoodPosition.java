package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.RobotMap;
import org.usfirst.frc.team2485.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetHoodPosition extends InstantCommand {
	private Shooter.HoodPosition hoodPosition;
	
	public SetHoodPosition(Shooter.HoodPosition hoodPosition) {
		this.hoodPosition = hoodPosition;

	}
	
	public void initialize() {
		RobotMap.shooter.setHoodPosition(hoodPosition);
	}
}
