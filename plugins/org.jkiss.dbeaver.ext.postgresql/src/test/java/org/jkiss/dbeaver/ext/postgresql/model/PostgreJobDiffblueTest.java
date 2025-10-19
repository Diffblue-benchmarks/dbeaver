package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.postgresql.model.PostgreJob.JobClassListProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreJobDiffblueTest {
  /**
   * Test JobClassListProvider {@link JobClassListProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link JobClassListProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JobClassListProvider.allowCustomValue()"})
  public void testJobClassListProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertFalse(new JobClassListProvider().allowCustomValue());
  }
}
