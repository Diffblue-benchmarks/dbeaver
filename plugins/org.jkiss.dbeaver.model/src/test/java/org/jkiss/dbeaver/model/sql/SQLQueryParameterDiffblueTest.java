package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryParameterDiffblueTest {
  /**
   * Test {@link SQLQueryParameter#SQLQueryParameter(SQLSyntaxManager, int, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#SQLQueryParameter(SQLSyntaxManager, int, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryParameter.<init>(SQLSyntaxManager, int, String, String)"})
  public void testNewSQLQueryParameter_whenNull_thenReturnName() {
    // Arrange and Act
    SQLQueryParameter actualSqlQueryParameter =
        new SQLQueryParameter(null, 1, "Name", "Original Name");

    // Assert
    assertEquals("Name", actualSqlQueryParameter.getName());
    assertEquals("Name", actualSqlQueryParameter.getVarName());
    assertEquals("Original Name", actualSqlQueryParameter.getOriginalName());
    assertNull(actualSqlQueryParameter.getValue());
    assertNull(actualSqlQueryParameter.getPrevious());
    assertEquals(0, actualSqlQueryParameter.getTokenLength());
    assertEquals(0, actualSqlQueryParameter.getTokenOffset());
    assertEquals(1, actualSqlQueryParameter.getOrdinalPosition());
    assertFalse(actualSqlQueryParameter.isVariableSet());
  }

  /**
   * Test {@link SQLQueryParameter#SQLQueryParameter(SQLSyntaxManager, int, String, String, int,
   * int)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#SQLQueryParameter(SQLSyntaxManager, int, String,
   * String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryParameter.<init>(SQLSyntaxManager, int, String, String, int, int)"
  })
  public void testNewSQLQueryParameter_whenNull_thenReturnName2() {
    // Arrange and Act
    SQLQueryParameter actualSqlQueryParameter =
        new SQLQueryParameter(null, 1, "Name", "Original Name", 1, 3);

    // Assert
    assertEquals("Name", actualSqlQueryParameter.getName());
    assertEquals("Name", actualSqlQueryParameter.getVarName());
    assertEquals("Original Name", actualSqlQueryParameter.getOriginalName());
    assertNull(actualSqlQueryParameter.getValue());
    assertNull(actualSqlQueryParameter.getPrevious());
    assertEquals(1, actualSqlQueryParameter.getOrdinalPosition());
    assertEquals(1, actualSqlQueryParameter.getTokenOffset());
    assertEquals(3, actualSqlQueryParameter.getTokenLength());
    assertFalse(actualSqlQueryParameter.isVariableSet());
  }

  /**
   * Test {@link SQLQueryParameter#isNamed()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#isNamed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryParameter.isNamed()"})
  public void testIsNamed_thenReturnFalse() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    SQLQueryParameter sqlQueryParameter =
        new SQLQueryParameter(syntaxManager, 1, "A", "Original Name");

    // Act
    boolean actualIsNamedResult = sqlQueryParameter.isNamed();

    // Assert
    verify(syntaxManager).getAnonymousParameterMark();
    assertFalse(actualIsNamedResult);
  }

  /**
   * Test {@link SQLQueryParameter#isNamed()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#isNamed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryParameter.isNamed()"})
  public void testIsNamed_thenReturnTrue() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getAnonymousParameterMark()).thenReturn('A');
    SQLQueryParameter sqlQueryParameter =
        new SQLQueryParameter(syntaxManager, 1, "Name", "Original Name");

    // Act
    boolean actualIsNamedResult = sqlQueryParameter.isNamed();

    // Assert
    verify(syntaxManager).getAnonymousParameterMark();
    assertTrue(actualIsNamedResult);
  }

  /**
   * Test {@link SQLQueryParameter#getVariablePattern()}.
   *
   * <p>Method under test: {@link SQLQueryParameter#getVariablePattern()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.regex.Pattern SQLQueryParameter.getVariablePattern()"})
  public void testGetVariablePattern() {
    // Arrange, Act and Assert
    assertEquals(
        "\\$P?!?\\{(?<pn>[a-z0-9_.\"]+)\\}", SQLQueryParameter.getVariablePattern().pattern());
  }

  /**
   * Test {@link SQLQueryParameter#supportsJasperSyntax()}.
   *
   * <p>Method under test: {@link SQLQueryParameter#supportsJasperSyntax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryParameter.supportsJasperSyntax()"})
  public void testSupportsJasperSyntax() {
    // Arrange, Act and Assert
    assertTrue(SQLQueryParameter.supportsJasperSyntax());
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   * <ul>
   *   <li>Then return {@code ${}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_thenReturnDollarSignLeftCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("${", SQLQueryParameter.stripVariablePattern("${"));
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   *
   * <ul>
   *   <li>When {@code ${}}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_whenDollarSignLeftCurlyBracketRightCurlyBracket() {
    // Arrange, Act and Assert
    assertEquals("", SQLQueryParameter.stripVariablePattern("${}"));
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   *
   * <ul>
   *   <li>When {@code $P{}}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_whenP_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", SQLQueryParameter.stripVariablePattern("$P{}"));
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   *
   * <ul>
   *   <li>When {@code $P!{}}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_whenP_thenReturnEmptyString2() {
    // Arrange, Act and Assert
    assertEquals("", SQLQueryParameter.stripVariablePattern("$P!{}"));
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   * <ul>
   *   <li>When {@code $P{}.</li>
   *   <li>Then return {@code $P{}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_whenP_thenReturnP() {
    // Arrange, Act and Assert
    assertEquals("$P{", SQLQueryParameter.stripVariablePattern("$P{"));
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   * <ul>
   *   <li>When {@code $P!{}.</li>
   *   <li>Then return {@code $P!{}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_whenP_thenReturnP2() {
    // Arrange, Act and Assert
    assertEquals("$P!{", SQLQueryParameter.stripVariablePattern("$P!{"));
  }

  /**
   * Test {@link SQLQueryParameter#stripVariablePattern(String)}.
   *
   * <ul>
   *   <li>When {@code Pattern}.
   *   <li>Then return {@code Pattern}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryParameter#stripVariablePattern(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLQueryParameter.stripVariablePattern(String)"})
  public void testStripVariablePattern_whenPattern_thenReturnPattern() {
    // Arrange, Act and Assert
    assertEquals("Pattern", SQLQueryParameter.stripVariablePattern("Pattern"));
  }
}
