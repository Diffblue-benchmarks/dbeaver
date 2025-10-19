package org.jkiss.dbeaver.ext.exasol.model.security;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolTableObjectTypeDiffblueTest {
  /**
   * Test {@link ExasolTableObjectType#getDescription()}.
   *
   * <p>Method under test: {@link ExasolTableObjectType#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ExasolTableObjectType.getDescription()"})
  public void testGetDescription() {
    // Arrange, Act and Assert
    assertEquals("VIEW", ExasolTableObjectType.valueOf("VIEW").getDescription());
  }
}
