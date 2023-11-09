/** 
 * This class represents Star object and is child of SpaceObject.
 * @author Maksym Yahnyshchak
 */
public class Star extends SpaceObject {
    
    /**
     * Constructor of Star object.
     * @param s SolarSystem object
     * @param size diameter of the star
     * @param col color of the star
     */
    public Star(SolarSystem s, double size, String col ){
        super( s, size, col );
    }

    /**
     * Updates image of the star.
     */
    @Override public void update(){
        s.drawSolarObject(this.distance, this.angle, this.size, this.color);
    }

}
