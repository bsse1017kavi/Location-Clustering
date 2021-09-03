package locationPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LocationClusterer
{
    String filePath = "sampleLocations1.txt";

    File file = new File(filePath);

    ArrayList<Coordinate> locations = new ArrayList<>();

    int numberOfData = 0;

    String [] values;

    double [] distances;

    Coordinate [] centroids;

    ArrayList<Coordinate> [] clusterValues;

    ArrayList<Cluster> clusters;

    double eps = 0.1;

    public void initialize()
    {
        try
        {
            Scanner fileReader = new Scanner(file);

            while(fileReader.hasNextLine())
            {
                String currentLine = fileReader.nextLine();

                values = currentLine.split(" ");

                locations.add(new Coordinate(Double.parseDouble(values[0]), Double.parseDouble(values[1])));

                numberOfData++;
            }

            fileReader.close();


//            for(Coordinate coordinate: locations)
//            {
//                System.out.println(coordinate.getLatitude()+" "+coordinate.getLongitute());
//            }

        }catch (FileNotFoundException e)
        {

        }
    }

    public int min(double [] arr)
    {
        int minIndex = 0;
        double minValue = arr[0];

        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]<minValue)
            {
                minValue = arr[i];
                minIndex = i;
            }
        }

        return  minIndex;
    }

    public double distance (Coordinate c1, Coordinate c2)
    {
        return Math.sqrt(
                Math.pow(c1.getLatitude() - c2.getLatitude(),2)+
                        Math.pow(c1.getLongitude() - c2.getLongitude(),2));
    }

    public ArrayList<Cluster> cluster(int k)
    {
        distances = new double[k];
        centroids = new Coordinate[k];
        clusterValues = new ArrayList[k];
        clusters = new ArrayList<>();

        Random random = new Random();

        for(int i=0;i<k;i++)
        {
            double x = random.doubles(-180,180).findFirst().getAsDouble();
            double y = random.doubles(-180,180).findFirst().getAsDouble();

            centroids[i] = new Coordinate(x,y);
        }

        do
        {
            for(int i=0;i<k;i++)
            {
                clusterValues[i] = new ArrayList<>();
            }



            for(Coordinate coordinate: locations)
            {
                for(int z=0;z<k;z++)
                {
                    distances[z] = distance(centroids[z],coordinate);
                }

                int index = min(distances);

                clusterValues[index].add(coordinate);
            }

            Coordinate prevCentroid = centroids[0];

            for(int i=0;i<k;i++)
            {
                double latitudeSum = 0;
                double longitudeSum = 0;

                for(Coordinate coordinate: clusterValues[i])
                {
                    latitudeSum += coordinate.getLatitude();
                    longitudeSum += coordinate.getLongitude();
                }

                centroids[i] = new Coordinate(latitudeSum/ clusterValues[i].size(), longitudeSum/ clusterValues[i].size());
            }

            if(distance(prevCentroid, centroids[0]) < eps)
            {
                break;
            }


        }while (true);

        for(int i=0;i<centroids.length;i++)
        {
            Cluster cluster = new Cluster(clusterValues[i], centroids[i]);

            clusters.add(cluster);
        }

        return clusters;
    }

    public void printClusters()
    {
        for(int i = 0; i< clusterValues.length ; i++)
        {
            System.out.println("Cluster " + (i+1) + ":");

            for(Coordinate coordinate: clusterValues[i])
            {
                System.out.println(coordinate.getLatitude() + " " + coordinate.getLongitude());
            }
        }
    }
}
