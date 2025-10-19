package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PrintStreamProgressMonitorDiffblueTest {
  /**
   * Test {@link PrintStreamProgressMonitor#PrintStreamProgressMonitor(DBRProgressMonitor,
   * PrintStream)}.
   *
   * <p>Method under test: {@link
   * PrintStreamProgressMonitor#PrintStreamProgressMonitor(DBRProgressMonitor, PrintStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrintStreamProgressMonitor.<init>(DBRProgressMonitor, PrintStream)"})
  public void testNewPrintStreamProgressMonitor() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    PrintStreamProgressMonitor actualPrintStreamProgressMonitor =
        new PrintStreamProgressMonitor(monitor, new PrintStream(new ByteArrayOutputStream()));

    // Assert
    DBRProgressMonitor dbrProgressMonitor = actualPrintStreamProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof LoggingProgressMonitor);
    assertNull(dbrProgressMonitor.getActiveBlocks());
    assertNull(actualPrintStreamProgressMonitor.getActiveBlocks());
    assertFalse(actualPrintStreamProgressMonitor.isForceCacheUsage());
    assertFalse(dbrProgressMonitor.isForceCacheUsage());
    assertSame(
        actualPrintStreamProgressMonitor.getNestedMonitor(), dbrProgressMonitor.getNestedMonitor());
  }
}
