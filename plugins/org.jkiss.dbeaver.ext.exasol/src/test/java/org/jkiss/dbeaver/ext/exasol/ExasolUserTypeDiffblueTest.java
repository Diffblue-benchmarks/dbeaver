package org.jkiss.dbeaver.ext.exasol;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolUserTypeDiffblueTest {
  /**
   * Test {@link ExasolUserType#getName()}.
   *
   * <p>Method under test: {@link ExasolUserType#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ExasolUserType.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("kerberos", ExasolUserType.valueOf("KERBEROS").getName());
  }
}
