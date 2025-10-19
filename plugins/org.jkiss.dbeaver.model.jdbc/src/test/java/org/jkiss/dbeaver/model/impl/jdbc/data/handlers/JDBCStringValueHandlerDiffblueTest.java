package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
import org.mockito.Mockito;

public class JDBCStringValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCStringValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStringValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStringValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_givenSQLException_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException()).when(statement).setString(anyInt(), Mockito.<String>any());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> JDBCStringValueHandler.INSTANCE.bindParameter(session, statement, null, 1, "Value"));
    verify(statement).setString(1, "Value");
  }

  /**
   * Test {@link JDBCStringValueHandler#bindParameter(JDBCSession, JDBCPreparedStatement,
   * DBSTypedObject, int, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCPreparedStatement#setString(int, String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStringValueHandler#bindParameter(JDBCSession,
   * JDBCPreparedStatement, DBSTypedObject, int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStringValueHandler.bindParameter(JDBCSession, JDBCPreparedStatement, DBSTypedObject, int, Object)"
  })
  public void testBindParameter_thenCallsSetString() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCPreparedStatement statement = mock(JDBCPreparedStatement.class);
    doNothing().when(statement).setString(anyInt(), Mockito.<String>any());

    // Act
    JDBCStringValueHandler.INSTANCE.bindParameter(session, statement, null, 1, "Value");

    // Assert
    verify(statement).setString(1, "Value");
  }

  /**
   * Test {@link JDBCStringValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStringValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCStringValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_whenNull_thenReturnString() {
    // Arrange and Act
    Class<String> actualValueObjectType = JDBCStringValueHandler.INSTANCE.getValueObjectType(null);

    // Assert
    Class<String> expectedValueObjectType = String.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link JDBCStringValueHandler}
   *   <li>{@link JDBCStringValueHandler#getDefaultValueLabel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStringValueHandler.<init>()",
    "String JDBCStringValueHandler.getDefaultValueLabel()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Empty string", new JDBCStringValueHandler().getDefaultValueLabel());
  }
}
