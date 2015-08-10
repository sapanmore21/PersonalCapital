package com.personalcapital.challenge.model;

public class Portfolio {
	
	private double initialAmount;
	private PortfolioType portfolioType;
	private double median;
	private double bestCase;
	private double worstCase;

	//Constructor which takes initialAmount and portfolioType
	public Portfolio(double initialAmount, String portfolioType){

        if(portfolioType == null || portfolioType.isEmpty()){
            throw new IllegalArgumentException("Portfolio Type cannot be null/empty");
        }
        if(initialAmount <= 0){
            throw new IllegalArgumentException("InitialAmount cannot be less than or equal to zero");
        }

        this.initialAmount = initialAmount;
		PortfolioType pType = PortfolioType.getPortfolioTypeByName(portfolioType);
		this.portfolioType = pType;

	}
	
	public double getInitialAmount() {
		return initialAmount;
	}

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }


    public PortfolioType getPortfolioType() {
		return portfolioType;
	}
	
	public void setPortfolioType(PortfolioType portfolioType) {
		this.portfolioType = portfolioType;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public double getBestCase() {
		return bestCase;
	}

	public void setBestCase(double bestCase) {
		this.bestCase = bestCase;
	}

	public double getWorstCase() {
		return worstCase;
	}

	public void setWorstCase(double worstCase) {
		this.worstCase = worstCase;
	}

	
}
