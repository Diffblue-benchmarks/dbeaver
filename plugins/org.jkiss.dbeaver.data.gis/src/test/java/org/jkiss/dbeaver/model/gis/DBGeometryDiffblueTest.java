package org.jkiss.dbeaver.model.gis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.CoordinateXYM;
import org.locationtech.jts.geom.CoordinateXYZM;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

public class DBGeometryDiffblueTest {
  /**
   * Test {@link DBGeometry#DBGeometry()}.
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>()"})
  public void testNewDBGeometry() {
    // Arrange and Act
    DBGeometry actualDbGeometry = new DBGeometry();

    // Assert
    assertNull(actualDbGeometry.getRawValue());
    assertNull(actualDbGeometry.getString());
    assertNull(actualDbGeometry.getProperties());
    assertNull(actualDbGeometry.getGeometry());
    assertEquals(0, actualDbGeometry.getSRID());
    assertFalse(actualDbGeometry.isEmpty());
    assertFalse(actualDbGeometry.isModified());
    assertTrue(actualDbGeometry.isNull());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(Object, int)}.
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(Object, int)"})
  public void testNewDBGeometry2() {
    // Arrange and Act
    DBGeometry actualDbGeometry = new DBGeometry("Raw Value", 1);

    // Assert
    assertEquals("Raw Value", actualDbGeometry.getString());
    assertEquals("Raw Value", actualDbGeometry.getRawValue());
    assertNull(actualDbGeometry.getProperties());
    assertNull(actualDbGeometry.getGeometry());
    assertEquals(1, actualDbGeometry.getSRID());
    assertFalse(actualDbGeometry.isEmpty());
    assertFalse(actualDbGeometry.isModified());
    assertFalse(actualDbGeometry.isNull());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(DBGeometry)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>Then Geometry return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(DBGeometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(DBGeometry)"})
  public void testNewDBGeometry_givenHashMap_thenGeometryReturnLineString() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    DBGeometry source = new DBGeometry(rawValue);
    source.setProperties(new HashMap<>());

    // Act
    DBGeometry actualDbGeometry = new DBGeometry(source);

    // Assert
    Geometry geometry = actualDbGeometry.getGeometry();
    assertTrue(geometry instanceof LineString);
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", actualDbGeometry.getString());
    assertFalse(actualDbGeometry.isNull());
    assertTrue(actualDbGeometry.getProperties().isEmpty());
    assertSame(rawValue, geometry);
    assertSame(rawValue, actualDbGeometry.getRawValue());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(Geometry)}.
   *
   * <ul>
   *   <li>Then Geometry return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(Geometry)"})
  public void testNewDBGeometry_thenGeometryReturnLineString() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    // Act
    DBGeometry actualDbGeometry = new DBGeometry(rawValue);

    // Assert
    Geometry geometry = actualDbGeometry.getGeometry();
    assertTrue(geometry instanceof LineString);
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", actualDbGeometry.getString());
    assertFalse(actualDbGeometry.isNull());
    assertSame(rawValue, geometry);
    assertSame(rawValue, actualDbGeometry.getRawValue());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(DBGeometry)}.
   *
   * <ul>
   *   <li>When {@link DBGeometry#DBGeometry()}.
   *   <li>Then return RawValue is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(DBGeometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(DBGeometry)"})
  public void testNewDBGeometry_whenDBGeometry_thenReturnRawValueIsNull() {
    // Arrange and Act
    DBGeometry actualDbGeometry = new DBGeometry(new DBGeometry());

    // Assert
    assertNull(actualDbGeometry.getRawValue());
    assertNull(actualDbGeometry.getString());
    assertNull(actualDbGeometry.getProperties());
    assertNull(actualDbGeometry.getGeometry());
    assertTrue(actualDbGeometry.isNull());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(Object, int, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return Properties Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(Object, int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(Object, int, Map)"})
  public void testNewDBGeometry_whenHashMap_thenReturnPropertiesEmpty() {
    // Arrange and Act
    DBGeometry actualDbGeometry = new DBGeometry("Raw Value", 1, new HashMap<>());

    // Assert
    assertEquals("Raw Value", actualDbGeometry.getString());
    assertEquals("Raw Value", actualDbGeometry.getRawValue());
    assertNull(actualDbGeometry.getGeometry());
    assertEquals(1, actualDbGeometry.getSRID());
    assertFalse(actualDbGeometry.isEmpty());
    assertFalse(actualDbGeometry.isModified());
    assertFalse(actualDbGeometry.isNull());
    assertTrue(actualDbGeometry.getProperties().isEmpty());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(Object, int, Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Properties is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(Object, int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(Object, int, Map)"})
  public void testNewDBGeometry_whenNull_thenReturnPropertiesIsNull() {
    // Arrange and Act
    DBGeometry actualDbGeometry = new DBGeometry("Raw Value", 1, null);

    // Assert
    assertEquals("Raw Value", actualDbGeometry.getString());
    assertEquals("Raw Value", actualDbGeometry.getRawValue());
    assertNull(actualDbGeometry.getProperties());
    assertNull(actualDbGeometry.getGeometry());
    assertEquals(1, actualDbGeometry.getSRID());
    assertFalse(actualDbGeometry.isEmpty());
    assertFalse(actualDbGeometry.isModified());
    assertFalse(actualDbGeometry.isNull());
  }

  /**
   * Test {@link DBGeometry#DBGeometry(Geometry)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return RawValue is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#DBGeometry(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.<init>(Geometry)"})
  public void testNewDBGeometry_whenNull_thenReturnRawValueIsNull() {
    // Arrange and Act
    DBGeometry actualDbGeometry = new DBGeometry((Geometry) null);

    // Assert
    assertNull(actualDbGeometry.getRawValue());
    assertNull(actualDbGeometry.getString());
    assertNull(actualDbGeometry.getGeometry());
    assertTrue(actualDbGeometry.isNull());
  }

  /**
   * Test {@link DBGeometry#getGeometry()}.
   *
   * <p>Method under test: {@link DBGeometry#getGeometry()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry DBGeometry.getGeometry()"})
  public void testGetGeometry() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertSame(rawValue, new DBGeometry(rawValue).getGeometry());
  }

  /**
   * Test {@link DBGeometry#getGeometry()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getGeometry()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry DBGeometry.getGeometry()"})
  public void testGetGeometry_givenDBGeometry_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBGeometry().getGeometry());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3, 1, 1);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Given {@link CoordinateArraySequence#CoordinateArraySequence(int, int)} with size is
   *       three and dimension is one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_givenCoordinateArraySequenceWithSizeIsThreeAndDimensionIsOne() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3, 1);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Object, int)} with {@code Raw Value} and srid is one.
   *   <li>Then return {@code Raw Value}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_givenDBGeometryWithRawValueAndSridIsOne_thenReturnRawValue() {
    // Arrange, Act and Assert
    assertEquals("Raw Value", new DBGeometry("Raw Value", 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_givenDBGeometry_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBGeometry().getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Given {@link PrecisionModel#PrecisionModel(double)} with scale is ten.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_givenPrecisionModelWithScaleIsTen() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(10.0d), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code GEOMETRYCOLLECTION (LINESTRING (0 0, 0 0, 0 0))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnGeometrycollectionLinestring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection geometryCollection =
        new GeometryCollection(geometries, new GeometryFactory());

    // Act and Assert
    assertEquals(
        "GEOMETRYCOLLECTION (LINESTRING (0 0, 0 0, 0 0))",
        new DBGeometry(geometryCollection, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code LINEARRING (0 0, 0 0, 0 0)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnLinearring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());

    // Act and Assert
    assertEquals("LINEARRING (0 0, 0 0, 0 0)", new DBGeometry(linearRing, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code LINESTRING (0 0, 0 0, 0 0)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnLinestring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", new DBGeometry(rawValue).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTILINESTRING ((0 0, 0 0, 0 0))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultilinestring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    LineString[] lineStrings = new LineString[] {lineString};
    MultiLineString multiLineString = new MultiLineString(lineStrings, new GeometryFactory());

    // Act and Assert
    assertEquals(
        "MULTILINESTRING ((0 0, 0 0, 0 0))", new DBGeometry(multiLineString, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygon000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new GeometryFactory());

    // Act and Assert
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0)))", new DBGeometry(multiPolygon, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0), EMPTY))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygon000000Empty() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.size()).thenReturn(0);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).size();
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0), EMPTY))", actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0), (0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygon000000000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(
        "MULTIPOLYGON (((0 0, 0 0, 0 0), (0 0, 0 0, 0 0)))",
        new DBGeometry(multiPolygon, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0), ()))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygon0000002() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(-1);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0), ()))", actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0)), ((0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygon0000000000002() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell2 = new LinearRing(points2, new GeometryFactory());
    Polygon polygon2 = new Polygon(shell2, new PrecisionModel(), 1);
    MultiPolygon multiPolygon =
        new MultiPolygon(new Polygon[] {polygon, polygon2}, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(
        "MULTIPOLYGON (((0 0, 0 0, 0 0)), ((0 0, 0 0, 0 0)))",
        new DBGeometry(multiPolygon, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (EMPTY)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonEmpty() {
    // Arrange
    Polygon polygon = new Polygon(null, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("MULTIPOLYGON (EMPTY)", new DBGeometry(multiPolygon, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonEmpty2() {
    // Arrange
    MultiPolygon multiPolygon = new MultiPolygon(null, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("MULTIPOLYGON EMPTY", new DBGeometry(multiPolygon, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON M(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3
   *       10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonM00NaN00NaN00NaN231023102310() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(Double.NaN);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON M(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3 10)))", actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON Z(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3
   *       10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonZ00NaN00NaN00NaN231023102310() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(Double.NaN);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON Z(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3 10)))", actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON ZM(((0 0 NaN 0, 0 0 NaN 0, 0 0 NaN 0), (2 3 10 10, 2 3 10
   *       10, 2 3 10 10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonZm00NaN000NaN000NaN0231010231010231010() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3, 4, 1);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN 0, 0 0 NaN 0, 0 0 NaN 0), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10,
   *       2 3 10 10, 2 3 10 10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonZm00NaNNaN00NaNNaN00NaNNaN231010231010231010() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON ZM(((2 3 10 10, 2 3 10 10, 2 3 10 10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnMultipolygonZm231010231010231010() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualString = new DBGeometry(multiPolygon, 1).getString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals("MULTIPOLYGON ZM(((2 3 10 10, 2 3 10 10, 2 3 10 10)))", actualString);
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code POINT (0 0)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnPoint00() {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point point = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("POINT (0 0)", new DBGeometry(point, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code POINT EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnPointEmpty() {
    // Arrange
    Point point = new Point(null, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("POINT EMPTY", new DBGeometry(point, 1).getString());
  }

  /**
   * Test {@link DBGeometry#getString()}.
   *
   * <ul>
   *   <li>Then return {@code POLYGON ((0 0, 0 0, 0 0))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#getString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.getString()"})
  public void testGetString_thenReturnPolygon000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("POLYGON ((0 0, 0 0, 0 0))", new DBGeometry(polygon, 1).getString());
  }

  /**
   * Test {@link DBGeometry#isNull()}.
   *
   * <ul>
   *   <li>Given {@link CoordinateArraySequence#CoordinateArraySequence(int)} with size is three.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isNull()"})
  public void testIsNull_givenCoordinateArraySequenceWithSizeIsThree_thenReturnFalse() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertFalse(new DBGeometry(rawValue).isNull());
  }

  /**
   * Test {@link DBGeometry#isNull()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isNull()"})
  public void testIsNull_givenDBGeometry_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBGeometry().isNull());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3, 1, 1);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Given {@link CoordinateArraySequence#CoordinateArraySequence(int, int)} with size is
   *       three and dimension is one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_givenCoordinateArraySequenceWithSizeIsThreeAndDimensionIsOne() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3, 1);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Object, int)} with {@code Raw Value} and srid is one.
   *   <li>Then return {@code Raw Value}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_givenDBGeometryWithRawValueAndSridIsOne_thenReturnRawValue() {
    // Arrange, Act and Assert
    assertEquals("Raw Value", new DBGeometry("Raw Value", 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Given {@link PrecisionModel#PrecisionModel(double)} with scale is ten.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_givenPrecisionModelWithScaleIsTen() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(10.0d), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code GEOMETRYCOLLECTION (LINESTRING (0 0, 0 0, 0 0))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnGeometrycollectionLinestring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection geometryCollection =
        new GeometryCollection(geometries, new GeometryFactory());

    // Act and Assert
    assertEquals(
        "GEOMETRYCOLLECTION (LINESTRING (0 0, 0 0, 0 0))",
        new DBGeometry(geometryCollection, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code LINEARRING (0 0, 0 0, 0 0)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnLinearring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());

    // Act and Assert
    assertEquals("LINEARRING (0 0, 0 0, 0 0)", new DBGeometry(linearRing, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code LINESTRING (0 0, 0 0, 0 0)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnLinestring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", new DBGeometry(rawValue).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTILINESTRING ((0 0, 0 0, 0 0))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultilinestring000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    LineString[] lineStrings = new LineString[] {lineString};
    MultiLineString multiLineString = new MultiLineString(lineStrings, new GeometryFactory());

    // Act and Assert
    assertEquals(
        "MULTILINESTRING ((0 0, 0 0, 0 0))", new DBGeometry(multiLineString, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygon000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new GeometryFactory());

    // Act and Assert
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0)))", new DBGeometry(multiPolygon, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0), EMPTY))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygon000000Empty() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.size()).thenReturn(0);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).size();
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0), EMPTY))", actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0), (0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygon000000000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(
        "MULTIPOLYGON (((0 0, 0 0, 0 0), (0 0, 0 0, 0 0)))",
        new DBGeometry(multiPolygon, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0), ()))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygon0000002() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(-1);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0), ()))", actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (((0 0, 0 0, 0 0)), ((0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygon0000000000002() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell2 = new LinearRing(points2, new GeometryFactory());
    Polygon polygon2 = new Polygon(shell2, new PrecisionModel(), 1);
    MultiPolygon multiPolygon =
        new MultiPolygon(new Polygon[] {polygon, polygon2}, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals(
        "MULTIPOLYGON (((0 0, 0 0, 0 0)), ((0 0, 0 0, 0 0)))",
        new DBGeometry(multiPolygon, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON (EMPTY)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonEmpty() {
    // Arrange
    Polygon polygon = new Polygon(null, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("MULTIPOLYGON (EMPTY)", new DBGeometry(multiPolygon, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonEmpty2() {
    // Arrange
    MultiPolygon multiPolygon = new MultiPolygon(null, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("MULTIPOLYGON EMPTY", new DBGeometry(multiPolygon, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON M(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3
   *       10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonM00NaN00NaN00NaN231023102310() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(Double.NaN);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON M(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON Z(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3
   *       10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonZ00NaN00NaN00NaN231023102310() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(Double.NaN);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON Z(((0 0 NaN, 0 0 NaN, 0 0 NaN), (2 3 10, 2 3 10, 2 3 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON ZM(((0 0 NaN 0, 0 0 NaN 0, 0 0 NaN 0), (2 3 10 10, 2 3 10
   *       10, 2 3 10 10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonZm00NaN000NaN000NaN0231010231010231010() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3, 4, 1);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN 0, 0 0 NaN 0, 0 0 NaN 0), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10,
   *       2 3 10 10, 2 3 10 10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonZm00NaNNaN00NaNNaN00NaNNaN231010231010231010() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals(
        "MULTIPOLYGON ZM(((0 0 NaN NaN, 0 0 NaN NaN, 0 0 NaN NaN), (2 3 10 10, 2 3 10 10, 2 3 10 10)))",
        actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code MULTIPOLYGON ZM(((2 3 10 10, 2 3 10 10, 2 3 10 10)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnMultipolygonZm231010231010231010() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    String actualToStringResult = new DBGeometry(multiPolygon, 1).toString();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals("MULTIPOLYGON ZM(((2 3 10 10, 2 3 10 10, 2 3 10 10)))", actualToStringResult);
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code POINT (0 0)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnPoint00() {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point point = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("POINT (0 0)", new DBGeometry(point, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code POINT EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnPointEmpty() {
    // Arrange
    Point point = new Point(null, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("POINT EMPTY", new DBGeometry(point, 1).toString());
  }

  /**
   * Test {@link DBGeometry#toString()}.
   *
   * <ul>
   *   <li>Then return {@code POLYGON ((0 0, 0 0, 0 0))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBGeometry.toString()"})
  public void testToString_thenReturnPolygon000000() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);

    // Act and Assert
    assertEquals("POLYGON ((0 0, 0 0, 0 0))", new DBGeometry(polygon, 1).toString());
  }

  /**
   * Test {@link DBGeometry#setSRID(int)}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then {@link DBGeometry#DBGeometry()} SRID is one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#setSRID(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.setSRID(int)"})
  public void testSetSRID_givenDBGeometry_thenDBGeometrySridIsOne() {
    // Arrange
    DBGeometry dbGeometry = new DBGeometry();

    // Act
    dbGeometry.setSRID(1);

    // Assert
    assertEquals(1, dbGeometry.getSRID());
  }

  /**
   * Test {@link DBGeometry#setSRID(int)}.
   *
   * <ul>
   *   <li>Then {@link DBGeometry#DBGeometry(Geometry)} with rawValue is {@link
   *       LineString#LineString(CoordinateSequence, GeometryFactory)} Geometry {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#setSRID(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.setSRID(int)"})
  public void testSetSRID_thenDBGeometryWithRawValueIsLineStringGeometryLineString() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());
    DBGeometry dbGeometry = new DBGeometry(rawValue);

    // Act
    dbGeometry.setSRID(1);

    // Assert
    Geometry geometry = dbGeometry.getGeometry();
    assertTrue(geometry instanceof LineString);
    assertEquals(1, dbGeometry.getSRID());
    assertEquals(1, geometry.getSRID());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new GeometryFactory());

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiPolygon, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    assertEquals(multiPolygon, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Object, int)} with {@code Raw Value} and srid is one.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_givenDBGeometryWithRawValueAndSridIsOne_thenThrowDBException()
      throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> new DBGeometry("Raw Value", 1).flipCoordinates());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Object, int)} with rawValue is forty-two and srid is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_givenDBGeometryWithRawValueIsFortyTwoAndSridIsOne()
      throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> new DBGeometry(42, 1).flipCoordinates());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Geometry)} with rawValue is {@link
   *       LineString#LineString(CoordinateSequence, GeometryFactory)} Properties is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_givenDBGeometryWithRawValueIsLineStringPropertiesIsNull()
      throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    DBGeometry dbGeometry = new DBGeometry(rawValue);
    dbGeometry.setProperties(null);

    // Act
    DBGeometry actualFlipCoordinatesResult = dbGeometry.flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof LineString);
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", actualFlipCoordinatesResult.getString());
    assertEquals(0, actualFlipCoordinatesResult.getSRID());
    assertEquals(rawValue, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then Geometry Centroid Boundary return {@link GeometryCollection}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenGeometryCentroidBoundaryReturnGeometryCollection()
      throws DBException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    when(points.copy()).thenReturn(null);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiPolygon, 1).flipCoordinates();

    // Assert
    verify(points).copy();
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry.getCentroid().getBoundary() instanceof GeometryCollection);
    Geometry boundary = geometry.getBoundary();
    assertTrue(boundary instanceof MultiLineString);
    Geometry unionResult = geometry.union();
    Geometry boundary2 = unionResult.getBoundary();
    assertTrue(boundary2 instanceof MultiLineString);
    assertTrue(boundary.getBoundary() instanceof MultiPoint);
    assertTrue(geometry instanceof MultiPolygon);
    assertTrue(unionResult instanceof Polygon);
    assertEquals("MULTIPOLYGON (EMPTY)", actualFlipCoordinatesResult.getString());
    assertEquals(boundary, boundary2);
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then Geometry return {@link GeometryCollection}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenGeometryReturnGeometryCollection() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection geometryCollection =
        new GeometryCollection(geometries, new GeometryFactory());

    // Act
    DBGeometry actualFlipCoordinatesResult =
        new DBGeometry(geometryCollection, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof GeometryCollection);
    assertEquals(
        "GEOMETRYCOLLECTION (LINESTRING (0 0, 0 0, 0 0))", actualFlipCoordinatesResult.getString());
    assertEquals(geometryCollection, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then Geometry return {@link LinearRing}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenGeometryReturnLinearRing() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(linearRing, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof LinearRing);
    assertEquals("LINEARRING (0 0, 0 0, 0 0)", actualFlipCoordinatesResult.getString());
    assertEquals(linearRing, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then Geometry return {@link MultiLineString}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenGeometryReturnMultiLineString() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    LineString[] lineStrings = new LineString[] {lineString};
    MultiLineString multiLineString = new MultiLineString(lineStrings, new GeometryFactory());

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiLineString, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof MultiLineString);
    assertEquals("MULTILINESTRING ((0 0, 0 0, 0 0))", actualFlipCoordinatesResult.getString());
    assertEquals(multiLineString, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then Geometry return {@link Point}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenGeometryReturnPoint() throws DBException {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point point = new Point(coordinate, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(point, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof Point);
    assertEquals("POINT (0 0)", actualFlipCoordinatesResult.getString());
    assertEquals(point, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then Geometry return {@link Polygon}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenGeometryReturnPolygon() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(polygon, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof Polygon);
    assertEquals("POLYGON ((0 0, 0 0, 0 0))", actualFlipCoordinatesResult.getString());
    assertEquals(polygon, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then return array length is three.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenReturnArrayLengthIsThree() throws DBException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    Coordinate coordinate = new Coordinate();
    when(points.getCoordinate(anyInt())).thenReturn(coordinate);
    when(points.size()).thenReturn(3);
    when(points.copy()).thenReturn(new CoordinateArraySequence(3));
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiPolygon, 1).flipCoordinates();

    // Assert
    verify(points).copy();
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry.getBoundary() instanceof MultiLineString);
    assertTrue(geometry instanceof MultiPolygon);
    assertEquals(3, geometry.getCoordinates().length);
    assertEquals(coordinate, geometry.getCoordinate());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then return Properties Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenReturnPropertiesEmpty() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    DBGeometry dbGeometry = new DBGeometry(rawValue);
    dbGeometry.setProperties(new HashMap<>());

    // Act
    DBGeometry actualFlipCoordinatesResult = dbGeometry.flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof LineString);
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", actualFlipCoordinatesResult.getString());
    assertEquals(0, actualFlipCoordinatesResult.getSRID());
    assertTrue(actualFlipCoordinatesResult.getProperties().isEmpty());
    assertEquals(rawValue, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then return String is {@code MULTIPOLYGON (((0 0, 0 0, 0 0), (0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenReturnStringIsMultipolygon000000000000() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiPolygon, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    assertEquals(
        "MULTIPOLYGON (((0 0, 0 0, 0 0), (0 0, 0 0, 0 0)))",
        actualFlipCoordinatesResult.getString());
    assertEquals(multiPolygon, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then return String is {@code MULTIPOLYGON (((0 0, 0 0, 0 0)), ((0 0, 0 0, 0 0)))}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenReturnStringIsMultipolygon0000000000002() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell2 = new LinearRing(points2, new GeometryFactory());
    Polygon polygon2 = new Polygon(shell2, new PrecisionModel(), 1);
    MultiPolygon multiPolygon =
        new MultiPolygon(new Polygon[] {polygon, polygon2}, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiPolygon, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    assertEquals(
        "MULTIPOLYGON (((0 0, 0 0, 0 0)), ((0 0, 0 0, 0 0)))",
        actualFlipCoordinatesResult.getString());
    assertEquals(multiPolygon, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#flipCoordinates()}.
   *
   * <ul>
   *   <li>Then return String is {@code MULTIPOLYGON EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#flipCoordinates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.flipCoordinates()"})
  public void testFlipCoordinates_thenReturnStringIsMultipolygonEmpty() throws DBException {
    // Arrange
    MultiPolygon multiPolygon = new MultiPolygon(null, new PrecisionModel(), 1);

    // Act
    DBGeometry actualFlipCoordinatesResult = new DBGeometry(multiPolygon, 1).flipCoordinates();

    // Assert
    Geometry geometry = actualFlipCoordinatesResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    assertEquals("MULTIPOLYGON EMPTY", actualFlipCoordinatesResult.getString());
    assertEquals(multiPolygon, geometry);
    assertSame(geometry, actualFlipCoordinatesResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D() throws DBException {
    // Arrange
    Polygon polygon = new Polygon(null, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    DBGeometry dbGeometry = new DBGeometry(multiPolygon, 1);

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    assertSame(dbGeometry, actualForce2DResult);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D2() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    DBGeometry dbGeometry = new DBGeometry(multiPolygon, 1);

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    assertSame(dbGeometry, actualForce2DResult);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D3() throws DBException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.copy()).thenReturn(new CoordinateArraySequence(3));
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    CoordinateXYZM coordinateXYZM = new CoordinateXYZM(2.0d, 3.0d, 10.0d, 10.0d);
    when(points.toCoordinateArray()).thenReturn(new Coordinate[] {coordinateXYZM});
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    DBGeometry actualForce2DResult = new DBGeometry(multiPolygon, 1).force2D();

    // Assert
    verify(points).copy();
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    verify(points).toCoordinateArray();
    Geometry geometry = actualForce2DResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    assertEquals(multiPolygon, geometry);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Object, int)} with {@code Raw Value} and srid is one.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_givenDBGeometryWithRawValueAndSridIsOne_thenThrowDBException()
      throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> new DBGeometry("Raw Value", 1).force2D());
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry(Object, int)} with rawValue is forty-two and srid is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_givenDBGeometryWithRawValueIsFortyTwoAndSridIsOne() throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> new DBGeometry(42, 1).force2D());
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Then Geometry union InteriorPoint union return {@link Point}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_thenGeometryUnionInteriorPointUnionReturnPoint() throws DBException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.copy()).thenReturn(null);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    CoordinateXYZM coordinateXYZM = new CoordinateXYZM(2.0d, 3.0d, 10.0d, 10.0d);
    when(points.toCoordinateArray()).thenReturn(new Coordinate[] {coordinateXYZM});
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    DBGeometry actualForce2DResult = new DBGeometry(multiPolygon, 1).force2D();

    // Assert
    verify(points).copy();
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    verify(points).toCoordinateArray();
    Geometry geometry = actualForce2DResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    Geometry unionResult = geometry.union();
    assertTrue(unionResult.getInteriorPoint().union() instanceof Point);
    assertTrue(unionResult instanceof Polygon);
    Geometry unionResult2 = unionResult.union();
    assertTrue(unionResult2 instanceof Polygon);
    assertEquals("MULTIPOLYGON (((0 0, 0 0, 0 0), EMPTY))", actualForce2DResult.getString());
    assertEquals(unionResult2, unionResult2);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Then return {@link DBGeometry#DBGeometry(Object, int)} with rawValue is {@link
   *       GeometryCollection#GeometryCollection(Geometry[], GeometryFactory)} and srid is one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_thenReturnDBGeometryWithRawValueIsGeometryCollectionAndSridIsOne()
      throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection geometryCollection =
        new GeometryCollection(geometries, new GeometryFactory());
    DBGeometry dbGeometry = new DBGeometry(geometryCollection, 1);

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    assertSame(dbGeometry, actualForce2DResult);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Then return {@link DBGeometry#DBGeometry(Geometry)} with rawValue is {@link
   *       LineString#LineString(CoordinateSequence, GeometryFactory)}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_thenReturnDBGeometryWithRawValueIsLineString() throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    DBGeometry dbGeometry = new DBGeometry(rawValue);
    dbGeometry.setProperties(null);

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    assertSame(dbGeometry, actualForce2DResult);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Then return {@link DBGeometry#DBGeometry(Object, int)} with rawValue is {@link
   *       MultiPolygon#MultiPolygon(Polygon[], GeometryFactory)} and srid is one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_thenReturnDBGeometryWithRawValueIsMultiPolygonAndSridIsOne()
      throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new GeometryFactory());
    DBGeometry dbGeometry = new DBGeometry(multiPolygon, 1);

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    assertSame(dbGeometry, actualForce2DResult);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Then return {@link DBGeometry#DBGeometry(Object, int)} with rawValue is {@link
   *       Polygon#Polygon(LinearRing, PrecisionModel, int)} and srid is one.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_thenReturnDBGeometryWithRawValueIsPolygonAndSridIsOne()
      throws DBException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    DBGeometry dbGeometry = new DBGeometry(polygon, 1);

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    assertSame(dbGeometry, actualForce2DResult);
  }

  /**
   * Test {@link DBGeometry#force2D()}.
   *
   * <ul>
   *   <li>Then return Properties Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#force2D()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.force2D()"})
  public void testForce2D_thenReturnPropertiesEmpty() throws DBException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.copy()).thenReturn(new CoordinateArraySequence(3));
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    CoordinateXYZM coordinateXYZM = new CoordinateXYZM(2.0d, 3.0d, 10.0d, 10.0d);
    when(points.toCoordinateArray()).thenReturn(new Coordinate[] {coordinateXYZM});
    LinearRing linearRing = new LinearRing(points, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points2, new GeometryFactory());

    Polygon polygon = new Polygon(shell, holes, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);

    DBGeometry dbGeometry = new DBGeometry(multiPolygon, 1);
    dbGeometry.putProperties(new HashMap<>());

    // Act
    DBGeometry actualForce2DResult = dbGeometry.force2D();

    // Assert
    verify(points).copy();
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    verify(points).toCoordinateArray();
    Geometry geometry = actualForce2DResult.getGeometry();
    assertTrue(geometry instanceof MultiPolygon);
    assertTrue(actualForce2DResult.getProperties().isEmpty());
    assertEquals(multiPolygon, geometry);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBGeometry#setProperties(Map)}
   *   <li>{@link DBGeometry#release()}
   *   <li>{@link DBGeometry#getProperties()}
   *   <li>{@link DBGeometry#getRawValue()}
   *   <li>{@link DBGeometry#getSRID()}
   *   <li>{@link DBGeometry#isModified()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map DBGeometry.getProperties()",
    "Object DBGeometry.getRawValue()",
    "int DBGeometry.getSRID()",
    "boolean DBGeometry.isModified()",
    "void DBGeometry.release()",
    "void DBGeometry.setProperties(Map)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBGeometry dbGeometry = new DBGeometry();
    HashMap<String, Object> properties = new HashMap<>();

    // Act
    dbGeometry.setProperties(properties);
    dbGeometry.release();
    Map<String, Object> actualProperties = dbGeometry.getProperties();
    Object actualRawValue = dbGeometry.getRawValue();
    int actualSRID = dbGeometry.getSRID();

    // Assert
    assertNull(actualRawValue);
    assertEquals(0, actualSRID);
    assertFalse(dbGeometry.isModified());
    assertTrue(actualProperties.isEmpty());
    assertSame(properties, actualProperties);
  }

  /**
   * Test {@link DBGeometry#putProperties(Map)}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then {@link DBGeometry#DBGeometry()} Properties Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#putProperties(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.putProperties(Map)"})
  public void testPutProperties_givenDBGeometry_thenDBGeometryPropertiesEmpty() {
    // Arrange
    DBGeometry dbGeometry = new DBGeometry();

    // Act
    dbGeometry.putProperties(new HashMap<>());

    // Assert
    assertTrue(dbGeometry.getProperties().isEmpty());
  }

  /**
   * Test {@link DBGeometry#putProperties(Map)}.
   *
   * <ul>
   *   <li>Then {@link DBGeometry#DBGeometry(Geometry)} with rawValue is {@link
   *       LineString#LineString(CoordinateSequence, GeometryFactory)} Properties Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#putProperties(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBGeometry.putProperties(Map)"})
  public void testPutProperties_thenDBGeometryWithRawValueIsLineStringPropertiesEmpty() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    DBGeometry dbGeometry = new DBGeometry(rawValue);
    dbGeometry.setProperties(new HashMap<>());

    // Act
    dbGeometry.putProperties(new HashMap<>());

    // Assert that nothing has changed
    assertTrue(dbGeometry.getProperties().isEmpty());
  }

  /**
   * Test {@link DBGeometry#copy()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then return RawValue is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#copy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.copy()"})
  public void testCopy_givenDBGeometry_thenReturnRawValueIsNull() {
    // Arrange and Act
    DBGeometry actualCopyResult = new DBGeometry().copy();

    // Assert
    assertNull(actualCopyResult.getRawValue());
    assertNull(actualCopyResult.getString());
    assertNull(actualCopyResult.getProperties());
    assertNull(actualCopyResult.getGeometry());
    assertTrue(actualCopyResult.isNull());
  }

  /**
   * Test {@link DBGeometry#copy()}.
   *
   * <ul>
   *   <li>Then Geometry return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#copy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGeometry DBGeometry.copy()"})
  public void testCopy_thenGeometryReturnLineString() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    DBGeometry dbGeometry = new DBGeometry(rawValue);
    dbGeometry.setProperties(new HashMap<>());

    // Act
    DBGeometry actualCopyResult = dbGeometry.copy();

    // Assert
    Geometry geometry = actualCopyResult.getGeometry();
    assertTrue(geometry instanceof LineString);
    assertEquals("LINESTRING (0 0, 0 0, 0 0)", actualCopyResult.getString());
    assertFalse(actualCopyResult.isNull());
    assertTrue(actualCopyResult.getProperties().isEmpty());
    assertSame(rawValue, geometry);
    assertSame(rawValue, actualCopyResult.getRawValue());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString rawValue = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertFalse(new DBGeometry(rawValue).isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty2() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection rawValue = new GeometryCollection(geometries, new GeometryFactory());

    // Act and Assert
    assertFalse(new DBGeometry(rawValue).isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty3() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon rawValue = new MultiPolygon(polygons, new GeometryFactory());

    // Act and Assert
    assertFalse(new DBGeometry(rawValue).isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty4() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.toCoordinateArray()).thenReturn(new Coordinate[] {new CoordinateXY()});
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon rawValue = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    boolean actualIsEmptyResult = new DBGeometry(rawValue).isEmpty();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    verify(points).toCoordinateArray();
    assertFalse(actualIsEmptyResult);
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty5() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.toCoordinateArray()).thenReturn(new Coordinate[] {new Coordinate(2.0d, 3.0d)});
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon rawValue = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    boolean actualIsEmptyResult = new DBGeometry(rawValue).isEmpty();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    verify(points).toCoordinateArray();
    assertFalse(actualIsEmptyResult);
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty6() {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.toCoordinateArray()).thenReturn(new Coordinate[] {new CoordinateXYM()});
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon rawValue = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act
    boolean actualIsEmptyResult = new DBGeometry(rawValue).isEmpty();

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).size();
    verify(points).toCoordinateArray();
    assertFalse(actualIsEmptyResult);
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty7() {
    // Arrange
    Polygon polygon = new Polygon(null, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon rawValue = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act and Assert
    assertTrue(new DBGeometry(rawValue).isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty8() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon rawValue = new MultiPolygon(polygons, new PrecisionModel(), 1);

    // Act and Assert
    assertFalse(new DBGeometry(rawValue).isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty9() {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon rawValue = new Polygon(shell, new PrecisionModel(), 1);

    // Act and Assert
    assertFalse(new DBGeometry(rawValue).isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBGeometry#DBGeometry()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty_givenDBGeometry_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DBGeometry().isEmpty());
  }

  /**
   * Test {@link DBGeometry#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link LineString#LineString(CoordinateSequence, GeometryFactory)} with points is
   *       {@code null} and factory is {@link GeometryFactory#GeometryFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link DBGeometry#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBGeometry.isEmpty()"})
  public void testIsEmpty_givenLineStringWithPointsIsNullAndFactoryIsGeometryFactory() {
    // Arrange
    LineString rawValue = new LineString(null, new GeometryFactory());

    // Act and Assert
    assertTrue(new DBGeometry(rawValue).isEmpty());
  }
}
