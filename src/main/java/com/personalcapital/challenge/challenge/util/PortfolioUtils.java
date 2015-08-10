package com.personalcapital.challenge.challenge.util;

import com.personalcapital.challenge.challenge.constant.PortfolioConstants;

import java.util.List;

/**
 * Created by smore on 8/10/2015.
 */
public class PortfolioUtils {

    // method used for calculating median investment amount for each year
    public static double calcMedianInvstAmt(List<Double> investmentAmtList){
        //take the median amount based on the number of values
        if(investmentAmtList.size()%2 ==0){
            return (investmentAmtList.get(investmentAmtList.size()/2) + investmentAmtList.get(investmentAmtList.size()/2 -1))/2;
        } else{
            return investmentAmtList.get(investmentAmtList.size()/2);
        }
    }

    //calculating best case for last year
    //10% Best Case :  90th Percentile value among the 10,000 simulations
    public static double calcBestCaseInvstAmt(List<Double> investmentAmtList){
        int bestCaseIndex = (int) (investmentAmtList.size() * PortfolioConstants.BEST_CASE_FACTOR);
        return investmentAmtList.get(bestCaseIndex-1);
    }

    //calculating worst case for last year
    //10% Worst Case:  10th Percentile value among the 10,000 simulations
    public static double calcWorstCaseInvstAmt(List<Double> investmentAmtList){
        int worstCaseIndex = (int) (investmentAmtList.size() * PortfolioConstants.WORST_CASE_FACTOR);
        return investmentAmtList.get(worstCaseIndex-1);
    }

    public static double calculateMeanValue(double meanValue, double medianInvestmentAmt){
        return meanValue * medianInvestmentAmt/100;
    }



}
