package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMTransactionStateDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMTransactionState#QMTransactionState(int, int, boolean, long)}
   *   <li>{@link QMTransactionState#getExecuteCount()}
   *   <li>{@link QMTransactionState#getTransactionStartTime()}
   *   <li>{@link QMTransactionState#getUpdateCount()}
   *   <li>{@link QMTransactionState#isTransactionMode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMTransactionState.<init>(int, int, boolean, long)",
    "int QMTransactionState.getExecuteCount()",
    "long QMTransactionState.getTransactionStartTime()",
    "int QMTransactionState.getUpdateCount()",
    "boolean QMTransactionState.isTransactionMode()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    QMTransactionState actualQmTransactionState = new QMTransactionState(3, 3, true, 1L);
    int actualExecuteCount = actualQmTransactionState.getExecuteCount();
    long actualTransactionStartTime = actualQmTransactionState.getTransactionStartTime();
    int actualUpdateCount = actualQmTransactionState.getUpdateCount();

    // Assert
    assertEquals(1L, actualTransactionStartTime);
    assertEquals(3, actualExecuteCount);
    assertEquals(3, actualUpdateCount);
    assertTrue(actualQmTransactionState.isTransactionMode());
  }
}
