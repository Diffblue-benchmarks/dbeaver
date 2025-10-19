package org.jkiss.dbeaver.ext.spanner.auth;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SpannerAuthModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SpannerAuthModel}
   *   <li>{@link SpannerAuthModel#isUserNameApplicable()}
   *   <li>{@link SpannerAuthModel#isUserPasswordApplicable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SpannerAuthModel.<init>()",
    "boolean SpannerAuthModel.isUserNameApplicable()",
    "boolean SpannerAuthModel.isUserPasswordApplicable()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SpannerAuthModel actualSpannerAuthModel = new SpannerAuthModel();
    boolean actualIsUserNameApplicableResult = actualSpannerAuthModel.isUserNameApplicable();

    // Assert
    assertFalse(actualIsUserNameApplicableResult);
    assertFalse(actualSpannerAuthModel.isUserPasswordApplicable());
  }
}
