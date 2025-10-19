package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Iterator;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.exec.DBCSavepoint;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.qm.meta.QMMConnectionInfo.Builder;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class QMMTransactionSavepointInfoDiffblueTest {
  /**
   * Test {@link QMMTransactionSavepointInfo#QMMTransactionSavepointInfo(QMMTransactionInfo,
   * DBCSavepoint, String, QMMTransactionSavepointInfo)}.
   *
   * <p>Method under test: {@link
   * QMMTransactionSavepointInfo#QMMTransactionSavepointInfo(QMMTransactionInfo, DBCSavepoint,
   * String, QMMTransactionSavepointInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMTransactionSavepointInfo.<init>(QMMTransactionInfo, DBCSavepoint, String, QMMTransactionSavepointInfo)"
  })
  public void testNewQMMTransactionSavepointInfo() {
    // Arrange
    QMMTransactionInfo transaction = new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);
    DBCSavepoint reference = mock(DBCSavepoint.class);

    // Act
    QMMTransactionSavepointInfo actualQmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, reference, "Name", null);

    // Assert
    assertEquals("Name", actualQmmTransactionSavepointInfo.getName());
    assertNull(actualQmmTransactionSavepointInfo.getText());
    assertNull(actualQmmTransactionSavepointInfo.getLastExecute());
    assertNull(actualQmmTransactionSavepointInfo.getPrevious());
    assertEquals(-1L, actualQmmTransactionSavepointInfo.getDuration());
    assertEquals(0L, actualQmmTransactionSavepointInfo.getCloseTime());
    assertEquals(
        QMMetaObjectType.TRANSACTION_SAVEPOINT_INFO,
        actualQmmTransactionSavepointInfo.getObjectType());
    assertFalse(actualQmmTransactionSavepointInfo.getExecutions().hasNext());
    assertFalse(actualQmmTransactionSavepointInfo.isClosed());
    assertFalse(actualQmmTransactionSavepointInfo.isUpdated());
    assertFalse(actualQmmTransactionSavepointInfo.hasUserExecutions());
    assertFalse(actualQmmTransactionSavepointInfo.isCommitted());
    assertSame(transaction, actualQmmTransactionSavepointInfo.getTransaction());
    assertSame(reference, actualQmmTransactionSavepointInfo.getReference());
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#close(boolean)} with {@code boolean}.
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#close(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionSavepointInfo.close(boolean)"})
  public void testCloseWithBoolean() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection3, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), "Name", null);

    // Act
    qmmTransactionSavepointInfo.close(true);

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    assertNull(qmmTransactionSavepointInfo.getReference());
    assertTrue(qmmTransactionSavepointInfo.isClosed());
    assertTrue(qmmTransactionSavepointInfo.isUpdated());
    assertTrue(qmmTransactionSavepointInfo.isCommitted());
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#getExecutions()}.
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#getExecutions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Iterator QMMTransactionSavepointInfo.getExecutions()"})
  public void testGetExecutions() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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

    Builder setStatementStackResult =
        setOpenTimeResult
            .setProjectInfo(
                QMMProjectInfo.builder()
                    .setId("42")
                    .setAnonymous(true)
                    .setName("Name")
                    .setPath("Path")
                    .build())
            .setStatementStack(null);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();
    QMMConnectionInfo connection2 =
        setStatementStackResult
            .setTransaction(new QMMTransactionInfo(connection, 1L))
            .setTransactional(true)
            .build();
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection2, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), "Name", null);

    // Act
    Iterator<QMMStatementExecuteInfo> actualExecutions =
        qmmTransactionSavepointInfo.getExecutions();

    // Assert
    verify(statement).getPurpose();
    assertFalse(actualExecutions.hasNext());
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#toString()}.
   *
   * <ul>
   *   <li>Then return {@code SAVEPOINTName}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMTransactionSavepointInfo.toString()"})
  public void testToString_thenReturnSAVEPOINTName() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection3, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), "Name", null);

    // Act
    String actualToStringResult = qmmTransactionSavepointInfo.toString();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    assertEquals("SAVEPOINTName", actualToStringResult);
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#toString()}.
   *
   * <ul>
   *   <li>Then return {@code SAVEPOINT}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMTransactionSavepointInfo.toString()"})
  public void testToString_thenReturnSavepoint() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection3, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), null, null);

    // Act
    String actualToStringResult = qmmTransactionSavepointInfo.toString();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    assertEquals("SAVEPOINT", actualToStringResult);
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#hasUserExecutions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#hasUserExecutions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMTransactionSavepointInfo.hasUserExecutions()"})
  public void testHasUserExecutions_thenReturnFalse() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection3, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), "Name", null);

    // Act
    boolean actualHasUserExecutionsResult = qmmTransactionSavepointInfo.hasUserExecutions();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    assertFalse(actualHasUserExecutionsResult);
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#hasUserExecutions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#hasUserExecutions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QMMTransactionSavepointInfo.hasUserExecutions()"})
  public void testHasUserExecutions_thenReturnTrue() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection3, 1L);

    QMMStatementInfo statement2 = mock(QMMStatementInfo.class);
    when(statement2.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.isTransactionModifyingQuery(Mockito.<String>any())).thenReturn(true);
    QMMConnectionInfo connection4 =
        QMMConnectionInfo.builder()
            .setCloseTime(1L)
            .setConnectionUrl("https://example.org/example")
            .setConnectionUserName("janedoe")
            .setContainerId("42")
            .setContainerName("Container Name")
            .setContextName("Context Name")
            .setDriverId("42")
            .setExecutionStack(mock(QMMStatementExecuteInfo.class))
            .setInstanceId("42")
            .setOpenTime(1L)
            .setProjectInfo(QMMProjectInfo.builder().build())
            .setStatementStack(mock(QMMStatementInfo.class))
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();
    QMMTransactionInfo transaction2 = new QMMTransactionInfo(connection4, 1L);
    QMMTransactionSavepointInfo savepoint =
        new QMMTransactionSavepointInfo(transaction2, mock(DBCSavepoint.class), "Name", null);

    QMMStatementExecuteInfo lastExecute =
        new QMMStatementExecuteInfo(
            statement2,
            savepoint,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
            sqlDialect,
            "Schema",
            "Catalog");

    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), "Name", null);
    qmmTransactionSavepointInfo.setLastExecute(lastExecute);

    // Act
    boolean actualHasUserExecutionsResult = qmmTransactionSavepointInfo.hasUserExecutions();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(statement).getPurpose();
    verify(statement2, atLeast(1)).getPurpose();
    verify(sqlDialect).isTransactionModifyingQuery("Query String");
    assertTrue(actualHasUserExecutionsResult);
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#getText()}.
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMTransactionSavepointInfo.getText()"})
  public void testGetText() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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

    Builder setStatementStackResult =
        setOpenTimeResult
            .setProjectInfo(
                QMMProjectInfo.builder()
                    .setId("42")
                    .setAnonymous(true)
                    .setName("Name")
                    .setPath("Path")
                    .build())
            .setStatementStack(null);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();
    QMMConnectionInfo connection2 =
        setStatementStackResult
            .setTransaction(new QMMTransactionInfo(connection, 1L))
            .setTransactional(true)
            .build();
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection2, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction, mock(DBCSavepoint.class), "Name", null);

    // Act
    String actualText = qmmTransactionSavepointInfo.getText();

    // Assert
    verify(statement).getPurpose();
    assertEquals("Container Name - Context Name", actualText);
  }

  /**
   * Test {@link QMMTransactionSavepointInfo#getConnection()}.
   *
   * <p>Method under test: {@link QMMTransactionSavepointInfo#getConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMConnectionInfo QMMTransactionSavepointInfo.getConnection()"})
  public void testGetConnection() {
    // Arrange
    QMMStatementInfo statement = mock(QMMStatementInfo.class);
    when(statement.getPurpose()).thenReturn(DBCExecutionPurpose.USER);
    QMMStatementExecuteInfo executionStack =
        new QMMStatementExecuteInfo(
            statement,
            null,
            "Query String",
            mock(QMMStatementExecuteInfo.class),
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

    Builder setStatementStackResult =
        setOpenTimeResult
            .setProjectInfo(
                QMMProjectInfo.builder()
                    .setId("42")
                    .setAnonymous(true)
                    .setName("Name")
                    .setPath("Path")
                    .build())
            .setStatementStack(null);
    QMMConnectionInfo connection = QMMConnectionInfo.builder().build();
    QMMTransactionInfo transaction = new QMMTransactionInfo(connection, 1L);
    QMMConnectionInfo connection2 =
        setStatementStackResult.setTransaction(transaction).setTransactional(true).build();
    QMMTransactionInfo transaction2 = new QMMTransactionInfo(connection2, 1L);
    QMMTransactionSavepointInfo qmmTransactionSavepointInfo =
        new QMMTransactionSavepointInfo(transaction2, mock(DBCSavepoint.class), "Name", null);

    // Act
    QMMConnectionInfo actualConnection = qmmTransactionSavepointInfo.getConnection();

    // Assert
    verify(statement).getPurpose();
    assertEquals("42", actualConnection.getContainerId());
    assertEquals("42", actualConnection.getDriverId());
    assertEquals("42", actualConnection.getInstanceId());
    assertEquals("42", actualConnection.getProjectId());
    assertEquals("Container Name - Context Name", actualConnection.getText());
    assertEquals("Container Name", actualConnection.getContainerName());
    assertEquals("Context Name", actualConnection.getContextName());
    assertEquals("https://example.org/example", actualConnection.getConnectionUrl());
    assertEquals("janedoe", actualConnection.getConnectionUserName());
    assertNull(actualConnection.getStatementStack());
    assertEquals(0L, actualConnection.getDuration());
    assertEquals(1L, actualConnection.getCloseTime());
    assertEquals(1L, actualConnection.getOpenTime());
    assertEquals(QMMetaObjectType.CONNECTION_INFO, actualConnection.getObjectType());
    assertFalse(actualConnection.isUpdated());
    assertTrue(actualConnection.isTransactional());
    assertTrue(actualConnection.isClosed());
    assertSame(executionStack, actualConnection.getExecutionStack());
    assertSame(transaction, actualConnection.getTransaction());
  }
}
