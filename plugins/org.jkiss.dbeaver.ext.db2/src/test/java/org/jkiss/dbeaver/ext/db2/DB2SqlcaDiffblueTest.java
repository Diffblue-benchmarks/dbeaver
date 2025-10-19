package org.jkiss.dbeaver.ext.db2;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Connection;
import java.sql.DataTruncation;
import java.sql.SQLException;
import java.sql.SQLWarning;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2SqlcaDiffblueTest {
  /**
   * Test {@link DB2Sqlca#from(Connection)} with {@code connection}.
   *
   * <p>Method under test: {@link DB2Sqlca#from(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2Sqlca DB2Sqlca.from(Connection)"})
  public void testFromWithConnection() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    DataTruncation dataTruncation = new DataTruncation(1, true, true, 3, 3);
    when(connection.getWarnings()).thenReturn(dataTruncation);

    // Act
    DB2Sqlca actualFromResult = DB2Sqlca.from(connection);

    // Assert
    verify(connection).getWarnings();
    assertNull(actualFromResult);
  }

  /**
   * Test {@link DB2Sqlca#from(Connection)} with {@code connection}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link Connection} {@link Connection#getWarnings()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2Sqlca#from(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2Sqlca DB2Sqlca.from(Connection)"})
  public void testFromWithConnection_givenNull_whenConnectionGetWarningsReturnNull()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getWarnings()).thenReturn(null);

    // Act
    DB2Sqlca actualFromResult = DB2Sqlca.from(connection);

    // Assert
    verify(connection).getWarnings();
    assertNull(actualFromResult);
  }

  /**
   * Test {@link DB2Sqlca#from(Connection)} with {@code connection}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link DB2Sqlca#from(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2Sqlca DB2Sqlca.from(Connection)"})
  public void testFromWithConnection_givenSQLException_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getWarnings()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(SQLException.class, () -> DB2Sqlca.from(connection));
    verify(connection).getWarnings();
  }

  /**
   * Test {@link DB2Sqlca#from(Connection)} with {@code connection}.
   *
   * <ul>
   *   <li>Given {@link SQLWarning#SQLWarning()}.
   *   <li>When {@link Connection} {@link Connection#getWarnings()} return {@link
   *       SQLWarning#SQLWarning()}.
   * </ul>
   *
   * <p>Method under test: {@link DB2Sqlca#from(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2Sqlca DB2Sqlca.from(Connection)"})
  public void testFromWithConnection_givenSQLWarning_whenConnectionGetWarningsReturnSQLWarning()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getWarnings()).thenReturn(new SQLWarning());

    // Act
    DB2Sqlca actualFromResult = DB2Sqlca.from(connection);

    // Assert
    verify(connection).getWarnings();
    assertNull(actualFromResult);
  }

  /**
   * Test {@link DB2Sqlca#from(SQLWarning)} with {@code warning}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2Sqlca#from(SQLWarning)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2Sqlca DB2Sqlca.from(SQLWarning)"})
  public void testFromWithWarning_whenNull() throws SQLException {
    // Arrange, Act and Assert
    assertNull(DB2Sqlca.from((SQLWarning) null));
  }

  /**
   * Test {@link DB2Sqlca#from(SQLWarning)} with {@code warning}.
   *
   * <ul>
   *   <li>When {@link SQLWarning#SQLWarning()}.
   * </ul>
   *
   * <p>Method under test: {@link DB2Sqlca#from(SQLWarning)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2Sqlca DB2Sqlca.from(SQLWarning)"})
  public void testFromWithWarning_whenSQLWarning() throws SQLException {
    // Arrange, Act and Assert
    assertNull(DB2Sqlca.from(new SQLWarning()));
  }
}
