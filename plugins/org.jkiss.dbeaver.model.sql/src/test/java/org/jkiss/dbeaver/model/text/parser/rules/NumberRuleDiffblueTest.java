package org.jkiss.dbeaver.model.text.parser.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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

public class NumberRuleDiffblueTest {
  /**
   * Test {@link NumberRule#NumberRule(TPToken)}.
   *
   * <p>Method under test: {@link NumberRule#NumberRule(TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberRule.<init>(TPToken)"})
  public void testNewNumberRule() {
    // Arrange, Act and Assert
    TPToken tpToken = new NumberRule(TPTokenAbstract.EOF).fToken;
    assertTrue(tpToken instanceof TPTokenAbstract);
    assertNull(tpToken.getData());
    assertFalse(tpToken.isOther());
    assertFalse(tpToken.isUndefined());
    assertFalse(tpToken.isWhitespace());
    assertTrue(tpToken.isEOF());
  }

  /**
   * Test {@link NumberRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>When {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link NumberRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken NumberRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_whenTPRuleBasedScanner_thenReturnUndefined() {
    // Arrange
    NumberRule numberRule = new NumberRule(TPTokenAbstract.EOF);

    // Act
    TPToken actualEvaluateResult = numberRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
