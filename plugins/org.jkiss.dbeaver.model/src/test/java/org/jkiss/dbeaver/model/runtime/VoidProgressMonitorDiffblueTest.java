package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VoidProgressMonitorDiffblueTest {
  /**
   * Test new {@link VoidProgressMonitor} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link VoidProgressMonitor}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VoidProgressMonitor.<init>()"})
  public void testNewVoidProgressMonitor() {
    // Arrange and Act
    VoidProgressMonitor actualVoidProgressMonitor = new VoidProgressMonitor();

    // Assert
    IProgressMonitor nestedMonitor = actualVoidProgressMonitor.getNestedMonitor();
    assertTrue(nestedMonitor instanceof NullProgressMonitor);
    assertNull(actualVoidProgressMonitor.getActiveBlocks());
    assertFalse(nestedMonitor.isCanceled());
    assertFalse(actualVoidProgressMonitor.isForceCacheUsage());
  }
}
