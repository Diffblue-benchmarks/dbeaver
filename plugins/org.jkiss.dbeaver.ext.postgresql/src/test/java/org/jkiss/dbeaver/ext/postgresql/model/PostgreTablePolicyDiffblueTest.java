package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreTablePolicy.RoleListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTablePolicyDiffblueTest {
  /**
   * Test RoleListProvider {@link RoleListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link RoleListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RoleListProvider.allowCustomValue()"})
  public void testRoleListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new RoleListProvider().allowCustomValue());
  }
}
