package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTypeCategoryDiffblueTest {
  /**
   * Test {@link PostgreTypeCategory#getName()}.
   *
   * <p>Method under test: {@link PostgreTypeCategory#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String PostgreTypeCategory.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("Array", PostgreTypeCategory.valueOf("A").getName());
  }
}
