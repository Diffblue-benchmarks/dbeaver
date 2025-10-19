package org.jkiss.dbeaver.model.impl.admin.locks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.admin.locks.DBAServerLock;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LockGraphDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LockGraph#LockGraph(DBAServerLock)}
   *   <li>{@link LockGraph#setMaxWidth(int)}
   *   <li>{@link LockGraph#setSelection(LockGraphNode)}
   *   <li>{@link LockGraph#getLockRoot()}
   *   <li>{@link LockGraph#getMaxWidth()}
   *   <li>{@link LockGraph#getNodes()}
   *   <li>{@link LockGraph#getSelection()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LockGraph.<init>(DBAServerLock)",
    "DBAServerLock LockGraph.getLockRoot()",
    "int LockGraph.getMaxWidth()",
    "List LockGraph.getNodes()",
    "LockGraphNode LockGraph.getSelection()",
    "void LockGraph.setMaxWidth(int)",
    "void LockGraph.setSelection(LockGraphNode)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBAServerLock lockRoot = mock(DBAServerLock.class);

    // Act
    LockGraph actualLockGraph = new LockGraph(lockRoot);
    actualLockGraph.setMaxWidth(1);
    LockGraphNode selection = new LockGraphNode("Dr", 1, 1);
    actualLockGraph.setSelection(selection);
    DBAServerLock actualLockRoot = actualLockGraph.getLockRoot();
    int actualMaxWidth = actualLockGraph.getMaxWidth();
    List<LockGraphNode> actualNodes = actualLockGraph.getNodes();
    LockGraphNode actualSelection = actualLockGraph.getSelection();

    // Assert
    assertEquals(1, actualMaxWidth);
    assertTrue(actualNodes.isEmpty());
    assertSame(selection, actualSelection);
    assertSame(lockRoot, actualLockRoot);
  }
}
