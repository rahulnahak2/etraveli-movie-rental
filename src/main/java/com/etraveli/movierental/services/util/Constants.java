package com.etraveli.movierental.services.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public final String CUSTOMER_DETAIL_STATEMENT="Rental Record for %s\n";
    public final String TOTAL_AMOUNT_STATEMENT="Amount owed is %.2f%n";
    public final String FREQUENT_POINT_STATEMENT="You earned %d frequent points%n";
    public final int BASE_DAYS_FOR_CATAGORY_CHILDREN=3;
    public final double BASE_RATE_FOR_CATAGORY_CHILDREN=1.50;
    public final double EXTRA_RATE_PER_DAY_CHILDREN=1.50;
    public final double PER_DAY_RATE_CATAGORY_NEW=3.00;
    public final int BASE_DAYS_FOR_CATAGORY_REGULAR=2;
    public final double BASE_RATE_FOR_CATAGORY_REGULAR=2.00;
    public final double EXTRA_RATE_PER_DAY_REGULAR=1.5;
    public final int DAYS_TO_EARN_BASE_FREQUENT_POINT=2;
    public final int BASE_FREQUENT_POINTS=1;
    public final int EXTRA_FREQUENT_POINTS=2;
}
