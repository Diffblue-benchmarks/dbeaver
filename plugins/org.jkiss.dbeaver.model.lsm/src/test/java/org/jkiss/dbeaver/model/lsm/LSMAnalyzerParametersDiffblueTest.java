package org.jkiss.dbeaver.model.lsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LSMAnalyzerParametersDiffblueTest {
  /**
   * Test {@link LSMAnalyzerParameters#forDialect(SQLDialect, SQLSyntaxManager)}.
   *
   * <ul>
   *   <li>Given empty 2D array of {@link String}.
   *   <li>Then return knownIdentifierQuotes Empty.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerParameters#forDialect(SQLDialect, SQLSyntaxManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LSMAnalyzerParameters LSMAnalyzerParameters.forDialect(SQLDialect, SQLSyntaxManager)"
  })
  public void testForDialect_givenEmpty2dArrayOfString_thenReturnKnownIdentifierQuotesEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.isAnonymousParametersEnabled()).thenReturn(true);
    when(syntaxManager.isParametersEnabled()).thenReturn(true);
    when(syntaxManager.isVariablesEnabled()).thenReturn(true);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getNamedParameterPrefixes())
        .thenReturn(new String[] {"Named Parameter Prefixes"});

    // Act
    LSMAnalyzerParameters actualForDialectResult =
        LSMAnalyzerParameters.forDialect(dialect, syntaxManager);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getNamedParameterPrefixes();
    verify(syntaxManager).isAnonymousParametersEnabled();
    verify(syntaxManager).isParametersEnabled();
    verify(syntaxManager).isVariablesEnabled();
    assertEquals('A', actualForDialectResult.anonymousParameterMark());
    assertEquals(1, actualForDialectResult.namedParameterPrefixes().size());
    assertTrue(actualForDialectResult.knownIdentifierQuotes().isEmpty());
    assertTrue(actualForDialectResult.isAnonymousSqlParametersEnabled());
    assertTrue(actualForDialectResult.isSqlParametersEnabled());
    assertTrue(actualForDialectResult.variablesEnabled());
  }

  /**
   * Test {@link LSMAnalyzerParameters#forDialect(SQLDialect, SQLSyntaxManager)}.
   *
   * <ul>
   *   <li>Then return knownIdentifierQuotes size is one.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerParameters#forDialect(SQLDialect, SQLSyntaxManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LSMAnalyzerParameters LSMAnalyzerParameters.forDialect(SQLDialect, SQLSyntaxManager)"
  })
  public void testForDialect_thenReturnKnownIdentifierQuotesSizeIsOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.isAnonymousParametersEnabled()).thenReturn(true);
    when(syntaxManager.isParametersEnabled()).thenReturn(true);
    when(syntaxManager.isVariablesEnabled()).thenReturn(true);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getNamedParameterPrefixes())
        .thenReturn(new String[] {"Named Parameter Prefixes"});

    // Act
    LSMAnalyzerParameters actualForDialectResult =
        LSMAnalyzerParameters.forDialect(dialect, syntaxManager);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getNamedParameterPrefixes();
    verify(syntaxManager).isAnonymousParametersEnabled();
    verify(syntaxManager).isParametersEnabled();
    verify(syntaxManager).isVariablesEnabled();
    Map<String, String> knownIdentifierQuotesResult =
        actualForDialectResult.knownIdentifierQuotes();
    assertEquals(1, knownIdentifierQuotesResult.size());
    assertEquals("42", knownIdentifierQuotesResult.get("foo"));
    assertEquals('A', actualForDialectResult.anonymousParameterMark());
    assertEquals(1, actualForDialectResult.namedParameterPrefixes().size());
    assertTrue(actualForDialectResult.isAnonymousSqlParametersEnabled());
    assertTrue(actualForDialectResult.isSqlParametersEnabled());
    assertTrue(actualForDialectResult.variablesEnabled());
  }

  /**
   * Test {@link LSMAnalyzerParameters#forDialect(SQLDialect, SQLSyntaxManager)}.
   *
   * <ul>
   *   <li>Then return knownIdentifierQuotes size is two.
   * </ul>
   *
   * <p>Method under test: {@link LSMAnalyzerParameters#forDialect(SQLDialect, SQLSyntaxManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LSMAnalyzerParameters LSMAnalyzerParameters.forDialect(SQLDialect, SQLSyntaxManager)"
  })
  public void testForDialect_thenReturnKnownIdentifierQuotesSizeIsTwo() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"42", "42"}, new String[] {"foo", "42"}});

    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.isAnonymousParametersEnabled()).thenReturn(true);
    when(syntaxManager.isParametersEnabled()).thenReturn(true);
    when(syntaxManager.isVariablesEnabled()).thenReturn(true);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    when(syntaxManager.getNamedParameterPrefixes())
        .thenReturn(new String[] {"Named Parameter Prefixes"});

    // Act
    LSMAnalyzerParameters actualForDialectResult =
        LSMAnalyzerParameters.forDialect(dialect, syntaxManager);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(syntaxManager).getAnonymousParameterMark();
    verify(syntaxManager).getNamedParameterPrefixes();
    verify(syntaxManager).isAnonymousParametersEnabled();
    verify(syntaxManager).isParametersEnabled();
    verify(syntaxManager).isVariablesEnabled();
    Map<String, String> knownIdentifierQuotesResult =
        actualForDialectResult.knownIdentifierQuotes();
    assertEquals(2, knownIdentifierQuotesResult.size());
    assertEquals("42", knownIdentifierQuotesResult.get("42"));
    assertEquals("42", knownIdentifierQuotesResult.get("foo"));
    assertEquals('A', actualForDialectResult.anonymousParameterMark());
    assertEquals(1, actualForDialectResult.namedParameterPrefixes().size());
    assertTrue(actualForDialectResult.isAnonymousSqlParametersEnabled());
    assertTrue(actualForDialectResult.isSqlParametersEnabled());
    assertTrue(actualForDialectResult.variablesEnabled());
  }
}
