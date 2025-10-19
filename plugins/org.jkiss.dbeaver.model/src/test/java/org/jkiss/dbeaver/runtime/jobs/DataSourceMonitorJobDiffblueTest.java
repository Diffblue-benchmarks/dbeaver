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
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceMonitorJobDiffblueTest {
  /**
   * Test {@link DataSourceMonitorJob#DataSourceMonitorJob(DBPPlatform)}.
   *
   * <p>Method under test: {@link DataSourceMonitorJob#DataSourceMonitorJob(DBPPlatform)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceMonitorJob.<init>(DBPPlatform)"})
  public void testNewDataSourceMonitorJob() {
    // Arrange and Act
    DataSourceMonitorJob actualDataSourceMonitorJob =
        new DataSourceMonitorJob(mock(DBPPlatform.class));

    // Assert
    assertEquals("Connections monitoring", actualDataSourceMonitorJob.getName());
    assertNull(actualDataSourceMonitorJob.getThread());
    assertNull(actualDataSourceMonitorJob.getResult());
    assertNull(actualDataSourceMonitorJob.getRule());
    assertNull(actualDataSourceMonitorJob.getJobGroup());
    assertEquals(-1L, actualDataSourceMonitorJob.getCancelTimestamp());
    assertEquals(0, actualDataSourceMonitorJob.getState());
    assertEquals(30, actualDataSourceMonitorJob.getPriority());
    assertFalse(actualDataSourceMonitorJob.isBlocking());
    assertFalse(actualDataSourceMonitorJob.isUser());
    assertFalse(actualDataSourceMonitorJob.isCanceled());
    assertFalse(actualDataSourceMonitorJob.isFinished());
    assertFalse(actualDataSourceMonitorJob.isRunDirectly());
    assertTrue(actualDataSourceMonitorJob.isSystem());
    assertTrue(actualDataSourceMonitorJob.isForceCancel());
  }

  /**
   * Test {@link DataSourceMonitorJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return {@link Status#OK_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceMonitorJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus DataSourceMonitorJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnOk_status() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenReturn(true);
    DataSourceMonitorJob dataSourceMonitorJob = new DataSourceMonitorJob(platform);

    // Act
    IStatus actualRunResult = dataSourceMonitorJob.run(new LoggingProgressMonitor());

    // Assert
    verify(platform).isShuttingDown();
    assertSame(((Status) actualRunResult).OK_STATUS, actualRunResult);
  }

  /**
   * Test {@link DataSourceMonitorJob#checkDataSourceAliveInWorkspace(DBPWorkspace, long)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceMonitorJob#checkDataSourceAliveInWorkspace(DBPWorkspace,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceMonitorJob.checkDataSourceAliveInWorkspace(DBPWorkspace, long)"
  })
  public void testCheckDataSourceAliveInWorkspace_givenArrayList() {
    // Arrange
    DataSourceMonitorJob dataSourceMonitorJob = new DataSourceMonitorJob(mock(DBPPlatform.class));

    DBPWorkspace workspace = mock(DBPWorkspace.class);
    org.mockito.Mockito.<List<? extends DBPProject>>when(workspace.getProjects())
        .thenReturn(new ArrayList<>());

    // Act
    dataSourceMonitorJob.checkDataSourceAliveInWorkspace(workspace, 1L);

    // Assert
    verify(workspace).getProjects();
  }

  /**
   * Test {@link DataSourceMonitorJob#checkDataSourceAliveInWorkspace(DBPWorkspace, long)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceRegistry#getDataSources()}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceMonitorJob#checkDataSourceAliveInWorkspace(DBPWorkspace,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceMonitorJob.checkDataSourceAliveInWorkspace(DBPWorkspace, long)"
  })
  public void testCheckDataSourceAliveInWorkspace_thenCallsGetDataSources() {
    // Arrange
    DataSourceMonitorJob dataSourceMonitorJob = new DataSourceMonitorJob(mock(DBPPlatform.class));

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    org.mockito.Mockito.<List<? extends DBPDataSourceContainer>>when(
            dbpDataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);
    when(dbpProject.isRegistryLoaded()).thenReturn(true);
    when(dbpProject.isOpen()).thenReturn(true);

    ArrayList<DBPProject> dbpProjectList = new ArrayList<>();
    dbpProjectList.add(dbpProject);

    DBPWorkspace workspace = mock(DBPWorkspace.class);
    org.mockito.Mockito.<List<? extends DBPProject>>when(workspace.getProjects())
        .thenReturn(dbpProjectList);

    // Act
    dataSourceMonitorJob.checkDataSourceAliveInWorkspace(workspace, 1L);

    // Assert
    verify(dbpDataSourceRegistry).getDataSources();
    verify(dbpProject).getDataSourceRegistry();
    verify(dbpProject).isOpen();
    verify(dbpProject).isRegistryLoaded();
    verify(workspace).getProjects();
  }

  /**
   * Test {@link DataSourceMonitorJob#scheduleMonitor()}.
   *
   * <p>Method under test: {@link DataSourceMonitorJob#scheduleMonitor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceMonitorJob.scheduleMonitor()"})
  public void testScheduleMonitor() {
    // Arrange
    DataSourceMonitorJob dataSourceMonitorJob = new DataSourceMonitorJob(mock(DBPPlatform.class));

    // Act
    dataSourceMonitorJob.scheduleMonitor();

    // Assert
    assertEquals(1, dataSourceMonitorJob.getState());
  }

  /**
   * Test {@link DataSourceMonitorJob#getDisconnectTimeoutSeconds(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then return {@code 14400}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataSourceMonitorJob#getDisconnectTimeoutSeconds(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long DataSourceMonitorJob.getDisconnectTimeoutSeconds(DBPDataSourceContainer)"
  })
  public void testGetDisconnectTimeoutSeconds_thenReturn14400() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setCloseIdleInterval(0);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    long actualDisconnectTimeoutSeconds =
        DataSourceMonitorJob.getDisconnectTimeoutSeconds(container);

    // Assert
    verify(container).getConnectionConfiguration();
    assertEquals(14400L, actualDisconnectTimeoutSeconds);
  }

  /**
   * Test {@link DataSourceMonitorJob#getDisconnectTimeoutSeconds(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataSourceMonitorJob#getDisconnectTimeoutSeconds(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long DataSourceMonitorJob.getDisconnectTimeoutSeconds(DBPDataSourceContainer)"
  })
  public void testGetDisconnectTimeoutSeconds_thenReturnOne() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setCloseIdleInterval(1);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    long actualDisconnectTimeoutSeconds =
        DataSourceMonitorJob.getDisconnectTimeoutSeconds(container);

    // Assert
    verify(container).getConnectionConfiguration();
    assertEquals(1L, actualDisconnectTimeoutSeconds);
  }

  /**
   * Test {@link DataSourceMonitorJob#getDisconnectTimeoutSeconds(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataSourceMonitorJob#getDisconnectTimeoutSeconds(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long DataSourceMonitorJob.getDisconnectTimeoutSeconds(DBPDataSourceContainer)"
  })
  public void testGetDisconnectTimeoutSeconds_thenReturnZero() {
    // Arrange
    DBPConnectionConfiguration dbpConnectionConfiguration =
        new DBPConnectionConfiguration(new DBPConnectionConfiguration());
    dbpConnectionConfiguration.setCloseIdleConnection(false);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getConnectionConfiguration()).thenReturn(dbpConnectionConfiguration);

    // Act
    long actualDisconnectTimeoutSeconds =
        DataSourceMonitorJob.getDisconnectTimeoutSeconds(container);

    // Assert
    verify(container).getConnectionConfiguration();
    assertEquals(0L, actualDisconnectTimeoutSeconds);
  }

  /**
   * Test {@link DataSourceMonitorJob#showNotification(DBPDataSource)}.
   *
   * <p>Method under test: {@link DataSourceMonitorJob#showNotification(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceMonitorJob.showNotification(DBPDataSource)"})
  public void testShowNotification() {
    // Arrange
    DataSourceMonitorJob dataSourceMonitorJob = new DataSourceMonitorJob(mock(DBPPlatform.class));

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act
    dataSourceMonitorJob.showNotification(dataSource);

    // Assert
    verify(dataSource).getContainer();
    verify(dbpDataSourceContainer).getName();
  }
}
