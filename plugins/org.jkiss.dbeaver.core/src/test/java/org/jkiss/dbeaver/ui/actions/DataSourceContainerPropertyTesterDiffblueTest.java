package org.jkiss.dbeaver.ui.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceContainerPropertyTesterDiffblueTest {
  /**
   * Test {@link DataSourceContainerPropertyTester#test(Object, String, Object[], Object)}.
   *
   * <p>Method under test: {@link DataSourceContainerPropertyTester#test(Object, String, Object[],
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DataSourceContainerPropertyTester.test(Object, String, Object[], Object)"
  })
  public void testTest() {
    // Arrange, Act and Assert
    assertFalse(
        new DataSourceContainerPropertyTester()
            .test(DBPEvent.RENAME, "Property", new Object[] {DBPEvent.RENAME}, DBPEvent.RENAME));
  }

  /**
   * Test new {@link DataSourceContainerPropertyTester} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * DataSourceContainerPropertyTester}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceContainerPropertyTester.<init>()"})
  public void testNewDataSourceContainerPropertyTester() {
    // Arrange, Act and Assert
    assertTrue(new DataSourceContainerPropertyTester().isInstantiated());
  }
}
