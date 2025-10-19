package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
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
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceFolder;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPEvent.Action;
import org.jkiss.dbeaver.model.DBPEventListener;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.navigator.DBNEvent.NodeChange;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBNProjectDatabasesDiffblueTest {
  /**
   * Test {@link DBNProjectDatabases#DBNProjectDatabases(DBNProject, DBPDataSourceRegistry)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>When {@code null}.
   *   <li>Then NodeIcon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#DBNProjectDatabases(DBNProject,
   * DBPDataSourceRegistry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.<init>(DBNProject, DBPDataSourceRegistry)"})
  public void testNewDBNProjectDatabases_givenArrayList_whenNull_thenNodeIconReturnDBIcon() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    // Act
    DBNProjectDatabases actualDbnProjectDatabases =
        new DBNProjectDatabases(null, dataSourceRegistry);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    DBPImage nodeIcon = actualDbnProjectDatabases.getNodeIcon();
    assertTrue(nodeIcon instanceof DBIcon);
    assertEquals("Connection", actualDbnProjectDatabases.getChildrenType());
    assertEquals("Connections", actualDbnProjectDatabases.getNodeFullName());
    assertEquals("Connections", actualDbnProjectDatabases.getNodeTargetName());
    assertEquals("Connections", actualDbnProjectDatabases.getName());
    assertEquals("Connections", actualDbnProjectDatabases.getNodeDisplayName());
    assertEquals("datasources", actualDbnProjectDatabases.getNodeTypeLabel());
    assertEquals("datasources", actualDbnProjectDatabases.getNodeId());
    assertEquals("datasources", actualDbnProjectDatabases.getNodeType());
    assertEquals("node://datasources", actualDbnProjectDatabases.getNodeUri());
    assertNull(actualDbnProjectDatabases.getNodeBriefInfo());
    assertNull(actualDbnProjectDatabases.getLastLoadError());
    assertNull(actualDbnProjectDatabases.getOwnerProjectOrNull());
    assertNull(actualDbnProjectDatabases.getModel());
    assertNull(actualDbnProjectDatabases.getParentNode());
    assertFalse(actualDbnProjectDatabases.isFiltered());
    assertFalse(actualDbnProjectDatabases.isLocked());
    assertFalse(actualDbnProjectDatabases.isManageable());
    assertFalse(actualDbnProjectDatabases.isDisposed());
    assertTrue(actualDbnProjectDatabases.getDataSources().isEmpty());
    assertTrue(actualDbnProjectDatabases.isPersisted());
    Class<DBPDataSourceContainer> expectedChildrenClass = DBPDataSourceContainer.class;
    assertEquals(expectedChildrenClass, actualDbnProjectDatabases.getChildrenClass());
    assertSame(dataSourceRegistry, actualDbnProjectDatabases.getDataSourceRegistry());
    assertSame(dataSourceRegistry, actualDbnProjectDatabases.getValueObject());
    assertSame(nodeIcon, actualDbnProjectDatabases.getNodeIconDefault());
  }

  /**
   * Test {@link DBNProjectDatabases#isDisposed()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#isDisposed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.isDisposed()"})
  public void testIsDisposed_thenReturnFalse() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualIsDisposedResult = dbnProjectDatabases.isDisposed();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertFalse(actualIsDisposedResult);
  }

  /**
   * Test {@link DBNProjectDatabases#dispose(boolean)}.
   *
   * <p>Method under test: {@link DBNProjectDatabases#dispose(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.dispose(boolean)"})
  public void testDispose() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dataSourceRegistry.removeDataSourceListener(Mockito.<DBPEventListener>any()))
        .thenReturn(true);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    dbnProjectDatabases.dispose(true);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dataSourceRegistry).removeDataSourceListener(isA(DBPEventListener.class));
    assertNull(dbnProjectDatabases.getValueObject());
    assertNull(dbnProjectDatabases.getDataSourceRegistry());
    assertTrue(dbnProjectDatabases.isDisposed());
  }

  /**
   * Test {@link DBNProjectDatabases#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Connections}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNProjectDatabases.getName()"})
  public void testGetName_thenReturnConnections() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    String actualName = dbnProjectDatabases.getName();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertEquals("Connections", actualName);
  }

  /**
   * Test {@link DBNProjectDatabases#getNodeDescription()}.
   *
   * <ul>
   *   <li>Then return {@code Name connections}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getNodeDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNProjectDatabases.getNodeDescription()"})
  public void testGetNodeDescription_thenReturnNameConnections() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getName()).thenReturn("Name");

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getProject()).thenReturn(dbpProject);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    String actualNodeDescription = dbnProjectDatabases.getNodeDescription();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbpProject).getName();
    verify(parentNode).getProject();
    assertEquals("Name connections", actualNodeDescription);
  }

  /**
   * Test {@link DBNProjectDatabases#getParentNode()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getParentNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNProject DBNProjectDatabases.getParentNode()"})
  public void testGetParentNode_thenReturnNull() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    DBNProject actualParentNode = dbnProjectDatabases.getParentNode();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertNull(actualParentNode);
  }

  /**
   * Test {@link DBNProjectDatabases#allowsChildren()}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link DBPDataSourceFolder}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.allowsChildren()"})
  public void testAllowsChildren_givenArrayListAddDBPDataSourceFolder_thenReturnTrue() {
    // Arrange
    ArrayList<DBPDataSourceFolder> dbpDataSourceFolderList = new ArrayList<>();
    dbpDataSourceFolderList.add(mock(DBPDataSourceFolder.class));

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    Mockito.<List<? extends DBPDataSourceFolder>>when(dataSourceRegistry.getRootFolders())
        .thenReturn(dbpDataSourceFolderList);
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualAllowsChildrenResult = dbnProjectDatabases.allowsChildren();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dataSourceRegistry).getRootFolders();
    assertTrue(actualAllowsChildrenResult);
  }

  /**
   * Test {@link DBNProjectDatabases#allowsChildren()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.allowsChildren()"})
  public void testAllowsChildren_thenReturnFalse() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    Mockito.<List<? extends DBPDataSourceFolder>>when(dataSourceRegistry.getRootFolders())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualAllowsChildrenResult = dbnProjectDatabases.allowsChildren();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dataSourceRegistry).getRootFolders();
    assertFalse(actualAllowsChildrenResult);
  }

  /**
   * Test {@link DBNProjectDatabases#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceFolder#getParent()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode[] DBNProjectDatabases.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenCallsGetParent() {
    // Arrange
    DBPDataSourceFolder dbpDataSourceFolder = mock(DBPDataSourceFolder.class);
    when(dbpDataSourceFolder.getParent()).thenReturn(mock(DBPDataSourceFolder.class));

    ArrayList<DBPDataSourceFolder> dbpDataSourceFolderList = new ArrayList<>();
    dbpDataSourceFolderList.add(dbpDataSourceFolder);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceFolder>>when(dataSourceRegistry.getAllFolders())
        .thenReturn(dbpDataSourceFolderList);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    DBNNode[] actualChildren = dbnProjectDatabases.getChildren(new LoggingProgressMonitor());

    // Assert
    verify(dbpDataSourceFolder).getParent();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getAllFolders();
    verify(dataSourceRegistry).getDataSources();
    assertEquals(0, actualChildren.length);
  }

  /**
   * Test {@link DBNProjectDatabases#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode[] DBNProjectDatabases.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenReturnArrayLengthIsZero() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceFolder>>when(dataSourceRegistry.getAllFolders())
        .thenReturn(new ArrayList<>());
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    DBNNode[] actualChildren = dbnProjectDatabases.getChildren(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getAllFolders();
    verify(dataSourceRegistry).getDataSources();
    assertEquals(0, actualChildren.length);
  }

  /**
   * Test {@link DBNProjectDatabases#supportsDrop(DBNNode)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#supportsDrop(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.supportsDrop(DBNNode)"})
  public void testSupportsDrop_thenReturnFalse() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualSupportsDropResult = dbnProjectDatabases.supportsDrop(new DBNEmptyNode());

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertFalse(actualSupportsDropResult);
  }

  /**
   * Test {@link DBNProjectDatabases#supportsDrop(DBNNode)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#supportsDrop(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.supportsDrop(DBNNode)"})
  public void testSupportsDrop_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualSupportsDropResult = dbnProjectDatabases.supportsDrop(null);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualSupportsDropResult);
  }

  /**
   * Test {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <p>Method under test: {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes() throws DBException {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbnProjectDatabases.dropNodes(monitor, new ArrayList<>());

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_givenDBNEmptyNode_whenArrayListAddDBNEmptyNode() throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());

    // Act
    dbnProjectDatabases.dropNodes(monitor, nodes);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_givenDBNEmptyNode_whenArrayListAddDBNEmptyNode2() throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());
    nodes.add(new DBNEmptyNode());

    // Act
    dbnProjectDatabases.dropNodes(monitor, nodes);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBNModel} {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)} does
   *       nothing.
   *   <li>Then calls {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_givenDBNModelFireNodeUpdateDoesNothing_thenCallsFireNodeUpdate()
      throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbnProjectDatabases.dropNodes(monitor, new ArrayList<>());

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#moveNodesToFolder(Collection, DBPDataSourceFolder)}.
   *
   * <p>Method under test: {@link DBNProjectDatabases#moveNodesToFolder(Collection,
   * DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.moveNodesToFolder(Collection, DBPDataSourceFolder)"})
  public void testMoveNodesToFolder() throws DBException {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    dbnProjectDatabases.moveNodesToFolder(new ArrayList<>(), mock(DBPDataSourceFolder.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#moveNodesToFolder(Collection, DBPDataSourceFolder)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#moveNodesToFolder(Collection,
   * DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.moveNodesToFolder(Collection, DBPDataSourceFolder)"})
  public void testMoveNodesToFolder_givenDBNEmptyNode_whenArrayListAddDBNEmptyNode()
      throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());

    // Act
    dbnProjectDatabases.moveNodesToFolder(nodes, mock(DBPDataSourceFolder.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#moveNodesToFolder(Collection, DBPDataSourceFolder)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#moveNodesToFolder(Collection,
   * DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.moveNodesToFolder(Collection, DBPDataSourceFolder)"})
  public void testMoveNodesToFolder_givenDBNEmptyNode_whenArrayListAddDBNEmptyNode2()
      throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());
    nodes.add(new DBNEmptyNode());

    // Act
    dbnProjectDatabases.moveNodesToFolder(nodes, mock(DBPDataSourceFolder.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#moveNodesToFolder(Collection, DBPDataSourceFolder)}.
   *
   * <ul>
   *   <li>Then calls {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#moveNodesToFolder(Collection,
   * DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.moveNodesToFolder(Collection, DBPDataSourceFolder)"})
  public void testMoveNodesToFolder_thenCallsFireNodeUpdate() throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    dbnProjectDatabases.moveNodesToFolder(new ArrayList<>(), mock(DBPDataSourceFolder.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#refreshChildren()}.
   *
   * <p>Method under test: {@link DBNProjectDatabases#refreshChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.refreshChildren()"})
  public void testRefreshChildren() {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    dbnProjectDatabases.refreshChildren();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#refreshChildren()}.
   *
   * <ul>
   *   <li>Then calls {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#refreshChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.refreshChildren()"})
  public void testRefreshChildren_thenCallsFireNodeUpdate() {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    dbnProjectDatabases.refreshChildren();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel)
        .fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.STRUCT_REFRESH));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNProjectDatabases#allowsOpen()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#allowsOpen()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.allowsOpen()"})
  public void testAllowsOpen_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualAllowsOpenResult = dbnProjectDatabases.allowsOpen();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualAllowsOpenResult);
  }

  /**
   * Test {@link DBNProjectDatabases#getNodeItemPath()}.
   *
   * <ul>
   *   <li>Then return {@code Node Item Path/Connections}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getNodeItemPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNProjectDatabases.getNodeItemPath()"})
  public void testGetNodeItemPath_thenReturnNodeItemPathConnections() {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getNodeItemPath()).thenReturn("Node Item Path");

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    String actualNodeItemPath = dbnProjectDatabases.getNodeItemPath();

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getNodeItemPath();
    assertEquals("Node Item Path/Connections", actualNodeItemPath);
  }

  /**
   * Test {@link DBNProjectDatabases#getFolderNode(DBPDataSourceFolder)}.
   *
   * <ul>
   *   <li>Given {@link DBNModel} {@link DBNModel#fireNodeEvent(DBNEvent)} does nothing.
   *   <li>Then NodeIcon return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getFolderNode(DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNLocalFolder DBNProjectDatabases.getFolderNode(DBPDataSourceFolder)"})
  public void testGetFolderNode_givenDBNModelFireNodeEventDoesNothing_thenNodeIconReturnDBIcon() {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing().when(dbnModel).fireNodeEvent(Mockito.<DBNEvent>any());

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);
    DBPDataSourceFolder folder = mock(DBPDataSourceFolder.class);

    // Act
    DBNLocalFolder actualFolderNode = dbnProjectDatabases.getFolderNode(folder);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(dbnModel).fireNodeEvent(isA(DBNEvent.class));
    verify(parentNode).getModel();
    DBPImage nodeIcon = actualFolderNode.getNodeIcon();
    assertTrue(nodeIcon instanceof DBIcon);
    assertEquals("Connection", actualFolderNode.getChildrenType());
    assertEquals("Connections.null", actualFolderNode.getNodeFullName());
    assertEquals("folder", actualFolderNode.getNodeType());
    assertEquals("folder", actualFolderNode.getNodeTypeLabel());
    assertNull(actualFolderNode.getName());
    assertNull(actualFolderNode.getNodeDescription());
    assertNull(actualFolderNode.getNodeDisplayName());
    assertNull(actualFolderNode.getDescription());
    assertNull(actualFolderNode.getNodeBriefInfo());
    assertNull(actualFolderNode.getNodeId());
    assertNull(actualFolderNode.getNodeTargetName());
    assertNull(actualFolderNode.getLastLoadError());
    assertNull(actualFolderNode.getOwnerProjectOrNull());
    assertFalse(actualFolderNode.isDisposed());
    assertFalse(actualFolderNode.isFiltered());
    assertFalse(actualFolderNode.isLocked());
    assertFalse(actualFolderNode.isManageable());
    assertTrue(actualFolderNode.getDataSources().isEmpty());
    assertTrue(actualFolderNode.getNestedDataSources().isEmpty());
    assertTrue(actualFolderNode.isPersisted());
    Class<DBPDataSourceContainer> expectedChildrenClass = DBPDataSourceContainer.class;
    assertEquals(expectedChildrenClass, actualFolderNode.getChildrenClass());
    assertSame(dbnProjectDatabases, actualFolderNode.getLogicalParent());
    assertSame(dbnProjectDatabases, actualFolderNode.getParentNode());
    assertSame(folder, actualFolderNode.getFolder());
    assertSame(folder, actualFolderNode.getValueObject());
    assertSame(nodeIcon, actualFolderNode.getNodeIconDefault());
  }

  /**
   * Test {@link DBNProjectDatabases#getFolderNode(DBPDataSourceFolder)}.
   *
   * <ul>
   *   <li>Then LogicalParent return {@link DBNProjectDatabases}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getFolderNode(DBPDataSourceFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNLocalFolder DBNProjectDatabases.getFolderNode(DBPDataSourceFolder)"})
  public void testGetFolderNode_thenLogicalParentReturnDBNProjectDatabases() {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    // Act
    DBNLocalFolder actualFolderNode =
        dbnProjectDatabases.getFolderNode(mock(DBPDataSourceFolder.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getModel();
    DBNNode logicalParent = actualFolderNode.getLogicalParent();
    assertTrue(logicalParent instanceof DBNProjectDatabases);
    assertSame(dbnModel, logicalParent.getModel());
    assertSame(dbnModel, actualFolderNode.getModel());
  }

  /**
   * Test {@link DBNProjectDatabases#getDataSource(DBPDataSourceContainer)} with {@code ds}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getDataSource(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDataSource DBNProjectDatabases.getDataSource(DBPDataSourceContainer)"})
  public void testGetDataSourceWithDs_thenReturnNull() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    DBNDataSource actualDataSource =
        dbnProjectDatabases.getDataSource(mock(DBPDataSourceContainer.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertNull(actualDataSource);
  }

  /**
   * Test {@link DBNProjectDatabases#getDataSource(String)} with {@code id}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#getDataSource(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDataSource DBNProjectDatabases.getDataSource(String)"})
  public void testGetDataSourceWithId_thenReturnNull() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    DBNDataSource actualDataSource = dbnProjectDatabases.getDataSource("42");

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertNull(actualDataSource);
  }

  /**
   * Test {@link DBNProjectDatabases#removeDataSource(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceRegistry#addDataSourceListener(DBPEventListener)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#removeDataSource(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.removeDataSource(DBPDataSourceContainer)"})
  public void testRemoveDataSource_thenCallsAddDataSourceListener() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    dbnProjectDatabases.removeDataSource(mock(DBPDataSourceContainer.class));

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
  }

  /**
   * Test {@link DBNProjectDatabases#handleDataSourceEvent(DBPEvent)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPEvent#getAction()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#handleDataSourceEvent(DBPEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNProjectDatabases.handleDataSourceEvent(DBPEvent)"})
  public void testHandleDataSourceEvent_thenCallsGetAction() {
    // Arrange
    DBNProject parentNode = mock(DBNProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());

    DBNProjectDatabases dbnProjectDatabases =
        new DBNProjectDatabases(parentNode, dataSourceRegistry);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getParentObject()).thenReturn(null);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBSDocumentContainer entity127 = mock(DBSDocumentContainer.class);
    when(entity127.getParentObject()).thenReturn(new DBSDocumentConstraint(entity126));
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity127);

    DBPEvent event = mock(DBPEvent.class);
    when(event.getOptions()).thenReturn(new HashMap<>());
    when(event.getObject()).thenReturn(dbsDocumentConstraint);
    when(event.getAction()).thenReturn(Action.OBJECT_ADD);

    // Act
    dbnProjectDatabases.handleDataSourceEvent(event);

    // Assert
    verify(event).getAction();
    verify(event, atLeast(1)).getObject();
    verify(event).getOptions();
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    verify(parentNode).getModel();
    verify(entity127, atLeast(1)).getParentObject();
    verify(entity126, atLeast(1)).getParentObject();
    verify(entity125, atLeast(1)).getParentObject();
    verify(entity124, atLeast(1)).getParentObject();
    verify(entity123, atLeast(1)).getParentObject();
    verify(entity122, atLeast(1)).getParentObject();
    verify(entity121, atLeast(1)).getParentObject();
    verify(entity120, atLeast(1)).getParentObject();
    verify(entity119, atLeast(1)).getParentObject();
    verify(entity118, atLeast(1)).getParentObject();
    verify(entity117, atLeast(1)).getParentObject();
    verify(entity116, atLeast(1)).getParentObject();
    verify(entity115, atLeast(1)).getParentObject();
    verify(entity114, atLeast(1)).getParentObject();
    verify(entity113, atLeast(1)).getParentObject();
    verify(entity112, atLeast(1)).getParentObject();
    verify(entity111, atLeast(1)).getParentObject();
    verify(entity110, atLeast(1)).getParentObject();
    verify(entity109, atLeast(1)).getParentObject();
    verify(entity108, atLeast(1)).getParentObject();
    verify(entity107, atLeast(1)).getParentObject();
    verify(entity106, atLeast(1)).getParentObject();
    verify(entity105, atLeast(1)).getParentObject();
    verify(entity104, atLeast(1)).getParentObject();
    verify(entity103, atLeast(1)).getParentObject();
    verify(entity102, atLeast(1)).getParentObject();
    verify(entity101, atLeast(1)).getParentObject();
    verify(entity100, atLeast(1)).getParentObject();
    verify(entity99, atLeast(1)).getParentObject();
    verify(entity98, atLeast(1)).getParentObject();
    verify(entity97, atLeast(1)).getParentObject();
    verify(entity96, atLeast(1)).getParentObject();
    verify(entity95, atLeast(1)).getParentObject();
    verify(entity94, atLeast(1)).getParentObject();
    verify(entity93, atLeast(1)).getParentObject();
    verify(entity92, atLeast(1)).getParentObject();
    verify(entity91, atLeast(1)).getParentObject();
    verify(entity90, atLeast(1)).getParentObject();
    verify(entity89, atLeast(1)).getParentObject();
    verify(entity88, atLeast(1)).getParentObject();
    verify(entity87, atLeast(1)).getParentObject();
    verify(entity86, atLeast(1)).getParentObject();
    verify(entity85, atLeast(1)).getParentObject();
    verify(entity84, atLeast(1)).getParentObject();
    verify(entity83, atLeast(1)).getParentObject();
    verify(entity82, atLeast(1)).getParentObject();
    verify(entity81, atLeast(1)).getParentObject();
    verify(entity80, atLeast(1)).getParentObject();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
  }

  /**
   * Test {@link DBNProjectDatabases#hasChildren(boolean)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNProjectDatabases#hasChildren(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNProjectDatabases.hasChildren(boolean)"})
  public void testHasChildren_thenReturnTrue() {
    // Arrange
    DBPDataSourceRegistry dataSourceRegistry = mock(DBPDataSourceRegistry.class);
    Mockito.<List<? extends DBPDataSourceContainer>>when(dataSourceRegistry.getDataSources())
        .thenReturn(new ArrayList<>());
    doNothing().when(dataSourceRegistry).addDataSourceListener(Mockito.<DBPEventListener>any());
    DBNProjectDatabases dbnProjectDatabases = new DBNProjectDatabases(null, dataSourceRegistry);

    // Act
    boolean actualHasChildrenResult = dbnProjectDatabases.hasChildren(true);

    // Assert
    verify(dataSourceRegistry).addDataSourceListener(isA(DBPEventListener.class));
    verify(dataSourceRegistry).getDataSources();
    assertTrue(actualHasChildrenResult);
  }
}
