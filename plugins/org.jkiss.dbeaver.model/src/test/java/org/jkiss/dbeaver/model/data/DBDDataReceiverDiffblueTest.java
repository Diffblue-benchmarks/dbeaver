package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.model.sql.RowDataReceiver;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDDataReceiverDiffblueTest {
  /**
   * Test {@link DBDDataReceiver#getStatistics()}.
   *
   * <ul>
   *   <li>Then return QueryText is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDataReceiver#getStatistics()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatistics DBDDataReceiver.getStatistics()"})
  public void testGetStatistics_thenReturnQueryTextIsNull() {
    // Arrange
    DBDAttributeBinding[] curAttributes = new DBDAttributeBinding[] {null};
    RowDataReceiver rowDataReceiver = new RowDataReceiver(curAttributes);

    // Act
    DBCStatistics actualStatistics = rowDataReceiver.getStatistics();

    // Assert
    assertNull(actualStatistics.getQueryText());
    assertNull(actualStatistics.getError());
    assertNull(actualStatistics.getMessages());
    assertNull(actualStatistics.getWarnings());
    assertEquals(-1L, actualStatistics.getRowsFetched());
    assertEquals(-1L, actualStatistics.getRowsUpdated());
    assertEquals(0, actualStatistics.getStatementsCount());
    assertEquals(0L, actualStatistics.getExecuteTime());
    assertEquals(0L, actualStatistics.getFetchTime());
    assertEquals(0L, actualStatistics.getTotalTime());
    assertTrue(actualStatistics.getInfo().isEmpty());
    assertTrue(actualStatistics.isEmpty());
  }
}
