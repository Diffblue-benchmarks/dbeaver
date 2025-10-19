package org.jkiss.dbeaver.model.impl.admin.locks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.admin.locks.DBAServerLock;
import org.jkiss.dbeaver.model.impl.admin.locks.LockGraphNode.LevelPosition;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LockGraphNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LockGraphNode#LockGraphNode(String, int, int)}
   *   <li>{@link LockGraphNode#setLevel(int)}
   *   <li>{@link LockGraphNode#setLevelPosition(LevelPosition)}
   *   <li>{@link LockGraphNode#setSpan(int)}
   *   <li>{@link LockGraphNode#getLevel()}
   *   <li>{@link LockGraphNode#getLevelPosition()}
   *   <li>{@link LockGraphNode#getLock()}
   *   <li>{@link LockGraphNode#getSourceEdges()}
   *   <li>{@link LockGraphNode#getSpan()}
   *   <li>{@link LockGraphNode#getTargetEdges()}
   *   <li>{@link LockGraphNode#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LockGraphNode.<init>(String, int, int)",
    "int LockGraphNode.getLevel()",
    "LevelPosition LockGraphNode.getLevelPosition()",
    "DBAServerLock LockGraphNode.getLock()",
    "List LockGraphNode.getSourceEdges()",
    "int LockGraphNode.getSpan()",
    "List LockGraphNode.getTargetEdges()",
    "String LockGraphNode.getTitle()",
    "void LockGraphNode.setLevel(int)",
    "void LockGraphNode.setLevelPosition(LevelPosition)",
    "void LockGraphNode.setSpan(int)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    LockGraphNode actualLockGraphNode = new LockGraphNode("Dr", 1, 1);
    actualLockGraphNode.setLevel(1);
    actualLockGraphNode.setLevelPosition(LevelPosition.LEFT);
    actualLockGraphNode.setSpan(1);
    int actualLevel = actualLockGraphNode.getLevel();
    LevelPosition actualLevelPosition = actualLockGraphNode.getLevelPosition();
    DBAServerLock actualLock = actualLockGraphNode.getLock();
    List<LockGraphEdge> actualSourceEdges = actualLockGraphNode.getSourceEdges();
    int actualSpan = actualLockGraphNode.getSpan();
    List<LockGraphEdge> actualTargetEdges = actualLockGraphNode.getTargetEdges();

    // Assert
    assertEquals("Dr", actualLockGraphNode.getTitle());
    assertNull(actualLock);
    assertEquals(1, actualLevel);
    assertEquals(1, actualSpan);
    assertEquals(LevelPosition.LEFT, actualLevelPosition);
    assertTrue(actualSourceEdges.isEmpty());
    assertTrue(actualTargetEdges.isEmpty());
  }

  /**
   * Test {@link LockGraphNode#LockGraphNode(DBAServerLock)}.
   *
   * <p>Method under test: {@link LockGraphNode#LockGraphNode(DBAServerLock)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphNode.<init>(DBAServerLock)"})
  public void testNewLockGraphNode() {
    // Arrange
    DBAServerLock lock = mock(DBAServerLock.class);
    when(lock.getTitle()).thenReturn("Dr");

    // Act
    LockGraphNode actualLockGraphNode = new LockGraphNode(lock);

    // Assert
    verify(lock).getTitle();
    assertEquals("Dr", actualLockGraphNode.getTitle());
    assertEquals(0, actualLockGraphNode.getLevel());
    assertEquals(0, actualLockGraphNode.getSpan());
    assertEquals(LevelPosition.CENTER, actualLockGraphNode.getLevelPosition());
    assertTrue(actualLockGraphNode.getSourceEdges().isEmpty());
    assertTrue(actualLockGraphNode.getTargetEdges().isEmpty());
    assertSame(lock, actualLockGraphNode.getLock());
  }
}
