package com.personalcapital.challenge;

import com.personalcapital.challenge.model.Portfolio;
import com.personalcapital.challenge.model.PortfolioType;
import com.personalcapital.challenge.service.PortfolioService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by admin on 8/10/15.
 */
public class ClientTest {

    private Client client;
    private Portfolio portfolio;
    private PortfolioService portfolioService;
    private double initialAmt = 100000;
    private String pType = "Aggressive";
    //private Account account;
    //private long withdrawlAmount2000 = 2000L;


    @Before
    public void setupMock() {
        client = new Client();
        portfolioService = mock(PortfolioService.class);
        client.setPortfolioService(portfolioService);
        //customer.setAccountManager(accountManager);
       /* portfolio = mock(Portfolio.class);

        //when(Portfolio()).thenReturn(new Portfolio(100000,"Aggressive"));
        //portfolio = new Portfolio(100000,"Aggressive");

        when(portfolio.getInitialAmount()).thenReturn((double) 100000);
        when(portfolio.getPortfolioType()).thenReturn(PortfolioType.Aggressive);
        when(portfolio.getMedian()).thenReturn((double) 150000);
        when(portfolio.getWorstCase()).thenReturn((double) 200000);
        when(portfolio.getBestCase()).thenReturn((double) 300000);
*/
    }



    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMain() throws Exception {

    }

    @Test
    public void testAnalyseAggressivePortfolio() throws Exception {

        //when(portfolioService.analysePortfolio(portfolio)).thenReturn(portfolio);

        //when(accountManager.getBalance(account)).thenReturn(balanceAmount200);



        client.analyseAggressivePortfolio(initialAmt, pType);


        verify(portfolioService).analysePortfolio(initialAmt, pType);

        //verifyNoMoreInteractions(portfolioService);

        //verify(portfolioService).findAccount(customer)

    }

    @Test
    public void testAnalyseConservativePortfolio() throws Exception {

    }
}