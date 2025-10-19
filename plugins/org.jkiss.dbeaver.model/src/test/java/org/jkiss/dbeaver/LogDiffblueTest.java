package org.jkiss.dbeaver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.jkiss.dbeaver.Log.Context;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LogDiffblueTest {
  /**
   * Test {@link Log#buildContext(String)}.
   *
   * <p>Method under test: {@link Log#buildContext(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Context Log.buildContext(String)"})
  public void testBuildContext() {
    // Arrange and Act
    Context actualBuildContextResult = Log.buildContext("Name");

    // Assert
    assertEquals("Name", actualBuildContextResult.getContextName());
    assertTrue(actualBuildContextResult.getContextParameters().isEmpty());
  }

  /**
   * Test Context getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Context#getContextName()}
   *   <li>{@link Context#getContextParameters()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String Context.getContextName()",
    "java.util.Map Context.getContextParameters()"
  })
  public void testContextGettersAndSetters() {
    // Arrange
    Context buildContextResult = Log.buildContext("Name");

    // Act
    String actualContextName = buildContextResult.getContextName();

    // Assert
    assertEquals("Name", actualContextName);
    assertTrue(buildContextResult.getContextParameters().isEmpty());
  }

  /**
   * Test Context {@link Context#withParameter(String, Object)}.
   *
   * <p>Method under test: {@link Context#withParameter(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Context Context.withParameter(String, Object)"})
  public void testContextWithParameter() {
    // Arrange
    Context buildContextResult = Log.buildContext("Name");

    // Act
    Context actualWithParameterResult = buildContextResult.withParameter("Name", DBPEvent.RENAME);

    // Assert
    assertSame(buildContextResult, actualWithParameterResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Log#isQuietMode()}
   *   <li>{@link Log#setDefaultDebugStream(PrintStream)}
   *   <li>{@link Log#setLogHandler(LogHandler)}
   *   <li>{@link Log#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String Log.getName()",
    "boolean Log.isQuietMode()",
    "void Log.setDefaultDebugStream(PrintStream)",
    "void Log.setLogHandler(LogHandler)"
  })
  public void testGettersAndSetters() {
    // Arrange
    Class<Object> forClass = Object.class;
    Log log = Log.getLog(forClass);

    // Act
    boolean actualIsQuietModeResult = log.isQuietMode();
    log.setDefaultDebugStream(new PrintStream(new ByteArrayOutputStream()));
    log.setLogHandler(mock(LogHandler.class));

    // Assert
    assertEquals("java.lang.Object", log.getName());
    assertFalse(actualIsQuietModeResult);
  }
}
