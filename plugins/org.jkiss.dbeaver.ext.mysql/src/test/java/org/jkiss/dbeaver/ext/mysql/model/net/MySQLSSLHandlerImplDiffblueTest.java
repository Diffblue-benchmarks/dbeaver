package org.jkiss.dbeaver.ext.mysql.model.net;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLSSLHandlerImplDiffblueTest {
  /**
   * Test new {@link MySQLSSLHandlerImpl} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link MySQLSSLHandlerImpl}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLSSLHandlerImpl.<init>()"})
  public void testNewMySQLSSLHandlerImpl() {
    // Arrange, Act and Assert
    assertEquals(0, new MySQLSSLHandlerImpl().getDependentDataSources().length);
  }
}
