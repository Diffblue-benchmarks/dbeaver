package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DisconnectJobDiffblueTest {
  /**
   * Test {@link DisconnectJob#DisconnectJob(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DisconnectJob#DisconnectJob(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DisconnectJob.<init>(DBPDataSourceContainer)"})
  public void testNewDisconnectJob() {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");

    // Act
    DisconnectJob actualDisconnectJob = new DisconnectJob(container);

    // Assert
    verify(container).getName();
    assertEquals("Disconnect from 'Name'", actualDisconnectJob.getName());
    assertNull(actualDisconnectJob.getThread());
    assertNull(actualDisconnectJob.getResult());
    assertNull(actualDisconnectJob.getConnectStatus());
    assertNull(actualDisconnectJob.getRule());
    assertNull(actualDisconnectJob.getJobGroup());
    assertNull(actualDisconnectJob.getDataSource());
    assertEquals(-1L, actualDisconnectJob.getCancelTimestamp());
    assertEquals(0, actualDisconnectJob.getState());
    assertEquals(30, actualDisconnectJob.getPriority());
    assertFalse(actualDisconnectJob.isBlocking());
    assertFalse(actualDisconnectJob.isSystem());
    assertFalse(actualDisconnectJob.isCanceled());
    assertFalse(actualDisconnectJob.isFinished());
    assertFalse(actualDisconnectJob.isRunDirectly());
    assertTrue(actualDisconnectJob.isUser());
    assertTrue(actualDisconnectJob.isForceCancel());
  }

  /**
   * Test {@link DisconnectJob#getConnectStatus()}.
   *
   * <p>Method under test: {@link DisconnectJob#getConnectStatus()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus DisconnectJob.getConnectStatus()"})
  public void testGetConnectStatus() {
    // Arrange, Act and Assert
    assertNull(new DisconnectJob(mock(DBPDataSourceContainer.class)).getConnectStatus());
  }

  /**
   * Test {@link DisconnectJob#getDataSource()}.
   *
   * <p>Method under test: {@link DisconnectJob#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DisconnectJob.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");
    when(container.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    new DisconnectJob(container).getDataSource();

    // Assert
    verify(container).getDataSource();
    verify(container).getName();
  }

  /**
   * Test {@link DisconnectJob#updateDataSource(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DisconnectJob#updateDataSource(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus DisconnectJob.updateDataSource(DBRProgressMonitor)"})
  public void testUpdateDataSource() throws DBException {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.disconnect(Mockito.<DBRProgressMonitor>any())).thenReturn(true);
    when(container.getName()).thenReturn("Name");
    DisconnectJob disconnectJob = new DisconnectJob(container);

    // Act
    IStatus actualUpdateDataSourceResult =
        disconnectJob.updateDataSource(new LoggingProgressMonitor());

    // Assert
    verify(container).disconnect(isA(DBRProgressMonitor.class));
    verify(container).getName();
    IStatus iStatus = ((Status) actualUpdateDataSourceResult).OK_STATUS;
    assertSame(iStatus, disconnectJob.getConnectStatus());
    assertSame(iStatus, actualUpdateDataSourceResult);
  }

  /**
   * Test {@link DisconnectJob#belongsTo(Object)}.
   *
   * <p>Method under test: {@link DisconnectJob#belongsTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DisconnectJob.belongsTo(Object)"})
  public void testBelongsTo() {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");

    // Act
    boolean actualBelongsToResult = new DisconnectJob(container).belongsTo(DBPEvent.RENAME);

    // Assert
    verify(container).getName();
    assertFalse(actualBelongsToResult);
  }
}
