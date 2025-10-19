package org.jkiss.dbeaver.ext.firebird.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FireBirdPlanNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FireBirdPlanNode#FireBirdPlanNode(String)}
   *   <li>{@link FireBirdPlanNode#getNested()}
   *   <li>{@link FireBirdPlanNode#getNodeDescription()}
   *   <li>{@link FireBirdPlanNode#getNodeName()}
   *   <li>{@link FireBirdPlanNode#getNodeType()}
   *   <li>{@link FireBirdPlanNode#getParent()}
   *   <li>{@link FireBirdPlanNode#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FireBirdPlanNode.<init>(String)",
    "Collection FireBirdPlanNode.getNested()",
    "String FireBirdPlanNode.getNodeDescription()",
    "String FireBirdPlanNode.getNodeName()",
    "String FireBirdPlanNode.getNodeType()",
    "DBCPlanNode FireBirdPlanNode.getParent()",
    "String FireBirdPlanNode.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    FireBirdPlanNode actualFireBirdPlanNode = new FireBirdPlanNode("Plan");
    Collection<FireBirdPlanNode> actualNested = actualFireBirdPlanNode.getNested();
    String actualNodeDescription = actualFireBirdPlanNode.getNodeDescription();
    String actualNodeName = actualFireBirdPlanNode.getNodeName();
    String actualNodeType = actualFireBirdPlanNode.getNodeType();
    DBCPlanNode actualParent = actualFireBirdPlanNode.getParent();

    // Assert
    assertTrue(actualNested instanceof List);
    assertEquals("Plan", actualNodeDescription);
    assertEquals("Plan", actualNodeName);
    assertEquals("Plan", actualNodeType);
    assertEquals("Plan", actualFireBirdPlanNode.toString());
    assertNull(actualParent);
  }
}
