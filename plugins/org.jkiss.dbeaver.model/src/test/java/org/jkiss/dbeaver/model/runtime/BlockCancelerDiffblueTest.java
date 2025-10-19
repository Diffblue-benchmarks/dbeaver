package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class BlockCancelerDiffblueTest {
  /**
   * Test {@link BlockCanceler#cancelBlock(DBRProgressMonitor, DBRBlockingObject)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link BlockCanceler#cancelBlock(DBRProgressMonitor, DBRBlockingObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlockCanceler.cancelBlock(DBRProgressMonitor, DBRBlockingObject)"})
  public void testCancelBlock_givenRuntimeException_thenThrowRuntimeException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBRBlockingObject block = mock(DBRBlockingObject.class);
    when(block.getBlockThread()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> BlockCanceler.cancelBlock(monitor, block));
    verify(block).getBlockThread();
  }

  /**
   * Test {@link BlockCanceler#cancelBlock(DBRProgressMonitor, DBRBlockingObject)}.
   *
   * <ul>
   *   <li>Given {@link Thread#Thread()}.
   *   <li>Then calls {@link DBRBlockingObject#cancelBlock(DBRProgressMonitor, Thread)}.
   * </ul>
   *
   * <p>Method under test: {@link BlockCanceler#cancelBlock(DBRProgressMonitor, DBRBlockingObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlockCanceler.cancelBlock(DBRProgressMonitor, DBRBlockingObject)"})
  public void testCancelBlock_givenThread_thenCallsCancelBlock() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBRBlockingObject block = mock(DBRBlockingObject.class);
    doNothing().when(block).cancelBlock(Mockito.<DBRProgressMonitor>any(), Mockito.<Thread>any());
    when(block.getBlockThread()).thenReturn(new Thread());

    // Act
    BlockCanceler.cancelBlock(monitor, block);

    // Assert
    verify(block).cancelBlock(isA(DBRProgressMonitor.class), isA(Thread.class));
    verify(block).getBlockThread();
  }
}
