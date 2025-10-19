package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBRProgressMonitorDiffblueTest {
  /**
   * Test {@link DBRProgressMonitor#isForceCacheUsage()}.
   *
   * <p>Method under test: {@link DBRProgressMonitor#isForceCacheUsage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRProgressMonitor.isForceCacheUsage()"})
  public void testIsForceCacheUsage() {
    // Arrange, Act and Assert
    assertFalse(new LoggingProgressMonitor().isForceCacheUsage());
  }
}
