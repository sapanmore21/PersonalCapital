package com.personalcapital.challenge.challenge.client;

import com.personalcapital.challenge.challenge.model.Portfolio;
import com.personalcapital.challenge.challenge.service.PortfolioService;

import org.junit.*;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.mockito.Mockito.*;


public class ExternalClientTest {

    private ExternalClient client;
    private Portfolio portfolio;
    private PortfolioService portfolioService;

    @Before
    public void setUp() throws Exception {
        client = new ExternalClient();
        portfolioService = mock(PortfolioService.class);
        client.setPortfolioService(portfolioService);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
        portfolioService = null;
        portfolio = null;
    }

    @Test
    public void testAggressivePortfolioIsCreated() throws Exception {

        double initialAmt = 100000;
        String pType = "Aggressive";

        portfolio = Portfolio.getPortfolio(100000, "Aggressive");
        portfolio.setMedian(1500000);
        portfolio.setBestCase(2000000);
        portfolio.setWorstCase(1000000);

        // behavior verification
        when(portfolioService.analysePortfolio(initialAmt, pType)).thenReturn(portfolio);
        Portfolio pfolio = client.createAndAnalysePortfolio(initialAmt, pType);
        Mockito.verify(portfolioService, VerificationModeFactory.times(1)).analysePortfolio(100000, "Aggressive");

        verifyNoMoreInteractions(portfolioService);

        // state verification
        Assert.assertEquals("Portfolio created is Aggresive portfolio", "Aggressive", pfolio.getPortfolioType().name());
        Assert.assertTrue("Portfolio Median should be greater than 0", pfolio.getMedian() > 0);
        Assert.assertTrue("Portfolio BestCase should be greater than 0", pfolio.getBestCase() > 0);
        Assert.assertTrue("Portfolio WorstCase should be greater than 0", pfolio.getWorstCase() > 0);
        Assert.assertTrue("Portfolio BestCase should be greater than Portfolio WorstCase", pfolio.getBestCase() > pfolio.getWorstCase());
    }

    @Test
    public void testAggressivePortfolioIsNotCreated() throws Exception {

        double initialAmt = 100000;
        String pType = "Aggressive";

        portfolio = Portfolio.getPortfolio(100000, "Aggressive");
        portfolio.setMedian(1500000);
        portfolio.setBestCase(2000000);
        portfolio.setWorstCase(1000000);

        // behavior verification
        when(portfolioService.analysePortfolio(initialAmt, pType)).thenReturn(portfolio);
        Portfolio pfolio = client.createAndAnalysePortfolio(initialAmt, "Conservative");

        Assert.assertNull("Aggressive portfolio cannot be created as passing Convervative as a type, where as expected is Aggressive", pfolio);
    }

    @Test
    public void testConservativePortfolioIsCreated() throws Exception {

        double initialAmt = 100000;
        String pType = "Conservative";

        portfolio = Portfolio.getPortfolio(100000, "Conservative");
        portfolio.setMedian(1500000);
        portfolio.setBestCase(2000000);
        portfolio.setWorstCase(1000000);

        // behavior verification
        when(portfolioService.analysePortfolio(initialAmt, pType)).thenReturn(portfolio);
        Portfolio pfolio = client.createAndAnalysePortfolio(initialAmt, pType);
        Mockito.verify(portfolioService, VerificationModeFactory.times(1)).analysePortfolio(100000, "Conservative");

        // state verification
        Assert.assertEquals("Portfolio created is Conservative portfolio", "Conservative", pfolio.getPortfolioType().name());
        Assert.assertTrue("Portfolio Median should be greater than 0", pfolio.getMedian() > 0);
        Assert.assertTrue("Portfolio BestCase should be greater than 0", pfolio.getBestCase() > 0);
        Assert.assertTrue("Portfolio WorstCase should be greater than 0", pfolio.getWorstCase() > 0);
        Assert.assertTrue("Portfolio BestCase should be greater than Portfolio WorstCase", pfolio.getBestCase() > pfolio.getWorstCase());
    }


    @Test
    public void testConservativePortfolioIsNotCreated() throws Exception {

        double initialAmt = 100000;
        String pType = "Conservative";

        portfolio = Portfolio.getPortfolio(100000, "Conservative");
        portfolio.setMedian(1500000);
        portfolio.setBestCase(2000000);
        portfolio.setWorstCase(1000000);

        // behavior verification
        when(portfolioService.analysePortfolio(initialAmt, pType)).thenReturn(portfolio);
        Portfolio pfolio = client.createAndAnalysePortfolio(initialAmt, "Aggressive");

        Assert.assertNull("Conservative portfolio cannot be created as passing Aggressive as a type, where as expected is Conservative", pfolio);
    }

}