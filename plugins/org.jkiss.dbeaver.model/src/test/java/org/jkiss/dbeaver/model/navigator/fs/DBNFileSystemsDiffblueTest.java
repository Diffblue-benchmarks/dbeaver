package org.jkiss.dbeaver.model.navigator.fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystem;
import org.jkiss.dbeaver.model.navigator.DBNEmptyNode;
import org.jkiss.dbeaver.model.navigator.DBNNode;
import org.jkiss.dbeaver.model.navigator.DBNProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBNFileSystemsDiffblueTest {
  @InjectMocks private DBNFileSystems dBNFileSystems;

  @Mock private DBNProject dBNProject;

  /**
   * Test {@link DBNFileSystems#getNodeType()}.
   *
   * <p>Method under test: {@link DBNFileSystems#getNodeType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystems.getNodeType()"})
  public void testGetNodeType() {
    // Arrange, Act and Assert
    assertEquals("dbvfs", new DBNFileSystems(null).getNodeType());
  }

  /**
   * Test {@link DBNFileSystems#getNodeId()}.
   *
   * <p>Method under test: {@link DBNFileSystems#getNodeId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystems.getNodeId()"})
  public void testGetNodeId() {
    // Arrange, Act and Assert
    assertEquals("dbvfs", new DBNFileSystems(null).getNodeId());
  }

  /**
   * Test {@link DBNFileSystems#getName()}.
   *
   * <p>Method under test: {@link DBNFileSystems#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystems.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("dbvfs", new DBNFileSystems(null).getName());
  }

  /**
   * Test {@link DBNFileSystems#allowsChildren()}.
   *
   * <p>Method under test: {@link DBNFileSystems#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystems.allowsChildren()"})
  public void testAllowsChildren() {
    // Arrange, Act and Assert
    assertTrue(new DBNFileSystems(null).allowsChildren());
  }

  /**
   * Test {@link DBNFileSystems#getFileSystem(String, String)}.
   *
   * <p>Method under test: {@link DBNFileSystems#getFileSystem(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem DBNFileSystems.getFileSystem(String, String)"})
  public void testGetFileSystem() {
    // Arrange, Act and Assert
    assertNull(new DBNFileSystems(null).getFileSystem("Type", "42"));
  }

  /**
   * Test {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystems.getRootFolder(DBRProgressMonitor, String)"})
  public void testGetRootFolder() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    DefaultProgressMonitor original =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnFileSystems.getRootFolder(
                new SubTaskProgressMonitor(original), "Read available file systems"));
  }

  /**
   * Test {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystems.getRootFolder(DBRProgressMonitor, String)"})
  public void testGetRootFolder_thenReturnNull() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(
        dbnFileSystems.getRootFolder(
            new LocalCacheProgressMonitor(new LoggingProgressMonitor()), "42"));
  }

  /**
   * Test {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystems.getRootFolder(DBRProgressMonitor, String)"})
  public void testGetRootFolder_whenJavaLangObject_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getRootFolder(new LoggingProgressMonitor(Log.getLog(forClass)), "42"));
  }

  /**
   * Test {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystems.getRootFolder(DBRProgressMonitor, String)"})
  public void testGetRootFolder_whenLoggingProgressMonitor_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getRootFolder(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystems.getRootFolder(DBRProgressMonitor, String)"})
  public void testGetRootFolder_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnFileSystems.getRootFolder(
                new SubTaskProgressMonitor(new LoggingProgressMonitor()), "42"));
  }

  /**
   * Test {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getRootFolder(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystemRoot DBNFileSystems.getRootFolder(DBRProgressMonitor, String)"})
  public void testGetRootFolder_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor2()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getRootFolder(new SubTaskProgressMonitor(original), "42"));
  }

  /**
   * Test {@link DBNFileSystems#getChildren(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBNFileSystems#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getChildren(DBRProgressMonitor)"})
  public void testGetChildren() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    DefaultProgressMonitor original =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getChildren(new SubTaskProgressMonitor(original)));
  }

  /**
   * Test {@link DBNFileSystems#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenReturnNull() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act
    DBNFileSystem[] actualChildren =
        dbnFileSystems.getChildren(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Assert
    assertNull(actualChildren);
  }

  /**
   * Test {@link DBNFileSystems#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_whenJavaLangObject_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getChildren(new LoggingProgressMonitor(Log.getLog(forClass))));
  }

  /**
   * Test {@link DBNFileSystems#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_whenLoggingProgressMonitor_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getChildren(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBNFileSystems#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getChildren(new SubTaskProgressMonitor(new LoggingProgressMonitor())));
  }

  /**
   * Test {@link DBNFileSystems#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor2()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.getChildren(new SubTaskProgressMonitor(original)));
  }

  /**
   * Test {@link DBNFileSystems#readChildNodes(DBRProgressMonitor, DBNFileSystem[])}.
   *
   * <p>Method under test: {@link DBNFileSystems#readChildNodes(DBRProgressMonitor,
   * DBNFileSystem[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNFileSystem[] DBNFileSystems.readChildNodes(DBRProgressMonitor, DBNFileSystem[])"
  })
  public void testReadChildNodes() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(new LoggingProgressMonitor());
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.readChildNodes(monitor, new DBNFileSystem[] {dbnFileSystem}));
  }

  /**
   * Test {@link DBNFileSystems#readChildNodes(DBRProgressMonitor, DBNFileSystem[])}.
   *
   * <p>Method under test: {@link DBNFileSystems#readChildNodes(DBRProgressMonitor,
   * DBNFileSystem[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNFileSystem[] DBNFileSystems.readChildNodes(DBRProgressMonitor, DBNFileSystem[])"
  })
  public void testReadChildNodes2() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(original);
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.readChildNodes(monitor, new DBNFileSystem[] {dbnFileSystem}));
  }

  /**
   * Test {@link DBNFileSystems#readChildNodes(DBRProgressMonitor, DBNFileSystem[])}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#readChildNodes(DBRProgressMonitor,
   * DBNFileSystem[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNFileSystem[] DBNFileSystems.readChildNodes(DBRProgressMonitor, DBNFileSystem[])"
  })
  public void testReadChildNodes_whenJavaLangObject_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.readChildNodes(monitor, new DBNFileSystem[] {dbnFileSystem}));
  }

  /**
   * Test {@link DBNFileSystems#readChildNodes(DBRProgressMonitor, DBNFileSystem[])}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#readChildNodes(DBRProgressMonitor,
   * DBNFileSystem[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNFileSystem[] DBNFileSystems.readChildNodes(DBRProgressMonitor, DBNFileSystem[])"
  })
  public void testReadChildNodes_whenLoggingProgressMonitor_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.readChildNodes(monitor, new DBNFileSystem[] {dbnFileSystem}));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(
        dbnFileSystems.findNodeByPath(
            new LocalCacheProgressMonitor(new LoggingProgressMonitor()), "fs"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath2() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnFileSystems.findNodeByPath(
                new SubTaskProgressMonitor(new LoggingProgressMonitor()), "fs"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath3() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.findNodeByPath(new SubTaskProgressMonitor(original), "fs"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath4() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    DefaultProgressMonitor original =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.findNodeByPath(new SubTaskProgressMonitor(original), "Path"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(
        dbnFileSystems.findNodeByPath(
            new LocalCacheProgressMonitor(new LoggingProgressMonitor()), "fs", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath2() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnFileSystems.findNodeByPath(
                new SubTaskProgressMonitor(new LoggingProgressMonitor()), "fs", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath3() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.findNodeByPath(new SubTaskProgressMonitor(original), "fs", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath4() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    DefaultProgressMonitor original =
        new DefaultProgressMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.findNodeByPath(new SubTaskProgressMonitor(original), "Path", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath_thenThrowDBException() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            dbnFileSystems.findNodeByPath(
                new LoggingProgressMonitor(), "Read available file systems", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.findNodeByPath(new LoggingProgressMonitor(), "Path", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath_whenEmptyString_thenReturnNull()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(dbnFileSystems.findNodeByPath(new LoggingProgressMonitor(), "", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath_whenFalse_thenReturnNull()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(
        dbnFileSystems.findNodeByPath(
            new LocalCacheProgressMonitor(new LoggingProgressMonitor()), "fs", false));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <ul>
   *   <li>When {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath_whenJavaLangObject() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnFileSystems.findNodeByPath(
                new LoggingProgressMonitor(Log.getLog(forClass)), "Path", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String, boolean)} with {@code
   * monitor}, {@code path}, {@code shortPath}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String, boolean)"
  })
  public void testFindNodeByPathWithMonitorPathShortPath_whenSlash_thenReturnNull()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(dbnFileSystems.findNodeByPath(new LoggingProgressMonitor(), "/", true));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath_thenThrowDBException() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            dbnFileSystems.findNodeByPath(
                new LoggingProgressMonitor(), "Read available file systems"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath_thenThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> dbnFileSystems.findNodeByPath(new LoggingProgressMonitor(), "Path"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath_whenEmptyString_thenReturnNull()
      throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(dbnFileSystems.findNodeByPath(new LoggingProgressMonitor(), ""));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <ul>
   *   <li>When {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath_whenJavaLangObject() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);
    Class<Object> forClass = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnFileSystems.findNodeByPath(
                new LoggingProgressMonitor(Log.getLog(forClass)), "Path"));
  }

  /**
   * Test {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)} with {@code monitor},
   * {@code path}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNFileSystems#findNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.fs.DBNPathBase DBNFileSystems.findNodeByPath(DBRProgressMonitor, String)"
  })
  public void testFindNodeByPathWithMonitorPath_whenSlash_thenReturnNull() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act and Assert
    assertNull(dbnFileSystems.findNodeByPath(new LoggingProgressMonitor(), "/"));
  }

  /**
   * Test {@link DBNFileSystems#refreshNode(DBRProgressMonitor, Object)}.
   *
   * <p>Method under test: {@link DBNFileSystems#refreshNode(DBRProgressMonitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNFileSystems.refreshNode(DBRProgressMonitor, Object)"})
  public void testRefreshNode() throws DBException {
    // Arrange
    DBNFileSystems dbnFileSystems = new DBNFileSystems(null);

    // Act
    DBNNode actualRefreshNodeResult =
        dbnFileSystems.refreshNode(new LoggingProgressMonitor(), DBPEvent.RENAME);

    // Assert
    assertSame(dbnFileSystems, actualRefreshNodeResult);
  }

  /**
   * Test {@link DBNFileSystems#getNodeItemPath()}.
   *
   * <p>Method under test: {@link DBNFileSystems#getNodeItemPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNFileSystems.getNodeItemPath()"})
  public void testGetNodeItemPath() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getId()).thenReturn("42");
    when(dBNProject.getProject()).thenReturn(dbpProject);

    // Act
    String actualNodeItemPath = dBNFileSystems.getNodeItemPath();

    // Assert
    verify(dbpProject).getId();
    verify(dBNProject).getProject();
    assertEquals("ext://42/dbvfs", actualNodeItemPath);
  }

  /**
   * Test {@link DBNFileSystems#supportsRename()}.
   *
   * <p>Method under test: {@link DBNFileSystems#supportsRename()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystems.supportsRename()"})
  public void testSupportsRename() {
    // Arrange, Act and Assert
    assertFalse(new DBNFileSystems(null).supportsRename());
  }

  /**
   * Test {@link DBNFileSystems#needsInitialization()}.
   *
   * <p>Method under test: {@link DBNFileSystems#needsInitialization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNFileSystems.needsInitialization()"})
  public void testNeedsInitialization() {
    // Arrange, Act and Assert
    assertTrue(new DBNFileSystems(null).needsInitialization());
  }

  /**
   * Test {@link DBNFileSystems#getCachedChildren()}.
   *
   * <p>Method under test: {@link DBNFileSystems#getCachedChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNFileSystem[] DBNFileSystems.getCachedChildren()"})
  public void testGetCachedChildren() {
    // Arrange, Act and Assert
    assertNull(new DBNFileSystems(null).getCachedChildren());
  }
}
