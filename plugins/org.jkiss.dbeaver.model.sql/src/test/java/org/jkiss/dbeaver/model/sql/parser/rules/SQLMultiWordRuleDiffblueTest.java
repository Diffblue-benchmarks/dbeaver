package org.jkiss.dbeaver.model.sql.parser.rules;

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

public class SQLMultiWordRuleDiffblueTest {
  /**
   * Test {@link SQLMultiWordRule#SQLMultiWordRule(String[], TPToken)}.
   *
   * <p>Method under test: {@link SQLMultiWordRule#SQLMultiWordRule(String[], TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLMultiWordRule.<init>(String[], TPToken)"})
  public void testNewSQLMultiWordRule() {
    // Arrange and Act
    SQLMultiWordRule actualSqlMultiWordRule =
        new SQLMultiWordRule(new String[] {"Parts", "42"}, TPTokenAbstract.EOF);
    TPToken actualEvaluateResult = actualSqlMultiWordRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLMultiWordRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLMultiWordRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLMultiWordRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_thenReturnUndefined() {
    // Arrange
    SQLMultiWordRule sqlMultiWordRule =
        new SQLMultiWordRule(new String[] {"Parts", "42"}, TPTokenAbstract.EOF);

    // Act
    TPToken actualEvaluateResult = sqlMultiWordRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
