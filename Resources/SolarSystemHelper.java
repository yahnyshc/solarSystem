import java.util.HashMap;
import java.awt.event.*;

/**
 * Class implementing functions to help in solar system features creation/optimization
 * @author Maksym Yahnyshchak
 */
public class SolarSystemHelper {
    // solar system object
    SolarSystem s;

    /**
     * Constructor of the solar system helper
     * @param s
     */
    SolarSystemHelper(SolarSystem s){
        this.s = s;
    }
    
    /**
     * Create asteroids belt around the centrum space object.
     * @param centrum Object we create belt around
     * @param asteroidNumber Number of moons/asteroids in the belt
     * @param beltDistance Average distance from centrum object to belt
     * @param distanceRange Array to represent limits of asteroids range of distance.
     * @param angleRanges  Array to represent limits of asteroids range of angle.
     * @param velocityRange Array to represent limits of asteroids range of velocity.
     * @param sizeRange Array to represent limits of asteroids range of size.
     * @param color color of the moons/asteroids
     * @return Array of Moon objects that represents belt.
     */
    public Moon[] createBelt( SpaceObject centrum, int asteroidNumber, double beltDistance, double[] distanceRange, double[][] angleRanges, double[] velocityRange, double[] sizeRange, String color){
        Moon[] belt = new Moon[asteroidNumber];
        // hashmap to save generated asteroids position to avoid duplicate positions
        HashMap<Integer,Integer> asteroidsPos = new HashMap<Integer,Integer>();

        int angleRange;
        double distance, angle, velocity, size;
        // Generating random asteroids/moons
        for ( int i = 0; i < asteroidNumber; i++ ){
            do{
                distance = beltDistance + (double) ((Math.random() * (distanceRange[1] - distanceRange[0])) + distanceRange[0]);
                // 360 degrees.
                angleRange = (int) (Math.random() * (angleRanges.length)); 
                angle = (double) ((Math.random() * ( angleRanges[angleRange][1] - angleRanges[angleRange][0] ) ) + angleRanges[angleRange][0]);
                velocity = (double) ((Math.random() * (velocityRange[1] - velocityRange[0])) + velocityRange[0]);
                size = (double) ((Math.random() * (sizeRange[1] - sizeRange[0])) + sizeRange[0]);
            }
            while ( asteroidsPos.containsKey((int)distance) && (int)asteroidsPos.get((int)distance) == (int)angle);
            asteroidsPos.put((int)distance, (int)angle);

            belt[i] = new Moon( this.s, centrum, distance, angle, size, velocity, color );
        }   

        return belt;
    }

    /**
     * Partition array of space objects between different threads. 
     * @param objects array of objects to partition
     * @param threadNumber number of threads to partition on
     * @param objectNumberPerThread number of objects per each thread
     * @return array of runnable threads
     */
    public Thread[] partitionSpaceObjectsOnThreads(SpaceObject[] objects, int threadNumber, int objectNumberPerThread){
        Thread [] objectsThreads = new Thread[threadNumber];
        for (int i = 0; i < threadNumber; i++){
            final int partition = i;
            objectsThreads[i] = new Thread(){ 
                public void run(){ 
                    for (int j = partition*objectNumberPerThread; j < (partition+1)*objectNumberPerThread; j++){ 
                        objects[j].update(); 
                    } 
                } 
            };
        }

        return objectsThreads;
    }


    /**
     * add action listener to solar system to be able to accelerate and slow down the planets.
     * 'f' to accelerate and 's' to slow down.
     */
    public void addAccelerationListener(){
        this.s.addKeyListener(new KeyListener() {
            public void keyPressed (KeyEvent e) {    
                if (Character.toLowerCase(e.getKeyChar()) == 'f' && Config.rotatingSpeed - 0.3 > 0){
                    Config.rotatingSpeed -= 0.3;
                }  
                if (Character.toLowerCase(e.getKeyChar()) == 's'){
                    Config.rotatingSpeed += 0.3;
                }
            }       
        
            public void keyReleased (KeyEvent e) {    
            }    

            public void keyTyped (KeyEvent e) {    
            }   
        });
    }

}
