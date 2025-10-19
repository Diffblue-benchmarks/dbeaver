package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVExceptionDiffblueTest {
  /**
   * Test {@link DBVException#DBVException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVException#DBVException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVException.<init>(String)",
    "void DBVException.<init>(String, Throwable)"
  })
  public void testNewDBVException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    DBVException actualDbvException = new DBVException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbvException.getMessage());
    assertNull(actualDbvException.getCause());
    assertEquals(0, actualDbvException.getSuppressed().length);
  }

  /**
   * Test {@link DBVException#DBVException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Cause is {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVException#DBVException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVException.<init>(String)",
    "void DBVException.<init>(String, Throwable)"
  })
  public void testNewDBVException_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBVException actualDbvException = new DBVException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualDbvException.getMessage());
    assertEquals(0, actualDbvException.getSuppressed().length);
    assertSame(cause, actualDbvException.getCause());
  }
}
