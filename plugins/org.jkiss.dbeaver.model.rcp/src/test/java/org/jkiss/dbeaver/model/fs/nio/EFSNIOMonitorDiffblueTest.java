package org.jkiss.dbeaver.model.fs.nio;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EFSNIOMonitorDiffblueTest {
  /**
   * Test {@link EFSNIOMonitor#removeListener(EFSNIOListener)}.
   *
   * <ul>
   *   <li>When {@link EFSNIOListener}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOMonitor#removeListener(EFSNIOListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EFSNIOMonitor.removeListener(EFSNIOListener)"})
  public void testRemoveListener_whenEFSNIOListener_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(EFSNIOMonitor.removeListener(mock(EFSNIOListener.class)));
  }
}
