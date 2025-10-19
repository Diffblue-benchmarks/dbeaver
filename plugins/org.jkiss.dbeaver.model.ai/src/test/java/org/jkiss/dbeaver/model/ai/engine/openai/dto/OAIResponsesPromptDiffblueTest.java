package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesPromptDiffblueTest {
  /**
   * Test new {@link OAIResponsesPrompt} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesPrompt}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesPrompt.<init>()"})
  public void testNewOAIResponsesPrompt() {
    // Arrange and Act
    OAIResponsesPrompt actualOaiResponsesPrompt = new OAIResponsesPrompt();

    // Assert
    assertNull(actualOaiResponsesPrompt.id);
    assertNull(actualOaiResponsesPrompt.version);
    assertNull(actualOaiResponsesPrompt.variables);
  }
}
