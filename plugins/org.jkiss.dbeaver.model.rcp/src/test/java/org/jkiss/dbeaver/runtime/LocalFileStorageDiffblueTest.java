package org.jkiss.dbeaver.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalFileStorageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalFileStorage#LocalFileStorage(File, String)}
   *   <li>{@link LocalFileStorage#getCharset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalFileStorage.<init>(File, String)",
    "String LocalFileStorage.getCharset()"
  })
  public void testGettersAndSetters() throws CoreException {
    // Arrange, Act and Assert
    assertEquals(
        "UTF-8",
        new LocalFileStorage(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "UTF-8")
            .getCharset());
  }

  /**
   * Test {@link LocalFileStorage#getContents()}.
   *
   * <p>Method under test: {@link LocalFileStorage#getContents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream LocalFileStorage.getContents()"})
  public void testGetContents() throws CoreException {
    // Arrange, Act and Assert
    assertThrows(
        CoreException.class,
        () ->
            new LocalFileStorage(
                    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "UTF-8")
                .getContents());
  }

  /**
   * Test {@link LocalFileStorage#getFullPath()}.
   *
   * <p>Method under test: {@link LocalFileStorage#getFullPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath LocalFileStorage.getFullPath()"})
  public void testGetFullPath() {
    // Arrange and Act
    IPath actualFullPath =
        new LocalFileStorage(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "UTF-8")
            .getFullPath();

    // Assert
    assertTrue(actualFullPath instanceof Path);
    File toFileResult = actualFullPath.toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertEquals("txt", actualFullPath.getFileExtension());
    assertNull(actualFullPath.getDevice());
    assertFalse(actualFullPath.hasTrailingSeparator());
    assertFalse(actualFullPath.isEmpty());
    assertFalse(actualFullPath.isRoot());
    assertFalse(actualFullPath.isUNC());
    assertTrue(toFileResult.isAbsolute());
    assertTrue(actualFullPath.isAbsolute());
  }

  /**
   * Test {@link LocalFileStorage#getName()}.
   *
   * <p>Method under test: {@link LocalFileStorage#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String LocalFileStorage.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals(
        "test.txt",
        new LocalFileStorage(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "UTF-8")
            .getName());
  }

  /**
   * Test {@link LocalFileStorage#getAdapter(Class)}.
   *
   * <p>Method under test: {@link LocalFileStorage#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LocalFileStorage.getAdapter(Class)"})
  public void testGetAdapter() {
    // Arrange
    LocalFileStorage localFileStorage =
        new LocalFileStorage(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "UTF-8");
    Class<Object> adapter = Object.class;

    // Act and Assert
    assertNull(localFileStorage.getAdapter(adapter));
  }
}
