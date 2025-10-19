package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTypeAlignDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostgreTypeAlign#getBytes()}
   *   <li>{@link PostgreTypeAlign#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int PostgreTypeAlign.getBytes()",
    "java.lang.String PostgreTypeAlign.getName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PostgreTypeAlign valueOfResult = PostgreTypeAlign.valueOf("c");

    // Act
    int actualBytes = valueOfResult.getBytes();

    // Assert
    assertEquals("char", valueOfResult.getName());
    assertEquals(1, actualBytes);
  }
}
