package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class MonitorRunnableContextDiffblueTest {
  /**
   * Test {@link MonitorRunnableContext#run(boolean, boolean, DBRRunnableWithProgress)}.
   *
   * <ul>
   *   <li>Then throw {@link InvocationTargetException}.
   * </ul>
   *
   * <p>Method under test: {@link MonitorRunnableContext#run(boolean, boolean,
   * DBRRunnableWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonitorRunnableContext.run(boolean, boolean, DBRRunnableWithProgress)"})
  public void testRun_thenThrowInvocationTargetException()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    MonitorRunnableContext monitorRunnableContext =
        new MonitorRunnableContext(new LoggingProgressMonitor());

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doThrow(new InvocationTargetException(new Throwable(), "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());

    // Act and Assert
    assertThrows(
        InvocationTargetException.class, () -> monitorRunnableContext.run(true, true, runnable));
    verify(runnable).run(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link MonitorRunnableContext#run(boolean, boolean, DBRRunnableWithProgress)}.
   *
   * <ul>
   *   <li>When {@link DBRRunnableWithProgress} {@link
   *       DBRRunnableWithProgress#run(DBRProgressMonitor)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link MonitorRunnableContext#run(boolean, boolean,
   * DBRRunnableWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonitorRunnableContext.run(boolean, boolean, DBRRunnableWithProgress)"})
  public void testRun_whenDBRRunnableWithProgressRunDoesNothing()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    MonitorRunnableContext monitorRunnableContext =
        new MonitorRunnableContext(new LoggingProgressMonitor());

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doNothing().when(runnable).run(Mockito.<DBRProgressMonitor>any());

    // Act
    monitorRunnableContext.run(true, true, runnable);

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
  }
}
