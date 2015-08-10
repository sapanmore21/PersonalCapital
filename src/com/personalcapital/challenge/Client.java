package com.personalcapital.challenge;

import com.personalcapital.challenge.model.Portfolio;
import com.personalcapital.challenge.service.PortfolioService;

/**
 * Created by admin on 8/9/15.
 */
public class Client {

    private PortfolioService portfolioService;


    public PortfolioService getPortfolioService() {
        return portfolioService;
    }

    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }



    public static void main(String[] args){
        PortfolioService portfolioService = new PortfolioService();

        //create an aggressive portfolio and analyse it
        Portfolio aggressivePortfolio = new Portfolio(100000,"Aggressive");
        portfolioService.analysePortfolio(aggressivePortfolio);
        portfolioService.printAnalysedPortfolio(aggressivePortfolio);

        //create a conservative portfolio and analyse it
        Portfolio conservativePortfolio = new Portfolio(100000,"Conservative");
        portfolioService.analysePortfolio(conservativePortfolio);
        portfolioService.printAnalysedPortfolio(conservativePortfolio);
    }

    public void analyseAggressivePortfolio(double initialAmt, String pType){

        //PortfolioService portfolioService = new PortfolioService();
        //Portfolio aggressivePortfolio = new Portfolio(100000,"Aggressive");

        portfolioService.analysePortfolio(initialAmt, pType);

        //portfolioService.printAnalysedPortfolio(aggressivePortfolio);
    }


    public void analyseConservativePortfolio(){

        //PortfolioService portfolioService = new PortfolioService();
        Portfolio conservativePortfolio = new Portfolio(100000,"Conservative");
        portfolioService.analysePortfolio(conservativePortfolio);

        //portfolioService.printAnalysedPortfolio(conservativePortfolio);


    }

    public Portfolio createPortfolio(){
        return new Portfolio(100000,"Aggressive");
    }





}
