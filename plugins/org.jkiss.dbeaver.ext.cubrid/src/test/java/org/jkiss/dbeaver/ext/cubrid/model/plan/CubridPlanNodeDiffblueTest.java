package org.jkiss.dbeaver.ext.cubrid.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNodeKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CubridPlanNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CubridPlanNode#CubridPlanNode()}
   *   <li>{@link CubridPlanNode#setCost(long)}
   *   <li>{@link CubridPlanNode#getCardinality()}
   *   <li>{@link CubridPlanNode#getCost()}
   *   <li>{@link CubridPlanNode#getExtra()}
   *   <li>{@link CubridPlanNode#getFullText()}
   *   <li>{@link CubridPlanNode#getIndex()}
   *   <li>{@link CubridPlanNode#getNested()}
   *   <li>{@link CubridPlanNode#getNodeName()}
   *   <li>{@link CubridPlanNode#getTerms()}
   *   <li>{@link CubridPlanNode#getTotal()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CubridPlanNode.<init>()",
    "long CubridPlanNode.getCardinality()",
    "long CubridPlanNode.getCost()",
    "String CubridPlanNode.getExtra()",
    "String CubridPlanNode.getFullText()",
    "String CubridPlanNode.getIndex()",
    "Collection CubridPlanNode.getNested()",
    "String CubridPlanNode.getNodeName()",
    "String CubridPlanNode.getTerms()",
    "String CubridPlanNode.getTotal()",
    "void CubridPlanNode.setCost(long)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    CubridPlanNode actualCubridPlanNode = new CubridPlanNode();
    actualCubridPlanNode.setCost(1L);
    long actualCardinality = actualCubridPlanNode.getCardinality();
    long actualCost = actualCubridPlanNode.getCost();
    String actualExtra = actualCubridPlanNode.getExtra();
    String actualFullText = actualCubridPlanNode.getFullText();
    String actualIndex = actualCubridPlanNode.getIndex();
    Collection<CubridPlanNode> actualNested = actualCubridPlanNode.getNested();
    String actualNodeName = actualCubridPlanNode.getNodeName();
    String actualTerms = actualCubridPlanNode.getTerms();

    // Assert
    assertTrue(actualNested instanceof List);
    assertNull(actualExtra);
    assertNull(actualFullText);
    assertNull(actualIndex);
    assertNull(actualNodeName);
    assertNull(actualTerms);
    assertNull(actualCubridPlanNode.getTotal());
    assertNull(actualCubridPlanNode.getParent());
    assertEquals(0L, actualCardinality);
    assertEquals(1L, actualCost);
  }

  /**
   * Test {@link CubridPlanNode#CubridPlanNode(String)}.
   *
   * <ul>
   *   <li>When {@code inner: U}.
   *   <li>Then return Nested size is one.
   * </ul>
   *
   * <p>Method under test: {@link CubridPlanNode#CubridPlanNode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CubridPlanNode.<init>(String)"})
  public void testNewCubridPlanNode_whenInnerU_thenReturnNestedSizeIsOne() {
    // Arrange and Act
    CubridPlanNode actualCubridPlanNode = new CubridPlanNode("inner: U");

    // Assert
    Collection<CubridPlanNode> nested = actualCubridPlanNode.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    CubridPlanNode getResult = ((List<CubridPlanNode>) nested).get(0);
    Collection<CubridPlanNode> nested2 = getResult.getNested();
    assertTrue(nested2 instanceof List);
    assertEquals("U", actualCubridPlanNode.getNodeType());
    assertEquals("inner: U", actualCubridPlanNode.getFullText());
    assertEquals("inner: U", getResult.getFullText());
    assertNull(getResult.getExtra());
    assertNull(getResult.getIndex());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getTerms());
    assertNull(getResult.getTotal());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0L, getResult.getCardinality());
    assertEquals(0L, getResult.getCost());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    CubridPlanNode actualParent = actualCubridPlanNode.getParent();
    assertSame(actualCubridPlanNode, actualParent);
    assertSame(actualCubridPlanNode, getResult.getParent());
  }

  /**
   * Test {@link CubridPlanNode#CubridPlanNode(String)}.
   *
   * <ul>
   *   <li>When {@code Query Plan}.
   *   <li>Then return FullText is {@code Query Plan}.
   * </ul>
   *
   * <p>Method under test: {@link CubridPlanNode#CubridPlanNode(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CubridPlanNode.<init>(String)"})
  public void testNewCubridPlanNode_whenQueryPlan_thenReturnFullTextIsQueryPlan() {
    // Arrange and Act
    CubridPlanNode actualCubridPlanNode = new CubridPlanNode("Query Plan");

    // Assert
    Collection<CubridPlanNode> nested = actualCubridPlanNode.getNested();
    assertTrue(nested instanceof List);
    assertEquals("Query Plan", actualCubridPlanNode.getFullText());
    assertNull(actualCubridPlanNode.getExtra());
    assertNull(actualCubridPlanNode.getIndex());
    assertNull(actualCubridPlanNode.getNodeName());
    assertNull(actualCubridPlanNode.getTerms());
    assertNull(actualCubridPlanNode.getTotal());
    assertNull(actualCubridPlanNode.getNodeCondition());
    assertNull(actualCubridPlanNode.getNodeDescription());
    assertNull(actualCubridPlanNode.getParent());
    assertEquals(0L, actualCubridPlanNode.getCardinality());
    assertEquals(0L, actualCubridPlanNode.getCost());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualCubridPlanNode.getNodeKind());
    assertTrue(nested.isEmpty());
  }

  /**
   * Test {@link CubridPlanNode#getNodeType()}.
   *
   * <p>Method under test: {@link CubridPlanNode#getNodeType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CubridPlanNode.getNodeType()"})
  public void testGetNodeType() {
    // Arrange, Act and Assert
    assertEquals("Query", new CubridPlanNode().getNodeType());
  }

  /**
   * Test {@link CubridPlanNode#getParent()}.
   *
   * <p>Method under test: {@link CubridPlanNode#getParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CubridPlanNode CubridPlanNode.getParent()"})
  public void testGetParent() {
    // Arrange, Act and Assert
    assertNull(new CubridPlanNode("Query Plan").getParent());
  }

  /**
   * Test {@link CubridPlanNode#getNodeKind()}.
   *
   * <p>Method under test: {@link CubridPlanNode#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind CubridPlanNode.getNodeKind()"})
  public void testGetNodeKind() {
    // Arrange, Act and Assert
    assertEquals(DBCPlanNodeKind.DEFAULT, new CubridPlanNode("Query Plan").getNodeKind());
  }

  /**
   * Test {@link CubridPlanNode#setAllNestedNode(List)}.
   *
   * <ul>
   *   <li>Then {@link CubridPlanNode#CubridPlanNode(String)} with {@code Query Plan} Nested is
   *       {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CubridPlanNode#setAllNestedNode(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CubridPlanNode.setAllNestedNode(List)"})
  public void testSetAllNestedNode_thenCubridPlanNodeWithQueryPlanNestedIsArrayList() {
    // Arrange
    CubridPlanNode cubridPlanNode = new CubridPlanNode("Query Plan");

    ArrayList<CubridPlanNode> nodes = new ArrayList<>();
    nodes.add(new CubridPlanNode("Query Plan"));
    nodes.add(new CubridPlanNode("Query Plan"));

    // Act
    cubridPlanNode.setAllNestedNode(nodes);

    // Assert
    assertEquals(nodes, cubridPlanNode.getNested());
  }

  /**
   * Test {@link CubridPlanNode#setAllNestedNode(List)}.
   *
   * <ul>
   *   <li>Then {@link CubridPlanNode#CubridPlanNode(String)} with {@code Query Plan} Nested size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link CubridPlanNode#setAllNestedNode(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CubridPlanNode.setAllNestedNode(List)"})
  public void testSetAllNestedNode_thenCubridPlanNodeWithQueryPlanNestedSizeIsOne() {
    // Arrange
    CubridPlanNode cubridPlanNode = new CubridPlanNode("Query Plan");

    ArrayList<CubridPlanNode> nodes = new ArrayList<>();
    CubridPlanNode cubridPlanNode2 = new CubridPlanNode("Query Plan");
    nodes.add(cubridPlanNode2);

    // Act
    cubridPlanNode.setAllNestedNode(nodes);

    // Assert
    Collection<CubridPlanNode> nested = cubridPlanNode.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    assertSame(cubridPlanNode2, ((List<CubridPlanNode>) nested).get(0));
  }

  /**
   * Test {@link CubridPlanNode#setAllNestedNode(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link CubridPlanNode#setAllNestedNode(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CubridPlanNode.setAllNestedNode(List)"})
  public void testSetAllNestedNode_whenArrayList() {
    // Arrange
    CubridPlanNode cubridPlanNode = new CubridPlanNode("Query Plan");
    ArrayList<CubridPlanNode> nodes = new ArrayList<>();

    // Act
    cubridPlanNode.setAllNestedNode(nodes);

    // Assert that nothing has changed
    assertEquals(nodes, cubridPlanNode.getNested());
  }

  /**
   * Test {@link CubridPlanNode#parseNode()}.
   *
   * <p>Method under test: {@link CubridPlanNode#parseNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CubridPlanNode.parseNode()"})
  public void testParseNode() {
    // Arrange
    CubridPlanNode cubridPlanNode = new CubridPlanNode("Query Plan");

    // Act
    cubridPlanNode.parseNode();

    // Assert
    Collection<CubridPlanNode> nested = cubridPlanNode.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    CubridPlanNode getResult = ((List<CubridPlanNode>) nested).get(0);
    Collection<CubridPlanNode> nested2 = getResult.getNested();
    assertTrue(nested2 instanceof List);
    assertEquals("Query Plan", getResult.getFullText());
    assertNull(getResult.getExtra());
    assertNull(getResult.getIndex());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getTerms());
    assertNull(getResult.getTotal());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0L, getResult.getCardinality());
    assertEquals(0L, getResult.getCost());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    CubridPlanNode actualParent = cubridPlanNode.getParent();
    assertSame(cubridPlanNode, actualParent);
    assertSame(cubridPlanNode, getResult.getParent());
  }
}
