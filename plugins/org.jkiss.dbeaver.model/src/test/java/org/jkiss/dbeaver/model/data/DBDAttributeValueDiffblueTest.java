package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDAttributeValueDiffblueTest {
  /**
   * Test {@link DBDAttributeValue#getAttributes(DBDAttributeValue[])} with {@code
   * DBDAttributeValue[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getAttributes(DBDAttributeValue[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSAttributeBase[] DBDAttributeValue.getAttributes(DBDAttributeValue[])"})
  public void testGetAttributesWithDBDAttributeValue_thenReturnArrayLengthIsOne() {
    // Arrange
    AttributeMetaDataProxy attribute = new AttributeMetaDataProxy(mock(DBSAttributeBase.class));

    // Act
    DBSAttributeBase[] actualAttributes =
        DBDAttributeValue.getAttributes(
            new DBDAttributeValue[] {new DBDAttributeValue(attribute, DBPEvent.RENAME)});

    // Assert
    assertEquals(1, actualAttributes.length);
    assertSame(attribute, actualAttributes[0]);
  }

  /**
   * Test {@link DBDAttributeValue#getAttributes(List)} with {@code List}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getAttributes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSAttributeBase[] DBDAttributeValue.getAttributes(List)"})
  public void testGetAttributesWithList_thenReturnArrayLengthIsOne() {
    // Arrange
    ArrayList<DBDAttributeValue> attrValues = new ArrayList<>();
    AttributeMetaDataProxy attribute = new AttributeMetaDataProxy(null);
    attrValues.add(new DBDAttributeValue(attribute, DBPEvent.RENAME));

    // Act
    DBSAttributeBase[] actualAttributes = DBDAttributeValue.getAttributes(attrValues);

    // Assert
    assertEquals(1, actualAttributes.length);
    assertSame(attribute, actualAttributes[0]);
  }

  /**
   * Test {@link DBDAttributeValue#getAttributes(List)} with {@code List}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getAttributes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSAttributeBase[] DBDAttributeValue.getAttributes(List)"})
  public void testGetAttributesWithList_thenReturnArrayLengthIsTwo() {
    // Arrange
    ArrayList<DBDAttributeValue> attrValues = new ArrayList<>();
    attrValues.add(new DBDAttributeValue(new AttributeMetaDataProxy(null), DBPEvent.RENAME));
    AttributeMetaDataProxy attribute = new AttributeMetaDataProxy(null);
    attrValues.add(new DBDAttributeValue(attribute, DBPEvent.RENAME));

    // Act
    DBSAttributeBase[] actualAttributes = DBDAttributeValue.getAttributes(attrValues);

    // Assert
    assertEquals(2, actualAttributes.length);
    assertSame(attribute, actualAttributes[1]);
  }

  /**
   * Test {@link DBDAttributeValue#getAttributes(List)} with {@code List}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getAttributes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSAttributeBase[] DBDAttributeValue.getAttributes(List)"})
  public void testGetAttributesWithList_whenArrayList_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    DBSAttributeBase[] actualAttributes = DBDAttributeValue.getAttributes(new ArrayList<>());

    // Assert
    assertEquals(0, actualAttributes.length);
  }

  /**
   * Test {@link DBDAttributeValue#getValues(DBDAttributeValue[])} with {@code DBDAttributeValue[]}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getValues(DBDAttributeValue[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] DBDAttributeValue.getValues(DBDAttributeValue[])"})
  public void testGetValuesWithDBDAttributeValue_thenReturnArrayLengthIsOne() {
    // Arrange
    AttributeMetaDataProxy attribute = new AttributeMetaDataProxy(mock(DBSAttributeBase.class));

    // Act and Assert
    assertEquals(
        1,
        DBDAttributeValue.getValues(
                new DBDAttributeValue[] {new DBDAttributeValue(attribute, DBPEvent.RENAME)})
            .length);
  }

  /**
   * Test {@link DBDAttributeValue#getValues(List)} with {@code List}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getValues(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] DBDAttributeValue.getValues(List)"})
  public void testGetValuesWithList_thenReturnArrayLengthIsOne() {
    // Arrange
    ArrayList<DBDAttributeValue> attrValues = new ArrayList<>();
    attrValues.add(new DBDAttributeValue(new AttributeMetaDataProxy(null), DBPEvent.RENAME));

    // Act
    Object[] actualValues = DBDAttributeValue.getValues(attrValues);

    // Assert
    assertEquals(1, actualValues.length);
  }

  /**
   * Test {@link DBDAttributeValue#getValues(List)} with {@code List}.
   *
   * <ul>
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getValues(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] DBDAttributeValue.getValues(List)"})
  public void testGetValuesWithList_thenReturnArrayLengthIsTwo() {
    // Arrange
    ArrayList<DBDAttributeValue> attrValues = new ArrayList<>();
    attrValues.add(new DBDAttributeValue(new AttributeMetaDataProxy(null), DBPEvent.RENAME));
    attrValues.add(new DBDAttributeValue(new AttributeMetaDataProxy(null), DBPEvent.RENAME));

    // Act
    Object[] actualValues = DBDAttributeValue.getValues(attrValues);

    // Assert
    assertEquals(2, actualValues.length);
    Object expectedObject = actualValues[0];
    assertSame(expectedObject, actualValues[1]);
  }

  /**
   * Test {@link DBDAttributeValue#getValues(List)} with {@code List}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBDAttributeValue#getValues(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] DBDAttributeValue.getValues(List)"})
  public void testGetValuesWithList_whenArrayList_thenReturnArrayLengthIsZero() {
    // Arrange and Act
    Object[] actualValues = DBDAttributeValue.getValues(new ArrayList<>());

    // Assert
    assertEquals(0, actualValues.length);
  }

  /**
   * Test {@link DBDAttributeValue#getAttributeValue(List, DBSEntityAttribute)}.
   *
   * <p>Method under test: {@link DBDAttributeValue#getAttributeValue(List, DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBDAttributeValue DBDAttributeValue.getAttributeValue(List, DBSEntityAttribute)"
  })
  public void testGetAttributeValue() {
    // Arrange
    ArrayList<DBDAttributeValue> valueList = new ArrayList<>();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBDAttributeValue actualAttributeValue =
        DBDAttributeValue.getAttributeValue(valueList, attribute);

    // Assert
    assertNull(actualAttributeValue);
  }
}
