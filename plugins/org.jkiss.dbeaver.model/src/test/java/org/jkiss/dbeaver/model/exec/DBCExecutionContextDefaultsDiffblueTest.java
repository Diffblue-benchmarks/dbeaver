package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCExecutionContextDefaultsDiffblueTest {
  /**
   * Test {@link DBCExecutionContextDefaults#getCachedDefault()}.
   *
   * <p>Method under test: {@link DBCExecutionContextDefaults#getCachedDefault()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCCachedContextDefaults DBCExecutionContextDefaults.getCachedDefault()"})
  public void testGetCachedDefault() {
    // Arrange and Act
    DBCCachedContextDefaults actualCachedDefault =
        new VoidExecutionContextDefaults().getCachedDefault();

    // Assert
    assertNull(actualCachedDefault.catalogName());
    assertNull(actualCachedDefault.schemaName());
  }
}
