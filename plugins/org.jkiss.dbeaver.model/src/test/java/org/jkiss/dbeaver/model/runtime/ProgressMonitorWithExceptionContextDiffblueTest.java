package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProgressMonitorWithExceptionContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       ProgressMonitorWithExceptionContext#ProgressMonitorWithExceptionContext(DBRProgressMonitor)}
   *   <li>{@link ProgressMonitorWithExceptionContext#getExceptions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProgressMonitorWithExceptionContext.<init>(DBRProgressMonitor)",
    "List ProgressMonitorWithExceptionContext.getExceptions()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(
        new ProgressMonitorWithExceptionContext(new LoggingProgressMonitor())
            .getExceptions()
            .isEmpty());
  }

  /**
   * Test {@link ProgressMonitorWithExceptionContext#addException(Exception)}.
   *
   * <p>Method under test: {@link ProgressMonitorWithExceptionContext#addException(Exception)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProgressMonitorWithExceptionContext.addException(Exception)"})
  public void testAddException() {
    // Arrange
    ProgressMonitorWithExceptionContext progressMonitorWithExceptionContext =
        new ProgressMonitorWithExceptionContext(new LoggingProgressMonitor());
    Exception e = new Exception();

    // Act
    progressMonitorWithExceptionContext.addException(e);

    // Assert
    List<Exception> exceptions = progressMonitorWithExceptionContext.getExceptions();
    assertEquals(1, exceptions.size());
    assertSame(e, exceptions.get(0));
  }
}
