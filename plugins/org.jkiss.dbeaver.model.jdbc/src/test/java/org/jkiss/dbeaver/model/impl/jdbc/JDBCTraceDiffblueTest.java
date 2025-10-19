package org.jkiss.dbeaver.model.impl.jdbc;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCTraceDiffblueTest {
  /**
   * Test {@link JDBCTrace#dumpResultSetRow(ResultSet)}.
   *
   * <p>Method under test: {@link JDBCTrace#dumpResultSetRow(ResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTrace.dumpResultSetRow(ResultSet)"})
  public void testDumpResultSetRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new RuntimeException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCTrace.dumpResultSetRow(dbResult);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCTrace#dumpResultSetOpen(ResultSet)}.
   *
   * <p>Method under test: {@link JDBCTrace#dumpResultSetOpen(ResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTrace.dumpResultSetOpen(ResultSet)"})
  public void testDumpResultSetOpen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new RuntimeException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCTrace.dumpResultSetOpen(dbResult);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }
}
