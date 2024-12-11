// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static final int kMotorID = 5;

  public static CANSparkMax m_motor;

  public ExampleSubsystem() {
    m_motor = new CANSparkMax(kMotorID, CANSparkLowLevel.MotorType.kBrushed);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand(double val) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
          m_motor.set(val);
        });
  }

  public Command newCommand() {
    return Commands.startEnd(
      () -> m_motor.set(0.5), 
      () -> m_motor.set(0.0), 
      this).withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public Command runMotorWithTrigger() {
    return new RunCommand(
      //() -> m_motor.set(RobotContainer.m_driverController.getLeftTriggerAxis()),
      () -> {
        double speed = RobotContainer.m_driverController.getLeftTriggerAxis();
        System.out.println(speed);
        m_motor.set(speed);
      },
      this);
  }  

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
