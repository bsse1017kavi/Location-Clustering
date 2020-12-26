package mainPackage;

import locationPackage.LocationClusterer;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        int k;

        Scanner scanner = new Scanner(System.in);

        LocationClusterer locationClusterer = new LocationClusterer();

        locationClusterer.initialize();

        System.out.println("Give the value of k:");

        k = scanner.nextInt();

        locationClusterer.cluster(k);

        locationClusterer.printClusters();
    }
}
