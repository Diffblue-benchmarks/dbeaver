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

public class NestedMultiLineRuleDiffblueTest {
  /**
   * Test {@link NestedMultiLineRule#NestedMultiLineRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>When {@code End Sequence}.
   * </ul>
   *
   * <p>Method under test: {@link NestedMultiLineRule#NestedMultiLineRule(String, String, TPToken,
   * char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NestedMultiLineRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewNestedMultiLineRule_whenEndSequence() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    NestedMultiLineRule actualNestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", token, 'A', true);

    // Assert
    assertFalse(actualNestedMultiLineRule.isRollback());
    assertSame(((TPTokenAbstract) token).EOF, actualNestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link NestedMultiLineRule#NestedMultiLineRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NestedMultiLineRule#NestedMultiLineRule(String, String, TPToken,
   * char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NestedMultiLineRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewNestedMultiLineRule_whenNull() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    NestedMultiLineRule actualNestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", null, token, 'A', true);

    // Assert
    assertFalse(actualNestedMultiLineRule.isRollback());
    assertSame(((TPTokenAbstract) token).EOF, actualNestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken NestedMultiLineRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = nestedMultiLineRule.doEvaluate(scanner, true);

    // Assert
    assertEquals(1, scanner.getOffset());
    TPToken tpToken = ((TPTokenAbstract) actualDoEvaluateResult).EOF;
    assertSame(tpToken, actualDoEvaluateResult);
    assertSame(tpToken, nestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken NestedMultiLineRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume2() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    nestedMultiLineRule.setRollback(true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = nestedMultiLineRule.doEvaluate(scanner, true);

    // Assert
    assertEquals(1, scanner.getOffset());
    TPToken tpToken = ((TPTokenAbstract) actualDoEvaluateResult).EOF;
    assertSame(tpToken, actualDoEvaluateResult);
    assertSame(tpToken, nestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken NestedMultiLineRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume3() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = nestedMultiLineRule.doEvaluate(scanner, true);

    // Assert
    assertEquals(1, scanner.getOffset());
    TPToken tpToken = ((TPTokenAbstract) actualDoEvaluateResult).EOF;
    assertSame(tpToken, actualDoEvaluateResult);
    assertSame(tpToken, nestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken NestedMultiLineRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume4() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = nestedMultiLineRule.doEvaluate(scanner, true);

    // Assert
    assertEquals(1, scanner.getOffset());
    TPToken tpToken = ((TPTokenAbstract) actualDoEvaluateResult).EOF;
    assertSame(tpToken, actualDoEvaluateResult);
    assertSame(tpToken, nestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner},
   * {@code resume}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken NestedMultiLineRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume5() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = nestedMultiLineRule.doEvaluate(scanner, false);

    // Assert
    TPToken successToken = nestedMultiLineRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertEquals(0, scanner.getOffset());
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).UNDEFINED, actualDoEvaluateResult);
  }

  /**
   * Test {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NestedMultiLineRule.endSequenceDetected(TPCharacterScanner)"})
  public void testEndSequenceDetected() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualEndSequenceDetectedResult = nestedMultiLineRule.endSequenceDetected(scanner);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualEndSequenceDetectedResult);
  }

  /**
   * Test {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NestedMultiLineRule.endSequenceDetected(TPCharacterScanner)"})
  public void testEndSequenceDetected2() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    nestedMultiLineRule.setRollback(true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualEndSequenceDetectedResult = nestedMultiLineRule.endSequenceDetected(scanner);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualEndSequenceDetectedResult);
  }

  /**
   * Test {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NestedMultiLineRule.endSequenceDetected(TPCharacterScanner)"})
  public void testEndSequenceDetected3() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualEndSequenceDetectedResult = nestedMultiLineRule.endSequenceDetected(scanner);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualEndSequenceDetectedResult);
  }

  /**
   * Test {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}.
   *
   * <p>Method under test: {@link NestedMultiLineRule#endSequenceDetected(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NestedMultiLineRule.endSequenceDetected(TPCharacterScanner)"})
  public void testEndSequenceDetected4() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualEndSequenceDetectedResult = nestedMultiLineRule.endSequenceDetected(scanner);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualEndSequenceDetectedResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NestedMultiLineRule#setRollback(boolean)}
   *   <li>{@link NestedMultiLineRule#isRollback()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean NestedMultiLineRule.isRollback()",
    "void NestedMultiLineRule.setRollback(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);

    // Act
    nestedMultiLineRule.setRollback(true);

    // Assert
    assertTrue(nestedMultiLineRule.isRollback());
  }
}
