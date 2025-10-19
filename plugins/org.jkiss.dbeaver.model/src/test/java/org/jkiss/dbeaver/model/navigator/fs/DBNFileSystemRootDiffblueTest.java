package org.jkiss.dbeaver.model.navigator.fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystem;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.navigator.DBNEmptyNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBNFileSystemRootDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBNFileSystemRoot#DBNFileSystemRoot(DBNFileSystem, DBFVirtualFileSystemRoot)}
   *   <li>{@link DBNFileSystemRoot#setPath(Path)}
   *   <li>{@link DBNFileSystemRoot#getNodeIcon()}
   *   <li>{@link DBNFileSystemRoot#getNodeTypeLabel()}
   *   <li>{@link DBNFileSystemRoot#getRoot()}
   *   <li>{@link DBNFileSystemRoot#isDirectory()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBNFileSystemRoot.<init>(DBNFileSystem, DBFVirtualFileSystemRoot)",
    "DBPImage DBNFileSystemRoot.getNodeIcon()",
    "String DBNFileSystemRoot.getNodeTypeLabel()",
    "DBFVirtualFileSystemRoot DBNFileSystemRoot.getRoot()",
    "boolean DBNFileSystemRoot.isDirectory()",
    "void DBNFileSystemRoot.setPath(Path)",
    "String DBNFileSystemRoot.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBNFileSystem parentNode =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));
    DBFVirtualFileSystemRoot root = mock(DBFVirtualFileSystemRoot.class);

    // Act
    DBNFileSystemRoot actualDbnFileSystemRoot = new DBNFileSystemRoot(parentNode, root);
    actualDbnFileSystemRoot.setPath(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    DBPImage actualNodeIcon = actualDbnFileSystemRoot.getNodeIcon();
    String actualNodeTypeLabel = actualDbnFileSystemRoot.getNodeTypeLabel();
    DBFVirtualFileSystemRoot actualRoot = actualDbnFileSystemRoot.getRoot();

    // Assert
    assertEquals("Folder", actualNodeTypeLabel);
    assertTrue(actualDbnFileSystemRoot.isDirectory());
    assertSame(parentNode, actualDbnFileSystemRoot.getParentNode());
    assertSame(((DBIcon) actualNodeIcon).TREE_FOLDER_CONSTRAINT, actualNodeIcon);
    assertSame(root, actualRoot);
  }

  /**
   * Test {@link DBNFileSystemRoot#dispose(boolean)}.
   *
   * <p>Method under test: {@link DBNFileSystemRoot#dispose(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNFileSystemRoot.dispose(boolean)"})
  public void testDispose() {
    // Arrange
    DBNFileSystem parentNode =
        new DBNFileSystem(new DBNEmptyNode(), mock(DBFVirtualFileSystem.class));
    DBNFileSystemRoot dbnFileSystemRoot =
        new DBNFileSystemRoot(parentNode, mock(DBFVirtualFileSystemRoot.class));

    // Act
    dbnFileSystemRoot.dispose(true);

    // Assert
    assertNull(dbnFileSystemRoot.getRoot());
    assertTrue(dbnFileSystemRoot.isDisposed());
  }
}
