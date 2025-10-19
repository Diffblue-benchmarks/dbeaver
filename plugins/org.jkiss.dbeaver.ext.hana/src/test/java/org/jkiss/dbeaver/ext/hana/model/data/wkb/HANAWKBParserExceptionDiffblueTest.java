package org.jkiss.dbeaver.ext.hana.model.data.wkb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HANAWKBParserExceptionDiffblueTest {
  /**
   * Test {@link HANAWKBParserException#HANAWKBParserException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParserException#HANAWKBParserException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HANAWKBParserException.<init>(String)",
    "void HANAWKBParserException.<init>(String, Throwable)",
    "void HANAWKBParserException.<init>(Throwable)"
  })
  public void testNewHANAWKBParserException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    HANAWKBParserException actualHanawkbParserException =
        new HANAWKBParserException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualHanawkbParserException.getMessage());
    assertEquals(0, actualHanawkbParserException.getSuppressed().length);
    assertSame(cause, actualHanawkbParserException.getCause());
  }

  /**
   * Test {@link HANAWKBParserException#HANAWKBParserException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParserException#HANAWKBParserException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HANAWKBParserException.<init>(String)",
    "void HANAWKBParserException.<init>(String, Throwable)",
    "void HANAWKBParserException.<init>(Throwable)"
  })
  public void testNewHANAWKBParserException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    HANAWKBParserException actualHanawkbParserException =
        new HANAWKBParserException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualHanawkbParserException.getMessage());
    assertNull(actualHanawkbParserException.getCause());
    assertEquals(0, actualHanawkbParserException.getSuppressed().length);
  }

  /**
   * Test {@link HANAWKBParserException#HANAWKBParserException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBParserException#HANAWKBParserException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HANAWKBParserException.<init>(String)",
    "void HANAWKBParserException.<init>(String, Throwable)",
    "void HANAWKBParserException.<init>(Throwable)"
  })
  public void testNewHANAWKBParserException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    HANAWKBParserException actualHanawkbParserException = new HANAWKBParserException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualHanawkbParserException.getMessage());
    assertEquals(0, actualHanawkbParserException.getSuppressed().length);
    assertSame(cause, actualHanawkbParserException.getCause());
  }
}
