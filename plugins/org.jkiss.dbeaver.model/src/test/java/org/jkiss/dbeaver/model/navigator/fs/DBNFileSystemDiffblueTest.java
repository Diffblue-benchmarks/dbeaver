package org.jkiss.dbeaver.model.navigator.fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystem;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.navigator.DBNEmptyNode;
import org.jkiss.dbeaver.model.navigator.DBNEvent;
import org.jkiss.dbeaver.model.navigator.DBNEvent.NodeChange;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.navigator.DBNNode;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBNFileSystemDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBNFileSystem#DBNFileSystem(DBNNode, DBFVirtualFileSystem)}
   *   <li>{@link DBNFileSystem#toString()}
   *   <li>{@link DBNFileSystem#getFileSystem()}
   *   <li>{@link DBNFileSystem#getNodeTypeLabel()}
   *   <li>{@link DBNFileSystem#isManageable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBNFileSystem.<init>(DBNNode, DBFVirtualFileSystem)",
    "DBFVirtualFileSystem DBNFileSystem.getFileSystem()",
    "String DBNFileSystem.getNodeTypeLabel()",
    "boolean DBNFileSystem.isManageable()",
    "String DBNFileSystem.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBNEmptyNode parentNode = new DBNEmptyNode();
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);

    // Act
    DBNFileSystem actualDbnFileSystem = new DBNFileSystem(parentNode, fileSystem);
    String actualToStringResult = actualDbnFileSystem.toString();
    DBFVirtualFileSystem actualFileSystem = actualDbnFileSystem.getFileSystem();
    String actualNodeTypeLabel = actualDbnFileSystem.getNodeTypeLabel();

    // Assert
    assertEquals("File system", actualNodeTypeLabel);
    assertNull(actualToStringResult);
    assertTrue(actualDbnFileSystem.isManageable());
    assertSame(parentNode, actualDbnFileSystem.getParentNode());
    assertSame(fileSystem, actualFileSystem);
  }

  /**
   * Test {@link DBNFileSystem#getRoot(DBFVirtualFileSystemRoot)} with {@code dbfRoot}.
   *
   * <p>Method under test: {@link DBNFileSystem#getRoot(DBFVirtualFileSystemRoot)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystem.getRoot(DBFVirtualFileSystemRoot)"})
  public void testGetRootWithDbfRoot() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertNull(dbnFileSystem.getRoot(mock(DBFVirtualFileSystemRoot.class)));
  }

  /**
   * Test {@link DBNFileSystem#getRoot(String)} with {@code path}.
   *
   * <p>Method under test: {@link DBNFileSystem#getRoot(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystem.getRoot(String)"})
  public void testGetRootWithPath() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertNull(dbnFileSystem.getRoot("Path"));
  }

  /**
   * Test {@link DBNFileSystem#isDisposed()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#isDisposed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystem.isDisposed()"})
  public void testIsDisposed_thenReturnFalse() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertFalse(dbnFileSystem.isDisposed());
  }

  /**
   * Test {@link DBNFileSystem#isDisposed()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#isDisposed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystem.isDisposed()"})
  public void testIsDisposed_thenReturnTrue() {
    // Arrange
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), null);

    // Act and Assert
    assertTrue(dbnFileSystem.isDisposed());
  }

  /**
   * Test {@link DBNFileSystem#dispose(boolean)}.
   *
   * <p>Method under test: {@link DBNFileSystem#dispose(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.dispose(boolean)"})
  public void testDispose() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act
    dbnFileSystem.dispose(true);

    // Assert
    assertNull(dbnFileSystem.getFileSystem());
    assertTrue(dbnFileSystem.isDisposed());
  }

  /**
   * Test {@link DBNFileSystem#getNodeType()}.
   *
   * <p>Method under test: {@link DBNFileSystem#getNodeType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getNodeType()"})
  public void testGetNodeType() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertEquals("dbvfs.fileSystem", dbnFileSystem.getNodeType());
  }

  /**
   * Test {@link DBNFileSystem#getNodeDisplayName()}.
   *
   * <p>Method under test: {@link DBNFileSystem#getNodeDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getNodeDisplayName()"})
  public void testGetNodeDisplayName() {
    // Arrange
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getFileSystemDisplayName()).thenReturn("File System Display Name");
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), fileSystem);

    // Act
    String actualNodeDisplayName = dbnFileSystem.getNodeDisplayName();

    // Assert
    verify(fileSystem).getFileSystemDisplayName();
    assertEquals("File System Display Name", actualNodeDisplayName);
  }

  /**
   * Test {@link DBNFileSystem#getNodeDescription()}.
   *
   * <p>Method under test: {@link DBNFileSystem#getNodeDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getNodeDescription()"})
  public void testGetNodeDescription() {
    // Arrange
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getDescription()).thenReturn("The characteristics of someone or something");
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), fileSystem);

    // Act
    String actualNodeDescription = dbnFileSystem.getNodeDescription();

    // Assert
    verify(fileSystem).getDescription();
    assertEquals("The characteristics of someone or something", actualNodeDescription);
  }

  /**
   * Test {@link DBNFileSystem#allowsChildren()}.
   *
   * <p>Method under test: {@link DBNFileSystem#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystem.allowsChildren()"})
  public void testAllowsChildren() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertTrue(dbnFileSystem.allowsChildren());
  }

  /**
   * Test {@link DBNFileSystem#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot[] DBNFileSystem.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenReturnArrayLengthIsZero() throws DBException {
    // Arrange
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), null);

    // Act and Assert
    assertEquals(0, dbnFileSystem.getChildren(new LoggingProgressMonitor()).length);
  }

  /**
   * Test {@link DBNFileSystem#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot[] DBNFileSystem.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenReturnNull() throws DBException {
    // Arrange
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), null);

    // Act
    DBNFileSystemRoot[] actualChildren =
        dbnFileSystem.getChildren(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Assert
    assertNull(actualChildren);
  }

  /**
   * Test {@link DBNFileSystem#getChild(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getChild(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystem.getChild(DBRProgressMonitor, String)"})
  public void testGetChild_thenReturnNull() throws DBException {
    // Arrange
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), null);

    // Act and Assert
    assertNull(dbnFileSystem.getChild(new LoggingProgressMonitor(), "Name"));
  }

  /**
   * Test {@link DBNFileSystem#getChild(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@link LocalCacheProgressMonitor#LocalCacheProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getChild(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystem.getChild(DBRProgressMonitor, String)"})
  public void testGetChild_whenLocalCacheProgressMonitorWithOriginalIsLoggingProgressMonitor()
      throws DBException {
    // Arrange
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), null);

    // Act and Assert
    assertNull(
        dbnFileSystem.getChild(
            new LocalCacheProgressMonitor(new LoggingProgressMonitor()), "Name"));
  }

  /**
   * Test {@link DBNFileSystem#readChildNodes(DBRProgressMonitor, DBNFileSystemRoot[])}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#readChildNodes(DBRProgressMonitor,
   * DBNFileSystemRoot[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNFileSystemRoot[] DBNFileSystem.readChildNodes(DBRProgressMonitor, DBNFileSystemRoot[])"
  })
  public void testReadChildNodes_thenReturnArrayLengthIsZero() throws DBException {
    // Arrange
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), null);

    // Act and Assert
    assertEquals(0, dbnFileSystem.readChildNodes(new LoggingProgressMonitor(), null).length);
  }

  /**
   * Test {@link DBNFileSystem#refreshNode(DBRProgressMonitor, Object)}.
   *
   * <p>Method under test: {@link DBNFileSystem#refreshNode(DBRProgressMonitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNFileSystem.refreshNode(DBRProgressMonitor, Object)"})
  public void testRefreshNode() throws DBException {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing()
        .when(dbnModel)
        .fireNodeUpdate(Mockito.<Object>any(), Mockito.<DBNNode>any(), Mockito.<NodeChange>any());

    DBNFileSystemRoot parentNode = mock(DBNFileSystemRoot.class);
    when(parentNode.getModel()).thenReturn(dbnModel);
    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode, mock(DBFVirtualFileSystem.class));

    // Act
    DBNNode actualRefreshNodeResult =
        dbnFileSystem.refreshNode(new LoggingProgressMonitor(), DBPEvent.RENAME);

    // Assert
    verify(dbnModel).fireNodeUpdate(isA(Object.class), isA(DBNNode.class), eq(NodeChange.REFRESH));
    verify(parentNode).getModel();
    assertSame(dbnFileSystem, actualRefreshNodeResult);
  }

  /**
   * Test {@link DBNFileSystem#refreshNode(DBRProgressMonitor, Object)}.
   *
   * <ul>
   *   <li>Then return {@link DBNFileSystem}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#refreshNode(DBRProgressMonitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNFileSystem.refreshNode(DBRProgressMonitor, Object)"})
  public void testRefreshNode_thenReturnDBNFileSystem() throws DBException {
    // Arrange
    DBNFileSystemRoot parentNode = mock(DBNFileSystemRoot.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    ArrayList<DBPProject> modelProjects = new ArrayList<>();

    DBNModel dbnModel = new DBNModel(platform, modelProjects);
    when(parentNode.getModel()).thenReturn(dbnModel);
    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode, mock(DBFVirtualFileSystem.class));

    // Act
    DBNNode actualRefreshNodeResult =
        dbnFileSystem.refreshNode(new LoggingProgressMonitor(), DBPEvent.RENAME);

    // Assert
    verify(parentNode).getModel();
    assertTrue(actualRefreshNodeResult instanceof DBNFileSystem);
    assertEquals(
        modelProjects, ((DBNFileSystem) actualRefreshNodeResult).getAssociatedDataSources());
    assertSame(dbnModel, actualRefreshNodeResult.getModel());
  }

  /**
   * Test {@link DBNFileSystem#getNodeItemPath()}.
   *
   * <ul>
   *   <li>Then return {@code /42}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getNodeItemPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getNodeItemPath()"})
  public void testGetNodeItemPath_thenReturn42() {
    // Arrange
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getId()).thenReturn("42");
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), fileSystem);

    // Act
    String actualNodeItemPath = dbnFileSystem.getNodeItemPath();

    // Assert
    verify(fileSystem).getId();
    assertEquals("/42", actualNodeItemPath);
  }

  /**
   * Test {@link DBNFileSystem#getNodeItemPath()}.
   *
   * <ul>
   *   <li>Then return {@code /42/foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getNodeItemPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getNodeItemPath()"})
  public void testGetNodeItemPath_thenReturn42Foo() {
    // Arrange
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getId()).thenReturn("42");
    DBNFileSystem parentNode = new DBNFileSystem(new DBNEmptyNode(), fileSystem);

    DBFVirtualFileSystem fileSystem2 = mock(DBFVirtualFileSystem.class);
    when(fileSystem2.getId()).thenReturn("foo");

    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode, fileSystem2);

    // Act
    String actualNodeItemPath = dbnFileSystem.getNodeItemPath();

    // Assert
    verify(fileSystem).getId();
    verify(fileSystem2).getId();
    assertEquals("/42/foo", actualNodeItemPath);
  }

  /**
   * Test {@link DBNFileSystem#getNodeItemPath()}.
   *
   * <ul>
   *   <li>Then return {@code /42/Name/foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#getNodeItemPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getNodeItemPath()"})
  public void testGetNodeItemPath_thenReturn42NameFoo() {
    // Arrange
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getId()).thenReturn("42");
    DBNFileSystem parentNode = new DBNFileSystem(new DBNEmptyNode(), fileSystem);

    DBFVirtualFileSystemRoot root = mock(DBFVirtualFileSystemRoot.class);
    when(root.getName()).thenReturn("Name");

    DBNFileSystemRoot parentNode2 = new DBNFileSystemRoot(parentNode, root);

    DBFVirtualFileSystem fileSystem2 = mock(DBFVirtualFileSystem.class);
    when(fileSystem2.getId()).thenReturn("foo");

    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode2, fileSystem2);

    // Act
    String actualNodeItemPath = dbnFileSystem.getNodeItemPath();

    // Assert
    verify(root).getName();
    verify(fileSystem).getId();
    verify(fileSystem2).getId();
    assertEquals("/42/Name/foo", actualNodeItemPath);
  }

  /**
   * Test {@link DBNFileSystem#getName()}.
   *
   * <p>Method under test: {@link DBNFileSystem#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystem.getName()"})
  public void testGetName() {
    // Arrange
    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getId()).thenReturn("42");
    DBNFileSystem dbnFileSystem = new DBNFileSystem(new DBNEmptyNode(), fileSystem);

    // Act
    String actualName = dbnFileSystem.getName();

    // Assert
    verify(fileSystem).getId();
    assertEquals("42", actualName);
  }

  /**
   * Test {@link DBNFileSystem#supportsRename()}.
   *
   * <p>Method under test: {@link DBNFileSystem#supportsRename()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystem.supportsRename()"})
  public void testSupportsRename() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertFalse(dbnFileSystem.supportsRename());
  }

  /**
   * Test {@link DBNFileSystem#sortChildren(DBNNode[])}.
   *
   * <ul>
   *   <li>Then array length is three.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#sortChildren(DBNNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.sortChildren(DBNNode[])"})
  public void testSortChildren_thenArrayLengthIsThree() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    DBNEmptyNode dbnEmptyNode2 = new DBNEmptyNode();
    DBNNode[] list = new DBNNode[] {dbnEmptyNode, dbnEmptyNode2, new DBNEmptyNode()};

    // Act
    dbnFileSystem.sortChildren(list);

    // Assert that nothing has changed
    Collection<DBPDataSourceContainer> associatedDataSources =
        dbnFileSystem.getAssociatedDataSources();
    assertTrue(associatedDataSources instanceof List);
    DBNNode dbnNode = list[0];
    assertTrue(dbnNode instanceof DBNEmptyNode);
    DBNNode dbnNode2 = list[1];
    assertTrue(dbnNode2 instanceof DBNEmptyNode);
    assertEquals(3, list.length);
    assertTrue(associatedDataSources.isEmpty());
    assertSame(dbnEmptyNode, dbnNode);
    assertSame(dbnEmptyNode2, dbnNode2);
  }

  /**
   * Test {@link DBNFileSystem#sortChildren(DBNNode[])}.
   *
   * <ul>
   *   <li>Then first element {@link DBNFileSystem}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#sortChildren(DBNNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.sortChildren(DBNNode[])"})
  public void testSortChildren_thenFirstElementDBNFileSystem() {
    // Arrange
    DBNFileSystem parentNode =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));
    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode, mock(DBFVirtualFileSystem.class));

    DBFVirtualFileSystemRoot root = mock(DBFVirtualFileSystemRoot.class);
    when(root.getName()).thenReturn("Name");
    DBNFileSystem parentNode2 =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    DBNFileSystemRoot dbnFileSystemRoot = new DBNFileSystemRoot(parentNode2, root);

    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getFileSystemDisplayName()).thenReturn("File System Display Name");
    DBNFileSystem dbnFileSystem2 = new DBNFileSystem(new DBNEmptyNode(), fileSystem);
    DBNNode[] list = new DBNNode[] {dbnFileSystemRoot, dbnFileSystem2};

    // Act
    dbnFileSystem.sortChildren(list);

    // Assert
    verify(root).getName();
    verify(fileSystem).getFileSystemDisplayName();
    DBNNode dbnNode = list[0];
    assertTrue(dbnNode instanceof DBNFileSystem);
    DBNNode dbnNode2 = list[1];
    assertTrue(dbnNode2 instanceof DBNFileSystemRoot);
    assertEquals(2, list.length);
    assertSame(dbnFileSystem2, dbnNode);
    assertSame(dbnFileSystemRoot, dbnNode2);
  }

  /**
   * Test {@link DBNFileSystem#sortChildren(DBNNode[])}.
   *
   * <ul>
   *   <li>Then second element {@link DBNEmptyNode}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#sortChildren(DBNNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.sortChildren(DBNNode[])"})
  public void testSortChildren_thenSecondElementDBNEmptyNode() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    DBNEmptyNode dbnEmptyNode2 = new DBNEmptyNode();
    DBNNode[] list = new DBNNode[] {dbnEmptyNode, dbnEmptyNode2};

    // Act
    dbnFileSystem.sortChildren(list);

    // Assert that nothing has changed
    Collection<DBPDataSourceContainer> associatedDataSources =
        dbnFileSystem.getAssociatedDataSources();
    assertTrue(associatedDataSources instanceof List);
    DBNNode dbnNode = list[0];
    assertTrue(dbnNode instanceof DBNEmptyNode);
    DBNNode dbnNode2 = list[1];
    assertTrue(dbnNode2 instanceof DBNEmptyNode);
    assertEquals(2, list.length);
    assertTrue(associatedDataSources.isEmpty());
    assertSame(dbnEmptyNode, dbnNode);
    assertSame(dbnEmptyNode2, dbnNode2);
  }

  /**
   * Test {@link DBNFileSystem#sortChildren(DBNNode[])}.
   *
   * <ul>
   *   <li>Then second element {@link DBNFileSystem}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#sortChildren(DBNNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.sortChildren(DBNNode[])"})
  public void testSortChildren_thenSecondElementDBNFileSystem() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    DBFVirtualFileSystem fileSystem = mock(DBFVirtualFileSystem.class);
    when(fileSystem.getFileSystemDisplayName()).thenReturn("File System Display Name");
    DBNFileSystem dbnFileSystem2 = new DBNFileSystem(new DBNEmptyNode(), fileSystem);
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    DBNNode[] list = new DBNNode[] {dbnFileSystem2, dbnEmptyNode};

    // Act
    dbnFileSystem.sortChildren(list);

    // Assert
    verify(fileSystem).getFileSystemDisplayName();
    DBNNode dbnNode = list[0];
    assertTrue(dbnNode instanceof DBNEmptyNode);
    DBNNode dbnNode2 = list[1];
    assertTrue(dbnNode2 instanceof DBNFileSystem);
    assertEquals(2, list.length);
    assertSame(dbnEmptyNode, dbnNode);
    assertSame(dbnFileSystem2, dbnNode2);
  }

  /**
   * Test {@link DBNFileSystem#sortChildren(DBNNode[])}.
   *
   * <ul>
   *   <li>When array of {@link DBNNode} with {@link DBNEmptyNode} (default constructor).
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#sortChildren(DBNNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.sortChildren(DBNNode[])"})
  public void testSortChildren_whenArrayOfDBNNodeWithDBNEmptyNode_thenArrayLengthIsOne() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();
    DBNNode[] list = new DBNNode[] {dbnEmptyNode};

    // Act
    dbnFileSystem.sortChildren(list);

    // Assert that nothing has changed
    Collection<DBPDataSourceContainer> associatedDataSources =
        dbnFileSystem.getAssociatedDataSources();
    assertTrue(associatedDataSources instanceof List);
    DBNNode dbnNode = list[0];
    assertTrue(dbnNode instanceof DBNEmptyNode);
    assertEquals(1, list.length);
    assertTrue(associatedDataSources.isEmpty());
    assertSame(dbnEmptyNode, dbnNode);
  }

  /**
   * Test {@link DBNFileSystem#getAssociatedDataSources()}.
   *
   * <p>Method under test: {@link DBNFileSystem#getAssociatedDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBNFileSystem.getAssociatedDataSources()"})
  public void testGetAssociatedDataSources() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act
    Collection<DBPDataSourceContainer> actualAssociatedDataSources =
        dbnFileSystem.getAssociatedDataSources();

    // Assert
    assertTrue(actualAssociatedDataSources instanceof List);
    assertTrue(actualAssociatedDataSources.isEmpty());
  }

  /**
   * Test {@link DBNFileSystem#refreshResourceState(Object)}.
   *
   * <p>Method under test: {@link DBNFileSystem#refreshResourceState(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.refreshResourceState(Object)"})
  public void testRefreshResourceState() {
    // Arrange
    DBNFileSystemRoot parentNode = mock(DBNFileSystemRoot.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(parentNode.getModel()).thenReturn(dbnModel);
    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode, mock(DBFVirtualFileSystem.class));

    // Act
    dbnFileSystem.refreshResourceState(DBPEvent.RENAME);

    // Assert
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNFileSystem#refreshResourceState(Object)}.
   *
   * <ul>
   *   <li>Then calls {@link DBNModel#fireNodeEvent(DBNEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystem#refreshResourceState(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystem.refreshResourceState(Object)"})
  public void testRefreshResourceState_thenCallsFireNodeEvent() {
    // Arrange
    DBNModel dbnModel = mock(DBNModel.class);
    doNothing().when(dbnModel).fireNodeEvent(Mockito.<DBNEvent>any());

    DBNFileSystemRoot parentNode = mock(DBNFileSystemRoot.class);
    when(parentNode.getModel()).thenReturn(dbnModel);
    DBNFileSystem dbnFileSystem = new DBNFileSystem(parentNode, mock(DBFVirtualFileSystem.class));

    // Act
    dbnFileSystem.refreshResourceState(DBPEvent.RENAME);

    // Assert
    verify(dbnModel).fireNodeEvent(isA(DBNEvent.class));
    verify(parentNode).getModel();
  }

  /**
   * Test {@link DBNFileSystem#needsInitialization()}.
   *
   * <p>Method under test: {@link DBNFileSystem#needsInitialization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystem.needsInitialization()"})
  public void testNeedsInitialization() {
    // Arrange
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertTrue(dbnFileSystem.needsInitialization());
  }
}
