public class Star extends SpaceObject {
    
    public Star(SolarSystem s, double size, String col ){
        super( s, size, col );
    }

    public void draw(){
        s.drawSolarObject(this.distance, this.angle, this.size, this.color);
    }

}
