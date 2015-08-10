package com.personalcapital.challenge.challenge.client;

import com.personalcapital.challenge.challenge.model.Portfolio;
import com.personalcapital.challenge.challenge.service.PortfolioService;


public class ExternalClient {

    private PortfolioService portfolioService;

    public PortfolioService getPortfolioService() {
        return portfolioService;
    }

    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    public Portfolio createAndAnalysePortfolio(double initialAmt, String pType){
        if(portfolioService == null){
            portfolioService = new PortfolioService();
        }
        return portfolioService.analysePortfolio(initialAmt, pType);
    }
}
