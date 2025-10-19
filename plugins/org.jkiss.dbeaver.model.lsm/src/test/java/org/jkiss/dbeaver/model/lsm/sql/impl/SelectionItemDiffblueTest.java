package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SelectionItemDiffblueTest {
  /**
   * Test new {@link SelectionItem} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SelectionItem}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SelectionItem.<init>()"})
  public void testNewSelectionItem() {
    // Arrange and Act
    SelectionItem actualSelectionItem = new SelectionItem();

    // Assert
    assertNull(actualSelectionItem.alias);
    assertNull(actualSelectionItem.columnName);
    assertNull(actualSelectionItem.expression);
  }
}
