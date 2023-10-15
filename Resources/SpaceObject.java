public abstract class SpaceObject {
    protected SolarSystem s;
    protected double distance;
    protected double angle;
    protected double size;
    protected double velocity;
    protected String color;

    public SpaceObject(SolarSystem s, double size, String col ){
        this.s = s;
        this.distance = 0.0;
        this.angle = 0.0;
        this.size = size;
        this.color = col;
    }

    public SpaceObject(SolarSystem s, double distance, double angle, double size, double velocity, String col ){
        this.s = s;
        this.distance = distance;
        this.angle = angle;
        this.size = size;
        this.color = col;
        this.velocity = velocity;
    }

    public abstract void update();

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

    public double getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
