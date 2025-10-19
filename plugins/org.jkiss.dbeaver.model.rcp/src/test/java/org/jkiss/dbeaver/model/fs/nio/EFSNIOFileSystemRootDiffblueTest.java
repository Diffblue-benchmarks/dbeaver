package org.jkiss.dbeaver.model.fs.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.resources.IProject;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EFSNIOFileSystemRootDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EFSNIOFileSystemRoot#EFSNIOFileSystemRoot(IProject, DBFVirtualFileSystemRoot,
   *       String)}
   *   <li>{@link EFSNIOFileSystemRoot#getPrefix()}
   *   <li>{@link EFSNIOFileSystemRoot#getProject()}
   *   <li>{@link EFSNIOFileSystemRoot#getRoot()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EFSNIOFileSystemRoot.<init>(IProject, DBFVirtualFileSystemRoot, String)",
    "String EFSNIOFileSystemRoot.getPrefix()",
    "IProject EFSNIOFileSystemRoot.getProject()",
    "DBFVirtualFileSystemRoot EFSNIOFileSystemRoot.getRoot()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBFVirtualFileSystemRoot fsRoot = mock(DBFVirtualFileSystemRoot.class);

    // Act
    EFSNIOFileSystemRoot actualEfsnioFileSystemRoot =
        new EFSNIOFileSystemRoot(null, fsRoot, "Fs Prefix");
    String actualPrefix = actualEfsnioFileSystemRoot.getPrefix();
    IProject actualProject = actualEfsnioFileSystemRoot.getProject();

    // Assert
    assertEquals("Fs Prefix", actualPrefix);
    assertNull(actualProject);
    assertSame(fsRoot, actualEfsnioFileSystemRoot.getRoot());
  }
}
