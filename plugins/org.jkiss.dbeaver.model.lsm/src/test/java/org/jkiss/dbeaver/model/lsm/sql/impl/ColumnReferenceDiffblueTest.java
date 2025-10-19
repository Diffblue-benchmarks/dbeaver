package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ColumnReferenceDiffblueTest {
  /**
   * Test new {@link ColumnReference} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ColumnReference}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ColumnReference.<init>()"})
  public void testNewColumnReference() {
    // Arrange and Act
    ColumnReference actualColumnReference = new ColumnReference();

    // Assert
    assertNull(actualColumnReference.catalogName);
    assertNull(actualColumnReference.columnName);
    assertNull(actualColumnReference.schemaName);
    assertNull(actualColumnReference.tableAlias);
    assertNull(actualColumnReference.tableName);
  }
}
