package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WriterProgressMonitorDiffblueTest {
  /**
   * Test {@link WriterProgressMonitor#WriterProgressMonitor(DBRProgressMonitor, Writer)}.
   *
   * <ul>
   *   <li>Then {@link ProxyProgressMonitor#original} return {@link LoggingProgressMonitor}.
   * </ul>
   *
   * <p>Method under test: {@link WriterProgressMonitor#WriterProgressMonitor(DBRProgressMonitor,
   * Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WriterProgressMonitor.<init>(DBRProgressMonitor, Writer)"})
  public void testNewWriterProgressMonitor_thenOriginalReturnLoggingProgressMonitor() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    WriterProgressMonitor actualWriterProgressMonitor =
        new WriterProgressMonitor(monitor, new StringWriter());

    // Assert
    DBRProgressMonitor dbrProgressMonitor = actualWriterProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof LoggingProgressMonitor);
    assertNull(dbrProgressMonitor.getActiveBlocks());
    assertNull(actualWriterProgressMonitor.getActiveBlocks());
    assertFalse(actualWriterProgressMonitor.isForceCacheUsage());
    assertFalse(dbrProgressMonitor.isForceCacheUsage());
  }
}
