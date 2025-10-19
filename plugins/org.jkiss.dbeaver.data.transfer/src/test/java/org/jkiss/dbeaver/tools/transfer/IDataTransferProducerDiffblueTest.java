package org.jkiss.dbeaver.tools.transfer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferProducer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IDataTransferProducerDiffblueTest {
  /**
   * Test {@link IDataTransferProducer#getStatistics()}.
   *
   * <p>Method under test: {@link IDataTransferProducer#getStatistics()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCStatistics IDataTransferProducer.getStatistics()"})
  public void testGetStatistics() {
    // Arrange and Act
    DBCStatistics actualStatistics = new StreamTransferProducer().getStatistics();

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
