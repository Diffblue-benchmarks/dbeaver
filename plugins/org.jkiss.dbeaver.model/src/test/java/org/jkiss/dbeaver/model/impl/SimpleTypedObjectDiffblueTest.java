package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleTypedObjectDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SimpleTypedObject#SimpleTypedObject(String)}
   *   <li>{@link SimpleTypedObject#getDataKind()}
   *   <li>{@link SimpleTypedObject#getMaxLength()}
   *   <li>{@link SimpleTypedObject#getPrecision()}
   *   <li>{@link SimpleTypedObject#getScale()}
   *   <li>{@link SimpleTypedObject#getTypeID()}
   *   <li>{@link SimpleTypedObject#getTypeModifiers()}
   *   <li>{@link SimpleTypedObject#getTypeName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleTypedObject.<init>(String)",
    "DBPDataKind SimpleTypedObject.getDataKind()",
    "long SimpleTypedObject.getMaxLength()",
    "Integer SimpleTypedObject.getPrecision()",
    "Integer SimpleTypedObject.getScale()",
    "int SimpleTypedObject.getTypeID()",
    "long SimpleTypedObject.getTypeModifiers()",
    "String SimpleTypedObject.getTypeName()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SimpleTypedObject actualSimpleTypedObject = new SimpleTypedObject("Type Name");
    DBPDataKind actualDataKind = actualSimpleTypedObject.getDataKind();
    long actualMaxLength = actualSimpleTypedObject.getMaxLength();
    Integer actualPrecision = actualSimpleTypedObject.getPrecision();
    Integer actualScale = actualSimpleTypedObject.getScale();
    int actualTypeID = actualSimpleTypedObject.getTypeID();
    long actualTypeModifiers = actualSimpleTypedObject.getTypeModifiers();

    // Assert
    assertEquals("Type Name", actualSimpleTypedObject.getTypeName());
    assertNull(actualPrecision);
    assertNull(actualScale);
    assertEquals(0, actualTypeID);
    assertEquals(0L, actualMaxLength);
    assertEquals(0L, actualTypeModifiers);
    assertEquals(DBPDataKind.OBJECT, actualDataKind);
  }

  /**
   * Test {@link SimpleTypedObject#getFullTypeName()}.
   *
   * <p>Method under test: {@link SimpleTypedObject#getFullTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SimpleTypedObject.getFullTypeName()"})
  public void testGetFullTypeName() {
    // Arrange, Act and Assert
    assertEquals("Object", SimpleTypedObject.DEFAULT_TYPE.getFullTypeName());
  }
}
