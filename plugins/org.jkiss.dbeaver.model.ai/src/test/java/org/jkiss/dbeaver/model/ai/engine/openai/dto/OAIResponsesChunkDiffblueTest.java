package org.jkiss.dbeaver.model.ai.engine.openai.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OAIResponsesChunkDiffblueTest {
  /**
   * Test new {@link OAIResponsesChunk} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OAIResponsesChunk}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OAIResponsesChunk.<init>()"})
  public void testNewOAIResponsesChunk() {
    // Arrange and Act
    OAIResponsesChunk actualOaiResponsesChunk = new OAIResponsesChunk();

    // Assert
    assertNull(actualOaiResponsesChunk.sequenceNumber);
    assertNull(actualOaiResponsesChunk.delta);
    assertNull(actualOaiResponsesChunk.itemId);
    assertNull(actualOaiResponsesChunk.type);
    assertNull(actualOaiResponsesChunk.item);
    assertNull(actualOaiResponsesChunk.response);
    assertEquals(0, actualOaiResponsesChunk.contentIndex);
    assertEquals(0, actualOaiResponsesChunk.outputIndex);
  }
}
