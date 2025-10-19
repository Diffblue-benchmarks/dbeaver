package org.jkiss.dbeaver.model.impl.sql;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPIdentifierCase;
import org.jkiss.dbeaver.model.DBPKeywordType;
import org.jkiss.dbeaver.model.data.DBDBinaryFormatter;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.impl.data.formatters.BinaryFormatterHexNative;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLDialect.MultiValueInsertMode;
import org.jkiss.dbeaver.model.sql.SQLQueryGenerator;
import org.jkiss.dbeaver.model.sql.SQLStateType;
import org.jkiss.dbeaver.model.sql.parser.EmptyTokenPredicateSet;
import org.jkiss.dbeaver.model.sql.parser.SQLTokenPredicateSet;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedure;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedureParameter;
import org.jkiss.dbeaver.model.struct.rdb.DBSProcedureType;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractSQLDialectDiffblueTest {
  /**
   * Test {@link AbstractSQLDialect#getQueryGenerator()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQueryGenerator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryGenerator AbstractSQLDialect.getQueryGenerator()"})
  public void testGetQueryGenerator() {
    // Arrange and Act
    SQLQueryGenerator actualQueryGenerator = BasicSQLDialect.INSTANCE.getQueryGenerator();

    // Assert
    assertTrue(actualQueryGenerator instanceof StandardSQLDialectQueryGenerator);
    assertSame(
        ((StandardSQLDialectQueryGenerator) actualQueryGenerator).INSTANCE, actualQueryGenerator);
  }

  /**
   * Test {@link AbstractSQLDialect#getIdentifierQuoteStrings()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getIdentifierQuoteStrings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[][] AbstractSQLDialect.getIdentifierQuoteStrings()"})
  public void testGetIdentifierQuoteStrings() {
    // Arrange, Act and Assert
    assertSame(
        BasicSQLDialect.DEFAULT_IDENTIFIER_QUOTES,
        BasicSQLDialect.INSTANCE.getIdentifierQuoteStrings());
  }

  /**
   * Test {@link AbstractSQLDialect#getStringQuoteStrings()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getStringQuoteStrings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[][] AbstractSQLDialect.getStringQuoteStrings()"})
  public void testGetStringQuoteStrings() {
    // Arrange, Act and Assert
    assertSame(
        BasicSQLDialect.DEFAULT_STRING_QUOTES, BasicSQLDialect.INSTANCE.getStringQuoteStrings());
  }

  /**
   * Test {@link AbstractSQLDialect#getQueryKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQueryKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getQueryKeywords()"})
  public void testGetQueryKeywords() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[] {"SELECT"}, BasicSQLDialect.INSTANCE.getQueryKeywords());
  }

  /**
   * Test {@link AbstractSQLDialect#getExecuteKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getExecuteKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getExecuteKeywords()"})
  public void testGetExecuteKeywords() {
    // Arrange, Act and Assert
    assertEquals(0, BasicSQLDialect.INSTANCE.getExecuteKeywords().length);
  }

  /**
   * Test {@link AbstractSQLDialect#getDDLKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getDDLKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getDDLKeywords()"})
  public void testGetDDLKeywords() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"CREATE", "ALTER", "DROP"}, BasicSQLDialect.INSTANCE.getDDLKeywords());
  }

  /**
   * Test {@link AbstractSQLDialect#getInClauseParentheses()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getInClauseParentheses()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair AbstractSQLDialect.getInClauseParentheses()"})
  public void testGetInClauseParentheses() {
    // Arrange, Act and Assert
    assertSame(
        AbstractSQLDialect.IN_CLAUSE_PARENTHESES,
        BasicSQLDialect.INSTANCE.getInClauseParentheses());
  }

  /**
   * Test {@link AbstractSQLDialect#addFunctions(Collection)}.
   *
   * <ul>
   *   <li>Then {@link BasicSQLDialect#BasicSQLDialect()} ReservedWords size is four hundred
   *       thirteen.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFunctions(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSQLDialect.addFunctions(Collection)"})
  public void testAddFunctions_thenBasicSQLDialectReservedWordsSizeIsFourHundredThirteen() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();

    ArrayList<String> allFunctions = new ArrayList<>();
    allFunctions.add("42");

    // Act
    basicSQLDialect.addFunctions(allFunctions);

    // Assert
    assertEquals(413, basicSQLDialect.getReservedWords().size());
    assertEquals(Integer.SIZE, basicSQLDialect.getFunctions().size());
  }

  /**
   * Test {@link AbstractSQLDialect#turnFunctionIntoKeyword(String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#turnFunctionIntoKeyword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSQLDialect.turnFunctionIntoKeyword(String)"})
  public void testTurnFunctionIntoKeyword() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();

    // Act
    basicSQLDialect.turnFunctionIntoKeyword("\"");

    // Assert
    assertEquals(413, basicSQLDialect.getReservedWords().size());
  }

  /**
   * Test {@link AbstractSQLDialect#turnFunctionIntoKeyword(String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#turnFunctionIntoKeyword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSQLDialect.turnFunctionIntoKeyword(String)"})
  public void testTurnFunctionIntoKeyword2() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();

    // Act
    basicSQLDialect.turnFunctionIntoKeyword("DECIMAL");

    // Assert that nothing has changed
    assertEquals(412, basicSQLDialect.getReservedWords().size());
  }

  /**
   * Test {@link AbstractSQLDialect#addDataTypes(Collection)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addDataTypes(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSQLDialect.addDataTypes(Collection)"})
  public void testAddDataTypes_givenBasicSQLDialect() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();

    LinkedHashSet<String> allTypes = new LinkedHashSet<>();
    allTypes.add("All Types");

    // Act
    basicSQLDialect.addDataTypes(allTypes);

    // Assert
    assertEquals(413, basicSQLDialect.getReservedWords().size());
  }

  /**
   * Test {@link AbstractSQLDialect#addDataTypes(Collection)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()} addSQLKeyword {@code 42}.
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addDataTypes(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSQLDialect.addDataTypes(Collection)"})
  public void testAddDataTypes_givenBasicSQLDialectAddSQLKeyword42_whenLinkedHashSetAdd42() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();
    basicSQLDialect.addSQLKeyword("42");

    LinkedHashSet<String> allTypes = new LinkedHashSet<>();
    allTypes.add("42");

    // Act
    basicSQLDialect.addDataTypes(allTypes);

    // Assert that nothing has changed
    assertEquals(413, basicSQLDialect.getReservedWords().size());
  }

  /**
   * Test {@link AbstractSQLDialect#addKeywords(Collection, DBPKeywordType)}.
   *
   * <ul>
   *   <li>Then {@link BasicSQLDialect#BasicSQLDialect()} ReservedWords size is four hundred
   *       thirteen.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addKeywords(Collection, DBPKeywordType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSQLDialect.addKeywords(Collection, DBPKeywordType)"})
  public void testAddKeywords_thenBasicSQLDialectReservedWordsSizeIsFourHundredThirteen() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();

    ArrayList<String> set = new ArrayList<>();
    set.add("foo");

    // Act
    basicSQLDialect.addKeywords(set, DBPKeywordType.KEYWORD);

    // Assert
    assertEquals(413, basicSQLDialect.getReservedWords().size());
  }

  /**
   * Test {@link AbstractSQLDialect#getKeywordType(String)}.
   *
   * <ul>
   *   <li>When {@code Word}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getKeywordType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPKeywordType AbstractSQLDialect.getKeywordType(String)"})
  public void testGetKeywordType_whenWord_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(BasicSQLDialect.INSTANCE.getKeywordType("Word"));
  }

  /**
   * Test {@link AbstractSQLDialect#getMatchedKeywords(String)}.
   *
   * <ul>
   *   <li>When {@code Word}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getMatchedKeywords(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AbstractSQLDialect.getMatchedKeywords(String)"})
  public void testGetMatchedKeywords_whenWord_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.getMatchedKeywords("Word").isEmpty());
  }

  /**
   * Test {@link AbstractSQLDialect#isKeywordStart(String)}.
   *
   * <ul>
   *   <li>When {@code Word}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isKeywordStart(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isKeywordStart(String)"})
  public void testIsKeywordStart_whenWord_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isKeywordStart("Word"));
  }

  /**
   * Test {@link AbstractSQLDialect#isEntityQueryWord(String)}.
   *
   * <ul>
   *   <li>When {@code Word}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isEntityQueryWord(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isEntityQueryWord(String)"})
  public void testIsEntityQueryWord_whenWord_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isEntityQueryWord("Word"));
  }

  /**
   * Test {@link AbstractSQLDialect#isAttributeQueryWord(String)}.
   *
   * <ul>
   *   <li>When {@code Word}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isAttributeQueryWord(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isAttributeQueryWord(String)"})
  public void testIsAttributeQueryWord_whenWord_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isAttributeQueryWord("Word"));
  }

  /**
   * Test {@link AbstractSQLDialect#getKeywordNextLineIndent(String)}.
   *
   * <ul>
   *   <li>When {@code Word}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getKeywordNextLineIndent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractSQLDialect.getKeywordNextLineIndent(String)"})
  public void testGetKeywordNextLineIndent_whenWord_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, BasicSQLDialect.INSTANCE.getKeywordNextLineIndent("Word"));
  }

  /**
   * Test {@link AbstractSQLDialect#getSearchStringEscape()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getSearchStringEscape()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getSearchStringEscape()"})
  public void testGetSearchStringEscape() {
    // Arrange, Act and Assert
    assertEquals("", BasicSQLDialect.INSTANCE.getSearchStringEscape());
  }

  /**
   * Test {@link AbstractSQLDialect#getStringEscapeCharacter()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getStringEscapeCharacter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char AbstractSQLDialect.getStringEscapeCharacter()"})
  public void testGetStringEscapeCharacter() {
    // Arrange, Act and Assert
    assertEquals('\u0000', BasicSQLDialect.INSTANCE.getStringEscapeCharacter());
  }

  /**
   * Test {@link AbstractSQLDialect#getCatalogUsage()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getCatalogUsage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractSQLDialect.getCatalogUsage()"})
  public void testGetCatalogUsage() {
    // Arrange, Act and Assert
    assertEquals(0, BasicSQLDialect.INSTANCE.getCatalogUsage());
  }

  /**
   * Test {@link AbstractSQLDialect#getSchemaUsage()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getSchemaUsage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractSQLDialect.getSchemaUsage()"})
  public void testGetSchemaUsage() {
    // Arrange, Act and Assert
    assertEquals(0, BasicSQLDialect.INSTANCE.getSchemaUsage());
  }

  /**
   * Test {@link AbstractSQLDialect#getCatalogSeparator()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getCatalogSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getCatalogSeparator()"})
  public void testGetCatalogSeparator() {
    // Arrange, Act and Assert
    assertEquals(".", BasicSQLDialect.INSTANCE.getCatalogSeparator());
  }

  /**
   * Test {@link AbstractSQLDialect#getStructSeparator()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getStructSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"char AbstractSQLDialect.getStructSeparator()"})
  public void testGetStructSeparator() {
    // Arrange, Act and Assert
    assertEquals('.', BasicSQLDialect.INSTANCE.getStructSeparator());
  }

  /**
   * Test {@link AbstractSQLDialect#getParametersPrefixes()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getParametersPrefixes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getParametersPrefixes()"})
  public void testGetParametersPrefixes() {
    // Arrange, Act and Assert
    assertEquals(0, BasicSQLDialect.INSTANCE.getParametersPrefixes().length);
  }

  /**
   * Test {@link AbstractSQLDialect#isCatalogAtStart()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isCatalogAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isCatalogAtStart()"})
  public void testIsCatalogAtStart() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isCatalogAtStart());
  }

  /**
   * Test {@link AbstractSQLDialect#getSQLStateType()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getSQLStateType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLStateType AbstractSQLDialect.getSQLStateType()"})
  public void testGetSQLStateType() {
    // Arrange, Act and Assert
    assertEquals(SQLStateType.SQL99, BasicSQLDialect.INSTANCE.getSQLStateType());
  }

  /**
   * Test {@link AbstractSQLDialect#getScriptDelimiters()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getScriptDelimiters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getScriptDelimiters()"})
  public void testGetScriptDelimiters() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[] {";"}, BasicSQLDialect.INSTANCE.getScriptDelimiters());
  }

  /**
   * Test {@link AbstractSQLDialect#getScriptDelimiterRedefiner()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getScriptDelimiterRedefiner()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getScriptDelimiterRedefiner()"})
  public void testGetScriptDelimiterRedefiner() {
    // Arrange, Act and Assert
    assertNull(BasicSQLDialect.INSTANCE.getScriptDelimiterRedefiner());
  }

  /**
   * Test {@link AbstractSQLDialect#getBlockBoundStrings()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getBlockBoundStrings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[][] AbstractSQLDialect.getBlockBoundStrings()"})
  public void testGetBlockBoundStrings() {
    // Arrange and Act
    String[][] actualBlockBoundStrings = BasicSQLDialect.INSTANCE.getBlockBoundStrings();

    // Assert
    assertEquals(1, actualBlockBoundStrings.length);
    assertArrayEquals(new String[] {"BEGIN", "END"}, actualBlockBoundStrings[0]);
  }

  /**
   * Test {@link AbstractSQLDialect#getBlockHeaderStrings()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getBlockHeaderStrings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getBlockHeaderStrings()"})
  public void testGetBlockHeaderStrings() {
    // Arrange, Act and Assert
    assertNull(BasicSQLDialect.INSTANCE.getBlockHeaderStrings());
  }

  /**
   * Test {@link AbstractSQLDialect#getInnerBlockPrefixes()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getInnerBlockPrefixes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getInnerBlockPrefixes()"})
  public void testGetInnerBlockPrefixes() {
    // Arrange, Act and Assert
    assertNull(BasicSQLDialect.INSTANCE.getInnerBlockPrefixes());
  }

  /**
   * Test {@link AbstractSQLDialect#isWordStart(int)}.
   *
   * <ul>
   *   <li>When ninety-five.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isWordStart(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isWordStart(int)"})
  public void testIsWordStart_whenNinetyFive_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isWordStart(95));
  }

  /**
   * Test {@link AbstractSQLDialect#isWordStart(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isWordStart(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isWordStart(int)"})
  public void testIsWordStart_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isWordStart(1));
  }

  /**
   * Test {@link AbstractSQLDialect#isWordPart(int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isWordPart(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isWordPart(int)"})
  public void testIsWordPart_whenMinusOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isWordPart(-1));
  }

  /**
   * Test {@link AbstractSQLDialect#isWordPart(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isWordPart(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isWordPart(int)"})
  public void testIsWordPart_whenOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isWordPart(1));
  }

  /**
   * Test {@link AbstractSQLDialect#validIdentifierStart(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#validIdentifierStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.validIdentifierStart(char)"})
  public void testValidIdentifierStart_whenA_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.validIdentifierStart('A'));
  }

  /**
   * Test {@link AbstractSQLDialect#validIdentifierStart(char)}.
   *
   * <ul>
   *   <li>When end of text.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#validIdentifierStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.validIdentifierStart(char)"})
  public void testValidIdentifierStart_whenEndOfText_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.validIdentifierStart('\u0003'));
  }

  /**
   * Test {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When {@code 1}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_when1_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.validIdentifierPart('1', true));
  }

  /**
   * Test {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenA_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.validIdentifierPart('A', true));
  }

  /**
   * Test {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When start of heading.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenStartOfHeading_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.validIdentifierPart('\u0001', true));
  }

  /**
   * Test {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}.
   *
   * <ul>
   *   <li>When {@code _}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#validIdentifierPart(char, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.validIdentifierPart(char, boolean)"})
  public void testValidIdentifierPart_whenUnderscore_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.validIdentifierPart('_', true));
  }

  /**
   * Test {@link AbstractSQLDialect#useCaseInsensitiveNameLookup()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#useCaseInsensitiveNameLookup()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.useCaseInsensitiveNameLookup()"})
  public void testUseCaseInsensitiveNameLookup() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.useCaseInsensitiveNameLookup());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsUnquotedMixedCase()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsUnquotedMixedCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsUnquotedMixedCase()"})
  public void testSupportsUnquotedMixedCase() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsUnquotedMixedCase());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsQuotedMixedCase()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsQuotedMixedCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsQuotedMixedCase()"})
  public void testSupportsQuotedMixedCase() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsQuotedMixedCase());
  }

  /**
   * Test {@link AbstractSQLDialect#storesUnquotedCase()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#storesUnquotedCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPIdentifierCase AbstractSQLDialect.storesUnquotedCase()"})
  public void testStoresUnquotedCase() {
    // Arrange, Act and Assert
    assertEquals(DBPIdentifierCase.UPPER, BasicSQLDialect.INSTANCE.storesUnquotedCase());
  }

  /**
   * Test {@link AbstractSQLDialect#storesQuotedCase()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#storesQuotedCase()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPIdentifierCase AbstractSQLDialect.storesQuotedCase()"})
  public void testStoresQuotedCase() {
    // Arrange, Act and Assert
    assertEquals(DBPIdentifierCase.MIXED, BasicSQLDialect.INSTANCE.storesQuotedCase());
  }

  /**
   * Test {@link AbstractSQLDialect#getCastedAttributeName(DBSAttributeBase, String)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>Then return {@code "Attribute Name"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getCastedAttributeName(DBSAttributeBase,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getCastedAttributeName(DBSAttributeBase, String)"})
  public void testGetCastedAttributeName_givenBasicSQLDialect_thenReturnAttributeName() {
    // Arrange
    BasicSQLDialect basicSQLDialect = new BasicSQLDialect();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualCastedAttributeName =
        basicSQLDialect.getCastedAttributeName(attribute, "Attribute Name");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"Attribute Name\"", actualCastedAttributeName);
  }

  /**
   * Test {@link AbstractSQLDialect#getCastedAttributeName(DBSAttributeBase, String)}.
   *
   * <ul>
   *   <li>Then return {@code Attribute Name}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getCastedAttributeName(DBSAttributeBase,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getCastedAttributeName(DBSAttributeBase, String)"})
  public void testGetCastedAttributeName_thenReturnAttributeName() {
    // Arrange, Act and Assert
    assertEquals(
        "Attribute Name",
        BasicSQLDialect.INSTANCE.getCastedAttributeName(
            new AttributeMetaDataProxy(null), "Attribute Name"));
  }

  /**
   * Test {@link AbstractSQLDialect#getCastedAttributeName(DBSAttributeBase, String)}.
   *
   * <ul>
   *   <li>Then return {@code "Attribute Name"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getCastedAttributeName(DBSAttributeBase,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getCastedAttributeName(DBSAttributeBase, String)"})
  public void testGetCastedAttributeName_thenReturnAttributeName2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualCastedAttributeName =
        BasicSQLDialect.INSTANCE.getCastedAttributeName(attribute, "Attribute Name");

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    assertEquals("\"Attribute Name\"", actualCastedAttributeName);
  }

  /**
   * Test {@link AbstractSQLDialect#getTypeCastClause(DBSTypedObject, String, boolean)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getTypeCastClause(DBSTypedObject, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getTypeCastClause(DBSTypedObject, String, boolean)"
  })
  public void testGetTypeCastClause() {
    // Arrange, Act and Assert
    assertEquals(
        "Expression",
        BasicSQLDialect.INSTANCE.getTypeCastClause(
            SimpleTypedObject.DEFAULT_TYPE, "Expression", true));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedIdentifier(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedIdentifier(String)"})
  public void testIsQuotedIdentifier_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isQuotedIdentifier("42"));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedIdentifier(String)}.
   *
   * <ul>
   *   <li>When {@code "42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedIdentifier(String)"})
  public void testIsQuotedIdentifier_when42_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isQuotedIdentifier("\"42"));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedIdentifier(String)}.
   *
   * <ul>
   *   <li>When {@code "}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedIdentifier(String)"})
  public void testIsQuotedIdentifier_whenQuotationMark_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isQuotedIdentifier("\""));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code "42"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_givenBasicSQLDialect_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("\"42\"", new BasicSQLDialect().getQuotedIdentifier("42", false, false));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>When {@code DECIMAL}.
   *   <li>Then return {@code "DECIMAL"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_givenBasicSQLDialect_whenDecimal_thenReturnDecimal() {
    // Arrange, Act and Assert
    assertEquals("\"DECIMAL\"", new BasicSQLDialect().getQuotedIdentifier("DECIMAL", false, false));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code "}.
   *   <li>Then return {@code "}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_givenInstance_whenQuotationMark_thenReturnQuotationMark() {
    // Arrange, Act and Assert
    assertEquals("\"", BasicSQLDialect.INSTANCE.getQuotedIdentifier("\"", true, true));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code Str}.
   *   <li>Then return {@code "Str"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_givenInstance_whenStr_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals("\"Str\"", BasicSQLDialect.INSTANCE.getQuotedIdentifier("Str", true, true));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code STR}.
   *   <li>Then return {@code "STR"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_givenInstance_whenStr_thenReturnStr2() {
    // Arrange, Act and Assert
    assertEquals("\"STR\"", BasicSQLDialect.INSTANCE.getQuotedIdentifier("STR", true, true));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code "datasource.max-string-type-length"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_thenReturnDatasourceMaxStringTypeLength() {
    // Arrange, Act and Assert
    assertEquals(
        "\"datasource.max-string-type-length\"",
        new BasicSQLDialect()
            .getQuotedIdentifier("datasource.max-string-type-length", false, false));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedIdentifier(String, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedIdentifier(String, boolean, boolean)"})
  public void testGetQuotedIdentifier_whenEmptyString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", new BasicSQLDialect().getQuotedIdentifier("", false, false));
  }

  /**
   * Test {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>When {@code datasource.max-string-type-length}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.mustBeQuoted(String, boolean)"})
  public void testMustBeQuoted_givenBasicSQLDialect_whenDatasourceMaxStringTypeLength() {
    // Arrange, Act and Assert
    assertTrue(new BasicSQLDialect().mustBeQuoted("datasource.max-string-type-length", false));
  }

  /**
   * Test {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>When {@code DECIMAL}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.mustBeQuoted(String, boolean)"})
  public void testMustBeQuoted_givenBasicSQLDialect_whenDecimal_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new BasicSQLDialect().mustBeQuoted("DECIMAL", false));
  }

  /**
   * Test {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>When empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.mustBeQuoted(String, boolean)"})
  public void testMustBeQuoted_givenBasicSQLDialect_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new BasicSQLDialect().mustBeQuoted("", false));
  }

  /**
   * Test {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#BasicSQLDialect()}.
   *   <li>When {@code "}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.mustBeQuoted(String, boolean)"})
  public void testMustBeQuoted_givenBasicSQLDialect_whenQuotationMark_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new BasicSQLDialect().mustBeQuoted("\"", false));
  }

  /**
   * Test {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code STR}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.mustBeQuoted(String, boolean)"})
  public void testMustBeQuoted_givenInstance_whenStr_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.mustBeQuoted("STR", true));
  }

  /**
   * Test {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@code Str}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#mustBeQuoted(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.mustBeQuoted(String, boolean)"})
  public void testMustBeQuoted_givenInstance_whenStr_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.mustBeQuoted("Str", true));
  }

  /**
   * Test {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.quoteIdentifier(String, String[][])"})
  public void testQuoteIdentifier() {
    // Arrange, Act and Assert
    assertEquals(
        "\"\"\"\"",
        BasicSQLDialect.INSTANCE.quoteIdentifier("\"", new String[][] {new String[] {"\"", "\""}}));
  }

  /**
   * Test {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}.
   *
   * <ul>
   *   <li>Then return {@code Quote StringsStr"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.quoteIdentifier(String, String[][])"})
  public void testQuoteIdentifier_thenReturnQuoteStringsStr() {
    // Arrange, Act and Assert
    assertEquals(
        "Quote StringsStr\"",
        BasicSQLDialect.INSTANCE.quoteIdentifier(
            "Str", new String[][] {new String[] {"Quote Strings", "\""}}));
  }

  /**
   * Test {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}.
   *
   * <ul>
   *   <li>Then return {@code Quote StringsStrQuote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.quoteIdentifier(String, String[][])"})
  public void testQuoteIdentifier_thenReturnQuoteStringsStrQuoteStrings() {
    // Arrange, Act and Assert
    assertEquals(
        "Quote StringsStrQuote Strings",
        BasicSQLDialect.INSTANCE.quoteIdentifier(
            "Str", new String[][] {new String[] {"Quote Strings", "Quote Strings"}}));
  }

  /**
   * Test {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}.
   *
   * <ul>
   *   <li>Then return {@code "Str"}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#quoteIdentifier(String, String[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.quoteIdentifier(String, String[][])"})
  public void testQuoteIdentifier_thenReturnStr() {
    // Arrange, Act and Assert
    assertEquals(
        "\"Str\"",
        BasicSQLDialect.INSTANCE.quoteIdentifier(
            "Str", new String[][] {new String[] {"\"", "\""}}));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String, boolean)} with {@code identifier},
   * {@code unescapeQuotesInsideIdentifier}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String, boolean)"})
  public void testGetUnquotedIdentifierWithIdentifierUnescapeQuotesInsideIdentifier() {
    // Arrange, Act and Assert
    assertEquals("42", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("42", true));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String, boolean)} with {@code identifier},
   * {@code unescapeQuotesInsideIdentifier}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String, boolean)"})
  public void testGetUnquotedIdentifierWithIdentifierUnescapeQuotesInsideIdentifier2() {
    // Arrange, Act and Assert
    assertEquals("\"", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("\"", true));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String, boolean)} with {@code identifier},
   * {@code unescapeQuotesInsideIdentifier}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String, boolean)"})
  public void testGetUnquotedIdentifierWithIdentifierUnescapeQuotesInsideIdentifier_whenFalse() {
    // Arrange, Act and Assert
    assertEquals("42", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("42", false));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String)} with {@code identifier}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String)"})
  public void testGetUnquotedIdentifierWithIdentifier_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("\"\""));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String)} with {@code identifier}.
   *
   * <ul>
   *   <li>Then return {@code "}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String)"})
  public void testGetUnquotedIdentifierWithIdentifier_thenReturnQuotationMark() {
    // Arrange, Act and Assert
    assertEquals("\"", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("\""));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String)} with {@code identifier}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String)"})
  public void testGetUnquotedIdentifierWithIdentifier_when42_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("42"));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedIdentifier(String)} with {@code identifier}.
   *
   * <ul>
   *   <li>When {@code "42}.
   *   <li>Then return {@code "42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedIdentifier(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedIdentifier(String)"})
  public void testGetUnquotedIdentifierWithIdentifier_when42_thenReturn422() {
    // Arrange, Act and Assert
    assertEquals("\"42", BasicSQLDialect.INSTANCE.getUnquotedIdentifier("\"42"));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedString(String)}.
   *
   * <ul>
   *   <li>When {@code ''}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedString(String)"})
  public void testIsQuotedString_whenApostropheApostrophe_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isQuotedString("''"));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedString(String)}.
   *
   * <ul>
   *   <li>When {@code "}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedString(String)"})
  public void testIsQuotedString_whenQuotationMark_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isQuotedString("\""));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedString(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedString(String)"})
  public void testIsQuotedString_whenString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isQuotedString("String"));
  }

  /**
   * Test {@link AbstractSQLDialect#isQuotedString(String)}.
   *
   * <ul>
   *   <li>When {@code 'String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuotedString(String)"})
  public void testIsQuotedString_whenString_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isQuotedString("'String"));
  }

  /**
   * Test {@link AbstractSQLDialect#getQuotedString(String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getQuotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getQuotedString(String)"})
  public void testGetQuotedString() {
    // Arrange, Act and Assert
    assertEquals("'String'", BasicSQLDialect.INSTANCE.getQuotedString("String"));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedString(String)}.
   *
   * <ul>
   *   <li>When {@code ''}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedString(String)"})
  public void testGetUnquotedString_whenApostropheApostrophe_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", BasicSQLDialect.INSTANCE.getUnquotedString("''"));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedString(String)}.
   *
   * <ul>
   *   <li>When {@code "}.
   *   <li>Then return {@code "}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedString(String)"})
  public void testGetUnquotedString_whenQuotationMark_thenReturnQuotationMark() {
    // Arrange, Act and Assert
    assertEquals("\"", BasicSQLDialect.INSTANCE.getUnquotedString("\""));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedString(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedString(String)"})
  public void testGetUnquotedString_whenString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", BasicSQLDialect.INSTANCE.getUnquotedString("String"));
  }

  /**
   * Test {@link AbstractSQLDialect#getUnquotedString(String)}.
   *
   * <ul>
   *   <li>When {@code 'String}.
   *   <li>Then return {@code 'String}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getUnquotedString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getUnquotedString(String)"})
  public void testGetUnquotedString_whenString_thenReturnString2() {
    // Arrange, Act and Assert
    assertEquals("'String", BasicSQLDialect.INSTANCE.getUnquotedString("'String"));
  }

  /**
   * Test {@link AbstractSQLDialect#escapeString(String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#escapeString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.escapeString(String)"})
  public void testEscapeString() {
    // Arrange, Act and Assert
    assertEquals("String", BasicSQLDialect.INSTANCE.escapeString("String"));
  }

  /**
   * Test {@link AbstractSQLDialect#unEscapeString(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#unEscapeString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.unEscapeString(String)"})
  public void testUnEscapeString_whenNull_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", BasicSQLDialect.INSTANCE.unEscapeString(null));
  }

  /**
   * Test {@link AbstractSQLDialect#unEscapeString(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#unEscapeString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.unEscapeString(String)"})
  public void testUnEscapeString_whenString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", BasicSQLDialect.INSTANCE.unEscapeString("String"));
  }

  /**
   * Test {@link AbstractSQLDialect#escapeScriptValue(DBSTypedObject, Object, String)}.
   *
   * <ul>
   *   <li>When randomUUID.
   *   <li>Then return {@code '42'}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#escapeScriptValue(DBSTypedObject, Object,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.escapeScriptValue(DBSTypedObject, Object, String)"})
  public void testEscapeScriptValue_whenRandomUUID_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals(
        "'42'",
        BasicSQLDialect.INSTANCE.escapeScriptValue(
            SimpleTypedObject.DEFAULT_TYPE, UUID.randomUUID(), "42"));
  }

  /**
   * Test {@link AbstractSQLDialect#escapeScriptValue(DBSTypedObject, Object, String)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#escapeScriptValue(DBSTypedObject, Object,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.escapeScriptValue(DBSTypedObject, Object, String)"})
  public void testEscapeScriptValue_whenRename_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        BasicSQLDialect.INSTANCE.escapeScriptValue(
            SimpleTypedObject.DEFAULT_TYPE, DBPEvent.RENAME, "42"));
  }

  /**
   * Test {@link AbstractSQLDialect#getDefaultMultiValueInsertMode()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getDefaultMultiValueInsertMode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLDialect.MultiValueInsertMode AbstractSQLDialect.getDefaultMultiValueInsertMode()"
  })
  public void testGetDefaultMultiValueInsertMode() {
    // Arrange, Act and Assert
    assertEquals(
        MultiValueInsertMode.NOT_SUPPORTED,
        BasicSQLDialect.INSTANCE.getDefaultMultiValueInsertMode());
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(new StandardSQLDialectQueryGenerator());
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("Error parsing SQL query");
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "Query", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    assertEquals(
        "SELECT * FROM (\nQuery\n) z_q WHERE Error parsing SQL query ORDER BY Error parsing SQL query",
        actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        mock(StandardSQLDialectQueryGenerator.class);
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(standardSQLDialectQueryGenerator);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {""});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("Error parsing SQL query");
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "Query", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(standardSQLDialectQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            eq(true),
            isA(StringBuilder.class));
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    verify(standardSQLDialectQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals("SELECT * FROM (\nQuery\n) z_q WHERE  ORDER BY ", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()} Order is empty string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_givenEmptyString_whenDBDDataFilterOrderIsEmptyString()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setOrder("");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "Query", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    assertEquals("SELECT * FROM (\nQuery\n) z_q", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_givenSQLDialectGetMultiLineCommentsReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(
            monitor, dataSource, "Query", new DBDDataFilter());

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    assertEquals("SELECT * FROM (\nQuery\n) z_q", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code SELECT * FROM ( Query ) z_q ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_thenReturnSelectFromQueryZQOrderBy() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        mock(StandardSQLDialectQueryGenerator.class);
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(standardSQLDialectQueryGenerator);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "Query", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(standardSQLDialectQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            eq(true),
            isA(StringBuilder.class));
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    assertEquals("SELECT * FROM (\nQuery\n) z_q ORDER BY ", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code SELECT * FROM ( Query ) z_q ORDER BY Error parsing SQL query}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_thenReturnSelectFromQueryZQOrderByErrorParsingSqlQuery()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(new StandardSQLDialectQueryGenerator());
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "Query", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    assertEquals(
        "SELECT * FROM (\nQuery\n) z_q ORDER BY Error parsing SQL query",
        actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code SELECT * FROM ( Query ) z_q WHERE ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_thenReturnSelectFromQueryZQWhereOrderBy() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        mock(StandardSQLDialectQueryGenerator.class);
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(standardSQLDialectQueryGenerator);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("Error parsing SQL query");
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "Query", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(standardSQLDialectQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            eq(true),
            isA(StringBuilder.class));
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    verify(standardSQLDialectQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals("SELECT * FROM (\nQuery\n) z_q WHERE  ORDER BY ", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code SELECT * FROM ( SELECT * FROM ( ) z_q WHERE ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_thenReturnSelectFromSelectFromZQWhereOrderBy()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        mock(StandardSQLDialectQueryGenerator.class);
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(standardSQLDialectQueryGenerator);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("Error parsing SQL query");
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(
            monitor, dataSource, "SELECT * FROM (\n", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(standardSQLDialectQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            eq(true),
            isA(StringBuilder.class));
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    verify(standardSQLDialectQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals(
        "SELECT * FROM (\nSELECT * FROM (\n\n) z_q WHERE  ORDER BY ",
        actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code SELECT * FROM ( Query ) z_q}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_whenDBDDataFilter_thenReturnSelectFromQueryZQ()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(
            monitor, dataSource, "Query", new DBDDataFilter());

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    assertEquals("SELECT * FROM (\nQuery\n) z_q", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code SELECT * FROM ( ) z_q WHERE ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_whenEmptyString_thenReturnSelectFromZQWhereOrderBy()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        mock(StandardSQLDialectQueryGenerator.class);
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(standardSQLDialectQueryGenerator);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("Error parsing SQL query");
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(standardSQLDialectQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            eq(true),
            isA(StringBuilder.class));
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    verify(standardSQLDialectQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals("SELECT * FROM (\n\n) z_q WHERE  ORDER BY ", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@code )}.
   *   <li>Then return {@code SELECT * FROM ( ) ) z_q WHERE ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_whenRightParenthesis_thenReturnSelectFromZQWhereOrderBy()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        mock(StandardSQLDialectQueryGenerator.class);
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendConditionString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<StringBuilder>any(),
            anyBoolean(),
            anyBoolean());
    doNothing()
        .when(standardSQLDialectQueryGenerator)
        .appendOrderString(
            Mockito.<DBDDataFilter>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            anyBoolean(),
            Mockito.<StringBuilder>any());

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(standardSQLDialectQueryGenerator);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.supportsSubqueries()).thenReturn(false);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(AbstractSQLDialect.IN_CLAUSE_PARENTHESES);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("Error parsing SQL query");
    filter.setOrder("Error parsing SQL query");

    // Act
    String actualAddFiltersToQueryResult =
        BasicSQLDialect.INSTANCE.addFiltersToQuery(monitor, dataSource, "\n) ", filter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(standardSQLDialectQueryGenerator)
        .appendOrderString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            eq(true),
            isA(StringBuilder.class));
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect, atLeast(1)).getQueryGenerator();
    verify(sqlDialect).getSingleLineComments();
    verify(sqlDialect).supportsSubqueries();
    verify(standardSQLDialectQueryGenerator)
        .appendConditionString(
            isA(DBDDataFilter.class),
            isA(DBPDataSource.class),
            eq("z_q"),
            isA(StringBuilder.class),
            eq(true),
            eq(true));
    assertEquals("SELECT * FROM (\n\n) \n) z_q WHERE  ORDER BY ", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link AbstractSQLDialect#supportsSubqueries()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsSubqueries()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsSubqueries()"})
  public void testSupportsSubqueries() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsSubqueries());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsAliasInSelect()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsAliasInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsAliasInSelect()"})
  public void testSupportsAliasInSelect() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.supportsAliasInSelect());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsAsKeywordBeforeAliasInFromClause()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsAsKeywordBeforeAliasInFromClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsAsKeywordBeforeAliasInFromClause()"})
  public void testSupportsAsKeywordBeforeAliasInFromClause() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsAsKeywordBeforeAliasInFromClause());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsAliasInUpdate()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsAliasInUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsAliasInUpdate()"})
  public void testSupportsAliasInUpdate() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.supportsAliasInUpdate());
  }

  /**
   * Test {@link AbstractSQLDialect#getAllAttributesAlias()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getAllAttributesAlias()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getAllAttributesAlias()"})
  public void testGetAllAttributesAlias() {
    // Arrange, Act and Assert
    assertEquals("*", BasicSQLDialect.INSTANCE.getAllAttributesAlias());
  }

  /**
   * Test {@link AbstractSQLDialect#getDefaultGroupAttribute()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getDefaultGroupAttribute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getDefaultGroupAttribute()"})
  public void testGetDefaultGroupAttribute() {
    // Arrange, Act and Assert
    assertEquals("*", BasicSQLDialect.INSTANCE.getDefaultGroupAttribute());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsAliasInConditions()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsAliasInConditions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsAliasInConditions()"})
  public void testSupportsAliasInConditions() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsAliasInConditions());
  }

  /**
   * Test {@link AbstractSQLDialect#getOffsetLimitQueryPart(int, int)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getOffsetLimitQueryPart(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getOffsetLimitQueryPart(int, int)"})
  public void testGetOffsetLimitQueryPart() {
    // Arrange, Act and Assert
    assertEquals("LIMIT 1 OFFSET 2", BasicSQLDialect.INSTANCE.getOffsetLimitQueryPart(2, 1));
  }

  /**
   * Test {@link AbstractSQLDialect#getClobComparingPart(String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getClobComparingPart(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getClobComparingPart(String)"})
  public void testGetClobComparingPart() {
    // Arrange, Act and Assert
    assertEquals("Column Name=?", BasicSQLDialect.INSTANCE.getClobComparingPart("Column Name"));
  }

  /**
   * Test {@link AbstractSQLDialect#supportsAliasInHaving()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsAliasInHaving()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsAliasInHaving()"})
  public void testSupportsAliasInHaving() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsAliasInHaving());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsTableDropCascade()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsTableDropCascade()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsTableDropCascade()"})
  public void testSupportsTableDropCascade() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.supportsTableDropCascade());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsOrderByIndex()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsOrderByIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsOrderByIndex()"})
  public void testSupportsOrderByIndex() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsOrderByIndex());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsNestedComments()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsNestedComments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsNestedComments()"})
  public void testSupportsNestedComments() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.supportsNestedComments());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsCommentQuery()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsCommentQuery()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsCommentQuery()"})
  public void testSupportsCommentQuery() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.supportsCommentQuery());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsNullability()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsNullability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsNullability()"})
  public void testSupportsNullability() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsNullability());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsColumnAutoIncrement()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsColumnAutoIncrement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsColumnAutoIncrement()"})
  public void testSupportsColumnAutoIncrement() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsColumnAutoIncrement());
  }

  /**
   * Test {@link AbstractSQLDialect#getCaseInsensitiveExpressionFormatter(DBCLogicalOperator)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractSQLDialect#getCaseInsensitiveExpressionFormatter(DBCLogicalOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.SQLExpressionFormatter AbstractSQLDialect.getCaseInsensitiveExpressionFormatter(DBCLogicalOperator)"
  })
  public void testGetCaseInsensitiveExpressionFormatter_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        BasicSQLDialect.INSTANCE.getCaseInsensitiveExpressionFormatter(DBCLogicalOperator.EQUALS));
  }

  /**
   * Test {@link AbstractSQLDialect#getMultiLineComments()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getMultiLineComments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair AbstractSQLDialect.getMultiLineComments()"})
  public void testGetMultiLineComments() {
    // Arrange and Act
    Pair<String, String> actualMultiLineComments = BasicSQLDialect.INSTANCE.getMultiLineComments();

    // Assert
    assertEquals("*/", actualMultiLineComments.getSecond());
    assertEquals("/*", actualMultiLineComments.getFirst());
  }

  /**
   * Test {@link AbstractSQLDialect#getSingleLineComments()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getSingleLineComments()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getSingleLineComments()"})
  public void testGetSingleLineComments() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[] {"--"}, BasicSQLDialect.INSTANCE.getSingleLineComments());
  }

  /**
   * Test {@link AbstractSQLDialect#isDelimiterAfterQuery()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isDelimiterAfterQuery()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isDelimiterAfterQuery()"})
  public void testIsDelimiterAfterQuery() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isDelimiterAfterQuery());
  }

  /**
   * Test {@link AbstractSQLDialect#isDelimiterAfterBlock()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isDelimiterAfterBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isDelimiterAfterBlock()"})
  public void testIsDelimiterAfterBlock() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isDelimiterAfterBlock());
  }

  /**
   * Test {@link AbstractSQLDialect#needsDelimiterFor(String, String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#needsDelimiterFor(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.needsDelimiterFor(String, String)"})
  public void testNeedsDelimiterFor() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.needsDelimiterFor("First Keyword", "Last Keyword"));
  }

  /**
   * Test {@link AbstractSQLDialect#getNativeBinaryFormatter()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getNativeBinaryFormatter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDBinaryFormatter AbstractSQLDialect.getNativeBinaryFormatter()"})
  public void testGetNativeBinaryFormatter() {
    // Arrange and Act
    DBDBinaryFormatter actualNativeBinaryFormatter =
        BasicSQLDialect.INSTANCE.getNativeBinaryFormatter();

    // Assert
    assertSame(
        ((BinaryFormatterHexNative) actualNativeBinaryFormatter).INSTANCE,
        actualNativeBinaryFormatter);
  }

  /**
   * Test {@link AbstractSQLDialect#getTestSQL()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getTestSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getTestSQL()"})
  public void testGetTestSQL() {
    // Arrange, Act and Assert
    assertNull(BasicSQLDialect.INSTANCE.getTestSQL());
  }

  /**
   * Test {@link AbstractSQLDialect#getDualTableName()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getDualTableName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getDualTableName()"})
  public void testGetDualTableName() {
    // Arrange, Act and Assert
    assertNull(BasicSQLDialect.INSTANCE.getDualTableName());
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}.
   *
   * <ul>
   *   <li>When {@code DO}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingQuery(String)"})
  public void testIsTransactionModifyingQuery_whenDo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isTransactionModifyingQuery("DO"));
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingQuery(String)"})
  public void testIsTransactionModifyingQuery_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isTransactionModifyingQuery(""));
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}.
   *
   * <ul>
   *   <li>When {@code Query String}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingQuery(String)"})
  public void testIsTransactionModifyingQuery_whenQueryString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isTransactionModifyingQuery("Query String"));
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}.
   *
   * <ul>
   *   <li>When {@code /*}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingQuery(String)"})
  public void testIsTransactionModifyingQuery_whenSlashAsterisk_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isTransactionModifyingQuery("/*"));
  }

  /**
   * Test {@link AbstractSQLDialect#getTransactionCommitKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getTransactionCommitKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getTransactionCommitKeywords()"})
  public void testGetTransactionCommitKeywords() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"COMMIT"}, BasicSQLDialect.INSTANCE.getTransactionCommitKeywords());
  }

  /**
   * Test {@link AbstractSQLDialect#getTransactionRollbackKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getTransactionRollbackKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getTransactionRollbackKeywords()"})
  public void testGetTransactionRollbackKeywords() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new String[] {"ROLLBACK"}, BasicSQLDialect.INSTANCE.getTransactionRollbackKeywords());
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}.
   *
   * <ul>
   *   <li>When {@code DECIMAL}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingKeyword(String)"})
  public void testIsTransactionModifyingKeyword_whenDecimal_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isTransactionModifyingKeyword("DECIMAL"));
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}.
   *
   * <ul>
   *   <li>When {@code DO}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingKeyword(String)"})
  public void testIsTransactionModifyingKeyword_whenDo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isTransactionModifyingKeyword("DO"));
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}.
   *
   * <ul>
   *   <li>When {@code First Keyword}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingKeyword(String)"})
  public void testIsTransactionModifyingKeyword_whenFirstKeyword_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isTransactionModifyingKeyword("First Keyword"));
  }

  /**
   * Test {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}.
   *
   * <ul>
   *   <li>When {@code NUMERIC}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#isTransactionModifyingKeyword(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isTransactionModifyingKeyword(String)"})
  public void testIsTransactionModifyingKeyword_whenNumeric_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isTransactionModifyingKeyword("NUMERIC"));
  }

  /**
   * Test {@link AbstractSQLDialect#getDMLKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getDMLKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getDMLKeywords()"})
  public void testGetDMLKeywords() {
    // Arrange, Act and Assert
    assertSame(BasicSQLDialect.DML_KEYWORDS, BasicSQLDialect.INSTANCE.getDMLKeywords());
  }

  /**
   * Test {@link AbstractSQLDialect#getNonTransactionKeywords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getNonTransactionKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AbstractSQLDialect.getNonTransactionKeywords()"})
  public void testGetNonTransactionKeywords() {
    // Arrange, Act and Assert
    assertSame(
        BasicSQLDialect.NON_TRANSACTIONAL_KEYWORDS,
        BasicSQLDialect.INSTANCE.getNonTransactionKeywords());
  }

  /**
   * Test {@link AbstractSQLDialect#isQuoteReservedWords()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isQuoteReservedWords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isQuoteReservedWords()"})
  public void testIsQuoteReservedWords() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isQuoteReservedWords());
  }

  /**
   * Test {@link AbstractSQLDialect#isCRLFBroken()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isCRLFBroken()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isCRLFBroken()"})
  public void testIsCRLFBroken() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isCRLFBroken());
  }

  /**
   * Test {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource,
   * DBSTypedObject, String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_given42_thenCallsGetId() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute parent =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute column = new DBVEntityAttribute(entity, parent, "Name");
    column.setScale(null);
    column.setPrecision(null);

    // Act
    String actualColumnTypeModifiers =
        BasicSQLDialect.INSTANCE.getColumnTypeModifiers(
            dataSource, column, null, DBPDataKind.STRING);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>When {@link DBPDataKind#BINARY}.
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource,
   * DBSTypedObject, String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_givenDBPDataSource_whenBinary_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute column = new DBVEntityAttribute(entity, parent2, "Name");
    column.setScale(null);
    column.setPrecision(null);

    // Act
    String actualColumnTypeModifiers =
        BasicSQLDialect.INSTANCE.getColumnTypeModifiers(
            dataSource, column, null, DBPDataKind.BINARY);

    // Assert
    verify(parent).getDataSource();
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>When {@link DBPDataKind#CONTENT}.
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource,
   * DBSTypedObject, String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_givenDBPDataSource_whenContent_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute column = new DBVEntityAttribute(entity, parent2, "Name");
    column.setScale(null);
    column.setPrecision(null);

    // Act
    String actualColumnTypeModifiers =
        BasicSQLDialect.INSTANCE.getColumnTypeModifiers(
            dataSource, column, null, DBPDataKind.CONTENT);

    // Assert
    verify(parent).getDataSource();
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>When {@link DBPDataKind#NUMERIC}.
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource,
   * DBSTypedObject, String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_givenDBPDataSource_whenNumeric_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute column = new DBVEntityAttribute(entity, parent2, "Name");
    column.setScale(null);
    column.setPrecision(null);

    // Act
    String actualColumnTypeModifiers =
        BasicSQLDialect.INSTANCE.getColumnTypeModifiers(
            dataSource, column, null, DBPDataKind.NUMERIC);

    // Assert
    verify(parent).getDataSource();
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>When {@link DBPDataKind#STRING}.
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource,
   * DBSTypedObject, String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_givenDBPDataSource_whenString_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute parent2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");

    DBVEntityAttribute column = new DBVEntityAttribute(entity, parent2, "Name");
    column.setScale(null);
    column.setPrecision(null);

    // Act
    String actualColumnTypeModifiers =
        BasicSQLDialect.INSTANCE.getColumnTypeModifiers(
            dataSource, column, null, DBPDataKind.STRING);

    // Assert
    verify(parent).getDataSource();
    assertNull(actualColumnTypeModifiers);
  }

  /**
   * Test {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String,
   * DBPDataKind)}.
   *
   * <ul>
   *   <li>When {@link SimpleTypedObject#DEFAULT_TYPE}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getColumnTypeModifiers(DBPDataSource,
   * DBSTypedObject, String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AbstractSQLDialect.getColumnTypeModifiers(DBPDataSource, DBSTypedObject, String, DBPDataKind)"
  })
  public void testGetColumnTypeModifiers_whenDefault_type_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        BasicSQLDialect.INSTANCE.getColumnTypeModifiers(
            mock(DBPDataSource.class),
            SimpleTypedObject.DEFAULT_TYPE,
            "Type Name",
            DBPDataKind.BOOLEAN));
  }

  /**
   * Test {@link AbstractSQLDialect#formatStoredProcedureCall(DBPDataSource, String)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#formatStoredProcedureCall(DBPDataSource,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.formatStoredProcedureCall(DBPDataSource, String)"})
  public void testFormatStoredProcedureCall() {
    // Arrange, Act and Assert
    assertEquals(
        "Sql Text",
        BasicSQLDialect.INSTANCE.formatStoredProcedureCall(mock(DBPDataSource.class), "Sql Text"));
  }

  /**
   * Test {@link AbstractSQLDialect#getMaxParameterLength(Collection, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractSQLDialect#getMaxParameterLength(Collection, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractSQLDialect.getMaxParameterLength(Collection, List)"})
  public void testGetMaxParameterLength_whenArrayList_thenReturnZero() {
    // Arrange
    ArrayList<DBSProcedureParameter> parameters = new ArrayList<>();

    // Act and Assert
    assertEquals(0, BasicSQLDialect.INSTANCE.getMaxParameterLength(parameters, new ArrayList<>()));
  }

  /**
   * Test {@link AbstractSQLDialect#useBracketsForExec(DBSProcedure)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#useBracketsForExec(DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.useBracketsForExec(DBSProcedure)"})
  public void testUseBracketsForExec() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.useBracketsForExec(mock(DBSProcedure.class)));
  }

  /**
   * Test {@link AbstractSQLDialect#getStoredProcedureCallInitialClause(DBSProcedure)}.
   *
   * <ul>
   *   <li>Given {@link DBSProcedureType#FUNCTION}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractSQLDialect#getStoredProcedureCallInitialClause(DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getStoredProcedureCallInitialClause(DBSProcedure)"})
  public void testGetStoredProcedureCallInitialClause_givenFunction() {
    // Arrange
    DBSProcedure proc = mock(DBSProcedure.class);
    when(proc.getProcedureType()).thenReturn(DBSProcedureType.FUNCTION);
    when(proc.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any())).thenReturn("Dr Jane Doe");

    // Act
    String actualStoredProcedureCallInitialClause =
        BasicSQLDialect.INSTANCE.getStoredProcedureCallInitialClause(proc);

    // Assert
    verify(proc).getFullyQualifiedName(DBPEvaluationContext.DML);
    verify(proc).getProcedureType();
    assertEquals("SELECT Dr Jane Doe", actualStoredProcedureCallInitialClause);
  }

  /**
   * Test {@link AbstractSQLDialect#getStoredProcedureCallInitialClause(DBSProcedure)}.
   *
   * <ul>
   *   <li>Given {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractSQLDialect#getStoredProcedureCallInitialClause(DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getStoredProcedureCallInitialClause(DBSProcedure)"})
  public void testGetStoredProcedureCallInitialClause_givenUnknown() {
    // Arrange
    DBSProcedure proc = mock(DBSProcedure.class);
    when(proc.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any())).thenReturn("Dr Jane Doe");
    when(proc.getProcedureType()).thenReturn(DBSProcedureType.UNKNOWN);

    // Act
    String actualStoredProcedureCallInitialClause =
        BasicSQLDialect.INSTANCE.getStoredProcedureCallInitialClause(proc);

    // Assert
    verify(proc).getFullyQualifiedName(DBPEvaluationContext.DML);
    verify(proc).getProcedureType();
    assertEquals("SELECT Dr Jane Doe", actualStoredProcedureCallInitialClause);
  }

  /**
   * Test {@link AbstractSQLDialect#getProcedureCallEndClause(DBSProcedure)}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getProcedureCallEndClause(DBSProcedure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractSQLDialect.getProcedureCallEndClause(DBSProcedure)"})
  public void testGetProcedureCallEndClause() {
    // Arrange, Act and Assert
    assertEquals("", BasicSQLDialect.INSTANCE.getProcedureCallEndClause(mock(DBSProcedure.class)));
  }

  /**
   * Test {@link AbstractSQLDialect#isStoredProcedureCallIncludesOutParameters()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isStoredProcedureCallIncludesOutParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isStoredProcedureCallIncludesOutParameters()"})
  public void testIsStoredProcedureCallIncludesOutParameters() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.isStoredProcedureCallIncludesOutParameters());
  }

  /**
   * Test {@link AbstractSQLDialect#isDisableScriptEscapeProcessing()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isDisableScriptEscapeProcessing()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isDisableScriptEscapeProcessing()"})
  public void testIsDisableScriptEscapeProcessing() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isDisableScriptEscapeProcessing());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsAlterTableStatement()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsAlterTableStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsAlterTableStatement()"})
  public void testSupportsAlterTableStatement() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsAlterTableStatement());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsIndexCreateAndDrop()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsIndexCreateAndDrop()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsIndexCreateAndDrop()"})
  public void testSupportsIndexCreateAndDrop() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsIndexCreateAndDrop());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsInsertAllDefaultValuesStatement()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsInsertAllDefaultValuesStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsInsertAllDefaultValuesStatement()"})
  public void testSupportsInsertAllDefaultValuesStatement() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.supportsInsertAllDefaultValuesStatement());
  }

  /**
   * Test {@link AbstractSQLDialect#supportsUuid()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#supportsUuid()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.supportsUuid()"})
  public void testSupportsUuid() {
    // Arrange, Act and Assert
    assertTrue(BasicSQLDialect.INSTANCE.supportsUuid());
  }

  /**
   * Test {@link AbstractSQLDialect#getSkipTokenPredicates()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#getSkipTokenPredicates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLTokenPredicateSet AbstractSQLDialect.getSkipTokenPredicates()"})
  public void testGetSkipTokenPredicates() {
    // Arrange and Act
    SQLTokenPredicateSet actualSkipTokenPredicates =
        BasicSQLDialect.INSTANCE.getSkipTokenPredicates();

    // Assert
    assertSame(
        ((EmptyTokenPredicateSet) actualSkipTokenPredicates).INSTANCE, actualSkipTokenPredicates);
  }

  /**
   * Test {@link AbstractSQLDialect#isStripCommentsBeforeBlocks()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isStripCommentsBeforeBlocks()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isStripCommentsBeforeBlocks()"})
  public void testIsStripCommentsBeforeBlocks() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isStripCommentsBeforeBlocks());
  }

  /**
   * Test {@link AbstractSQLDialect#isEscapeBackslash()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#isEscapeBackslash()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.isEscapeBackslash()"})
  public void testIsEscapeBackslash() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.isEscapeBackslash());
  }

  /**
   * Test {@link AbstractSQLDialect#hasCaseSensitiveFiltration()}.
   *
   * <p>Method under test: {@link AbstractSQLDialect#hasCaseSensitiveFiltration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractSQLDialect.hasCaseSensitiveFiltration()"})
  public void testHasCaseSensitiveFiltration() {
    // Arrange, Act and Assert
    assertFalse(BasicSQLDialect.INSTANCE.hasCaseSensitiveFiltration());
  }
}
