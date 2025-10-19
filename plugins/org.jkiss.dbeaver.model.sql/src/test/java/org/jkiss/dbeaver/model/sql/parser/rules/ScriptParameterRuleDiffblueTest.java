package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.Plugin;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLParameterToken;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScriptParameterRuleDiffblueTest {
  /**
   * Test {@link ScriptParameterRule#ScriptParameterRule(SQLSyntaxManager, SQLParameterToken,
   * String)}.
   *
   * <p>Method under test: {@link ScriptParameterRule#ScriptParameterRule(SQLSyntaxManager,
   * SQLParameterToken, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScriptParameterRule.<init>(SQLSyntaxManager, SQLParameterToken, String)"
  })
  public void testNewScriptParameterRule() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    // Act
    ScriptParameterRule actualScriptParameterRule =
        new ScriptParameterRule(syntaxManager, new SQLParameterToken(), "Prefix");
    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(1);
    doNothing().when(scanner).unread();
    TPToken actualEvaluateResult = actualScriptParameterRule.evaluate(scanner);

    // Assert
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(scanner).read();
    verify(scanner).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link ScriptParameterRule#ScriptParameterRule(SQLSyntaxManager, SQLParameterToken,
   * String)}.
   *
   * <p>Method under test: {@link ScriptParameterRule#ScriptParameterRule(SQLSyntaxManager,
   * SQLParameterToken, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScriptParameterRule.<init>(SQLSyntaxManager, SQLParameterToken, String)"
  })
  public void testNewScriptParameterRule2() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    // Act
    ScriptParameterRule actualScriptParameterRule =
        new ScriptParameterRule(syntaxManager, new SQLParameterToken(), "Prefix");
    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(-1);
    doNothing().when(scanner).unread();
    TPToken actualEvaluateResult = actualScriptParameterRule.evaluate(scanner);

    // Assert
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(scanner, atLeast(1)).read();
    verify(scanner, atLeast(1)).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link ScriptParameterRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>When {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#read()} return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ScriptParameterRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken ScriptParameterRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_givenMinusOne_whenTPRuleBasedScannerReadReturnMinusOne() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    ScriptParameterRule scriptParameterRule =
        new ScriptParameterRule(syntaxManager, new SQLParameterToken(), "Prefix");

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(-1);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = scriptParameterRule.evaluate(scanner);

    // Assert
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(scanner, atLeast(1)).read();
    verify(scanner, atLeast(1)).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link ScriptParameterRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#read()} return one.
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link ScriptParameterRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken ScriptParameterRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_givenOne_whenTPRuleBasedScannerReadReturnOne_thenReturnUndefined() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    ScriptParameterRule scriptParameterRule =
        new ScriptParameterRule(syntaxManager, new SQLParameterToken(), "Prefix");

    TPRuleBasedScanner scanner = mock(TPRuleBasedScanner.class);
    when(scanner.read()).thenReturn(1);
    doNothing().when(scanner).unread();

    // Act
    TPToken actualEvaluateResult = scriptParameterRule.evaluate(scanner);

    // Assert
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getIdentifierQuoteStrings();
    verify(scanner).read();
    verify(scanner).unread();
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }

  /**
   * Test {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect, CharSequence, int)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ScriptParameterRule.tryConsumeParameterName(SQLDialect, CharSequence, int)"
  })
  public void testTryConsumeParameterName_givenNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings()).thenReturn(null);

    // Act
    int actualTryConsumeParameterNameResult =
        ScriptParameterRule.tryConsumeParameterName(sqlDialect, Plugin.PLUGIN_PREFERENCE_SCOPE, 1);

    // Assert
    verify(sqlDialect).getIdentifierQuoteStrings();
    assertEquals(8, actualTryConsumeParameterNameResult);
  }

  /**
   * Test {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect, CharSequence, int)}.
   *
   * <ul>
   *   <li>Then return eight.
   * </ul>
   *
   * <p>Method under test: {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ScriptParameterRule.tryConsumeParameterName(SQLDialect, CharSequence, int)"
  })
  public void testTryConsumeParameterName_thenReturnEight() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    // Act
    int actualTryConsumeParameterNameResult =
        ScriptParameterRule.tryConsumeParameterName(sqlDialect, Plugin.PLUGIN_PREFERENCE_SCOPE, 1);

    // Assert
    verify(sqlDialect).getIdentifierQuoteStrings();
    assertEquals(8, actualTryConsumeParameterNameResult);
  }

  /**
   * Test {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect, CharSequence, int)}.
   *
   * <ul>
   *   <li>Then return eleven.
   * </ul>
   *
   * <p>Method under test: {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ScriptParameterRule.tryConsumeParameterName(SQLDialect, CharSequence, int)"
  })
  public void testTryConsumeParameterName_thenReturnEleven() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    // Act
    int actualTryConsumeParameterNameResult =
        ScriptParameterRule.tryConsumeParameterName(
            sqlDialect, Plugin.PREFERENCES_DEFAULT_OVERRIDE_FILE_NAME, 1);

    // Assert
    verify(sqlDialect).getIdentifierQuoteStrings();
    assertEquals(11, actualTryConsumeParameterNameResult);
  }

  /**
   * Test {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect, CharSequence, int)}.
   *
   * <ul>
   *   <li>When lineSeparator.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ScriptParameterRule#tryConsumeParameterName(SQLDialect,
   * CharSequence, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int ScriptParameterRule.tryConsumeParameterName(SQLDialect, CharSequence, int)"
  })
  public void testTryConsumeParameterName_whenLineSeparator_thenReturnMinusOne() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});

    // Act
    int actualTryConsumeParameterNameResult =
        ScriptParameterRule.tryConsumeParameterName(sqlDialect, System.lineSeparator(), 1);

    // Assert
    verify(sqlDialect).getIdentifierQuoteStrings();
    assertEquals(-1, actualTryConsumeParameterNameResult);
  }
}
