package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDelimiterRuleDiffblueTest {
  /**
   * Test {@link SQLDelimiterRule#SQLDelimiterRule(String[], TPToken)}.
   *
   * <p>Method under test: {@link SQLDelimiterRule#SQLDelimiterRule(String[], TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterRule.<init>(String[], TPToken)"})
  public void testNewSQLDelimiterRule() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};

    // Act
    SQLDelimiterRule actualSqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Assert
    char[][] delimiters2 = actualSqlDelimiterRule.getDelimiters();
    assertEquals(1, delimiters2.length);
    assertArrayEquals("DELIMITERS".toCharArray(), delimiters2[0]);
  }

  /**
   * Test {@link SQLDelimiterRule#getDelimiters()}.
   *
   * <p>Method under test: {@link SQLDelimiterRule#getDelimiters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char[][] SQLDelimiterRule.getDelimiters()"})
  public void testGetDelimiters() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    char[][] actualDelimiters = sqlDelimiterRule.getDelimiters();

    // Assert
    assertEquals(1, actualDelimiters.length);
    assertArrayEquals("DELIMITERS".toCharArray(), actualDelimiters[0]);
  }

  /**
   * Test {@link SQLDelimiterRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLDelimiterRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_whenTPRuleBasedScanner_thenReturnUndefined() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    TPToken actualEvaluateResult = sqlDelimiterRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLDelimiterRule#changeDelimiter(String)}.
   *
   * <p>Method under test: {@link SQLDelimiterRule#changeDelimiter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterRule.changeDelimiter(String)"})
  public void testChangeDelimiter() {
    // Arrange
    String[] delimiters = new String[] {""};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    sqlDelimiterRule.changeDelimiter("New Delimiter");

    // Assert
    char[][] delimiters2 = sqlDelimiterRule.getDelimiters();
    assertEquals(1, delimiters2.length);
    assertArrayEquals("NEW DELIMITER".toCharArray(), delimiters2[0]);
  }

  /**
   * Test {@link SQLDelimiterRule#changeDelimiter(String)}.
   *
   * <ul>
   *   <li>When {@code DELIMITERS}.
   *   <li>Then first element is {@code DELIMITERS} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterRule#changeDelimiter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterRule.changeDelimiter(String)"})
  public void testChangeDelimiter_whenDelimiters_thenFirstElementIsDelimitersToCharArray() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    sqlDelimiterRule.changeDelimiter("DELIMITERS");

    // Assert that nothing has changed
    char[][] delimiters2 = sqlDelimiterRule.getDelimiters();
    assertEquals(1, delimiters2.length);
    assertArrayEquals("DELIMITERS".toCharArray(), delimiters2[0]);
  }

  /**
   * Test {@link SQLDelimiterRule#changeDelimiter(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then first element is {@code DELIMITERS} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterRule#changeDelimiter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterRule.changeDelimiter(String)"})
  public void testChangeDelimiter_whenEmptyString_thenFirstElementIsDelimitersToCharArray() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    sqlDelimiterRule.changeDelimiter("");

    // Assert that nothing has changed
    char[][] delimiters2 = sqlDelimiterRule.getDelimiters();
    assertEquals(1, delimiters2.length);
    assertArrayEquals("DELIMITERS".toCharArray(), delimiters2[0]);
  }

  /**
   * Test {@link SQLDelimiterRule#changeDelimiter(String)}.
   *
   * <ul>
   *   <li>When {@code New Delimiter}.
   *   <li>Then first element is {@code NEW DELIMITER} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterRule#changeDelimiter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterRule.changeDelimiter(String)"})
  public void testChangeDelimiter_whenNewDelimiter_thenFirstElementIsNewDelimiterToCharArray() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    sqlDelimiterRule.changeDelimiter("New Delimiter");

    // Assert
    char[][] delimiters2 = sqlDelimiterRule.getDelimiters();
    assertEquals(1, delimiters2.length);
    assertArrayEquals("NEW DELIMITER".toCharArray(), delimiters2[0]);
  }

  /**
   * Test {@link SQLDelimiterRule#changeDelimiter(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then first element is {@code DELIMITERS} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterRule#changeDelimiter(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDelimiterRule.changeDelimiter(String)"})
  public void testChangeDelimiter_whenNull_thenFirstElementIsDelimitersToCharArray() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule sqlDelimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    // Act
    sqlDelimiterRule.changeDelimiter(null);

    // Assert that nothing has changed
    char[][] delimiters2 = sqlDelimiterRule.getDelimiters();
    assertEquals(1, delimiters2.length);
    assertArrayEquals("DELIMITERS".toCharArray(), delimiters2[0]);
  }
}
