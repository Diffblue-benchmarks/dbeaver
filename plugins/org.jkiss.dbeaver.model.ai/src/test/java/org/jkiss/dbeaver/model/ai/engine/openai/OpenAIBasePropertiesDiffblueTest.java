package org.jkiss.dbeaver.model.ai.engine.openai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OpenAIBasePropertiesDiffblueTest {
  /**
   * Test {@link OpenAIBaseProperties#isStreamingEnabled()}.
   *
   * <p>Method under test: {@link OpenAIBaseProperties#isStreamingEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIBaseProperties.isStreamingEnabled()"})
  public void testIsStreamingEnabled() {
    // Arrange, Act and Assert
    assertTrue(new OpenAIProperties().isStreamingEnabled());
  }

  /**
   * Test {@link OpenAIBaseProperties#isValidConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Token is empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIBaseProperties#isValidConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIBaseProperties.isValidConfiguration()"})
  public void testIsValidConfiguration_givenOpenAIPropertiesTokenIsEmptyString_thenReturnFalse() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setToken("");

    // Act and Assert
    assertFalse(openAIProperties.isValidConfiguration());
  }

  /**
   * Test {@link OpenAIBaseProperties#isValidConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor) Token is {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIBaseProperties#isValidConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIBaseProperties.isValidConfiguration()"})
  public void testIsValidConfiguration_givenOpenAIPropertiesTokenIsFoo_thenReturnTrue() {
    // Arrange
    OpenAIProperties openAIProperties = new OpenAIProperties();
    openAIProperties.setToken("foo");

    // Act and Assert
    assertTrue(openAIProperties.isValidConfiguration());
  }

  /**
   * Test {@link OpenAIBaseProperties#isValidConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link OpenAIProperties} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OpenAIBaseProperties#isValidConfiguration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpenAIBaseProperties.isValidConfiguration()"})
  public void testIsValidConfiguration_givenOpenAIProperties_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new OpenAIProperties().isValidConfiguration());
  }
}
