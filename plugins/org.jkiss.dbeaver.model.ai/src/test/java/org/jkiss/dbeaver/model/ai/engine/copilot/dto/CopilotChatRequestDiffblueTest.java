package org.jkiss.dbeaver.model.ai.engine.copilot.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.ai.engine.copilot.dto.CopilotChatRequest.Builder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CopilotChatRequestDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#withIntent(boolean)}
   *   <li>{@link Builder#withMessages(List)}
   *   <li>{@link Builder#withModel(String)}
   *   <li>{@link Builder#withN(int)}
   *   <li>{@link Builder#withStream(boolean)}
   *   <li>{@link Builder#withTemperature(double)}
   *   <li>{@link Builder#withTopP(int)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "CopilotChatRequest Builder.build()",
    "Builder Builder.withIntent(boolean)",
    "Builder Builder.withMessages(List)",
    "Builder Builder.withModel(String)",
    "Builder Builder.withN(int)",
    "Builder Builder.withStream(boolean)",
    "Builder Builder.withTemperature(double)",
    "Builder Builder.withTopP(int)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    Builder actualWithIntentResult = CopilotChatRequest.builder().withIntent(true);
    ArrayList<CopilotMessage> messages = new ArrayList<>();
    CopilotChatRequest actualCopilotChatRequest =
        actualWithIntentResult
            .withMessages(messages)
            .withModel("Model")
            .withN(1)
            .withStream(true)
            .withTemperature(10.0d)
            .withTopP(1)
            .build();

    // Assert
    assertEquals("Model", actualCopilotChatRequest.model());
    assertEquals(1, actualCopilotChatRequest.n());
    assertEquals(1, actualCopilotChatRequest.topP());
    assertEquals(10.0d, actualCopilotChatRequest.temperature(), 0.0);
    List<CopilotMessage> messagesResult = actualCopilotChatRequest.messages();
    assertTrue(messagesResult.isEmpty());
    assertTrue(actualCopilotChatRequest.intent());
    assertTrue(actualCopilotChatRequest.stream());
    assertSame(messages, messagesResult);
  }
}
