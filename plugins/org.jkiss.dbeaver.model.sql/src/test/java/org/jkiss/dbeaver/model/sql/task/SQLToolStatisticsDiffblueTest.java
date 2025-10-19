package org.jkiss.dbeaver.model.sql.task;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute.PropagationPolicy;
import org.jkiss.dbeaver.model.data.DBDPseudoAttributeType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLToolStatisticsDiffblueTest {
  /**
   * Test {@link SQLToolStatistics#getExecutionTime()}.
   *
   * <p>Method under test: {@link SQLToolStatistics#getExecutionTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long SQLToolStatistics.getExecutionTime()"})
  public void testGetExecutionTime() {
    // Arrange
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);

    // Act and Assert
    assertEquals(0L, new SQLToolStatisticsSimple(object, true).getExecutionTime());
  }

  /**
   * Test {@link SQLToolStatistics#setExecutionTime(long)}.
   *
   * <p>Method under test: {@link SQLToolStatistics#setExecutionTime(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLToolStatistics.setExecutionTime(long)"})
  public void testSetExecutionTime() {
    // Arrange
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);
    SQLToolStatisticsSimple sqlToolStatisticsSimple = new SQLToolStatisticsSimple(object, true);

    // Act
    sqlToolStatisticsSimple.setExecutionTime(1L);

    // Assert
    assertEquals(1L, sqlToolStatisticsSimple.getExecutionTime());
  }
}
