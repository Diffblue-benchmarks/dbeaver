package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPMessageTypeDiffblueTest {
  /**
   * Test {@link DBPMessageType#getStatusCode()}.
   *
   * <p>Method under test: {@link DBPMessageType#getStatusCode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBPMessageType.getStatusCode()"})
  public void testGetStatusCode() {
    // Arrange, Act and Assert
    assertEquals(1, DBPMessageType.valueOf("INFORMATION").getStatusCode());
  }
}
