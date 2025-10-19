package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BaseWorkspaceImplDiffblueTest {
  /**
   * Test {@link BaseWorkspaceImpl#readWorkspaceInfo(Path)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link BaseWorkspaceImpl#readWorkspaceInfo(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Properties BaseWorkspaceImpl.readWorkspaceInfo(Path)"})
  public void testReadWorkspaceInfo_whenPropertyIsJavaIoTmpdirIsTestTxt_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(
        BaseWorkspaceImpl.readWorkspaceInfo(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"))
            .isEmpty());
  }
}
