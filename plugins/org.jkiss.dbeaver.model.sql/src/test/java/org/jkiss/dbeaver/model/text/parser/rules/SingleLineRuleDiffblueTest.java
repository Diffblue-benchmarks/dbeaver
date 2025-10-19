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

public class SingleLineRuleDiffblueTest {
  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken)"})
  public void testNewSingleLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", "End Sequence", token);

    // Assert
    assertEquals('\u0000', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fBreaksOnEOF);
    assertFalse(actualSingleLineRule.fEscapeContinuesLine);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualSingleLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken, char)"})
  public void testNewSingleLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", "End Sequence", token, 'A');

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fBreaksOnEOF);
    assertFalse(actualSingleLineRule.fEscapeContinuesLine);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualSingleLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewSingleLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", "End Sequence", token, 'A', true);

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fEscapeContinuesLine);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOF);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualSingleLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken, char, boolean, boolean)"})
  public void testNewSingleLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray4() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", "End Sequence", token, 'A', true, true);

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOF);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertTrue(actualSingleLineRule.fEscapeContinuesLine);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualSingleLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return {@link PatternRule#fEndSequence} is {@code End Sequence} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SingleLineRule.<init>(String, String, TPToken, char, boolean, boolean, boolean)"
  })
  public void testNewSingleLineRule_thenReturnFEndSequenceIsEndSequenceToCharArray5() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", "End Sequence", token, 'A', true, true, true);

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertTrue(actualSingleLineRule.fBreaksOnEOF);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertTrue(actualSingleLineRule.fEscapeContinuesLine);
    assertTrue(actualSingleLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("End Sequence".toCharArray(), actualSingleLineRule.fEndSequence);
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken)"})
  public void testNewSingleLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule = new SingleLineRule("Start Sequence", null, token);

    // Assert
    assertEquals('\u0000', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fBreaksOnEOF);
    assertFalse(actualSingleLineRule.fEscapeContinuesLine);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualSingleLineRule.fEndSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken, char)"})
  public void testNewSingleLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule = new SingleLineRule("Start Sequence", null, token, 'A');

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fBreaksOnEOF);
    assertFalse(actualSingleLineRule.fEscapeContinuesLine);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualSingleLineRule.fEndSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken, char, boolean)"})
  public void testNewSingleLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", null, token, 'A', true);

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fEscapeContinuesLine);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOF);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualSingleLineRule.fEndSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SingleLineRule.<init>(String, String, TPToken, char, boolean, boolean)"})
  public void testNewSingleLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar4() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", null, token, 'A', true, true);

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertFalse(actualSingleLineRule.fExcludeLineDelimiter);
    assertTrue(actualSingleLineRule.fBreaksOnEOF);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertTrue(actualSingleLineRule.fEscapeContinuesLine);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualSingleLineRule.fEndSequence);
  }

  /**
   * Test {@link SingleLineRule#SingleLineRule(String, String, TPToken, char, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PatternRule#fEndSequence} is empty array of {@code char}.
   * </ul>
   *
   * <p>Method under test: {@link SingleLineRule#SingleLineRule(String, String, TPToken, char,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SingleLineRule.<init>(String, String, TPToken, char, boolean, boolean, boolean)"
  })
  public void testNewSingleLineRule_whenNull_thenReturnFEndSequenceIsEmptyArrayOfChar5() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    SingleLineRule actualSingleLineRule =
        new SingleLineRule("Start Sequence", null, token, 'A', true, true, true);

    // Assert
    assertEquals('A', actualSingleLineRule.fEscapeCharacter);
    assertEquals(-1, actualSingleLineRule.fColumn);
    assertTrue(actualSingleLineRule.fBreaksOnEOF);
    assertTrue(actualSingleLineRule.fBreaksOnEOL);
    assertTrue(actualSingleLineRule.fEscapeContinuesLine);
    assertTrue(actualSingleLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualSingleLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualSingleLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualSingleLineRule.fEndSequence);
  }
}
