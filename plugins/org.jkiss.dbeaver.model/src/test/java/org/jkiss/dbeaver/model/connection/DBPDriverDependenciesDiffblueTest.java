package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.connection.DBPDriverDependencies.DependencyNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPDriverDependenciesDiffblueTest {
  /**
   * Test DependencyNode {@link DependencyNode#DependencyNode(DependencyNode, DBPDriverLibrary)}.
   *
   * <ul>
   *   <li>Then return {@link DependencyNode#owner} {@link DependencyNode#owner} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DependencyNode#DependencyNode(DependencyNode, DBPDriverLibrary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DependencyNode.<init>(DependencyNode, DBPDriverLibrary)"})
  public void testDependencyNodeNewDependencyNode_thenReturnOwnerOwnerIsNull() {
    // Arrange
    DependencyNode owner = new DependencyNode(null, mock(DBPDriverLibrary.class));

    // Act
    DependencyNode actualDependencyNode = new DependencyNode(owner, mock(DBPDriverLibrary.class));

    // Assert
    DependencyNode dependencyNode = actualDependencyNode.owner;
    assertNull(dependencyNode.owner);
    assertEquals(0, dependencyNode.depth);
    assertEquals(1, actualDependencyNode.depth);
    assertFalse(dependencyNode.duplicate);
    assertTrue(dependencyNode.dependencies.isEmpty());
    assertSame(owner.library, dependencyNode.library);
  }

  /**
   * Test DependencyNode {@link DependencyNode#DependencyNode(DependencyNode, DBPDriverLibrary)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link DependencyNode#owner} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DependencyNode#DependencyNode(DependencyNode, DBPDriverLibrary)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DependencyNode.<init>(DependencyNode, DBPDriverLibrary)"})
  public void testDependencyNodeNewDependencyNode_whenNull_thenReturnOwnerIsNull() {
    // Arrange and Act
    DependencyNode actualDependencyNode = new DependencyNode(null, mock(DBPDriverLibrary.class));

    // Assert
    assertNull(actualDependencyNode.owner);
    assertEquals(0, actualDependencyNode.depth);
    assertFalse(actualDependencyNode.duplicate);
    assertTrue(actualDependencyNode.dependencies.isEmpty());
  }

  /**
   * Test DependencyNode {@link DependencyNode#toString()}.
   *
   * <p>Method under test: {@link DependencyNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DependencyNode.toString()"})
  public void testDependencyNodeToString() {
    // Arrange
    DependencyNode dependencyNode = new DependencyNode(null, mock(DBPDriverLibrary.class));

    // Act and Assert
    assertNull(dependencyNode.toString());
  }
}
