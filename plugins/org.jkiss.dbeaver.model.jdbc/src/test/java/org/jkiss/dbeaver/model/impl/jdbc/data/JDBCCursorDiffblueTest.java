package org.jkiss.dbeaver.model.impl.jdbc.data;

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
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCFactoryDefault;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCCursorDiffblueTest {
  /**
   * Test {@link JDBCCursor#JDBCCursor(JDBCSession, ResultSet, String)}.
   *
   * <ul>
   *   <li>Then RawValue return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCursor#JDBCCursor(JDBCSession, ResultSet, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCursor.<init>(JDBCSession, ResultSet, String)"})
  public void testNewJDBCCursor_thenRawValueReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCCursor actualJdbcCursor =
        new JDBCCursor(session, resultSet, "The characteristics of someone or something");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualJdbcCursor.getRawValue() instanceof JDBCResultSetImpl);
    assertNull(actualJdbcCursor.getCursorName());
    assertFalse(actualJdbcCursor.isModified());
    assertFalse(actualJdbcCursor.isNull());
  }

  /**
   * Test {@link JDBCCursor#release()}.
   *
   * <p>Method under test: {@link JDBCCursor#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCursor.release()"})
  public void testRelease() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCCursor jdbcCursor =
        new JDBCCursor(session, resultSet, "The characteristics of someone or something");

    // Act
    jdbcCursor.release();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertNull(jdbcCursor.getRawValue());
  }

  /**
   * Test {@link JDBCCursor#release()}.
   *
   * <p>Method under test: {@link JDBCCursor#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCursor.release()"})
  public void testRelease2() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);
    JDBCCursor jdbcCursor =
        new JDBCCursor(session, null, "The characteristics of someone or something");

    // Act
    jdbcCursor.release();

    // Assert
    verify(session).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertNull(jdbcCursor.getRawValue());
  }

  /**
   * Test {@link JDBCCursor#release()}.
   *
   * <p>Method under test: {@link JDBCCursor#release()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCursor.release()"})
  public void testRelease3() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCCursor jdbcCursor =
        new JDBCCursor(session, null, "The characteristics of someone or something");
    jdbcCursor.setCloseResultsOnRelease(false);

    // Act
    jdbcCursor.release();

    // Assert
    verify(session).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertNull(jdbcCursor.getRawValue());
  }

  /**
   * Test {@link JDBCCursor#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Cursor Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCursor#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCursor.toString()"})
  public void testToString_thenReturnCursorName() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCCursor jdbcCursor =
        new JDBCCursor(session, resultSet, "The characteristics of someone or something");
    jdbcCursor.setCursorName("Cursor Name");

    // Act
    String actualToStringResult = jdbcCursor.toString();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertEquals("Cursor Name", actualToStringResult);
  }
}
