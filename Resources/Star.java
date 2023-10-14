public class Star extends SpaceObject implements java.lang.Runnable {
    
    public Star(SolarSystem s, double size, String col ){
        super( s, size, col );
        s.drawSolarObject(this.distance, this.angle, this.size, this.color);
    }

    public void run(){
        this.draw();
    }

    public void draw(){
        s.drawSolarObject(this.distance, this.angle, this.size, this.color);
    }

}
