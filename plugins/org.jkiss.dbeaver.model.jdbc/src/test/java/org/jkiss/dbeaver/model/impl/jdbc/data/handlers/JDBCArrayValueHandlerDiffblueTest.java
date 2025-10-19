package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.data.DBDCollection;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCCollection;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCArrayValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCArrayValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link DBDCollection}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCArrayValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnDBDCollection() {
    // Arrange and Act
    Class<DBDCollection> actualValueObjectType =
        JDBCArrayValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<DBDCollection> expectedValueObjectType = DBDCollection.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCArrayValueHandler#convertSingleValueToArray()}.
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#convertSingleValueToArray()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCArrayValueHandler.convertSingleValueToArray()"})
  public void testConvertSingleValueToArray() {
    // Arrange, Act and Assert
    assertTrue(JDBCArrayValueHandler.INSTANCE.convertSingleValueToArray());
  }

  /**
   * Test {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCArrayValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42]",
        JDBCArrayValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCArrayValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenDBDDocumentXMLWithDocumentIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCArrayValueHandler.INSTANCE.getValueDisplayString(
            null, new DBDDocumentXML(null), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link JDBCCollection#JDBCCollection()}.
   *   <li>Then return {@code NULL}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCArrayValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenJDBCCollection_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "NULL",
        JDBCArrayValueHandler.INSTANCE.getValueDisplayString(
            null, new JDBCCollection(), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCArrayValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCArrayValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCArrayValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        JDBCArrayValueHandler.INSTANCE.getValueDisplayString(null, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCArrayValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCArrayValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_givenSQLException_thenThrowSQLException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setNull(anyInt(), anyInt());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            JDBCArrayValueHandler.INSTANCE.bindParameter(
                session, statement, null, 1, new JDBCCollection()));
    verify(statement).setNull(1, 2003);
  }

  /**
   * Test {@link JDBCArrayValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setNull(int, int)} does
   *       nothing.
   *   <li>Then calls {@link JDBCPreparedStatement#setNull(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCArrayValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetNullDoesNothing_thenCallsSetNull()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setNull(anyInt(), anyInt());

    // Act
    JDBCArrayValueHandler.INSTANCE.bindParameter(session, statement, null, 1, new JDBCCollection());

    // Assert
    verify(statement).setNull(1, 2003);
  }

  /**
   * Test {@link JDBCArrayValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCArrayValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCArrayValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenValue_thenThrowDBCException()
      throws SQLException, DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            JDBCArrayValueHandler.INSTANCE.bindParameter(
                mock(JDBCSession.class), mock(JDBCPreparedStatement.class), null, 1, "Value"));
  }

  /**
   * Test new {@link JDBCArrayValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JDBCArrayValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCArrayValueHandler.<init>()"})
  public void testNewJDBCArrayValueHandler() {
    // Arrange, Act and Assert
    assertNull(new JDBCArrayValueHandler().getComparator());
  }
}
