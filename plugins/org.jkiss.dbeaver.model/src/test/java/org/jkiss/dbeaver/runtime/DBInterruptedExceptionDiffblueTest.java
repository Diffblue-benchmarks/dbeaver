package org.jkiss.dbeaver.runtime;

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

public class DBInterruptedExceptionDiffblueTest {
  /**
   * Test {@link DBInterruptedException#DBInterruptedException(String)}.
   *
   * <p>Method under test: {@link DBInterruptedException#DBInterruptedException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBInterruptedException.<init>(String)"})
  public void testNewDBInterruptedException() {
    // Arrange and Act
    DBInterruptedException actualDbInterruptedException =
        new DBInterruptedException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbInterruptedException.getMessage());
    assertNull(actualDbInterruptedException.getCause());
    assertEquals(0, actualDbInterruptedException.getSuppressed().length);
    assertTrue(actualDbInterruptedException.hasMessage());
  }

  /**
   * Test {@link DBInterruptedException#DBInterruptedException(Throwable, DBPDataSource)}.
   *
   * <p>Method under test: {@link DBInterruptedException#DBInterruptedException(Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBInterruptedException.<init>(Throwable, DBPDataSource)"})
  public void testNewDBInterruptedException2() {
    // Arrange
    Throwable cause = new Throwable();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    DBInterruptedException actualDbInterruptedException =
        new DBInterruptedException(cause, dataSource);

    // Assert
    assertNull(actualDbInterruptedException.getLocalizedMessage());
    assertNull(actualDbInterruptedException.getMessage());
    assertEquals(0, actualDbInterruptedException.getSuppressed().length);
    assertFalse(actualDbInterruptedException.hasMessage());
    assertSame(cause, actualDbInterruptedException.getCause());
    assertSame(dataSource, actualDbInterruptedException.getDataSource());
  }

  /**
   * Test {@link DBInterruptedException#DBInterruptedException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBInterruptedException#DBInterruptedException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBInterruptedException.<init>(String, Throwable)"})
  public void testNewDBInterruptedException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange and Act
    DBInterruptedException actualDbInterruptedException =
        new DBInterruptedException("An error occurred", new Throwable());

    // Assert
    assertEquals("An error occurred", actualDbInterruptedException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbInterruptedException.getMessage());
    assertTrue(actualDbInterruptedException.hasMessage());
  }

  /**
   * Test {@link DBInterruptedException#DBInterruptedException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBInterruptedException#DBInterruptedException(String, Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBInterruptedException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBInterruptedException_thenReturnLocalizedMessageIsAnErrorOccurred2() {
    // Arrange and Act
    DBInterruptedException actualDbInterruptedException =
        new DBInterruptedException("An error occurred", new Throwable(), mock(DBPDataSource.class));

    // Assert
    assertEquals("An error occurred", actualDbInterruptedException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbInterruptedException.getMessage());
    assertTrue(actualDbInterruptedException.hasMessage());
  }

  /**
   * Test {@link DBInterruptedException#DBInterruptedException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBInterruptedException#DBInterruptedException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBInterruptedException.<init>(String, Throwable)"})
  public void testNewDBInterruptedException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange and Act
    DBInterruptedException actualDbInterruptedException =
        new DBInterruptedException(null, new Throwable());

    // Assert
    assertNull(actualDbInterruptedException.getLocalizedMessage());
    assertNull(actualDbInterruptedException.getMessage());
    assertFalse(actualDbInterruptedException.hasMessage());
  }

  /**
   * Test {@link DBInterruptedException#DBInterruptedException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBInterruptedException#DBInterruptedException(String, Throwable,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBInterruptedException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBInterruptedException_whenNull_thenReturnLocalizedMessageIsNull2() {
    // Arrange and Act
    DBInterruptedException actualDbInterruptedException =
        new DBInterruptedException(null, new Throwable(), mock(DBPDataSource.class));

    // Assert
    assertNull(actualDbInterruptedException.getLocalizedMessage());
    assertNull(actualDbInterruptedException.getMessage());
    assertFalse(actualDbInterruptedException.hasMessage());
  }
}
