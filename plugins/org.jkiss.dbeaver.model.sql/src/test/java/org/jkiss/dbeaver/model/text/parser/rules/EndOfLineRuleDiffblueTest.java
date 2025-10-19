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

public class EndOfLineRuleDiffblueTest {
  /**
   * Test {@link EndOfLineRule#EndOfLineRule(String, TPToken)}.
   *
   * <p>Method under test: {@link EndOfLineRule#EndOfLineRule(String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EndOfLineRule.<init>(String, TPToken)"})
  public void testNewEndOfLineRule() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    EndOfLineRule actualEndOfLineRule = new EndOfLineRule("Start Sequence", token);

    // Assert
    assertEquals('\u0000', actualEndOfLineRule.fEscapeCharacter);
    assertEquals(-1, actualEndOfLineRule.fColumn);
    assertFalse(actualEndOfLineRule.fEscapeContinuesLine);
    assertFalse(actualEndOfLineRule.fExcludeLineDelimiter);
    assertTrue(actualEndOfLineRule.fBreaksOnEOF);
    assertTrue(actualEndOfLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualEndOfLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualEndOfLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualEndOfLineRule.fEndSequence);
  }

  /**
   * Test {@link EndOfLineRule#EndOfLineRule(String, TPToken, char)}.
   *
   * <p>Method under test: {@link EndOfLineRule#EndOfLineRule(String, TPToken, char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EndOfLineRule.<init>(String, TPToken, char)"})
  public void testNewEndOfLineRule2() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    EndOfLineRule actualEndOfLineRule = new EndOfLineRule("Start Sequence", token, 'A');

    // Assert
    assertEquals('A', actualEndOfLineRule.fEscapeCharacter);
    assertEquals(-1, actualEndOfLineRule.fColumn);
    assertFalse(actualEndOfLineRule.fEscapeContinuesLine);
    assertFalse(actualEndOfLineRule.fExcludeLineDelimiter);
    assertTrue(actualEndOfLineRule.fBreaksOnEOF);
    assertTrue(actualEndOfLineRule.fBreaksOnEOL);
    assertSame(((TPTokenAbstract) token).EOF, actualEndOfLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualEndOfLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualEndOfLineRule.fEndSequence);
  }

  /**
   * Test {@link EndOfLineRule#EndOfLineRule(String, TPToken, char, boolean)}.
   *
   * <p>Method under test: {@link EndOfLineRule#EndOfLineRule(String, TPToken, char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EndOfLineRule.<init>(String, TPToken, char, boolean)"})
  public void testNewEndOfLineRule3() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    EndOfLineRule actualEndOfLineRule = new EndOfLineRule("Start Sequence", token, 'A', true);

    // Assert
    assertEquals('A', actualEndOfLineRule.fEscapeCharacter);
    assertEquals(-1, actualEndOfLineRule.fColumn);
    assertFalse(actualEndOfLineRule.fExcludeLineDelimiter);
    assertTrue(actualEndOfLineRule.fBreaksOnEOF);
    assertTrue(actualEndOfLineRule.fBreaksOnEOL);
    assertTrue(actualEndOfLineRule.fEscapeContinuesLine);
    assertSame(((TPTokenAbstract) token).EOF, actualEndOfLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualEndOfLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualEndOfLineRule.fEndSequence);
  }

  /**
   * Test {@link EndOfLineRule#EndOfLineRule(String, TPToken, char, boolean, boolean)}.
   *
   * <p>Method under test: {@link EndOfLineRule#EndOfLineRule(String, TPToken, char, boolean,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EndOfLineRule.<init>(String, TPToken, char, boolean, boolean)"})
  public void testNewEndOfLineRule4() {
    // Arrange
    TPToken token = TPTokenAbstract.EOF;

    // Act
    EndOfLineRule actualEndOfLineRule = new EndOfLineRule("Start Sequence", token, 'A', true, true);

    // Assert
    assertEquals('A', actualEndOfLineRule.fEscapeCharacter);
    assertEquals(-1, actualEndOfLineRule.fColumn);
    assertTrue(actualEndOfLineRule.fBreaksOnEOF);
    assertTrue(actualEndOfLineRule.fBreaksOnEOL);
    assertTrue(actualEndOfLineRule.fEscapeContinuesLine);
    assertTrue(actualEndOfLineRule.fExcludeLineDelimiter);
    assertSame(((TPTokenAbstract) token).EOF, actualEndOfLineRule.getSuccessToken());
    assertArrayEquals("Start Sequence".toCharArray(), actualEndOfLineRule.fStartSequence);
    assertArrayEquals(new char[] {}, actualEndOfLineRule.fEndSequence);
  }
}
