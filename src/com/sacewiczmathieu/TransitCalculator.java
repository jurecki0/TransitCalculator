package com.sacewiczmathieu;

import java.util.Arrays;

public class TransitCalculator {
    int daysNumber; //max 30days
    int individualRides;
    int passengerAge;
    boolean isDisabled;

    //Transit ride prices
    double singleRide;
    double unlimitedRides7d;
    double unlimitedRides30d;

    //Constructor
    public TransitCalculator(int days, int rides, int age, boolean disabled) {
        daysNumber = days;
        individualRides = rides;
        passengerAge = age;
        isDisabled = disabled;
    }

    /*isEligible method checks if people have disabilities and/or age above 65 to get discounts
     * Single Ride = 1.35
     * 7d Unlimited Ride 16.50
     * 30d Unlimited Ride 63.50
     * param: no param
     * return: void
     * */
    public void isEligible() {
        if(isDisabled || passengerAge>=65) {
            singleRide = 1.35;
            unlimitedRides7d = 16.50;
            unlimitedRides30d = 63.50;
        }
        else {
            singleRide = 2.75;
            unlimitedRides7d = 33.0;
            unlimitedRides30d = 127.0;
        }
    }

    /*unlimited7Price calculates the price-per-ride for the 7 day Unlimited Ride ticket
    * param: no parameter
    * return: price-per-ride for 7 day Unlimited Ride ticket
    * */
    public double unlimited7Price() {
        isEligible();

        double priceForAll = unlimitedRides7d;
        for (int i = individualRides; i % 7 == 0; i++) {
            int x = i / 7;
            priceForAll = x * priceForAll;
        }
        return priceForAll / individualRides;
    }

    /* getRidePrices calculates price-per-ride for all the fare options
    * param: no parameter
    * return: an Array of prices
    * */
    public double[] getRidePrices() {
        isEligible();

        double priceSingle = singleRide;
        double price7 = unlimited7Price();
        double price30 = unlimitedRides30d / individualRides;

        return new double[]{priceSingle, price7, price30};
    }

    /* getBestFare method checks the best fare option using previous methods
    *param: no parameter
    * return: a String with the best fare options and the price for this option.
     */
    public String getBestFare() {

        double lowestPrice;
        double currentLow;
        String bestFareMethod;
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
        TransitCalculator train = new TransitCalculator(29, 18, 77, false);
        System.out.println(Arrays.toString(train.getRidePrices()));

        System.out.println(train.getBestFare());
    }
}