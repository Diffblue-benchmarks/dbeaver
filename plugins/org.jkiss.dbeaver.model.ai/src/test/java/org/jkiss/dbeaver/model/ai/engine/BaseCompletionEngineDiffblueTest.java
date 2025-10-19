package org.jkiss.dbeaver.model.ai.engine;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.engine.copilot.CopilotCompletionEngine;
import org.jkiss.dbeaver.model.ai.engine.copilot.CopilotProperties;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BaseCompletionEngineDiffblueTest {
  /**
   * Test {@link BaseCompletionEngine#getProperties()}.
   *
   * <p>Method under test: {@link BaseCompletionEngine#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.ai.engine.AIEngineProperties BaseCompletionEngine.getProperties()"
  })
  public void testGetProperties() {
    // Arrange
    CopilotCompletionEngine copilotCompletionEngine =
        new CopilotCompletionEngine(new CopilotProperties());

    // Act
    CopilotProperties actualProperties = copilotCompletionEngine.getProperties();

    // Assert
    assertSame(copilotCompletionEngine.properties, actualProperties);
  }
}
