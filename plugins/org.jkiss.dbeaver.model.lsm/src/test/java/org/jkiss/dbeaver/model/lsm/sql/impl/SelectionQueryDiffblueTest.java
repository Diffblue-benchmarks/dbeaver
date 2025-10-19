package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SelectionQueryDiffblueTest {
  /**
   * Test new {@link SelectionQuery} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SelectionQuery}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SelectionQuery.<init>()"})
  public void testNewSelectionQuery() {
    // Arrange and Act
    SelectionQuery actualSelectionQuery = new SelectionQuery();

    // Assert
    assertNull(actualSelectionQuery.columns);
    assertNull(actualSelectionQuery.sources);
    assertNull(actualSelectionQuery.groupBy);
    assertNull(actualSelectionQuery.having);
    assertNull(actualSelectionQuery.where);
    assertNull(actualSelectionQuery.quantifier);
  }
}
