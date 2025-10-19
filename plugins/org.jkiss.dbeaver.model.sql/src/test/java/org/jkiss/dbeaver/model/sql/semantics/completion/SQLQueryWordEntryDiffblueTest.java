package org.jkiss.dbeaver.model.sql.semantics.completion;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryWordEntryDiffblueTest {
  /**
   * Test {@link SQLQueryWordEntry#SQLQueryWordEntry(int, String)}.
   *
   * <p>Method under test: {@link SQLQueryWordEntry#SQLQueryWordEntry(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryWordEntry.<init>(int, String)"})
  public void testNewSQLQueryWordEntry() {
    // Arrange and Act
    SQLQueryWordEntry actualSqlQueryWordEntry = new SQLQueryWordEntry(2, "String");

    // Assert
    assertEquals("String", actualSqlQueryWordEntry.string);
    assertEquals("string", actualSqlQueryWordEntry.filterString);
    assertEquals(2, actualSqlQueryWordEntry.offset);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)} with {@code
   * filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(SQLQueryWordEntry, boolean)"})
  public void testMatchesWithFilterKeyOrNullSearchInside_thenReturnMax_value() {
    // Arrange
    SQLQueryWordEntry sqlQueryWordEntry = new SQLQueryWordEntry(2, "String");

    // Act and Assert
    assertEquals(
        Integer.MAX_VALUE, sqlQueryWordEntry.matches(new SQLQueryWordEntry(2, "String"), false));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)} with {@code
   * filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return one hundred thirty-four.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(SQLQueryWordEntry, boolean)"})
  public void testMatchesWithFilterKeyOrNullSearchInside_thenReturnOneHundredThirtyFour() {
    // Arrange
    SQLQueryWordEntry sqlQueryWordEntry = new SQLQueryWordEntry(2, "String");

    // Act and Assert
    assertEquals(134, sqlQueryWordEntry.matches(new SQLQueryWordEntry(2, "String"), true));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)} with {@code
   * filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return twelve.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(SQLQueryWordEntry, boolean)"})
  public void testMatchesWithFilterKeyOrNullSearchInside_thenReturnTwelve() {
    // Arrange
    SQLQueryWordEntry sqlQueryWordEntry = new SQLQueryWordEntry(2, "42");

    // Act and Assert
    assertEquals(12, sqlQueryWordEntry.matches(new SQLQueryWordEntry(2, "42"), true));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)} with {@code
   * filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(SQLQueryWordEntry, boolean)"})
  public void testMatchesWithFilterKeyOrNullSearchInside_thenReturnZero() {
    // Arrange
    SQLQueryWordEntry sqlQueryWordEntry = new SQLQueryWordEntry(2, "42");

    // Act and Assert
    assertEquals(0, sqlQueryWordEntry.matches(new SQLQueryWordEntry(2, "String"), true));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)} with {@code
   * filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(SQLQueryWordEntry, boolean)"})
  public void testMatchesWithFilterKeyOrNullSearchInside_thenReturnZero2() {
    // Arrange
    SQLQueryWordEntry sqlQueryWordEntry = new SQLQueryWordEntry(2, "42");

    // Act and Assert
    assertEquals(0, sqlQueryWordEntry.matches(new SQLQueryWordEntry(2, "String"), false));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)} with {@code
   * filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(SQLQueryWordEntry, boolean)"})
  public void testMatchesWithFilterKeyOrNullSearchInside_whenNull_thenReturnMax_value() {
    // Arrange, Act and Assert
    assertEquals(
        Integer.MAX_VALUE,
        new SQLQueryWordEntry(2, "String").matches((SQLQueryWordEntry) null, false));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, boolean)} with {@code filterKeyStringOrNull},
   * {@code searchInside}.
   *
   * <ul>
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, boolean)"})
  public void testMatchesWithFilterKeyStringOrNullSearchInside_thenReturnMax_value() {
    // Arrange, Act and Assert
    assertEquals(Integer.MAX_VALUE, new SQLQueryWordEntry(2, "String").matches("", false));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, boolean)} with {@code filterKeyStringOrNull},
   * {@code searchInside}.
   *
   * <ul>
   *   <li>Then return twelve.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, boolean)"})
  public void testMatchesWithFilterKeyStringOrNullSearchInside_thenReturnTwelve() {
    // Arrange, Act and Assert
    assertEquals(12, new SQLQueryWordEntry(2, "42").matches("42", true));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, boolean)} with {@code filterKeyStringOrNull},
   * {@code searchInside}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, boolean)"})
  public void testMatchesWithFilterKeyStringOrNullSearchInside_whenEmptyString_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new SQLQueryWordEntry(2, "String").matches("", true));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, boolean)} with {@code filterKeyStringOrNull},
   * {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code Filter Key String Or Null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, boolean)"})
  public void testMatchesWithFilterKeyStringOrNullSearchInside_whenFilterKeyStringOrNull() {
    // Arrange, Act and Assert
    assertEquals(0, new SQLQueryWordEntry(2, "String").matches("Filter Key String Or Null", true));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, boolean)} with {@code filterKeyStringOrNull},
   * {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code Filter Key String Or Null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, boolean)"})
  public void testMatchesWithFilterKeyStringOrNullSearchInside_whenFilterKeyStringOrNull2() {
    // Arrange, Act and Assert
    assertEquals(0, new SQLQueryWordEntry(2, "String").matches("Filter Key String Or Null", false));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, boolean)} with {@code filterKeyStringOrNull},
   * {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, boolean)"})
  public void testMatchesWithFilterKeyStringOrNullSearchInside_whenNull_thenReturnMax_value() {
    // Arrange, Act and Assert
    assertEquals(
        Integer.MAX_VALUE, new SQLQueryWordEntry(2, "String").matches((String) null, false));
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)} with {@code string},
   * {@code filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, SQLQueryWordEntry, boolean)"})
  public void testMatchesWithStringFilterKeyOrNullSearchInside_thenReturnMax_value() {
    // Arrange and Act
    int actualMatchesResult =
        SQLQueryWordEntry.matches("42", new SQLQueryWordEntry(2, "42"), false);

    // Assert
    assertEquals(Integer.MAX_VALUE, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)} with {@code string},
   * {@code filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return one hundred thirty-four.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, SQLQueryWordEntry, boolean)"})
  public void testMatchesWithStringFilterKeyOrNullSearchInside_thenReturnOneHundredThirtyFour() {
    // Arrange and Act
    int actualMatchesResult =
        SQLQueryWordEntry.matches("String", new SQLQueryWordEntry(2, "String"), true);

    // Assert
    assertEquals(134, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)} with {@code string},
   * {@code filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return twelve.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, SQLQueryWordEntry, boolean)"})
  public void testMatchesWithStringFilterKeyOrNullSearchInside_thenReturnTwelve() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches("42", new SQLQueryWordEntry(2, "42"), true);

    // Assert
    assertEquals(12, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)} with {@code string},
   * {@code filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, SQLQueryWordEntry, boolean)"})
  public void testMatchesWithStringFilterKeyOrNullSearchInside_when42_thenReturnZero() {
    // Arrange and Act
    int actualMatchesResult =
        SQLQueryWordEntry.matches("42", new SQLQueryWordEntry(2, "String"), true);

    // Assert
    assertEquals(0, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)} with {@code string},
   * {@code filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, SQLQueryWordEntry, boolean)"})
  public void testMatchesWithStringFilterKeyOrNullSearchInside_whenNull_thenReturnMax_value() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches(null, (SQLQueryWordEntry) null, false);

    // Assert
    assertEquals(Integer.MAX_VALUE, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)} with {@code string},
   * {@code filterKeyOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, SQLQueryWordEntry, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, SQLQueryWordEntry, boolean)"})
  public void testMatchesWithStringFilterKeyOrNullSearchInside_whenString_thenReturnZero() {
    // Arrange and Act
    int actualMatchesResult =
        SQLQueryWordEntry.matches("String", new SQLQueryWordEntry(2, "String"), false);

    // Assert
    assertEquals(0, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_thenReturnMax_value() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches(null, (String) null, false);

    // Assert
    assertEquals(Integer.MAX_VALUE, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return {@link Integer#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_thenReturnMax_value2() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches("String", "", false);

    // Assert
    assertEquals(Integer.MAX_VALUE, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_thenReturnZero() {
    // Arrange and Act
    int actualMatchesResult =
        SQLQueryWordEntry.matches("String", "Filter Key String Or Null", true);

    // Assert
    assertEquals(0, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_thenReturnZero2() {
    // Arrange and Act
    int actualMatchesResult =
        SQLQueryWordEntry.matches("String", "Filter Key String Or Null", false);

    // Assert
    assertEquals(0, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return twelve.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_when42_thenReturnTwelve() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches("42", "42", true);

    // Assert
    assertEquals(12, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_whenEmptyString() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches("String", "", true);

    // Assert
    assertEquals(0, actualMatchesResult);
  }

  /**
   * Test {@link SQLQueryWordEntry#matches(String, String, boolean)} with {@code string}, {@code
   * filterKeyStringOrNull}, {@code searchInside}.
   *
   * <ul>
   *   <li>When {@code Strings must not be null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryWordEntry#matches(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLQueryWordEntry.matches(String, String, boolean)"})
  public void testMatchesWithStringFilterKeyStringOrNullSearchInside_whenStringsMustNotBeNull() {
    // Arrange and Act
    int actualMatchesResult = SQLQueryWordEntry.matches("String", "Strings must not be null", true);

    // Assert
    assertEquals(0, actualMatchesResult);
  }
}
