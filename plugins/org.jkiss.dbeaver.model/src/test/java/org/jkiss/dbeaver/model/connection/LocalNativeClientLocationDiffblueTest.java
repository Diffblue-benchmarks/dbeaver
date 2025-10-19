package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalNativeClientLocationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalNativeClientLocation#LocalNativeClientLocation(String, File, String)}
   *   <li>{@link LocalNativeClientLocation#toString()}
   *   <li>{@link LocalNativeClientLocation#getDisplayName()}
   *   <li>{@link LocalNativeClientLocation#getName()}
   *   <li>{@link LocalNativeClientLocation#getPath()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalNativeClientLocation.<init>(String, File, String)",
    "String LocalNativeClientLocation.getDisplayName()",
    "String LocalNativeClientLocation.getName()",
    "File LocalNativeClientLocation.getPath()",
    "String LocalNativeClientLocation.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    File path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    LocalNativeClientLocation actualLocalNativeClientLocation =
        new LocalNativeClientLocation("42", path, "Display Name");
    String actualToStringResult = actualLocalNativeClientLocation.toString();
    String actualDisplayName = actualLocalNativeClientLocation.getDisplayName();
    String actualName = actualLocalNativeClientLocation.getName();
    File actualPath = actualLocalNativeClientLocation.getPath();

    // Assert
    assertEquals("42", actualName);
    assertEquals("Display Name", actualDisplayName);
    String expectedToStringResult =
        String.join(
            "", "Local: ", Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString());
    assertEquals(expectedToStringResult, actualToStringResult);
    assertSame(path, actualPath);
  }

  /**
   * Test {@link LocalNativeClientLocation#LocalNativeClientLocation(String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Path Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#LocalNativeClientLocation(String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalNativeClientLocation.<init>(String, String)"})
  public void testNewLocalNativeClientLocation_whenNull_thenReturnPathNameIs42() {
    // Arrange and Act
    LocalNativeClientLocation actualLocalNativeClientLocation =
        new LocalNativeClientLocation("42", null);

    // Assert
    File path = actualLocalNativeClientLocation.getPath();
    assertEquals("42", path.getName());
    assertEquals("42", actualLocalNativeClientLocation.getName());
    assertFalse(path.isAbsolute());
    assertEquals(
        Paths.get(System.getProperty("user.dir"), "42").toString(),
        actualLocalNativeClientLocation.getDisplayName());
  }

  /**
   * Test {@link LocalNativeClientLocation#LocalNativeClientLocation(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Path Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#LocalNativeClientLocation(String,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalNativeClientLocation.<init>(String, String, String)"})
  public void testNewLocalNativeClientLocation_whenNull_thenReturnPathNameIs422() {
    // Arrange and Act
    LocalNativeClientLocation actualLocalNativeClientLocation =
        new LocalNativeClientLocation("42", (String) null, "Display Name");

    // Assert
    File path = actualLocalNativeClientLocation.getPath();
    assertEquals("42", path.getName());
    assertEquals("42", actualLocalNativeClientLocation.getName());
    assertEquals("Display Name", actualLocalNativeClientLocation.getDisplayName());
    assertFalse(path.isAbsolute());
  }

  /**
   * Test {@link LocalNativeClientLocation#LocalNativeClientLocation(String, String)}.
   *
   * <ul>
   *   <li>When {@code Path}.
   *   <li>Then return Path Name is {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#LocalNativeClientLocation(String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalNativeClientLocation.<init>(String, String)"})
  public void testNewLocalNativeClientLocation_whenPath_thenReturnPathNameIsPath() {
    // Arrange and Act
    LocalNativeClientLocation actualLocalNativeClientLocation =
        new LocalNativeClientLocation("42", "Path");

    // Assert
    assertEquals("42", actualLocalNativeClientLocation.getName());
    File path = actualLocalNativeClientLocation.getPath();
    assertEquals("Path", path.getName());
    assertFalse(path.isAbsolute());
    assertEquals(
        Paths.get(System.getProperty("user.dir"), "Path").toString(),
        actualLocalNativeClientLocation.getDisplayName());
  }

  /**
   * Test {@link LocalNativeClientLocation#LocalNativeClientLocation(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code Path}.
   *   <li>Then return Path Name is {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#LocalNativeClientLocation(String,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalNativeClientLocation.<init>(String, String, String)"})
  public void testNewLocalNativeClientLocation_whenPath_thenReturnPathNameIsPath2() {
    // Arrange and Act
    LocalNativeClientLocation actualLocalNativeClientLocation =
        new LocalNativeClientLocation("42", "Path", "Display Name");

    // Assert
    assertEquals("42", actualLocalNativeClientLocation.getName());
    assertEquals("Display Name", actualLocalNativeClientLocation.getDisplayName());
    File path = actualLocalNativeClientLocation.getPath();
    assertEquals("Path", path.getName());
    assertFalse(path.isAbsolute());
  }

  /**
   * Test {@link LocalNativeClientLocation#validateFilesPresence(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link
   * LocalNativeClientLocation#validateFilesPresence(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalNativeClientLocation.validateFilesPresence(DBRProgressMonitor)"})
  public void testValidateFilesPresence() {
    // Arrange
    LocalNativeClientLocation localNativeClientLocation =
        new LocalNativeClientLocation("42", "Path");

    // Act and Assert
    assertTrue(localNativeClientLocation.validateFilesPresence(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link LocalNativeClientLocation#equals(Object)}, and {@link
   * LocalNativeClientLocation#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalNativeClientLocation#equals(Object)}
   *   <li>{@link LocalNativeClientLocation#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LocalNativeClientLocation.equals(Object)",
    "int LocalNativeClientLocation.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    LocalNativeClientLocation localNativeClientLocation =
        new LocalNativeClientLocation("42", "Path");
    LocalNativeClientLocation localNativeClientLocation2 =
        new LocalNativeClientLocation("42", "Path");

    // Act and Assert
    assertEquals(localNativeClientLocation, localNativeClientLocation2);
    assertEquals(localNativeClientLocation.hashCode(), localNativeClientLocation2.hashCode());
  }

  /**
   * Test {@link LocalNativeClientLocation#equals(Object)}, and {@link
   * LocalNativeClientLocation#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalNativeClientLocation#equals(Object)}
   *   <li>{@link LocalNativeClientLocation#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LocalNativeClientLocation.equals(Object)",
    "int LocalNativeClientLocation.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LocalNativeClientLocation localNativeClientLocation =
        new LocalNativeClientLocation("42", "Path");

    // Act and Assert
    assertEquals(localNativeClientLocation, localNativeClientLocation);
    int expectedHashCodeResult = localNativeClientLocation.hashCode();
    assertEquals(expectedHashCodeResult, localNativeClientLocation.hashCode());
  }

  /**
   * Test {@link LocalNativeClientLocation#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LocalNativeClientLocation.equals(Object)",
    "int LocalNativeClientLocation.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    LocalNativeClientLocation localNativeClientLocation =
        new LocalNativeClientLocation(
            "42",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
            "Display Name");

    // Act and Assert
    assertNotEquals(localNativeClientLocation, new LocalNativeClientLocation("42", "Path"));
  }

  /**
   * Test {@link LocalNativeClientLocation#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LocalNativeClientLocation.equals(Object)",
    "int LocalNativeClientLocation.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new LocalNativeClientLocation("42", "Path"), null);
  }

  /**
   * Test {@link LocalNativeClientLocation#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link LocalNativeClientLocation#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LocalNativeClientLocation.equals(Object)",
    "int LocalNativeClientLocation.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new LocalNativeClientLocation("42", "Path"), "Different type to LocalNativeClientLocation");
  }
}
