package org.jkiss.dbeaver;

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

public class DBDatabaseExceptionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDatabaseException#DBDatabaseException(String)}
   *   <li>{@link DBDatabaseException#hasMessage()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDatabaseException.<init>(String)",
    "boolean DBDatabaseException.hasMessage()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBDatabaseException actualDbDatabaseException = new DBDatabaseException("An error occurred");
    boolean actualHasMessageResult = actualDbDatabaseException.hasMessage();

    // Assert
    assertEquals("An error occurred", actualDbDatabaseException.getMessage());
    assertNull(actualDbDatabaseException.getCause());
    assertEquals(0, actualDbDatabaseException.getSuppressed().length);
    assertTrue(actualHasMessageResult);
  }

  /**
   * Test {@link DBDatabaseException#DBDatabaseException(Throwable, DBPDataSource)}.
   *
   * <p>Method under test: {@link DBDatabaseException#DBDatabaseException(Throwable, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDatabaseException.<init>(Throwable, DBPDataSource)"})
  public void testNewDBDatabaseException() {
    // Arrange
    Throwable cause = new Throwable();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    DBDatabaseException actualDbDatabaseException = new DBDatabaseException(cause, dataSource);

    // Assert
    assertNull(actualDbDatabaseException.getLocalizedMessage());
    assertNull(actualDbDatabaseException.getMessage());
    assertEquals(0, actualDbDatabaseException.getSuppressed().length);
    assertFalse(actualDbDatabaseException.hasMessage());
    assertSame(cause, actualDbDatabaseException.getCause());
    assertSame(dataSource, actualDbDatabaseException.getDataSource());
  }

  /**
   * Test {@link DBDatabaseException#DBDatabaseException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBDatabaseException#DBDatabaseException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDatabaseException.<init>(String, Throwable)"})
  public void testNewDBDatabaseException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange and Act
    DBDatabaseException actualDbDatabaseException =
        new DBDatabaseException("An error occurred", new Throwable());

    // Assert
    assertEquals("An error occurred", actualDbDatabaseException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbDatabaseException.getMessage());
    assertTrue(actualDbDatabaseException.hasMessage());
  }

  /**
   * Test {@link DBDatabaseException#DBDatabaseException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBDatabaseException#DBDatabaseException(String, Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDatabaseException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBDatabaseException_thenReturnLocalizedMessageIsAnErrorOccurred2() {
    // Arrange and Act
    DBDatabaseException actualDbDatabaseException =
        new DBDatabaseException("An error occurred", new Throwable(), mock(DBPDataSource.class));

    // Assert
    assertEquals("An error occurred", actualDbDatabaseException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbDatabaseException.getMessage());
    assertTrue(actualDbDatabaseException.hasMessage());
  }

  /**
   * Test {@link DBDatabaseException#DBDatabaseException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDatabaseException#DBDatabaseException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDatabaseException.<init>(String, Throwable)"})
  public void testNewDBDatabaseException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange and Act
    DBDatabaseException actualDbDatabaseException = new DBDatabaseException(null, new Throwable());

    // Assert
    assertNull(actualDbDatabaseException.getLocalizedMessage());
    assertNull(actualDbDatabaseException.getMessage());
    assertFalse(actualDbDatabaseException.hasMessage());
  }

  /**
   * Test {@link DBDatabaseException#DBDatabaseException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDatabaseException#DBDatabaseException(String, Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDatabaseException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBDatabaseException_whenNull_thenReturnLocalizedMessageIsNull2() {
    // Arrange and Act
    DBDatabaseException actualDbDatabaseException =
        new DBDatabaseException(null, new Throwable(), mock(DBPDataSource.class));

    // Assert
    assertNull(actualDbDatabaseException.getLocalizedMessage());
    assertNull(actualDbDatabaseException.getMessage());
    assertFalse(actualDbDatabaseException.hasMessage());
  }

  /**
   * Test {@link DBDatabaseException#getDataSource()}.
   *
   * <p>Method under test: {@link DBDatabaseException#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBDatabaseException.getDataSource()"})
  public void testGetDataSource() {
    // Arrange, Act and Assert
    assertNull(new DBDatabaseException("An error occurred").getDataSource());
  }
}
