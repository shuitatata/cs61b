public class NBody {

    /**
     * Read the radius of the universe in that file
     * 
     * @param filename
     * @return the radius of the universe in the file
     */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        int N = planets.length;

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }

        double time = 0;

        while (time < T) {
            double xForces[] = new double[N];
            double yForces[] = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

            }
            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-universeRadius, universeRadius);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {

                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}
