/** 
 * This class represents abstract Space object. It implements Runnable so it can be used as a Thread parameter.
 * @author Maksym Yahnyshchak
 */
public abstract class SpaceObject implements Runnable {
    protected SolarSystem s;
    protected double distance;
    protected double angle;
    protected double size;
    protected String color;

    /**
     * Constructor of space object. Used for objects that do not orbit around others(e.g Sun)
     * @param s SolarSystem object
     * @param size Diameter of the object
     * @param col Color of the object. Can be string e.g "WHITE" or RGB "#463FBD"
     */
    public SpaceObject(SolarSystem s, double size, String col ){
        this.s = s;
        this.distance = 0.0;
        this.angle = 0.0;
        this.size = size;
        this.color = col;
    }

    /**
     * Constructor of space object.
     * @param s SolarSystem object
     * @param distance Distance from centrum to the planet.
     * @param angle Starting angle of planet on orbit
     * @param size Diameter of the object
     * @param velocity Velocity of its movement around orbit.
     * @param col Color of the object. Can be string e.g "WHITE" or RGB "#463FBD"
     */
    public SpaceObject(SolarSystem s, double distance, double angle, double size, String col ){
        this.s = s;
        this.distance = distance;
        this.angle = angle;
        this.size = size;
        this.color = col;
    }

    /**
     * Function that should be implemented by child classes to update image and postion of object.
     */
    public abstract void update();

    /**
     * Runnable implementation.
     */
    public void run(){ this.update(); }
    

    public SolarSystem getSolarSystem(){
        return this.s;
    }

    public void setSolarSystem(SolarSystem s){
        this.s = s;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAngle() {
        return this.angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
