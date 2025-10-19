package org.jkiss.dbeaver.ext.postgresql.model.net;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreSSLHandlerImplDiffblueTest {
  /**
   * Test new {@link PostgreSSLHandlerImpl} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PostgreSSLHandlerImpl}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgreSSLHandlerImpl.<init>()"})
  public void testNewPostgreSSLHandlerImpl() {
    // Arrange, Act and Assert
    assertEquals(0, new PostgreSSLHandlerImpl().getDependentDataSources().length);
  }
}
