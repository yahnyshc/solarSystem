// Importing utility classes 
import java.util.*; 

/**
 * This class creates and manitpulates space objects in such way that solar system is modeled.
 * @author Maksym Yahnyshchak
 */
public class Main {

    /**
     * Main method
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SolarSystem s = new SolarSystem(Config.windowWidth, Config.windowHeight);

        // The Sun
        Star Sun = new Star(s, Config.sunSize, Config.sunColor );

        // Mercury
        Planet Mercury = new Planet(s, Sun, Config.mercuryDistance, Config.mercuryAngle, Config.mercurySize, Config.mercuryVelocity, Config.mercuryColor );

        // Venus
        Planet Venus = new Planet(s, Sun, Config.venusDistance, Config.venusAngle, Config.venusSize, Config.venusVelocity, Config.venusColor );
    
        // Earth
        Planet Earth = new Planet(s, Sun, Config.earthDistance, Config.earthAngle, Config.earthSize, Config.earthVelocity, Config.earthColor );

        Moon earthMoon = new Moon(s, Earth, Config.earthMoonDistance, Config.earthMoonAngle, Config.earthMoonSize, Config.earthMoonVelocity, Config.earthMoonColor );

        Planet Mars = new Planet(s, Sun, Config.marsDistance, Config.marsAngle, Config.marsSize, Config.marsVelocity, Config.marsColor );

        Planet Jupiter = new Planet(s, Sun, Config.jupiterDistance, Config.jupiterAngle, Config.jupiterSize, Config.jupiterVelocity, Config.jupiterColor );

        // The Galilean Moons of Jupiter
        Moon ioMoon = new Moon(s, Jupiter, Config.ioMoonDistance, Config.ioMoonAngle, Config.ioMoonSize, Config.ioMoonVelocity, Config.ioMoonColor );
        Moon europaMoon = new Moon(s, Jupiter, Config.europaMoonDistance, Config.europaMoonAngle, Config.europaMoonSize, Config.europaMoonVelocity, Config.europaMoonColor );
        Moon ganymedeMoon = new Moon(s, Jupiter, Config.ganymedeMoonDistance, Config.ganymedeMoonAngle, Config.ganymedeMoonSize, Config.ganymedeMoonVelocity, Config.ganymedeMoonColor );
        Moon callistoMoon = new Moon(s, Jupiter, Config.callistoMoonDistance, Config.callistoMoonAngle, Config.callistoMoonSize, Config.callistoMoonVelocity, Config.callistoMoonColor );

        Planet Saturn = new Planet(s, Sun, Config.saturnDistance, Config.saturnAngle, Config.saturnSize, Config.saturnVelocity, Config.saturnColor );

        Planet Uranus = new Planet(s, Sun, Config.uranusDistance, Config.uranusAngle, Config.uranusSize, Config.uranusVelocity, Config.uranusColor );

        Planet Neptune = new Planet(s, Sun, Config.neptuneDistance, Config.neptuneAngle, Config.neptuneSize, Config.neptuneVelocity, Config.neptuneColor );

        Planet[] Planets = {Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune};
        Moon[] Moons = {earthMoon, ioMoon, europaMoon, ganymedeMoon, callistoMoon};
        Moon[] asteroidBelt = createBelt(s, Sun, Config.asteroidBeltAsteroidNumber, Config.asteroidBeltDistance, Config.asteroidBeltDistanceRange, Config.asteroidBeltAngleRanges, Config.asteroidBeltVelocityRange, Config.asteroidBeltSizeRange, Config.asteroidBeltColor);
        Moon[] trojanBelt = createBelt(s, Sun, Config.trojanBeltAsteroidNumber, Config.trojanBeltDistance, Config.trojanBeltDistanceRange, Config.trojanBeltAngleRanges, Config.trojanBeltVelocityRange, Config.trojanBeltSizeRange, Config.trojanBeltColor);
        Moon[] saturnBelt = createBelt(s, Saturn, Config.saturnBeltAsteroidNumber, Config.saturnBeltDistance, Config.saturnBeltDistanceRange, Config.asteroidBeltAngleRanges, Config.saturnBeltVelocityRange, Config.saturnBeltSizeRange, Config.saturnBeltColor);

        Thread sunThread, planetsThread, moonsThread;
        Thread [] asteroidBeltThreads, trojanBeltThreads, saturnBeltThreads;
        while (true){
            // Create runnable threads
            sunThread = new Thread(Sun);
            planetsThread = new Thread(){ public void run(){ for (Planet p: Planets){ p.update(); }}};
            moonsThread = new Thread(){ public void run(){ for (Moon m: Moons){ m.update(); } } };
            asteroidBeltThreads = partitionSpaceObjectsOnThreads(asteroidBelt, Config.asteroidBeltAsteroidNumber, Config.asteroidBeltAsteroidNumberPerThread);
            trojanBeltThreads = partitionSpaceObjectsOnThreads(trojanBelt, Config.trojanBeltAsteroidNumber, Config.trojanBeltAsteroidNumberPerThread);
            saturnBeltThreads = partitionSpaceObjectsOnThreads(saturnBelt, Config.saturnBeltAsteroidNumber, Config.saturnBeltAsteroidNumberPerThread);

            // Start all of the threads
            sunThread.start();
            planetsThread.start();
            moonsThread.start();
            for (int i = 0; i < Config.asteroidBeltThreadNumber; i++){ asteroidBeltThreads[i].start(); }
            for (int i = 0; i < Config.trojanBeltThreadNumber; i++){ trojanBeltThreads[i].start(); }
            for (int i = 0; i < Config.saturnBeltThreadNumber; i++){ saturnBeltThreads[i].start(); }

            //let all threads finish execution before finishing drawing
            try {
                sunThread.join();
                planetsThread.join();
                moonsThread.join();
                for (int i = 0; i < Config.asteroidBeltThreadNumber; i++){ asteroidBeltThreads[i].join(); }
                for (int i = 0; i < Config.trojanBeltThreadNumber; i++){ trojanBeltThreads[i].join(); }
                for (int i = 0; i < Config.saturnBeltThreadNumber; i++){ saturnBeltThreads[i].join(); }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // finish drawing
            s.finishedDrawing();
        }
    }

    /**
     * Create asteroids belt around the centrum space object.
     * @param s SolarSystem object
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
    public static Moon[] createBelt(SolarSystem s, SpaceObject centrum, int asteroidNumber, double beltDistance, double[] distanceRange, double[][] angleRanges, double[] velocityRange, double[] sizeRange, String color){
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

            belt[i] = new Moon( s, centrum, distance, angle, size, velocity, color );
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
    public static Thread[] partitionSpaceObjectsOnThreads(SpaceObject[] objects, int threadNumber, int objectNumberPerThread){
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


}
