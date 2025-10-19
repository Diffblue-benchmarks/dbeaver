package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCColumnMetaDataDiffblueTest {
  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@code Catalog Name}.
   *   <li>Then return {@code Catalog Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenCatalogName_thenReturnCatalogName()
      throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("Catalog Name", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Schema Name", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenEmptyString() throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Schema Name", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenInstance() throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn(null);
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertNull(actualJdbcColumnMetaData.getCatalogName());
    assertNull(actualJdbcColumnMetaData.getSchemaName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSQLDialect} {@link JDBCSQLDialect#getCatalogUsage()} return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenJDBCSQLDialectGetCatalogUsageReturnTen()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = mock(JDBCSQLDialect.class);
    when(jdbcsqlDialect.getSchemaUsage()).thenReturn(1);
    when(jdbcsqlDialect.getStructSeparator()).thenReturn('a');
    when(jdbcsqlDialect.getCatalogUsage()).thenReturn(10);
    when(jdbcsqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(false);
    when(jdbcsqlDialect.getCatalogSeparator()).thenReturn("");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(jdbcsqlDialect);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn(null);
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(jdbcsqlDialect).getCatalogSeparator();
    verify(jdbcsqlDialect).getCatalogUsage();
    verify(jdbcsqlDialect).getSchemaUsage();
    verify(jdbcsqlDialect).getStructSeparator();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    verify(jdbcsqlDialect).isQuotedIdentifier("Table Name");
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("T", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("ble Name", actualJdbcColumnMetaData.getEntityName());
    assertNull(actualJdbcColumnMetaData.getCatalogName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSQLDialect} {@link JDBCSQLDialect#getSchemaUsage()} return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenJDBCSQLDialectGetSchemaUsageReturnTen()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = mock(JDBCSQLDialect.class);
    when(jdbcsqlDialect.getSchemaUsage()).thenReturn(10);
    when(jdbcsqlDialect.getStructSeparator()).thenReturn('a');
    when(jdbcsqlDialect.getCatalogUsage()).thenReturn(1);
    when(jdbcsqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(false);
    when(jdbcsqlDialect.getCatalogSeparator()).thenReturn("");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(jdbcsqlDialect);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn(null);
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(jdbcsqlDialect).getCatalogSeparator();
    verify(jdbcsqlDialect).getCatalogUsage();
    verify(jdbcsqlDialect).getSchemaUsage();
    verify(jdbcsqlDialect).getStructSeparator();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    verify(jdbcsqlDialect).isQuotedIdentifier("Table Name");
    assertEquals("", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertNull(actualJdbcColumnMetaData.getSchemaName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSQLDialect} {@link JDBCSQLDialect#getStructSeparator()} return {@code
   *       A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenJDBCSQLDialectGetStructSeparatorReturnA()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = mock(JDBCSQLDialect.class);
    when(jdbcsqlDialect.getStructSeparator()).thenReturn('A');
    when(jdbcsqlDialect.getCatalogUsage()).thenReturn(1);
    when(jdbcsqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(false);
    when(jdbcsqlDialect.getCatalogSeparator()).thenReturn("");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(jdbcsqlDialect);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn(null);
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(jdbcsqlDialect).getCatalogSeparator();
    verify(jdbcsqlDialect).getCatalogUsage();
    verify(jdbcsqlDialect).getStructSeparator();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    verify(jdbcsqlDialect).isQuotedIdentifier("Table Name");
    assertEquals("", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertNull(actualJdbcColumnMetaData.getSchemaName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSQLDialect} {@link JDBCSQLDialect#isQuotedIdentifier(String)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenJDBCSQLDialectIsQuotedIdentifierReturnTrue()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = mock(JDBCSQLDialect.class);
    when(jdbcsqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(jdbcsqlDialect);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn(null);
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    verify(jdbcsqlDialect).isQuotedIdentifier("Table Name");
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertNull(actualJdbcColumnMetaData.getCatalogName());
    assertNull(actualJdbcColumnMetaData.getSchemaName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given {@code Schema Name}.
   *   <li>Then return CatalogName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenSchemaName_thenReturnCatalogNameIsNull()
      throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Schema Name", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertNull(actualJdbcColumnMetaData.getCatalogName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then return Required.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_givenZero_thenReturnRequired() throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(0);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("Catalog Name", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Schema Name", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
    assertTrue(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Then return EntityName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_thenReturnEntityNameIsNull() throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn(null);

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("Catalog Name", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("Schema Name", actualJdbcColumnMetaData.getSchemaName());
    assertNull(actualJdbcColumnMetaData.getEntityName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Then return Label is empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_thenReturnLabelIsEmptyString() throws SQLException {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals("", actualJdbcColumnMetaData.getLabel());
    assertEquals("Catalog Name", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Schema Name", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("Table Name", actualJdbcColumnMetaData.getEntityName());
  }

  /**
   * Test {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource, ResultSetMetaData, int)}.
   *
   * <ul>
   *   <li>Then return SchemaName is {@code T}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCColumnMetaData#JDBCColumnMetaData(DBPDataSource,
   * ResultSetMetaData, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCColumnMetaData.<init>(DBPDataSource, ResultSetMetaData, int)"})
  public void testNewJDBCColumnMetaData_thenReturnSchemaNameIsT() throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = mock(JDBCSQLDialect.class);
    when(jdbcsqlDialect.getSchemaUsage()).thenReturn(1);
    when(jdbcsqlDialect.getStructSeparator()).thenReturn('a');
    when(jdbcsqlDialect.getCatalogUsage()).thenReturn(1);
    when(jdbcsqlDialect.isQuotedIdentifier(Mockito.<String>any())).thenReturn(false);
    when(jdbcsqlDialect.getCatalogSeparator()).thenReturn("");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(jdbcsqlDialect);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn(null);
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn(null);
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");

    // Act
    JDBCColumnMetaData actualJdbcColumnMetaData =
        new JDBCColumnMetaData(dataSource, resultSetMeta, 1);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    verify(jdbcsqlDialect).getCatalogSeparator();
    verify(jdbcsqlDialect).getCatalogUsage();
    verify(jdbcsqlDialect).getSchemaUsage();
    verify(jdbcsqlDialect).getStructSeparator();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    verify(jdbcsqlDialect).isQuotedIdentifier("Table Name");
    assertEquals("", actualJdbcColumnMetaData.getCatalogName());
    assertEquals("Column Label", actualJdbcColumnMetaData.getLabel());
    assertEquals("T", actualJdbcColumnMetaData.getSchemaName());
    assertEquals("ble Name", actualJdbcColumnMetaData.getEntityName());
    assertFalse(actualJdbcColumnMetaData.isRequired());
  }
}
