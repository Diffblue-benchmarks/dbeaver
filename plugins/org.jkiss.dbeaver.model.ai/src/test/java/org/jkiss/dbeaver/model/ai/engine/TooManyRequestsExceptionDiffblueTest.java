package org.jkiss.dbeaver.model.ai.engine;

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

public class TooManyRequestsExceptionDiffblueTest {
  /**
   * Test {@link TooManyRequestsException#TooManyRequestsException(String)}.
   *
   * <p>Method under test: {@link TooManyRequestsException#TooManyRequestsException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TooManyRequestsException.<init>(String)"})
  public void testNewTooManyRequestsException() {
    // Arrange and Act
    TooManyRequestsException actualTooManyRequestsException =
        new TooManyRequestsException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualTooManyRequestsException.getMessage());
    assertNull(actualTooManyRequestsException.getCause());
    assertNull(actualTooManyRequestsException.getExecutionContext());
    assertEquals(0, actualTooManyRequestsException.getSuppressed().length);
    assertTrue(actualTooManyRequestsException.hasMessage());
  }

  /**
   * Test {@link TooManyRequestsException#TooManyRequestsException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link TooManyRequestsException#TooManyRequestsException(String,
   * Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TooManyRequestsException.<init>(String, Throwable)"})
  public void testNewTooManyRequestsException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    TooManyRequestsException actualTooManyRequestsException =
        new TooManyRequestsException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualTooManyRequestsException.getLocalizedMessage());
    assertEquals("An error occurred", actualTooManyRequestsException.getMessage());
    assertTrue(actualTooManyRequestsException.hasMessage());
    assertSame(cause, actualTooManyRequestsException.getCause());
  }

  /**
   * Test {@link TooManyRequestsException#TooManyRequestsException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TooManyRequestsException#TooManyRequestsException(String,
   * Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TooManyRequestsException.<init>(String, Throwable)"})
  public void testNewTooManyRequestsException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    TooManyRequestsException actualTooManyRequestsException =
        new TooManyRequestsException(null, cause);

    // Assert
    assertNull(actualTooManyRequestsException.getLocalizedMessage());
    assertNull(actualTooManyRequestsException.getMessage());
    assertFalse(actualTooManyRequestsException.hasMessage());
    assertSame(cause, actualTooManyRequestsException.getCause());
  }
}
