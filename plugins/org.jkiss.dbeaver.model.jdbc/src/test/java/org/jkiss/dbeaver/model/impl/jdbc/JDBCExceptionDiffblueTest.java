package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.DataTruncation;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCExceptionDiffblueTest {
  /**
   * Test {@link JDBCException#JDBCException(SQLException, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code SQL Error [01004]: Data truncation}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCException#JDBCException(SQLException, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCException.<init>(SQLException, DBCExecutionContext)"})
  public void testNewJDBCException_thenReturnLocalizedMessageIsSqlError01004DataTruncation() {
    // Arrange
    DataTruncation cause = new DataTruncation(1, true, true, 3, 3);

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext executionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act
    JDBCException actualJdbcException = new JDBCException(cause, executionContext);

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertTrue(actualJdbcException.getExecutionContext() instanceof JDBCExecutionContext);
    assertEquals("SQL Error [01004]: Data truncation", actualJdbcException.getLocalizedMessage());
    assertEquals("SQL Error [01004]: Data truncation", actualJdbcException.getMessage());
    assertSame(cause, actualJdbcException.getCause());
  }

  /**
   * Test {@link JDBCException#JDBCException(SQLException, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@link SQLException#SQLException()}.
   *   <li>Then return LocalizedMessage is {@code SQL Error}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCException#JDBCException(SQLException, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCException.<init>(SQLException, DBCExecutionContext)"})
  public void testNewJDBCException_whenSQLException_thenReturnLocalizedMessageIsSqlError() {
    // Arrange
    SQLException cause = new SQLException();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getName()).thenReturn("Name");
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    JDBCRemoteInstance instance = new JDBCRemoteInstance(dataSource);
    JDBCExecutionContext executionContext = new JDBCExecutionContext(instance, "Purpose");

    // Act
    JDBCException actualJdbcException = new JDBCException(cause, executionContext);

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(dataSource).getContainer();
    verify(dataSource).getName();
    assertTrue(actualJdbcException.getExecutionContext() instanceof JDBCExecutionContext);
    assertEquals("SQL Error", actualJdbcException.getLocalizedMessage());
    assertEquals("SQL Error", actualJdbcException.getMessage());
    assertSame(cause, actualJdbcException.getCause());
  }

  /**
   * Test {@link JDBCException#getCause()}.
   *
   * <ul>
   *   <li>Then return {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCException#getCause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLException JDBCException.getCause()"})
  public void testGetCause_thenReturnSQLException() {
    // Arrange
    JDBCExecutionContext executionContext = mock(JDBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);
    SQLException cause = new SQLException();

    JDBCException jdbcException = new JDBCException(cause, executionContext);

    // Act
    SQLException actualCause = jdbcException.getCause();

    // Assert
    verify(executionContext).getDataSource();
    assertSame(cause, actualCause);
  }
}
