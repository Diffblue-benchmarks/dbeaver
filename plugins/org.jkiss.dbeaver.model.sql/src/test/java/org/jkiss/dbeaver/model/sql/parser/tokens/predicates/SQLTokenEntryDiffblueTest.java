package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLTokenEntryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLTokenEntry#SQLTokenEntry(String, SQLTokenType, boolean)}
   *   <li>{@link SQLTokenEntry#getString()}
   *   <li>{@link SQLTokenEntry#isInverted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLTokenEntry.<init>(String, SQLTokenType, boolean)",
    "String SQLTokenEntry.getString()",
    "boolean SQLTokenEntry.isInverted()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLTokenEntry actualSqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);
    String actualString = actualSqlTokenEntry.getString();
    boolean actualIsInvertedResult = actualSqlTokenEntry.isInverted();

    // Assert
    assertEquals("String", actualString);
    assertEquals(SQLTokenType.T_KEYWORD, actualSqlTokenEntry.getTokenType());
    assertTrue(actualIsInvertedResult);
  }

  /**
   * Test {@link SQLTokenEntry#getTokenType()}.
   *
   * <p>Method under test: {@link SQLTokenEntry#getTokenType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType SQLTokenEntry.getTokenType()"})
  public void testGetTokenType() {
    // Arrange, Act and Assert
    assertEquals(
        SQLTokenType.T_KEYWORD,
        new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true).getTokenType());
  }

  /**
   * Test {@link SQLTokenEntry#matches(TokenEntry)}.
   *
   * <p>Method under test: {@link SQLTokenEntry#matches(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.matches(TokenEntry)"})
  public void testMatches() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, false);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode(null, null, "Key");

    // Act
    boolean actualMatchesResult = sqlTokenEntry.matches(other);

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link SQLTokenEntry#matches(TokenEntry)}.
   *
   * <p>Method under test: {@link SQLTokenEntry#matches(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.matches(TokenEntry)"})
  public void testMatches2() {
    // Arrange
    SQLTokenEntry sqlTokenEntry =
        new SQLTokenEntry(
            "org.jkiss.dbeaver.model.sql.parser.TokenEntry", SQLTokenType.T_KEYWORD, true);

    // Act
    boolean actualMatchesResult =
        sqlTokenEntry.matches(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link SQLTokenEntry#matches(TokenEntry)}.
   *
   * <ul>
   *   <li>Given {@link SQLTokenEntry#SQLTokenEntry(String, SQLTokenType, boolean)} with {@code
   *       String} and type is {@code null} and isInverted is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#matches(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.matches(TokenEntry)"})
  public void testMatches_givenSQLTokenEntryWithStringAndTypeIsNullAndIsInvertedIsFalse() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", null, false);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode(null, null, "Key");

    // Act
    boolean actualMatchesResult = sqlTokenEntry.matches(other);

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link SQLTokenEntry#matches(TokenEntry)}.
   *
   * <ul>
   *   <li>Given {@link SQLTokenEntry#SQLTokenEntry(String, SQLTokenType, boolean)} with {@code
   *       String} and type is {@code T_STRING} and isInverted is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#matches(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.matches(TokenEntry)"})
  public void testMatches_givenSQLTokenEntryWithStringAndTypeIsTStringAndIsInvertedIsTrue() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_STRING, true);

    // Act
    boolean actualMatchesResult =
        sqlTokenEntry.matches(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link SQLTokenEntry#matches(TokenEntry)}.
   *
   * <ul>
   *   <li>Given {@link SQLTokenEntry#SQLTokenEntry(String, SQLTokenType, boolean)} with string is
   *       {@code null} and type is {@code null} and isInverted is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#matches(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.matches(TokenEntry)"})
  public void testMatches_givenSQLTokenEntryWithStringIsNullAndTypeIsNullAndIsInvertedIsFalse() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, null, false);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode(null, null, "Key");

    // Act
    boolean actualMatchesResult = sqlTokenEntry.matches(other);

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link SQLTokenEntry#matches(TokenEntry)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#matches(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.matches(TokenEntry)"})
  public void testMatches_thenReturnFalse() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act
    boolean actualMatchesResult =
        sqlTokenEntry.matches(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}, and {@link SQLTokenEntry#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLTokenEntry#equals(Object)}
   *   <li>{@link SQLTokenEntry#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);
    SQLTokenEntry sqlTokenEntry2 = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertEquals(sqlTokenEntry, sqlTokenEntry2);
    assertEquals(sqlTokenEntry.hashCode(), sqlTokenEntry2.hashCode());
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}, and {@link SQLTokenEntry#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLTokenEntry#equals(Object)}
   *   <li>{@link SQLTokenEntry#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    CaptureTokenPredicateNode captureTokenPredicateNode = mock(CaptureTokenPredicateNode.class);
    when(captureTokenPredicateNode.isInverted()).thenReturn(true);
    when(captureTokenPredicateNode.getString()).thenReturn("String");
    when(captureTokenPredicateNode.getTokenType()).thenReturn(SQLTokenType.T_KEYWORD);

    // Act and Assert
    assertEquals(sqlTokenEntry, captureTokenPredicateNode);
    assertNotEquals(sqlTokenEntry.hashCode(), captureTokenPredicateNode.hashCode());
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}, and {@link SQLTokenEntry#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLTokenEntry#equals(Object)}
   *   <li>{@link SQLTokenEntry#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, true);

    CaptureTokenPredicateNode captureTokenPredicateNode = mock(CaptureTokenPredicateNode.class);
    when(captureTokenPredicateNode.isInverted()).thenReturn(true);
    when(captureTokenPredicateNode.getString()).thenReturn(null);
    when(captureTokenPredicateNode.getTokenType()).thenReturn(SQLTokenType.T_KEYWORD);

    // Act and Assert
    assertEquals(sqlTokenEntry, captureTokenPredicateNode);
    assertNotEquals(sqlTokenEntry.hashCode(), captureTokenPredicateNode.hashCode());
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}, and {@link SQLTokenEntry#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLTokenEntry#equals(Object)}
   *   <li>{@link SQLTokenEntry#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertEquals(sqlTokenEntry, sqlTokenEntry);
    int expectedHashCodeResult = sqlTokenEntry.hashCode();
    assertEquals(expectedHashCodeResult, sqlTokenEntry.hashCode());
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, null, true);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode(null, null, "Key");

    // Act
    boolean actualEqualsResult = sqlTokenEntry.equals((TokenEntry) other);

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther2() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, null, true);

    // Act
    boolean actualEqualsResult =
        sqlTokenEntry.equals(
            (TokenEntry) new CaptureTokenPredicateNode(null, SQLTokenType.T_KEYWORD, "Key"));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther3() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, true);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode(null, null, "Key");

    // Act
    boolean actualEqualsResult = sqlTokenEntry.equals((TokenEntry) other);

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther4() {
    // Arrange
    SQLTokenEntry sqlTokenEntry =
        new SQLTokenEntry(
            "org.jkiss.dbeaver.model.sql.parser.TokenEntry", SQLTokenType.T_KEYWORD, true);

    // Act
    boolean actualEqualsResult =
        sqlTokenEntry.equals(
            (TokenEntry) new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther5() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_STRING, true);

    // Act
    boolean actualEqualsResult =
        sqlTokenEntry.equals(
            (TokenEntry) new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <ul>
   *   <li>Given {@link SQLTokenEntry#SQLTokenEntry(String, SQLTokenType, boolean)} with {@code
   *       String} and type is {@code null} and isInverted is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther_givenSQLTokenEntryWithStringAndTypeIsNullAndIsInvertedIsTrue() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", null, true);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode(null, null, "Key");

    // Act
    boolean actualEqualsResult = sqlTokenEntry.equals((TokenEntry) other);

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther_thenReturnTrue() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act
    boolean actualEqualsResult =
        sqlTokenEntry.equals(
            (TokenEntry) new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    // Assert
    assertTrue(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(TokenEntry)} with {@code other}.
   *
   * <ul>
   *   <li>When {@link CaptureTokenPredicateNode#CaptureTokenPredicateNode(String, SQLTokenType,
   *       String)} with {@code String} and type is {@code null} and {@code Key}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(TokenEntry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(TokenEntry)"})
  public void testEqualsWithOther_whenCaptureTokenPredicateNodeWithStringAndTypeIsNullAndKey() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, null, true);
    CaptureTokenPredicateNode other = new CaptureTokenPredicateNode("String", null, "Key");

    // Act
    boolean actualEqualsResult = sqlTokenEntry.equals((TokenEntry) other);

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    SQLTokenEntry sqlTokenEntry =
        new SQLTokenEntry(
            "org.jkiss.dbeaver.model.sql.parser.TokenEntry", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", null, true);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_STRING, true);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, false);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, new SQLTokenEntry("String", null, true));
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, SQLTokenType.T_KEYWORD, true);

    CaptureTokenPredicateNode captureTokenPredicateNode = mock(CaptureTokenPredicateNode.class);
    when(captureTokenPredicateNode.isInverted()).thenReturn(true);
    when(captureTokenPredicateNode.getString()).thenReturn("String");
    when(captureTokenPredicateNode.getTokenType()).thenReturn(SQLTokenType.T_KEYWORD);

    // Act and Assert
    assertNotEquals(sqlTokenEntry, captureTokenPredicateNode);
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true), null);
  }

  /**
   * Test {@link SQLTokenEntry#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokenEntry.equals(Object)", "int SQLTokenEntry.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true),
        "Different type to SQLTokenEntry");
  }

  /**
   * Test {@link SQLTokenEntry#format(StringBuilder)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo<?>any}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#format(StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringBuilder SQLTokenEntry.format(StringBuilder)"})
  public void testFormat_thenStringBuilderWithFooToStringIsFooAny() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry(null, null, false);
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualFormatResult = sqlTokenEntry.format(sb);

    // Assert
    assertEquals("foo<?>any", sb.toString());
    assertSame(sb, actualFormatResult);
  }

  /**
   * Test {@link SQLTokenEntry#format(StringBuilder)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo!<T_KEYWORD>'String'}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenEntry#format(StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StringBuilder SQLTokenEntry.format(StringBuilder)"})
  public void testFormat_thenStringBuilderWithFooToStringIsFooTKeywordString() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualFormatResult = sqlTokenEntry.format(sb);

    // Assert
    assertEquals("foo!<T_KEYWORD>'String'", sb.toString());
    assertSame(sb, actualFormatResult);
  }

  /**
   * Test {@link SQLTokenEntry#applyImpl(TokenPredicateNodeVisitor, Object)}.
   *
   * <p>Method under test: {@link SQLTokenEntry#applyImpl(TokenPredicateNodeVisitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object SQLTokenEntry.applyImpl(TokenPredicateNodeVisitor, Object)"})
  public void testApplyImpl() {
    // Arrange
    SQLTokenEntry sqlTokenEntry = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    TokenPredicateNodeVisitor<Object, Object> visitor = mock(TokenPredicateNodeVisitor.class);
    when(visitor.visitTokenEntry(Mockito.<SQLTokenEntry>any(), Mockito.<Object>any()))
        .thenReturn("Visit Token Entry");

    // Act
    Object actualApplyImplResult = sqlTokenEntry.applyImpl(visitor, "Arg");

    // Assert
    verify(visitor).visitTokenEntry(isA(SQLTokenEntry.class), isA(Object.class));
    assertEquals("Visit Token Entry", actualApplyImplResult);
  }
}
