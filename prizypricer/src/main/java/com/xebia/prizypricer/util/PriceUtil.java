package com.xebia.prizypricer.util;

import java.util.ArrayList;
import java.util.List;

import com.xebia.prizypricer.model.Prices;

public class PriceUtil {
	
	public static String calculateMaxPrice(List<Integer> prices) {
		int maxValue = 0;
		for(Integer i : prices) {
			if(i > maxValue) {
				maxValue = i;
			}
		}
		return String.valueOf(maxValue);
	}
	
	public static String calculateMinPrice(List<Integer> prices) {
		int minValue = prices.get(0);
		for(Integer i : prices) {
			if(i < minValue) {
				minValue = i;
			}
		}
		return String.valueOf(minValue);
	}
	
	public static String calculateAvgPrice(List<Integer> prices) {
		int avgValue = 0;
		for(Integer i : prices) {
			avgValue +=  i;
		}
		return String.valueOf(avgValue/prices.size());
	}
	
	public static String calculateIdealPrice(List<Integer> prices) {
		
		int idealValue,max1,max2,min1,min2;
		int sum = 0;
		max1 = max2 = Integer.MIN_VALUE; 
		min1 = min2 = Integer.MAX_VALUE;
		
		if(prices.size() < 5) {
			return calculateAvgPrice(prices);
		}
		else {
		for (Integer i : prices) {
            if (i > max1) { 
                max2 = max1; 
                max1 = i; 
            } 
            else if (i > max2) {
                max2 = i; 
            } 
            if (i < min1) { 
                min2 = min1; 
                min1 = i; 
            } 
            else if (i < min2) {
                min2 = i; 
            }
            sum += i;
        } 
		idealValue = (sum - max1 - max2 - min1 - min2 / (prices.size() - 4)) + 20/100 * prices.size();
		return String.valueOf(idealValue);
		}
		
	}
	
	public static List<Integer> getPricesList(List<Prices> prices) {
		List<Integer> pricesList = new ArrayList<Integer>();
		for(Prices p : prices) {
			pricesList.add(p.getPrice());
		}
		return pricesList;
	}
	
}
