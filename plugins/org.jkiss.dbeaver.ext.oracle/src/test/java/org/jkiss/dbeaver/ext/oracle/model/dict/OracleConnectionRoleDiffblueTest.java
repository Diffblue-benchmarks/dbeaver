package org.jkiss.dbeaver.ext.oracle.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleConnectionRoleDiffblueTest {
  /**
   * Test {@link OracleConnectionRole#getTitle()}.
   *
   * <p>Method under test: {@link OracleConnectionRole#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleConnectionRole.getTitle()"})
  public void testGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Normal", OracleConnectionRole.valueOf("NORMAL").getTitle());
  }
}
