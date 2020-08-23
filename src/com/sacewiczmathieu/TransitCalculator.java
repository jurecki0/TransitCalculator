package com.sacewiczmathieu;

import java.util.Arrays;

public class TransitCalculator {
    int daysNumber; //max 30days
    int individualRides;

    public TransitCalculator(int days, int rides) {
        daysNumber = days;
        individualRides = rides;
    }

    //Transit ride prices
    double singleRide = 2.75;
    double unlimitedRides7d = 33.0;
    double unlimitedRides30d = 127.0;

    /*unlimited7Price calculates the price-per-ride for the 7 day Unlimited Ride ticket
    * param: no parameter
    * return: price-per-ride for 7 day Unlimited Ride ticket
    * */
    public double unlimited7Price() {
        double priceForAll = 33.0;
        for (int i = individualRides; i % 7 == 0; i++) {
            int x = i / 7;
            priceForAll = x * priceForAll;
        }
        double pricePerRide = priceForAll / individualRides;
        return pricePerRide;
    }

    /* getRidePrices calculates price-per-ride for all the fare options
    * param: no parameter
    * return: an Array of prices
    * */
    public double[] getRidePrices() {
        double priceSingle = 2.75;
        double price7 = unlimited7Price();
        double price30 = unlimitedRides30d / individualRides;

        double[] prices = {priceSingle, price7, price30};

        return prices;
    }

    /* getBestFare method checks the best fare option using previous methods
    *param: no parameter
    * return: a String with the best fare options and the price for this option.
     */
    public String getBestFare() {

        double lowestPrice = 0;
        double currentLow = 0;
        String bestFareMethod = null;
        String method = null;

        double[] prices = getRidePrices();
        String[] fareOptions = {"Single ride", "7 day Unlimited Ride",
                "30 day Unlimited Ride"};
        int winningIndex = 0;
        currentLow = prices[0];
        for(int i=0; i< prices.length; i++) {

            if (currentLow > prices[i]) {
                currentLow = prices[i];
                winningIndex = i;
            }
            method = fareOptions[winningIndex];
        }
        lowestPrice = Math.round(currentLow*100.0) / 100.0;
        bestFareMethod = method;

        return "You should get the " + bestFareMethod + " option at " +
                lowestPrice + " per ride.";
    }

    public static void main(String[] args) {
        TransitCalculator train = new TransitCalculator(29, 18);
        System.out.println(Arrays.toString(train.getRidePrices()));

        System.out.println(train.getBestFare());
    }
}