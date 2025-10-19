package org.jkiss.dbeaver.model.lsm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LSMExceptionDiffblueTest {
  /**
   * Test {@link LSMException#LSMException()}.
   *
   * <ul>
   *   <li>Then return Message is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LSMException#LSMException()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LSMException.<init>()",
    "void LSMException.<init>(String)",
    "void LSMException.<init>(String, Throwable)",
    "void LSMException.<init>(String, Throwable, boolean, boolean)",
    "void LSMException.<init>(Throwable)"
  })
  public void testNewLSMException_thenReturnMessageIsNull() {
    // Arrange and Act
    LSMException actualLsmException = new LSMException();

    // Assert
    assertNull(actualLsmException.getMessage());
    assertNull(actualLsmException.getCause());
    assertEquals(0, actualLsmException.getSuppressed().length);
  }

  /**
   * Test {@link LSMException#LSMException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LSMException#LSMException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LSMException.<init>()",
    "void LSMException.<init>(String)",
    "void LSMException.<init>(String, Throwable)",
    "void LSMException.<init>(String, Throwable, boolean, boolean)",
    "void LSMException.<init>(Throwable)"
  })
  public void testNewLSMException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    LSMException actualLsmException = new LSMException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualLsmException.getMessage());
    assertNull(actualLsmException.getCause());
    assertEquals(0, actualLsmException.getSuppressed().length);
  }

  /**
   * Test {@link LSMException#LSMException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link LSMException#LSMException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LSMException.<init>()",
    "void LSMException.<init>(String)",
    "void LSMException.<init>(String, Throwable)",
    "void LSMException.<init>(String, Throwable, boolean, boolean)",
    "void LSMException.<init>(Throwable)"
  })
  public void testNewLSMException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    LSMException actualLsmException = new LSMException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualLsmException.getMessage());
    assertEquals(0, actualLsmException.getSuppressed().length);
    assertSame(cause, actualLsmException.getCause());
  }

  /**
   * Test {@link LSMException#LSMException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link LSMException#LSMException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LSMException.<init>()",
    "void LSMException.<init>(String)",
    "void LSMException.<init>(String, Throwable)",
    "void LSMException.<init>(String, Throwable, boolean, boolean)",
    "void LSMException.<init>(Throwable)"
  })
  public void testNewLSMException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    LSMException actualLsmException = new LSMException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualLsmException.getMessage());
    assertEquals(0, actualLsmException.getSuppressed().length);
    assertSame(cause, actualLsmException.getCause());
  }

  /**
   * Test {@link LSMException#LSMException(String, Throwable, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link LSMException#LSMException(String, Throwable, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LSMException.<init>()",
    "void LSMException.<init>(String)",
    "void LSMException.<init>(String, Throwable)",
    "void LSMException.<init>(String, Throwable, boolean, boolean)",
    "void LSMException.<init>(Throwable)"
  })
  public void testNewLSMException_whenTrue_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    LSMException actualLsmException = new LSMException("An error occurred", cause, true, true);

    // Assert
    assertEquals("An error occurred", actualLsmException.getMessage());
    assertEquals(0, actualLsmException.getSuppressed().length);
    assertSame(cause, actualLsmException.getCause());
  }
}
