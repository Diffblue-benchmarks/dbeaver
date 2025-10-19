package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBFetchProgressDiffblueTest {
  @InjectMocks private DBFetchProgress dBFetchProgress;

  @Mock private DBRProgressMonitor dBRProgressMonitor;

  /**
   * Test {@link DBFetchProgress#DBFetchProgress(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBFetchProgress#DBFetchProgress(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFetchProgress.<init>(DBRProgressMonitor)"})
  public void testNewDBFetchProgress() {
    // Arrange, Act and Assert
    assertEquals(
        DBPDataSourceProvider.FEATURE_NONE,
        new DBFetchProgress(new LoggingProgressMonitor()).getRowCount());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBFetchProgress#getRowCount()}
   *   <li>{@link DBFetchProgress#getStartTime()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBFetchProgress.getRowCount()", "long DBFetchProgress.getStartTime()"})
  public void testGettersAndSetters() {
    // Arrange
    DBFetchProgress dbFetchProgress = new DBFetchProgress(new LoggingProgressMonitor());

    // Act
    long actualRowCount = dbFetchProgress.getRowCount();
    dbFetchProgress.getStartTime();

    // Assert
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, actualRowCount);
  }

  /**
   * Test {@link DBFetchProgress#monitorRowFetch()}.
   *
   * <p>Method under test: {@link DBFetchProgress#monitorRowFetch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFetchProgress.monitorRowFetch()"})
  public void testMonitorRowFetch() {
    // Arrange
    DBFetchProgress dbFetchProgress = new DBFetchProgress(new LoggingProgressMonitor());

    // Act
    dbFetchProgress.monitorRowFetch();

    // Assert
    assertEquals(DBPDataSourceProvider.FEATURE_CATALOGS, dbFetchProgress.getRowCount());
  }

  /**
   * Test {@link DBFetchProgress#dumpStatistics(DBCStatistics)}.
   *
   * <ul>
   *   <li>Then {@link DBCStatistics} (default constructor) StatementsCount is one.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#dumpStatistics(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFetchProgress.dumpStatistics(DBCStatistics)"})
  public void testDumpStatistics_thenDBCStatisticsStatementsCountIsOne() {
    // Arrange
    DBFetchProgress dbFetchProgress = new DBFetchProgress(new LoggingProgressMonitor());
    DBCStatistics statistics = new DBCStatistics();

    // Act
    dbFetchProgress.dumpStatistics(statistics);

    // Assert
    assertEquals(1, statistics.getStatementsCount());
    assertFalse(statistics.isEmpty());
    assertEquals(DBPDataSourceProvider.FEATURE_NONE, statistics.getRowsFetched());
  }

  /**
   * Test {@link DBFetchProgress#isCanceled()}.
   *
   * <ul>
   *   <li>Given {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#isCanceled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.isCanceled()"})
  public void testIsCanceled_givenDBRProgressMonitorIsCanceledReturnFalse_thenReturnFalse() {
    // Arrange
    when(dBRProgressMonitor.isCanceled()).thenReturn(false);

    // Act
    boolean actualIsCanceledResult = dBFetchProgress.isCanceled();

    // Assert
    verify(dBRProgressMonitor).isCanceled();
    assertFalse(actualIsCanceledResult);
  }

  /**
   * Test {@link DBFetchProgress#isCanceled()}.
   *
   * <ul>
   *   <li>Given {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#isCanceled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.isCanceled()"})
  public void testIsCanceled_givenDBRProgressMonitorIsCanceledReturnTrue_thenReturnTrue() {
    // Arrange
    when(dBRProgressMonitor.isCanceled()).thenReturn(true);

    // Act
    boolean actualIsCanceledResult = dBFetchProgress.isCanceled();

    // Assert
    verify(dBRProgressMonitor).isCanceled();
    assertTrue(actualIsCanceledResult);
  }

  /**
   * Test {@link DBFetchProgress#isMaxRowsFetched(long)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSourceProvider#FEATURE_CATALOGS}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#isMaxRowsFetched(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.isMaxRowsFetched(long)"})
  public void testIsMaxRowsFetched_whenFeature_catalogs() {
    // Arrange, Act and Assert
    assertFalse(
        new DBFetchProgress(new LoggingProgressMonitor())
            .isMaxRowsFetched(DBPDataSourceProvider.FEATURE_CATALOGS));
  }

  /**
   * Test {@link DBFetchProgress#isMaxRowsFetched(long)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSourceProvider#FEATURE_NONE}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#isMaxRowsFetched(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.isMaxRowsFetched(long)"})
  public void testIsMaxRowsFetched_whenFeature_none() {
    // Arrange, Act and Assert
    assertFalse(
        new DBFetchProgress(new LoggingProgressMonitor())
            .isMaxRowsFetched(DBPDataSourceProvider.FEATURE_NONE));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When {@code 99999}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_when99999_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBFetchProgress.monitorFetchProgress(99999L));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When {@code 100000}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_when100000_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBFetchProgress.monitorFetchProgress(100000L));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When {@code 999999}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_when999999_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBFetchProgress.monitorFetchProgress(999999L));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When {@code 1000000}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_when1000000_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBFetchProgress.monitorFetchProgress(1000000L));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSourceProvider#FEATURE_CATALOGS}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_whenFeature_catalogs_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBFetchProgress.monitorFetchProgress(DBPDataSourceProvider.FEATURE_CATALOGS));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_whenMax_value_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBFetchProgress.monitorFetchProgress(Long.MAX_VALUE));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When one hundred.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_whenOneHundred_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBFetchProgress.monitorFetchProgress(100L));
  }

  /**
   * Test {@link DBFetchProgress#monitorFetchProgress(long)}.
   *
   * <ul>
   *   <li>When one thousand.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBFetchProgress#monitorFetchProgress(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBFetchProgress.monitorFetchProgress(long)"})
  public void testMonitorFetchProgress_whenOneThousand_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DBFetchProgress.monitorFetchProgress(1000L));
  }
}
