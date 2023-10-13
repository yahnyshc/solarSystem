public class Main {
    public static void main(String[] args) {
        SolarSystem s = new SolarSystem(1000, 1000);

        // The Sun
        Star Sun = new Star(0, 0, 100, "YELLOW");

        // Earth
        Planet Earth = new Earth(200, 0, );



        s.drawSolarObject(0, 0, 100, "YELLOW");

        // Earth
        s.drawSolarObject(this.distance, angle, size , color);

        // Moon
        s.drawSolarObjectAbout(50, 70, 5, "WHITE", 200, 0);

        // Mars
        s.drawSolarObject(400, 0, 30, "RED");
    }
}
