package org.jkiss.dbeaver.model.text.parser.rules;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.rules.LineCommentRule;
import org.jkiss.dbeaver.model.sql.parser.rules.NestedMultiLineRule;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PatternRuleDiffblueTest {
  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PatternRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewPatternRule_thenReturnFEndSequenceIsEndSequenceToCharArray() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule =
        new PatternRule("Start Sequence", "End Sequence", token, 'A', true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertFalse(actualPatternRule.fBreaksOnEOF);
    assertFalse(actualPatternRule.fEscapeContinuesLine);
    assertFalse(actualPatternRule.fExcludeLineDelimiter);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualPatternRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PatternRule.<init>(String, String, TPToken, char, boolean, boolean)"})
  public void testNewPatternRule_thenReturnFEndSequenceIsEndSequenceToCharArray2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule =
        new PatternRule("Start Sequence", "End Sequence", token, 'A', true, true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertFalse(actualPatternRule.fEscapeContinuesLine);
    assertFalse(actualPatternRule.fExcludeLineDelimiter);
    assertTrue(actualPatternRule.fBreaksOnEOF);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualPatternRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PatternRule.<init>(String, String, TPToken, char, boolean, boolean, boolean)"
  })
  public void testNewPatternRule_thenReturnFEndSequenceIsEndSequenceToCharArray3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule =
        new PatternRule("Start Sequence", "End Sequence", token, 'A', true, true, true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertFalse(actualPatternRule.fExcludeLineDelimiter);
    assertTrue(actualPatternRule.fBreaksOnEOF);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertTrue(actualPatternRule.fEscapeContinuesLine);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualPatternRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PatternRule.<init>(String, String, TPToken, char, boolean, boolean, boolean, boolean)"
  })
  public void testNewPatternRule_thenReturnFEndSequenceIsEndSequenceToCharArray4() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule =
        new PatternRule("Start Sequence", "End Sequence", token, 'A', true, true, true, true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertTrue(actualPatternRule.fBreaksOnEOF);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertTrue(actualPatternRule.fEscapeContinuesLine);
    assertTrue(actualPatternRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualPatternRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>When {@code Start Sequence}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PatternRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewPatternRule_whenStartSequence_thenReturnFEndSequenceIsEmptyArrayOfChar() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule = new PatternRule("Start Sequence", null, token, 'A', true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertFalse(actualPatternRule.fBreaksOnEOF);
    assertFalse(actualPatternRule.fEscapeContinuesLine);
    assertFalse(actualPatternRule.fExcludeLineDelimiter);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualPatternRule.fEndSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code Start Sequence}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PatternRule.<init>(String, String, TPToken, char, boolean, boolean)"})
  public void testNewPatternRule_whenStartSequence_thenReturnFEndSequenceIsEmptyArrayOfChar2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule = new PatternRule("Start Sequence", null, token, 'A', true, true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertFalse(actualPatternRule.fEscapeContinuesLine);
    assertFalse(actualPatternRule.fExcludeLineDelimiter);
    assertTrue(actualPatternRule.fBreaksOnEOF);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualPatternRule.fEndSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code Start Sequence}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PatternRule.<init>(String, String, TPToken, char, boolean, boolean, boolean)"
  })
  public void testNewPatternRule_whenStartSequence_thenReturnFEndSequenceIsEmptyArrayOfChar3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule =
        new PatternRule("Start Sequence", null, token, 'A', true, true, true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertFalse(actualPatternRule.fExcludeLineDelimiter);
    assertTrue(actualPatternRule.fBreaksOnEOF);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertTrue(actualPatternRule.fEscapeContinuesLine);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualPatternRule.fEndSequence);
  }

  /**
   * Test {@link PatternRule#PatternRule(String, String, TPToken, char, boolean, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code Start Sequence}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#PatternRule(String, String, TPToken, char, boolean,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PatternRule.<init>(String, String, TPToken, char, boolean, boolean, boolean, boolean)"
  })
  public void testNewPatternRule_whenStartSequence_thenReturnFEndSequenceIsEmptyArrayOfChar4() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    PatternRule actualPatternRule =
        new PatternRule("Start Sequence", null, token, 'A', true, true, true, true);

    // Assert
    assertEquals('A', actualPatternRule.fEscapeCharacter);
    assertEquals(-1, actualPatternRule.fColumn);
    assertTrue(actualPatternRule.fBreaksOnEOF);
    assertTrue(actualPatternRule.fBreaksOnEOL);
    assertTrue(actualPatternRule.fEscapeContinuesLine);
    assertTrue(actualPatternRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualPatternRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualPatternRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualPatternRule.fEndSequence);
  }

  /**
   * Test {@link PatternRule#setColumnConstraint(int)}.
   *
   * <p>Method under test: {@link PatternRule#setColumnConstraint(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PatternRule.setColumnConstraint(int)"})
  public void testSetColumnConstraint() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);

    // Act
    patternRule.setColumnConstraint(1);

    // Assert
    assertEquals(1, patternRule.fColumn);
  }

  /**
   * Test {@link PatternRule#setColumnConstraint(int)}.
   *
   * <p>Method under test: {@link PatternRule#setColumnConstraint(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PatternRule.setColumnConstraint(int)"})
  public void testSetColumnConstraint2() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);

    // Act
    patternRule.setColumnConstraint(-1);

    // Assert that nothing has changed
    assertEquals(-1, patternRule.fColumn);
  }

  /**
   * Test {@link PatternRule#doEvaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <p>Method under test: {@link PatternRule#doEvaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.doEvaluate(TPCharacterScanner)"})
  public void testDoEvaluateWithScanner() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);

    // Act
    TPToken actualDoEvaluateResult = patternRule.doEvaluate(new TPRuleBasedScanner());

    // Assert
    TPToken successToken = patternRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).UNDEFINED, actualDoEvaluateResult);
  }

  /**
   * Test {@link PatternRule#doEvaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <p>Method under test: {@link PatternRule#doEvaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.doEvaluate(TPCharacterScanner)"})
  public void testDoEvaluateWithScanner2() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);

    // Act
    TPToken actualDoEvaluateResult = lineCommentRule.doEvaluate(new TPRuleBasedScanner());

    // Assert
    TPToken successToken = lineCommentRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).UNDEFINED, actualDoEvaluateResult);
  }

  /**
   * Test {@link PatternRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <p>Method under test: {@link PatternRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = patternRule.doEvaluate(scanner, false);

    // Assert
    TPToken successToken = patternRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertEquals(0, scanner.getOffset());
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualDoEvaluateResult).UNDEFINED, actualDoEvaluateResult);
  }

  /**
   * Test {@link PatternRule#doEvaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <ul>
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is one.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#doEvaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.doEvaluate(TPCharacterScanner, boolean)"})
  public void testDoEvaluateWithScannerResume_thenTPRuleBasedScannerOffsetIsOne() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule(
            "Start Sequence", "End Sequence", TPTokenAbstract.EOF, '\u0001', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualDoEvaluateResult = ((PatternRule) nestedMultiLineRule).doEvaluate(scanner, true);

    // Assert
    assertEquals(1, scanner.getOffset());
    TPToken tpToken = ((TPTokenAbstract) actualDoEvaluateResult).EOF;
    assertSame(tpToken, actualDoEvaluateResult);
    assertSame(tpToken, nestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link PatternRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <p>Method under test: {@link PatternRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);

    // Act
    TPToken actualEvaluateResult = patternRule.evaluate(new TPRuleBasedScanner());

    // Assert
    TPToken successToken = patternRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link PatternRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <p>Method under test: {@link PatternRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner2() {
    // Arrange
    LineCommentRule lineCommentRule = new LineCommentRule("Start Sequence", TPTokenAbstract.EOF);

    // Act
    TPToken actualEvaluateResult = lineCommentRule.evaluate(new TPRuleBasedScanner());

    // Assert
    TPToken successToken = lineCommentRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link PatternRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <p>Method under test: {@link PatternRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner3() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    patternRule.setColumnConstraint(1);

    // Act
    TPToken actualEvaluateResult = patternRule.evaluate(new TPRuleBasedScanner());

    // Assert
    TPToken successToken = patternRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link PatternRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <p>Method under test: {@link PatternRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualEvaluateResult = patternRule.evaluate(scanner, false);

    // Assert
    TPToken successToken = patternRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertEquals(0, scanner.getOffset());
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link PatternRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <p>Method under test: {@link PatternRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume2() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    patternRule.setColumnConstraint(1);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualEvaluateResult = patternRule.evaluate(scanner, true);

    // Assert
    TPToken successToken = patternRule.getSuccessToken();
    assertTrue(successToken instanceof TPTokenAbstract);
    assertEquals(0, scanner.getOffset());
    assertSame(((TPTokenAbstract) actualEvaluateResult).EOF, successToken);
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link PatternRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <ul>
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is one.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume_thenTPRuleBasedScannerOffsetIsOne() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, '￿', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    TPToken actualEvaluateResult = nestedMultiLineRule.evaluate(scanner, true);

    // Assert
    assertEquals(1, scanner.getOffset());
    TPToken tpToken = ((TPTokenAbstract) actualEvaluateResult).EOF;
    assertSame(tpToken, actualEvaluateResult);
    assertSame(tpToken, nestedMultiLineRule.getSuccessToken());
  }

  /**
   * Test {@link PatternRule#endSequenceDetected(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is one.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#endSequenceDetected(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PatternRule.endSequenceDetected(TPCharacterScanner)"})
  public void testEndSequenceDetected_thenTPRuleBasedScannerOffsetIsOne() {
    // Arrange
    NestedMultiLineRule nestedMultiLineRule =
        new NestedMultiLineRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualEndSequenceDetectedResult =
        ((PatternRule) nestedMultiLineRule).endSequenceDetected(scanner);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualEndSequenceDetectedResult);
  }

  /**
   * Test {@link PatternRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When array of {@code char} with {@code A} and {@code Z}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#sequenceDetected(TPCharacterScanner, char[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PatternRule.sequenceDetected(TPCharacterScanner, char[], boolean)"})
  public void testSequenceDetected_whenArrayOfCharWithAAndZ_thenReturnFalse() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualSequenceDetectedResult =
        patternRule.sequenceDetected(scanner, new char[] {'A', 'Z', 'A', 'Z'}, false);

    // Assert
    assertEquals(0, scanner.getOffset());
    assertFalse(actualSequenceDetectedResult);
  }

  /**
   * Test {@link PatternRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When {@code AZAZ} toCharArray.
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is one.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#sequenceDetected(TPCharacterScanner, char[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PatternRule.sequenceDetected(TPCharacterScanner, char[], boolean)"})
  public void testSequenceDetected_whenAzazToCharArray_thenTPRuleBasedScannerOffsetIsOne() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualSequenceDetectedResult =
        patternRule.sequenceDetected(scanner, "AZAZ".toCharArray(), true);

    // Assert
    assertEquals(1, scanner.getOffset());
    assertTrue(actualSequenceDetectedResult);
  }

  /**
   * Test {@link PatternRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When empty array of {@code char}.
   *   <li>Then {@link TPRuleBasedScanner} (default constructor) Offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link PatternRule#sequenceDetected(TPCharacterScanner, char[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PatternRule.sequenceDetected(TPCharacterScanner, char[], boolean)"})
  public void testSequenceDetected_whenEmptyArrayOfChar_thenTPRuleBasedScannerOffsetIsZero() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act
    boolean actualSequenceDetectedResult =
        patternRule.sequenceDetected(scanner, new char[] {}, true);

    // Assert
    assertEquals(0, scanner.getOffset());
    assertTrue(actualSequenceDetectedResult);
  }

  /**
   * Test {@link PatternRule#getSuccessToken()}.
   *
   * <p>Method under test: {@link PatternRule#getSuccessToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken PatternRule.getSuccessToken()"})
  public void testGetSuccessToken() {
    // Arrange
    PatternRule patternRule =
        new PatternRule("Start Sequence", "End Sequence", TPTokenAbstract.EOF, 'A', true);

    // Act
    TPToken actualSuccessToken = patternRule.getSuccessToken();

    // Assert
    assertSame(((TPTokenAbstract) actualSuccessToken).EOF, actualSuccessToken);
  }
}
