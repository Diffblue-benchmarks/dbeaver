package org.jkiss.dbeaver.ui.controls.txn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ui.controls.txn.TransactionMonitorToolbar.ToolbarContribution;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TransactionMonitorToolbarDiffblueTest {
  /**
   * Test ToolbarContribution new {@link ToolbarContribution} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ToolbarContribution}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ToolbarContribution.<init>()"})
  public void testToolbarContributionNewToolbarContribution() {
    // Arrange and Act
    ToolbarContribution actualToolbarContribution = new ToolbarContribution();

    // Assert
    assertEquals("dbeaver-transactions", actualToolbarContribution.getId());
    assertNull(actualToolbarContribution.getParent());
    assertNull(actualToolbarContribution.getWorkbenchWindow());
    assertEquals(0, actualToolbarContribution.getCurSide());
    assertTrue(actualToolbarContribution.isVisible());
  }
}
