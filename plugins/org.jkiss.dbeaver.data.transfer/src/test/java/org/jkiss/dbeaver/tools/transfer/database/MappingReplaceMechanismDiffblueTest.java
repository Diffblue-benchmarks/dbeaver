package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MappingReplaceMechanismDiffblueTest {
  /**
   * Test {@link MappingReplaceMechanism#getName()}.
   *
   * <p>Method under test: {@link MappingReplaceMechanism#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String MappingReplaceMechanism.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("Do not replace", MappingReplaceMechanism.valueOf("ABSENT").getName());
  }

  /**
   * Test {@link MappingReplaceMechanism#getCaseBySelectionId(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code UNDERSCORES}.
   * </ul>
   *
   * <p>Method under test: {@link MappingReplaceMechanism#getCaseBySelectionId(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MappingReplaceMechanism MappingReplaceMechanism.getCaseBySelectionId(int)"})
  public void testGetCaseBySelectionId_whenOne_thenReturnUnderscores() {
    // Arrange, Act and Assert
    assertEquals(
        MappingReplaceMechanism.UNDERSCORES, MappingReplaceMechanism.getCaseBySelectionId(1));
  }

  /**
   * Test {@link MappingReplaceMechanism#getCaseBySelectionId(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code ABSENT}.
   * </ul>
   *
   * <p>Method under test: {@link MappingReplaceMechanism#getCaseBySelectionId(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MappingReplaceMechanism MappingReplaceMechanism.getCaseBySelectionId(int)"})
  public void testGetCaseBySelectionId_whenThree_thenReturnAbsent() {
    // Arrange, Act and Assert
    assertEquals(MappingReplaceMechanism.ABSENT, MappingReplaceMechanism.getCaseBySelectionId(3));
  }
}
