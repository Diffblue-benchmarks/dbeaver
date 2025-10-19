package org.jkiss.dbeaver.model.ai.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIModelDiffblueTest {
  /**
   * Test {@link AIModel#AIModel(String, Integer, Set)}.
   *
   * <p>Method under test: {@link AIModel#AIModel(String, Integer, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIModel.<init>(String, Integer, Set)"})
  public void testNewAIModel() {
    // Arrange
    HashSet<AIModelFeature> features = new HashSet<>();

    // Act
    AIModel actualAiModel = new AIModel("Name", 3, features);

    // Assert
    assertEquals("Name", actualAiModel.name());
    assertEquals(0.0d, actualAiModel.defaultTemperature(), 0.0);
    assertEquals(3, actualAiModel.contextWindowSize().intValue());
    Set<AIModelFeature> featuresResult = actualAiModel.features();
    assertTrue(featuresResult.isEmpty());
    assertSame(features, featuresResult);
  }
}
