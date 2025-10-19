package org.jkiss.dbeaver.tools.transfer.stream.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.data.formatters.BinaryFormatterHexNative;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.StandardSQLDialectQueryGenerator;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect.MultiValueInsertMode;
import org.jkiss.dbeaver.model.sql.SQLStateType;
import org.jkiss.dbeaver.model.sql.parser.EmptyTokenPredicateSet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StreamDataSourceDialectDiffblueTest {
  /**
   * Test new {@link StreamDataSourceDialect} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link StreamDataSourceDialect}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamDataSourceDialect.<init>()"})
  public void testNewStreamDataSourceDialect() {
    // Arrange and Act
    StreamDataSourceDialect actualStreamDataSourceDialect = new StreamDataSourceDialect();

    // Assert
    assertTrue(
        actualStreamDataSourceDialect.getNativeBinaryFormatter()
            instanceof BinaryFormatterHexNative);
    assertTrue(
        actualStreamDataSourceDialect.getQueryGenerator()
            instanceof StandardSQLDialectQueryGenerator);
    assertTrue(
        actualStreamDataSourceDialect.getSkipTokenPredicates() instanceof EmptyTokenPredicateSet);
    assertEquals("", actualStreamDataSourceDialect.getSearchStringEscape());
    assertEquals("*", actualStreamDataSourceDialect.getAllAttributesAlias());
    assertEquals("*", actualStreamDataSourceDialect.getDefaultGroupAttribute());
    assertEquals(".", actualStreamDataSourceDialect.getCatalogSeparator());
    assertEquals("Stream", actualStreamDataSourceDialect.getDialectName());
    assertEquals("basic", actualStreamDataSourceDialect.getDialectId());
    assertEquals('.', actualStreamDataSourceDialect.getStructSeparator());
    assertEquals('\u0000', actualStreamDataSourceDialect.getStringEscapeCharacter());
    assertNull(actualStreamDataSourceDialect.getDualTableName());
    assertNull(actualStreamDataSourceDialect.getTestSQL());
    assertNull(actualStreamDataSourceDialect.getScriptDelimiterRedefiner());
    assertNull(actualStreamDataSourceDialect.getInnerBlockPrefixes());
    assertNull(actualStreamDataSourceDialect.getBlockHeaderStrings());
    assertEquals(0, actualStreamDataSourceDialect.getCatalogUsage());
    assertEquals(0, actualStreamDataSourceDialect.getSchemaUsage());
    assertEquals(0, actualStreamDataSourceDialect.getParametersPrefixes().length);
    assertEquals(0, actualStreamDataSourceDialect.getExecuteKeywords().length);
    assertEquals(0, actualStreamDataSourceDialect.getGlobalVariables().length);
    assertEquals(1, actualStreamDataSourceDialect.getQueryKeywords().length);
    assertEquals(1, actualStreamDataSourceDialect.getScriptDelimiters().length);
    assertEquals(1, actualStreamDataSourceDialect.getBlockBoundStrings().length);
    String[][] identifierQuoteStrings = actualStreamDataSourceDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(1, actualStreamDataSourceDialect.getSingleLineComments().length);
    String[][] stringQuoteStrings = actualStreamDataSourceDialect.getStringQuoteStrings();
    assertEquals(1, stringQuoteStrings.length);
    assertEquals(1, actualStreamDataSourceDialect.getTransactionCommitKeywords().length);
    assertEquals(1, actualStreamDataSourceDialect.getTransactionRollbackKeywords().length);
    assertEquals(3, actualStreamDataSourceDialect.getDDLKeywords().length);
    assertEquals(31, actualStreamDataSourceDialect.getFunctions().size());
    assertEquals(4, actualStreamDataSourceDialect.getProjectionAliasVisibilityScope().size());
    assertEquals(412, actualStreamDataSourceDialect.getReservedWords().size());
    assertEquals(6, actualStreamDataSourceDialect.getDMLKeywords().length);
    assertEquals(8, actualStreamDataSourceDialect.getColumnQueryWords().size());
    assertEquals(8, actualStreamDataSourceDialect.getNonTransactionKeywords().length);
    assertEquals(
        MultiValueInsertMode.NOT_SUPPORTED,
        actualStreamDataSourceDialect.getDefaultMultiValueInsertMode());
    assertEquals(SQLStateType.UNKNOWN, actualStreamDataSourceDialect.getSQLStateType());
    assertFalse(actualStreamDataSourceDialect.hasCaseSensitiveFiltration());
    assertFalse(actualStreamDataSourceDialect.isDisableScriptEscapeProcessing());
    assertFalse(actualStreamDataSourceDialect.isEscapeBackslash());
    assertFalse(actualStreamDataSourceDialect.isStripCommentsBeforeBlocks());
    assertFalse(actualStreamDataSourceDialect.isAmbiguousCountBroken());
    assertFalse(actualStreamDataSourceDialect.isCRLFBroken());
    assertFalse(actualStreamDataSourceDialect.isDelimiterAfterBlock());
    assertFalse(actualStreamDataSourceDialect.isDelimiterAfterQuery());
    assertTrue(actualStreamDataSourceDialect.isCatalogAtStart());
    assertTrue(actualStreamDataSourceDialect.isQuoteReservedWords());
    assertTrue(actualStreamDataSourceDialect.isStandardSQL());
    assertSame(
        AbstractSQLDialect.IN_CLAUSE_PARENTHESES,
        actualStreamDataSourceDialect.getInClauseParentheses());
    assertSame(BasicSQLDialect.DEFAULT_IDENTIFIER_QUOTES, identifierQuoteStrings);
    assertSame(BasicSQLDialect.DEFAULT_STRING_QUOTES, stringQuoteStrings);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamDataSourceDialect#getDialectName()}
   *   <li>{@link StreamDataSourceDialect#getSQLStateType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StreamDataSourceDialect.getDialectName()",
    "SQLStateType StreamDataSourceDialect.getSQLStateType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    StreamDataSourceDialect streamDataSourceDialect = new StreamDataSourceDialect();

    // Act
    String actualDialectName = streamDataSourceDialect.getDialectName();

    // Assert
    assertEquals("Stream", actualDialectName);
    assertEquals(SQLStateType.UNKNOWN, streamDataSourceDialect.getSQLStateType());
  }

  /**
   * Test {@link StreamDataSourceDialect#useCaseInsensitiveNameLookup()}.
   *
   * <p>Method under test: {@link StreamDataSourceDialect#useCaseInsensitiveNameLookup()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamDataSourceDialect.useCaseInsensitiveNameLookup()"})
  public void testUseCaseInsensitiveNameLookup() {
    // Arrange, Act and Assert
    assertTrue(new StreamDataSourceDialect().useCaseInsensitiveNameLookup());
  }
}
