public class Planet {
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * Calculate the distance between two Planets.
     * 
     * @param p
     * @return the distance between p and this Planet
     */
    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    /**
     * Calculate the force exerted on this planed by the given planet.
     * 
     * @param p
     * @return a double describing the force exerted on this planed by the given
     *         planet
     * 
     */
    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        return G * mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    }

    /**
     * Calculate the force exerted in the X direction.
     * 
     * @param p
     * @return a double describing the force exerted on this planed in the X
     *         direction by the given planet
     */
    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
    }

    /**
     * Calculate the force exerted in the Y direction.
     * 
     * @param p
     * @return a double describing the force exerted on this planed in the Y
     *         direction by the given planet
     */
    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
    }

    /**
     * Calculate the net X force
     * 
     * @param allPlanets
     * @return a double describing the net X force
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            netForceX += this.calcForceExertedByX(p);
        }
        return netForceX;
    }

    /**
     * Calculate the net Y force
     * 
     * @param allPlanets
     * @return a double describing the net Y force
     */

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            netForceY += this.calcForceExertedByY(p);
        }
        return netForceY;
    }

    public void update(double time, double xForce, double yForce) {
        double xA = xForce / mass;
        double yA = yForce / mass;
        xxVel += xA * time;
        yyVel += yA * time;
        xxPos += time * xxVel;
        yyPos += time * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
