package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Ref;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCReferenceValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, new LocalNativeClientLocation("42", "java.lang"), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42]",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42, 42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn4242() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42, 42]",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals(
        "[]",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, new ArrayList<>(), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenDBDDocumentXMLWithDocumentIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, new DBDDocumentXML(null), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code EDIT}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenEdit_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, new DBDDocumentXML(null), DBDDisplayFormat.EDIT));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenFortyTwo_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(null, 42, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCReferenceValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        JDBCReferenceValueHandler.INSTANCE.getValueDisplayString(
            null, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCReferenceValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Ref}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCReferenceValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCReferenceValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnRef() {
    // Arrange and Act
    Class<Ref> actualValueObjectType = JDBCReferenceValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<Ref> expectedValueObjectType = Ref.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test new {@link JDBCReferenceValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JDBCReferenceValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCReferenceValueHandler.<init>()"})
  public void testNewJDBCReferenceValueHandler() {
    // Arrange, Act and Assert
    assertNull(new JDBCReferenceValueHandler().getComparator());
  }
}
