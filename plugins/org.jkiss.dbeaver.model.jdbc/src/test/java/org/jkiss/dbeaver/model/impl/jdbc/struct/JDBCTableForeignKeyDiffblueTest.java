package org.jkiss.dbeaver.model.impl.jdbc.struct;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCTableForeignKey.ConstraintModifyRuleListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCTableForeignKeyDiffblueTest {
  /**
   * Test ConstraintModifyRuleListProvider {@link
   * ConstraintModifyRuleListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link ConstraintModifyRuleListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ConstraintModifyRuleListProvider.allowCustomValue()"})
  public void testConstraintModifyRuleListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new ConstraintModifyRuleListProvider().allowCustomValue());
  }
}
