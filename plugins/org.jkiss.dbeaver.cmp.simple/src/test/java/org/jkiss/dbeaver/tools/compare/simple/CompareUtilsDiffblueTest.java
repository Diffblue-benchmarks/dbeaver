package org.jkiss.dbeaver.tools.compare.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CompareUtilsDiffblueTest {
  /**
   * Test {@link CompareUtils#equalPropertyValues(Object, Object)}.
   *
   * <p>Method under test: {@link CompareUtils#equalPropertyValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompareUtils.equalPropertyValues(Object, Object)"})
  public void testEqualPropertyValues() {
    // Arrange, Act and Assert
    assertFalse(
        CompareUtils.equalPropertyValues(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Value2"));
  }

  /**
   * Test {@link CompareUtils#equalPropertyValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CompareUtils#equalPropertyValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompareUtils.equalPropertyValues(Object, Object)"})
  public void testEqualPropertyValues_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(CompareUtils.equalPropertyValues(null, "Value2"));
  }

  /**
   * Test {@link CompareUtils#equalPropertyValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Value1}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CompareUtils#equalPropertyValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompareUtils.equalPropertyValues(Object, Object)"})
  public void testEqualPropertyValues_whenValue1_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(CompareUtils.equalPropertyValues("Value1", "Value2"));
  }

  /**
   * Test {@link CompareUtils#equalPropertyValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Value1}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link CompareUtils#equalPropertyValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompareUtils.equalPropertyValues(Object, Object)"})
  public void testEqualPropertyValues_whenValue1_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(CompareUtils.equalPropertyValues("Value1", null));
  }

  /**
   * Test {@link CompareUtils#equalPropertyValues(Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Value2}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CompareUtils#equalPropertyValues(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CompareUtils.equalPropertyValues(Object, Object)"})
  public void testEqualPropertyValues_whenValue2_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(CompareUtils.equalPropertyValues("Value2", "Value2"));
  }
}
