package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.MissingResourceException;
import org.eclipse.core.internal.expressions.ExpressionStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.connection.DBPNativeClientLocation;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableParametrizedWithProgress;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

public class RuntimeUtilsDiffblueTest {
  /**
   * Test {@link RuntimeUtils#makeMonitor(IProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link LocalCacheProgressMonitor}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#makeMonitor(IProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRProgressMonitor RuntimeUtils.makeMonitor(IProgressMonitor)"})
  public void testMakeMonitor_thenReturnLocalCacheProgressMonitor() {
    // Arrange and Act
    DBRProgressMonitor actualMakeMonitorResult =
        RuntimeUtils.makeMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Assert
    assertTrue(actualMakeMonitorResult instanceof LocalCacheProgressMonitor);
    assertNull(actualMakeMonitorResult.getActiveBlocks());
    assertTrue(actualMakeMonitorResult.isForceCacheUsage());
  }

  /**
   * Test {@link RuntimeUtils#makeMonitor(IProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link DefaultProgressMonitor}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#makeMonitor(IProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRProgressMonitor RuntimeUtils.makeMonitor(IProgressMonitor)"})
  public void testMakeMonitor_whenNull_thenReturnDefaultProgressMonitor() {
    // Arrange and Act
    DBRProgressMonitor actualMakeMonitorResult = RuntimeUtils.makeMonitor(null);

    // Assert
    assertTrue(actualMakeMonitorResult instanceof DefaultProgressMonitor);
    assertNull(actualMakeMonitorResult.getActiveBlocks());
    assertNull(actualMakeMonitorResult.getNestedMonitor());
    assertFalse(actualMakeMonitorResult.isForceCacheUsage());
  }

  /**
   * Test {@link RuntimeUtils#getNestedMonitor(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link LocalCacheProgressMonitor}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getNestedMonitor(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IProgressMonitor RuntimeUtils.getNestedMonitor(DBRProgressMonitor)"})
  public void testGetNestedMonitor_thenReturnLocalCacheProgressMonitor() {
    // Arrange and Act
    IProgressMonitor actualNestedMonitor =
        RuntimeUtils.getNestedMonitor(new LocalCacheProgressMonitor(new LoggingProgressMonitor()));

    // Assert
    assertTrue(actualNestedMonitor instanceof LocalCacheProgressMonitor);
    assertNull(((LocalCacheProgressMonitor) actualNestedMonitor).getActiveBlocks());
    assertTrue(((LocalCacheProgressMonitor) actualNestedMonitor).isForceCacheUsage());
  }

  /**
   * Test {@link RuntimeUtils#getUserHomeDir()}.
   *
   * <p>Method under test: {@link RuntimeUtils#getUserHomeDir()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File RuntimeUtils.getUserHomeDir()"})
  public void testGetUserHomeDir() {
    // Arrange and Act
    File actualUserHomeDir = RuntimeUtils.getUserHomeDir();

    // Assert
    assertTrue(actualUserHomeDir.isAbsolute());
    assertEquals(System.getProperty("user.name"), actualUserHomeDir.getName());
  }

  /**
   * Test {@link RuntimeUtils#isTypeSupported(Class, Class[])}.
   *
   * <ul>
   *   <li>When array of {@link Class} with {@link Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isTypeSupported(Class, Class[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isTypeSupported(Class, Class[])"})
  public void testIsTypeSupported_whenArrayOfClassWithObject_thenReturnTrue() {
    // Arrange
    Class<Object> type = Object.class;
    Class<Object> forNameResult = Object.class;

    // Act
    boolean actualIsTypeSupportedResult =
        RuntimeUtils.isTypeSupported(type, new Class[] {forNameResult});

    // Assert
    assertTrue(actualIsTypeSupportedResult);
  }

  /**
   * Test {@link RuntimeUtils#isTypeSupported(Class, Class[])}.
   *
   * <ul>
   *   <li>When empty array of {@link Class}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isTypeSupported(Class, Class[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isTypeSupported(Class, Class[])"})
  public void testIsTypeSupported_whenEmptyArrayOfClass_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    boolean actualIsTypeSupportedResult = RuntimeUtils.isTypeSupported(type, new Class[] {});

    // Assert
    assertFalse(actualIsTypeSupportedResult);
  }

  /**
   * Test {@link RuntimeUtils#isTypeSupported(Class, Class[])}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isTypeSupported(Class, Class[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isTypeSupported(Class, Class[])"})
  public void testIsTypeSupported_whenJavaLangString_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;
    Class<String> forNameResult = String.class;

    // Act
    boolean actualIsTypeSupportedResult =
        RuntimeUtils.isTypeSupported(type, new Class[] {forNameResult});

    // Assert
    assertFalse(actualIsTypeSupportedResult);
  }

  /**
   * Test {@link RuntimeUtils#isTypeSupported(Class, Class[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isTypeSupported(Class, Class[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isTypeSupported(Class, Class[])"})
  public void testIsTypeSupported_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(RuntimeUtils.isTypeSupported(null, null));
  }

  /**
   * Test {@link RuntimeUtils#isTypeSupported(Class, Class[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isTypeSupported(Class, Class[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isTypeSupported(Class, Class[])"})
  public void testIsTypeSupported_whenNull_thenReturnFalse2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertFalse(RuntimeUtils.isTypeSupported(type, null));
  }

  /**
   * Test {@link RuntimeUtils#getNativeBinaryName(String)}.
   *
   * <p>Method under test: {@link RuntimeUtils#getNativeBinaryName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getNativeBinaryName(String)"})
  public void testGetNativeBinaryName() {
    // Arrange, Act and Assert
    assertEquals("Bin Name", RuntimeUtils.getNativeBinaryName("Bin Name"));
  }

  /**
   * Test {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation, String, String)}.
   *
   * <ul>
   *   <li>Then return Name is {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "File RuntimeUtils.getNativeClientBinary(DBPNativeClientLocation, String, String)"
  })
  public void testGetNativeClientBinary_thenReturnNameIsTestTxt() throws IOException {
    // Arrange
    LocalNativeClientLocation home =
        new LocalNativeClientLocation(
            "42",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
            "Display Name");

    // Act
    File actualNativeClientBinary = RuntimeUtils.getNativeClientBinary(home, "Bin Folder", "");

    // Assert
    assertEquals("test.txt", actualNativeClientBinary.getName());
    assertTrue(actualNativeClientBinary.isAbsolute());
  }

  /**
   * Test {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation, String, String)}.
   *
   * <ul>
   *   <li>Then return Name is {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "File RuntimeUtils.getNativeClientBinary(DBPNativeClientLocation, String, String)"
  })
  public void testGetNativeClientBinary_thenReturnNameIsTestTxt2() throws IOException {
    // Arrange
    LocalNativeClientLocation home =
        new LocalNativeClientLocation(
            "42",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
            "Display Name");

    // Act
    File actualNativeClientBinary = RuntimeUtils.getNativeClientBinary(home, null, "");

    // Assert
    assertEquals("test.txt", actualNativeClientBinary.getName());
    assertTrue(actualNativeClientBinary.isAbsolute());
  }

  /**
   * Test {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation, String, String)}.
   *
   * <ul>
   *   <li>When {@code Bin Name}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "File RuntimeUtils.getNativeClientBinary(DBPNativeClientLocation, String, String)"
  })
  public void testGetNativeClientBinary_whenBinName_thenThrowIOException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () ->
            RuntimeUtils.getNativeClientBinary(
                new LocalNativeClientLocation("42", "Path"), "Bin Folder", "Bin Name"));
  }

  /**
   * Test {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation, String, String)}.
   *
   * <ul>
   *   <li>When {@code Bin Name}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getNativeClientBinary(DBPNativeClientLocation,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "File RuntimeUtils.getNativeClientBinary(DBPNativeClientLocation, String, String)"
  })
  public void testGetNativeClientBinary_whenBinName_thenThrowIOException2() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () ->
            RuntimeUtils.getNativeClientBinary(
                new LocalNativeClientLocation("42", "Path"), null, "Bin Name"));
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack() {
    // Arrange
    ExpressionStatus status =
        new ExpressionStatus(-1, "Not all who wander are lost", new Throwable());

    // Act
    IStatus actualStripStackResult = RuntimeUtils.stripStack(status);

    // Assert
    assertTrue(actualStripStackResult instanceof Status);
    assertEquals(
        "java.lang.Throwable: Not all who wander are lost", actualStripStackResult.getMessage());
    assertEquals(
        "org.eclipse.core.internal.expressions.ExpressionStatus",
        actualStripStackResult.getPlugin());
    assertEquals(-1, actualStripStackResult.getCode());
    assertEquals(4, actualStripStackResult.getSeverity());
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack2() {
    // Arrange
    Class<Object> caller = Object.class;

    MultiStatus status = new MultiStatus(caller, 1, "Not all who wander are lost");
    Class<Object> caller2 = Object.class;
    Status status2 = new Status(1, caller2, "Not all who wander are lost");
    status.add(status2);

    // Act
    IStatus actualStripStackResult = RuntimeUtils.stripStack(status);

    // Assert
    assertTrue(actualStripStackResult instanceof MultiStatus);
    IStatus[] children = actualStripStackResult.getChildren();
    assertEquals(1, children.length);
    assertSame(status2, children[0]);
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <ul>
   *   <li>Given {@link Job#ASYNC_FINISH}.
   *   <li>Then first element return {@link Status}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack_givenAsync_finish_thenFirstElementReturnStatus() {
    // Arrange
    Class<Object> caller = Object.class;

    MultiStatus status = new MultiStatus(caller, 1, "Not all who wander are lost");
    status.add(Job.ASYNC_FINISH);

    // Act
    IStatus actualStripStackResult = RuntimeUtils.stripStack(status);

    // Assert
    assertTrue(actualStripStackResult instanceof MultiStatus);
    IStatus[] children = actualStripStackResult.getChildren();
    IStatus iStatus = children[0];
    assertTrue(iStatus instanceof Status);
    assertEquals("", iStatus.getMessage());
    assertEquals("org.eclipse.core.jobs", iStatus.getPlugin());
    assertEquals(0, iStatus.getSeverity());
    assertEquals(1, iStatus.getCode());
    assertEquals(1, children.length);
    assertTrue(iStatus.isOK());
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <ul>
   *   <li>Then return Code is zero.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack_thenReturnCodeIsZero() {
    // Arrange
    Class<Object> caller = Object.class;
    Status status = new Status(1, caller, "Not all who wander are lost", new Throwable());

    // Act
    IStatus actualStripStackResult = RuntimeUtils.stripStack(status);

    // Assert
    assertTrue(actualStripStackResult instanceof Status);
    assertEquals(
        "java.lang.Throwable: Not all who wander are lost", actualStripStackResult.getMessage());
    assertEquals(0, actualStripStackResult.getCode());
    assertEquals(0, actualStripStackResult.getChildren().length);
    assertEquals(1, actualStripStackResult.getSeverity());
    assertFalse(actualStripStackResult.isMultiStatus());
    assertFalse(actualStripStackResult.isOK());
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <ul>
   *   <li>Then return Message is {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack_thenReturnMessageIsNotAllWhoWanderAreLost() {
    // Arrange
    Class<Object> caller = Object.class;

    // Act
    IStatus actualStripStackResult =
        RuntimeUtils.stripStack(new MultiStatus(caller, 1, "Not all who wander are lost"));

    // Assert
    assertTrue(actualStripStackResult instanceof MultiStatus);
    assertEquals("Not all who wander are lost", actualStripStackResult.getMessage());
    assertEquals(0, actualStripStackResult.getSeverity());
    assertEquals(0, actualStripStackResult.getChildren().length);
    assertTrue(actualStripStackResult.isMultiStatus());
    assertTrue(actualStripStackResult.isOK());
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <ul>
   *   <li>When {@link Job#ASYNC_FINISH}.
   *   <li>Then return Message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack_whenAsync_finish_thenReturnMessageIsEmptyString() {
    // Arrange and Act
    IStatus actualStripStackResult = RuntimeUtils.stripStack(Job.ASYNC_FINISH);

    // Assert
    assertTrue(actualStripStackResult instanceof Status);
    assertEquals("", actualStripStackResult.getMessage());
    assertEquals("org.eclipse.core.jobs", actualStripStackResult.getPlugin());
    assertEquals(0, actualStripStackResult.getSeverity());
    assertEquals(0, actualStripStackResult.getChildren().length);
    assertFalse(actualStripStackResult.isMultiStatus());
    assertTrue(actualStripStackResult.isOK());
  }

  /**
   * Test {@link RuntimeUtils#stripStack(IStatus)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#stripStack(IStatus)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus RuntimeUtils.stripStack(IStatus)"})
  public void testStripStack_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RuntimeUtils.stripStack(null));
  }

  /**
   * Test {@link RuntimeUtils#formatExecutionTime(Duration)} with {@code duration}.
   *
   * <ul>
   *   <li>Then return {@code 2562047788015215h 30m 7s}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#formatExecutionTime(Duration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.formatExecutionTime(Duration)"})
  public void testFormatExecutionTimeWithDuration_thenReturn2562047788015215h30m7s() {
    // Arrange, Act and Assert
    assertEquals(
        "2562047788015215h 30m 7s",
        RuntimeUtils.formatExecutionTime(Duration.ofSeconds(Long.MAX_VALUE)));
  }

  /**
   * Test {@link RuntimeUtils#formatExecutionTime(Duration)} with {@code duration}.
   *
   * <ul>
   *   <li>When ofSeconds one hundred.
   *   <li>Then return {@code 1m 40s}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#formatExecutionTime(Duration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.formatExecutionTime(Duration)"})
  public void testFormatExecutionTimeWithDuration_whenOfSecondsOneHundred_thenReturn1m40s() {
    // Arrange, Act and Assert
    assertEquals("1m 40s", RuntimeUtils.formatExecutionTime(Duration.ofSeconds(100L)));
  }

  /**
   * Test {@link RuntimeUtils#formatExecutionTime(Duration)} with {@code duration}.
   *
   * <ul>
   *   <li>When ofSeconds one.
   *   <li>Then return {@code 1.0s}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#formatExecutionTime(Duration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.formatExecutionTime(Duration)"})
  public void testFormatExecutionTimeWithDuration_whenOfSecondsOne_thenReturn10s() {
    // Arrange, Act and Assert
    assertEquals("1.0s", RuntimeUtils.formatExecutionTime(Duration.ofSeconds(1L)));
  }

  /**
   * Test {@link RuntimeUtils#formatExecutionTime(Duration)} with {@code duration}.
   *
   * <ul>
   *   <li>When ofSeconds ten.
   *   <li>Then return {@code 10s}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#formatExecutionTime(Duration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.formatExecutionTime(Duration)"})
  public void testFormatExecutionTimeWithDuration_whenOfSecondsTen_thenReturn10s() {
    // Arrange, Act and Assert
    assertEquals("10s", RuntimeUtils.formatExecutionTime(Duration.ofSeconds(10L)));
  }

  /**
   * Test {@link RuntimeUtils#formatExecutionTime(long)} with {@code ms}.
   *
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.
   *   <li>Then return {@code 2562047788015h 12m 55s}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#formatExecutionTime(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.formatExecutionTime(long)"})
  public void testFormatExecutionTimeWithMs_whenMax_value_thenReturn2562047788015h12m55s() {
    // Arrange, Act and Assert
    assertEquals("2562047788015h 12m 55s", RuntimeUtils.formatExecutionTime(Long.MAX_VALUE));
  }

  /**
   * Test {@link RuntimeUtils#formatExecutionTime(long)} with {@code ms}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.0s}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#formatExecutionTime(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.formatExecutionTime(long)"})
  public void testFormatExecutionTimeWithMs_whenOne_thenReturn00s() {
    // Arrange, Act and Assert
    assertEquals("0.0s", RuntimeUtils.formatExecutionTime(1L));
  }

  /**
   * Test {@link RuntimeUtils#getPlatformFile(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return Name is {@code example}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getPlatformFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File RuntimeUtils.getPlatformFile(String)"})
  public void testGetPlatformFile_whenHttpsExampleOrgExample_thenReturnNameIsExample()
      throws IOException {
    // Arrange and Act
    File actualPlatformFile = RuntimeUtils.getPlatformFile("https://example.org/example");

    // Assert
    assertEquals("example", actualPlatformFile.getName());
    assertTrue(actualPlatformFile.isAbsolute());
  }

  /**
   * Test {@link RuntimeUtils#getLocalFileFromURL(URL)}.
   *
   * <p>Method under test: {@link RuntimeUtils#getLocalFileFromURL(URL)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File RuntimeUtils.getLocalFileFromURL(URL)"})
  public void testGetLocalFileFromURL() throws IOException {
    // Arrange and Act
    File actualLocalFileFromURL =
        RuntimeUtils.getLocalFileFromURL(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert
    assertEquals("test.txt", actualLocalFileFromURL.getName());
    assertTrue(actualLocalFileFromURL.isAbsolute());
  }

  /**
   * Test {@link RuntimeUtils#getLocalPathFromURL(URL)}.
   *
   * <p>Method under test: {@link RuntimeUtils#getLocalPathFromURL(URL)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path RuntimeUtils.getLocalPathFromURL(URL)"})
  public void testGetLocalPathFromURL() throws IOException {
    // Arrange, Act and Assert
    File toFileResult =
        RuntimeUtils.getLocalPathFromURL(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL())
            .toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link RuntimeUtils#getProcessResults(Process)}.
   *
   * <ul>
   *   <li>Given forty-two.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getProcessResults(Process)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getProcessResults(Process)"})
  public void testGetProcessResults_givenFortyTwo_thenThrowDBException()
      throws IOException, InterruptedException, DBException {
    // Arrange
    Process p = mock(Process.class);
    when(p.exitValue()).thenReturn(42);
    when(p.waitFor()).thenReturn(1);
    when(p.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    when(p.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    doNothing().when(p).destroy();

    // Act and Assert
    assertThrows(DBException.class, () -> RuntimeUtils.getProcessResults(p));
    verify(p).destroy();
    verify(p).exitValue();
    verify(p).getErrorStream();
    verify(p).getInputStream();
    verify(p).waitFor();
  }

  /**
   * Test {@link RuntimeUtils#getProcessResults(Process)}.
   *
   * <ul>
   *   <li>Given {@link InterruptedException#InterruptedException()}.
   *   <li>Then throw {@link InterruptedException}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getProcessResults(Process)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getProcessResults(Process)"})
  public void testGetProcessResults_givenInterruptedException_thenThrowInterruptedException()
      throws IOException, InterruptedException, DBException {
    // Arrange
    Process p = mock(Process.class);
    when(p.waitFor()).thenThrow(new InterruptedException());
    when(p.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    when(p.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    doNothing().when(p).destroy();

    // Act and Assert
    assertThrows(InterruptedException.class, () -> RuntimeUtils.getProcessResults(p));
    verify(p).destroy();
    verify(p).getErrorStream();
    verify(p).getInputStream();
    verify(p).waitFor();
  }

  /**
   * Test {@link RuntimeUtils#getProcessResults(Process)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then return {@code AXAXAXAX}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getProcessResults(Process)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getProcessResults(Process)"})
  public void testGetProcessResults_givenZero_thenReturnAxaxaxax()
      throws IOException, InterruptedException, DBException {
    // Arrange
    Process p = mock(Process.class);
    when(p.exitValue()).thenReturn(0);
    when(p.waitFor()).thenReturn(1);
    when(p.getErrorStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    when(p.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    doNothing().when(p).destroy();

    // Act
    String actualProcessResults = RuntimeUtils.getProcessResults(p);

    // Assert
    verify(p).destroy();
    verify(p).exitValue();
    verify(p).getErrorStream();
    verify(p).getInputStream();
    verify(p).waitFor();
    assertEquals("AXAXAXAX", actualProcessResults);
  }

  /**
   * Test {@link RuntimeUtils#getProcessResults(Process)}.
   *
   * <ul>
   *   <li>When {@link Process} {@link Process#getErrorStream()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getProcessResults(Process)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getProcessResults(Process)"})
  public void testGetProcessResults_whenProcessGetErrorStreamThrowIllegalArgumentException()
      throws IOException, InterruptedException, DBException {
    // Arrange
    Process p = mock(Process.class);
    when(p.getErrorStream()).thenThrow(new IllegalArgumentException());
    when(p.getInputStream()).thenReturn(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));
    doNothing().when(p).destroy();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> RuntimeUtils.getProcessResults(p));
    verify(p).destroy();
    verify(p).getErrorStream();
    verify(p).getInputStream();
  }

  /**
   * Test {@link RuntimeUtils#getProcessResults(Process)}.
   *
   * <ul>
   *   <li>When {@link Process} {@link Process#getInputStream()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getProcessResults(Process)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getProcessResults(Process)"})
  public void testGetProcessResults_whenProcessGetInputStreamThrowIllegalArgumentException()
      throws IOException, InterruptedException, DBException {
    // Arrange
    Process p = mock(Process.class);
    when(p.getInputStream()).thenThrow(new IllegalArgumentException());
    doThrow(new IllegalArgumentException()).when(p).destroy();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> RuntimeUtils.getProcessResults(p));
    verify(p).destroy();
    verify(p).getInputStream();
  }

  /**
   * Test {@link RuntimeUtils#isWindowsStoreApplication()}.
   *
   * <p>Method under test: {@link RuntimeUtils#isWindowsStoreApplication()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isWindowsStoreApplication()"})
  public void testIsWindowsStoreApplication() {
    // Arrange, Act and Assert
    assertFalse(RuntimeUtils.isWindowsStoreApplication());
  }

  /**
   * Test {@link RuntimeUtils#isWayland()}.
   *
   * <p>Method under test: {@link RuntimeUtils#isWayland()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isWayland()"})
  public void testIsWayland() {
    // Arrange, Act and Assert
    assertFalse(RuntimeUtils.isWayland());
  }

  /**
   * Test {@link RuntimeUtils#getOSVersion()}.
   *
   * <p>Method under test: {@link RuntimeUtils#getOSVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Version RuntimeUtils.getOSVersion()"})
  public void testGetOSVersion() {
    // Arrange and Act
    Version actualOSVersion = RuntimeUtils.getOSVersion();

    // Assert
    assertEquals("", actualOSVersion.getQualifier());
    assertEquals(0, actualOSVersion.getMicro());
    assertEquals(15, actualOSVersion.getMajor());
    assertEquals(5, actualOSVersion.getMinor());
  }

  /**
   * Test {@link RuntimeUtils#isOSVersionAtLeast(int, int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isOSVersionAtLeast(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isOSVersionAtLeast(int, int, int)"})
  public void testIsOSVersionAtLeast_whenOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(RuntimeUtils.isOSVersionAtLeast(1, 1, 1));
  }

  /**
   * Test {@link RuntimeUtils#isOSVersionAtLeast(int, int, int)}.
   *
   * <ul>
   *   <li>When thirty-four.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#isOSVersionAtLeast(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RuntimeUtils.isOSVersionAtLeast(int, int, int)"})
  public void testIsOSVersionAtLeast_whenThirtyFour_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(RuntimeUtils.isOSVersionAtLeast(34, 1, 1));
  }

  /**
   * Test {@link RuntimeUtils#splitCommandLine(String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#splitCommandLine(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List RuntimeUtils.splitCommandLine(String, boolean)"})
  public void testSplitCommandLine_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualSplitCommandLineResult = RuntimeUtils.splitCommandLine("", true);

    // Assert
    assertTrue(actualSplitCommandLineResult.isEmpty());
  }

  /**
   * Test {@link RuntimeUtils#splitCommandLine(String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#splitCommandLine(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List RuntimeUtils.splitCommandLine(String, boolean)"})
  public void testSplitCommandLine_whenFalse_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualSplitCommandLineResult = RuntimeUtils.splitCommandLine("Input", false);

    // Assert
    assertEquals(1, actualSplitCommandLineResult.size());
    assertEquals("Input", actualSplitCommandLineResult.get(0));
  }

  /**
   * Test {@link RuntimeUtils#splitCommandLine(String, boolean)}.
   *
   * <ul>
   *   <li>When {@code Input}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#splitCommandLine(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List RuntimeUtils.splitCommandLine(String, boolean)"})
  public void testSplitCommandLine_whenInput_thenReturnSizeIsOne() {
    // Arrange and Act
    List<String> actualSplitCommandLineResult = RuntimeUtils.splitCommandLine("Input", true);

    // Assert
    assertEquals(1, actualSplitCommandLineResult.size());
    assertEquals("Input", actualSplitCommandLineResult.get(0));
  }

  /**
   * Test {@link RuntimeUtils#getWorkingDirectory(String)}.
   *
   * <p>Method under test: {@link RuntimeUtils#getWorkingDirectory(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getWorkingDirectory(String)"})
  public void testGetWorkingDirectory() {
    // Arrange and Act
    String actualWorkingDirectory = RuntimeUtils.getWorkingDirectory("Sub Path");

    // Assert
    Path getResult = Paths.get(System.getProperty("user.home"), ".local", "share", "Sub");
    String expectedWorkingDirectory = String.join("", getResult.toString(), " Path");
    assertEquals(expectedWorkingDirectory, actualWorkingDirectory);
  }

  /**
   * Test {@link RuntimeUtils#getWorkingDirectory(String)}.
   *
   * <p>Method under test: {@link RuntimeUtils#getWorkingDirectory(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getWorkingDirectory(String)"})
  public void testGetWorkingDirectory2() {
    // Arrange and Act
    String actualWorkingDirectory = RuntimeUtils.getWorkingDirectory("");

    // Assert
    Path getResult = Paths.get(System.getProperty("user.home"), ".local", "share");
    assertEquals(getResult.toString().concat(File.separator), actualWorkingDirectory);
  }

  /**
   * Test {@link RuntimeUtils#getBundleLocalization(Bundle, String)}.
   *
   * <ul>
   *   <li>Given {@code Symbolic Name}.
   *   <li>Then throw {@link MissingResourceException}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getBundleLocalization(Bundle, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.ResourceBundle RuntimeUtils.getBundleLocalization(Bundle, String)"})
  public void testGetBundleLocalization_givenSymbolicName_thenThrowMissingResourceException()
      throws MissingResourceException {
    // Arrange
    EquinoxBundle bundle = mock(EquinoxBundle.class);
    when(bundle.getSymbolicName()).thenReturn("Symbolic Name");

    // Act and Assert
    assertThrows(
        MissingResourceException.class, () -> RuntimeUtils.getBundleLocalization(bundle, "en"));
    verify(bundle).getSymbolicName();
  }

  /**
   * Test {@link RuntimeUtils#executeJobsForEach(Collection, DBRRunnableParametrizedWithProgress)}.
   *
   * <ul>
   *   <li>Given {@link IStatus} {@link IStatus#getSeverity()} return one.
   *   <li>Then calls {@link IStatus#getSeverity()}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#executeJobsForEach(Collection,
   * DBRRunnableParametrizedWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RuntimeUtils.executeJobsForEach(Collection, DBRRunnableParametrizedWithProgress)"
  })
  public void testExecuteJobsForEach_givenIStatusGetSeverityReturnOne_thenCallsGetSeverity() {
    // Arrange
    IStatus status = mock(IStatus.class);
    when(status.getSeverity()).thenReturn(1);
    Class<Object> caller = Object.class;

    MultiStatus multiStatus = new MultiStatus(caller, 10, "Not all who wander are lost");
    multiStatus.add(status);

    ArrayList<Object> objects = new ArrayList<>();
    objects.add(multiStatus);
    objects.add(DBPEvent.RENAME);

    // Act
    RuntimeUtils.executeJobsForEach(objects, mock(DBRRunnableParametrizedWithProgress.class));

    // Assert
    verify(status).getSeverity();
  }

  /**
   * Test {@link RuntimeUtils#getSystemPropertyIgnoreCase(String)}.
   *
   * <ul>
   *   <li>When {@code Key}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getSystemPropertyIgnoreCase(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getSystemPropertyIgnoreCase(String)"})
  public void testGetSystemPropertyIgnoreCase_whenKey_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RuntimeUtils.getSystemPropertyIgnoreCase("Key"));
  }

  /**
   * Test {@link RuntimeUtils#getSystemPropertyIgnoreCase(String)}.
   *
   * <ul>
   *   <li>When {@code os.version}.
   *   <li>Then return {@code 15.5}.
   * </ul>
   *
   * <p>Method under test: {@link RuntimeUtils#getSystemPropertyIgnoreCase(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getSystemPropertyIgnoreCase(String)"})
  public void testGetSystemPropertyIgnoreCase_whenOsVersion_thenReturn155() {
    // Arrange, Act and Assert
    assertEquals("15.5", RuntimeUtils.getSystemPropertyIgnoreCase("os.version"));
  }

  /**
   * Test {@link RuntimeUtils#getSystemEnvIgnoreCase(String)}.
   *
   * <p>Method under test: {@link RuntimeUtils#getSystemEnvIgnoreCase(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RuntimeUtils.getSystemEnvIgnoreCase(String)"})
  public void testGetSystemEnvIgnoreCase() {
    // Arrange, Act and Assert
    assertNull(RuntimeUtils.getSystemEnvIgnoreCase("Key"));
  }
}
