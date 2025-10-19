package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalCacheProgressMonitorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalCacheProgressMonitor#LocalCacheProgressMonitor(DBRProgressMonitor)}
   *   <li>{@link LocalCacheProgressMonitor#isForceCacheUsage()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalCacheProgressMonitor.<init>(DBRProgressMonitor)",
    "boolean LocalCacheProgressMonitor.isForceCacheUsage()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new LocalCacheProgressMonitor(new LoggingProgressMonitor()).isForceCacheUsage());
  }
}
