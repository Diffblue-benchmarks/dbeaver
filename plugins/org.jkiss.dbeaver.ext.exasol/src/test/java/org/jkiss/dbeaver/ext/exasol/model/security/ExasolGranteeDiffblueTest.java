package org.jkiss.dbeaver.ext.exasol.model.security;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.exasol.model.security.ExasolGrantee.PriorityListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolGranteeDiffblueTest {
  /**
   * Test PriorityListProvider {@link PriorityListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link PriorityListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PriorityListProvider.allowCustomValue()"})
  public void testPriorityListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new PriorityListProvider().allowCustomValue());
  }
}
