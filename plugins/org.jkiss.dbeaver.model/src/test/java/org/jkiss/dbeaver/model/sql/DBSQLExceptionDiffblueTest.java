package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSQLExceptionDiffblueTest {
  /**
   * Test {@link DBSQLException#DBSQLException(String, Throwable, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link DBSQLException#DBSQLException(String, Throwable,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBSQLException.<init>(String, Throwable, DBCExecutionContext)"})
  public void testNewDBSQLException() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    DBSQLException actualDbsqlException = new DBSQLException("Sql Query", cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertEquals("Sql Query", actualDbsqlException.getSqlQuery());
    assertNull(actualDbsqlException.getLocalizedMessage());
    assertNull(actualDbsqlException.getMessage());
    assertEquals(0, actualDbsqlException.getSuppressed().length);
    assertFalse(actualDbsqlException.hasMessage());
    assertSame(cause, actualDbsqlException.getCause());
    assertSame(executionContext, actualDbsqlException.getExecutionContext());
  }

  /**
   * Test {@link DBSQLException#getSqlQuery()}.
   *
   * <p>Method under test: {@link DBSQLException#getSqlQuery()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBSQLException.getSqlQuery()"})
  public void testGetSqlQuery() {
    // Arrange
    DBSQLException dbsqlException =
        new DBSQLException("Sql Query", new Throwable(), mock(DBCExecutionContext.class));

    // Act and Assert
    assertEquals("Sql Query", dbsqlException.getSqlQuery());
  }
}
