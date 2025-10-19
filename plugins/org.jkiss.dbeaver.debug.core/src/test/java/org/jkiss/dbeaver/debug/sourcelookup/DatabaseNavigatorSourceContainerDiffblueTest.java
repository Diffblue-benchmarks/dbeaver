package org.jkiss.dbeaver.debug.sourcelookup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseNavigatorSourceContainerDiffblueTest {
  /**
   * Test {@link
   * DatabaseNavigatorSourceContainer#DatabaseNavigatorSourceContainer(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link
   * DatabaseNavigatorSourceContainer#DatabaseNavigatorSourceContainer(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseNavigatorSourceContainer.<init>(DBPDataSourceContainer)"})
  public void testNewDatabaseNavigatorSourceContainer() throws CoreException {
    // Arrange
    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(mock(DBPProject.class));

    DBPDataSourceContainer descriptor = mock(DBPDataSourceContainer.class);
    when(descriptor.getRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    DatabaseNavigatorSourceContainer actualDatabaseNavigatorSourceContainer =
        new DatabaseNavigatorSourceContainer(descriptor);

    // Assert
    verify(descriptor).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    assertNull(actualDatabaseNavigatorSourceContainer.getName());
    assertEquals(0, actualDatabaseNavigatorSourceContainer.getSourceContainers().length);
    assertTrue(actualDatabaseNavigatorSourceContainer.isComposite());
  }

  /**
   * Test {@link DatabaseNavigatorSourceContainer#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceRegistry} {@link DBPDataSourceRegistry#getProject()} return
   *       {@link DBPProject}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseNavigatorSourceContainer#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseNavigatorSourceContainer.getName()"})
  public void testGetName_givenDBPDataSourceRegistryGetProjectReturnDBPProject_thenReturnName() {
    // Arrange
    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(mock(DBPProject.class));

    DBPDataSourceContainer descriptor = mock(DBPDataSourceContainer.class);
    when(descriptor.getName()).thenReturn("Name");
    when(descriptor.getRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    String actualName = new DatabaseNavigatorSourceContainer(descriptor).getName();

    // Assert
    verify(descriptor).getRegistry();
    verify(descriptor).getName();
    verify(dbpDataSourceRegistry).getProject();
    assertEquals("Name", actualName);
  }

  /**
   * Test {@link DatabaseNavigatorSourceContainer#createSourceContainers()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseNavigatorSourceContainer#createSourceContainers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ISourceContainer[] DatabaseNavigatorSourceContainer.createSourceContainers()"
  })
  public void testCreateSourceContainers_thenReturnArrayLengthIsZero() throws CoreException {
    // Arrange
    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(mock(DBPProject.class));

    DBPDataSourceContainer descriptor = mock(DBPDataSourceContainer.class);
    when(descriptor.getRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    ISourceContainer[] actualCreateSourceContainersResult =
        new DatabaseNavigatorSourceContainer(descriptor).createSourceContainers();

    // Assert
    verify(descriptor).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    assertEquals(0, actualCreateSourceContainersResult.length);
  }
}
