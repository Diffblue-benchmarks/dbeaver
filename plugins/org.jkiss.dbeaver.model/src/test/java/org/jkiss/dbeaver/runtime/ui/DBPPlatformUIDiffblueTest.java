package org.jkiss.dbeaver.runtime.ui;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.runtime.ui.DBPPlatformUI.UserChoiceResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPPlatformUIDiffblueTest {
  /**
   * Test UserChoiceResponse {@link UserChoiceResponse#UserChoiceResponse(int, Integer)}.
   *
   * <p>Method under test: {@link UserChoiceResponse#UserChoiceResponse(int, Integer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void UserChoiceResponse.<init>(int, Integer)"})
  public void testUserChoiceResponseNewUserChoiceResponse() {
    // Arrange, Act and Assert
    assertEquals(1, new UserChoiceResponse(1, 1).forAllChoiceIndex.intValue());
  }
}
