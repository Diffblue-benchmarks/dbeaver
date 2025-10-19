package org.jkiss.dbeaver.model.sql.format.tokenized;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.format.SQLFormatterConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLTokensParserDiffblueTest {
  /**
   * Test {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}.
   *
   * <p>Method under test: {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokensParser.<init>(SQLFormatterConfiguration)"})
  public void testNewSQLTokensParser() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getEscapeChar()).thenReturn('A');
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration configuration = mock(SQLFormatterConfiguration.class);
    when(configuration.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new SQLTokensParser(configuration);

    // Assert
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlSyntaxManager).getCatalogSeparator();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getEscapeChar();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(configuration, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}.
   *
   * <p>Method under test: {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokensParser.<init>(SQLFormatterConfiguration)"})
  public void testNewSQLTokensParser2() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiter Redefiner");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getEscapeChar()).thenReturn('A');
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration configuration = mock(SQLFormatterConfiguration.class);
    when(configuration.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new SQLTokensParser(configuration);

    // Assert
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlSyntaxManager).getCatalogSeparator();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getEscapeChar();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(configuration, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}.
   *
   * <p>Method under test: {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokensParser.<init>(SQLFormatterConfiguration)"})
  public void testNewSQLTokensParser3() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getEscapeChar()).thenReturn('A');
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration configuration = mock(SQLFormatterConfiguration.class);
    when(configuration.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new SQLTokensParser(configuration);

    // Assert
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlSyntaxManager).getCatalogSeparator();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getEscapeChar();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(configuration, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}.
   *
   * <p>Method under test: {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokensParser.<init>(SQLFormatterConfiguration)"})
  public void testNewSQLTokensParser4() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("Script Delimiters");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getEscapeChar()).thenReturn('A');
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration configuration = mock(SQLFormatterConfiguration.class);
    when(configuration.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new SQLTokensParser(configuration);

    // Assert
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlSyntaxManager).getCatalogSeparator();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getEscapeChar();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(configuration, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}.
   *
   * <p>Method under test: {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokensParser.<init>(SQLFormatterConfiguration)"})
  public void testNewSQLTokensParser5() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn("");
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {""});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getEscapeChar()).thenReturn('A');
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration configuration = mock(SQLFormatterConfiguration.class);
    when(configuration.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new SQLTokensParser(configuration);

    // Assert
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlSyntaxManager).getCatalogSeparator();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getEscapeChar();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(configuration, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getScriptDelimiterRedefiner()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#SQLTokensParser(SQLFormatterConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokensParser.<init>(SQLFormatterConfiguration)"})
  public void testNewSQLTokensParser_givenSQLDialectGetScriptDelimiterRedefinerReturnNull() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getScriptDelimiterRedefiner()).thenReturn(null);
    when(sqlDialect.getScriptDelimiters()).thenReturn(new String[] {"Script Delimiters"});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    SQLSyntaxManager sqlSyntaxManager = mock(SQLSyntaxManager.class);
    when(sqlSyntaxManager.getEscapeChar()).thenReturn('A');
    when(sqlSyntaxManager.getStructSeparator()).thenReturn('A');
    when(sqlSyntaxManager.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(sqlSyntaxManager.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlSyntaxManager.getDialect()).thenReturn(sqlDialect);

    SQLFormatterConfiguration configuration = mock(SQLFormatterConfiguration.class);
    when(configuration.getSyntaxManager()).thenReturn(sqlSyntaxManager);

    // Act
    new SQLTokensParser(configuration);

    // Assert
    verify(sqlDialect).getScriptDelimiterRedefiner();
    verify(sqlDialect).getScriptDelimiters();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlSyntaxManager).getCatalogSeparator();
    verify(sqlSyntaxManager, atLeast(1)).getDialect();
    verify(sqlSyntaxManager).getEscapeChar();
    verify(sqlSyntaxManager).getIdentifierQuoteStrings();
    verify(sqlSyntaxManager).getStructSeparator();
    verify(configuration, atLeast(1)).getSyntaxManager();
  }

  /**
   * Test {@link SQLTokensParser#isSpace(char)}.
   *
   * <ul>
   *   <li>When end of text.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isSpace(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isSpace(char)"})
  public void testIsSpace_whenEndOfText_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLTokensParser.isSpace('\u0003'));
  }

  /**
   * Test {@link SQLTokensParser#isSpace(char)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isSpace(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isSpace(char)"})
  public void testIsSpace_whenSpace_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLTokensParser.isSpace(' '));
  }

  /**
   * Test {@link SQLTokensParser#isLetter(char)}.
   *
   * <ul>
   *   <li>When {@code 1}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isLetter(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isLetter(char)"})
  public void testIsLetter_when1_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLTokensParser.isLetter('1'));
  }

  /**
   * Test {@link SQLTokensParser#isLetter(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isLetter(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isLetter(char)"})
  public void testIsLetter_whenA_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLTokensParser.isLetter('A'));
  }

  /**
   * Test {@link SQLTokensParser#isLetter(char)}.
   *
   * <ul>
   *   <li>When {@code !}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isLetter(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isLetter(char)"})
  public void testIsLetter_whenExclamationMark_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLTokensParser.isLetter('!'));
  }

  /**
   * Test {@link SQLTokensParser#isDigit(char)}.
   *
   * <ul>
   *   <li>When {@code 1}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isDigit(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isDigit(char)"})
  public void testIsDigit_when1_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLTokensParser.isDigit('1'));
  }

  /**
   * Test {@link SQLTokensParser#isDigit(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isDigit(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isDigit(char)"})
  public void testIsDigit_whenA_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLTokensParser.isDigit('A'));
  }

  /**
   * Test {@link SQLTokensParser#isSymbol(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isSymbol(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isSymbol(char)"})
  public void testIsSymbol_whenA_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLTokensParser.isSymbol('A'));
  }

  /**
   * Test {@link SQLTokensParser#isSymbol(char)}.
   *
   * <ul>
   *   <li>When {@code !}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTokensParser#isSymbol(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTokensParser.isSymbol(char)"})
  public void testIsSymbol_whenExclamationMark_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLTokensParser.isSymbol('!'));
  }
}
