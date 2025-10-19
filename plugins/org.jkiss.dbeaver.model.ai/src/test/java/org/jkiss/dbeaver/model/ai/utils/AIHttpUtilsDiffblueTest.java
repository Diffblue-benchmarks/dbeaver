package org.jkiss.dbeaver.model.ai.utils;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIHttpUtilsDiffblueTest {
  /**
   * Test {@link AIHttpUtils#resolve(String, String[])}.
   *
   * <p>Method under test: {@link AIHttpUtils#resolve(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.net.URI AIHttpUtils.resolve(String, String[])"})
  public void testResolve() throws DBException {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        AIHttpUtils.resolve("https://example.org/example", "https://example.org/example")
            .toString());
  }
}
