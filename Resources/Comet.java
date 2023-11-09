/**
 * Comet object that has eliptic orbit
 * @author Maskym Yahnyshchak
 */
public class Comet extends SpaceObject{
    // distance from the centrum to the sun
    private double centrumDistance;
    // angle of centrum on the sun orbit
    private double centrumAngle;
    // velocity of comet
    private double velocity;
    // angle of the comet on its orbit
    private double orbitAngle;
    // a value of the ellipse orbit 
    private double orbitA;
    // b value of the ellipse orbit
    private double orbitB;

    /**
     * Comet constructor
     * @param s solar system
     * @param centrumX x of the centrum
     * @param centrumY y of the centrum
     * @param orbitAngle angle of the comet on its orbit
     * @param orbitA a value of the ellipse orbit
     * @param orbitB b value of the ellipse orbit
     * @param angle angle of the object on the orbit
     * @param size size of the commet
     * @param velocity velocity of the comet
     * @param col color of the comet
     */
    public Comet( SolarSystem s, double centrumX, double centrumY, double orbitAngle, double orbitA, double orbitB, double angle, double size, double velocity, String col ){
        super(s, size, col );
        this.velocity = velocity;
        this.orbitAngle = orbitAngle;
        this.orbitA = orbitA;
        this.orbitB = orbitB;
        this.angle = angle;
        if (centrumX != 0){
            this.centrumDistance = Math.sqrt(centrumX*centrumX + centrumY*centrumY);
            this.centrumAngle = Math.atan2(centrumY,centrumX) * (180/Math.PI);
        }
        else{
            this.centrumDistance = Math.abs(centrumY);
            this.centrumAngle = centrumY >= 0 ? 90 : -90;
        }
    }


    /**
     * Function to update the position of the comet.
     */
    public void update(){
        this.angle = (this.angle + (double)(this.velocity / Config.rotatingSpeed)) % 360; 
        double angleRadians = this.angle * (Math.PI/180);
        double elipseRotationRadians = this.orbitAngle * (Math.PI/180);
        double d = (orbitA*orbitB)/Math.sqrt(Math.pow((orbitB*Math.cos(angleRadians-elipseRotationRadians)),2) + Math.pow((orbitA*Math.sin(angleRadians-elipseRotationRadians)),2));

        s.drawSolarObjectAbout(d, this.angle, this.size, this.color, centrumDistance, centrumAngle);       
    }
}

