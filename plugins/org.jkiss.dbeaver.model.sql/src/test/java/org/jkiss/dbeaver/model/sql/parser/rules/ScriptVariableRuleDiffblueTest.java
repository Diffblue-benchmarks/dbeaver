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

public class ScriptVariableRuleDiffblueTest {
  /**
   * Test {@link ScriptVariableRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link ScriptVariableRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken ScriptVariableRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_whenTPRuleBasedScanner_thenReturnUndefined() {
    // Arrange
    ScriptVariableRule scriptVariableRule = new ScriptVariableRule(TPTokenAbstract.EOF);

    // Act
    TPToken actualEvaluateResult = scriptVariableRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
