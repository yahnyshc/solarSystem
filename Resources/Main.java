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

        Planet[] Planets = {Mercury, Venus, Earth, Mars, Jupiter};
        Moon[] Moons = {earthMoon};

        /*
        Thread sunThread = new Thread(Sun);

        Thread planetsThread = new Thread(){
            public void run(){
                for (Planet p: Planets){
                    p.move();
                }
            }
        };

        Thread moonsThread = new Thread(){
            public void run(){
                for (Moon m: Moons){
                    m.move();
                }
            }
        };
        */

        while(true){
            Sun.draw();
            for (Planet p: Planets){
                p.move();
            }
            for (Moon m: Moons){
                m.move();
            }
            s.finishedDrawing();
        }
        
        
    }
}
