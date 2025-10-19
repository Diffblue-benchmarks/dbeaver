package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.jkiss.dbeaver.model.text.parser.TPTokenDefault;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDollarQuoteRuleDiffblueTest {
  /**
   * Test {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDollarQuoteRule.<init>(boolean, boolean, boolean, boolean)"})
  public void testNewSQLDollarQuoteRule_whenFalse() {
    // Arrange and Act
    SQLDollarQuoteRule actualSqlDollarQuoteRule = new SQLDollarQuoteRule(false, true, false, false);

    // Assert
    TPToken successToken = actualSqlDollarQuoteRule.getSuccessToken();
    Object data = successToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertTrue(successToken instanceof TPTokenDefault);
    assertEquals(SQLTokenType.T_STRING, data);
    assertFalse(successToken.isEOF());
    assertFalse(successToken.isOther());
    assertFalse(successToken.isUndefined());
    assertFalse(successToken.isWhitespace());
  }

  /**
   * Test {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDollarQuoteRule.<init>(boolean, boolean, boolean, boolean)"})
  public void testNewSQLDollarQuoteRule_whenFalse2() {
    // Arrange and Act
    SQLDollarQuoteRule actualSqlDollarQuoteRule = new SQLDollarQuoteRule(true, true, false, true);

    // Assert
    TPToken successToken = actualSqlDollarQuoteRule.getSuccessToken();
    Object data = successToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertTrue(successToken instanceof TPTokenDefault);
    assertEquals(SQLTokenType.T_STRING, data);
    assertFalse(successToken.isEOF());
    assertFalse(successToken.isOther());
    assertFalse(successToken.isUndefined());
    assertFalse(successToken.isWhitespace());
  }

  /**
   * Test {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDollarQuoteRule.<init>(boolean, boolean, boolean, boolean)"})
  public void testNewSQLDollarQuoteRule_whenFalse3() {
    // Arrange and Act
    SQLDollarQuoteRule actualSqlDollarQuoteRule = new SQLDollarQuoteRule(true, true, true, false);

    // Assert
    TPToken successToken = actualSqlDollarQuoteRule.getSuccessToken();
    Object data = successToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertTrue(successToken instanceof TPTokenDefault);
    assertEquals(SQLTokenType.T_STRING, data);
    assertFalse(successToken.isEOF());
    assertFalse(successToken.isOther());
    assertFalse(successToken.isUndefined());
    assertFalse(successToken.isWhitespace());
  }

  /**
   * Test {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#SQLDollarQuoteRule(boolean, boolean, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDollarQuoteRule.<init>(boolean, boolean, boolean, boolean)"})
  public void testNewSQLDollarQuoteRule_whenTrue() {
    // Arrange and Act
    SQLDollarQuoteRule actualSqlDollarQuoteRule = new SQLDollarQuoteRule(true, true, true, true);

    // Assert
    TPToken successToken = actualSqlDollarQuoteRule.getSuccessToken();
    Object data = successToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertTrue(successToken instanceof TPTokenDefault);
    assertEquals(SQLTokenType.T_STRING, data);
    assertFalse(successToken.isEOF());
    assertFalse(successToken.isOther());
    assertFalse(successToken.isUndefined());
    assertFalse(successToken.isWhitespace());
  }

  /**
   * Test {@link SQLDollarQuoteRule#getSuccessToken()}.
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#getSuccessToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLDollarQuoteRule.getSuccessToken()"})
  public void testGetSuccessToken() {
    // Arrange
    SQLDollarQuoteRule sqlDollarQuoteRule = new SQLDollarQuoteRule(true, true, true, true);

    // Act and Assert
    assertTrue(sqlDollarQuoteRule.getSuccessToken() instanceof TPTokenDefault);
  }

  /**
   * Test {@link SQLDollarQuoteRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLDollarQuoteRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume_whenTPRuleBasedScanner_thenReturnUndefined() {
    // Arrange
    SQLDollarQuoteRule sqlDollarQuoteRule = new SQLDollarQuoteRule(true, true, true, true);

    // Act
    TPToken actualEvaluateResult = sqlDollarQuoteRule.evaluate(new TPRuleBasedScanner(), true);

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLDollarQuoteRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDollarQuoteRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLDollarQuoteRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner_whenTPRuleBasedScanner_thenReturnUndefined() {
    // Arrange
    SQLDollarQuoteRule sqlDollarQuoteRule = new SQLDollarQuoteRule(true, true, true, true);

    // Act
    TPToken actualEvaluateResult = sqlDollarQuoteRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
