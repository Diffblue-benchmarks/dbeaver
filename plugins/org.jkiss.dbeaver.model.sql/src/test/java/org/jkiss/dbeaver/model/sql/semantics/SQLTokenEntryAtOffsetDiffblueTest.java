package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLTokenEntryAtOffsetDiffblueTest {
  /**
   * Test {@link SQLTokenEntryAtOffset#SQLTokenEntryAtOffset(int, SQLQuerySymbolEntry)}.
   *
   * <p>Method under test: {@link SQLTokenEntryAtOffset#SQLTokenEntryAtOffset(int,
   * SQLQuerySymbolEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokenEntryAtOffset.<init>(int, SQLQuerySymbolEntry)"})
  public void testNewSQLTokenEntryAtOffset() {
    // Arrange and Act
    SQLTokenEntryAtOffset actualSqlTokenEntryAtOffset = new SQLTokenEntryAtOffset(2, null);

    // Assert
    assertNull(actualSqlTokenEntryAtOffset.entry);
    assertEquals(2, actualSqlTokenEntryAtOffset.offset);
  }
}
