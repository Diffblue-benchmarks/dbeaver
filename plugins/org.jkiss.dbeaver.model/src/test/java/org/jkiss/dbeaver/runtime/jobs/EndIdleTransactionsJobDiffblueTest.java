package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EndIdleTransactionsJobDiffblueTest {
  /**
   * Test {@link EndIdleTransactionsJob#EndIdleTransactionsJob(DBPDataSource, Map)}.
   *
   * <p>Method under test: {@link EndIdleTransactionsJob#EndIdleTransactionsJob(DBPDataSource, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EndIdleTransactionsJob.<init>(DBPDataSource, Map)"})
  public void testNewEndIdleTransactionsJob() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    EndIdleTransactionsJob actualEndIdleTransactionsJob =
        new EndIdleTransactionsJob(dataSource, new HashMap<>());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    assertEquals("End idle transaction for (Name)", actualEndIdleTransactionsJob.getName());
    assertNull(actualEndIdleTransactionsJob.getThread());
    assertNull(actualEndIdleTransactionsJob.getResult());
    assertNull(actualEndIdleTransactionsJob.getRule());
    assertNull(actualEndIdleTransactionsJob.getJobGroup());
    assertEquals(-1L, actualEndIdleTransactionsJob.getCancelTimestamp());
    assertEquals(0, actualEndIdleTransactionsJob.getState());
    assertEquals(30, actualEndIdleTransactionsJob.getPriority());
    assertFalse(actualEndIdleTransactionsJob.isBlocking());
    assertFalse(actualEndIdleTransactionsJob.isUser());
    assertFalse(actualEndIdleTransactionsJob.isCanceled());
    assertFalse(actualEndIdleTransactionsJob.isFinished());
    assertFalse(actualEndIdleTransactionsJob.isRunDirectly());
    assertTrue(actualEndIdleTransactionsJob.isSystem());
    assertTrue(actualEndIdleTransactionsJob.isForceCancel());
    assertSame(dataSource, actualEndIdleTransactionsJob.getDataSource());
  }
}
