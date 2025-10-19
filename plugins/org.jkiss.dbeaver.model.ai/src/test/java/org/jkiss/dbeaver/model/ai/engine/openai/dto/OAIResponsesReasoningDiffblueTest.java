package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesReasoningDiffblueTest {
  /**
   * Test new {@link OAIResponsesReasoning} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesReasoning}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesReasoning.<init>()"})
  public void testNewOAIResponsesReasoning() {
    // Arrange and Act
    OAIResponsesReasoning actualOaiResponsesReasoning = new OAIResponsesReasoning();

    // Assert
    assertNull(actualOaiResponsesReasoning.effort);
    assertNull(actualOaiResponsesReasoning.generateSummary);
    assertNull(actualOaiResponsesReasoning.summary);
  }
}
