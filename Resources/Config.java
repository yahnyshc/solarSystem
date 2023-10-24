/**
 * This class contains parameters of solar system.
 * @author Maksym Yahnyshchak
 */
public class Config {
    static int windowWidth = 1100;
    static int windowHeight = 1100;
    
    // Coeficient of objects rotation speed.
    static double rotatingSpeed = 30.0;
    // Coeficient of objects size.
    static double sizeCoef = 0.75;
    // Distance between planets orbits
    static double planetsDistance = 45 * sizeCoef;
    
    static double sunSize = 90 * sizeCoef;
    static String sunColor = "#FDB813";

    static double mercurySize = 6 * sizeCoef;
    static double mercuryDistance = sunSize/2 + mercurySize/2 + planetsDistance;
    static double mercuryAngle = randomAngle();
    static double mercuryVelocity = 47.36;
    static String mercuryColor = "#B7B8B9";

    static double venusSize = 18 * sizeCoef;
    static double venusDistance = mercuryDistance + mercurySize/2 + venusSize/2 + planetsDistance;
    static double venusAngle = randomAngle();
    static double venusVelocity = 35.02;
    static String venusColor = "#FFC649";

    static double earthSize = 19 * sizeCoef;
    static double earthDistance = venusDistance + venusSize/2 + earthSize/2 + planetsDistance;
    static double earthAngle = randomAngle();
    static double earthVelocity = 29.78;
    static String earthColor = "#4F4CB0";

    static double earthMoonSize = 2 * sizeCoef;
    static double earthMoonDistance = earthSize/2 + earthMoonSize/2 + 3;
    static double earthMoonAngle = randomAngle();
    static double earthMoonVelocity = 1.022;
    static String earthMoonColor = "#F6F1D5";

    static double marsSize = 9 * sizeCoef;
    static double marsDistance = earthDistance + earthSize/2 + marsSize/2 + planetsDistance;
    static double marsAngle = randomAngle();
    static double marsVelocity = 24.08;
    static String marsColor = "#C1440E";

    static double jupiterSize = 40 * sizeCoef;
    static double jupiterDistance = marsDistance + marsSize/2 + jupiterSize/2 + planetsDistance*3;
    static double jupiterAngle = randomAngle();
    static double jupiterVelocity = 13.06;
    static String jupiterColor = "#C99039";
    
    static double ioMoonSize = 1.5 * sizeCoef;
    static double ioMoonDistance = jupiterSize/2 + ioMoonSize/2 + 2;
    static double ioMoonAngle = randomAngle();
    static double ioMoonVelocity = 17.406;
    static String ioMoonColor = "YELLOW";

    static double europaMoonSize = 1 * sizeCoef;
    static double europaMoonDistance = ioMoonDistance + europaMoonSize/2 + 1;
    static double europaMoonAngle = randomAngle();
    static double europaMoonVelocity = 13.74;
    static String europaMoonColor = "WHITE";

    static double ganymedeMoonSize = 3 * sizeCoef;
    static double ganymedeMoonDistance = europaMoonDistance + ganymedeMoonSize/2 + 1;
    static double ganymedeMoonAngle = randomAngle();
    static double ganymedeMoonVelocity = 10.9;
    static String ganymedeMoonColor = "#411900";

    static double callistoMoonSize = 2.3 * sizeCoef;
    static double callistoMoonDistance = ganymedeMoonDistance + callistoMoonSize/2 + 1;
    static double callistoMoonAngle = randomAngle();
    static double callistoMoonVelocity = 8.2;
    static String callistoMoonColor = "#800080";

    static double saturnSize = 28 * sizeCoef;
    static double saturnDistance = jupiterDistance + jupiterSize/2 + saturnSize/2 + planetsDistance;
    static double saturnAngle = randomAngle();
    static double saturnVelocity = 9.67;
    static String saturnColor = "#EAD6B8";

    static double uranusSize = 24 * sizeCoef;
    static double uranusDistance = saturnDistance + saturnSize/2 + uranusSize/2 + planetsDistance;
    static double uranusAngle = randomAngle();
    static double uranusVelocity = 6.79;
    static String uranusColor = "#ACE5EE";

    static double neptuneSize = 21 * sizeCoef;
    static double neptuneDistance = uranusDistance + uranusSize/2 + neptuneSize/2 + planetsDistance;
    static double neptuneAngle = randomAngle();
    static double neptuneVelocity = 5.45;
    static String neptuneColor = "#4B70DD";

    static int asteroidBeltAsteroidNumber = 500;
    static int asteroidBeltAsteroidNumberPerThread = 250;
    static int asteroidBeltThreadNumber = asteroidBeltAsteroidNumber/asteroidBeltAsteroidNumberPerThread; 
    static double asteroidBeltDistance = marsDistance + marsSize/2 + planetsDistance;
    static double[] asteroidBeltDistanceRange = {-20 * sizeCoef, 20 * sizeCoef};
    static double[][] asteroidBeltAngleRanges = { {0,360} };
    static double[] asteroidBeltVelocityRange = {10, 15};
    static double[] asteroidBeltSizeRange = {1 * sizeCoef, 4 * sizeCoef};
    static String asteroidBeltColor = "WHITE";

    static int trojanBeltAsteroidNumber = 200;
    static int trojanBeltAsteroidNumberPerThread = 100;
    static int trojanBeltThreadNumber = asteroidBeltAsteroidNumber/asteroidBeltAsteroidNumberPerThread; 
    static double trojanBeltDistance = jupiterDistance;
    static double[] trojanBeltDistanceRange = {-20 * sizeCoef, 20 * sizeCoef};
    static double[][] trojanBeltAngleRanges = { {jupiterAngle+50,jupiterAngle+100}, {jupiterAngle+250, jupiterAngle + 300} };
    static double[] trojanBeltVelocityRange = {jupiterVelocity, jupiterVelocity};
    static double[] trojanBeltSizeRange = {1 * sizeCoef, 4 * sizeCoef};
    static String trojanBeltColor = "WHITE";

    static int saturnBeltAsteroidNumber = 150;
    static int saturnBeltAsteroidNumberPerThread = 75;
    static int saturnBeltThreadNumber = saturnBeltAsteroidNumber/saturnBeltAsteroidNumberPerThread; 
    static double saturnBeltDistance = saturnSize/2 + 6;
    static double[] saturnBeltDistanceRange = {-3 * sizeCoef, 3 * sizeCoef};
    static double[][] saturnBeltAngleRanges = { {0,360} };
    static double[] saturnBeltVelocityRange = {80,80};
    static double[] saturnBeltSizeRange = {1, 2};
    static String saturnBeltColor = "#DBB57C";

    static double commetSize = 5 * sizeCoef;
    static double commetCentrumX = 100;
    static double commetCentrumY = 100;
    static double commetOrbitAngle = 225.0;
    static double commetOrbitA = 240;
    static double commetOrbitB = 120;
    static double commetStartAngle = randomAngle();
    static double commetVelocity = 45.0;
    static String commetColor = "WHITE";

    private static double randomAngle(){
        return Math.random()*360;
    }
}
