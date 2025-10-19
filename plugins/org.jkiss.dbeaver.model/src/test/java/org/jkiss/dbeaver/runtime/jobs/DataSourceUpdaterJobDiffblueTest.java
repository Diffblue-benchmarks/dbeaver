package org.jkiss.dbeaver.runtime.jobs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
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
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataSourceUpdaterJobDiffblueTest {
  /**
   * Test {@link DataSourceUpdaterJob#isInProcess(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DataSourceUpdaterJob#isInProcess(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataSourceUpdaterJob.isInProcess(DBPDataSourceContainer)"})
  public void testIsInProcess() {
    // Arrange
    DBPDataSourceContainer ds = mock(DBPDataSourceContainer.class);
    when(ds.getId()).thenReturn("42");

    // Act
    boolean actualIsInProcessResult = DataSourceUpdaterJob.isInProcess(ds);

    // Assert
    verify(ds).getId();
    assertFalse(actualIsInProcessResult);
  }

  /**
   * Test {@link DataSourceUpdaterJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return {@link Status#OK_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceUpdaterJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus DataSourceUpdaterJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnOk_status() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.disconnect(Mockito.<DBRProgressMonitor>any())).thenReturn(true);
    when(container.getName()).thenReturn("Name");
    when(container.getDataSource()).thenReturn(dbpDataSource);
    DisconnectJob disconnectJob = new DisconnectJob(container);

    // Act
    IStatus actualRunResult = disconnectJob.run(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSource).getContainer();
    verify(container).disconnect(isA(DBRProgressMonitor.class));
    verify(container, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer).getId();
    verify(container).getName();
    IStatus iStatus = ((Status) actualRunResult).OK_STATUS;
    assertSame(iStatus, actualRunResult);
    assertSame(iStatus, disconnectJob.getConnectStatus());
  }
}
