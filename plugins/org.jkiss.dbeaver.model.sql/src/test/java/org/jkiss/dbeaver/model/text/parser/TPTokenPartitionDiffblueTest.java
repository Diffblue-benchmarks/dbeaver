package org.jkiss.dbeaver.model.text.parser;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TPTokenPartitionDiffblueTest {
  /**
   * Test {@link TPTokenPartition#TPTokenPartition(String)}.
   *
   * <p>Method under test: {@link TPTokenPartition#TPTokenPartition(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPTokenPartition.<init>(String)"})
  public void testNewTPTokenPartition() {
    // Arrange, Act and Assert
    assertEquals("text/plain", new TPTokenPartition("text/plain").getData());
  }
}
