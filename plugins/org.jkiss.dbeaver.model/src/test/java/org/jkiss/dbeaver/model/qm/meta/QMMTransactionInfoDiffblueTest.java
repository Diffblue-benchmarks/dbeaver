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
import org.jkiss.dbeaver.model.exec.DBCSavepoint;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMTransactionInfoDiffblueTest {
  /**
   * Test {@link QMMTransactionInfo#QMMTransactionInfo(QMMConnectionInfo, long)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Connection is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionInfo#QMMTransactionInfo(QMMConnectionInfo, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionInfo.<init>(QMMConnectionInfo, long)"})
  public void testNewQMMTransactionInfo_whenNull_thenReturnConnectionIsNull() {
    // Arrange and Act
    QMMTransactionInfo actualQmmTransactionInfo = new QMMTransactionInfo(null, 1L);

    // Assert
    assertNull(actualQmmTransactionInfo.getConnection());
    assertNull(actualQmmTransactionInfo.getPrevious());
    assertEquals(0L, actualQmmTransactionInfo.getDuration());
    assertEquals(1L, actualQmmTransactionInfo.getCloseTime());
    assertEquals(1L, actualQmmTransactionInfo.getOpenTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, actualQmmTransactionInfo.getObjectType());
    assertFalse(actualQmmTransactionInfo.isUpdated());
    assertFalse(actualQmmTransactionInfo.isCommitted());
    assertTrue(actualQmmTransactionInfo.isClosed());
  }

  /**
   * Test {@link QMMTransactionInfo#QMMTransactionInfo(QMMConnectionInfo, QMMTransactionInfo)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Connection is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionInfo#QMMTransactionInfo(QMMConnectionInfo,
   * QMMTransactionInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionInfo.<init>(QMMConnectionInfo, QMMTransactionInfo)"})
  public void testNewQMMTransactionInfo_whenNull_thenReturnConnectionIsNull2() {
    // Arrange
    QMMTransactionInfo previous = new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);

    // Act
    QMMTransactionInfo actualQmmTransactionInfo = new QMMTransactionInfo(null, previous);

    // Assert
    assertNull(actualQmmTransactionInfo.getConnection());
    assertEquals(-1L, actualQmmTransactionInfo.getDuration());
    assertEquals(0L, actualQmmTransactionInfo.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_INFO, actualQmmTransactionInfo.getObjectType());
    assertFalse(actualQmmTransactionInfo.isClosed());
    assertFalse(actualQmmTransactionInfo.isUpdated());
    assertFalse(actualQmmTransactionInfo.isCommitted());
    assertSame(previous, actualQmmTransactionInfo.getPrevious());
  }

  /**
   * Test {@link QMMTransactionInfo#commit()}.
   *
   * <p>Method under test: {@link QMMTransactionInfo#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionInfo.commit()"})
  public void testCommit() {
    // Arrange
    QMMTransactionInfo qmmTransactionInfo =
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);

    // Act
    qmmTransactionInfo.commit();

    // Assert
    QMMTransactionSavepointInfo currentSavepoint = qmmTransactionInfo.getCurrentSavepoint();
    assertTrue(currentSavepoint.isClosed());
    assertTrue(qmmTransactionInfo.isUpdated());
    assertTrue(currentSavepoint.isUpdated());
    assertTrue(currentSavepoint.isCommitted());
  }

  /**
   * Test {@link QMMTransactionInfo#commit()}.
   *
   * <p>Method under test: {@link QMMTransactionInfo#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionInfo.commit()"})
  public void testCommit2() {
    // Arrange
    QMMTransactionInfo qmmTransactionInfo =
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);
    qmmTransactionInfo.rollback(mock(DBCSavepoint.class));

    // Act
    qmmTransactionInfo.commit();

    // Assert
    QMMTransactionSavepointInfo currentSavepoint = qmmTransactionInfo.getCurrentSavepoint();
    assertFalse(currentSavepoint.isCommitted());
    assertTrue(currentSavepoint.isClosed());
    assertTrue(qmmTransactionInfo.isUpdated());
    assertTrue(currentSavepoint.isUpdated());
  }

  /**
   * Test {@link QMMTransactionInfo#rollback(DBCSavepoint)}.
   *
   * <ul>
   *   <li>When {@link DBCSavepoint}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionInfo#rollback(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionInfo.rollback(DBCSavepoint)"})
  public void testRollback_whenDBCSavepoint() {
    // Arrange
    QMMTransactionInfo qmmTransactionInfo =
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);

    // Act
    qmmTransactionInfo.rollback(mock(DBCSavepoint.class));

    // Assert
    QMMTransactionSavepointInfo currentSavepoint = qmmTransactionInfo.getCurrentSavepoint();
    assertTrue(currentSavepoint.isClosed());
    assertTrue(qmmTransactionInfo.isUpdated());
    assertTrue(currentSavepoint.isUpdated());
  }

  /**
   * Test {@link QMMTransactionInfo#rollback(DBCSavepoint)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionInfo#rollback(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMTransactionInfo.rollback(DBCSavepoint)"})
  public void testRollback_whenNull() {
    // Arrange
    QMMTransactionInfo qmmTransactionInfo =
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);

    // Act
    qmmTransactionInfo.rollback(null);

    // Assert
    QMMTransactionSavepointInfo currentSavepoint = qmmTransactionInfo.getCurrentSavepoint();
    assertTrue(currentSavepoint.isClosed());
    assertTrue(qmmTransactionInfo.isUpdated());
    assertTrue(currentSavepoint.isUpdated());
  }

  /**
   * Test {@link QMMTransactionInfo#getSavepoint(DBCSavepoint)}.
   *
   * <ul>
   *   <li>When {@link DBCSavepoint}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionInfo#getSavepoint(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMObject QMMTransactionInfo.getSavepoint(DBCSavepoint)"})
  public void testGetSavepoint_whenDBCSavepoint_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L)
            .getSavepoint(mock(DBCSavepoint.class)));
  }

  /**
   * Test {@link QMMTransactionInfo#getSavepoint(DBCSavepoint)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link QMMTransactionSavepointInfo}.
   * </ul>
   *
   * <p>Method under test: {@link QMMTransactionInfo#getSavepoint(DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QMMObject QMMTransactionInfo.getSavepoint(DBCSavepoint)"})
  public void testGetSavepoint_whenNull_thenReturnQMMTransactionSavepointInfo() {
    // Arrange
    QMMTransactionInfo qmmTransactionInfo =
        new QMMTransactionInfo(mock(QMMConnectionInfo.class), 1L);

    // Act
    QMMObject actualSavepoint = qmmTransactionInfo.getSavepoint(null);

    // Assert
    assertTrue(actualSavepoint instanceof QMMTransactionSavepointInfo);
    assertNull(actualSavepoint.getText());
    assertNull(((QMMTransactionSavepointInfo) actualSavepoint).getName());
    assertNull(((QMMTransactionSavepointInfo) actualSavepoint).getReference());
    assertNull(((QMMTransactionSavepointInfo) actualSavepoint).getLastExecute());
    assertNull(((QMMTransactionSavepointInfo) actualSavepoint).getPrevious());
    assertEquals(-1L, actualSavepoint.getDuration());
    assertEquals(0L, actualSavepoint.getCloseTime());
    assertEquals(QMMetaObjectType.TRANSACTION_SAVEPOINT_INFO, actualSavepoint.getObjectType());
    assertFalse(((QMMTransactionSavepointInfo) actualSavepoint).getExecutions().hasNext());
    assertFalse(actualSavepoint.isClosed());
    assertFalse(actualSavepoint.isUpdated());
    assertFalse(((QMMTransactionSavepointInfo) actualSavepoint).hasUserExecutions());
    assertFalse(((QMMTransactionSavepointInfo) actualSavepoint).isCommitted());
    assertSame(
        qmmTransactionInfo, ((QMMTransactionSavepointInfo) actualSavepoint).getTransaction());
  }

  /**
   * Test {@link QMMTransactionInfo#getText()}.
   *
   * <p>Method under test: {@link QMMTransactionInfo#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QMMTransactionInfo.getText()"})
  public void testGetText() {
    // Arrange
    QMMConnectionInfo connection = mock(QMMConnectionInfo.class);
    when(connection.getText()).thenReturn("Text");

    // Act
    String actualText = new QMMTransactionInfo(connection, 1L).getText();

    // Assert
    verify(connection).getText();
    assertEquals("Text", actualText);
  }
}
