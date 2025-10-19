package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPTransactionIsolation;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCInvalidatePhase;
import org.jkiss.dbeaver.model.exec.DBCSavepoint;
import org.jkiss.dbeaver.model.exec.DBCTransactionManager;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCSavepointImpl;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCExecutionContextDiffblueTest {
  /**
   * Test {@link JDBCExecutionContext#JDBCExecutionContext(JDBCRemoteInstance, boolean)}.
   *
   * <p>Method under test: {@link JDBCExecutionContext#JDBCExecutionContext(JDBCRemoteInstance,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.<init>(JDBCRemoteInstance, boolean)"})
  public void testNewJDBCExecutionContext() {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    JDBCExecutionContext actualJdbcExecutionContext = new JDBCExecutionContext(instance, true);

    // Assert
    verify(dataSource).getName();
    assertNull(actualJdbcExecutionContext.getConnectionOrNull());
    assertNull(actualJdbcExecutionContext.getContextDefaults());
    assertFalse(actualJdbcExecutionContext.isConnected());
    assertTrue(actualJdbcExecutionContext.getContextAttributes().isEmpty());
    assertSame(instance, actualJdbcExecutionContext.getOwnerInstance());
  }

  /**
   * Test {@link JDBCExecutionContext#JDBCExecutionContext(JDBCRemoteInstance, String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#JDBCExecutionContext(JDBCRemoteInstance,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.<init>(JDBCRemoteInstance, String)"})
  public void testNewJDBCExecutionContext_givenDBPDriverIsThreadSafeDriverReturnFalse() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    JDBCExecutionContext actualJdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertEquals("Purpose", actualJdbcExecutionContext.getContextName());
    assertNull(actualJdbcExecutionContext.getConnectionOrNull());
    assertNull(actualJdbcExecutionContext.getContextDefaults());
    assertFalse(actualJdbcExecutionContext.isConnected());
    assertTrue(actualJdbcExecutionContext.getContextAttributes().isEmpty());
    assertSame(instance, actualJdbcExecutionContext.getOwnerInstance());
  }

  /**
   * Test {@link JDBCExecutionContext#JDBCExecutionContext(JDBCRemoteInstance, String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#JDBCExecutionContext(JDBCRemoteInstance,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.<init>(JDBCRemoteInstance, String)"})
  public void testNewJDBCExecutionContext_givenDBPDriverIsThreadSafeDriverReturnTrue() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    JDBCExecutionContext actualJdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertEquals("Purpose", actualJdbcExecutionContext.getContextName());
    assertNull(actualJdbcExecutionContext.getConnectionOrNull());
    assertNull(actualJdbcExecutionContext.getContextDefaults());
    assertFalse(actualJdbcExecutionContext.isConnected());
    assertTrue(actualJdbcExecutionContext.getContextAttributes().isEmpty());
    assertSame(instance, actualJdbcExecutionContext.getOwnerInstance());
  }

  /**
   * Test {@link JDBCExecutionContext#getOwnerInstance()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCRemoteInstance#JDBCRemoteInstance(JDBCDataSource)} with dataSource
   *       is {@link JDBCDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#getOwnerInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCRemoteInstance JDBCExecutionContext.getOwnerInstance()"})
  public void testGetOwnerInstance_thenReturnJDBCRemoteInstanceWithDataSourceIsJDBCDataSource() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    JDBCRemoteInstance actualOwnerInstance =
        new JDBCExecutionContext(instance, "Purpose").getOwnerInstance();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertSame(instance, actualOwnerInstance);
  }

  /**
   * Test {@link JDBCExecutionContext#getConnection(DBRProgressMonitor, boolean)} with {@code
   * monitor}, {@code openIfNeeded}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#getConnection(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.sql.Connection JDBCExecutionContext.getConnection(DBRProgressMonitor, boolean)"
  })
  public void testGetConnectionWithMonitorOpenIfNeeded_thenThrowSQLException()
      throws SQLException, DBCException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.isConnectionReadOnly()).thenReturn(true);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.openConnection(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<JDBCExecutionContext>any(),
            Mockito.<String>any()))
        .thenThrow(new DBCException("An error occurred"));
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcExecutionContext.getConnection(new LoggingProgressMonitor(), true));
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).isConnectionReadOnly();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#getConnection(DBRProgressMonitor)} with {@code monitor}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#getConnection(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.Connection JDBCExecutionContext.getConnection(DBRProgressMonitor)"})
  public void testGetConnectionWithMonitor_thenThrowSQLException()
      throws SQLException, DBCException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.isConnectionReadOnly()).thenReturn(true);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.openConnection(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<JDBCExecutionContext>any(),
            Mockito.<String>any()))
        .thenThrow(new DBCException("An error occurred"));
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcExecutionContext.getConnection(new LoggingProgressMonitor()));
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).isConnectionReadOnly();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#connect(DBRProgressMonitor)} with {@code monitor}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#connect(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.connect(DBRProgressMonitor)"})
  public void testConnectWithMonitor_thenThrowDBCException() throws DBCException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.isConnectionReadOnly()).thenReturn(true);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.openConnection(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<JDBCExecutionContext>any(),
            Mockito.<String>any()))
        .thenThrow(new DBCException("An error occurred"));
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);

    // Act and Assert
    assertThrows(
        DBCException.class, () -> jdbcExecutionContext.connect(new LoggingProgressMonitor()));
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).isConnectionReadOnly();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#checkContextAlive(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#checkContextAlive(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.checkContextAlive(DBRProgressMonitor)"})
  public void testCheckContextAlive_thenThrowDBCException() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jdbcExecutionContext.checkContextAlive(new LoggingProgressMonitor()));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#isConnected()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#isConnected()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCExecutionContext.isConnected()"})
  public void testIsConnected_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnFalse() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    boolean actualIsConnectedResult = new JDBCExecutionContext(instance, "Purpose").isConnected();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertFalse(actualIsConnectedResult);
  }

  /**
   * Test {@link JDBCExecutionContext#invalidateContext(DBRProgressMonitor, DBCInvalidatePhase)}
   * with {@code monitor}, {@code phase}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#invalidateContext(DBRProgressMonitor,
   * DBCInvalidatePhase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCExecutionContext.invalidateContext(DBRProgressMonitor, DBCInvalidatePhase)"
  })
  public void testInvalidateContextWithMonitorPhase_thenThrowDBCException() throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.isConnectionReadOnly()).thenReturn(true);
    when(dbpDataSourceContainer.getConnectionConfiguration())
        .thenReturn(new DBPConnectionConfiguration());
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.openConnection(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<JDBCExecutionContext>any(),
            Mockito.<String>any()))
        .thenThrow(new DBCException("An error occurred"));
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jdbcExecutionContext.invalidateContext(
                new LoggingProgressMonitor(), DBCInvalidatePhase.INVALIDATE));
    verify(dbpDataSourceContainer).getConnectionConfiguration();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer).isConnectionReadOnly();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dataSource).getName();
    verify(dataSource)
        .openConnection(
            isA(DBRProgressMonitor.class), isA(JDBCExecutionContext.class), eq("Purpose"));
  }

  /**
   * Test {@link JDBCExecutionContext#invalidateContext(DBRProgressMonitor, DBCInvalidatePhase)}
   * with {@code monitor}, {@code phase}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDriver()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#invalidateContext(DBRProgressMonitor,
   * DBCInvalidatePhase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCExecutionContext.invalidateContext(DBRProgressMonitor, DBCInvalidatePhase)"
  })
  public void testInvalidateContextWithMonitorPhase_whenNull_thenCallsGetDriver()
      throws DBException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act
    jdbcExecutionContext.invalidateContext(new LoggingProgressMonitor(), null);

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#setTransactionIsolation(DBRProgressMonitor,
   * DBPTransactionIsolation)}.
   *
   * <ul>
   *   <li>When {@link DBPTransactionIsolation}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#setTransactionIsolation(DBRProgressMonitor,
   * DBPTransactionIsolation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCExecutionContext.setTransactionIsolation(DBRProgressMonitor, DBPTransactionIsolation)"
  })
  public void testSetTransactionIsolation_whenDBPTransactionIsolation_thenThrowDBCException()
      throws DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jdbcExecutionContext.setTransactionIsolation(
                new LoggingProgressMonitor(), mock(DBPTransactionIsolation.class)));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#setSavepoint(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#setSavepoint(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCSavepoint JDBCExecutionContext.setSavepoint(DBRProgressMonitor, String)"})
  public void testSetSavepoint_thenThrowDBCException() throws DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jdbcExecutionContext.setSavepoint(new LoggingProgressMonitor(), "Name"));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#releaseSavepoint(DBRProgressMonitor, DBCSavepoint)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getName()} return {@code Name}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#releaseSavepoint(DBRProgressMonitor,
   * DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCExecutionContext.releaseSavepoint(DBRProgressMonitor, DBCSavepoint)"
  })
  public void testReleaseSavepoint_givenJDBCDataSourceGetNameReturnName_thenThrowDBCException()
      throws DBCException {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, true);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            jdbcExecutionContext.releaseSavepoint(
                new LoggingProgressMonitor(), mock(JDBCSavepointImpl.class)));
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#getAdapter(Class)}.
   *
   * <p>Method under test: {@link JDBCExecutionContext#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCExecutionContext.getAdapter(Class)"})
  public void testGetAdapter() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");
    Class<DBCTransactionManager> adapter = DBCTransactionManager.class;

    // Act
    Object actualAdapter = jdbcExecutionContext.getAdapter(adapter);

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertSame(jdbcExecutionContext, actualAdapter);
  }

  /**
   * Test {@link JDBCExecutionContext#getAdapter(Class)}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#getAdapter(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCExecutionContext.getAdapter(Class)"})
  public void testGetAdapter_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnNull() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");
    Class<Object> adapter = Object.class;

    // Act
    Object actualAdapter = jdbcExecutionContext.getAdapter(adapter);

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertNull(actualAdapter);
  }

  /**
   * Test {@link JDBCExecutionContext#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code Name - Purpose}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCExecutionContext.toString()"})
  public void testToString_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnNamePurpose() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    String actualToStringResult = new JDBCExecutionContext(instance, "Purpose").toString();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource, atLeast(1)).getName();
    assertEquals("Name - Purpose", actualToStringResult);
  }

  /**
   * Test {@link JDBCExecutionContext#toString()}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getName()} return {@code foo}.
   *   <li>Then calls {@link JDBCDataSource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCExecutionContext.toString()"})
  public void testToString_givenJDBCDataSourceGetNameReturnFoo_thenCallsGetName() {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    JDBCDataSource dataSource2 = mock(JDBCDataSource.class);
    when(dataSource2.getName()).thenReturn("foo");
    JDBCRemoteInstance instance2 = new JDBCRemoteInstance(dataSource2);

    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance2, true);
    jdbcExecutionContext.setOwnerInstance(instance);

    // Act
    jdbcExecutionContext.toString();

    // Assert
    verify(dataSource, atLeast(1)).getName();
    verify(dataSource2, atLeast(1)).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#lockQueryExecution()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#lockQueryExecution()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.lockQueryExecution()"})
  public void testLockQueryExecution_givenDBPDriverIsThreadSafeDriverReturnFalse() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    new JDBCExecutionContext(instance, "Purpose").lockQueryExecution();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#lockQueryExecution()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#lockQueryExecution()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.lockQueryExecution()"})
  public void testLockQueryExecution_givenDBPDriverIsThreadSafeDriverReturnTrue() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    new JDBCExecutionContext(instance, "Purpose").lockQueryExecution();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }

  /**
   * Test {@link JDBCExecutionContext#unlockQueryExecution()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDriver()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCExecutionContext#unlockQueryExecution()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCExecutionContext.unlockQueryExecution()"})
  public void testUnlockQueryExecution_thenCallsGetDriver() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    new JDBCExecutionContext(instance, "Purpose").unlockQueryExecution();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
  }
}
