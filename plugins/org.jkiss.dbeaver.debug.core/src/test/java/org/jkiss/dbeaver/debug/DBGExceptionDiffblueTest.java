package org.jkiss.dbeaver.debug;

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

public class DBGExceptionDiffblueTest {
  /**
   * Test {@link DBGException#DBGException(String)}.
   *
   * <p>Method under test: {@link DBGException#DBGException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGException.<init>(String)"})
  public void testNewDBGException() {
    // Arrange and Act
    DBGException actualDbgException = new DBGException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbgException.getMessage());
    assertNull(actualDbgException.getCause());
    assertEquals(0, actualDbgException.getSuppressed().length);
    assertTrue(actualDbgException.hasMessage());
  }

  /**
   * Test {@link DBGException#DBGException(Throwable, DBPDataSource)}.
   *
   * <p>Method under test: {@link DBGException#DBGException(Throwable, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGException.<init>(Throwable, DBPDataSource)"})
  public void testNewDBGException2() {
    // Arrange
    Throwable cause = new Throwable();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    DBGException actualDbgException = new DBGException(cause, dataSource);

    // Assert
    assertNull(actualDbgException.getLocalizedMessage());
    assertNull(actualDbgException.getMessage());
    assertEquals(0, actualDbgException.getSuppressed().length);
    assertFalse(actualDbgException.hasMessage());
    assertSame(cause, actualDbgException.getCause());
    assertSame(dataSource, actualDbgException.getDataSource());
  }

  /**
   * Test {@link DBGException#DBGException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBGException#DBGException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGException.<init>(String, Throwable)"})
  public void testNewDBGException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange and Act
    DBGException actualDbgException = new DBGException("An error occurred", new Throwable());

    // Assert
    assertEquals("An error occurred", actualDbgException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbgException.getMessage());
    assertTrue(actualDbgException.hasMessage());
  }

  /**
   * Test {@link DBGException#DBGException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBGException#DBGException(String, Throwable, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBGException_thenReturnLocalizedMessageIsAnErrorOccurred2() {
    // Arrange and Act
    DBGException actualDbgException =
        new DBGException("An error occurred", new Throwable(), mock(DBPDataSource.class));

    // Assert
    assertEquals("An error occurred", actualDbgException.getLocalizedMessage());
    assertEquals("An error occurred", actualDbgException.getMessage());
    assertTrue(actualDbgException.hasMessage());
  }

  /**
   * Test {@link DBGException#DBGException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGException#DBGException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGException.<init>(String, Throwable)"})
  public void testNewDBGException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange and Act
    DBGException actualDbgException = new DBGException(null, new Throwable());

    // Assert
    assertNull(actualDbgException.getLocalizedMessage());
    assertNull(actualDbgException.getMessage());
    assertFalse(actualDbgException.hasMessage());
  }

  /**
   * Test {@link DBGException#DBGException(String, Throwable, DBPDataSource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGException#DBGException(String, Throwable, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGException.<init>(String, Throwable, DBPDataSource)"})
  public void testNewDBGException_whenNull_thenReturnLocalizedMessageIsNull2() {
    // Arrange and Act
    DBGException actualDbgException =
        new DBGException(null, new Throwable(), mock(DBPDataSource.class));

    // Assert
    assertNull(actualDbgException.getLocalizedMessage());
    assertNull(actualDbgException.getMessage());
    assertFalse(actualDbgException.hasMessage());
  }
}
