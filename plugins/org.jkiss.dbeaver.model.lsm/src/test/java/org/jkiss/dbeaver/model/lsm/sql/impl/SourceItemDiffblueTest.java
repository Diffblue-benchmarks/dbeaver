package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SourceItemDiffblueTest {
  /**
   * Test new {@link SourceItem} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SourceItem}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SourceItem.<init>()"})
  public void testNewSourceItem() {
    // Arrange and Act
    SourceItem actualSourceItem = new SourceItem();

    // Assert
    assertNull(actualSourceItem.alias);
    assertNull(actualSourceItem.catalogName);
    assertNull(actualSourceItem.schemaName);
    assertNull(actualSourceItem.tableName);
    assertNull(actualSourceItem.derivedColumns);
  }
}
