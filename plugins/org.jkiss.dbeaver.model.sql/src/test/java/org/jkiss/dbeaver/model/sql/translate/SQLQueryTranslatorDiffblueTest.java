package org.jkiss.dbeaver.model.sql.translate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import net.sf.jsqlparser.statement.Statement;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryTranslatorDiffblueTest {
  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate2() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate3() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>(null, "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate4() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    Pair<String, String> pair = new Pair<>("First", null);
    when(sqlDialect.getMultiLineComments()).thenReturn(pair);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate5() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);
    SQLQuery element = new SQLQuery(mock(DBPDataSource.class), "");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate6() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery sourceQuery = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQuery element = new SQLQuery(dataSource, "Text", sourceQuery);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate7() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {null});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text", 2, 3);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenArrayOfStringWithFooAnd42() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"foo", "42"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text", 2, 3);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenArrayOfStringWithIdentifierQuoteStrings() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code [} and {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenArrayOfStringWithLeftSquareBracketAnd42() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"[", "42"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text", 2, 3);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code [} and {@code ]}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenArrayOfStringWithLeftSquareBracketAndRightSquareBracket()
      throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"[", "]"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text", 2, 3);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getIdentifierQuoteStrings()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenSQLDialectGetIdentifierQuoteStringsReturnNull()
      throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings()).thenReturn(null);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text", 2, 3);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getMultiLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenSQLDialectGetMultiLineCommentsReturnNull() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});
    when(sqlDialect.getMultiLineComments()).thenReturn(null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getSingleLineComments()} return array of
   *       {@link String} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenSQLDialectGetSingleLineCommentsReturnArrayOfStringWithNull()
      throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {null});
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text", 2, 3);

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getSingleLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_givenSQLDialectGetSingleLineCommentsReturnNull() throws DBException {
    // Arrange
    SQLQueryTranslator sqlQueryTranslator = new SQLQueryTranslator(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getSingleLineComments()).thenReturn(null);
    when(sqlDialect.getMultiLineComments()).thenReturn(new Pair<>("First", "Second"));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    SQLQuery element = new SQLQuery(dataSource, "Text");

    // Act
    List<? extends SQLScriptElement> actualTranslateResult = sqlQueryTranslator.translate(element);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
    assertEquals(1, actualTranslateResult.size());
    assertSame(element, actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translate(SQLScriptElement)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return first is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translate(SQLScriptElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translate(SQLScriptElement)"})
  public void testTranslate_whenNull_thenReturnFirstIsNull() throws DBException {
    // Arrange and Act
    List<? extends SQLScriptElement> actualTranslateResult =
        new SQLQueryTranslator(null).translate(null);

    // Assert
    assertEquals(1, actualTranslateResult.size());
    assertNull(actualTranslateResult.get(0));
  }

  /**
   * Test {@link SQLQueryTranslator#translateStatement(SQLQuery, Statement)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryTranslator#translateStatement(SQLQuery, Statement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SQLQueryTranslator.translateStatement(SQLQuery, Statement)"})
  public void testTranslateStatement_thenReturnSizeIsOne() {
    // Arrange
    SQLTranslateContext sqlTranslateContext = mock(SQLTranslateContext.class);
    when(sqlTranslateContext.getTargetDialect()).thenReturn(mock(SQLDialect.class));

    // Act
    List<? extends SQLScriptElement> actualTranslateStatementResult =
        new SQLQueryTranslator(sqlTranslateContext).translateStatement(null, mock(Statement.class));

    // Assert
    verify(sqlTranslateContext).getTargetDialect();
    assertEquals(1, actualTranslateStatementResult.size());
    assertNull(actualTranslateStatementResult.get(0));
  }
}
