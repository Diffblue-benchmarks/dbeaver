package org.jkiss.dbeaver.ext.exasol.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.exasol.model.ExasolSchema.OwnerListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolSchemaDiffblueTest {
  /**
   * Test OwnerListProvider {@link OwnerListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link OwnerListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OwnerListProvider.allowCustomValue()"})
  public void testOwnerListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new OwnerListProvider().allowCustomValue());
  }
}
