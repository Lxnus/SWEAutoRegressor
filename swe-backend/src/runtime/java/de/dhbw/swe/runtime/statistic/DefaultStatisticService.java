package de.dhbw.swe.runtime.statistic;

import de.dhbw.swe.main.database.SyncRepository;
import de.dhbw.swe.main.statistic.Statistic;
import de.dhbw.swe.main.database.Database;
import de.dhbw.swe.main.statistic.StatisticService;
import de.dhbw.swe.runtime.inject.AutoBind;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@AutoBind(StatisticService.class)
public class DefaultStatisticService implements StatisticService {

  private final SyncRepository<Statistic> repository;

  @Inject
  public DefaultStatisticService(Database database) {
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
