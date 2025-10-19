package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMStatementInfoDiffblueTest {
  /**
   * Test {@link QMMStatementInfo#QMMStatementInfo(QMMConnectionInfo, DBCStatement,
   * QMMStatementInfo)}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#getPurpose()} return {@code USER}.
   *   <li>Then return Text is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementInfo#QMMStatementInfo(QMMConnectionInfo, DBCStatement,
   * QMMStatementInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementInfo.<init>(QMMConnectionInfo, DBCStatement, QMMStatementInfo)"
  })
  public void testNewQMMStatementInfo_givenDBCSessionGetPurposeReturnUser_thenReturnTextIsNull() {
    // Arrange
    QMMConnectionInfo connection = mock(QMMConnectionInfo.class);

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);

    // Act
    QMMStatementInfo actualQmmStatementInfo = new QMMStatementInfo(connection, reference, null);

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    assertNull(actualQmmStatementInfo.getText());
    assertNull(actualQmmStatementInfo.getPrevious());
    assertEquals(-1L, actualQmmStatementInfo.getDuration());
    assertEquals(0L, actualQmmStatementInfo.getCloseTime());
    assertEquals(DBCExecutionPurpose.USER, actualQmmStatementInfo.getPurpose());
    assertEquals(QMMetaObjectType.STATEMENT_INFO, actualQmmStatementInfo.getObjectType());
    assertFalse(actualQmmStatementInfo.isClosed());
    assertFalse(actualQmmStatementInfo.isUpdated());
    assertSame(reference, actualQmmStatementInfo.getReference());
    assertSame(connection, actualQmmStatementInfo.getConnection());
  }

  /**
   * Test {@link QMMStatementInfo#QMMStatementInfo(long, long, QMMConnectionInfo,
   * DBCExecutionPurpose)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Reference is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementInfo#QMMStatementInfo(long, long, QMMConnectionInfo,
   * DBCExecutionPurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMStatementInfo.<init>(long, long, QMMConnectionInfo, DBCExecutionPurpose)"
  })
  public void testNewQMMStatementInfo_whenNull_thenReturnReferenceIsNull() {
    // Arrange and Act
    QMMStatementInfo actualQmmStatementInfo =
        new QMMStatementInfo(1L, 1L, null, DBCExecutionPurpose.USER);

    // Assert
    assertNull(actualQmmStatementInfo.getReference());
    assertNull(actualQmmStatementInfo.getConnection());
    assertNull(actualQmmStatementInfo.getPrevious());
    assertEquals(0L, actualQmmStatementInfo.getDuration());
    assertEquals(1L, actualQmmStatementInfo.getCloseTime());
    assertEquals(1L, actualQmmStatementInfo.getOpenTime());
    assertEquals(DBCExecutionPurpose.USER, actualQmmStatementInfo.getPurpose());
    assertEquals(QMMetaObjectType.STATEMENT_INFO, actualQmmStatementInfo.getObjectType());
    assertFalse(actualQmmStatementInfo.isUpdated());
    assertTrue(actualQmmStatementInfo.isClosed());
  }

  /**
   * Test {@link QMMStatementInfo#close()}.
   *
   * <p>Method under test: {@link QMMStatementInfo#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMStatementInfo.close()"})
  public void testClose() {
    // Arrange
    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo connection = mock(QMMConnectionInfo.class);
    QMMConnectionInfo session =
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
            .setStatementStack(null)
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();

    QMMStatementInfo qmmStatementInfo =
        new QMMStatementInfo(
            connection, reference, new QMMStatementInfo(1L, 1L, session, DBCExecutionPurpose.USER));

    // Act
    qmmStatementInfo.close();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    assertNull(qmmStatementInfo.getReference());
    assertTrue(qmmStatementInfo.isClosed());
    assertTrue(qmmStatementInfo.isUpdated());
  }

  /**
   * Test {@link QMMStatementInfo#getText()}.
   *
   * <ul>
   *   <li>Given {@link QMMConnectionInfo} {@link QMMConnectionInfo#getText()} return {@code Text}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link QMMStatementInfo#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMStatementInfo.getText()"})
  public void testGetText_givenQMMConnectionInfoGetTextReturnText_thenReturnText() {
    // Arrange
    QMMConnectionInfo connection = mock(QMMConnectionInfo.class);
    when(connection.getText()).thenReturn("Text");

    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.getPurpose()).thenReturn(DBCExecutionPurpose.USER);

    DBCStatement reference = mock(DBCStatement.class);
    when(reference.getSession()).thenReturn(dbcSession);
    QMMConnectionInfo session =
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
            .setStatementStack(null)
            .setTransaction(mock(QMMTransactionInfo.class))
            .setTransactional(true)
            .build();

    QMMStatementInfo qmmStatementInfo =
        new QMMStatementInfo(
            connection, reference, new QMMStatementInfo(1L, 1L, session, DBCExecutionPurpose.USER));

    // Act
    String actualText = qmmStatementInfo.getText();

    // Assert
    verify(dbcSession).getPurpose();
    verify(reference).getSession();
    verify(connection).getText();
    assertEquals("Text", actualText);
  }
}
