package mainPackage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import locationPackage.Cluster;
import locationPackage.Coordinate;
import locationPackage.LocationClusterer;

import java.util.ArrayList;
import java.util.Scanner;

public class BarGraph extends Application {

    final static String text = "k = ";

    public Coordinate average(Coordinate[] array)
    {
        double x = 0;
        double y = 0;

        for(int i=0;i<array.length;i++)
        {
            if(Double.isNaN((array[i].getLongitude())) || Double.isNaN(array[i].getLongitude()))
            {

            }

            else
            {
                x += array[i].getLatitude();
                y += array[i].getLongitude();
            }
        }

        x /= array.length;
        y /= array.length;

        return new Coordinate(x,y);
    }

    public static Scene centroidBargraph(ArrayList<Cluster> clusters)
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Centroid average for different k value");
        xAxis.setLabel("Centroid Number");
        yAxis.setLabel("Value");

        XYChart.Series x = new XYChart.Series();
        XYChart.Series y = new XYChart.Series();
        XYChart.Series empty = new XYChart.Series();

        x.setName("X");
        y.setName("Y");
        empty.setName("Empty");

        LocationClusterer locationClusterer = new LocationClusterer();
        locationClusterer.initialize();

        for(int i=0;i<clusters.size();i++)
        {
            double latitude = clusters.get(i).getCentroid().getLatitude();
            double longitude = clusters.get(i).getCentroid().getLongitude();
            double emptyValue = 0;


            if(Double.isNaN(latitude) || Double.isNaN(longitude))
            {
                latitude = 0;
                longitude = 0;
                emptyValue = 10;
            }

            x.getData().add(new XYChart.Data("cluster " + (i+1), latitude));
            y.getData().add(new XYChart.Data("cluster " + (i+1), longitude));
            empty.getData().add(new XYChart.Data("cluster " + (i+1), emptyValue));
        }


        bc.getData().addAll(x, y, empty);
        Scene scene  = new Scene(bc,800,600);


        return scene;
    }

    @Override public void start(Stage stage) {
//        stage.setTitle("Bar Chart Sample");
//
//        //bc.getData().addAll(series1, series2, series3);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
