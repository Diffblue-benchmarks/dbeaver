package org.jkiss.dbeaver.model.impl.jdbc;

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
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPTransactionIsolation;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCDatabaseMetaData;
import org.jkiss.dbeaver.model.impl.struct.RelationalObjectType;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.Version;

public class JDBCDataSourceInfoDiffblueTest {
  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(DBPDataSourceContainer)"})
  public void testNewJDBCDataSourceInfo() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getName()).thenReturn("Name");

    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDataSource()).thenReturn(null);
    when(container.getDriver()).thenReturn(dbpDriver);

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(container);

    // Assert
    verify(container).getDataSource();
    verify(container).getDriver();
    verify(dbpDriver).getName();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertEquals(1, supportedTransactionsIsolation.size());
    assertTrue(supportedTransactionsIsolation instanceof List);
    assertEquals("", actualJdbcDataSourceInfo.getDatabaseProductVersion());
    assertEquals("?", actualJdbcDataSourceInfo.getDatabaseProductName());
    assertEquals("?", actualJdbcDataSourceInfo.getDriverVersion());
    assertEquals("Name", actualJdbcDataSourceInfo.getDriverName());
    assertNull(actualJdbcDataSourceInfo.getDatabaseProductDetails());
    assertNull(actualJdbcDataSourceInfo.getDatabaseVersion());
    assertEquals(10, actualJdbcDataSourceInfo.getSupportedObjectTypes().length);
    assertFalse(actualJdbcDataSourceInfo.isMultipleResultsFailsOnMaxRows());
    assertFalse(actualJdbcDataSourceInfo.isDynamicMetadata());
    assertFalse(actualJdbcDataSourceInfo.isMultipleResultsFetchBroken());
    assertFalse(actualJdbcDataSourceInfo.isReadOnlyData());
    assertFalse(actualJdbcDataSourceInfo.isReadOnlyMetaData());
    assertFalse(actualJdbcDataSourceInfo.supportsBatchUpdates());
    assertFalse(actualJdbcDataSourceInfo.supportsTransactions());
    assertTrue(actualJdbcDataSourceInfo.isIgnoreReadOnlyFlag());
    assertTrue(actualJdbcDataSourceInfo.supportsIndexes());
    assertTrue(actualJdbcDataSourceInfo.supportsStoredCode());
    assertTrue(actualJdbcDataSourceInfo.supportsViews());
    assertEquals(JDBCDataSourceInfo.TERM_CATALOG, actualJdbcDataSourceInfo.getCatalogTerm());
    assertEquals(JDBCDataSourceInfo.TERM_PROCEDURE, actualJdbcDataSourceInfo.getProcedureTerm());
    assertEquals(JDBCDataSourceInfo.TERM_SCHEMA, actualJdbcDataSourceInfo.getSchemaTerm());
  }

  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Given {@code Catalog Term}.
   *   <li>Then return {@code Catalog term}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(JDBCDatabaseMetaData)"})
  public void testNewJDBCDataSourceInfo_givenCatalogTerm_thenReturnCatalogTerm()
      throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertEquals(5, supportedTransactionsIsolation.size());
    assertTrue(supportedTransactionsIsolation instanceof List);
    DBPTransactionIsolation getResult =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(1);
    assertTrue(getResult instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult2 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(2);
    assertTrue(getResult2 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult3 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(3);
    assertTrue(getResult3 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult4 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(4);
    assertTrue(getResult4 instanceof JDBCTransactionIsolation);
    assertEquals("Catalog term", actualJdbcDataSourceInfo.getCatalogTerm());
    assertEquals(JDBCTransactionIsolation.READ_COMMITTED, getResult2);
    assertEquals(JDBCTransactionIsolation.READ_UNCOMMITTED, getResult);
    assertEquals(JDBCTransactionIsolation.REPEATABLE_READ, getResult3);
    assertEquals(JDBCTransactionIsolation.SERIALIZABLE, getResult4);
  }

  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(JDBCDatabaseMetaData)"})
  public void testNewJDBCDataSourceInfo_givenEmptyString() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertEquals(5, supportedTransactionsIsolation.size());
    assertTrue(supportedTransactionsIsolation instanceof List);
    DBPTransactionIsolation getResult =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(1);
    assertTrue(getResult instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult2 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(2);
    assertTrue(getResult2 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult3 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(3);
    assertTrue(getResult3 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult4 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(4);
    assertTrue(getResult4 instanceof JDBCTransactionIsolation);
    assertEquals(JDBCTransactionIsolation.READ_COMMITTED, getResult2);
    assertEquals(JDBCTransactionIsolation.READ_UNCOMMITTED, getResult);
    assertEquals(JDBCTransactionIsolation.REPEATABLE_READ, getResult3);
    assertEquals(JDBCTransactionIsolation.SERIALIZABLE, getResult4);
    assertEquals(JDBCDataSourceInfo.TERM_CATALOG, actualJdbcDataSourceInfo.getCatalogTerm());
  }

  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then return {@code Driver Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(JDBCDatabaseMetaData)"})
  public void testNewJDBCDataSourceInfo_thenReturnDriverName() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(false);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertTrue(supportedTransactionsIsolation instanceof List);
    assertEquals("Catalog term", actualJdbcDataSourceInfo.getCatalogTerm());
    assertEquals("Driver Name", actualJdbcDataSourceInfo.getDriverName());
    assertFalse(actualJdbcDataSourceInfo.supportsTransactions());
    assertTrue(supportedTransactionsIsolation.isEmpty());
  }

  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then return DriverName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(JDBCDatabaseMetaData)"})
  public void testNewJDBCDataSourceInfo_thenReturnDriverNameIsNull() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn(null);
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertEquals(5, supportedTransactionsIsolation.size());
    assertTrue(supportedTransactionsIsolation instanceof List);
    DBPTransactionIsolation getResult =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(1);
    assertTrue(getResult instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult2 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(2);
    assertTrue(getResult2 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult3 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(3);
    assertTrue(getResult3 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult4 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(4);
    assertTrue(getResult4 instanceof JDBCTransactionIsolation);
    assertEquals("Catalog term", actualJdbcDataSourceInfo.getCatalogTerm());
    assertNull(actualJdbcDataSourceInfo.getDriverName());
    assertEquals(JDBCTransactionIsolation.READ_COMMITTED, getResult2);
    assertEquals(JDBCTransactionIsolation.READ_UNCOMMITTED, getResult);
    assertEquals(JDBCTransactionIsolation.REPEATABLE_READ, getResult3);
    assertEquals(JDBCTransactionIsolation.SERIALIZABLE, getResult4);
  }

  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>Then return SupportedTransactionsIsolation size is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(JDBCDatabaseMetaData)"})
  public void testNewJDBCDataSourceInfo_thenReturnSupportedTransactionsIsolationSizeIsOne()
      throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(false);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertEquals(1, supportedTransactionsIsolation.size());
    assertTrue(supportedTransactionsIsolation instanceof List);
    DBPTransactionIsolation getResult =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(0);
    assertTrue(getResult instanceof JDBCTransactionIsolation);
    assertEquals(JDBCTransactionIsolation.NONE, getResult);
    assertTrue(actualJdbcDataSourceInfo.supportsTransactions());
  }

  /**
   * Test {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}.
   *
   * <ul>
   *   <li>When {@link JDBCDatabaseMetaData} {@link JDBCDatabaseMetaData#getCatalogTerm()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataSourceInfo.<init>(JDBCDatabaseMetaData)"})
  public void testNewJDBCDataSourceInfo_whenJDBCDatabaseMetaDataGetCatalogTermReturnNull()
      throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn(null);
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    JDBCDataSourceInfo actualJdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    Collection<DBPTransactionIsolation> supportedTransactionsIsolation =
        actualJdbcDataSourceInfo.getSupportedTransactionsIsolation();
    assertEquals(5, supportedTransactionsIsolation.size());
    assertTrue(supportedTransactionsIsolation instanceof List);
    DBPTransactionIsolation getResult =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(1);
    assertTrue(getResult instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult2 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(2);
    assertTrue(getResult2 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult3 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(3);
    assertTrue(getResult3 instanceof JDBCTransactionIsolation);
    DBPTransactionIsolation getResult4 =
        ((List<DBPTransactionIsolation>) supportedTransactionsIsolation).get(4);
    assertTrue(getResult4 instanceof JDBCTransactionIsolation);
    assertEquals(JDBCTransactionIsolation.READ_COMMITTED, getResult2);
    assertEquals(JDBCTransactionIsolation.READ_UNCOMMITTED, getResult);
    assertEquals(JDBCTransactionIsolation.REPEATABLE_READ, getResult3);
    assertEquals(JDBCTransactionIsolation.SERIALIZABLE, getResult4);
    assertEquals(JDBCDataSourceInfo.TERM_CATALOG, actualJdbcDataSourceInfo.getCatalogTerm());
  }

  /**
   * Test {@link JDBCDataSourceInfo#isReadOnlyData()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#isReadOnlyData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.isReadOnlyData()"})
  public void testIsReadOnlyData_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualIsReadOnlyDataResult = new JDBCDataSourceInfo(metaData).isReadOnlyData();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertFalse(actualIsReadOnlyDataResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#isReadOnlyData()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#isReadOnlyData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.isReadOnlyData()"})
  public void testIsReadOnlyData_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    JDBCDataSourceInfo jdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);
    jdbcDataSourceInfo.setReadOnlyData(true);

    // Act
    boolean actualIsReadOnlyDataResult = jdbcDataSourceInfo.isReadOnlyData();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualIsReadOnlyDataResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#isReadOnlyMetaData()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#isReadOnlyMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.isReadOnlyMetaData()"})
  public void testIsReadOnlyMetaData_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualIsReadOnlyMetaDataResult = new JDBCDataSourceInfo(metaData).isReadOnlyMetaData();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertFalse(actualIsReadOnlyMetaDataResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#isReadOnlyMetaData()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#isReadOnlyMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.isReadOnlyMetaData()"})
  public void testIsReadOnlyMetaData_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    JDBCDataSourceInfo jdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);
    jdbcDataSourceInfo.setReadOnlyMetaData(true);

    // Act
    boolean actualIsReadOnlyMetaDataResult = jdbcDataSourceInfo.isReadOnlyMetaData();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualIsReadOnlyMetaDataResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#getDatabaseProductVersion()}.
   *
   * <ul>
   *   <li>Then return {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#getDatabaseProductVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataSourceInfo.getDatabaseProductVersion()"})
  public void testGetDatabaseProductVersion_thenReturn102() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    String actualDatabaseProductVersion =
        new JDBCDataSourceInfo(metaData).getDatabaseProductVersion();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertEquals("1.0.2", actualDatabaseProductVersion);
  }

  /**
   * Test {@link JDBCDataSourceInfo#getDatabaseProductVersion()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#getDatabaseProductVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataSourceInfo.getDatabaseProductVersion()"})
  public void testGetDatabaseProductVersion_thenReturnEmptyString() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    String actualDatabaseProductVersion =
        new JDBCDataSourceInfo(metaData).getDatabaseProductVersion();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertEquals("", actualDatabaseProductVersion);
  }

  /**
   * Test {@link JDBCDataSourceInfo#getDatabaseVersion()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#getDatabaseVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Version JDBCDataSourceInfo.getDatabaseVersion()"})
  public void testGetDatabaseVersion_thenReturnNull() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    Version actualDatabaseVersion = new JDBCDataSourceInfo(metaData).getDatabaseVersion();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertNull(actualDatabaseVersion);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsSavepoints()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsSavepoints()"})
  public void testSupportsSavepoints_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualSupportsSavepointsResult = new JDBCDataSourceInfo(metaData).supportsSavepoints();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertFalse(actualSupportsSavepointsResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsReferentialIntegrity()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsReferentialIntegrity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsReferentialIntegrity()"})
  public void testSupportsReferentialIntegrity_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    JDBCDataSourceInfo jdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);
    jdbcDataSourceInfo.setSupportsReferences(false);

    // Act
    boolean actualSupportsReferentialIntegrityResult =
        jdbcDataSourceInfo.supportsReferentialIntegrity();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertFalse(actualSupportsReferentialIntegrityResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsReferentialIntegrity()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsReferentialIntegrity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsReferentialIntegrity()"})
  public void testSupportsReferentialIntegrity_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualSupportsReferentialIntegrityResult =
        new JDBCDataSourceInfo(metaData).supportsReferentialIntegrity();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualSupportsReferentialIntegrityResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsResultSetLimit()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsResultSetLimit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsResultSetLimit()"})
  public void testSupportsResultSetLimit_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualSupportsResultSetLimitResult =
        new JDBCDataSourceInfo(metaData).supportsResultSetLimit();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualSupportsResultSetLimitResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsResultSetScroll()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsResultSetScroll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsResultSetScroll()"})
  public void testSupportsResultSetScroll_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    JDBCDataSourceInfo jdbcDataSourceInfo = new JDBCDataSourceInfo(metaData);
    jdbcDataSourceInfo.setSupportsResultSetScroll(false);

    // Act
    boolean actualSupportsResultSetScrollResult = jdbcDataSourceInfo.supportsResultSetScroll();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertFalse(actualSupportsResultSetScrollResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsResultSetScroll()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsResultSetScroll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsResultSetScroll()"})
  public void testSupportsResultSetScroll_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualSupportsResultSetScrollResult =
        new JDBCDataSourceInfo(metaData).supportsResultSetScroll();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualSupportsResultSetScrollResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsMultipleResults()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsMultipleResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsMultipleResults()"})
  public void testSupportsMultipleResults_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualSupportsMultipleResultsResult =
        new JDBCDataSourceInfo(metaData).supportsMultipleResults();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertFalse(actualSupportsMultipleResultsResult);
  }

  /**
   * Test {@link JDBCDataSourceInfo#getSupportedObjectTypes()}.
   *
   * <ul>
   *   <li>Then first element return {@link RelationalObjectType}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#getSupportedObjectTypes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectType[] JDBCDataSourceInfo.getSupportedObjectTypes()"})
  public void testGetSupportedObjectTypes_thenFirstElementReturnRelationalObjectType()
      throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    DBSObjectType[] actualSupportedObjectTypes =
        new JDBCDataSourceInfo(metaData).getSupportedObjectTypes();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualSupportedObjectTypes[0] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[1] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[2] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[3] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[4] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[5] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[6] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[7] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[8] instanceof RelationalObjectType);
    assertTrue(actualSupportedObjectTypes[9] instanceof RelationalObjectType);
    assertEquals(10, actualSupportedObjectTypes.length);
  }

  /**
   * Test {@link JDBCDataSourceInfo#supportsStatementBinding()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataSourceInfo#supportsStatementBinding()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDataSourceInfo.supportsStatementBinding()"})
  public void testSupportsStatementBinding_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    // Act
    boolean actualSupportsStatementBindingResult =
        new JDBCDataSourceInfo(metaData).supportsStatementBinding();

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    assertTrue(actualSupportsStatementBindingResult);
  }
}
