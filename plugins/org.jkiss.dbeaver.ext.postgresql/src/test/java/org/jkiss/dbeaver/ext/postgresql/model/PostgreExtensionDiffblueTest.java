package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreExtension.SchemaListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreExtensionDiffblueTest {
  /**
   * Test SchemaListProvider {@link SchemaListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link SchemaListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SchemaListProvider.allowCustomValue()"})
  public void testSchemaListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new SchemaListProvider().allowCustomValue());
  }
}
