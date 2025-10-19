package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.DatabaseMetaData;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCDatabaseMetaDataImplDiffblueTest {
  /**
   * Test {@link JDBCDatabaseMetaDataImpl#JDBCDatabaseMetaDataImpl(JDBCSession, DatabaseMetaData)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#JDBCDatabaseMetaDataImpl(JDBCSession,
   * DatabaseMetaData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDatabaseMetaDataImpl.<init>(JDBCSession, DatabaseMetaData)"})
  public void testNewJDBCDatabaseMetaDataImpl() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    DatabaseMetaData original = mock(DatabaseMetaData.class);

    // Act
    JDBCDatabaseMetaDataImpl actualJdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(connection, original);

    // Assert
    assertSame(original, actualJdbcDatabaseMetaDataImpl.getOriginal());
    assertSame(connection, actualJdbcDatabaseMetaDataImpl.getConnection());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getOriginal()}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getOriginal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DatabaseMetaData JDBCDatabaseMetaDataImpl.getOriginal()"})
  public void testGetOriginal_thenDoesNotThrow() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), mock(DatabaseMetaData.class));

    // Act
    jdbcDatabaseMetaDataImpl.getOriginal();

    // Assert
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getOriginal()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getOriginal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DatabaseMetaData JDBCDatabaseMetaDataImpl.getOriginal()"})
  public void testGetOriginal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getOriginal());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSavepoints()"})
  public void testSupportsSavepoints() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSavepoints());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSavepoints()"})
  public void testSupportsSavepoints2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSavepoints()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSavepointsResult = jdbcDatabaseMetaDataImpl.supportsSavepoints();

    // Assert
    verify(original).supportsSavepoints();
    assertTrue(actualSupportsSavepointsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsSavepoints()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSavepoints()"})
  public void testSupportsSavepoints_givenDatabaseMetaDataSupportsSavepointsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSavepoints()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSavepoints());
    verify(original).supportsSavepoints();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSavepoints()"})
  public void testSupportsSavepoints_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSavepoints()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSavepointsResult = jdbcDatabaseMetaDataImpl.supportsSavepoints();

    // Assert
    verify(original).supportsSavepoints();
    assertFalse(actualSupportsSavepointsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSavepoints()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSavepoints()"})
  public void testSupportsSavepoints_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSavepoints()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSavepointsResult = jdbcDatabaseMetaDataImpl.supportsSavepoints();

    // Assert
    verify(original).supportsSavepoints();
    assertTrue(actualSupportsSavepointsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNamedParameters()"})
  public void testSupportsNamedParameters() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsNamedParameters());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNamedParameters()"})
  public void testSupportsNamedParameters2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNamedParameters()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsNamedParameters());
    verify(original).supportsNamedParameters();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNamedParameters()"})
  public void testSupportsNamedParameters3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNamedParameters()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsNamedParametersResult =
        jdbcDatabaseMetaDataImpl.supportsNamedParameters();

    // Assert
    verify(original).supportsNamedParameters();
    assertTrue(actualSupportsNamedParametersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNamedParameters()"})
  public void testSupportsNamedParameters_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNamedParameters()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsNamedParametersResult =
        jdbcDatabaseMetaDataImpl.supportsNamedParameters();

    // Assert
    verify(original).supportsNamedParameters();
    assertFalse(actualSupportsNamedParametersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNamedParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNamedParameters()"})
  public void testSupportsNamedParameters_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNamedParameters()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsNamedParametersResult =
        jdbcDatabaseMetaDataImpl.supportsNamedParameters();

    // Assert
    verify(original).supportsNamedParameters();
    assertTrue(actualSupportsNamedParametersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleOpenResults()"})
  public void testSupportsMultipleOpenResults() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMultipleOpenResults());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleOpenResults()"})
  public void testSupportsMultipleOpenResults2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleOpenResults()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMultipleOpenResults());
    verify(original).supportsMultipleOpenResults();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleOpenResults()"})
  public void testSupportsMultipleOpenResults3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleOpenResults()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsMultipleOpenResultsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleOpenResults();

    // Assert
    verify(original).supportsMultipleOpenResults();
    assertTrue(actualSupportsMultipleOpenResultsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleOpenResults()"})
  public void testSupportsMultipleOpenResults_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleOpenResults()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMultipleOpenResultsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleOpenResults();

    // Assert
    verify(original).supportsMultipleOpenResults();
    assertFalse(actualSupportsMultipleOpenResultsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleOpenResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleOpenResults()"})
  public void testSupportsMultipleOpenResults_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleOpenResults()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMultipleOpenResultsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleOpenResults();

    // Assert
    verify(original).supportsMultipleOpenResults();
    assertTrue(actualSupportsMultipleOpenResultsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGetGeneratedKeys()"})
  public void testSupportsGetGeneratedKeys() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGetGeneratedKeys());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGetGeneratedKeys()"})
  public void testSupportsGetGeneratedKeys2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGetGeneratedKeys()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGetGeneratedKeys());
    verify(original).supportsGetGeneratedKeys();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGetGeneratedKeys()"})
  public void testSupportsGetGeneratedKeys3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGetGeneratedKeys()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsGetGeneratedKeysResult =
        jdbcDatabaseMetaDataImpl.supportsGetGeneratedKeys();

    // Assert
    verify(original).supportsGetGeneratedKeys();
    assertTrue(actualSupportsGetGeneratedKeysResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGetGeneratedKeys()"})
  public void testSupportsGetGeneratedKeys_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGetGeneratedKeys()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGetGeneratedKeysResult =
        jdbcDatabaseMetaDataImpl.supportsGetGeneratedKeys();

    // Assert
    verify(original).supportsGetGeneratedKeys();
    assertFalse(actualSupportsGetGeneratedKeysResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGetGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGetGeneratedKeys()"})
  public void testSupportsGetGeneratedKeys_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGetGeneratedKeys()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGetGeneratedKeysResult =
        jdbcDatabaseMetaDataImpl.supportsGetGeneratedKeys();

    // Assert
    verify(original).supportsGetGeneratedKeys();
    assertTrue(actualSupportsGetGeneratedKeysResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetHoldability(int)"})
  public void testSupportsResultSetHoldability() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsResultSetHoldability(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetHoldability(int)"})
  public void testSupportsResultSetHoldability2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetHoldability(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsResultSetHoldability(1));
    verify(original).supportsResultSetHoldability(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetHoldability(int)"})
  public void testSupportsResultSetHoldability3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetHoldability(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsResultSetHoldabilityResult =
        jdbcDatabaseMetaDataImpl.supportsResultSetHoldability(1);

    // Assert
    verify(original).supportsResultSetHoldability(1);
    assertTrue(actualSupportsResultSetHoldabilityResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetHoldability(int)"})
  public void testSupportsResultSetHoldability_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetHoldability(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsResultSetHoldabilityResult =
        jdbcDatabaseMetaDataImpl.supportsResultSetHoldability(1);

    // Assert
    verify(original).supportsResultSetHoldability(1);
    assertFalse(actualSupportsResultSetHoldabilityResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetHoldability(int)"})
  public void testSupportsResultSetHoldability_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetHoldability(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsResultSetHoldabilityResult =
        jdbcDatabaseMetaDataImpl.supportsResultSetHoldability(1);

    // Assert
    verify(original).supportsResultSetHoldability(1);
    assertTrue(actualSupportsResultSetHoldabilityResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getResultSetHoldability()"})
  public void testGetResultSetHoldability() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getResultSetHoldability());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getResultSetHoldability()"})
  public void testGetResultSetHoldability2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getResultSetHoldability()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getResultSetHoldability());
    verify(original).getResultSetHoldability();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getResultSetHoldability()"})
  public void testGetResultSetHoldability3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getResultSetHoldability()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualResultSetHoldability = jdbcDatabaseMetaDataImpl.getResultSetHoldability();

    // Assert
    verify(original).getResultSetHoldability();
    assertEquals(1, actualResultSetHoldability);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getResultSetHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getResultSetHoldability()"})
  public void testGetResultSetHoldability_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getResultSetHoldability()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualResultSetHoldability = jdbcDatabaseMetaDataImpl.getResultSetHoldability();

    // Assert
    verify(original).getResultSetHoldability();
    assertEquals(1, actualResultSetHoldability);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMajorVersion()"})
  public void testGetDatabaseMajorVersion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseMajorVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMajorVersion()"})
  public void testGetDatabaseMajorVersion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseMajorVersion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseMajorVersion());
    verify(original).getDatabaseMajorVersion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMajorVersion()"})
  public void testGetDatabaseMajorVersion3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseMajorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualDatabaseMajorVersion = jdbcDatabaseMetaDataImpl.getDatabaseMajorVersion();

    // Assert
    verify(original).getDatabaseMajorVersion();
    assertEquals(1, actualDatabaseMajorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMajorVersion()"})
  public void testGetDatabaseMajorVersion_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseMajorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualDatabaseMajorVersion = jdbcDatabaseMetaDataImpl.getDatabaseMajorVersion();

    // Assert
    verify(original).getDatabaseMajorVersion();
    assertEquals(1, actualDatabaseMajorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMinorVersion()"})
  public void testGetDatabaseMinorVersion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseMinorVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMinorVersion()"})
  public void testGetDatabaseMinorVersion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseMinorVersion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseMinorVersion());
    verify(original).getDatabaseMinorVersion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMinorVersion()"})
  public void testGetDatabaseMinorVersion3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseMinorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualDatabaseMinorVersion = jdbcDatabaseMetaDataImpl.getDatabaseMinorVersion();

    // Assert
    verify(original).getDatabaseMinorVersion();
    assertEquals(1, actualDatabaseMinorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDatabaseMinorVersion()"})
  public void testGetDatabaseMinorVersion_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseMinorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualDatabaseMinorVersion = jdbcDatabaseMetaDataImpl.getDatabaseMinorVersion();

    // Assert
    verify(original).getDatabaseMinorVersion();
    assertEquals(1, actualDatabaseMinorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMajorVersion()"})
  public void testGetJDBCMajorVersion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getJDBCMajorVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMajorVersion()"})
  public void testGetJDBCMajorVersion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getJDBCMajorVersion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getJDBCMajorVersion());
    verify(original).getJDBCMajorVersion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMajorVersion()"})
  public void testGetJDBCMajorVersion3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getJDBCMajorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualJDBCMajorVersion = jdbcDatabaseMetaDataImpl.getJDBCMajorVersion();

    // Assert
    verify(original).getJDBCMajorVersion();
    assertEquals(1, actualJDBCMajorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMajorVersion()"})
  public void testGetJDBCMajorVersion_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getJDBCMajorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualJDBCMajorVersion = jdbcDatabaseMetaDataImpl.getJDBCMajorVersion();

    // Assert
    verify(original).getJDBCMajorVersion();
    assertEquals(1, actualJDBCMajorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMinorVersion()"})
  public void testGetJDBCMinorVersion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getJDBCMinorVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMinorVersion()"})
  public void testGetJDBCMinorVersion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getJDBCMinorVersion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getJDBCMinorVersion());
    verify(original).getJDBCMinorVersion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMinorVersion()"})
  public void testGetJDBCMinorVersion3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getJDBCMinorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualJDBCMinorVersion = jdbcDatabaseMetaDataImpl.getJDBCMinorVersion();

    // Assert
    verify(original).getJDBCMinorVersion();
    assertEquals(1, actualJDBCMinorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getJDBCMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getJDBCMinorVersion()"})
  public void testGetJDBCMinorVersion_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getJDBCMinorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualJDBCMinorVersion = jdbcDatabaseMetaDataImpl.getJDBCMinorVersion();

    // Assert
    verify(original).getJDBCMinorVersion();
    assertEquals(1, actualJDBCMinorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getSQLStateType()"})
  public void testGetSQLStateType() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSQLStateType());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getSQLStateType()"})
  public void testGetSQLStateType2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSQLStateType()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualSQLStateType = jdbcDatabaseMetaDataImpl.getSQLStateType();

    // Assert
    verify(original).getSQLStateType();
    assertEquals(1, actualSQLStateType);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getSQLStateType()} return one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getSQLStateType()"})
  public void testGetSQLStateType_givenDatabaseMetaDataGetSQLStateTypeReturnOne_thenReturnOne()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSQLStateType()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualSQLStateType = jdbcDatabaseMetaDataImpl.getSQLStateType();

    // Assert
    verify(original).getSQLStateType();
    assertEquals(1, actualSQLStateType);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getSQLStateType()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLStateType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getSQLStateType()"})
  public void testGetSQLStateType_givenDatabaseMetaDataGetSQLStateTypeThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSQLStateType()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSQLStateType());
    verify(original).getSQLStateType();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.locatorsUpdateCopy()"})
  public void testLocatorsUpdateCopy() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.locatorsUpdateCopy());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.locatorsUpdateCopy()"})
  public void testLocatorsUpdateCopy2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.locatorsUpdateCopy()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualLocatorsUpdateCopyResult = jdbcDatabaseMetaDataImpl.locatorsUpdateCopy();

    // Assert
    verify(original).locatorsUpdateCopy();
    assertTrue(actualLocatorsUpdateCopyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#locatorsUpdateCopy()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.locatorsUpdateCopy()"})
  public void testLocatorsUpdateCopy_givenDatabaseMetaDataLocatorsUpdateCopyThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.locatorsUpdateCopy()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.locatorsUpdateCopy());
    verify(original).locatorsUpdateCopy();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.locatorsUpdateCopy()"})
  public void testLocatorsUpdateCopy_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.locatorsUpdateCopy()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualLocatorsUpdateCopyResult = jdbcDatabaseMetaDataImpl.locatorsUpdateCopy();

    // Assert
    verify(original).locatorsUpdateCopy();
    assertFalse(actualLocatorsUpdateCopyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#locatorsUpdateCopy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.locatorsUpdateCopy()"})
  public void testLocatorsUpdateCopy_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.locatorsUpdateCopy()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualLocatorsUpdateCopyResult = jdbcDatabaseMetaDataImpl.locatorsUpdateCopy();

    // Assert
    verify(original).locatorsUpdateCopy();
    assertTrue(actualLocatorsUpdateCopyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStatementPooling()"})
  public void testSupportsStatementPooling() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsStatementPooling());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStatementPooling()"})
  public void testSupportsStatementPooling2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStatementPooling()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsStatementPooling());
    verify(original).supportsStatementPooling();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStatementPooling()"})
  public void testSupportsStatementPooling3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStatementPooling()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsStatementPoolingResult =
        jdbcDatabaseMetaDataImpl.supportsStatementPooling();

    // Assert
    verify(original).supportsStatementPooling();
    assertTrue(actualSupportsStatementPoolingResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStatementPooling()"})
  public void testSupportsStatementPooling_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStatementPooling()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsStatementPoolingResult =
        jdbcDatabaseMetaDataImpl.supportsStatementPooling();

    // Assert
    verify(original).supportsStatementPooling();
    assertFalse(actualSupportsStatementPoolingResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStatementPooling()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStatementPooling()"})
  public void testSupportsStatementPooling_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStatementPooling()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsStatementPoolingResult =
        jdbcDatabaseMetaDataImpl.supportsStatementPooling();

    // Assert
    verify(original).supportsStatementPooling();
    assertTrue(actualSupportsStatementPoolingResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RowIdLifetime JDBCDatabaseMetaDataImpl.getRowIdLifetime()"})
  public void testGetRowIdLifetime() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getRowIdLifetime());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RowIdLifetime JDBCDatabaseMetaDataImpl.getRowIdLifetime()"})
  public void testGetRowIdLifetime2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getRowIdLifetime()).thenReturn(RowIdLifetime.ROWID_UNSUPPORTED);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    RowIdLifetime actualRowIdLifetime = jdbcDatabaseMetaDataImpl.getRowIdLifetime();

    // Assert
    verify(original).getRowIdLifetime();
    assertEquals(RowIdLifetime.ROWID_UNSUPPORTED, actualRowIdLifetime);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getRowIdLifetime()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RowIdLifetime JDBCDatabaseMetaDataImpl.getRowIdLifetime()"})
  public void testGetRowIdLifetime_givenDatabaseMetaDataGetRowIdLifetimeThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getRowIdLifetime()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getRowIdLifetime());
    verify(original).getRowIdLifetime();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}.
   *
   * <ul>
   *   <li>Then return {@code ROWID_UNSUPPORTED}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getRowIdLifetime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RowIdLifetime JDBCDatabaseMetaDataImpl.getRowIdLifetime()"})
  public void testGetRowIdLifetime_thenReturnRowidUnsupported() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getRowIdLifetime()).thenReturn(RowIdLifetime.ROWID_UNSUPPORTED);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    RowIdLifetime actualRowIdLifetime = jdbcDatabaseMetaDataImpl.getRowIdLifetime();

    // Assert
    verify(original).getRowIdLifetime();
    assertEquals(RowIdLifetime.ROWID_UNSUPPORTED, actualRowIdLifetime);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax()"})
  public void testSupportsStoredFunctionsUsingCallSyntax() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax()"})
  public void testSupportsStoredFunctionsUsingCallSyntax2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredFunctionsUsingCallSyntax()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax());
    verify(original).supportsStoredFunctionsUsingCallSyntax();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax()"})
  public void testSupportsStoredFunctionsUsingCallSyntax3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredFunctionsUsingCallSyntax()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsStoredFunctionsUsingCallSyntaxResult =
        jdbcDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax();

    // Assert
    verify(original).supportsStoredFunctionsUsingCallSyntax();
    assertTrue(actualSupportsStoredFunctionsUsingCallSyntaxResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax()"})
  public void testSupportsStoredFunctionsUsingCallSyntax_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredFunctionsUsingCallSyntax()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsStoredFunctionsUsingCallSyntaxResult =
        jdbcDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax();

    // Assert
    verify(original).supportsStoredFunctionsUsingCallSyntax();
    assertFalse(actualSupportsStoredFunctionsUsingCallSyntaxResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredFunctionsUsingCallSyntax()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax()"})
  public void testSupportsStoredFunctionsUsingCallSyntax_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredFunctionsUsingCallSyntax()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsStoredFunctionsUsingCallSyntaxResult =
        jdbcDatabaseMetaDataImpl.supportsStoredFunctionsUsingCallSyntax();

    // Assert
    verify(original).supportsStoredFunctionsUsingCallSyntax();
    assertTrue(actualSupportsStoredFunctionsUsingCallSyntaxResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets()"})
  public void testAutoCommitFailureClosesAllResultSets() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets()"})
  public void testAutoCommitFailureClosesAllResultSets2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.autoCommitFailureClosesAllResultSets()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets());
    verify(original).autoCommitFailureClosesAllResultSets();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets()"})
  public void testAutoCommitFailureClosesAllResultSets3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.autoCommitFailureClosesAllResultSets()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualAutoCommitFailureClosesAllResultSetsResult =
        jdbcDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets();

    // Assert
    verify(original).autoCommitFailureClosesAllResultSets();
    assertTrue(actualAutoCommitFailureClosesAllResultSetsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets()"})
  public void testAutoCommitFailureClosesAllResultSets_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.autoCommitFailureClosesAllResultSets()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualAutoCommitFailureClosesAllResultSetsResult =
        jdbcDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets();

    // Assert
    verify(original).autoCommitFailureClosesAllResultSets();
    assertFalse(actualAutoCommitFailureClosesAllResultSetsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#autoCommitFailureClosesAllResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets()"})
  public void testAutoCommitFailureClosesAllResultSets_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.autoCommitFailureClosesAllResultSets()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualAutoCommitFailureClosesAllResultSetsResult =
        jdbcDatabaseMetaDataImpl.autoCommitFailureClosesAllResultSets();

    // Assert
    verify(original).autoCommitFailureClosesAllResultSets();
    assertTrue(actualAutoCommitFailureClosesAllResultSetsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.generatedKeyAlwaysReturned()"})
  public void testGeneratedKeyAlwaysReturned() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.generatedKeyAlwaysReturned());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.generatedKeyAlwaysReturned()"})
  public void testGeneratedKeyAlwaysReturned2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.generatedKeyAlwaysReturned()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.generatedKeyAlwaysReturned());
    verify(original).generatedKeyAlwaysReturned();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.generatedKeyAlwaysReturned()"})
  public void testGeneratedKeyAlwaysReturned3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.generatedKeyAlwaysReturned()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualGeneratedKeyAlwaysReturnedResult =
        jdbcDatabaseMetaDataImpl.generatedKeyAlwaysReturned();

    // Assert
    verify(original).generatedKeyAlwaysReturned();
    assertTrue(actualGeneratedKeyAlwaysReturnedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.generatedKeyAlwaysReturned()"})
  public void testGeneratedKeyAlwaysReturned_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.generatedKeyAlwaysReturned()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualGeneratedKeyAlwaysReturnedResult =
        jdbcDatabaseMetaDataImpl.generatedKeyAlwaysReturned();

    // Assert
    verify(original).generatedKeyAlwaysReturned();
    assertFalse(actualGeneratedKeyAlwaysReturnedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#generatedKeyAlwaysReturned()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.generatedKeyAlwaysReturned()"})
  public void testGeneratedKeyAlwaysReturned_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.generatedKeyAlwaysReturned()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualGeneratedKeyAlwaysReturnedResult =
        jdbcDatabaseMetaDataImpl.generatedKeyAlwaysReturned();

    // Assert
    verify(original).generatedKeyAlwaysReturned();
    assertTrue(actualGeneratedKeyAlwaysReturnedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#unwrap(Class)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCDatabaseMetaDataImpl.unwrap(Class)"})
  public void testUnwrap() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.unwrap(iface));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#unwrap(Class)} return {@code
   *       Unwrap}.
   *   <li>Then return {@code Unwrap}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCDatabaseMetaDataImpl.unwrap(Class)"})
  public void testUnwrap_givenDatabaseMetaDataUnwrapReturnUnwrap_thenReturnUnwrap()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.unwrap(Object.class)).thenReturn("Unwrap");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    Class<Object> iface = Object.class;

    // Act
    Object actualUnwrapResult = jdbcDatabaseMetaDataImpl.unwrap(iface);

    // Assert
    verify(original).unwrap(isA(Class.class));
    assertEquals("Unwrap", actualUnwrapResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#unwrap(Class)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCDatabaseMetaDataImpl.unwrap(Class)"})
  public void testUnwrap_givenDatabaseMetaDataUnwrapThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.unwrap(Object.class)).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.unwrap(iface));
    verify(original).unwrap(isA(Class.class));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.isWrapperFor(iface));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcDatabaseMetaDataImpl.isWrapperFor(iface);

    // Assert
    verify(original).isWrapperFor(isA(Class.class));
    assertTrue(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isWrapperFor(Class)} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenDatabaseMetaDataIsWrapperForReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcDatabaseMetaDataImpl.isWrapperFor(iface);

    // Assert
    verify(original).isWrapperFor(isA(Class.class));
    assertFalse(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isWrapperFor(Class)} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenDatabaseMetaDataIsWrapperForReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcDatabaseMetaDataImpl.isWrapperFor(iface);

    // Assert
    verify(original).isWrapperFor(isA(Class.class));
    assertTrue(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isWrapperFor(Class)} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenDatabaseMetaDataIsWrapperForThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isWrapperFor(Mockito.<Class<?>>any())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.isWrapperFor(iface));
    verify(original).isWrapperFor(isA(Class.class));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allProceduresAreCallable()"})
  public void testAllProceduresAreCallable() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.allProceduresAreCallable());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allProceduresAreCallable()"})
  public void testAllProceduresAreCallable2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allProceduresAreCallable()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.allProceduresAreCallable());
    verify(original).allProceduresAreCallable();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allProceduresAreCallable()"})
  public void testAllProceduresAreCallable3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allProceduresAreCallable()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualAllProceduresAreCallableResult =
        jdbcDatabaseMetaDataImpl.allProceduresAreCallable();

    // Assert
    verify(original).allProceduresAreCallable();
    assertTrue(actualAllProceduresAreCallableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allProceduresAreCallable()"})
  public void testAllProceduresAreCallable_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allProceduresAreCallable()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualAllProceduresAreCallableResult =
        jdbcDatabaseMetaDataImpl.allProceduresAreCallable();

    // Assert
    verify(original).allProceduresAreCallable();
    assertFalse(actualAllProceduresAreCallableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allProceduresAreCallable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allProceduresAreCallable()"})
  public void testAllProceduresAreCallable_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allProceduresAreCallable()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualAllProceduresAreCallableResult =
        jdbcDatabaseMetaDataImpl.allProceduresAreCallable();

    // Assert
    verify(original).allProceduresAreCallable();
    assertTrue(actualAllProceduresAreCallableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allTablesAreSelectable()"})
  public void testAllTablesAreSelectable() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.allTablesAreSelectable());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allTablesAreSelectable()"})
  public void testAllTablesAreSelectable2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allTablesAreSelectable()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.allTablesAreSelectable());
    verify(original).allTablesAreSelectable();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allTablesAreSelectable()"})
  public void testAllTablesAreSelectable3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allTablesAreSelectable()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualAllTablesAreSelectableResult = jdbcDatabaseMetaDataImpl.allTablesAreSelectable();

    // Assert
    verify(original).allTablesAreSelectable();
    assertTrue(actualAllTablesAreSelectableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allTablesAreSelectable()"})
  public void testAllTablesAreSelectable_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allTablesAreSelectable()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualAllTablesAreSelectableResult = jdbcDatabaseMetaDataImpl.allTablesAreSelectable();

    // Assert
    verify(original).allTablesAreSelectable();
    assertFalse(actualAllTablesAreSelectableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#allTablesAreSelectable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.allTablesAreSelectable()"})
  public void testAllTablesAreSelectable_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.allTablesAreSelectable()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualAllTablesAreSelectableResult = jdbcDatabaseMetaDataImpl.allTablesAreSelectable();

    // Assert
    verify(original).allTablesAreSelectable();
    assertTrue(actualAllTablesAreSelectableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getURL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getURL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getURL()"})
  public void testGetURL() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getURL());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getURL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getURL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getURL()"})
  public void testGetURL2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getURL()).thenReturn("https://example.org/example");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualURL = jdbcDatabaseMetaDataImpl.getURL();

    // Assert
    verify(original).getURL();
    assertEquals("https://example.org/example", actualURL);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getURL()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getURL()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getURL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getURL()"})
  public void testGetURL_givenDatabaseMetaDataGetURLThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getURL()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getURL());
    verify(original).getURL();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getURL()}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getURL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getURL()"})
  public void testGetURL_thenReturnHttpsExampleOrgExample() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getURL()).thenReturn("https://example.org/example");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualURL = jdbcDatabaseMetaDataImpl.getURL();

    // Assert
    verify(original).getURL();
    assertEquals("https://example.org/example", actualURL);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getUserName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getUserName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getUserName()"})
  public void testGetUserName() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getUserName());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getUserName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getUserName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getUserName()"})
  public void testGetUserName2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getUserName()).thenReturn("janedoe");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualUserName = jdbcDatabaseMetaDataImpl.getUserName();

    // Assert
    verify(original).getUserName();
    assertEquals("janedoe", actualUserName);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getUserName()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getUserName()} return {@code
   *       janedoe}.
   *   <li>Then return {@code janedoe}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getUserName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getUserName()"})
  public void testGetUserName_givenDatabaseMetaDataGetUserNameReturnJanedoe_thenReturnJanedoe()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getUserName()).thenReturn("janedoe");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualUserName = jdbcDatabaseMetaDataImpl.getUserName();

    // Assert
    verify(original).getUserName();
    assertEquals("janedoe", actualUserName);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getUserName()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getUserName()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getUserName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getUserName()"})
  public void testGetUserName_givenDatabaseMetaDataGetUserNameThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getUserName()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getUserName());
    verify(original).getUserName();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isReadOnly()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isReadOnly()"})
  public void testIsReadOnly() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.isReadOnly());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isReadOnly()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isReadOnly()"})
  public void testIsReadOnly2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isReadOnly()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualIsReadOnlyResult = jdbcDatabaseMetaDataImpl.isReadOnly();

    // Assert
    verify(original).isReadOnly();
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isReadOnly()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isReadOnly()} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isReadOnly()"})
  public void testIsReadOnly_givenDatabaseMetaDataIsReadOnlyReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isReadOnly()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualIsReadOnlyResult = jdbcDatabaseMetaDataImpl.isReadOnly();

    // Assert
    verify(original).isReadOnly();
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isReadOnly()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isReadOnly()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isReadOnly()"})
  public void testIsReadOnly_givenDatabaseMetaDataIsReadOnlyReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isReadOnly()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualIsReadOnlyResult = jdbcDatabaseMetaDataImpl.isReadOnly();

    // Assert
    verify(original).isReadOnly();
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isReadOnly()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isReadOnly()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isReadOnly()"})
  public void testIsReadOnly_givenDatabaseMetaDataIsReadOnlyThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isReadOnly()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.isReadOnly());
    verify(original).isReadOnly();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedHigh()"})
  public void testNullsAreSortedHigh() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedHigh());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedHigh()"})
  public void testNullsAreSortedHigh2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedHigh()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualNullsAreSortedHighResult = jdbcDatabaseMetaDataImpl.nullsAreSortedHigh();

    // Assert
    verify(original).nullsAreSortedHigh();
    assertTrue(actualNullsAreSortedHighResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#nullsAreSortedHigh()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedHigh()"})
  public void testNullsAreSortedHigh_givenDatabaseMetaDataNullsAreSortedHighThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedHigh()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedHigh());
    verify(original).nullsAreSortedHigh();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedHigh()"})
  public void testNullsAreSortedHigh_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedHigh()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedHighResult = jdbcDatabaseMetaDataImpl.nullsAreSortedHigh();

    // Assert
    verify(original).nullsAreSortedHigh();
    assertFalse(actualNullsAreSortedHighResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedHigh()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedHigh()"})
  public void testNullsAreSortedHigh_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedHigh()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedHighResult = jdbcDatabaseMetaDataImpl.nullsAreSortedHigh();

    // Assert
    verify(original).nullsAreSortedHigh();
    assertTrue(actualNullsAreSortedHighResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedLow()"})
  public void testNullsAreSortedLow() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedLow());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedLow()"})
  public void testNullsAreSortedLow2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedLow()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualNullsAreSortedLowResult = jdbcDatabaseMetaDataImpl.nullsAreSortedLow();

    // Assert
    verify(original).nullsAreSortedLow();
    assertTrue(actualNullsAreSortedLowResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#nullsAreSortedLow()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedLow()"})
  public void testNullsAreSortedLow_givenDatabaseMetaDataNullsAreSortedLowThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedLow()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedLow());
    verify(original).nullsAreSortedLow();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedLow()"})
  public void testNullsAreSortedLow_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedLow()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedLowResult = jdbcDatabaseMetaDataImpl.nullsAreSortedLow();

    // Assert
    verify(original).nullsAreSortedLow();
    assertFalse(actualNullsAreSortedLowResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedLow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedLow()"})
  public void testNullsAreSortedLow_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedLow()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedLowResult = jdbcDatabaseMetaDataImpl.nullsAreSortedLow();

    // Assert
    verify(original).nullsAreSortedLow();
    assertTrue(actualNullsAreSortedLowResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtStart()"})
  public void testNullsAreSortedAtStart() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedAtStart());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtStart()"})
  public void testNullsAreSortedAtStart2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtStart()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedAtStart());
    verify(original).nullsAreSortedAtStart();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtStart()"})
  public void testNullsAreSortedAtStart3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtStart()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualNullsAreSortedAtStartResult = jdbcDatabaseMetaDataImpl.nullsAreSortedAtStart();

    // Assert
    verify(original).nullsAreSortedAtStart();
    assertTrue(actualNullsAreSortedAtStartResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtStart()"})
  public void testNullsAreSortedAtStart_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtStart()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedAtStartResult = jdbcDatabaseMetaDataImpl.nullsAreSortedAtStart();

    // Assert
    verify(original).nullsAreSortedAtStart();
    assertFalse(actualNullsAreSortedAtStartResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtStart()"})
  public void testNullsAreSortedAtStart_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtStart()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedAtStartResult = jdbcDatabaseMetaDataImpl.nullsAreSortedAtStart();

    // Assert
    verify(original).nullsAreSortedAtStart();
    assertTrue(actualNullsAreSortedAtStartResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtEnd()"})
  public void testNullsAreSortedAtEnd() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedAtEnd());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtEnd()"})
  public void testNullsAreSortedAtEnd2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtEnd()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullsAreSortedAtEnd());
    verify(original).nullsAreSortedAtEnd();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtEnd()"})
  public void testNullsAreSortedAtEnd3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtEnd()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualNullsAreSortedAtEndResult = jdbcDatabaseMetaDataImpl.nullsAreSortedAtEnd();

    // Assert
    verify(original).nullsAreSortedAtEnd();
    assertTrue(actualNullsAreSortedAtEndResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtEnd()"})
  public void testNullsAreSortedAtEnd_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtEnd()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedAtEndResult = jdbcDatabaseMetaDataImpl.nullsAreSortedAtEnd();

    // Assert
    verify(original).nullsAreSortedAtEnd();
    assertFalse(actualNullsAreSortedAtEndResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullsAreSortedAtEnd()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullsAreSortedAtEnd()"})
  public void testNullsAreSortedAtEnd_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullsAreSortedAtEnd()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullsAreSortedAtEndResult = jdbcDatabaseMetaDataImpl.nullsAreSortedAtEnd();

    // Assert
    verify(original).nullsAreSortedAtEnd();
    assertTrue(actualNullsAreSortedAtEndResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductName()"})
  public void testGetDatabaseProductName() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseProductName());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductName()"})
  public void testGetDatabaseProductName2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseProductName()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseProductName());
    verify(original).getDatabaseProductName();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductName()"})
  public void testGetDatabaseProductName3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseProductName()).thenReturn("Database Product Name");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualDatabaseProductName = jdbcDatabaseMetaDataImpl.getDatabaseProductName();

    // Assert
    verify(original).getDatabaseProductName();
    assertEquals("Database Product Name", actualDatabaseProductName);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}.
   *
   * <ul>
   *   <li>Then return {@code Database Product Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductName()"})
  public void testGetDatabaseProductName_thenReturnDatabaseProductName() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseProductName()).thenReturn("Database Product Name");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualDatabaseProductName = jdbcDatabaseMetaDataImpl.getDatabaseProductName();

    // Assert
    verify(original).getDatabaseProductName();
    assertEquals("Database Product Name", actualDatabaseProductName);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductVersion()"})
  public void testGetDatabaseProductVersion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseProductVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductVersion()"})
  public void testGetDatabaseProductVersion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseProductVersion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDatabaseProductVersion());
    verify(original).getDatabaseProductVersion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductVersion()"})
  public void testGetDatabaseProductVersion3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseProductVersion()).thenReturn("1.0.2");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualDatabaseProductVersion = jdbcDatabaseMetaDataImpl.getDatabaseProductVersion();

    // Assert
    verify(original).getDatabaseProductVersion();
    assertEquals("1.0.2", actualDatabaseProductVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}.
   *
   * <ul>
   *   <li>Then return {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDatabaseProductVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDatabaseProductVersion()"})
  public void testGetDatabaseProductVersion_thenReturn102() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDatabaseProductVersion()).thenReturn("1.0.2");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualDatabaseProductVersion = jdbcDatabaseMetaDataImpl.getDatabaseProductVersion();

    // Assert
    verify(original).getDatabaseProductVersion();
    assertEquals("1.0.2", actualDatabaseProductVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverName()"})
  public void testGetDriverName() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDriverName());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverName()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverName()"})
  public void testGetDriverName2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverName()).thenReturn("Driver Name");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualDriverName = jdbcDatabaseMetaDataImpl.getDriverName();

    // Assert
    verify(original).getDriverName();
    assertEquals("Driver Name", actualDriverName);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverName()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getDriverName()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverName()"})
  public void testGetDriverName_givenDatabaseMetaDataGetDriverNameThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverName()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDriverName());
    verify(original).getDriverName();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverName()}.
   *
   * <ul>
   *   <li>Then return {@code Driver Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverName()"})
  public void testGetDriverName_thenReturnDriverName() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverName()).thenReturn("Driver Name");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualDriverName = jdbcDatabaseMetaDataImpl.getDriverName();

    // Assert
    verify(original).getDriverName();
    assertEquals("Driver Name", actualDriverName);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverVersion()"})
  public void testGetDriverVersion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDriverVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverVersion()"})
  public void testGetDriverVersion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverVersion()).thenReturn("1.0.2");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualDriverVersion = jdbcDatabaseMetaDataImpl.getDriverVersion();

    // Assert
    verify(original).getDriverVersion();
    assertEquals("1.0.2", actualDriverVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getDriverVersion()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverVersion()"})
  public void testGetDriverVersion_givenDatabaseMetaDataGetDriverVersionThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverVersion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDriverVersion());
    verify(original).getDriverVersion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}.
   *
   * <ul>
   *   <li>Then return {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getDriverVersion()"})
  public void testGetDriverVersion_thenReturn102() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverVersion()).thenReturn("1.0.2");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualDriverVersion = jdbcDatabaseMetaDataImpl.getDriverVersion();

    // Assert
    verify(original).getDriverVersion();
    assertEquals("1.0.2", actualDriverVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverMajorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDriverMajorVersion()"})
  public void testGetDriverMajorVersion() {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverMajorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualDriverMajorVersion = jdbcDatabaseMetaDataImpl.getDriverMajorVersion();

    // Assert
    verify(original).getDriverMajorVersion();
    assertEquals(1, actualDriverMajorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverMajorVersion()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDriverMajorVersion()"})
  public void testGetDriverMajorVersion_thenReturnOne() {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverMajorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualDriverMajorVersion = jdbcDatabaseMetaDataImpl.getDriverMajorVersion();

    // Assert
    verify(original).getDriverMajorVersion();
    assertEquals(1, actualDriverMajorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverMajorVersion()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDriverMajorVersion()"})
  public void testGetDriverMajorVersion_thenReturnZero() {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertEquals(0, jdbcDatabaseMetaDataImpl.getDriverMajorVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverMinorVersion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDriverMinorVersion()"})
  public void testGetDriverMinorVersion() {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverMinorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualDriverMinorVersion = jdbcDatabaseMetaDataImpl.getDriverMinorVersion();

    // Assert
    verify(original).getDriverMinorVersion();
    assertEquals(1, actualDriverMinorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverMinorVersion()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDriverMinorVersion()"})
  public void testGetDriverMinorVersion_thenReturnOne() {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDriverMinorVersion()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualDriverMinorVersion = jdbcDatabaseMetaDataImpl.getDriverMinorVersion();

    // Assert
    verify(original).getDriverMinorVersion();
    assertEquals(1, actualDriverMinorVersion);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDriverMinorVersion()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDriverMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDriverMinorVersion()"})
  public void testGetDriverMinorVersion_thenReturnZero() {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertEquals(0, jdbcDatabaseMetaDataImpl.getDriverMinorVersion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFiles()"})
  public void testUsesLocalFiles() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.usesLocalFiles());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFiles()"})
  public void testUsesLocalFiles2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFiles()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualUsesLocalFilesResult = jdbcDatabaseMetaDataImpl.usesLocalFiles();

    // Assert
    verify(original).usesLocalFiles();
    assertTrue(actualUsesLocalFilesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#usesLocalFiles()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFiles()"})
  public void testUsesLocalFiles_givenDatabaseMetaDataUsesLocalFilesReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFiles()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualUsesLocalFilesResult = jdbcDatabaseMetaDataImpl.usesLocalFiles();

    // Assert
    verify(original).usesLocalFiles();
    assertTrue(actualUsesLocalFilesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#usesLocalFiles()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFiles()"})
  public void testUsesLocalFiles_givenDatabaseMetaDataUsesLocalFilesThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFiles()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.usesLocalFiles());
    verify(original).usesLocalFiles();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFiles()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFiles()"})
  public void testUsesLocalFiles_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFiles()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualUsesLocalFilesResult = jdbcDatabaseMetaDataImpl.usesLocalFiles();

    // Assert
    verify(original).usesLocalFiles();
    assertFalse(actualUsesLocalFilesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFilePerTable()"})
  public void testUsesLocalFilePerTable() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.usesLocalFilePerTable());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFilePerTable()"})
  public void testUsesLocalFilePerTable2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFilePerTable()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.usesLocalFilePerTable());
    verify(original).usesLocalFilePerTable();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFilePerTable()"})
  public void testUsesLocalFilePerTable3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFilePerTable()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualUsesLocalFilePerTableResult = jdbcDatabaseMetaDataImpl.usesLocalFilePerTable();

    // Assert
    verify(original).usesLocalFilePerTable();
    assertTrue(actualUsesLocalFilePerTableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFilePerTable()"})
  public void testUsesLocalFilePerTable_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFilePerTable()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualUsesLocalFilePerTableResult = jdbcDatabaseMetaDataImpl.usesLocalFilePerTable();

    // Assert
    verify(original).usesLocalFilePerTable();
    assertFalse(actualUsesLocalFilePerTableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#usesLocalFilePerTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.usesLocalFilePerTable()"})
  public void testUsesLocalFilePerTable_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.usesLocalFilePerTable()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualUsesLocalFilePerTableResult = jdbcDatabaseMetaDataImpl.usesLocalFilePerTable();

    // Assert
    verify(original).usesLocalFilePerTable();
    assertTrue(actualUsesLocalFilePerTableResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseIdentifiers()"})
  public void testSupportsMixedCaseIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMixedCaseIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseIdentifiers()"})
  public void testSupportsMixedCaseIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMixedCaseIdentifiers());
    verify(original).supportsMixedCaseIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseIdentifiers()"})
  public void testSupportsMixedCaseIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsMixedCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.supportsMixedCaseIdentifiers();

    // Assert
    verify(original).supportsMixedCaseIdentifiers();
    assertTrue(actualSupportsMixedCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseIdentifiers()"})
  public void testSupportsMixedCaseIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMixedCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.supportsMixedCaseIdentifiers();

    // Assert
    verify(original).supportsMixedCaseIdentifiers();
    assertFalse(actualSupportsMixedCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseIdentifiers()"})
  public void testSupportsMixedCaseIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMixedCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.supportsMixedCaseIdentifiers();

    // Assert
    verify(original).supportsMixedCaseIdentifiers();
    assertTrue(actualSupportsMixedCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseIdentifiers()"})
  public void testStoresUpperCaseIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesUpperCaseIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseIdentifiers()"})
  public void testStoresUpperCaseIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesUpperCaseIdentifiers());
    verify(original).storesUpperCaseIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseIdentifiers()"})
  public void testStoresUpperCaseIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualStoresUpperCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesUpperCaseIdentifiers();

    // Assert
    verify(original).storesUpperCaseIdentifiers();
    assertTrue(actualStoresUpperCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseIdentifiers()"})
  public void testStoresUpperCaseIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresUpperCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesUpperCaseIdentifiers();

    // Assert
    verify(original).storesUpperCaseIdentifiers();
    assertFalse(actualStoresUpperCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseIdentifiers()"})
  public void testStoresUpperCaseIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresUpperCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesUpperCaseIdentifiers();

    // Assert
    verify(original).storesUpperCaseIdentifiers();
    assertTrue(actualStoresUpperCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseIdentifiers()"})
  public void testStoresLowerCaseIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesLowerCaseIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseIdentifiers()"})
  public void testStoresLowerCaseIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesLowerCaseIdentifiers());
    verify(original).storesLowerCaseIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseIdentifiers()"})
  public void testStoresLowerCaseIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualStoresLowerCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesLowerCaseIdentifiers();

    // Assert
    verify(original).storesLowerCaseIdentifiers();
    assertTrue(actualStoresLowerCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseIdentifiers()"})
  public void testStoresLowerCaseIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresLowerCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesLowerCaseIdentifiers();

    // Assert
    verify(original).storesLowerCaseIdentifiers();
    assertFalse(actualStoresLowerCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseIdentifiers()"})
  public void testStoresLowerCaseIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresLowerCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesLowerCaseIdentifiers();

    // Assert
    verify(original).storesLowerCaseIdentifiers();
    assertTrue(actualStoresLowerCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseIdentifiers()"})
  public void testStoresMixedCaseIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesMixedCaseIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseIdentifiers()"})
  public void testStoresMixedCaseIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesMixedCaseIdentifiers());
    verify(original).storesMixedCaseIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseIdentifiers()"})
  public void testStoresMixedCaseIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualStoresMixedCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesMixedCaseIdentifiers();

    // Assert
    verify(original).storesMixedCaseIdentifiers();
    assertTrue(actualStoresMixedCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseIdentifiers()"})
  public void testStoresMixedCaseIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresMixedCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesMixedCaseIdentifiers();

    // Assert
    verify(original).storesMixedCaseIdentifiers();
    assertFalse(actualStoresMixedCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseIdentifiers()"})
  public void testStoresMixedCaseIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresMixedCaseIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesMixedCaseIdentifiers();

    // Assert
    verify(original).storesMixedCaseIdentifiers();
    assertTrue(actualStoresMixedCaseIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers()"})
  public void testSupportsMixedCaseQuotedIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers()"})
  public void testSupportsMixedCaseQuotedIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseQuotedIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers());
    verify(original).supportsMixedCaseQuotedIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers()"})
  public void testSupportsMixedCaseQuotedIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsMixedCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers();

    // Assert
    verify(original).supportsMixedCaseQuotedIdentifiers();
    assertTrue(actualSupportsMixedCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers()"})
  public void testSupportsMixedCaseQuotedIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseQuotedIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMixedCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers();

    // Assert
    verify(original).supportsMixedCaseQuotedIdentifiers();
    assertFalse(actualSupportsMixedCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers()"})
  public void testSupportsMixedCaseQuotedIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMixedCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.supportsMixedCaseQuotedIdentifiers();

    // Assert
    verify(original).supportsMixedCaseQuotedIdentifiers();
    assertTrue(actualSupportsMixedCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers()"})
  public void testStoresUpperCaseQuotedIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers()"})
  public void testStoresUpperCaseQuotedIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseQuotedIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers());
    verify(original).storesUpperCaseQuotedIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers()"})
  public void testStoresUpperCaseQuotedIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualStoresUpperCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers();

    // Assert
    verify(original).storesUpperCaseQuotedIdentifiers();
    assertTrue(actualStoresUpperCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers()"})
  public void testStoresUpperCaseQuotedIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseQuotedIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresUpperCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers();

    // Assert
    verify(original).storesUpperCaseQuotedIdentifiers();
    assertFalse(actualStoresUpperCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesUpperCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers()"})
  public void testStoresUpperCaseQuotedIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesUpperCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresUpperCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesUpperCaseQuotedIdentifiers();

    // Assert
    verify(original).storesUpperCaseQuotedIdentifiers();
    assertTrue(actualStoresUpperCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers()"})
  public void testStoresLowerCaseQuotedIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers()"})
  public void testStoresLowerCaseQuotedIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseQuotedIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers());
    verify(original).storesLowerCaseQuotedIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers()"})
  public void testStoresLowerCaseQuotedIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualStoresLowerCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers();

    // Assert
    verify(original).storesLowerCaseQuotedIdentifiers();
    assertTrue(actualStoresLowerCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers()"})
  public void testStoresLowerCaseQuotedIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseQuotedIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresLowerCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers();

    // Assert
    verify(original).storesLowerCaseQuotedIdentifiers();
    assertFalse(actualStoresLowerCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesLowerCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers()"})
  public void testStoresLowerCaseQuotedIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesLowerCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresLowerCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesLowerCaseQuotedIdentifiers();

    // Assert
    verify(original).storesLowerCaseQuotedIdentifiers();
    assertTrue(actualStoresLowerCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers()"})
  public void testStoresMixedCaseQuotedIdentifiers() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers()"})
  public void testStoresMixedCaseQuotedIdentifiers2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseQuotedIdentifiers()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers());
    verify(original).storesMixedCaseQuotedIdentifiers();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers()"})
  public void testStoresMixedCaseQuotedIdentifiers3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualStoresMixedCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers();

    // Assert
    verify(original).storesMixedCaseQuotedIdentifiers();
    assertTrue(actualStoresMixedCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers()"})
  public void testStoresMixedCaseQuotedIdentifiers_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseQuotedIdentifiers()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresMixedCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers();

    // Assert
    verify(original).storesMixedCaseQuotedIdentifiers();
    assertFalse(actualStoresMixedCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#storesMixedCaseQuotedIdentifiers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers()"})
  public void testStoresMixedCaseQuotedIdentifiers_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.storesMixedCaseQuotedIdentifiers()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualStoresMixedCaseQuotedIdentifiersResult =
        jdbcDatabaseMetaDataImpl.storesMixedCaseQuotedIdentifiers();

    // Assert
    verify(original).storesMixedCaseQuotedIdentifiers();
    assertTrue(actualStoresMixedCaseQuotedIdentifiersResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getIdentifierQuoteString()"})
  public void testGetIdentifierQuoteString() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getIdentifierQuoteString());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getIdentifierQuoteString()"})
  public void testGetIdentifierQuoteString2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getIdentifierQuoteString()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getIdentifierQuoteString());
    verify(original).getIdentifierQuoteString();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getIdentifierQuoteString()"})
  public void testGetIdentifierQuoteString3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualIdentifierQuoteString = jdbcDatabaseMetaDataImpl.getIdentifierQuoteString();

    // Assert
    verify(original).getIdentifierQuoteString();
    assertEquals("Identifier Quote String", actualIdentifierQuoteString);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}.
   *
   * <ul>
   *   <li>Then return {@code Identifier Quote String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getIdentifierQuoteString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getIdentifierQuoteString()"})
  public void testGetIdentifierQuoteString_thenReturnIdentifierQuoteString() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getIdentifierQuoteString()).thenReturn("Identifier Quote String");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualIdentifierQuoteString = jdbcDatabaseMetaDataImpl.getIdentifierQuoteString();

    // Assert
    verify(original).getIdentifierQuoteString();
    assertEquals("Identifier Quote String", actualIdentifierQuoteString);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSQLKeywords()"})
  public void testGetSQLKeywords() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSQLKeywords());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSQLKeywords()"})
  public void testGetSQLKeywords2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSQLKeywords()).thenReturn("Sql Keywords");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualSQLKeywords = jdbcDatabaseMetaDataImpl.getSQLKeywords();

    // Assert
    verify(original).getSQLKeywords();
    assertEquals("Sql Keywords", actualSQLKeywords);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getSQLKeywords()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSQLKeywords()"})
  public void testGetSQLKeywords_givenDatabaseMetaDataGetSQLKeywordsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSQLKeywords()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSQLKeywords());
    verify(original).getSQLKeywords();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}.
   *
   * <ul>
   *   <li>Then return {@code Sql Keywords}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSQLKeywords()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSQLKeywords()"})
  public void testGetSQLKeywords_thenReturnSqlKeywords() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSQLKeywords()).thenReturn("Sql Keywords");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualSQLKeywords = jdbcDatabaseMetaDataImpl.getSQLKeywords();

    // Assert
    verify(original).getSQLKeywords();
    assertEquals("Sql Keywords", actualSQLKeywords);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getNumericFunctions()"})
  public void testGetNumericFunctions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getNumericFunctions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getNumericFunctions()"})
  public void testGetNumericFunctions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getNumericFunctions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getNumericFunctions());
    verify(original).getNumericFunctions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getNumericFunctions()"})
  public void testGetNumericFunctions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getNumericFunctions()).thenReturn("Numeric Functions");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualNumericFunctions = jdbcDatabaseMetaDataImpl.getNumericFunctions();

    // Assert
    verify(original).getNumericFunctions();
    assertEquals("Numeric Functions", actualNumericFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}.
   *
   * <ul>
   *   <li>Then return {@code Numeric Functions}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getNumericFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getNumericFunctions()"})
  public void testGetNumericFunctions_thenReturnNumericFunctions() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getNumericFunctions()).thenReturn("Numeric Functions");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualNumericFunctions = jdbcDatabaseMetaDataImpl.getNumericFunctions();

    // Assert
    verify(original).getNumericFunctions();
    assertEquals("Numeric Functions", actualNumericFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getStringFunctions()"})
  public void testGetStringFunctions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getStringFunctions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getStringFunctions()"})
  public void testGetStringFunctions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getStringFunctions()).thenReturn("String Functions");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualStringFunctions = jdbcDatabaseMetaDataImpl.getStringFunctions();

    // Assert
    verify(original).getStringFunctions();
    assertEquals("String Functions", actualStringFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getStringFunctions()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getStringFunctions()"})
  public void testGetStringFunctions_givenDatabaseMetaDataGetStringFunctionsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getStringFunctions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getStringFunctions());
    verify(original).getStringFunctions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}.
   *
   * <ul>
   *   <li>Then return {@code String Functions}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getStringFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getStringFunctions()"})
  public void testGetStringFunctions_thenReturnStringFunctions() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getStringFunctions()).thenReturn("String Functions");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualStringFunctions = jdbcDatabaseMetaDataImpl.getStringFunctions();

    // Assert
    verify(original).getStringFunctions();
    assertEquals("String Functions", actualStringFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSystemFunctions()"})
  public void testGetSystemFunctions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSystemFunctions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSystemFunctions()"})
  public void testGetSystemFunctions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSystemFunctions()).thenReturn("System Functions");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualSystemFunctions = jdbcDatabaseMetaDataImpl.getSystemFunctions();

    // Assert
    verify(original).getSystemFunctions();
    assertEquals("System Functions", actualSystemFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getSystemFunctions()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSystemFunctions()"})
  public void testGetSystemFunctions_givenDatabaseMetaDataGetSystemFunctionsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSystemFunctions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSystemFunctions());
    verify(original).getSystemFunctions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}.
   *
   * <ul>
   *   <li>Then return {@code System Functions}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSystemFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSystemFunctions()"})
  public void testGetSystemFunctions_thenReturnSystemFunctions() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSystemFunctions()).thenReturn("System Functions");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualSystemFunctions = jdbcDatabaseMetaDataImpl.getSystemFunctions();

    // Assert
    verify(original).getSystemFunctions();
    assertEquals("System Functions", actualSystemFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getTimeDateFunctions()"})
  public void testGetTimeDateFunctions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getTimeDateFunctions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getTimeDateFunctions()"})
  public void testGetTimeDateFunctions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getTimeDateFunctions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getTimeDateFunctions());
    verify(original).getTimeDateFunctions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getTimeDateFunctions()"})
  public void testGetTimeDateFunctions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getTimeDateFunctions()).thenReturn("2020-03-01");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualTimeDateFunctions = jdbcDatabaseMetaDataImpl.getTimeDateFunctions();

    // Assert
    verify(original).getTimeDateFunctions();
    assertEquals("2020-03-01", actualTimeDateFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}.
   *
   * <ul>
   *   <li>Then return {@code 2020-03-01}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getTimeDateFunctions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getTimeDateFunctions()"})
  public void testGetTimeDateFunctions_thenReturn20200301() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getTimeDateFunctions()).thenReturn("2020-03-01");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualTimeDateFunctions = jdbcDatabaseMetaDataImpl.getTimeDateFunctions();

    // Assert
    verify(original).getTimeDateFunctions();
    assertEquals("2020-03-01", actualTimeDateFunctions);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSearchStringEscape()"})
  public void testGetSearchStringEscape() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSearchStringEscape());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSearchStringEscape()"})
  public void testGetSearchStringEscape2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSearchStringEscape()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSearchStringEscape());
    verify(original).getSearchStringEscape();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSearchStringEscape()"})
  public void testGetSearchStringEscape3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSearchStringEscape()).thenReturn("Search String Escape");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualSearchStringEscape = jdbcDatabaseMetaDataImpl.getSearchStringEscape();

    // Assert
    verify(original).getSearchStringEscape();
    assertEquals("Search String Escape", actualSearchStringEscape);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}.
   *
   * <ul>
   *   <li>Then return {@code Search String Escape}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSearchStringEscape()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSearchStringEscape()"})
  public void testGetSearchStringEscape_thenReturnSearchStringEscape() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSearchStringEscape()).thenReturn("Search String Escape");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualSearchStringEscape = jdbcDatabaseMetaDataImpl.getSearchStringEscape();

    // Assert
    verify(original).getSearchStringEscape();
    assertEquals("Search String Escape", actualSearchStringEscape);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getExtraNameCharacters()"})
  public void testGetExtraNameCharacters() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getExtraNameCharacters());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getExtraNameCharacters()"})
  public void testGetExtraNameCharacters2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getExtraNameCharacters()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getExtraNameCharacters());
    verify(original).getExtraNameCharacters();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getExtraNameCharacters()"})
  public void testGetExtraNameCharacters3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualExtraNameCharacters = jdbcDatabaseMetaDataImpl.getExtraNameCharacters();

    // Assert
    verify(original).getExtraNameCharacters();
    assertEquals("Extra Name Characters", actualExtraNameCharacters);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}.
   *
   * <ul>
   *   <li>Then return {@code Extra Name Characters}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getExtraNameCharacters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getExtraNameCharacters()"})
  public void testGetExtraNameCharacters_thenReturnExtraNameCharacters() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getExtraNameCharacters()).thenReturn("Extra Name Characters");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualExtraNameCharacters = jdbcDatabaseMetaDataImpl.getExtraNameCharacters();

    // Assert
    verify(original).getExtraNameCharacters();
    assertEquals("Extra Name Characters", actualExtraNameCharacters);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithAddColumn()"})
  public void testSupportsAlterTableWithAddColumn() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsAlterTableWithAddColumn());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithAddColumn()"})
  public void testSupportsAlterTableWithAddColumn2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithAddColumn()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsAlterTableWithAddColumn());
    verify(original).supportsAlterTableWithAddColumn();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithAddColumn()"})
  public void testSupportsAlterTableWithAddColumn3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithAddColumn()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsAlterTableWithAddColumnResult =
        jdbcDatabaseMetaDataImpl.supportsAlterTableWithAddColumn();

    // Assert
    verify(original).supportsAlterTableWithAddColumn();
    assertTrue(actualSupportsAlterTableWithAddColumnResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithAddColumn()"})
  public void testSupportsAlterTableWithAddColumn_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithAddColumn()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsAlterTableWithAddColumnResult =
        jdbcDatabaseMetaDataImpl.supportsAlterTableWithAddColumn();

    // Assert
    verify(original).supportsAlterTableWithAddColumn();
    assertFalse(actualSupportsAlterTableWithAddColumnResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithAddColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithAddColumn()"})
  public void testSupportsAlterTableWithAddColumn_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithAddColumn()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsAlterTableWithAddColumnResult =
        jdbcDatabaseMetaDataImpl.supportsAlterTableWithAddColumn();

    // Assert
    verify(original).supportsAlterTableWithAddColumn();
    assertTrue(actualSupportsAlterTableWithAddColumnResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithDropColumn()"})
  public void testSupportsAlterTableWithDropColumn() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsAlterTableWithDropColumn());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithDropColumn()"})
  public void testSupportsAlterTableWithDropColumn2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithDropColumn()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsAlterTableWithDropColumn());
    verify(original).supportsAlterTableWithDropColumn();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithDropColumn()"})
  public void testSupportsAlterTableWithDropColumn3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithDropColumn()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsAlterTableWithDropColumnResult =
        jdbcDatabaseMetaDataImpl.supportsAlterTableWithDropColumn();

    // Assert
    verify(original).supportsAlterTableWithDropColumn();
    assertTrue(actualSupportsAlterTableWithDropColumnResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithDropColumn()"})
  public void testSupportsAlterTableWithDropColumn_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithDropColumn()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsAlterTableWithDropColumnResult =
        jdbcDatabaseMetaDataImpl.supportsAlterTableWithDropColumn();

    // Assert
    verify(original).supportsAlterTableWithDropColumn();
    assertFalse(actualSupportsAlterTableWithDropColumnResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsAlterTableWithDropColumn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsAlterTableWithDropColumn()"})
  public void testSupportsAlterTableWithDropColumn_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsAlterTableWithDropColumn()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsAlterTableWithDropColumnResult =
        jdbcDatabaseMetaDataImpl.supportsAlterTableWithDropColumn();

    // Assert
    verify(original).supportsAlterTableWithDropColumn();
    assertTrue(actualSupportsAlterTableWithDropColumnResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsColumnAliasing()"})
  public void testSupportsColumnAliasing() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsColumnAliasing());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsColumnAliasing()"})
  public void testSupportsColumnAliasing2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsColumnAliasing()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsColumnAliasing());
    verify(original).supportsColumnAliasing();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsColumnAliasing()"})
  public void testSupportsColumnAliasing3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsColumnAliasing()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsColumnAliasingResult = jdbcDatabaseMetaDataImpl.supportsColumnAliasing();

    // Assert
    verify(original).supportsColumnAliasing();
    assertTrue(actualSupportsColumnAliasingResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsColumnAliasing()"})
  public void testSupportsColumnAliasing_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsColumnAliasing()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsColumnAliasingResult = jdbcDatabaseMetaDataImpl.supportsColumnAliasing();

    // Assert
    verify(original).supportsColumnAliasing();
    assertFalse(actualSupportsColumnAliasingResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsColumnAliasing()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsColumnAliasing()"})
  public void testSupportsColumnAliasing_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsColumnAliasing()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsColumnAliasingResult = jdbcDatabaseMetaDataImpl.supportsColumnAliasing();

    // Assert
    verify(original).supportsColumnAliasing();
    assertTrue(actualSupportsColumnAliasingResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullPlusNonNullIsNull()"})
  public void testNullPlusNonNullIsNull() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullPlusNonNullIsNull());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullPlusNonNullIsNull()"})
  public void testNullPlusNonNullIsNull2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullPlusNonNullIsNull()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.nullPlusNonNullIsNull());
    verify(original).nullPlusNonNullIsNull();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullPlusNonNullIsNull()"})
  public void testNullPlusNonNullIsNull3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullPlusNonNullIsNull()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualNullPlusNonNullIsNullResult = jdbcDatabaseMetaDataImpl.nullPlusNonNullIsNull();

    // Assert
    verify(original).nullPlusNonNullIsNull();
    assertTrue(actualNullPlusNonNullIsNullResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullPlusNonNullIsNull()"})
  public void testNullPlusNonNullIsNull_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullPlusNonNullIsNull()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullPlusNonNullIsNullResult = jdbcDatabaseMetaDataImpl.nullPlusNonNullIsNull();

    // Assert
    verify(original).nullPlusNonNullIsNull();
    assertFalse(actualNullPlusNonNullIsNullResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#nullPlusNonNullIsNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.nullPlusNonNullIsNull()"})
  public void testNullPlusNonNullIsNull_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.nullPlusNonNullIsNull()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualNullPlusNonNullIsNullResult = jdbcDatabaseMetaDataImpl.nullPlusNonNullIsNull();

    // Assert
    verify(original).nullPlusNonNullIsNull();
    assertTrue(actualNullPlusNonNullIsNullResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert()"})
  public void testSupportsConvert() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsConvert());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert()"})
  public void testSupportsConvert2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsConvertResult = jdbcDatabaseMetaDataImpl.supportsConvert();

    // Assert
    verify(original).supportsConvert();
    assertTrue(actualSupportsConvertResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert(int, int)"})
  public void testSupportsConvertWithIntInt() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsConvert(1, 1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert(int, int)"})
  public void testSupportsConvertWithIntInt2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert(anyInt(), anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsConvert(1, 1));
    verify(original).supportsConvert(1, 1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert(int, int)"})
  public void testSupportsConvertWithIntInt3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert(anyInt(), anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsConvertResult = jdbcDatabaseMetaDataImpl.supportsConvert(1, 1);

    // Assert
    verify(original).supportsConvert(1, 1);
    assertTrue(actualSupportsConvertResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert(int, int)"})
  public void testSupportsConvertWithIntInt_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert(anyInt(), anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsConvertResult = jdbcDatabaseMetaDataImpl.supportsConvert(1, 1);

    // Assert
    verify(original).supportsConvert(1, 1);
    assertFalse(actualSupportsConvertResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert(int, int)"})
  public void testSupportsConvertWithIntInt_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert(anyInt(), anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsConvertResult = jdbcDatabaseMetaDataImpl.supportsConvert(1, 1);

    // Assert
    verify(original).supportsConvert(1, 1);
    assertTrue(actualSupportsConvertResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsConvert()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert()"})
  public void testSupportsConvert_givenDatabaseMetaDataSupportsConvertThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsConvert());
    verify(original).supportsConvert();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert()"})
  public void testSupportsConvert_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsConvertResult = jdbcDatabaseMetaDataImpl.supportsConvert();

    // Assert
    verify(original).supportsConvert();
    assertFalse(actualSupportsConvertResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsConvert()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsConvert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsConvert()"})
  public void testSupportsConvert_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsConvert()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsConvertResult = jdbcDatabaseMetaDataImpl.supportsConvert();

    // Assert
    verify(original).supportsConvert();
    assertTrue(actualSupportsConvertResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTableCorrelationNames()"})
  public void testSupportsTableCorrelationNames() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsTableCorrelationNames());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTableCorrelationNames()"})
  public void testSupportsTableCorrelationNames2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTableCorrelationNames()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsTableCorrelationNames());
    verify(original).supportsTableCorrelationNames();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTableCorrelationNames()"})
  public void testSupportsTableCorrelationNames3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTableCorrelationNames()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsTableCorrelationNamesResult =
        jdbcDatabaseMetaDataImpl.supportsTableCorrelationNames();

    // Assert
    verify(original).supportsTableCorrelationNames();
    assertTrue(actualSupportsTableCorrelationNamesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTableCorrelationNames()"})
  public void testSupportsTableCorrelationNames_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTableCorrelationNames()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsTableCorrelationNamesResult =
        jdbcDatabaseMetaDataImpl.supportsTableCorrelationNames();

    // Assert
    verify(original).supportsTableCorrelationNames();
    assertFalse(actualSupportsTableCorrelationNamesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTableCorrelationNames()"})
  public void testSupportsTableCorrelationNames_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTableCorrelationNames()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsTableCorrelationNamesResult =
        jdbcDatabaseMetaDataImpl.supportsTableCorrelationNames();

    // Assert
    verify(original).supportsTableCorrelationNames();
    assertTrue(actualSupportsTableCorrelationNamesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames()"})
  public void testSupportsDifferentTableCorrelationNames() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames()"})
  public void testSupportsDifferentTableCorrelationNames2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDifferentTableCorrelationNames()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames());
    verify(original).supportsDifferentTableCorrelationNames();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames()"})
  public void testSupportsDifferentTableCorrelationNames3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDifferentTableCorrelationNames()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsDifferentTableCorrelationNamesResult =
        jdbcDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames();

    // Assert
    verify(original).supportsDifferentTableCorrelationNames();
    assertTrue(actualSupportsDifferentTableCorrelationNamesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames()"})
  public void testSupportsDifferentTableCorrelationNames_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDifferentTableCorrelationNames()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsDifferentTableCorrelationNamesResult =
        jdbcDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames();

    // Assert
    verify(original).supportsDifferentTableCorrelationNames();
    assertFalse(actualSupportsDifferentTableCorrelationNamesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsDifferentTableCorrelationNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames()"})
  public void testSupportsDifferentTableCorrelationNames_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDifferentTableCorrelationNames()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsDifferentTableCorrelationNamesResult =
        jdbcDatabaseMetaDataImpl.supportsDifferentTableCorrelationNames();

    // Assert
    verify(original).supportsDifferentTableCorrelationNames();
    assertTrue(actualSupportsDifferentTableCorrelationNamesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExpressionsInOrderBy()"})
  public void testSupportsExpressionsInOrderBy() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsExpressionsInOrderBy());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExpressionsInOrderBy()"})
  public void testSupportsExpressionsInOrderBy2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExpressionsInOrderBy()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsExpressionsInOrderBy());
    verify(original).supportsExpressionsInOrderBy();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExpressionsInOrderBy()"})
  public void testSupportsExpressionsInOrderBy3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExpressionsInOrderBy()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsExpressionsInOrderByResult =
        jdbcDatabaseMetaDataImpl.supportsExpressionsInOrderBy();

    // Assert
    verify(original).supportsExpressionsInOrderBy();
    assertTrue(actualSupportsExpressionsInOrderByResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExpressionsInOrderBy()"})
  public void testSupportsExpressionsInOrderBy_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExpressionsInOrderBy()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsExpressionsInOrderByResult =
        jdbcDatabaseMetaDataImpl.supportsExpressionsInOrderBy();

    // Assert
    verify(original).supportsExpressionsInOrderBy();
    assertFalse(actualSupportsExpressionsInOrderByResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExpressionsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExpressionsInOrderBy()"})
  public void testSupportsExpressionsInOrderBy_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExpressionsInOrderBy()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsExpressionsInOrderByResult =
        jdbcDatabaseMetaDataImpl.supportsExpressionsInOrderBy();

    // Assert
    verify(original).supportsExpressionsInOrderBy();
    assertTrue(actualSupportsExpressionsInOrderByResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOrderByUnrelated()"})
  public void testSupportsOrderByUnrelated() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOrderByUnrelated());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOrderByUnrelated()"})
  public void testSupportsOrderByUnrelated2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOrderByUnrelated()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOrderByUnrelated());
    verify(original).supportsOrderByUnrelated();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOrderByUnrelated()"})
  public void testSupportsOrderByUnrelated3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOrderByUnrelated()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsOrderByUnrelatedResult =
        jdbcDatabaseMetaDataImpl.supportsOrderByUnrelated();

    // Assert
    verify(original).supportsOrderByUnrelated();
    assertTrue(actualSupportsOrderByUnrelatedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOrderByUnrelated()"})
  public void testSupportsOrderByUnrelated_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOrderByUnrelated()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOrderByUnrelatedResult =
        jdbcDatabaseMetaDataImpl.supportsOrderByUnrelated();

    // Assert
    verify(original).supportsOrderByUnrelated();
    assertFalse(actualSupportsOrderByUnrelatedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOrderByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOrderByUnrelated()"})
  public void testSupportsOrderByUnrelated_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOrderByUnrelated()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOrderByUnrelatedResult =
        jdbcDatabaseMetaDataImpl.supportsOrderByUnrelated();

    // Assert
    verify(original).supportsOrderByUnrelated();
    assertTrue(actualSupportsOrderByUnrelatedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupBy()"})
  public void testSupportsGroupBy() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGroupBy());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupBy()"})
  public void testSupportsGroupBy2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupBy()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsGroupByResult = jdbcDatabaseMetaDataImpl.supportsGroupBy();

    // Assert
    verify(original).supportsGroupBy();
    assertTrue(actualSupportsGroupByResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsGroupBy()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupBy()"})
  public void testSupportsGroupBy_givenDatabaseMetaDataSupportsGroupByThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupBy()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGroupBy());
    verify(original).supportsGroupBy();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupBy()"})
  public void testSupportsGroupBy_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupBy()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGroupByResult = jdbcDatabaseMetaDataImpl.supportsGroupBy();

    // Assert
    verify(original).supportsGroupBy();
    assertFalse(actualSupportsGroupByResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupBy()"})
  public void testSupportsGroupBy_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupBy()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGroupByResult = jdbcDatabaseMetaDataImpl.supportsGroupBy();

    // Assert
    verify(original).supportsGroupBy();
    assertTrue(actualSupportsGroupByResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByUnrelated()"})
  public void testSupportsGroupByUnrelated() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGroupByUnrelated());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByUnrelated()"})
  public void testSupportsGroupByUnrelated2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByUnrelated()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGroupByUnrelated());
    verify(original).supportsGroupByUnrelated();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByUnrelated()"})
  public void testSupportsGroupByUnrelated3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByUnrelated()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsGroupByUnrelatedResult =
        jdbcDatabaseMetaDataImpl.supportsGroupByUnrelated();

    // Assert
    verify(original).supportsGroupByUnrelated();
    assertTrue(actualSupportsGroupByUnrelatedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByUnrelated()"})
  public void testSupportsGroupByUnrelated_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByUnrelated()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGroupByUnrelatedResult =
        jdbcDatabaseMetaDataImpl.supportsGroupByUnrelated();

    // Assert
    verify(original).supportsGroupByUnrelated();
    assertFalse(actualSupportsGroupByUnrelatedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByUnrelated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByUnrelated()"})
  public void testSupportsGroupByUnrelated_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByUnrelated()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGroupByUnrelatedResult =
        jdbcDatabaseMetaDataImpl.supportsGroupByUnrelated();

    // Assert
    verify(original).supportsGroupByUnrelated();
    assertTrue(actualSupportsGroupByUnrelatedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByBeyondSelect()"})
  public void testSupportsGroupByBeyondSelect() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGroupByBeyondSelect());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByBeyondSelect()"})
  public void testSupportsGroupByBeyondSelect2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByBeyondSelect()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsGroupByBeyondSelect());
    verify(original).supportsGroupByBeyondSelect();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByBeyondSelect()"})
  public void testSupportsGroupByBeyondSelect3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByBeyondSelect()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsGroupByBeyondSelectResult =
        jdbcDatabaseMetaDataImpl.supportsGroupByBeyondSelect();

    // Assert
    verify(original).supportsGroupByBeyondSelect();
    assertTrue(actualSupportsGroupByBeyondSelectResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByBeyondSelect()"})
  public void testSupportsGroupByBeyondSelect_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByBeyondSelect()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGroupByBeyondSelectResult =
        jdbcDatabaseMetaDataImpl.supportsGroupByBeyondSelect();

    // Assert
    verify(original).supportsGroupByBeyondSelect();
    assertFalse(actualSupportsGroupByBeyondSelectResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsGroupByBeyondSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsGroupByBeyondSelect()"})
  public void testSupportsGroupByBeyondSelect_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsGroupByBeyondSelect()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsGroupByBeyondSelectResult =
        jdbcDatabaseMetaDataImpl.supportsGroupByBeyondSelect();

    // Assert
    verify(original).supportsGroupByBeyondSelect();
    assertTrue(actualSupportsGroupByBeyondSelectResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLikeEscapeClause()"})
  public void testSupportsLikeEscapeClause() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsLikeEscapeClause());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLikeEscapeClause()"})
  public void testSupportsLikeEscapeClause2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLikeEscapeClause()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsLikeEscapeClause());
    verify(original).supportsLikeEscapeClause();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLikeEscapeClause()"})
  public void testSupportsLikeEscapeClause3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLikeEscapeClause()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsLikeEscapeClauseResult =
        jdbcDatabaseMetaDataImpl.supportsLikeEscapeClause();

    // Assert
    verify(original).supportsLikeEscapeClause();
    assertTrue(actualSupportsLikeEscapeClauseResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLikeEscapeClause()"})
  public void testSupportsLikeEscapeClause_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLikeEscapeClause()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsLikeEscapeClauseResult =
        jdbcDatabaseMetaDataImpl.supportsLikeEscapeClause();

    // Assert
    verify(original).supportsLikeEscapeClause();
    assertFalse(actualSupportsLikeEscapeClauseResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLikeEscapeClause()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLikeEscapeClause()"})
  public void testSupportsLikeEscapeClause_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLikeEscapeClause()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsLikeEscapeClauseResult =
        jdbcDatabaseMetaDataImpl.supportsLikeEscapeClause();

    // Assert
    verify(original).supportsLikeEscapeClause();
    assertTrue(actualSupportsLikeEscapeClauseResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleResultSets()"})
  public void testSupportsMultipleResultSets() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMultipleResultSets());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleResultSets()"})
  public void testSupportsMultipleResultSets2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleResultSets()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMultipleResultSets());
    verify(original).supportsMultipleResultSets();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleResultSets()"})
  public void testSupportsMultipleResultSets3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleResultSets()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsMultipleResultSetsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleResultSets();

    // Assert
    verify(original).supportsMultipleResultSets();
    assertTrue(actualSupportsMultipleResultSetsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleResultSets()"})
  public void testSupportsMultipleResultSets_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleResultSets()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMultipleResultSetsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleResultSets();

    // Assert
    verify(original).supportsMultipleResultSets();
    assertFalse(actualSupportsMultipleResultSetsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleResultSets()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleResultSets()"})
  public void testSupportsMultipleResultSets_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleResultSets()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMultipleResultSetsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleResultSets();

    // Assert
    verify(original).supportsMultipleResultSets();
    assertTrue(actualSupportsMultipleResultSetsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleTransactions()"})
  public void testSupportsMultipleTransactions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMultipleTransactions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleTransactions()"})
  public void testSupportsMultipleTransactions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleTransactions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMultipleTransactions());
    verify(original).supportsMultipleTransactions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleTransactions()"})
  public void testSupportsMultipleTransactions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsMultipleTransactionsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleTransactions();

    // Assert
    verify(original).supportsMultipleTransactions();
    assertTrue(actualSupportsMultipleTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleTransactions()"})
  public void testSupportsMultipleTransactions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleTransactions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMultipleTransactionsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleTransactions();

    // Assert
    verify(original).supportsMultipleTransactions();
    assertFalse(actualSupportsMultipleTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMultipleTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMultipleTransactions()"})
  public void testSupportsMultipleTransactions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMultipleTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMultipleTransactionsResult =
        jdbcDatabaseMetaDataImpl.supportsMultipleTransactions();

    // Assert
    verify(original).supportsMultipleTransactions();
    assertTrue(actualSupportsMultipleTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNonNullableColumns()"})
  public void testSupportsNonNullableColumns() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsNonNullableColumns());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNonNullableColumns()"})
  public void testSupportsNonNullableColumns2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNonNullableColumns()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsNonNullableColumns());
    verify(original).supportsNonNullableColumns();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNonNullableColumns()"})
  public void testSupportsNonNullableColumns3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNonNullableColumns()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsNonNullableColumnsResult =
        jdbcDatabaseMetaDataImpl.supportsNonNullableColumns();

    // Assert
    verify(original).supportsNonNullableColumns();
    assertTrue(actualSupportsNonNullableColumnsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNonNullableColumns()"})
  public void testSupportsNonNullableColumns_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNonNullableColumns()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsNonNullableColumnsResult =
        jdbcDatabaseMetaDataImpl.supportsNonNullableColumns();

    // Assert
    verify(original).supportsNonNullableColumns();
    assertFalse(actualSupportsNonNullableColumnsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsNonNullableColumns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsNonNullableColumns()"})
  public void testSupportsNonNullableColumns_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsNonNullableColumns()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsNonNullableColumnsResult =
        jdbcDatabaseMetaDataImpl.supportsNonNullableColumns();

    // Assert
    verify(original).supportsNonNullableColumns();
    assertTrue(actualSupportsNonNullableColumnsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMinimumSQLGrammar()"})
  public void testSupportsMinimumSQLGrammar() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMinimumSQLGrammar());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMinimumSQLGrammar()"})
  public void testSupportsMinimumSQLGrammar2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMinimumSQLGrammar()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsMinimumSQLGrammar());
    verify(original).supportsMinimumSQLGrammar();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMinimumSQLGrammar()"})
  public void testSupportsMinimumSQLGrammar3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMinimumSQLGrammar()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsMinimumSQLGrammarResult =
        jdbcDatabaseMetaDataImpl.supportsMinimumSQLGrammar();

    // Assert
    verify(original).supportsMinimumSQLGrammar();
    assertTrue(actualSupportsMinimumSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMinimumSQLGrammar()"})
  public void testSupportsMinimumSQLGrammar_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMinimumSQLGrammar()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMinimumSQLGrammarResult =
        jdbcDatabaseMetaDataImpl.supportsMinimumSQLGrammar();

    // Assert
    verify(original).supportsMinimumSQLGrammar();
    assertFalse(actualSupportsMinimumSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsMinimumSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsMinimumSQLGrammar()"})
  public void testSupportsMinimumSQLGrammar_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsMinimumSQLGrammar()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsMinimumSQLGrammarResult =
        jdbcDatabaseMetaDataImpl.supportsMinimumSQLGrammar();

    // Assert
    verify(original).supportsMinimumSQLGrammar();
    assertTrue(actualSupportsMinimumSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCoreSQLGrammar()"})
  public void testSupportsCoreSQLGrammar() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCoreSQLGrammar());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCoreSQLGrammar()"})
  public void testSupportsCoreSQLGrammar2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCoreSQLGrammar()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCoreSQLGrammar());
    verify(original).supportsCoreSQLGrammar();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCoreSQLGrammar()"})
  public void testSupportsCoreSQLGrammar3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCoreSQLGrammar()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCoreSQLGrammarResult = jdbcDatabaseMetaDataImpl.supportsCoreSQLGrammar();

    // Assert
    verify(original).supportsCoreSQLGrammar();
    assertTrue(actualSupportsCoreSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCoreSQLGrammar()"})
  public void testSupportsCoreSQLGrammar_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCoreSQLGrammar()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCoreSQLGrammarResult = jdbcDatabaseMetaDataImpl.supportsCoreSQLGrammar();

    // Assert
    verify(original).supportsCoreSQLGrammar();
    assertFalse(actualSupportsCoreSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCoreSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCoreSQLGrammar()"})
  public void testSupportsCoreSQLGrammar_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCoreSQLGrammar()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCoreSQLGrammarResult = jdbcDatabaseMetaDataImpl.supportsCoreSQLGrammar();

    // Assert
    verify(original).supportsCoreSQLGrammar();
    assertTrue(actualSupportsCoreSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExtendedSQLGrammar()"})
  public void testSupportsExtendedSQLGrammar() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsExtendedSQLGrammar());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExtendedSQLGrammar()"})
  public void testSupportsExtendedSQLGrammar2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExtendedSQLGrammar()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsExtendedSQLGrammar());
    verify(original).supportsExtendedSQLGrammar();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExtendedSQLGrammar()"})
  public void testSupportsExtendedSQLGrammar3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExtendedSQLGrammar()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsExtendedSQLGrammarResult =
        jdbcDatabaseMetaDataImpl.supportsExtendedSQLGrammar();

    // Assert
    verify(original).supportsExtendedSQLGrammar();
    assertTrue(actualSupportsExtendedSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExtendedSQLGrammar()"})
  public void testSupportsExtendedSQLGrammar_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExtendedSQLGrammar()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsExtendedSQLGrammarResult =
        jdbcDatabaseMetaDataImpl.supportsExtendedSQLGrammar();

    // Assert
    verify(original).supportsExtendedSQLGrammar();
    assertFalse(actualSupportsExtendedSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsExtendedSQLGrammar()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsExtendedSQLGrammar()"})
  public void testSupportsExtendedSQLGrammar_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsExtendedSQLGrammar()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsExtendedSQLGrammarResult =
        jdbcDatabaseMetaDataImpl.supportsExtendedSQLGrammar();

    // Assert
    verify(original).supportsExtendedSQLGrammar();
    assertTrue(actualSupportsExtendedSQLGrammarResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL()"})
  public void testSupportsANSI92EntryLevelSQL() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL()"})
  public void testSupportsANSI92EntryLevelSQL2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92EntryLevelSQL()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL());
    verify(original).supportsANSI92EntryLevelSQL();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL()"})
  public void testSupportsANSI92EntryLevelSQL3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92EntryLevelSQL()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsANSI92EntryLevelSQLResult =
        jdbcDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL();

    // Assert
    verify(original).supportsANSI92EntryLevelSQL();
    assertTrue(actualSupportsANSI92EntryLevelSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL()"})
  public void testSupportsANSI92EntryLevelSQL_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92EntryLevelSQL()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsANSI92EntryLevelSQLResult =
        jdbcDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL();

    // Assert
    verify(original).supportsANSI92EntryLevelSQL();
    assertFalse(actualSupportsANSI92EntryLevelSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92EntryLevelSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL()"})
  public void testSupportsANSI92EntryLevelSQL_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92EntryLevelSQL()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsANSI92EntryLevelSQLResult =
        jdbcDatabaseMetaDataImpl.supportsANSI92EntryLevelSQL();

    // Assert
    verify(original).supportsANSI92EntryLevelSQL();
    assertTrue(actualSupportsANSI92EntryLevelSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92IntermediateSQL()"})
  public void testSupportsANSI92IntermediateSQL() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsANSI92IntermediateSQL());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92IntermediateSQL()"})
  public void testSupportsANSI92IntermediateSQL2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92IntermediateSQL()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsANSI92IntermediateSQL());
    verify(original).supportsANSI92IntermediateSQL();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92IntermediateSQL()"})
  public void testSupportsANSI92IntermediateSQL3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92IntermediateSQL()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsANSI92IntermediateSQLResult =
        jdbcDatabaseMetaDataImpl.supportsANSI92IntermediateSQL();

    // Assert
    verify(original).supportsANSI92IntermediateSQL();
    assertTrue(actualSupportsANSI92IntermediateSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92IntermediateSQL()"})
  public void testSupportsANSI92IntermediateSQL_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92IntermediateSQL()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsANSI92IntermediateSQLResult =
        jdbcDatabaseMetaDataImpl.supportsANSI92IntermediateSQL();

    // Assert
    verify(original).supportsANSI92IntermediateSQL();
    assertFalse(actualSupportsANSI92IntermediateSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92IntermediateSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92IntermediateSQL()"})
  public void testSupportsANSI92IntermediateSQL_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92IntermediateSQL()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsANSI92IntermediateSQLResult =
        jdbcDatabaseMetaDataImpl.supportsANSI92IntermediateSQL();

    // Assert
    verify(original).supportsANSI92IntermediateSQL();
    assertTrue(actualSupportsANSI92IntermediateSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92FullSQL()"})
  public void testSupportsANSI92FullSQL() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsANSI92FullSQL());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92FullSQL()"})
  public void testSupportsANSI92FullSQL2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92FullSQL()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsANSI92FullSQL());
    verify(original).supportsANSI92FullSQL();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92FullSQL()"})
  public void testSupportsANSI92FullSQL3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92FullSQL()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsANSI92FullSQLResult = jdbcDatabaseMetaDataImpl.supportsANSI92FullSQL();

    // Assert
    verify(original).supportsANSI92FullSQL();
    assertTrue(actualSupportsANSI92FullSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92FullSQL()"})
  public void testSupportsANSI92FullSQL_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92FullSQL()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsANSI92FullSQLResult = jdbcDatabaseMetaDataImpl.supportsANSI92FullSQL();

    // Assert
    verify(original).supportsANSI92FullSQL();
    assertFalse(actualSupportsANSI92FullSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsANSI92FullSQL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsANSI92FullSQL()"})
  public void testSupportsANSI92FullSQL_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsANSI92FullSQL()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsANSI92FullSQLResult = jdbcDatabaseMetaDataImpl.supportsANSI92FullSQL();

    // Assert
    verify(original).supportsANSI92FullSQL();
    assertTrue(actualSupportsANSI92FullSQLResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility()"})
  public void testSupportsIntegrityEnhancementFacility() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility()"})
  public void testSupportsIntegrityEnhancementFacility2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsIntegrityEnhancementFacility()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility());
    verify(original).supportsIntegrityEnhancementFacility();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility()"})
  public void testSupportsIntegrityEnhancementFacility3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsIntegrityEnhancementFacility()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsIntegrityEnhancementFacilityResult =
        jdbcDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility();

    // Assert
    verify(original).supportsIntegrityEnhancementFacility();
    assertTrue(actualSupportsIntegrityEnhancementFacilityResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility()"})
  public void testSupportsIntegrityEnhancementFacility_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsIntegrityEnhancementFacility()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsIntegrityEnhancementFacilityResult =
        jdbcDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility();

    // Assert
    verify(original).supportsIntegrityEnhancementFacility();
    assertFalse(actualSupportsIntegrityEnhancementFacilityResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsIntegrityEnhancementFacility()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility()"})
  public void testSupportsIntegrityEnhancementFacility_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsIntegrityEnhancementFacility()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsIntegrityEnhancementFacilityResult =
        jdbcDatabaseMetaDataImpl.supportsIntegrityEnhancementFacility();

    // Assert
    verify(original).supportsIntegrityEnhancementFacility();
    assertTrue(actualSupportsIntegrityEnhancementFacilityResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOuterJoins()"})
  public void testSupportsOuterJoins() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOuterJoins());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOuterJoins()"})
  public void testSupportsOuterJoins2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOuterJoins()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsOuterJoinsResult = jdbcDatabaseMetaDataImpl.supportsOuterJoins();

    // Assert
    verify(original).supportsOuterJoins();
    assertTrue(actualSupportsOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsOuterJoins()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOuterJoins()"})
  public void testSupportsOuterJoins_givenDatabaseMetaDataSupportsOuterJoinsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOuterJoins()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOuterJoins());
    verify(original).supportsOuterJoins();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOuterJoins()"})
  public void testSupportsOuterJoins_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOuterJoins()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOuterJoinsResult = jdbcDatabaseMetaDataImpl.supportsOuterJoins();

    // Assert
    verify(original).supportsOuterJoins();
    assertFalse(actualSupportsOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOuterJoins()"})
  public void testSupportsOuterJoins_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOuterJoins()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOuterJoinsResult = jdbcDatabaseMetaDataImpl.supportsOuterJoins();

    // Assert
    verify(original).supportsOuterJoins();
    assertTrue(actualSupportsOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsFullOuterJoins()"})
  public void testSupportsFullOuterJoins() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsFullOuterJoins());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsFullOuterJoins()"})
  public void testSupportsFullOuterJoins2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsFullOuterJoins()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsFullOuterJoins());
    verify(original).supportsFullOuterJoins();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsFullOuterJoins()"})
  public void testSupportsFullOuterJoins3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsFullOuterJoins()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsFullOuterJoinsResult = jdbcDatabaseMetaDataImpl.supportsFullOuterJoins();

    // Assert
    verify(original).supportsFullOuterJoins();
    assertTrue(actualSupportsFullOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsFullOuterJoins()"})
  public void testSupportsFullOuterJoins_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsFullOuterJoins()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsFullOuterJoinsResult = jdbcDatabaseMetaDataImpl.supportsFullOuterJoins();

    // Assert
    verify(original).supportsFullOuterJoins();
    assertFalse(actualSupportsFullOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsFullOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsFullOuterJoins()"})
  public void testSupportsFullOuterJoins_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsFullOuterJoins()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsFullOuterJoinsResult = jdbcDatabaseMetaDataImpl.supportsFullOuterJoins();

    // Assert
    verify(original).supportsFullOuterJoins();
    assertTrue(actualSupportsFullOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLimitedOuterJoins()"})
  public void testSupportsLimitedOuterJoins() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsLimitedOuterJoins());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLimitedOuterJoins()"})
  public void testSupportsLimitedOuterJoins2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLimitedOuterJoins()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsLimitedOuterJoins());
    verify(original).supportsLimitedOuterJoins();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLimitedOuterJoins()"})
  public void testSupportsLimitedOuterJoins3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLimitedOuterJoins()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsLimitedOuterJoinsResult =
        jdbcDatabaseMetaDataImpl.supportsLimitedOuterJoins();

    // Assert
    verify(original).supportsLimitedOuterJoins();
    assertTrue(actualSupportsLimitedOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLimitedOuterJoins()"})
  public void testSupportsLimitedOuterJoins_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLimitedOuterJoins()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsLimitedOuterJoinsResult =
        jdbcDatabaseMetaDataImpl.supportsLimitedOuterJoins();

    // Assert
    verify(original).supportsLimitedOuterJoins();
    assertFalse(actualSupportsLimitedOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsLimitedOuterJoins()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsLimitedOuterJoins()"})
  public void testSupportsLimitedOuterJoins_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsLimitedOuterJoins()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsLimitedOuterJoinsResult =
        jdbcDatabaseMetaDataImpl.supportsLimitedOuterJoins();

    // Assert
    verify(original).supportsLimitedOuterJoins();
    assertTrue(actualSupportsLimitedOuterJoinsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSchemaTerm()"})
  public void testGetSchemaTerm() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSchemaTerm());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSchemaTerm()"})
  public void testGetSchemaTerm2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSchemaTerm()).thenReturn("Schema Term");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualSchemaTerm = jdbcDatabaseMetaDataImpl.getSchemaTerm();

    // Assert
    verify(original).getSchemaTerm();
    assertEquals("Schema Term", actualSchemaTerm);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getSchemaTerm()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSchemaTerm()"})
  public void testGetSchemaTerm_givenDatabaseMetaDataGetSchemaTermThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSchemaTerm()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getSchemaTerm());
    verify(original).getSchemaTerm();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}.
   *
   * <ul>
   *   <li>Then return {@code Schema Term}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getSchemaTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getSchemaTerm()"})
  public void testGetSchemaTerm_thenReturnSchemaTerm() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getSchemaTerm()).thenReturn("Schema Term");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualSchemaTerm = jdbcDatabaseMetaDataImpl.getSchemaTerm();

    // Assert
    verify(original).getSchemaTerm();
    assertEquals("Schema Term", actualSchemaTerm);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getProcedureTerm()"})
  public void testGetProcedureTerm() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getProcedureTerm());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getProcedureTerm()"})
  public void testGetProcedureTerm2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getProcedureTerm()).thenReturn("Procedure Term");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualProcedureTerm = jdbcDatabaseMetaDataImpl.getProcedureTerm();

    // Assert
    verify(original).getProcedureTerm();
    assertEquals("Procedure Term", actualProcedureTerm);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getProcedureTerm()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getProcedureTerm()"})
  public void testGetProcedureTerm_givenDatabaseMetaDataGetProcedureTermThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getProcedureTerm()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getProcedureTerm());
    verify(original).getProcedureTerm();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}.
   *
   * <ul>
   *   <li>Then return {@code Procedure Term}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getProcedureTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getProcedureTerm()"})
  public void testGetProcedureTerm_thenReturnProcedureTerm() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getProcedureTerm()).thenReturn("Procedure Term");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualProcedureTerm = jdbcDatabaseMetaDataImpl.getProcedureTerm();

    // Assert
    verify(original).getProcedureTerm();
    assertEquals("Procedure Term", actualProcedureTerm);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogTerm()"})
  public void testGetCatalogTerm() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getCatalogTerm());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogTerm()"})
  public void testGetCatalogTerm2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getCatalogTerm()).thenReturn("Catalog Term");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualCatalogTerm = jdbcDatabaseMetaDataImpl.getCatalogTerm();

    // Assert
    verify(original).getCatalogTerm();
    assertEquals("Catalog Term", actualCatalogTerm);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getCatalogTerm()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogTerm()"})
  public void testGetCatalogTerm_givenDatabaseMetaDataGetCatalogTermThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getCatalogTerm()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getCatalogTerm());
    verify(original).getCatalogTerm();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}.
   *
   * <ul>
   *   <li>Then return {@code Catalog Term}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogTerm()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogTerm()"})
  public void testGetCatalogTerm_thenReturnCatalogTerm() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getCatalogTerm()).thenReturn("Catalog Term");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualCatalogTerm = jdbcDatabaseMetaDataImpl.getCatalogTerm();

    // Assert
    verify(original).getCatalogTerm();
    assertEquals("Catalog Term", actualCatalogTerm);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isCatalogAtStart()"})
  public void testIsCatalogAtStart() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.isCatalogAtStart());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isCatalogAtStart()"})
  public void testIsCatalogAtStart2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isCatalogAtStart()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualIsCatalogAtStartResult = jdbcDatabaseMetaDataImpl.isCatalogAtStart();

    // Assert
    verify(original).isCatalogAtStart();
    assertTrue(actualIsCatalogAtStartResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#isCatalogAtStart()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isCatalogAtStart()"})
  public void testIsCatalogAtStart_givenDatabaseMetaDataIsCatalogAtStartThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isCatalogAtStart()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.isCatalogAtStart());
    verify(original).isCatalogAtStart();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isCatalogAtStart()"})
  public void testIsCatalogAtStart_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isCatalogAtStart()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualIsCatalogAtStartResult = jdbcDatabaseMetaDataImpl.isCatalogAtStart();

    // Assert
    verify(original).isCatalogAtStart();
    assertFalse(actualIsCatalogAtStartResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#isCatalogAtStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.isCatalogAtStart()"})
  public void testIsCatalogAtStart_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.isCatalogAtStart()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualIsCatalogAtStartResult = jdbcDatabaseMetaDataImpl.isCatalogAtStart();

    // Assert
    verify(original).isCatalogAtStart();
    assertTrue(actualIsCatalogAtStartResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogSeparator()"})
  public void testGetCatalogSeparator() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getCatalogSeparator());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogSeparator()"})
  public void testGetCatalogSeparator2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getCatalogSeparator()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getCatalogSeparator());
    verify(original).getCatalogSeparator();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogSeparator()"})
  public void testGetCatalogSeparator3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getCatalogSeparator()).thenReturn("Catalog Separator");
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    String actualCatalogSeparator = jdbcDatabaseMetaDataImpl.getCatalogSeparator();

    // Assert
    verify(original).getCatalogSeparator();
    assertEquals("Catalog Separator", actualCatalogSeparator);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}.
   *
   * <ul>
   *   <li>Then return {@code Catalog Separator}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getCatalogSeparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDatabaseMetaDataImpl.getCatalogSeparator()"})
  public void testGetCatalogSeparator_thenReturnCatalogSeparator() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getCatalogSeparator()).thenReturn("Catalog Separator");
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    String actualCatalogSeparator = jdbcDatabaseMetaDataImpl.getCatalogSeparator();

    // Assert
    verify(original).getCatalogSeparator();
    assertEquals("Catalog Separator", actualCatalogSeparator);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInDataManipulation()"})
  public void testSupportsSchemasInDataManipulation() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInDataManipulation());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInDataManipulation()"})
  public void testSupportsSchemasInDataManipulation2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInDataManipulation()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInDataManipulation());
    verify(original).supportsSchemasInDataManipulation();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInDataManipulation()"})
  public void testSupportsSchemasInDataManipulation3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInDataManipulation()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSchemasInDataManipulationResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInDataManipulation();

    // Assert
    verify(original).supportsSchemasInDataManipulation();
    assertTrue(actualSupportsSchemasInDataManipulationResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInDataManipulation()"})
  public void testSupportsSchemasInDataManipulation_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInDataManipulation()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInDataManipulationResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInDataManipulation();

    // Assert
    verify(original).supportsSchemasInDataManipulation();
    assertFalse(actualSupportsSchemasInDataManipulationResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInDataManipulation()"})
  public void testSupportsSchemasInDataManipulation_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInDataManipulation()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInDataManipulationResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInDataManipulation();

    // Assert
    verify(original).supportsSchemasInDataManipulation();
    assertTrue(actualSupportsSchemasInDataManipulationResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInProcedureCalls()"})
  public void testSupportsSchemasInProcedureCalls() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInProcedureCalls());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInProcedureCalls()"})
  public void testSupportsSchemasInProcedureCalls2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInProcedureCalls()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInProcedureCalls());
    verify(original).supportsSchemasInProcedureCalls();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInProcedureCalls()"})
  public void testSupportsSchemasInProcedureCalls3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInProcedureCalls()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSchemasInProcedureCallsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInProcedureCalls();

    // Assert
    verify(original).supportsSchemasInProcedureCalls();
    assertTrue(actualSupportsSchemasInProcedureCallsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInProcedureCalls()"})
  public void testSupportsSchemasInProcedureCalls_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInProcedureCalls()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInProcedureCallsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInProcedureCalls();

    // Assert
    verify(original).supportsSchemasInProcedureCalls();
    assertFalse(actualSupportsSchemasInProcedureCallsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInProcedureCalls()"})
  public void testSupportsSchemasInProcedureCalls_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInProcedureCalls()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInProcedureCallsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInProcedureCalls();

    // Assert
    verify(original).supportsSchemasInProcedureCalls();
    assertTrue(actualSupportsSchemasInProcedureCallsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInTableDefinitions()"})
  public void testSupportsSchemasInTableDefinitions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInTableDefinitions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInTableDefinitions()"})
  public void testSupportsSchemasInTableDefinitions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInTableDefinitions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInTableDefinitions());
    verify(original).supportsSchemasInTableDefinitions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInTableDefinitions()"})
  public void testSupportsSchemasInTableDefinitions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInTableDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSchemasInTableDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInTableDefinitions();

    // Assert
    verify(original).supportsSchemasInTableDefinitions();
    assertTrue(actualSupportsSchemasInTableDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInTableDefinitions()"})
  public void testSupportsSchemasInTableDefinitions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInTableDefinitions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInTableDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInTableDefinitions();

    // Assert
    verify(original).supportsSchemasInTableDefinitions();
    assertFalse(actualSupportsSchemasInTableDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInTableDefinitions()"})
  public void testSupportsSchemasInTableDefinitions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInTableDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInTableDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInTableDefinitions();

    // Assert
    verify(original).supportsSchemasInTableDefinitions();
    assertTrue(actualSupportsSchemasInTableDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions()"})
  public void testSupportsSchemasInIndexDefinitions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions()"})
  public void testSupportsSchemasInIndexDefinitions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInIndexDefinitions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions());
    verify(original).supportsSchemasInIndexDefinitions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions()"})
  public void testSupportsSchemasInIndexDefinitions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInIndexDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSchemasInIndexDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions();

    // Assert
    verify(original).supportsSchemasInIndexDefinitions();
    assertTrue(actualSupportsSchemasInIndexDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions()"})
  public void testSupportsSchemasInIndexDefinitions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInIndexDefinitions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInIndexDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions();

    // Assert
    verify(original).supportsSchemasInIndexDefinitions();
    assertFalse(actualSupportsSchemasInIndexDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions()"})
  public void testSupportsSchemasInIndexDefinitions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInIndexDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInIndexDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInIndexDefinitions();

    // Assert
    verify(original).supportsSchemasInIndexDefinitions();
    assertTrue(actualSupportsSchemasInIndexDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions()"})
  public void testSupportsSchemasInPrivilegeDefinitions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions()"})
  public void testSupportsSchemasInPrivilegeDefinitions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInPrivilegeDefinitions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions());
    verify(original).supportsSchemasInPrivilegeDefinitions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions()"})
  public void testSupportsSchemasInPrivilegeDefinitions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSchemasInPrivilegeDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions();

    // Assert
    verify(original).supportsSchemasInPrivilegeDefinitions();
    assertTrue(actualSupportsSchemasInPrivilegeDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions()"})
  public void testSupportsSchemasInPrivilegeDefinitions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInPrivilegeDefinitions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInPrivilegeDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions();

    // Assert
    verify(original).supportsSchemasInPrivilegeDefinitions();
    assertFalse(actualSupportsSchemasInPrivilegeDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSchemasInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions()"})
  public void testSupportsSchemasInPrivilegeDefinitions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSchemasInPrivilegeDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSchemasInPrivilegeDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsSchemasInPrivilegeDefinitions();

    // Assert
    verify(original).supportsSchemasInPrivilegeDefinitions();
    assertTrue(actualSupportsSchemasInPrivilegeDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInDataManipulation()"})
  public void testSupportsCatalogsInDataManipulation() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInDataManipulation());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInDataManipulation()"})
  public void testSupportsCatalogsInDataManipulation2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInDataManipulation()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInDataManipulation());
    verify(original).supportsCatalogsInDataManipulation();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInDataManipulation()"})
  public void testSupportsCatalogsInDataManipulation3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInDataManipulation()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCatalogsInDataManipulationResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInDataManipulation();

    // Assert
    verify(original).supportsCatalogsInDataManipulation();
    assertTrue(actualSupportsCatalogsInDataManipulationResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInDataManipulation()"})
  public void testSupportsCatalogsInDataManipulation_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInDataManipulation()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInDataManipulationResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInDataManipulation();

    // Assert
    verify(original).supportsCatalogsInDataManipulation();
    assertFalse(actualSupportsCatalogsInDataManipulationResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInDataManipulation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInDataManipulation()"})
  public void testSupportsCatalogsInDataManipulation_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInDataManipulation()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInDataManipulationResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInDataManipulation();

    // Assert
    verify(original).supportsCatalogsInDataManipulation();
    assertTrue(actualSupportsCatalogsInDataManipulationResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls()"})
  public void testSupportsCatalogsInProcedureCalls() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls()"})
  public void testSupportsCatalogsInProcedureCalls2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInProcedureCalls()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls());
    verify(original).supportsCatalogsInProcedureCalls();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls()"})
  public void testSupportsCatalogsInProcedureCalls3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInProcedureCalls()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCatalogsInProcedureCallsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls();

    // Assert
    verify(original).supportsCatalogsInProcedureCalls();
    assertTrue(actualSupportsCatalogsInProcedureCallsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls()"})
  public void testSupportsCatalogsInProcedureCalls_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInProcedureCalls()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInProcedureCallsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls();

    // Assert
    verify(original).supportsCatalogsInProcedureCalls();
    assertFalse(actualSupportsCatalogsInProcedureCallsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInProcedureCalls()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls()"})
  public void testSupportsCatalogsInProcedureCalls_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInProcedureCalls()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInProcedureCallsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInProcedureCalls();

    // Assert
    verify(original).supportsCatalogsInProcedureCalls();
    assertTrue(actualSupportsCatalogsInProcedureCallsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions()"})
  public void testSupportsCatalogsInTableDefinitions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions()"})
  public void testSupportsCatalogsInTableDefinitions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInTableDefinitions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions());
    verify(original).supportsCatalogsInTableDefinitions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions()"})
  public void testSupportsCatalogsInTableDefinitions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInTableDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCatalogsInTableDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions();

    // Assert
    verify(original).supportsCatalogsInTableDefinitions();
    assertTrue(actualSupportsCatalogsInTableDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions()"})
  public void testSupportsCatalogsInTableDefinitions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInTableDefinitions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInTableDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions();

    // Assert
    verify(original).supportsCatalogsInTableDefinitions();
    assertFalse(actualSupportsCatalogsInTableDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInTableDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions()"})
  public void testSupportsCatalogsInTableDefinitions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInTableDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInTableDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInTableDefinitions();

    // Assert
    verify(original).supportsCatalogsInTableDefinitions();
    assertTrue(actualSupportsCatalogsInTableDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions()"})
  public void testSupportsCatalogsInIndexDefinitions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions()"})
  public void testSupportsCatalogsInIndexDefinitions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInIndexDefinitions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions());
    verify(original).supportsCatalogsInIndexDefinitions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions()"})
  public void testSupportsCatalogsInIndexDefinitions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCatalogsInIndexDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions();

    // Assert
    verify(original).supportsCatalogsInIndexDefinitions();
    assertTrue(actualSupportsCatalogsInIndexDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions()"})
  public void testSupportsCatalogsInIndexDefinitions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInIndexDefinitions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInIndexDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions();

    // Assert
    verify(original).supportsCatalogsInIndexDefinitions();
    assertFalse(actualSupportsCatalogsInIndexDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInIndexDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions()"})
  public void testSupportsCatalogsInIndexDefinitions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInIndexDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInIndexDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInIndexDefinitions();

    // Assert
    verify(original).supportsCatalogsInIndexDefinitions();
    assertTrue(actualSupportsCatalogsInIndexDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions()"})
  public void testSupportsCatalogsInPrivilegeDefinitions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions()"})
  public void testSupportsCatalogsInPrivilegeDefinitions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInPrivilegeDefinitions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions());
    verify(original).supportsCatalogsInPrivilegeDefinitions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions()"})
  public void testSupportsCatalogsInPrivilegeDefinitions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCatalogsInPrivilegeDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions();

    // Assert
    verify(original).supportsCatalogsInPrivilegeDefinitions();
    assertTrue(actualSupportsCatalogsInPrivilegeDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions()"})
  public void testSupportsCatalogsInPrivilegeDefinitions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInPrivilegeDefinitions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInPrivilegeDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions();

    // Assert
    verify(original).supportsCatalogsInPrivilegeDefinitions();
    assertFalse(actualSupportsCatalogsInPrivilegeDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCatalogsInPrivilegeDefinitions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions()"})
  public void testSupportsCatalogsInPrivilegeDefinitions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCatalogsInPrivilegeDefinitions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCatalogsInPrivilegeDefinitionsResult =
        jdbcDatabaseMetaDataImpl.supportsCatalogsInPrivilegeDefinitions();

    // Assert
    verify(original).supportsCatalogsInPrivilegeDefinitions();
    assertTrue(actualSupportsCatalogsInPrivilegeDefinitionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedDelete()"})
  public void testSupportsPositionedDelete() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsPositionedDelete());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedDelete()"})
  public void testSupportsPositionedDelete2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedDelete()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsPositionedDelete());
    verify(original).supportsPositionedDelete();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedDelete()"})
  public void testSupportsPositionedDelete3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedDelete()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsPositionedDeleteResult =
        jdbcDatabaseMetaDataImpl.supportsPositionedDelete();

    // Assert
    verify(original).supportsPositionedDelete();
    assertTrue(actualSupportsPositionedDeleteResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedDelete()"})
  public void testSupportsPositionedDelete_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedDelete()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsPositionedDeleteResult =
        jdbcDatabaseMetaDataImpl.supportsPositionedDelete();

    // Assert
    verify(original).supportsPositionedDelete();
    assertFalse(actualSupportsPositionedDeleteResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedDelete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedDelete()"})
  public void testSupportsPositionedDelete_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedDelete()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsPositionedDeleteResult =
        jdbcDatabaseMetaDataImpl.supportsPositionedDelete();

    // Assert
    verify(original).supportsPositionedDelete();
    assertTrue(actualSupportsPositionedDeleteResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedUpdate()"})
  public void testSupportsPositionedUpdate() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsPositionedUpdate());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedUpdate()"})
  public void testSupportsPositionedUpdate2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedUpdate()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsPositionedUpdate());
    verify(original).supportsPositionedUpdate();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedUpdate()"})
  public void testSupportsPositionedUpdate3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedUpdate()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsPositionedUpdateResult =
        jdbcDatabaseMetaDataImpl.supportsPositionedUpdate();

    // Assert
    verify(original).supportsPositionedUpdate();
    assertTrue(actualSupportsPositionedUpdateResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedUpdate()"})
  public void testSupportsPositionedUpdate_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedUpdate()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsPositionedUpdateResult =
        jdbcDatabaseMetaDataImpl.supportsPositionedUpdate();

    // Assert
    verify(original).supportsPositionedUpdate();
    assertFalse(actualSupportsPositionedUpdateResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsPositionedUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsPositionedUpdate()"})
  public void testSupportsPositionedUpdate_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsPositionedUpdate()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsPositionedUpdateResult =
        jdbcDatabaseMetaDataImpl.supportsPositionedUpdate();

    // Assert
    verify(original).supportsPositionedUpdate();
    assertTrue(actualSupportsPositionedUpdateResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSelectForUpdate()"})
  public void testSupportsSelectForUpdate() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSelectForUpdate());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSelectForUpdate()"})
  public void testSupportsSelectForUpdate2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSelectForUpdate()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSelectForUpdate());
    verify(original).supportsSelectForUpdate();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSelectForUpdate()"})
  public void testSupportsSelectForUpdate3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSelectForUpdate()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSelectForUpdateResult =
        jdbcDatabaseMetaDataImpl.supportsSelectForUpdate();

    // Assert
    verify(original).supportsSelectForUpdate();
    assertTrue(actualSupportsSelectForUpdateResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSelectForUpdate()"})
  public void testSupportsSelectForUpdate_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSelectForUpdate()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSelectForUpdateResult =
        jdbcDatabaseMetaDataImpl.supportsSelectForUpdate();

    // Assert
    verify(original).supportsSelectForUpdate();
    assertFalse(actualSupportsSelectForUpdateResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSelectForUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSelectForUpdate()"})
  public void testSupportsSelectForUpdate_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSelectForUpdate()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSelectForUpdateResult =
        jdbcDatabaseMetaDataImpl.supportsSelectForUpdate();

    // Assert
    verify(original).supportsSelectForUpdate();
    assertTrue(actualSupportsSelectForUpdateResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredProcedures()"})
  public void testSupportsStoredProcedures() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsStoredProcedures());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredProcedures()"})
  public void testSupportsStoredProcedures2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredProcedures()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsStoredProcedures());
    verify(original).supportsStoredProcedures();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredProcedures()"})
  public void testSupportsStoredProcedures3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredProcedures()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsStoredProceduresResult =
        jdbcDatabaseMetaDataImpl.supportsStoredProcedures();

    // Assert
    verify(original).supportsStoredProcedures();
    assertTrue(actualSupportsStoredProceduresResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredProcedures()"})
  public void testSupportsStoredProcedures_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredProcedures()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsStoredProceduresResult =
        jdbcDatabaseMetaDataImpl.supportsStoredProcedures();

    // Assert
    verify(original).supportsStoredProcedures();
    assertFalse(actualSupportsStoredProceduresResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsStoredProcedures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsStoredProcedures()"})
  public void testSupportsStoredProcedures_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsStoredProcedures()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsStoredProceduresResult =
        jdbcDatabaseMetaDataImpl.supportsStoredProcedures();

    // Assert
    verify(original).supportsStoredProcedures();
    assertTrue(actualSupportsStoredProceduresResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInComparisons()"})
  public void testSupportsSubqueriesInComparisons() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInComparisons());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInComparisons()"})
  public void testSupportsSubqueriesInComparisons2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInComparisons()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInComparisons());
    verify(original).supportsSubqueriesInComparisons();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInComparisons()"})
  public void testSupportsSubqueriesInComparisons3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInComparisons()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSubqueriesInComparisonsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInComparisons();

    // Assert
    verify(original).supportsSubqueriesInComparisons();
    assertTrue(actualSupportsSubqueriesInComparisonsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInComparisons()"})
  public void testSupportsSubqueriesInComparisons_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInComparisons()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInComparisonsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInComparisons();

    // Assert
    verify(original).supportsSubqueriesInComparisons();
    assertFalse(actualSupportsSubqueriesInComparisonsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInComparisons()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInComparisons()"})
  public void testSupportsSubqueriesInComparisons_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInComparisons()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInComparisonsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInComparisons();

    // Assert
    verify(original).supportsSubqueriesInComparisons();
    assertTrue(actualSupportsSubqueriesInComparisonsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInExists()"})
  public void testSupportsSubqueriesInExists() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInExists());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInExists()"})
  public void testSupportsSubqueriesInExists2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInExists()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInExists());
    verify(original).supportsSubqueriesInExists();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInExists()"})
  public void testSupportsSubqueriesInExists3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInExists()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSubqueriesInExistsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInExists();

    // Assert
    verify(original).supportsSubqueriesInExists();
    assertTrue(actualSupportsSubqueriesInExistsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInExists()"})
  public void testSupportsSubqueriesInExists_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInExists()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInExistsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInExists();

    // Assert
    verify(original).supportsSubqueriesInExists();
    assertFalse(actualSupportsSubqueriesInExistsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInExists()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInExists()"})
  public void testSupportsSubqueriesInExists_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInExists()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInExistsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInExists();

    // Assert
    verify(original).supportsSubqueriesInExists();
    assertTrue(actualSupportsSubqueriesInExistsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInIns()"})
  public void testSupportsSubqueriesInIns() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInIns());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInIns()"})
  public void testSupportsSubqueriesInIns2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInIns()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInIns());
    verify(original).supportsSubqueriesInIns();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInIns()"})
  public void testSupportsSubqueriesInIns3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInIns()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSubqueriesInInsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInIns();

    // Assert
    verify(original).supportsSubqueriesInIns();
    assertTrue(actualSupportsSubqueriesInInsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInIns()"})
  public void testSupportsSubqueriesInIns_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInIns()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInInsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInIns();

    // Assert
    verify(original).supportsSubqueriesInIns();
    assertFalse(actualSupportsSubqueriesInInsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInIns()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInIns()"})
  public void testSupportsSubqueriesInIns_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInIns()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInInsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInIns();

    // Assert
    verify(original).supportsSubqueriesInIns();
    assertTrue(actualSupportsSubqueriesInInsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds()"})
  public void testSupportsSubqueriesInQuantifieds() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds()"})
  public void testSupportsSubqueriesInQuantifieds2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInQuantifieds()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds());
    verify(original).supportsSubqueriesInQuantifieds();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds()"})
  public void testSupportsSubqueriesInQuantifieds3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInQuantifieds()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsSubqueriesInQuantifiedsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds();

    // Assert
    verify(original).supportsSubqueriesInQuantifieds();
    assertTrue(actualSupportsSubqueriesInQuantifiedsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds()"})
  public void testSupportsSubqueriesInQuantifieds_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInQuantifieds()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInQuantifiedsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds();

    // Assert
    verify(original).supportsSubqueriesInQuantifieds();
    assertFalse(actualSupportsSubqueriesInQuantifiedsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsSubqueriesInQuantifieds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds()"})
  public void testSupportsSubqueriesInQuantifieds_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsSubqueriesInQuantifieds()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsSubqueriesInQuantifiedsResult =
        jdbcDatabaseMetaDataImpl.supportsSubqueriesInQuantifieds();

    // Assert
    verify(original).supportsSubqueriesInQuantifieds();
    assertTrue(actualSupportsSubqueriesInQuantifiedsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCorrelatedSubqueries()"})
  public void testSupportsCorrelatedSubqueries() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCorrelatedSubqueries());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCorrelatedSubqueries()"})
  public void testSupportsCorrelatedSubqueries2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCorrelatedSubqueries()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsCorrelatedSubqueries());
    verify(original).supportsCorrelatedSubqueries();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCorrelatedSubqueries()"})
  public void testSupportsCorrelatedSubqueries3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCorrelatedSubqueries()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsCorrelatedSubqueriesResult =
        jdbcDatabaseMetaDataImpl.supportsCorrelatedSubqueries();

    // Assert
    verify(original).supportsCorrelatedSubqueries();
    assertTrue(actualSupportsCorrelatedSubqueriesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCorrelatedSubqueries()"})
  public void testSupportsCorrelatedSubqueries_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCorrelatedSubqueries()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCorrelatedSubqueriesResult =
        jdbcDatabaseMetaDataImpl.supportsCorrelatedSubqueries();

    // Assert
    verify(original).supportsCorrelatedSubqueries();
    assertFalse(actualSupportsCorrelatedSubqueriesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsCorrelatedSubqueries()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsCorrelatedSubqueries()"})
  public void testSupportsCorrelatedSubqueries_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsCorrelatedSubqueries()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsCorrelatedSubqueriesResult =
        jdbcDatabaseMetaDataImpl.supportsCorrelatedSubqueries();

    // Assert
    verify(original).supportsCorrelatedSubqueries();
    assertTrue(actualSupportsCorrelatedSubqueriesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnion()"})
  public void testSupportsUnion() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsUnion());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnion()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnion()"})
  public void testSupportsUnion2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnion()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsUnionResult = jdbcDatabaseMetaDataImpl.supportsUnion();

    // Assert
    verify(original).supportsUnion();
    assertTrue(actualSupportsUnionResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnion()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsUnion()} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnion()"})
  public void testSupportsUnion_givenDatabaseMetaDataSupportsUnionReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnion()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsUnionResult = jdbcDatabaseMetaDataImpl.supportsUnion();

    // Assert
    verify(original).supportsUnion();
    assertFalse(actualSupportsUnionResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnion()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsUnion()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnion()"})
  public void testSupportsUnion_givenDatabaseMetaDataSupportsUnionReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnion()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsUnionResult = jdbcDatabaseMetaDataImpl.supportsUnion();

    // Assert
    verify(original).supportsUnion();
    assertTrue(actualSupportsUnionResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnion()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsUnion()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnion()"})
  public void testSupportsUnion_givenDatabaseMetaDataSupportsUnionThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnion()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsUnion());
    verify(original).supportsUnion();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnionAll()"})
  public void testSupportsUnionAll() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsUnionAll());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnionAll()"})
  public void testSupportsUnionAll2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnionAll()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsUnionAllResult = jdbcDatabaseMetaDataImpl.supportsUnionAll();

    // Assert
    verify(original).supportsUnionAll();
    assertTrue(actualSupportsUnionAllResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#supportsUnionAll()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnionAll()"})
  public void testSupportsUnionAll_givenDatabaseMetaDataSupportsUnionAllThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnionAll()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsUnionAll());
    verify(original).supportsUnionAll();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnionAll()"})
  public void testSupportsUnionAll_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnionAll()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsUnionAllResult = jdbcDatabaseMetaDataImpl.supportsUnionAll();

    // Assert
    verify(original).supportsUnionAll();
    assertFalse(actualSupportsUnionAllResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsUnionAll()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsUnionAll()"})
  public void testSupportsUnionAll_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsUnionAll()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsUnionAllResult = jdbcDatabaseMetaDataImpl.supportsUnionAll();

    // Assert
    verify(original).supportsUnionAll();
    assertTrue(actualSupportsUnionAllResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit()"})
  public void testSupportsOpenCursorsAcrossCommit() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit()"})
  public void testSupportsOpenCursorsAcrossCommit2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossCommit()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit());
    verify(original).supportsOpenCursorsAcrossCommit();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit()"})
  public void testSupportsOpenCursorsAcrossCommit3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossCommit()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsOpenCursorsAcrossCommitResult =
        jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit();

    // Assert
    verify(original).supportsOpenCursorsAcrossCommit();
    assertTrue(actualSupportsOpenCursorsAcrossCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit()"})
  public void testSupportsOpenCursorsAcrossCommit_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossCommit()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenCursorsAcrossCommitResult =
        jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit();

    // Assert
    verify(original).supportsOpenCursorsAcrossCommit();
    assertFalse(actualSupportsOpenCursorsAcrossCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit()"})
  public void testSupportsOpenCursorsAcrossCommit_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossCommit()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenCursorsAcrossCommitResult =
        jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossCommit();

    // Assert
    verify(original).supportsOpenCursorsAcrossCommit();
    assertTrue(actualSupportsOpenCursorsAcrossCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback()"})
  public void testSupportsOpenCursorsAcrossRollback() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback()"})
  public void testSupportsOpenCursorsAcrossRollback2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossRollback()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback());
    verify(original).supportsOpenCursorsAcrossRollback();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback()"})
  public void testSupportsOpenCursorsAcrossRollback3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossRollback()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsOpenCursorsAcrossRollbackResult =
        jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback();

    // Assert
    verify(original).supportsOpenCursorsAcrossRollback();
    assertTrue(actualSupportsOpenCursorsAcrossRollbackResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback()"})
  public void testSupportsOpenCursorsAcrossRollback_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossRollback()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenCursorsAcrossRollbackResult =
        jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback();

    // Assert
    verify(original).supportsOpenCursorsAcrossRollback();
    assertFalse(actualSupportsOpenCursorsAcrossRollbackResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenCursorsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback()"})
  public void testSupportsOpenCursorsAcrossRollback_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenCursorsAcrossRollback()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenCursorsAcrossRollbackResult =
        jdbcDatabaseMetaDataImpl.supportsOpenCursorsAcrossRollback();

    // Assert
    verify(original).supportsOpenCursorsAcrossRollback();
    assertTrue(actualSupportsOpenCursorsAcrossRollbackResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit()"})
  public void testSupportsOpenStatementsAcrossCommit() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit()"})
  public void testSupportsOpenStatementsAcrossCommit2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossCommit()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit());
    verify(original).supportsOpenStatementsAcrossCommit();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit()"})
  public void testSupportsOpenStatementsAcrossCommit3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossCommit()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsOpenStatementsAcrossCommitResult =
        jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit();

    // Assert
    verify(original).supportsOpenStatementsAcrossCommit();
    assertTrue(actualSupportsOpenStatementsAcrossCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit()"})
  public void testSupportsOpenStatementsAcrossCommit_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossCommit()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenStatementsAcrossCommitResult =
        jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit();

    // Assert
    verify(original).supportsOpenStatementsAcrossCommit();
    assertFalse(actualSupportsOpenStatementsAcrossCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit()"})
  public void testSupportsOpenStatementsAcrossCommit_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossCommit()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenStatementsAcrossCommitResult =
        jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossCommit();

    // Assert
    verify(original).supportsOpenStatementsAcrossCommit();
    assertTrue(actualSupportsOpenStatementsAcrossCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback()"})
  public void testSupportsOpenStatementsAcrossRollback() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback()"})
  public void testSupportsOpenStatementsAcrossRollback2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossRollback()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback());
    verify(original).supportsOpenStatementsAcrossRollback();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback()"})
  public void testSupportsOpenStatementsAcrossRollback3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossRollback()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsOpenStatementsAcrossRollbackResult =
        jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback();

    // Assert
    verify(original).supportsOpenStatementsAcrossRollback();
    assertTrue(actualSupportsOpenStatementsAcrossRollbackResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback()"})
  public void testSupportsOpenStatementsAcrossRollback_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossRollback()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenStatementsAcrossRollbackResult =
        jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback();

    // Assert
    verify(original).supportsOpenStatementsAcrossRollback();
    assertFalse(actualSupportsOpenStatementsAcrossRollbackResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsOpenStatementsAcrossRollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback()"})
  public void testSupportsOpenStatementsAcrossRollback_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsOpenStatementsAcrossRollback()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsOpenStatementsAcrossRollbackResult =
        jdbcDatabaseMetaDataImpl.supportsOpenStatementsAcrossRollback();

    // Assert
    verify(original).supportsOpenStatementsAcrossRollback();
    assertTrue(actualSupportsOpenStatementsAcrossRollbackResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxBinaryLiteralLength()"})
  public void testGetMaxBinaryLiteralLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxBinaryLiteralLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxBinaryLiteralLength()"})
  public void testGetMaxBinaryLiteralLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxBinaryLiteralLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxBinaryLiteralLength());
    verify(original).getMaxBinaryLiteralLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxBinaryLiteralLength()"})
  public void testGetMaxBinaryLiteralLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxBinaryLiteralLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxBinaryLiteralLength = jdbcDatabaseMetaDataImpl.getMaxBinaryLiteralLength();

    // Assert
    verify(original).getMaxBinaryLiteralLength();
    assertEquals(3, actualMaxBinaryLiteralLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxBinaryLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxBinaryLiteralLength()"})
  public void testGetMaxBinaryLiteralLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxBinaryLiteralLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxBinaryLiteralLength = jdbcDatabaseMetaDataImpl.getMaxBinaryLiteralLength();

    // Assert
    verify(original).getMaxBinaryLiteralLength();
    assertEquals(3, actualMaxBinaryLiteralLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCharLiteralLength()"})
  public void testGetMaxCharLiteralLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxCharLiteralLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCharLiteralLength()"})
  public void testGetMaxCharLiteralLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCharLiteralLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxCharLiteralLength());
    verify(original).getMaxCharLiteralLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCharLiteralLength()"})
  public void testGetMaxCharLiteralLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCharLiteralLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxCharLiteralLength = jdbcDatabaseMetaDataImpl.getMaxCharLiteralLength();

    // Assert
    verify(original).getMaxCharLiteralLength();
    assertEquals(3, actualMaxCharLiteralLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCharLiteralLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCharLiteralLength()"})
  public void testGetMaxCharLiteralLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCharLiteralLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxCharLiteralLength = jdbcDatabaseMetaDataImpl.getMaxCharLiteralLength();

    // Assert
    verify(original).getMaxCharLiteralLength();
    assertEquals(3, actualMaxCharLiteralLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnNameLength()"})
  public void testGetMaxColumnNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnNameLength()"})
  public void testGetMaxColumnNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnNameLength());
    verify(original).getMaxColumnNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnNameLength()"})
  public void testGetMaxColumnNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxColumnNameLength = jdbcDatabaseMetaDataImpl.getMaxColumnNameLength();

    // Assert
    verify(original).getMaxColumnNameLength();
    assertEquals(3, actualMaxColumnNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnNameLength()"})
  public void testGetMaxColumnNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxColumnNameLength = jdbcDatabaseMetaDataImpl.getMaxColumnNameLength();

    // Assert
    verify(original).getMaxColumnNameLength();
    assertEquals(3, actualMaxColumnNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInGroupBy()"})
  public void testGetMaxColumnsInGroupBy() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInGroupBy());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInGroupBy()"})
  public void testGetMaxColumnsInGroupBy2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInGroupBy()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInGroupBy());
    verify(original).getMaxColumnsInGroupBy();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInGroupBy()"})
  public void testGetMaxColumnsInGroupBy3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInGroupBy()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxColumnsInGroupBy = jdbcDatabaseMetaDataImpl.getMaxColumnsInGroupBy();

    // Assert
    verify(original).getMaxColumnsInGroupBy();
    assertEquals(3, actualMaxColumnsInGroupBy);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInGroupBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInGroupBy()"})
  public void testGetMaxColumnsInGroupBy_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInGroupBy()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxColumnsInGroupBy = jdbcDatabaseMetaDataImpl.getMaxColumnsInGroupBy();

    // Assert
    verify(original).getMaxColumnsInGroupBy();
    assertEquals(3, actualMaxColumnsInGroupBy);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInIndex()"})
  public void testGetMaxColumnsInIndex() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInIndex());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInIndex()"})
  public void testGetMaxColumnsInIndex2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInIndex()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInIndex());
    verify(original).getMaxColumnsInIndex();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInIndex()"})
  public void testGetMaxColumnsInIndex3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInIndex()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxColumnsInIndex = jdbcDatabaseMetaDataImpl.getMaxColumnsInIndex();

    // Assert
    verify(original).getMaxColumnsInIndex();
    assertEquals(1, actualMaxColumnsInIndex);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInIndex()"})
  public void testGetMaxColumnsInIndex_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInIndex()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxColumnsInIndex = jdbcDatabaseMetaDataImpl.getMaxColumnsInIndex();

    // Assert
    verify(original).getMaxColumnsInIndex();
    assertEquals(1, actualMaxColumnsInIndex);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInOrderBy()"})
  public void testGetMaxColumnsInOrderBy() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInOrderBy());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInOrderBy()"})
  public void testGetMaxColumnsInOrderBy2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInOrderBy()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInOrderBy());
    verify(original).getMaxColumnsInOrderBy();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInOrderBy()"})
  public void testGetMaxColumnsInOrderBy3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInOrderBy()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxColumnsInOrderBy = jdbcDatabaseMetaDataImpl.getMaxColumnsInOrderBy();

    // Assert
    verify(original).getMaxColumnsInOrderBy();
    assertEquals(3, actualMaxColumnsInOrderBy);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInOrderBy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInOrderBy()"})
  public void testGetMaxColumnsInOrderBy_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInOrderBy()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxColumnsInOrderBy = jdbcDatabaseMetaDataImpl.getMaxColumnsInOrderBy();

    // Assert
    verify(original).getMaxColumnsInOrderBy();
    assertEquals(3, actualMaxColumnsInOrderBy);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInSelect()"})
  public void testGetMaxColumnsInSelect() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInSelect());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInSelect()"})
  public void testGetMaxColumnsInSelect2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInSelect()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInSelect());
    verify(original).getMaxColumnsInSelect();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInSelect()"})
  public void testGetMaxColumnsInSelect3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInSelect()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxColumnsInSelect = jdbcDatabaseMetaDataImpl.getMaxColumnsInSelect();

    // Assert
    verify(original).getMaxColumnsInSelect();
    assertEquals(3, actualMaxColumnsInSelect);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInSelect()"})
  public void testGetMaxColumnsInSelect_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInSelect()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxColumnsInSelect = jdbcDatabaseMetaDataImpl.getMaxColumnsInSelect();

    // Assert
    verify(original).getMaxColumnsInSelect();
    assertEquals(3, actualMaxColumnsInSelect);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInTable()"})
  public void testGetMaxColumnsInTable() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInTable());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInTable()"})
  public void testGetMaxColumnsInTable2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInTable()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxColumnsInTable());
    verify(original).getMaxColumnsInTable();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInTable()"})
  public void testGetMaxColumnsInTable3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInTable()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxColumnsInTable = jdbcDatabaseMetaDataImpl.getMaxColumnsInTable();

    // Assert
    verify(original).getMaxColumnsInTable();
    assertEquals(3, actualMaxColumnsInTable);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxColumnsInTable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxColumnsInTable()"})
  public void testGetMaxColumnsInTable_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxColumnsInTable()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxColumnsInTable = jdbcDatabaseMetaDataImpl.getMaxColumnsInTable();

    // Assert
    verify(original).getMaxColumnsInTable();
    assertEquals(3, actualMaxColumnsInTable);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxConnections()"})
  public void testGetMaxConnections() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxConnections());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxConnections()"})
  public void testGetMaxConnections2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxConnections()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxConnections = jdbcDatabaseMetaDataImpl.getMaxConnections();

    // Assert
    verify(original).getMaxConnections();
    assertEquals(3, actualMaxConnections);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getMaxConnections()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxConnections()"})
  public void testGetMaxConnections_givenDatabaseMetaDataGetMaxConnectionsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxConnections()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxConnections());
    verify(original).getMaxConnections();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxConnections()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxConnections()"})
  public void testGetMaxConnections_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxConnections()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxConnections = jdbcDatabaseMetaDataImpl.getMaxConnections();

    // Assert
    verify(original).getMaxConnections();
    assertEquals(3, actualMaxConnections);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCursorNameLength()"})
  public void testGetMaxCursorNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxCursorNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCursorNameLength()"})
  public void testGetMaxCursorNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCursorNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxCursorNameLength());
    verify(original).getMaxCursorNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCursorNameLength()"})
  public void testGetMaxCursorNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCursorNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxCursorNameLength = jdbcDatabaseMetaDataImpl.getMaxCursorNameLength();

    // Assert
    verify(original).getMaxCursorNameLength();
    assertEquals(3, actualMaxCursorNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCursorNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCursorNameLength()"})
  public void testGetMaxCursorNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCursorNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxCursorNameLength = jdbcDatabaseMetaDataImpl.getMaxCursorNameLength();

    // Assert
    verify(original).getMaxCursorNameLength();
    assertEquals(3, actualMaxCursorNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxIndexLength()"})
  public void testGetMaxIndexLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxIndexLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxIndexLength()"})
  public void testGetMaxIndexLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxIndexLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxIndexLength = jdbcDatabaseMetaDataImpl.getMaxIndexLength();

    // Assert
    verify(original).getMaxIndexLength();
    assertEquals(3, actualMaxIndexLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getMaxIndexLength()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxIndexLength()"})
  public void testGetMaxIndexLength_givenDatabaseMetaDataGetMaxIndexLengthThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxIndexLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxIndexLength());
    verify(original).getMaxIndexLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxIndexLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxIndexLength()"})
  public void testGetMaxIndexLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxIndexLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxIndexLength = jdbcDatabaseMetaDataImpl.getMaxIndexLength();

    // Assert
    verify(original).getMaxIndexLength();
    assertEquals(3, actualMaxIndexLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxSchemaNameLength()"})
  public void testGetMaxSchemaNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxSchemaNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxSchemaNameLength()"})
  public void testGetMaxSchemaNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxSchemaNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxSchemaNameLength());
    verify(original).getMaxSchemaNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxSchemaNameLength()"})
  public void testGetMaxSchemaNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxSchemaNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxSchemaNameLength = jdbcDatabaseMetaDataImpl.getMaxSchemaNameLength();

    // Assert
    verify(original).getMaxSchemaNameLength();
    assertEquals(3, actualMaxSchemaNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxSchemaNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxSchemaNameLength()"})
  public void testGetMaxSchemaNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxSchemaNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxSchemaNameLength = jdbcDatabaseMetaDataImpl.getMaxSchemaNameLength();

    // Assert
    verify(original).getMaxSchemaNameLength();
    assertEquals(3, actualMaxSchemaNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxProcedureNameLength()"})
  public void testGetMaxProcedureNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxProcedureNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxProcedureNameLength()"})
  public void testGetMaxProcedureNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxProcedureNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxProcedureNameLength());
    verify(original).getMaxProcedureNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxProcedureNameLength()"})
  public void testGetMaxProcedureNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxProcedureNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxProcedureNameLength = jdbcDatabaseMetaDataImpl.getMaxProcedureNameLength();

    // Assert
    verify(original).getMaxProcedureNameLength();
    assertEquals(3, actualMaxProcedureNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxProcedureNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxProcedureNameLength()"})
  public void testGetMaxProcedureNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxProcedureNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxProcedureNameLength = jdbcDatabaseMetaDataImpl.getMaxProcedureNameLength();

    // Assert
    verify(original).getMaxProcedureNameLength();
    assertEquals(3, actualMaxProcedureNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCatalogNameLength()"})
  public void testGetMaxCatalogNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxCatalogNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCatalogNameLength()"})
  public void testGetMaxCatalogNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCatalogNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxCatalogNameLength());
    verify(original).getMaxCatalogNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCatalogNameLength()"})
  public void testGetMaxCatalogNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCatalogNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxCatalogNameLength = jdbcDatabaseMetaDataImpl.getMaxCatalogNameLength();

    // Assert
    verify(original).getMaxCatalogNameLength();
    assertEquals(3, actualMaxCatalogNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxCatalogNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxCatalogNameLength()"})
  public void testGetMaxCatalogNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxCatalogNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxCatalogNameLength = jdbcDatabaseMetaDataImpl.getMaxCatalogNameLength();

    // Assert
    verify(original).getMaxCatalogNameLength();
    assertEquals(3, actualMaxCatalogNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxRowSize()"})
  public void testGetMaxRowSize() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxRowSize());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxRowSize()"})
  public void testGetMaxRowSize2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxRowSize()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxRowSize = jdbcDatabaseMetaDataImpl.getMaxRowSize();

    // Assert
    verify(original).getMaxRowSize();
    assertEquals(3, actualMaxRowSize);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getMaxRowSize()} return three.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxRowSize()"})
  public void testGetMaxRowSize_givenDatabaseMetaDataGetMaxRowSizeReturnThree_thenReturnThree()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxRowSize()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxRowSize = jdbcDatabaseMetaDataImpl.getMaxRowSize();

    // Assert
    verify(original).getMaxRowSize();
    assertEquals(3, actualMaxRowSize);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getMaxRowSize()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxRowSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxRowSize()"})
  public void testGetMaxRowSize_givenDatabaseMetaDataGetMaxRowSizeThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxRowSize()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxRowSize());
    verify(original).getMaxRowSize();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs()"})
  public void testDoesMaxRowSizeIncludeBlobs() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs()"})
  public void testDoesMaxRowSizeIncludeBlobs2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.doesMaxRowSizeIncludeBlobs()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs());
    verify(original).doesMaxRowSizeIncludeBlobs();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs()"})
  public void testDoesMaxRowSizeIncludeBlobs3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.doesMaxRowSizeIncludeBlobs()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualDoesMaxRowSizeIncludeBlobsResult =
        jdbcDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs();

    // Assert
    verify(original).doesMaxRowSizeIncludeBlobs();
    assertTrue(actualDoesMaxRowSizeIncludeBlobsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs()"})
  public void testDoesMaxRowSizeIncludeBlobs_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.doesMaxRowSizeIncludeBlobs()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDoesMaxRowSizeIncludeBlobsResult =
        jdbcDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs();

    // Assert
    verify(original).doesMaxRowSizeIncludeBlobs();
    assertFalse(actualDoesMaxRowSizeIncludeBlobsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#doesMaxRowSizeIncludeBlobs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs()"})
  public void testDoesMaxRowSizeIncludeBlobs_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.doesMaxRowSizeIncludeBlobs()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDoesMaxRowSizeIncludeBlobsResult =
        jdbcDatabaseMetaDataImpl.doesMaxRowSizeIncludeBlobs();

    // Assert
    verify(original).doesMaxRowSizeIncludeBlobs();
    assertTrue(actualDoesMaxRowSizeIncludeBlobsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatementLength()"})
  public void testGetMaxStatementLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxStatementLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatementLength()"})
  public void testGetMaxStatementLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxStatementLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxStatementLength());
    verify(original).getMaxStatementLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatementLength()"})
  public void testGetMaxStatementLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxStatementLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxStatementLength = jdbcDatabaseMetaDataImpl.getMaxStatementLength();

    // Assert
    verify(original).getMaxStatementLength();
    assertEquals(3, actualMaxStatementLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatementLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatementLength()"})
  public void testGetMaxStatementLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxStatementLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxStatementLength = jdbcDatabaseMetaDataImpl.getMaxStatementLength();

    // Assert
    verify(original).getMaxStatementLength();
    assertEquals(3, actualMaxStatementLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatements()"})
  public void testGetMaxStatements() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxStatements());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatements()"})
  public void testGetMaxStatements2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxStatements()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxStatements = jdbcDatabaseMetaDataImpl.getMaxStatements();

    // Assert
    verify(original).getMaxStatements();
    assertEquals(3, actualMaxStatements);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#getMaxStatements()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatements()"})
  public void testGetMaxStatements_givenDatabaseMetaDataGetMaxStatementsThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxStatements()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxStatements());
    verify(original).getMaxStatements();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxStatements()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxStatements()"})
  public void testGetMaxStatements_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxStatements()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxStatements = jdbcDatabaseMetaDataImpl.getMaxStatements();

    // Assert
    verify(original).getMaxStatements();
    assertEquals(3, actualMaxStatements);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTableNameLength()"})
  public void testGetMaxTableNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxTableNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTableNameLength()"})
  public void testGetMaxTableNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxTableNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxTableNameLength());
    verify(original).getMaxTableNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTableNameLength()"})
  public void testGetMaxTableNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxTableNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxTableNameLength = jdbcDatabaseMetaDataImpl.getMaxTableNameLength();

    // Assert
    verify(original).getMaxTableNameLength();
    assertEquals(3, actualMaxTableNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTableNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTableNameLength()"})
  public void testGetMaxTableNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxTableNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxTableNameLength = jdbcDatabaseMetaDataImpl.getMaxTableNameLength();

    // Assert
    verify(original).getMaxTableNameLength();
    assertEquals(3, actualMaxTableNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTablesInSelect()"})
  public void testGetMaxTablesInSelect() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxTablesInSelect());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTablesInSelect()"})
  public void testGetMaxTablesInSelect2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxTablesInSelect()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxTablesInSelect());
    verify(original).getMaxTablesInSelect();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTablesInSelect()"})
  public void testGetMaxTablesInSelect3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxTablesInSelect()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxTablesInSelect = jdbcDatabaseMetaDataImpl.getMaxTablesInSelect();

    // Assert
    verify(original).getMaxTablesInSelect();
    assertEquals(3, actualMaxTablesInSelect);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxTablesInSelect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxTablesInSelect()"})
  public void testGetMaxTablesInSelect_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxTablesInSelect()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxTablesInSelect = jdbcDatabaseMetaDataImpl.getMaxTablesInSelect();

    // Assert
    verify(original).getMaxTablesInSelect();
    assertEquals(3, actualMaxTablesInSelect);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxUserNameLength()"})
  public void testGetMaxUserNameLength() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxUserNameLength());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxUserNameLength()"})
  public void testGetMaxUserNameLength2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxUserNameLength()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.getMaxUserNameLength());
    verify(original).getMaxUserNameLength();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxUserNameLength()"})
  public void testGetMaxUserNameLength3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxUserNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualMaxUserNameLength = jdbcDatabaseMetaDataImpl.getMaxUserNameLength();

    // Assert
    verify(original).getMaxUserNameLength();
    assertEquals(3, actualMaxUserNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getMaxUserNameLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getMaxUserNameLength()"})
  public void testGetMaxUserNameLength_thenReturnThree() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getMaxUserNameLength()).thenReturn(3);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualMaxUserNameLength = jdbcDatabaseMetaDataImpl.getMaxUserNameLength();

    // Assert
    verify(original).getMaxUserNameLength();
    assertEquals(3, actualMaxUserNameLength);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDefaultTransactionIsolation()"})
  public void testGetDefaultTransactionIsolation() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDefaultTransactionIsolation());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDefaultTransactionIsolation()"})
  public void testGetDefaultTransactionIsolation2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDefaultTransactionIsolation()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.getDefaultTransactionIsolation());
    verify(original).getDefaultTransactionIsolation();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDefaultTransactionIsolation()"})
  public void testGetDefaultTransactionIsolation3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDefaultTransactionIsolation()).thenReturn(1);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    int actualDefaultTransactionIsolation =
        jdbcDatabaseMetaDataImpl.getDefaultTransactionIsolation();

    // Assert
    verify(original).getDefaultTransactionIsolation();
    assertEquals(1, actualDefaultTransactionIsolation);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDefaultTransactionIsolation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCDatabaseMetaDataImpl.getDefaultTransactionIsolation()"})
  public void testGetDefaultTransactionIsolation_thenReturnOne() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.getDefaultTransactionIsolation()).thenReturn(1);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    int actualDefaultTransactionIsolation =
        jdbcDatabaseMetaDataImpl.getDefaultTransactionIsolation();

    // Assert
    verify(original).getDefaultTransactionIsolation();
    assertEquals(1, actualDefaultTransactionIsolation);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactions()"})
  public void testSupportsTransactions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsTransactions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactions()"})
  public void testSupportsTransactions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsTransactions());
    verify(original).supportsTransactions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactions()"})
  public void testSupportsTransactions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsTransactionsResult = jdbcDatabaseMetaDataImpl.supportsTransactions();

    // Assert
    verify(original).supportsTransactions();
    assertTrue(actualSupportsTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactions()"})
  public void testSupportsTransactions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsTransactionsResult = jdbcDatabaseMetaDataImpl.supportsTransactions();

    // Assert
    verify(original).supportsTransactions();
    assertFalse(actualSupportsTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactions()"})
  public void testSupportsTransactions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsTransactionsResult = jdbcDatabaseMetaDataImpl.supportsTransactions();

    // Assert
    verify(original).supportsTransactions();
    assertTrue(actualSupportsTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactionIsolationLevel(int)"})
  public void testSupportsTransactionIsolationLevel() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsTransactionIsolationLevel(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactionIsolationLevel(int)"})
  public void testSupportsTransactionIsolationLevel2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactionIsolationLevel(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsTransactionIsolationLevel(1));
    verify(original).supportsTransactionIsolationLevel(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactionIsolationLevel(int)"})
  public void testSupportsTransactionIsolationLevel3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsTransactionIsolationLevelResult =
        jdbcDatabaseMetaDataImpl.supportsTransactionIsolationLevel(1);

    // Assert
    verify(original).supportsTransactionIsolationLevel(1);
    assertTrue(actualSupportsTransactionIsolationLevelResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactionIsolationLevel(int)"})
  public void testSupportsTransactionIsolationLevel_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactionIsolationLevel(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsTransactionIsolationLevelResult =
        jdbcDatabaseMetaDataImpl.supportsTransactionIsolationLevel(1);

    // Assert
    verify(original).supportsTransactionIsolationLevel(1);
    assertFalse(actualSupportsTransactionIsolationLevelResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsTransactionIsolationLevel(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsTransactionIsolationLevel(int)"})
  public void testSupportsTransactionIsolationLevel_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsTransactionIsolationLevelResult =
        jdbcDatabaseMetaDataImpl.supportsTransactionIsolationLevel(1);

    // Assert
    verify(original).supportsTransactionIsolationLevel(1);
    assertTrue(actualSupportsTransactionIsolationLevelResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions()"
  })
  public void testSupportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions()"
  })
  public void testSupportsDataDefinitionAndDataManipulationTransactions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataDefinitionAndDataManipulationTransactions())
        .thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions());
    verify(original).supportsDataDefinitionAndDataManipulationTransactions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions()"
  })
  public void testSupportsDataDefinitionAndDataManipulationTransactions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataDefinitionAndDataManipulationTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsDataDefinitionAndDataManipulationTransactionsResult =
        jdbcDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions();

    // Assert
    verify(original).supportsDataDefinitionAndDataManipulationTransactions();
    assertTrue(actualSupportsDataDefinitionAndDataManipulationTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions()"
  })
  public void testSupportsDataDefinitionAndDataManipulationTransactions_thenReturnFalse()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataDefinitionAndDataManipulationTransactions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsDataDefinitionAndDataManipulationTransactionsResult =
        jdbcDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions();

    // Assert
    verify(original).supportsDataDefinitionAndDataManipulationTransactions();
    assertFalse(actualSupportsDataDefinitionAndDataManipulationTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataDefinitionAndDataManipulationTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean JDBCDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions()"
  })
  public void testSupportsDataDefinitionAndDataManipulationTransactions_thenReturnTrue()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataDefinitionAndDataManipulationTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsDataDefinitionAndDataManipulationTransactionsResult =
        jdbcDatabaseMetaDataImpl.supportsDataDefinitionAndDataManipulationTransactions();

    // Assert
    verify(original).supportsDataDefinitionAndDataManipulationTransactions();
    assertTrue(actualSupportsDataDefinitionAndDataManipulationTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly()"})
  public void testSupportsDataManipulationTransactionsOnly() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly()"})
  public void testSupportsDataManipulationTransactionsOnly2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataManipulationTransactionsOnly()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly());
    verify(original).supportsDataManipulationTransactionsOnly();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}.
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly()"})
  public void testSupportsDataManipulationTransactionsOnly3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataManipulationTransactionsOnly()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsDataManipulationTransactionsOnlyResult =
        jdbcDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly();

    // Assert
    verify(original).supportsDataManipulationTransactionsOnly();
    assertTrue(actualSupportsDataManipulationTransactionsOnlyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly()"})
  public void testSupportsDataManipulationTransactionsOnly_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataManipulationTransactionsOnly()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsDataManipulationTransactionsOnlyResult =
        jdbcDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly();

    // Assert
    verify(original).supportsDataManipulationTransactionsOnly();
    assertFalse(actualSupportsDataManipulationTransactionsOnlyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * JDBCDatabaseMetaDataImpl#supportsDataManipulationTransactionsOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly()"})
  public void testSupportsDataManipulationTransactionsOnly_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsDataManipulationTransactionsOnly()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsDataManipulationTransactionsOnlyResult =
        jdbcDatabaseMetaDataImpl.supportsDataManipulationTransactionsOnly();

    // Assert
    verify(original).supportsDataManipulationTransactionsOnly();
    assertTrue(actualSupportsDataManipulationTransactionsOnlyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit()"})
  public void testDataDefinitionCausesTransactionCommit() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit()"})
  public void testDataDefinitionCausesTransactionCommit2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionCausesTransactionCommit()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit());
    verify(original).dataDefinitionCausesTransactionCommit();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit()"})
  public void testDataDefinitionCausesTransactionCommit3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionCausesTransactionCommit()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualDataDefinitionCausesTransactionCommitResult =
        jdbcDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit();

    // Assert
    verify(original).dataDefinitionCausesTransactionCommit();
    assertTrue(actualDataDefinitionCausesTransactionCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit()"})
  public void testDataDefinitionCausesTransactionCommit_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionCausesTransactionCommit()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDataDefinitionCausesTransactionCommitResult =
        jdbcDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit();

    // Assert
    verify(original).dataDefinitionCausesTransactionCommit();
    assertFalse(actualDataDefinitionCausesTransactionCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionCausesTransactionCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit()"})
  public void testDataDefinitionCausesTransactionCommit_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionCausesTransactionCommit()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDataDefinitionCausesTransactionCommitResult =
        jdbcDatabaseMetaDataImpl.dataDefinitionCausesTransactionCommit();

    // Assert
    verify(original).dataDefinitionCausesTransactionCommit();
    assertTrue(actualDataDefinitionCausesTransactionCommitResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions()"})
  public void testDataDefinitionIgnoredInTransactions() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions()"})
  public void testDataDefinitionIgnoredInTransactions2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionIgnoredInTransactions()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions());
    verify(original).dataDefinitionIgnoredInTransactions();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions()"})
  public void testDataDefinitionIgnoredInTransactions3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionIgnoredInTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualDataDefinitionIgnoredInTransactionsResult =
        jdbcDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions();

    // Assert
    verify(original).dataDefinitionIgnoredInTransactions();
    assertTrue(actualDataDefinitionIgnoredInTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions()"})
  public void testDataDefinitionIgnoredInTransactions_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionIgnoredInTransactions()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDataDefinitionIgnoredInTransactionsResult =
        jdbcDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions();

    // Assert
    verify(original).dataDefinitionIgnoredInTransactions();
    assertFalse(actualDataDefinitionIgnoredInTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#dataDefinitionIgnoredInTransactions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions()"})
  public void testDataDefinitionIgnoredInTransactions_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.dataDefinitionIgnoredInTransactions()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDataDefinitionIgnoredInTransactionsResult =
        jdbcDatabaseMetaDataImpl.dataDefinitionIgnoredInTransactions();

    // Assert
    verify(original).dataDefinitionIgnoredInTransactions();
    assertTrue(actualDataDefinitionIgnoredInTransactionsResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#getDataSource()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCDataSource JDBCDatabaseMetaDataImpl.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(connection, mock(DatabaseMetaData.class));

    // Act
    jdbcDatabaseMetaDataImpl.getDataSource();

    // Assert
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetType(int)"})
  public void testSupportsResultSetType() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsResultSetType(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetType(int)"})
  public void testSupportsResultSetType2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetType(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsResultSetType(1));
    verify(original).supportsResultSetType(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetType(int)"})
  public void testSupportsResultSetType3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetType(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsResultSetTypeResult = jdbcDatabaseMetaDataImpl.supportsResultSetType(1);

    // Assert
    verify(original).supportsResultSetType(1);
    assertTrue(actualSupportsResultSetTypeResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetType(int)"})
  public void testSupportsResultSetType_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetType(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsResultSetTypeResult = jdbcDatabaseMetaDataImpl.supportsResultSetType(1);

    // Assert
    verify(original).supportsResultSetType(1);
    assertFalse(actualSupportsResultSetTypeResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetType(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetType(int)"})
  public void testSupportsResultSetType_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetType(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsResultSetTypeResult = jdbcDatabaseMetaDataImpl.supportsResultSetType(1);

    // Assert
    verify(original).supportsResultSetType(1);
    assertTrue(actualSupportsResultSetTypeResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetConcurrency(int, int)"})
  public void testSupportsResultSetConcurrency() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsResultSetConcurrency(1, 1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetConcurrency(int, int)"})
  public void testSupportsResultSetConcurrency2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetConcurrency(anyInt(), anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsResultSetConcurrency(1, 1));
    verify(original).supportsResultSetConcurrency(1, 1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetConcurrency(int, int)"})
  public void testSupportsResultSetConcurrency3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetConcurrency(anyInt(), anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsResultSetConcurrencyResult =
        jdbcDatabaseMetaDataImpl.supportsResultSetConcurrency(1, 1);

    // Assert
    verify(original).supportsResultSetConcurrency(1, 1);
    assertTrue(actualSupportsResultSetConcurrencyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetConcurrency(int, int)"})
  public void testSupportsResultSetConcurrency_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetConcurrency(anyInt(), anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsResultSetConcurrencyResult =
        jdbcDatabaseMetaDataImpl.supportsResultSetConcurrency(1, 1);

    // Assert
    verify(original).supportsResultSetConcurrency(1, 1);
    assertFalse(actualSupportsResultSetConcurrencyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsResultSetConcurrency(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsResultSetConcurrency(int, int)"})
  public void testSupportsResultSetConcurrency_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsResultSetConcurrency(anyInt(), anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsResultSetConcurrencyResult =
        jdbcDatabaseMetaDataImpl.supportsResultSetConcurrency(1, 1);

    // Assert
    verify(original).supportsResultSetConcurrency(1, 1);
    assertTrue(actualSupportsResultSetConcurrencyResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownUpdatesAreVisible(int)"})
  public void testOwnUpdatesAreVisible() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.ownUpdatesAreVisible(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownUpdatesAreVisible(int)"})
  public void testOwnUpdatesAreVisible2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownUpdatesAreVisible(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.ownUpdatesAreVisible(1));
    verify(original).ownUpdatesAreVisible(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownUpdatesAreVisible(int)"})
  public void testOwnUpdatesAreVisible3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownUpdatesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualOwnUpdatesAreVisibleResult = jdbcDatabaseMetaDataImpl.ownUpdatesAreVisible(1);

    // Assert
    verify(original).ownUpdatesAreVisible(1);
    assertTrue(actualOwnUpdatesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownUpdatesAreVisible(int)"})
  public void testOwnUpdatesAreVisible_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownUpdatesAreVisible(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOwnUpdatesAreVisibleResult = jdbcDatabaseMetaDataImpl.ownUpdatesAreVisible(1);

    // Assert
    verify(original).ownUpdatesAreVisible(1);
    assertFalse(actualOwnUpdatesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownUpdatesAreVisible(int)"})
  public void testOwnUpdatesAreVisible_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownUpdatesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOwnUpdatesAreVisibleResult = jdbcDatabaseMetaDataImpl.ownUpdatesAreVisible(1);

    // Assert
    verify(original).ownUpdatesAreVisible(1);
    assertTrue(actualOwnUpdatesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownDeletesAreVisible(int)"})
  public void testOwnDeletesAreVisible() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.ownDeletesAreVisible(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownDeletesAreVisible(int)"})
  public void testOwnDeletesAreVisible2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownDeletesAreVisible(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.ownDeletesAreVisible(1));
    verify(original).ownDeletesAreVisible(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownDeletesAreVisible(int)"})
  public void testOwnDeletesAreVisible3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownDeletesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualOwnDeletesAreVisibleResult = jdbcDatabaseMetaDataImpl.ownDeletesAreVisible(1);

    // Assert
    verify(original).ownDeletesAreVisible(1);
    assertTrue(actualOwnDeletesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownDeletesAreVisible(int)"})
  public void testOwnDeletesAreVisible_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownDeletesAreVisible(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOwnDeletesAreVisibleResult = jdbcDatabaseMetaDataImpl.ownDeletesAreVisible(1);

    // Assert
    verify(original).ownDeletesAreVisible(1);
    assertFalse(actualOwnDeletesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownDeletesAreVisible(int)"})
  public void testOwnDeletesAreVisible_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownDeletesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOwnDeletesAreVisibleResult = jdbcDatabaseMetaDataImpl.ownDeletesAreVisible(1);

    // Assert
    verify(original).ownDeletesAreVisible(1);
    assertTrue(actualOwnDeletesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownInsertsAreVisible(int)"})
  public void testOwnInsertsAreVisible() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.ownInsertsAreVisible(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownInsertsAreVisible(int)"})
  public void testOwnInsertsAreVisible2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownInsertsAreVisible(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.ownInsertsAreVisible(1));
    verify(original).ownInsertsAreVisible(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownInsertsAreVisible(int)"})
  public void testOwnInsertsAreVisible3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownInsertsAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualOwnInsertsAreVisibleResult = jdbcDatabaseMetaDataImpl.ownInsertsAreVisible(1);

    // Assert
    verify(original).ownInsertsAreVisible(1);
    assertTrue(actualOwnInsertsAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownInsertsAreVisible(int)"})
  public void testOwnInsertsAreVisible_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownInsertsAreVisible(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOwnInsertsAreVisibleResult = jdbcDatabaseMetaDataImpl.ownInsertsAreVisible(1);

    // Assert
    verify(original).ownInsertsAreVisible(1);
    assertFalse(actualOwnInsertsAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#ownInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.ownInsertsAreVisible(int)"})
  public void testOwnInsertsAreVisible_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.ownInsertsAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOwnInsertsAreVisibleResult = jdbcDatabaseMetaDataImpl.ownInsertsAreVisible(1);

    // Assert
    verify(original).ownInsertsAreVisible(1);
    assertTrue(actualOwnInsertsAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersUpdatesAreVisible(int)"})
  public void testOthersUpdatesAreVisible() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.othersUpdatesAreVisible(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersUpdatesAreVisible(int)"})
  public void testOthersUpdatesAreVisible2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersUpdatesAreVisible(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.othersUpdatesAreVisible(1));
    verify(original).othersUpdatesAreVisible(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersUpdatesAreVisible(int)"})
  public void testOthersUpdatesAreVisible3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersUpdatesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualOthersUpdatesAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersUpdatesAreVisible(1);

    // Assert
    verify(original).othersUpdatesAreVisible(1);
    assertTrue(actualOthersUpdatesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersUpdatesAreVisible(int)"})
  public void testOthersUpdatesAreVisible_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersUpdatesAreVisible(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOthersUpdatesAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersUpdatesAreVisible(1);

    // Assert
    verify(original).othersUpdatesAreVisible(1);
    assertFalse(actualOthersUpdatesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersUpdatesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersUpdatesAreVisible(int)"})
  public void testOthersUpdatesAreVisible_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersUpdatesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOthersUpdatesAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersUpdatesAreVisible(1);

    // Assert
    verify(original).othersUpdatesAreVisible(1);
    assertTrue(actualOthersUpdatesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersDeletesAreVisible(int)"})
  public void testOthersDeletesAreVisible() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.othersDeletesAreVisible(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersDeletesAreVisible(int)"})
  public void testOthersDeletesAreVisible2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersDeletesAreVisible(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.othersDeletesAreVisible(1));
    verify(original).othersDeletesAreVisible(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersDeletesAreVisible(int)"})
  public void testOthersDeletesAreVisible3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersDeletesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualOthersDeletesAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersDeletesAreVisible(1);

    // Assert
    verify(original).othersDeletesAreVisible(1);
    assertTrue(actualOthersDeletesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersDeletesAreVisible(int)"})
  public void testOthersDeletesAreVisible_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersDeletesAreVisible(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOthersDeletesAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersDeletesAreVisible(1);

    // Assert
    verify(original).othersDeletesAreVisible(1);
    assertFalse(actualOthersDeletesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersDeletesAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersDeletesAreVisible(int)"})
  public void testOthersDeletesAreVisible_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersDeletesAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOthersDeletesAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersDeletesAreVisible(1);

    // Assert
    verify(original).othersDeletesAreVisible(1);
    assertTrue(actualOthersDeletesAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersInsertsAreVisible(int)"})
  public void testOthersInsertsAreVisible() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.othersInsertsAreVisible(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersInsertsAreVisible(int)"})
  public void testOthersInsertsAreVisible2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersInsertsAreVisible(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.othersInsertsAreVisible(1));
    verify(original).othersInsertsAreVisible(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersInsertsAreVisible(int)"})
  public void testOthersInsertsAreVisible3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersInsertsAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualOthersInsertsAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersInsertsAreVisible(1);

    // Assert
    verify(original).othersInsertsAreVisible(1);
    assertTrue(actualOthersInsertsAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersInsertsAreVisible(int)"})
  public void testOthersInsertsAreVisible_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersInsertsAreVisible(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOthersInsertsAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersInsertsAreVisible(1);

    // Assert
    verify(original).othersInsertsAreVisible(1);
    assertFalse(actualOthersInsertsAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#othersInsertsAreVisible(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.othersInsertsAreVisible(int)"})
  public void testOthersInsertsAreVisible_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.othersInsertsAreVisible(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualOthersInsertsAreVisibleResult =
        jdbcDatabaseMetaDataImpl.othersInsertsAreVisible(1);

    // Assert
    verify(original).othersInsertsAreVisible(1);
    assertTrue(actualOthersInsertsAreVisibleResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.updatesAreDetected(int)"})
  public void testUpdatesAreDetected() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.updatesAreDetected(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.updatesAreDetected(int)"})
  public void testUpdatesAreDetected2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.updatesAreDetected(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualUpdatesAreDetectedResult = jdbcDatabaseMetaDataImpl.updatesAreDetected(1);

    // Assert
    verify(original).updatesAreDetected(1);
    assertTrue(actualUpdatesAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#updatesAreDetected(int)} throw
   *       {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.updatesAreDetected(int)"})
  public void testUpdatesAreDetected_givenDatabaseMetaDataUpdatesAreDetectedThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.updatesAreDetected(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.updatesAreDetected(1));
    verify(original).updatesAreDetected(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.updatesAreDetected(int)"})
  public void testUpdatesAreDetected_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.updatesAreDetected(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualUpdatesAreDetectedResult = jdbcDatabaseMetaDataImpl.updatesAreDetected(1);

    // Assert
    verify(original).updatesAreDetected(1);
    assertFalse(actualUpdatesAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#updatesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.updatesAreDetected(int)"})
  public void testUpdatesAreDetected_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.updatesAreDetected(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualUpdatesAreDetectedResult = jdbcDatabaseMetaDataImpl.updatesAreDetected(1);

    // Assert
    verify(original).updatesAreDetected(1);
    assertTrue(actualUpdatesAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.deletesAreDetected(int)"})
  public void testDeletesAreDetected() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.deletesAreDetected(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.deletesAreDetected(int)"})
  public void testDeletesAreDetected2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.deletesAreDetected(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualDeletesAreDetectedResult = jdbcDatabaseMetaDataImpl.deletesAreDetected(1);

    // Assert
    verify(original).deletesAreDetected(1);
    assertTrue(actualDeletesAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#deletesAreDetected(int)} throw
   *       {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.deletesAreDetected(int)"})
  public void testDeletesAreDetected_givenDatabaseMetaDataDeletesAreDetectedThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.deletesAreDetected(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.deletesAreDetected(1));
    verify(original).deletesAreDetected(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.deletesAreDetected(int)"})
  public void testDeletesAreDetected_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.deletesAreDetected(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDeletesAreDetectedResult = jdbcDatabaseMetaDataImpl.deletesAreDetected(1);

    // Assert
    verify(original).deletesAreDetected(1);
    assertFalse(actualDeletesAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#deletesAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.deletesAreDetected(int)"})
  public void testDeletesAreDetected_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.deletesAreDetected(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualDeletesAreDetectedResult = jdbcDatabaseMetaDataImpl.deletesAreDetected(1);

    // Assert
    verify(original).deletesAreDetected(1);
    assertTrue(actualDeletesAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.insertsAreDetected(int)"})
  public void testInsertsAreDetected() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.insertsAreDetected(1));
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.insertsAreDetected(int)"})
  public void testInsertsAreDetected2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.insertsAreDetected(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualInsertsAreDetectedResult = jdbcDatabaseMetaDataImpl.insertsAreDetected(1);

    // Assert
    verify(original).insertsAreDetected(1);
    assertTrue(actualInsertsAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData} {@link DatabaseMetaData#insertsAreDetected(int)} throw
   *       {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.insertsAreDetected(int)"})
  public void testInsertsAreDetected_givenDatabaseMetaDataInsertsAreDetectedThrowSQLException()
      throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.insertsAreDetected(anyInt())).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.insertsAreDetected(1));
    verify(original).insertsAreDetected(1);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.insertsAreDetected(int)"})
  public void testInsertsAreDetected_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.insertsAreDetected(anyInt())).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualInsertsAreDetectedResult = jdbcDatabaseMetaDataImpl.insertsAreDetected(1);

    // Assert
    verify(original).insertsAreDetected(1);
    assertFalse(actualInsertsAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#insertsAreDetected(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.insertsAreDetected(int)"})
  public void testInsertsAreDetected_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.insertsAreDetected(anyInt())).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualInsertsAreDetectedResult = jdbcDatabaseMetaDataImpl.insertsAreDetected(1);

    // Assert
    verify(original).insertsAreDetected(1);
    assertTrue(actualInsertsAreDetectedResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsBatchUpdates()"})
  public void testSupportsBatchUpdates() throws SQLException {
    // Arrange
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), null);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsBatchUpdates());
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsBatchUpdates()"})
  public void testSupportsBatchUpdates2() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsBatchUpdates()).thenThrow(new SQLException());
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcDatabaseMetaDataImpl.supportsBatchUpdates());
    verify(original).supportsBatchUpdates();
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}.
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsBatchUpdates()"})
  public void testSupportsBatchUpdates3() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsBatchUpdates()).thenReturn(true);
    JDBCDatabaseMetaDataImpl original2 =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original2);

    // Act
    boolean actualSupportsBatchUpdatesResult = jdbcDatabaseMetaDataImpl.supportsBatchUpdates();

    // Assert
    verify(original).supportsBatchUpdates();
    assertTrue(actualSupportsBatchUpdatesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsBatchUpdates()"})
  public void testSupportsBatchUpdates_thenReturnFalse() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsBatchUpdates()).thenReturn(false);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsBatchUpdatesResult = jdbcDatabaseMetaDataImpl.supportsBatchUpdates();

    // Assert
    verify(original).supportsBatchUpdates();
    assertFalse(actualSupportsBatchUpdatesResult);
  }

  /**
   * Test {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDatabaseMetaDataImpl#supportsBatchUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCDatabaseMetaDataImpl.supportsBatchUpdates()"})
  public void testSupportsBatchUpdates_thenReturnTrue() throws SQLException {
    // Arrange
    DatabaseMetaData original = mock(DatabaseMetaData.class);
    when(original.supportsBatchUpdates()).thenReturn(true);
    JDBCDatabaseMetaDataImpl jdbcDatabaseMetaDataImpl =
        new JDBCDatabaseMetaDataImpl(mock(JDBCSession.class), original);

    // Act
    boolean actualSupportsBatchUpdatesResult = jdbcDatabaseMetaDataImpl.supportsBatchUpdates();

    // Assert
    verify(original).supportsBatchUpdates();
    assertTrue(actualSupportsBatchUpdatesResult);
  }
}
