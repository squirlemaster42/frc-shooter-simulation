package team1699.subsystem;

public class ShooterSimulator{

    private final double kV, kT, kG, kI, kR;
    private double velocity;
       
    public ShooterSimulator(final double kV, final double kT, final double kG, final double kI, final double kR){
        this.kV = kV;
        this.kT = kT;
        this.kG = kG;
        this.kI = kI;
        this.kR = kR;
        this.velocity = 0.0;
    }

    public void simulateTimeI(double dT, double current){
        velocity += getAcceleration(currentToVoltage(current)) * dT;
    }

    public void simulateTimeV(double dT, double voltage){
        velocity += getAcceleration(voltage) * dT;
    }

    public double getAcceleration(double voltage){
        return (voltage - (velocity/kV)) * (kT/(kG * kI));
    }

    public double currentToVoltage(double current){
        return current*kR;
    }

    public double getVelocity(){
        return velocity;
    }
}
