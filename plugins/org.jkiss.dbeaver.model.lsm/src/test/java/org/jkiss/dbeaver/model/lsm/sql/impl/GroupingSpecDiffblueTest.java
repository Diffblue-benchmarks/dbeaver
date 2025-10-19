package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.lsm.sql.impl.GroupingSpec.GroupingColumnSpec;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GroupingSpecDiffblueTest {
  /**
   * Test GroupingColumnSpec new {@link GroupingColumnSpec} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link GroupingColumnSpec}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GroupingColumnSpec.<init>()"})
  public void testGroupingColumnSpecNewGroupingColumnSpec() {
    // Arrange and Act
    GroupingColumnSpec actualGroupingColumnSpec = new GroupingColumnSpec();

    // Assert
    assertNull(actualGroupingColumnSpec.collation);
    assertNull(actualGroupingColumnSpec.column);
  }

  /**
   * Test new {@link GroupingSpec} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link GroupingSpec}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GroupingSpec.<init>()"})
  public void testNewGroupingSpec() {
    // Arrange, Act and Assert
    assertNull(new GroupingSpec().columns);
  }
}
