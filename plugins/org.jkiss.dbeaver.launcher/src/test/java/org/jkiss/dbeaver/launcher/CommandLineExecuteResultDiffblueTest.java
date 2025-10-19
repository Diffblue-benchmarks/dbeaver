package org.jkiss.dbeaver.launcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CommandLineExecuteResultDiffblueTest {
  /**
   * Test {@link CommandLineExecuteResult#CommandLineExecuteResult(boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return exitCode is minus one.
   * </ul>
   *
   * <p>Method under test: {@link CommandLineExecuteResult#CommandLineExecuteResult(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CommandLineExecuteResult.<init>(boolean)"})
  public void testNewCommandLineExecuteResult_whenFalse_thenReturnExitCodeIsMinusOne() {
    // Arrange and Act
    CommandLineExecuteResult actualCommandLineExecuteResult = new CommandLineExecuteResult(false);

    // Assert
    assertEquals((short) -1, actualCommandLineExecuteResult.exitCode());
    assertFalse(actualCommandLineExecuteResult.shutdown());
  }

  /**
   * Test {@link CommandLineExecuteResult#CommandLineExecuteResult(boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return exitCode is zero.
   * </ul>
   *
   * <p>Method under test: {@link CommandLineExecuteResult#CommandLineExecuteResult(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CommandLineExecuteResult.<init>(boolean)"})
  public void testNewCommandLineExecuteResult_whenTrue_thenReturnExitCodeIsZero() {
    // Arrange and Act
    CommandLineExecuteResult actualCommandLineExecuteResult = new CommandLineExecuteResult(true);

    // Assert
    assertEquals((short) 0, actualCommandLineExecuteResult.exitCode());
    assertTrue(actualCommandLineExecuteResult.shutdown());
  }
}
