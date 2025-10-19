package org.jkiss.dbeaver.ext.postgresql.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNodeKind;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgrePlanNodeBaseDiffblueTest {
  /**
   * Test {@link PostgrePlanNodeBase#setAttributes(Map)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#setAttributes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeBase.setAttributes(Map)"})
  public void testSetAttributes() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);
    HashMap<String, String> attributes = new HashMap<>();

    // Act
    postgrePlanNodeText.setAttributes(attributes);

    // Assert
    assertNull(postgrePlanNodeText.getNodeCost());
    assertNull(postgrePlanNodeText.getEntity());
    assertNull(postgrePlanNodeText.getNodeName());
    assertNull(postgrePlanNodeText.getNodeType());
    assertEquals(0, postgrePlanNodeText.getProperties().length);
    assertTrue(attributes.isEmpty());
    assertTrue(postgrePlanNodeText.attributes.isEmpty());
  }

  /**
   * Test {@link PostgrePlanNodeBase#setAttributes(Map)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#setAttributes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeBase.setAttributes(Map)"})
  public void testSetAttributes2() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("Node-Type", "Attributes");
    attributes.put("Relation-Name", "Attributes");
    attributes.put("Function-Name", "Attributes");
    attributes.put("Index-Name", "Attributes");
    attributes.put("CTE-Name", "Attributes");
    attributes.put("Startup-Cost", "");
    attributes.put("Total-Cost", "");
    attributes.put("Parallel-Aware", Boolean.TRUE.toString());

    // Act
    postgrePlanNodeText.setAttributes(attributes);

    // Assert
    assertEquals("", postgrePlanNodeText.getCost());
    assertEquals("Attributes", postgrePlanNodeText.getEntity());
    assertEquals("Attributes", postgrePlanNodeText.getNodeName());
    assertEquals("Parallel Attributes", postgrePlanNodeText.getNodeType());
    assertEquals(7, attributes.size());
    Map<String, String> stringStringMap = postgrePlanNodeText.attributes;
    assertEquals(7, stringStringMap.size());
    assertEquals(7, postgrePlanNodeText.getProperties().length);
    assertTrue(attributes.containsKey("CTE-Name"));
    assertTrue(attributes.containsKey("Function-Name"));
    assertTrue(attributes.containsKey("Index-Name"));
    assertTrue(attributes.containsKey("Parallel-Aware"));
    assertTrue(attributes.containsKey("Relation-Name"));
    assertTrue(attributes.containsKey("Startup-Cost"));
    assertTrue(attributes.containsKey("Total-Cost"));
    assertTrue(stringStringMap.containsKey("CTE-Name"));
    assertTrue(stringStringMap.containsKey("Function-Name"));
    assertTrue(stringStringMap.containsKey("Index-Name"));
    assertTrue(stringStringMap.containsKey("Parallel-Aware"));
    assertTrue(stringStringMap.containsKey("Startup-Cost"));
    assertTrue(stringStringMap.containsKey("Total-Cost"));
    assertEquals(Double.NaN, postgrePlanNodeText.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link PostgrePlanNodeBase#setAttributes(Map)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#setAttributes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeBase.setAttributes(Map)"})
  public void testSetAttributes3() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("Node-Type", "Attributes");
    attributes.put("Relation-Name", "Attributes");
    attributes.put("Function-Name", "Attributes");
    attributes.put("Index-Name", "Attributes");
    attributes.put("CTE-Name", "Attributes");
    attributes.put("Startup-Cost", "");
    attributes.put("Total-Cost", "Attributes");
    attributes.put("Parallel-Aware", Boolean.TRUE.toString());

    // Act
    postgrePlanNodeText.setAttributes(attributes);

    // Assert
    assertEquals(" - Attributes", postgrePlanNodeText.getCost());
    assertEquals("Attributes", postgrePlanNodeText.getEntity());
    assertEquals("Attributes", postgrePlanNodeText.getNodeName());
    assertEquals("Parallel Attributes", postgrePlanNodeText.getNodeType());
    assertEquals(7, attributes.size());
    Map<String, String> stringStringMap = postgrePlanNodeText.attributes;
    assertEquals(7, stringStringMap.size());
    assertEquals(7, postgrePlanNodeText.getProperties().length);
    assertTrue(attributes.containsKey("CTE-Name"));
    assertTrue(attributes.containsKey("Function-Name"));
    assertTrue(attributes.containsKey("Index-Name"));
    assertTrue(attributes.containsKey("Parallel-Aware"));
    assertTrue(attributes.containsKey("Relation-Name"));
    assertTrue(attributes.containsKey("Startup-Cost"));
    assertTrue(attributes.containsKey("Total-Cost"));
    assertTrue(stringStringMap.containsKey("CTE-Name"));
    assertTrue(stringStringMap.containsKey("Function-Name"));
    assertTrue(stringStringMap.containsKey("Index-Name"));
    assertTrue(stringStringMap.containsKey("Parallel-Aware"));
    assertTrue(stringStringMap.containsKey("Startup-Cost"));
    assertTrue(stringStringMap.containsKey("Total-Cost"));
    assertEquals(Double.NaN, postgrePlanNodeText.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link PostgrePlanNodeBase#setAttributes(Map)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#setAttributes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeBase.setAttributes(Map)"})
  public void testSetAttributes4() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("Node-Type", "Attributes");
    attributes.put("Relation-Name", "Attributes");
    attributes.put("Function-Name", "Attributes");
    attributes.put("Index-Name", "Attributes");
    attributes.put("CTE-Name", "Attributes");
    attributes.put("Startup-Cost", "Attributes");
    attributes.put("Total-Cost", "");
    attributes.put("Parallel-Aware", Boolean.TRUE.toString());

    // Act
    postgrePlanNodeText.setAttributes(attributes);

    // Assert
    assertEquals("Attributes - ", postgrePlanNodeText.getCost());
    assertEquals("Attributes", postgrePlanNodeText.getEntity());
    assertEquals("Attributes", postgrePlanNodeText.getNodeName());
    assertEquals("Parallel Attributes", postgrePlanNodeText.getNodeType());
    assertEquals(7, attributes.size());
    Map<String, String> stringStringMap = postgrePlanNodeText.attributes;
    assertEquals(7, stringStringMap.size());
    assertEquals(7, postgrePlanNodeText.getProperties().length);
    assertTrue(attributes.containsKey("CTE-Name"));
    assertTrue(attributes.containsKey("Function-Name"));
    assertTrue(attributes.containsKey("Index-Name"));
    assertTrue(attributes.containsKey("Parallel-Aware"));
    assertTrue(attributes.containsKey("Relation-Name"));
    assertTrue(attributes.containsKey("Startup-Cost"));
    assertTrue(attributes.containsKey("Total-Cost"));
    assertTrue(stringStringMap.containsKey("CTE-Name"));
    assertTrue(stringStringMap.containsKey("Function-Name"));
    assertTrue(stringStringMap.containsKey("Index-Name"));
    assertTrue(stringStringMap.containsKey("Parallel-Aware"));
    assertTrue(stringStringMap.containsKey("Startup-Cost"));
    assertTrue(stringStringMap.containsKey("Total-Cost"));
    assertEquals(Double.NaN, postgrePlanNodeText.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeName()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getNodeName()"})
  public void testGetNodeName() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodeName());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeType()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getNodeType()"})
  public void testGetNodeType() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertEquals("Line", postgrePlanNodeText.getNodeType());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeDescription()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getNodeDescription()"})
  public void testGetNodeDescription() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodeDescription());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getEntity()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getEntity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getEntity()"})
  public void testGetEntity() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getEntity());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getCost()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getCost()"})
  public void testGetCost() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertEquals("", postgrePlanNodeText.getCost());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getActualRows()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getActualRows()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getActualRows()"})
  public void testGetActualRows() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getActualRows());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getTotalTime()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getTotalTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getTotalTime()"})
  public void testGetTotalTime() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getTotalTime());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeCondition()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeCondition()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.getNodeCondition()"})
  public void testGetNodeCondition() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodeCondition());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getParent()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PostgrePlanNodeBase PostgrePlanNodeBase.getParent()"})
  public void testGetParent() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getParent());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNested()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNested()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List PostgrePlanNodeBase.getNested()"})
  public void testGetNested() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertTrue(postgrePlanNodeText.getNested().isEmpty());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeCost()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Number PostgrePlanNodeBase.getNodeCost()"})
  public void testGetNodeCost() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodeCost());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodePercent()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodePercent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Number PostgrePlanNodeBase.getNodePercent()"})
  public void testGetNodePercent() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodePercent());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeDuration()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeDuration()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Number PostgrePlanNodeBase.getNodeDuration()"})
  public void testGetNodeDuration() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodeDuration());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeRowCount()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Number PostgrePlanNodeBase.getNodeRowCount()"})
  public void testGetNodeRowCount() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getNodeRowCount());
  }

  /**
   * Test {@link PostgrePlanNodeBase#toString()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PostgrePlanNodeBase.toString()"})
  public void testToString() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertEquals("Type: Line; ; Cost: ", postgrePlanNodeText.toString());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind PostgrePlanNodeBase.getNodeKind()"})
  public void testGetNodeKind_thenReturnDefault() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertEquals(DBCPlanNodeKind.DEFAULT, postgrePlanNodeText.getNodeKind());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code FUNCTION}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind PostgrePlanNodeBase.getNodeKind()"})
  public void testGetNodeKind_thenReturnFunction() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Function", 1);

    // Act and Assert
    assertEquals(DBCPlanNodeKind.FUNCTION, postgrePlanNodeText.getNodeKind());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code INDEX_SCAN}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind PostgrePlanNodeBase.getNodeKind()"})
  public void testGetNodeKind_thenReturnIndexScan() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Index", 1);

    // Act and Assert
    assertEquals(DBCPlanNodeKind.INDEX_SCAN, postgrePlanNodeText.getNodeKind());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getEditableValue()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PostgrePlanNodeBase.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act
    Object actualEditableValue = postgrePlanNodeText.getEditableValue();

    // Assert
    assertSame(postgrePlanNodeText, actualEditableValue);
  }

  /**
   * Test {@link PostgrePlanNodeBase#getProperties()}.
   *
   * <ul>
   *   <li>Then first element return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] PostgrePlanNodeBase.getProperties()"})
  public void testGetProperties_thenFirstElementReturnPropertyDescriptor() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);
    postgrePlanNodeText.addProp("Line");

    // Act
    DBPPropertyDescriptor[] actualProperties = postgrePlanNodeText.getProperties();

    // Assert
    DBPPropertyDescriptor dbpPropertyDescriptor = actualProperties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Details", dbpPropertyDescriptor.getCategory());
    assertEquals("Info 1", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Info 1", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Info 1", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertNull(dbpPropertyDescriptor.getRequiredFeatures());
    assertNull(((PropertyDescriptor) dbpPropertyDescriptor).getPropertyType());
    assertEquals(1, actualProperties.length);
    assertEquals(PropertyLength.LONG, dbpPropertyDescriptor.getLength());
    assertFalse(dbpPropertyDescriptor.isRequired());
    Class<String> expectedDataType = String.class;
    assertEquals(expectedDataType, dbpPropertyDescriptor.getDataType());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getProperties()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] PostgrePlanNodeBase.getProperties()"})
  public void testGetProperties_thenReturnArrayLengthIsZero() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertEquals(0, postgrePlanNodeText.getProperties().length);
  }

  /**
   * Test {@link PostgrePlanNodeBase#getProperties()}.
   *
   * <ul>
   *   <li>Then second element return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] PostgrePlanNodeBase.getProperties()"})
  public void testGetProperties_thenSecondElementReturnPropertyDescriptor() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);
    postgrePlanNodeText.addProp("Line");
    postgrePlanNodeText.addProp("Line");

    // Act
    DBPPropertyDescriptor[] actualProperties = postgrePlanNodeText.getProperties();

    // Assert
    DBPPropertyDescriptor dbpPropertyDescriptor = actualProperties[1];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Details", dbpPropertyDescriptor.getCategory());
    assertEquals("Info 2", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Info 2", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Info 2", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertNull(dbpPropertyDescriptor.getRequiredFeatures());
    assertNull(((PropertyDescriptor) dbpPropertyDescriptor).getPropertyType());
    assertEquals(2, actualProperties.length);
    assertEquals(PropertyLength.LONG, dbpPropertyDescriptor.getLength());
    assertFalse(dbpPropertyDescriptor.isRequired());
    Class<String> expectedDataType = String.class;
    assertEquals(expectedDataType, dbpPropertyDescriptor.getDataType());
  }

  /**
   * Test {@link PostgrePlanNodeBase#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#getPropertyValue(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PostgrePlanNodeBase.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertNull(postgrePlanNodeText.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link PostgrePlanNodeBase#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostgrePlanNodeBase.isPropertySet(String)"})
  public void testIsPropertySet() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertFalse(postgrePlanNodeText.isPropertySet("42"));
  }

  /**
   * Test {@link PostgrePlanNodeBase#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeBase#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PostgrePlanNodeBase.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertFalse(postgrePlanNodeText.isPropertyResettable("42"));
  }
}
