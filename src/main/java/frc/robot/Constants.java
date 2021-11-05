/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

	//Computer outlets are called Port
	//CANSparkMax motors are called DeviceID
	//Sensors placements are called Number
	//Buttons on the joysticks are called Number

	//Joystick and controller port constants
	public static int driverStickPort = 0;
	public static int operatorStickPort = 1;

	//CANSparkMax Device ID constants
	public static int leftFrontMotorDeviceID = 4; 
	public static int leftRearMotorDeviceID = 2;
	public static int rightFrontMotorDeviceID = 1;
	public static int rightRearMotorDeviceID = 3;
	public static int intakeMotorDeviceID = 6;
	public static int conveyorMotorDeviceID = 7;
	public static int shooterMotorDeviceID = 8;
	public static int climberMotorDeviceID = 5;
	public static int colorWheelDeviceID = -1;
	public static int hoodMotorDeviceID = 9;

	//CANSParkMax Motor Speeds
	public static double intakeMotorSpeed = 0.5;
	public static double conveyorMotorSpeed = 0.6;
	public static double shooterMotorSpeed = -0.95;
	public static double climberMotorSpeed = 0.3;
	public static double colorWheelMotorSpeed = 0.1;

	//Spike Relay Number constants
	//public static int intakeSpikeNumber = 1;

	//Sensor number constants
	public static int intakeSensorNumber = 1;
	public static int conveyorSensorNumber = -1;
	public static int shooterSensorNumber = -1;
	public static int colorSensorNumber = -1;
	 
	//Driver button constants
	public static int turboButtonNumber = 2;
	public static int slowButtonNumber = 3;
	public static int AutoAlignButtonNumber = 1;
	public static int magicTurnButtonNumber = 4;
	public static int inverseJoyStickControl = 16;

	//Operator button constants
	public static int shootButtonNumber = 1;
	public static int intakeButtonNumber = 7;
	public static int outtakeButtonNumber = 8;
	public static int conveyorUpButtonNumber = 6;
	public static int conveyorDownButtonNumber = 9;
	public static int climberUpButtonNumber = 4;			
	public static int dropIntakeButtonNumber = 16;
	public static int hoodToggleBtnNum = 2;
	public static int hoodRunBtnNum = 3;
	public static int AutoColorButtonNumber = -1;
	public static int manualColorButtonNumber = -1;
	public static int shooterClearButtonNumber = 10;


	//Constants for angular and linear alignment within AlignRobotCommand()
	public static double kPAim = 0.075;
	public static double kPDistance = 0.15;
	public static double maxMove = 0.30; // Speed of auto move
	public static double maxTurn = 0.20;
	public static double minHeadingError = 0.25;
	public static double minDistanceError = 0.50;
	public static double position1 = -5.0;
	public static double position2 = 0.0;
	public static double position3 = 5.0;
	// Constants to angle the hood
	public static double hoodGear = 175;
	public static double kPHoodAim = 0.294;
	
	public static double hoodLSRLA = 1; //represents coefficient of x ^ 2
	public static double hoodLSRLB = 1; //represents coefficient of x
	public static double hoodLSRLC = 1; //represents constant
	public static double hoodAngle1 = 0.000;
	public static double hoodAngle2 = 0.125 * hoodGear;
	public static double hoodAngle3 = 0.167 * hoodGear;

	//Constants for driving modes within ManualDriveCommand()
	public static double turboScale = 1.00; //faster speed for the robot's drivetrain
	public static double slowScale = 0.5; //Slower speed for the robot's drivetrain
	public static double driverScale = 0.86; //Original speed for the robot's drivetrain
	public static double nadavsSexyTurnNumber = 0.93; //sorry for the name salil <3 ~CR

	//Constants for Solenoids within DropIntakeSubsystem()
	public static double solenoidHoldTime = 2.0;
	
	//Constants for limit switch
	public static int shooterLimitSwitch=0;


}
