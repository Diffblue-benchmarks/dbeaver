package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataTypeProvider;
import org.jkiss.dbeaver.model.DBPIdentifierCase;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCDatabaseMetaData;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.formatters.BinaryFormatterHexNative;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.StandardSQLDialectQueryGenerator;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect.MultiValueInsertMode;
import org.jkiss.dbeaver.model.sql.SQLStateType;
import org.jkiss.dbeaver.model.sql.parser.EmptyTokenPredicateSet;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCSQLDialectDiffblueTest {
  /**
   * Test {@link JDBCSQLDialect#JDBCSQLDialect(String, String)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#JDBCSQLDialect(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.<init>(String, String)"})
  public void testNewJDBCSQLDialect() {
    // Arrange and Act
    JDBCSQLDialect actualJdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    // Assert
    assertTrue(actualJdbcsqlDialect.getNativeBinaryFormatter() instanceof BinaryFormatterHexNative);
    assertTrue(
        actualJdbcsqlDialect.getQueryGenerator() instanceof StandardSQLDialectQueryGenerator);
    assertTrue(actualJdbcsqlDialect.getSkipTokenPredicates() instanceof EmptyTokenPredicateSet);
    assertEquals("", actualJdbcsqlDialect.validCharacters);
    assertEquals("*", actualJdbcsqlDialect.getAllAttributesAlias());
    assertEquals("*", actualJdbcsqlDialect.getDefaultGroupAttribute());
    assertEquals(".", actualJdbcsqlDialect.getCatalogSeparator());
    assertEquals("42", actualJdbcsqlDialect.getDialectId());
    assertEquals("Name", actualJdbcsqlDialect.getDialectName());
    assertEquals('.', actualJdbcsqlDialect.getStructSeparator());
    assertEquals('\u0000', actualJdbcsqlDialect.getStringEscapeCharacter());
    assertNull(actualJdbcsqlDialect.getSearchStringEscape());
    assertNull(actualJdbcsqlDialect.getDualTableName());
    assertNull(actualJdbcsqlDialect.getTestSQL());
    assertNull(actualJdbcsqlDialect.getScriptDelimiterRedefiner());
    assertNull(actualJdbcsqlDialect.getInnerBlockPrefixes());
    assertNull(actualJdbcsqlDialect.getBlockHeaderStrings());
    assertNull(actualJdbcsqlDialect.getSQLStateType());
    assertEquals(0, actualJdbcsqlDialect.getExecuteKeywords().length);
    assertEquals(0, actualJdbcsqlDialect.getParametersPrefixes().length);
    assertEquals(0, actualJdbcsqlDialect.getGlobalVariables().length);
    assertEquals(1, actualJdbcsqlDialect.getIdentifierQuoteStrings().length);
    assertEquals(1, actualJdbcsqlDialect.getQueryKeywords().length);
    assertEquals(1, actualJdbcsqlDialect.getScriptDelimiters().length);
    assertEquals(1, actualJdbcsqlDialect.getBlockBoundStrings().length);
    assertEquals(1, actualJdbcsqlDialect.getSingleLineComments().length);
    String[][] stringQuoteStrings = actualJdbcsqlDialect.getStringQuoteStrings();
    assertEquals(1, stringQuoteStrings.length);
    assertEquals(1, actualJdbcsqlDialect.getTransactionCommitKeywords().length);
    assertEquals(1, actualJdbcsqlDialect.getTransactionRollbackKeywords().length);
    assertEquals(3, actualJdbcsqlDialect.getDDLKeywords().length);
    assertEquals(31, actualJdbcsqlDialect.getFunctions().size());
    assertEquals(4, actualJdbcsqlDialect.getProjectionAliasVisibilityScope().size());
    assertEquals(412, actualJdbcsqlDialect.getReservedWords().size());
    assertEquals(6, actualJdbcsqlDialect.getDMLKeywords().length);
    assertEquals(8, actualJdbcsqlDialect.getColumnQueryWords().size());
    assertEquals(8, actualJdbcsqlDialect.getNonTransactionKeywords().length);
    assertEquals(DBPIdentifierCase.MIXED, actualJdbcsqlDialect.getDefaultIdentifiersCase());
    assertEquals(
        MultiValueInsertMode.NOT_SUPPORTED, actualJdbcsqlDialect.getDefaultMultiValueInsertMode());
    assertFalse(actualJdbcsqlDialect.isCatalogAtStart());
    assertFalse(actualJdbcsqlDialect.supportsQuotedMixedCase());
    assertFalse(actualJdbcsqlDialect.supportsSubqueries());
    assertFalse(actualJdbcsqlDialect.supportsUnquotedMixedCase());
    assertFalse(actualJdbcsqlDialect.hasCaseSensitiveFiltration());
    assertFalse(actualJdbcsqlDialect.isDisableScriptEscapeProcessing());
    assertFalse(actualJdbcsqlDialect.isEscapeBackslash());
    assertFalse(actualJdbcsqlDialect.isStripCommentsBeforeBlocks());
    assertFalse(actualJdbcsqlDialect.isAmbiguousCountBroken());
    assertFalse(actualJdbcsqlDialect.isCRLFBroken());
    assertFalse(actualJdbcsqlDialect.isDelimiterAfterBlock());
    assertFalse(actualJdbcsqlDialect.isDelimiterAfterQuery());
    assertTrue(actualJdbcsqlDialect.isQuoteReservedWords());
    assertTrue(actualJdbcsqlDialect.isStandardSQL());
    assertEquals(Integer.MAX_VALUE, actualJdbcsqlDialect.getCatalogUsage());
    assertEquals(Integer.MAX_VALUE, actualJdbcsqlDialect.getSchemaUsage());
    assertSame(
        AbstractSQLDialect.IN_CLAUSE_PARENTHESES, actualJdbcsqlDialect.getInClauseParentheses());
    assertSame(BasicSQLDialect.DEFAULT_STRING_QUOTES, stringQuoteStrings);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings2() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenThrow(new SQLException());
    when(metaData.storesUpperCaseIdentifiers()).thenThrow(new SQLException());
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenThrow(new SQLException());
    when(metaData.supportsCatalogsInDataManipulation()).thenThrow(new SQLException());
    when(metaData.supportsCorrelatedSubqueries()).thenThrow(new SQLException());
    when(metaData.supportsMixedCaseIdentifiers()).thenThrow(new SQLException());
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenThrow(new SQLException());
    when(metaData.supportsSchemasInDataManipulation()).thenThrow(new SQLException());
    when(metaData.getSQLStateType()).thenThrow(new SQLException());
    when(metaData.getCatalogSeparator()).thenThrow(new SQLException());
    when(metaData.getExtraNameCharacters()).thenThrow(new SQLException());
    when(metaData.getIdentifierQuoteString()).thenThrow(new SQLException());
    when(metaData.getNumericFunctions()).thenThrow(new SQLException());
    when(metaData.getSQLKeywords()).thenThrow(new SQLException());
    when(metaData.getSearchStringEscape()).thenThrow(new SQLException());

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    assertEquals("", jdbcsqlDialect.getSearchStringEscape());
    assertEquals(0, jdbcsqlDialect.getCatalogUsage());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(3, jdbcsqlDialect.getSchemaUsage());
    assertEquals(31, jdbcsqlDialect.getFunctions().size());
    assertEquals(412, jdbcsqlDialect.getReservedWords().size());
    assertArrayEquals(new String[] {"\"", "\""}, identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings3() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesLowerCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(false);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesLowerCaseIdentifiers();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings4() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesLowerCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(false);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesLowerCaseQuotedIdentifiers();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings5() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(false);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings6() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(false);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings7() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(false);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings8() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(false);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings9() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(".", jdbcsqlDialect.getCatalogSeparator());
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings10() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn(null);
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(0, jdbcsqlDialect.getIdentifierQuoteStrings().length);
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings11() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(0, jdbcsqlDialect.getIdentifierQuoteStrings().length);
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings12() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(34, jdbcsqlDialect.getFunctions().size());
    assertEquals(416, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings13() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesLowerCaseIdentifiers()).thenReturn(false);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(false);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesLowerCaseIdentifiers();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings14() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesLowerCaseQuotedIdentifiers()).thenReturn(false);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(false);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesLowerCaseQuotedIdentifiers();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Given {@code ;,}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_givenSemicolonComma() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn(";,");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(416, jdbcsqlDialect.getReservedWords().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCDatabaseMetaData#supportsSubqueriesInComparisons()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenCallsSupportsSubqueriesInComparisons()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(false);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.supportsSubqueriesInComparisons()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    verify(metaData).supportsSubqueriesInComparisons();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} CatalogUsage is eleven.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42CatalogUsageIsEleven()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(false);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(11, jdbcsqlDialect.getCatalogUsage());
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} CatalogUsage is fourteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42CatalogUsageIsFourteen()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(false);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(14, jdbcsqlDialect.getCatalogUsage());
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} CatalogUsage is thirteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42CatalogUsageIsThirteen()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(false);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(13, jdbcsqlDialect.getCatalogUsage());
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} SQLStateType is {@code SQL99}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42SQLStateTypeIsSql99()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(2);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
    assertEquals(SQLStateType.SQL99, jdbcsqlDialect.getSQLStateType());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} SQLStateType is {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42SQLStateTypeIsUnknown()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(12);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
    assertEquals(SQLStateType.UNKNOWN, jdbcsqlDialect.getSQLStateType());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} SchemaUsage is eleven.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42SchemaUsageIsEleven()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(false);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(11, jdbcsqlDialect.getSchemaUsage());
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} SchemaUsage is fourteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42SchemaUsageIsFourteen()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(false);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(14, jdbcsqlDialect.getSchemaUsage());
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then {@link JDBCSQLDialect#JDBCSQLDialect(String, String)} with {@code Name} and id is
   *       {@code 42} SchemaUsage is thirteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_thenJDBCSQLDialectWithNameAndIdIs42SchemaUsageIsThirteen()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(false);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals("Catalog Separator", jdbcsqlDialect.getCatalogSeparator());
    String[][] identifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    assertEquals(1, identifierQuoteStrings.length);
    assertEquals(13, jdbcsqlDialect.getSchemaUsage());
    assertEquals(35, jdbcsqlDialect.getFunctions().size());
    assertEquals(SQLStateType.XOPEN, jdbcsqlDialect.getSQLStateType());
    assertArrayEquals(
        new String[] {"Identifier Quote String", "Identifier Quote String"},
        identifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>When {@link JDBCDatabaseMetaData} {@link JDBCDatabaseMetaData#getCatalogSeparator()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_whenJDBCDatabaseMetaDataGetCatalogSeparatorReturnNull()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn(null);
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(".", jdbcsqlDialect.getCatalogSeparator());
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>When {@link JDBCDatabaseMetaData} {@link JDBCDatabaseMetaData#getExtraNameCharacters()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_whenJDBCDatabaseMetaDataGetExtraNameCharactersReturnNull()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn(null);
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(417, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>When {@link JDBCDatabaseMetaData} {@link JDBCDatabaseMetaData#getNumericFunctions()}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#initDriverSettings(JDBCSession, JDBCDataSource,
   * JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCSQLDialect.initDriverSettings(JDBCSession, JDBCDataSource, JDBCDatabaseMetaData)"
  })
  public void testInitDriverSettings_whenJDBCDatabaseMetaDataGetNumericFunctionsReturnNull()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.isCatalogAtStart()).thenReturn(true);
    when(metaData.storesUpperCaseIdentifiers()).thenReturn(true);
    when(metaData.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsCatalogsInDataManipulation()).thenReturn(true);
    when(metaData.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsCatalogsInProcedureCalls()).thenReturn(true);
    when(metaData.supportsCatalogsInTableDefinitions()).thenReturn(true);
    when(metaData.supportsCorrelatedSubqueries()).thenReturn(true);
    when(metaData.supportsMixedCaseIdentifiers()).thenReturn(true);
    when(metaData.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    when(metaData.supportsSchemasInDataManipulation()).thenReturn(true);
    when(metaData.supportsSchemasInIndexDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    when(metaData.supportsSchemasInProcedureCalls()).thenReturn(true);
    when(metaData.supportsSchemasInTableDefinitions()).thenReturn(true);
    when(metaData.getSQLStateType()).thenReturn(1);
    when(metaData.getCatalogSeparator()).thenReturn("Catalog Separator");
    when(metaData.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    when(metaData.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    when(metaData.getNumericFunctions()).thenReturn(null);
    when(metaData.getSQLKeywords()).thenReturn("Sql Keywords");
    when(metaData.getSearchStringEscape()).thenReturn("Search String Escape");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");

    // Act
    jdbcsqlDialect.initDriverSettings(session, dataSource, metaData);

    // Assert
    verify(metaData).getCatalogSeparator();
    verify(metaData).getExtraNameCharacters();
    verify(metaData).getIdentifierQuoteString();
    verify(metaData).getNumericFunctions();
    verify(metaData).getSQLKeywords();
    verify(metaData).getSQLStateType();
    verify(metaData).getSearchStringEscape();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    verify(metaData).isCatalogAtStart();
    verify(metaData).storesUpperCaseIdentifiers();
    verify(metaData).storesUpperCaseQuotedIdentifiers();
    verify(metaData).supportsCatalogsInDataManipulation();
    verify(metaData).supportsCatalogsInIndexDefinitions();
    verify(metaData).supportsCatalogsInPrivilegeDefinitions();
    verify(metaData).supportsCatalogsInProcedureCalls();
    verify(metaData).supportsCatalogsInTableDefinitions();
    verify(metaData).supportsCorrelatedSubqueries();
    verify(metaData).supportsMixedCaseIdentifiers();
    verify(metaData).supportsMixedCaseQuotedIdentifiers();
    verify(metaData).supportsSchemasInDataManipulation();
    verify(metaData).supportsSchemasInIndexDefinitions();
    verify(metaData).supportsSchemasInPrivilegeDefinitions();
    verify(metaData).supportsSchemasInProcedureCalls();
    verify(metaData).supportsSchemasInTableDefinitions();
    assertEquals(15, jdbcsqlDialect.getCatalogUsage());
    assertEquals(15, jdbcsqlDialect.getSchemaUsage());
    assertEquals(34, jdbcsqlDialect.getFunctions().size());
    assertEquals(416, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#getExecuteKeywords()}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#getExecuteKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] JDBCSQLDialect.getExecuteKeywords()"})
  public void testGetExecuteKeywords() {
    // Arrange, Act and Assert
    assertEquals(0, new JDBCSQLDialect("Name", "42").getExecuteKeywords().length);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCSQLDialect#setIdentifierQuoteString(String[][])}
   *   <li>{@link JDBCSQLDialect#setSupportsQuotedMixedCase(boolean)}
   *   <li>{@link JDBCSQLDialect#setSupportsSubqueries(boolean)}
   *   <li>{@link JDBCSQLDialect#setSupportsUnquotedMixedCase(boolean)}
   *   <li>{@link JDBCSQLDialect#setUnquotedIdentCase(DBPIdentifierCase)}
   *   <li>{@link JDBCSQLDialect#getCatalogSeparator()}
   *   <li>{@link JDBCSQLDialect#getCatalogUsage()}
   *   <li>{@link JDBCSQLDialect#getDefaultIdentifiersCase()}
   *   <li>{@link JDBCSQLDialect#getDialectId()}
   *   <li>{@link JDBCSQLDialect#getDialectName()}
   *   <li>{@link JDBCSQLDialect#getIdentifierQuoteStrings()}
   *   <li>{@link JDBCSQLDialect#getSQLStateType()}
   *   <li>{@link JDBCSQLDialect#getSchemaUsage()}
   *   <li>{@link JDBCSQLDialect#getSearchStringEscape()}
   *   <li>{@link JDBCSQLDialect#isCatalogAtStart()}
   *   <li>{@link JDBCSQLDialect#supportsQuotedMixedCase()}
   *   <li>{@link JDBCSQLDialect#supportsSubqueries()}
   *   <li>{@link JDBCSQLDialect#supportsUnquotedMixedCase()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.getCatalogSeparator()",
    "int JDBCSQLDialect.getCatalogUsage()",
    "DBPIdentifierCase JDBCSQLDialect.getDefaultIdentifiersCase()",
    "String JDBCSQLDialect.getDialectId()",
    "String JDBCSQLDialect.getDialectName()",
    "String[][] JDBCSQLDialect.getIdentifierQuoteStrings()",
    "SQLStateType JDBCSQLDialect.getSQLStateType()",
    "int JDBCSQLDialect.getSchemaUsage()",
    "String JDBCSQLDialect.getSearchStringEscape()",
    "boolean JDBCSQLDialect.isCatalogAtStart()",
    "void JDBCSQLDialect.setIdentifierQuoteString(String[][])",
    "void JDBCSQLDialect.setSupportsQuotedMixedCase(boolean)",
    "void JDBCSQLDialect.setSupportsSubqueries(boolean)",
    "void JDBCSQLDialect.setSupportsUnquotedMixedCase(boolean)",
    "void JDBCSQLDialect.setUnquotedIdentCase(DBPIdentifierCase)",
    "boolean JDBCSQLDialect.supportsQuotedMixedCase()",
    "boolean JDBCSQLDialect.supportsSubqueries()",
    "boolean JDBCSQLDialect.supportsUnquotedMixedCase()"
  })
  public void testGettersAndSetters() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    String[][] identifierQuoteString = new String[][] {new String[] {"Identifier Quote String"}};

    // Act
    jdbcsqlDialect.setIdentifierQuoteString(identifierQuoteString);
    jdbcsqlDialect.setSupportsQuotedMixedCase(true);
    jdbcsqlDialect.setSupportsSubqueries(true);
    jdbcsqlDialect.setSupportsUnquotedMixedCase(true);
    jdbcsqlDialect.setUnquotedIdentCase(DBPIdentifierCase.UPPER);
    String actualCatalogSeparator = jdbcsqlDialect.getCatalogSeparator();
    int actualCatalogUsage = jdbcsqlDialect.getCatalogUsage();
    DBPIdentifierCase actualDefaultIdentifiersCase = jdbcsqlDialect.getDefaultIdentifiersCase();
    String actualDialectId = jdbcsqlDialect.getDialectId();
    String actualDialectName = jdbcsqlDialect.getDialectName();
    String[][] actualIdentifierQuoteStrings = jdbcsqlDialect.getIdentifierQuoteStrings();
    SQLStateType actualSQLStateType = jdbcsqlDialect.getSQLStateType();
    int actualSchemaUsage = jdbcsqlDialect.getSchemaUsage();
    String actualSearchStringEscape = jdbcsqlDialect.getSearchStringEscape();
    boolean actualIsCatalogAtStartResult = jdbcsqlDialect.isCatalogAtStart();
    boolean actualSupportsQuotedMixedCaseResult = jdbcsqlDialect.supportsQuotedMixedCase();
    boolean actualSupportsSubqueriesResult = jdbcsqlDialect.supportsSubqueries();

    // Assert
    assertEquals(".", actualCatalogSeparator);
    assertEquals("42", actualDialectId);
    assertEquals("Name", actualDialectName);
    assertNull(actualSearchStringEscape);
    assertNull(actualSQLStateType);
    assertEquals(1, actualIdentifierQuoteStrings.length);
    assertEquals(DBPIdentifierCase.MIXED, actualDefaultIdentifiersCase);
    assertFalse(actualIsCatalogAtStartResult);
    assertTrue(actualSupportsQuotedMixedCaseResult);
    assertTrue(actualSupportsSubqueriesResult);
    assertTrue(jdbcsqlDialect.supportsUnquotedMixedCase());
    assertEquals(Integer.MAX_VALUE, actualCatalogUsage);
    assertEquals(Integer.MAX_VALUE, actualSchemaUsage);
    assertSame(identifierQuoteString, actualIdentifierQuoteStrings);
    assertArrayEquals(new String[] {"Identifier Quote String"}, actualIdentifierQuoteStrings[0]);
  }

  /**
   * Test {@link JDBCSQLDialect#getStructSeparator()}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#getStructSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char JDBCSQLDialect.getStructSeparator()"})
  public void testGetStructSeparator() {
    // Arrange, Act and Assert
    assertEquals('.', new JDBCSQLDialect("Name", "42").getStructSeparator());
  }

  /**
   * Test {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When {@code 1}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_when1_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JDBCSQLDialect("Name", "42").validIdentifierPart('1', true));
  }

  /**
   * Test {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenA_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JDBCSQLDialect("Name", "42").validIdentifierPart('A', true));
  }

  /**
   * Test {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When start of heading.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenStartOfHeading_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JDBCSQLDialect("Name", "42").validIdentifierPart('\u0001', true));
  }

  /**
   * Test {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When start of heading.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenStartOfHeading_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(new JDBCSQLDialect("Name", "42").validIdentifierPart('\u0001', false));
  }

  /**
   * Test {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenUnderscore_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new JDBCSQLDialect("Name", "42").validIdentifierPart('_', false));
  }

  /**
   * Test {@link JDBCSQLDialect#storesUnquotedCase()}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#storesUnquotedCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPIdentifierCase JDBCSQLDialect.storesUnquotedCase()"})
  public void testStoresUnquotedCase() {
    // Arrange, Act and Assert
    assertEquals(DBPIdentifierCase.MIXED, new JDBCSQLDialect("Name", "42").storesUnquotedCase());
  }

  /**
   * Test {@link JDBCSQLDialect#storesQuotedCase()}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#storesQuotedCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPIdentifierCase JDBCSQLDialect.storesQuotedCase()"})
  public void testStoresQuotedCase() {
    // Arrange, Act and Assert
    assertEquals(DBPIdentifierCase.MIXED, new JDBCSQLDialect("Name", "42").storesQuotedCase());
  }

  /**
   * Test {@link JDBCSQLDialect#supportsUpsertStatement()}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#supportsUpsertStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCSQLDialect.supportsUpsertStatement()"})
  public void testSupportsUpsertStatement() {
    // Arrange, Act and Assert
    assertFalse(new JDBCSQLDialect("Name", "42").supportsUpsertStatement());
  }

  /**
   * Test {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return size is nineteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection JDBCSQLDialect.getDataTypes(DBPDataSource)"})
  public void testGetDataTypes_givenArrayList_thenReturnSizeIsNineteen() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    Mockito.<Collection<? extends DBSDataType>>when(dataSource.getLocalDataTypes())
        .thenReturn(new ArrayList<>());

    // Act
    Collection<String> actualDataTypes = jdbcsqlDialect.getDataTypes(dataSource);

    // Assert
    verify(dataSource).getLocalDataTypes();
    assertEquals(19, actualDataTypes.size());
    assertEquals(412, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#resolveDataKind(String, int)} return
   *       {@code STRUCT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection JDBCSQLDialect.getDataTypes(DBPDataSource)"})
  public void testGetDataTypes_givenJDBCDataSourceResolveDataKindReturnStruct() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.resolveDataKind(Mockito.<String>any(), anyInt()))
        .thenReturn(DBPDataKind.STRUCT);
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(dataSource);
    JDBCRemoteInstance jdbcRemoteInstance2 = new JDBCRemoteInstance(mock(JDBCDataSource.class));
    JDBCDataType<DBSObject> typed =
        new JDBCDataType<>(jdbcRemoteInstance2, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(jdbcRemoteInstance, typed);

    ArrayList<DBSDataType> dbsDataTypeList = new ArrayList<>();
    dbsDataTypeList.add(jdbcDataType);

    JDBCDataSource dataSource2 = mock(JDBCDataSource.class);
    Mockito.<Collection<? extends DBSDataType>>when(dataSource2.getLocalDataTypes())
        .thenReturn(dbsDataTypeList);

    // Act
    Collection<String> actualDataTypes = jdbcsqlDialect.getDataTypes(dataSource2);

    // Assert
    verify(dataSource2).getLocalDataTypes();
    verify(dataSource).resolveDataKind("Name", 42);
    assertEquals(19, actualDataTypes.size());
    assertEquals(412, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection JDBCSQLDialect.getDataTypes(DBPDataSource)"})
  public void testGetDataTypes_thenReturnSizeIsOne() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.resolveDataKind(Mockito.<String>any(), anyInt()))
        .thenReturn(DBPDataKind.BOOLEAN);
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(dataSource);
    JDBCRemoteInstance jdbcRemoteInstance2 = new JDBCRemoteInstance(mock(JDBCDataSource.class));
    JDBCDataType<DBSObject> typed =
        new JDBCDataType<>(jdbcRemoteInstance2, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(jdbcRemoteInstance, typed);

    ArrayList<DBSDataType> dbsDataTypeList = new ArrayList<>();
    dbsDataTypeList.add(jdbcDataType);

    JDBCDataSource dataSource2 = mock(JDBCDataSource.class);
    Mockito.<Collection<? extends DBSDataType>>when(dataSource2.getLocalDataTypes())
        .thenReturn(dbsDataTypeList);

    // Act
    Collection<String> actualDataTypes = jdbcsqlDialect.getDataTypes(dataSource2);

    // Assert
    verify(dataSource2).getLocalDataTypes();
    verify(dataSource).resolveDataKind("Name", 42);
    assertEquals(1, actualDataTypes.size());
    assertEquals(413, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSource}.
   *   <li>Then return size is nineteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#getDataTypes(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection JDBCSQLDialect.getDataTypes(DBPDataSource)"})
  public void testGetDataTypes_whenDBPDataSource_thenReturnSizeIsNineteen() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    // Act and Assert
    assertEquals(19, jdbcsqlDialect.getDataTypes(mock(DBPDataSource.class)).size());
    assertEquals(412, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#loadDataTypesFromDatabase(JDBCDataSource)}.
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadDataTypesFromDatabase(JDBCDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadDataTypesFromDatabase(JDBCDataSource)"})
  public void testLoadDataTypesFromDatabase() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.resolveDataKind(Mockito.<String>any(), anyInt()))
        .thenReturn(DBPDataKind.BOOLEAN);
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(dataSource);
    JDBCRemoteInstance jdbcRemoteInstance2 = new JDBCRemoteInstance(mock(JDBCDataSource.class));
    JDBCDataType<DBSObject> typed =
        new JDBCDataType<>(jdbcRemoteInstance2, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(jdbcRemoteInstance, typed);

    ArrayList<DBSDataType> dbsDataTypeList = new ArrayList<>();
    dbsDataTypeList.add(jdbcDataType);

    JDBCDataSource dataSource2 = mock(JDBCDataSource.class);
    Mockito.<Collection<? extends DBSDataType>>when(dataSource2.getLocalDataTypes())
        .thenReturn(dbsDataTypeList);

    // Act
    jdbcsqlDialect.loadDataTypesFromDatabase(dataSource2);

    // Assert
    verify(dataSource2).getLocalDataTypes();
    verify(dataSource).resolveDataKind("Name", 42);
    assertEquals(413, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#loadDataTypesFromDatabase(JDBCDataSource)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadDataTypesFromDatabase(JDBCDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadDataTypesFromDatabase(JDBCDataSource)"})
  public void testLoadDataTypesFromDatabase_givenArrayList() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    Mockito.<Collection<? extends DBSDataType>>when(dataSource.getLocalDataTypes())
        .thenReturn(new ArrayList<>());

    // Act
    jdbcsqlDialect.loadDataTypesFromDatabase(dataSource);

    // Assert that nothing has changed
    verify(dataSource).getLocalDataTypes();
    assertEquals(412, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#loadDataTypesFromDatabase(JDBCDataSource)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#resolveDataKind(String, int)} return
   *       {@code STRUCT}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadDataTypesFromDatabase(JDBCDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadDataTypesFromDatabase(JDBCDataSource)"})
  public void testLoadDataTypesFromDatabase_givenJDBCDataSourceResolveDataKindReturnStruct() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.resolveDataKind(Mockito.<String>any(), anyInt()))
        .thenReturn(DBPDataKind.STRUCT);
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(dataSource);
    JDBCRemoteInstance jdbcRemoteInstance2 = new JDBCRemoteInstance(mock(JDBCDataSource.class));
    JDBCDataType<DBSObject> typed =
        new JDBCDataType<>(jdbcRemoteInstance2, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(jdbcRemoteInstance, typed);

    ArrayList<DBSDataType> dbsDataTypeList = new ArrayList<>();
    dbsDataTypeList.add(jdbcDataType);

    JDBCDataSource dataSource2 = mock(JDBCDataSource.class);
    Mockito.<Collection<? extends DBSDataType>>when(dataSource2.getLocalDataTypes())
        .thenReturn(dbsDataTypeList);

    // Act
    jdbcsqlDialect.loadDataTypesFromDatabase(dataSource2);

    // Assert that nothing has changed
    verify(dataSource2).getLocalDataTypes();
    verify(dataSource).resolveDataKind("Name", 42);
    assertEquals(412, jdbcsqlDialect.getReservedWords().size());
  }

  /**
   * Test {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)"})
  public void testLoadFunctions_given42_whenHashSetAdd42_thenThrowSQLException()
      throws SQLException, DBException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.getNumericFunctions()).thenThrow(new SQLException());

    HashSet<String> allFunctions = new HashSet<>();
    allFunctions.add("42");

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcsqlDialect.loadFunctions(session, metaData, allFunctions));
    verify(metaData).getNumericFunctions();
  }

  /**
   * Test {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link HashSet#HashSet()} add empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)"})
  public void testLoadFunctions_givenEmptyString_whenHashSetAddEmptyString()
      throws SQLException, DBException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.getNumericFunctions()).thenThrow(new SQLException());

    HashSet<String> allFunctions = new HashSet<>();
    allFunctions.add("");
    allFunctions.add("42");

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcsqlDialect.loadFunctions(session, metaData, allFunctions));
    verify(metaData).getNumericFunctions();
  }

  /**
   * Test {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)"})
  public void testLoadFunctions_givenNull() throws SQLException, DBException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.getNumericFunctions()).thenReturn(null);
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");
    HashSet<String> allFunctions = new HashSet<>();

    // Act
    jdbcsqlDialect.loadFunctions(session, metaData, allFunctions);

    // Assert
    verify(metaData).getNumericFunctions();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    assertEquals(3, allFunctions.size());
    assertTrue(allFunctions.contains("2020-03-01"));
    assertTrue(allFunctions.contains("String Functions"));
    assertTrue(allFunctions.contains("System Functions"));
  }

  /**
   * Test {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)}.
   *
   * <ul>
   *   <li>Given {@code Numeric Functions}.
   *   <li>Then {@link HashSet#HashSet()} size is four.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)"})
  public void testLoadFunctions_givenNumericFunctions_thenHashSetSizeIsFour()
      throws SQLException, DBException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.getNumericFunctions()).thenReturn("Numeric Functions");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");
    HashSet<String> allFunctions = new HashSet<>();

    // Act
    jdbcsqlDialect.loadFunctions(session, metaData, allFunctions);

    // Assert
    verify(metaData).getNumericFunctions();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    assertEquals(4, allFunctions.size());
    assertTrue(allFunctions.contains("Numeric Functions"));
    assertTrue(allFunctions.contains("String Functions"));
    assertTrue(allFunctions.contains("System Functions"));
  }

  /**
   * Test {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)"})
  public void testLoadFunctions_givenSQLException_thenThrowSQLException()
      throws SQLException, DBException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.getNumericFunctions()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcsqlDialect.loadFunctions(session, metaData, new HashSet<>()));
    verify(metaData).getNumericFunctions();
  }

  /**
   * Test {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)}.
   *
   * <ul>
   *   <li>When {@link JDBCDatabaseMetaData} {@link JDBCDatabaseMetaData#getNumericFunctions()}
   *       return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#loadFunctions(JDBCSession, JDBCDatabaseMetaData,
   * Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCSQLDialect.loadFunctions(JDBCSession, JDBCDatabaseMetaData, Set)"})
  public void testLoadFunctions_whenJDBCDatabaseMetaDataGetNumericFunctionsReturnEmptyString()
      throws SQLException, DBException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    JDBCSession session = mock(JDBCSession.class);

    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.getNumericFunctions()).thenReturn("");
    when(metaData.getStringFunctions()).thenReturn("String Functions");
    when(metaData.getSystemFunctions()).thenReturn("System Functions");
    when(metaData.getTimeDateFunctions()).thenReturn("2020-03-01");
    HashSet<String> allFunctions = new HashSet<>();

    // Act
    jdbcsqlDialect.loadFunctions(session, metaData, allFunctions);

    // Assert
    verify(metaData).getNumericFunctions();
    verify(metaData).getStringFunctions();
    verify(metaData).getSystemFunctions();
    verify(metaData).getTimeDateFunctions();
    assertEquals(3, allFunctions.size());
    assertTrue(allFunctions.contains("2020-03-01"));
    assertTrue(allFunctions.contains("String Functions"));
    assertTrue(allFunctions.contains("System Functions"));
  }

  /**
   * Test {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.convertExternalDataType(SQLDialect, DBSTypedObject, DBPDataTypeProvider)"
  })
  public void testConvertExternalDataType_givenNull() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    SimpleTypedObject sourceTypedObject = new SimpleTypedObject("varchar");

    DBPDataTypeProvider targetTypeProvider = mock(DBPDataTypeProvider.class);
    when(targetTypeProvider.getLocalDataType(Mockito.<String>any())).thenReturn(null);

    // Act
    String actualConvertExternalDataTypeResult =
        jdbcsqlDialect.convertExternalDataType(
            BasicSQLDialect.INSTANCE, sourceTypedObject, targetTypeProvider);

    // Assert
    verify(targetTypeProvider, atLeast(1)).getLocalDataType(Mockito.<String>any());
    assertNull(actualConvertExternalDataTypeResult);
  }

  /**
   * Test {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}.
   *
   * <ul>
   *   <li>Then return {@code varchar}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.convertExternalDataType(SQLDialect, DBSTypedObject, DBPDataTypeProvider)"
  })
  public void testConvertExternalDataType_thenReturnVarchar() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");
    SimpleTypedObject sourceTypedObject = new SimpleTypedObject("varchar");

    DBPDataTypeProvider targetTypeProvider = mock(DBPDataTypeProvider.class);
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(mock(JDBCDataSource.class));
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(jdbcRemoteInstance, 42, "varchar", "varchar", true, true, 7, 1, 3);
    when(targetTypeProvider.getLocalDataType(Mockito.<String>any())).thenReturn(jdbcDataType);

    // Act
    String actualConvertExternalDataTypeResult =
        jdbcsqlDialect.convertExternalDataType(
            BasicSQLDialect.INSTANCE, sourceTypedObject, targetTypeProvider);

    // Assert
    verify(targetTypeProvider).getLocalDataType("longtext");
    assertEquals("varchar", actualConvertExternalDataTypeResult);
  }

  /**
   * Test {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with typeName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.convertExternalDataType(SQLDialect, DBSTypedObject, DBPDataTypeProvider)"
  })
  public void testConvertExternalDataType_whenSimpleTypedObjectWithTypeNameIsEmptyString() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    // Act and Assert
    assertNull(
        jdbcsqlDialect.convertExternalDataType(
            BasicSQLDialect.INSTANCE, new SimpleTypedObject(""), mock(DBPDataTypeProvider.class)));
  }

  /**
   * Test {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with typeName is {@code not
   *       empty}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.convertExternalDataType(SQLDialect, DBSTypedObject, DBPDataTypeProvider)"
  })
  public void testConvertExternalDataType_whenSimpleTypedObjectWithTypeNameIsNotEmpty() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    // Act and Assert
    assertNull(
        jdbcsqlDialect.convertExternalDataType(
            BasicSQLDialect.INSTANCE, new SimpleTypedObject("not empty"), null));
  }

  /**
   * Test {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with typeName is {@code not
   *       empty}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.convertExternalDataType(SQLDialect, DBSTypedObject, DBPDataTypeProvider)"
  })
  public void testConvertExternalDataType_whenSimpleTypedObjectWithTypeNameIsNotEmpty2() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    // Act and Assert
    assertNull(
        jdbcsqlDialect.convertExternalDataType(
            BasicSQLDialect.INSTANCE,
            new SimpleTypedObject("not empty"),
            mock(DBPDataTypeProvider.class)));
  }

  /**
   * Test {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#SimpleTypedObject(String)} with typeName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCSQLDialect#convertExternalDataType(SQLDialect, DBSTypedObject,
   * DBPDataTypeProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCSQLDialect.convertExternalDataType(SQLDialect, DBSTypedObject, DBPDataTypeProvider)"
  })
  public void testConvertExternalDataType_whenSimpleTypedObjectWithTypeNameIsNull() {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = new JDBCSQLDialect("Name", "42");

    // Act and Assert
    assertNull(
        jdbcsqlDialect.convertExternalDataType(
            BasicSQLDialect.INSTANCE,
            new SimpleTypedObject(null),
            mock(DBPDataTypeProvider.class)));
  }
}
