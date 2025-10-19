package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCRowId;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCObjectValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCPreparedStatement#setObject(int, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_thenCallsSetObject() throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setObject(anyInt(), Mockito.<Object>any());
    doThrow(new SQLException())
        .when(statement)
        .setObject(anyInt(), Mockito.<Object>any(), anyInt());

    // Act
    JDBCObjectValueHandler.INSTANCE.bindParameter(
        session, statement, new SimpleTypedObject("Type Name"), 1, "Value");

    // Assert
    verify(statement).setObject(eq(1), isA(Object.class));
    verify(statement).setObject(eq(1), isA(Object.class), eq(0));
  }

  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setNull(int, int)} does
   *       nothing.
   *   <li>Then calls {@link JDBCPreparedStatement#setNull(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetNullDoesNothing_thenCallsSetNull()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setNull(anyInt(), anyInt());

    // Act
    JDBCObjectValueHandler.INSTANCE.bindParameter(
        session, statement, new SimpleTypedObject("Type Name"), 1, null);

    // Assert
    verify(statement).setNull(1, 0);
  }

  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setNull(int, int)} throw
   *       {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetNullThrowSQLException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setNull(anyInt(), anyInt());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            JDBCObjectValueHandler.INSTANCE.bindParameter(
                session, statement, new SimpleTypedObject("Type Name"), 1, null));
    verify(statement).setNull(1, 0);
  }

  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setObject(int, Object,
   *       int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetObjectDoesNothing()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setObject(anyInt(), Mockito.<Object>any(), anyInt());

    // Act
    JDBCObjectValueHandler.INSTANCE.bindParameter(
        session, statement, new SimpleTypedObject("Type Name"), 1, "Value");

    // Assert
    verify(statement).setObject(eq(1), isA(Object.class), eq(0));
  }

  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setObject(int, Object)}
   *       throw {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetObjectThrowSQLException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setObject(anyInt(), Mockito.<Object>any());
    doThrow(new SQLException())
        .when(statement)
        .setObject(anyInt(), Mockito.<Object>any(), anyInt());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            JDBCObjectValueHandler.INSTANCE.bindParameter(
                session, statement, new SimpleTypedObject("Type Name"), 1, "Value"));
    verify(statement).setObject(eq(1), isA(Object.class));
    verify(statement).setObject(eq(1), isA(Object.class), eq(0));
  }

  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setRowId(int, RowId)}
   *       does nothing.
   *   <li>Then calls {@link JDBCPreparedStatement#setRowId(int, RowId)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetRowIdDoesNothing_thenCallsSetRowId()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setRowId(anyInt(), Mockito.<RowId>any());
    SimpleTypedObject paramType = new SimpleTypedObject("Type Name");

    // Act
    JDBCObjectValueHandler.INSTANCE.bindParameter(
        session, statement, paramType, 1, new JDBCRowId(null));

    // Assert
    verify(statement).setRowId(eq(1), isNull());
  }

  /**
   * Test {@link JDBCObjectValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setRowId(int, RowId)}
   *       throw {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCObjectValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetRowIdThrowSQLException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setRowId(anyInt(), Mockito.<RowId>any());
    SimpleTypedObject paramType = new SimpleTypedObject("Type Name");

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            JDBCObjectValueHandler.INSTANCE.bindParameter(
                session, statement, paramType, 1, new JDBCRowId(null)));
    verify(statement).setRowId(eq(1), isNull());
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCObjectValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnObject() {
    // Arrange and Act
    Class<?> actualValueObjectType = JDBCObjectValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<Object> expectedValueObjectType = Object.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(
            null, new LocalNativeClientLocation("42", "java.lang"), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42]",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42, 42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn4242() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42, 42]",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code [42, [NULL]]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_givenDBDDocumentXMLWithDocumentIsNull_thenReturn42Null() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add(new DBDDocumentXML(null));

    // Act and Assert
    assertEquals(
        "[42, [NULL]]",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals(
        "[]",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(
            null, new ArrayList<>(), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code EDIT}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenEdit_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals(
        "",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.EDIT));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenFortyTwo_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals(
        "42", JDBCObjectValueHandler.INSTANCE.getValueDisplayString(null, 42, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code UI}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenUi_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.String JDBCObjectValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        JDBCObjectValueHandler.INSTANCE.getValueDisplayString(null, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test new {@link JDBCObjectValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JDBCObjectValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCObjectValueHandler.<init>()"})
  public void testNewJDBCObjectValueHandler() {
    // Arrange, Act and Assert
    assertNull(new JDBCObjectValueHandler().getComparator());
  }
}
