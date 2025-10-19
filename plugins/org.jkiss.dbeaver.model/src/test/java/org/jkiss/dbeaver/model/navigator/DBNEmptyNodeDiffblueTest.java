package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBNEmptyNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBNEmptyNode}
   *   <li>{@link DBNEmptyNode#getNodeDescription()}
   *   <li>{@link DBNEmptyNode#getNodeDisplayName()}
   *   <li>{@link DBNEmptyNode#getNodeIcon()}
   *   <li>{@link DBNEmptyNode#getNodeItemPath()}
   *   <li>{@link DBNEmptyNode#getNodeType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBNEmptyNode.<init>()",
    "String DBNEmptyNode.getNodeDescription()",
    "String DBNEmptyNode.getNodeDisplayName()",
    "DBPImage DBNEmptyNode.getNodeIcon()",
    "String DBNEmptyNode.getNodeItemPath()",
    "String DBNEmptyNode.getNodeType()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBNEmptyNode actualDbnEmptyNode = new DBNEmptyNode();
    String actualNodeDescription = actualDbnEmptyNode.getNodeDescription();
    String actualNodeDisplayName = actualDbnEmptyNode.getNodeDisplayName();
    DBPImage actualNodeIcon = actualDbnEmptyNode.getNodeIcon();
    String actualNodeItemPath = actualDbnEmptyNode.getNodeItemPath();

    // Assert
    assertEquals("", actualNodeItemPath);
    assertEquals("#empty", actualNodeDisplayName);
    assertEquals("Empty", actualNodeDescription);
    assertEquals("empty", actualDbnEmptyNode.getNodeType());
    assertNull(actualNodeIcon);
    assertNull(actualDbnEmptyNode.getParentNode());
  }

  /**
   * Test {@link DBNEmptyNode#allowsChildren()}.
   *
   * <p>Method under test: {@link DBNEmptyNode#allowsChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNEmptyNode.allowsChildren()"})
  public void testAllowsChildren() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().allowsChildren());
  }

  /**
   * Test {@link DBNEmptyNode#getChildren(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBNEmptyNode#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.navigator.DBNNode[] DBNEmptyNode.getChildren(DBRProgressMonitor)"
  })
  public void testGetChildren() {
    // Arrange
    DBNEmptyNode dbnEmptyNode = new DBNEmptyNode();

    // Act and Assert
    assertEquals(0, dbnEmptyNode.getChildren(new LoggingProgressMonitor()).length);
  }

  /**
   * Test {@link DBNEmptyNode#allowsOpen()}.
   *
   * <p>Method under test: {@link DBNEmptyNode#allowsOpen()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNEmptyNode.allowsOpen()"})
  public void testAllowsOpen() {
    // Arrange, Act and Assert
    assertFalse(new DBNEmptyNode().allowsOpen());
  }
}
