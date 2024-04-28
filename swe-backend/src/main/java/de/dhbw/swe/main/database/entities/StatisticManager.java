package de.dhbw.swe.main.database.entities;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.implementation.database.entities.Statistic;
import de.dhbw.swe.internal.database.entities.DefaultStatisticManager;

@ImplementedBy(DefaultStatisticManager.class)
public interface StatisticManager {

  void update(Statistic statistic);

  Statistic getCurrentStatistic();
}
