package org.jkiss.dbeaver.model.security.exception;

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
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMExceptionDiffblueTest {
  /**
   * Test {@link SMException#SMException(String)}.
   *
   * <p>Method under test: {@link SMException#SMException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMException.<init>(String)"})
  public void testNewSMException() {
    // Arrange and Act
    SMException actualSmException = new SMException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualSmException.getMessage());
    assertNull(actualSmException.getCause());
    assertNull(actualSmException.getExecutionContext());
    assertEquals(0, actualSmException.getSuppressed().length);
    assertTrue(actualSmException.hasMessage());
  }

  /**
   * Test {@link SMException#SMException(Throwable, DBCExecutionContext)}.
   *
   * <p>Method under test: {@link SMException#SMException(Throwable, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMException.<init>(Throwable, DBCExecutionContext)"})
  public void testNewSMException2() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    SMException actualSmException = new SMException(cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertNull(actualSmException.getLocalizedMessage());
    assertNull(actualSmException.getMessage());
    assertEquals(0, actualSmException.getSuppressed().length);
    assertFalse(actualSmException.hasMessage());
    assertSame(cause, actualSmException.getCause());
    assertSame(executionContext, actualSmException.getExecutionContext());
  }

  /**
   * Test {@link SMException#SMException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link SMException#SMException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMException.<init>(String, Throwable)"})
  public void testNewSMException_thenReturnLocalizedMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SMException actualSmException = new SMException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualSmException.getLocalizedMessage());
    assertEquals("An error occurred", actualSmException.getMessage());
    assertTrue(actualSmException.hasMessage());
    assertSame(cause, actualSmException.getCause());
  }

  /**
   * Test {@link SMException#SMException(String, Throwable, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Then return LocalizedMessage is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link SMException#SMException(String, Throwable, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMException.<init>(String, Throwable, DBCExecutionContext)"})
  public void testNewSMException_thenReturnLocalizedMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    SMException actualSmException = new SMException("An error occurred", cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertEquals("An error occurred", actualSmException.getLocalizedMessage());
    assertEquals("An error occurred", actualSmException.getMessage());
    assertTrue(actualSmException.hasMessage());
  }

  /**
   * Test {@link SMException#SMException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SMException#SMException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMException.<init>(String, Throwable)"})
  public void testNewSMException_whenNull_thenReturnLocalizedMessageIsNull() {
    // Arrange
    DBCException cause = new DBCException("An error occurred");

    // Act
    SMException actualSmException = new SMException(null, cause);

    // Assert
    assertNull(actualSmException.getLocalizedMessage());
    assertNull(actualSmException.getMessage());
    assertFalse(actualSmException.hasMessage());
    assertSame(cause, actualSmException.getCause());
  }

  /**
   * Test {@link SMException#SMException(String, Throwable, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SMException#SMException(String, Throwable, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMException.<init>(String, Throwable, DBCExecutionContext)"})
  public void testNewSMException_whenNull_thenReturnLocalizedMessageIsNull2() {
    // Arrange
    Throwable cause = new Throwable();

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    SMException actualSmException = new SMException(null, cause, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    assertNull(actualSmException.getLocalizedMessage());
    assertNull(actualSmException.getMessage());
    assertFalse(actualSmException.hasMessage());
  }
}
