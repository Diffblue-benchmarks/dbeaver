package org.jkiss.dbeaver.model.security.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMTooManySessionsExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMTooManySessionsException#SMTooManySessionsException(String)}
   *   <li>{@link SMTooManySessionsException#getErrorType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMTooManySessionsException.<init>(String)",
    "String SMTooManySessionsException.getErrorType()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMTooManySessionsException actualSmTooManySessionsException =
        new SMTooManySessionsException("An error occurred");
    String actualErrorType = actualSmTooManySessionsException.getErrorType();

    // Assert
    assertEquals("An error occurred", actualSmTooManySessionsException.getMessage());
    assertEquals("tooManySessions", actualErrorType);
    assertNull(actualSmTooManySessionsException.getCause());
    assertNull(actualSmTooManySessionsException.getExecutionContext());
    assertEquals(0, actualSmTooManySessionsException.getSuppressed().length);
    assertTrue(actualSmTooManySessionsException.hasMessage());
  }
}
