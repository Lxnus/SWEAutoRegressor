package de.dhbw.swe.main.statistic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StatisticServiceTest {

    private StatisticService statisticService;
    private Statistic statistic;

    @Before
    public void setUp() {
        statisticService = Mockito.mock(StatisticService.class);
        statistic = Mockito.mock(Statistic.class);
    }

    @Test
    public void testUpdate() {
        statisticService.update(statistic);
        Mockito.verify(statisticService).update(statistic);
    }

    @Test
    public void testGetCurrentStatistic() {
        statisticService.getCurrentStatistic();
        Mockito.verify(statisticService).getCurrentStatistic();
    }
}
