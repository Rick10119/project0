public class NBody {
    public static double readRadius(String FileName) {
        if (FileName == "") {
            System.out.println("Please supply a file name.");
            return 0;
        }	// input is none

        /* Start reading in the given file */
        In in = new In(FileName);// instead of "FileName"
        
		/* looking for Radius, hopefully that the config is the same. */
		in.readInt();
        double radius = in.readDouble();// radius is expected to be in the second position
        return radius;
    }


    public static Body [] readBodies(String FileName) {
        if (FileName == "") {
            System.out.println("Please supply a file name.");
            return null;
        }	// input is none

        In in = new In(FileName);// instead of "FileName"
        
		/* looking for Bodies */
        int BodyNumber = in.readInt();
        // to move to the correct position
        in.readDouble();
        Body [] bodies = new Body [BodyNumber];
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
    
}