package org.jkiss.dbeaver.ext.mssql.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.mssql.model.SQLServerExtendedProperty.DataTypeListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerExtendedPropertyDiffblueTest {
  /**
   * Test DataTypeListProvider {@link DataTypeListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link DataTypeListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTypeListProvider.allowCustomValue()"})
  public void testDataTypeListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new DataTypeListProvider().allowCustomValue());
  }
}
