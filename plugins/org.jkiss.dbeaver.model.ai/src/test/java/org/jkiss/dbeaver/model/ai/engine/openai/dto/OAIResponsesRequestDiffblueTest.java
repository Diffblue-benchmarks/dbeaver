package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesRequestDiffblueTest {
  /**
   * Test new {@link OAIResponsesRequest} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesRequest}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesRequest.<init>()"})
  public void testNewOAIResponsesRequest() {
    // Arrange and Act
    OAIResponsesRequest actualOaiResponsesRequest = new OAIResponsesRequest();

    // Assert
    assertNull(actualOaiResponsesRequest.parallel_tool_calls);
    assertNull(actualOaiResponsesRequest.store);
    assertNull(actualOaiResponsesRequest.stream);
    assertNull(actualOaiResponsesRequest.temperature);
    assertNull(actualOaiResponsesRequest.topP);
    assertNull(actualOaiResponsesRequest.maxOutputTokens);
    assertNull(actualOaiResponsesRequest.maxToolCalls);
    assertNull(actualOaiResponsesRequest.topLogprobs);
    assertNull(actualOaiResponsesRequest.model);
    assertNull(actualOaiResponsesRequest.previous_response_id);
    assertNull(actualOaiResponsesRequest.promptCacheKey);
    assertNull(actualOaiResponsesRequest.safetyIdentifier);
    assertNull(actualOaiResponsesRequest.serviceTier);
    assertNull(actualOaiResponsesRequest.toolChoice);
    assertNull(actualOaiResponsesRequest.truncation);
    assertNull(actualOaiResponsesRequest.include);
    assertNull(actualOaiResponsesRequest.input);
    assertNull(actualOaiResponsesRequest.tools);
    assertNull(actualOaiResponsesRequest.metadata);
    assertNull(actualOaiResponsesRequest.prompt);
    assertNull(actualOaiResponsesRequest.reasoning);
    assertNull(actualOaiResponsesRequest.stream_options);
    assertNull(actualOaiResponsesRequest.text);
  }
}
