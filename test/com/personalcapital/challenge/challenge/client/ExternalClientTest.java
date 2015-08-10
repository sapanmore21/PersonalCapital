package com.personalcapital.challenge.challenge.client;

import com.personalcapital.challenge.challenge.client.ExternalClient;
import com.personalcapital.challenge.challenge.model.Portfolio;
import com.personalcapital.challenge.challenge.service.PortfolioService;

import org.junit.*;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.mockito.Mockito.*;

/**
 * Created by admin on 8/10/15.
 */
public class ExternalClientTest {

    private ExternalClient client;
    private Portfolio portfolio;
    private PortfolioService portfolioService;
    private double initialAmt;
    private String pType;

    @Before
    public void setUp() throws Exception {
        initialAmt = 100000;
        pType = "Aggressive";
        client = new ExternalClient();
        portfolioService = mock(PortfolioService.class);
        client.setPortfolioService(portfolioService);
        portfolio = Portfolio.getPortfolio(initialAmt, "Aggressive");
        portfolio.setMedian(1500000);
        portfolio.setBestCase(2000000);
        portfolio.setWorstCase(1000000);

    }

    @After
    public void tearDown() throws Exception {
        pType = null;
        client = null;
        portfolioService = null;
        portfolio = null;
    }

    @Test
    public void testAnalyseAggressivePortfolio() throws Exception {

        when(portfolioService.analysePortfolio(initialAmt, pType)).thenReturn(portfolio);
        Portfolio pfolio = client.createAndAnalysePortfolio(initialAmt, pType);
        Mockito.verify(portfolioService, VerificationModeFactory.times(1)).analysePortfolio(100000, "Aggressive");
        Assert.assertTrue("Portfolio Median should be greater than 0", pfolio.getMedian() > 0);
        Assert.assertTrue("Portfolio BestCase should be greater than 0", pfolio.getBestCase() > 0);
        Assert.assertTrue("Portfolio WorstCase should be greater than 0", pfolio.getWorstCase() > 0);
        Assert.assertTrue("Portfolio BestCase should be greater than Portfolio WorstCase", pfolio.getBestCase() > pfolio.getWorstCase());

    }

    @Test
    public void testAnalyseConservativePortfolio() throws Exception {

    }
}