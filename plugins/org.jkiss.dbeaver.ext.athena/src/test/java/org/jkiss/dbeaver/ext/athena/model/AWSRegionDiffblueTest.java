package org.jkiss.dbeaver.ext.athena.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AWSRegionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AWSRegion#getId()}
   *   <li>{@link AWSRegion#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AWSRegion.getId()", "String AWSRegion.getName()"})
  public void testGettersAndSetters() {
    // Arrange
    AWSRegion valueOfResult = AWSRegion.valueOf("us_east_1");

    // Act
    String actualId = valueOfResult.getId();

    // Assert
    assertEquals("US East (N. Virginia)", valueOfResult.getName());
    assertEquals("us-east-1", actualId);
  }
}
