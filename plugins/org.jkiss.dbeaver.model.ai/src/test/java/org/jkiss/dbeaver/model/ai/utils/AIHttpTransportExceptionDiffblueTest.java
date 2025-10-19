package org.jkiss.dbeaver.model.ai.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIHttpTransportExceptionDiffblueTest {
  /**
   * Test {@link AIHttpTransportException#AIHttpTransportException(int, String)}.
   *
   * <p>Method under test: {@link AIHttpTransportException#AIHttpTransportException(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIHttpTransportException.<init>(int, String)"})
  public void testNewAIHttpTransportException() {
    // Arrange and Act
    AIHttpTransportException actualAiHttpTransportException =
        new AIHttpTransportException(1, "https://example.org/example");

    // Assert
    assertEquals(
        "HTTP error: 1 https://example.org/example",
        actualAiHttpTransportException.getLocalizedMessage());
    assertEquals(
        "HTTP error: 1 https://example.org/example", actualAiHttpTransportException.getMessage());
    assertEquals("https://example.org/example", actualAiHttpTransportException.getResponseBody());
    assertNull(actualAiHttpTransportException.getCause());
    assertEquals(0, actualAiHttpTransportException.getSuppressed().length);
    assertEquals(1, actualAiHttpTransportException.getStatusCode());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIHttpTransportException#toString()}
   *   <li>{@link AIHttpTransportException#getResponseBody()}
   *   <li>{@link AIHttpTransportException#getStatusCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AIHttpTransportException.getResponseBody()",
    "int AIHttpTransportException.getStatusCode()",
    "String AIHttpTransportException.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    AIHttpTransportException aiHttpTransportException =
        new AIHttpTransportException(1, "https://example.org/example");

    // Act
    String actualToStringResult = aiHttpTransportException.toString();
    String actualResponseBody = aiHttpTransportException.getResponseBody();

    // Assert
    assertEquals(
        "HttpException{statusCode=1, responseBody='https://example.org/example'}",
        actualToStringResult);
    assertEquals("https://example.org/example", actualResponseBody);
    assertEquals(1, aiHttpTransportException.getStatusCode());
  }
}
