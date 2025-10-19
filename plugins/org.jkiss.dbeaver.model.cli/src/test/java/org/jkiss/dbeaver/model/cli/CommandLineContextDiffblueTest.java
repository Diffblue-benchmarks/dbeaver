package org.jkiss.dbeaver.model.cli;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CommandLineContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CommandLineContext#CommandLineContext(ApplicationInstanceController)}
   *   <li>{@link CommandLineContext#getContext()}
   *   <li>{@link CommandLineContext#getInstanceController()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CommandLineContext.<init>(ApplicationInstanceController)",
    "Map CommandLineContext.getContext()",
    "ApplicationInstanceController CommandLineContext.getInstanceController()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ApplicationInstanceController instanceController = mock(ApplicationInstanceController.class);

    // Act
    CommandLineContext actualCommandLineContext = new CommandLineContext(instanceController);
    Map<String, Object> actualContext = actualCommandLineContext.getContext();
    ApplicationInstanceController actualInstanceController =
        actualCommandLineContext.getInstanceController();

    // Assert
    assertTrue(actualContext.isEmpty());
    assertSame(instanceController, actualInstanceController);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CommandLineContext#CommandLineContext(ApplicationInstanceController)}
   *   <li>{@link CommandLineContext#getContext()}
   *   <li>{@link CommandLineContext#getInstanceController()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CommandLineContext.<init>(ApplicationInstanceController)",
    "Map CommandLineContext.getContext()",
    "ApplicationInstanceController CommandLineContext.getInstanceController()"
  })
  public void testGettersAndSetters2() {
    // Arrange
    ApplicationInstanceController instanceController = mock(ApplicationInstanceController.class);

    // Act
    CommandLineContext actualCommandLineContext = new CommandLineContext(instanceController);
    Map<String, Object> actualContext = actualCommandLineContext.getContext();
    ApplicationInstanceController actualInstanceController =
        actualCommandLineContext.getInstanceController();

    // Assert
    assertTrue(actualContext.isEmpty());
    assertSame(instanceController, actualInstanceController);
  }
}
