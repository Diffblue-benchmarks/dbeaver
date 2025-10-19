package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
public class ConnectionTestJobDiffblueTest {
  @InjectMocks private ConnectionTestJob connectionTestJob;

  @Mock private DBPDataSourceContainer dBPDataSourceContainer;

  @Mock private DBRProgressMonitor dBRProgressMonitor;

  /**
   * Test {@link ConnectionTestJob#ConnectionTestJob(DBPDataSourceContainer,
   * DBRRunnableParametrized)}.
   *
   * <p>Method under test: {@link ConnectionTestJob#ConnectionTestJob(DBPDataSourceContainer,
   * DBRRunnableParametrized)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConnectionTestJob.<init>(DBPDataSourceContainer, DBRRunnableParametrized)"
  })
  public void testNewConnectionTestJob() {
    // Arrange
    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    when(testDataSource.getName()).thenReturn("Name");

    // Act
    ConnectionTestJob actualConnectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Assert
    verify(testDataSource).getName();
    assertEquals("?", actualConnectionTestJob.getClientVersion());
    assertEquals("?", actualConnectionTestJob.getServerVersion());
    assertEquals("Connect to 'Name'", actualConnectionTestJob.getName());
    assertNull(actualConnectionTestJob.getDriverName());
    assertNull(actualConnectionTestJob.getDriverVersion());
    assertNull(actualConnectionTestJob.getProductName());
    assertNull(actualConnectionTestJob.getProductVersion());
    assertNull(actualConnectionTestJob.getThread());
    assertNull(actualConnectionTestJob.getConnectError());
    assertNull(actualConnectionTestJob.getResult());
    assertNull(actualConnectionTestJob.getConnectStatus());
    assertNull(actualConnectionTestJob.getRule());
    assertNull(actualConnectionTestJob.getJobGroup());
    assertEquals(-1L, actualConnectionTestJob.getCancelTimestamp());
    assertEquals(-1L, actualConnectionTestJob.getConnectTime());
    assertEquals(0, actualConnectionTestJob.getState());
    assertEquals(30, actualConnectionTestJob.getPriority());
    assertFalse(actualConnectionTestJob.isBlocking());
    assertFalse(actualConnectionTestJob.isUser());
    assertFalse(actualConnectionTestJob.isCanceled());
    assertFalse(actualConnectionTestJob.isFinished());
    assertFalse(actualConnectionTestJob.isRunDirectly());
    assertTrue(actualConnectionTestJob.isSystem());
    assertTrue(actualConnectionTestJob.isForceCancel());
    assertTrue(actualConnectionTestJob.initialize);
    assertTrue(actualConnectionTestJob.reflect);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConnectionTestJob#setOwnerMonitor(DBRProgressMonitor)}
   *   <li>{@link ConnectionTestJob#getClientVersion()}
   *   <li>{@link ConnectionTestJob#getConnectTime()}
   *   <li>{@link ConnectionTestJob#getDriverName()}
   *   <li>{@link ConnectionTestJob#getDriverVersion()}
   *   <li>{@link ConnectionTestJob#getProductName()}
   *   <li>{@link ConnectionTestJob#getProductVersion()}
   *   <li>{@link ConnectionTestJob#getServerVersion()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ConnectionTestJob.getClientVersion()",
    "long ConnectionTestJob.getConnectTime()",
    "String ConnectionTestJob.getDriverName()",
    "String ConnectionTestJob.getDriverVersion()",
    "String ConnectionTestJob.getProductName()",
    "String ConnectionTestJob.getProductVersion()",
    "String ConnectionTestJob.getServerVersion()",
    "void ConnectionTestJob.setOwnerMonitor(DBRProgressMonitor)"
  })
  public void testGettersAndSetters() {
    // Arrange
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(
            mock(DBPDataSourceContainer.class), mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.setOwnerMonitor(new LoggingProgressMonitor());
    String actualClientVersion = connectionTestJob.getClientVersion();
    long actualConnectTime = connectionTestJob.getConnectTime();
    String actualDriverName = connectionTestJob.getDriverName();
    String actualDriverVersion = connectionTestJob.getDriverVersion();
    String actualProductName = connectionTestJob.getProductName();
    String actualProductVersion = connectionTestJob.getProductVersion();

    // Assert
    assertEquals("?", actualClientVersion);
    assertEquals("?", connectionTestJob.getServerVersion());
    assertNull(actualDriverName);
    assertNull(actualDriverVersion);
    assertNull(actualProductName);
    assertNull(actualProductVersion);
    assertEquals(-1L, actualConnectTime);
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullName()).thenReturn("Dr Jane Doe");
    when(dbpDriver.isNotAvailable()).thenReturn(true);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource, atLeast(1)).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).getFullName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    Throwable connectError = connectionTestJob.getConnectError();
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
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor2() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getFullName()).thenReturn("Dr Jane Doe");
    when(dbpDriver.isNotAvailable()).thenReturn(true);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);

    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));
    connectionTestJob.setOwnerMonitor(new LoggingProgressMonitor());

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource, atLeast(1)).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).getFullName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    Throwable connectError = connectionTestJob.getConnectError();
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
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor3() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);
    when(dBRProgressMonitor.isCanceled()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> connectionTestJob.run(dBRProgressMonitor));
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    verify(dBRProgressMonitor).isCanceled();
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link
   *       DBPDataSourceContainer#connect(DBRProgressMonitor, boolean, boolean)} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_givenDBPDataSourceContainerConnectReturnFalse()
      throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(false);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);
    when(dBRProgressMonitor.isCanceled()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> connectionTestJob.run(dBRProgressMonitor));
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    verify(dBRProgressMonitor).isCanceled();
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#getFullName()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_givenDBPDriverGetFullNameThrowRuntimeException() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    RuntimeException runtimeException = new RuntimeException();
    when(dbpDriver.getFullName()).thenThrow(runtimeException);
    when(dbpDriver.isNotAvailable()).thenReturn(true);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource, atLeast(1)).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).getFullName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertEquals("RuntimeException", connectStatus.getMessage());
    assertSame(runtimeException, connectStatus.getException());
    assertSame(runtimeException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isNotAvailable()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_givenDBPDriverIsNotAvailableThrowRuntimeException() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    RuntimeException runtimeException = new RuntimeException();
    when(dbpDriver.isNotAvailable()).thenThrow(runtimeException);

    DBPDataSourceContainer testDataSource = mock(DBPDataSourceContainer.class);
    when(testDataSource.getName()).thenReturn("Name");
    when(testDataSource.getDriver()).thenReturn(dbpDriver);
    ConnectionTestJob connectionTestJob =
        new ConnectionTestJob(testDataSource, mock(DBRRunnableParametrized.class));

    // Act
    connectionTestJob.run(new LoggingProgressMonitor());

    // Assert
    verify(testDataSource).getDriver();
    verify(testDataSource).getName();
    verify(dbpDriver).isNotAvailable();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertEquals("RuntimeException", connectStatus.getMessage());
    assertSame(runtimeException, connectStatus.getException());
    assertSame(runtimeException, connectionTestJob.getConnectError());
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenCallsGetInfo() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenThrow(new RuntimeException());
    when(dBPDataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);
    when(dBRProgressMonitor.isCanceled()).thenReturn(false);
    doNothing().when(dBRProgressMonitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(dBRProgressMonitor).subTask(Mockito.<String>any());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> connectionTestJob.run(dBRProgressMonitor));
    verify(dbpDataSource).getInfo();
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dBPDataSourceContainer).getDataSource();
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    verify(dBRProgressMonitor).beginTask("Obtain connection", 3);
    verify(dBRProgressMonitor).isCanceled();
    verify(dBRProgressMonitor).subTask("Test connection");
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then {@link ConnectionTestJob} ConnectError is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenConnectionTestJobConnectErrorIsNull()
      throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);
    when(dBRProgressMonitor.isCanceled()).thenReturn(true);

    // Act
    IStatus actualRunResult = connectionTestJob.run(dBRProgressMonitor);

    // Assert
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    verify(dBRProgressMonitor).isCanceled();
    assertNull(connectionTestJob.getConnectError());
    IStatus iStatus = ((Status) actualRunResult).OK_STATUS;
    assertSame(iStatus, connectionTestJob.getConnectStatus());
    assertSame(iStatus, actualRunResult);
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then {@link ConnectionTestJob} ConnectStatus is {@link Status#CANCEL_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenConnectionTestJobConnectStatusIsCancel_status()
      throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isNotAvailable()).thenReturn(false);
    when(dBPDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(false);
    when(dBPDataSourceContainer.getDriver()).thenReturn(dbpDriver);
    when(dBRProgressMonitor.isCanceled()).thenReturn(false);

    // Act
    IStatus actualRunResult = connectionTestJob.run(dBRProgressMonitor);

    // Assert
    verify(dBPDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dBPDataSourceContainer).getDriver();
    verify(dbpDriver).isNotAvailable();
    verify(dBRProgressMonitor).isCanceled();
    IStatus iStatus = ((Status) actualRunResult).CANCEL_STATUS;
    assertSame(iStatus, connectionTestJob.getConnectStatus());
    assertSame(iStatus, actualRunResult);
  }

  /**
   * Test {@link ConnectionTestJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then {@link ConnectionTestJob} ConnectStatus {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link ConnectionTestJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus ConnectionTestJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenConnectionTestJobConnectStatusStatus() {
    // Arrange
    RuntimeException runtimeException = new RuntimeException();
    when(dBPDataSourceContainer.getDriver()).thenThrow(runtimeException);

    // Act
    connectionTestJob.run(dBRProgressMonitor);

    // Assert
    verify(dBPDataSourceContainer).getDriver();
    IStatus connectStatus = connectionTestJob.getConnectStatus();
    assertTrue(connectStatus instanceof Status);
    assertEquals("RuntimeException", connectStatus.getMessage());
    assertSame(runtimeException, connectStatus.getException());
    assertSame(runtimeException, connectionTestJob.getConnectError());
  }
}
