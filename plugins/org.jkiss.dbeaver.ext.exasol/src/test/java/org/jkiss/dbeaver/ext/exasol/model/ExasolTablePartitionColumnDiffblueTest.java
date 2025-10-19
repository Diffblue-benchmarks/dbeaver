package org.jkiss.dbeaver.ext.exasol.model;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.exasol.model.ExasolTablePartitionColumn.TableColumListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolTablePartitionColumnDiffblueTest {
  /**
   * Test TableColumListProvider {@link TableColumListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link TableColumListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TableColumListProvider.allowCustomValue()"})
  public void testTableColumListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertTrue(new TableColumListProvider().allowCustomValue());
  }
}
