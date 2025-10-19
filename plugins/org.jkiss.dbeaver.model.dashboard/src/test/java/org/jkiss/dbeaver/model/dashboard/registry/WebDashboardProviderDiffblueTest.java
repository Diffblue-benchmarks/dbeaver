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

public class WebDashboardProviderDiffblueTest {
  /**
   * Test {@link WebDashboardProvider#loadStaticDashboards(DashboardProviderDescriptor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * WebDashboardProvider#loadStaticDashboards(DashboardProviderDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List WebDashboardProvider.loadStaticDashboards(DashboardProviderDescriptor)"
  })
  public void testLoadStaticDashboards_whenNull_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new WebDashboardProvider().loadStaticDashboards(null).isEmpty());
  }

  /**
   * Test {@link WebDashboardProvider#loadRootFolders(DBRProgressMonitor,
   * DashboardProviderDescriptor, DBDashboardContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link WebDashboardProvider#loadRootFolders(DBRProgressMonitor,
   * DashboardProviderDescriptor, DBDashboardContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List WebDashboardProvider.loadRootFolders(DBRProgressMonitor, DashboardProviderDescriptor, DBDashboardContext)"
  })
  public void testLoadRootFolders_whenNull_thenReturnEmpty() {
    // Arrange
    WebDashboardProvider webDashboardProvider = new WebDashboardProvider();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertTrue(
        webDashboardProvider.loadRootFolders(monitor, null, new DBDashboardContext()).isEmpty());
  }

  /**
   * Test {@link WebDashboardProvider#appliesTo(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link WebDashboardProvider#appliesTo(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WebDashboardProvider.appliesTo(DBPDataSourceContainer)"})
  public void testAppliesTo() {
    // Arrange, Act and Assert
    assertTrue(new WebDashboardProvider().appliesTo(mock(DBPDataSourceContainer.class)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link WebDashboardProvider}
   *   <li>{@link WebDashboardProvider#getId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WebDashboardProvider.<init>()",
    "java.lang.String WebDashboardProvider.getId()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("database", new WebDashboardProvider().getId());
  }
}
