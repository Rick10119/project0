public class NBody {
    public static double readRadius(String FileName) {
        if (FileName == "") {
            System.out.println("Please supply a file name.");
            return 0;
        } // input is none

        /* Start reading in the given file */
        In in = new In(FileName);// instead of "FileName"

        /* looking for Radius, hopefully that the config is the same. */
        in.readInt();
        double radius = in.readDouble();// radius is expected to be in the second position
        return radius;
    }

    public static Body[] readBodies(String FileName) {
        if (FileName == "") {
            System.out.println("Please supply a file name.");
            return null;
        } // input is none
          // instead of "FileName"
        In in = new In(FileName);

        /* looking for Bodies */
        int BodyNumber = in.readInt();
        // to move to the correct position
        in.readDouble();
        Body[] bodies = new Body[BodyNumber];
        // some temperary viables to instantiate
        double temp_xP, temp_yP, temp_xV, temp_yV, temp_m;
        String temp_img;
        for (int i = 0; i < BodyNumber; i += 1) {
            temp_xP = in.readDouble();
            temp_yP = in.readDouble();
            temp_xV = in.readDouble();
            temp_yV = in.readDouble();
            temp_m = in.readDouble();
            temp_img = in.readString();
            bodies[i] = new Body(temp_xP, temp_yP, temp_xV, temp_yV, temp_m, temp_img);
        }
        return bodies;
    }

    /** input as String: (T, dt, filename) */
    public static void main(String[] args) {

        /** read the file */
        ///////////////////////////////////////////////////////////////////////////
        if (args.length < 3) {
            System.out.println("not enough arguments: (T, dt, filename)");
            return;
        }
        // getting the arguments
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // read in bodies and radius in filename
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        /** calc and drawing */
        /////////////////////////////////////////////////////////////////////////

        /** to prepare for calc the status of each body and draw it */
        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length];

        /** a loop of time */
        for (double time = 0; time < T; time += dt) {
            /** calc the status of each body */
            // calc the force
            int i = 0;
            for (Body EachBody : bodies) {
                // the forces
                xForces[i] = EachBody.calcNetForceExertedByX(bodies);
                yForces[i] = EachBody.calcNetForceExertedByY(bodies);
                i += 1;
            }

            // to update position and velocity
            i = 0;
            for (Body EachBody : bodies) {
                EachBody.update(dt, xForces[i], yForces[i]);
                i += 1;
            }

            // to draw
            StdDraw.enableDoubleBuffering();
            // Sets up the universe according to radius
            StdDraw.setScale(-radius, radius);
            /* Clears the drawing window. */
            StdDraw.clear();
            /* draw the background. */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            /* draw each body in the bodies array */
            for (Body EachBody : bodies) {
                EachBody.draw();
            }

            /* Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }
        
    }

}