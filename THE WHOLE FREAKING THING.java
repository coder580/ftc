package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "robot in java", group = "")
public class robot extends LinearOpMode {
  
  static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    // Define class members
    Servo   servo;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;

  private DcMotor motor_right;
  private DcMotor motor_left;
  private DcMotor claw;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    servo = hardwareMap.get(Servo.class, "CharlesLee");

        // Wait for the start button
        telemetry.addData(">", "Press Start to scan Servo." );
        telemetry.update();
        waitForStart();

    double claw_on;

    motor_right = hardwareMap.dcMotor.get("motor_right");
    motor_left = hardwareMap.dcMotor.get("motor_left");
    claw = hardwareMap.dcMotor.get("claw");

    // You will have to determine which motor to reverse for your robot.
    // In this example, the right motor was reversed so that positive
    // applied power makes it move the robot in the forward direction.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        // The Y axis of a joystick ranges from -1 in its topmost position
        // to +1 in its bottommost position. We negate this value so that
        // the topmost position corresponds to maximum forward power.
        telemetry.addData("Left Pow", motor_left.getPower());
        telemetry.addData("Right Pow", motor_right.getPower());
        telemetry.update();
       
        if (gamepad1.right_trigger == 1) {
          motor_left.setDirection(DcMotorSimple.Direction.REVERSE);
          motor_right.setDirection(DcMotorSimple.Direction.REVERSE);
          motor_left.setPower(1);
          motor_right.setPower(1);
        } else if (gamepad1.left_trigger == 1) {
          motor_left.setDirection(DcMotorSimple.Direction.FORWARD);
          motor_right.setDirection(DcMotorSimple.Direction.FORWARD);
          motor_left.setPower(1);
          motor_right.setPower(1);
        } else {
        motor_left.setPower(gamepad1.left_stick_y);
        motor_right.setPower(gamepad1.right_stick_y);
        motor_right.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_left.setDirection(DcMotorSimple.Direction.FORWARD);
        {
        
          if (gamepad1.y) {
            claw.setDirection(DcMotorSimple.Direction.FORWARD);
            claw.setPower(1);
          } else if (gamepad1.a) {
            claw.setDirection(DcMotorSimple.Direction.REVERSE);
            claw.setPower(1);
          } else {
            claw.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            claw.setPower(0);
          }
        
      }
    }
    // slew the servo, according to the rampUp (direction) variable.
            if (gamepad1.left_bumper) {
                position += INCREMENT ;
                if (position >= MAX_POS ) {
                    position = MAX_POS;
                    rampUp = !rampUp;   // Switch ramp direction
                }
            }
            else {
                if (gamepad1.right_bumper)
                position -= INCREMENT ;
                if (position <= MIN_POS ) {
                    position = MIN_POS;
                    rampUp = !rampUp;  // Switch ramp direction
                }
            }

            // Display the current value
            telemetry.addData("Servo Position", "%5.2f", position);
            telemetry.update();

            servo.setPosition(position);
            
        if (motor_left.getPower() == 0) {
          motor_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);        
        }
        if (motor_right.getPower() == 0) {
          motor_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);        
        }
  }}}}
