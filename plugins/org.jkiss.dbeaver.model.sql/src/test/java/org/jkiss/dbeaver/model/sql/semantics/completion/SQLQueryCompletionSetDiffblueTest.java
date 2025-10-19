package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryCompletionSetDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLQueryCompletionSet#SQLQueryCompletionSet(int, int, Collection)}
   *   <li>{@link SQLQueryCompletionSet#getItems()}
   *   <li>{@link SQLQueryCompletionSet#getReplacementLength()}
   *   <li>{@link SQLQueryCompletionSet#getReplacementPosition()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryCompletionSet.<init>(int, int, Collection)",
    "Collection SQLQueryCompletionSet.getItems()",
    "int SQLQueryCompletionSet.getReplacementLength()",
    "int SQLQueryCompletionSet.getReplacementPosition()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<SQLQueryCompletionItem> items = new ArrayList<>();

    // Act
    SQLQueryCompletionSet actualSqlQueryCompletionSet = new SQLQueryCompletionSet(1, 3, items);
    Collection<? extends SQLQueryCompletionItem> actualItems =
        actualSqlQueryCompletionSet.getItems();
    int actualReplacementLength = actualSqlQueryCompletionSet.getReplacementLength();

    // Assert
    assertTrue(actualItems instanceof List);
    assertEquals(1, actualSqlQueryCompletionSet.getReplacementPosition());
    assertEquals(3, actualReplacementLength);
    assertSame(items, actualItems);
  }
}
