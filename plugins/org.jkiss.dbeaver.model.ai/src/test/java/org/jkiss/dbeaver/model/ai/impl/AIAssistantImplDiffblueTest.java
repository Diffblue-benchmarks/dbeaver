package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.ai.engine.TooManyRequestsException;
import org.jkiss.dbeaver.model.ai.utils.ThrowableSupplier;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIAssistantImplDiffblueTest {
  /**
   * Test {@link AIAssistantImpl#callWithRetry(ThrowableSupplier)}.
   *
   * <ul>
   *   <li>Given {@link DBException#DBException(String)} with message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantImpl#callWithRetry(ThrowableSupplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AIAssistantImpl.callWithRetry(ThrowableSupplier)"})
  public void testCallWithRetry_givenDBExceptionWithMessageIsAnErrorOccurred() throws Exception {
    // Arrange
    ThrowableSupplier<Object, DBException> supplier = mock(ThrowableSupplier.class);
    when(supplier.get()).thenThrow(new DBException("An error occurred"));

    // Act and Assert
    assertThrows(DBException.class, () -> AIAssistantImpl.callWithRetry(supplier));
    verify(supplier).get();
  }

  /**
   * Test {@link AIAssistantImpl#callWithRetry(ThrowableSupplier)}.
   *
   * <ul>
   *   <li>Given {@code Get}.
   *   <li>When {@link ThrowableSupplier} {@link ThrowableSupplier#get()} return {@code Get}.
   *   <li>Then return {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantImpl#callWithRetry(ThrowableSupplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AIAssistantImpl.callWithRetry(ThrowableSupplier)"})
  public void testCallWithRetry_givenGet_whenThrowableSupplierGetReturnGet_thenReturnGet()
      throws Exception {
    // Arrange
    ThrowableSupplier<Object, DBException> supplier = mock(ThrowableSupplier.class);
    when(supplier.get()).thenReturn("Get");

    // Act
    Object actualCallWithRetryResult = AIAssistantImpl.callWithRetry(supplier);

    // Assert
    verify(supplier).get();
    assertEquals("Get", actualCallWithRetryResult);
  }

  /**
   * Test {@link AIAssistantImpl#callWithRetry(ThrowableSupplier)}.
   *
   * <ul>
   *   <li>Given {@link TooManyRequestsException#TooManyRequestsException(String)} with message is
   *       {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link AIAssistantImpl#callWithRetry(ThrowableSupplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AIAssistantImpl.callWithRetry(ThrowableSupplier)"})
  public void testCallWithRetry_givenTooManyRequestsExceptionWithMessageIsAnErrorOccurred()
      throws Exception {
    // Arrange
    ThrowableSupplier<Object, DBException> supplier = mock(ThrowableSupplier.class);
    when(supplier.get()).thenThrow(new TooManyRequestsException("An error occurred"));

    // Act and Assert
    assertThrows(DBException.class, () -> AIAssistantImpl.callWithRetry(supplier));
    verify(supplier, atLeast(1)).get();
  }
}
