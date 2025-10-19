package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCConnectExceptionDiffblueTest {
  /**
   * Test {@link DBCConnectException#DBCConnectException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBCConnectException#DBCConnectException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCConnectException.<init>(String, Throwable)"})
  public void testNewDBCConnectException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBCConnectException actualDbcConnectException =
        new DBCConnectException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualDbcConnectException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbcConnectException.getMessage());
    assertTrue(actualDbcConnectException.hasMessage());
    assertSame(cause, actualDbcConnectException.getCause());
  }

  /**
   * Test {@link DBCConnectException#DBCConnectException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBCConnectException#DBCConnectException(String, Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCConnectException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBCConnectException_thenReturnLocalizedMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBCConnectException actualDbcConnectException =
        new DBCConnectException("An error occurred", cause, mock(DBPDataSource.class));

    // Assert
    assertEquals("An error occurred", actualDbcConnectException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbcConnectException.getMessage());
    assertTrue(actualDbcConnectException.hasMessage());
    assertSame(cause, actualDbcConnectException.getCause());
  }

  /**
   * Test {@link DBCConnectException#DBCConnectException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBCConnectException#DBCConnectException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCConnectException.<init>(String, Throwable)"})
  public void testNewDBCConnectException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    DBCConnectException actualDbcConnectException = new DBCConnectException(null, cause);

    // Assert
    assertNull(actualDbcConnectException.getLocalizedMessage());
    assertNull(actualDbcConnectException.getMessage());
    assertFalse(actualDbcConnectException.hasMessage());
    assertSame(cause, actualDbcConnectException.getCause());
  }

  /**
   * Test {@link DBCConnectException#DBCConnectException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBCConnectException#DBCConnectException(String, Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCConnectException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBCConnectException_whenNull_thenReturnLocalizedMessageIsNull2() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    DBCConnectException actualDbcConnectException =
        new DBCConnectException(null, cause, mock(DBPDataSource.class));

    // Assert
    assertNull(actualDbcConnectException.getLocalizedMessage());
    assertNull(actualDbcConnectException.getMessage());
    assertFalse(actualDbcConnectException.hasMessage());
    assertSame(cause, actualDbcConnectException.getCause());
  }
}
