/**basic class of body */
public class Body {
    public double xxPos;//Its current x posotion
    public double yyPos;//Its current y posotion
    public double xxVel;//Its current velocity in the x direction
    public double yyVel;//Its current velocity in the y direction
    public double mass;// Its mass
    public String imgFileName;
    // The name of the file that corresponds to the image that depicts the body

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
}