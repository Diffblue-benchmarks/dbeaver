package org.jkiss.dbeaver.runtime;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OperationSystemStateDiffblueTest {
  /**
   * Test {@link OperationSystemState#isInSleepMode()}.
   *
   * <p>Method under test: {@link OperationSystemState#isInSleepMode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OperationSystemState.isInSleepMode()"})
  public void testIsInSleepMode() {
    // Arrange, Act and Assert
    assertFalse(OperationSystemState.isInSleepMode());
  }
}
