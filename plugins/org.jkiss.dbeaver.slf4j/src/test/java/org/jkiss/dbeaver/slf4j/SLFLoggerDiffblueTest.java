package org.jkiss.dbeaver.slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Marker;

public class SLFLoggerDiffblueTest {
  /**
   * Test {@link SLFLogger#SLFLogger(String)}.
   *
   * <p>Method under test: {@link SLFLogger#SLFLogger(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SLFLogger.<init>(String)"})
  public void testNewSLFLogger() {
    // Arrange and Act
    SLFLogger actualSlfLogger = new SLFLogger("Name");

    // Assert
    assertEquals("Name", actualSlfLogger.getName());
    assertTrue(actualSlfLogger.isDebugEnabled());
    assertTrue(actualSlfLogger.isErrorEnabled());
    assertTrue(actualSlfLogger.isInfoEnabled());
    assertTrue(actualSlfLogger.isTraceEnabled());
    assertTrue(actualSlfLogger.isWarnEnabled());
  }

  /**
   * Test {@link SLFLogger#getName()}.
   *
   * <p>Method under test: {@link SLFLogger#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SLFLogger.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("Name", new SLFLogger("Name").getName());
  }

  /**
   * Test {@link SLFLogger#isTraceEnabled()}.
   *
   * <p>Method under test: {@link SLFLogger#isTraceEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isTraceEnabled()"})
  public void testIsTraceEnabled() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isTraceEnabled());
  }

  /**
   * Test {@link SLFLogger#isTraceEnabled(Marker)} with {@code Marker}.
   *
   * <p>Method under test: {@link SLFLogger#isTraceEnabled(Marker)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isTraceEnabled(Marker)"})
  public void testIsTraceEnabledWithMarker() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isTraceEnabled(mock(Marker.class)));
  }

  /**
   * Test {@link SLFLogger#isDebugEnabled()}.
   *
   * <p>Method under test: {@link SLFLogger#isDebugEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isDebugEnabled()"})
  public void testIsDebugEnabled() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isDebugEnabled());
  }

  /**
   * Test {@link SLFLogger#isDebugEnabled(Marker)} with {@code Marker}.
   *
   * <p>Method under test: {@link SLFLogger#isDebugEnabled(Marker)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isDebugEnabled(Marker)"})
  public void testIsDebugEnabledWithMarker() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isDebugEnabled(mock(Marker.class)));
  }

  /**
   * Test {@link SLFLogger#isInfoEnabled()}.
   *
   * <p>Method under test: {@link SLFLogger#isInfoEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isInfoEnabled()"})
  public void testIsInfoEnabled() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isInfoEnabled());
  }

  /**
   * Test {@link SLFLogger#isInfoEnabled(Marker)} with {@code Marker}.
   *
   * <p>Method under test: {@link SLFLogger#isInfoEnabled(Marker)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isInfoEnabled(Marker)"})
  public void testIsInfoEnabledWithMarker() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isInfoEnabled(mock(Marker.class)));
  }

  /**
   * Test {@link SLFLogger#isWarnEnabled()}.
   *
   * <p>Method under test: {@link SLFLogger#isWarnEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isWarnEnabled()"})
  public void testIsWarnEnabled() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isWarnEnabled());
  }

  /**
   * Test {@link SLFLogger#isWarnEnabled(Marker)} with {@code Marker}.
   *
   * <p>Method under test: {@link SLFLogger#isWarnEnabled(Marker)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isWarnEnabled(Marker)"})
  public void testIsWarnEnabledWithMarker() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isWarnEnabled(mock(Marker.class)));
  }

  /**
   * Test {@link SLFLogger#isErrorEnabled()}.
   *
   * <p>Method under test: {@link SLFLogger#isErrorEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isErrorEnabled()"})
  public void testIsErrorEnabled() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isErrorEnabled());
  }

  /**
   * Test {@link SLFLogger#isErrorEnabled(Marker)} with {@code Marker}.
   *
   * <p>Method under test: {@link SLFLogger#isErrorEnabled(Marker)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SLFLogger.isErrorEnabled(Marker)"})
  public void testIsErrorEnabledWithMarker() {
    // Arrange, Act and Assert
    assertTrue(new SLFLogger("Name").isErrorEnabled(mock(Marker.class)));
  }
}
