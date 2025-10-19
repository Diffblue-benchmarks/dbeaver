package org.jkiss.dbeaver.ext.exasol.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolPlanNodeDiffblueTest {
  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code STMT_ID} is {@code 42}.
   *   <li>Then return StmtId is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_given42_whenHashMapStmtIdIs42_thenReturnStmtIdIsFortyTwo() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("STMT_ID", "42");

    // Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, attributes);

    // Assert
    assertNull(actualExasolPlanNode.getCommandName());
    assertNull(actualExasolPlanNode.getNodeType());
    assertEquals(0.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertEquals(42, actualExasolPlanNode.getStmtId());
    assertSame(attributes, actualExasolPlanNode.getAttributes());
  }

  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>When {@link HashMap#HashMap()} {@code STMT_ID} is {@code A}.
   *   <li>Then return StmtId is sixty-five.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_givenA_whenHashMapStmtIdIsA_thenReturnStmtIdIsSixtyFive() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("STMT_ID", (byte) 'A');

    // Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, attributes);

    // Assert
    assertNull(actualExasolPlanNode.getCommandName());
    assertNull(actualExasolPlanNode.getNodeType());
    assertEquals(0.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertEquals(65, actualExasolPlanNode.getStmtId());
    assertSame(attributes, actualExasolPlanNode.getAttributes());
  }

  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link HashMap#HashMap()} {@code STMT_ID} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_givenEmptyString_whenHashMapStmtIdIsEmptyString() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("STMT_ID", "");

    // Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, attributes);

    // Assert
    assertNull(actualExasolPlanNode.getCommandName());
    assertNull(actualExasolPlanNode.getNodeType());
    assertEquals(0, actualExasolPlanNode.getStmtId());
    assertEquals(0.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertSame(attributes, actualExasolPlanNode.getAttributes());
  }

  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>Then return ObjectRows doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_thenReturnObjectRowsDoubleValueIsFortyTwo() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("OBJECT_ROWS", "42");

    // Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, attributes);

    // Assert
    assertNull(actualExasolPlanNode.getCommandName());
    assertNull(actualExasolPlanNode.getNodeType());
    assertEquals(0, actualExasolPlanNode.getStmtId());
    assertEquals(42.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertSame(attributes, actualExasolPlanNode.getAttributes());
  }

  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code COMMAND_NAME} is {@code 42}.
   *   <li>Then return CommandName is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_whenHashMapCommandNameIs42_thenReturnCommandNameIs42() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("COMMAND_NAME", "42");
    attributes.put("STMT_ID", "42");

    // Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, attributes);

    // Assert
    assertEquals("42", actualExasolPlanNode.getCommandName());
    assertEquals("42", actualExasolPlanNode.getNodeType());
    assertEquals(0.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertEquals(42, actualExasolPlanNode.getStmtId());
    assertSame(attributes, actualExasolPlanNode.getAttributes());
  }

  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code STMT_ID} is {@code STMT_ID}.
   *   <li>Then return StmtId is zero.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_whenHashMapStmtIdIsStmtId_thenReturnStmtIdIsZero() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("STMT_ID", "STMT_ID");

    // Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, attributes);

    // Assert
    assertNull(actualExasolPlanNode.getCommandName());
    assertNull(actualExasolPlanNode.getNodeType());
    assertEquals(0, actualExasolPlanNode.getStmtId());
    assertEquals(0.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertSame(attributes, actualExasolPlanNode.getAttributes());
  }

  /**
   * Test {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return Attributes Empty.
   * </ul>
   *
   * <p>Method under test: {@link ExasolPlanNode#ExasolPlanNode(ExasolPlanNode, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExasolPlanNode.<init>(ExasolPlanNode, Map)"})
  public void testNewExasolPlanNode_whenHashMap_thenReturnAttributesEmpty() {
    // Arrange and Act
    ExasolPlanNode actualExasolPlanNode = new ExasolPlanNode(null, new HashMap<>());

    // Assert
    assertNull(actualExasolPlanNode.getCommandName());
    assertNull(actualExasolPlanNode.getNodeType());
    assertEquals(0, actualExasolPlanNode.getStmtId());
    assertEquals(0.0d, actualExasolPlanNode.getObjectRows().doubleValue(), 0.0);
    assertTrue(actualExasolPlanNode.getAttributes().isEmpty());
  }
}
