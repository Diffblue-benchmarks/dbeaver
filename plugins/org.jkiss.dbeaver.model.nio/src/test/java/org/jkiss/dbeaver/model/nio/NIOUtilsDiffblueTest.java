package org.jkiss.dbeaver.model.nio;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NIOUtilsDiffblueTest {
  /**
   * Test {@link NIOUtils#resolve(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code Base Path}.
   *   <li>Then return {@code Base PathSeparatorPath}.
   * </ul>
   *
   * <p>Method under test: {@link NIOUtils#resolve(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NIOUtils.resolve(String, String, String)"})
  public void testResolve_whenBasePath_thenReturnBasePathSeparatorPath() {
    // Arrange and Act
    String actualResolveResult = NIOUtils.resolve("Separator", "Base Path", "Path");

    // Assert
    assertEquals("Base PathSeparatorPath", actualResolveResult);
  }

  /**
   * Test {@link NIOUtils#resolve(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link NIOUtils#resolve(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NIOUtils.resolve(String, String, String)"})
  public void testResolve_whenNull_thenReturnPath() {
    // Arrange and Act
    String actualResolveResult = NIOUtils.resolve("Separator", null, "Path");

    // Assert
    assertEquals("Path", actualResolveResult);
  }
}
