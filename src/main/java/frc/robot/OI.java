package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.DriveAsTank;
import frc.robot.commands.LoadCargo;
import frc.robot.commands.ScoreHatch;
import frc.robot.commands.LoadHatch;
import frc.robot.commands.ScoreCargo;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchPlacer;
import frc.robot.subsystems.CargoCannon;
import frc.robot.tools.Gamepad;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Gamepad _driverGamepad;
  private final Gamepad _operatorGamepad;

  private final Drivetrain _drivetrain;
  private final HatchPlacer _hatchPlacer;
  private final CargoCannon _cargoCannon;
  private boolean _initializedTeleop = false;

  public OI(StationMap stationMap, RobotMap robotMap) {

    _driverGamepad = new Gamepad(stationMap.driverGamepadPort, stationMap.driverGamepadType);
    _operatorGamepad = new Gamepad(stationMap.operatorGamepadPort, stationMap.operatorGamepadType);

    _drivetrain = new Drivetrain(robotMap);
    _hatchPlacer = new HatchPlacer(robotMap);
    _cargoCannon = new CargoCannon(robotMap);

    // Show the subsystems in a tab named "Subsystems" on Shuffleboard
    // so we can monitor current Commands on each subsystem.
    Shuffleboard.getTab("Subsystems").add(_drivetrain);
    Shuffleboard.getTab("Subsystems").add(_hatchPlacer);
    Shuffleboard.getTab("Subsystems").add(_cargoCannon);

  }

  public void useTeleopMode() {

    if (!_initializedTeleop) {

      CameraServer.getInstance().startAutomaticCapture();

      _drivetrain.setDefaultCommand(new DriveAsTank(
        _drivetrain,
        _driverGamepad.leftYAxis,
        _driverGamepad.rightYAxis,
        _driverGamepad.rightAnalogTrigger
      ));
  
      _operatorGamepad.buttonWest.whileHeld(new LoadHatch(_hatchPlacer));
      _operatorGamepad.buttonSouth.whileHeld(new ScoreHatch(_hatchPlacer));

      _operatorGamepad.leftBumper.whileHeld(new LoadCargo(_cargoCannon));
      _operatorGamepad.rightBumper.whileHeld(new ScoreCargo(_cargoCannon));
      
      _initializedTeleop = true;

    }

  }

}
