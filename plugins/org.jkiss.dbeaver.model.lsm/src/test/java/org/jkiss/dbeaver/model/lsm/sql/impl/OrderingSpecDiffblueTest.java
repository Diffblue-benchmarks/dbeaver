package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.lsm.sql.impl.OrderingSpec.SortSpec;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OrderingSpecDiffblueTest {
  /**
   * Test new {@link OrderingSpec} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OrderingSpec}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OrderingSpec.<init>()"})
  public void testNewOrderingSpec() {
    // Arrange, Act and Assert
    assertNull(new OrderingSpec().sorting);
  }

  /**
   * Test SortSpec new {@link SortSpec} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SortSpec}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SortSpec.<init>()"})
  public void testSortSpecNewSortSpec() {
    // Arrange and Act
    SortSpec actualSortSpec = new SortSpec();

    // Assert
    assertNull(actualSortSpec.collation);
    assertNull(actualSortSpec.columnName);
    assertNull(actualSortSpec.ordering);
    assertEquals(0, actualSortSpec.columnNumber);
  }
}
