package locationPackage;

import java.util.ArrayList;

public class Cluster
{
    private ArrayList<Coordinate> coordinates = new ArrayList<>();

    private Coordinate centroid;

    public Cluster(ArrayList<Coordinate> coordinates, Coordinate centroid) {
        this.coordinates = coordinates;
        this.centroid = centroid;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public Coordinate getCentroid() {
        return centroid;
    }
}
