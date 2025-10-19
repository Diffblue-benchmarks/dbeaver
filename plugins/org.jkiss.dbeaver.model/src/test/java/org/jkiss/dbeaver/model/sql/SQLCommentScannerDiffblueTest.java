package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.NoSuchElementException;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLCommentScannerDiffblueTest {
  /**
   * Test {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}.
   *
   * <ul>
   *   <li>Then return next is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentScanner.<init>(Pair, String[], String)"})
  public void testNewSQLCommentScanner_thenReturnNextIsEmptyString() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};

    // Act
    SQLCommentScanner actualSqlCommentScanner =
        new SQLCommentScanner(new Pair<>("", ""), slTokens, "Sql");

    // Assert
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertEquals("", actualSqlCommentScanner.next());
    assertTrue(actualSqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}.
   *
   * <ul>
   *   <li>When array of {@link String} with empty string.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentScanner.<init>(Pair, String[], String)"})
  public void testNewSQLCommentScanner_whenArrayOfStringWithEmptyString_thenReturnNotHasNext() {
    // Arrange
    String[] slTokens = new String[] {""};

    // Act
    SQLCommentScanner actualSqlCommentScanner =
        new SQLCommentScanner(AbstractSQLDialect.IN_CLAUSE_PARENTHESES, slTokens, "Sql");

    // Assert
    assertFalse(actualSqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}.
   *
   * <ul>
   *   <li>When {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentScanner.<init>(Pair, String[], String)"})
  public void testNewSQLCommentScanner_whenIn_clause_parentheses_thenReturnNotHasNext() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};

    // Act
    SQLCommentScanner actualSqlCommentScanner =
        new SQLCommentScanner(AbstractSQLDialect.IN_CLAUSE_PARENTHESES, slTokens, "Sql");

    // Assert
    assertFalse(actualSqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentScanner.<init>(Pair, String[], String)"})
  public void testNewSQLCommentScanner_whenNull_thenReturnNotHasNext() {
    // Arrange and Act
    SQLCommentScanner actualSqlCommentScanner = new SQLCommentScanner(null, null, "Sql");

    // Assert
    assertFalse(actualSqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}.
   *
   * <ul>
   *   <li>When {@link Pair#Pair(Object, Object)} with first is empty string and {@code Second}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentScanner.<init>(Pair, String[], String)"})
  public void testNewSQLCommentScanner_whenPairWithFirstIsEmptyStringAndSecond() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};

    // Act
    SQLCommentScanner actualSqlCommentScanner =
        new SQLCommentScanner(new Pair<>("", "Second"), slTokens, "Sql");

    // Assert
    assertEquals("Sql", actualSqlCommentScanner.next());
    assertFalse(actualSqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}.
   *
   * <ul>
   *   <li>When {@link Pair#Pair(Object, Object)} with first is empty string and second is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#SQLCommentScanner(Pair, String[], String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLCommentScanner.<init>(Pair, String[], String)"})
  public void testNewSQLCommentScanner_whenPairWithFirstIsEmptyStringAndSecondIsNull() {
    // Arrange
    Pair<String, String> mlComments = new Pair<>("", null);
    String[] slTokens = new String[] {"ABC123"};

    // Act
    SQLCommentScanner actualSqlCommentScanner = new SQLCommentScanner(mlComments, slTokens, "Sql");

    // Assert
    assertEquals("Sql", actualSqlCommentScanner.next());
    assertFalse(actualSqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#hasNext()}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with first is empty string and {@code Second}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLCommentScanner.hasNext()"})
  public void testHasNext_givenPairWithFirstIsEmptyStringAndSecond_thenReturnTrue() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};
    SQLCommentScanner sqlCommentScanner =
        new SQLCommentScanner(new Pair<>("", "Second"), slTokens, "Sql");

    // Act and Assert
    assertTrue(sqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#hasNext()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLCommentScanner.hasNext()"})
  public void testHasNext_thenReturnFalse() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};
    SQLCommentScanner sqlCommentScanner =
        new SQLCommentScanner(AbstractSQLDialect.IN_CLAUSE_PARENTHESES, slTokens, "Sql");

    // Act and Assert
    assertFalse(sqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#next()}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with first is empty string and {@code Second}.
   *   <li>Then return {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLCommentScanner.next()"})
  public void testNext_givenPairWithFirstIsEmptyStringAndSecond_thenReturnSql() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};
    SQLCommentScanner sqlCommentScanner =
        new SQLCommentScanner(new Pair<>("", "Second"), slTokens, "Sql");

    // Act and Assert
    assertEquals("Sql", sqlCommentScanner.next());
    assertFalse(sqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#next()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLCommentScanner.next()"})
  public void testNext_thenReturnEmptyString() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};
    SQLCommentScanner sqlCommentScanner =
        new SQLCommentScanner(new Pair<>("", ""), slTokens, "Sql");

    // Act and Assert
    assertEquals("", sqlCommentScanner.next());
    assertTrue(sqlCommentScanner.hasNext());
  }

  /**
   * Test {@link SQLCommentScanner#next()}.
   *
   * <ul>
   *   <li>Then throw {@link NoSuchElementException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommentScanner#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLCommentScanner.next()"})
  public void testNext_thenThrowNoSuchElementException() {
    // Arrange
    String[] slTokens = new String[] {"ABC123"};
    SQLCommentScanner sqlCommentScanner =
        new SQLCommentScanner(AbstractSQLDialect.IN_CLAUSE_PARENTHESES, slTokens, "Sql");

    // Act and Assert
    assertThrows(NoSuchElementException.class, () -> sqlCommentScanner.next());
  }
}
