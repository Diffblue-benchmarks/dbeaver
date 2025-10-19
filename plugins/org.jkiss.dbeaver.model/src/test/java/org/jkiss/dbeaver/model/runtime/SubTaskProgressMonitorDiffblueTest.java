package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SubTaskProgressMonitorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)}
   *   <li>{@link SubTaskProgressMonitor#done()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SubTaskProgressMonitor.<init>(DBRProgressMonitor)",
    "void SubTaskProgressMonitor.done()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SubTaskProgressMonitor actualSubTaskProgressMonitor =
        new SubTaskProgressMonitor(new LoggingProgressMonitor());
    actualSubTaskProgressMonitor.done();

    // Assert
    DBRProgressMonitor dbrProgressMonitor = actualSubTaskProgressMonitor.original;
    assertTrue(dbrProgressMonitor instanceof LoggingProgressMonitor);
    assertNull(dbrProgressMonitor.getActiveBlocks());
    assertNull(actualSubTaskProgressMonitor.getActiveBlocks());
    assertFalse(actualSubTaskProgressMonitor.isForceCacheUsage());
    assertFalse(dbrProgressMonitor.isForceCacheUsage());
    assertSame(
        actualSubTaskProgressMonitor.getNestedMonitor(), dbrProgressMonitor.getNestedMonitor());
  }
}
