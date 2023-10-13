

public class Planet extends SpaceObject{
    protected SpaceObject centrum;
    private double rotatingSpeed = 30.0;
    
    public Planet(SolarSystem s, SpaceObject centrum, double distance, double angle, double size, double velocity ,String col ){
        super(s, distance, angle, size, velocity, col);
        this.centrum = centrum;
    }

    public void move(){
        this.angle += (double)(this.velocity / rotatingSpeed); 
        if (this.angle >= 360) { this.angle -= 360; }
        
        s.drawSolarObjectAbout(this.distance, this.angle, this.size, this.color, centrum.getDistance(), centrum.getAngle());
    }

}
