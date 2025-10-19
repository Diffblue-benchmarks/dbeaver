package org.jkiss.dbeaver.model.text.parser.rules;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MultiLineRuleDiffblueTest {
  /**
   * Test {@link MultiLineRule#MultiLineRule(String, String, TPToken)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link MultiLineRule#MultiLineRule(String, String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiLineRule.<init>(String, String, TPToken)"})
  public void testNewMultiLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    MultiLineRule actualMultiLineRule = new MultiLineRule("Start Sequence", "End Sequence", token);

    // Assert
    assertEquals('\u0000', actualMultiLineRule.fEscapeCharacter);
    assertEquals(-1, actualMultiLineRule.fColumn);
    assertFalse(actualMultiLineRule.fBreaksOnEOF);
    assertFalse(actualMultiLineRule.fBreaksOnEOL);
    assertFalse(actualMultiLineRule.fEscapeContinuesLine);
    assertFalse(actualMultiLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualMultiLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualMultiLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualMultiLineRule.fStartSequence);
  }

  /**
   * Test {@link MultiLineRule#MultiLineRule(String, String, TPToken, char)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link MultiLineRule#MultiLineRule(String, String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiLineRule.<init>(String, String, TPToken, char)"})
  public void testNewMultiLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    MultiLineRule actualMultiLineRule =
        new MultiLineRule("Start Sequence", "End Sequence", token, 'A');

    // Assert
    assertEquals('A', actualMultiLineRule.fEscapeCharacter);
    assertEquals(-1, actualMultiLineRule.fColumn);
    assertFalse(actualMultiLineRule.fBreaksOnEOF);
    assertFalse(actualMultiLineRule.fBreaksOnEOL);
    assertFalse(actualMultiLineRule.fEscapeContinuesLine);
    assertFalse(actualMultiLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualMultiLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualMultiLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualMultiLineRule.fStartSequence);
  }

  /**
   * Test {@link MultiLineRule#MultiLineRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link MultiLineRule#MultiLineRule(String, String, TPToken, char,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiLineRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewMultiLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    MultiLineRule actualMultiLineRule =
        new MultiLineRule("Start Sequence", "End Sequence", token, 'A', true);

    // Assert
    assertEquals('A', actualMultiLineRule.fEscapeCharacter);
    assertEquals(-1, actualMultiLineRule.fColumn);
    assertFalse(actualMultiLineRule.fBreaksOnEOL);
    assertFalse(actualMultiLineRule.fEscapeContinuesLine);
    assertFalse(actualMultiLineRule.fExcludeLineDelimiter);
    assertTrue(actualMultiLineRule.fBreaksOnEOF);
    assertSame(((TPTokenAbstract) token).EOF, actualMultiLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualMultiLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualMultiLineRule.fStartSequence);
  }

  /**
   * Test {@link MultiLineRule#MultiLineRule(String, String, TPToken)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link MultiLineRule#MultiLineRule(String, String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiLineRule.<init>(String, String, TPToken)"})
  public void testNewMultiLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    MultiLineRule actualMultiLineRule = new MultiLineRule("Start Sequence", null, token);

    // Assert
    assertEquals('\u0000', actualMultiLineRule.fEscapeCharacter);
    assertEquals(-1, actualMultiLineRule.fColumn);
    assertFalse(actualMultiLineRule.fBreaksOnEOF);
    assertFalse(actualMultiLineRule.fBreaksOnEOL);
    assertFalse(actualMultiLineRule.fEscapeContinuesLine);
    assertFalse(actualMultiLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualMultiLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualMultiLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualMultiLineRule.fEndSequence);
  }

  /**
   * Test {@link MultiLineRule#MultiLineRule(String, String, TPToken, char)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link MultiLineRule#MultiLineRule(String, String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiLineRule.<init>(String, String, TPToken, char)"})
  public void testNewMultiLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    MultiLineRule actualMultiLineRule = new MultiLineRule("Start Sequence", null, token, 'A');

    // Assert
    assertEquals('A', actualMultiLineRule.fEscapeCharacter);
    assertEquals(-1, actualMultiLineRule.fColumn);
    assertFalse(actualMultiLineRule.fBreaksOnEOF);
    assertFalse(actualMultiLineRule.fBreaksOnEOL);
    assertFalse(actualMultiLineRule.fEscapeContinuesLine);
    assertFalse(actualMultiLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualMultiLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualMultiLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualMultiLineRule.fEndSequence);
  }

  /**
   * Test {@link MultiLineRule#MultiLineRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link MultiLineRule#MultiLineRule(String, String, TPToken, char,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultiLineRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewMultiLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    MultiLineRule actualMultiLineRule = new MultiLineRule("Start Sequence", null, token, 'A', true);

    // Assert
    assertEquals('A', actualMultiLineRule.fEscapeCharacter);
    assertEquals(-1, actualMultiLineRule.fColumn);
    assertFalse(actualMultiLineRule.fBreaksOnEOL);
    assertFalse(actualMultiLineRule.fEscapeContinuesLine);
    assertFalse(actualMultiLineRule.fExcludeLineDelimiter);
    assertTrue(actualMultiLineRule.fBreaksOnEOF);
    assertSame(((TPTokenAbstract) token).EOF, actualMultiLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualMultiLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualMultiLineRule.fEndSequence);
  }
}
