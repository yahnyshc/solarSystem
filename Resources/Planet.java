

public class Planet extends SpaceObject{
    protected SpaceObject centrum;
    
    public Planet(SolarSystem s, SpaceObject centrum, double distance, double angle, double size, double velocity ,String col ){
        super(s, distance, angle, size, velocity, col);
        this.centrum = centrum;
    }

    public void move(){
        this.angle += (double)(this.velocity / Config.rotatingSpeed); 
        if (this.angle >= 360) { this.angle -= 360; }
        
        s.drawSolarObjectAbout(this.distance, this.angle, this.size, this.color, centrum.getDistance(), centrum.getAngle());
    }

}
