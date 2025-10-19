package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreJobStep.DatabaseListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreJobStepDiffblueTest {
  /**
   * Test DatabaseListProvider {@link DatabaseListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link DatabaseListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseListProvider.allowCustomValue()"})
  public void testDatabaseListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new DatabaseListProvider().allowCustomValue());
  }
}
