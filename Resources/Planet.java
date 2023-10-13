

public class Planet extends SpaceObject{
    private SpaceObject c;
    
    public Planet(SpaceObject centrum, int distance, int angle, int size, String col){
        this.c = centrum;
        this.distance = distance;
        this.angle = angle;
        this.size = size;
        this.color = col;
    }

}
