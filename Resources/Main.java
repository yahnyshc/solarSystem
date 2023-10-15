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
        Moon[] asteroidsBeltOne = new Moon[200];
 
        for ( int i = 0; i < 200; i++ ){
            double randomRange = (double) ((Math.random() * (40 - (-2))) + (-5));
            double randomAngle = (double) ((Math.random() * (360 - 0)) + 0);
            double randomVelocity = (double) ((Math.random() * (15 - 10)) + 10);
            double randomSize = (double) ((Math.random() * (4 - 1)) + 1);

            asteroidsBeltOne[i] = new Moon( s, Sun, Config.marsDistance + Config.marsSize/2 + 20 + randomRange, randomAngle, randomSize, randomVelocity, "WHITE" );
        }   

        Moon[] saturnBelt = new Moon[60];

        for ( int i = 0; i < 60; i++ ){
            double randomRange = (double) ((Math.random() * (6 - 0)) + 0);
            double randomAngle = (double) i * 6;
            double randomVelocity = (double) 80;

            saturnBelt[i] = new Moon( s, Saturn, Config.saturnSize/2 + 2.5 + randomRange, randomAngle, 1, randomVelocity, "WHITE" );
        }   

        Thread cleanerThread = new Thread(){
            public void run(){
                while(true){
                    s.finishedDrawing();
                    try{ Thread.sleep(18); } catch (Exception e) {} 
                }
            }
        };
        
        Thread sunThread = new Thread(){
            public void run(){
                while(true){
                    Sun.draw();
                    try{ Thread.sleep(6); } catch (Exception e) {} 
                }
            }
        };

        Thread planetsThread = new Thread(){
            public void run(){
                while(true){
                    for (Planet p: Planets){
                        p.move();
                    }
                    try{ Thread.sleep(6); } catch (Exception e) {} 
                }
            }
        };

        Thread moonsThread = new Thread(){
            public void run(){
                while(true){
                    for (Moon m: Moons){
                        m.move();
                    }
                    try{ Thread.sleep(6); } catch (Exception e) {} 
                }
            }
        };

        Thread asteroidsBeltOneThread = new Thread(){
            public void run(){
                while(true){
                    for (Moon a: asteroidsBeltOne){
                        a.move();
                    }
                    try{ Thread.sleep(17); } catch (Exception e) {} 
                }
            }
        };

        Thread saturnRingThread = new Thread(){
            public void run(){
                while(true){
                    for (Moon a: saturnBelt){
                        a.move();
                    }
                    try{ Thread.sleep(14); } catch (Exception e) {} 
                }
            }
        };
        
        
        cleanerThread.start();
        sunThread.start();
        planetsThread.start();
        moonsThread.start();
        asteroidsBeltOneThread.start();
        saturnRingThread.start();
        
    }
}
