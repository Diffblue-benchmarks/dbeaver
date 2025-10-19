package org.jkiss.dbeaver.slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;

public class DefaultLoggerBinderDiffblueTest {
  /**
   * Test {@link DefaultLoggerBinder#getLogger(String)}.
   *
   * <p>Method under test: {@link DefaultLoggerBinder#getLogger(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Logger DefaultLoggerBinder.getLogger(String)"})
  public void testGetLogger() {
    // Arrange and Act
    Logger actualLogger = DefaultLoggerBinder.getSingleton().getLogger("Name");

    // Assert
    assertTrue(actualLogger instanceof SLFLogger);
    assertEquals("Name", actualLogger.getName());
    assertTrue(actualLogger.isDebugEnabled());
    assertTrue(actualLogger.isErrorEnabled());
    assertTrue(actualLogger.isInfoEnabled());
    assertTrue(actualLogger.isTraceEnabled());
    assertTrue(actualLogger.isWarnEnabled());
  }
}
