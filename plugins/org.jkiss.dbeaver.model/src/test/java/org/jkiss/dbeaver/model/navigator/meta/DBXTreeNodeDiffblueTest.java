package org.jkiss.dbeaver.model.navigator.meta;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBXTreeNodeDiffblueTest {
  /**
   * Test {@link DBXTreeNode#hasNonFolderNode(List)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeNode#hasNonFolderNode(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBXTreeNode.hasNonFolderNode(List)"})
  public void testHasNonFolderNode_givenNull_whenArrayListAddNull_thenReturnTrue() {
    // Arrange
    ArrayList<DBXTreeNode> list = new ArrayList<>();
    list.add(null);

    // Act and Assert
    assertTrue(DBXTreeNode.hasNonFolderNode(list));
  }

  /**
   * Test {@link DBXTreeNode#hasNonFolderNode(List)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeNode#hasNonFolderNode(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBXTreeNode.hasNonFolderNode(List)"})
  public void testHasNonFolderNode_givenNull_whenArrayListAddNull_thenReturnTrue2() {
    // Arrange
    ArrayList<DBXTreeNode> list = new ArrayList<>();
    list.add(null);
    list.add(null);

    // Act and Assert
    assertTrue(DBXTreeNode.hasNonFolderNode(list));
  }

  /**
   * Test {@link DBXTreeNode#hasNonFolderNode(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBXTreeNode#hasNonFolderNode(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBXTreeNode.hasNonFolderNode(List)"})
  public void testHasNonFolderNode_whenArrayList_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBXTreeNode.hasNonFolderNode(new ArrayList<>()));
  }
}
