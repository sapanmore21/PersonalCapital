package com.personalcapital.challenge.service;

import com.personalcapital.challenge.constant.PortfolioConstants;
import com.personalcapital.challenge.model.Portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PortfolioService {
	
	/**
	 * This method analyses the portfolio based on Monte Carlo simulation
	 * @param portfolio
	 * @return AnalysedPortfolio
	 */
	public Portfolio analysePortfolio(Portfolio portfolio){
		
		Random random = new Random();
		double stdDeviation;
		double meanValue;
		double finalReturn=0;
		// start 1st year median Investment amount with initial amount and then re-calculate it every year
		double medianInvestmentAmt = portfolio.getInitialAmount();
		List<Double> investmentAmtList = null;

		// looping through no of years for a given portfolio
		for(int j=0;j< portfolio.getPortfolioType().getDuration(); j++){
			
			investmentAmtList = new ArrayList<Double>();
			meanValue = (portfolio.getPortfolioType().getMean() * medianInvestmentAmt/100);
			finalReturn=0;

			// looping through no of simulations for each year
			for(int i=0;i< PortfolioConstants.NUMBER_OF_SIMULATIONS; i++){
				//random number generation with normal distribution and mean and std-deviation taken from portfolio 
				stdDeviation = random.nextGaussian()*portfolio.getPortfolioType().getStandardDeviation();
				
				//Formula for calculating finalReturn is = standard Deviation * randomly generated number + mean Value
				//final return calculation takes into account inflation
				//Inflation is 3.5% which needs to be deducted from the final amount
				finalReturn = (stdDeviation + meanValue + medianInvestmentAmt) * PortfolioConstants.INFLATION_FACTOR;
				investmentAmtList.add(finalReturn);
			}
			
			//sort the array of values from 10000 simulations
			Collections.sort(investmentAmtList);
			//
			medianInvestmentAmt = calcMedianInvstAmt(investmentAmtList);
		}

		portfolio.setMedian(medianInvestmentAmt);
		portfolio.setBestCase(calcBestCaseInvstAmt(investmentAmtList));
		portfolio.setWorstCase(calcWorstCaseInvstAmt(investmentAmtList));

	return portfolio;
	}


	public Portfolio analysePortfolio(double initialAmt, String pType){

        Portfolio portfolio = new Portfolio(initialAmt, pType);

		Random random = new Random();
		double stdDeviation;
		double meanValue;
		double finalReturn=0;
		// start 1st year median Investment amount with initial amount and then re-calculate it every year
		double medianInvestmentAmt = portfolio.getInitialAmount();
		List<Double> investmentAmtList = null;

		// looping through no of years for a given portfolio
		for(int j=0;j< portfolio.getPortfolioType().getDuration(); j++){

			investmentAmtList = new ArrayList<Double>();
			meanValue = (portfolio.getPortfolioType().getMean() * medianInvestmentAmt/100);
			finalReturn=0;

			// looping through no of simulations for each year
			for(int i=0;i< PortfolioConstants.NUMBER_OF_SIMULATIONS; i++){
				//random number generation with normal distribution and mean and std-deviation taken from portfolio
				stdDeviation = random.nextGaussian()*portfolio.getPortfolioType().getStandardDeviation();

				//Formula for calculating finalReturn is = standard Deviation * randomly generated number + mean Value
				//final return calculation takes into account inflation
				//Inflation is 3.5% which needs to be deducted from the final amount
				finalReturn = (stdDeviation + meanValue + medianInvestmentAmt) * PortfolioConstants.INFLATION_FACTOR;
				investmentAmtList.add(finalReturn);
			}

			//sort the array of values from 10000 simulations
			Collections.sort(investmentAmtList);
			//
			medianInvestmentAmt = calcMedianInvstAmt(investmentAmtList);
		}

		portfolio.setMedian(medianInvestmentAmt);
		portfolio.setBestCase(calcBestCaseInvstAmt(investmentAmtList));
		portfolio.setWorstCase(calcWorstCaseInvstAmt(investmentAmtList));

		return portfolio;
	}


	// method used for calculating median investment amount for each year
    private static double calcMedianInvstAmt(List<Double> investmentAmtList){
	    //take the median amount based on the number of values
		if(investmentAmtList.size()%2 ==0){
			return (investmentAmtList.get(investmentAmtList.size()/2) + investmentAmtList.get(investmentAmtList.size()/2 -1))/2;
		} else{
			return investmentAmtList.get(investmentAmtList.size()/2);
		}
	}

	//calculating best case for last year
	//10% Best Case :  90th Percentile value among the 10,000 simulations
	private static double calcBestCaseInvstAmt(List<Double> investmentAmtList){
		int bestCaseIndex = (int) (investmentAmtList.size() * PortfolioConstants.BEST_CASE_FACTOR);
		return investmentAmtList.get(bestCaseIndex-1);
	}

	//calculating worst case for last year
	//10% Worst Case:  10th Percentile value among the 10,000 simulations
	private static double calcWorstCaseInvstAmt(List<Double> investmentAmtList){
		int worstCaseIndex = (int) (investmentAmtList.size() * PortfolioConstants.WORST_CASE_FACTOR);
		return investmentAmtList.get(worstCaseIndex-1);
	}

	/**
	 * This method prints the analysed portfolio information
	 * @param portfolio
	 */
	public void printAnalysedPortfolio(Portfolio portfolio){

		System.out.println("\n*-*-*-*-*-*-*-*-*-*-Analysed portfolio-*-*-*-*-*-*-*-*-*-*");

		System.out.println("PortfolioType: " + portfolio.getPortfolioType());
		System.out.println("Portfolio Amount: $" + portfolio.getInitialAmount());
		System.out.println("Median 20th year: " + portfolio.getMedian());
		System.out.println("10% Best Case: " + portfolio.getBestCase());
		System.out.println("10% Worst Case: " + portfolio.getWorstCase());
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
	}
	

}
