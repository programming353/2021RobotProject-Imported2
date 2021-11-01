/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BallTransitSubsystem extends SubsystemBase {
  /**
   * Creates a new BallTransitSystem.
   */
  public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorDeviceID,MotorType.kBrushless);
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless);
  public CANSparkMax shooterMotor = new CANSparkMax(Constants.shooterMotorDeviceID,MotorType.kBrushless);
  public CANEncoder shooterMotorEncoder = shooterMotor.getEncoder();
  
  public DigitalInput shooterSensor = new DigitalInput(Constants.shooterLimitSwitch);
  public DigitalInput intakeSensor = new DigitalInput(Constants.intakeSensorNumber);
  //public DigitalInput conveyorSensor = new DigitalInput(Constants.conveyorSensorNumber);

  public boolean intakeIn;
  public boolean intakeOut;
  public boolean shooterRunning;
  public int countTime;
  public boolean shooterReverse;


  public BallTransitSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
     boolean intakeBtn = RobotContainer.operatorStick.getRawButton(Constants.intakeButtonNumber);
     boolean outtakeBtn = RobotContainer.operatorStick.getRawButton(Constants.outtakeButtonNumber);
     boolean conveyorUpBtn = RobotContainer.operatorStick.getRawButton(Constants.conveyorUpButtonNumber);
     boolean conveyorDownBtn = RobotContainer.operatorStick.getRawButton(Constants.conveyorDownButtonNumber);
     boolean shootBtn = RobotContainer.operatorStick.getRawButton(Constants.shootButtonNumber);
     boolean shootPresssed = RobotContainer.operatorStick.getRawButtonPressed(Constants.shootButtonNumber);
     boolean shootClear = RobotContainer.operatorStick.getRawButton(Constants.shooterClearButtonNumber);
    
      runIntake(intakeBtn,outtakeBtn);
    

    //if(shootBtn){
      runShooter(shootBtn,shootClear);
    //}

    if(conveyorUpBtn){ // these top 2 are simple conditional for if button for conveyor is pressed
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }
    else if(conveyorDownBtn){
      conveyorMotor.set(Constants.conveyorMotorSpeed*-1);
    }
    else if(intakeIn){ // these next three respond to global querries to run conveyor, could be ors but style
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }
    else if(intakeOut){
      conveyorMotor.set(Constants.conveyorMotorSpeed*-1);
    }
    else if(shooterRunning){
      conveyorMotor.set(Constants.conveyorMotorSpeed);
    }
    else if(shooterReverse){
      conveyorMotor.set(Constants.conveyorMotorSpeed*-1);
    }
    else{
        conveyorMotor.set(0);
    }
  /*
  if(intakeBtn){
    intakeMotor.set(Constants.intakeMotorSpeed);
  }
  else if(outtakeBtn){
    intakeMotor.set(Constants.intakeMotorSpeed*-1);
  }
  else{
    intakeMotor.set(0);
  }
  */
}
  
public void runIntake(boolean intakeBtn,  boolean outtakeBtn){
  if (intakeBtn == true ){//&& conveyorSensor.get() == false
    intakeMotor.set(Constants.intakeMotorSpeed);
    if(true){  //if(intakeSensor.get() == true){ // Removed for limit switch concerns on 2/29 ~CR
      intakeIn = true;
      intakeOut = false;
    }
  }
  else if (outtakeBtn == true){
    intakeMotor.set(-Constants.conveyorMotorSpeed);
    intakeIn = false;
    intakeOut = true;
  }
  else{
    intakeMotor.set(0);
    intakeIn = false;
    intakeOut = false;
  }
}
  public void runShooter (boolean shootBtn, boolean shootClear){
   
     /* //countTime++;
      //if (countTime > 50){
        shooterMotor.set(Constants.shooterMotorSpeed);
        if (false){//if (shooterSensor.get() == true){
          shooterRunning = false;
        }
        else{
          shooterRunning = true;
        }
      //}
      
      else{
        shooterReverse = true;
      }*/
      //shooterMotorEncoder.setVelocityConversionFactor(factor)

   /* if (shootBtn== true && shooterMotorEncoder.getVelocity() >= .1){
      shooterReverse = false;
      shooterRunning = true;
      shooterMotor.set(Constants.shooterMotorSpeed);
    }*/
   if (shootBtn == true){ // && shooterMotorEncoder.getVelocity() < .1
      //if (shootPresssed == false){
      shooterMotor.set(Constants.shooterMotorSpeed);
      //}
      //shooterReverse = !true;
      //shooterRunning = !false;
    }
    else if (shootClear){
      shooterMotor.set(Constants.shooterMotorSpeed*-1);
    }
    else{
      shooterRunning = false;
      shooterMotor.set(0);
      shooterReverse = false;
    }
  




}
}

