package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.data.DBDContent;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.data.DBDValueError;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCContentValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCContentValueHandler#getValueContentType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Then return {@code application/octet-stream}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueContentType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCContentValueHandler.getValueContentType(DBSTypedObject)"})
  public void testGetValueContentType_thenReturnApplicationOctetStream() {
    // Arrange and Act
    String actualValueContentType =
        JDBCContentValueHandler.INSTANCE.getValueContentType(new SimpleTypedObject("Type Name"));

    // Assert
    assertEquals("application/octet-stream", actualValueContentType);
  }

  /**
   * Test {@link JDBCContentValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link DBDValueError#DBDValueError(Throwable)} with error is {@link
   *       Throwable#Throwable()}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCContentValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenDBDValueErrorWithErrorIsThrowable_thenThrowDBCException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            JDBCContentValueHandler.INSTANCE.bindParameter(
                session, statement, null, 1, new DBDValueError(new Throwable())));
  }

  /**
   * Test {@link JDBCContentValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCContentValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenValue_thenThrowDBCException()
      throws SQLException, DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            JDBCContentValueHandler.INSTANCE.bindParameter(
                mock(JDBCSession.class), mock(JDBCPreparedStatement.class), null, 1, "Value"));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link DBDContent}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCContentValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnDBDContent() {
    // Arrange and Act
    Class<DBDContent> actualValueObjectType =
        JDBCContentValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<DBDContent> expectedValueObjectType = DBDContent.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(
            null, new LocalNativeClientLocation("42", "java.lang"), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42]",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42, 42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn4242() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42, 42]",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals(
        "[]",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(
            null, new ArrayList<>(), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenDBDDocumentXMLWithDocumentIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(
            null, new DBDDocumentXML(null), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code EDIT}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenEdit_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.EDIT));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenFortyTwo_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(null, 42, DBDDisplayFormat.EDIT));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(null, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCContentValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCContentValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue2() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        JDBCContentValueHandler.INSTANCE.getValueDisplayString(null, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test new {@link JDBCContentValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JDBCContentValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCContentValueHandler.<init>()"})
  public void testNewJDBCContentValueHandler() {
    // Arrange, Act and Assert
    assertNull(new JDBCContentValueHandler().getComparator());
  }
}
