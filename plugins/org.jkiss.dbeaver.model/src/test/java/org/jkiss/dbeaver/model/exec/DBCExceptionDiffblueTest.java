package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBCException#DBCException(String)}
   *   <li>{@link DBCException#getExecutionContext()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCException.<init>(String)",
    "DBCExecutionContext DBCException.getExecutionContext()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBCException actualDbcException = new DBCException("An error occurred");
    DBCExecutionContext actualExecutionContext = actualDbcException.getExecutionContext();

    // Assert
    assertEquals("An error occurred", actualDbcException.getMessage());
    assertNull(actualDbcException.getCause());
    assertNull(actualExecutionContext);
    assertEquals(0, actualDbcException.getSuppressed().length);
    assertTrue(actualDbcException.hasMessage());
  }

  /**
   * Test {@link DBCException#DBCException(Throwable, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link DBCException#DBCException(Throwable, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(Throwable, DBCExecutionContext)"})
  public void testNewDBCException() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    DBCException actualDbcException = new DBCException(cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertNull(actualDbcException.getLocalizedMessage());
    assertNull(actualDbcException.getMessage());
    assertEquals(0, actualDbcException.getSuppressed().length);
    assertFalse(actualDbcException.hasMessage());
    assertSame(cause, actualDbcException.getCause());
    assertSame(executionContext, actualDbcException.getExecutionContext());
  }

  /**
   * Test {@link DBCException#DBCException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBCException#DBCException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(String, Throwable)"})
  public void testNewDBCException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBCException actualDbcException = new DBCException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualDbcException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbcException.getMessage());
    assertTrue(actualDbcException.hasMessage());
    assertSame(cause, actualDbcException.getCause());
  }

  /**
   * Test {@link DBCException#DBCException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBCException#DBCException(String, Throwable, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBCException_thenReturnLocalizedMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBCException actualDbcException =
        new DBCException("An error occurred", cause, mock(DBPDataSource.class));

    // Assert
    assertEquals("An error occurred", actualDbcException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbcException.getMessage());
    assertTrue(actualDbcException.hasMessage());
    assertSame(cause, actualDbcException.getCause());
  }

  /**
   * Test {@link DBCException#DBCException(String, Throwable, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBCException#DBCException(String, Throwable, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(String, Throwable, DBCExecutionContext)"})
  public void testNewDBCException_thenReturnLocalizedMessageIsAnErrorOccurred3() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    DBCException actualDbcException =
        new DBCException("An error occurred", cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertEquals("An error occurred", actualDbcException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbcException.getMessage());
    assertTrue(actualDbcException.hasMessage());
  }

  /**
   * Test {@link DBCException#DBCException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBCException#DBCException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(String, Throwable)"})
  public void testNewDBCException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    DBCException actualDbcException = new DBCException(null, cause);

    // Assert
    assertNull(actualDbcException.getLocalizedMessage());
    assertNull(actualDbcException.getMessage());
    assertFalse(actualDbcException.hasMessage());
    assertSame(cause, actualDbcException.getCause());
  }

  /**
   * Test {@link DBCException#DBCException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBCException#DBCException(String, Throwable, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBCException_whenNull_thenReturnLocalizedMessageIsNull2() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    DBCException actualDbcException = new DBCException(null, cause, mock(DBPDataSource.class));

    // Assert
    assertNull(actualDbcException.getLocalizedMessage());
    assertNull(actualDbcException.getMessage());
    assertFalse(actualDbcException.hasMessage());
    assertSame(cause, actualDbcException.getCause());
  }

  /**
   * Test {@link DBCException#DBCException(String, Throwable, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBCException#DBCException(String, Throwable, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCException.<init>(String, Throwable, DBCExecutionContext)"})
  public void testNewDBCException_whenNull_thenReturnLocalizedMessageIsNull3() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    DBCException actualDbcException = new DBCException(null, cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertNull(actualDbcException.getLocalizedMessage());
    assertNull(actualDbcException.getMessage());
    assertFalse(actualDbcException.hasMessage());
  }
}
