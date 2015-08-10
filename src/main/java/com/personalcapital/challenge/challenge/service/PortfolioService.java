package com.personalcapital.challenge.challenge.service;

import com.personalcapital.challenge.challenge.constant.PortfolioConstants;
import com.personalcapital.challenge.challenge.model.Portfolio;
import com.personalcapital.challenge.challenge.util.PortfolioUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PortfolioService {

	/**
	 * This method analyses the portfolio based on Monte Carlo simulation
	 * @param Double initialAmt,
	 * @param String pType
	 * @return AnalysedPortfolio
	 */
	public Portfolio analysePortfolio(double initialAmt, String pType){

		// we should not create a new object here, it is not this methods job, it should ask for object from someone
        //Portfolio portfolio = new Portfolio(initialAmt, pType);
		Portfolio portfolio = Portfolio.getPortfolio(initialAmt, pType);

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
			meanValue = PortfolioUtils.calculateMeanValue(portfolio.getPortfolioType().getMean(), medianInvestmentAmt);
			finalReturn=0;

			// looping through no of simulations for each year
			for(int i=0;i< PortfolioConstants.NUMBER_OF_SIMULATIONS; i++){
				//random number generation with normal distribution and mean and std-deviation taken from portfolio
				stdDeviation = random.nextGaussian()*portfolio.getPortfolioType().getStandardDeviation();
				/*
				 *Formula for calculating finalReturn is = standard Deviation * randomly generated number + mean Value
				 *final return calculation takes into account inflation
				 *Inflation is 3.5% which needs to be deducted from the final amount
				 */
				finalReturn = (stdDeviation + meanValue + medianInvestmentAmt) * PortfolioConstants.INFLATION_FACTOR;
				investmentAmtList.add(finalReturn);
			}
			//sort the array of values from 10000 simulations
			Collections.sort(investmentAmtList);
			//
			medianInvestmentAmt = PortfolioUtils.calcMedianInvstAmt(investmentAmtList);
		}

		portfolio.setMedian(medianInvestmentAmt);
		portfolio.setBestCase(PortfolioUtils.calcBestCaseInvstAmt(investmentAmtList));
		portfolio.setWorstCase(PortfolioUtils.calcWorstCaseInvstAmt(investmentAmtList));

		return portfolio;
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
