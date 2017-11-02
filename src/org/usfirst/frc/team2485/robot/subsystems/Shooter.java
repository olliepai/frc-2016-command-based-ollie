package org.usfirst.frc.team2485.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team2485.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public static enum HoodPosition {
		LOW_ANGLE, HIGH_ANGLE, STOWED
	};

	private static final HoodPosition DEFAULT_HOOD_POSITION = HoodPosition.HIGH_ANGLE;

	private HoodPosition currHoodPosition;

	public Shooter() {
		currHoodPosition = DEFAULT_HOOD_POSITION;

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

		RobotMap.shooterWrapper.set(pwm);

	}

	public void disableShooter() {

		RobotMap.shooterWrapper.emergencyStop();

	}

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
