package org.jkiss.dbeaver.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSecurityExceptionDiffblueTest {
  /**
   * Test {@link DBSecurityException#DBSecurityException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBSecurityException#DBSecurityException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSecurityException.<init>(String)",
    "void DBSecurityException.<init>(String, Throwable)"
  })
  public void testNewDBSecurityException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    DBSecurityException actualDbSecurityException = new DBSecurityException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbSecurityException.getMessage());
    assertNull(actualDbSecurityException.getCause());
    assertEquals(0, actualDbSecurityException.getSuppressed().length);
  }

  /**
   * Test {@link DBSecurityException#DBSecurityException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Cause is {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link DBSecurityException#DBSecurityException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSecurityException.<init>(String)",
    "void DBSecurityException.<init>(String, Throwable)"
  })
  public void testNewDBSecurityException_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBSecurityException actualDbSecurityException =
        new DBSecurityException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualDbSecurityException.getMessage());
    assertEquals(0, actualDbSecurityException.getSuppressed().length);
    assertSame(cause, actualDbSecurityException.getCause());
  }
}
