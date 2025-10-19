package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIMessageTypeDiffblueTest {
  /**
   * Test {@link AIMessageType#isLocal()}.
   *
   * <p>Method under test: {@link AIMessageType#isLocal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AIMessageType.isLocal()"})
  public void testIsLocal() {
    // Arrange, Act and Assert
    assertFalse(AIMessageType.valueOf("SYSTEM").isLocal());
  }
}
