package org.usfirst.frc.team2485.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2485.robot.ConstantsIO;
import org.usfirst.frc.team2485.robot.EncoderWrapperRateAndDistance;
import org.usfirst.frc.team2485.robot.RobotMap;
import org.usfirst.frc.team2485.robot.WarlordsPIDController;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public WarlordsPIDController ratePID;
	
	
	public static enum HoodPosition {
		LOW_ANGLE, HIGH_ANGLE, STOWED
	};

	private static final HoodPosition DEFAULT_HOOD_POSITION = HoodPosition.HIGH_ANGLE;

	private HoodPosition currHoodPosition;

	public Shooter() {
		currHoodPosition = DEFAULT_HOOD_POSITION;
		

		ratePID = new WarlordsPIDController();
		ratePID.setSources(RobotMap.shooterEncoder);
		ratePID.setOutputs(RobotMap.shooterWrapper);
		ratePID.setOutputRange(0, 1);
		ratePID.setPID(ConstantsIO.kP_Shooter, ConstantsIO.kI_Shooter, ConstantsIO.kD_Shooter, ConstantsIO.kF_Shooter);
		
		disableShooter();
	}
	
	public boolean isPIDEnabled() {
		return ratePID.isEnabled();
	}
	
	public void setRate() {
		if (!isPIDEnabled()) {
			ratePID.enable();
		}
		
		ratePID.setSetpoint(ConstantsIO.kShotRPS);
	}

	public void setHoodPosition(final HoodPosition newHoodPosition) {

		if (newHoodPosition == HoodPosition.LOW_ANGLE) {
			if (currHoodPosition == HoodPosition.HIGH_ANGLE) {
				RobotMap.upperSolenoid.set(true); // This should extend the upper piston
			} else if (currHoodPosition == HoodPosition.STOWED) {
				RobotMap.lowerSolenoid.set(false); // Retracting the lower piston pulls open the shooter

				RobotMap.upperSolenoid.set(true);
			}
		} else if (newHoodPosition == HoodPosition.HIGH_ANGLE) {
			if (currHoodPosition == HoodPosition.LOW_ANGLE) {
				RobotMap.upperSolenoid.set(false);

			} else if (currHoodPosition == HoodPosition.STOWED) {
				RobotMap.lowerSolenoid.set(false);
			}
		} else { // setting to stowed

			if (currHoodPosition == HoodPosition.LOW_ANGLE) {

				RobotMap.upperSolenoid.set(false);

				RobotMap.lowerSolenoid.set(true);

			} else if (currHoodPosition == HoodPosition.HIGH_ANGLE) {
				RobotMap.lowerSolenoid.set(true);
			}
		}

		currHoodPosition = newHoodPosition;

	}

	public void resetHood() {
		setHoodPosition(DEFAULT_HOOD_POSITION);
	}

	public HoodPosition getHoodPosition() {
		return currHoodPosition;
	}

	public void setPWM(double pwm) {
		if (isPIDEnabled()) {
			ratePID.disable();
		}
		
		RobotMap.shooterWrapper.set(pwm);
	}

	public void disableShooter() {
		setPWM(0);
	}

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
