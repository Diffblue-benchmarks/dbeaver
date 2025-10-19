package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyByte;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyShort;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialRef;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCArrayImpl;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCContentBytes;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCSQLXMLImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCResultSetImplDiffblueTest {
  /**
   * Test {@link JDBCResultSetImpl#makeResultSet(JDBCSession, JDBCStatement, ResultSet, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#makeResultSet(JDBCSession, JDBCStatement,
   * ResultSet, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCResultSet JDBCResultSetImpl.makeResultSet(JDBCSession, JDBCStatement, ResultSet, boolean)"
  })
  public void testMakeResultSet_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);
    JDBCStatement statement = mock(JDBCStatement.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement2 =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement2);

    // Act
    JDBCResultSet actualMakeResultSetResult =
        JDBCResultSetImpl.makeResultSet(session, statement, original, true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualMakeResultSetResult instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCResultSetImpl#JDBCResultSetImpl(JDBCSession, JDBCStatement, ResultSet,
   * boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#JDBCResultSetImpl(JDBCSession, JDBCStatement,
   * ResultSet, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCResultSetImpl.<init>(JDBCSession, JDBCStatement, ResultSet, boolean)"
  })
  public void testNewJDBCResultSetImpl_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCStatement statement = mock(JDBCStatement.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement2 =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement2);

    // Act
    new JDBCResultSetImpl(session, statement, original, true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCResultSetImpl#setMaxRows(long)}
   *   <li>{@link JDBCResultSetImpl#getOriginal()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ResultSet JDBCResultSetImpl.getOriginal()",
    "void JDBCResultSetImpl.setMaxRows(long)"
  })
  public void testGettersAndSetters() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCStatement statement = mock(JDBCStatement.class);
    JDBCSession session2 = mock(JDBCSession.class);
    JDBCCallableStatementImpl statement2 =
        new JDBCCallableStatementImpl(
            mock(JDBCSession.class), mock(JDBCObjectSupplier.class), "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(session2, statement2);

    JDBCResultSetImpl jdbcResultSetImpl = new JDBCResultSetImpl(session, statement, original, true);

    // Act
    jdbcResultSetImpl.setMaxRows(1L);
    ResultSet actualOriginal = jdbcResultSetImpl.getOriginal();

    // Assert
    assertTrue(actualOriginal instanceof JDBCResultSetCallable);
    assertSame(original, actualOriginal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getSession()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getSession()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCSession JDBCResultSetImpl.getSession()"})
  public void testGetSession() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.getSession();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getSourceStatement()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getSourceStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCStatement JDBCResultSetImpl.getSourceStatement()"})
  public void testGetSourceStatement() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.getSourceStatement();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getStatement()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCStatement JDBCResultSetImpl.getStatement()"})
  public void testGetStatement() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.getStatement();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getAttributeValue(int)} with {@code index}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAttributeValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getAttributeValue(int)"})
  public void testGetAttributeValueWithIndex_thenThrowIllegalStateException() throws DBCException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getAttributeValue(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getAttributeValue(String)} with {@code name}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getAttributeValue(String)"})
  public void testGetAttributeValueWithName() throws DBCException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getAttributeValue("Name"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getAttributeValue(String)} with {@code name}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getAttributeValue(String)"})
  public void testGetAttributeValueWithName_thenCallsGetExecutionContext()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getExecutionContext()).thenThrow(new IllegalStateException());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(session, mock(JDBCStatement.class), original, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getAttributeValue("Name"));
    verify(callableStatement).getObject("Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    verify(session).getExecutionContext();
  }

  /**
   * Test {@link JDBCResultSetImpl#getAttributeValue(String)} with {@code name}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getAttributeValue(String)"})
  public void testGetAttributeValueWithName_thenReturnObject() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Object actualAttributeValue = jdbcResultSetImpl.getAttributeValue("Name");

    // Assert
    verify(callableStatement).getObject("Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualAttributeValue);
  }

  /**
   * Test {@link JDBCResultSetImpl#getAttributeValue(String)} with {@code name}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAttributeValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getAttributeValue(String)"})
  public void testGetAttributeValueWithName_thenReturnObject2() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    Object actualAttributeValue = jdbcResultSetImpl.getAttributeValue("Name");

    // Assert
    verify(callableStatement).getObject("Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualAttributeValue);
  }

  /**
   * Test {@link JDBCResultSetImpl#nextRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#nextRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.nextRow()"})
  public void testNextRow() throws DBCException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertFalse(jdbcResultSetImpl.nextRow());
  }

  /**
   * Test {@link JDBCResultSetImpl#nextRow()}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#nextRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.nextRow()"})
  public void testNextRow_thenCallsGetParameterCount() throws SQLException, DBCException {
    // Arrange
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
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(null, mock(JDBCStatement.class), original, true);
    jdbcResultSetImpl.setMaxRows(0L);

    // Act
    boolean actualNextRowResult = jdbcResultSetImpl.nextRow();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualNextRowResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#moveTo(int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.moveTo(int)"})
  public void testMoveTo_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualMoveToResult = jdbcResultSetImpl.moveTo(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualMoveToResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#moveTo(int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.moveTo(int)"})
  public void testMoveTo_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue2()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualMoveToResult = jdbcResultSetImpl.moveTo(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualMoveToResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#moveTo(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#moveTo(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.moveTo(int)"})
  public void testMoveTo_thenReturnFalse() throws DBCException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertFalse(jdbcResultSetImpl.moveTo(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getMeta()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getMeta()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSetMetaData JDBCResultSetImpl.getMeta()"})
  public void testGetMeta_thenThrowIllegalStateException() throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session2 = mock(JDBCSession.class);
    when(session2.getDataSource()).thenReturn(jdbcDataSource2);

    JDBCDataSource jdbcDataSource3 = mock(JDBCDataSource.class);
    when(jdbcDataSource3.getJdbcFactory()).thenThrow(new IllegalStateException());

    JDBCSession session3 = mock(JDBCSession.class);
    when(session3.getDataSource()).thenReturn(jdbcDataSource3);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(session3, mock(JDBCStatement.class), original, true);

    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(session2, mock(JDBCStatement.class), original2, true);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(session, mock(JDBCStatement.class), original3, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getMeta());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(session2).getDataSource();
    verify(session3).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    verify(jdbcDataSource2).getJdbcFactory();
    verify(jdbcDataSource3).getJdbcFactory();
  }

  /**
   * Test {@link JDBCResultSetImpl#getResultSetName()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getResultSetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getResultSetName()"})
  public void testGetResultSetName() throws DBCException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertNull(jdbcResultSetImpl.getResultSetName());
  }

  /**
   * Test {@link JDBCResultSetImpl#getResultSetName()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getResultSetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getResultSetName()"})
  public void testGetResultSetName2() throws DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCStatement statement = mock(JDBCStatement.class);
    JDBCSession session2 = mock(JDBCSession.class);
    JDBCStatement statement2 = mock(JDBCStatement.class);
    JDBCResultSetImpl original =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    JDBCResultSetImpl original2 = new JDBCResultSetImpl(session2, statement2, original, true);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(session, statement, original2, true);

    // Act and Assert
    assertNull(jdbcResultSetImpl.getResultSetName());
  }

  /**
   * Test {@link JDBCResultSetImpl#getResultSetName()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getResultSetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getResultSetName()"})
  public void testGetResultSetName_thenCallsGetParameterMetaData()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    String actualResultSetName = jdbcResultSetImpl.getResultSetName();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualResultSetName);
  }

  /**
   * Test {@link JDBCResultSetImpl#getResultSetName()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getResultSetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getResultSetName()"})
  public void testGetResultSetName_thenCallsGetParameterMetaData2()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    String actualResultSetName = jdbcResultSetImpl.getResultSetName();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualResultSetName);
  }

  /**
   * Test {@link JDBCResultSetImpl#getResultSetName()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getResultSetName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getResultSetName()"})
  public void testGetResultSetName_thenCallsGetParameterMetaData3()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    String actualResultSetName = jdbcResultSetImpl.getResultSetName();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualResultSetName);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFeature(String)}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getFeature(String)"})
  public void testGetFeature_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
    // Arrange
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
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Object actualFeature = jdbcResultSetImpl.getFeature("jdbc");

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue((Boolean) actualFeature);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFeature(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getFeature(String)"})
  public void testGetFeature_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Object actualFeature = jdbcResultSetImpl.getFeature("Name");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualFeature);
  }

  /**
   * Test {@link JDBCResultSetImpl#getMetaData()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ResultSetMetaData JDBCResultSetImpl.getMetaData()"})
  public void testGetMetaData_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session2 = mock(JDBCSession.class);
    when(session2.getDataSource()).thenReturn(jdbcDataSource2);

    JDBCDataSource jdbcDataSource3 = mock(JDBCDataSource.class);
    when(jdbcDataSource3.getJdbcFactory()).thenThrow(new IllegalStateException());

    JDBCSession session3 = mock(JDBCSession.class);
    when(session3.getDataSource()).thenReturn(jdbcDataSource3);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(session3, mock(JDBCStatement.class), original, true);

    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(session2, mock(JDBCStatement.class), original2, true);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(session, mock(JDBCStatement.class), original3, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getMetaData());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(session2).getDataSource();
    verify(session3).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    verify(jdbcDataSource2).getJdbcFactory();
    verify(jdbcDataSource3).getJdbcFactory();
  }

  /**
   * Test {@link JDBCResultSetImpl#next()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.next()"})
  public void testNext() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertFalse(jdbcResultSetImpl.next());
  }

  /**
   * Test {@link JDBCResultSetImpl#next()}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.next()"})
  public void testNext_thenCallsGetParameterCount() throws SQLException {
    // Arrange
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
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(null, mock(JDBCStatement.class), original, true);
    jdbcResultSetImpl.setMaxRows(0L);

    // Act
    boolean actualNextResult = jdbcResultSetImpl.next();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualNextResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#close()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.close()"})
  public void testClose_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    try (JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(
            mock(JDBCSession.class), mock(JDBCStatement.class), original, true)) {}

    // Act and Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#close()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.close()"})
  public void testClose_thenCallsGetParameterMetaData2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    try (JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(
            mock(JDBCSession.class), mock(JDBCStatement.class), original2, true)) {}

    // Act and Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#wasNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.wasNull()"})
  public void testWasNull_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualWasNullResult = jdbcResultSetImpl.wasNull();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualWasNullResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#wasNull()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.wasNull()"})
  public void testWasNull_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualWasNullResult = jdbcResultSetImpl.wasNull();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualWasNullResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#wasNull()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.wasNull()"})
  public void testWasNull_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.wasNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#wasNull()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.wasNull()"})
  public void testWasNull_thenThrowIllegalStateException2() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCStatement statement = mock(JDBCStatement.class);
    JDBCResultSetImpl original =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    JDBCResultSetImpl jdbcResultSetImpl = new JDBCResultSetImpl(session, statement, original, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.wasNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#getString(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getString(int)"})
  public void testGetStringWithColumnIndex_thenReturnString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(anyInt())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    String actualString = jdbcResultSetImpl.getString(1);

    // Assert
    verify(callableStatement).getString(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCResultSetImpl#getString(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getString(int)"})
  public void testGetStringWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getString(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getString(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getString(String)"})
  public void testGetStringWithColumnLabel_thenReturnString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    String actualString = jdbcResultSetImpl.getString("Column Label");

    // Assert
    verify(callableStatement).getString("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCResultSetImpl#getString(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getString(String)"})
  public void testGetStringWithColumnLabel_thenReturnString2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    String actualString = jdbcResultSetImpl.getString("Column Label");

    // Assert
    verify(callableStatement).getString("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCResultSetImpl#getString(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getString(String)"})
  public void testGetStringWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getString("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(int)"})
  public void testGetBooleanWithColumnIndex_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(anyInt())).thenReturn(false);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualBoolean = jdbcResultSetImpl.getBoolean(1);

    // Assert
    verify(callableStatement).getBoolean(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(int)"})
  public void testGetBooleanWithColumnIndex_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(anyInt())).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualBoolean = jdbcResultSetImpl.getBoolean(1);

    // Assert
    verify(callableStatement).getBoolean(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(int)"})
  public void testGetBooleanWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBoolean(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(String)"})
  public void testGetBooleanWithColumnLabel_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualBoolean = jdbcResultSetImpl.getBoolean("Column Label");

    // Assert
    verify(callableStatement).getBoolean("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(String)"})
  public void testGetBooleanWithColumnLabel_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualBoolean = jdbcResultSetImpl.getBoolean("Column Label");

    // Assert
    verify(callableStatement).getBoolean("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(String)"})
  public void testGetBooleanWithColumnLabel_thenReturnTrue2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualBoolean = jdbcResultSetImpl.getBoolean("Column Label");

    // Assert
    verify(callableStatement).getBoolean("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBoolean(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.getBoolean(String)"})
  public void testGetBooleanWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBoolean("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getByte(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getByte(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetImpl.getByte(int)"})
  public void testGetByteWithColumnIndex_thenReturnA() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(anyInt())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    byte actualByte = jdbcResultSetImpl.getByte(1);

    // Assert
    verify(callableStatement).getByte(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCResultSetImpl#getByte(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getByte(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetImpl.getByte(int)"})
  public void testGetByteWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getByte(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getByte(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getByte(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetImpl.getByte(String)"})
  public void testGetByteWithColumnLabel_thenReturnA() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(Mockito.<String>any())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    byte actualByte = jdbcResultSetImpl.getByte("Column Label");

    // Assert
    verify(callableStatement).getByte("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCResultSetImpl#getByte(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getByte(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetImpl.getByte(String)"})
  public void testGetByteWithColumnLabel_thenReturnA2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(Mockito.<String>any())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    byte actualByte = jdbcResultSetImpl.getByte("Column Label");

    // Assert
    verify(callableStatement).getByte("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCResultSetImpl#getByte(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getByte(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetImpl.getByte(String)"})
  public void testGetByteWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getByte("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getShort(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getShort(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetImpl.getShort(int)"})
  public void testGetShortWithColumnIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getShort(anyInt())).thenReturn((short) 1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    short actualShort = jdbcResultSetImpl.getShort(1);

    // Assert
    verify(callableStatement).getShort(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCResultSetImpl#getShort(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getShort(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetImpl.getShort(int)"})
  public void testGetShortWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getShort(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getShort(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getShort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetImpl.getShort(String)"})
  public void testGetShortWithColumnLabel_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getShort(Mockito.<String>any())).thenReturn((short) 1);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    short actualShort = jdbcResultSetImpl.getShort("Column Label");

    // Assert
    verify(callableStatement).getShort("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCResultSetImpl#getShort(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getShort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetImpl.getShort(String)"})
  public void testGetShortWithColumnLabel_thenReturnOne2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getShort(Mockito.<String>any())).thenReturn((short) 1);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    short actualShort = jdbcResultSetImpl.getShort("Column Label");

    // Assert
    verify(callableStatement).getShort("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCResultSetImpl#getShort(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getShort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetImpl.getShort(String)"})
  public void testGetShortWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getShort("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getInt(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getInt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getInt(int)"})
  public void testGetIntWithColumnIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(anyInt())).thenReturn(1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualInt = jdbcResultSetImpl.getInt(1);

    // Assert
    verify(callableStatement).getInt(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCResultSetImpl#getInt(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getInt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getInt(int)"})
  public void testGetIntWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getInt(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getInt(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getInt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getInt(String)"})
  public void testGetIntWithColumnLabel_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualInt = jdbcResultSetImpl.getInt("Column Label");

    // Assert
    verify(callableStatement).getInt("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCResultSetImpl#getInt(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getInt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getInt(String)"})
  public void testGetIntWithColumnLabel_thenReturnOne2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualInt = jdbcResultSetImpl.getInt("Column Label");

    // Assert
    verify(callableStatement).getInt("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCResultSetImpl#getInt(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getInt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getInt(String)"})
  public void testGetIntWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getInt("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getLong(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getLong(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetImpl.getLong(int)"})
  public void testGetLongWithColumnIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getLong(anyInt())).thenReturn(1L);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    long actualLong = jdbcResultSetImpl.getLong(1);

    // Assert
    verify(callableStatement).getLong(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCResultSetImpl#getLong(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getLong(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetImpl.getLong(int)"})
  public void testGetLongWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getLong(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getLong(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getLong(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetImpl.getLong(String)"})
  public void testGetLongWithColumnLabel_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getLong(Mockito.<String>any())).thenReturn(1L);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    long actualLong = jdbcResultSetImpl.getLong("Column Label");

    // Assert
    verify(callableStatement).getLong("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCResultSetImpl#getLong(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getLong(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetImpl.getLong(String)"})
  public void testGetLongWithColumnLabel_thenReturnOne2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getLong(Mockito.<String>any())).thenReturn(1L);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    long actualLong = jdbcResultSetImpl.getLong("Column Label");

    // Assert
    verify(callableStatement).getLong("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCResultSetImpl#getLong(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getLong(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetImpl.getLong(String)"})
  public void testGetLongWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getLong("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getFloat(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFloat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetImpl.getFloat(int)"})
  public void testGetFloatWithColumnIndex_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(anyInt())).thenReturn(10.0f);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    float actualFloat = jdbcResultSetImpl.getFloat(1);

    // Assert
    verify(callableStatement).getFloat(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFloat(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFloat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetImpl.getFloat(int)"})
  public void testGetFloatWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getFloat(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getFloat(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFloat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetImpl.getFloat(String)"})
  public void testGetFloatWithColumnLabel_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(Mockito.<String>any())).thenReturn(10.0f);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    float actualFloat = jdbcResultSetImpl.getFloat("Column Label");

    // Assert
    verify(callableStatement).getFloat("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFloat(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFloat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetImpl.getFloat(String)"})
  public void testGetFloatWithColumnLabel_thenReturnTen2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(Mockito.<String>any())).thenReturn(10.0f);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    float actualFloat = jdbcResultSetImpl.getFloat("Column Label");

    // Assert
    verify(callableStatement).getFloat("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFloat(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFloat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetImpl.getFloat(String)"})
  public void testGetFloatWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getFloat("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getDouble(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDouble(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetImpl.getDouble(int)"})
  public void testGetDoubleWithColumnIndex_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(anyInt())).thenReturn(10.0d);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    double actualDouble = jdbcResultSetImpl.getDouble(1);

    // Assert
    verify(callableStatement).getDouble(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCResultSetImpl#getDouble(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDouble(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetImpl.getDouble(int)"})
  public void testGetDoubleWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getDouble(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getDouble(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDouble(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetImpl.getDouble(String)"})
  public void testGetDoubleWithColumnLabel_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(Mockito.<String>any())).thenReturn(10.0d);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    double actualDouble = jdbcResultSetImpl.getDouble("Column Label");

    // Assert
    verify(callableStatement).getDouble("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCResultSetImpl#getDouble(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDouble(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetImpl.getDouble(String)"})
  public void testGetDoubleWithColumnLabel_thenReturnTen2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(Mockito.<String>any())).thenReturn(10.0d);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    double actualDouble = jdbcResultSetImpl.getDouble("Column Label");

    // Assert
    verify(callableStatement).getDouble("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCResultSetImpl#getDouble(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDouble(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetImpl.getDouble(String)"})
  public void testGetDoubleWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getDouble("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(int, int)} with {@code columnIndex}, {@code scale}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithColumnIndexScale_thenReturnBigDecimalWith23()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal(1, 3);

    // Assert
    verify(callableStatement).getBigDecimal(0, 3);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(int, int)} with {@code columnIndex}, {@code scale}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithColumnIndexScale_thenReturnBigDecimalWith232()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal(1, 1);

    // Assert
    verify(callableStatement).getBigDecimal(0, 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(int, int)} with {@code columnIndex}, {@code scale}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithColumnIndexScale_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBigDecimal(1, 3));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(int, int)} with {@code columnIndex}, {@code scale}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithColumnIndexScale_whenMinusOne_thenReturnBigDecimalWith23()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal(1, -1);

    // Assert
    verify(callableStatement).getBigDecimal(0, -1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(int, int)} with {@code columnIndex}, {@code scale}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithColumnIndexScale_whenZero_thenReturnBigDecimalWith23()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal(1, 0);

    // Assert
    verify(callableStatement).getBigDecimal(0, 0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(int)"})
  public void testGetBigDecimalWithColumnIndex_thenReturnBigDecimalWith23() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal(1);

    // Assert
    verify(callableStatement).getBigDecimal(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(String, int)} with {@code columnLabel}, {@code
   * scale}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(String, int)"})
  public void testGetBigDecimalWithColumnLabelScale_thenReturnBigDecimalWith23()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(Mockito.<String>any())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal("2.3", 1);

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(String, int)} with {@code columnLabel}, {@code
   * scale}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(String, int)"})
  public void testGetBigDecimalWithColumnLabelScale_thenReturnBigDecimalWith232()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(Mockito.<String>any())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal("2.3", 1);

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(String, int)} with {@code columnLabel}, {@code
   * scale}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(String, int)"})
  public void testGetBigDecimalWithColumnLabelScale_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBigDecimal("2.3", 1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(String)"})
  public void testGetBigDecimalWithColumnLabel_thenReturnBigDecimalWith23() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(Mockito.<String>any())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal("2.3");

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBigDecimal(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBigDecimal(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetImpl.getBigDecimal(String)"})
  public void testGetBigDecimalWithColumnLabel_thenReturnBigDecimalWith232() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(Mockito.<String>any())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetImpl.getBigDecimal("2.3");

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetImpl#isBeforeFirst()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isBeforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isBeforeFirst()"})
  public void testIsBeforeFirst_thenReturnFalse() throws SQLException {
    // Arrange
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

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.absolute(1);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsBeforeFirstResult = jdbcResultSetImpl.isBeforeFirst();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualIsBeforeFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isBeforeFirst()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isBeforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isBeforeFirst()"})
  public void testIsBeforeFirst_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualIsBeforeFirstResult = jdbcResultSetImpl.isBeforeFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualIsBeforeFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isBeforeFirst()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isBeforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isBeforeFirst()"})
  public void testIsBeforeFirst_thenReturnTrue2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsBeforeFirstResult = jdbcResultSetImpl.isBeforeFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualIsBeforeFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isAfterLast()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isAfterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isAfterLast()"})
  public void testIsAfterLast_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
    // Arrange
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

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.absolute(1);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsAfterLastResult = jdbcResultSetImpl.isAfterLast();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualIsAfterLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isAfterLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isAfterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isAfterLast()"})
  public void testIsAfterLast_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualIsAfterLastResult = jdbcResultSetImpl.isAfterLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsAfterLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isAfterLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isAfterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isAfterLast()"})
  public void testIsAfterLast_thenReturnFalse2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsAfterLastResult = jdbcResultSetImpl.isAfterLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsAfterLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isFirst()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isFirst()"})
  public void testIsFirst_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
    // Arrange
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

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.relative(1);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsFirstResult = jdbcResultSetImpl.isFirst();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualIsFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isFirst()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isFirst()"})
  public void testIsFirst_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualIsFirstResult = jdbcResultSetImpl.isFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isFirst()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isFirst()"})
  public void testIsFirst_thenReturnFalse2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsFirstResult = jdbcResultSetImpl.isFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isLast()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isLast()"})
  public void testIsLast_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
    // Arrange
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

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.relative(1);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsLastResult = jdbcResultSetImpl.isLast();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualIsLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isLast()"})
  public void testIsLast_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualIsLastResult = jdbcResultSetImpl.isLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isLast()"})
  public void testIsLast_thenReturnFalse2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsLastResult = jdbcResultSetImpl.isLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBytes(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBytes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetImpl.getBytes(int)"})
  public void testGetBytesWithColumnIndex_thenReturnAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(anyInt())).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    byte[] actualBytes = jdbcResultSetImpl.getBytes(1);

    // Assert
    verify(callableStatement).getBytes(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBytes(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBytes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetImpl.getBytes(int)"})
  public void testGetBytesWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBytes(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBytes(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetImpl.getBytes(String)"})
  public void testGetBytesWithColumnLabel_thenReturnAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(Mockito.<String>any()))
        .thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    byte[] actualBytes = jdbcResultSetImpl.getBytes("Column Label");

    // Assert
    verify(callableStatement).getBytes("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBytes(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetImpl.getBytes(String)"})
  public void testGetBytesWithColumnLabel_thenReturnAxaxaxaxBytesIsUtf82()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(Mockito.<String>any()))
        .thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    byte[] actualBytes = jdbcResultSetImpl.getBytes("Column Label");

    // Assert
    verify(callableStatement).getBytes("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBytes(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetImpl.getBytes(String)"})
  public void testGetBytesWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBytes("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(int, Calendar)} with {@code columnIndex}, {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(int, Calendar)"})
  public void testGetDateWithColumnIndexCal_thenCallsGetDate() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.getDate(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(int)"})
  public void testGetDateWithColumnIndex_thenCallsGetDate() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.getDate(1);

    // Assert
    verify(callableStatement).getDate(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(int)"})
  public void testGetDateWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getDate(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(String, Calendar)} with {@code columnLabel}, {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(String, Calendar)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(String, Calendar)"})
  public void testGetDateWithColumnLabelCal_thenCallsGetDate() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.getDate("Column Label", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(eq("Column Label"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(String, Calendar)} with {@code columnLabel}, {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(String, Calendar)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(String, Calendar)"})
  public void testGetDateWithColumnLabelCal_thenCallsGetDate2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.getDate("Column Label", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(eq("Column Label"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(String)"})
  public void testGetDateWithColumnLabel_thenCallsGetDate() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.getDate("Column Label");

    // Assert
    verify(callableStatement).getDate("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(String)"})
  public void testGetDateWithColumnLabel_thenCallsGetDate2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.getDate("Column Label");

    // Assert
    verify(callableStatement).getDate("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getDate(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getDate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetImpl.getDate(String)"})
  public void testGetDateWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getDate("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getTime(int, Calendar)} with {@code columnIndex}, {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTime(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetImpl.getTime(int, Calendar)"})
  public void testGetTimeWithColumnIndexCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getTime(anyInt())).thenReturn(null);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Time actualTime = jdbcResultSetImpl.getTime(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTime(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTime(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetImpl.getTime(int)"})
  public void testGetTimeWithColumnIndex_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getTime(anyInt())).thenReturn(null);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Time actualTime = jdbcResultSetImpl.getTime(1);

    // Assert
    verify(callableStatement).getTime(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTime(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetImpl.getTime(int)"})
  public void testGetTimeWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getTime(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getTime(String, Calendar)} with {@code columnLabel}, {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTime(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetImpl.getTime(String, Calendar)"})
  public void testGetTimeWithColumnLabelCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTime(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    Time actualTime = jdbcResultSetImpl.getTime("Column Label", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTime(eq("Column Label"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTime(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetImpl.getTime(String)"})
  public void testGetTimeWithColumnLabel_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTime(Mockito.<String>any())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    Time actualTime = jdbcResultSetImpl.getTime("Column Label");

    // Assert
    verify(callableStatement).getTime("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTime(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetImpl.getTime(String)"})
  public void testGetTimeWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getTime("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getTimestamp(int, Calendar)} with {@code columnIndex}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTimestamp(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetImpl.getTimestamp(int, Calendar)"})
  public void testGetTimestampWithColumnIndexCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getTimestamp(anyInt())).thenReturn(null);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Timestamp actualTimestamp = jdbcResultSetImpl.getTimestamp(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTimestamp(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTimestamp(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTimestamp(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetImpl.getTimestamp(int)"})
  public void testGetTimestampWithColumnIndex_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getTimestamp(anyInt())).thenReturn(null);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Timestamp actualTimestamp = jdbcResultSetImpl.getTimestamp(1);

    // Assert
    verify(callableStatement).getTimestamp(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTimestamp(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTimestamp(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetImpl.getTimestamp(int)"})
  public void testGetTimestampWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getTimestamp(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getTimestamp(String, Calendar)} with {@code columnLabel}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTimestamp(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetImpl.getTimestamp(String, Calendar)"})
  public void testGetTimestampWithColumnLabelCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTimestamp(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    Timestamp actualTimestamp =
        jdbcResultSetImpl.getTimestamp("Column Label", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTimestamp(eq("Column Label"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTimestamp(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTimestamp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetImpl.getTimestamp(String)"})
  public void testGetTimestampWithColumnLabel_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTimestamp(Mockito.<String>any())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    Timestamp actualTimestamp = jdbcResultSetImpl.getTimestamp("Column Label");

    // Assert
    verify(callableStatement).getTimestamp("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetImpl#getTimestamp(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getTimestamp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetImpl.getTimestamp(String)"})
  public void testGetTimestampWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getTimestamp("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getURL(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getURL(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCResultSetImpl.getURL(int)"})
  public void testGetURLWithColumnIndex() throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(anyInt()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    URL actualURL = jdbcResultSetImpl.getURL(1);

    // Assert
    verify(callableStatement).getURL(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    String expectedToStringResult =
        String.join(
            "",
            "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
                .toString()
                .concat(File.separator));
    assertEquals(expectedToStringResult, actualURL.toString());
  }

  /**
   * Test {@link JDBCResultSetImpl#getURL(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCResultSetImpl.getURL(String)"})
  public void testGetURLWithColumnLabel() throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    URL actualURL = jdbcResultSetImpl.getURL("https://example.org/example");

    // Assert
    verify(callableStatement).getURL("https://example.org/example");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    String expectedToStringResult =
        String.join(
            "",
            "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
                .toString()
                .concat(File.separator));
    assertEquals(expectedToStringResult, actualURL.toString());
  }

  /**
   * Test {@link JDBCResultSetImpl#getURL(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCResultSetImpl.getURL(String)"})
  public void testGetURLWithColumnLabel2() throws MalformedURLException, SQLException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    URL actualURL = jdbcResultSetImpl.getURL("https://example.org/example");

    // Assert
    verify(callableStatement).getURL("https://example.org/example");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    String expectedToStringResult =
        String.join(
            "",
            "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
                .toString()
                .concat(File.separator));
    assertEquals(expectedToStringResult, actualURL.toString());
  }

  /**
   * Test {@link JDBCResultSetImpl#updateRef(int, Ref)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateRef(int, Ref)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateRef(int, Ref)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateRef(int, Ref)"})
  public void testUpdateRefWithColumnIndexX_thenCallsUpdateRef() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateRef(anyInt(), Mockito.<Ref>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateRef(1, mock(SerialRef.class));

    // Assert
    verify(original).updateRef(eq(1), isA(Ref.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateRef(String, Ref)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateRef(String, Ref)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateRef(String, Ref)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateRef(String, Ref)"})
  public void testUpdateRefWithColumnLabelX_thenCallsUpdateRef() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateRef(Mockito.<String>any(), Mockito.<Ref>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateRef("Column Label", mock(SerialRef.class));

    // Assert
    verify(original).updateRef(eq("Column Label"), isA(Ref.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBlob(int, InputStream, long)} with {@code columnIndex},
   * {@code inputStream}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBlob(int, InputStream, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBlob(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBlob(int, InputStream, long)"})
  public void testUpdateBlobWithColumnIndexInputStreamLength_thenCallsUpdateBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBlob(anyInt(), Mockito.<InputStream>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBlob(1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(original).updateBlob(eq(1), isA(InputStream.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBlob(int, InputStream)} with {@code columnIndex}, {@code
   * inputStream}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBlob(int, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBlob(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBlob(int, InputStream)"})
  public void testUpdateBlobWithColumnIndexInputStream_thenCallsUpdateBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBlob(anyInt(), Mockito.<InputStream>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBlob(1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateBlob(eq(1), isA(InputStream.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBlob(int, Blob)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBlob(int, Blob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBlob(int, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBlob(int, Blob)"})
  public void testUpdateBlobWithColumnIndexX_thenCallsUpdateBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBlob(anyInt(), Mockito.<Blob>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBlob(1, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateBlob(eq(1), isA(Blob.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBlob(String, InputStream, long)} with {@code columnLabel},
   * {@code inputStream}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBlob(String, InputStream, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBlob(String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBlob(String, InputStream, long)"})
  public void testUpdateBlobWithColumnLabelInputStreamLength_thenCallsUpdateBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateBlob(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBlob(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(original).updateBlob(eq("Column Label"), isA(InputStream.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBlob(String, InputStream)} with {@code columnLabel}, {@code
   * inputStream}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBlob(String, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBlob(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBlob(String, InputStream)"})
  public void testUpdateBlobWithColumnLabelInputStream_thenCallsUpdateBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBlob(Mockito.<String>any(), Mockito.<InputStream>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBlob(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateBlob(eq("Column Label"), isA(InputStream.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBlob(String, Blob)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBlob(String, Blob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBlob(String, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBlob(String, Blob)"})
  public void testUpdateBlobWithColumnLabelX_thenCallsUpdateBlob()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBlob(Mockito.<String>any(), Mockito.<Blob>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBlob("Column Label", new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateBlob(eq("Column Label"), isA(Blob.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateClob(int, Reader, long)} with {@code columnIndex}, {@code
   * reader}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateClob(int, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateClob(int, Reader, long)"})
  public void testUpdateClobWithColumnIndexReaderLength_thenCallsUpdateClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateClob(anyInt(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateClob(1, new StringReader("foo"), 3L);

    // Assert
    verify(original).updateClob(eq(1), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateClob(int, Reader)} with {@code columnIndex}, {@code
   * reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateClob(int, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateClob(int, Reader)"})
  public void testUpdateClobWithColumnIndexReader_thenCallsUpdateClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateClob(anyInt(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateClob(1, new StringReader("foo"));

    // Assert
    verify(original).updateClob(eq(1), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateClob(int, Clob)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateClob(int, Clob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateClob(int, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateClob(int, Clob)"})
  public void testUpdateClobWithColumnIndexX_thenCallsUpdateClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateClob(anyInt(), Mockito.<Clob>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateClob(1, new SerialClob("AZAZ".toCharArray()));

    // Assert
    verify(original).updateClob(eq(1), isA(Clob.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateClob(String, Reader, long)} with {@code columnLabel},
   * {@code reader}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateClob(String, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateClob(String, Reader, long)"})
  public void testUpdateClobWithColumnLabelReaderLength_thenCallsUpdateClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateClob(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateClob("Column Label", new StringReader("foo"), 3L);

    // Assert
    verify(original).updateClob(eq("Column Label"), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateClob(String, Reader)} with {@code columnLabel}, {@code
   * reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateClob(String, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateClob(String, Reader)"})
  public void testUpdateClobWithColumnLabelReader_thenCallsUpdateClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateClob(Mockito.<String>any(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateClob("Column Label", new StringReader("foo"));

    // Assert
    verify(original).updateClob(eq("Column Label"), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateClob(String, Clob)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateClob(String, Clob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateClob(String, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateClob(String, Clob)"})
  public void testUpdateClobWithColumnLabelX_thenCallsUpdateClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateClob(Mockito.<String>any(), Mockito.<Clob>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateClob("Column Label", new SerialClob("AZAZ".toCharArray()));

    // Assert
    verify(original).updateClob(eq("Column Label"), isA(Clob.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateArray(int, Array)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateArray(int, Array)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateArray(int, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateArray(int, Array)"})
  public void testUpdateArrayWithColumnIndexX_thenCallsUpdateArray() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateArray(anyInt(), Mockito.<Array>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    Object[] items = new Object[] {"Items"};

    // Act
    jdbcResultSetImpl.updateArray(1, new JDBCArrayImpl("Type Name", 1, items));

    // Assert
    verify(original).updateArray(eq(1), isA(Array.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateArray(String, Array)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateArray(String, Array)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateArray(String, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateArray(String, Array)"})
  public void testUpdateArrayWithColumnLabelX_thenCallsUpdateArray() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateArray(Mockito.<String>any(), Mockito.<Array>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    Object[] items = new Object[] {"Items"};

    // Act
    jdbcResultSetImpl.updateArray("Column Label", new JDBCArrayImpl("Type Name", 1, items));

    // Assert
    verify(original).updateArray(eq("Column Label"), isA(Array.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateRowId(int, RowId)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateRowId(int, RowId)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateRowId(int, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateRowId(int, RowId)"})
  public void testUpdateRowIdWithColumnIndexX_thenCallsUpdateRowId() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateRowId(anyInt(), Mockito.<RowId>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateRowId(1, null);

    // Assert
    verify(original).updateRowId(eq(1), (RowId) isNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#updateRowId(String, RowId)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateRowId(String, RowId)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateRowId(String, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateRowId(String, RowId)"})
  public void testUpdateRowIdWithColumnLabelX_thenCallsUpdateRowId() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateRowId(Mockito.<String>any(), Mockito.<RowId>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateRowId("Column Label", null);

    // Assert
    verify(original).updateRowId(eq("Column Label"), (RowId) isNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#getHoldability()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getHoldability()"})
  public void testGetHoldability_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualHoldability = jdbcResultSetImpl.getHoldability();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualHoldability);
  }

  /**
   * Test {@link JDBCResultSetImpl#getHoldability()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getHoldability()"})
  public void testGetHoldability_thenReturnOne2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualHoldability = jdbcResultSetImpl.getHoldability();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualHoldability);
  }

  /**
   * Test {@link JDBCResultSetImpl#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isClosed()"})
  public void testIsClosed_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualIsClosedResult = jdbcResultSetImpl.isClosed();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsClosedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isClosed()"})
  public void testIsClosed_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualIsClosedResult = jdbcResultSetImpl.isClosed();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsClosedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNString(int, String)} with {@code columnIndex}, {@code
   * nString}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNString(int, String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNString(int, String)"})
  public void testUpdateNStringWithColumnIndexNString_thenCallsUpdateNString() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNString(anyInt(), Mockito.<String>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNString(1, "N String");

    // Assert
    verify(original).updateNString(1, "N String");
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNString(String, String)} with {@code columnLabel}, {@code
   * nString}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNString(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNString(String, String)"})
  public void testUpdateNStringWithColumnLabelNString_thenCallsUpdateNString() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNString(Mockito.<String>any(), Mockito.<String>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNString("Column Label", "N String");

    // Assert
    verify(original).updateNString("Column Label", "N String");
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNClob(int, NClob)} with {@code columnIndex}, {@code nClob}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNClob(int, NClob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNClob(int, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNClob(int, NClob)"})
  public void testUpdateNClobWithColumnIndexNClob_thenCallsUpdateNClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNClob(anyInt(), Mockito.<NClob>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNClob(1, mock(NClob.class));

    // Assert
    verify(original).updateNClob(eq(1), isA(NClob.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNClob(int, Reader, long)} with {@code columnIndex}, {@code
   * reader}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNClob(int, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNClob(int, Reader, long)"})
  public void testUpdateNClobWithColumnIndexReaderLength_thenCallsUpdateNClob()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNClob(anyInt(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNClob(1, new StringReader("foo"), 3L);

    // Assert
    verify(original).updateNClob(eq(1), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNClob(int, Reader)} with {@code columnIndex}, {@code
   * reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNClob(int, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNClob(int, Reader)"})
  public void testUpdateNClobWithColumnIndexReader_thenCallsUpdateNClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNClob(anyInt(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNClob(1, new StringReader("foo"));

    // Assert
    verify(original).updateNClob(eq(1), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNClob(String, NClob)} with {@code columnLabel}, {@code
   * nClob}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNClob(String, NClob)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNClob(String, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNClob(String, NClob)"})
  public void testUpdateNClobWithColumnLabelNClob_thenCallsUpdateNClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNClob(Mockito.<String>any(), Mockito.<NClob>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNClob("Column Label", mock(NClob.class));

    // Assert
    verify(original).updateNClob(eq("Column Label"), isA(NClob.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNClob(String, Reader, long)} with {@code columnLabel},
   * {@code reader}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNClob(String, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNClob(String, Reader, long)"})
  public void testUpdateNClobWithColumnLabelReaderLength_thenCallsUpdateNClob()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNClob(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNClob("Column Label", new StringReader("foo"), 3L);

    // Assert
    verify(original).updateNClob(eq("Column Label"), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNClob(String, Reader)} with {@code columnLabel}, {@code
   * reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNClob(String, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNClob(String, Reader)"})
  public void testUpdateNClobWithColumnLabelReader_thenCallsUpdateNClob() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNClob(Mockito.<String>any(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNClob("Column Label", new StringReader("foo"));

    // Assert
    verify(original).updateNClob(eq("Column Label"), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#getNClob(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getNClob(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCResultSetImpl.getNClob(int)"})
  public void testGetNClobWithColumnIndex_thenCallsGetNClob() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(anyInt())).thenReturn(mock(NClob.class));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.getNClob(1);

    // Assert
    verify(callableStatement).getNClob(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getNClob(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getNClob(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCResultSetImpl.getNClob(String)"})
  public void testGetNClobWithColumnLabel_thenCallsGetNClob() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(Mockito.<String>any())).thenReturn(mock(NClob.class));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.getNClob("Column Label");

    // Assert
    verify(callableStatement).getNClob("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getNClob(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getNClob(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCResultSetImpl.getNClob(String)"})
  public void testGetNClobWithColumnLabel_thenCallsGetNClob2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(Mockito.<String>any())).thenReturn(mock(NClob.class));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.getNClob("Column Label");

    // Assert
    verify(callableStatement).getNClob("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getSQLXML(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getSQLXML(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCResultSetImpl.getSQLXML(int)"})
  public void testGetSQLXMLWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    when(callableStatement.getSQLXML(anyInt())).thenReturn(jdbcsqlxmlImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    SQLXML actualSQLXML = jdbcResultSetImpl.getSQLXML(1);

    // Assert
    verify(callableStatement).getSQLXML(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCResultSetImpl#getSQLXML(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getSQLXML(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCResultSetImpl.getSQLXML(String)"})
  public void testGetSQLXMLWithColumnLabel() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    when(callableStatement.getSQLXML(Mockito.<String>any())).thenReturn(jdbcsqlxmlImpl);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    SQLXML actualSQLXML = jdbcResultSetImpl.getSQLXML("Column Label");

    // Assert
    verify(callableStatement).getSQLXML("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCResultSetImpl#getSQLXML(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getSQLXML(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCResultSetImpl.getSQLXML(String)"})
  public void testGetSQLXMLWithColumnLabel2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    when(callableStatement.getSQLXML(Mockito.<String>any())).thenReturn(jdbcsqlxmlImpl);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    SQLXML actualSQLXML = jdbcResultSetImpl.getSQLXML("Column Label");

    // Assert
    verify(callableStatement).getSQLXML("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateSQLXML(int, SQLXML)} with {@code columnIndex}, {@code
   * xmlObject}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateSQLXML(int, SQLXML)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateSQLXML(int, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateSQLXML(int, SQLXML)"})
  public void testUpdateSQLXMLWithColumnIndexXmlObject_thenCallsUpdateSQLXML() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateSQLXML(anyInt(), Mockito.<SQLXML>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateSQLXML(1, new JDBCSQLXMLImpl(new JDBCContentBytes(null)));

    // Assert
    verify(original).updateSQLXML(eq(1), isA(SQLXML.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateSQLXML(String, SQLXML)} with {@code columnLabel}, {@code
   * xmlObject}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateSQLXML(String, SQLXML)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateSQLXML(String, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateSQLXML(String, SQLXML)"})
  public void testUpdateSQLXMLWithColumnLabelXmlObject_thenCallsUpdateSQLXML() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateSQLXML(Mockito.<String>any(), Mockito.<SQLXML>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateSQLXML("Column Label", new JDBCSQLXMLImpl(new JDBCContentBytes(null)));

    // Assert
    verify(original).updateSQLXML(eq("Column Label"), isA(SQLXML.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#getNString(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getNString(int)"})
  public void testGetNStringWithColumnIndex_thenReturnNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(anyInt())).thenReturn("N String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    String actualNString = jdbcResultSetImpl.getNString(1);

    // Assert
    verify(callableStatement).getNString(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCResultSetImpl#getNString(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getNString(String)"})
  public void testGetNStringWithColumnLabel_thenReturnNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(Mockito.<String>any())).thenReturn("N String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    String actualNString = jdbcResultSetImpl.getNString("Column Label");

    // Assert
    verify(callableStatement).getNString("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCResultSetImpl#getNString(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getNString(String)"})
  public void testGetNStringWithColumnLabel_thenReturnNString2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(Mockito.<String>any())).thenReturn("N String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    String actualNString = jdbcResultSetImpl.getNString("Column Label");

    // Assert
    verify(callableStatement).getNString("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCResultSetImpl#getNCharacterStream(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetImpl.getNCharacterStream(int)"})
  public void testGetNCharacterStreamWithColumnIndex_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getNCharacterStream(anyInt())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Reader actualNCharacterStream = jdbcResultSetImpl.getNCharacterStream(1);

    // Assert
    verify(callableStatement).getNCharacterStream(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualNCharacterStream.ready());
    assertSame(stringReader, actualNCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetImpl#getNCharacterStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetImpl.getNCharacterStream(String)"})
  public void testGetNCharacterStreamWithColumnLabel_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getNCharacterStream(Mockito.<String>any())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Reader actualNCharacterStream = jdbcResultSetImpl.getNCharacterStream("Column Label");

    // Assert
    verify(callableStatement).getNCharacterStream("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualNCharacterStream.ready());
    assertSame(stringReader, actualNCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetImpl#getNCharacterStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getNCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetImpl.getNCharacterStream(String)"})
  public void testGetNCharacterStreamWithColumnLabel_thenReturnReady2()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getNCharacterStream(Mockito.<String>any())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Reader actualNCharacterStream = jdbcResultSetImpl.getNCharacterStream("Column Label");

    // Assert
    verify(callableStatement).getNCharacterStream("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualNCharacterStream.ready());
    assertSame(stringReader, actualNCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNCharacterStream(int, Reader, long)} with {@code
   * columnIndex}, {@code x}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNCharacterStream(int, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNCharacterStream(int, Reader, long)"})
  public void testUpdateNCharacterStreamWithColumnIndexXLength_thenCallsUpdateNCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNCharacterStream(anyInt(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNCharacterStream(1, new StringReader("foo"), 3L);

    // Assert
    verify(original).updateNCharacterStream(eq(1), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNCharacterStream(int, Reader)} with {@code columnIndex},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNCharacterStream(int, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNCharacterStream(int, Reader)"})
  public void testUpdateNCharacterStreamWithColumnIndexX_thenCallsUpdateNCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNCharacterStream(anyInt(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNCharacterStream(1, new StringReader("foo"));

    // Assert
    verify(original).updateNCharacterStream(eq(1), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNCharacterStream(String, Reader, long)} with {@code
   * columnLabel}, {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNCharacterStream(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNCharacterStream(String, Reader, long)"})
  public void testUpdateNCharacterStreamWithColumnLabelReaderLength() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateNCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNCharacterStream("Column Label", new StringReader("foo"), 3L);

    // Assert
    verify(original).updateNCharacterStream(eq("Column Label"), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNCharacterStream(String, Reader)} with {@code columnLabel},
   * {@code reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNCharacterStream(String, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNCharacterStream(String, Reader)"})
  public void testUpdateNCharacterStreamWithColumnLabelReader_thenCallsUpdateNCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNCharacterStream(Mockito.<String>any(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNCharacterStream("Column Label", new StringReader("foo"));

    // Assert
    verify(original).updateNCharacterStream(eq("Column Label"), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#getAsciiStream(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAsciiStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetImpl.getAsciiStream(int)"})
  public void testGetAsciiStreamWithColumnIndex_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getAsciiStream(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getAsciiStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getAsciiStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetImpl.getAsciiStream(String)"})
  public void testGetAsciiStreamWithColumnLabel_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> jdbcResultSetImpl.getAsciiStream("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getUnicodeStream(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getUnicodeStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetImpl.getUnicodeStream(int)"})
  public void testGetUnicodeStreamWithColumnIndex_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getUnicodeStream(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getUnicodeStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getUnicodeStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetImpl.getUnicodeStream(String)"})
  public void testGetUnicodeStreamWithColumnLabel_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> jdbcResultSetImpl.getUnicodeStream("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBinaryStream(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBinaryStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetImpl.getBinaryStream(int)"})
  public void testGetBinaryStreamWithColumnIndex_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getBinaryStream(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getBinaryStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBinaryStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetImpl.getBinaryStream(String)"})
  public void testGetBinaryStreamWithColumnLabel_thenThrowIllegalStateException()
      throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> jdbcResultSetImpl.getBinaryStream("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#getWarnings()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning JDBCResultSetImpl.getWarnings()"})
  public void testGetWarnings_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnNull()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    SQLWarning actualWarnings = jdbcResultSetImpl.getWarnings();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualWarnings);
  }

  /**
   * Test {@link JDBCResultSetImpl#getWarnings()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning JDBCResultSetImpl.getWarnings()"})
  public void testGetWarnings_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnNull2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    SQLWarning actualWarnings = jdbcResultSetImpl.getWarnings();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualWarnings);
  }

  /**
   * Test {@link JDBCResultSetImpl#getWarnings()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning JDBCResultSetImpl.getWarnings()"})
  public void testGetWarnings_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getWarnings());
  }

  /**
   * Test {@link JDBCResultSetImpl#getWarnings()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning JDBCResultSetImpl.getWarnings()"})
  public void testGetWarnings_thenThrowIllegalStateException2() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCStatement statement = mock(JDBCStatement.class);
    JDBCResultSetImpl original =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    JDBCResultSetImpl jdbcResultSetImpl = new JDBCResultSetImpl(session, statement, original, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getWarnings());
  }

  /**
   * Test {@link JDBCResultSetImpl#clearWarnings()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#clearWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.clearWarnings()"})
  public void testClearWarnings() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.clearWarnings();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#clearWarnings()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#clearWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.clearWarnings()"})
  public void testClearWarnings_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.clearWarnings();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getCursorName()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getCursorName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getCursorName()"})
  public void testGetCursorName() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertNull(jdbcResultSetImpl.getCursorName());
  }

  /**
   * Test {@link JDBCResultSetImpl#getCursorName()}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getCursorName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getCursorName()"})
  public void testGetCursorName2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    String actualCursorName = jdbcResultSetImpl.getCursorName();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualCursorName);
  }

  /**
   * Test {@link JDBCResultSetImpl#getCursorName()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getCursorName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetImpl.getCursorName()"})
  public void testGetCursorName_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    String actualCursorName = jdbcResultSetImpl.getCursorName();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualCursorName);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(int, Map)} with {@code columnIndex}, {@code map}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(int, Map)"})
  public void testGetObjectWithColumnIndexMap_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Object actualObject = jdbcResultSetImpl.getObject(1, new HashMap<>());

    // Assert
    verify(callableStatement).getObject(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(int, Class)} with {@code columnIndex}, {@code type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(int, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(int, Class)"})
  public void testGetObjectWithColumnIndexType_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt(), eq(Object.class))).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcResultSetImpl.getObject(1, type);

    // Assert
    verify(callableStatement).getObject(eq(0), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(int)"})
  public void testGetObjectWithColumnIndex_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Object actualObject = jdbcResultSetImpl.getObject(1);

    // Assert
    verify(callableStatement).getObject(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(int)"})
  public void testGetObjectWithColumnIndex_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getObject(1));
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(String, Class)} with {@code columnLabel}, {@code type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(String, Class)"})
  public void testGetObjectWithColumnLabelType_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any(), eq(Object.class))).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcResultSetImpl.getObject("Column Label", type);

    // Assert
    verify(callableStatement).getObject(eq("Column Label"), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(String, Class)} with {@code columnLabel}, {@code type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(String, Class)"})
  public void testGetObjectWithColumnLabelType_thenReturnObject2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any(), eq(Object.class))).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcResultSetImpl.getObject("Column Label", type);

    // Assert
    verify(callableStatement).getObject(eq("Column Label"), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(String)"})
  public void testGetObjectWithColumnLabel_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Object actualObject = jdbcResultSetImpl.getObject("Column Label");

    // Assert
    verify(callableStatement).getObject("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(String)"})
  public void testGetObjectWithColumnLabel_thenReturnObject2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Object actualObject = jdbcResultSetImpl.getObject("Column Label");

    // Assert
    verify(callableStatement).getObject("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetImpl#getObject(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.getObject(String)"})
  public void testGetObjectWithColumnLabel_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), null, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.getObject("Column Label"));
  }

  /**
   * Test {@link JDBCResultSetImpl#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.unwrap(Class)"})
  public void testUnwrap_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnNull()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    Class<Object> iface = Object.class;

    // Act
    Object actualUnwrapResult = jdbcResultSetImpl.unwrap(iface);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualUnwrapResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetImpl.unwrap(Class)"})
  public void testUnwrap_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnNull2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    Class<Object> iface = Object.class;

    // Act
    Object actualUnwrapResult = jdbcResultSetImpl.unwrap(iface);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualUnwrapResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcResultSetImpl.isWrapperFor(iface);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_thenReturnFalse2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcResultSetImpl.isWrapperFor(iface);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#getRef(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialRef#SerialRef(Ref)} with ref is {@link SerialRef}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getRef(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCResultSetImpl.getRef(int)"})
  public void testGetRefWithColumnIndex_thenReturnSerialRefWithRefIsSerialRef()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    SerialRef ref = mock(SerialRef.class);
    when(ref.getBaseTypeName()).thenReturn("Base Type Name");
    SerialRef serialRef = new SerialRef(ref);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getRef(anyInt())).thenReturn(serialRef);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Ref actualRef = jdbcResultSetImpl.getRef(1);

    // Assert
    verify(callableStatement).getRef(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(ref, atLeast(1)).getBaseTypeName();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(serialRef, actualRef);
  }

  /**
   * Test {@link JDBCResultSetImpl#getRef(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link SerialRef#SerialRef(Ref)} with ref is {@link SerialRef}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getRef(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCResultSetImpl.getRef(String)"})
  public void testGetRefWithColumnLabel_thenReturnSerialRefWithRefIsSerialRef()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    SerialRef ref = mock(SerialRef.class);
    when(ref.getBaseTypeName()).thenReturn("Base Type Name");
    SerialRef serialRef = new SerialRef(ref);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getRef(Mockito.<String>any())).thenReturn(serialRef);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original3, true);

    // Act
    Ref actualRef = jdbcResultSetImpl.getRef("Column Label");

    // Assert
    verify(callableStatement).getRef("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(ref, atLeast(1)).getBaseTypeName();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialRef, actualRef);
  }

  /**
   * Test {@link JDBCResultSetImpl#findColumn(String)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#findColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.findColumn(String)"})
  public void testFindColumn_thenReturnMinusOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualFindColumnResult = jdbcResultSetImpl.findColumn("Column Label");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(-1, actualFindColumnResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#findColumn(String)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#findColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.findColumn(String)"})
  public void testFindColumn_thenReturnMinusOne2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualFindColumnResult = jdbcResultSetImpl.findColumn("Column Label");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(-1, actualFindColumnResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#getCharacterStream(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetImpl.getCharacterStream(int)"})
  public void testGetCharacterStreamWithColumnIndex_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getCharacterStream(anyInt())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Reader actualCharacterStream = jdbcResultSetImpl.getCharacterStream(1);

    // Assert
    verify(callableStatement).getCharacterStream(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualCharacterStream.ready());
    assertSame(stringReader, actualCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetImpl#getCharacterStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetImpl.getCharacterStream(String)"})
  public void testGetCharacterStreamWithColumnLabel_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getCharacterStream(Mockito.<String>any())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Reader actualCharacterStream = jdbcResultSetImpl.getCharacterStream("Column Label");

    // Assert
    verify(callableStatement).getCharacterStream("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualCharacterStream.ready());
    assertSame(stringReader, actualCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetImpl#getCharacterStream(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetImpl.getCharacterStream(String)"})
  public void testGetCharacterStreamWithColumnLabel_thenReturnReady2()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getCharacterStream(Mockito.<String>any())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Reader actualCharacterStream = jdbcResultSetImpl.getCharacterStream("Column Label");

    // Assert
    verify(callableStatement).getCharacterStream("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualCharacterStream.ready());
    assertSame(stringReader, actualCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetImpl#beforeFirst()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#beforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.beforeFirst()"})
  public void testBeforeFirst_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.beforeFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#beforeFirst()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#beforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.beforeFirst()"})
  public void testBeforeFirst_thenCallsGetParameterMetaData2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.beforeFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#afterLast()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#afterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.afterLast()"})
  public void testAfterLast_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.afterLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#afterLast()}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#afterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.afterLast()"})
  public void testAfterLast_thenCallsGetParameterMetaData2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.afterLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#first()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#first()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.first()"})
  public void testFirst_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualFirstResult = jdbcResultSetImpl.first();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#first()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#first()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.first()"})
  public void testFirst_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualFirstResult = jdbcResultSetImpl.first();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualFirstResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#last()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#last()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.last()"})
  public void testLast_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualLastResult = jdbcResultSetImpl.last();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#last()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#last()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.last()"})
  public void testLast_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualLastResult = jdbcResultSetImpl.last();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualLastResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#getRow()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getRow()"})
  public void testGetRow_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnMinusOne()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualRow = jdbcResultSetImpl.getRow();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(-1, actualRow);
  }

  /**
   * Test {@link JDBCResultSetImpl#getRow()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getRow()"})
  public void testGetRow_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnMinusOne2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualRow = jdbcResultSetImpl.getRow();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(-1, actualRow);
  }

  /**
   * Test {@link JDBCResultSetImpl#absolute(int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#absolute(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.absolute(int)"})
  public void testAbsolute_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualAbsoluteResult = jdbcResultSetImpl.absolute(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualAbsoluteResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#absolute(int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#absolute(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.absolute(int)"})
  public void testAbsolute_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualAbsoluteResult = jdbcResultSetImpl.absolute(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualAbsoluteResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#relative(int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#relative(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.relative(int)"})
  public void testRelative_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualRelativeResult = jdbcResultSetImpl.relative(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualRelativeResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#relative(int)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#relative(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.relative(int)"})
  public void testRelative_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualRelativeResult = jdbcResultSetImpl.relative(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualRelativeResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#previous()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#previous()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.previous()"})
  public void testPrevious_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualPreviousResult = jdbcResultSetImpl.previous();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualPreviousResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#previous()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#previous()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.previous()"})
  public void testPrevious_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnTrue2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualPreviousResult = jdbcResultSetImpl.previous();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualPreviousResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#setFetchDirection(int)}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#setFetchDirection(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.setFetchDirection(int)"})
  public void testSetFetchDirection_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.setFetchDirection(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#setFetchDirection(int)}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#setFetchDirection(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.setFetchDirection(int)"})
  public void testSetFetchDirection_thenCallsGetParameterMetaData2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.setFetchDirection(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getFetchDirection()}.
   *
   * <ul>
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFetchDirection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getFetchDirection()"})
  public void testGetFetchDirection_thenReturnOneThousand() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualFetchDirection = jdbcResultSetImpl.getFetchDirection();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1000, actualFetchDirection);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFetchDirection()}.
   *
   * <ul>
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFetchDirection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getFetchDirection()"})
  public void testGetFetchDirection_thenReturnOneThousand2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualFetchDirection = jdbcResultSetImpl.getFetchDirection();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1000, actualFetchDirection);
  }

  /**
   * Test {@link JDBCResultSetImpl#setFetchSize(int)}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#setFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.setFetchSize(int)"})
  public void testSetFetchSize_thenCallsGetParameterMetaData() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    jdbcResultSetImpl.setFetchSize(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#setFetchSize(int)}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getParameterMetaData()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#setFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.setFetchSize(int)"})
  public void testSetFetchSize_thenCallsGetParameterMetaData2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.setFetchSize(1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetImpl#getFetchSize()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFetchSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getFetchSize()"})
  public void testGetFetchSize_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnOne()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualFetchSize = jdbcResultSetImpl.getFetchSize();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualFetchSize);
  }

  /**
   * Test {@link JDBCResultSetImpl#getFetchSize()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getFetchSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getFetchSize()"})
  public void testGetFetchSize_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnOne2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualFetchSize = jdbcResultSetImpl.getFetchSize();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualFetchSize);
  }

  /**
   * Test {@link JDBCResultSetImpl#getType()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code 1005}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getType()"})
  public void testGetType_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturn1005()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualType = jdbcResultSetImpl.getType();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1005, actualType);
  }

  /**
   * Test {@link JDBCResultSetImpl#getType()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code 1005}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getType()"})
  public void testGetType_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturn10052()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualType = jdbcResultSetImpl.getType();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1005, actualType);
  }

  /**
   * Test {@link JDBCResultSetImpl#getConcurrency()}.
   *
   * <ul>
   *   <li>Then return {@code 1007}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getConcurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getConcurrency()"})
  public void testGetConcurrency_thenReturn1007() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    int actualConcurrency = jdbcResultSetImpl.getConcurrency();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1007, actualConcurrency);
  }

  /**
   * Test {@link JDBCResultSetImpl#getConcurrency()}.
   *
   * <ul>
   *   <li>Then return {@code 1007}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getConcurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetImpl.getConcurrency()"})
  public void testGetConcurrency_thenReturn10072() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    int actualConcurrency = jdbcResultSetImpl.getConcurrency();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1007, actualConcurrency);
  }

  /**
   * Test {@link JDBCResultSetImpl#rowUpdated()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#rowUpdated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.rowUpdated()"})
  public void testRowUpdated_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualRowUpdatedResult = jdbcResultSetImpl.rowUpdated();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowUpdatedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#rowUpdated()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#rowUpdated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.rowUpdated()"})
  public void testRowUpdated_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualRowUpdatedResult = jdbcResultSetImpl.rowUpdated();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowUpdatedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#rowInserted()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#rowInserted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.rowInserted()"})
  public void testRowInserted_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualRowInsertedResult = jdbcResultSetImpl.rowInserted();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowInsertedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#rowInserted()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#rowInserted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.rowInserted()"})
  public void testRowInserted_thenReturnFalse2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualRowInsertedResult = jdbcResultSetImpl.rowInserted();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowInsertedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#rowDeleted()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#rowDeleted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.rowDeleted()"})
  public void testRowDeleted_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    boolean actualRowDeletedResult = jdbcResultSetImpl.rowDeleted();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowDeletedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#rowDeleted()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@link
   *       JDBCDataSource}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#rowDeleted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetImpl.rowDeleted()"})
  public void testRowDeleted_givenJDBCSessionGetDataSourceReturnJDBCDataSource_thenReturnFalse2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    boolean actualRowDeletedResult = jdbcResultSetImpl.rowDeleted();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowDeletedResult);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNull(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNull(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNull(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNull(int)"})
  public void testUpdateNullWithColumnIndex_thenCallsUpdateNull() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNull(anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNull(1);

    // Assert
    verify(original).updateNull(1);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateNull(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateNull(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateNull(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateNull(String)"})
  public void testUpdateNullWithColumnLabel_thenCallsUpdateNull() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateNull(Mockito.<String>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateNull("Column Label");

    // Assert
    verify(original).updateNull("Column Label");
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBoolean(int, boolean)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBoolean(int, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBoolean(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBoolean(int, boolean)"})
  public void testUpdateBooleanWithColumnIndexX_thenCallsUpdateBoolean() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBoolean(anyInt(), anyBoolean());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBoolean(1, true);

    // Assert
    verify(original).updateBoolean(1, true);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBoolean(String, boolean)} with {@code columnLabel}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBoolean(String, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBoolean(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBoolean(String, boolean)"})
  public void testUpdateBooleanWithColumnLabelX_thenCallsUpdateBoolean() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBoolean(Mockito.<String>any(), anyBoolean());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBoolean("Column Label", true);

    // Assert
    verify(original).updateBoolean("Column Label", true);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateByte(int, byte)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateByte(int, byte)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateByte(int, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateByte(int, byte)"})
  public void testUpdateByteWithColumnIndexX_thenCallsUpdateByte() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateByte(anyInt(), anyByte());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateByte(1, (byte) 'A');

    // Assert
    verify(original).updateByte(1, (byte) 65);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateByte(String, byte)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateByte(String, byte)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateByte(String, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateByte(String, byte)"})
  public void testUpdateByteWithColumnLabelX_thenCallsUpdateByte() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateByte(Mockito.<String>any(), anyByte());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateByte("Column Label", (byte) 'A');

    // Assert
    verify(original).updateByte("Column Label", (byte) 65);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateShort(int, short)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateShort(int, short)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateShort(int, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateShort(int, short)"})
  public void testUpdateShortWithColumnIndexX_thenCallsUpdateShort() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateShort(anyInt(), anyShort());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateShort(1, (short) 1);

    // Assert
    verify(original).updateShort(1, (short) 1);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateShort(String, short)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateShort(String, short)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateShort(String, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateShort(String, short)"})
  public void testUpdateShortWithColumnLabelX_thenCallsUpdateShort() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateShort(Mockito.<String>any(), anyShort());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateShort("Column Label", (short) 1);

    // Assert
    verify(original).updateShort("Column Label", (short) 1);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateInt(int, int)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateInt(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateInt(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateInt(int, int)"})
  public void testUpdateIntWithColumnIndexX_thenCallsUpdateInt() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateInt(anyInt(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateInt(1, 2);

    // Assert
    verify(original).updateInt(1, 2);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateInt(String, int)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateInt(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateInt(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateInt(String, int)"})
  public void testUpdateIntWithColumnLabelX_thenCallsUpdateInt() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateInt(Mockito.<String>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateInt("Column Label", 2);

    // Assert
    verify(original).updateInt("Column Label", 2);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateLong(int, long)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateLong(int, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateLong(int, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateLong(int, long)"})
  public void testUpdateLongWithColumnIndexX_thenCallsUpdateLong() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateLong(anyInt(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateLong(1, 1L);

    // Assert
    verify(original).updateLong(1, 1L);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateLong(String, long)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateLong(String, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateLong(String, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateLong(String, long)"})
  public void testUpdateLongWithColumnLabelX_thenCallsUpdateLong() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateLong(Mockito.<String>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateLong("Column Label", 1L);

    // Assert
    verify(original).updateLong("Column Label", 1L);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateFloat(int, float)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateFloat(int, float)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateFloat(int, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateFloat(int, float)"})
  public void testUpdateFloatWithColumnIndexX_thenCallsUpdateFloat() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateFloat(anyInt(), anyFloat());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateFloat(1, 10.0f);

    // Assert
    verify(original).updateFloat(1, 10.0f);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateFloat(String, float)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateFloat(String, float)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateFloat(String, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateFloat(String, float)"})
  public void testUpdateFloatWithColumnLabelX_thenCallsUpdateFloat() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateFloat(Mockito.<String>any(), anyFloat());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateFloat("Column Label", 10.0f);

    // Assert
    verify(original).updateFloat("Column Label", 10.0f);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateDouble(int, double)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateDouble(int, double)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateDouble(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateDouble(int, double)"})
  public void testUpdateDoubleWithColumnIndexX_thenCallsUpdateDouble() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateDouble(anyInt(), anyDouble());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateDouble(1, 2.0d);

    // Assert
    verify(original).updateDouble(1, 2.0d);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateDouble(String, double)} with {@code columnLabel}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateDouble(String, double)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateDouble(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateDouble(String, double)"})
  public void testUpdateDoubleWithColumnLabelX_thenCallsUpdateDouble() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateDouble(Mockito.<String>any(), anyDouble());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateDouble("Column Label", 2.0d);

    // Assert
    verify(original).updateDouble("Column Label", 2.0d);
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBigDecimal(int, BigDecimal)} with {@code columnIndex},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBigDecimal(int, BigDecimal)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBigDecimal(int, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBigDecimal(int, BigDecimal)"})
  public void testUpdateBigDecimalWithColumnIndexX_thenCallsUpdateBigDecimal() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBigDecimal(anyInt(), Mockito.<BigDecimal>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBigDecimal(1, new BigDecimal("2.3"));

    // Assert
    verify(original).updateBigDecimal(eq(1), isA(BigDecimal.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBigDecimal(String, BigDecimal)} with {@code columnLabel},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBigDecimal(String, BigDecimal)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBigDecimal(String, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBigDecimal(String, BigDecimal)"})
  public void testUpdateBigDecimalWithColumnLabelX_thenCallsUpdateBigDecimal() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBigDecimal(Mockito.<String>any(), Mockito.<BigDecimal>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBigDecimal("2.3", new BigDecimal("2.3"));

    // Assert
    verify(original).updateBigDecimal(eq("2.3"), isA(BigDecimal.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateString(int, String)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateString(int, String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateString(int, String)"})
  public void testUpdateStringWithColumnIndexX_thenCallsUpdateString() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateString(anyInt(), Mockito.<String>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateString(1, "foo");

    // Assert
    verify(original).updateString(1, "foo");
  }

  /**
   * Test {@link JDBCResultSetImpl#updateString(String, String)} with {@code columnLabel}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateString(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateString(String, String)"})
  public void testUpdateStringWithColumnLabelX_thenCallsUpdateString() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateString(Mockito.<String>any(), Mockito.<String>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateString("Column Label", "foo");

    // Assert
    verify(original).updateString("Column Label", "foo");
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBytes(int, byte[])} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBytes(int, byte[])}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBytes(int, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBytes(int, byte[])"})
  public void testUpdateBytesWithColumnIndexX_thenCallsUpdateBytes()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBytes(anyInt(), Mockito.<byte[]>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBytes(1, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(original).updateBytes(eq(1), isA(byte[].class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBytes(String, byte[])} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBytes(String, byte[])}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBytes(String, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBytes(String, byte[])"})
  public void testUpdateBytesWithColumnLabelX_thenCallsUpdateBytes()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBytes(Mockito.<String>any(), Mockito.<byte[]>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBytes("Column Label", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(original).updateBytes(eq("Column Label"), isA(byte[].class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateDate(int, Date)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateDate(int, Date)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateDate(int, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateDate(int, Date)"})
  public void testUpdateDateWithColumnIndexX_thenCallsUpdateDate() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateDate(anyInt(), Mockito.<Date>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateDate(1, new Date(1L));

    // Assert
    verify(original).updateDate(eq(1), isA(Date.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateDate(String, Date)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateDate(String, Date)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateDate(String, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateDate(String, Date)"})
  public void testUpdateDateWithColumnLabelX_thenCallsUpdateDate() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateDate(Mockito.<String>any(), Mockito.<Date>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateDate("Column Label", new Date(1L));

    // Assert
    verify(original).updateDate(eq("Column Label"), isA(Date.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateTime(int, Time)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateTime(int, Time)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateTime(int, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateTime(int, Time)"})
  public void testUpdateTimeWithColumnIndexX_thenCallsUpdateTime() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateTime(anyInt(), Mockito.<Time>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateTime(1, null);

    // Assert
    verify(original).updateTime(eq(1), (Time) isNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#updateTime(String, Time)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateTime(String, Time)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateTime(String, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateTime(String, Time)"})
  public void testUpdateTimeWithColumnLabelX_thenCallsUpdateTime() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateTime(Mockito.<String>any(), Mockito.<Time>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateTime("Column Label", null);

    // Assert
    verify(original).updateTime(eq("Column Label"), (Time) isNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#updateTimestamp(int, Timestamp)} with {@code columnIndex}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateTimestamp(int, Timestamp)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateTimestamp(int, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateTimestamp(int, Timestamp)"})
  public void testUpdateTimestampWithColumnIndexX_thenCallsUpdateTimestamp() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateTimestamp(anyInt(), Mockito.<Timestamp>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateTimestamp(1, null);

    // Assert
    verify(original).updateTimestamp(eq(1), (Timestamp) isNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#updateTimestamp(String, Timestamp)} with {@code columnLabel},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateTimestamp(String, Timestamp)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateTimestamp(String, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateTimestamp(String, Timestamp)"})
  public void testUpdateTimestampWithColumnLabelX_thenCallsUpdateTimestamp() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateTimestamp(Mockito.<String>any(), Mockito.<Timestamp>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateTimestamp("Column Label", null);

    // Assert
    verify(original).updateTimestamp(eq("Column Label"), (Timestamp) isNull());
  }

  /**
   * Test {@link JDBCResultSetImpl#updateAsciiStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateAsciiStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateAsciiStream(int, InputStream, int)"})
  public void testUpdateAsciiStreamWithIntInputStreamInt_thenCallsUpdateAsciiStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateAsciiStream(anyInt(), Mockito.<InputStream>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateAsciiStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(original).updateAsciiStream(eq(1), isA(InputStream.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateAsciiStream(int, InputStream, long)} with {@code int},
   * {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateAsciiStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateAsciiStream(int, InputStream, long)"})
  public void testUpdateAsciiStreamWithIntInputStreamLong_thenCallsUpdateAsciiStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateAsciiStream(anyInt(), Mockito.<InputStream>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateAsciiStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(original).updateAsciiStream(eq(1), isA(InputStream.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateAsciiStream(int, InputStream)} with {@code int}, {@code
   * InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateAsciiStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateAsciiStream(int, InputStream)"})
  public void testUpdateAsciiStreamWithIntInputStream_thenCallsUpdateAsciiStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateAsciiStream(anyInt(), Mockito.<InputStream>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateAsciiStream(1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateAsciiStream(eq(1), isA(InputStream.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateAsciiStream(String, InputStream, int)} with {@code String},
   * {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateAsciiStream(String, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateAsciiStream(String, InputStream, int)"})
  public void testUpdateAsciiStreamWithStringInputStreamInt_thenCallsUpdateAsciiStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateAsciiStream(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(original).updateAsciiStream(eq("Column Label"), isA(InputStream.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateAsciiStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateAsciiStream(String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateAsciiStream(String, InputStream, long)"})
  public void testUpdateAsciiStreamWithStringInputStreamLong_thenCallsUpdateAsciiStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateAsciiStream(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(original).updateAsciiStream(eq("Column Label"), isA(InputStream.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateAsciiStream(String, InputStream)} with {@code String},
   * {@code InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateAsciiStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateAsciiStream(String, InputStream)"})
  public void testUpdateAsciiStreamWithStringInputStream_thenCallsUpdateAsciiStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateAsciiStream(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateAsciiStream(eq("Column Label"), isA(InputStream.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBinaryStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBinaryStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBinaryStream(int, InputStream, int)"})
  public void testUpdateBinaryStreamWithIntInputStreamInt_thenCallsUpdateBinaryStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBinaryStream(anyInt(), Mockito.<InputStream>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBinaryStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(original).updateBinaryStream(eq(1), isA(InputStream.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBinaryStream(int, InputStream, long)} with {@code int},
   * {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBinaryStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBinaryStream(int, InputStream, long)"})
  public void testUpdateBinaryStreamWithIntInputStreamLong_thenCallsUpdateBinaryStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBinaryStream(anyInt(), Mockito.<InputStream>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBinaryStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(original).updateBinaryStream(eq(1), isA(InputStream.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBinaryStream(int, InputStream)} with {@code int}, {@code
   * InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBinaryStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBinaryStream(int, InputStream)"})
  public void testUpdateBinaryStreamWithIntInputStream_thenCallsUpdateBinaryStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateBinaryStream(anyInt(), Mockito.<InputStream>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBinaryStream(1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateBinaryStream(eq(1), isA(InputStream.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBinaryStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBinaryStream(String, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBinaryStream(String, InputStream, int)"})
  public void testUpdateBinaryStreamWithStringInputStreamInt_thenCallsUpdateBinaryStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBinaryStream(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(original).updateBinaryStream(eq("Column Label"), isA(InputStream.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBinaryStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBinaryStream(String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBinaryStream(String, InputStream, long)"})
  public void testUpdateBinaryStreamWithStringInputStreamLong_thenCallsUpdateBinaryStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBinaryStream(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(original).updateBinaryStream(eq("Column Label"), isA(InputStream.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateBinaryStream(String, InputStream)} with {@code String},
   * {@code InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateBinaryStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateBinaryStream(String, InputStream)"})
  public void testUpdateBinaryStreamWithStringInputStream_thenCallsUpdateBinaryStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateBinaryStream(
        "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(original).updateBinaryStream(eq("Column Label"), isA(InputStream.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateCharacterStream(int, Reader, int)} with {@code int}, {@code
   * Reader}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateCharacterStream(int, Reader, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateCharacterStream(int, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateCharacterStream(int, Reader, int)"})
  public void testUpdateCharacterStreamWithIntReaderInt_thenCallsUpdateCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateCharacterStream(anyInt(), Mockito.<Reader>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateCharacterStream(1, new StringReader("foo"), 3);

    // Assert
    verify(original).updateCharacterStream(eq(1), isA(Reader.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateCharacterStream(int, Reader, long)} with {@code int},
   * {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateCharacterStream(int, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateCharacterStream(int, Reader, long)"})
  public void testUpdateCharacterStreamWithIntReaderLong_thenCallsUpdateCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateCharacterStream(anyInt(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateCharacterStream(1, new StringReader("foo"), 3L);

    // Assert
    verify(original).updateCharacterStream(eq(1), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateCharacterStream(int, Reader)} with {@code int}, {@code
   * Reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateCharacterStream(int, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateCharacterStream(int, Reader)"})
  public void testUpdateCharacterStreamWithIntReader_thenCallsUpdateCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateCharacterStream(anyInt(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateCharacterStream(1, new StringReader("foo"));

    // Assert
    verify(original).updateCharacterStream(eq(1), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateCharacterStream(String, Reader, int)} with {@code String},
   * {@code Reader}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateCharacterStream(String, Reader, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateCharacterStream(String, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateCharacterStream(String, Reader, int)"})
  public void testUpdateCharacterStreamWithStringReaderInt_thenCallsUpdateCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateCharacterStream("Column Label", new StringReader("foo"), 3);

    // Assert
    verify(original).updateCharacterStream(eq("Column Label"), isA(Reader.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateCharacterStream(String, Reader, long)} with {@code String},
   * {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateCharacterStream(String, Reader, long)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateCharacterStream(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateCharacterStream(String, Reader, long)"})
  public void testUpdateCharacterStreamWithStringReaderLong_thenCallsUpdateCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing()
        .when(original)
        .updateCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateCharacterStream("Column Label", new StringReader("foo"), 3L);

    // Assert
    verify(original).updateCharacterStream(eq("Column Label"), isA(Reader.class), eq(3L));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateCharacterStream(String, Reader)} with {@code String},
   * {@code Reader}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateCharacterStream(String, Reader)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateCharacterStream(String, Reader)"})
  public void testUpdateCharacterStreamWithStringReader_thenCallsUpdateCharacterStream()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateCharacterStream(Mockito.<String>any(), Mockito.<Reader>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateCharacterStream("Column Label", new StringReader("foo"));

    // Assert
    verify(original).updateCharacterStream(eq("Column Label"), isA(Reader.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateObject(int, Object, int)} with {@code columnIndex}, {@code
   * x}, {@code scaleOrLength}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateObject(int, Object, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateObject(int, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateObject(int, Object, int)"})
  public void testUpdateObjectWithColumnIndexXScaleOrLength_thenCallsUpdateObject()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateObject(anyInt(), Mockito.<Object>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateObject(1, "42", 3);

    // Assert
    verify(original).updateObject(eq(1), isA(Object.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateObject(int, Object)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateObject(int, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateObject(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateObject(int, Object)"})
  public void testUpdateObjectWithColumnIndexX_thenCallsUpdateObject() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateObject(anyInt(), Mockito.<Object>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateObject(1, "42");

    // Assert
    verify(original).updateObject(eq(1), isA(Object.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateObject(String, Object, int)} with {@code columnLabel},
   * {@code x}, {@code scaleOrLength}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateObject(String, Object, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateObject(String, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateObject(String, Object, int)"})
  public void testUpdateObjectWithColumnLabelXScaleOrLength_thenCallsUpdateObject()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateObject(Mockito.<String>any(), Mockito.<Object>any(), anyInt());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateObject("Column Label", "42", 3);

    // Assert
    verify(original).updateObject(eq("Column Label"), isA(Object.class), eq(3));
  }

  /**
   * Test {@link JDBCResultSetImpl#updateObject(String, Object)} with {@code columnLabel}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#updateObject(String, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateObject(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateObject(String, Object)"})
  public void testUpdateObjectWithColumnLabelX_thenCallsUpdateObject() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateObject(Mockito.<String>any(), Mockito.<Object>any());
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateObject("Column Label", "42");

    // Assert
    verify(original).updateObject(eq("Column Label"), isA(Object.class));
  }

  /**
   * Test {@link JDBCResultSetImpl#insertRow()}.
   *
   * <ul>
   *   <li>Given {@link JDBCResultSetCallable} {@link JDBCResultSetCallable#insertRow()} does
   *       nothing.
   *   <li>Then calls {@link JDBCResultSetCallable#insertRow()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#insertRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.insertRow()"})
  public void testInsertRow_givenJDBCResultSetCallableInsertRowDoesNothing_thenCallsInsertRow()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).insertRow();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.insertRow();

    // Assert
    verify(original).insertRow();
  }

  /**
   * Test {@link JDBCResultSetImpl#updateRow()}.
   *
   * <ul>
   *   <li>Given {@link JDBCResultSetCallable} {@link JDBCResultSetCallable#updateRow()} does
   *       nothing.
   *   <li>Then calls {@link JDBCResultSetCallable#updateRow()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#updateRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.updateRow()"})
  public void testUpdateRow_givenJDBCResultSetCallableUpdateRowDoesNothing_thenCallsUpdateRow()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).updateRow();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.updateRow();

    // Assert
    verify(original).updateRow();
  }

  /**
   * Test {@link JDBCResultSetImpl#deleteRow()}.
   *
   * <ul>
   *   <li>Given {@link JDBCResultSetCallable} {@link JDBCResultSetCallable#deleteRow()} does
   *       nothing.
   *   <li>Then calls {@link JDBCResultSetCallable#deleteRow()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#deleteRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.deleteRow()"})
  public void testDeleteRow_givenJDBCResultSetCallableDeleteRowDoesNothing_thenCallsDeleteRow()
      throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).deleteRow();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.deleteRow();

    // Assert
    verify(original).deleteRow();
  }

  /**
   * Test {@link JDBCResultSetImpl#refreshRow()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#refreshRow()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#refreshRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.refreshRow()"})
  public void testRefreshRow_thenCallsRefreshRow() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).refreshRow();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.refreshRow();

    // Assert
    verify(original).refreshRow();
  }

  /**
   * Test {@link JDBCResultSetImpl#cancelRowUpdates()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#cancelRowUpdates()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#cancelRowUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.cancelRowUpdates()"})
  public void testCancelRowUpdates_thenCallsCancelRowUpdates() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).cancelRowUpdates();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.cancelRowUpdates();

    // Assert
    verify(original).cancelRowUpdates();
  }

  /**
   * Test {@link JDBCResultSetImpl#moveToInsertRow()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#moveToInsertRow()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#moveToInsertRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.moveToInsertRow()"})
  public void testMoveToInsertRow_thenCallsMoveToInsertRow() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).moveToInsertRow();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.moveToInsertRow();

    // Assert
    verify(original).moveToInsertRow();
  }

  /**
   * Test {@link JDBCResultSetImpl#moveToCurrentRow()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCResultSetCallable#moveToCurrentRow()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#moveToCurrentRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetImpl.moveToCurrentRow()"})
  public void testMoveToCurrentRow_thenCallsMoveToCurrentRow() throws SQLException {
    // Arrange
    JDBCResultSetCallable original = mock(JDBCResultSetCallable.class);
    doNothing().when(original).moveToCurrentRow();
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    jdbcResultSetImpl.moveToCurrentRow();

    // Assert
    verify(original).moveToCurrentRow();
  }

  /**
   * Test {@link JDBCResultSetImpl#getBlob(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBlob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCResultSetImpl.getBlob(int)"})
  public void testGetBlobWithColumnIndex_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getBlob(anyInt())).thenReturn(serialBlob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Blob actualBlob = jdbcResultSetImpl.getBlob(1);

    // Assert
    verify(callableStatement).getBlob(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBlob(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBlob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCResultSetImpl.getBlob(String)"})
  public void testGetBlobWithColumnLabel_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getBlob(Mockito.<String>any())).thenReturn(serialBlob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Blob actualBlob = jdbcResultSetImpl.getBlob("Column Label");

    // Assert
    verify(callableStatement).getBlob("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCResultSetImpl#getBlob(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getBlob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCResultSetImpl.getBlob(String)"})
  public void testGetBlobWithColumnLabel_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf82()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getBlob(Mockito.<String>any())).thenReturn(serialBlob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Blob actualBlob = jdbcResultSetImpl.getBlob("Column Label");

    // Assert
    verify(callableStatement).getBlob("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCResultSetImpl#getClob(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCResultSetImpl.getClob(int)"})
  public void testGetClobWithColumnIndex_thenReturnSerialClobWithChIsAzazToCharArray()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(callableStatement.getClob(anyInt())).thenReturn(serialClob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Clob actualClob = jdbcResultSetImpl.getClob(1);

    // Assert
    verify(callableStatement).getClob(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCResultSetImpl#getClob(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCResultSetImpl.getClob(String)"})
  public void testGetClobWithColumnLabel_thenReturnSerialClobWithChIsAzazToCharArray()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(callableStatement.getClob(Mockito.<String>any())).thenReturn(serialClob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Clob actualClob = jdbcResultSetImpl.getClob("Column Label");

    // Assert
    verify(callableStatement).getClob("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCResultSetImpl#getClob(String)} with {@code columnLabel}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCResultSetImpl.getClob(String)"})
  public void testGetClobWithColumnLabel_thenReturnSerialClobWithChIsAzazToCharArray2()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(callableStatement.getClob(Mockito.<String>any())).thenReturn(serialClob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Clob actualClob = jdbcResultSetImpl.getClob("Column Label");

    // Assert
    verify(callableStatement).getClob("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCResultSetImpl#getArray(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getArray(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCResultSetImpl.getArray(int)"})
  public void testGetArrayWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);
    when(callableStatement.getArray(anyInt())).thenReturn(jdbcArrayImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    original.addColumn("Label", DBPDataKind.BOOLEAN);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Array actualArray = jdbcResultSetImpl.getArray(1);

    // Assert
    verify(callableStatement).getArray(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCResultSetImpl#getArray(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCResultSetImpl.getArray(String)"})
  public void testGetArrayWithColumnLabel() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);
    when(callableStatement.getArray(Mockito.<String>any())).thenReturn(jdbcArrayImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);

    // Act
    Array actualArray = jdbcResultSetImpl.getArray("Column Label");

    // Assert
    verify(callableStatement).getArray("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCResultSetImpl#getArray(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetImpl#getArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCResultSetImpl.getArray(String)"})
  public void testGetArrayWithColumnLabel2() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);
    when(callableStatement.getArray(Mockito.<String>any())).thenReturn(jdbcArrayImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original, true);
    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(mock(JDBCSession.class), mock(JDBCStatement.class), original2, true);

    // Act
    Array actualArray = jdbcResultSetImpl.getArray("Column Label");

    // Assert
    verify(callableStatement).getArray("Column Label");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCResultSetImpl#createMetaDataImpl()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetImpl#createMetaDataImpl()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSetMetaData JDBCResultSetImpl.createMetaDataImpl()"})
  public void testCreateMetaDataImpl_thenThrowIllegalStateException() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession session2 = mock(JDBCSession.class);
    when(session2.getDataSource()).thenReturn(jdbcDataSource2);

    JDBCDataSource jdbcDataSource3 = mock(JDBCDataSource.class);
    when(jdbcDataSource3.getJdbcFactory()).thenThrow(new IllegalStateException());

    JDBCSession session3 = mock(JDBCSession.class);
    when(session3.getDataSource()).thenReturn(jdbcDataSource3);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    JDBCResultSetImpl original2 =
        new JDBCResultSetImpl(session3, mock(JDBCStatement.class), original, true);

    JDBCResultSetImpl original3 =
        new JDBCResultSetImpl(session2, mock(JDBCStatement.class), original2, true);

    JDBCResultSetImpl jdbcResultSetImpl =
        new JDBCResultSetImpl(session, mock(JDBCStatement.class), original3, true);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> jdbcResultSetImpl.createMetaDataImpl());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(session2).getDataSource();
    verify(session3).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    verify(jdbcDataSource2).getJdbcFactory();
    verify(jdbcDataSource3).getJdbcFactory();
  }
}
