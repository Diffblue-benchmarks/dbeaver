package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPExclusiveResource;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.impl.SimpleExclusiveLock;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCRemoteInstanceDiffblueTest {
  /**
   * Test {@link JDBCRemoteInstance#JDBCRemoteInstance(JDBCDataSource)}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#JDBCRemoteInstance(JDBCDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.<init>(JDBCDataSource)"})
  public void testNewJDBCRemoteInstance() {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    // Act
    JDBCRemoteInstance actualJdbcRemoteInstance = new JDBCRemoteInstance(dataSource);

    // Assert
    assertTrue(actualJdbcRemoteInstance.getExclusiveLock() instanceof SimpleExclusiveLock);
    assertNull(actualJdbcRemoteInstance.getDescription());
    assertNull(actualJdbcRemoteInstance.getName());
    assertNull(actualJdbcRemoteInstance.executionContext);
    assertNull(actualJdbcRemoteInstance.metaContext);
    assertNull(actualJdbcRemoteInstance.sharedInstance);
    assertEquals(0, actualJdbcRemoteInstance.getAllContexts().length);
    assertTrue(actualJdbcRemoteInstance.isPersisted());
    assertEquals(JDBCExecutionContext.TYPE_MAIN, actualJdbcRemoteInstance.getMainContextName());
    assertEquals(
        JDBCExecutionContext.TYPE_METADATA, actualJdbcRemoteInstance.getMetadataContextName());
    assertSame(dataSource, actualJdbcRemoteInstance.getDataSource());
    assertSame(dataSource, actualJdbcRemoteInstance.getParentObject());
  }

  /**
   * Test {@link JDBCRemoteInstance#JDBCRemoteInstance(DBRProgressMonitor, JDBCDataSource,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBCException#DBCException(String)} with message is {@code An error
   *       occurred}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#JDBCRemoteInstance(DBRProgressMonitor,
   * JDBCDataSource, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.<init>(DBRProgressMonitor, JDBCDataSource, boolean)"})
  public void testNewJDBCRemoteInstance_givenDBCExceptionWithMessageIsAnErrorOccurred()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getClientApplicationName()).thenReturn("Dr Jane Doe");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.createExecutionContext(
            Mockito.<JDBCRemoteInstance>any(), Mockito.<String>any()))
        .thenThrow(new DBCException("An error occurred"));
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(DBCException.class, () -> new JDBCRemoteInstance(monitor, dataSource, true));
    verify(dbpDataSourceContainer).getClientApplicationName();
    verify(dataSource).getContainer();
    verify(dataSource).createExecutionContext(isA(JDBCRemoteInstance.class), eq("Dr Jane Doe"));
  }

  /**
   * Test {@link JDBCRemoteInstance#JDBCRemoteInstance(DBRProgressMonitor, JDBCDataSource,
   * boolean)}.
   *
   * <ul>
   *   <li>Then ExclusiveLock return {@link SimpleExclusiveLock}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#JDBCRemoteInstance(DBRProgressMonitor,
   * JDBCDataSource, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.<init>(DBRProgressMonitor, JDBCDataSource, boolean)"})
  public void testNewJDBCRemoteInstance_thenExclusiveLockReturnSimpleExclusiveLock()
      throws DBException {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);

    // Act
    JDBCRemoteInstance actualJdbcRemoteInstance =
        new JDBCRemoteInstance(new LoggingProgressMonitor(), dataSource, false);

    // Assert
    assertTrue(actualJdbcRemoteInstance.getExclusiveLock() instanceof SimpleExclusiveLock);
    assertNull(actualJdbcRemoteInstance.getDescription());
    assertNull(actualJdbcRemoteInstance.getName());
    assertNull(actualJdbcRemoteInstance.executionContext);
    assertNull(actualJdbcRemoteInstance.metaContext);
    assertNull(actualJdbcRemoteInstance.sharedInstance);
    assertEquals(0, actualJdbcRemoteInstance.getAllContexts().length);
    assertTrue(actualJdbcRemoteInstance.isPersisted());
    assertEquals(JDBCExecutionContext.TYPE_MAIN, actualJdbcRemoteInstance.getMainContextName());
    assertEquals(
        JDBCExecutionContext.TYPE_METADATA, actualJdbcRemoteInstance.getMetadataContextName());
    assertSame(dataSource, actualJdbcRemoteInstance.getDataSource());
    assertSame(dataSource, actualJdbcRemoteInstance.getParentObject());
  }

  /**
   * Test {@link JDBCRemoteInstance#getDataSource()}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCDataSource JDBCRemoteInstance.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(mock(JDBCDataSource.class));

    // Act
    JDBCDataSource actualDataSource = jdbcRemoteInstance.getDataSource();

    // Assert
    assertSame(jdbcRemoteInstance.dataSource, actualDataSource);
  }

  /**
   * Test {@link JDBCRemoteInstance#getName()}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCRemoteInstance.getName()"})
  public void testGetName() {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");

    // Act
    String actualName = new JDBCRemoteInstance(dataSource).getName();

    // Assert
    verify(dataSource).getName();
    assertEquals("Name", actualName);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCRemoteInstance#getDescription()}
   *   <li>{@link JDBCRemoteInstance#getExclusiveLock()}
   *   <li>{@link JDBCRemoteInstance#getMainContextName()}
   *   <li>{@link JDBCRemoteInstance#getMetadataContextName()}
   *   <li>{@link JDBCRemoteInstance#getParentObject()}
   *   <li>{@link JDBCRemoteInstance#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCRemoteInstance.getDescription()",
    "DBPExclusiveResource JDBCRemoteInstance.getExclusiveLock()",
    "String JDBCRemoteInstance.getMainContextName()",
    "String JDBCRemoteInstance.getMetadataContextName()",
    "DBSObject JDBCRemoteInstance.getParentObject()",
    "boolean JDBCRemoteInstance.isPersisted()"
  })
  public void testGettersAndSetters() {
    // Arrange
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);

    // Act
    String actualDescription = jdbcRemoteInstance.getDescription();
    DBPExclusiveResource actualExclusiveLock = jdbcRemoteInstance.getExclusiveLock();
    String actualMainContextName = jdbcRemoteInstance.getMainContextName();
    String actualMetadataContextName = jdbcRemoteInstance.getMetadataContextName();
    DBSObject actualParentObject = jdbcRemoteInstance.getParentObject();

    // Assert
    assertTrue(actualExclusiveLock instanceof SimpleExclusiveLock);
    assertNull(actualDescription);
    assertNull(actualParentObject);
    assertTrue(jdbcRemoteInstance.isPersisted());
    assertEquals(JDBCExecutionContext.TYPE_MAIN, actualMainContextName);
    assertEquals(JDBCExecutionContext.TYPE_METADATA, actualMetadataContextName);
  }

  /**
   * Test {@link JDBCRemoteInstance#initializeMainContext(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#initializeMainContext(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.initializeMainContext(DBRProgressMonitor)"})
  public void testInitializeMainContext() throws DBCException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getClientApplicationName()).thenReturn("Dr Jane Doe");

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.createExecutionContext(
            Mockito.<JDBCRemoteInstance>any(), Mockito.<String>any()))
        .thenThrow(new DBCException("An error occurred"));
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(dataSource);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> jdbcRemoteInstance.initializeMainContext(new LoggingProgressMonitor()));
    verify(dbpDataSourceContainer).getClientApplicationName();
    verify(dataSource).getContainer();
    verify(dataSource).createExecutionContext(isA(JDBCRemoteInstance.class), eq("Dr Jane Doe"));
  }

  /**
   * Test {@link JDBCRemoteInstance#getAllContexts()}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#getAllContexts()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCExecutionContext[] JDBCRemoteInstance.getAllContexts()"})
  public void testGetAllContexts() {
    // Arrange, Act and Assert
    assertEquals(0, new JDBCRemoteInstance(mock(JDBCDataSource.class)).getAllContexts().length);
  }

  /**
   * Test {@link JDBCRemoteInstance#getDefaultContext(boolean)} with {@code meta}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#getDefaultContext(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCExecutionContext JDBCRemoteInstance.getDefaultContext(boolean)"})
  public void testGetDefaultContextWithMeta() {
    // Arrange, Act and Assert
    assertNull(new JDBCRemoteInstance(mock(JDBCDataSource.class)).getDefaultContext(true));
  }

  /**
   * Test {@link JDBCRemoteInstance#getDefaultContext(DBRProgressMonitor, boolean)} with {@code
   * monitor}, {@code meta}.
   *
   * <p>Method under test: {@link JDBCRemoteInstance#getDefaultContext(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCExecutionContext JDBCRemoteInstance.getDefaultContext(DBRProgressMonitor, boolean)"
  })
  public void testGetDefaultContextWithMonitorMeta() {
    // Arrange
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(mock(JDBCDataSource.class));

    // Act and Assert
    assertNull(jdbcRemoteInstance.getDefaultContext(new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)} with {@code monitor},
   * {@code keepMeta}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCExecutionContext#getContextName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor, boolean)"})
  public void testShutdownWithMonitorKeepMeta_thenCallsGetContextName() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    doNothing().when(context).close();

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new LoggingProgressMonitor(), false);

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)} with {@code monitor},
   * {@code keepMeta}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)} with {@link
   *       JDBCDataSourceProvider#log}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor, boolean)"})
  public void testShutdownWithMonitorKeepMeta_whenLoggingProgressMonitorWithLog() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    doNothing().when(context).close();

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new LoggingProgressMonitor(JDBCDataSourceProvider.log), false);

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)} with {@code monitor},
   * {@code keepMeta}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then calls {@link JDBCExecutionContext#getContextName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor, boolean)"})
  public void testShutdownWithMonitorKeepMeta_whenTrue_thenCallsGetContextName() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    doNothing().when(context).close();

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new LoggingProgressMonitor(), true);

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)} with {@code monitor},
   * {@code keepMeta}.
   *
   * <ul>
   *   <li>When {@link VoidProgressMonitor} (default constructor).
   *   <li>Then calls {@link JDBCExecutionContext#getContextName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor, boolean)"})
  public void testShutdownWithMonitorKeepMeta_whenVoidProgressMonitor_thenCallsGetContextName() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    when(context.getContextName()).thenReturn("Context Name");
    doNothing().when(context).close();

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new VoidProgressMonitor(), false);

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor)} with {@code monitor}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCExecutionContext#getContextName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor)"})
  public void testShutdownWithMonitor_thenCallsGetContextName() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    doNothing().when(context).close();
    when(context.getContextName()).thenReturn("Context Name");

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new LoggingProgressMonitor());

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor)} with {@code monitor}.
   *
   * <ul>
   *   <li>When {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)} with {@link
   *       JDBCDataSourceProvider#log}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor)"})
  public void testShutdownWithMonitor_whenLoggingProgressMonitorWithLog() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    doNothing().when(context).close();
    when(context.getContextName()).thenReturn("Context Name");

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new LoggingProgressMonitor(JDBCDataSourceProvider.log));

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor)} with {@code monitor}.
   *
   * <ul>
   *   <li>When {@link VoidProgressMonitor} (default constructor).
   *   <li>Then calls {@link JDBCExecutionContext#getContextName()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#shutdown(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.shutdown(DBRProgressMonitor)"})
  public void testShutdownWithMonitor_whenVoidProgressMonitor_thenCallsGetContextName() {
    // Arrange
    JDBCExecutionContext context = mock(JDBCExecutionContext.class);
    doNothing().when(context).close();
    when(context.getContextName()).thenReturn("Context Name");

    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(null);
    jdbcRemoteInstance.addContext(context);

    // Act
    jdbcRemoteInstance.shutdown(new VoidProgressMonitor());

    // Assert
    verify(context).getContextName();
    verify(context).close();
  }

  /**
   * Test {@link JDBCRemoteInstance#addContext(JDBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#addContext(JDBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCRemoteInstance.addContext(JDBCExecutionContext)"})
  public void testAddContext_givenDBPDriverIsThreadSafeDriverReturnTrue_thenArrayLengthIsOne() {
    // Arrange
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(mock(JDBCDataSource.class));

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext context = new JDBCExecutionContext(instance, "Purpose");

    // Act
    jdbcRemoteInstance.addContext(context);

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    JDBCExecutionContext[] allContexts = jdbcRemoteInstance.getAllContexts();
    assertEquals(1, allContexts.length);
    assertSame(context, allContexts[0]);
  }

  /**
   * Test {@link JDBCRemoteInstance#removeContext(JDBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDriver()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#removeContext(JDBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCRemoteInstance.removeContext(JDBCExecutionContext)"})
  public void testRemoveContext_givenDBPDriverIsThreadSafeDriverReturnTrue_thenCallsGetDriver() {
    // Arrange
    JDBCRemoteInstance jdbcRemoteInstance = new JDBCRemoteInstance(mock(JDBCDataSource.class));

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);

    // Act
    boolean actualRemoveContextResult =
        jdbcRemoteInstance.removeContext(new JDBCExecutionContext(instance, "Purpose"));

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertFalse(actualRemoveContextResult);
  }

  /**
   * Test {@link JDBCRemoteInstance#removeContext(JDBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCRemoteInstance#removeContext(JDBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCRemoteInstance.removeContext(JDBCExecutionContext)"})
  public void testRemoveContext_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new JDBCRemoteInstance(mock(JDBCDataSource.class)).removeContext(null));
  }
}
