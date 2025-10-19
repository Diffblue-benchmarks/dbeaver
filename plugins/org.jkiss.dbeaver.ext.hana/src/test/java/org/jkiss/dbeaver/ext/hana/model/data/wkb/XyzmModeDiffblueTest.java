package org.jkiss.dbeaver.ext.hana.model.data.wkb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class XyzmModeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link XyzmMode#getCoordinatesPerPoint()}
   *   <li>{@link XyzmMode#hasM()}
   *   <li>{@link XyzmMode#hasZ()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int XyzmMode.getCoordinatesPerPoint()",
    "boolean XyzmMode.hasM()",
    "boolean XyzmMode.hasZ()"
  })
  public void testGettersAndSetters() {
    // Arrange
    XyzmMode valueOfResult = XyzmMode.valueOf("XY");

    // Act
    int actualCoordinatesPerPoint = valueOfResult.getCoordinatesPerPoint();
    boolean actualHasMResult = valueOfResult.hasM();

    // Assert
    assertEquals(2, actualCoordinatesPerPoint);
    assertFalse(actualHasMResult);
    assertFalse(valueOfResult.hasZ());
  }
}
