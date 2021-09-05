package mainPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import locationPackage.Cluster;
import locationPackage.Coordinate;
import locationPackage.LocationClusterer;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphPlot extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("K means Plotting");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final ScatterChart<Number,Number> plot =
                new ScatterChart<Number,Number>(xAxis,yAxis);


        LocationClusterer locationClusterer = new LocationClusterer();
        locationClusterer.initialize();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the value of k:");
        int k = scanner.nextInt();

        ArrayList<Cluster> clusters = locationClusterer.cluster(k);

        scanner.close();

        locationClusterer.printClusters();

        int i = 0;

        for(Cluster cluster: clusters)
        {

            XYChart.Series series = new XYChart.Series();
            series.setName("Cluster " + (i+1));

            for(Coordinate coordinate: cluster.getCoordinates())
            {
                series.getData().add(new XYChart.Data(coordinate.getLatitude(),coordinate.getLongitude()));
            }

            plot.getData().add(series);

            i++;
        }

        //bc.getData().add(series1);

        Scene scene  = new Scene(plot,800,600);

        Stage stage1 = new Stage();
        Scene scene1 = BarGraph.centroidBargraph(clusters);
        stage1.setTitle("Centroid Bar Graph");
        stage1.setScene(scene1);

        stage.setScene(scene);

        stage1.setX(50);
        stage1.setY(200);
        stage1.show();

        stage.setX(1000);
        stage.setY(200);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
