package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLVariableToken;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLVariableRuleDiffblueTest {
  /**
   * Test {@link SQLVariableRule#SQLVariableRule(SQLDialect)}.
   *
   * <p>Method under test: {@link SQLVariableRule#SQLVariableRule(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLVariableRule.<init>(SQLDialect)"})
  public void testNewSQLVariableRule() {
    // Arrange, Act and Assert
    TPToken successToken = new SQLVariableRule(mock(SQLDialect.class)).getSuccessToken();
    Object data = successToken.getData();
    assertTrue(data instanceof SQLTokenType);
    assertTrue(successToken instanceof SQLVariableToken);
    assertEquals(SQLTokenType.T_VARIABLE, data);
    assertFalse(successToken.isEOF());
    assertFalse(successToken.isOther());
    assertFalse(successToken.isUndefined());
    assertFalse(successToken.isWhitespace());
  }

  /**
   * Test {@link SQLVariableRule#getSuccessToken()}.
   *
   * <p>Method under test: {@link SQLVariableRule#getSuccessToken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.getSuccessToken()"})
  public void testGetSuccessToken() {
    // Arrange, Act and Assert
    assertTrue(
        new SQLVariableRule(mock(SQLDialect.class)).getSuccessToken() instanceof SQLVariableToken);
  }

  /**
   * Test {@link SQLVariableRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLVariableRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume_givenSQLDialectValidIdentifierPartReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    SQLVariableRule sqlVariableRule = new SQLVariableRule(dialect);

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(1);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = sqlVariableRule.evaluate(scanner, true);

    // Assert
    verify(dialect).validIdentifierPart('\u0001', false);
    verify(scanner, atLeast(1)).read();
    verify(scanner, atLeast(1)).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLVariableRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLVariableRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume_givenSQLDialectValidIdentifierPartReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    SQLVariableRule sqlVariableRule = new SQLVariableRule(dialect);

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(1);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = sqlVariableRule.evaluate(scanner, true);

    // Assert
    verify(dialect).validIdentifierPart('\u0001', false);
    verify(scanner).read();
    verify(scanner).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLVariableRule#evaluate(TPCharacterScanner, boolean)} with {@code scanner}, {@code
   * resume}.
   *
   * <ul>
   *   <li>Given {@link Double#SIZE}.
   *   <li>When {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#read()} return {@link
   *       Double#SIZE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLVariableRule#evaluate(TPCharacterScanner, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.evaluate(TPCharacterScanner, boolean)"})
  public void testEvaluateWithScannerResume_givenSize_whenTPRuleBasedScannerReadReturnSize() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    SQLVariableRule sqlVariableRule = new SQLVariableRule(dialect);

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(Double.SIZE);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = sqlVariableRule.evaluate(scanner, true);

    // Assert
    verify(dialect, atLeast(1)).validIdentifierPart('@', false);
    verify(scanner, atLeast(1)).read();
    verify(scanner, atLeast(1)).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLVariableRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLVariableRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner_givenSQLDialectValidIdentifierPartReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    SQLVariableRule sqlVariableRule = new SQLVariableRule(dialect);

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(1);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = sqlVariableRule.evaluate(scanner);

    // Assert
    verify(dialect).validIdentifierPart('\u0001', false);
    verify(scanner, atLeast(1)).read();
    verify(scanner, atLeast(1)).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLVariableRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLVariableRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner_givenSQLDialectValidIdentifierPartReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    SQLVariableRule sqlVariableRule = new SQLVariableRule(dialect);

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(1);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = sqlVariableRule.evaluate(scanner);

    // Assert
    verify(dialect).validIdentifierPart('\u0001', false);
    verify(scanner).read();
    verify(scanner).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link SQLVariableRule#evaluate(TPCharacterScanner)} with {@code scanner}.
   *
   * <ul>
   *   <li>Given {@link Double#SIZE}.
   *   <li>When {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#read()} return {@link
   *       Double#SIZE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLVariableRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLVariableRule.evaluate(TPCharacterScanner)"})
  public void testEvaluateWithScanner_givenSize_whenTPRuleBasedScannerReadReturnSize() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    SQLVariableRule sqlVariableRule = new SQLVariableRule(dialect);

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(Double.SIZE);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = sqlVariableRule.evaluate(scanner);

    // Assert
    verify(dialect, atLeast(1)).validIdentifierPart('@', false);
    verify(scanner, atLeast(1)).read();
    verify(scanner, atLeast(1)).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
