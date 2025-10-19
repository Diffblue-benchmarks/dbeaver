package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTypeStorageDiffblueTest {
  /**
   * Test {@link PostgreTypeStorage#getName()}.
   *
   * <p>Method under test: {@link PostgreTypeStorage#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String PostgreTypeStorage.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("plain", PostgreTypeStorage.valueOf("p").getName());
  }
}
