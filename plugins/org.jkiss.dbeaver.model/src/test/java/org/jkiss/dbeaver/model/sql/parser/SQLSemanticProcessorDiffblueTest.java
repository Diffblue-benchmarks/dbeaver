package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDAttributeConstraint;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLQueryGenerator;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLSemanticProcessorDiffblueTest {
  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            SQLSemanticProcessor.parseExpression(
                "net.sf.jsqlparser.schema.Tablenet.sf.jsqlparser.schema.Table"));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("Expression", true);

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("Expression", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("Expression", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("Expression", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("Expression", ((Column) actualParseExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse2() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("net.sf.jsqlparser.statement.select.Join", true);

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("jsqlparser", ((Column) actualParseExpressionResult).getSchemaName());
    assertEquals("jsqlparser", ((Column) actualParseExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "net.sf.jsqlparser.statement.select",
        ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("select", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("select", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("select", ((Column) actualParseExpressionResult).getUnquotedName());
    assertEquals("sf", ((Column) actualParseExpressionResult).getCatalogName());
    assertEquals("sf", ((Column) actualParseExpressionResult).getUnquotedCatalogName());
    assertEquals("statement", ((Column) actualParseExpressionResult).getTableName());
    assertEquals("statement", ((Column) actualParseExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>Then return ColumnName is {@code Error}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_thenReturnColumnNameIsError()
      throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("Error parsing conditional SQL expression", true);

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("Error", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("Error", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("Error", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("Error", ((Column) actualParseExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>Then return ColumnName is {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_thenReturnColumnNameIsString()
      throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("java.lang.String[]", true);

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("String", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("String", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("String", ((Column) actualParseExpressionResult).getUnquotedName());
    assertEquals("java", ((Column) actualParseExpressionResult).getSchemaName());
    assertEquals("java", ((Column) actualParseExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "java.lang.String[]", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("lang", ((Column) actualParseExpressionResult).getTableName());
    assertEquals("lang", ((Column) actualParseExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_thenThrowDBCException()
      throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            SQLSemanticProcessor.parseExpression(
                "Error parsing conditional SQL expression", false));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_when42_thenReturnLongValue()
      throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult = SQLSemanticProcessor.parseExpression("42", true);

    // Assert
    assertTrue(actualParseExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualParseExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualParseExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualParseExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_whenEmptyString()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseExpression("", false));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When {@code [}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_whenLeftSquareBracket()
      throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> SQLSemanticProcessor.parseExpression("[", true));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String, boolean)"})
  public void testParseExpressionWithExpressionAllowPartialParse_whenNull_thenReturnNull()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseExpression(null, false));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return ColumnName is {@code Error}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_thenReturnColumnNameIsError() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("Error parsing conditional SQL expression");

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("Error", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("Error", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("Error", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("Error", ((Column) actualParseExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return ColumnName is {@code Expression}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_thenReturnColumnNameIsExpression()
      throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult = SQLSemanticProcessor.parseExpression("Expression");

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("Expression", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("Expression", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("Expression", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("Expression", ((Column) actualParseExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return SchemaName is {@code jsqlparser}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_thenReturnSchemaNameIsJsqlparser()
      throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("net.sf.jsqlparser.statement.select.Join");

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("jsqlparser", ((Column) actualParseExpressionResult).getSchemaName());
    assertEquals("jsqlparser", ((Column) actualParseExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "net.sf.jsqlparser.statement.select",
        ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("select", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("select", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("select", ((Column) actualParseExpressionResult).getUnquotedName());
    assertEquals("sf", ((Column) actualParseExpressionResult).getCatalogName());
    assertEquals("sf", ((Column) actualParseExpressionResult).getUnquotedCatalogName());
    assertEquals("statement", ((Column) actualParseExpressionResult).getTableName());
    assertEquals("statement", ((Column) actualParseExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_when42_thenReturnLongValue() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult = SQLSemanticProcessor.parseExpression("42");

    // Assert
    assertTrue(actualParseExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualParseExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualParseExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualParseExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code 42[}.
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_when42_thenReturnLongValue2() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult = SQLSemanticProcessor.parseExpression("42[");

    // Assert
    assertTrue(actualParseExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualParseExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualParseExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualParseExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_whenEmptyString_thenReturnNull()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseExpression(""));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code String[]}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_whenJavaLangString() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("java.lang.String[]");

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("String", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("String", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("String", ((Column) actualParseExpressionResult).getUnquotedName());
    assertEquals("java", ((Column) actualParseExpressionResult).getSchemaName());
    assertEquals("java", ((Column) actualParseExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "java.lang.String[]", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("lang", ((Column) actualParseExpressionResult).getTableName());
    assertEquals("lang", ((Column) actualParseExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code String[]Expression}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_whenJavaLangStringExpression() throws DBCException {
    // Arrange and Act
    Expression actualParseExpressionResult =
        SQLSemanticProcessor.parseExpression("java.lang.String[]Expression");

    // Assert
    assertTrue(actualParseExpressionResult instanceof Column);
    assertEquals("String", ((Column) actualParseExpressionResult).getColumnName());
    assertEquals("String", ((Column) actualParseExpressionResult).getUnquotedColumnName());
    assertEquals("String", ((Column) actualParseExpressionResult).getUnquotedName());
    assertEquals("java", ((Column) actualParseExpressionResult).getSchemaName());
    assertEquals("java", ((Column) actualParseExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "java.lang.String[]", ((Column) actualParseExpressionResult).getFullyQualifiedName());
    assertEquals("lang", ((Column) actualParseExpressionResult).getTableName());
    assertEquals("lang", ((Column) actualParseExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code [}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_whenLeftSquareBracket_thenThrowDBCException()
      throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> SQLSemanticProcessor.parseExpression("["));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseExpression(String)"})
  public void testParseExpressionWithExpression_whenNull_thenReturnNull() throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseExpression(null));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            SQLSemanticProcessor.parseCondExpression(
                "net.sf.jsqlparser.schema.Tablenet.sf.jsqlparser.schema.Table"));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse() throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("Expression", true);

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse2() throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("java.lang.String[]", true);

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("String", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("String", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("String", ((Column) actualParseCondExpressionResult).getUnquotedName());
    assertEquals("java", ((Column) actualParseCondExpressionResult).getSchemaName());
    assertEquals("java", ((Column) actualParseCondExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "java.lang.String[]", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("lang", ((Column) actualParseCondExpressionResult).getTableName());
    assertEquals("lang", ((Column) actualParseCondExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse3() throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("Error parsing SQL expression", true);

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse4() throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("net.sf.jsqlparser.statement.select.Join", true);

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("jsqlparser", ((Column) actualParseCondExpressionResult).getSchemaName());
    assertEquals("jsqlparser", ((Column) actualParseCondExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "net.sf.jsqlparser.statement.select",
        ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("select", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("select", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("select", ((Column) actualParseCondExpressionResult).getUnquotedName());
    assertEquals("sf", ((Column) actualParseCondExpressionResult).getCatalogName());
    assertEquals("sf", ((Column) actualParseCondExpressionResult).getUnquotedCatalogName());
    assertEquals("statement", ((Column) actualParseCondExpressionResult).getTableName());
    assertEquals("statement", ((Column) actualParseCondExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse_thenReturnLongValue()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("42", true);

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualParseCondExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualParseCondExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualParseCondExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse_thenThrowDBCException()
      throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () -> SQLSemanticProcessor.parseCondExpression("Error parsing SQL expression", false));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse_whenEmptyString()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseCondExpression("", false));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When {@code [}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse_whenLeftSquareBracket()
      throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> SQLSemanticProcessor.parseCondExpression("[", true));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String, boolean)} with {@code expression},
   * {@code allowPartialParse}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String, boolean)"})
  public void testParseCondExpressionWithExpressionAllowPartialParse_whenNull_thenReturnNull()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseCondExpression(null, false));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return ColumnName is {@code Error}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_thenReturnColumnNameIsError()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("Error parsing SQL expression");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("Error", ((Column) actualParseCondExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return ColumnName is {@code Expression}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_thenReturnColumnNameIsExpression()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("Expression");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("Expression", ((Column) actualParseCondExpressionResult).getUnquotedName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return SchemaName is {@code jsqlparser}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_thenReturnSchemaNameIsJsqlparser()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("net.sf.jsqlparser.statement.select.Join");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("jsqlparser", ((Column) actualParseCondExpressionResult).getSchemaName());
    assertEquals("jsqlparser", ((Column) actualParseCondExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "net.sf.jsqlparser.statement.select",
        ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("select", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("select", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("select", ((Column) actualParseCondExpressionResult).getUnquotedName());
    assertEquals("sf", ((Column) actualParseCondExpressionResult).getCatalogName());
    assertEquals("sf", ((Column) actualParseCondExpressionResult).getUnquotedCatalogName());
    assertEquals("statement", ((Column) actualParseCondExpressionResult).getTableName());
    assertEquals("statement", ((Column) actualParseCondExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_when42_thenReturnLongValue()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult = SQLSemanticProcessor.parseCondExpression("42");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualParseCondExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualParseCondExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualParseCondExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code 42[}.
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_when42_thenReturnLongValue2()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult = SQLSemanticProcessor.parseCondExpression("42[");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualParseCondExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualParseCondExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualParseCondExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_whenEmptyString_thenReturnNull()
      throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseCondExpression(""));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code String[]}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_whenJavaLangString() throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("java.lang.String[]");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("String", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("String", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("String", ((Column) actualParseCondExpressionResult).getUnquotedName());
    assertEquals("java", ((Column) actualParseCondExpressionResult).getSchemaName());
    assertEquals("java", ((Column) actualParseCondExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "java.lang.String[]", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("lang", ((Column) actualParseCondExpressionResult).getTableName());
    assertEquals("lang", ((Column) actualParseCondExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code String[]Expression}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_whenJavaLangStringExpression()
      throws DBCException {
    // Arrange and Act
    Expression actualParseCondExpressionResult =
        SQLSemanticProcessor.parseCondExpression("java.lang.String[]Expression");

    // Assert
    assertTrue(actualParseCondExpressionResult instanceof Column);
    assertEquals("String", ((Column) actualParseCondExpressionResult).getColumnName());
    assertEquals("String", ((Column) actualParseCondExpressionResult).getUnquotedColumnName());
    assertEquals("String", ((Column) actualParseCondExpressionResult).getUnquotedName());
    assertEquals("java", ((Column) actualParseCondExpressionResult).getSchemaName());
    assertEquals("java", ((Column) actualParseCondExpressionResult).getUnquotedSchemaName());
    assertEquals(
        "java.lang.String[]", ((Column) actualParseCondExpressionResult).getFullyQualifiedName());
    assertEquals("lang", ((Column) actualParseCondExpressionResult).getTableName());
    assertEquals("lang", ((Column) actualParseCondExpressionResult).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code [}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_whenLeftSquareBracket() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> SQLSemanticProcessor.parseCondExpression("["));
  }

  /**
   * Test {@link SQLSemanticProcessor#parseCondExpression(String)} with {@code expression}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#parseCondExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression SQLSemanticProcessor.parseCondExpression(String)"})
  public void testParseCondExpressionWithExpression_whenNull_thenReturnNull() throws DBCException {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.parseCondExpression(null));
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given 2D array of {@link String} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_given2dArrayOfStringWithNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings()).thenReturn(new String[][] {null});
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code foo} and empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenArrayOfStringWithFooAndEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings()).thenReturn(null);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"foo", ""});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Identifier Quote Strings}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenArrayOfStringWithIdentifierQuoteStrings() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenArrayOfStringWithNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {null});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Single Line Comments}.
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenArrayOfStringWithSingleLineComments_whenNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, null);

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES} First is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenIn_clause_parenthesesFirstIsEmptyString() {
    // Arrange
    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setFirst("");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(pair);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES} Second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenIn_clause_parenthesesSecondIsNull() {
    // Arrange
    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setSecond(null);

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(pair);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link SQLDialect} {@link SQLDialect#getSingleLineComments()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_givenNull_whenSQLDialectGetSingleLineCommentsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(null);

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_whenInstance() {
    // Arrange, Act and Assert
    assertFalse(SQLSemanticProcessor.isSelectQuery(BasicSQLDialect.INSTANCE, "Query"));
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_whenNull() {
    // Arrange, Act and Assert
    assertFalse(SQLSemanticProcessor.isSelectQuery(null, "Query"));
  }

  /**
   * Test {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}.
   *
   * <ul>
   *   <li>When {@link SQLDialect} {@link SQLDialect#getIdentifierQuoteStrings()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isSelectQuery(SQLDialect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isSelectQuery(SQLDialect, String)"})
  public void testIsSelectQuery_whenSQLDialectGetIdentifierQuoteStringsReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getIdentifierQuoteStrings()).thenReturn(null);
    when(dialect.getMultiLineComments()).thenReturn(null);
    when(dialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    // Act
    boolean actualIsSelectQueryResult = SQLSemanticProcessor.isSelectQuery(dialect, "Query");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getMultiLineComments();
    verify(dialect).getSingleLineComments();
    assertFalse(actualIsSelectQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code Query With Applied Filters}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_thenReturnQueryWithAppliedFilters() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    when(sqlQueryGenerator.getQueryWithAppliedFilters(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<DBDDataFilter>any()))
        .thenReturn("Query With Applied Filters");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualAddFiltersToQueryResult =
        SQLSemanticProcessor.addFiltersToQuery(
            monitor, dataSource, "Sql Query", new DBDDataFilter());

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .getQueryWithAppliedFilters(
            isA(DBRProgressMonitor.class),
            isA(DBPDataSource.class),
            eq("Sql Query"),
            isA(DBDDataFilter.class));
    assertEquals("Query With Applied Filters", actualAddFiltersToQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.addFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testAddFiltersToQuery_thenThrowDBCException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLQueryGenerator sqlQueryGenerator = mock(SQLQueryGenerator.class);
    when(sqlQueryGenerator.getQueryWithAppliedFilters(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBPDataSource>any(),
            Mockito.<String>any(),
            Mockito.<DBDDataFilter>any()))
        .thenThrow(new DBCException("An error occurred"));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQueryGenerator()).thenReturn(sqlQueryGenerator);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            SQLSemanticProcessor.addFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQueryGenerator();
    verify(sqlQueryGenerator)
        .getQueryWithAppliedFilters(
            isA(DBRProgressMonitor.class),
            isA(DBPDataSource.class),
            eq("Sql Query"),
            isA(DBDDataFilter.class));
  }

  /**
   * Test {@link SQLSemanticProcessor#isForceFilterSubQuery(DBPDataSource)}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#isForceFilterSubQuery(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.isForceFilterSubQuery(DBPDataSource)"})
  public void testIsForceFilterSubQuery() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.supportsSubqueries()).thenReturn(false);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    boolean actualIsForceFilterSubQueryResult =
        SQLSemanticProcessor.isForceFilterSubQuery(dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).supportsSubqueries();
    assertFalse(actualIsForceFilterSubQueryResult);
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getMultiLineComments()).thenReturn(null);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {""});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES} First is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery_givenIn_clause_parenthesesFirstIsEmptyString()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setFirst("");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(pair);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@link AbstractSQLDialect#IN_CLAUSE_PARENTHESES} Second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery_givenIn_clause_parenthesesSecondIsNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    Pair<String, String> pair = AbstractSQLDialect.IN_CLAUSE_PARENTHESES;
    pair.setSecond(null);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getMultiLineComments()).thenReturn(pair);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery_givenInstance() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery_givenNull_whenDBPDataSourceGetSQLDialectReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(null);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getIdentifierQuoteStrings()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery_givenSQLDialectGetIdentifierQuoteStringsReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings()).thenReturn(null);
    when(sqlDialect.getMultiLineComments()).thenReturn(null);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
  }

  /**
   * Test {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor, DBPDataSource,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getIdentifierQuoteStrings()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#injectFiltersToQuery(DBRProgressMonitor,
   * DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSemanticProcessor.injectFiltersToQuery(DBRProgressMonitor, DBPDataSource, String, DBDDataFilter)"
  })
  public void testInjectFiltersToQuery_thenCallsGetIdentifierQuoteStrings() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(sqlDialect.getMultiLineComments()).thenReturn(null);
    when(sqlDialect.getSingleLineComments()).thenReturn(new String[] {"Single Line Comments"});

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            SQLSemanticProcessor.injectFiltersToQuery(
                monitor, dataSource, "Sql Query", new DBDDataFilter()));
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getIdentifierQuoteStrings();
    verify(sqlDialect).getMultiLineComments();
    verify(sqlDialect).getSingleLineComments();
  }

  /**
   * Test {@link SQLSemanticProcessor#getConstraintTable(DBPDataSource, PlainSelect,
   * DBDAttributeConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getConstraintTable(DBPDataSource,
   * PlainSelect, DBDAttributeConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Table SQLSemanticProcessor.getConstraintTable(DBPDataSource, PlainSelect, DBDAttributeConstraint)"
  })
  public void testGetConstraintTable_thenReturnNull() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    PlainSelect select = new PlainSelect();

    // Act
    Table actualConstraintTable =
        SQLSemanticProcessor.getConstraintTable(
            dataSource, select, new DBDAttributeConstraint("Attribute Name", 1));

    // Assert
    assertNull(actualConstraintTable);
  }

  /**
   * Test {@link SQLSemanticProcessor#getTableFromSelect(Select)}.
   *
   * <ul>
   *   <li>Given {@link PlainSelect#PlainSelect()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getTableFromSelect(Select)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.getTableFromSelect(Select)"})
  public void testGetTableFromSelect_givenPlainSelect() {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(new PlainSelect());

    // Act and Assert
    assertNull(SQLSemanticProcessor.getTableFromSelect(select));
  }

  /**
   * Test {@link SQLSemanticProcessor#getTableFromSelect(Select)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table(String)} with {@code Name}.
   *   <li>Then return DBLinkName is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getTableFromSelect(Select)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.getTableFromSelect(Select)"})
  public void testGetTableFromSelect_givenTableWithName_thenReturnDBLinkNameIsName() {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(new Table("Name"));

    // Act
    Table actualTableFromSelect = SQLSemanticProcessor.getTableFromSelect(select);

    // Assert
    assertEquals("Name", actualTableFromSelect.getDBLinkName());
    assertEquals("Name", actualTableFromSelect.getFullyQualifiedName());
    assertEquals("Name", actualTableFromSelect.getName());
    assertEquals("Name", actualTableFromSelect.getUnquotedName());
    assertNull(actualTableFromSelect.getCatalogName());
    assertNull(actualTableFromSelect.getDatabaseName());
    assertNull(actualTableFromSelect.getSchemaName());
    assertNull(actualTableFromSelect.getUnquotedCatalogName());
    assertNull(actualTableFromSelect.getUnquotedDatabaseName());
    assertNull(actualTableFromSelect.getUnquotedSchemaName());
    assertNull(actualTableFromSelect.getAlias());
    assertNull(actualTableFromSelect.getIndexHint());
    assertNull(actualTableFromSelect.getSqlServerHints());
    assertNull(actualTableFromSelect.getASTNode());
    assertNull(actualTableFromSelect.getPivot());
    assertNull(actualTableFromSelect.getSampleClause());
    assertNull(actualTableFromSelect.getUnPivot());
    assertEquals(1, actualTableFromSelect.getNameParts().size());
    assertTrue(actualTableFromSelect.getNamePartDelimiters().isEmpty());
  }

  /**
   * Test {@link SQLSemanticProcessor#getTableFromSelect(Select)}.
   *
   * <ul>
   *   <li>When {@link LateralSubSelect#LateralSubSelect(String)} with {@code Prefix}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getTableFromSelect(Select)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.getTableFromSelect(Select)"})
  public void testGetTableFromSelect_whenLateralSubSelectWithPrefix_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(SQLSemanticProcessor.getTableFromSelect(new LateralSubSelect("Prefix")));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Given {@link Join} (default constructor) RightItem is {@code null}.
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_givenJoinRightItemIsNull_whenNull_thenReturnNull() {
    // Arrange
    Join join = new Join();
    join.setRightItem(null);

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(null);
    select.setJoins(list);

    // Act and Assert
    assertNull(SQLSemanticProcessor.findTableByNameOrAlias(select, null));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Given {@link Join} (default constructor) RightItem is {@link Table#Table(String)} with
   *       {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_givenJoinRightItemIsTableWithName() {
    // Arrange
    Join join = new Join();
    join.setRightItem(new Table("Name"));

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(null);
    select.setJoins(list);

    // Act and Assert
    assertNull(SQLSemanticProcessor.findTableByNameOrAlias(select, "Table Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_givenTable() {
    // Arrange
    Join join = new Join();
    join.setRightItem(new Table("Name"));

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(new Table());
    select.setJoins(list);

    // Act and Assert
    assertNull(SQLSemanticProcessor.findTableByNameOrAlias(select, "Table Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table(String)} with {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_givenTableWithName() {
    // Arrange
    Join join = new Join();
    join.setRightItem(null);

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(new Table("Name"));
    select.setJoins(list);

    // Act and Assert
    assertNull(SQLSemanticProcessor.findTableByNameOrAlias(select, "Table Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table(String)} with {@code Name} Alias is {@link Alias#Alias(String)}
   *       with {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_givenTableWithNameAliasIsAliasWithName() {
    // Arrange
    Table item = new Table("Name");
    item.setAlias(new Alias("Name"));

    Join join = new Join();
    join.setRightItem(item);

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(null);
    select.setJoins(list);

    // Act and Assert
    assertNull(SQLSemanticProcessor.findTableByNameOrAlias(select, "Table Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table(String)} with {@code Name}.
   *   <li>Then return DBLinkName is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_givenTableWithName_thenReturnDBLinkNameIsName() {
    // Arrange
    Join join = new Join();
    join.setRightItem(null);

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(new Table("Name"));
    select.setJoins(list);

    // Act
    Table actualFindTableByNameOrAliasResult =
        SQLSemanticProcessor.findTableByNameOrAlias(select, null);

    // Assert
    assertEquals("Name", actualFindTableByNameOrAliasResult.getDBLinkName());
    assertEquals("Name", actualFindTableByNameOrAliasResult.getFullyQualifiedName());
    assertEquals("Name", actualFindTableByNameOrAliasResult.getName());
    assertEquals("Name", actualFindTableByNameOrAliasResult.getUnquotedName());
    assertNull(actualFindTableByNameOrAliasResult.getCatalogName());
    assertNull(actualFindTableByNameOrAliasResult.getDatabaseName());
    assertNull(actualFindTableByNameOrAliasResult.getSchemaName());
    assertNull(actualFindTableByNameOrAliasResult.getUnquotedCatalogName());
    assertNull(actualFindTableByNameOrAliasResult.getUnquotedDatabaseName());
    assertNull(actualFindTableByNameOrAliasResult.getUnquotedSchemaName());
    assertEquals(1, actualFindTableByNameOrAliasResult.getNameParts().size());
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>Then return {@code Catalog Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_thenReturnCatalogName() {
    // Arrange
    Join join = new Join();
    join.setRightItem(new Table("Name"));

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    Table item = new Table("Catalog Name", "Schema Name", "Table Name");
    select.setFromItem(item);
    select.setJoins(list);

    // Act
    Table actualFindTableByNameOrAliasResult =
        SQLSemanticProcessor.findTableByNameOrAlias(select, "Table Name");

    // Assert
    assertEquals("Catalog Name", actualFindTableByNameOrAliasResult.getCatalogName());
    assertEquals("Catalog Name", actualFindTableByNameOrAliasResult.getDatabaseName());
    assertEquals("Catalog Name", actualFindTableByNameOrAliasResult.getUnquotedCatalogName());
    assertEquals("Catalog Name", actualFindTableByNameOrAliasResult.getUnquotedDatabaseName());
    assertEquals(
        "Catalog Name.Schema Name.Table Name",
        actualFindTableByNameOrAliasResult.getFullyQualifiedName());
    assertEquals("Schema Name", actualFindTableByNameOrAliasResult.getSchemaName());
    assertEquals("Schema Name", actualFindTableByNameOrAliasResult.getUnquotedSchemaName());
    assertEquals("Table Name", actualFindTableByNameOrAliasResult.getDBLinkName());
    assertEquals("Table Name", actualFindTableByNameOrAliasResult.getName());
    assertEquals("Table Name", actualFindTableByNameOrAliasResult.getUnquotedName());
    assertEquals(3, actualFindTableByNameOrAliasResult.getNameParts().size());
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>When {@link LateralSubSelect#LateralSubSelect(String)} with {@code Prefix}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_whenLateralSubSelectWithPrefix_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        SQLSemanticProcessor.findTableByNameOrAlias(new LateralSubSelect("Prefix"), "Table Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return DBLinkName is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_whenNull_thenReturnDBLinkNameIsName() {
    // Arrange
    Join join = new Join();
    join.setRightItem(new Table("Name"));

    ArrayList<Join> list = new ArrayList<>();
    list.add(join);

    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(null);
    select.setJoins(list);

    // Act
    Table actualFindTableByNameOrAliasResult =
        SQLSemanticProcessor.findTableByNameOrAlias(select, null);

    // Assert
    assertEquals("Name", actualFindTableByNameOrAliasResult.getDBLinkName());
    assertEquals("Name", actualFindTableByNameOrAliasResult.getFullyQualifiedName());
    assertEquals("Name", actualFindTableByNameOrAliasResult.getName());
    assertEquals("Name", actualFindTableByNameOrAliasResult.getUnquotedName());
    assertNull(actualFindTableByNameOrAliasResult.getCatalogName());
    assertNull(actualFindTableByNameOrAliasResult.getDatabaseName());
    assertNull(actualFindTableByNameOrAliasResult.getSchemaName());
    assertNull(actualFindTableByNameOrAliasResult.getUnquotedCatalogName());
    assertNull(actualFindTableByNameOrAliasResult.getUnquotedDatabaseName());
    assertNull(actualFindTableByNameOrAliasResult.getUnquotedSchemaName());
    assertEquals(1, actualFindTableByNameOrAliasResult.getNameParts().size());
  }

  /**
   * Test {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}.
   *
   * <ul>
   *   <li>When {@link PlainSelect#PlainSelect(FromItem)} with fromItem is {@link
   *       Table#Table(String)} Joins is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#findTableByNameOrAlias(Select, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Table SQLSemanticProcessor.findTableByNameOrAlias(Select, String)"})
  public void testFindTableByNameOrAlias_whenPlainSelectWithFromItemIsTableJoinsIsNull() {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setFromItem(null);
    select.setJoins(null);

    // Act and Assert
    assertNull(SQLSemanticProcessor.findTableByNameOrAlias(select, null));
  }

  /**
   * Test {@link SQLSemanticProcessor#equalTables(Table, String)}.
   *
   * <ul>
   *   <li>Given {@link Alias#Alias(String)} with {@code Name}.
   *   <li>When {@link Table#Table(String)} with {@code Name} Alias is {@link Alias#Alias(String)}
   *       with {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#equalTables(Table, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.equalTables(Table, String)"})
  public void testEqualTables_givenAliasWithName_whenTableWithNameAliasIsAliasWithName() {
    // Arrange
    Table t1 = new Table("Name");
    t1.setAlias(new Alias("Name"));

    // Act and Assert
    assertTrue(SQLSemanticProcessor.equalTables(t1, "Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#equalTables(Table, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#equalTables(Table, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.equalTables(Table, String)"})
  public void testEqualTables_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLSemanticProcessor.equalTables(null, null));
  }

  /**
   * Test {@link SQLSemanticProcessor#equalTables(Table, String)}.
   *
   * <ul>
   *   <li>When {@link Table#Table(String, String, String)} with {@code Catalog Name} and {@code
   *       Schema Name} and {@code Table Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#equalTables(Table, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.equalTables(Table, String)"})
  public void testEqualTables_whenTableWithCatalogNameAndSchemaNameAndTableName() {
    // Arrange
    Table t1 = new Table("Catalog Name", "Schema Name", "Table Name");

    // Act and Assert
    assertFalse(SQLSemanticProcessor.equalTables(t1, "Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#equalTables(Table, String)}.
   *
   * <ul>
   *   <li>When {@link Table#Table(String)} with {@code Name}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#equalTables(Table, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.equalTables(Table, String)"})
  public void testEqualTables_whenTableWithName_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(SQLSemanticProcessor.equalTables(new Table("Name"), "Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#equalTables(Table, String)}.
   *
   * <ul>
   *   <li>When {@link Table#Table(String)} with {@code Name}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#equalTables(Table, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.equalTables(Table, String)"})
  public void testEqualTables_whenTableWithName_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(SQLSemanticProcessor.equalTables(new Table("Name"), null));
  }

  /**
   * Test {@link SQLSemanticProcessor#equalTables(Table, String)}.
   *
   * <ul>
   *   <li>When {@link Table#Table()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#equalTables(Table, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLSemanticProcessor.equalTables(Table, String)"})
  public void testEqualTables_whenTable_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(SQLSemanticProcessor.equalTables(new Table(), "Name"));
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString() throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setWhere(null);

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, (String) null);

    // Assert that nothing has changed
    assertNull(select.getWhere());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString2() throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    CustomExpression where = new CustomExpression("Expression");
    select.setWhere(where);

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, (String) null);

    // Assert
    Expression where2 = select.getWhere();
    Expression leftExpression = ((AndExpression) where2).getLeftExpression();
    assertTrue(leftExpression instanceof List);
    assertTrue(where2 instanceof AndExpression);
    assertEquals("AND", ((AndExpression) where2).getStringExpression());
    assertNull(((AndExpression) where2).getRightExpression());
    assertNull(where2.getASTNode());
    assertEquals(1, ((List<CustomExpression>) leftExpression).size());
    assertFalse(((AndExpression) where2).isUseOperator());
    assertSame(where, ((List<CustomExpression>) leftExpression).get(0));
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString3() throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setWhere(null);

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, "net.sf.jsqlparser.statement.select.Join");

    // Assert
    Expression where = select.getWhere();
    assertTrue(where instanceof Column);
    assertEquals("jsqlparser", ((Column) where).getSchemaName());
    assertEquals("jsqlparser", ((Column) where).getUnquotedSchemaName());
    assertEquals("net.sf.jsqlparser.statement.select", ((Column) where).getFullyQualifiedName());
    assertEquals("select", ((Column) where).getColumnName());
    assertEquals("select", ((Column) where).getUnquotedColumnName());
    assertEquals("select", ((Column) where).getUnquotedName());
    assertEquals("sf", ((Column) where).getCatalogName());
    assertEquals("sf", ((Column) where).getUnquotedCatalogName());
    assertEquals("statement", ((Column) where).getTableName());
    assertEquals("statement", ((Column) where).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <ul>
   *   <li>Then {@link PlainSelect#PlainSelect()} Where ColumnName is {@code Cond}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString_thenPlainSelectWhereColumnNameIsCond()
      throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect();

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, "Cond String");

    // Assert
    Expression where = select.getWhere();
    assertTrue(where instanceof Column);
    assertEquals("Cond", ((Column) where).getColumnName());
    assertEquals("Cond", ((Column) where).getFullyQualifiedName());
    assertEquals("Cond", ((Column) where).getUnquotedColumnName());
    assertEquals("Cond", ((Column) where).getUnquotedName());
    assertNull(((Column) where).getSchemaName());
    assertNull(((Column) where).getTableName());
    assertNull(((Column) where).getUnquotedSchemaName());
    assertNull(((Column) where).getUnquotedTableName());
    assertNull(((Column) where).getTable());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <ul>
   *   <li>Then {@link PlainSelect#PlainSelect()} Where ColumnName is {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString_thenPlainSelectWhereColumnNameIsString()
      throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect();

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, "java.lang.String[]");

    // Assert
    Expression where = select.getWhere();
    assertTrue(where instanceof Column);
    assertEquals("String", ((Column) where).getColumnName());
    assertEquals("String", ((Column) where).getUnquotedColumnName());
    assertEquals("String", ((Column) where).getUnquotedName());
    assertEquals("java", ((Column) where).getSchemaName());
    assertEquals("java", ((Column) where).getUnquotedSchemaName());
    assertEquals("java.lang.String[]", ((Column) where).getFullyQualifiedName());
    assertEquals("lang", ((Column) where).getTableName());
    assertEquals("lang", ((Column) where).getUnquotedTableName());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString_thenThrowDBCException() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class, () -> SQLSemanticProcessor.addWhereToSelect(new PlainSelect(), "["));
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link PlainSelect#PlainSelect()} Where {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString_when42_thenPlainSelectWhereLongValue()
      throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect();

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, "42");

    // Assert
    Expression where = select.getWhere();
    assertTrue(where instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) where).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) where).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) where).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)} with {@code select},
   * {@code condString}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, String)"})
  public void testAddWhereToSelectWithSelectCondString_whenEmptyString() throws DBCException {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    select.setWhere(null);

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, "");

    // Assert that nothing has changed
    assertNull(select.getWhere());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, Expression)} with {@code
   * select}, {@code conditionExpr}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, Expression)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, Expression)"})
  public void testAddWhereToSelectWithSelectConditionExpr() {
    // Arrange
    PlainSelect select = new PlainSelect();
    CustomExpression conditionExpr = new CustomExpression("Expression");

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, conditionExpr);

    // Assert
    assertSame(conditionExpr, select.getWhere());
  }

  /**
   * Test {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, Expression)} with {@code
   * select}, {@code conditionExpr}.
   *
   * <p>Method under test: {@link SQLSemanticProcessor#addWhereToSelect(PlainSelect, Expression)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSemanticProcessor.addWhereToSelect(PlainSelect, Expression)"})
  public void testAddWhereToSelectWithSelectConditionExpr2() {
    // Arrange
    PlainSelect select = new PlainSelect(new Table("Name"));
    CustomExpression where = new CustomExpression("Expression");
    select.setWhere(where);
    CustomExpression conditionExpr = new CustomExpression("Expression");

    // Act
    SQLSemanticProcessor.addWhereToSelect(select, conditionExpr);

    // Assert
    Expression where2 = select.getWhere();
    Expression leftExpression = ((AndExpression) where2).getLeftExpression();
    assertTrue(leftExpression instanceof List);
    assertTrue(where2 instanceof AndExpression);
    assertEquals("AND", ((AndExpression) where2).getStringExpression());
    assertNull(where2.getASTNode());
    assertEquals(1, ((List<CustomExpression>) leftExpression).size());
    assertFalse(((AndExpression) where2).isUseOperator());
    assertSame(where, ((List<CustomExpression>) leftExpression).get(0));
    assertSame(conditionExpr, ((AndExpression) where2).getRightExpression());
  }

  /**
   * Test {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@link LateralSubSelect#LateralSubSelect()}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLSemanticProcessor.getSimpleTableName(PlainSelect, SQLDialect)"})
  public void testGetSimpleTableName_givenLateralSubSelect_thenThrowDBException()
      throws DBException {
    // Arrange
    PlainSelect select = new PlainSelect();
    select.setFromItem(new LateralSubSelect());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> SQLSemanticProcessor.getSimpleTableName(select, BasicSQLDialect.INSTANCE));
  }

  /**
   * Test {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table(String)} with {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLSemanticProcessor.getSimpleTableName(PlainSelect, SQLDialect)"})
  public void testGetSimpleTableName_givenTableWithName_thenReturnName() throws DBException {
    // Arrange
    PlainSelect select = new PlainSelect();
    select.setFromItem(new Table("Name"));

    // Act and Assert
    assertEquals("Name", SQLSemanticProcessor.getSimpleTableName(select, BasicSQLDialect.INSTANCE));
  }

  /**
   * Test {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table(String, String)} with {@code Schema Name} and {@code Name}.
   *   <li>Then return {@code Schema Name.Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLSemanticProcessor.getSimpleTableName(PlainSelect, SQLDialect)"})
  public void testGetSimpleTableName_givenTableWithSchemaNameAndName_thenReturnSchemaNameName()
      throws DBException {
    // Arrange
    PlainSelect select = new PlainSelect();
    select.setFromItem(new Table("Schema Name", "Name"));

    // Act and Assert
    assertEquals(
        "Schema Name.Name",
        SQLSemanticProcessor.getSimpleTableName(select, BasicSQLDialect.INSTANCE));
  }

  /**
   * Test {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}.
   *
   * <ul>
   *   <li>Given {@link Table#Table()}.
   *   <li>When {@link PlainSelect#PlainSelect()} FromItem is {@link Table#Table()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSemanticProcessor#getSimpleTableName(PlainSelect, SQLDialect)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLSemanticProcessor.getSimpleTableName(PlainSelect, SQLDialect)"})
  public void testGetSimpleTableName_givenTable_whenPlainSelectFromItemIsTable_thenReturnNull()
      throws DBException {
    // Arrange
    PlainSelect select = new PlainSelect();
    select.setFromItem(new Table());

    // Act and Assert
    assertNull(SQLSemanticProcessor.getSimpleTableName(select, BasicSQLDialect.INSTANCE));
  }
}
