package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPTransactionIsolation;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSavepoint;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractTransactionManagerDiffblueTest {
  /**
   * Test {@link AbstractTransactionManager#setTransactionIsolation(DBRProgressMonitor,
   * DBPTransactionIsolation)}.
   *
   * <p>Method under test: {@link
   * AbstractTransactionManager#setTransactionIsolation(DBRProgressMonitor,
   * DBPTransactionIsolation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractTransactionManager.setTransactionIsolation(DBRProgressMonitor, DBPTransactionIsolation)"
  })
  public void testSetTransactionIsolation() throws DBCException {
    // Arrange
    AbstractTransactionManager abstractTransactionManager = new AbstractTransactionManager();

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            abstractTransactionManager.setTransactionIsolation(
                new LoggingProgressMonitor(), mock(DBPTransactionIsolation.class)));
  }

  /**
   * Test {@link AbstractTransactionManager#setAutoCommit(DBRProgressMonitor, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractTransactionManager#setAutoCommit(DBRProgressMonitor,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractTransactionManager.setAutoCommit(DBRProgressMonitor, boolean)"})
  public void testSetAutoCommit_whenFalse_thenThrowDBCException() throws DBCException {
    // Arrange
    AbstractTransactionManager abstractTransactionManager = new AbstractTransactionManager();

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> abstractTransactionManager.setAutoCommit(new LoggingProgressMonitor(), false));
  }

  /**
   * Test {@link AbstractTransactionManager#setAutoCommit(DBRProgressMonitor, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link AbstractTransactionManager#setAutoCommit(DBRProgressMonitor,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractTransactionManager.setAutoCommit(DBRProgressMonitor, boolean)"})
  public void testSetAutoCommit_whenTrue_thenDoesNotThrow() throws DBCException {
    // Arrange
    AbstractTransactionManager abstractTransactionManager = new AbstractTransactionManager();

    // Act and Assert
    abstractTransactionManager.setAutoCommit(new LoggingProgressMonitor(), true);
  }

  /**
   * Test {@link AbstractTransactionManager#supportsSavepoints()}.
   *
   * <p>Method under test: {@link AbstractTransactionManager#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractTransactionManager.supportsSavepoints()"})
  public void testSupportsSavepoints() {
    // Arrange, Act and Assert
    assertFalse(new AbstractTransactionManager().supportsSavepoints());
  }

  /**
   * Test {@link AbstractTransactionManager#setSavepoint(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link AbstractTransactionManager#setSavepoint(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCSavepoint AbstractTransactionManager.setSavepoint(DBRProgressMonitor, String)"
  })
  public void testSetSavepoint() throws DBCException {
    // Arrange
    AbstractTransactionManager abstractTransactionManager = new AbstractTransactionManager();

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> abstractTransactionManager.setSavepoint(new LoggingProgressMonitor(), "Name"));
  }

  /**
   * Test {@link AbstractTransactionManager#releaseSavepoint(DBRProgressMonitor, DBCSavepoint)}.
   *
   * <p>Method under test: {@link AbstractTransactionManager#releaseSavepoint(DBRProgressMonitor,
   * DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractTransactionManager.releaseSavepoint(DBRProgressMonitor, DBCSavepoint)"
  })
  public void testReleaseSavepoint() throws DBCException {
    // Arrange
    AbstractTransactionManager abstractTransactionManager = new AbstractTransactionManager();

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            abstractTransactionManager.releaseSavepoint(
                new LoggingProgressMonitor(), mock(DBCSavepoint.class)));
  }

  /**
   * Test {@link AbstractTransactionManager#rollback(DBCSession, DBCSavepoint)}.
   *
   * <p>Method under test: {@link AbstractTransactionManager#rollback(DBCSession, DBCSavepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractTransactionManager.rollback(DBCSession, DBCSavepoint)"})
  public void testRollback() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            new AbstractTransactionManager()
                .rollback(mock(DBCSession.class), mock(DBCSavepoint.class)));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AbstractTransactionManager}
   *   <li>{@link AbstractTransactionManager#commit(DBCSession)}
   *   <li>{@link AbstractTransactionManager#getTransactionIsolation()}
   *   <li>{@link AbstractTransactionManager#isAutoCommit()}
   *   <li>{@link AbstractTransactionManager#isSupportsTransactions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AbstractTransactionManager.<init>()",
    "void AbstractTransactionManager.commit(DBCSession)",
    "DBPTransactionIsolation AbstractTransactionManager.getTransactionIsolation()",
    "boolean AbstractTransactionManager.isAutoCommit()",
    "boolean AbstractTransactionManager.isSupportsTransactions()"
  })
  public void testGettersAndSetters() throws DBCException {
    // Arrange and Act
    AbstractTransactionManager actualAbstractTransactionManager = new AbstractTransactionManager();
    actualAbstractTransactionManager.commit(mock(DBCSession.class));
    DBPTransactionIsolation actualTransactionIsolation =
        actualAbstractTransactionManager.getTransactionIsolation();
    boolean actualIsAutoCommitResult = actualAbstractTransactionManager.isAutoCommit();

    // Assert
    assertNull(actualTransactionIsolation);
    assertFalse(actualAbstractTransactionManager.isSupportsTransactions());
    assertTrue(actualIsAutoCommitResult);
  }
}
