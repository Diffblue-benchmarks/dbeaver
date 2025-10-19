package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.ai.impl.ChatTruncator.Builder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ChatTruncatorDiffblueTest {
  /**
   * Test {@link ChatTruncator#builder()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ChatTruncator#builder()}
   *   <li>{@link ChatTruncator#maxTokens(int)}
   *   <li>{@link ChatTruncator#reserveForOverhead(int)}
   *   <li>{@link ChatTruncator#reserveForReply(int)}
   *   <li>{@link ChatTruncator#reserveForSystem(int)}
   *   <li>{@link ChatTruncator#tokenCounter(TokenCounter)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>()",
    "ChatTruncator Builder.build()",
    "Builder Builder.maxTokens(int)",
    "Builder Builder.reserveForOverhead(int)",
    "Builder Builder.reserveForReply(int)",
    "Builder Builder.reserveForSystem(int)",
    "Builder Builder.tokenCounter(TokenCounter)"
  })
  public void testBuilder() {
    // Arrange and Act
    Builder actualReserveForSystemResult =
        ChatTruncator.builder()
            .maxTokens(1)
            .reserveForOverhead(1)
            .reserveForReply(1)
            .reserveForSystem(1);
    Builder actualTokenCounterResult =
        actualReserveForSystemResult.tokenCounter(mock(TokenCounter.class));

    // Assert
    assertSame(actualReserveForSystemResult, actualTokenCounterResult);
  }

  /**
   * Test {@link ChatTruncator#truncate(List)}.
   *
   * <p>Method under test: {@link ChatTruncator#truncate(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ChatTruncator.truncate(List)"})
  public void testTruncate() {
    // Arrange
    ChatTruncator chatTruncator =
        ChatTruncator.builder()
            .tokenCounter(mock(TokenCounter.class))
            .maxTokens(1)
            .reserveForOverhead(Integer.MIN_VALUE)
            .reserveForReply(1)
            .reserveForSystem(1)
            .build();

    // Act and Assert
    assertTrue(chatTruncator.truncate(new ArrayList<>()).isEmpty());
  }
}
