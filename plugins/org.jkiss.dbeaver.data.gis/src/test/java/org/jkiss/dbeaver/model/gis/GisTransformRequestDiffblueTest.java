package org.jkiss.dbeaver.model.gis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

public class GisTransformRequestDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link GisTransformRequest#GisTransformRequest(Geometry, int, int)}
   *   <li>{@link GisTransformRequest#setShowOnMap(boolean)}
   *   <li>{@link GisTransformRequest#setTargetSRID(int)}
   *   <li>{@link GisTransformRequest#getSourceSRID()}
   *   <li>{@link GisTransformRequest#getSourceValue()}
   *   <li>{@link GisTransformRequest#getTargetSRID()}
   *   <li>{@link GisTransformRequest#getTargetValue()}
   *   <li>{@link GisTransformRequest#isShowOnMap()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GisTransformRequest.<init>(Geometry, int, int)",
    "int GisTransformRequest.getSourceSRID()",
    "Geometry GisTransformRequest.getSourceValue()",
    "int GisTransformRequest.getTargetSRID()",
    "Geometry GisTransformRequest.getTargetValue()",
    "boolean GisTransformRequest.isShowOnMap()",
    "void GisTransformRequest.setShowOnMap(boolean)",
    "void GisTransformRequest.setTargetSRID(int)"
  })
  public void testGettersAndSetters() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString sourceValue = new LineString(points, new GeometryFactory());

    // Act
    GisTransformRequest actualGisTransformRequest = new GisTransformRequest(sourceValue, 1, 1);
    actualGisTransformRequest.setShowOnMap(true);
    actualGisTransformRequest.setTargetSRID(1);
    int actualSourceSRID = actualGisTransformRequest.getSourceSRID();
    Geometry actualSourceValue = actualGisTransformRequest.getSourceValue();
    int actualTargetSRID = actualGisTransformRequest.getTargetSRID();
    Geometry actualTargetValue = actualGisTransformRequest.getTargetValue();

    // Assert
    assertNull(actualTargetValue);
    assertEquals(1, actualSourceSRID);
    assertEquals(1, actualTargetSRID);
    assertTrue(actualGisTransformRequest.isShowOnMap());
    assertSame(sourceValue, actualSourceValue);
  }

  /**
   * Test {@link GisTransformRequest#setTargetValue(Geometry)}.
   *
   * <p>Method under test: {@link GisTransformRequest#setTargetValue(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GisTransformRequest.setTargetValue(Geometry)"})
  public void testSetTargetValue() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString sourceValue = new LineString(points, new GeometryFactory());
    GisTransformRequest gisTransformRequest = new GisTransformRequest(sourceValue, 1, 1);

    // Act
    gisTransformRequest.setTargetValue(null);

    // Assert
    assertEquals(0, gisTransformRequest.getTargetSRID());
  }
}
