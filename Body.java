/**basic class of body */
public class Body {
    public double xxPos;//Its current x posotion
    public double yyPos;//Its current y posotion
    public double xxVel;//Its current velocity in the x direction
    public double yyVel;//Its current velocity in the y direction
    public double mass;// Its mass
    public String imgFileName;
    // The name of the file that corresponds to the image that depicts the body

    /** constant of gravity */
    public static final double G = 6.67e-11;

    /** instantiate a body class */
    public Body(double xP, double yP,
    double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** copy from a body instance  */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** calculate the distance of two bodies */
    public double calcDistance (Body b) {
        // displacement of the two
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        // distance r
        double r = Math.hypot(dx, dy);
        return r;
    }

    /** to calculate the force exerted by b */
    public double calcForceExertedBy(Body b) {
        //the distance r
        double r = this.calcDistance(b);
        // the force F
        double F = Body.G * this.mass * b.mass / (r * r);
        return F;

    }

    /** to calculate the force by b in x direction */
    public double calcForceExertedByX(Body b) {
        // displacement of the two
        double dx = b.xxPos - this.xxPos;
        // the distance r
        double r = this.calcDistance(b);
        // the force F
        double F = this.calcForceExertedBy(b);
        // F_x in x direction
        double F_x = F * dx / r;
        return F_x;
    }

    /** to calculate the force by b in y direction */
    public double calcForceExertedByY(Body b) {
        // displacement of the two
        double dy = b.yyPos - this.yyPos;
        // the distance r
        double r = this.calcDistance(b);
        // the force F
        double F = this.calcForceExertedBy(b);
        // F_x in x direction
        double F_y = F * dy / r;
        return F_y;
    }

    /** to calculate the Net Force in x direction */
    public double calcNetForceExertedByX(Body [] allBodys) {
        double NetForce_x = 0;
        for (Body b : allBodys) {//force beside itself
            if (! this.equals(b)) {
                NetForce_x += this.calcForceExertedByX(b);
            }
        }
        return NetForce_x;
    }

    /** to calculate the Net Force in y direction */
    public double calcNetForceExertedByY(Body [] allBodys) {
        double NetForce_y = 0;
        for (Body b : allBodys) {
            if (! this.equals(b)) {
                NetForce_y += this.calcForceExertedByY(b);
            }
        }
        return NetForce_y;
    }

    /** to update the velocity and position of this body*/
    public void update(double dt, double NetForceX, double NetForceY) {
        // its acceleration in two directions
        double xxNetA = NetForceX / this.mass;
        double yyNetA = NetForceY / this.mass;
        // the new vel of x and y
        this.xxVel += dt * xxNetA;
        this.yyVel += dt * yyNetA;
        // the new postion of x and y
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
}