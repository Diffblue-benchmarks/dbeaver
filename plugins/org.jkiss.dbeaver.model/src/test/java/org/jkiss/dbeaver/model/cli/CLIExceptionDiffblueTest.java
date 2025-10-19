package org.jkiss.dbeaver.model.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CLIExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link CLIConstants#EXIT_CODE_ERROR}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CLIException#CLIException(String, short)}
   *   <li>{@link CLIException#getExitCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CLIException.<init>(String, Throwable, short)",
    "void CLIException.<init>(String, short)",
    "short CLIException.getExitCode()"
  })
  public void testGettersAndSetters_whenExit_code_error_thenReturnCauseIsNull() {
    // Arrange and Act
    CLIException actualCliException =
        new CLIException("An error occurred", CLIConstants.EXIT_CODE_ERROR);
    short actualExitCode = actualCliException.getExitCode();

    // Assert
    assertEquals("An error occurred", actualCliException.getMessage());
    assertNull(actualCliException.getCause());
    assertEquals(0, actualCliException.getSuppressed().length);
    assertEquals(CLIConstants.EXIT_CODE_ERROR, actualExitCode);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Cause is {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CLIException#CLIException(String, Throwable, short)}
   *   <li>{@link CLIException#getExitCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CLIException.<init>(String, Throwable, short)",
    "void CLIException.<init>(String, short)",
    "short CLIException.getExitCode()"
  })
  public void testGettersAndSetters_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CLIException actualCliException =
        new CLIException("An error occurred", cause, CLIConstants.EXIT_CODE_ERROR);
    short actualExitCode = actualCliException.getExitCode();

    // Assert
    assertEquals("An error occurred", actualCliException.getMessage());
    assertEquals(0, actualCliException.getSuppressed().length);
    assertEquals(CLIConstants.EXIT_CODE_ERROR, actualExitCode);
    assertSame(cause, actualCliException.getCause());
  }
}
