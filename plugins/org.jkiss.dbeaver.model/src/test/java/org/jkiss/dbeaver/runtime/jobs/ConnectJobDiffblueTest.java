package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableParametrized;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectJobDiffblueTest {
  @InjectMocks private ConnectJob connectJob;

  @Mock private DBPDataSourceContainer dBPDataSourceContainer;

  @Mock private DBRProgressMonitor dBRProgressMonitor;

  /**
   * Test {@link ConnectJob#ConnectJob(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link ConnectJob#ConnectJob(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConnectJob.<init>(DBPDataSourceContainer)"})
  public void testNewConnectJob() {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");

    // Act
    ConnectJob actualConnectJob = new ConnectJob(container);

    // Assert
    verify(container).getName();
    assertEquals("Connect to 'Name'", actualConnectJob.getName());
    assertNull(actualConnectJob.getThread());
    assertNull(actualConnectJob.getConnectError());
    assertNull(actualConnectJob.getResult());
    assertNull(actualConnectJob.getConnectStatus());
    assertNull(actualConnectJob.getRule());
    assertNull(actualConnectJob.getJobGroup());
    assertEquals(-1L, actualConnectJob.getCancelTimestamp());
    assertEquals(0, actualConnectJob.getState());
    assertEquals(30, actualConnectJob.getPriority());
    assertFalse(actualConnectJob.isBlocking());
    assertFalse(actualConnectJob.isSystem());
    assertFalse(actualConnectJob.isCanceled());
    assertFalse(actualConnectJob.isFinished());
    assertFalse(actualConnectJob.isRunDirectly());
    assertTrue(actualConnectJob.isUser());
    assertTrue(actualConnectJob.isForceCancel());
    assertTrue(actualConnectJob.initialize);
    assertTrue(actualConnectJob.reflect);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConnectJob#getConnectError()}
   *   <li>{@link ConnectJob#getConnectStatus()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Throwable ConnectJob.getConnectError()",
    "IStatus ConnectJob.getConnectStatus()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ConnectJob connectJob = new ConnectJob(mock(DBPDataSourceContainer.class));

    // Act
    Throwable actualConnectError = connectJob.getConnectError();

    // Assert
    assertNull(actualConnectError);
    assertNull(connectJob.getConnectStatus());
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullName()).thenReturn("Dr Jane Doe");
    when(dbpDriver.isNotAvailable()).thenReturn(true);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");
    when(container.getDriver()).thenReturn(dbpDriver);
    ConnectJob connectJob = new ConnectJob(container);

    // Act
    connectJob.run(new LoggingProgressMonitor());

    // Assert
    verify(container, atLeast(1)).getDriver();
    verify(container).getName();
    verify(dbpDriver).getFullName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    Throwable connectError = connectJob.getConnectError();
    assertTrue(connectError instanceof DBException);
    assertEquals(
        "Driver Dr Jane Doe is not available. Please see the connection page for more info.",
        connectError.getLocalizedMessage());
    assertEquals(
        "Driver Dr Jane Doe is not available. Please see the connection page for more info.",
        connectError.getMessage());
    assertEquals(
        "Driver Dr Jane Doe is not available. Please see the connection page for more info.",
        connectStatus.getMessage());
    assertSame(connectError, connectStatus.getException());
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor2() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(container.getName()).thenReturn("Name");
    when(container.getDriver()).thenReturn(dbpDriver);
    ConnectJob connectJob = new ConnectJob(container);

    // Act
    IStatus actualRunResult = connectJob.run(new LoggingProgressMonitor());

    // Assert
    verify(container).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(container).getDriver();
    verify(container).getName();
    verify(dbpDriver).isNotAvailable();
    assertNull(connectJob.getConnectError());
    IStatus iStatus = ((Status) actualRunResult).OK_STATUS;
    assertSame(iStatus, connectJob.getConnectStatus());
    assertSame(iStatus, actualRunResult);
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor3() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(container.getName()).thenReturn("Name");
    when(container.getDriver()).thenReturn(dbpDriver);

    ConnectJob connectJob = new ConnectJob(container);
    connectJob.runSync(new LoggingProgressMonitor());

    // Act
    IStatus actualRunResult = connectJob.run(new LoggingProgressMonitor());

    // Assert
    verify(container, atLeast(1)).connect(Mockito.<DBRProgressMonitor>any(), eq(true), eq(false));
    verify(container, atLeast(1)).getDriver();
    verify(container).getName();
    verify(dbpDriver, atLeast(1)).isNotAvailable();
    assertNull(connectJob.getConnectError());
    IStatus iStatus = ((Status) actualRunResult).OK_STATUS;
    assertSame(iStatus, connectJob.getConnectStatus());
    assertSame(iStatus, actualRunResult);
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor4() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(false);
    when(container.getName()).thenReturn("Name");
    when(container.getDriver()).thenReturn(dbpDriver);
    ConnectJob connectJob = new ConnectJob(container);

    // Act
    IStatus actualRunResult = connectJob.run(new LoggingProgressMonitor());

    // Assert
    verify(container).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(container).getDriver();
    verify(container).getName();
    verify(dbpDriver).isNotAvailable();
    assertSame(((Status) actualRunResult).CANCEL_STATUS, connectJob.getConnectStatus());
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor5() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException("An error occurred");
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);

    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));
    connectionTestJob.addJobChangeListener(new JobChangeAdapter());

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertEquals("An error occurred", connectStatus.getMessage());
    assertSame(dbException, connectStatus.getException());
    assertSame(dbException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor6() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException("An error occurred");
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);

    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));
    connectionTestJob.runSync(new LoggingProgressMonitor());
    connectionTestJob.addJobChangeListener(new JobChangeAdapter());

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert that nothing has changed
    verify(testDataSource, atLeast(1))
        .connect(Mockito.<DBRProgressMonitor>any(), eq(true), eq(false));
    verify(testDataSource, atLeast(1)).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver, atLeast(1)).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertEquals("An error occurred", connectStatus.getMessage());
    assertSame(dbException, connectStatus.getException());
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor7() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException(null);
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);

    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));
    connectionTestJob.addJobChangeListener(new JobChangeAdapter());

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertSame(dbException, connectStatus.getException());
    assertSame(dbException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor8() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException("");
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);

    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));
    connectionTestJob.addJobChangeListener(new JobChangeAdapter());

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertSame(dbException, connectStatus.getException());
    assertSame(dbException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectJob#runSync(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link ConnectJob#runSync(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.runSync(DBRProgressMonitor)"})
  public void testRunSync() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullName()).thenReturn("Dr Jane Doe");
    when(dbpDriver.isNotAvailable()).thenReturn(true);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");
    when(container.getDriver()).thenReturn(dbpDriver);
    ConnectJob connectJob = new ConnectJob(container);

    // Act
    IStatus actualRunSyncResult = connectJob.runSync(new LoggingProgressMonitor());

    // Assert
    verify(container, atLeast(1)).getDriver();
    verify(container).getName();
    verify(dbpDriver).getFullName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    Throwable connectError = connectJob.getConnectError();
    assertTrue(connectError instanceof DBException);
    assertEquals(
        "Driver Dr Jane Doe is not available. Please see the connection page for more info.",
        connectError.getLocalizedMessage());
    assertEquals(
        "Driver Dr Jane Doe is not available. Please see the connection page for more info.",
        connectError.getMessage());
    assertEquals(
        "Driver Dr Jane Doe is not available. Please see the connection page for more info.",
        connectStatus.getMessage());
    assertSame(((Status) actualRunSyncResult).OK_STATUS, actualRunSyncResult);
    assertSame(connectError, connectStatus.getException());
  }

  /**
   * Test {@link ConnectJob#runSync(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link ConnectJob#runSync(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.runSync(DBRProgressMonitor)"})
  public void testRunSync2() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException("An error occurred");
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.runSync(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).connect(isA(DBRProgressMonitor.class), eq(true), eq(false));
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertEquals("An error occurred", connectStatus.getMessage());
    assertSame(dbException, connectStatus.getException());
    assertSame(dbException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectJob#runSync(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link ConnectJob#runSync(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.runSync(DBRProgressMonitor)"})
  public void testRunSync3() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException(null);
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.runSync(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).connect(isA(DBRProgressMonitor.class), eq(true), eq(false));
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertSame(dbException, connectStatus.getException());
    assertSame(dbException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectJob#runSync(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link ConnectJob#runSync(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.runSync(DBRProgressMonitor)"})
  public void testRunSync4() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    DBException dbException = new DBException("");
    when(testDataSource.connect(Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenThrow(dbException);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.runSync(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).connect(isA(DBRProgressMonitor.class), eq(true), eq(false));
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertSame(dbException, connectStatus.getException());
    assertSame(dbException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectJob#runSync(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link ConnectJob} ConnectError is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectJob#runSync(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.runSync(DBRProgressMonitor)"})
  public void testRunSync_thenConnectJobConnectErrorIsNull() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    // Act
    IStatus actualRunSyncResult = connectJob.runSync(dBRProgressMonitor);

    // Assert
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(false));
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    assertNull(connectJob.getConnectError());
    assertFalse(connectJob.reflect);
    IStatus iStatus = ((Status) actualRunSyncResult).OK_STATUS;
    assertSame(iStatus, connectJob.getConnectStatus());
    assertSame(iStatus, actualRunSyncResult);
  }

  /**
   * Test {@link ConnectJob#runSync(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then {@link ConnectJob} ConnectStatus is {@link Status#CANCEL_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectJob#runSync(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectJob.runSync(DBRProgressMonitor)"})
  public void testRunSync_thenConnectJobConnectStatusIsCancel_status() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(false);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    // Act
    IStatus actualRunSyncResult = connectJob.runSync(dBRProgressMonitor);

    // Assert
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(false));
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    assertSame(((Status) actualRunSyncResult).CANCEL_STATUS, connectJob.getConnectStatus());
  }

  /**
   * Test {@link ConnectJob#belongsTo(Object)}.
   *
   * <p>Method under test: {@link ConnectJob#belongsTo(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConnectJob.belongsTo(Object)"})
  public void testBelongsTo() {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");

    // Act
    boolean actualBelongsToResult = new ConnectJob(container).belongsTo(DBPEvent.RENAME);

    // Assert
    verify(container).getName();
    assertFalse(actualBelongsToResult);
  }

  /**
   * Test {@link ConnectJob#canceling()}.
   *
   * <p>Method under test: {@link ConnectJob#canceling()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConnectJob.canceling()"})
  public void testCanceling() {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getName()).thenReturn("Name");

    // Act
    new ConnectJob(container).canceling();

    // Assert
    verify(container).getName();
  }
}
