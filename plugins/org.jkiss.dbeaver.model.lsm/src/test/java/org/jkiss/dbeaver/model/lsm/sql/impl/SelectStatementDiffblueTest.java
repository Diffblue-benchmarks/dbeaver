package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SelectStatementDiffblueTest {
  /**
   * Test new {@link SelectStatement} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SelectStatement}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SelectStatement.<init>()"})
  public void testNewSelectStatement() {
    // Arrange and Act
    SelectStatement actualSelectStatement = new SelectStatement();

    // Assert
    assertNull(actualSelectStatement.columns);
    assertNull(actualSelectStatement.sources);
    assertNull(actualSelectStatement.groupBy);
    assertNull(actualSelectStatement.orderBy);
    assertNull(actualSelectStatement.having);
    assertNull(actualSelectStatement.where);
    assertNull(actualSelectStatement.quantifier);
  }
}
