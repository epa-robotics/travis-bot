/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.StopShooter;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public SpeedController conveyor;
  public SpeedController flywheel;

  public Shooter(RobotMap robotMap) {
    conveyor = robotMap.conveyor;
    flywheel = robotMap.flywheel;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new StopShooter(this));
  }
}
