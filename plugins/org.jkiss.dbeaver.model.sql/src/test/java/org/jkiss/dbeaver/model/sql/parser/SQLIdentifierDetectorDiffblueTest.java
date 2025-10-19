package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.parser.SQLIdentifierDetector.WordRegion;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLIdentifierDetectorDiffblueTest {
  /**
   * Test {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLIdentifierDetector.<init>(SQLDialect)"})
  public void testNewSQLIdentifierDetector_givenArrayOfStringWithIdentifierQuoteStrings() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    SQLIdentifierDetector actualSqlIdentifierDetector = new SQLIdentifierDetector(dialect);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals('A', actualSqlIdentifierDetector.getStructSeparator());
  }

  /**
   * Test {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLIdentifierDetector.<init>(SQLDialect)"})
  public void testNewSQLIdentifierDetector_givenNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings()).thenReturn(null);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    SQLIdentifierDetector actualSqlIdentifierDetector = new SQLIdentifierDetector(dialect);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals('A', actualSqlIdentifierDetector.getStructSeparator());
  }

  /**
   * Test {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect, char, String[][])}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getStringQuoteStrings()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect, char,
   * String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLIdentifierDetector.<init>(SQLDialect, char, String[][])"})
  public void testNewSQLIdentifierDetector_thenCallsGetStringQuoteStrings() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    String[][] quoteStrings = new String[][] {new String[] {"Quote Strings"}};

    // Act
    SQLIdentifierDetector actualSqlIdentifierDetector =
        new SQLIdentifierDetector(dialect, 'A', quoteStrings);

    // Assert
    verify(dialect).getStringQuoteStrings();
    assertEquals('A', actualSqlIdentifierDetector.getStructSeparator());
  }

  /**
   * Test {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect, char, String[][])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link SQLIdentifierDetector#dialect} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect, char,
   * String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLIdentifierDetector.<init>(SQLDialect, char, String[][])"})
  public void testNewSQLIdentifierDetector_whenNull_thenReturnDialectIsNull() {
    // Arrange and Act
    SQLIdentifierDetector actualSqlIdentifierDetector = new SQLIdentifierDetector(null, 'A', null);

    // Assert
    assertEquals('A', actualSqlIdentifierDetector.getStructSeparator());
    assertNull(actualSqlIdentifierDetector.dialect);
  }

  /**
   * Test {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect, char, String[][])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link SQLIdentifierDetector#dialect} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#SQLIdentifierDetector(SQLDialect, char,
   * String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLIdentifierDetector.<init>(SQLDialect, char, String[][])"})
  public void testNewSQLIdentifierDetector_whenNull_thenReturnDialectIsNull2() {
    // Arrange
    String[][] quoteStrings = new String[][] {new String[] {"Quote Strings"}};

    // Act
    SQLIdentifierDetector actualSqlIdentifierDetector =
        new SQLIdentifierDetector(null, 'A', quoteStrings);

    // Assert
    assertEquals('A', actualSqlIdentifierDetector.getStructSeparator());
    assertNull(actualSqlIdentifierDetector.dialect);
  }

  /**
   * Test {@link SQLIdentifierDetector#isQuote(char)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   *   <li>When {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isQuote(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isQuote(char)"})
  public void testIsQuote_givenArrayOfStringWithFooAnd42_whenA_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsQuoteResult = new SQLIdentifierDetector(dialect).isQuote('A');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertFalse(actualIsQuoteResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isQuote(char)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isQuote(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isQuote(char)"})
  public void testIsQuote_whenSpace_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsQuoteResult = new SQLIdentifierDetector(dialect).isQuote(' ');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsQuoteResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isStringQuote(char)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   *   <li>When {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isStringQuote(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isStringQuote(char)"})
  public void testIsStringQuote_givenArrayOfStringWithFooAnd42_whenA_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings()).thenReturn(new String[][] {new String[] {"foo", "42"}});

    // Act
    boolean actualIsStringQuoteResult = new SQLIdentifierDetector(dialect).isStringQuote('A');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertFalse(actualIsStringQuoteResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isStringQuote(char)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Quote Strings}.
   *   <li>When space.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isStringQuote(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isStringQuote(char)"})
  public void testIsStringQuote_givenArrayOfStringWithQuoteStrings_whenSpace_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    String[][] quoteStrings = new String[][] {new String[] {"Quote Strings"}};

    // Act
    boolean actualIsStringQuoteResult =
        new SQLIdentifierDetector(dialect, '\u0001', quoteStrings).isStringQuote(' ');

    // Assert
    verify(dialect).getStringQuoteStrings();
    assertTrue(actualIsStringQuoteResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#getStructSeparator()}.
   *
   * <p>Method under test: {@link SQLIdentifierDetector#getStructSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char SQLIdentifierDetector.getStructSeparator()"})
  public void testGetStructSeparator() {
    // Arrange, Act and Assert
    assertEquals('\u0000', new SQLIdentifierDetector(mock(SQLDialect.class)).getStructSeparator());
  }

  /**
   * Test {@link SQLIdentifierDetector#containsSeparator(String)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#containsSeparator(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.containsSeparator(String)"})
  public void testContainsSeparator_givenSQLDialectGetStructSeparatorReturnA_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualContainsSeparatorResult =
        new SQLIdentifierDetector(dialect).containsSeparator("42");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertFalse(actualContainsSeparatorResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#containsSeparator(String)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code a}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#containsSeparator(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.containsSeparator(String)"})
  public void testContainsSeparator_givenSQLDialectGetStructSeparatorReturnA_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('a');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualContainsSeparatorResult =
        new SQLIdentifierDetector(dialect)
            .containsSeparator("org.jkiss.dbeaver.model.text.parser.TPToken");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualContainsSeparatorResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#splitIdentifier(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#splitIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLIdentifierDetector.splitIdentifier(String)"})
  public void testSplitIdentifier_givenArrayOfStringWithEmptyStringAnd42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('a');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {new String[] {"", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String[] actualSplitIdentifierResult =
        new SQLIdentifierDetector(dialect)
            .splitIdentifier("org.jkiss.dbeaver.model.text.parser.TPToken");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertArrayEquals(
        new String[] {"org.jkiss.dbe", "ver.model.text.p", "rser.TPToken"},
        actualSplitIdentifierResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#splitIdentifier(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#splitIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLIdentifierDetector.splitIdentifier(String)"})
  public void testSplitIdentifier_givenArrayOfStringWithFooAnd42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('a');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String[] actualSplitIdentifierResult =
        new SQLIdentifierDetector(dialect)
            .splitIdentifier("org.jkiss.dbeaver.model.text.parser.TPToken");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertArrayEquals(
        new String[] {"org.jkiss.dbe", "ver.model.text.p", "rser.TPToken"},
        actualSplitIdentifierResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#splitIdentifier(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#splitIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLIdentifierDetector.splitIdentifier(String)"})
  public void testSplitIdentifier_givenArrayOfStringWithFooAndEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('a');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {new String[] {"foo", ""}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String[] actualSplitIdentifierResult =
        new SQLIdentifierDetector(dialect)
            .splitIdentifier("org.jkiss.dbeaver.model.text.parser.TPToken");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertArrayEquals(
        new String[] {"org.jkiss.dbe", "ver.model.text.p", "rser.TPToken"},
        actualSplitIdentifierResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#splitIdentifier(String)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#splitIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLIdentifierDetector.splitIdentifier(String)"})
  public void testSplitIdentifier_thenReturnArrayOfStringWith42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String[] actualSplitIdentifierResult = new SQLIdentifierDetector(dialect).splitIdentifier("42");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertArrayEquals(new String[] {"42"}, actualSplitIdentifierResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#splitIdentifier(String)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#splitIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] SQLIdentifierDetector.splitIdentifier(String)"})
  public void testSplitIdentifier_thenReturnArrayOfStringWith422() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String[] actualSplitIdentifierResult = new SQLIdentifierDetector(dialect).splitIdentifier("42");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertArrayEquals(new String[] {"42"}, actualSplitIdentifierResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordStart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code A}.
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordStart(char)"})
  public void testIsWordStart_givenSQLDialectGetStructSeparatorReturnA_whenA_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordStartResult = new SQLIdentifierDetector(dialect).isWordStart('A');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsWordStartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordStart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierStart(char)} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordStart(char)"})
  public void testIsWordStart_givenSQLDialectValidIdentifierStartReturnFalse_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierStart(anyChar())).thenReturn(false);
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    String[][] quoteStrings = new String[][] {new String[] {"Quote Strings"}};

    // Act
    boolean actualIsWordStartResult =
        new SQLIdentifierDetector(dialect, '\u0003', quoteStrings).isWordStart('\u0003');

    // Assert
    verify(dialect).getStringQuoteStrings();
    verify(dialect).validIdentifierStart('\u0003');
    assertFalse(actualIsWordStartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordStart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierStart(char)} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordStart(char)"})
  public void testIsWordStart_givenSQLDialectValidIdentifierStartReturnTrue_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierStart(anyChar())).thenReturn(true);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordStartResult = new SQLIdentifierDetector(dialect).isWordStart('\u0003');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    verify(dialect).validIdentifierStart('\u0003');
    assertTrue(actualIsWordStartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordPart(char)"})
  public void testIsWordPart_givenArrayOfStringWithIdentifierQuoteStrings_whenA_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordPartResult = new SQLIdentifierDetector(dialect).isWordPart('A');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   *   <li>When {@code $}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordPart(char)"})
  public void testIsWordPart_givenArrayOfStringWithIdentifierQuoteStrings_whenDollarSign() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordPartResult = new SQLIdentifierDetector(dialect).isWordPart('$');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   *   <li>When space.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordPart(char)"})
  public void testIsWordPart_givenArrayOfStringWithIdentifierQuoteStrings_whenSpace() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordPartResult = new SQLIdentifierDetector(dialect).isWordPart(' ');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordPart(char)"})
  public void testIsWordPart_givenSQLDialectGetStructSeparatorReturnQuestionMark() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('?');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordPartResult = new SQLIdentifierDetector(dialect).isWordPart('?');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordPart(char)"})
  public void testIsWordPart_givenSQLDialectValidIdentifierPartReturnFalse_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordPartResult = new SQLIdentifierDetector(dialect).isWordPart('?');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    verify(dialect).validIdentifierPart('?', true);
    assertFalse(actualIsWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isWordPart(char)"})
  public void testIsWordPart_givenSQLDialectValidIdentifierPartReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsWordPartResult = new SQLIdentifierDetector(dialect).isWordPart('?');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    verify(dialect).validIdentifierPart('?', true);
    assertTrue(actualIsWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isPlainWordPart(char)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#validIdentifierPart(char, boolean)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isPlainWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isPlainWordPart(char)"})
  public void testIsPlainWordPart_givenSQLDialectValidIdentifierPartReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(true);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsPlainWordPartResult = new SQLIdentifierDetector(dialect).isPlainWordPart('?');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    verify(dialect).validIdentifierPart('?', false);
    assertTrue(actualIsPlainWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isPlainWordPart(char)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isPlainWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isPlainWordPart(char)"})
  public void testIsPlainWordPart_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.validIdentifierPart(anyChar(), anyBoolean())).thenReturn(false);
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    String[][] quoteStrings = new String[][] {new String[] {"Quote Strings"}};

    // Act
    boolean actualIsPlainWordPartResult =
        new SQLIdentifierDetector(dialect, '\u0001', quoteStrings).isPlainWordPart('?');

    // Assert
    verify(dialect).getStringQuoteStrings();
    verify(dialect).validIdentifierPart('?', false);
    assertFalse(actualIsPlainWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isPlainWordPart(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isPlainWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isPlainWordPart(char)"})
  public void testIsPlainWordPart_whenA_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsPlainWordPartResult = new SQLIdentifierDetector(dialect).isPlainWordPart('A');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsPlainWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isPlainWordPart(char)}.
   *
   * <ul>
   *   <li>When {@code $}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isPlainWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isPlainWordPart(char)"})
  public void testIsPlainWordPart_whenDollarSign_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsPlainWordPartResult = new SQLIdentifierDetector(dialect).isPlainWordPart('$');

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsPlainWordPartResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isQuoted(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string.
   *   <li>When {@code ABC123}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isQuoted(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isQuoted(String)"})
  public void testIsQuoted_givenArrayOfStringWithEmptyString_whenAbc123_thenReturnTrue() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {new String[] {""}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsQuotedResult = new SQLIdentifierDetector(dialect).isQuoted("ABC123");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualIsQuotedResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isQuoted(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isQuoted(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isQuoted(String)"})
  public void testIsQuoted_givenArrayOfStringWithIdentifierQuoteStrings_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsQuotedResult = new SQLIdentifierDetector(dialect).isQuoted("ABC123");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertFalse(actualIsQuotedResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#isQuoted(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code null}.
   *   <li>When {@code ABC123}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#isQuoted(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLIdentifierDetector.isQuoted(String)"})
  public void testIsQuoted_givenArrayOfStringWithNull_whenAbc123_thenReturnFalse() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {new String[] {null}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    boolean actualIsQuotedResult = new SQLIdentifierDetector(dialect).isQuoted("ABC123");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertFalse(actualIsQuotedResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#removeQuotes(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with empty string and {@code 42}.
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#removeQuotes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLIdentifierDetector.removeQuotes(String)"})
  public void testRemoveQuotes_givenArrayOfStringWithEmptyStringAnd42_whenName_thenReturnName() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {new String[] {"", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String actualRemoveQuotesResult = new SQLIdentifierDetector(dialect).removeQuotes("Name");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals("Name", actualRemoveQuotesResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#removeQuotes(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#removeQuotes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLIdentifierDetector.removeQuotes(String)"})
  public void testRemoveQuotes_givenArrayOfStringWithFooAnd42_whenName_thenReturnName() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String actualRemoveQuotesResult = new SQLIdentifierDetector(dialect).removeQuotes("Name");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals("Name", actualRemoveQuotesResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#removeQuotes(String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and empty string.
   *   <li>When {@code Name}.
   *   <li>Then return {@code N}.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#removeQuotes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLIdentifierDetector.removeQuotes(String)"})
  public void testRemoveQuotes_givenArrayOfStringWithFooAndEmptyString_whenName_thenReturnN() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {new String[] {"foo", ""}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});

    // Act
    String actualRemoveQuotesResult = new SQLIdentifierDetector(dialect).removeQuotes("Name");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals("N", actualRemoveQuotesResult);
  }

  /**
   * Test {@link SQLIdentifierDetector#extractIdentifier(IDocument, IRegion, SQLRuleManager)}.
   *
   * <ul>
   *   <li>Then return {@link WordRegion#identEnd} is minus one.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#extractIdentifier(IDocument, IRegion,
   * SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WordRegion SQLIdentifierDetector.extractIdentifier(IDocument, IRegion, SQLRuleManager)"
  })
  public void testExtractIdentifier_thenReturnIdentEndIsMinusOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector sqlIdentifierDetector = new SQLIdentifierDetector(dialect);
    Document document = new Document();

    // Act
    WordRegion actualExtractIdentifierResult =
        sqlIdentifierDetector.extractIdentifier(document, new Region(-1, 3), null);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals("", actualExtractIdentifierResult.identifier);
    assertEquals("", actualExtractIdentifierResult.word);
    assertEquals(-1, actualExtractIdentifierResult.identEnd);
    assertEquals(-1, actualExtractIdentifierResult.identStart);
    assertEquals(-1, actualExtractIdentifierResult.wordEnd);
    assertEquals(-1, actualExtractIdentifierResult.wordStart);
    assertTrue(actualExtractIdentifierResult.isEmpty());
  }

  /**
   * Test {@link SQLIdentifierDetector#extractIdentifier(IDocument, IRegion, SQLRuleManager)}.
   *
   * <ul>
   *   <li>Then return {@link WordRegion#identEnd} is two.
   * </ul>
   *
   * <p>Method under test: {@link SQLIdentifierDetector#extractIdentifier(IDocument, IRegion,
   * SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WordRegion SQLIdentifierDetector.extractIdentifier(IDocument, IRegion, SQLRuleManager)"
  })
  public void testExtractIdentifier_thenReturnIdentEndIsTwo() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector sqlIdentifierDetector = new SQLIdentifierDetector(dialect);
    Document document = new Document();

    // Act
    WordRegion actualExtractIdentifierResult =
        sqlIdentifierDetector.extractIdentifier(document, new Region(2, 3), null);

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertEquals("", actualExtractIdentifierResult.identifier);
    assertEquals("", actualExtractIdentifierResult.word);
    assertEquals(-1, actualExtractIdentifierResult.wordEnd);
    assertEquals(-1, actualExtractIdentifierResult.wordStart);
    assertEquals(2, actualExtractIdentifierResult.identEnd);
    assertEquals(2, actualExtractIdentifierResult.identStart);
    assertTrue(actualExtractIdentifierResult.isEmpty());
  }

  /**
   * Test WordRegion {@link WordRegion#extract(IDocument)}.
   *
   * <ul>
   *   <li>Given {@link WordRegion#WordRegion(int)} with offset is zero.
   * </ul>
   *
   * <p>Method under test: {@link WordRegion#extract(IDocument)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordRegion.extract(IDocument)"})
  public void testWordRegionExtract_givenWordRegionWithOffsetIsZero() throws BadLocationException {
    // Arrange
    WordRegion wordRegion = new WordRegion(0);

    // Act
    wordRegion.extract(new Document());

    // Assert
    assertEquals(0, wordRegion.wordEnd);
    assertEquals(0, wordRegion.wordStart);
  }

  /**
   * Test WordRegion {@link WordRegion#extract(IDocument)}.
   *
   * <ul>
   *   <li>Given {@link WordRegion#WordRegion(int)} with offset is zero {@link WordRegion#wordStart}
   *       is zero.
   * </ul>
   *
   * <p>Method under test: {@link WordRegion#extract(IDocument)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordRegion.extract(IDocument)"})
  public void testWordRegionExtract_givenWordRegionWithOffsetIsZeroWordStartIsZero()
      throws BadLocationException {
    // Arrange
    WordRegion wordRegion = new WordRegion(0);
    wordRegion.wordStart = 0;
    wordRegion.wordEnd = 0;

    // Act
    wordRegion.extract(new Document());

    // Assert that nothing has changed
    assertEquals(0, wordRegion.wordEnd);
    assertEquals(0, wordRegion.wordStart);
  }

  /**
   * Test WordRegion {@link WordRegion#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link WordRegion#WordRegion(int)} with offset is two {@link WordRegion#word} is
   *       {@code foo}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link WordRegion#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WordRegion.isEmpty()"})
  public void testWordRegionIsEmpty_givenWordRegionWithOffsetIsTwoWordIsFoo_thenReturnFalse() {
    // Arrange
    WordRegion wordRegion = new WordRegion(2);
    wordRegion.word = "foo";

    // Act and Assert
    assertFalse(wordRegion.isEmpty());
  }

  /**
   * Test WordRegion {@link WordRegion#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link WordRegion#WordRegion(int)} with offset is two.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WordRegion#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WordRegion.isEmpty()"})
  public void testWordRegionIsEmpty_givenWordRegionWithOffsetIsTwo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new WordRegion(2).isEmpty());
  }

  /**
   * Test WordRegion {@link WordRegion#WordRegion(int)}.
   *
   * <p>Method under test: {@link WordRegion#WordRegion(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WordRegion.<init>(int)"})
  public void testWordRegionNewWordRegion() {
    // Arrange and Act
    WordRegion actualWordRegion = new WordRegion(2);

    // Assert
    assertEquals("", actualWordRegion.identifier);
    assertEquals("", actualWordRegion.word);
    assertEquals(-1, actualWordRegion.wordEnd);
    assertEquals(-1, actualWordRegion.wordStart);
    assertEquals(2, actualWordRegion.identEnd);
    assertEquals(2, actualWordRegion.identStart);
    assertTrue(actualWordRegion.isEmpty());
  }
}
