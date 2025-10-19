package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCBooleanValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_givenSQLException_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setBoolean(anyInt(), anyBoolean());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, "Value"));
    verify(statement).setBoolean(1, false);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>When forty-two.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_givenSQLException_whenFortyTwo_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setBoolean(anyInt(), anyBoolean());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, 42));
    verify(statement).setBoolean(1, true);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>When {@code true}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_givenSQLException_whenTrue_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setBoolean(anyInt(), anyBoolean());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, true));
    verify(statement).setBoolean(1, true);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then calls {@link JDBCPreparedStatement#setBoolean(int, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenFortyTwo_thenCallsSetBoolean() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setBoolean(anyInt(), anyBoolean());

    // Act
    JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, 42);

    // Assert
    verify(statement).setBoolean(1, true);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then calls {@link JDBCPreparedStatement#setBoolean(int, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenTrue_thenCallsSetBoolean() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setBoolean(anyInt(), anyBoolean());

    // Act
    JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, true);

    // Assert
    verify(statement).setBoolean(1, true);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then calls {@link JDBCPreparedStatement#setBoolean(int, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenValue_thenCallsSetBoolean() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setBoolean(anyInt(), anyBoolean());

    // Act
    JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, "Value");

    // Assert
    verify(statement).setBoolean(1, false);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then calls {@link JDBCPreparedStatement#setBoolean(int, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_whenZero_thenCallsSetBoolean() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setBoolean(anyInt(), anyBoolean());

    // Act
    JDBCBooleanValueHandler.INSTANCE.bindParameter(session, statement, null, 1, 0);

    // Assert
    verify(statement).setBoolean(1, false);
  }

  /**
   * Test {@link JDBCBooleanValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Boolean}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBooleanValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCBooleanValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnBoolean() {
    // Arrange and Act
    Class<Boolean> actualValueObjectType =
        JDBCBooleanValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<Boolean> expectedValueObjectType = Boolean.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link JDBCBooleanValueHandler}
   *   <li>{@link JDBCBooleanValueHandler#getDefaultValueLabel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCBooleanValueHandler.<init>()",
    "java.lang.String JDBCBooleanValueHandler.getDefaultValueLabel()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("False", new JDBCBooleanValueHandler().getDefaultValueLabel());
  }
}
