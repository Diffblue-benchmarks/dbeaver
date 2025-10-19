package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.SQLRuleManager;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRule;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.jkiss.dbeaver.model.text.parser.TPTokenDefault;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLTokenPredicateFactoryDiffblueTest {
  /**
   * Test {@link SQLTokenPredicateFactory#SQLTokenPredicateFactory(SQLRuleManager)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect}.
   *   <li>Then not {@code Str} return {@link SQLTokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenPredicateFactory#SQLTokenPredicateFactory(SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokenPredicateFactory.<init>(SQLRuleManager)"})
  public void testNewSQLTokenPredicateFactory_givenSQLDialect_thenNotStrReturnSQLTokenEntry() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    SQLRuleManager ruleManager = new SQLRuleManager(syntaxManager);

    // Act
    SQLTokenPredicateFactory actualSqlTokenPredicateFactory =
        new SQLTokenPredicateFactory(ruleManager);

    // Assert
    verify(syntaxManager).getDialect();
    TokenPredicateNode notResult = actualSqlTokenPredicateFactory.not("Str");
    assertTrue(notResult instanceof SQLTokenEntry);
    assertEquals("Str", ((SQLTokenEntry) notResult).getString());
    assertNull(((SQLTokenEntry) notResult).getTokenType());
    assertNull(actualSqlTokenPredicateFactory.classifyToken("String"));
    assertTrue(((SQLTokenEntry) notResult).isInverted());
  }

  /**
   * Test {@link SQLTokenPredicateFactory#classifyToken(String)}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#EOF}.
   *   <li>Then return {@code T_OTHER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenPredicateFactory#classifyToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType SQLTokenPredicateFactory.classifyToken(String)"})
  public void testClassifyToken_givenTPRuleEvaluateReturnEof_thenReturnTOther() {
    // Arrange
    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    SQLRuleManager ruleManager = mock(SQLRuleManager.class);
    when(ruleManager.getAllRules()).thenReturn(new TPRule[] {tpRule});

    // Act
    SQLTokenType actualClassifyTokenResult =
        new SQLTokenPredicateFactory(ruleManager).classifyToken("String");

    // Assert
    verify(ruleManager).getAllRules();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertEquals(SQLTokenType.T_OTHER, actualClassifyTokenResult);
  }

  /**
   * Test {@link SQLTokenPredicateFactory#classifyToken(String)}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenPredicateFactory#classifyToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType SQLTokenPredicateFactory.classifyToken(String)"})
  public void testClassifyToken_givenTPRuleEvaluateReturnNull_thenReturnNull() {
    // Arrange
    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(null);

    SQLRuleManager ruleManager = mock(SQLRuleManager.class);
    when(ruleManager.getAllRules()).thenReturn(new TPRule[] {tpRule});

    // Act
    SQLTokenType actualClassifyTokenResult =
        new SQLTokenPredicateFactory(ruleManager).classifyToken("String");

    // Assert
    verify(ruleManager).getAllRules();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualClassifyTokenResult);
  }

  /**
   * Test {@link SQLTokenPredicateFactory#classifyToken(String)}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#UNDEFINED}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenPredicateFactory#classifyToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType SQLTokenPredicateFactory.classifyToken(String)"})
  public void testClassifyToken_givenTPRuleEvaluateReturnUndefined_thenReturnNull() {
    // Arrange
    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.UNDEFINED);

    SQLRuleManager ruleManager = mock(SQLRuleManager.class);
    when(ruleManager.getAllRules()).thenReturn(new TPRule[] {tpRule});

    // Act
    SQLTokenType actualClassifyTokenResult =
        new SQLTokenPredicateFactory(ruleManager).classifyToken("String");

    // Assert
    verify(ruleManager).getAllRules();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualClassifyTokenResult);
  }

  /**
   * Test {@link SQLTokenPredicateFactory#classifyToken(String)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLSyntaxManager#getDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenPredicateFactory#classifyToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType SQLTokenPredicateFactory.classifyToken(String)"})
  public void testClassifyToken_thenCallsGetDialect() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    SQLRuleManager ruleManager = new SQLRuleManager(syntaxManager);

    // Act
    SQLTokenType actualClassifyTokenResult =
        new SQLTokenPredicateFactory(ruleManager).classifyToken("String");

    // Assert
    verify(syntaxManager).getDialect();
    assertNull(actualClassifyTokenResult);
  }

  /**
   * Test {@link SQLTokenPredicateFactory#classifyToken(String)}.
   *
   * <ul>
   *   <li>Then return {@code T_BLOCK_BEGIN}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokenPredicateFactory#classifyToken(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenType SQLTokenPredicateFactory.classifyToken(String)"})
  public void testClassifyToken_thenReturnTBlockBegin() {
    // Arrange
    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any()))
        .thenReturn(new TPTokenDefault(SQLTokenType.T_BLOCK_BEGIN));

    SQLRuleManager ruleManager = mock(SQLRuleManager.class);
    when(ruleManager.getAllRules()).thenReturn(new TPRule[] {tpRule});

    // Act
    SQLTokenType actualClassifyTokenResult =
        new SQLTokenPredicateFactory(ruleManager).classifyToken("String");

    // Assert
    verify(ruleManager).getAllRules();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertEquals(SQLTokenType.T_BLOCK_BEGIN, actualClassifyTokenResult);
  }
}
