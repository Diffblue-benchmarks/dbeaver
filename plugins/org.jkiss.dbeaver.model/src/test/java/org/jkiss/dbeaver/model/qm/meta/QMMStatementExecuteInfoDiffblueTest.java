package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.qm.meta.QMMConnectionInfo.Builder;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class QMMStatementExecuteInfoDiffblueTest {
  /**
   * Test {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementExecuteInfo.<init>(QMMStatementInfo, QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)"
  })
  public void testNewQMMStatementExecuteInfo_givenFalse() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference = new LocalStatement(session, "Text");
    QMMConnectionInfo connection =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(null)
            .setInstanceId("42")
            .setOpenTime(1L)
            .setProjectInfo(QMMProjectInfo.builder().build())
            .setStatementStack(mock(QMMStatementInfo.class))
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(false);

    // Act
    QMMStatementExecuteInfo actualQmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Assert
    verify(session).getPurpose();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertEquals("Query String", actualQmmStatementExecuteInfo.getQueryString());
    assertEquals("Query String", actualQmmStatementExecuteInfo.getText());
    assertFalse(actualQmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionPurpose#META}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementExecuteInfo.<init>(QMMStatementInfo, QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)"
  })
  public void testNewQMMStatementExecuteInfo_givenMeta() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.META);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference = new LocalStatement(session, "Text");
    QMMConnectionInfo connection =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(null)
            .setInstanceId("42")
            .setOpenTime(1L)
            .setProjectInfo(QMMProjectInfo.builder().build())
            .setStatementStack(mock(QMMStatementInfo.class))
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    // Act
    QMMStatementExecuteInfo actualQmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement,
            savepoint,
            "Query String",
            previous,
            mock(SQLDialect.class),
            "Schema",
            "Catalog");

    // Assert
    verify(session).getPurpose();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertEquals("Query String", actualQmmStatementExecuteInfo.getQueryString());
    assertEquals("Query String", actualQmmStatementExecuteInfo.getText());
    assertFalse(actualQmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return Transactional.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementExecuteInfo.<init>(QMMStatementInfo, QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)"
  })
  public void testNewQMMStatementExecuteInfo_givenTrue_thenReturnTransactional() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference = new LocalStatement(session, "Text");
    QMMConnectionInfo connection =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(null)
            .setInstanceId("42")
            .setOpenTime(1L)
            .setProjectInfo(QMMProjectInfo.builder().build())
            .setStatementStack(mock(QMMStatementInfo.class))
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    // Act
    QMMStatementExecuteInfo actualQmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Assert
    verify(session).getPurpose();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertEquals("Query String", actualQmmStatementExecuteInfo.getQueryString());
    assertEquals("Query String", actualQmmStatementExecuteInfo.getText());
    assertTrue(actualQmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(long, long, QMMStatementInfo,
   * String, long, int, String, long, long, boolean, String, String)}.
   *
   * <ul>
   *   <li>Then return ErrorMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(long, long,
   * QMMStatementInfo, String, long, int, String, long, long, boolean, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementExecuteInfo.<init>(long, long, QMMStatementInfo, String, long, int, String, long, long, boolean, String, String)"
  })
  public void testNewQMMStatementExecuteInfo_thenReturnErrorMessageIsAnErrorOccurred() {
    // Arrange
    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMStatementInfo stmt =
        new QMMStatementInfo(
            mock(QMMConnectionInfo.class), reference, mock(QMMStatementInfo.class));

    // Act
    QMMStatementExecuteInfo actualQmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    assertEquals("An error occurred", actualQmmStatementExecuteInfo.getErrorMessage());
    assertEquals("Catalog", actualQmmStatementExecuteInfo.getCatalog());
    assertEquals("Query String", actualQmmStatementExecuteInfo.getQueryString());
    assertEquals("Query String", actualQmmStatementExecuteInfo.getText());
    assertEquals("Schema", actualQmmStatementExecuteInfo.getSchema());
    assertNull(actualQmmStatementExecuteInfo.getPrevious());
    assertNull(actualQmmStatementExecuteInfo.getSavepoint());
    assertEquals(-1, actualQmmStatementExecuteInfo.getErrorCode());
    assertEquals(-1L, actualQmmStatementExecuteInfo.getUpdateRowCount());
    assertEquals(0L, actualQmmStatementExecuteInfo.getDuration());
    assertEquals(1L, actualQmmStatementExecuteInfo.getCloseTime());
    assertEquals(1L, actualQmmStatementExecuteInfo.getOpenTime());
    assertEquals(1L, actualQmmStatementExecuteInfo.getFetchBeginTime());
    assertEquals(1L, actualQmmStatementExecuteInfo.getFetchEndTime());
    assertEquals(3L, actualQmmStatementExecuteInfo.getFetchRowCount());
    assertEquals(
        QMMetaObjectType.STATEMENT_EXECUTE_INFO, actualQmmStatementExecuteInfo.getObjectType());
    assertFalse(actualQmmStatementExecuteInfo.isUpdated());
    assertFalse(actualQmmStatementExecuteInfo.isFetching());
    assertTrue(actualQmmStatementExecuteInfo.isClosed());
    assertTrue(actualQmmStatementExecuteInfo.hasError());
    assertTrue(actualQmmStatementExecuteInfo.isTransactional());
    assertSame(stmt, actualQmmStatementExecuteInfo.getStatement());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}.
   *
   * <ul>
   *   <li>When {@link QMMStatementInfo}.
   *   <li>Then return QueryString is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#QMMStatementExecuteInfo(QMMStatementInfo,
   * QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementExecuteInfo.<init>(QMMStatementInfo, QMMTransactionSavepointInfo, String, QMMStatementExecuteInfo, SQLDialect, String, String)"
  })
  public void testNewQMMStatementExecuteInfo_whenQMMStatementInfo_thenReturnQueryStringIsNull() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference = new LocalStatement(session, "Text");
    QMMConnectionInfo connection =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(null)
            .setInstanceId("42")
            .setOpenTime(1L)
            .setProjectInfo(QMMProjectInfo.builder().build())
            .setStatementStack(mock(QMMStatementInfo.class))
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    // Act
    QMMStatementExecuteInfo actualQmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, null, previous, mock(SQLDialect.class), "Schema", "Catalog");

    // Assert
    verify(session).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertNull(actualQmmStatementExecuteInfo.getQueryString());
    assertNull(actualQmmStatementExecuteInfo.getText());
    assertFalse(actualQmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#close(long, Throwable)} with {@code long}, {@code
   * Throwable}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#close(long, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementExecuteInfo.close(long, Throwable)"})
  public void testCloseWithLongThrowable() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    qmmStatementExecuteInfo.close(3L, new Throwable());

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertEquals(3L, qmmStatementExecuteInfo.getUpdateRowCount());
    assertTrue(qmmStatementExecuteInfo.isClosed());
    assertTrue(qmmStatementExecuteInfo.isUpdated());
    assertTrue(qmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#close(long, Throwable)} with {@code long}, {@code
   * Throwable}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#close(long, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementExecuteInfo.close(long, Throwable)"})
  public void testCloseWithLongThrowable2() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    qmmStatementExecuteInfo.close(3L, null);

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertEquals(3L, qmmStatementExecuteInfo.getUpdateRowCount());
    assertTrue(qmmStatementExecuteInfo.isClosed());
    assertTrue(qmmStatementExecuteInfo.isUpdated());
    assertTrue(qmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#close(long, Throwable)} with {@code long}, {@code
   * Throwable}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#close(long, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementExecuteInfo.close(long, Throwable)"})
  public void testCloseWithLongThrowable3() {
    // Arrange
    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            mock(QMMStatementInfo.class),
            savepoint,
            null,
            previous,
            mock(SQLDialect.class),
            "Schema",
            "Catalog");

    // Act
    qmmStatementExecuteInfo.close(3L, null);

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertEquals(3L, qmmStatementExecuteInfo.getUpdateRowCount());
    assertTrue(qmmStatementExecuteInfo.isClosed());
    assertTrue(qmmStatementExecuteInfo.isUpdated());
    assertTrue(qmmStatementExecuteInfo.isTransactional());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#close(long, Throwable)} with {@code long}, {@code
   * Throwable}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#close(long, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementExecuteInfo.close(long, Throwable)"})
  public void testCloseWithLongThrowable4() {
    // Arrange
    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            mock(QMMStatementInfo.class),
            savepoint,
            null,
            previous,
            mock(SQLDialect.class),
            "Schema",
            "Catalog");

    // Act
    qmmStatementExecuteInfo.close(-1L, null);

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertEquals(-1L, qmmStatementExecuteInfo.getUpdateRowCount());
    assertFalse(qmmStatementExecuteInfo.isTransactional());
    assertTrue(qmmStatementExecuteInfo.isClosed());
    assertTrue(qmmStatementExecuteInfo.isUpdated());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#beginFetch()}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#beginFetch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementExecuteInfo.beginFetch()"})
  public void testBeginFetch() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    qmmStatementExecuteInfo.beginFetch();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertTrue(qmmStatementExecuteInfo.isFetching());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#endFetch(long)}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#endFetch(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementExecuteInfo.endFetch(long)"})
  public void testEndFetch() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    qmmStatementExecuteInfo.endFetch(3L);

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertEquals(3L, qmmStatementExecuteInfo.getFetchRowCount());
  }

  /**
   * Test {@link QMMStatementExecuteInfo#hasError()}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#hasError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.hasError()"})
  public void testHasError() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            savepoint,
            "Query String",
            null,
            BasicSQLDialect.INSTANCE,
            "Schema",
            "Catalog");

    Builder setOpenTimeResult =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(executionStack)
            .setInstanceId("42")
            .setOpenTime(1L);

    Builder setProjectInfoResult =
        setOpenTimeResult.setProjectInfo(
            QMMProjectInfo.builder()
                .setId("42")
                .setAnonymous(true)
                .setName("Name")
                .setPath("Path")
                .build());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo statementStack =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));

    Builder setStatementStackResult = setProjectInfoResult.setStatementStack(statementStack);
    QMMConnectionInfo connection2 = QMMConnectionInfo.builder().build();
    QMMConnectionInfo connection3 =
        setStatementStackResult
            .setTransaction(new QMMTransactionInfo(connection2, 1L))
            .setTransactional(true)
            .build();

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference2 = new LocalStatement(session, "Text");
    QMMConnectionInfo session2 = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(
            connection3,
            reference2,
            new QMMStatementInfo(1L, 1L, session2, DBCExecutionPurpose.USER));
    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            0,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    // Act
    boolean actualHasErrorResult = qmmStatementExecuteInfo.hasError();

    // Assert
    verify(dbcSession).getPurpose();
    verify(session).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertTrue(actualHasErrorResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#hasError()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#hasError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.hasError()"})
  public void testHasError_thenReturnFalse() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    boolean actualHasErrorResult = qmmStatementExecuteInfo.hasError();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertFalse(actualHasErrorResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#hasError()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#hasError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.hasError()"})
  public void testHasError_thenReturnTrue() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            savepoint,
            "Query String",
            null,
            BasicSQLDialect.INSTANCE,
            "Schema",
            "Catalog");

    Builder setOpenTimeResult =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(executionStack)
            .setInstanceId("42")
            .setOpenTime(1L);

    Builder setProjectInfoResult =
        setOpenTimeResult.setProjectInfo(
            QMMProjectInfo.builder()
                .setId("42")
                .setAnonymous(true)
                .setName("Name")
                .setPath("Path")
                .build());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo statementStack =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));

    Builder setStatementStackResult = setProjectInfoResult.setStatementStack(statementStack);
    QMMConnectionInfo connection2 = QMMConnectionInfo.builder().build();
    QMMConnectionInfo connection3 =
        setStatementStackResult
            .setTransaction(new QMMTransactionInfo(connection2, 1L))
            .setTransactional(true)
            .build();

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference2 = new LocalStatement(session, "Text");
    QMMConnectionInfo session2 = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(
            connection3,
            reference2,
            new QMMStatementInfo(1L, 1L, session2, DBCExecutionPurpose.USER));
    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    // Act
    boolean actualHasErrorResult = qmmStatementExecuteInfo.hasError();

    // Assert
    verify(dbcSession).getPurpose();
    verify(session).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertTrue(actualHasErrorResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#isFetching()}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#isTransactionModifyingQuery(String)}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#isFetching()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.isFetching()"})
  public void testIsFetching_thenCallsIsTransactionModifyingQuery() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    boolean actualIsFetchingResult = qmmStatementExecuteInfo.isFetching();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertFalse(actualIsFetchingResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#isFetching()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#isFetching()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.isFetching()"})
  public void testIsFetching_thenReturnFalse() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            savepoint,
            "Query String",
            null,
            BasicSQLDialect.INSTANCE,
            "Schema",
            "Catalog");

    Builder setOpenTimeResult =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(executionStack)
            .setInstanceId("42")
            .setOpenTime(1L);

    Builder setProjectInfoResult =
        setOpenTimeResult.setProjectInfo(
            QMMProjectInfo.builder()
                .setId("42")
                .setAnonymous(true)
                .setName("Name")
                .setPath("Path")
                .build());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo statementStack =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));

    Builder setStatementStackResult = setProjectInfoResult.setStatementStack(statementStack);
    QMMConnectionInfo connection2 = QMMConnectionInfo.builder().build();
    QMMConnectionInfo connection3 =
        setStatementStackResult
            .setTransaction(new QMMTransactionInfo(connection2, 1L))
            .setTransactional(true)
            .build();

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference2 = new LocalStatement(session, "Text");
    QMMConnectionInfo session2 = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(
            connection3,
            reference2,
            new QMMStatementInfo(1L, 1L, session2, DBCExecutionPurpose.USER));
    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    // Act
    boolean actualIsFetchingResult = qmmStatementExecuteInfo.isFetching();

    // Assert
    verify(dbcSession).getPurpose();
    verify(session).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertFalse(actualIsFetchingResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#isFetching()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#isFetching()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.isFetching()"})
  public void testIsFetching_thenReturnTrue() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            savepoint,
            "Query String",
            null,
            BasicSQLDialect.INSTANCE,
            "Schema",
            "Catalog");

    Builder setOpenTimeResult =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(executionStack)
            .setInstanceId("42")
            .setOpenTime(1L);

    Builder setProjectInfoResult =
        setOpenTimeResult.setProjectInfo(
            QMMProjectInfo.builder()
                .setId("42")
                .setAnonymous(true)
                .setName("Name")
                .setPath("Path")
                .build());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo statementStack =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));

    Builder setStatementStackResult = setProjectInfoResult.setStatementStack(statementStack);
    QMMConnectionInfo connection2 = QMMConnectionInfo.builder().build();
    QMMConnectionInfo connection3 =
        setStatementStackResult
            .setTransaction(new QMMTransactionInfo(connection2, 1L))
            .setTransactional(true)
            .build();

    DBCSession session = mock(DBCSession.class);
    when(session.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    LocalStatement reference2 = new LocalStatement(session, "Text");
    QMMConnectionInfo session2 = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(
            connection3,
            reference2,
            new QMMStatementInfo(1L, 1L, session2, DBCExecutionPurpose.USER));
    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            0L,
            true,
            "Schema",
            "Catalog");

    // Act
    boolean actualIsFetchingResult = qmmStatementExecuteInfo.isFetching();

    // Assert
    verify(dbcSession).getPurpose();
    verify(session).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    assertTrue(actualIsFetchingResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#isTransactional()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#isTransactional()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.isTransactional()"})
  public void testIsTransactional_thenReturnFalse() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(false);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    boolean actualIsTransactionalResult = qmmStatementExecuteInfo.isTransactional();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertFalse(actualIsTransactionalResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#isTransactional()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#isTransactional()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMStatementExecuteInfo.isTransactional()"})
  public void testIsTransactional_thenReturnTrue() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    boolean actualIsTransactionalResult = qmmStatementExecuteInfo.isTransactional();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertTrue(actualIsTransactionalResult);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#getDuration()}.
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#getDuration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMMStatementExecuteInfo.getDuration()"})
  public void testGetDuration() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");
    qmmStatementExecuteInfo.setCloseTime(1L);

    // Act
    qmmStatementExecuteInfo.getDuration();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
  }

  /**
   * Test {@link QMMStatementExecuteInfo#getDuration()}.
   *
   * <ul>
   *   <li>Given {@link QMMStatementInfo} {@link QMMStatementInfo#getPurpose()} return {@code USER}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#getDuration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long QMMStatementExecuteInfo.getDuration()"})
  public void testGetDuration_givenQMMStatementInfoGetPurposeReturnUser_thenReturnMinusOne() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    // Act
    long actualDuration = qmmStatementExecuteInfo.getDuration();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertEquals(-1L, actualDuration);
  }

  /**
   * Test {@link QMMStatementExecuteInfo#getConnection()}.
   *
   * <ul>
   *   <li>Then return ContainerId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementExecuteInfo#getConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMConnectionInfo QMMStatementExecuteInfo.getConnection()"})
  public void testGetConnection_thenReturnContainerIdIs42() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    QMMTransactionSavepointInfo savepoint = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt =
        new QMMStatementInfo(connection, reference, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement, savepoint, "Query String", previous, sqlDialect, "Schema", "Catalog");

    Builder setOpenTimeResult =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(executionStack)
            .setInstanceId("42")
            .setOpenTime(1L);

    Builder setProjectInfoResult =
        setOpenTimeResult.setProjectInfo(
            QMMProjectInfo.builder()
                .setId("42")
                .setAnonymous(true)
                .setName("Name")
                .setPath("Path")
                .build());

    DBCSession dbcSession2 = mock(DBCSession.class);
    when(dbcSession2.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference2 = mock(DBCStatement.class);
    when(reference2.getSession()).thenReturn(dbcSession2);
    QMMStatementInfo statementStack =
        new QMMStatementInfo(
            mock(QMMConnectionInfo.class), reference2, mock(QMMStatementInfo.class));

    Builder setStatementStackResult = setProjectInfoResult.setStatementStack(statementStack);
    QMMTransactionInfo transaction = new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);
    QMMConnectionInfo qmmConnectionInfo =
        setStatementStackResult.setTransaction(transaction).setTransactional(true).build();

    QMMStatementInfo statement2 = mock(QMMStatementInfo.class);
    when(statement2.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    when(statement2.getConnection()).thenReturn(qmmConnectionInfo);

    QMMTransactionSavepointInfo savepoint2 = mock(QMMTransactionSavepointInfo.class);
    doNothing().when(savepoint2).setLastExecute(Mockito.<QMMStatementExecuteInfo>any());

    DBCSession dbcSession3 = mock(DBCSession.class);
    when(dbcSession3.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference3 = mock(DBCStatement.class);
    when(reference3.getSession()).thenReturn(dbcSession3);
    QMMConnectionInfo connection2 = QMMConnectionInfo.builder().build();

    QMMStatementInfo stmt2 =
        new QMMStatementInfo(connection2, reference3, mock(QMMStatementInfo.class));
    QMMStatementExecuteInfo previous2 =
        new QMMStatementExecuteInfo(
            1L,
            1L,
            stmt2,
            "Query String",
            3L,
            -1,
            "An error occurred",
            1L,
            1L,
            true,
            "Schema",
            "Catalog");

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);

    QMMStatementExecuteInfo qmmStatementExecuteInfo =
        new QMMStatementExecuteInfo(
            statement2, savepoint2, "Query String", previous2, sqlDialect2, "Schema", "Catalog");

    // Act
    QMMConnectionInfo actualConnection = qmmStatementExecuteInfo.getConnection();

    // Assert
    verify(dbcSession).getPurpose();
    verify(dbcSession2).getPurpose();
    verify(dbcSession3).getPurpose();
    verify(reference).getSession();
    verify(reference2).getSession();
    verify(reference3).getSession();
    verify(statement2).getConnection();
    verify(statement2).getPurpose();
    verify(statement).getPurpose();
    verify(savepoint).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(savepoint2).setLastExecute(isA(QMMStatementExecuteInfo.class));
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    verify(sqlDialect2).isTransactionModifyingQuery("Query String");
    assertEquals("42", actualConnection.getContainerId());
    assertEquals("42", actualConnection.getDriverId());
    assertEquals("42", actualConnection.getInstanceId());
    assertEquals("42", actualConnection.getProjectId());
    assertEquals("Container Name - Context Name", actualConnection.getText());
    assertEquals("Container Name", actualConnection.getContainerName());
    assertEquals("Context Name", actualConnection.getContextName());
    assertEquals("https://example.org/example", actualConnection.getConnectionUrl());
    assertEquals("janedoe", actualConnection.getConnectionUserName());
    assertEquals(0L, actualConnection.getDuration());
    assertEquals(1L, actualConnection.getCloseTime());
    assertEquals(1L, actualConnection.getOpenTime());
    assertEquals(QMMetaObjectType.CONNECTION_INFO, actualConnection.getObjectType());
    assertFalse(actualConnection.isUpdated());
    assertTrue(actualConnection.isTransactional());
    assertTrue(actualConnection.isClosed());
    assertSame(executionStack, actualConnection.getExecutionStack());
    assertSame(statementStack, actualConnection.getStatementStack());
    assertSame(transaction, actualConnection.getTransaction());
  }
}
