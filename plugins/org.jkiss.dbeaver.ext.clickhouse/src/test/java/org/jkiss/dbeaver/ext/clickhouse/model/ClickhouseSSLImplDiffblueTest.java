package org.jkiss.dbeaver.ext.clickhouse.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClickhouseSSLImplDiffblueTest {
  /**
   * Test new {@link ClickhouseSSLImpl} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ClickhouseSSLImpl}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClickhouseSSLImpl.<init>()"})
  public void testNewClickhouseSSLImpl() {
    // Arrange, Act and Assert
    assertEquals(0, new ClickhouseSSLImpl().getDependentDataSources().length);
  }
}
