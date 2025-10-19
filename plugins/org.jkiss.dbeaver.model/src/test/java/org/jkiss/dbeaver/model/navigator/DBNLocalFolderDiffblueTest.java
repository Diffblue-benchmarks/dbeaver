package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceFolder;
import org.jkiss.dbeaver.model.DBPEventListener;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBNLocalFolderDiffblueTest {
  /**
   * Test {@link DBNLocalFolder#dispose(boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceRegistry#addDataSourceListener(DBPEventListener)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#dispose(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNLocalFolder.dispose(boolean)"})
  public void testDispose_thenCallsAddDataSourceListener() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    dbnLocalFolder.dispose(true);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
  }

  /**
   * Test {@link DBNLocalFolder#getDataSourceRegistry()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceRegistry#addDataSourceListener(DBPEventListener)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getDataSourceRegistry()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceRegistry DBNLocalFolder.getDataSourceRegistry()"})
  public void testGetDataSourceRegistry_thenCallsAddDataSourceListener() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    dbnLocalFolder.getDataSourceRegistry();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
  }

  /**
   * Test {@link DBNLocalFolder#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.getName()"})
  public void testGetName_thenReturnName() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getName()).thenReturn("Name");

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    String actualName = dbnLocalFolder.getName();

    // Assert
    verify(folder).getName();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertEquals("Name", actualName);
  }

  /**
   * Test {@link DBNLocalFolder#getNodeDisplayName()}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getNodeDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.getNodeDisplayName()"})
  public void testGetNodeDisplayName_thenReturnName() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getName()).thenReturn("Name");

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    String actualNodeDisplayName = dbnLocalFolder.getNodeDisplayName();

    // Assert
    verify(folder).getName();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertEquals("Name", actualNodeDisplayName);
  }

  /**
   * Test {@link DBNLocalFolder#getNodeDescription()}.
   *
   * <ul>
   *   <li>Then return {@code The characteristics of someone or something}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getNodeDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.getNodeDescription()"})
  public void testGetNodeDescription_thenReturnTheCharacteristicsOfSomeoneOrSomething() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getDescription()).thenReturn("The characteristics of someone or something");

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    String actualNodeDescription = dbnLocalFolder.getNodeDescription();

    // Assert
    verify(folder).getDescription();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertEquals("The characteristics of someone or something", actualNodeDescription);
  }

  /**
   * Test {@link DBNLocalFolder#getNodeIcon()}.
   *
   * <ul>
   *   <li>Then return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getNodeIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBNLocalFolder.getNodeIcon()"})
  public void testGetNodeIcon_thenReturnDBIcon() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    DBPImage actualNodeIcon = dbnLocalFolder.getNodeIcon();
    String actualLocation = actualNodeIcon.getLocation();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualNodeIcon instanceof DBIcon);
    DBNNode logicalParent = dbnLocalFolder.getLogicalParent();
    DBPImage nodeIcon = logicalParent.getNodeIcon();
    assertTrue(nodeIcon instanceof DBIcon);
    assertTrue(logicalParent instanceof DBNProjectDatabases);
    assertEquals("folder_database", ((DBIcon) actualNodeIcon).getToken());
    assertEquals("tree/folder_database.svg", actualNodeIcon.getLocation());
    assertEquals("tree/folder_database.svg", actualLocation);
    DBIcon dbIcon = ((DBIcon) actualNodeIcon).TREE_DATABASE_CATEGORY;
    assertSame(dbIcon, nodeIcon);
    assertSame(dbIcon, logicalParent.getNodeIconDefault());
    assertSame(((DBIcon) actualNodeIcon).TREE_FOLDER_DATABASE, dbnLocalFolder.getNodeIconDefault());
  }

  /**
   * Test {@link DBNLocalFolder#getNodeItemPath()}.
   *
   * <ul>
   *   <li>Then return {@code folder://42/Folder Path}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getNodeItemPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.getNodeItemPath()"})
  public void testGetNodeItemPath_thenReturnFolder42FolderPath() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getId()).thenReturn("42");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getFolderPath()).thenReturn("Folder Path");
    when(folder.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    String actualNodeItemPath = dbnLocalFolder.getNodeItemPath();

    // Assert
    verify(folder).getDataSourceRegistry();
    verify(folder).getFolderPath();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getId();
    assertEquals("folder://42/Folder Path", actualNodeItemPath);
  }

  /**
   * Test {@link DBNLocalFolder#getParentNode()}.
   *
   * <p>Method under test: {@link DBNLocalFolder#getParentNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNProjectDatabases DBNLocalFolder.getParentNode()"})
  public void testGetParentNode() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    DBNProjectDatabases actualParentNode = dbnLocalFolder.getParentNode();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertSame(dbnLocalFolder.parentNode, actualParentNode);
  }

  /**
   * Test {@link DBNLocalFolder#allowsChildren()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.allowsChildren()"})
  public void testAllowsChildren_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    boolean actualAllowsChildrenResult = dbnLocalFolder.allowsChildren();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualAllowsChildrenResult);
  }

  /**
   * Test {@link DBNLocalFolder#hasChildren(boolean)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#hasChildren(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.hasChildren(boolean)"})
  public void testHasChildren_thenReturnFalse() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getChildren()).thenReturn(new DBPDataSourceFolder[] {});

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));
    dbnLocalFolder.setFolder(folder);

    // Act
    boolean actualHasChildrenResult = dbnLocalFolder.hasChildren(true);

    // Assert
    verify(folder).getChildren();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertFalse(actualHasChildrenResult);
  }

  /**
   * Test {@link DBNLocalFolder#hasChildren(boolean)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#hasChildren(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.hasChildren(boolean)"})
  public void testHasChildren_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getChildren())
        .thenReturn(new DBPDataSourceFolder[] {mock(DBPDataSourceFolder.class)});

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    boolean actualHasChildrenResult = dbnLocalFolder.hasChildren(true);

    // Assert
    verify(folder).getChildren();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualHasChildrenResult);
  }

  /**
   * Test {@link DBNLocalFolder#getLogicalParent()}.
   *
   * <ul>
   *   <li>Then NodeIcon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getLogicalParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNLocalFolder.getLogicalParent()"})
  public void testGetLogicalParent_thenNodeIconReturnDBIcon() {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases parentNode2 = new DBNProjectDatabases(parentNode, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getParent()).thenReturn(mock(DBPDataSourceFolder.class));

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode2, folder);

    // Act
    DBNNode actualLogicalParent = dbnLocalFolder.getLogicalParent();

    // Assert
    verify(folder, atLeast(1)).getParent();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getModel();
    DBPImage nodeIcon = actualLogicalParent.getNodeIcon();
    assertTrue(nodeIcon instanceof DBIcon);
    assertTrue(actualLogicalParent instanceof DBNLocalFolder);
    assertEquals("Connection", ((DBNLocalFolder) actualLogicalParent).getChildrenType());
    assertEquals("Connections.null", actualLogicalParent.getNodeFullName());
    assertEquals("folder", actualLogicalParent.getNodeType());
    assertEquals("folder", actualLogicalParent.getNodeTypeLabel());
    assertNull(actualLogicalParent.getDescription());
    assertNull(actualLogicalParent.getName());
    assertNull(actualLogicalParent.getNodeBriefInfo());
    assertNull(actualLogicalParent.getNodeDescription());
    assertNull(actualLogicalParent.getNodeDisplayName());
    assertNull(actualLogicalParent.getNodeId());
    assertNull(actualLogicalParent.getNodeTargetName());
    assertNull(actualLogicalParent.getLastLoadError());
    assertNull(actualLogicalParent.getOwnerProjectOrNull());
    assertFalse(actualLogicalParent.isDisposed());
    assertFalse(actualLogicalParent.isFiltered());
    assertFalse(actualLogicalParent.isLocked());
    assertFalse(actualLogicalParent.isManageable());
    assertTrue(((DBNLocalFolder) actualLogicalParent).getDataSources().isEmpty());
    assertTrue(((DBNLocalFolder) actualLogicalParent).getNestedDataSources().isEmpty());
    assertTrue(actualLogicalParent.isPersisted());
    Class<DBPDataSourceContainer> expectedChildrenClass = DBPDataSourceContainer.class;
    assertEquals(expectedChildrenClass, ((DBNLocalFolder) actualLogicalParent).getChildrenClass());
    assertSame(dbnModel, actualLogicalParent.getModel());
    assertSame(parentNode2, actualLogicalParent.getParentNode());
    assertSame(nodeIcon, actualLogicalParent.getNodeIconDefault());
  }

  /**
   * Test {@link DBNLocalFolder#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code DBNDataSource[]}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode[] DBNLocalFolder.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenReturnDBNDataSource() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getChildren()).thenReturn(new DBPDataSourceFolder[] {});

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    DBNNode[] actualChildren = dbnLocalFolder.getChildren(new LoggingProgressMonitor());

    // Assert
    verify(folder).getChildren();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualChildren instanceof DBNDataSource[]);
    assertEquals(0, actualChildren.length);
  }

  /**
   * Test {@link DBNLocalFolder#getDataSources()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBNLocalFolder.getDataSources()"})
  public void testGetDataSources_thenReturnEmpty() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    List<DBNDataSource> actualDataSources = dbnLocalFolder.getDataSources();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualDataSources.isEmpty());
  }

  /**
   * Test {@link DBNLocalFolder#supportsDrop(DBNNode)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#supportsDrop(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.supportsDrop(DBNNode)"})
  public void testSupportsDrop_thenReturnFalse() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    boolean actualSupportsDropResult = dbnLocalFolder.supportsDrop(new DBNEmptyNode());

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertFalse(actualSupportsDropResult);
  }

  /**
   * Test {@link DBNLocalFolder#supportsDrop(DBNNode)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#supportsDrop(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.supportsDrop(DBNNode)"})
  public void testSupportsDrop_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    boolean actualSupportsDropResult = dbnLocalFolder.supportsDrop(null);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualSupportsDropResult);
  }

  /**
   * Test {@link DBNLocalFolder#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNLocalFolder.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_givenDBNEmptyNode_thenThrowIllegalStateException() throws DBException {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbnLocalFolder.dropNodes(monitor, nodes));
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
  }

  /**
   * Test {@link DBNLocalFolder#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNLocalFolder.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_givenDBNEmptyNode_thenThrowIllegalStateException2() throws DBException {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());
    nodes.add(new DBNEmptyNode());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbnLocalFolder.dropNodes(monitor, nodes));
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
  }

  /**
   * Test {@link DBNLocalFolder#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNLocalFolder.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_thenDoesNotThrow() throws DBException {
    // Arrange
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(null, mock(DBPDataSourceFolder.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    dbnLocalFolder.dropNodes(monitor, new ArrayList<>());
  }

  /**
   * Test {@link DBNLocalFolder#supportsRename()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#supportsRename()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.supportsRename()"})
  public void testSupportsRename_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);
    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, mock(DBPDataSourceFolder.class));

    // Act
    boolean actualSupportsRenameResult = dbnLocalFolder.supportsRename();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualSupportsRenameResult);
  }

  /**
   * Test {@link DBNLocalFolder#hasConnected()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#hasConnected()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNLocalFolder.hasConnected()"})
  public void testHasConnected_thenReturnFalse() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getChildren()).thenReturn(new DBPDataSourceFolder[] {});

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    boolean actualHasConnectedResult = dbnLocalFolder.hasConnected();

    // Assert
    verify(folder).getChildren();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertFalse(actualHasConnectedResult);
  }

  /**
   * Test {@link DBNLocalFolder#getNestedDataSources()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#getNestedDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBNLocalFolder.getNestedDataSources()"})
  public void testGetNestedDataSources_thenReturnEmpty() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getChildren()).thenReturn(new DBPDataSourceFolder[] {});

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    // Act
    List<DBNDataSource> actualNestedDataSources = dbnLocalFolder.getNestedDataSources();

    // Assert
    verify(folder).getChildren();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualNestedDataSources.isEmpty());
  }

  /**
   * Test {@link DBNLocalFolder#generateNewFolderPath(DBPDataSourceFolder, String)}.
   *
   * <ul>
   *   <li>Then return {@code Folder Path/New Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBNLocalFolder#generateNewFolderPath(DBPDataSourceFolder, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.generateNewFolderPath(DBPDataSourceFolder, String)"})
  public void testGenerateNewFolderPath_thenReturnFolderPathNewName() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases parentNode = new DBNProjectDatabases(null, dataSourceRegistry);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getFolderPath()).thenReturn("Folder Path");

    DBNLocalFolder dbnLocalFolder = new DBNLocalFolder(parentNode, folder);

    DBPDataSourceFolder newParent = mock(DBPDataSourceFolder.class);
    when(newParent.getFolderPath()).thenReturn("Folder Path");

    // Act
    String actualGenerateNewFolderPathResult =
        dbnLocalFolder.generateNewFolderPath(newParent, "New Name");

    // Assert
    verify(folder).getFolderPath();
    verify(newParent).getFolderPath();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertEquals("Folder Path/New Name", actualGenerateNewFolderPathResult);
  }

  /**
   * Test {@link DBNLocalFolder#makeLocalFolderItemPath(DBPDataSourceFolder)} with {@code folder}.
   *
   * <p>Method under test: {@link DBNLocalFolder#makeLocalFolderItemPath(DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.makeLocalFolderItemPath(DBPDataSourceFolder)"})
  public void testMakeLocalFolderItemPathWithFolder() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getId()).thenReturn("42");

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);
    when(folder.getFolderPath()).thenReturn("Folder Path");
    when(folder.getDataSourceRegistry()).thenReturn(dbpDataSourceRegistry);

    // Act
    String actualMakeLocalFolderItemPathResult = DBNLocalFolder.makeLocalFolderItemPath(folder);

    // Assert
    verify(folder).getDataSourceRegistry();
    verify(folder).getFolderPath();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getId();
    assertEquals("folder://42/Folder Path", actualMakeLocalFolderItemPathResult);
  }

  /**
   * Test {@link DBNLocalFolder#makeLocalFolderItemPath(String, String)} with {@code projectId},
   * {@code folderPath}.
   *
   * <p>Method under test: {@link DBNLocalFolder#makeLocalFolderItemPath(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNLocalFolder.makeLocalFolderItemPath(String, String)"})
  public void testMakeLocalFolderItemPathWithProjectIdFolderPath() {
    // Arrange, Act and Assert
    assertEquals(
        "folder://myproject/Folder Path",
        DBNLocalFolder.makeLocalFolderItemPath("myproject", "Folder Path"));
  }
}
