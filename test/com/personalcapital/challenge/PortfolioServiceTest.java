package com.personalcapital.challenge;

import com.personalcapital.challenge.model.Portfolio;
import com.personalcapital.challenge.model.PortfolioType;
import com.personalcapital.challenge.service.PortfolioService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.jvm.hotspot.utilities.AssertionFailure;

/**
 * Created by admin on 8/9/15.
 */
public class PortfolioServiceTest {

    private Portfolio aggressivePortfolio;

    @Before
    public void setUp() throws Exception {
        aggressivePortfolio = new Portfolio(100000, "Aggressive");
    }

    @After
    public void tearDown() throws Exception {
        aggressivePortfolio = null;
    }



    @Test
    public void testPortfolioObjectCreation() throws Exception {
        Assert.assertNotNull(aggressivePortfolio);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPortfolioTypeShouldNotBeNull() throws Exception {
        aggressivePortfolio = new Portfolio(100000, null);
        Assert.fail("Portfolio should not be created with null PortfolioType");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialAmtShouldNotBeZeroOrLess() throws Exception {
        aggressivePortfolio = new Portfolio(0, "Aggressive");
        Assert.fail("Portfolio should not be created with InitialAmount less than or equal to zero");
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

    @Test(expected = IllegalArgumentException.class)
    public void testNotAValidPortfolioType() throws Exception {
        aggressivePortfolio = new Portfolio(100000, "Moderate");
        Assert.fail("Moderate is not a valid Portfolio type");
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