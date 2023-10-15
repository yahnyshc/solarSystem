public class Config {
    static int windowWidth = 1200;
    static int windowHeight = 1200;
    static double rotatingSpeed = 30.0;

    static double sunSize = 110;
    static String sunColor = "#FDB813";

    static double mercuryDistance = sunSize/2 + 20;
    static double mercuryAngle = 0;
    static double mercurySize = 6;
    static double mercuryVelocity = 47.36;
    static String mercuryColor = "#B7B8B9";

    static double venusDistance = sunSize/2 + 60;
    static double venusAngle = 0;
    static double venusSize = 18;
    static double venusVelocity = 35.02;
    static String venusColor = "#FFC649";

    static double earthDistance = sunSize/2 + 100;
    static double earthAngle = 0;
    static double earthSize = 19;
    static double earthVelocity = 29.78;
    static String earthColor = "#4F4CB0";

    static double earthMoonDistance = earthSize/2 + 4;
    static double earthMoonAngle = 0;
    static double earthMoonSize = 1;
    static double earthMoonVelocity = 1.022;
    static String earthMoonColor = "#F6F1D5";

    static double marsDistance = sunSize/2 + 140;
    static double marsAngle = 0;
    static double marsSize = 9;
    static double marsVelocity = 24.08;
    static String marsColor = "#C1440E";

    static double jupiterDistance = sunSize/2 + 240;
    static double jupiterAngle = 0;
    static double jupiterSize = 40;
    static double jupiterVelocity = 13.06;
    static String jupiterColor = "#C99039";
    
    static double ioMoonDistance = jupiterSize/2 + 3;
    static double ioMoonAngle = 0;
    static double ioMoonSize = 1.5;
    static double ioMoonVelocity = 17.406;
    static String ioMoonColor = "YELLOW";

    static double europaMoonDistance = jupiterSize/2 + 7;
    static double europaMoonAngle = 83;
    static double europaMoonSize = 1;
    static double europaMoonVelocity = 13.74;
    static String europaMoonColor = "WHITE";

    static double ganymedeMoonDistance = jupiterSize/2 + 12;
    static double ganymedeMoonAngle = 195;
    static double ganymedeMoonSize = 3;
    static double ganymedeMoonVelocity = 10.9;
    static String ganymedeMoonColor = "#411900";

    static double callistoMoonDistance = jupiterSize/2 + 16;
    static double callistoMoonAngle = 256;
    static double callistoMoonSize = 2.3;
    static double callistoMoonVelocity = 8.2;
    static String callistoMoonColor = "#800080";

    static double saturnDistance = sunSize/2 + 290;
    static double saturnAngle = 0;
    static double saturnSize = 28;
    static double saturnVelocity = 9.67;
    static String saturnColor = "#EAD6B8";

    static double uranusDistance = sunSize/2 + 330;
    static double uranusAngle = 0;
    static double uranusSize = 24;
    static double uranusVelocity = 6.79;
    static String uranusColor = "#ACE5EE";

    static double neptuneDistance = sunSize/2 + 380;
    static double neptuneAngle = 0;
    static double neptuneSize = 21;
    static double neptuneVelocity = 5.45;
    static String neptuneColor = "#4B70DD";

    static int asteroidBeltAsteroidNumber = 500;
    static int asteroidBeltAsteroidNumberPerThread = 250;
    static int asteroidBeltThreadNumber = asteroidBeltAsteroidNumber/asteroidBeltAsteroidNumberPerThread; 
    static double asteroidBeltDistance = marsDistance + marsSize/2;
    static double[] asteroidBeltDistanceRange = {20, 40};
    static double[][] asteroidBeltAngleRanges = { {0,360} };
    static double[] asteroidBeltVelocityRange = {10, 15};
    static double[] asteroidBeltSizeRange = {1, 4};
    static String asteroidBeltColor = "WHITE";

    static int trojanBeltAsteroidNumber = 200;
    static int trojanBeltAsteroidNumberPerThread = 100;
    static int trojanBeltThreadNumber = asteroidBeltAsteroidNumber/asteroidBeltAsteroidNumberPerThread; 
    static double trojanBeltDistance = jupiterDistance;
    static double[] trojanBeltDistanceRange = {20, 40};
    static double[][] trojanBeltAngleRanges = { {50,100}, {250, 300} };
    static double[] trojanBeltVelocityRange = {jupiterVelocity, jupiterVelocity};
    static double[] trojanBeltSizeRange = {1, 4};
    static String trojanBeltColor = "WHITE";

    static int saturnBeltAsteroidNumber = 150;
    static int saturnBeltAsteroidNumberPerThread = 75;
    static int saturnBeltThreadNumber = saturnBeltAsteroidNumber/saturnBeltAsteroidNumberPerThread; 
    static double saturnBeltDistance = saturnSize/2;
    static double[] saturnBeltDistanceRange = {2.5, 8};
    static double[][] saturnBeltAngleRanges = { {0,360} };
    static double[] saturnBeltVelocityRange = {80,80};
    static double[] saturnBeltSizeRange = {1, 2};
    static String saturnBeltColor = "#DBB57C";
}
