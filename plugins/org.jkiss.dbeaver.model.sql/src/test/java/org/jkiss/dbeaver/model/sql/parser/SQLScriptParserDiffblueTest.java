package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.List;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TypedRegion;
import org.eclipse.jface.text.projection.ChildDocument;
import org.eclipse.jface.text.rules.DefaultPartitioner;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.jkiss.dbeaver.ModelPreferences;
import org.jkiss.dbeaver.ModelPreferences.SQLScriptStatementDelimiterMode;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.jkiss.dbeaver.model.sql.parser.tokens.predicates.TokenPredicateSet;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRule;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.jkiss.dbeaver.model.text.parser.TPTokenDefault;
import org.jkiss.dbeaver.utils.ListNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLScriptParserDiffblueTest {
  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new Document());

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(context).getDocument();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters2() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    assertTrue(actualParseQueryResult instanceof SQLQuery);
    assertEquals("ot", actualParseQueryResult.toString());
    assertEquals("ot", actualParseQueryResult.getOriginalText());
    assertEquals("ot", actualParseQueryResult.getText());
    assertEquals(1, actualParseQueryResult.getOffset());
    assertEquals(2, actualParseQueryResult.getLength());
    assertFalse(((SQLQuery) actualParseQueryResult).isEndsWithDelimiter());
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters3() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(null);
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    assertTrue(actualParseQueryResult instanceof SQLQuery);
    assertEquals("ot", actualParseQueryResult.toString());
    assertEquals("ot", actualParseQueryResult.getOriginalText());
    assertEquals("ot", actualParseQueryResult.getText());
    assertEquals(1, actualParseQueryResult.getOffset());
    assertEquals(2, actualParseQueryResult.getLength());
    assertFalse(((SQLQuery) actualParseQueryResult).isEndsWithDelimiter());
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters4() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    assertTrue(actualParseQueryResult instanceof SQLQuery);
    assertEquals("ot", actualParseQueryResult.toString());
    assertEquals("ot", actualParseQueryResult.getOriginalText());
    assertEquals("ot", actualParseQueryResult.getText());
    assertEquals(1, actualParseQueryResult.getOffset());
    assertEquals(2, actualParseQueryResult.getLength());
    assertFalse(((SQLQuery) actualParseQueryResult).isEndsWithDelimiter());
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters5() {
    // Arrange
    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertTrue(actualParseQueryResult instanceof SQLQuery);
    assertEquals("ot", actualParseQueryResult.toString());
    assertEquals("ot", actualParseQueryResult.getOriginalText());
    assertEquals("ot", actualParseQueryResult.getText());
    assertEquals(1, actualParseQueryResult.getOffset());
    assertEquals(2, actualParseQueryResult.getLength());
    assertFalse(((SQLQuery) actualParseQueryResult).isEndsWithDelimiter());
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters6() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new ChildDocument(new Document()));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(context).getDocument();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters7() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters8() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.SMART);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters9() {
    // Arrange
    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.UNDEFINED);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    verify(tpRule, atLeast(1)).evaluate(isA(TPCharacterScanner.class));
    assertTrue(actualParseQueryResult instanceof SQLQuery);
    assertEquals("ot", actualParseQueryResult.toString());
    assertEquals("ot", actualParseQueryResult.getOriginalText());
    assertEquals("ot", actualParseQueryResult.getText());
    assertEquals(1, actualParseQueryResult.getOffset());
    assertEquals(2, actualParseQueryResult.getLength());
    assertFalse(((SQLQuery) actualParseQueryResult).isEndsWithDelimiter());
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters10() {
    // Arrange
    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters11() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters12() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.SMART);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters13() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(mock(TPTokenDefault.class));
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters14() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(mock(TPTokenDefault.class));
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters15()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters16()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.SMART);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters17()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters18()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {null});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters19() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(0);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters20()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(false);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument, atLeast(1)).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters21()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_END);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters22()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_HEADER);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters23()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_TOGGLE);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument, atLeast(1)).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters24()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_COMMENT);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters25()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_DELIMITER);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet, atLeast(1)).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters26()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(false);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters27()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("\n");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, true, true);

    // Assert
    verify(iDocument, atLeast(1)).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet, atLeast(1)).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters28() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(mock(IDocument.class));

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 3, 3, 1, true, true);

    // Assert
    verify(context).getDocument();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int, boolean, boolean)} with
   * {@code context}, {@code startPos}, {@code endPos}, {@code currentPos}, {@code scriptMode},
   * {@code keepDelimiters}.
   *
   * <p>Method under test: {@link SQLScriptParser#parseQuery(SQLParserContext, int, int, int,
   * boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.parseQuery(SQLParserContext, int, int, int, boolean, boolean)"
  })
  public void testParseQueryWithContextStartPosEndPosCurrentPosScriptModeKeepDelimiters29()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getDocument()).thenReturn(iDocument);

    // Act
    SQLScriptElement actualParseQueryResult =
        SQLScriptParser.parseQuery(context, 1, 3, 1, false, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDialect();
    verify(context).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(2));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertNull(actualParseQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.toString());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getText());
    assertEquals(27, actualExtractQueryAtPosResult.getLength());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos2() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.toString());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getText());
    assertEquals(27, actualExtractQueryAtPosResult.getLength());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos3() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.ONLY_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.toString());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getText());
    assertEquals(27, actualExtractQueryAtPosResult.getLength());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos4() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOfOffset(anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));
    String[] legalContentTypes = new String[] {"text/plain"};
    DefaultPartitioner defaultPartitioner =
        new DefaultPartitioner(new RuleBasedPartitionScanner(), legalContentTypes);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(defaultPartitioner);
    when(document.getLength()).thenReturn(3);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(mock(SQLSyntaxManager.class));
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document).getLineOfOffset(1);
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos5() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters())
        .thenReturn(new String[] {" -- name : U", SQLParserPartitions.SQL_PARTITIONING});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Get", actualExtractQueryAtPosResult.toString());
    assertEquals("Get", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Get", actualExtractQueryAtPosResult.getText());
    assertEquals(1, actualExtractQueryAtPosResult.getLength());
    assertEquals(2, actualExtractQueryAtPosResult.getOffset());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos6() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.ONLY_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document).getLineInformation(2);
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Get", actualExtractQueryAtPosResult.toString());
    assertEquals("Get", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Get", actualExtractQueryAtPosResult.getText());
    assertEquals(1, actualExtractQueryAtPosResult.getLength());
    assertEquals(2, actualExtractQueryAtPosResult.getOffset());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos7() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 0));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos8() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, -1));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Get", actualExtractQueryAtPosResult.toString());
    assertEquals("Get", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Get", actualExtractQueryAtPosResult.getText());
    assertEquals(1, actualExtractQueryAtPosResult.getLength());
    assertEquals(2, actualExtractQueryAtPosResult.getOffset());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos9() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document).get(2, 3);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(2);
    verify(document).getLineOfOffset(1);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos10() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt())).thenReturn("text/plain");

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(Integer.MIN_VALUE, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(-2147483648), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos11() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt()))
        .thenReturn(SQLParserPartitions.CONTENT_TYPE_SQL_COMMENT);

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(1);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos12() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt()))
        .thenReturn(SQLParserPartitions.CONTENT_TYPE_SQL_MULTILINE_COMMENT);

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(anyInt());
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ChildDocument#ChildDocument(IDocument)} with masterDocument is {@link
   *       Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenChildDocumentWithMasterDocumentIsDocument() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new ChildDocument(new Document()));

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(context).getDocument();
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocument() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new Document());

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(context).getDocument();
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getLength()} return one.
   *   <li>Then calls {@link TPRuleBasedScanner#setRules(TPRule[])}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetLengthReturnOne_thenCallsSetRules()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(1);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getLineInformation(int)} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetLineInformationReturnNull_thenReturnNull()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(null);
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getLineOffset(int)} return minus one.
   *   <li>Then calls {@link TPRuleBasedScanner#setRules(TPRule[])}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetLineOffsetReturnMinusOne_thenCallsSetRules()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getLineOffset(int)} return three.
   *   <li>Then calls {@link TPRuleBasedScanner#setRules(TPRule[])}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetLineOffsetReturnThree_thenCallsSetRules()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(3);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getNumberOfLines()} return five.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetNumberOfLinesReturnFive()
      throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt())).thenReturn("text/plain");

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(5);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(1);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#get(int, int)} return lf.
   *   <li>Then calls {@link TPRuleBasedScanner#setRules(TPRule[])}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetReturnLf_thenCallsSetRules()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.get(anyInt(), anyInt())).thenReturn("\n");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(2, 3);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#get(int, int)} return {@code MD}.
   *   <li>Then calls {@link TPRuleBasedScanner#getTokenLength()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenDocumentGetReturnMd_thenCallsGetTokenLength()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("MD");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link IDocumentPartitioner} {@link IDocumentPartitioner#getContentType(int)}
   *       return {@code text/plain}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenIDocumentPartitionerGetContentTypeReturnTextPlain()
      throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt())).thenReturn("text/plain");

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(1);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#isStripCommentsBeforeBlocks()} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenSQLDialectIsStripCommentsBeforeBlocksReturnFalse()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(false);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#getTokenLength()} return one.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenTPRuleBasedScannerGetTokenLengthReturnOne()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} (default constructor).
   *   <li>Then return toString is {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenTPRuleBasedScanner_thenReturnToStringIsGet()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Get", actualExtractQueryAtPosResult.toString());
    assertEquals("Get", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Get", actualExtractQueryAtPosResult.getText());
    assertEquals(1, actualExtractQueryAtPosResult.getLength());
    assertEquals(2, actualExtractQueryAtPosResult.getOffset());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#EOF}.
   *   <li>Then calls {@link TPRule#evaluate(TPCharacterScanner)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenTPRuleEvaluateReturnEof_thenCallsEvaluate()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#UNDEFINED}.
   *   <li>Then calls {@link TPRule#evaluate(TPCharacterScanner)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_givenTPRuleEvaluateReturnUndefined_thenCallsEvaluate()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.UNDEFINED);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    verify(tpRule, atLeast(1)).evaluate(isA(TPCharacterScanner.class));
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Get", actualExtractQueryAtPosResult.toString());
    assertEquals("Get", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Get", actualExtractQueryAtPosResult.getText());
    assertEquals(1, actualExtractQueryAtPosResult.getLength());
    assertEquals(2, actualExtractQueryAtPosResult.getOffset());
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link TPRuleBasedScanner#getTokenLength()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_thenCallsGetTokenLength() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(1);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractQueryAtPosResult);
  }

  /**
   * Test {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}.
   *
   * <ul>
   *   <li>Then return toString is {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractQueryAtPos(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractQueryAtPos(SQLParserContext, int, boolean)"
  })
  public void testExtractQueryAtPos_thenReturnToStringIsNotAllWhoWanderAreLost() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractQueryAtPosResult =
        SQLScriptParser.extractQueryAtPos(context, 1, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertTrue(actualExtractQueryAtPosResult instanceof SQLQuery);
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.toString());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getOriginalText());
    assertEquals("Not all who wander are lost", actualExtractQueryAtPosResult.getText());
    assertEquals(27, actualExtractQueryAtPosResult.getLength());
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, SQLScriptElement, boolean)} with
   * {@code context}, {@code curElement}, {@code next}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext,
   * SQLScriptElement, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, SQLScriptElement, boolean)"
  })
  public void testExtractNextQueryWithContextCurElementNext_whenNull_thenReturnNull() {
    // Arrange and Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(null, null, true);

    // Assert
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext2() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext3() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.ONLY_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext4() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new ChildDocument(new Document()));

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(context).getDocument();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext5() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOfOffset(anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));
    String[] legalContentTypes = new String[] {"text/plain"};
    DefaultPartitioner defaultPartitioner =
        new DefaultPartitioner(new RuleBasedPartitionScanner(), legalContentTypes);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(defaultPartitioner);
    when(document.getLength()).thenReturn(3);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(mock(SQLSyntaxManager.class));
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document).getLineOfOffset(2);
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext6() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenThrow(new BadLocationException("An error occurred"));
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document, atLeast(1)).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext7() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters())
        .thenReturn(new String[] {" -- name : U", SQLParserPartitions.SQL_PARTITIONING});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document, atLeast(1)).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext8() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.ONLY_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document, atLeast(1)).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document).getLineInformation(2);
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext9() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 0));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext10() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, -1));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document, atLeast(1)).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext11() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(null);
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext12() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext13() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(false);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext14() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document).get(2, 3);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(2);
    verify(document).getLineOfOffset(2);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext15() throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(-1);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext16() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt())).thenReturn("text/plain");

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(2);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext17() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt())).thenReturn("text/plain");

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(-1, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(-1, 3);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext18() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getContentType(anyInt())).thenReturn("text/plain");

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(5);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(2);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext19() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getPartition(anyInt())).thenReturn(new TypedRegion(2, 3, "Type"));
    when(iDocumentPartitioner.getContentType(anyInt()))
        .thenReturn(SQLParserPartitions.CONTENT_TYPE_SQL_COMMENT);

    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getChar(2);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(2);
    verify(iDocumentPartitioner).getPartition(2);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext20() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getPartition(anyInt()))
        .thenReturn(new TypedRegion(2, 3, SQLParserPartitions.CONTENT_TYPE_SQL_COMMENT));
    when(iDocumentPartitioner.getContentType(anyInt()))
        .thenReturn(SQLParserPartitions.CONTENT_TYPE_SQL_COMMENT);

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(2);
    verify(iDocumentPartitioner).getPartition(2);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext21() throws BadLocationException {
    // Arrange
    IDocumentPartitioner iDocumentPartitioner = mock(IDocumentPartitioner.class);
    when(iDocumentPartitioner.getPartition(anyInt()))
        .thenReturn(new TypedRegion(2, 3, SQLParserPartitions.CONTENT_TYPE_SQL_MULTILINE_COMMENT));
    when(iDocumentPartitioner.getContentType(anyInt()))
        .thenReturn(SQLParserPartitions.CONTENT_TYPE_SQL_COMMENT);

    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getNumberOfLines()).thenReturn(2);
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(iDocumentPartitioner);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document, atLeast(1)).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(document).getNumberOfLines();
    verify(iDocumentPartitioner, atLeast(1)).getContentType(2);
    verify(iDocumentPartitioner).getPartition(2);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenDocument() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new Document());

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(context).getDocument();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getLength()} return one.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenDocumentGetLengthReturnOne()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(1);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#getLineOffset(int)} return three.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenDocumentGetLineOffsetReturnThree()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(3);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#get(int, int)} return lf.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenDocumentGetReturnLf()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.get(anyInt(), anyInt())).thenReturn("\n");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(2, 3);
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context).getDocument();
    verify(context).getSyntaxManager();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link Document} {@link Document#get(int, int)} return {@code MD}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenDocumentGetReturnMd()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("MD");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#EOF}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenTPRuleEvaluateReturnEof()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_givenTPRuleEvaluateReturnUndefined()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.UNDEFINED);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document, atLeast(1)).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    verify(tpRule, atLeast(1)).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Then calls {@link TrieNode#accumulateSubnodesByTerm(Object, ListNode)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_thenCallsAccumulateSubnodesByTerm() {
    // Arrange
    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet, atLeast(1)).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Then calls {@link Document#getChar(int)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_thenCallsGetChar()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getChar(anyInt())).thenReturn('A');
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(context.getScanner()).thenReturn(new TPRuleBasedScanner());
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document, atLeast(1)).getChar(2);
    verify(document, atLeast(1)).getDocumentPartitioner("___sql_partitioning");
    verify(document).getLegalLineDelimiters();
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect, atLeast(1)).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDataSource();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)} with {@code
   * context}, {@code offset}, {@code next}.
   *
   * <ul>
   *   <li>Then calls {@link TPRuleBasedScanner#getTokenLength()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractNextQuery(SQLParserContext, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractNextQuery(SQLParserContext, int, boolean)"
  })
  public void testExtractNextQueryWithContextOffsetNext_thenCallsGetTokenLength()
      throws BadLocationException {
    // Arrange
    Document document = mock(Document.class);
    when(document.getLineOffset(anyInt())).thenReturn(2);
    when(document.get(anyInt(), anyInt())).thenReturn("Get");
    when(document.getLineOfOffset(anyInt())).thenReturn(2);
    when(document.getDocumentPartitioner(Mockito.<String>any())).thenReturn(null);
    when(document.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(document.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(document);

    // Act
    SQLScriptElement actualExtractNextQueryResult =
        SQLScriptParser.extractNextQuery(context, 2, true);

    // Assert
    verify(document, atLeast(1)).get(eq(2), anyInt());
    verify(document).getDocumentPartitioner("___sql_partitioning");
    verify(document, atLeast(1)).getLength();
    verify(document, atLeast(1)).getLineInformation(anyInt());
    verify(document).getLineOfOffset(2);
    verify(document, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(2), eq(1));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertNull(actualExtractNextQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractActiveQuery(SQLParserContext, IRegion[])} with {@code
   * context}, {@code regions}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractActiveQuery(SQLParserContext, IRegion[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractActiveQuery(SQLParserContext, IRegion[])"
  })
  public void testExtractActiveQueryWithContextRegions_thenReturnNull() {
    // Arrange and Act
    SQLScriptElement actualExtractActiveQueryResult =
        SQLScriptParser.extractActiveQuery(null, new IRegion[] {new Region(-1, 3)});

    // Assert
    assertNull(actualExtractActiveQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)} with {@code
   * context}, {@code selOffset}, {@code selLength}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractActiveQuery(SQLParserContext, int, int)"
  })
  public void testExtractActiveQueryWithContextSelOffsetSelLength() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new ChildDocument(new Document()));

    // Act
    SQLScriptElement actualExtractActiveQueryResult =
        SQLScriptParser.extractActiveQuery(context, 1, 3);

    // Assert
    verify(context, atLeast(1)).getDocument();
    assertNull(actualExtractActiveQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)} with {@code
   * context}, {@code selOffset}, {@code selLength}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractActiveQuery(SQLParserContext, int, int)"
  })
  public void testExtractActiveQueryWithContextSelOffsetSelLength2() throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLineOfOffset(anyInt())).thenReturn(2);
    when(iDocument.getLineInformation(anyInt())).thenReturn(null);
    when(iDocument.getLength()).thenReturn(3);
    when(iDocument.get(anyInt(), anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(iDocument);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    new SQLRuleManager(syntaxManager);

    // Act
    SQLScriptElement actualExtractActiveQueryResult =
        SQLScriptParser.extractActiveQuery(context, 1, 3);

    // Assert
    verify(iDocument).get(1, 3);
    verify(iDocument, atLeast(1)).getLength();
    verify(iDocument, atLeast(1)).getLineInformation(anyInt());
    verify(iDocument).getLineOfOffset(1);
    verify(syntaxManager).getDialect();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(context, atLeast(1)).getDocument();
    verify(context).getSyntaxManager();
    assertNull(actualExtractActiveQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)} with {@code
   * context}, {@code selOffset}, {@code selLength}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractActiveQuery(SQLParserContext, int, int)"
  })
  public void testExtractActiveQueryWithContextSelOffsetSelLength3() throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(iDocument.getLineOfOffset(anyInt())).thenReturn(2);
    when(iDocument.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(iDocument.getLength()).thenReturn(3);
    when(iDocument.get(anyInt(), anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(iDocument);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    new SQLRuleManager(syntaxManager);

    // Act
    SQLScriptElement actualExtractActiveQueryResult =
        SQLScriptParser.extractActiveQuery(context, 1, 3);

    // Assert
    verify(iDocument, atLeast(1)).get(anyInt(), eq(3));
    verify(iDocument).getLegalLineDelimiters();
    verify(iDocument, atLeast(1)).getLength();
    verify(iDocument).getLineInformation(2);
    verify(iDocument).getLineOfOffset(1);
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(syntaxManager).getDialect();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualExtractActiveQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)} with {@code
   * context}, {@code selOffset}, {@code selLength}.
   *
   * <ul>
   *   <li>Given {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractActiveQuery(SQLParserContext, int, int)"
  })
  public void testExtractActiveQueryWithContextSelOffsetSelLength_givenDocument() {
    // Arrange
    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getDocument()).thenReturn(new Document());

    // Act
    SQLScriptElement actualExtractActiveQueryResult =
        SQLScriptParser.extractActiveQuery(context, 1, 3);

    // Assert
    verify(context, atLeast(1)).getDocument();
    assertNull(actualExtractActiveQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)} with {@code
   * context}, {@code selOffset}, {@code selLength}.
   *
   * <ul>
   *   <li>Then calls {@link IDocument#getLineOffset(int)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractActiveQuery(SQLParserContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptElement SQLScriptParser.extractActiveQuery(SQLParserContext, int, int)"
  })
  public void testExtractActiveQueryWithContextSelOffsetSelLength_thenCallsGetLineOffset()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLineOffset(anyInt())).thenReturn(2);
    when(iDocument.getLegalLineDelimiters()).thenReturn(new String[] {"Legal Line Delimiters"});
    when(iDocument.getLineOfOffset(anyInt())).thenReturn(2);
    when(iDocument.getLineInformation(anyInt())).thenReturn(new Region(2, 3));
    when(iDocument.getLength()).thenReturn(3);
    when(iDocument.get(anyInt(), anyInt()))
        .thenThrow(new BadLocationException("An error occurred"));

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.ONLY_SEPARATOR);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(new TokenPredicateSet());

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext context = mock(SQLParserContext.class);
    when(context.getScanner()).thenReturn(tpRuleBasedScanner);
    when(context.getDialect()).thenReturn(sqlDialect);
    when(context.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(context.getDocument()).thenReturn(iDocument);

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));
    new SQLRuleManager(syntaxManager);

    // Act
    SQLScriptElement actualExtractActiveQueryResult =
        SQLScriptParser.extractActiveQuery(context, 1, 3);

    // Assert
    verify(iDocument, atLeast(1)).get(anyInt(), anyInt());
    verify(iDocument).getLegalLineDelimiters();
    verify(iDocument, atLeast(1)).getLength();
    verify(iDocument).getLineInformation(2);
    verify(iDocument).getLineOfOffset(1);
    verify(iDocument, atLeast(1)).getLineOffset(anyInt());
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(syntaxManager).getDialect();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiters();
    verify(context).getDialect();
    verify(context, atLeast(1)).getDocument();
    verify(context).getScanner();
    verify(context, atLeast(1)).getSyntaxManager();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertNull(actualExtractActiveQueryResult);
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries2() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {null});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(mock(TPTokenDefault.class));
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link ChildDocument#ChildDocument(IDocument)} with masterDocument is {@link
   *       Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenChildDocumentWithMasterDocumentIsDocument() {
    // Arrange
    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getDocument()).thenReturn(new ChildDocument(new Document()));

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(parserContext).getDocument();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link Document#Document()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenDocument() {
    // Arrange
    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getDocument()).thenReturn(new Document());

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(parserContext).getDocument();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link IDocument} {@link IDocument#getLength()} return one.
   *   <li>Then calls {@link IDocument#getLength()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenIDocumentGetLengthReturnOne_thenCallsGetLength() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(1);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLSyntaxManager} {@link SQLSyntaxManager#getStatementDelimiters()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenSQLSyntaxManagerGetStatementDelimitersReturnNull() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(null);
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#getTokenLength()} return
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPRuleBasedScannerGetTokenLengthReturnThree() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#getTokenLength()} return
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPRuleBasedScannerGetTokenLengthReturnThree2() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(mock(TPTokenDefault.class));
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#getTokenLength()} return
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPRuleBasedScannerGetTokenLengthReturnThree3() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(mock(TPTokenDefault.class));
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#getTokenOffset()} return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPRuleBasedScannerGetTokenOffsetReturnZero() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(0);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRuleBasedScanner} {@link TPRuleBasedScanner#nextToken()} return {@link
   *       TPTokenAbstract#EOF}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPRuleBasedScannerNextTokenReturnEof() {
    // Arrange
    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(3);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(TPTokenAbstract.EOF);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPRule} {@link TPRule#evaluate(TPCharacterScanner)} return {@link
   *       TPTokenAbstract#EOF}.
   *   <li>Then calls {@link TPRule#evaluate(TPCharacterScanner)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPRuleEvaluateReturnEof_thenCallsEvaluate() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPRule tpRule = mock(TPRule.class);
    when(tpRule.evaluate(Mockito.<TPCharacterScanner>any())).thenReturn(TPTokenAbstract.EOF);

    TPRuleBasedScanner tpRuleBasedScanner = new TPRuleBasedScanner();
    tpRuleBasedScanner.setRules(new TPRule[] {tpRule});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(new Document("Not all who wander are lost"));

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRule).evaluate(isA(TPCharacterScanner.class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_BLOCK_BEGIN}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_block_begin()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"MD"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_BLOCK_BEGIN}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_block_begin2()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_BLOCK_END}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_block_end()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_END);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_BLOCK_HEADER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_block_header()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_HEADER);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_BLOCK_TOGGLE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_block_toggle()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_TOGGLE);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_COMMENT}.
   *   <li>Then calls {@link IDocument#get(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_comment_thenCallsGet()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_COMMENT);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#getData()} return {@link
   *       SQLTokenType#T_DELIMITER}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultGetDataReturnT_delimiter()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(mock(TrieNode.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(true);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_DELIMITER);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tokenPredicateSet).getMaxPrefixLength();
    verify(tokenPredicateSet).getMaxSuffixLength();
    verify(tokenPredicateSet, atLeast(1)).getPrefixTreeRoot();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link TPTokenDefault} {@link TPTokenDefault#isWhitespace()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_givenTPTokenDefaultIsWhitespaceReturnFalse()
      throws BadLocationException {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.get(anyInt(), anyInt())).thenReturn("Get");
    when(iDocument.getChar(anyInt())).thenReturn('A');
    when(iDocument.getLength()).thenReturn(3);

    TrieNode<TokenEntry, SQLTokenPredicate> trieNode = mock(TrieNode.class);
    when(trieNode.getValues()).thenReturn(new HashSet<>());
    when(trieNode.accumulateSubnodesByTerm(
            Mockito.<TokenEntry>any(),
            Mockito.<ListNode<TrieNode<TokenEntry, SQLTokenPredicate>>>any()))
        .thenReturn(null);

    TokenPredicateSet tokenPredicateSet = mock(TokenPredicateSet.class);
    when(tokenPredicateSet.hasCaptures()).thenReturn(true);
    when(tokenPredicateSet.getMaxPrefixLength()).thenReturn(3);
    when(tokenPredicateSet.getMaxSuffixLength()).thenReturn(3);
    when(tokenPredicateSet.getPrefixTreeRoot()).thenReturn(trieNode);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isStripCommentsBeforeBlocks()).thenReturn(true);
    when(sqlDialect.getSkipTokenPredicates()).thenReturn(tokenPredicateSet);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiters()).thenReturn(new String[] {"\n"});
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    TPTokenDefault tpTokenDefault = mock(TPTokenDefault.class);
    when(tpTokenDefault.isEOF()).thenReturn(true);
    when(tpTokenDefault.isWhitespace()).thenReturn(false);
    when(tpTokenDefault.getData()).thenReturn(SQLTokenType.T_BLOCK_BEGIN);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    when(tpRuleBasedScanner.getTokenLength()).thenReturn(1);
    when(tpRuleBasedScanner.getTokenOffset()).thenReturn(1);
    when(tpRuleBasedScanner.nextToken()).thenReturn(tpTokenDefault);
    doNothing().when(tpRuleBasedScanner).setRange(Mockito.<IDocument>any(), anyInt(), anyInt());
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    when(parserContext.getScanner()).thenReturn(tpRuleBasedScanner);
    when(parserContext.getDialect()).thenReturn(sqlDialect);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 3, true, true, true);

    // Assert
    verify(iDocument, atLeast(1)).get(1, 1);
    verify(iDocument).getChar(1);
    verify(iDocument, atLeast(1)).getLength();
    verify(sqlDialect).getSkipTokenPredicates();
    verify(sqlDialect).isStripCommentsBeforeBlocks();
    verify(sqlSyntaxManager, atLeast(1)).getStatementDelimiterMode();
    verify(sqlSyntaxManager).getStatementDelimiters();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext).getDialect();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getScanner();
    verify(parserContext, atLeast(1)).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(trieNode).accumulateSubnodesByTerm(isA(TokenEntry.class), isNull());
    verify(trieNode).getValues();
    verify(tokenPredicateSet, atLeast(1)).getMaxPrefixLength();
    verify(tokenPredicateSet, atLeast(1)).getMaxSuffixLength();
    verify(tokenPredicateSet).getPrefixTreeRoot();
    verify(tokenPredicateSet).hasCaptures();
    verify(tpRuleBasedScanner).getTokenLength();
    verify(tpRuleBasedScanner).getTokenOffset();
    verify(tpRuleBasedScanner).nextToken();
    verify(tpRuleBasedScanner).setRange(isA(IDocument.class), eq(1), eq(3));
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    verify(tpTokenDefault).getData();
    verify(tpTokenDefault, atLeast(1)).isEOF();
    verify(tpTokenDefault, atLeast(1)).isWhitespace();
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }

  /**
   * Test {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int, boolean, boolean,
   * boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then calls {@link IDocument#getLength()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptParser#extractScriptQueries(SQLParserContext, int, int,
   * boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLScriptParser.extractScriptQueries(SQLParserContext, int, int, boolean, boolean, boolean)"
  })
  public void testExtractScriptQueries_whenZero_thenCallsGetLength() {
    // Arrange
    IDocument iDocument = mock(IDocument.class);
    when(iDocument.getLength()).thenReturn(3);

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getStatementDelimiterMode())
        .thenReturn(SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR);

    SQLParserContext parserContext = mock(SQLParserContext.class);
    when(parserContext.getSyntaxManager()).thenReturn(sqlSyntaxManager);
    doNothing().when(parserContext).endScriptEvaluation();
    doNothing().when(parserContext).startScriptEvaluation();
    when(parserContext.getDocument()).thenReturn(iDocument);

    TPRuleBasedScanner tpRuleBasedScanner = mock(TPRuleBasedScanner.class);
    doNothing().when(tpRuleBasedScanner).setRules(Mockito.<TPRule[]>any());
    tpRuleBasedScanner.setRules(new TPRule[] {mock(TPRule.class)});

    // Act
    List<SQLScriptElement> actualExtractScriptQueriesResult =
        SQLScriptParser.extractScriptQueries(parserContext, 1, 0, true, true, true);

    // Assert
    verify(iDocument).getLength();
    verify(sqlSyntaxManager).getStatementDelimiterMode();
    verify(parserContext).endScriptEvaluation();
    verify(parserContext, atLeast(1)).getDocument();
    verify(parserContext).getSyntaxManager();
    verify(parserContext).startScriptEvaluation();
    verify(tpRuleBasedScanner).setRules(isA(TPRule[].class));
    assertTrue(actualExtractScriptQueriesResult.isEmpty());
  }
}
