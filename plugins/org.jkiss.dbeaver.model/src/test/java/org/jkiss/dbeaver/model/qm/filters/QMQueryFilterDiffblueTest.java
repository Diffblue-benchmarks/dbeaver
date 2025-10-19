package org.jkiss.dbeaver.model.qm.filters;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMQueryFilterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMQueryFilter#QMQueryFilter(String, String)}
   *   <li>{@link QMQueryFilter#getFilterValue()}
   *   <li>{@link QMQueryFilter#getQuery()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMQueryFilter.<init>(String, String)",
    "String QMQueryFilter.getFilterValue()",
    "String QMQueryFilter.getQuery()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    QMQueryFilter actualQmQueryFilter = new QMQueryFilter("Query", "42");
    String actualFilterValue = actualQmQueryFilter.getFilterValue();

    // Assert
    assertEquals("42", actualFilterValue);
    assertEquals("Query", actualQmQueryFilter.getQuery());
  }
}
