/** 
 * This class represents Moon object and is child of SpaceObject.
 * @author Maksym Yahnyshchak
 */
public class Planet extends SpaceObject{
    protected SpaceObject centrum;
    protected double velocity;
    
    /**
     * Constructor of the planet class
     * @param s SolarSystem object 
     * @param centrum Space object on whose orbit this planet is.
     * @param distance Distance from centrum to the planet.
     * @param angle Starting angle of planet on orbit
     * @param size Diameter of the planet
     * @param velocity Velocity of its movement around orbit.
     * @param col Color of this planet. Can be string e.g "WHITE" or RGB "#463FBD"
     */
    public Planet(SolarSystem s, SpaceObject centrum, double distance, double angle, double size, double velocity ,String col ){
        super(s, distance, angle, size, col);
        this.centrum = centrum;
        this.velocity = velocity;
    }

    /**
     * Updates position of the object according to velocity.
     */
    @Override public void update(){
        this.angle += (double)(this.velocity / Config.rotatingSpeed); 
        if (this.angle >= 360) { this.angle -= 360; }
        
        s.drawSolarObjectAbout(this.distance, this.angle, this.size, this.color, centrum.getDistance(), centrum.getAngle());
    }

    public SpaceObject getCentrum() {
        return this.centrum;
    }

    public void setCentrum(SpaceObject centrum) {
        this.centrum = centrum;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
}
