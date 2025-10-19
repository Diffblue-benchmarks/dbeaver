package org.jkiss.dbeaver.runtime.net;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.LanguageCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultCallbackHandlerDiffblueTest {
  /**
   * Test {@link DefaultCallbackHandler#handle(Callback[])}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedCallbackException}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCallbackHandler#handle(Callback[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultCallbackHandler.handle(Callback[])"})
  public void testHandle_thenThrowUnsupportedCallbackException()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    DefaultCallbackHandler defaultCallbackHandler = new DefaultCallbackHandler();

    // Act and Assert
    assertThrows(
        UnsupportedCallbackException.class,
        () -> defaultCallbackHandler.handle(new Callback[] {new LanguageCallback()}));
  }

  /**
   * Test {@link DefaultCallbackHandler#handle(Callback[])}.
   *
   * <ul>
   *   <li>When empty array of {@link Callback}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DefaultCallbackHandler#handle(Callback[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultCallbackHandler.handle(Callback[])"})
  public void testHandle_whenEmptyArrayOfCallback_thenDoesNotThrow()
      throws IOException, UnsupportedCallbackException {
    // Arrange, Act and Assert
    new DefaultCallbackHandler().handle(new Callback[] {});
  }
}
