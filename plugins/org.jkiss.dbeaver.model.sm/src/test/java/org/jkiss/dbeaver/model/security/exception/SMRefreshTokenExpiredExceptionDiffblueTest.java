package org.jkiss.dbeaver.model.security.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMRefreshTokenExpiredExceptionDiffblueTest {
  /**
   * Test {@link SMRefreshTokenExpiredException#SMRefreshTokenExpiredException(String)}.
   *
   * <p>Method under test: {@link
   * SMRefreshTokenExpiredException#SMRefreshTokenExpiredException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMRefreshTokenExpiredException.<init>(String)"})
  public void testNewSMRefreshTokenExpiredException() {
    // Arrange and Act
    SMRefreshTokenExpiredException actualSmRefreshTokenExpiredException =
        new SMRefreshTokenExpiredException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualSmRefreshTokenExpiredException.getMessage());
    assertNull(actualSmRefreshTokenExpiredException.getCause());
    assertNull(actualSmRefreshTokenExpiredException.getExecutionContext());
    assertEquals(0, actualSmRefreshTokenExpiredException.getSuppressed().length);
    assertTrue(actualSmRefreshTokenExpiredException.hasMessage());
  }

  /**
   * Test {@link SMRefreshTokenExpiredException#SMRefreshTokenExpiredException(String, Throwable)}.
   *
   * <p>Method under test: {@link
   * SMRefreshTokenExpiredException#SMRefreshTokenExpiredException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMRefreshTokenExpiredException.<init>(String, Throwable)"})
  public void testNewSMRefreshTokenExpiredException2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SMRefreshTokenExpiredException actualSmRefreshTokenExpiredException =
        new SMRefreshTokenExpiredException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualSmRefreshTokenExpiredException.getLocalizedMessage());
    assertEquals("An error occurred", actualSmRefreshTokenExpiredException.getMessage());
    assertTrue(actualSmRefreshTokenExpiredException.hasMessage());
    assertSame(cause, actualSmRefreshTokenExpiredException.getCause());
  }

  /**
   * Test {@link SMRefreshTokenExpiredException#SMRefreshTokenExpiredException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SMRefreshTokenExpiredException#SMRefreshTokenExpiredException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMRefreshTokenExpiredException.<init>(String, Throwable)"})
  public void testNewSMRefreshTokenExpiredException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    SMRefreshTokenExpiredException actualSmRefreshTokenExpiredException =
        new SMRefreshTokenExpiredException(null, cause);

    // Assert
    assertNull(actualSmRefreshTokenExpiredException.getLocalizedMessage());
    assertNull(actualSmRefreshTokenExpiredException.getMessage());
    assertFalse(actualSmRefreshTokenExpiredException.hasMessage());
    assertSame(cause, actualSmRefreshTokenExpiredException.getCause());
  }
}
