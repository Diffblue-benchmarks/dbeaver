package org.jkiss.dbeaver.debug.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.core.LaunchConfiguration;
import org.eclipse.debug.internal.core.LaunchConfigurationWorkingCopy;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.debug.DBGController;
import org.jkiss.dbeaver.debug.core.model.DatabaseDebugElement;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DebugUtilsDiffblueTest {
  /**
   * Test {@link DebugUtils#abort(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code IOException}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String, Throwable)"})
  public void testAbortWithMessageTh_thenReturnLocalizedMessageIsIOException() {
    // Arrange
    IOException th = new IOException("");

    // Act
    CoreException actualAbortResult = DebugUtils.abort(null, th);

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("IOException", actualAbortResult.getLocalizedMessage());
    assertEquals("IOException", actualAbortResult.getMessage());
    assertEquals("IOException", status.getMessage());
    assertSame(th, actualAbortResult.getCause());
    assertSame(th, status.getException());
  }

  /**
   * Test {@link DebugUtils#abort(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String, Throwable)"})
  public void testAbortWithMessageTh_thenReturnLocalizedMessageIsNotAllWhoWanderAreLost() {
    // Arrange and Act
    CoreException actualAbortResult =
        DebugUtils.abort("Not all who wander are lost", new Throwable());

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("Not all who wander are lost", actualAbortResult.getLocalizedMessage());
    assertEquals("Not all who wander are lost", actualAbortResult.getMessage());
    assertEquals("Not all who wander are lost", status.getMessage());
    assertSame(actualAbortResult.getCause(), status.getException());
  }

  /**
   * Test {@link DebugUtils#abort(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code org.jkiss.dbeaver.debug.core}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String, Throwable)"})
  public void testAbortWithMessageTh_thenReturnLocalizedMessageIsOrgJkissDbeaverDebugCore() {
    // Arrange
    IOException th = new IOException("org.jkiss.dbeaver.debug.core");

    // Act
    CoreException actualAbortResult = DebugUtils.abort(null, th);

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("org.jkiss.dbeaver.debug.core", actualAbortResult.getLocalizedMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", actualAbortResult.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", status.getMessage());
    assertSame(th, actualAbortResult.getCause());
    assertSame(th, status.getException());
  }

  /**
   * Test {@link DebugUtils#abort(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return LocalizedMessage is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String, Throwable)"})
  public void testAbortWithMessageTh_whenEmptyString_thenReturnLocalizedMessageIsEmptyString() {
    // Arrange and Act
    CoreException actualAbortResult = DebugUtils.abort("", null);

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("", actualAbortResult.getLocalizedMessage());
    assertEquals("", actualAbortResult.getMessage());
    assertEquals("", status.getMessage());
    assertNull(actualAbortResult.getCause());
    assertNull(status.getException());
  }

  /**
   * Test {@link DebugUtils#abort(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String, Throwable)"})
  public void testAbortWithMessageTh_whenNull_thenReturnLocalizedMessageIsEmptyString() {
    // Arrange and Act
    CoreException actualAbortResult = DebugUtils.abort(null, null);

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("", actualAbortResult.getLocalizedMessage());
    assertEquals("", actualAbortResult.getMessage());
    assertEquals("", status.getMessage());
    assertNull(actualAbortResult.getCause());
    assertNull(status.getException());
  }

  /**
   * Test {@link DebugUtils#abort(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return LocalizedMessage is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String, Throwable)"})
  public void testAbortWithMessageTh_whenThrowable_thenReturnLocalizedMessageIsThrowable() {
    // Arrange and Act
    CoreException actualAbortResult = DebugUtils.abort(null, new Throwable());

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("Throwable", actualAbortResult.getLocalizedMessage());
    assertEquals("Throwable", actualAbortResult.getMessage());
    assertEquals("Throwable", status.getMessage());
    assertSame(actualAbortResult.getCause(), status.getException());
  }

  /**
   * Test {@link DebugUtils#abort(String)} with {@code message}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String)"})
  public void testAbortWithMessage_thenReturnLocalizedMessageIsNotAllWhoWanderAreLost() {
    // Arrange and Act
    CoreException actualAbortResult = DebugUtils.abort("Not all who wander are lost");

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("Not all who wander are lost", actualAbortResult.getLocalizedMessage());
    assertEquals("Not all who wander are lost", actualAbortResult.getMessage());
    assertEquals("Not all who wander are lost", status.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", status.getPlugin());
    assertNull(actualAbortResult.getCause());
    assertNull(status.getException());
    assertEquals(0, status.getCode());
    assertEquals(0, actualAbortResult.getSuppressed().length);
    assertEquals(0, status.getChildren().length);
    assertEquals(4, status.getSeverity());
    assertFalse(status.isMultiStatus());
    assertFalse(status.isOK());
  }

  /**
   * Test {@link DebugUtils#abort(String)} with {@code message}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return LocalizedMessage is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String)"})
  public void testAbortWithMessage_whenEmptyString_thenReturnLocalizedMessageIsEmptyString() {
    // Arrange and Act
    CoreException actualAbortResult = DebugUtils.abort("");

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("", actualAbortResult.getLocalizedMessage());
    assertEquals("", actualAbortResult.getMessage());
    assertEquals("", status.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", status.getPlugin());
    assertNull(actualAbortResult.getCause());
    assertNull(status.getException());
    assertEquals(0, status.getCode());
    assertEquals(0, actualAbortResult.getSuppressed().length);
    assertEquals(0, status.getChildren().length);
    assertEquals(4, status.getSeverity());
    assertFalse(status.isMultiStatus());
    assertFalse(status.isOK());
  }

  /**
   * Test {@link DebugUtils#abort(String)} with {@code message}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#abort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CoreException DebugUtils.abort(String)"})
  public void testAbortWithMessage_whenNull_thenReturnLocalizedMessageIsEmptyString() {
    // Arrange and Act
    CoreException actualAbortResult = DebugUtils.abort(null);

    // Assert
    IStatus status = actualAbortResult.getStatus();
    assertTrue(status instanceof Status);
    assertEquals("", actualAbortResult.getLocalizedMessage());
    assertEquals("", actualAbortResult.getMessage());
    assertEquals("", status.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", status.getPlugin());
    assertNull(actualAbortResult.getCause());
    assertNull(status.getException());
    assertEquals(0, status.getCode());
    assertEquals(0, actualAbortResult.getSuppressed().length);
    assertEquals(0, status.getChildren().length);
    assertEquals(4, status.getSeverity());
    assertFalse(status.isMultiStatus());
    assertFalse(status.isOK());
  }

  /**
   * Test {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}.
   *
   * <ul>
   *   <li>Given abort {@code Not all who wander are lost} and {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DebugUtils.canLaunch(ILaunchConfiguration, String)"})
  public void testCanLaunch_givenAbortNotAllWhoWanderAreLostAndThrowable() throws CoreException {
    // Arrange
    LaunchConfiguration configuration = mock(LaunchConfiguration.class);
    CoreException abortResult = DebugUtils.abort("Not all who wander are lost", new Throwable());
    when(configuration.supportsMode(Mockito.<String>any())).thenThrow(abortResult);
    when(configuration.exists()).thenReturn(true);

    // Act
    boolean actualCanLaunchResult = DebugUtils.canLaunch(configuration, "Mode");

    // Assert
    verify(configuration).exists();
    verify(configuration).supportsMode("Mode");
    assertFalse(actualCanLaunchResult);
  }

  /**
   * Test {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link LaunchConfiguration} {@link LaunchConfiguration#exists()} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DebugUtils.canLaunch(ILaunchConfiguration, String)"})
  public void testCanLaunch_givenFalse_whenLaunchConfigurationExistsReturnFalse() {
    // Arrange
    LaunchConfiguration configuration = mock(LaunchConfiguration.class);
    when(configuration.exists()).thenReturn(false);

    // Act
    boolean actualCanLaunchResult = DebugUtils.canLaunch(configuration, "Mode");

    // Assert
    verify(configuration).exists();
    assertFalse(actualCanLaunchResult);
  }

  /**
   * Test {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}.
   *
   * <ul>
   *   <li>When {@link LaunchConfiguration} {@link LaunchConfiguration#supportsMode(String)} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DebugUtils.canLaunch(ILaunchConfiguration, String)"})
  public void testCanLaunch_whenLaunchConfigurationSupportsModeReturnTrue_thenReturnTrue()
      throws CoreException {
    // Arrange
    LaunchConfiguration configuration = mock(LaunchConfiguration.class);
    when(configuration.supportsMode(Mockito.<String>any())).thenReturn(true);
    when(configuration.exists()).thenReturn(true);

    // Act
    boolean actualCanLaunchResult = DebugUtils.canLaunch(configuration, "Mode");

    // Assert
    verify(configuration).exists();
    verify(configuration).supportsMode("Mode");
    assertTrue(actualCanLaunchResult);
  }

  /**
   * Test {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#canLaunch(ILaunchConfiguration, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DebugUtils.canLaunch(ILaunchConfiguration, String)"})
  public void testCanLaunch_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DebugUtils.canLaunch(null, "Mode"));
  }

  /**
   * Test {@link DebugUtils#extractLaunchable(Object[])}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#extractLaunchable(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DebugUtils.extractLaunchable(Object[])"})
  public void testExtractLaunchable_thenReturnSizeIsOne() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    List<DBSObject> actualExtractLaunchableResult =
        DebugUtils.extractLaunchable(new Object[] {dbsDocumentConstraint});

    // Assert
    assertEquals(1, actualExtractLaunchableResult.size());
    assertSame(dbsDocumentConstraint, actualExtractLaunchableResult.get(0));
  }

  /**
   * Test {@link DebugUtils#extractLaunchable(Object[])}.
   *
   * <ul>
   *   <li>When array of {@link Object} with {@link
   *       DatabaseDebugElement#DatabaseDebugElement(IDatabaseDebugTarget)} with target is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#extractLaunchable(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DebugUtils.extractLaunchable(Object[])"})
  public void testExtractLaunchable_whenArrayOfObjectWithDatabaseDebugElementWithTargetIsNull() {
    // Arrange and Act
    List<DBSObject> actualExtractLaunchableResult =
        DebugUtils.extractLaunchable(new Object[] {new DatabaseDebugElement(null)});

    // Assert
    assertTrue(actualExtractLaunchableResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#extractLaunchable(Object[])}.
   *
   * <ul>
   *   <li>When array of {@link Object} with {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#extractLaunchable(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DebugUtils.extractLaunchable(Object[])"})
  public void testExtractLaunchable_whenArrayOfObjectWithNull_thenReturnEmpty() {
    // Arrange and Act
    List<DBSObject> actualExtractLaunchableResult =
        DebugUtils.extractLaunchable(new Object[] {null});

    // Assert
    assertTrue(actualExtractLaunchableResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#extractLaunchable(Object[])}.
   *
   * <ul>
   *   <li>When array of {@link Object} with {@code Scope} and {@code Scope}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#extractLaunchable(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DebugUtils.extractLaunchable(Object[])"})
  public void testExtractLaunchable_whenArrayOfObjectWithScopeAndScope_thenReturnEmpty() {
    // Arrange and Act
    List<DBSObject> actualExtractLaunchableResult =
        DebugUtils.extractLaunchable(new Object[] {"Scope", "Scope"});

    // Assert
    assertTrue(actualExtractLaunchableResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#extractLaunchable(Object[])}.
   *
   * <ul>
   *   <li>When array of {@link Object} with {@code Scope}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#extractLaunchable(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DebugUtils.extractLaunchable(Object[])"})
  public void testExtractLaunchable_whenArrayOfObjectWithScope_thenReturnEmpty() {
    // Arrange and Act
    List<DBSObject> actualExtractLaunchableResult =
        DebugUtils.extractLaunchable(new Object[] {"Scope"});

    // Assert
    assertTrue(actualExtractLaunchableResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#extractLaunchable(Object[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#extractLaunchable(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DebugUtils.extractLaunchable(Object[])"})
  public void testExtractLaunchable_whenNull_thenReturnEmpty() {
    // Arrange and Act
    List<DBSObject> actualExtractLaunchableResult = DebugUtils.extractLaunchable(null);

    // Assert
    assertTrue(actualExtractLaunchableResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>Then return Message is {@code IOException}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String, Throwable)"})
  public void testNewErrorStatusWithMessageTh_thenReturnMessageIsIOException() {
    // Arrange
    IOException th = new IOException("");

    // Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus(null, th);

    // Assert
    assertEquals("IOException", actualNewErrorStatusResult.getMessage());
    assertSame(th, actualNewErrorStatusResult.getException());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>Then return Message is {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String, Throwable)"})
  public void testNewErrorStatusWithMessageTh_thenReturnMessageIsNotAllWhoWanderAreLost() {
    // Arrange
    Throwable th = new Throwable();

    // Act
    Status actualNewErrorStatusResult =
        DebugUtils.newErrorStatus("Not all who wander are lost", th);

    // Assert
    assertEquals("Not all who wander are lost", actualNewErrorStatusResult.getMessage());
    assertSame(th, actualNewErrorStatusResult.getException());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>Then return Message is {@code org.jkiss.dbeaver.debug.core}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String, Throwable)"})
  public void testNewErrorStatusWithMessageTh_thenReturnMessageIsOrgJkissDbeaverDebugCore() {
    // Arrange
    IOException th = new IOException("org.jkiss.dbeaver.debug.core");

    // Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus(null, th);

    // Assert
    assertEquals("org.jkiss.dbeaver.debug.core", actualNewErrorStatusResult.getMessage());
    assertSame(th, actualNewErrorStatusResult.getException());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String, Throwable)"})
  public void testNewErrorStatusWithMessageTh_whenEmptyString_thenReturnMessageIsEmptyString() {
    // Arrange and Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus("", null);

    // Assert
    assertEquals("", actualNewErrorStatusResult.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", actualNewErrorStatusResult.getPlugin());
    assertNull(actualNewErrorStatusResult.getException());
    assertEquals(0, actualNewErrorStatusResult.getCode());
    assertEquals(0, actualNewErrorStatusResult.getChildren().length);
    assertEquals(4, actualNewErrorStatusResult.getSeverity());
    assertFalse(actualNewErrorStatusResult.isMultiStatus());
    assertFalse(actualNewErrorStatusResult.isOK());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String, Throwable)"})
  public void testNewErrorStatusWithMessageTh_whenNull_thenReturnMessageIsEmptyString() {
    // Arrange and Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus(null, null);

    // Assert
    assertEquals("", actualNewErrorStatusResult.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", actualNewErrorStatusResult.getPlugin());
    assertNull(actualNewErrorStatusResult.getException());
    assertEquals(0, actualNewErrorStatusResult.getCode());
    assertEquals(0, actualNewErrorStatusResult.getChildren().length);
    assertEquals(4, actualNewErrorStatusResult.getSeverity());
    assertFalse(actualNewErrorStatusResult.isMultiStatus());
    assertFalse(actualNewErrorStatusResult.isOK());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String, Throwable)} with {@code message}, {@code th}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String, Throwable)"})
  public void testNewErrorStatusWithMessageTh_whenThrowable_thenReturnMessageIsThrowable() {
    // Arrange
    Throwable th = new Throwable();

    // Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus(null, th);

    // Assert
    assertEquals("Throwable", actualNewErrorStatusResult.getMessage());
    assertSame(th, actualNewErrorStatusResult.getException());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String)} with {@code message}.
   *
   * <ul>
   *   <li>Then return Message is {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String)"})
  public void testNewErrorStatusWithMessage_thenReturnMessageIsNotAllWhoWanderAreLost() {
    // Arrange and Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", actualNewErrorStatusResult.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", actualNewErrorStatusResult.getPlugin());
    assertNull(actualNewErrorStatusResult.getException());
    assertEquals(0, actualNewErrorStatusResult.getCode());
    assertEquals(0, actualNewErrorStatusResult.getChildren().length);
    assertEquals(4, actualNewErrorStatusResult.getSeverity());
    assertFalse(actualNewErrorStatusResult.isMultiStatus());
    assertFalse(actualNewErrorStatusResult.isOK());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String)} with {@code message}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String)"})
  public void testNewErrorStatusWithMessage_whenEmptyString_thenReturnMessageIsEmptyString() {
    // Arrange and Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus("");

    // Assert
    assertEquals("", actualNewErrorStatusResult.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", actualNewErrorStatusResult.getPlugin());
    assertNull(actualNewErrorStatusResult.getException());
    assertEquals(0, actualNewErrorStatusResult.getCode());
    assertEquals(0, actualNewErrorStatusResult.getChildren().length);
    assertEquals(4, actualNewErrorStatusResult.getSeverity());
    assertFalse(actualNewErrorStatusResult.isMultiStatus());
    assertFalse(actualNewErrorStatusResult.isOK());
  }

  /**
   * Test {@link DebugUtils#newErrorStatus(String)} with {@code message}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#newErrorStatus(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Status DebugUtils.newErrorStatus(String)"})
  public void testNewErrorStatusWithMessage_whenNull_thenReturnMessageIsEmptyString() {
    // Arrange and Act
    Status actualNewErrorStatusResult = DebugUtils.newErrorStatus(null);

    // Assert
    assertEquals("", actualNewErrorStatusResult.getMessage());
    assertEquals("org.jkiss.dbeaver.debug.core", actualNewErrorStatusResult.getPlugin());
    assertNull(actualNewErrorStatusResult.getException());
    assertEquals(0, actualNewErrorStatusResult.getCode());
    assertEquals(0, actualNewErrorStatusResult.getChildren().length);
    assertEquals(4, actualNewErrorStatusResult.getSeverity());
    assertFalse(actualNewErrorStatusResult.isMultiStatus());
    assertFalse(actualNewErrorStatusResult.isOK());
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseObject(DBPDataSourceContainer, Map, Object,
   * DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseObject(DBPDataSourceContainer, Map,
   * Object, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DebugUtils.resolveDatabaseObject(DBPDataSourceContainer, Map, Object, DBRProgressMonitor)"
  })
  public void testResolveDatabaseObject_whenDBPDataSourceContainer() throws DBException {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    HashMap<String, Object> context = new HashMap<>();

    // Act
    DBSObject actualResolveDatabaseObjectResult =
        DebugUtils.resolveDatabaseObject(
            container, context, "Identifier", new LoggingProgressMonitor());

    // Assert
    assertNull(actualResolveDatabaseObjectResult);
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseObject(DBPDataSourceContainer, Map, Object,
   * DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseObject(DBPDataSourceContainer, Map,
   * Object, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DebugUtils.resolveDatabaseObject(DBPDataSourceContainer, Map, Object, DBRProgressMonitor)"
  })
  public void testResolveDatabaseObject_whenNull() throws DBException {
    // Arrange
    HashMap<String, Object> context = new HashMap<>();

    // Act
    DBSObject actualResolveDatabaseObjectResult =
        DebugUtils.resolveDatabaseObject(null, context, "Identifier", new LoggingProgressMonitor());

    // Assert
    assertNull(actualResolveDatabaseObjectResult);
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseContext(DBSObject)}.
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseContext(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DebugUtils.resolveDatabaseContext(DBSObject)"})
  public void testResolveDatabaseContext() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    Map<String, Object> actualResolveDatabaseContextResult =
        DebugUtils.resolveDatabaseContext(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
    assertTrue(actualResolveDatabaseContextResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseContext(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseContext(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DebugUtils.resolveDatabaseContext(DBSObject)"})
  public void testResolveDatabaseContext_given42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    Map<String, Object> actualResolveDatabaseContextResult =
        DebugUtils.resolveDatabaseContext(new DBVEntityForeignKey(entity));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(actualResolveDatabaseContextResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseContext(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseContext(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DebugUtils.resolveDatabaseContext(DBSObject)"})
  public void testResolveDatabaseContext_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    Map<String, Object> actualResolveDatabaseContextResult =
        DebugUtils.resolveDatabaseContext(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
    assertTrue(actualResolveDatabaseContextResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseContext(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseContext(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DebugUtils.resolveDatabaseContext(DBSObject)"})
  public void testResolveDatabaseContext_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    Map<String, Object> actualResolveDatabaseContextResult =
        DebugUtils.resolveDatabaseContext(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
    assertTrue(actualResolveDatabaseContextResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#resolveDatabaseContext(DBSObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#resolveDatabaseContext(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DebugUtils.resolveDatabaseContext(DBSObject)"})
  public void testResolveDatabaseContext_whenNull_thenReturnEmpty() {
    // Arrange and Act
    Map<String, Object> actualResolveDatabaseContextResult =
        DebugUtils.resolveDatabaseContext(null);

    // Assert
    assertTrue(actualResolveDatabaseContextResult.isEmpty());
  }

  /**
   * Test {@link DebugUtils#getSourceName(Object)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#getSourceName(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DebugUtils.getSourceName(Object)"})
  public void testGetSourceName_whenObject_thenReturnObject() throws CoreException {
    // Arrange, Act and Assert
    assertEquals("Object", DebugUtils.getSourceName("Object"));
  }

  /**
   * Test {@link DebugUtils#getSourceName(Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#getSourceName(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DebugUtils.getSourceName(Object)"})
  public void testGetSourceName_whenOne_thenReturnNull() throws CoreException {
    // Arrange, Act and Assert
    assertNull(DebugUtils.getSourceName(1));
  }

  /**
   * Test {@link DebugUtils#toBreakpointDescriptor(Map)}.
   *
   * <p>Method under test: {@link DebugUtils#toBreakpointDescriptor(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DebugUtils.toBreakpointDescriptor(Map)"})
  public void testToBreakpointDescriptor() {
    // Arrange and Act
    Map<String, Object> actualToBreakpointDescriptorResult =
        DebugUtils.toBreakpointDescriptor(new HashMap<>());

    // Assert
    assertEquals(1, actualToBreakpointDescriptorResult.size());
    assertNull(actualToBreakpointDescriptorResult.get("lineNumber"));
  }

  /**
   * Test {@link DebugUtils#findDatabaseObject(DBGController, Object, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#findDatabaseObject(DBGController, Object,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DebugUtils.findDatabaseObject(DBGController, Object, DBRProgressMonitor)"
  })
  public void testFindDatabaseObject_givenDBPDataSourceContainer() throws DBException {
    // Arrange
    DBGController controller = mock(DBGController.class);
    when(controller.getDebugConfiguration()).thenReturn(new HashMap<>());
    when(controller.getDataSourceContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    // Act
    DBSObject actualFindDatabaseObjectResult =
        DebugUtils.findDatabaseObject(controller, "Identifier", new LoggingProgressMonitor());

    // Assert
    verify(controller).getDataSourceContainer();
    verify(controller).getDebugConfiguration();
    assertNull(actualFindDatabaseObjectResult);
  }

  /**
   * Test {@link DebugUtils#findDatabaseObject(DBGController, Object, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DebugUtils#findDatabaseObject(DBGController, Object,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject DebugUtils.findDatabaseObject(DBGController, Object, DBRProgressMonitor)"
  })
  public void testFindDatabaseObject_givenNull() throws DBException {
    // Arrange
    DBGController controller = mock(DBGController.class);
    when(controller.getDataSourceContainer()).thenReturn(null);
    when(controller.getDebugConfiguration()).thenReturn(new HashMap<>());

    // Act
    DBSObject actualFindDatabaseObjectResult =
        DebugUtils.findDatabaseObject(controller, "Identifier", new LoggingProgressMonitor());

    // Assert
    verify(controller).getDataSourceContainer();
    verify(controller).getDebugConfiguration();
    assertNull(actualFindDatabaseObjectResult);
  }

  /**
   * Test {@link DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}.
   *
   * <ul>
   *   <li>Given {@link BiFunction} {@link BiFunction#apply(Object, Object)} return one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DebugUtils.putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)"
  })
  public void testPutContextInConfiguration_givenBiFunctionApplyReturnOne() {
    // Arrange
    LaunchConfigurationWorkingCopy configuration = mock(LaunchConfigurationWorkingCopy.class);
    doNothing().when(configuration).setAttribute(Mockito.<String>any(), Mockito.<Object>any());

    BiFunction<String, Object, Object> biFunction = mock(BiFunction.class);
    when(biFunction.apply(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(1);

    HashMap<String, Object> attrs = new HashMap<>();
    attrs.put("", "42");
    attrs.replaceAll(biFunction);

    // Act
    DebugUtils.putContextInConfiguration(configuration, attrs);

    // Assert
    verify(biFunction).apply(eq(""), isA(Object.class));
    verify(configuration).setAttribute(eq(""), isA(Object.class));
  }

  /**
   * Test {@link DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}.
   *
   * <ul>
   *   <li>Given {@link BiFunction} {@link BiFunction#apply(Object, Object)} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DebugUtils.putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)"
  })
  public void testPutContextInConfiguration_givenBiFunctionApplyReturnTrue() {
    // Arrange
    LaunchConfigurationWorkingCopy configuration = mock(LaunchConfigurationWorkingCopy.class);
    doNothing().when(configuration).setAttribute(Mockito.<String>any(), Mockito.<Object>any());

    BiFunction<String, Object, Object> biFunction = mock(BiFunction.class);
    when(biFunction.apply(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(true);

    HashMap<String, Object> attrs = new HashMap<>();
    attrs.put("", "42");
    attrs.replaceAll(biFunction);

    // Act
    DebugUtils.putContextInConfiguration(configuration, attrs);

    // Assert
    verify(biFunction).apply(eq(""), isA(Object.class));
    verify(configuration).setAttribute(eq(""), isA(Object.class));
  }

  /**
   * Test {@link DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}.
   *
   * <ul>
   *   <li>Given {@code Remove Attribute}.
   *   <li>Then calls {@link LaunchConfigurationWorkingCopy#removeAttribute(String)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DebugUtils.putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)"
  })
  public void testPutContextInConfiguration_givenRemoveAttribute_thenCallsRemoveAttribute() {
    // Arrange
    LaunchConfigurationWorkingCopy configuration = mock(LaunchConfigurationWorkingCopy.class);
    when(configuration.removeAttribute(Mockito.<String>any())).thenReturn("Remove Attribute");

    BiFunction<String, Object, Object> biFunction = mock(BiFunction.class);
    when(biFunction.apply(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(null);

    HashMap<String, Object> attrs = new HashMap<>();
    attrs.put("", "42");
    attrs.replaceAll(biFunction);

    // Act
    DebugUtils.putContextInConfiguration(configuration, attrs);

    // Assert
    verify(biFunction).apply(eq(""), isA(Object.class));
    verify(configuration).removeAttribute("");
  }

  /**
   * Test {@link DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link LaunchConfigurationWorkingCopy#setAttribute(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DebugUtils#putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DebugUtils.putContextInConfiguration(ILaunchConfigurationWorkingCopy, Map)"
  })
  public void testPutContextInConfiguration_thenCallsSetAttribute() {
    // Arrange
    LaunchConfigurationWorkingCopy configuration = mock(LaunchConfigurationWorkingCopy.class);
    doNothing().when(configuration).setAttribute(Mockito.<String>any(), Mockito.<String>any());

    BiFunction<String, Object, Object> biFunction = mock(BiFunction.class);
    when(biFunction.apply(Mockito.<String>any(), Mockito.<Object>any())).thenReturn("Apply");

    HashMap<String, Object> attrs = new HashMap<>();
    attrs.put("", "42");
    attrs.replaceAll(biFunction);

    // Act
    DebugUtils.putContextInConfiguration(configuration, attrs);

    // Assert
    verify(biFunction).apply(eq(""), isA(Object.class));
    verify(configuration).setAttribute("", "Apply");
  }
}
