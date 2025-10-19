package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBExceptionWithHistoryDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBExceptionWithHistory#DBExceptionWithHistory(String, Throwable, List)}
   *   <li>{@link DBExceptionWithHistory#getExceptions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBExceptionWithHistory.<init>(String, Throwable, List)",
    "List DBExceptionWithHistory.getExceptions()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Throwable cause = new Throwable();
    ArrayList<Throwable> exceptions = new ArrayList<>();

    // Act
    DBExceptionWithHistory actualDbExceptionWithHistory =
        new DBExceptionWithHistory("An error occurred", cause, exceptions);
    List<Throwable> actualExceptions = actualDbExceptionWithHistory.getExceptions();

    // Assert
    assertEquals("An error occurred", actualDbExceptionWithHistory.getMessage());
    assertEquals(0, actualDbExceptionWithHistory.getSuppressed().length);
    assertTrue(actualExceptions.isEmpty());
    assertSame(cause, actualDbExceptionWithHistory.getCause());
    assertSame(exceptions, actualExceptions);
  }
}
