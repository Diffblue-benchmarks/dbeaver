package org.jkiss.dbeaver.model.dashboard.registry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.dashboard.DBDashboardContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseDashboardProviderDiffblueTest {
  /**
   * Test {@link DatabaseDashboardProvider#loadRootFolders(DBRProgressMonitor,
   * DashboardProviderDescriptor, DBDashboardContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseDashboardProvider#loadRootFolders(DBRProgressMonitor,
   * DashboardProviderDescriptor, DBDashboardContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List DatabaseDashboardProvider.loadRootFolders(DBRProgressMonitor, DashboardProviderDescriptor, DBDashboardContext)"
  })
  public void testLoadRootFolders_whenNull_thenReturnEmpty() {
    // Arrange
    DatabaseDashboardProvider databaseDashboardProvider = new DatabaseDashboardProvider();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertTrue(
        databaseDashboardProvider
            .loadRootFolders(monitor, null, new DBDashboardContext())
            .isEmpty());
  }

  /**
   * Test {@link DatabaseDashboardProvider#appliesTo(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link DatabaseDashboardProvider#appliesTo(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseDashboardProvider.appliesTo(DBPDataSourceContainer)"})
  public void testAppliesTo() {
    // Arrange, Act and Assert
    assertTrue(new DatabaseDashboardProvider().appliesTo(mock(DBPDataSourceContainer.class)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DatabaseDashboardProvider}
   *   <li>{@link DatabaseDashboardProvider#getId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseDashboardProvider.<init>()",
    "java.lang.String DatabaseDashboardProvider.getId()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("database", new DatabaseDashboardProvider().getId());
  }
}
