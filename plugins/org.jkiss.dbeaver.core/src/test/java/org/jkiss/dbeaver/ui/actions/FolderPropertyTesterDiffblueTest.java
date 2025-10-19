package org.jkiss.dbeaver.ui.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceFolder;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPEventListener;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.navigator.DBNLocalFolder;
import org.jkiss.dbeaver.model.navigator.DBNProjectDatabases;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class FolderPropertyTesterDiffblueTest {
  /**
   * Test {@link FolderPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given empty array of {@link DBPDataSourceFolder}.
   *   <li>Then calls {@link DBPDataSourceFolder#getChildren()}.
   * </ul>
   *
   * <p>Method under test: {@link FolderPropertyTester#test(Object, String, Object[], Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FolderPropertyTester.test(Object, String, Object[], Object)"})
  public void testTest_givenEmptyArrayOfDBPDataSourceFolder_thenCallsGetChildren() {
    // Arrange
    FolderPropertyTester folderPropertyTester = new FolderPropertyTester();

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getChildren()).thenReturn(new DBPDataSourceFolder[] {});

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    boolean actualTestResult =
        folderPropertyTester.test(
            dbnLocalFolder, "Property", new Object[] {DBPEvent.RENAME}, DBPEvent.RENAME);

    // Assert
    verify(folder).getChildren();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertFalse(actualTestResult);
  }

  /**
   * Test {@link FolderPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <ul>
   *   <li>Given {@link FolderPropertyTester} (default constructor).
   *   <li>When array of {@link Object} with {@link DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FolderPropertyTester#test(Object, String, Object[], Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FolderPropertyTester.test(Object, String, Object[], Object)"})
  public void testTest_givenFolderPropertyTester_whenArrayOfObjectWithRename_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new FolderPropertyTester()
            .test(DBPEvent.RENAME, "Property", new Object[] {DBPEvent.RENAME}, DBPEvent.RENAME));
  }

  /**
   * Test new {@link FolderPropertyTester} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link FolderPropertyTester}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FolderPropertyTester.<init>()"})
  public void testNewFolderPropertyTester() {
    // Arrange, Act and Assert
    assertTrue(new FolderPropertyTester().isInstantiated());
  }
}
