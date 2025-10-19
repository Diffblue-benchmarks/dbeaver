package org.jkiss.dbeaver.ext.hana.model.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.hana.model.data.wkb.XyzmMode;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

public class HANAXyzmModeFinderDiffblueTest {
  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString g = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode2() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon g = new Polygon(shell, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode3() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection g = new GeometryCollection(geometries, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode4() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3, 1, 1);
    LineString g = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode5() {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point g = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode6() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3, 3, 1);
    LineString g = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XYM, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode7() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {polygon};
    GeometryCollection g = new GeometryCollection(geometries, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode8() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {polygon};
    GeometryCollection g = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    XyzmMode actualFindXyzmModeResult = HANAXyzmModeFinder.findXyzmMode(g);

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(XyzmMode.XYM, actualFindXyzmModeResult);
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link CoordinateSequence} {@link CoordinateSequence#hasZ()} return {@code false}.
   *   <li>Then calls {@link CoordinateSequence#hasM()}.
   * </ul>
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode_givenFalse_whenCoordinateSequenceHasZReturnFalse_thenCallsHasM() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.size()).thenReturn(0);
    when(points.hasM()).thenReturn(true);
    when(points.hasZ()).thenReturn(false);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection g = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    XyzmMode actualFindXyzmModeResult = HANAXyzmModeFinder.findXyzmMode(g);

    // Assert
    verify(points).hasM();
    verify(points).hasZ();
    verify(points, atLeast(1)).size();
    assertEquals(XyzmMode.XYM, actualFindXyzmModeResult);
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link Coordinate#Coordinate()} Z is ten.
   *   <li>Then return {@code XYZ}.
   * </ul>
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode_givenTen_whenCoordinateZIsTen_thenReturnXyz() {
    // Arrange
    Coordinate coordinate = new Coordinate();
    coordinate.setZ(10.0d);
    Point g = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(XyzmMode.XYZ, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link CoordinateSequence} {@link CoordinateSequence#hasZ()} return {@code true}.
   *   <li>Then calls {@link CoordinateSequence#hasM()}.
   * </ul>
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode_givenZero_whenCoordinateSequenceHasZReturnTrue_thenCallsHasM() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.size()).thenReturn(0);
    when(points.hasM()).thenReturn(true);
    when(points.hasZ()).thenReturn(true);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection g = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    XyzmMode actualFindXyzmModeResult = HANAXyzmModeFinder.findXyzmMode(g);

    // Assert
    verify(points).hasM();
    verify(points).hasZ();
    verify(points, atLeast(1)).size();
    assertEquals(XyzmMode.XYM, actualFindXyzmModeResult);
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <ul>
   *   <li>Then return {@code XYZM}.
   * </ul>
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode_thenReturnXyzm() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection g = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    XyzmMode actualFindXyzmModeResult = HANAXyzmModeFinder.findXyzmMode(g);

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(XyzmMode.XYZM, actualFindXyzmModeResult);
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <ul>
   *   <li>When {@link CoordinateArraySequence#CoordinateArraySequence(int, int)} with size is three
   *       and dimension is one.
   * </ul>
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode_whenCoordinateArraySequenceWithSizeIsThreeAndDimensionIsOne() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3, 1);
    LineString g = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XY, HANAXyzmModeFinder.findXyzmMode(g));
  }

  /**
   * Test {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}.
   *
   * <ul>
   *   <li>When {@link LineString#LineString(CoordinateSequence, GeometryFactory)} with points is
   *       {@code null} and factory is {@link GeometryFactory#GeometryFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link HANAXyzmModeFinder#findXyzmMode(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"XyzmMode HANAXyzmModeFinder.findXyzmMode(Geometry)"})
  public void testFindXyzmMode_whenLineStringWithPointsIsNullAndFactoryIsGeometryFactory() {
    // Arrange
    LineString g = new LineString(null, new GeometryFactory());

    // Act and Assert
    assertEquals(XyzmMode.XYZ, HANAXyzmModeFinder.findXyzmMode(g));
  }
}
