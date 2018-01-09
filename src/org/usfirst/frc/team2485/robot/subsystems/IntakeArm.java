package org.usfirst.frc.team2485.robot.subsystems;


import org.usfirst.frc.team2485.robot.ConstantsIO;
import org.usfirst.frc.team2485.robot.RobotMap;
import org.usfirst.frc.team2485.robot.WarlordsPIDController;
import org.usfirst.frc.team2485.robot.commands.IntakeArmWithControllers;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeArm extends Subsystem {
	
	public static final double ABSOLUTE_TOLERANCE = 0.01;

	public static double FLOOR_POSITION = 0.122,
			
	LOW_NO_INTAKE_POSITION = (FLOOR_POSITION + 0.06) % 1,
			INTAKE_POSITION = (FLOOR_POSITION + 0.09) % 1,
			PORTCULLIS_POSITION = (FLOOR_POSITION + 0.261) % 1,
			FULL_UP_POSITION = (FLOOR_POSITION + 0.320) % 1;
	
	public static final double INTAKE_THRESHOLD = 0.1;
	
	public WarlordsPIDController armPID = new WarlordsPIDController();
	
	public IntakeArm() {
		armPID.setSources(RobotMap.absEncoder);
		armPID.setOutputs(RobotMap.intakeVictorSP);
		armPID.setPID(ConstantsIO.kP_IntakeArm, ConstantsIO.kI_IntakeArm, ConstantsIO.kD_IntakeArm);
		armPID.setContinuous(true);
		armPID.setInputRange(0, 1);
		armPID.setOutputRange(-1, 1);
	}
	
	public void setSetpoint(double setpoint) {
		if (!armPID.isEnabled()) {
			armPID.enable();
		}
		armPID.setSetpoint(setpoint);
		
	}
	
	public void setManual(double pwm) {
		if (armPID.isEnabled()) {
			armPID.disable();
		}
		if (Math.abs(pwm) <= INTAKE_THRESHOLD) {
			pwm = 0;
		} else {
			pwm = pwm;
		}

		double encoderPos = RobotMap.absEncoder.get();

		// safeguards to prevent manually driving into ground or robot
		boolean disableDownwards = false;
		if (FLOOR_POSITION > 0.1) {
			if (encoderPos < FLOOR_POSITION
					&& Math.abs(encoderPos - FLOOR_POSITION) < 0.1) {
				disableDownwards = true;
			}
		} else {
			if (encoderPos < FLOOR_POSITION
					|| Math.abs((encoderPos - 1) - FLOOR_POSITION) < 0.1) {
				disableDownwards = true;
			}
		}

		boolean disableUpwards = false;
		if (FULL_UP_POSITION < 0.9) {
			if (encoderPos > FULL_UP_POSITION
					&& Math.abs(encoderPos - FULL_UP_POSITION) < 0.1) {
				disableUpwards = true;
			}
		} else {
			if (encoderPos > FULL_UP_POSITION
					|| Math.abs((encoderPos + 1) - FULL_UP_POSITION) < 0.1) {
				disableUpwards = true;
			}
		}

		if (pwm < 0) {
			if (!disableDownwards) {
				RobotMap.armWrapper.set(pwm);
			} else {
				RobotMap.armWrapper.set(0);
			}
		} else {
			if (disableUpwards) {
				RobotMap.armWrapper.set(0);
			} else {
				RobotMap.armWrapper.set(pwm);
			}
		}
	}
	
	public double getCurrentPosition() {
		return RobotMap.absEncoder.get();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new IntakeArmWithControllers());
	}
}
