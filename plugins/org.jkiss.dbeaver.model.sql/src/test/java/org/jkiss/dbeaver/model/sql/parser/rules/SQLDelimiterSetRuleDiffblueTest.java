package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLSetDelimiterToken;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDelimiterSetRuleDiffblueTest {
  /**
   * Test {@link SQLDelimiterSetRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Then return {@link SQLSetDelimiterToken} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterSetRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLDelimiterSetRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_thenReturnSQLSetDelimiterToken() {
    // Arrange
    SQLSetDelimiterToken setDelimiterToken = new SQLSetDelimiterToken();
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    SQLDelimiterSetRule sqlDelimiterSetRule =
        new SQLDelimiterSetRule("", setDelimiterToken, delimiterRule);

    // Act and Assert
    assertSame(setDelimiterToken, sqlDelimiterSetRule.evaluate(new TPRuleBasedScanner()));
  }

  /**
   * Test {@link SQLDelimiterSetRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDelimiterSetRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLDelimiterSetRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_thenReturnUndefined() {
    // Arrange
    SQLSetDelimiterToken setDelimiterToken = new SQLSetDelimiterToken();
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimiterRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    SQLDelimiterSetRule sqlDelimiterSetRule =
        new SQLDelimiterSetRule("Set Delimiter Word", setDelimiterToken, delimiterRule);

    // Act
    TPToken actualEvaluateResult = sqlDelimiterSetRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
