package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLFullLineRuleDiffblueTest {
  /**
   * Test {@link SQLFullLineRule#SQLFullLineRule(String, TPToken)}.
   *
   * <p>Method under test: {@link SQLFullLineRule#SQLFullLineRule(String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFullLineRule.<init>(String, TPToken)"})
  public void testNewSQLFullLineRule() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SQLFullLineRule actualSqlFullLineRule = new SQLFullLineRule("Start Sequence", token);

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualSqlFullLineRule.getSuccessToken());
  }

  /**
   * Test {@link SQLFullLineRule#SQLFullLineRule(String, TPToken, char)}.
   *
   * <p>Method under test: {@link SQLFullLineRule#SQLFullLineRule(String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFullLineRule.<init>(String, TPToken, char)"})
  public void testNewSQLFullLineRule2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SQLFullLineRule actualSqlFullLineRule = new SQLFullLineRule("Start Sequence", token, 'A');

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualSqlFullLineRule.getSuccessToken());
  }

  /**
   * Test {@link SQLFullLineRule#SQLFullLineRule(String, TPToken, char, boolean)}.
   *
   * <p>Method under test: {@link SQLFullLineRule#SQLFullLineRule(String, TPToken, char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLFullLineRule.<init>(String, TPToken, char, boolean)"})
  public void testNewSQLFullLineRule3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SQLFullLineRule actualSqlFullLineRule = new SQLFullLineRule("Start Sequence", token, 'A', true);

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualSqlFullLineRule.getSuccessToken());
  }

  /**
   * Test {@link SQLFullLineRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <p>Method under test: {@link SQLFullLineRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLFullLineRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner() {
    // Arrange
    SQLFullLineRule sqlFullLineRule = new SQLFullLineRule("Start Sequence", TPTokenAbstract.EOF);

    // Act
    TPToken actualEvaluateResult = sqlFullLineRule.evaluate(new TPRuleBasedScanner());

    // Assert
    TPToken successToken = sqlFullLineRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
