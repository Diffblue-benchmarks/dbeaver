package org.jkiss.dbeaver.model.lsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.LexerInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.jkiss.dbeaver.model.lsm.sql.dialect.SQLStandardAnalyzer;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser.DirectSqlDataStatementContext;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser.SqlQueryContext;
import org.jkiss.dbeaver.model.stm.STMErrorListener;
import org.jkiss.dbeaver.model.stm.STMLoggingErrorListener;
import org.jkiss.dbeaver.model.stm.STMSource;
import org.jkiss.dbeaver.model.stm.STMTreeNode;
import org.jkiss.dbeaver.model.stm.STMTreeRuleNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LSMAnalyzerImplDiffblueTest {
  /**
   * Test {@link LSMAnalyzerImpl#prepareParser(STMSource, STMErrorListener)}.
   *
   * <ul>
   *   <li>Then ErrorListeners first return {@link STMLoggingErrorListener}.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerImpl#prepareParser(STMSource, STMErrorListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.stm.STMParserOverrides LSMAnalyzerImpl.prepareParser(STMSource, STMErrorListener)"
  })
  public void testPrepareParser_thenErrorListenersFirstReturnSTMLoggingErrorListener() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));
    STMLoggingErrorListener errorListener = new STMLoggingErrorListener();

    // Act
    SQLStandardParser actualPrepareParserResult =
        sqlStandardAnalyzer.prepareParser(source, errorListener);

    // Assert
    verify(source).getStream();
    TokenStream inputStream = actualPrepareParserResult.getInputStream();
    assertTrue(inputStream instanceof CommonTokenStream);
    List<? extends ANTLRErrorListener> errorListeners =
        actualPrepareParserResult.getErrorListeners();
    assertEquals(1, errorListeners.size());
    ANTLRErrorListener getResult = errorListeners.get(0);
    assertTrue(getResult instanceof STMLoggingErrorListener);
    assertSame(errorListener, getResult);
    assertSame(actualPrepareParserResult, actualPrepareParserResult.getInterpreter().getParser());
    assertSame(inputStream, actualPrepareParserResult.getTokenStream());
  }

  /**
   * Test {@link LSMAnalyzerImpl#prepareParser(STMSource, STMErrorListener)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then ErrorListeners first return {@link ConsoleErrorListener}.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerImpl#prepareParser(STMSource, STMErrorListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.stm.STMParserOverrides LSMAnalyzerImpl.prepareParser(STMSource, STMErrorListener)"
  })
  public void testPrepareParser_whenNull_thenErrorListenersFirstReturnConsoleErrorListener() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    // Act
    SQLStandardParser actualPrepareParserResult = sqlStandardAnalyzer.prepareParser(source, null);

    // Assert
    verify(source).getStream();
    TokenStream inputStream = actualPrepareParserResult.getInputStream();
    assertTrue(inputStream instanceof CommonTokenStream);
    List<? extends ANTLRErrorListener> errorListeners =
        actualPrepareParserResult.getErrorListeners();
    assertEquals(1, errorListeners.size());
    assertTrue(errorListeners.get(0) instanceof ConsoleErrorListener);
    assertSame(actualPrepareParserResult, actualPrepareParserResult.getInterpreter().getParser());
    assertSame(inputStream, actualPrepareParserResult.getTokenStream());
  }

  /**
   * Test {@link LSMAnalyzerImpl#parseSqlQueryTree(STMSource, STMErrorListener)}.
   *
   * <p>Method under test: {@link LSMAnalyzerImpl#parseSqlQueryTree(STMSource, STMErrorListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "STMTreeRuleNode LSMAnalyzerImpl.parseSqlQueryTree(STMSource, STMErrorListener)"
  })
  public void testParseSqlQueryTree() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    when(source.getStream()).thenReturn(new ANTLRInputStream("Input"));

    // Act
    STMTreeRuleNode actualParseSqlQueryTreeResult =
        sqlStandardAnalyzer.parseSqlQueryTree(source, new STMLoggingErrorListener());

    // Assert
    verify(source).getStream();
    STMTreeNode findFirstNonErrorChildResult =
        actualParseSqlQueryTreeResult.findFirstNonErrorChild();
    assertTrue(findFirstNonErrorChildResult instanceof DirectSqlDataStatementContext);
    assertTrue(actualParseSqlQueryTreeResult instanceof SqlQueryContext);
    assertEquals(
        "([] ([591] ([603 591] ([608 603 591] Input))))",
        actualParseSqlQueryTreeResult.toStringTree());
    assertEquals("Input", actualParseSqlQueryTreeResult.getText());
    assertEquals("Input", actualParseSqlQueryTreeResult.getTextContent());
    assertNull(((SqlQueryContext) actualParseSqlQueryTreeResult).exception);
    assertEquals(1, actualParseSqlQueryTreeResult.getChildren().size());
    assertEquals(1, ((SqlQueryContext) actualParseSqlQueryTreeResult).children.size());
    assertEquals(1, actualParseSqlQueryTreeResult.getChildCount());
    assertFalse(actualParseSqlQueryTreeResult.hasErrorChildren());
    assertSame(findFirstNonErrorChildResult, actualParseSqlQueryTreeResult.findLastNonErrorChild());
  }

  /**
   * Test {@link LSMAnalyzerImpl#parseSqlQueryTree(STMSource, STMErrorListener)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then Stop return {@link CommonToken}.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerImpl#parseSqlQueryTree(STMSource, STMErrorListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "STMTreeRuleNode LSMAnalyzerImpl.parseSqlQueryTree(STMSource, STMErrorListener)"
  })
  public void testParseSqlQueryTree_givenA_thenStopReturnCommonToken() throws IOException {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    ByteArrayInputStream input =
        new ByteArrayInputStream(new byte[] {'A', -1, 'A', -1, 'A', -1, 'A', -1});
    when(source.getStream()).thenReturn(new ANTLRInputStream(input));

    // Act
    STMTreeRuleNode actualParseSqlQueryTreeResult =
        sqlStandardAnalyzer.parseSqlQueryTree(source, new STMLoggingErrorListener());

    // Assert
    verify(source).getStream();
    assertTrue(actualParseSqlQueryTreeResult.getStop() instanceof CommonToken);
    assertTrue(
        ((SqlQueryContext) actualParseSqlQueryTreeResult).exception
            instanceof InputMismatchException);
    assertTrue(actualParseSqlQueryTreeResult instanceof SqlQueryContext);
    assertEquals("([] A A A A)", actualParseSqlQueryTreeResult.toStringTree());
    assertEquals("AAAA", actualParseSqlQueryTreeResult.getText());
    assertEquals("A�A�A�A", actualParseSqlQueryTreeResult.getTextContent());
    assertNull(actualParseSqlQueryTreeResult.findFirstNonErrorChild());
    assertNull(actualParseSqlQueryTreeResult.findLastNonErrorChild());
    assertEquals(4, actualParseSqlQueryTreeResult.getChildren().size());
    assertEquals(4, ((SqlQueryContext) actualParseSqlQueryTreeResult).children.size());
    assertEquals(4, actualParseSqlQueryTreeResult.getChildCount());
    assertTrue(actualParseSqlQueryTreeResult.hasErrorChildren());
  }

  /**
   * Test {@link LSMAnalyzerImpl#parseSqlQueryTree(STMSource, STMErrorListener)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerImpl#parseSqlQueryTree(STMSource, STMErrorListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "STMTreeRuleNode LSMAnalyzerImpl.parseSqlQueryTree(STMSource, STMErrorListener)"
  })
  public void testParseSqlQueryTree_thenReturnNull() {
    // Arrange
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'A', new ArrayList<>(), true);
    SQLStandardAnalyzer sqlStandardAnalyzer = new SQLStandardAnalyzer(parameters);

    STMSource source = mock(STMSource.class);
    ArrayList<String> tokenNames = new ArrayList<>();
    ArrayList<String> ruleNames = new ArrayList<>();
    ArrayList<String> modeNames = new ArrayList<>();

    LexerInterpreter recognizer =
        new LexerInterpreter(
            "foo.txt",
            tokenNames,
            ruleNames,
            modeNames,
            SQLStandardLexer._ATN,
            new ANTLRInputStream("Input"));
    RecognitionException recognitionException =
        new RecognitionException(
            recognizer, new ANTLRInputStream("Input"), ParserRuleContext.EMPTY);
    when(source.getStream()).thenThrow(recognitionException);

    // Act
    STMTreeRuleNode actualParseSqlQueryTreeResult =
        sqlStandardAnalyzer.parseSqlQueryTree(source, new STMLoggingErrorListener());

    // Assert
    verify(source).getStream();
    assertNull(actualParseSqlQueryTreeResult);
  }
}
