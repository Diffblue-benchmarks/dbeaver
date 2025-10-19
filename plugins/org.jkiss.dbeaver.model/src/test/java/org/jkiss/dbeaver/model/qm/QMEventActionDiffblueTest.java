package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMEventActionDiffblueTest {
  /**
   * Test {@link QMEventAction#getId()}.
   *
   * <p>Method under test: {@link QMEventAction#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int QMEventAction.getId()"})
  public void testGetId() {
    // Arrange, Act and Assert
    assertEquals(0, QMEventAction.valueOf("BEGIN").getId());
  }

  /**
   * Test {@link QMEventAction#getById(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code END}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventAction#getById(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMEventAction QMEventAction.getById(int)"})
  public void testGetById_whenOne_thenReturnEnd() {
    // Arrange, Act and Assert
    assertEquals(QMEventAction.END, QMEventAction.getById(1));
  }

  /**
   * Test {@link QMEventAction#getById(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code BEGIN}.
   * </ul>
   *
   * <p>Method under test: {@link QMEventAction#getById(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMEventAction QMEventAction.getById(int)"})
  public void testGetById_whenThree_thenReturnBegin() {
    // Arrange, Act and Assert
    assertEquals(QMEventAction.BEGIN, QMEventAction.getById(3));
  }
}
