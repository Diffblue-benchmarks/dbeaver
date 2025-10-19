package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIPromptUtilsDiffblueTest {
  /**
   * Test {@link AIPromptUtils#describeDataSourceInfo(DBSLogicalDataSource)}.
   *
   * <p>Method under test: {@link AIPromptUtils#describeDataSourceInfo(DBSLogicalDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] AIPromptUtils.describeDataSourceInfo(DBSLogicalDataSource)"})
  public void testDescribeDataSourceInfo() {
    // Arrange and Act
    String[] actualDescribeDataSourceInfoResult = AIPromptUtils.describeDataSourceInfo(null);

    // Assert
    assertEquals(2, actualDescribeDataSourceInfoResult.length);
  }
}
