// Importing utility classes 
import java.util.*; 

public class Main {
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

        Thread sunThread, planetsThread, moonsThread, saturnRingThread;
        Thread [] asteroidBeltThreads = new Thread[Config.asteroidBeltThreadNumber];
        Thread [] trojanBeltThreads = new Thread[Config.trojanBeltThreadNumber];
        Thread [] saturnBeltThreads = new Thread[Config.saturnBeltThreadNumber];
        while (true){
            sunThread = new Thread(){ public void run(){ Sun.update(); } };
            planetsThread = new Thread(){ public void run(){ for (Planet p: Planets){ p.update(); }}};
            moonsThread = new Thread(){ public void run(){ for (Moon m: Moons){ m.update(); } } };
            asteroidBeltThreads = partitionSpaceObjectsOnThreads(asteroidBelt, Config.asteroidBeltAsteroidNumber, Config.asteroidBeltAsteroidNumberPerThread);
            trojanBeltThreads = partitionSpaceObjectsOnThreads(trojanBelt, Config.trojanBeltAsteroidNumber, Config.trojanBeltAsteroidNumberPerThread);
            saturnBeltThreads = partitionSpaceObjectsOnThreads(saturnBelt, Config.saturnBeltAsteroidNumber, Config.saturnBeltAsteroidNumberPerThread);

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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            s.finishedDrawing();
        }
    }

    public static Moon[] createBelt(SolarSystem s, SpaceObject centrum, int asteroidNumber, double beltDistance, double[] distanceRange, double[][] angleRanges, double[] velocityRange, double[] sizeRange, String color){
        Moon[] belt = new Moon[asteroidNumber];
        HashMap<Integer,Integer> asteroidsPos = new HashMap<Integer,Integer>();

        int angleRange;
        double distance, angle, velocity, size;
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
