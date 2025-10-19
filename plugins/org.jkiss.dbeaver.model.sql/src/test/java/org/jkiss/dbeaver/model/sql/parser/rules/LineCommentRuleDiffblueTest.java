package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertEquals;
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

public class LineCommentRuleDiffblueTest {
  /**
   * Test {@link LineCommentRule#LineCommentRule(String, TPToken)}.
   *
   * <p>Method under test: {@link LineCommentRule#LineCommentRule(String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineCommentRule.<init>(String, TPToken)"})
  public void testNewLineCommentRule() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    LineCommentRule actualLineCommentRule = new LineCommentRule("Start Sequence", token);

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualLineCommentRule.getSuccessToken());
  }

  /**
   * Test {@link LineCommentRule#LineCommentRule(String, TPToken, char)}.
   *
   * <p>Method under test: {@link LineCommentRule#LineCommentRule(String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineCommentRule.<init>(String, TPToken, char)"})
  public void testNewLineCommentRule2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    LineCommentRule actualLineCommentRule = new LineCommentRule("Start Sequence", token, 'A');

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualLineCommentRule.getSuccessToken());
  }

  /**
   * Test {@link LineCommentRule#LineCommentRule(String, TPToken, char, boolean)}.
   *
   * <p>Method under test: {@link LineCommentRule#LineCommentRule(String, TPToken, char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineCommentRule.<init>(String, TPToken, char, boolean)"})
  public void testNewLineCommentRule3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    LineCommentRule actualLineCommentRule = new LineCommentRule("Start Sequence", token, 'A', true);

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualLineCommentRule.getSuccessToken());
  }

  /**
   * Test {@link LineCommentRule#LineCommentRule(String, TPToken, char, boolean, boolean)}.
   *
   * <p>Method under test: {@link LineCommentRule#LineCommentRule(String, TPToken, char, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LineCommentRule.<init>(String, TPToken, char, boolean, boolean)"})
  public void testNewLineCommentRule4() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    LineCommentRule actualLineCommentRule =
        new LineCommentRule("Start Sequence", token, 'A', true, true);

    // Assert
    assertSame(((TPTokenAbstract) token).EOF, actualLineCommentRule.getSuccessToken());
  }

  /**
   * Test {@link LineCommentRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link LineCommentRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken LineCommentRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);

    // Act
    TPToken actualDoEvaluateResult = lineCommentRule.doEvaluate(new TPRuleBasedScanner(), false);

    // Assert
    TPToken successToken = lineCommentRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).UNDEFINED, actualDoEvaluateResult);
  }

  /**
   * Test {@link LineCommentRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <p>Method under test: {@link LineCommentRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken LineCommentRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);
    lineCommentRule.setColumnConstraint(-1);

    // Act
    TPToken actualEvaluateResult = lineCommentRule.evaluate(new TPRuleBasedScanner(), false);

    // Assert
    TPToken successToken = lineCommentRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link LineCommentRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <p>Method under test: {@link LineCommentRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken LineCommentRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume2() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);
    lineCommentRule.setColumnConstraint(1);

    // Act
    TPToken actualEvaluateResult = lineCommentRule.evaluate(new TPRuleBasedScanner(), false);

    // Assert
    TPToken successToken = lineCommentRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link LineCommentRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When array of {@code char} with {@code A} and {@code Z}.
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link LineCommentRule#sequenceDetected(TPCharacterScanner, char[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LineCommentRule.sequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testSequenceDetected_whenArrayOfCharWithAAndZ_thenTPRuleBasedScannerOffsetIsZero() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualSequenceDetectedResult =
        lineCommentRule.sequenceDetected(scanner, new char[] {'A', 'Z', 'A', 'Z'}, false);

    // Assert
    assertEquals(0, scanner.getOffset());
    assertFalse(actualSequenceDetectedResult);
  }

  /**
   * Test {@link LineCommentRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When {@code AZAZ} toCharArray.
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is one.
   * </ul>
   *
   * <p>Method under test: {@link LineCommentRule#sequenceDetected(TPCharacterScanner, char[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean LineCommentRule.sequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testSequenceDetected_whenAzazToCharArray_thenTPRuleBasedScannerOffsetIsOne() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualSequenceDetectedResult =
        lineCommentRule.sequenceDetected(scanner, "AZAZ".toCharArray(), true);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualSequenceDetectedResult);
  }
}
