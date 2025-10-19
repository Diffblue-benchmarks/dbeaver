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

public class BaseProgressMonitorDiffblueTest {
  /**
   * Test {@link BaseProgressMonitor#getNestedMonitor()}.
   *
   * <p>Method under test: {@link BaseProgressMonitor#getNestedMonitor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IProgressMonitor BaseProgressMonitor.getNestedMonitor()"})
  public void testGetNestedMonitor() {
    // Arrange and Act
    IProgressMonitor actualNestedMonitor = new VoidProgressMonitor().getNestedMonitor();

    // Assert
    assertTrue(actualNestedMonitor instanceof NullProgressMonitor);
    assertFalse(actualNestedMonitor.isCanceled());
  }

  /**
   * Test {@link BaseProgressMonitor#getActiveBlocks()}.
   *
   * <p>Method under test: {@link BaseProgressMonitor#getActiveBlocks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List BaseProgressMonitor.getActiveBlocks()"})
  public void testGetActiveBlocks() {
    // Arrange, Act and Assert
    assertNull(new VoidProgressMonitor().getActiveBlocks());
  }
}
