/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoCannon;

public class IntakeUntilCargoReachesFlywheel extends Command {
  private CargoCannon _cargoCannon;
  public IntakeUntilCargoReachesFlywheel(CargoCannon cargoCannon) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(cargoCannon);
    _cargoCannon = cargoCannon;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // it should never take longer than 3 seconds total to shoot
    _cargoCannon.startConveyorIntake();
    setTimeout(5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // // Once the ball is no longer detected, give it some seconds to exit the flywheel
    if (!_cargoCannon.hasCargoInHoldingArea()) {
      setTimeout(2);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    _cargoCannon.stopConveyor();
    _cargoCannon.stopFlywheel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
