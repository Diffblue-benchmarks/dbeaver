package org.jkiss.dbeaver.ext.oracle.oci;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OCIUtilsDiffblueTest {
  /**
   * Test {@link OCIUtils#getOraHomes()}.
   *
   * <p>Method under test: {@link OCIUtils#getOraHomes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List OCIUtils.getOraHomes()"})
  public void testGetOraHomes() {
    // Arrange and Act
    List<OracleHomeDescriptor> actualOraHomes = OCIUtils.getOraHomes();

    // Assert
    assertTrue(actualOraHomes.isEmpty());
  }

  /**
   * Test {@link OCIUtils#getDefaultOraHome()}.
   *
   * <p>Method under test: {@link OCIUtils#getDefaultOraHome()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleHomeDescriptor OCIUtils.getDefaultOraHome()"})
  public void testGetDefaultOraHome() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.getDefaultOraHome());
  }

  /**
   * Test {@link OCIUtils#getDefaultOraHomePath()}.
   *
   * <p>Method under test: {@link OCIUtils#getDefaultOraHomePath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File OCIUtils.getDefaultOraHomePath()"})
  public void testGetDefaultOraHomePath() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.getDefaultOraHomePath());
  }

  /**
   * Test {@link OCIUtils#getOraHome(String)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#getOraHome(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleHomeDescriptor OCIUtils.getOraHome(String)"})
  public void testGetOraHome_whenEmptyString() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.getOraHome(""));
  }

  /**
   * Test {@link OCIUtils#getOraHome(String)}.
   *
   * <ul>
   *   <li>When {@code Ora Home}.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#getOraHome(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleHomeDescriptor OCIUtils.getOraHome(String)"})
  public void testGetOraHome_whenOraHome() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.getOraHome("Ora Home"));
  }

  /**
   * Test {@link OCIUtils#getOraHomeByName(String)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#getOraHomeByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleHomeDescriptor OCIUtils.getOraHomeByName(String)"})
  public void testGetOraHomeByName_whenEmptyString() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.getOraHomeByName(""));
  }

  /**
   * Test {@link OCIUtils#getOraHomeByName(String)}.
   *
   * <ul>
   *   <li>When {@code Ora Home Name}.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#getOraHomeByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleHomeDescriptor OCIUtils.getOraHomeByName(String)"})
  public void testGetOraHomeByName_whenOraHomeName() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.getOraHomeByName("Ora Home Name"));
  }

  /**
   * Test {@link OCIUtils#readWinRegistry(String, String)}.
   *
   * <p>Method under test: {@link OCIUtils#readWinRegistry(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OCIUtils.readWinRegistry(String, String)"})
  public void testReadWinRegistry() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.readWinRegistry("Ora Home", "Name"));
  }

  /**
   * Test {@link OCIUtils#findTnsNamesFile(File, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#findTnsNamesFile(File, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File OCIUtils.findTnsNamesFile(File, boolean)"})
  public void testFindTnsNamesFile_whenNull() {
    // Arrange, Act and Assert
    assertNull(OCIUtils.findTnsNamesFile(null, false));
  }

  /**
   * Test {@link OCIUtils#findTnsNamesFile(File, boolean)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#findTnsNamesFile(File, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File OCIUtils.findTnsNamesFile(File, boolean)"})
  public void testFindTnsNamesFile_whenPropertyIsJavaIoTmpdirIsTestTxtToFile() {
    // Arrange, Act and Assert
    assertNull(
        OCIUtils.findTnsNamesFile(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true));
  }

  /**
   * Test {@link OCIUtils#readTnsNames(File, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#readTnsNames(File, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map OCIUtils.readTnsNames(File, boolean)"})
  public void testReadTnsNames_whenNull() {
    // Arrange and Act
    Map<String, String> actualReadTnsNamesResult = OCIUtils.readTnsNames(null, false);

    // Assert
    assertTrue(actualReadTnsNamesResult.isEmpty());
  }

  /**
   * Test {@link OCIUtils#readTnsNames(File, boolean)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt} toFile.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#readTnsNames(File, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map OCIUtils.readTnsNames(File, boolean)"})
  public void testReadTnsNames_whenPropertyIsJavaIoTmpdirIsTestTxtToFile() {
    // Arrange and Act
    Map<String, String> actualReadTnsNamesResult =
        OCIUtils.readTnsNames(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), true);

    // Assert
    assertTrue(actualReadTnsNamesResult.isEmpty());
  }

  /**
   * Test {@link OCIUtils#isInstantClient(String)}.
   *
   * <ul>
   *   <li>When {@code Ora Home}.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#isInstantClient(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OCIUtils.isInstantClient(String)"})
  public void testIsInstantClient_whenOraHome() {
    // Arrange, Act and Assert
    assertFalse(OCIUtils.isInstantClient("Ora Home"));
  }

  /**
   * Test {@link OCIUtils#isInstantClient(String)}.
   *
   * <ul>
   *   <li>When {@code /}.
   * </ul>
   *
   * <p>Method under test: {@link OCIUtils#isInstantClient(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OCIUtils.isInstantClient(String)"})
  public void testIsInstantClient_whenSlash() {
    // Arrange, Act and Assert
    assertFalse(OCIUtils.isInstantClient("/"));
  }
}
