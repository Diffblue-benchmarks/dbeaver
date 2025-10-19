package org.jkiss.dbeaver.model.ai.prompt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIPromptAbstractDiffblueTest {
  /**
   * Test {@link AIPromptAbstract#addGoals(String[])}.
   *
   * <p>Method under test: {@link AIPromptAbstract#addGoals(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIPromptAbstract AIPromptAbstract.addGoals(String[])"})
  public void testAddGoals() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();

    // Act
    AIPromptAbstract actualAddGoalsResult = aiPromptGenerateSql.addGoals("Goals");

    // Assert
    String actualString = aiPromptGenerateSql.build();
    assertEquals("Goals:\n- Goals\n\nContext:\n", actualString);
    assertSame(aiPromptGenerateSql, actualAddGoalsResult);
  }

  /**
   * Test {@link AIPromptAbstract#addExamples(String[])}.
   *
   * <p>Method under test: {@link AIPromptAbstract#addExamples(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIPromptAbstract AIPromptAbstract.addExamples(String[])"})
  public void testAddExamples() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();

    // Act
    AIPromptAbstract actualAddExamplesResult = aiPromptGenerateSql.addExamples("Examples");

    // Assert
    String actualString = aiPromptGenerateSql.build();
    assertEquals("Goals:\n\nExamples:\n- Examples\n\nContext:\n", actualString);
    assertSame(aiPromptGenerateSql, actualAddExamplesResult);
  }

  /**
   * Test {@link AIPromptAbstract#addInstructions(String[])}.
   *
   * <p>Method under test: {@link AIPromptAbstract#addInstructions(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIPromptAbstract AIPromptAbstract.addInstructions(String[])"})
  public void testAddInstructions() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();

    // Act
    AIPromptAbstract actualAddInstructionsResult =
        aiPromptGenerateSql.addInstructions("Instructions");

    // Assert
    String actualString = aiPromptGenerateSql.build();
    assertEquals("Goals:\n\nInstructions:\n- Instructions\n\nContext:\n", actualString);
    assertSame(aiPromptGenerateSql, actualAddInstructionsResult);
  }

  /**
   * Test {@link AIPromptAbstract#addContexts(String[])}.
   *
   * <p>Method under test: {@link AIPromptAbstract#addContexts(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIPromptAbstract AIPromptAbstract.addContexts(String[])"})
  public void testAddContexts() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();

    // Act
    AIPromptAbstract actualAddContextsResult = aiPromptGenerateSql.addContexts("Contexts");

    // Assert
    String actualString = aiPromptGenerateSql.build();
    assertEquals("Goals:\n\nContext:\n- Contexts\n", actualString);
    assertSame(aiPromptGenerateSql, actualAddContextsResult);
  }

  /**
   * Test {@link AIPromptAbstract#addOutputFormats(String[])}.
   *
   * <p>Method under test: {@link AIPromptAbstract#addOutputFormats(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AIPromptAbstract AIPromptAbstract.addOutputFormats(String[])"})
  public void testAddOutputFormats() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();

    // Act
    AIPromptAbstract actualAddOutputFormatsResult =
        aiPromptGenerateSql.addOutputFormats("Output Formats");

    // Assert
    String actualString = aiPromptGenerateSql.build();
    assertEquals("Goals:\n\nContext:\n\nOutput Format:\n- Output Formats\n", actualString);
    assertSame(aiPromptGenerateSql, actualAddOutputFormatsResult);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Given {@link AIPromptGenerateSql} (default constructor).
   *   <li>Then return {@code Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_givenAIPromptGenerateSql_thenReturnGoalsContext() {
    // Arrange and Act
    String actualString = new AIPromptGenerateSql().build();

    // Assert
    assertEquals("Goals:\n\nContext:\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Goals:}.
   *   <li>Then return {@code Goals: - Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_givenArrayOfStringWithGoals_thenReturnGoalsGoalsContext() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n\nContext:\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Goals:}.
   *   <li>Then return {@code Goals: - Goals: - Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_givenArrayOfStringWithGoals_thenReturnGoalsGoalsGoalsContext() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addGoals("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n- Goals:\n\n\nContext:\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Context: - Goals:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsContextGoals() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addContexts("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n\nContext:\n- Goals:\n\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Context: - Goals: - Goals:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsContextGoalsGoals() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addContexts("Goals:\n");
    aiPromptGenerateSql.addContexts("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n\nContext:\n- Goals:\n\n- Goals:\n\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Context: Output Format: - Goals:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsContextOutputFormatGoals() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addOutputFormats("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n\nContext:\n\nOutput Format:\n- Goals:\n\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Context: Output Format: - Goals: - Goals:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsContextOutputFormatGoalsGoals() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addOutputFormats("Goals:\n");
    aiPromptGenerateSql.addOutputFormats("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals(
        "Goals:\n- Goals:\n\n\nContext:\n\nOutput Format:\n- Goals:\n\n- Goals:\n\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Examples: - Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsExamplesGoalsContext() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addExamples("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n\nExamples:\n- Goals:\n\n\nContext:\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Examples: - Goals: - Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsExamplesGoalsGoalsContext() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addExamples("Goals:\n");
    aiPromptGenerateSql.addExamples("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals(
        "Goals:\n- Goals:\n\n\nExamples:\n- Goals:\n\n- Goals:\n\n\nContext:\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Instructions: - Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsInstructionsGoalsContext() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addInstructions("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals("Goals:\n- Goals:\n\n\nInstructions:\n- Goals:\n\n\nContext:\n", actualString);
  }

  /**
   * Test {@link AIPromptAbstract#build()}.
   *
   * <ul>
   *   <li>Then return {@code Goals: - Goals: Instructions: - Goals: - Goals: Context:}.
   * </ul>
   *
   * <p>Method under test: {@link AIPromptAbstract#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AIPromptAbstract.build()"})
  public void testBuild_thenReturnGoalsGoalsInstructionsGoalsGoalsContext() {
    // Arrange
    AIPromptGenerateSql aiPromptGenerateSql = new AIPromptGenerateSql();
    aiPromptGenerateSql.addInstructions("Goals:\n");
    aiPromptGenerateSql.addInstructions("Goals:\n");
    aiPromptGenerateSql.addGoals("Goals:\n");

    // Act
    String actualString = aiPromptGenerateSql.build();

    // Assert
    assertEquals(
        "Goals:\n- Goals:\n\n\nInstructions:\n- Goals:\n\n- Goals:\n\n\nContext:\n", actualString);
  }
}
