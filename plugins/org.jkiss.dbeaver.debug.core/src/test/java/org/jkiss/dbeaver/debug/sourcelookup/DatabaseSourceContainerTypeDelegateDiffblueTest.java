package org.jkiss.dbeaver.debug.sourcelookup;

import static org.junit.Assert.assertNull;
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

public class DatabaseSourceContainerTypeDelegateDiffblueTest {
  /**
   * Test {@link DatabaseSourceContainerTypeDelegate#createSourceContainer(String)}.
   *
   * <p>Method under test: {@link DatabaseSourceContainerTypeDelegate#createSourceContainer(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ISourceContainer DatabaseSourceContainerTypeDelegate.createSourceContainer(String)"
  })
  public void testCreateSourceContainer() throws CoreException {
    // Arrange, Act and Assert
    assertNull(
        new DatabaseSourceContainerTypeDelegate()
            .createSourceContainer("alice.liddell@example.org"));
  }

  /**
   * Test {@link DatabaseSourceContainerTypeDelegate#getMemento(ISourceContainer)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseSourceContainerTypeDelegate#getMemento(ISourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseSourceContainerTypeDelegate.getMemento(ISourceContainer)"})
  public void testGetMemento_thenReturnNull() throws CoreException {
    // Arrange
    DatabaseSourceContainerTypeDelegate databaseSourceContainerTypeDelegate =
        new DatabaseSourceContainerTypeDelegate();

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(mock(DBPProject.class));

    DBPDataSourceContainer descriptor = mock(DBPDataSourceContainer.class);
    when(descriptor.getRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    String actualMemento =
        databaseSourceContainerTypeDelegate.getMemento(
            new DatabaseNavigatorSourceContainer(descriptor));

    // Assert
    verify(descriptor).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    assertNull(actualMemento);
  }
}
