package org.jkiss.dbeaver.model.sql.semantics;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQuerySymbolDiffblueTest {
  /**
   * Test {@link SQLQuerySymbol#merge(SQLQuerySymbol)}.
   *
   * <ul>
   *   <li>Given {@link SQLQuerySymbol#SQLQuerySymbol(String)} with name is {@code 42}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQuerySymbol#merge(SQLQuerySymbol)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQuerySymbol SQLQuerySymbol.merge(SQLQuerySymbol)"})
  public void testMerge_givenSQLQuerySymbolWithNameIs42_thenThrowUnsupportedOperationException() {
    // Arrange
    SQLQuerySymbol sqlQuerySymbol = new SQLQuerySymbol("42");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> sqlQuerySymbol.merge(new SQLQuerySymbol("Name")));
  }
}
