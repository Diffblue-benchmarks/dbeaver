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

public class SMAccessTokenExpiredExceptionDiffblueTest {
  /**
   * Test {@link SMAccessTokenExpiredException#SMAccessTokenExpiredException(String)}.
   *
   * <p>Method under test: {@link
   * SMAccessTokenExpiredException#SMAccessTokenExpiredException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMAccessTokenExpiredException.<init>(String)"})
  public void testNewSMAccessTokenExpiredException() {
    // Arrange and Act
    SMAccessTokenExpiredException actualSmAccessTokenExpiredException =
        new SMAccessTokenExpiredException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualSmAccessTokenExpiredException.getMessage());
    assertNull(actualSmAccessTokenExpiredException.getCause());
    assertNull(actualSmAccessTokenExpiredException.getExecutionContext());
    assertEquals(0, actualSmAccessTokenExpiredException.getSuppressed().length);
    assertTrue(actualSmAccessTokenExpiredException.hasMessage());
  }

  /**
   * Test {@link SMAccessTokenExpiredException#SMAccessTokenExpiredException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SMAccessTokenExpiredException#SMAccessTokenExpiredException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMAccessTokenExpiredException.<init>(String, Throwable)"})
  public void testNewSMAccessTokenExpiredException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SMAccessTokenExpiredException actualSmAccessTokenExpiredException =
        new SMAccessTokenExpiredException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualSmAccessTokenExpiredException.getLocalizedMessage());
    assertEquals("An error occurred", actualSmAccessTokenExpiredException.getMessage());
    assertTrue(actualSmAccessTokenExpiredException.hasMessage());
    assertSame(cause, actualSmAccessTokenExpiredException.getCause());
  }

  /**
   * Test {@link SMAccessTokenExpiredException#SMAccessTokenExpiredException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SMAccessTokenExpiredException#SMAccessTokenExpiredException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMAccessTokenExpiredException.<init>(String, Throwable)"})
  public void testNewSMAccessTokenExpiredException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    SMAccessTokenExpiredException actualSmAccessTokenExpiredException =
        new SMAccessTokenExpiredException(null, cause);

    // Assert
    assertNull(actualSmAccessTokenExpiredException.getLocalizedMessage());
    assertNull(actualSmAccessTokenExpiredException.getMessage());
    assertFalse(actualSmAccessTokenExpiredException.hasMessage());
    assertSame(cause, actualSmAccessTokenExpiredException.getCause());
  }
}
