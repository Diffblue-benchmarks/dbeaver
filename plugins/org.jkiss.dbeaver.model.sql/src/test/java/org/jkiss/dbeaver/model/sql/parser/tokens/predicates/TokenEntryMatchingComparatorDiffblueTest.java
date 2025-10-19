package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TokenEntryMatchingComparatorDiffblueTest {
  /**
   * Test {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isStronglyComparable(TokenEntry)"})
  public void testIsStronglyComparableWithTokenEntry() {
    // Arrange and Act
    boolean actualIsStronglyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isStronglyComparable(
            new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertFalse(actualIsStronglyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isStronglyComparable(TokenEntry)"})
  public void testIsStronglyComparableWithTokenEntry2() {
    // Arrange and Act
    boolean actualIsStronglyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isStronglyComparable(
            new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, true));

    // Assert
    assertFalse(actualIsStronglyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isStronglyComparable(TokenEntry)"})
  public void testIsStronglyComparableWithTokenEntry3() {
    // Arrange
    SQLTokenEntry term = new SQLTokenEntry("String", null, true);

    // Act
    boolean actualIsStronglyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isStronglyComparable(term);

    // Assert
    assertFalse(actualIsStronglyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isStronglyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isStronglyComparable(TokenEntry)"})
  public void testIsStronglyComparableWithTokenEntry_thenReturnTrue() {
    // Arrange and Act
    boolean actualIsStronglyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isStronglyComparable(
            new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, false));

    // Assert
    assertTrue(actualIsStronglyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#isPartiallyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isPartiallyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isPartiallyComparable(TokenEntry)"})
  public void testIsPartiallyComparableWithTokenEntry() {
    // Arrange and Act
    boolean actualIsPartiallyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isPartiallyComparable(
            new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertFalse(actualIsPartiallyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#isPartiallyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isPartiallyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isPartiallyComparable(TokenEntry)"})
  public void testIsPartiallyComparableWithTokenEntry2() {
    // Arrange
    SQLTokenEntry term = new SQLTokenEntry("String", null, true);

    // Act
    boolean actualIsPartiallyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isPartiallyComparable(term);

    // Assert
    assertFalse(actualIsPartiallyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#isPartiallyComparable(TokenEntry)} with {@code
   * TokenEntry}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#isPartiallyComparable(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.isPartiallyComparable(TokenEntry)"})
  public void testIsPartiallyComparableWithTokenEntry_thenReturnTrue() {
    // Arrange and Act
    boolean actualIsPartiallyComparableResult =
        TokenEntryMatchingComparator.INSTANCE.isPartiallyComparable(
            new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, false));

    // Assert
    assertTrue(actualIsPartiallyComparableResult);
  }

  /**
   * Test {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)} with {@code
   * TokenEntry}, {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TokenEntryMatchingComparator.compare(TokenEntry, TokenEntry)"})
  public void testCompareWithTokenEntryTokenEntry_thenReturnMinusOne() {
    // Arrange
    CaptureTokenPredicateNode first = new CaptureTokenPredicateNode("String", null, "Key");

    // Act and Assert
    assertEquals(
        -1,
        TokenEntryMatchingComparator.INSTANCE.compare(
            first, new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key")));
  }

  /**
   * Test {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)} with {@code
   * TokenEntry}, {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TokenEntryMatchingComparator.compare(TokenEntry, TokenEntry)"})
  public void testCompareWithTokenEntryTokenEntry_thenReturnOne() {
    // Arrange
    CaptureTokenPredicateNode first =
        new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key");
    CaptureTokenPredicateNode second = new CaptureTokenPredicateNode("String", null, "Key");

    // Act and Assert
    assertEquals(1, TokenEntryMatchingComparator.INSTANCE.compare(first, second));
  }

  /**
   * Test {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)} with {@code
   * TokenEntry}, {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TokenEntryMatchingComparator.compare(TokenEntry, TokenEntry)"})
  public void testCompareWithTokenEntryTokenEntry_thenReturnZero() {
    // Arrange
    SQLTokenEntry first = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertEquals(
        0,
        TokenEntryMatchingComparator.INSTANCE.compare(
            first, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true)));
  }

  /**
   * Test {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)} with {@code
   * TokenEntry}, {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#compare(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TokenEntryMatchingComparator.compare(TokenEntry, TokenEntry)"})
  public void testCompareWithTokenEntryTokenEntry_thenReturnZero2() {
    // Arrange
    CaptureTokenPredicateNode first = new CaptureTokenPredicateNode("String", null, "Key");
    CaptureTokenPredicateNode second = new CaptureTokenPredicateNode("String", null, "Key");

    // Act and Assert
    assertEquals(0, TokenEntryMatchingComparator.INSTANCE.compare(first, second));
  }

  /**
   * Test {@link TokenEntryMatchingComparator#match(TokenEntry, TokenEntry)} with {@code
   * TokenEntry}, {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#match(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.match(TokenEntry, TokenEntry)"})
  public void testMatchWithTokenEntryTokenEntry_thenReturnFalse() {
    // Arrange
    SQLTokenEntry key = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertFalse(
        TokenEntryMatchingComparator.INSTANCE.match(
            key, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true)));
  }

  /**
   * Test {@link TokenEntryMatchingComparator#match(TokenEntry, TokenEntry)} with {@code
   * TokenEntry}, {@code TokenEntry}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TokenEntryMatchingComparator#match(TokenEntry, TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenEntryMatchingComparator.match(TokenEntry, TokenEntry)"})
  public void testMatchWithTokenEntryTokenEntry_thenReturnTrue() {
    // Arrange
    SQLTokenEntry key =
        new SQLTokenEntry(
            "org.jkiss.dbeaver.model.sql.parser.TokenEntry", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertTrue(
        TokenEntryMatchingComparator.INSTANCE.match(
            key, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true)));
  }
}
