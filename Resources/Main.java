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
        Moon[] asteroidsBeltOne = new Moon[800];
        HashMap<Integer,Integer> asteroidsPos = new HashMap<Integer,Integer>();

        double randomRange;
        double randomAngle;
        double randomVelocity;
        double randomSize;
        for ( int i = 0; i < 800; i++ ){
            do{
                randomRange = (double) ((Math.random() * (50 - (-10))) + (-10));
                randomAngle = (double) ((Math.random() * (360 - 0)) + 0);
                randomVelocity = (double) ((Math.random() * (15 - 10)) + 10);
                randomSize = (double) ((Math.random() * (4 - 1)) + 1);
            }
            while ( asteroidsPos.containsKey((int)randomRange) && (int)asteroidsPos.get((int)randomRange) == (int)randomAngle);
            asteroidsPos.put((int)randomRange, (int)randomAngle);

            asteroidsBeltOne[i] = new Moon( s, Sun, Config.marsDistance + Config.marsSize/2 + 20 + randomRange, randomAngle, randomSize, randomVelocity, "WHITE" );
        }   

        Moon[] saturnBelt = new Moon[180];
        asteroidsPos.clear();

        for ( int i = 0; i < 180; i++ ){
            do{
                randomRange = (double) ((Math.random() * (6 - 0)) + 0);
                randomAngle = (double) i * 2;
                randomVelocity = (double) 80;
            }
            while ( asteroidsPos.containsKey((int)randomRange) && (int)asteroidsPos.get((int)randomRange) == (int)randomAngle);
            asteroidsPos.put((int)randomRange, (int)randomAngle);

            saturnBelt[i] = new Moon( s, Saturn, Config.saturnSize/2 + 2.5 + randomRange, randomAngle, 1, randomVelocity, "WHITE" );
        }   

        Thread sunThread, planetsThread, moonsThread, asteroidsBeltFirstThread, asteroidsBeltSecondThread, saturnRingThread;
        while (true){
            sunThread = new Thread(){ public void run(){ Sun.draw(); } };
            planetsThread = new Thread(){ public void run(){ for (Planet p: Planets){ p.move(); }}};
            moonsThread = new Thread(){ public void run(){ for (Moon m: Moons){ m.move(); } } };
            asteroidsBeltFirstThread = new Thread(){ public void run(){ for (int i = 0; i < 400; i++){ asteroidsBeltOne[i].move(); } } };
            asteroidsBeltSecondThread = new Thread(){ public void run(){ for (int i = 400; i < 800; i++){ asteroidsBeltOne[i].move(); } } };
            saturnRingThread = new Thread(){ public void run(){ for (Moon a: saturnBelt){ a.move(); } } };

            sunThread.start();
            planetsThread.start();
            moonsThread.start();
            asteroidsBeltFirstThread.start();
            asteroidsBeltSecondThread.start();
            saturnRingThread.start();

            //let all threads finish execution before finishing drawing
            try {
                sunThread.join();
                planetsThread.join();
                moonsThread.join();
                asteroidsBeltFirstThread.join();
                asteroidsBeltSecondThread.join();
                saturnRingThread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            s.finishedDrawing();
        }
        
        
    }
}
