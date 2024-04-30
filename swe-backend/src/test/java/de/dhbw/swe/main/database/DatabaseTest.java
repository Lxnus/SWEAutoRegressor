package de.dhbw.swe.main.database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DatabaseTest {

        private Database database;

        @Before
        public void setUp() {
            database = Mockito.mock(Database.class);
        }

        @Test
        public void testInit() {
            Class<?>[] classes = new Class<?>[0];
            database.init(classes);
            Mockito.verify(database).init(classes);
        }

        @Test
        public void testCreateRepository() {
            Class<?> clazz = null;
            database.createRepository(clazz);
            Mockito.verify(database).createRepository(clazz);
        }

        @Test
        public void testGetDriver() {
            database.getDriver();
            Mockito.verify(database).getDriver();
        }
}
