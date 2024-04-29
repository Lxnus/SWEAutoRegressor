package de.dhbw.swe.main.statistic;

public interface StatisticService {

  void update(Statistic statistic);

  Statistic getCurrentStatistic();
}
