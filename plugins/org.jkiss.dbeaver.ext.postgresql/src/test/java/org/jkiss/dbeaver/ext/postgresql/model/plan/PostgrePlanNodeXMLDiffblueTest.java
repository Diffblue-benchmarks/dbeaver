package org.jkiss.dbeaver.ext.postgresql.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.imageio.metadata.IIOMetadataNode;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.w3c.dom.Element;

public class PostgrePlanNodeXMLDiffblueTest {
  /**
   * Test {@link PostgrePlanNodeXML#PostgrePlanNodeXML(DBPDataSource, PostgrePlanNodeXML, Element)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeXML#PostgrePlanNodeXML(DBPDataSource,
   * PostgrePlanNodeXML, Element)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeXML.<init>(DBPDataSource, PostgrePlanNodeXML, Element)"})
  public void testNewPostgrePlanNodeXML() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    IIOMetadataNode iioMetadataNode = new IIOMetadataNode("Plans");
    iioMetadataNode.appendChild(new IIOMetadataNode());

    IIOMetadataNode element = new IIOMetadataNode();
    element.appendChild(iioMetadataNode);

    // Act
    PostgrePlanNodeXML actualPostgrePlanNodeXML = new PostgrePlanNodeXML(dataSource, null, element);

    // Assert
    assertEquals("", actualPostgrePlanNodeXML.getCost());
    assertNull(actualPostgrePlanNodeXML.getNodeCost());
    assertNull(actualPostgrePlanNodeXML.getNodeDuration());
    assertNull(actualPostgrePlanNodeXML.getNodePercent());
    assertNull(actualPostgrePlanNodeXML.getNodeRowCount());
    assertNull(actualPostgrePlanNodeXML.getActualRows());
    assertNull(actualPostgrePlanNodeXML.getEntity());
    assertNull(actualPostgrePlanNodeXML.getNodeCondition());
    assertNull(actualPostgrePlanNodeXML.getNodeDescription());
    assertNull(actualPostgrePlanNodeXML.getNodeName());
    assertNull(actualPostgrePlanNodeXML.getNodeType());
    assertNull(actualPostgrePlanNodeXML.getTotalTime());
    assertNull(actualPostgrePlanNodeXML.getParent());
    assertEquals(0, actualPostgrePlanNodeXML.getProperties().length);
    assertTrue(actualPostgrePlanNodeXML.getNested().isEmpty());
    assertTrue(actualPostgrePlanNodeXML.attributes.isEmpty());
  }

  /**
   * Test {@link PostgrePlanNodeXML#PostgrePlanNodeXML(DBPDataSource, PostgrePlanNodeXML, Element)}.
   *
   * <ul>
   *   <li>Given {@link IIOMetadataNode#IIOMetadataNode(String)} with {@code Plans}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeXML#PostgrePlanNodeXML(DBPDataSource,
   * PostgrePlanNodeXML, Element)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeXML.<init>(DBPDataSource, PostgrePlanNodeXML, Element)"})
  public void testNewPostgrePlanNodeXML_givenIIOMetadataNodeWithPlans() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    IIOMetadataNode element = new IIOMetadataNode();
    element.appendChild(new IIOMetadataNode("Plans"));

    // Act
    PostgrePlanNodeXML actualPostgrePlanNodeXML = new PostgrePlanNodeXML(dataSource, null, element);

    // Assert
    assertEquals("", actualPostgrePlanNodeXML.getCost());
    assertNull(actualPostgrePlanNodeXML.getNodeCost());
    assertNull(actualPostgrePlanNodeXML.getNodeDuration());
    assertNull(actualPostgrePlanNodeXML.getNodePercent());
    assertNull(actualPostgrePlanNodeXML.getNodeRowCount());
    assertNull(actualPostgrePlanNodeXML.getActualRows());
    assertNull(actualPostgrePlanNodeXML.getEntity());
    assertNull(actualPostgrePlanNodeXML.getNodeCondition());
    assertNull(actualPostgrePlanNodeXML.getNodeDescription());
    assertNull(actualPostgrePlanNodeXML.getNodeName());
    assertNull(actualPostgrePlanNodeXML.getNodeType());
    assertNull(actualPostgrePlanNodeXML.getTotalTime());
    assertNull(actualPostgrePlanNodeXML.getParent());
    assertEquals(0, actualPostgrePlanNodeXML.getProperties().length);
    assertTrue(actualPostgrePlanNodeXML.getNested().isEmpty());
    assertTrue(actualPostgrePlanNodeXML.attributes.isEmpty());
  }

  /**
   * Test {@link PostgrePlanNodeXML#PostgrePlanNodeXML(DBPDataSource, PostgrePlanNodeXML, Element)}.
   *
   * <ul>
   *   <li>When {@link IIOMetadataNode#IIOMetadataNode()}.
   *   <li>Then return Cost is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeXML#PostgrePlanNodeXML(DBPDataSource,
   * PostgrePlanNodeXML, Element)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeXML.<init>(DBPDataSource, PostgrePlanNodeXML, Element)"})
  public void testNewPostgrePlanNodeXML_whenIIOMetadataNode_thenReturnCostIsEmptyString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    PostgrePlanNodeXML actualPostgrePlanNodeXML =
        new PostgrePlanNodeXML(dataSource, null, new IIOMetadataNode());

    // Assert
    assertEquals("", actualPostgrePlanNodeXML.getCost());
    assertNull(actualPostgrePlanNodeXML.getNodeCost());
    assertNull(actualPostgrePlanNodeXML.getNodeDuration());
    assertNull(actualPostgrePlanNodeXML.getNodePercent());
    assertNull(actualPostgrePlanNodeXML.getNodeRowCount());
    assertNull(actualPostgrePlanNodeXML.getActualRows());
    assertNull(actualPostgrePlanNodeXML.getEntity());
    assertNull(actualPostgrePlanNodeXML.getNodeCondition());
    assertNull(actualPostgrePlanNodeXML.getNodeDescription());
    assertNull(actualPostgrePlanNodeXML.getNodeName());
    assertNull(actualPostgrePlanNodeXML.getNodeType());
    assertNull(actualPostgrePlanNodeXML.getTotalTime());
    assertNull(actualPostgrePlanNodeXML.getParent());
    assertEquals(0, actualPostgrePlanNodeXML.getProperties().length);
    assertTrue(actualPostgrePlanNodeXML.getNested().isEmpty());
    assertTrue(actualPostgrePlanNodeXML.attributes.isEmpty());
  }
}
