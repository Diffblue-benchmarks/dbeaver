package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVColorOverrideDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVColorOverride#DBVColorOverride(String, DBCLogicalOperator, Object[], String,
   *       String)}
   *   <li>{@link DBVColorOverride#setAttributeName(String)}
   *   <li>{@link DBVColorOverride#setAttributeValues(Object[])}
   *   <li>{@link DBVColorOverride#setColorBackground2(String)}
   *   <li>{@link DBVColorOverride#setColorBackground(String)}
   *   <li>{@link DBVColorOverride#setColorForeground2(String)}
   *   <li>{@link DBVColorOverride#setColorForeground(String)}
   *   <li>{@link DBVColorOverride#setOperator(DBCLogicalOperator)}
   *   <li>{@link DBVColorOverride#setRange(boolean)}
   *   <li>{@link DBVColorOverride#setSingleColumn(boolean)}
   *   <li>{@link DBVColorOverride#toString()}
   *   <li>{@link DBVColorOverride#getAttributeName()}
   *   <li>{@link DBVColorOverride#getAttributeValues()}
   *   <li>{@link DBVColorOverride#getColorBackground()}
   *   <li>{@link DBVColorOverride#getColorBackground2()}
   *   <li>{@link DBVColorOverride#getColorForeground()}
   *   <li>{@link DBVColorOverride#getColorForeground2()}
   *   <li>{@link DBVColorOverride#getOperator()}
   *   <li>{@link DBVColorOverride#isRange()}
   *   <li>{@link DBVColorOverride#isSingleColumn()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVColorOverride.<init>(String, DBCLogicalOperator, Object[], String, String)",
    "String DBVColorOverride.getAttributeName()",
    "Object[] DBVColorOverride.getAttributeValues()",
    "String DBVColorOverride.getColorBackground()",
    "String DBVColorOverride.getColorBackground2()",
    "String DBVColorOverride.getColorForeground()",
    "String DBVColorOverride.getColorForeground2()",
    "DBCLogicalOperator DBVColorOverride.getOperator()",
    "boolean DBVColorOverride.isRange()",
    "boolean DBVColorOverride.isSingleColumn()",
    "void DBVColorOverride.setAttributeName(String)",
    "void DBVColorOverride.setAttributeValues(Object[])",
    "void DBVColorOverride.setColorBackground(String)",
    "void DBVColorOverride.setColorBackground2(String)",
    "void DBVColorOverride.setColorForeground(String)",
    "void DBVColorOverride.setColorForeground2(String)",
    "void DBVColorOverride.setOperator(DBCLogicalOperator)",
    "void DBVColorOverride.setRange(boolean)",
    "void DBVColorOverride.setSingleColumn(boolean)",
    "String DBVColorOverride.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};

    // Act
    DBVColorOverride actualDbvColorOverride =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    actualDbvColorOverride.setAttributeName("Attribute Name");
    Object[] attributeValues2 = new Object[] {DBPEvent.RENAME};
    actualDbvColorOverride.setAttributeValues(attributeValues2);
    actualDbvColorOverride.setColorBackground2("Color Background2");
    actualDbvColorOverride.setColorBackground("Color Background");
    actualDbvColorOverride.setColorForeground2("Color Foreground2");
    actualDbvColorOverride.setColorForeground("Color Foreground");
    actualDbvColorOverride.setOperator(DBCLogicalOperator.EQUALS);
    actualDbvColorOverride.setRange(true);
    actualDbvColorOverride.setSingleColumn(true);
    actualDbvColorOverride.toString();
    String actualAttributeName = actualDbvColorOverride.getAttributeName();
    Object[] actualAttributeValues = actualDbvColorOverride.getAttributeValues();
    String actualColorBackground = actualDbvColorOverride.getColorBackground();
    String actualColorBackground2 = actualDbvColorOverride.getColorBackground2();
    String actualColorForeground = actualDbvColorOverride.getColorForeground();
    String actualColorForeground2 = actualDbvColorOverride.getColorForeground2();
    DBCLogicalOperator actualOperator = actualDbvColorOverride.getOperator();
    boolean actualIsRangeResult = actualDbvColorOverride.isRange();

    // Assert
    assertEquals("Attribute Name", actualAttributeName);
    assertEquals("Color Background", actualColorBackground);
    assertEquals("Color Background2", actualColorBackground2);
    assertEquals("Color Foreground", actualColorForeground);
    assertEquals("Color Foreground2", actualColorForeground2);
    assertEquals(1, actualAttributeValues.length);
    assertEquals(DBCLogicalOperator.EQUALS, actualOperator);
    assertTrue(actualIsRangeResult);
    assertTrue(actualDbvColorOverride.isSingleColumn());
    assertSame(attributeValues2, actualAttributeValues);
  }

  /**
   * Test {@link DBVColorOverride#DBVColorOverride(DBVColorOverride)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return AttributeValues is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVColorOverride#DBVColorOverride(DBVColorOverride)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVColorOverride.<init>(DBVColorOverride)"})
  public void testNewDBVColorOverride_givenNull_thenReturnAttributeValuesIsNull() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    DBVColorOverride source2 = new DBVColorOverride(source);
    source2.setAttributeValues(null);

    // Act
    DBVColorOverride actualDbvColorOverride = new DBVColorOverride(source2);

    // Assert
    assertEquals("Attribute Name", actualDbvColorOverride.getAttributeName());
    assertEquals("Color Background", actualDbvColorOverride.getColorBackground());
    assertEquals("Color Foreground", actualDbvColorOverride.getColorForeground());
    assertNull(actualDbvColorOverride.getAttributeValues());
    assertNull(actualDbvColorOverride.getColorBackground2());
    assertNull(actualDbvColorOverride.getColorForeground2());
    assertEquals(DBCLogicalOperator.EQUALS, actualDbvColorOverride.getOperator());
    assertFalse(actualDbvColorOverride.isRange());
    assertFalse(actualDbvColorOverride.isSingleColumn());
  }

  /**
   * Test {@link DBVColorOverride#DBVColorOverride(DBVColorOverride)}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVColorOverride#DBVColorOverride(DBVColorOverride)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVColorOverride.<init>(DBVColorOverride)"})
  public void testNewDBVColorOverride_thenReturnArrayLengthIsOne() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    // Act
    DBVColorOverride actualDbvColorOverride = new DBVColorOverride(source);

    // Assert
    assertEquals("Attribute Name", actualDbvColorOverride.getAttributeName());
    assertEquals("Color Background", actualDbvColorOverride.getColorBackground());
    assertEquals("Color Foreground", actualDbvColorOverride.getColorForeground());
    assertNull(actualDbvColorOverride.getColorBackground2());
    assertNull(actualDbvColorOverride.getColorForeground2());
    assertEquals(1, actualDbvColorOverride.getAttributeValues().length);
    assertEquals(DBCLogicalOperator.EQUALS, actualDbvColorOverride.getOperator());
    assertFalse(actualDbvColorOverride.isRange());
    assertFalse(actualDbvColorOverride.isSingleColumn());
  }

  /**
   * Test {@link DBVColorOverride#addAttributeValue(Object)}.
   *
   * <ul>
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVColorOverride#addAttributeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVColorOverride.addAttributeValue(Object)"})
  public void testAddAttributeValue_thenArrayLengthIsOne() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride source =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    DBVColorOverride dbvColorOverride = new DBVColorOverride(source);
    dbvColorOverride.setAttributeValues(null);
    Object object = DBPEvent.RENAME;

    // Act
    dbvColorOverride.addAttributeValue(object);

    // Assert
    Object[] attributeValues2 = dbvColorOverride.getAttributeValues();
    assertEquals(1, attributeValues2.length);
    assertSame(object, attributeValues2[0]);
  }

  /**
   * Test {@link DBVColorOverride#addAttributeValue(Object)}.
   *
   * <ul>
   *   <li>Then array length is two.
   * </ul>
   *
   * <p>Method under test: {@link DBVColorOverride#addAttributeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVColorOverride.addAttributeValue(Object)"})
  public void testAddAttributeValue_thenArrayLengthIsTwo() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride dbvColorOverride =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    Object object = DBPEvent.RENAME;

    // Act
    dbvColorOverride.addAttributeValue(object);

    // Assert
    Object[] attributeValues2 = dbvColorOverride.getAttributeValues();
    assertEquals(2, attributeValues2.length);
    assertSame(object, attributeValues2[0]);
    assertSame(object, attributeValues2[1]);
  }

  /**
   * Test {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}.
   *
   * <p>Method under test: {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVColorOverride.matches(String, DBCLogicalOperator, Object[])"})
  public void testMatches() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride dbvColorOverride =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    // Act and Assert
    assertFalse(
        dbvColorOverride.matches(
            "Attr Name", DBCLogicalOperator.EQUALS, new Object[] {DBPEvent.RENAME}));
  }

  /**
   * Test {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}.
   *
   * <p>Method under test: {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVColorOverride.matches(String, DBCLogicalOperator, Object[])"})
  public void testMatches2() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride dbvColorOverride =
        new DBVColorOverride(
            "Attr Name",
            DBCLogicalOperator.NOT_EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    // Act and Assert
    assertFalse(
        dbvColorOverride.matches(
            "Attr Name", DBCLogicalOperator.EQUALS, new Object[] {DBPEvent.RENAME}));
  }

  /**
   * Test {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}.
   *
   * <p>Method under test: {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVColorOverride.matches(String, DBCLogicalOperator, Object[])"})
  public void testMatches3() {
    // Arrange
    DBVColorOverride dbvColorOverride =
        new DBVColorOverride(
            "Attr Name",
            DBCLogicalOperator.EQUALS,
            new Object[] {},
            "Color Foreground",
            "Color Background");

    // Act and Assert
    assertFalse(
        dbvColorOverride.matches(
            "Attr Name", DBCLogicalOperator.EQUALS, new Object[] {DBPEvent.RENAME}));
  }

  /**
   * Test {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBVColorOverride#matches(String, DBCLogicalOperator, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVColorOverride.matches(String, DBCLogicalOperator, Object[])"})
  public void testMatches_thenReturnTrue() {
    // Arrange
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride dbvColorOverride =
        new DBVColorOverride(
            "Attr Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");

    // Act and Assert
    assertTrue(
        dbvColorOverride.matches(
            "Attr Name", DBCLogicalOperator.EQUALS, new Object[] {DBPEvent.RENAME}));
  }
}
