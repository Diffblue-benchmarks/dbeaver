package org.jkiss.dbeaver.model.dashboard;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDashboardContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDashboardContext#DBDashboardContext()}
   *   <li>{@link DBDashboardContext#setDataSource(DBPDataSourceContainer)}
   *   <li>{@link DBDashboardContext#setProject(DBPProject)}
   *   <li>{@link DBDashboardContext#getDataSource()}
   *   <li>{@link DBDashboardContext#getProject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDashboardContext.<init>()",
    "void DBDashboardContext.<init>(DBPProject)",
    "DBPDataSourceContainer DBDashboardContext.getDataSource()",
    "DBPProject DBDashboardContext.getProject()",
    "void DBDashboardContext.setDataSource(DBPDataSourceContainer)",
    "void DBDashboardContext.setProject(DBPProject)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBDashboardContext actualDbDashboardContext = new DBDashboardContext();
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    actualDbDashboardContext.setDataSource(dataSource);
    DBPProject project = mock(DBPProject.class);
    actualDbDashboardContext.setProject(project);
    DBPDataSourceContainer actualDataSource = actualDbDashboardContext.getDataSource();

    // Assert
    assertSame(dataSource, actualDataSource);
    assertSame(project, actualDbDashboardContext.getProject());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DBPProject}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDashboardContext#DBDashboardContext(DBPProject)}
   *   <li>{@link DBDashboardContext#setDataSource(DBPDataSourceContainer)}
   *   <li>{@link DBDashboardContext#setProject(DBPProject)}
   *   <li>{@link DBDashboardContext#getDataSource()}
   *   <li>{@link DBDashboardContext#getProject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDashboardContext.<init>()",
    "void DBDashboardContext.<init>(DBPProject)",
    "DBPDataSourceContainer DBDashboardContext.getDataSource()",
    "DBPProject DBDashboardContext.getProject()",
    "void DBDashboardContext.setDataSource(DBPDataSourceContainer)",
    "void DBDashboardContext.setProject(DBPProject)"
  })
  public void testGettersAndSetters_whenDBPProject() {
    // Arrange and Act
    DBDashboardContext actualDbDashboardContext = new DBDashboardContext(mock(DBPProject.class));
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    actualDbDashboardContext.setDataSource(dataSource);
    DBPProject project = mock(DBPProject.class);
    actualDbDashboardContext.setProject(project);
    DBPDataSourceContainer actualDataSource = actualDbDashboardContext.getDataSource();

    // Assert
    assertSame(dataSource, actualDataSource);
    assertSame(project, actualDbDashboardContext.getProject());
  }

  /**
   * Test {@link DBDashboardContext#DBDashboardContext(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then return DataSource is {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBDashboardContext#DBDashboardContext(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDashboardContext.<init>(DBPDataSourceContainer)"})
  public void testNewDBDashboardContext_thenReturnDataSourceIsDBPDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getProject()).thenReturn(mock(DBPProject.class));

    // Act
    DBDashboardContext actualDbDashboardContext = new DBDashboardContext(dataSource);

    // Assert
    verify(dataSource).getProject();
    assertSame(dataSource, actualDbDashboardContext.getDataSource());
  }

  /**
   * Test {@link DBDashboardContext#DBDashboardContext(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return DataSource is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDashboardContext#DBDashboardContext(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDashboardContext.<init>(DBPDataSourceContainer)"})
  public void testNewDBDashboardContext_whenNull_thenReturnDataSourceIsNull() {
    // Arrange and Act
    DBDashboardContext actualDbDashboardContext =
        new DBDashboardContext((DBPDataSourceContainer) null);

    // Assert
    assertNull(actualDbDashboardContext.getDataSource());
    assertNull(actualDbDashboardContext.getProject());
  }
}
