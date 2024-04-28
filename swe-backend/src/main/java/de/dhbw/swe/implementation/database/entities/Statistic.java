package de.dhbw.swe.implementation.database.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Statistics")
public class Statistic {

  @Id
  private long id = 0;

  private int linearClassifierOnline;
  private int linearClassifiersCreated;

  public Statistic() {}

  public void adjustLinearClassifierOnline(int adjustment) {
    linearClassifierOnline += adjustment;
  }

  public void adjustLinearClassifierCreated(int adjustment) {
    linearClassifiersCreated += adjustment;
  }
}
