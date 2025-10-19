package org.jkiss.dbeaver.model.ai.prompt;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIPromptGenerateSqlDiffblueTest {
  /**
   * Test {@link AIPromptGenerateSql#generatorId()}.
   *
   * <p>Method under test: {@link AIPromptGenerateSql#generatorId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIPromptGenerateSql.generatorId()"})
  public void testGeneratorId() {
    // Arrange, Act and Assert
    assertEquals(AIPromptGenerateSql.SQL_GENERATOR_ID, new AIPromptGenerateSql().generatorId());
  }
}
