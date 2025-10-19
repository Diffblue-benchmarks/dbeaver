package org.jkiss.dbeaver.ext.exasol;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolSysTablePrefixDiffblueTest {
  /**
   * Test {@link ExasolSysTablePrefix#toString()}.
   *
   * <p>Method under test: {@link ExasolSysTablePrefix#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ExasolSysTablePrefix.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("EXA_SESSION", ExasolSysTablePrefix.valueOf("SESSION").toString());
  }
}
