public class Comet extends SpaceObject{
    private double centrumDistance;
    private double centrumAngle;
    private double velocity;
    private double orbitAngle;
    private double orbitA;
    private double orbitB;

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

    public void update(){
        this.angle = (this.angle + (double)(this.velocity / Config.rotatingSpeed)) % 360; 
        double angleRadians = this.angle * (Math.PI/180);
        double elipseRotationRadians = this.orbitAngle * (Math.PI/180);
        double d = (orbitA*orbitB)/Math.sqrt(Math.pow((orbitB*Math.cos(angleRadians-elipseRotationRadians)),2) + Math.pow((orbitA*Math.sin(angleRadians-elipseRotationRadians)),2));

        s.drawSolarObjectAbout(d, this.angle, this.size, this.color, centrumDistance, centrumAngle);       
    }
}

