package org.jkiss.dbeaver.model.lsm.sql.impl.syntax;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.jkiss.dbeaver.model.lsm.LSMAnalyzerParameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLStandardLexerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLStandardLexer#getATN()}
   *   <li>{@link SQLStandardLexer#getChannelNames()}
   *   <li>{@link SQLStandardLexer#getGrammarFileName()}
   *   <li>{@link SQLStandardLexer#getModeNames()}
   *   <li>{@link SQLStandardLexer#getRuleNames()}
   *   <li>{@link SQLStandardLexer#getSerializedATN()}
   *   <li>{@link SQLStandardLexer#getTokenNames()}
   *   <li>{@link SQLStandardLexer#getVocabulary()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ATN SQLStandardLexer.getATN()",
    "String[] SQLStandardLexer.getChannelNames()",
    "String SQLStandardLexer.getGrammarFileName()",
    "String[] SQLStandardLexer.getModeNames()",
    "String[] SQLStandardLexer.getRuleNames()",
    "String SQLStandardLexer.getSerializedATN()",
    "String[] SQLStandardLexer.getTokenNames()",
    "Vocabulary SQLStandardLexer.getVocabulary()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream("Input"));

    // Act
    ATN actualATN = sqlStandardLexer.getATN();
    String[] actualChannelNames = sqlStandardLexer.getChannelNames();
    String actualGrammarFileName = sqlStandardLexer.getGrammarFileName();
    String[] actualModeNames = sqlStandardLexer.getModeNames();
    String[] actualRuleNames = sqlStandardLexer.getRuleNames();
    String actualSerializedATN = sqlStandardLexer.getSerializedATN();
    String[] actualTokenNames = sqlStandardLexer.getTokenNames();
    Vocabulary actualVocabulary = sqlStandardLexer.getVocabulary();

    // Assert
    assertTrue(actualVocabulary instanceof VocabularyImpl);
    assertEquals("SQLStandardLexer.g4", actualGrammarFileName);
    assertEquals(SQLStandardLexer._serializedATN, actualSerializedATN);
    assertSame(SQLStandardLexer.VOCABULARY, actualVocabulary);
    assertSame(SQLStandardLexer._ATN, actualATN);
    assertSame(SQLStandardLexer.channelNames, actualChannelNames);
    assertSame(SQLStandardLexer.modeNames, actualModeNames);
    assertSame(SQLStandardLexer.ruleNames, actualRuleNames);
    assertSame(SQLStandardLexer.tokenNames, actualTokenNames);
    assertArrayEquals(new String[] {"DEFAULT_MODE"}, actualModeNames);
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred() {
    // Arrange
    ANTLRInputStream input = new ANTLRInputStream("Input");
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(
            knownIdentifierQuotes, true, true, '\u0001', new ArrayList<>(), true);

    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(input, parameters);

    // Act and Assert
    assertFalse(sqlStandardLexer.sempred(ParserRuleContext.EMPTY, 1, 2));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred2() {
    // Arrange
    ANTLRInputStream input = new ANTLRInputStream("Input");
    HashMap<String, String> knownIdentifierQuotes = new HashMap<>();
    LSMAnalyzerParameters parameters =
        new LSMAnalyzerParameters(knownIdentifierQuotes, true, true, 'I', new ArrayList<>(), true);

    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(input, parameters);

    // Act and Assert
    assertTrue(sqlStandardLexer.sempred(ParserRuleContext.EMPTY, 1, 2));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>Given {@link ANTLRInputStream#ANTLRInputStream(String)} with {@code Input} seek one.
   *   <li>When zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_givenANTLRInputStreamWithInputSeekOne_whenZero_thenReturnFalse() {
    // Arrange
    ANTLRInputStream input = new ANTLRInputStream("Input");
    input.seek(1);

    // Act and Assert
    assertFalse(new SQLStandardLexer(input).sempred(ParserRuleContext.EMPTY, 0, 0));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When four.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenFour_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 2, 4));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenMinusOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new SQLStandardLexer(new ANTLRInputStream("Input"))
            .sempred(ParserRuleContext.EMPTY, -1, 1));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 1, 2));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 1, 1));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenThree_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 2, 3));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenZero_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 0, 0));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenZero_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 0, 1));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenZero_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 0, 2));
  }

  /**
   * Test {@link SQLStandardLexer#sempred(RuleContext, int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLStandardLexer#sempred(RuleContext, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLStandardLexer.sempred(RuleContext, int, int)"})
  public void testSempred_whenZero_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(
        new SQLStandardLexer(new ANTLRInputStream("Input")).sempred(ParserRuleContext.EMPTY, 2, 0));
  }
}
