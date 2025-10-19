package org.jkiss.dbeaver.model.sql.semantics.model.select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.semantics.model.select.SQLQueryRowsProjectionModel.FiltersData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryRowsProjectionModelDiffblueTest {
  /**
   * Test FiltersData {@link FiltersData#empty()}.
   *
   * <p>Method under test: {@link FiltersData#empty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FiltersData FiltersData.empty()"})
  public void testFiltersDataEmpty() {
    // Arrange and Act
    FiltersData<Object> actualEmptyResult = FiltersData.empty();

    // Assert
    assertNull(actualEmptyResult.groupByClause);
    assertNull(actualEmptyResult.havingClause);
    assertNull(actualEmptyResult.orderByClause);
    assertNull(actualEmptyResult.whereClause);
  }

  /**
   * Test FiltersData {@link FiltersData#of(Object, Object, Object, Object)}.
   *
   * <p>Method under test: {@link FiltersData#of(Object, Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FiltersData FiltersData.of(Object, Object, Object, Object)"})
  public void testFiltersDataOf() {
    // Arrange and Act
    FiltersData<Object> actualOfResult = FiltersData.of("Where", "Group By", "Having", "Order By");

    // Assert
    assertEquals("Group By", actualOfResult.groupByClause);
    assertEquals("Having", actualOfResult.havingClause);
    assertEquals("Order By", actualOfResult.orderByClause);
    assertEquals("Where", actualOfResult.whereClause);
  }
}
