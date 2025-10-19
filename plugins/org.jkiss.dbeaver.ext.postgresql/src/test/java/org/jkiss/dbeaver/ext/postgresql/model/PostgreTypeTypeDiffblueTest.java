package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTypeTypeDiffblueTest {
  /**
   * Test {@link PostgreTypeType#getName()}.
   *
   * <p>Method under test: {@link PostgreTypeType#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String PostgreTypeType.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("Abstract", PostgreTypeType.valueOf("a").getName());
  }
}
