package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.Log;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LoggingProgressMonitorDiffblueTest {
  /**
   * Test {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   *
   * <p>Method under test: {@link LoggingProgressMonitor#LoggingProgressMonitor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LoggingProgressMonitor.<init>()"})
  public void testNewLoggingProgressMonitor() {
    // Arrange and Act
    LoggingProgressMonitor actualLoggingProgressMonitor = new LoggingProgressMonitor();

    // Assert
    assertNull(actualLoggingProgressMonitor.getActiveBlocks());
    assertFalse(actualLoggingProgressMonitor.isForceCacheUsage());
  }

  /**
   * Test {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}.
   *
   * <p>Method under test: {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LoggingProgressMonitor.<init>(Log)"})
  public void testNewLoggingProgressMonitor2() {
    // Arrange
    Class<Object> forClass = Object.class;

    // Act
    LoggingProgressMonitor actualLoggingProgressMonitor =
        new LoggingProgressMonitor(Log.getLog(forClass));

    // Assert
    assertNull(actualLoggingProgressMonitor.getActiveBlocks());
    assertFalse(actualLoggingProgressMonitor.isForceCacheUsage());
  }
}
