package com.personalcapital.challenge.challenge;

import com.personalcapital.challenge.challenge.model.Portfolio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by admin on 8/9/15.
 */
public class PortfolioServiceTest {

    private Portfolio aggressivePortfolio;

    @Before
    public void setUp() throws Exception {
        aggressivePortfolio = Portfolio.getPortfolio(100000, "Aggressive");
    }

    @After
    public void tearDown() throws Exception {
        aggressivePortfolio = null;
    }



    @Test
    public void testPortfolioObjectCreation() throws Exception {
        Assert.assertNotNull(aggressivePortfolio);
    }

    @Test
    public void testPortfolioTypeShouldNotBeNull() throws Exception {
        aggressivePortfolio = Portfolio.getPortfolio(100000, null);
        Assert.assertNull(aggressivePortfolio);
    }

    @Test
    public void testInitialAmtShouldNotBeZeroOrLess() throws Exception {
        aggressivePortfolio = Portfolio.getPortfolio(0, "Aggressive");
        Assert.assertNull(aggressivePortfolio);
    }

    @Test
    public void testPortfolioInitialAmount() throws Exception {
        Assert.assertEquals("Portfolio should have initial amount as 100000", 0, Double.compare(aggressivePortfolio.getInitialAmount(), 100000.0));
    }

    @Test
    public void testPortfolioTypeIsAggressive() throws Exception {
        Assert.assertEquals("Portfolio should have Portfolio Type as Aggressive", "Aggressive", aggressivePortfolio.getPortfolioType().name());
    }

    @Test
    public void testPortfolioTypeIsNotConservative() throws Exception {
        Assert.assertNotEquals("Portfolio should not have Portfolio Type as Conservative", "Conservative", aggressivePortfolio.getPortfolioType().name());
    }

    @Test
    public void testNotAValidPortfolioType() throws Exception {
        aggressivePortfolio = Portfolio.getPortfolio(100000, "Moderate");
        Assert.assertNull(aggressivePortfolio);
    }

    @Test
    public void testPortfolioDuration() throws Exception {
        Assert.assertEquals("Portfolio should have duration as 20", 20, aggressivePortfolio.getPortfolioType().getDuration());
    }

    @Test
    public void testPortfolioMean() throws Exception {
        Assert.assertEquals("Portfolio should have mean as 9.4324", 0, Double.compare(aggressivePortfolio.getPortfolioType().getMean(), 9.4324));
    }

    @Test
    public void testPortfolioStandardDeviation() throws Exception {
        Assert.assertEquals("Portfolio should have standard deviation as 15.675", 0, Double.compare(aggressivePortfolio.getPortfolioType().getStandardDeviation(), 15.675));
    }

    @Test
    public void testPortfolioMedian() throws Exception {
        Assert.assertNotNull("Portfolio Median should be null", aggressivePortfolio.getMedian());

    }

    @Test
    public void testPortfolioBestCase() throws Exception {
        Assert.assertNotNull("Portfolio Best case should be null", aggressivePortfolio.getBestCase());
    }


    @Test
    public void testPortfolioWorstCase() throws Exception {
        Assert.assertNotNull("Portfolio Worst case should be null", aggressivePortfolio.getWorstCase());
    }
}