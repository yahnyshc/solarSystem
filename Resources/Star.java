public class Star extends SpaceObject implements java.lang.Runnable {
    
    public Star(SolarSystem s, double size, String col ){
        super( s, size, col );
        s.drawSolarObject(this.distance, this.angle, this.size, this.color);
    }

    public void run(){
        while(true){
            s.drawSolarObject(this.distance, this.angle, this.size, this.color);
            s.finishedDrawing();
            try{
                Thread.sleep(10);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        
    }



}
