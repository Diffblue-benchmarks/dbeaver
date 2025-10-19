package org.jkiss.dbeaver.model.impl.admin.locks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LockGraphEdgeDiffblueTest {
  /**
   * Test {@link LockGraphEdge#setSource(LockGraphNode)}.
   *
   * <p>Method under test: {@link LockGraphEdge#setSource(LockGraphNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphEdge.setSource(LockGraphNode)"})
  public void testSetSource() {
    // Arrange
    LockGraphEdge lockGraphEdge = new LockGraphEdge();
    LockGraphNode newSource = new LockGraphNode("Dr", 1, 1);

    // Act
    lockGraphEdge.setSource(newSource);

    // Assert
    List<LockGraphEdge> sourceEdges = newSource.getSourceEdges();
    assertEquals(1, sourceEdges.size());
    assertSame(lockGraphEdge, sourceEdges.get(0));
    assertSame(newSource, lockGraphEdge.getSource());
  }

  /**
   * Test {@link LockGraphEdge#setSource(LockGraphNode)}.
   *
   * <p>Method under test: {@link LockGraphEdge#setSource(LockGraphNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphEdge.setSource(LockGraphNode)"})
  public void testSetSource2() {
    // Arrange
    LockGraphEdge lockGraphEdge = new LockGraphEdge();
    LockGraphNode newTarget = new LockGraphNode("Dr", 1, 1);
    lockGraphEdge.setTarget(newTarget);
    lockGraphEdge.setSource(new LockGraphNode("Dr", 1, 1));
    LockGraphNode newSource = new LockGraphNode("Dr", 1, 1);

    // Act
    lockGraphEdge.setSource(newSource);

    // Assert
    List<LockGraphEdge> sourceEdges = newSource.getSourceEdges();
    assertEquals(1, sourceEdges.size());
    LockGraphEdge getResult = sourceEdges.get(0);
    assertSame(newSource, getResult.getSource());
    assertSame(newTarget, getResult.getTarget());
  }

  /**
   * Test {@link LockGraphEdge#setSource(LockGraphNode)}.
   *
   * <ul>
   *   <li>Given {@link LockGraphEdge} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then {@link LockGraphEdge} (default constructor) Source is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LockGraphEdge#setSource(LockGraphNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphEdge.setSource(LockGraphNode)"})
  public void testSetSource_givenLockGraphEdge_whenNull_thenLockGraphEdgeSourceIsNull() {
    // Arrange
    LockGraphEdge lockGraphEdge = new LockGraphEdge();

    // Act
    lockGraphEdge.setSource(null);

    // Assert that nothing has changed
    assertNull(lockGraphEdge.getSource());
  }

  /**
   * Test {@link LockGraphEdge#setTarget(LockGraphNode)}.
   *
   * <p>Method under test: {@link LockGraphEdge#setTarget(LockGraphNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphEdge.setTarget(LockGraphNode)"})
  public void testSetTarget() {
    // Arrange
    LockGraphEdge lockGraphEdge = new LockGraphEdge();
    LockGraphNode newTarget = new LockGraphNode("Dr", 1, 1);

    // Act
    lockGraphEdge.setTarget(newTarget);

    // Assert
    List<LockGraphEdge> targetEdges = newTarget.getTargetEdges();
    assertEquals(1, targetEdges.size());
    assertSame(lockGraphEdge, targetEdges.get(0));
    assertSame(newTarget, lockGraphEdge.getTarget());
  }

  /**
   * Test {@link LockGraphEdge#setTarget(LockGraphNode)}.
   *
   * <p>Method under test: {@link LockGraphEdge#setTarget(LockGraphNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphEdge.setTarget(LockGraphNode)"})
  public void testSetTarget2() {
    // Arrange
    LockGraphEdge lockGraphEdge = new LockGraphEdge();
    LockGraphNode newSource = new LockGraphNode("Dr", 1, 1);
    lockGraphEdge.setSource(newSource);
    lockGraphEdge.setTarget(new LockGraphNode("Dr", 1, 1));
    LockGraphNode newTarget = new LockGraphNode("Dr", 1, 1);

    // Act
    lockGraphEdge.setTarget(newTarget);

    // Assert
    List<LockGraphEdge> targetEdges = newTarget.getTargetEdges();
    assertEquals(1, targetEdges.size());
    LockGraphEdge getResult = targetEdges.get(0);
    assertSame(newSource, getResult.getSource());
    assertSame(newTarget, getResult.getTarget());
  }

  /**
   * Test {@link LockGraphEdge#setTarget(LockGraphNode)}.
   *
   * <ul>
   *   <li>Given {@link LockGraphEdge} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then {@link LockGraphEdge} (default constructor) Target is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LockGraphEdge#setTarget(LockGraphNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LockGraphEdge.setTarget(LockGraphNode)"})
  public void testSetTarget_givenLockGraphEdge_whenNull_thenLockGraphEdgeTargetIsNull() {
    // Arrange
    LockGraphEdge lockGraphEdge = new LockGraphEdge();

    // Act
    lockGraphEdge.setTarget(null);

    // Assert that nothing has changed
    assertNull(lockGraphEdge.getTarget());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link LockGraphEdge}
   *   <li>{@link LockGraphEdge#getSource()}
   *   <li>{@link LockGraphEdge#getTarget()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LockGraphEdge.<init>()",
    "LockGraphNode LockGraphEdge.getSource()",
    "LockGraphNode LockGraphEdge.getTarget()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    LockGraphEdge actualLockGraphEdge = new LockGraphEdge();
    LockGraphNode actualSource = actualLockGraphEdge.getSource();

    // Assert
    assertNull(actualSource);
    assertNull(actualLockGraphEdge.getTarget());
  }
}
