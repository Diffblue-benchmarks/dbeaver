package org.jkiss.dbeaver.ext.postgresql.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNodeKind;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgrePlanNodeTextDiffblueTest {
  /**
   * Test {@link PostgrePlanNodeText#getIndent()}.
   *
   * <p>Method under test: {@link PostgrePlanNodeText#getIndent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int PostgrePlanNodeText.getIndent()"})
  public void testGetIndent() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act and Assert
    assertEquals(1, postgrePlanNodeText.getIndent());
  }

  /**
   * Test {@link PostgrePlanNodeText#addProp(String)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeText#addProp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeText.addProp(String)"})
  public void testAddProp() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act
    postgrePlanNodeText.addProp(":");

    // Assert
    DBPPropertyDescriptor[] properties = postgrePlanNodeText.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> stringStringMap = postgrePlanNodeText.attributes;
    assertEquals(1, stringStringMap.size());
    assertEquals("", stringStringMap.get(""));
    assertEquals("", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("", dbpPropertyDescriptor.getDisplayName());
    assertEquals("", dbpPropertyDescriptor.getId());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link PostgrePlanNodeText#addProp(String)}.
   *
   * <ul>
   *   <li>When {@code Line}.
   *   <li>Then first element Name is {@code Info 1}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeText#addProp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgrePlanNodeText.addProp(String)"})
  public void testAddProp_whenLine_thenFirstElementNameIsInfo1() {
    // Arrange
    PostgrePlanNodeText postgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act
    postgrePlanNodeText.addProp("Line");

    // Assert
    DBPPropertyDescriptor[] properties = postgrePlanNodeText.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Info 1", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Info 1", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Info 1", dbpPropertyDescriptor.getId());
    Map<String, String> stringStringMap = postgrePlanNodeText.attributes;
    assertEquals(1, stringStringMap.size());
    assertEquals("Line", stringStringMap.get("Info 1"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link PostgrePlanNodeText#PostgrePlanNodeText(DBPDataSource, PostgrePlanNodeText, String,
   * int)}.
   *
   * <p>Method under test: {@link PostgrePlanNodeText#PostgrePlanNodeText(DBPDataSource,
   * PostgrePlanNodeText, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgrePlanNodeText.<init>(DBPDataSource, PostgrePlanNodeText, String, int)"
  })
  public void testNewPostgrePlanNodeText() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    PostgrePlanNodeText parent =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Act
    PostgrePlanNodeText actualPostgrePlanNodeText =
        new PostgrePlanNodeText(dataSource, parent, "Line", 1);

    // Assert
    assertSame(parent, actualPostgrePlanNodeText.getParent());
  }

  /**
   * Test {@link PostgrePlanNodeText#PostgrePlanNodeText(DBPDataSource, PostgrePlanNodeText, String,
   * int)}.
   *
   * <ul>
   *   <li>When {@code Line}.
   *   <li>Then return NodeType is {@code Line}.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeText#PostgrePlanNodeText(DBPDataSource,
   * PostgrePlanNodeText, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgrePlanNodeText.<init>(DBPDataSource, PostgrePlanNodeText, String, int)"
  })
  public void testNewPostgrePlanNodeText_whenLine_thenReturnNodeTypeIsLine() {
    // Arrange and Act
    PostgrePlanNodeText actualPostgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, "Line", 1);

    // Assert
    assertEquals("", actualPostgrePlanNodeText.getCost());
    assertEquals("Line", actualPostgrePlanNodeText.getNodeType());
    assertNull(actualPostgrePlanNodeText.getNodeCost());
    assertNull(actualPostgrePlanNodeText.getNodeDuration());
    assertNull(actualPostgrePlanNodeText.getNodePercent());
    assertNull(actualPostgrePlanNodeText.getNodeRowCount());
    assertNull(actualPostgrePlanNodeText.getActualRows());
    assertNull(actualPostgrePlanNodeText.getEntity());
    assertNull(actualPostgrePlanNodeText.getNodeCondition());
    assertNull(actualPostgrePlanNodeText.getNodeDescription());
    assertNull(actualPostgrePlanNodeText.getNodeName());
    assertNull(actualPostgrePlanNodeText.getTotalTime());
    assertNull(actualPostgrePlanNodeText.getParent());
    assertEquals(0, actualPostgrePlanNodeText.getProperties().length);
    assertEquals(1, actualPostgrePlanNodeText.getIndent());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualPostgrePlanNodeText.getNodeKind());
    assertTrue(actualPostgrePlanNodeText.getNested().isEmpty());
    assertTrue(actualPostgrePlanNodeText.attributes.isEmpty());
  }

  /**
   * Test {@link PostgrePlanNodeText#PostgrePlanNodeText(DBPDataSource, PostgrePlanNodeText, String,
   * int)}.
   *
   * <ul>
   *   <li>When space.
   *   <li>Then return NodeType is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PostgrePlanNodeText#PostgrePlanNodeText(DBPDataSource,
   * PostgrePlanNodeText, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgrePlanNodeText.<init>(DBPDataSource, PostgrePlanNodeText, String, int)"
  })
  public void testNewPostgrePlanNodeText_whenSpace_thenReturnNodeTypeIsEmptyString() {
    // Arrange and Act
    PostgrePlanNodeText actualPostgrePlanNodeText =
        new PostgrePlanNodeText(mock(DBPDataSource.class), null, " ", 1);

    // Assert
    assertEquals("", actualPostgrePlanNodeText.getCost());
    assertEquals("", actualPostgrePlanNodeText.getNodeType());
    assertNull(actualPostgrePlanNodeText.getNodeCost());
    assertNull(actualPostgrePlanNodeText.getNodeDuration());
    assertNull(actualPostgrePlanNodeText.getNodePercent());
    assertNull(actualPostgrePlanNodeText.getNodeRowCount());
    assertNull(actualPostgrePlanNodeText.getActualRows());
    assertNull(actualPostgrePlanNodeText.getEntity());
    assertNull(actualPostgrePlanNodeText.getNodeCondition());
    assertNull(actualPostgrePlanNodeText.getNodeDescription());
    assertNull(actualPostgrePlanNodeText.getNodeName());
    assertNull(actualPostgrePlanNodeText.getTotalTime());
    assertNull(actualPostgrePlanNodeText.getParent());
    assertEquals(0, actualPostgrePlanNodeText.getProperties().length);
    assertEquals(1, actualPostgrePlanNodeText.getIndent());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualPostgrePlanNodeText.getNodeKind());
    assertTrue(actualPostgrePlanNodeText.getNested().isEmpty());
    assertTrue(actualPostgrePlanNodeText.attributes.isEmpty());
  }
}
