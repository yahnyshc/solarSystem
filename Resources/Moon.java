/** 
 * This class represents Moon object and is child of Planet object.
 * @author Maksym Yahnyshchak
 */
public class Moon extends Planet {

    /**
     * Constructor of the moon class
     * @param s SolarSystem object 
     * @param centrum Space object on whose orbit this moon is.
     * @param distance Distance from centrum to the moon.
     * @param angle Starting angle of moon on orbit
     * @param size Diameter of the moon
     * @param velocity Velocity of its movement around orbit.
     * @param col Color of this moon. Can be string e.g "WHITE" or RGB "#463FBD"
     */
    public Moon( SolarSystem s, SpaceObject centrum, double distance, double angle, double size, double velocity, String col ) {
        super(s, centrum, distance, angle, size, velocity, col );
    }
    
}
