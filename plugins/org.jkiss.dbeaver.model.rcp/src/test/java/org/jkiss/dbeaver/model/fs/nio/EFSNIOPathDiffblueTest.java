package org.jkiss.dbeaver.model.fs.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eclipse.core.runtime.IPath;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EFSNIOPathDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EFSNIOPath#EFSNIOPath(Path)}
   *   <li>{@link EFSNIOPath#getDevice()}
   *   <li>{@link EFSNIOPath#getFileExtension()}
   *   <li>{@link EFSNIOPath#hasTrailingSeparator()}
   *   <li>{@link EFSNIOPath#isAbsolute()}
   *   <li>{@link EFSNIOPath#isEmpty()}
   *   <li>{@link EFSNIOPath#isRoot()}
   *   <li>{@link EFSNIOPath#isUNC()}
   *   <li>{@link EFSNIOPath#toFile()}
   *   <li>{@link EFSNIOPath#toOSString()}
   *   <li>{@link EFSNIOPath#toPath()}
   *   <li>{@link EFSNIOPath#toPortableString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EFSNIOPath.<init>(Path)",
    "String EFSNIOPath.getDevice()",
    "String EFSNIOPath.getFileExtension()",
    "boolean EFSNIOPath.hasTrailingSeparator()",
    "boolean EFSNIOPath.isAbsolute()",
    "boolean EFSNIOPath.isEmpty()",
    "boolean EFSNIOPath.isRoot()",
    "boolean EFSNIOPath.isUNC()",
    "File EFSNIOPath.toFile()",
    "String EFSNIOPath.toOSString()",
    "Path EFSNIOPath.toPath()",
    "String EFSNIOPath.toPortableString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    EFSNIOPath actualEfsnioPath = new EFSNIOPath(nioPath);
    String actualDevice = actualEfsnioPath.getDevice();
    String actualFileExtension = actualEfsnioPath.getFileExtension();
    boolean actualHasTrailingSeparatorResult = actualEfsnioPath.hasTrailingSeparator();
    boolean actualIsAbsoluteResult = actualEfsnioPath.isAbsolute();
    boolean actualIsEmptyResult = actualEfsnioPath.isEmpty();
    boolean actualIsRootResult = actualEfsnioPath.isRoot();
    boolean actualIsUNCResult = actualEfsnioPath.isUNC();
    File actualToFileResult = actualEfsnioPath.toFile();
    String actualToOSStringResult = actualEfsnioPath.toOSString();
    Path actualToPathResult = actualEfsnioPath.toPath();

    // Assert
    assertNull(actualToFileResult);
    assertNull(actualDevice);
    assertNull(actualFileExtension);
    assertNull(actualToOSStringResult);
    assertNull(actualEfsnioPath.toPortableString());
    assertFalse(actualHasTrailingSeparatorResult);
    assertFalse(actualIsAbsoluteResult);
    assertFalse(actualIsEmptyResult);
    assertFalse(actualIsRootResult);
    assertFalse(actualIsUNCResult);
    assertSame(nioPath, actualToPathResult);
  }

  /**
   * Test {@link EFSNIOPath#addFileExtension(String)}.
   *
   * <p>Method under test: {@link EFSNIOPath#addFileExtension(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.addFileExtension(String)"})
  public void testAddFileExtension() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualAddFileExtensionResult = efsnioPath.addFileExtension("Extension");

    // Assert
    assertSame(efsnioPath, actualAddFileExtensionResult);
  }

  /**
   * Test {@link EFSNIOPath#addTrailingSeparator()}.
   *
   * <p>Method under test: {@link EFSNIOPath#addTrailingSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.addTrailingSeparator()"})
  public void testAddTrailingSeparator() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualAddTrailingSeparatorResult = efsnioPath.addTrailingSeparator();

    // Assert
    assertSame(efsnioPath, actualAddTrailingSeparatorResult);
  }

  /**
   * Test {@link EFSNIOPath#append(IPath)} with {@code IPath}.
   *
   * <p>Method under test: {@link EFSNIOPath#append(IPath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.append(IPath)"})
  public void testAppendWithIPath() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualAppendResult = efsnioPath.append(IPath.EMPTY);

    // Assert
    assertSame(efsnioPath, actualAppendResult);
  }

  /**
   * Test {@link EFSNIOPath#append(String)} with {@code String}.
   *
   * <p>Method under test: {@link EFSNIOPath#append(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.append(String)"})
  public void testAppendWithString() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualAppendResult = efsnioPath.append("Path");

    // Assert
    assertSame(efsnioPath, actualAppendResult);
  }

  /**
   * Test {@link EFSNIOPath#clone()}.
   *
   * <p>Method under test: {@link EFSNIOPath#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object EFSNIOPath.clone()"})
  public void testClone() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    Object actualCloneResult = new EFSNIOPath(nioPath).clone();

    // Assert
    assertTrue(actualCloneResult instanceof EFSNIOPath);
    assertNull(((EFSNIOPath) actualCloneResult).toFile());
    assertNull(((EFSNIOPath) actualCloneResult).getDevice());
    assertNull(((EFSNIOPath) actualCloneResult).getFileExtension());
    assertNull(((EFSNIOPath) actualCloneResult).toOSString());
    assertNull(((EFSNIOPath) actualCloneResult).toPortableString());
    assertEquals(0, ((EFSNIOPath) actualCloneResult).segments().length);
    assertFalse(((EFSNIOPath) actualCloneResult).hasTrailingSeparator());
    assertFalse(((EFSNIOPath) actualCloneResult).isAbsolute());
    assertFalse(((EFSNIOPath) actualCloneResult).isEmpty());
    assertFalse(((EFSNIOPath) actualCloneResult).isRoot());
    assertFalse(((EFSNIOPath) actualCloneResult).isUNC());
    assertSame(nioPath, ((EFSNIOPath) actualCloneResult).toPath());
  }

  /**
   * Test {@link EFSNIOPath#isPrefixOf(IPath)}.
   *
   * <p>Method under test: {@link EFSNIOPath#isPrefixOf(IPath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EFSNIOPath.isPrefixOf(IPath)"})
  public void testIsPrefixOf() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertFalse(new EFSNIOPath(nioPath).isPrefixOf(IPath.EMPTY));
  }

  /**
   * Test {@link EFSNIOPath#isValidPath(String)}.
   *
   * <p>Method under test: {@link EFSNIOPath#isValidPath(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EFSNIOPath.isValidPath(String)"})
  public void testIsValidPath() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertFalse(new EFSNIOPath(nioPath).isValidPath("Path"));
  }

  /**
   * Test {@link EFSNIOPath#isValidSegment(String)}.
   *
   * <p>Method under test: {@link EFSNIOPath#isValidSegment(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EFSNIOPath.isValidSegment(String)"})
  public void testIsValidSegment() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertFalse(new EFSNIOPath(nioPath).isValidSegment("Segment"));
  }

  /**
   * Test {@link EFSNIOPath#lastSegment()}.
   *
   * <p>Method under test: {@link EFSNIOPath#lastSegment()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String EFSNIOPath.lastSegment()"})
  public void testLastSegment() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new EFSNIOPath(nioPath).lastSegment());
  }

  /**
   * Test {@link EFSNIOPath#makeAbsolute()}.
   *
   * <p>Method under test: {@link EFSNIOPath#makeAbsolute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.makeAbsolute()"})
  public void testMakeAbsolute() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualMakeAbsoluteResult = efsnioPath.makeAbsolute();

    // Assert
    assertSame(efsnioPath, actualMakeAbsoluteResult);
  }

  /**
   * Test {@link EFSNIOPath#makeRelative()}.
   *
   * <p>Method under test: {@link EFSNIOPath#makeRelative()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.makeRelative()"})
  public void testMakeRelative() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualMakeRelativeResult = efsnioPath.makeRelative();

    // Assert
    assertSame(efsnioPath, actualMakeRelativeResult);
  }

  /**
   * Test {@link EFSNIOPath#makeRelativeTo(IPath)}.
   *
   * <p>Method under test: {@link EFSNIOPath#makeRelativeTo(IPath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.makeRelativeTo(IPath)"})
  public void testMakeRelativeTo() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualMakeRelativeToResult = efsnioPath.makeRelativeTo(IPath.EMPTY);

    // Assert
    assertSame(efsnioPath, actualMakeRelativeToResult);
  }

  /**
   * Test {@link EFSNIOPath#makeUNC(boolean)}.
   *
   * <p>Method under test: {@link EFSNIOPath#makeUNC(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.makeUNC(boolean)"})
  public void testMakeUNC() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualMakeUNCResult = efsnioPath.makeUNC(true);

    // Assert
    assertSame(efsnioPath, actualMakeUNCResult);
  }

  /**
   * Test {@link EFSNIOPath#matchingFirstSegments(IPath)}.
   *
   * <p>Method under test: {@link EFSNIOPath#matchingFirstSegments(IPath)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int EFSNIOPath.matchingFirstSegments(IPath)"})
  public void testMatchingFirstSegments() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(0, new EFSNIOPath(nioPath).matchingFirstSegments(IPath.EMPTY));
  }

  /**
   * Test {@link EFSNIOPath#removeFileExtension()}.
   *
   * <p>Method under test: {@link EFSNIOPath#removeFileExtension()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.removeFileExtension()"})
  public void testRemoveFileExtension() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualRemoveFileExtensionResult = efsnioPath.removeFileExtension();

    // Assert
    assertSame(efsnioPath, actualRemoveFileExtensionResult);
  }

  /**
   * Test {@link EFSNIOPath#removeFirstSegments(int)}.
   *
   * <p>Method under test: {@link EFSNIOPath#removeFirstSegments(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.removeFirstSegments(int)"})
  public void testRemoveFirstSegments() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualRemoveFirstSegmentsResult = efsnioPath.removeFirstSegments(3);

    // Assert
    assertSame(efsnioPath, actualRemoveFirstSegmentsResult);
  }

  /**
   * Test {@link EFSNIOPath#removeLastSegments(int)}.
   *
   * <p>Method under test: {@link EFSNIOPath#removeLastSegments(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.removeLastSegments(int)"})
  public void testRemoveLastSegments() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualRemoveLastSegmentsResult = efsnioPath.removeLastSegments(3);

    // Assert
    assertSame(efsnioPath, actualRemoveLastSegmentsResult);
  }

  /**
   * Test {@link EFSNIOPath#removeTrailingSeparator()}.
   *
   * <p>Method under test: {@link EFSNIOPath#removeTrailingSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.removeTrailingSeparator()"})
  public void testRemoveTrailingSeparator() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOPath efsnioPath = new EFSNIOPath(nioPath);

    // Act
    IPath actualRemoveTrailingSeparatorResult = efsnioPath.removeTrailingSeparator();

    // Assert
    assertSame(efsnioPath, actualRemoveTrailingSeparatorResult);
  }

  /**
   * Test {@link EFSNIOPath#segment(int)}.
   *
   * <p>Method under test: {@link EFSNIOPath#segment(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String EFSNIOPath.segment(int)"})
  public void testSegment() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new EFSNIOPath(nioPath).segment(1));
  }

  /**
   * Test {@link EFSNIOPath#segmentCount()}.
   *
   * <p>Method under test: {@link EFSNIOPath#segmentCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int EFSNIOPath.segmentCount()"})
  public void testSegmentCount() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(0, new EFSNIOPath(nioPath).segmentCount());
  }

  /**
   * Test {@link EFSNIOPath#segments()}.
   *
   * <p>Method under test: {@link EFSNIOPath#segments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] EFSNIOPath.segments()"})
  public void testSegments() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(0, new EFSNIOPath(nioPath).segments().length);
  }

  /**
   * Test {@link EFSNIOPath#setDevice(String)}.
   *
   * <p>Method under test: {@link EFSNIOPath#setDevice(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.setDevice(String)"})
  public void testSetDevice() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new EFSNIOPath(nioPath).setDevice("Device"));
  }

  /**
   * Test {@link EFSNIOPath#uptoSegment(int)}.
   *
   * <p>Method under test: {@link EFSNIOPath#uptoSegment(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IPath EFSNIOPath.uptoSegment(int)"})
  public void testUptoSegment() {
    // Arrange
    Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new EFSNIOPath(nioPath).uptoSegment(3));
  }
}
