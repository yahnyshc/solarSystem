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
        SolarSystemHelper helper = new SolarSystemHelper(s);

        // add acceleration to solar system. 'f' to accelerate and 's' to slow down
        helper.addAccelerationListener();
        
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
        Moon[] asteroidBelt = helper.createBelt( Sun, Config.asteroidBeltAsteroidNumber, Config.asteroidBeltDistance, Config.asteroidBeltDistanceRange, Config.asteroidBeltAngleRanges, Config.asteroidBeltVelocityRange, Config.asteroidBeltSizeRange, Config.asteroidBeltColor);
        Moon[] trojanBelt = helper.createBelt( Sun, Config.trojanBeltAsteroidNumber, Config.trojanBeltDistance, Config.trojanBeltDistanceRange, Config.trojanBeltAngleRanges, Config.trojanBeltVelocityRange, Config.trojanBeltSizeRange, Config.trojanBeltColor);
        Moon[] saturnBelt = helper.createBelt( Saturn, Config.saturnBeltAsteroidNumber, Config.saturnBeltDistance, Config.saturnBeltDistanceRange, Config.asteroidBeltAngleRanges, Config.saturnBeltVelocityRange, Config.saturnBeltSizeRange, Config.saturnBeltColor);

        // commet that will have eliptic orbit
        Comet commet = new Comet(s, Config.commetCentrumX, Config.commetCentrumY, Config.commetOrbitAngle, Config.commetOrbitA, Config.commetOrbitB, Config.commetStartAngle, Config.commetSize, Config.commetVelocity, Config.commetColor);

        Thread sunThread, planetsThread, moonsThread, commetThread;
        Thread [] asteroidBeltThreads, trojanBeltThreads, saturnBeltThreads;
        while (true){
            // Create runnable threads
            sunThread = new Thread(Sun);
            planetsThread = new Thread(){ public void run(){ for (Planet p: Planets){ p.update(); }}};
            moonsThread = new Thread(){ public void run(){ for (Moon m: Moons){ m.update(); } } };
            asteroidBeltThreads = helper.partitionSpaceObjectsOnThreads(asteroidBelt, Config.asteroidBeltAsteroidNumber, Config.asteroidBeltAsteroidNumberPerThread);
            trojanBeltThreads = helper.partitionSpaceObjectsOnThreads(trojanBelt, Config.trojanBeltAsteroidNumber, Config.trojanBeltAsteroidNumberPerThread);
            saturnBeltThreads = helper.partitionSpaceObjectsOnThreads(saturnBelt, Config.saturnBeltAsteroidNumber, Config.saturnBeltAsteroidNumberPerThread);
            commetThread = new Thread(commet);

            // Start all of the threads
            sunThread.start();
            planetsThread.start();
            moonsThread.start();
            commetThread.start();
            for (int i = 0; i < Config.asteroidBeltThreadNumber; i++){ asteroidBeltThreads[i].start(); }
            for (int i = 0; i < Config.trojanBeltThreadNumber; i++){ trojanBeltThreads[i].start(); }
            for (int i = 0; i < Config.saturnBeltThreadNumber; i++){ saturnBeltThreads[i].start(); }

            //let all threads finish execution before finishing drawing
            try {
                sunThread.join();
                planetsThread.join();
                moonsThread.join();
                commetThread.join();
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
}
