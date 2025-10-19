package org.slf4j.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.slf4j.SLFLogger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class StaticLoggerBinderDiffblueTest {
  /**
   * Test {@link StaticLoggerBinder#getLoggerFactory()}.
   *
   * <p>Method under test: {@link StaticLoggerBinder#getLoggerFactory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ILoggerFactory StaticLoggerBinder.getLoggerFactory()"})
  public void testGetLoggerFactory() {
    // Arrange and Act
    ILoggerFactory actualLoggerFactory = StaticLoggerBinder.getSingleton().getLoggerFactory();
    Logger actualLogger = actualLoggerFactory.getLogger("foo");

    // Assert
    assertTrue(actualLogger instanceof SLFLogger);
    assertTrue(actualLoggerFactory instanceof StaticLoggerBinder);
    assertEquals("foo", actualLogger.getName());
    assertEquals(
        "org.slf4j.impl.StaticLoggerBinder",
        ((StaticLoggerBinder) actualLoggerFactory).getLoggerFactoryClassStr());
    assertTrue(actualLogger.isDebugEnabled());
    assertTrue(actualLogger.isErrorEnabled());
    assertTrue(actualLogger.isInfoEnabled());
    assertTrue(actualLogger.isTraceEnabled());
    assertTrue(actualLogger.isWarnEnabled());
  }

  /**
   * Test {@link StaticLoggerBinder#getLoggerFactoryClassStr()}.
   *
   * <p>Method under test: {@link StaticLoggerBinder#getLoggerFactoryClassStr()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StaticLoggerBinder.getLoggerFactoryClassStr()"})
  public void testGetLoggerFactoryClassStr() {
    // Arrange, Act and Assert
    assertEquals(
        "org.slf4j.impl.StaticLoggerBinder",
        StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr());
  }

  /**
   * Test {@link StaticLoggerBinder#getLogger(String)}.
   *
   * <p>Method under test: {@link StaticLoggerBinder#getLogger(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Logger StaticLoggerBinder.getLogger(String)"})
  public void testGetLogger() {
    // Arrange and Act
    Logger actualLogger = StaticLoggerBinder.getSingleton().getLogger("Name");

    // Assert
    assertTrue(actualLogger instanceof SLFLogger);
    assertEquals("Name", actualLogger.getName());
    assertTrue(actualLogger.isDebugEnabled());
    assertTrue(actualLogger.isErrorEnabled());
    assertTrue(actualLogger.isInfoEnabled());
    assertTrue(actualLogger.isTraceEnabled());
    assertTrue(actualLogger.isWarnEnabled());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link StaticLoggerBinder}
   *   <li>{@link StaticLoggerBinder#getSingleton()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StaticLoggerBinder.<init>()",
    "StaticLoggerBinder StaticLoggerBinder.getSingleton()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "org.slf4j.impl.StaticLoggerBinder",
        new StaticLoggerBinder().getSingleton().getLoggerFactoryClassStr());
  }
}
