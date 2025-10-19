package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectItem;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLSelectItemDiffblueTest {
  /**
   * Test {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link Table#Table(String)} with {@code Name}.
   *   <li>Then return Name is {@link SQLConstants#ASTERISK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSelectItem.<init>(SQLQuery, SelectItem)"})
  public void testNewSQLSelectItem_givenNull_whenTableWithName_thenReturnNameIsAsterisk() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    AllTableColumns allTableColumns = new AllTableColumns(new Table("Name"));

    SelectItem<?> item = new SelectItem<>(allTableColumns);
    item.setAlias(null);

    // Act
    SQLSelectItem actualSqlSelectItem = new SQLSelectItem(query, item);

    // Assert
    assertFalse(actualSqlSelectItem.isPlainColumn());
    assertEquals(SQLConstants.ASTERISK, actualSqlSelectItem.getName());
  }

  /**
   * Test {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}.
   *
   * <ul>
   *   <li>When {@link Column#Column(String)} with {@code Column Name}.
   *   <li>Then return Name is {@code Column Name}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSelectItem.<init>(SQLQuery, SelectItem)"})
  public void testNewSQLSelectItem_whenColumnWithColumnName_thenReturnNameIsColumnName() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");

    SelectItem<?> item = new SelectItem<>(new Column("Column Name"));
    item.setAlias(null);

    // Act
    SQLSelectItem actualSqlSelectItem = new SQLSelectItem(query, item);

    // Assert
    assertEquals("Column Name", actualSqlSelectItem.getName());
    assertNull(actualSqlSelectItem.getEntityMetaData());
    assertTrue(actualSqlSelectItem.isPlainColumn());
  }

  /**
   * Test {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}.
   *
   * <ul>
   *   <li>When {@link SelectItem#SelectItem(Expression)} with expression is {@link
   *       AllColumns#AllColumns()} Alias is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSelectItem.<init>(SQLQuery, SelectItem)"})
  public void testNewSQLSelectItem_whenSelectItemWithExpressionIsAllColumnsAliasIsNull() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");

    SelectItem<?> item = new SelectItem<>(new AllColumns());
    item.setAlias(null);

    // Act
    SQLSelectItem actualSqlSelectItem = new SQLSelectItem(query, item);

    // Assert
    assertNull(actualSqlSelectItem.getEntityMetaData());
    assertFalse(actualSqlSelectItem.isPlainColumn());
    assertEquals(SQLConstants.ASTERISK, actualSqlSelectItem.getName());
  }

  /**
   * Test {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}.
   *
   * <ul>
   *   <li>When {@link SelectItem#SelectItem()}.
   *   <li>Then return Name is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLSelectItem#SQLSelectItem(SQLQuery, SelectItem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLSelectItem.<init>(SQLQuery, SelectItem)"})
  public void testNewSQLSelectItem_whenSelectItem_thenReturnNameIsNull() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLSelectItem actualSqlSelectItem = new SQLSelectItem(query, new SelectItem<>());

    // Assert
    assertEquals("null", actualSqlSelectItem.getName());
    assertNull(actualSqlSelectItem.getEntityMetaData());
    assertFalse(actualSqlSelectItem.isPlainColumn());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLSelectItem#toString()}
   *   <li>{@link SQLSelectItem#getName()}
   *   <li>{@link SQLSelectItem#isPlainColumn()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLSelectItem.getName()",
    "boolean SQLSelectItem.isPlainColumn()",
    "String SQLSelectItem.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    SQLSelectItem sqlSelectItem = new SQLSelectItem(query, new SelectItem<>());

    // Act
    String actualToStringResult = sqlSelectItem.toString();
    String actualName = sqlSelectItem.getName();

    // Assert
    assertEquals("null", actualName);
    assertEquals("null", actualToStringResult);
    assertFalse(sqlSelectItem.isPlainColumn());
  }

  /**
   * Test {@link SQLSelectItem#getEntityMetaData()}.
   *
   * <p>Method under test: {@link SQLSelectItem#getEntityMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCEntityMetaData SQLSelectItem.getEntityMetaData()"
  })
  public void testGetEntityMetaData() {
    // Arrange
    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    SQLSelectItem sqlSelectItem = new SQLSelectItem(query, new SelectItem<>());

    // Act and Assert
    assertNull(sqlSelectItem.getEntityMetaData());
  }
}
