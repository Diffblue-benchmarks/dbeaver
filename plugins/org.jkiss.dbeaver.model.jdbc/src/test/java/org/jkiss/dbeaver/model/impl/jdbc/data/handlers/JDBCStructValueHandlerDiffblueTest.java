package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.data.DBDDisplayFormat;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCStructImpl;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCComposite;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCCompositeDynamic;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCCompositeUnknown;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCStructValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown jdbcCompositeUnknown =
        new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());

    // Act
    String actualValueDisplayString =
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, jdbcCompositeUnknown, DBDDisplayFormat.UI);

    // Assert
    verify(session).getDataSource();
    assertEquals("[NULL]", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString2() {
    // Arrange, Act and Assert
    assertEquals(
        "42",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, new LocalNativeClientLocation("42", "java.lang"), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn42() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42]",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code [42, 42]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_given42_whenArrayListAdd42_thenReturn4242() {
    // Arrange
    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");

    // Act and Assert
    assertEquals(
        "[42, 42]",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, objectList, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   *   <li>When {@code EDIT}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_givenDBPDataSource_whenEdit_thenReturnEmptyString()
      throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    JDBCCompositeDynamic struct = new JDBCCompositeDynamic(session, null, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown jdbcCompositeUnknown =
        new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());

    // Act
    String actualValueDisplayString =
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, jdbcCompositeUnknown, DBDDisplayFormat.EDIT);

    // Assert
    verify(session).getDataSource();
    assertEquals("", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange, Act and Assert
    assertEquals(
        "[]",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, new ArrayList<>(), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>Then return {@code [null]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_thenReturnNull() throws DBCException {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    when(session.getDataSource()).thenReturn(mock(DBPDataSource.class));
    Object[] attributes = new Object[] {"Attributes"};
    JDBCStructImpl contents = new JDBCStructImpl("[NULL]", attributes, "42");

    JDBCCompositeDynamic struct =
        new JDBCCompositeDynamic(session, contents, new RowSetMetaDataImpl());
    JDBCCompositeDynamic struct2 = new JDBCCompositeDynamic(struct, new LoggingProgressMonitor());
    JDBCCompositeUnknown jdbcCompositeUnknown =
        new JDBCCompositeUnknown(struct2, new LoggingProgressMonitor());

    // Act
    String actualValueDisplayString =
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, jdbcCompositeUnknown, DBDDisplayFormat.UI);

    // Assert
    verify(session).getDataSource();
    assertEquals("[null]", actualValueDisplayString);
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenDBDDocumentXMLWithDocumentIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(
            null, new DBDDocumentXML(null), DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenFortyTwo_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals(
        "42", JDBCStructValueHandler.INSTANCE.getValueDisplayString(null, 42, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code [NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals(
        "[NULL]",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(null, null, DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject, Object,
   * DBDDisplayFormat)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueDisplayString(DBSTypedObject,
   * Object, DBDDisplayFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String JDBCStructValueHandler.getValueDisplayString(DBSTypedObject, Object, DBDDisplayFormat)"
  })
  public void testGetValueDisplayString_whenValue_thenReturnValue() {
    // Arrange, Act and Assert
    assertEquals(
        "Value",
        JDBCStructValueHandler.INSTANCE.getValueDisplayString(null, "Value", DBDDisplayFormat.UI));
  }

  /**
   * Test {@link JDBCStructValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link JDBCComposite}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCStructValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnJDBCComposite() {
    // Arrange and Act
    Class<?> actualValueObjectType = JDBCStructValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<JDBCComposite> expectedValueObjectType = JDBCComposite.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test {@link JDBCStructValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStructValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
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
        () -> JDBCStructValueHandler.INSTANCE.bindParameter(session, statement, null, 1, null));
    verify(statement).setNull(1, 2002);
  }

  /**
   * Test {@link JDBCStructValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setNull(int, int)} does
   *       nothing.
   *   <li>Then calls {@link JDBCPreparedStatement#setNull(int, int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStructValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenJDBCPreparedStatementSetNullDoesNothing_thenCallsSetNull()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setNull(anyInt(), anyInt());

    // Act
    JDBCStructValueHandler.INSTANCE.bindParameter(session, statement, null, 1, null);

    // Assert
    verify(statement).setNull(1, 2002);
  }

  /**
   * Test {@link JDBCStructValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStructValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStructValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenValue_thenThrowDBCException()
      throws SQLException, DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            JDBCStructValueHandler.INSTANCE.bindParameter(
                mock(JDBCSession.class), mock(JDBCPreparedStatement.class), null, 1, "Value"));
  }

  /**
   * Test new {@link JDBCStructValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JDBCStructValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStructValueHandler.<init>()"})
  public void testNewJDBCStructValueHandler() {
    // Arrange, Act and Assert
    assertNull(new JDBCStructValueHandler().getComparator());
  }
}
