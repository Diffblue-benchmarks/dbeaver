package org.jkiss.dbeaver.ext.hana.model.data.wkb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HANAWKBWriterExceptionDiffblueTest {
  /**
   * Test {@link HANAWKBWriterException#HANAWKBWriterException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriterException#HANAWKBWriterException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HANAWKBWriterException.<init>(String)",
    "void HANAWKBWriterException.<init>(String, Throwable)",
    "void HANAWKBWriterException.<init>(Throwable)"
  })
  public void testNewHANAWKBWriterException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    HANAWKBWriterException actualHanawkbWriterException =
        new HANAWKBWriterException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualHanawkbWriterException.getMessage());
    assertEquals(0, actualHanawkbWriterException.getSuppressed().length);
    assertSame(cause, actualHanawkbWriterException.getCause());
  }

  /**
   * Test {@link HANAWKBWriterException#HANAWKBWriterException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriterException#HANAWKBWriterException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HANAWKBWriterException.<init>(String)",
    "void HANAWKBWriterException.<init>(String, Throwable)",
    "void HANAWKBWriterException.<init>(Throwable)"
  })
  public void testNewHANAWKBWriterException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    HANAWKBWriterException actualHanawkbWriterException =
        new HANAWKBWriterException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualHanawkbWriterException.getMessage());
    assertNull(actualHanawkbWriterException.getCause());
    assertEquals(0, actualHanawkbWriterException.getSuppressed().length);
  }

  /**
   * Test {@link HANAWKBWriterException#HANAWKBWriterException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriterException#HANAWKBWriterException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HANAWKBWriterException.<init>(String)",
    "void HANAWKBWriterException.<init>(String, Throwable)",
    "void HANAWKBWriterException.<init>(Throwable)"
  })
  public void testNewHANAWKBWriterException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    HANAWKBWriterException actualHanawkbWriterException = new HANAWKBWriterException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualHanawkbWriterException.getMessage());
    assertEquals(0, actualHanawkbWriterException.getSuppressed().length);
    assertSame(cause, actualHanawkbWriterException.getCause());
  }
}
