package team1699.subsystem;

import static org.junit.Assert.*;
import org.junit.*; //TODO Remove * import
import team1699.utils.MotorConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import team1699.subsystem.ShooterSimulator;

public class ShooterSimTest{

    private ShooterSimulator shooterSim;

    @Before
    public void setupTest(){
        shooterSim = new ShooterSimulator(MotorConstants.MotorFalcon.Kv, MotorConstants.MotorFalcon.Kt, 1.0, 1.0, MotorConstants.MotorFalcon.kResistance);
    }

    @Test
    public void runModelWithConstantVoltage(){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new File("shooterDump.csv"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return; //Couldn't open the file so exit
        }
        pw.write("t, acceleration, velocity\n");
        final double dT = 0.01;
        for(double i = 0.0; i < 60.0; i = i + dT){
            double voltage = 12.0;
            shooterSim.simulateTimeV(dT, voltage);
            pw.write(String.format("%f,%f,%f\n", i, shooterSim.getAcceleration(voltage), shooterSim.getVelocity()));
            pw.flush();
        }
        pw.close();
    }
}
