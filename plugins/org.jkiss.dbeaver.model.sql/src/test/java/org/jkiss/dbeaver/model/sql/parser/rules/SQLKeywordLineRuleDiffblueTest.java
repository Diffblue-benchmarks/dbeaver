package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertFalse;
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

public class SQLKeywordLineRuleDiffblueTest {
  /**
   * Test {@link SQLKeywordLineRule#SQLKeywordLineRule(String, TPToken)}.
   *
   * <p>Method under test: {@link SQLKeywordLineRule#SQLKeywordLineRule(String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLKeywordLineRule.<init>(String, TPToken)"})
  public void testNewSQLKeywordLineRule() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SQLKeywordLineRule actualSqlKeywordLineRule = new SQLKeywordLineRule("Keyword String", token);

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualSqlKeywordLineRule.getSuccessToken());
  }

  /**
   * Test {@link SQLKeywordLineRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link SQLKeywordLineRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLKeywordLineRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume() {
    // Arrange
    SQLKeywordLineRule sqlKeywordLineRule =
        new SQLKeywordLineRule("Keyword String", TPTokenAbstract.EOF);

    // Act
    TPToken actualDoEvaluateResult = sqlKeywordLineRule.doEvaluate(new TPRuleBasedScanner(), false);

    // Assert
    TPToken successToken = sqlKeywordLineRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).UNDEFINED, actualDoEvaluateResult);
  }

  /**
   * Test {@link SQLKeywordLineRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link SQLKeywordLineRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLKeywordLineRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume() {
    // Arrange
    SQLKeywordLineRule sqlKeywordLineRule =
        new SQLKeywordLineRule("Keyword String", TPTokenAbstract.EOF);
    sqlKeywordLineRule.setColumnConstraint(-1);

    // Act
    TPToken actualEvaluateResult = sqlKeywordLineRule.evaluate(new TPRuleBasedScanner(), false);

    // Assert
    TPToken successToken = sqlKeywordLineRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLKeywordLineRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link SQLKeywordLineRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLKeywordLineRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume2() {
    // Arrange
    SQLKeywordLineRule sqlKeywordLineRule =
        new SQLKeywordLineRule("Keyword String", TPTokenAbstract.EOF);
    sqlKeywordLineRule.setColumnConstraint(1);

    // Act
    TPToken actualEvaluateResult = sqlKeywordLineRule.evaluate(new TPRuleBasedScanner(), false);

    // Assert
    TPToken successToken = sqlKeywordLineRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLKeywordLineRule#caselessSequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLKeywordLineRule#caselessSequenceDetected(TPCharacterScanner,
   * char[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SQLKeywordLineRule.caselessSequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testCaselessSequenceDetected_whenTPRuleBasedScanner_thenReturnFalse() {
    // Arrange
    SQLKeywordLineRule sqlKeywordLineRule =
        new SQLKeywordLineRule("Keyword String", TPTokenAbstract.EOF);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act and Assert
    assertFalse(sqlKeywordLineRule.caselessSequenceDetected(scanner, "AZAZ".toCharArray(), true));
  }

  /**
   * Test {@link SQLKeywordLineRule#caselessSequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLKeywordLineRule#caselessSequenceDetected(TPCharacterScanner,
   * char[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SQLKeywordLineRule.caselessSequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testCaselessSequenceDetected_whenTPRuleBasedScanner_thenReturnTrue() {
    // Arrange
    SQLKeywordLineRule sqlKeywordLineRule =
        new SQLKeywordLineRule("Keyword String", TPTokenAbstract.EOF);

    // Act and Assert
    assertTrue(
        sqlKeywordLineRule.caselessSequenceDetected(new TPRuleBasedScanner(), new char[] {}, true));
  }
}
