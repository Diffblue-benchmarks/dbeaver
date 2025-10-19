package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesResponseDiffblueTest {
  /**
   * Test new {@link OAIResponsesResponse} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesResponse}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesResponse.<init>()"})
  public void testNewOAIResponsesResponse() {
    // Arrange and Act
    OAIResponsesResponse actualOaiResponsesResponse = new OAIResponsesResponse();

    // Assert
    assertNull(actualOaiResponsesResponse.parallel_tool_calls);
    assertNull(actualOaiResponsesResponse.store);
    assertNull(actualOaiResponsesResponse.stream);
    assertNull(actualOaiResponsesResponse.temperature);
    assertNull(actualOaiResponsesResponse.topP);
    assertNull(actualOaiResponsesResponse.maxOutputTokens);
    assertNull(actualOaiResponsesResponse.maxToolCalls);
    assertNull(actualOaiResponsesResponse.topLogprobs);
    assertNull(actualOaiResponsesResponse.model);
    assertNull(actualOaiResponsesResponse.previous_response_id);
    assertNull(actualOaiResponsesResponse.promptCacheKey);
    assertNull(actualOaiResponsesResponse.safetyIdentifier);
    assertNull(actualOaiResponsesResponse.serviceTier);
    assertNull(actualOaiResponsesResponse.toolChoice);
    assertNull(actualOaiResponsesResponse.truncation);
    assertNull(actualOaiResponsesResponse.include);
    assertNull(actualOaiResponsesResponse.output);
    assertNull(actualOaiResponsesResponse.tools);
    assertNull(actualOaiResponsesResponse.usage);
    assertNull(actualOaiResponsesResponse.metadata);
    assertNull(actualOaiResponsesResponse.prompt);
    assertNull(actualOaiResponsesResponse.reasoning);
    assertNull(actualOaiResponsesResponse.stream_options);
    assertNull(actualOaiResponsesResponse.text);
  }
}
