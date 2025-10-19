package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTTaskRunStatusDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBTTaskRunStatus}
   *   <li>{@link DBTTaskRunStatus#setResultMessage(String)}
   *   <li>{@link DBTTaskRunStatus#getResultMessage()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTTaskRunStatus.<init>()",
    "String DBTTaskRunStatus.getResultMessage()",
    "void DBTTaskRunStatus.setResultMessage(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBTTaskRunStatus actualDbtTaskRunStatus = new DBTTaskRunStatus();
    actualDbtTaskRunStatus.setResultMessage("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualDbtTaskRunStatus.getResultMessage());
  }

  /**
   * Test {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>Then return ResultMessage is {@code rows fetched: 1}.
   * </ul>
   *
   * <p>Method under test: {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskRunStatus DBTTaskRunStatus.makeStatisticsStatus(DBCStatistics)"})
  public void testMakeStatisticsStatus_givenMinusOne_thenReturnResultMessageIsRowsFetched1() {
    // Arrange
    DBCStatistics statistics = new DBCStatistics();
    statistics.setRowsFetched(1L);
    statistics.addRowsUpdated(-1L);

    // Act
    DBTTaskRunStatus actualMakeStatisticsStatusResult =
        DBTTaskRunStatus.makeStatisticsStatus(statistics);

    // Assert
    assertEquals("rows fetched: 1", actualMakeStatisticsStatusResult.getResultMessage());
  }

  /**
   * Test {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}.
   *
   * <ul>
   *   <li>Then return ResultMessage is {@code queries: 1}.
   * </ul>
   *
   * <p>Method under test: {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskRunStatus DBTTaskRunStatus.makeStatisticsStatus(DBCStatistics)"})
  public void testMakeStatisticsStatus_thenReturnResultMessageIsQueries1() {
    // Arrange
    DBCStatistics statistics = new DBCStatistics();
    statistics.addStatementsCount();

    // Act
    DBTTaskRunStatus actualMakeStatisticsStatusResult =
        DBTTaskRunStatus.makeStatisticsStatus(statistics);

    // Assert
    assertEquals("queries: 1", actualMakeStatisticsStatusResult.getResultMessage());
  }

  /**
   * Test {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}.
   *
   * <ul>
   *   <li>Then return ResultMessage is {@code rows modified: 1}.
   * </ul>
   *
   * <p>Method under test: {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskRunStatus DBTTaskRunStatus.makeStatisticsStatus(DBCStatistics)"})
  public void testMakeStatisticsStatus_thenReturnResultMessageIsRowsModified1() {
    // Arrange
    DBCStatistics statistics = new DBCStatistics();
    statistics.addRowsUpdated(1L);

    // Act
    DBTTaskRunStatus actualMakeStatisticsStatusResult =
        DBTTaskRunStatus.makeStatisticsStatus(statistics);

    // Assert
    assertEquals("rows modified: 1", actualMakeStatisticsStatusResult.getResultMessage());
  }

  /**
   * Test {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}.
   *
   * <ul>
   *   <li>Then return ResultMessage is {@code rows modified: 1, queries: 1}.
   * </ul>
   *
   * <p>Method under test: {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskRunStatus DBTTaskRunStatus.makeStatisticsStatus(DBCStatistics)"})
  public void testMakeStatisticsStatus_thenReturnResultMessageIsRowsModified1Queries1() {
    // Arrange
    DBCStatistics statistics = new DBCStatistics();
    statistics.addRowsUpdated(1L);
    statistics.addStatementsCount();

    // Act
    DBTTaskRunStatus actualMakeStatisticsStatusResult =
        DBTTaskRunStatus.makeStatisticsStatus(statistics);

    // Assert
    assertEquals(
        "rows modified: 1, queries: 1", actualMakeStatisticsStatusResult.getResultMessage());
  }

  /**
   * Test {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}.
   *
   * <ul>
   *   <li>When {@link DBCStatistics} (default constructor).
   *   <li>Then return ResultMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBTTaskRunStatus#makeStatisticsStatus(DBCStatistics)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskRunStatus DBTTaskRunStatus.makeStatisticsStatus(DBCStatistics)"})
  public void testMakeStatisticsStatus_whenDBCStatistics_thenReturnResultMessageIsNull() {
    // Arrange and Act
    DBTTaskRunStatus actualMakeStatisticsStatusResult =
        DBTTaskRunStatus.makeStatisticsStatus(new DBCStatistics());

    // Assert
    assertNull(actualMakeStatisticsStatusResult.getResultMessage());
  }
}
