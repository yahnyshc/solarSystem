public class Main {
    public static void main(String[] args) {
        SolarSystem s = new SolarSystem(Config.windowWidth, Config.windowHeight);

        // The Sun
        Star Sun = new Star(s, Config.sunSize, Config.sunColor );

        Thread sunThread = new Thread(Sun);
        sunThread.start();

        // Earth
        Planet Earth = new Planet(s, Sun, Config.earthDistance, Config.earthAngle, Config.earthSize, Config.earthVelocity, Config.earthColor );

        Moon earthMoon = new Moon(s, Earth, Config.earthMoonDistance, Config.earthMoonAngle, Config.earthMoonSize, Config.earthMoonVelocity, Config.earthMoonColor );

        Planet Mars = new Planet(s, Sun, Config.marsDistance, Config.marsAngle, Config.marsSize, Config.marsVelocity, Config.marsColor );

        Planet[] Planets = {Earth, Mars};
        Moon[] Moons = {earthMoon};
        
        Thread planetsThread = new Thread(){
            public void run(){
                while(true){
                    for (Planet p: Planets){
                        p.move();
                    }
                    s.finishedDrawing();
                    try{
                        Thread.sleep(10);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        };

        Thread moonsThread = new Thread(){
            public void run(){
                while(true){
                    for (Moon m: Moons){
                        m.move();
                    }
                    s.finishedDrawing();
                    try{
                        Thread.sleep(10);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        };

        planetsThread.start();
        moonsThread.start();
    }
}
