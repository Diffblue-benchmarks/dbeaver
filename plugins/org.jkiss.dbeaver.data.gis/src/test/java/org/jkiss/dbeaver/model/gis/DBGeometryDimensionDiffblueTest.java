package org.jkiss.dbeaver.model.gis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBGeometryDimensionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBGeometryDimension#getCoordinates()}
   *   <li>{@link DBGeometryDimension#hasM()}
   *   <li>{@link DBGeometryDimension#hasZ()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int DBGeometryDimension.getCoordinates()",
    "boolean DBGeometryDimension.hasM()",
    "boolean DBGeometryDimension.hasZ()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBGeometryDimension valueOfResult = DBGeometryDimension.valueOf("XY");

    // Act
    int actualCoordinates = valueOfResult.getCoordinates();
    boolean actualHasMResult = valueOfResult.hasM();

    // Assert
    assertEquals(2, actualCoordinates);
    assertFalse(actualHasMResult);
    assertFalse(valueOfResult.hasZ());
  }
}
