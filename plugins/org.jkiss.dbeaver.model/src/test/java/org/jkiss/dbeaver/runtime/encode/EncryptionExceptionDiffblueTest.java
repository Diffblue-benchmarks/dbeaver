package org.jkiss.dbeaver.runtime.encode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EncryptionExceptionDiffblueTest {
  /**
   * Test {@link EncryptionException#EncryptionException(String)}.
   *
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link EncryptionException#EncryptionException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EncryptionException.<init>(String)",
    "void EncryptionException.<init>(Throwable)"
  })
  public void testNewEncryptionException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    EncryptionException actualEncryptionException = new EncryptionException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualEncryptionException.getMessage());
    assertNull(actualEncryptionException.getCause());
    assertEquals(0, actualEncryptionException.getSuppressed().length);
  }

  /**
   * Test {@link EncryptionException#EncryptionException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link EncryptionException#EncryptionException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EncryptionException.<init>(String)",
    "void EncryptionException.<init>(Throwable)"
  })
  public void testNewEncryptionException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable t = new Throwable();

    // Act
    EncryptionException actualEncryptionException = new EncryptionException(t);

    // Assert
    assertEquals("java.lang.Throwable", actualEncryptionException.getMessage());
    assertEquals(0, actualEncryptionException.getSuppressed().length);
    assertSame(t, actualEncryptionException.getCause());
  }
}
