package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class KeepAlivePingJobDiffblueTest {
  /**
   * Test {@link KeepAlivePingJob#KeepAlivePingJob(DBPDataSource, boolean)}.
   *
   * <p>Method under test: {@link KeepAlivePingJob#KeepAlivePingJob(DBPDataSource, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeepAlivePingJob.<init>(DBPDataSource, boolean)"})
  public void testNewKeepAlivePingJob() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    KeepAlivePingJob actualKeepAlivePingJob = new KeepAlivePingJob(dataSource, true);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    assertEquals("Connection ping (Name)", actualKeepAlivePingJob.getName());
    assertNull(actualKeepAlivePingJob.getThread());
    assertNull(actualKeepAlivePingJob.getResult());
    assertNull(actualKeepAlivePingJob.getRule());
    assertNull(actualKeepAlivePingJob.getJobGroup());
    assertEquals(-1L, actualKeepAlivePingJob.getCancelTimestamp());
    assertEquals(0, actualKeepAlivePingJob.getState());
    assertEquals(30, actualKeepAlivePingJob.getPriority());
    assertFalse(actualKeepAlivePingJob.isBlocking());
    assertFalse(actualKeepAlivePingJob.isUser());
    assertFalse(actualKeepAlivePingJob.isCanceled());
    assertFalse(actualKeepAlivePingJob.isFinished());
    assertFalse(actualKeepAlivePingJob.isRunDirectly());
    assertTrue(actualKeepAlivePingJob.isSystem());
    assertTrue(actualKeepAlivePingJob.isForceCancel());
  }

  /**
   * Test {@link KeepAlivePingJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link KeepAlivePingJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus KeepAlivePingJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getAllContexts()).thenThrow(new RuntimeException());

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    KeepAlivePingJob keepAlivePingJob = new KeepAlivePingJob(dataSource, true);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> keepAlivePingJob.run(new LoggingProgressMonitor()));
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(dbsInstance).getAllContexts();
    verify(dataSource).getAvailableInstances();
  }

  /**
   * Test {@link KeepAlivePingJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link KeepAlivePingJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus KeepAlivePingJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor2() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    doNothing().when(dbcExecutionContext).checkContextAlive(Mockito.<DBRProgressMonitor>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    KeepAlivePingJob keepAlivePingJob = new KeepAlivePingJob(dataSource, true);

    // Act
    IStatus actualRunResult = keepAlivePingJob.run(new LoggingProgressMonitor());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(dbcExecutionContext).checkContextAlive(isA(DBRProgressMonitor.class));
    verify(dbsInstance).getAllContexts();
    verify(dataSource).getAvailableInstances();
    assertSame(((Status) actualRunResult).OK_STATUS, actualRunResult);
  }

  /**
   * Test {@link KeepAlivePingJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getName()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link KeepAlivePingJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus KeepAlivePingJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_givenDBPDataSourceGetNameThrowRuntimeException()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    doThrow(new RuntimeException())
        .when(dbcExecutionContext)
        .checkContextAlive(Mockito.<DBRProgressMonitor>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getName()).thenThrow(new RuntimeException());
    Mockito.<Collection<? extends DBSInstance>>when(dataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    KeepAlivePingJob keepAlivePingJob = new KeepAlivePingJob(dataSource, true);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> keepAlivePingJob.run(new LoggingProgressMonitor()));
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    verify(dbpDataSourceContainer).getName();
    verify(dbcExecutionContext).checkContextAlive(isA(DBRProgressMonitor.class));
    verify(dbsInstance).getAllContexts();
    verify(dataSource).getAvailableInstances();
  }

  /**
   * Test {@link KeepAlivePingJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then calls {@link DBCExecutionContext#getContextName()}.
   * </ul>
   *
   * <p>Method under test: {@link KeepAlivePingJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus KeepAlivePingJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenCallsGetContextName() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.getContextName()).thenThrow(new RuntimeException());
    doThrow(new RuntimeException())
        .when(dbcExecutionContext)
        .checkContextAlive(Mockito.<DBRProgressMonitor>any());

    DBSInstance dbsInstance = mock(DBSInstance.class);
    when(dbsInstance.getAllContexts()).thenReturn(new DBCExecutionContext[] {dbcExecutionContext});

    ArrayList<DBSInstance> dbsInstanceList = new ArrayList<>();
    dbsInstanceList.add(dbsInstance);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    Mockito.<Collection<? extends DBSInstance>>when(dataSource.getAvailableInstances())
        .thenReturn(dbsInstanceList);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    KeepAlivePingJob keepAlivePingJob = new KeepAlivePingJob(dataSource, true);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> keepAlivePingJob.run(new LoggingProgressMonitor()));
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    verify(dbpDataSourceContainer).getName();
    verify(dbcExecutionContext).checkContextAlive(isA(DBRProgressMonitor.class));
    verify(dbcExecutionContext).getContextName();
    verify(dbsInstance).getAllContexts();
    verify(dataSource).getAvailableInstances();
  }

  /**
   * Test {@link KeepAlivePingJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return {@link Status#OK_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link KeepAlivePingJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus KeepAlivePingJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnOk_status() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    Mockito.<Collection<? extends DBSInstance>>when(dataSource.getAvailableInstances())
        .thenReturn(new ArrayList<>());
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    KeepAlivePingJob keepAlivePingJob = new KeepAlivePingJob(dataSource, true);

    // Act
    IStatus actualRunResult = keepAlivePingJob.run(new LoggingProgressMonitor());

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
    verify(dataSource).getAvailableInstances();
    assertSame(((Status) actualRunResult).OK_STATUS, actualRunResult);
  }

  /**
   * Test {@link KeepAlivePingJob#getFailedAttemptCount(DBPDataSource)}.
   *
   * <p>Method under test: {@link KeepAlivePingJob#getFailedAttemptCount(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int KeepAlivePingJob.getFailedAttemptCount(DBPDataSource)"})
  public void testGetFailedAttemptCount() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    int actualFailedAttemptCount = KeepAlivePingJob.getFailedAttemptCount(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getId();
    assertEquals(0, actualFailedAttemptCount);
  }
}
