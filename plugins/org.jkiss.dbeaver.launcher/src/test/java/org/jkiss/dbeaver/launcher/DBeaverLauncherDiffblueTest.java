package org.jkiss.dbeaver.launcher;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.launcher.DBeaverLauncher.Identifier;
import org.jkiss.dbeaver.launcher.DBeaverLauncher.SplashHandler;
import org.jkiss.dbeaver.launcher.DBeaverLauncher.StartupClassLoader;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBeaverLauncherDiffblueTest {
  @Mock private DBeaverLauncher dBeaverLauncher;

  /**
   * Test {@link DBeaverLauncher#decode(String)}.
   *
   * <p>Method under test: {@link DBeaverLauncher#decode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.decode(String)"})
  public void testDecode() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example", new DBeaverLauncher().decode("https://example.org/example"));
  }

  /**
   * Test {@link DBeaverLauncher#getArrayFromList(String)}.
   *
   * <ul>
   *   <li>When {@code ,}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#getArrayFromList(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.getArrayFromList(String)"})
  public void testGetArrayFromList_whenComma_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().getArrayFromList(",").length);
  }

  /**
   * Test {@link DBeaverLauncher#getArrayFromList(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#getArrayFromList(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.getArrayFromList(String)"})
  public void testGetArrayFromList_whenEmptyString_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().getArrayFromList("").length);
  }

  /**
   * Test {@link DBeaverLauncher#getArrayFromList(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#getArrayFromList(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.getArrayFromList(String)"})
  public void testGetArrayFromList_whenNull_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().getArrayFromList(null).length);
  }

  /**
   * Test {@link DBeaverLauncher#getArrayFromList(String)}.
   *
   * <ul>
   *   <li>When {@code Prop}.
   *   <li>Then return array of {@link String} with {@code Prop}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#getArrayFromList(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.getArrayFromList(String)"})
  public void testGetArrayFromList_whenProp_thenReturnArrayOfStringWithProp() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[] {"Prop"}, new DBeaverLauncher().getArrayFromList("Prop"));
  }

  /**
   * Test {@link DBeaverLauncher#addEntry(URL, List)}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#addEntry(URL, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverLauncher.addEntry(URL, List)"})
  public void testAddEntry_thenArrayListSizeIsTwo() throws MalformedURLException {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

    ArrayList<URL> result = new ArrayList<>();
    result.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Act
    dBeaverLauncher.addEntry(url, result);

    // Assert
    assertEquals(2, result.size());
    assertSame(url, result.get(1));
  }

  /**
   * Test {@link DBeaverLauncher#addEntry(URL, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#addEntry(URL, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverLauncher.addEntry(URL, List)"})
  public void testAddEntry_whenArrayList_thenArrayListSizeIsOne() throws MalformedURLException {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    ArrayList<URL> result = new ArrayList<>();

    // Act
    dBeaverLauncher.addEntry(url, result);

    // Assert
    assertEquals(1, result.size());
    assertSame(url, result.get(0));
  }

  /**
   * Test Identifier {@link Identifier#isGreaterEqualTo(Identifier)}.
   *
   * <p>Method under test: {@link Identifier#isGreaterEqualTo(Identifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Identifier.isGreaterEqualTo(Identifier)"})
  public void testIdentifierIsGreaterEqualTo() {
    // Arrange
    Identifier identifier = new Identifier(1, 1, 1);

    // Act
    boolean actualIsGreaterEqualToResult = identifier.isGreaterEqualTo(new Identifier("1.0.2"));

    // Assert
    assertTrue(actualIsGreaterEqualToResult);
  }

  /**
   * Test Identifier {@link Identifier#isGreaterEqualTo(Identifier)}.
   *
   * <p>Method under test: {@link Identifier#isGreaterEqualTo(Identifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Identifier.isGreaterEqualTo(Identifier)"})
  public void testIdentifierIsGreaterEqualTo2() {
    // Arrange
    Identifier identifier = new Identifier("1.0.2");

    // Act
    boolean actualIsGreaterEqualToResult = identifier.isGreaterEqualTo(new Identifier(1, 1, 1));

    // Assert
    assertFalse(actualIsGreaterEqualToResult);
  }

  /**
   * Test Identifier {@link Identifier#isGreaterEqualTo(Identifier)}.
   *
   * <p>Method under test: {@link Identifier#isGreaterEqualTo(Identifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Identifier.isGreaterEqualTo(Identifier)"})
  public void testIdentifierIsGreaterEqualTo3() {
    // Arrange
    Identifier identifier = new Identifier(0, 1, 1);

    // Act
    boolean actualIsGreaterEqualToResult = identifier.isGreaterEqualTo(new Identifier("1.0.2"));

    // Assert
    assertFalse(actualIsGreaterEqualToResult);
  }

  /**
   * Test Identifier {@link Identifier#isGreaterEqualTo(Identifier)}.
   *
   * <p>Method under test: {@link Identifier#isGreaterEqualTo(Identifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Identifier.isGreaterEqualTo(Identifier)"})
  public void testIdentifierIsGreaterEqualTo4() {
    // Arrange
    Identifier identifier = new Identifier(1, 0, 1);

    // Act
    boolean actualIsGreaterEqualToResult = identifier.isGreaterEqualTo(new Identifier("1.0.2"));

    // Assert
    assertFalse(actualIsGreaterEqualToResult);
  }

  /**
   * Test Identifier {@link Identifier#isGreaterEqualTo(Identifier)}.
   *
   * <p>Method under test: {@link Identifier#isGreaterEqualTo(Identifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Identifier.isGreaterEqualTo(Identifier)"})
  public void testIdentifierIsGreaterEqualTo5() {
    // Arrange
    Identifier identifier = new Identifier("1.0.2");

    // Act
    boolean actualIsGreaterEqualToResult = identifier.isGreaterEqualTo(new Identifier(0, 1, 1));

    // Assert
    assertTrue(actualIsGreaterEqualToResult);
  }

  /**
   * Test Identifier {@link Identifier#isGreaterEqualTo(Identifier)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Identifier#isGreaterEqualTo(Identifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Identifier.isGreaterEqualTo(Identifier)"})
  public void testIdentifierIsGreaterEqualTo_thenReturnTrue() {
    // Arrange
    Identifier identifier = new Identifier("1.0.2");

    // Act
    boolean actualIsGreaterEqualToResult = identifier.isGreaterEqualTo(new Identifier("1.0.2"));

    // Assert
    assertTrue(actualIsGreaterEqualToResult);
  }

  /**
   * Test {@link DBeaverLauncher#searchFor(String, String)}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When {@code configuration}.
   *   <li>Then return {@code /configuration}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#searchFor(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.searchFor(String, String)"})
  public void testSearchFor_givenDBeaverLauncher_whenConfiguration_thenReturnConfiguration() {
    // Arrange, Act and Assert
    assertEquals("/configuration", new DBeaverLauncher().searchFor("configuration", ""));
  }

  /**
   * Test {@link DBeaverLauncher#searchFor(String, String)}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#searchFor(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.searchFor(String, String)"})
  public void testSearchFor_givenDBeaverLauncher_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBeaverLauncher().searchFor("Target", ""));
  }

  /**
   * Test {@link DBeaverLauncher#searchFor(String, String)}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When {@code Start}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#searchFor(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.searchFor(String, String)"})
  public void testSearchFor_givenDBeaverLauncher_whenStart_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBeaverLauncher().searchFor("Target", "Start"));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When array of {@link String} with {@code 2020-03-01}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_givenDBeaverLauncher_whenArrayOfStringWith20200301_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().findMax("Prefix", new String[] {"2020-03-01"}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When array of {@link String} with {@code 2020-03-01}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_givenDBeaverLauncher_whenArrayOfStringWith20200301_thenReturnZero2() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().findMax(null, new String[] {"2020-03-01"}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When array of {@link String} with {@code 20200301}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_givenDBeaverLauncher_whenArrayOfStringWith20200301_thenReturnZero3() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().findMax(null, new String[] {"20200301"}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When array of {@link String} with {@code .jar}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_givenDBeaverLauncher_whenArrayOfStringWithJar_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().findMax(null, new String[] {".jar"}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher} (default constructor).
   *   <li>When array of {@link String} with {@code null}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_givenDBeaverLauncher_whenArrayOfStringWithNull_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().findMax("Prefix", new String[] {null}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code 2020-03-01} and {@code 20200301}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_whenArrayOfStringWith20200301And20200301_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, new DBeaverLauncher().findMax(null, new String[] {"2020-03-01", "20200301"}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code 2020-03-01} and {@code 2020/03/01}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_whenArrayOfStringWith20200301And20200301_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0, new DBeaverLauncher().findMax("Prefix", new String[] {"2020-03-01", "2020/03/01"}));
  }

  /**
   * Test {@link DBeaverLauncher#findMax(String, String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code 20200301} and {@code 2020/03/01}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#findMax(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.findMax(String, String[])"})
  public void testFindMax_whenArrayOfStringWith20200301And20200301_thenReturnZero2() {
    // Arrange, Act and Assert
    assertEquals(0, new DBeaverLauncher().findMax(null, new String[] {"20200301", "2020/03/01"}));
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndDash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult = dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       -debug}.
   *   <li>Then {@link DBeaverLauncher} (default constructor) {@link DBeaverLauncher#debug}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndDebug_thenDBeaverLauncherDebug() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-debug"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertTrue(dBeaverLauncher.debug);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code -dev}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndDev() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult = dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-dev"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertTrue(dBeaverLauncher.inDevelopmentMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       eclipse.startTime}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndEclipseStartTime() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "eclipse.startTime"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.cliMode);
    assertTrue(dBeaverLauncher.splashDown);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       -initialize}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndInitialize() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-initialize"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       --launcher.appendVmargs}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndLauncherAppendVmargs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "--launcher.appendVmargs"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       --launcher.overrideVmargs}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndLauncherOverrideVmargs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(
            new String[] {Constants.ARG_FORCE_CLI_MODE, "--launcher.overrideVmargs"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       -noExit}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndNoExit() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-noExit"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       -nosplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndNosplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-nosplash"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.cliMode);
    assertTrue(dBeaverLauncher.splashDown);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE} and {@code
   *       -showsplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_modeAndShowsplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE, "-showsplash"});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@link Constants#ARG_FORCE_CLI_MODE}.
   *   <li>Then not {@link DBeaverLauncher} (default constructor) {@link
   *       DBeaverLauncher#splashDown}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithArg_force_cli_mode_thenNotDBeaverLauncherSplashDown() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult = dBeaverLauncher.run(new String[] {Constants.ARG_FORCE_CLI_MODE});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -dev} and {@link Constants#ARG_FORCE_CLI_MODE}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithDevAndArg_force_cli_mode() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult = dBeaverLauncher.run(new String[] {"-dev", Constants.ARG_FORCE_CLI_MODE});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertTrue(dBeaverLauncher.inDevelopmentMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code eclipse.startTime} and {@link
   *       Constants#ARG_FORCE_CLI_MODE}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithEclipseStartTimeAndArg_force_cli_mode() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {"eclipse.startTime", Constants.ARG_FORCE_CLI_MODE});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.cliMode);
    assertTrue(dBeaverLauncher.splashDown);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -newInstance} and {@link
   *       Constants#ARG_FORCE_CLI_MODE}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithNewInstanceAndArg_force_cli_mode() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {"-newInstance", Constants.ARG_FORCE_CLI_MODE});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#run(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -showsplash} and {@link
   *       Constants#ARG_FORCE_CLI_MODE}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#run(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBeaverLauncher.run(String[])"})
  public void testRun_whenArrayOfStringWithShowsplashAndArg_force_cli_mode() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    int actualRunResult =
        dBeaverLauncher.run(new String[] {"-showsplash", Constants.ARG_FORCE_CLI_MODE});

    // Assert
    assertEquals(0, actualRunResult);
    assertFalse(dBeaverLauncher.debug);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
    assertEquals(
        System.getProperty("osgi.configuration.area"),
        dBeaverLauncher.configurationLocation.toString());
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-vmargs", "Args"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {null, null}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then array of {@link String} with {@code --launcher.appendVmargs}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenArrayOfStringWithLauncherAppendVmargs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"--launcher.appendVmargs"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"--launcher.appendVmargs"}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then array of {@link String} with {@code --launcher.overrideVmargs}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenArrayOfStringWithLauncherOverrideVmargs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"--launcher.overrideVmargs"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"--launcher.overrideVmargs"}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then array of {@link String} with {@code -newInstance}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenArrayOfStringWithNewInstance() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-newInstance"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"-newInstance"}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then array of {@link String} with {@code -showsplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenArrayOfStringWithShowsplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-showsplash"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"-showsplash"}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then array of {@link String} with {@code -vmargs} is array of {@link String} with {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenArrayOfStringWithVmargsIsArrayOfStringWithNull() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-vmargs"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {null}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then {@link DBeaverLauncher} (default constructor) {@link DBeaverLauncher#cliMode}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenDBeaverLauncherCliMode() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act and Assert
    assertEquals(
        0, dBeaverLauncher.processCommandLine(new String[] {Constants.ARG_FORCE_CLI_MODE}).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.cliMode);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then {@link DBeaverLauncher} (default constructor) {@link DBeaverLauncher#debug}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenDBeaverLauncherDebug() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"-debug", "-nosplash"});

    // Assert
    assertTrue(dBeaverLauncher.debug);
    assertTrue(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"-debug"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then {@link DBeaverLauncher} (default constructor) {@link DBeaverLauncher#devClassPath}
   *       is {@code Args}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenDBeaverLauncherDevClassPathIsArgs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"-dev", "Args"});

    // Assert
    assertEquals("Args", dBeaverLauncher.devClassPath);
    assertTrue(dBeaverLauncher.inDevelopmentMode);
    assertArrayEquals(new String[] {"-dev", "Args"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then {@link DBeaverLauncher} (default constructor) {@link DBeaverLauncher#devClassPath}
   *       is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenDBeaverLauncherDevClassPathIsNull() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult = dBeaverLauncher.processCommandLine(new String[] {});

    // Assert
    assertNull(dBeaverLauncher.devClassPath);
    assertEquals(0, actualProcessCommandLineResult.length);
    assertFalse(dBeaverLauncher.cliMode);
    assertFalse(dBeaverLauncher.debug);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then first element is {@code --launcher.library}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenFirstElementIsLauncherLibrary() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"--launcher.library", "Args"};

    // Act
    String[] actualProcessCommandLineResult = dBeaverLauncher.processCommandLine(args);

    // Assert
    assertEquals("--launcher.library", args[0]);
    assertEquals(0, actualProcessCommandLineResult.length);
    assertEquals(2, args.length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code Args} and {@code Args}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenReturnArrayOfStringWithArgsAndArgs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"Args", "Args"});

    // Assert
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"Args", "Args"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code -dev} and {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenReturnArrayOfStringWithDevAndDash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"-dev", "-"});

    // Assert
    assertTrue(dBeaverLauncher.inDevelopmentMode);
    assertArrayEquals(new String[] {"-dev", "-"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code -initialize}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_thenReturnArrayOfStringWithInitialize() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"-initialize"});

    // Assert
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"-initialize"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code Args} and {@code -nosplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithArgsAndNosplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"Args", "-nosplash"});

    // Assert
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"Args"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code Args}.
   *   <li>Then return array of {@link String} with {@code Args}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithArgs_thenReturnArrayOfStringWithArgs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"Args"});

    // Assert
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"Args"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -dev} and {@code -nosplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithDevAndNosplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"-dev", "-nosplash"});

    // Assert
    assertTrue(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"-dev"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -dev}.
   *   <li>Then return array of {@link String} with {@code -dev}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithDev_thenReturnArrayOfStringWithDev() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();

    // Act
    String[] actualProcessCommandLineResult =
        dBeaverLauncher.processCommandLine(new String[] {"-dev"});

    // Assert
    assertFalse(dBeaverLauncher.splashDown);
    assertTrue(dBeaverLauncher.inDevelopmentMode);
    assertArrayEquals(new String[] {"-dev"}, actualProcessCommandLineResult);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -noExit}.
   *   <li>Then array of {@link String} with {@code -noExit}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithNoExit_thenArrayOfStringWithNoExit() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-noExit"};

    // Act and Assert
    assertEquals(0, dBeaverLauncher.processCommandLine(args).length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
    assertArrayEquals(new String[] {"-noExit"}, args);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -protect} and {@code master}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithProtectAndMaster() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-protect", "master"};

    // Act
    String[] actualProcessCommandLineResult = dBeaverLauncher.processCommandLine(args);

    // Assert
    assertEquals("-protect", args[0]);
    assertEquals(0, actualProcessCommandLineResult.length);
    assertEquals(2, args.length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -protect} and {@code -nosplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithProtectAndNosplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-protect", "-nosplash"};

    // Act
    String[] actualProcessCommandLineResult = dBeaverLauncher.processCommandLine(args);

    // Assert
    assertEquals("-protect", args[0]);
    assertEquals(0, actualProcessCommandLineResult.length);
    assertEquals(2, args.length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -showsplash} and {@code Args}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithShowsplashAndArgs() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-showsplash", "Args"};

    // Act
    String[] actualProcessCommandLineResult = dBeaverLauncher.processCommandLine(args);

    // Assert
    assertEquals("-showsplash", args[0]);
    assertEquals(0, actualProcessCommandLineResult.length);
    assertEquals(2, args.length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertFalse(dBeaverLauncher.splashDown);
  }

  /**
   * Test {@link DBeaverLauncher#processCommandLine(String[])}.
   *
   * <ul>
   *   <li>When array of {@link String} with {@code -showsplash} and {@code -nosplash}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#processCommandLine(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] DBeaverLauncher.processCommandLine(String[])"})
  public void testProcessCommandLine_whenArrayOfStringWithShowsplashAndNosplash() {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    String[] args = new String[] {"-showsplash", "-nosplash"};

    // Act
    String[] actualProcessCommandLineResult = dBeaverLauncher.processCommandLine(args);

    // Assert
    assertEquals("-showsplash", args[0]);
    assertEquals(0, actualProcessCommandLineResult.length);
    assertEquals(2, args.length);
    assertFalse(dBeaverLauncher.inDevelopmentMode);
    assertTrue(dBeaverLauncher.splashDown);
  }

  /**
   * Test {@link DBeaverLauncher#getDefaultSecretStorageLocation()}.
   *
   * <p>Method under test: {@link DBeaverLauncher#getDefaultSecretStorageLocation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBeaverLauncher.getDefaultSecretStorageLocation()"})
  public void testGetDefaultSecretStorageLocation() {
    // Arrange, Act and Assert
    File toFileResult = DBeaverLauncher.getDefaultSecretStorageLocation().toFile();
    assertEquals("secure_storage", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBeaverLauncher#getWorkingDirectory(String)}.
   *
   * <p>Method under test: {@link DBeaverLauncher#getWorkingDirectory(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.getWorkingDirectory(String)"})
  public void testGetWorkingDirectory() {
    // Arrange and Act
    String actualWorkingDirectory =
        DBeaverLauncher.getWorkingDirectory("Default Workspace Location");

    // Assert
    Path getResult = Paths.get(System.getProperty("user.home"), "Library", "Default");
    String expectedWorkingDirectory = String.join("", getResult.toString(), " Workspace Location");
    assertEquals(expectedWorkingDirectory, actualWorkingDirectory);
  }

  /**
   * Test SplashHandler {@link SplashHandler#run()}.
   *
   * <p>Method under test: {@link SplashHandler#run()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashHandler.run()"})
  public void testSplashHandlerRun() {
    // Arrange
    doNothing().when(dBeaverLauncher).takeDownSplash();

    // Act
    dBeaverLauncher.new SplashHandler().run();

    // Assert
    verify(dBeaverLauncher).takeDownSplash();
  }

  /**
   * Test StartupClassLoader {@link StartupClassLoader#findClass(String, String)} with {@code
   * String}, {@code String}.
   *
   * <p>Method under test: {@link StartupClassLoader#findClass(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Class StartupClassLoader.findClass(String, String)"})
  public void testStartupClassLoaderFindClassWithStringString() throws MalformedURLException {
    // Arrange
    URL[] urlArray =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    StartupClassLoader startupClassLoader = new DBeaverLauncher().new StartupClassLoader(urlArray);

    // Act and Assert
    assertNull(startupClassLoader.findClass("Module Name", "Name"));
  }

  /**
   * Test StartupClassLoader {@link StartupClassLoader#findLibrary(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link StartupClassLoader#findLibrary(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StartupClassLoader.findLibrary(String)"})
  public void testStartupClassLoaderFindLibrary_thenReturnNull() throws MalformedURLException {
    // Arrange
    URL[] urlArray =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    StartupClassLoader startupClassLoader = new DBeaverLauncher().new StartupClassLoader(urlArray);

    // Act and Assert
    assertNull(startupClassLoader.findLibrary("Name"));
  }

  /**
   * Test StartupClassLoader {@link StartupClassLoader#findResource(String, String)} with {@code
   * String}, {@code String}.
   *
   * <p>Method under test: {@link StartupClassLoader#findResource(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL StartupClassLoader.findResource(String, String)"})
  public void testStartupClassLoaderFindResourceWithStringString() throws MalformedURLException {
    // Arrange
    URL[] urlArray =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    StartupClassLoader startupClassLoader = new DBeaverLauncher().new StartupClassLoader(urlArray);

    // Act and Assert
    assertNull(startupClassLoader.findResource("Module Name", "Name"));
  }

  /**
   * Test StartupClassLoader {@link StartupClassLoader#StartupClassLoader(DBeaverLauncher, URL[])}.
   *
   * <p>Method under test: {@link StartupClassLoader#StartupClassLoader(DBeaverLauncher, URL[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[])",
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[], ClassLoader)",
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[], ClassLoader, URLStreamHandlerFactory)"
  })
  public void testStartupClassLoaderNewStartupClassLoader() throws MalformedURLException {
    // Arrange
    URL[] urlArray =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};

    // Act
    StartupClassLoader actualStartupClassLoader =
        new DBeaverLauncher().new StartupClassLoader(urlArray);

    // Assert
    assertNotNull(actualStartupClassLoader);
  }

  /**
   * Test StartupClassLoader {@link StartupClassLoader#StartupClassLoader(DBeaverLauncher, URL[],
   * ClassLoader)}.
   *
   * <p>Method under test: {@link StartupClassLoader#StartupClassLoader(DBeaverLauncher, URL[],
   * ClassLoader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[])",
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[], ClassLoader)",
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[], ClassLoader, URLStreamHandlerFactory)"
  })
  public void testStartupClassLoaderNewStartupClassLoader2() throws MalformedURLException {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    URL[] urlArray =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    URL[] urlArray2 =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    URLClassLoader urlClassLoader = new URLClassLoader(urlArray2);

    // Act
    StartupClassLoader actualStartupClassLoader =
        dBeaverLauncher.new StartupClassLoader(urlArray, urlClassLoader);

    // Assert
    assertNotNull(actualStartupClassLoader);
  }

  /**
   * Test StartupClassLoader {@link StartupClassLoader#StartupClassLoader(DBeaverLauncher, URL[],
   * ClassLoader, URLStreamHandlerFactory)}.
   *
   * <ul>
   *   <li>When {@link URLStreamHandlerFactory}.
   * </ul>
   *
   * <p>Method under test: {@link StartupClassLoader#StartupClassLoader(DBeaverLauncher, URL[],
   * ClassLoader, URLStreamHandlerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[])",
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[], ClassLoader)",
    "void StartupClassLoader.<init>(DBeaverLauncher, URL[], ClassLoader, URLStreamHandlerFactory)"
  })
  public void testStartupClassLoaderNewStartupClassLoader_whenURLStreamHandlerFactory()
      throws MalformedURLException {
    // Arrange
    DBeaverLauncher dBeaverLauncher = new DBeaverLauncher();
    URL[] urlArray =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    URL[] urlArray2 =
        new URL[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()};
    URLClassLoader urlClassLoader = new URLClassLoader(urlArray2);

    // Act
    StartupClassLoader actualStartupClassLoader =
        dBeaverLauncher
        .new StartupClassLoader(urlArray, urlClassLoader, mock(URLStreamHandlerFactory.class));

    // Assert
    assertNotNull(actualStartupClassLoader);
  }

  /**
   * Test {@link DBeaverLauncher#substituteVars(String)} with {@code path}.
   *
   * <ul>
   *   <li>Then return {@code $$}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#substituteVars(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.substituteVars(String)"})
  public void testSubstituteVarsWithPath_thenReturnDollarSignDollarSign() {
    // Arrange, Act and Assert
    assertEquals("$$", DBeaverLauncher.substituteVars("$$"));
  }

  /**
   * Test {@link DBeaverLauncher#substituteVars(String)} with {@code path}.
   *
   * <ul>
   *   <li>Then return {@link DBeaverLauncher#VARIABLE_DELIM_STRING}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#substituteVars(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.substituteVars(String)"})
  public void testSubstituteVarsWithPath_thenReturnVariable_delim_string() {
    // Arrange, Act and Assert
    assertEquals(
        DBeaverLauncher.VARIABLE_DELIM_STRING,
        DBeaverLauncher.substituteVars(DBeaverLauncher.VARIABLE_DELIM_STRING));
  }

  /**
   * Test {@link DBeaverLauncher#substituteVars(String)} with {@code path}.
   *
   * <ul>
   *   <li>When {@code Path}.
   *   <li>Then return {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#substituteVars(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.substituteVars(String)"})
  public void testSubstituteVarsWithPath_whenPath_thenReturnPath() {
    // Arrange, Act and Assert
    assertEquals("Path", DBeaverLauncher.substituteVars("Path"));
  }

  /**
   * Test {@link DBeaverLauncher#substituteVars(String)} with {@code path}.
   *
   * <ul>
   *   <li>When {@code $Path}.
   *   <li>Then return {@code $Path}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncher#substituteVars(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBeaverLauncher.substituteVars(String)"})
  public void testSubstituteVarsWithPath_whenPath_thenReturnPath2() {
    // Arrange, Act and Assert
    assertEquals("$Path", DBeaverLauncher.substituteVars("$Path"));
  }

  /**
   * Test new {@link DBeaverLauncher} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DBeaverLauncher}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverLauncher.<init>()"})
  public void testNewDBeaverLauncher() {
    // Arrange and Act
    DBeaverLauncher actualDBeaverLauncher = new DBeaverLauncher();

    // Assert
    assertEquals("org.eclipse.osgi", actualDBeaverLauncher.framework);
    assertNull(actualDBeaverLauncher.log);
    assertNull(actualDBeaverLauncher.logFile);
    assertNull(actualDBeaverLauncher.arch);
    assertNull(actualDBeaverLauncher.bootLocation);
    assertNull(actualDBeaverLauncher.devClassPath);
    assertNull(actualDBeaverLauncher.os);
    assertNull(actualDBeaverLauncher.parentConfigurationLocation);
    assertNull(actualDBeaverLauncher.ws);
    assertNull(actualDBeaverLauncher.extensionPaths);
    assertNull(actualDBeaverLauncher.configurationLocation);
    assertNull(actualDBeaverLauncher.installLocation);
    assertNull(actualDBeaverLauncher.bridge);
    assertFalse(actualDBeaverLauncher.cliMode);
    assertFalse(actualDBeaverLauncher.debug);
    assertFalse(actualDBeaverLauncher.inDevelopmentMode);
    assertFalse(actualDBeaverLauncher.splashDown);
    assertTrue(actualDBeaverLauncher.newSession);
  }
}
