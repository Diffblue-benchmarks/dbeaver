package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HelpUtilsDiffblueTest {
  /**
   * Test {@link HelpUtils#getHelpExternalReference(String)}.
   *
   * <p>Method under test: {@link HelpUtils#getHelpExternalReference(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String HelpUtils.getHelpExternalReference(String)"})
  public void testGetHelpExternalReference() {
    // Arrange, Act and Assert
    assertEquals("https://dbeaver.com/docs/dbeaver/42", HelpUtils.getHelpExternalReference("42"));
  }

  /**
   * Test {@link HelpUtils#getHelpGitHubReference(String)}.
   *
   * <p>Method under test: {@link HelpUtils#getHelpGitHubReference(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String HelpUtils.getHelpGitHubReference(String)"})
  public void testGetHelpGitHubReference() {
    // Arrange, Act and Assert
    assertEquals(
        "https://github.com/dbeaver/dbeaver/wiki/42", HelpUtils.getHelpGitHubReference("42"));
  }
}
