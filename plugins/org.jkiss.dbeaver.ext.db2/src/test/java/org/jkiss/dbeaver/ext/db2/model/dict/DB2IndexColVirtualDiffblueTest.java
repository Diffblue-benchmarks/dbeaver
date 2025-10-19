package org.jkiss.dbeaver.ext.db2.model.dict;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2IndexColVirtualDiffblueTest {
  /**
   * Test {@link DB2IndexColVirtual#isNotVirtual()}.
   *
   * <ul>
   *   <li>Given {@code N}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DB2IndexColVirtual#isNotVirtual()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Boolean DB2IndexColVirtual.isNotVirtual()"})
  public void testIsNotVirtual_givenN_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(DB2IndexColVirtual.N.isNotVirtual());
  }

  /**
   * Test {@link DB2IndexColVirtual#isNotVirtual()}.
   *
   * <ul>
   *   <li>Given {@code S}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DB2IndexColVirtual#isNotVirtual()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Boolean DB2IndexColVirtual.isNotVirtual()"})
  public void testIsNotVirtual_givenS_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DB2IndexColVirtual.S.isNotVirtual());
  }
}
