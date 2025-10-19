package org.jkiss.dbeaver.model.cli;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CLIUtilsDiffblueTest {
  /**
   * Test {@link CLIUtils#readValueFromFileOrSystemIn(CommandLineContext)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CLIUtils#readValueFromFileOrSystemIn(CommandLineContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String CLIUtils.readValueFromFileOrSystemIn(CommandLineContext)"})
  public void testReadValueFromFileOrSystemIn_thenReturnNull() throws CLIException {
    // Arrange, Act and Assert
    assertNull(
        CLIUtils.readValueFromFileOrSystemIn(
            new CommandLineContext(mock(ApplicationInstanceController.class))));
  }
}
