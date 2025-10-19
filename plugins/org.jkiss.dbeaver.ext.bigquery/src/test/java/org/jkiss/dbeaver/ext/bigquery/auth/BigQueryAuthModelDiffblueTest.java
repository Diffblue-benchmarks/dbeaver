package org.jkiss.dbeaver.ext.bigquery.auth;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BigQueryAuthModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link BigQueryAuthModel}
   *   <li>{@link BigQueryAuthModel#isUserNameApplicable()}
   *   <li>{@link BigQueryAuthModel#isUserPasswordApplicable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BigQueryAuthModel.<init>()",
    "boolean BigQueryAuthModel.isUserNameApplicable()",
    "boolean BigQueryAuthModel.isUserPasswordApplicable()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    BigQueryAuthModel actualBigQueryAuthModel = new BigQueryAuthModel();
    boolean actualIsUserNameApplicableResult = actualBigQueryAuthModel.isUserNameApplicable();

    // Assert
    assertFalse(actualIsUserNameApplicableResult);
    assertFalse(actualBigQueryAuthModel.isUserPasswordApplicable());
  }
}
