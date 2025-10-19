package org.jkiss.dbeaver.ext.clickhouse;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.ProxyErrorListener;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClickhouseDataTypesLexerDiffblueTest {
  @Mock private CharStream charStream;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ClickhouseDataTypesLexer#getATN()}
   *   <li>{@link ClickhouseDataTypesLexer#getChannelNames()}
   *   <li>{@link ClickhouseDataTypesLexer#getGrammarFileName()}
   *   <li>{@link ClickhouseDataTypesLexer#getModeNames()}
   *   <li>{@link ClickhouseDataTypesLexer#getRuleNames()}
   *   <li>{@link ClickhouseDataTypesLexer#getSerializedATN()}
   *   <li>{@link ClickhouseDataTypesLexer#getTokenNames()}
   *   <li>{@link ClickhouseDataTypesLexer#getVocabulary()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ATN ClickhouseDataTypesLexer.getATN()",
    "String[] ClickhouseDataTypesLexer.getChannelNames()",
    "String ClickhouseDataTypesLexer.getGrammarFileName()",
    "String[] ClickhouseDataTypesLexer.getModeNames()",
    "String[] ClickhouseDataTypesLexer.getRuleNames()",
    "String ClickhouseDataTypesLexer.getSerializedATN()",
    "String[] ClickhouseDataTypesLexer.getTokenNames()",
    "Vocabulary ClickhouseDataTypesLexer.getVocabulary()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ClickhouseDataTypesLexer clickhouseDataTypesLexer =
        new ClickhouseDataTypesLexer(new ANTLRInputStream("Input"));

    // Act
    ATN actualATN = clickhouseDataTypesLexer.getATN();
    String[] actualChannelNames = clickhouseDataTypesLexer.getChannelNames();
    String actualGrammarFileName = clickhouseDataTypesLexer.getGrammarFileName();
    String[] actualModeNames = clickhouseDataTypesLexer.getModeNames();
    String[] actualRuleNames = clickhouseDataTypesLexer.getRuleNames();
    String actualSerializedATN = clickhouseDataTypesLexer.getSerializedATN();
    String[] actualTokenNames = clickhouseDataTypesLexer.getTokenNames();
    Vocabulary actualVocabulary = clickhouseDataTypesLexer.getVocabulary();

    // Assert
    assertTrue(actualVocabulary instanceof VocabularyImpl);
    assertEquals("ClickhouseDataTypes.g4", actualGrammarFileName);
    assertEquals(ClickhouseDataTypesLexer._serializedATN, actualSerializedATN);
    assertSame(ClickhouseDataTypesLexer.VOCABULARY, actualVocabulary);
    assertSame(ClickhouseDataTypesLexer._ATN, actualATN);
    assertSame(ClickhouseDataTypesLexer.channelNames, actualChannelNames);
    assertSame(ClickhouseDataTypesLexer.modeNames, actualModeNames);
    assertSame(ClickhouseDataTypesLexer.ruleNames, actualRuleNames);
    assertSame(ClickhouseDataTypesLexer.tokenNames, actualTokenNames);
    assertArrayEquals(new String[] {"DEFAULT_MODE"}, actualModeNames);
  }

  /**
   * Test {@link ClickhouseDataTypesLexer#ClickhouseDataTypesLexer(CharStream)}.
   *
   * <p>Method under test: {@link ClickhouseDataTypesLexer#ClickhouseDataTypesLexer(CharStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClickhouseDataTypesLexer.<init>(CharStream)"})
  public void testNewClickhouseDataTypesLexer() {
    // Arrange and Act
    ClickhouseDataTypesLexer actualClickhouseDataTypesLexer =
        new ClickhouseDataTypesLexer(charStream);

    // Assert
    assertTrue(actualClickhouseDataTypesLexer.getTokenFactory() instanceof CommonTokenFactory);
    assertTrue(
        actualClickhouseDataTypesLexer.getErrorListenerDispatch() instanceof ProxyErrorListener);
    Vocabulary vocabulary = actualClickhouseDataTypesLexer.getVocabulary();
    assertTrue(vocabulary instanceof VocabularyImpl);
    assertEquals("ClickhouseDataTypes.g4", actualClickhouseDataTypesLexer.getGrammarFileName());
    assertNull(actualClickhouseDataTypesLexer.getSourceName());
    assertNull(actualClickhouseDataTypesLexer.getText());
    assertNull(actualClickhouseDataTypesLexer._text);
    assertNull(actualClickhouseDataTypesLexer.getToken());
    assertNull(actualClickhouseDataTypesLexer.getParseInfo());
    assertEquals(-1, actualClickhouseDataTypesLexer.getState());
    assertEquals(0, actualClickhouseDataTypesLexer.getChannel());
    assertEquals(0, actualClickhouseDataTypesLexer.getCharIndex());
    assertEquals(0, actualClickhouseDataTypesLexer.getCharPositionInLine());
    assertEquals(0, actualClickhouseDataTypesLexer.getType());
    assertEquals(0, actualClickhouseDataTypesLexer._mode);
    assertEquals(0, actualClickhouseDataTypesLexer._tokenStartCharPositionInLine);
    assertEquals(1, actualClickhouseDataTypesLexer.getErrorListeners().size());
    assertEquals(1, actualClickhouseDataTypesLexer.getLine());
    String[] modeNames = actualClickhouseDataTypesLexer.getModeNames();
    assertEquals(1, modeNames.length);
    Map<String, Integer> tokenTypeMap = actualClickhouseDataTypesLexer.getTokenTypeMap();
    assertEquals(37, tokenTypeMap.size());
    assertFalse(actualClickhouseDataTypesLexer._hitEOF);
    Map<String, Integer> ruleIndexMap = actualClickhouseDataTypesLexer.getRuleIndexMap();
    assertEquals(ClickhouseDataTypesLexer.String, ruleIndexMap.size());
    assertTrue(ruleIndexMap.containsKey("DateTime"));
    assertTrue(ruleIndexMap.containsKey("Enum"));
    assertTrue(ruleIndexMap.containsKey("Eq"));
    assertTrue(ruleIndexMap.containsKey("Int"));
    assertTrue(ruleIndexMap.containsKey("LeftParen"));
    assertTrue(ruleIndexMap.containsKey(ClickhouseConstants.DATA_TYPE_STRING));
    assertTrue(tokenTypeMap.containsKey("')'"));
    assertTrue(tokenTypeMap.containsKey("'Array'"));
    assertTrue(tokenTypeMap.containsKey("'IPV4'"));
    assertTrue(tokenTypeMap.containsKey("Enum"));
    assertTrue(tokenTypeMap.containsKey("LeftParen"));
    assertTrue(tokenTypeMap.containsKey(ClickhouseConstants.DATA_TYPE_STRING));
    assertEquals(
        ClickhouseDataTypesLexer._serializedATN, actualClickhouseDataTypesLexer.getSerializedATN());
    assertSame(charStream, actualClickhouseDataTypesLexer.getInputStream());
    assertSame(ClickhouseDataTypesLexer.VOCABULARY, vocabulary);
    assertSame(ClickhouseDataTypesLexer._ATN, actualClickhouseDataTypesLexer.getATN());
    assertSame(
        ClickhouseDataTypesLexer.channelNames, actualClickhouseDataTypesLexer.getChannelNames());
    assertSame(ClickhouseDataTypesLexer.modeNames, modeNames);
    assertSame(ClickhouseDataTypesLexer.ruleNames, actualClickhouseDataTypesLexer.getRuleNames());
    assertSame(ClickhouseDataTypesLexer.tokenNames, actualClickhouseDataTypesLexer.getTokenNames());
  }
}
