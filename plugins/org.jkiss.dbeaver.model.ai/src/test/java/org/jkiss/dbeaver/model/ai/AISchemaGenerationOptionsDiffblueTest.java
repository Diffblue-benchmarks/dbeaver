package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.AISchemaGenerationOptions.Builder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AISchemaGenerationOptionsDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#withMaxDbSnapshotTokens(int)}
   *   <li>{@link Builder#withSendColumnTypes(boolean)}
   *   <li>{@link Builder#withSendConstraints(boolean)}
   *   <li>{@link Builder#withSendForeignKeys(boolean)}
   *   <li>{@link Builder#withSendObjectComment(boolean)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AISchemaGenerationOptions Builder.build()",
    "Builder Builder.withMaxDbSnapshotTokens(int)",
    "Builder Builder.withSendColumnTypes(boolean)",
    "Builder Builder.withSendConstraints(boolean)",
    "Builder Builder.withSendForeignKeys(boolean)",
    "Builder Builder.withSendObjectComment(boolean)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    AISchemaGenerationOptions actualAiSchemaGenerationOptions =
        AISchemaGenerationOptions.builder()
            .withMaxDbSnapshotTokens(3)
            .withSendColumnTypes(true)
            .withSendConstraints(true)
            .withSendForeignKeys(true)
            .withSendObjectComment(true)
            .build();

    // Assert
    assertEquals(3, actualAiSchemaGenerationOptions.maxDbSnapshotTokens());
    assertTrue(actualAiSchemaGenerationOptions.sendColumnTypes());
    assertTrue(actualAiSchemaGenerationOptions.sendConstraints());
    assertTrue(actualAiSchemaGenerationOptions.sendForeignKeys());
    assertTrue(actualAiSchemaGenerationOptions.sendObjectComment());
  }
}
