package org.jkiss.dbeaver.registry.maven.versioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InvalidVersionSpecificationExceptionDiffblueTest {
  /**
   * Test {@link InvalidVersionSpecificationException#InvalidVersionSpecificationException(String)}.
   *
   * <p>Method under test: {@link
   * InvalidVersionSpecificationException#InvalidVersionSpecificationException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InvalidVersionSpecificationException.<init>(String)"})
  public void testNewInvalidVersionSpecificationException() {
    // Arrange and Act
    InvalidVersionSpecificationException actualInvalidVersionSpecificationException =
        new InvalidVersionSpecificationException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualInvalidVersionSpecificationException.getMessage());
    assertNull(actualInvalidVersionSpecificationException.getCause());
    assertEquals(0, actualInvalidVersionSpecificationException.getSuppressed().length);
  }
}
