package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.impl.PropertyGroupDescriptor;
import org.jkiss.dbeaver.model.navigator.DBNNode.NodePathType;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeDescriptor;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeFolder;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeNode;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBNNodeDiffblueTest {
  /**
   * Test {@link DBNNode#isDisposed()}.
   *
   * <p>Method under test: {@link DBNNode#isDisposed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.isDisposed()"})
  public void testIsDisposed() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().isDisposed());
  }

  /**
   * Test {@link DBNNode#getModel()}.
   *
   * <p>Method under test: {@link DBNNode#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.navigator.DBNModel DBNNode.getModel()"})
  public void testGetModel() {
    // Arrange, Act and Assert
    assertNull(new DBNEmptyNode().getModel());
  }

  /**
   * Test {@link DBNNode#getParentNode()}.
   *
   * <p>Method under test: {@link DBNNode#getParentNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNNode.getParentNode()"})
  public void testGetParentNode() {
    // Arrange, Act and Assert
    assertNull(new DBNEmptyNode().getParentNode());
  }

  /**
   * Test {@link DBNNode#isLocked()}.
   *
   * <p>Method under test: {@link DBNNode#isLocked()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.isLocked()"})
  public void testIsLocked() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().isLocked());
  }

  /**
   * Test {@link DBNNode#isPersisted()}.
   *
   * <p>Method under test: {@link DBNNode#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.isPersisted()"})
  public void testIsPersisted() {
    // Arrange, Act and Assert
    assertTrue(new DBNEmptyNode().isPersisted());
  }

  /**
   * Test {@link DBNNode#isManageable()}.
   *
   * <p>Method under test: {@link DBNNode#isManageable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.isManageable()"})
  public void testIsManageable() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().isManageable());
  }

  /**
   * Test {@link DBNNode#getNodeId()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getNodeId()"})
  public void testGetNodeId() {
    // Arrange, Act and Assert
    assertEquals("#empty", new DBNEmptyNode().getNodeId());
  }

  /**
   * Test {@link DBNNode#getName()}.
   *
   * <p>Method under test: {@link DBNNode#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("#empty", new DBNEmptyNode().getName());
  }

  /**
   * Test {@link DBNNode#getLocalizedName(String)}.
   *
   * <p>Method under test: {@link DBNNode#getLocalizedName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getLocalizedName(String)"})
  public void testGetLocalizedName() {
    // Arrange, Act and Assert
    assertEquals("#empty", new DBNEmptyNode().getLocalizedName("en"));
  }

  /**
   * Test {@link DBNNode#getNodeTypeLabel()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeTypeLabel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getNodeTypeLabel()"})
  public void testGetNodeTypeLabel() {
    // Arrange, Act and Assert
    assertEquals("empty", new DBNEmptyNode().getNodeTypeLabel());
  }

  /**
   * Test {@link DBNNode#getNodeBriefInfo()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeBriefInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getNodeBriefInfo()"})
  public void testGetNodeBriefInfo() {
    // Arrange, Act and Assert
    assertNull(new DBNEmptyNode().getNodeBriefInfo());
  }

  /**
   * Test {@link DBNNode#getDescription()}.
   *
   * <p>Method under test: {@link DBNNode#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getDescription()"})
  public void testGetDescription() {
    // Arrange, Act and Assert
    assertEquals("Empty", new DBNEmptyNode().getDescription());
  }

  /**
   * Test {@link DBNNode#getNodeIconDefault()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeIconDefault()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBNNode.getNodeIconDefault()"})
  public void testGetNodeIconDefault() {
    // Arrange and Act
    DBPImage actualNodeIconDefault = new DBNEmptyNode().getNodeIconDefault();
    String actualLocation = actualNodeIconDefault.getLocation();

    // Assert
    assertTrue(actualNodeIconDefault instanceof DBIcon);
    assertEquals("page", ((DBIcon) actualNodeIconDefault).getToken());
    assertEquals("tree/page.svg", actualNodeIconDefault.getLocation());
    assertEquals("tree/page.svg", actualLocation);
  }

  /**
   * Test {@link DBNNode#getNodeFullName()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeFullName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getNodeFullName()"})
  public void testGetNodeFullName() {
    // Arrange, Act and Assert
    assertEquals("#empty", new DBNEmptyNode().getNodeFullName());
  }

  /**
   * Test {@link DBNNode#getNodeTargetName()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeTargetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getNodeTargetName()"})
  public void testGetNodeTargetName() {
    // Arrange, Act and Assert
    assertEquals("#empty", new DBNEmptyNode().getNodeTargetName());
  }

  /**
   * Test {@link DBNNode#hasChildren(boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#hasChildren(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.hasChildren(boolean)"})
  public void testHasChildren_whenFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().hasChildren(false));
  }

  /**
   * Test {@link DBNNode#hasChildren(boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#hasChildren(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.hasChildren(boolean)"})
  public void testHasChildren_whenTrue() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().hasChildren(true));
  }

  /**
   * Test {@link DBNNode#allowsNavigableChildren()}.
   *
   * <p>Method under test: {@link DBNNode#allowsNavigableChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.allowsNavigableChildren()"})
  public void testAllowsNavigableChildren() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().allowsNavigableChildren());
  }

  /**
   * Test NodePathType {@link NodePathType#getPrefix()}.
   *
   * <p>Method under test: {@link NodePathType#getPrefix()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NodePathType.getPrefix()"})
  public void testNodePathTypeGetPrefix() {
    // Arrange, Act and Assert
    assertEquals("resource://", NodePathType.resource.getPrefix());
  }

  /**
   * Test {@link DBNNode#supportsRename()}.
   *
   * <p>Method under test: {@link DBNNode#supportsRename()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.supportsRename()"})
  public void testSupportsRename() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().supportsRename());
  }

  /**
   * Test {@link DBNNode#rename(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link DBNNode#rename(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.rename(DBRProgressMonitor, String)"})
  public void testRename() throws DBException {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();

    // Act and Assert
    assertThrows(
        DBException.class, () -> dbnEmptyNode.rename(new LoggingProgressMonitor(), "New Name"));
  }

  /**
   * Test {@link DBNNode#supportsDrop(DBNNode)}.
   *
   * <p>Method under test: {@link DBNNode#supportsDrop(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.supportsDrop(DBNNode)"})
  public void testSupportsDrop() {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();

    // Act and Assert
    assertFalse(dbnEmptyNode.supportsDrop(new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNNode#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_whenArrayList() throws DBException {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(DBException.class, () -> dbnEmptyNode.dropNodes(monitor, new ArrayList<>()));
  }

  /**
   * Test {@link DBNNode#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_whenArrayListAddDBNEmptyNode() throws DBException {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());

    // Act and Assert
    assertThrows(DBException.class, () -> dbnEmptyNode.dropNodes(monitor, nodes));
  }

  /**
   * Test {@link DBNNode#dropNodes(DBRProgressMonitor, Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#dropNodes(DBRProgressMonitor, Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.dropNodes(DBRProgressMonitor, Collection)"})
  public void testDropNodes_whenArrayListAddDBNEmptyNode2() throws DBException {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());
    nodes.add(new DBNEmptyNode());

    // Act and Assert
    assertThrows(DBException.class, () -> dbnEmptyNode.dropNodes(monitor, nodes));
  }

  /**
   * Test {@link DBNNode#refreshNode(DBRProgressMonitor, Object)}.
   *
   * <p>Method under test: {@link DBNNode#refreshNode(DBRProgressMonitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNNode.refreshNode(DBRProgressMonitor, Object)"})
  public void testRefreshNode() throws DBException {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();

    // Act and Assert
    assertNull(dbnEmptyNode.refreshNode(new LoggingProgressMonitor(), DBNEvent.FORCE_REFRESH));
  }

  /**
   * Test {@link DBNNode#allowsOpen()}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#allowsOpen()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.allowsOpen()"})
  public void testAllowsOpen_givenDBNEmptyNode_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().allowsOpen());
  }

  /**
   * Test {@link DBNNode#isChildOf(DBNNode)}.
   *
   * <p>Method under test: {@link DBNNode#isChildOf(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.isChildOf(DBNNode)"})
  public void testIsChildOf() {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();

    // Act and Assert
    assertFalse(dbnEmptyNode.isChildOf(new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNNode#isFiltered()}.
   *
   * <p>Method under test: {@link DBNNode#isFiltered()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.isFiltered()"})
  public void testIsFiltered() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().isFiltered());
  }

  /**
   * Test {@link DBNNode#getNodeUri()}.
   *
   * <p>Method under test: {@link DBNNode#getNodeUri()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.getNodeUri()"})
  public void testGetNodeUri() {
    // Arrange, Act and Assert
    assertEquals("node://#empty", new DBNEmptyNode().getNodeUri());
  }

  /**
   * Test {@link DBNNode#getAdapter(Class)}.
   *
   * <p>Method under test: {@link DBNNode#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBNNode.getAdapter(Class)"})
  public void testGetAdapter() {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(dbnEmptyNode.getAdapter(adapter));
  }

  /**
   * Test {@link DBNNode#getOwnerProjectOrNull()}.
   *
   * <p>Method under test: {@link DBNNode#getOwnerProjectOrNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.app.DBPProject DBNNode.getOwnerProjectOrNull()"})
  public void testGetOwnerProjectOrNull() {
    // Arrange, Act and Assert
    assertNull(new DBNEmptyNode().getOwnerProjectOrNull());
  }

  /**
   * Test {@link DBNNode#getOwnerProject()}.
   *
   * <p>Method under test: {@link DBNNode#getOwnerProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.app.DBPProject DBNNode.getOwnerProject()"})
  public void testGetOwnerProject() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> new DBNEmptyNode().getOwnerProject());
  }

  /**
   * Test {@link DBNNode#getLastLoadError()}.
   *
   * <p>Method under test: {@link DBNNode#getLastLoadError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Throwable DBNNode.getLastLoadError()"})
  public void testGetLastLoadError() {
    // Arrange, Act and Assert
    assertNull(new DBNEmptyNode().getLastLoadError());
  }

  /**
   * Test {@link DBNNode#sortNodes(List)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>Then {@link ArrayList#ArrayList()} size is three.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#sortNodes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.sortNodes(List)"})
  public void testSortNodes_givenDBNEmptyNode_thenArrayListSizeIsThree() {
    // Arrange
    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    nodes.add(dbnEmptyNode);
    nodes.add(new DBNEmptyNode());

    // Act
    DBNNode.sortNodes(nodes);

    // Assert that nothing has changed
    assertEquals(3, nodes.size());
    DBNNode getResult = nodes.get(1);
    assertTrue(getResult instanceof DBNEmptyNode);
    assertSame(dbnEmptyNode, getResult);
  }

  /**
   * Test {@link DBNNode#sortNodes(List)}.
   *
   * <ul>
   *   <li>Given {@link DBNEmptyNode} (default constructor).
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#sortNodes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.sortNodes(List)"})
  public void testSortNodes_givenDBNEmptyNode_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(new DBNEmptyNode());
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    nodes.add(dbnEmptyNode);

    // Act
    DBNNode.sortNodes(nodes);

    // Assert that nothing has changed
    assertEquals(2, nodes.size());
    DBNNode getResult = nodes.get(1);
    assertTrue(getResult instanceof DBNEmptyNode);
    assertSame(dbnEmptyNode, getResult);
  }

  /**
   * Test {@link DBNNode#sortNodes(List)}.
   *
   * <ul>
   *   <li>Given {@link DBNLocalFolder}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#sortNodes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.sortNodes(List)"})
  public void testSortNodes_givenDBNLocalFolder_thenArrayListSizeIsTwo() {
    // Arrange
    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(mock(DBNLocalFolder.class));
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    nodes.add(dbnEmptyNode);

    // Act
    DBNNode.sortNodes(nodes);

    // Assert that nothing has changed
    assertEquals(2, nodes.size());
    DBNNode getResult = nodes.get(1);
    assertTrue(getResult instanceof DBNEmptyNode);
    assertSame(dbnEmptyNode, getResult);
  }

  /**
   * Test {@link DBNNode#sortNodes(List)}.
   *
   * <ul>
   *   <li>Given {@link DBNLocalFolder}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#sortNodes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.sortNodes(List)"})
  public void testSortNodes_givenDBNLocalFolder_thenArrayListSizeIsTwo2() {
    // Arrange
    ArrayList<DBNNode> nodes = new ArrayList<>();
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    nodes.add(dbnEmptyNode);
    nodes.add(mock(DBNLocalFolder.class));

    // Act
    DBNNode.sortNodes(nodes);

    // Assert
    assertEquals(2, nodes.size());
    DBNNode getResult = nodes.get(1);
    assertTrue(getResult instanceof DBNEmptyNode);
    assertSame(dbnEmptyNode, getResult);
  }

  /**
   * Test {@link DBNNode#sortNodes(List)}.
   *
   * <ul>
   *   <li>Then calls {@link DBNLocalFolder#getNodeDisplayName()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#sortNodes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNNode.sortNodes(List)"})
  public void testSortNodes_thenCallsGetNodeDisplayName() {
    // Arrange
    DBNLocalFolder dbnLocalFolder = mock(DBNLocalFolder.class);
    when(dbnLocalFolder.getNodeDisplayName()).thenReturn("Node Display Name");

    DBNLocalFolder dbnLocalFolder2 = mock(DBNLocalFolder.class);
    when(dbnLocalFolder2.getNodeDisplayName()).thenReturn("Node Display Name");

    ArrayList<DBNNode> nodes = new ArrayList<>();
    nodes.add(dbnLocalFolder2);
    nodes.add(dbnLocalFolder);

    // Act
    DBNNode.sortNodes(nodes);

    // Assert
    verify(dbnLocalFolder2).getNodeDisplayName();
    verify(dbnLocalFolder).getNodeDisplayName();
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    DBXTreeFolder dbxTreeFolder = mock(DBXTreeFolder.class);
    when(dbxTreeFolder.getType()).thenThrow(new IllegalStateException());

    ArrayList<DBXTreeNode> dbxTreeNodeList = new ArrayList<>();
    dbxTreeNodeList.add(dbxTreeFolder);

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(dbxTreeNodeList);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> DBNNode.nodeHasStructureContainers(node, meta));
    verify(dbxTreeFolder).getType();
    verify(meta).getChildren(isA(DBNNode.class));
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers2() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    DBXTreeFolder dbxTreeFolder = mock(DBXTreeFolder.class);
    when(dbxTreeFolder.getSource()).thenThrow(new IllegalStateException());
    when(dbxTreeFolder.getType()).thenReturn("Type");

    ArrayList<DBXTreeNode> dbxTreeNodeList = new ArrayList<>();
    dbxTreeNodeList.add(dbxTreeFolder);

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(dbxTreeNodeList);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> DBNNode.nodeHasStructureContainers(node, meta));
    verify(dbxTreeFolder).getType();
    verify(meta).getChildren(isA(DBNNode.class));
    verify(dbxTreeFolder).getSource();
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers3() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    when(propertyGroupDescriptor.getObjectClass(
            Mockito.<String>any(), Mockito.<Class<DBSObject>>any()))
        .thenReturn(null);

    DBXTreeFolder dbxTreeFolder = mock(DBXTreeFolder.class);
    when(dbxTreeFolder.getSource()).thenReturn(propertyGroupDescriptor);
    when(dbxTreeFolder.getType()).thenReturn("Type");

    ArrayList<DBXTreeNode> dbxTreeNodeList = new ArrayList<>();
    dbxTreeNodeList.add(dbxTreeFolder);

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(dbxTreeNodeList);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBNEvent.FORCE_REFRESH, DBNEvent.FORCE_REFRESH);
    new ConfigurationElementHandle(new RegistryObjectManager(registry), 1);

    // Act
    boolean actualNodeHasStructureContainersResult = DBNNode.nodeHasStructureContainers(node, meta);

    // Assert
    verify(propertyGroupDescriptor).getObjectClass(eq("Type"), isA(Class.class));
    verify(dbxTreeFolder).getType();
    verify(meta).getChildren(isA(DBNNode.class));
    verify(dbxTreeFolder).getSource();
    assertFalse(actualNodeHasStructureContainersResult);
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers_givenArrayListAddNull_thenReturnFalse() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    ArrayList<DBXTreeNode> dbxTreeNodeList = new ArrayList<>();
    dbxTreeNodeList.add(null);

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(dbxTreeNodeList);

    // Act
    boolean actualNodeHasStructureContainersResult = DBNNode.nodeHasStructureContainers(node, meta);

    // Assert
    verify(meta).getChildren(isA(DBNNode.class));
    assertFalse(actualNodeHasStructureContainersResult);
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers_givenArrayList_thenReturnFalse() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(new ArrayList<>());

    // Act
    boolean actualNodeHasStructureContainersResult = DBNNode.nodeHasStructureContainers(node, meta);

    // Assert
    verify(meta).getChildren(isA(DBNNode.class));
    assertFalse(actualNodeHasStructureContainersResult);
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers_givenNull() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(null);

    // Act
    boolean actualNodeHasStructureContainersResult = DBNNode.nodeHasStructureContainers(node, meta);

    // Assert
    verify(meta).getChildren(isA(DBNNode.class));
    assertFalse(actualNodeHasStructureContainersResult);
  }

  /**
   * Test {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}.
   *
   * <ul>
   *   <li>Given {@code DBSObject}.
   * </ul>
   *
   * <p>Method under test: {@link DBNNode#nodeHasStructureContainers(DBNNode, DBXTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNNode.nodeHasStructureContainers(DBNNode, DBXTreeNode)"})
  public void testNodeHasStructureContainers_givenOrgJkissDbeaverModelStructDBSObject() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    Class<DBSObject> forNameResult = DBSObject.class;
    when(propertyGroupDescriptor.getObjectClass(
            Mockito.<String>any(), Mockito.<Class<DBSObject>>any()))
        .thenReturn(forNameResult);

    DBXTreeFolder dbxTreeFolder = mock(DBXTreeFolder.class);
    when(dbxTreeFolder.getSource()).thenReturn(propertyGroupDescriptor);
    when(dbxTreeFolder.getType()).thenReturn("Type");

    ArrayList<DBXTreeNode> dbxTreeNodeList = new ArrayList<>();
    dbxTreeNodeList.add(dbxTreeFolder);

    DBXTreeDescriptor meta = mock(DBXTreeDescriptor.class);
    when(meta.getChildren(Mockito.<DBNNode>any())).thenReturn(dbxTreeNodeList);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBNEvent.FORCE_REFRESH, DBNEvent.FORCE_REFRESH);
    new ConfigurationElementHandle(new RegistryObjectManager(registry), 1);

    // Act
    boolean actualNodeHasStructureContainersResult = DBNNode.nodeHasStructureContainers(node, meta);

    // Assert
    verify(propertyGroupDescriptor).getObjectClass(eq("Type"), isA(Class.class));
    verify(dbxTreeFolder).getType();
    verify(meta).getChildren(isA(DBNNode.class));
    verify(dbxTreeFolder).getSource();
    assertFalse(actualNodeHasStructureContainersResult);
  }

  /**
   * Test {@link DBNNode#toString()}.
   *
   * <p>Method under test: {@link DBNNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNNode.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("node://#empty", new DBNEmptyNode().toString());
  }
}
