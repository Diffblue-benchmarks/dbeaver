package org.jkiss.dbeaver.ext.altibase.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.altibase.model.AltibaseTablespace.State;
import org.jkiss.dbeaver.ext.altibase.model.AltibaseTablespace.TbsType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AltibaseTablespaceDiffblueTest {
  /**
   * Test State {@link State#getStateByIdx(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code OFFLINE}.
   * </ul>
   *
   * <p>Method under test: {@link State#getStateByIdx(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"State State.getStateByIdx(int)"})
  public void testStateGetStateByIdx_whenOne_thenReturnOffline() {
    // Arrange, Act and Assert
    assertEquals(State.OFFLINE, State.getStateByIdx(1));
  }

  /**
   * Test State {@link State#getStateByIdx(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link State#getStateByIdx(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"State State.getStateByIdx(int)"})
  public void testStateGetStateByIdx_whenThree_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals(State.UNKNOWN, State.getStateByIdx(3));
  }

  /**
   * Test State {@link State#getStateByIdx(int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code ONLINE}.
   * </ul>
   *
   * <p>Method under test: {@link State#getStateByIdx(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"State State.getStateByIdx(int)"})
  public void testStateGetStateByIdx_whenTwo_thenReturnOnline() {
    // Arrange, Act and Assert
    assertEquals(State.ONLINE, State.getStateByIdx(2));
  }

  /**
   * Test TbsType {@link TbsType#getTbsTypeByIdx(int)}.
   *
   * <ul>
   *   <li>When nine.
   *   <li>Then return {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link TbsType#getTbsTypeByIdx(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TbsType TbsType.getTbsTypeByIdx(int)"})
  public void testTbsTypeGetTbsTypeByIdx_whenNine_thenReturnUnknown() {
    // Arrange, Act and Assert
    assertEquals(TbsType.UNKNOWN, TbsType.getTbsTypeByIdx(9));
  }

  /**
   * Test TbsType {@link TbsType#getTbsTypeByIdx(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code MEMORY_SYSTEM_DATA}.
   * </ul>
   *
   * <p>Method under test: {@link TbsType#getTbsTypeByIdx(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TbsType TbsType.getTbsTypeByIdx(int)"})
  public void testTbsTypeGetTbsTypeByIdx_whenOne_thenReturnMemorySystemData() {
    // Arrange, Act and Assert
    assertEquals(TbsType.MEMORY_SYSTEM_DATA, TbsType.getTbsTypeByIdx(1));
  }
}
