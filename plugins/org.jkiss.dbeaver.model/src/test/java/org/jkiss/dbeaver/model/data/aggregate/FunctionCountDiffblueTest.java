package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FunctionCountDiffblueTest {
  /**
   * Test {@link FunctionCount#accumulate(Object, boolean)}.
   *
   * <p>Method under test: {@link FunctionCount#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCount.accumulate(Object, boolean)"})
  public void testAccumulate() {
    // Arrange
    FunctionCount functionCount = new FunctionCount();

    // Act
    boolean actualAccumulateResult = functionCount.accumulate(DBPEvent.RENAME, true);

    // Assert
    assertEquals(1, ((Integer) functionCount.getResult(3)).intValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCount#getResult(int)}.
   *
   * <p>Method under test: {@link FunctionCount#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionCount.getResult(int)"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertEquals(0, ((Integer) new FunctionCount().getResult(3)).intValue());
  }
}
