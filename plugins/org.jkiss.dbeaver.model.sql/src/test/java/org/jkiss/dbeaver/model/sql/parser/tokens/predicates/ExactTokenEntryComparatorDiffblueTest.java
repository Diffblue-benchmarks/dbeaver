package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExactTokenEntryComparatorDiffblueTest {
  /**
   * Test {@link ExactTokenEntryComparator#compare(TokenEntry, TokenEntry)} with {@code TokenEntry},
   * {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link ExactTokenEntryComparator#compare(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ExactTokenEntryComparator.compare(TokenEntry, TokenEntry)"})
  public void testCompareWithTokenEntryTokenEntry_thenReturnOne() {
    // Arrange
    SQLTokenEntry first = new SQLTokenEntry("String", SQLTokenType.T_STRING, true);

    // Act and Assert
    assertEquals(
        1,
        ExactTokenEntryComparator.INSTANCE.compare(
            first, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true)));
  }

  /**
   * Test {@link ExactTokenEntryComparator#compare(TokenEntry, TokenEntry)} with {@code TokenEntry},
   * {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ExactTokenEntryComparator#compare(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ExactTokenEntryComparator.compare(TokenEntry, TokenEntry)"})
  public void testCompareWithTokenEntryTokenEntry_thenReturnZero() {
    // Arrange
    SQLTokenEntry first = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertEquals(
        0,
        ExactTokenEntryComparator.INSTANCE.compare(
            first, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true)));
  }
}
