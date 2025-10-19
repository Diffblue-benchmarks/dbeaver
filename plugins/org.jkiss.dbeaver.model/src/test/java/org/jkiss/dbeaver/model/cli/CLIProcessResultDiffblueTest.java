package org.jkiss.dbeaver.model.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.cli.CLIProcessResult.PostAction;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CLIProcessResultDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link CLIConstants#EXIT_CODE_ERROR}.
   *   <li>Then return Output is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CLIProcessResult#CLIProcessResult(PostAction, short)}
   *   <li>{@link CLIProcessResult#getExitCode()}
   *   <li>{@link CLIProcessResult#getOutput()}
   *   <li>{@link CLIProcessResult#getPostAction()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CLIProcessResult.<init>(PostAction, String, short)",
    "void CLIProcessResult.<init>(PostAction, short)",
    "short CLIProcessResult.getExitCode()",
    "String CLIProcessResult.getOutput()",
    "PostAction CLIProcessResult.getPostAction()"
  })
  public void testGettersAndSetters_whenExit_code_error_thenReturnOutputIsNull() {
    // Arrange and Act
    CLIProcessResult actualCliProcessResult =
        new CLIProcessResult(PostAction.START_INSTANCE, CLIConstants.EXIT_CODE_ERROR);
    short actualExitCode = actualCliProcessResult.getExitCode();
    String actualOutput = actualCliProcessResult.getOutput();

    // Assert
    assertNull(actualOutput);
    assertEquals(PostAction.START_INSTANCE, actualCliProcessResult.getPostAction());
    assertEquals(CLIConstants.EXIT_CODE_ERROR, actualExitCode);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Output}.
   *   <li>Then return {@code Output}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CLIProcessResult#CLIProcessResult(PostAction, String, short)}
   *   <li>{@link CLIProcessResult#getExitCode()}
   *   <li>{@link CLIProcessResult#getOutput()}
   *   <li>{@link CLIProcessResult#getPostAction()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CLIProcessResult.<init>(PostAction, String, short)",
    "void CLIProcessResult.<init>(PostAction, short)",
    "short CLIProcessResult.getExitCode()",
    "String CLIProcessResult.getOutput()",
    "PostAction CLIProcessResult.getPostAction()"
  })
  public void testGettersAndSetters_whenOutput_thenReturnOutput() {
    // Arrange and Act
    CLIProcessResult actualCliProcessResult =
        new CLIProcessResult(PostAction.START_INSTANCE, "Output", CLIConstants.EXIT_CODE_ERROR);
    short actualExitCode = actualCliProcessResult.getExitCode();
    String actualOutput = actualCliProcessResult.getOutput();

    // Assert
    assertEquals("Output", actualOutput);
    assertEquals(PostAction.START_INSTANCE, actualCliProcessResult.getPostAction());
    assertEquals(CLIConstants.EXIT_CODE_ERROR, actualExitCode);
  }

  /**
   * Test {@link CLIProcessResult#CLIProcessResult(PostAction)}.
   *
   * <p>Method under test: {@link CLIProcessResult#CLIProcessResult(PostAction)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CLIProcessResult.<init>(PostAction)"})
  public void testNewCLIProcessResult() {
    // Arrange and Act
    CLIProcessResult actualCliProcessResult = new CLIProcessResult(PostAction.START_INSTANCE);

    // Assert
    assertNull(actualCliProcessResult.getOutput());
    assertEquals(PostAction.START_INSTANCE, actualCliProcessResult.getPostAction());
    assertEquals(CLIConstants.EXIT_CODE_CONTINUE, actualCliProcessResult.getExitCode());
  }

  /**
   * Test {@link CLIProcessResult#CLIProcessResult(PostAction, String)}.
   *
   * <p>Method under test: {@link CLIProcessResult#CLIProcessResult(PostAction, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CLIProcessResult.<init>(PostAction, String)"})
  public void testNewCLIProcessResult2() {
    // Arrange and Act
    CLIProcessResult actualCliProcessResult =
        new CLIProcessResult(PostAction.START_INSTANCE, "Output");

    // Assert
    assertEquals("Output", actualCliProcessResult.getOutput());
    assertEquals(PostAction.START_INSTANCE, actualCliProcessResult.getPostAction());
    assertEquals(CLIConstants.EXIT_CODE_CONTINUE, actualCliProcessResult.getExitCode());
  }
}
