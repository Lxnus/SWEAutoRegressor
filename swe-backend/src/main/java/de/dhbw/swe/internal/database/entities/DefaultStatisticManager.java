package de.dhbw.swe.internal.database.entities;

import de.dhbw.swe.implementation.database.SyncRepository;
import de.dhbw.swe.implementation.database.entities.Statistic;
import de.dhbw.swe.main.database.Database;
import de.dhbw.swe.main.database.entities.StatisticManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DefaultStatisticManager implements StatisticManager {

  private final SyncRepository<Statistic> repository;

  @Inject
  public DefaultStatisticManager(Database database) {
    repository = database.createRepository(Statistic.class);
  }

  @Override
  public void update(Statistic statistic) {
    repository.update(statistic);
  }

  @Override
  public Statistic getCurrentStatistic() {
    Statistic statistic = repository.get(0, Statistic.class);
    if(statistic == null) {
      return new Statistic();
    }
    return statistic;
  }
}
