package org.usfirst.frc.team2485.robot.commands;

import org.usfirst.frc.team2485.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetShooter extends InstantCommand {
	private double pwm;
	
	public SetShooter(double pwm) {
		this.pwm = pwm;
		requires(RobotMap.shooter);
	}
	
	public void initialize() {
		RobotMap.shooter.setPWM(pwm);
	}
}
