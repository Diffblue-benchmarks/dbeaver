package org.jkiss.dbeaver.ext.altibase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.altibase.AltibaseConstants.ExplainPlan;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AltibaseConstantsDiffblueTest {
  /**
   * Test ExplainPlan {@link ExplainPlan#getByIndex(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code ON}.
   * </ul>
   *
   * <p>Method under test: {@link ExplainPlan#getByIndex(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExplainPlan ExplainPlan.getByIndex(int)"})
  public void testExplainPlanGetByIndex_whenOne_thenReturnOn()
      throws ArrayIndexOutOfBoundsException {
    // Arrange, Act and Assert
    assertEquals(ExplainPlan.ON, ExplainPlan.getByIndex(1));
  }

  /**
   * Test ExplainPlan getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExplainPlan#getArgValue()}
   *   <li>{@link ExplainPlan#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte ExplainPlan.getArgValue()", "String ExplainPlan.getTitle()"})
  public void testExplainPlanGettersAndSetters() {
    // Arrange
    ExplainPlan valueOfResult = ExplainPlan.valueOf("ONLY");

    // Act
    byte actualArgValue = valueOfResult.getArgValue();

    // Assert
    assertEquals("EXPLAIN PLAN = ONLY", valueOfResult.getTitle());
    assertEquals((byte) 2, actualArgValue);
  }

  /**
   * Test {@link AltibaseConstants#isSysUser(String)}.
   *
   * <ul>
   *   <li>When {@code janedoe}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseConstants#isSysUser(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AltibaseConstants.isSysUser(String)"})
  public void testIsSysUser_whenJanedoe_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AltibaseConstants.isSysUser("janedoe"));
  }

  /**
   * Test {@link AltibaseConstants#isSysUser(String)}.
   *
   * <ul>
   *   <li>When {@link AltibaseConstants#USER_SYS}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseConstants#isSysUser(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AltibaseConstants.isSysUser(String)"})
  public void testIsSysUser_whenUser_sys_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(AltibaseConstants.isSysUser(AltibaseConstants.USER_SYS));
  }

  /**
   * Test {@link AltibaseConstants#isSysUser(String)}.
   *
   * <ul>
   *   <li>When {@link AltibaseConstants#USER_SYSTEM_}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AltibaseConstants#isSysUser(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AltibaseConstants.isSysUser(String)"})
  public void testIsSysUser_whenUser_system__thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(AltibaseConstants.isSysUser(AltibaseConstants.USER_SYSTEM_));
  }
}
