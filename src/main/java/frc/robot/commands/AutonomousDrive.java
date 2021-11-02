/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HoodSubsystem;

import com.revrobotics.CANSparkMax;

//import frc.robot.subsystems.HoodSubsystem;
import edu.wpi.first.networktables.*;

public class AutonomousDrive extends CommandBase {
  HoodSubsystem hood;
  double tx;
  double ty;
  double tv;
  double distanceError;
  double headingError;
  private final DriveSubsystem driveSubsystem;
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless);
  public CANSparkMax shooterMotor = new CANSparkMax(Constants.shooterMotorDeviceID,MotorType.kBrushless);
  public int t = 0;
  /**
   * Creates a new AutonomousDrive.
   */
  public AutonomousDrive(DriveSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveSubsystem = subsystem;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    
    //sets the heading error to the opposite of the tx value of the limelight
    headingError = -tx;
    distanceError = Constants.position1 - ty;
    //proportional scaling of the move and turn variables
    double turn = headingError * Constants.kPAim;
    double move = distanceError * Constants.kPDistance;
    //ensures that move and turn do not exceed the maximum
    double testMaxMove = Constants.maxMove;
    double testMaxTurn = Constants.maxTurn;

    if(move > testMaxMove && move > 0) //go forward at 0.3 speed
    {
      move = testMaxMove;
    }
    else if (move < 0 && move < (-1*testMaxMove)) //go backwards at 0.3 speed
    {
      move = -1 * testMaxMove;
    }
    if(turn > testMaxTurn && turn > 0){
      turn = testMaxTurn;
    }
    else if (turn < 0 && turn < (-1 * testMaxTurn))
    {
      turn = -1 * testMaxTurn;
    }
    driveSubsystem.autoAlignDrive(move, turn);
    t++;
    if (t > 50 && t < 400){
      shooterMotor.set(Constants.shooterMotorSpeed);
    }else if(t > 100 && t < 400){
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }else if( t > 400){
      shooterMotor.set(0);
      conveyorMotor.set(0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted){
      driveSubsystem.autoAlignDrive(0, 0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(headingError) < Constants.minHeadingError && Math.abs(distanceError) < Constants.minDistanceError);
  }
}
