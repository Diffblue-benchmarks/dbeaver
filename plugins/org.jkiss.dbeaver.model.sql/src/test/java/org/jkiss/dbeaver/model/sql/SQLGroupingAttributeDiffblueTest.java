package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.math.BigInteger;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.sql.SQLGroupingAttribute.BoundAttribute;
import org.jkiss.dbeaver.model.sql.SQLGroupingAttribute.CustomAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLGroupingAttributeDiffblueTest {
  /**
   * Test BoundAttribute {@link BoundAttribute#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getFullyQualifiedName(DBPEvaluationContext)}.
   * </ul>
   *
   * <p>Method under test: {@link BoundAttribute#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource BoundAttribute.getDataSource()"})
  public void testBoundAttributeGetDataSource_thenCallsGetFullyQualifiedName() {
    // Arrange
    DBDAttributeBindingCustom binding = mock(DBDAttributeBindingCustom.class);
    when(binding.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any()))
        .thenReturn("Dr Jane Doe");
    when(binding.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    new BoundAttribute(binding).getDataSource();

    // Assert
    verify(binding).getFullyQualifiedName(DBPEvaluationContext.DML);
    verify(binding).getDataSource();
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#equals(Object)}, and {@link
   * CustomAttribute#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CustomAttribute#equals(Object)}
   *   <li>{@link CustomAttribute#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CustomAttribute.equals(Object)", "int CustomAttribute.hashCode()"})
  public void testCustomAttributeEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CustomAttribute customAttribute =
        new CustomAttribute(mock(DBPDataSource.class), "Expression String");
    CustomAttribute customAttribute2 =
        new CustomAttribute(mock(DBPDataSource.class), "Expression String");

    // Act and Assert
    assertEquals(customAttribute, customAttribute2);
    assertEquals(customAttribute.hashCode(), customAttribute2.hashCode());
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#equals(Object)}, and {@link
   * CustomAttribute#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CustomAttribute#equals(Object)}
   *   <li>{@link CustomAttribute#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CustomAttribute.equals(Object)", "int CustomAttribute.hashCode()"})
  public void testCustomAttributeEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CustomAttribute customAttribute =
        new CustomAttribute(mock(DBPDataSource.class), "Expression String");

    // Act and Assert
    assertEquals(customAttribute, customAttribute);
    int expectedHashCodeResult = customAttribute.hashCode();
    assertEquals(expectedHashCodeResult, customAttribute.hashCode());
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CustomAttribute.equals(Object)", "int CustomAttribute.hashCode()"})
  public void testCustomAttributeEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    CustomAttribute customAttribute = new CustomAttribute(mock(DBPDataSource.class), "42");

    // Act and Assert
    assertNotEquals(
        customAttribute, new CustomAttribute(mock(DBPDataSource.class), "Expression String"));
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CustomAttribute.equals(Object)", "int CustomAttribute.hashCode()"})
  public void testCustomAttributeEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CustomAttribute(mock(DBPDataSource.class), "Expression String"), null);
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CustomAttribute.equals(Object)", "int CustomAttribute.hashCode()"})
  public void testCustomAttributeEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new CustomAttribute(mock(DBPDataSource.class), "Expression String"),
        "Different type to CustomAttribute");
  }

  /**
   * Test CustomAttribute getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CustomAttribute#CustomAttribute(DBPDataSource, String)}
   *   <li>{@link CustomAttribute#getDataSource()}
   *   <li>{@link CustomAttribute#getDisplayName()}
   *   <li>{@link CustomAttribute#getExpressionString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CustomAttribute.<init>(DBPDataSource, String)",
    "DBPDataSource CustomAttribute.getDataSource()",
    "String CustomAttribute.getDisplayName()",
    "String CustomAttribute.getExpressionString()"
  })
  public void testCustomAttributeGettersAndSetters() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    CustomAttribute actualCustomAttribute = new CustomAttribute(dataSource, "Expression String");
    DBPDataSource actualDataSource = actualCustomAttribute.getDataSource();
    String actualDisplayName = actualCustomAttribute.getDisplayName();

    // Assert
    assertEquals("Expression String", actualDisplayName);
    assertEquals("Expression String", actualCustomAttribute.getExpressionString());
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#prepareExpression()}.
   *
   * <ul>
   *   <li>Then return {@link Column}.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#prepareExpression()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression CustomAttribute.prepareExpression()"})
  public void testCustomAttributePrepareExpression_thenReturnColumn() {
    // Arrange and Act
    Expression actualPrepareExpressionResult =
        new CustomAttribute(mock(DBPDataSource.class), "Expression String").prepareExpression();

    // Assert
    assertTrue(actualPrepareExpressionResult instanceof Column);
    assertEquals("Expression", ((Column) actualPrepareExpressionResult).getColumnName());
    assertEquals("Expression", ((Column) actualPrepareExpressionResult).getFullyQualifiedName());
    assertEquals("Expression", ((Column) actualPrepareExpressionResult).getUnquotedColumnName());
    assertEquals("Expression", ((Column) actualPrepareExpressionResult).getUnquotedName());
    assertNull(((Column) actualPrepareExpressionResult).getCatalogName());
    assertNull(((Column) actualPrepareExpressionResult).getCommentText());
    assertNull(((Column) actualPrepareExpressionResult).getSchemaName());
    assertNull(((Column) actualPrepareExpressionResult).getTableName());
    assertNull(((Column) actualPrepareExpressionResult).getUnquotedCatalogName());
    assertNull(((Column) actualPrepareExpressionResult).getUnquotedSchemaName());
    assertNull(((Column) actualPrepareExpressionResult).getUnquotedTableName());
    assertNull(((Column) actualPrepareExpressionResult).getArrayConstructor());
    assertNull(((Column) actualPrepareExpressionResult).getTable());
    assertEquals(SQLConstants.DOT, ((Column) actualPrepareExpressionResult).getTableDelimiter());
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#prepareExpression()}.
   *
   * <ul>
   *   <li>Then return {@link LongValue}.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#prepareExpression()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression CustomAttribute.prepareExpression()"})
  public void testCustomAttributePrepareExpression_thenReturnLongValue() {
    // Arrange and Act
    Expression actualPrepareExpressionResult =
        new CustomAttribute(mock(DBPDataSource.class), "42").prepareExpression();

    // Assert
    assertTrue(actualPrepareExpressionResult instanceof LongValue);
    BigInteger bigIntegerValue = ((LongValue) actualPrepareExpressionResult).getBigIntegerValue();
    assertEquals("42", bigIntegerValue.toString());
    assertEquals("42", ((LongValue) actualPrepareExpressionResult).getStringValue());
    assertEquals(1, bigIntegerValue.getLowestSetBit());
    assertEquals(1, bigIntegerValue.signum());
    assertEquals(42L, ((LongValue) actualPrepareExpressionResult).getValue());
    assertArrayEquals(new byte[] {'*'}, bigIntegerValue.toByteArray());
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#prepareExpression()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#prepareExpression()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Expression CustomAttribute.prepareExpression()"})
  public void testCustomAttributePrepareExpression_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new CustomAttribute(mock(DBPDataSource.class), "").prepareExpression());
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#prepareSqlString(String)}.
   *
   * <p>Method under test: {@link CustomAttribute#prepareSqlString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CustomAttribute.prepareSqlString(String)"})
  public void testCustomAttributePrepareSqlString() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        new CustomAttribute(mock(DBPDataSource.class), "42").prepareSqlString("Subquery Alias"));
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#prepareSqlString(String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getSQLDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#prepareSqlString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CustomAttribute.prepareSqlString(String)"})
  public void testCustomAttributePrepareSqlString_thenCallsGetSQLDialect() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);

    // Act
    String actualPrepareSqlStringResult =
        new CustomAttribute(dataSource, "Expression String").prepareSqlString("Subquery Alias");

    // Assert
    verify(dataSource).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Expression String", true, false);
    assertEquals("42", actualPrepareSqlStringResult);
  }

  /**
   * Test CustomAttribute {@link CustomAttribute#prepareSqlString(String)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link CustomAttribute#prepareSqlString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CustomAttribute.prepareSqlString(String)"})
  public void testCustomAttributePrepareSqlString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "", new CustomAttribute(mock(DBPDataSource.class), "").prepareSqlString("Subquery Alias"));
  }

  /**
   * Test {@link SQLGroupingAttribute#makeCustom(DBPDataSource, String)}.
   *
   * <p>Method under test: {@link SQLGroupingAttribute#makeCustom(DBPDataSource, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLGroupingAttribute SQLGroupingAttribute.makeCustom(DBPDataSource, String)"})
  public void testMakeCustom() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    SQLGroupingAttribute actualMakeCustomResult =
        SQLGroupingAttribute.makeCustom(dataSource, "Expression String");

    // Assert
    assertTrue(actualMakeCustomResult instanceof CustomAttribute);
    assertEquals("Expression String", actualMakeCustomResult.getDisplayName());
    assertEquals(
        "Expression String", ((CustomAttribute) actualMakeCustomResult).getExpressionString());
    assertSame(dataSource, actualMakeCustomResult.getDataSource());
  }
}
