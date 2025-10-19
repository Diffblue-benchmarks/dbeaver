package org.jkiss.dbeaver.ext.mssql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerSSLHandlerImplDiffblueTest {
  /**
   * Test new {@link SQLServerSSLHandlerImpl} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLServerSSLHandlerImpl}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLServerSSLHandlerImpl.<init>()"})
  public void testNewSQLServerSSLHandlerImpl() {
    // Arrange, Act and Assert
    assertEquals(0, new SQLServerSSLHandlerImpl().getDependentDataSources().length);
  }
}
