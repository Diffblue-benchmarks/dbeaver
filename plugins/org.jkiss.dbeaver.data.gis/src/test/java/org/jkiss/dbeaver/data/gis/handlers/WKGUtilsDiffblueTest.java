package org.jkiss.dbeaver.data.gis.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.cugos.wkg.CircularString;
import org.cugos.wkg.CompoundCurve;
import org.cugos.wkg.Curve;
import org.cugos.wkg.CurvePolygon;
import org.cugos.wkg.Dimension;
import org.cugos.wkg.Geometry;
import org.cugos.wkg.LineString;
import org.cugos.wkg.LinearRing;
import org.cugos.wkg.MultiCurve;
import org.cugos.wkg.MultiLineString;
import org.cugos.wkg.MultiPolygon;
import org.cugos.wkg.MultiSurface;
import org.cugos.wkg.Polygon;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WKGUtilsDiffblueTest {
  /**
   * Test {@link WKGUtils#parseWKT(String)}.
   *
   * <ul>
   *   <li>When {@code Error parsing geometry value from string}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#parseWKT(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.gis.DBGeometry WKGUtils.parseWKT(String)"})
  public void testParseWKT_whenErrorParsingGeometryValueFromString() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCException.class, () -> WKGUtils.parseWKT("Error parsing geometry value from string"));
  }

  /**
   * Test {@link WKGUtils#parseWKT(String)}.
   *
   * <ul>
   *   <li>When {@code SRID=}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#parseWKT(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.gis.DBGeometry WKGUtils.parseWKT(String)"})
  public void testParseWKT_whenSrid() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> WKGUtils.parseWKT("SRID="));
  }

  /**
   * Test {@link WKGUtils#parseWKT(String)}.
   *
   * <ul>
   *   <li>When {@code Wkt}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#parseWKT(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.gis.DBGeometry WKGUtils.parseWKT(String)"})
  public void testParseWKT_whenWkt() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> WKGUtils.parseWKT("Wkt"));
  }

  /**
   * Test {@link WKGUtils#parseWKB(String)}.
   *
   * <ul>
   *   <li>When {@code CircularString}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#parseWKB(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.gis.DBGeometry WKGUtils.parseWKB(String)"})
  public void testParseWKB_whenOrgCugosWkgCircularString_thenThrowDBCException()
      throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> WKGUtils.parseWKB("org.cugos.wkg.CircularString"));
  }

  /**
   * Test {@link WKGUtils#parseWKB(String)}.
   *
   * <ul>
   *   <li>When {@code SRID=ListSRID=}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#parseWKB(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.gis.DBGeometry WKGUtils.parseWKB(String)"})
  public void testParseWKB_whenSridJavaUtilListSRID_thenThrowDBCException() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> WKGUtils.parseWKB("SRID=java.util.ListSRID="));
  }

  /**
   * Test {@link WKGUtils#parseWKB(String)}.
   *
   * <ul>
   *   <li>When {@code SRID=SRID=}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#parseWKB(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.gis.DBGeometry WKGUtils.parseWKB(String)"})
  public void testParseWKB_whenSridSrid_thenThrowDBCException() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(DBCException.class, () -> WKGUtils.parseWKB("SRID=SRID="));
  }

  /**
   * Test {@link WKGUtils#isCurve(Object)}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#isCurve(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WKGUtils.isCurve(Object)"})
  public void testIsCurve_whenCreateEmpty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(WKGUtils.isCurve(CircularString.createEmpty()));
  }

  /**
   * Test {@link WKGUtils#isCurve(Object)}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#isCurve(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WKGUtils.isCurve(Object)"})
  public void testIsCurve_whenCreateEmpty_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(WKGUtils.isCurve(CompoundCurve.createEmpty()));
  }

  /**
   * Test {@link WKGUtils#isCurve(Object)}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#isCurve(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WKGUtils.isCurve(Object)"})
  public void testIsCurve_whenCreateEmpty_thenReturnTrue3() {
    // Arrange, Act and Assert
    assertTrue(WKGUtils.isCurve(CurvePolygon.createEmpty()));
  }

  /**
   * Test {@link WKGUtils#isCurve(Object)}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#isCurve(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WKGUtils.isCurve(Object)"})
  public void testIsCurve_whenCreateEmpty_thenReturnTrue4() {
    // Arrange, Act and Assert
    assertTrue(WKGUtils.isCurve(MultiCurve.createEmpty()));
  }

  /**
   * Test {@link WKGUtils#isCurve(Object)}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#isCurve(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WKGUtils.isCurve(Object)"})
  public void testIsCurve_whenCreateEmpty_thenReturnTrue5() {
    // Arrange, Act and Assert
    assertTrue(WKGUtils.isCurve(MultiSurface.createEmpty()));
  }

  /**
   * Test {@link WKGUtils#isCurve(Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#isCurve(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WKGUtils.isCurve(Object)"})
  public void testIsCurve_whenValue_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(WKGUtils.isCurve("Value"));
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>Then return InnerLinearRings size is one.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_thenReturnInnerLinearRingsSizeIsOne() {
    // Arrange
    ArrayList<Curve> innerCurves = new ArrayList<>();
    innerCurves.add(CircularString.createEmpty());
    CurvePolygon value = new CurvePolygon(CircularString.createEmpty(), innerCurves, Dimension.Two);

    // Act
    Geometry actualLinearizeResult = WKGUtils.linearize(value, 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof Polygon);
    List<LinearRing> innerLinearRings = ((Polygon) actualLinearizeResult).getInnerLinearRings();
    assertEquals(1, innerLinearRings.size());
    LinearRing getResult = innerLinearRings.get(0);
    assertNull(getResult.getData());
    assertNull(getResult.getSrid());
    assertEquals(0, getResult.getNumberOfCoordinates());
    assertEquals(Dimension.Two, getResult.getDimension());
    assertTrue(getResult.getCoordinates().isEmpty());
    assertTrue(getResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>Then return InnerLinearRings size is two.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_thenReturnInnerLinearRingsSizeIsTwo() {
    // Arrange
    ArrayList<Curve> innerCurves = new ArrayList<>();
    innerCurves.add(CircularString.createEmpty());
    innerCurves.add(CircularString.createEmpty());
    CurvePolygon value = new CurvePolygon(CircularString.createEmpty(), innerCurves, Dimension.Two);

    // Act
    Geometry actualLinearizeResult = WKGUtils.linearize(value, 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof Polygon);
    List<LinearRing> innerLinearRings = ((Polygon) actualLinearizeResult).getInnerLinearRings();
    assertEquals(2, innerLinearRings.size());
    LinearRing getResult = innerLinearRings.get(1);
    assertNull(getResult.getData());
    assertNull(getResult.getSrid());
    assertEquals(0, getResult.getNumberOfCoordinates());
    assertEquals(Dimension.Two, getResult.getDimension());
    assertTrue(getResult.getCoordinates().isEmpty());
    assertTrue(getResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>Then return OuterLinearRing Data is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_thenReturnOuterLinearRingDataIsNull() {
    // Arrange and Act
    Geometry actualLinearizeResult = WKGUtils.linearize(CurvePolygon.createEmpty(), 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof Polygon);
    LinearRing outerLinearRing = ((Polygon) actualLinearizeResult).getOuterLinearRing();
    assertNull(outerLinearRing.getData());
    assertNull(outerLinearRing.getSrid());
    assertEquals(0, actualLinearizeResult.getNumberOfCoordinates());
    assertEquals(0, outerLinearRing.getNumberOfCoordinates());
    assertEquals(Dimension.Two, outerLinearRing.getDimension());
    assertTrue(actualLinearizeResult.getCoordinates().isEmpty());
    assertTrue(outerLinearRing.getCoordinates().isEmpty());
    assertTrue(((Polygon) actualLinearizeResult).getInnerLinearRings().isEmpty());
    assertTrue(actualLinearizeResult.isEmpty());
    assertTrue(outerLinearRing.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_whenCreateEmpty_thenReturnLineString() {
    // Arrange and Act
    Geometry actualLinearizeResult = WKGUtils.linearize(CircularString.createEmpty(), 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof LineString);
    assertEquals(0, actualLinearizeResult.getNumberOfCoordinates());
    assertTrue(actualLinearizeResult.getCoordinates().isEmpty());
    assertTrue(actualLinearizeResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_whenCreateEmpty_thenReturnLineString2() {
    // Arrange and Act
    Geometry actualLinearizeResult = WKGUtils.linearize(CompoundCurve.createEmpty(), 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof LineString);
    assertEquals(0, actualLinearizeResult.getNumberOfCoordinates());
    assertTrue(actualLinearizeResult.getCoordinates().isEmpty());
    assertTrue(actualLinearizeResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link MultiLineString}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_whenCreateEmpty_thenReturnMultiLineString() {
    // Arrange and Act
    Geometry actualLinearizeResult = WKGUtils.linearize(MultiCurve.createEmpty(), 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof MultiLineString);
    assertEquals(0, actualLinearizeResult.getNumberOfCoordinates());
    assertTrue(actualLinearizeResult.getCoordinates().isEmpty());
    assertTrue(((MultiLineString) actualLinearizeResult).getLineStrings().isEmpty());
    assertTrue(actualLinearizeResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link MultiPolygon}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_whenCreateEmpty_thenReturnMultiPolygon() {
    // Arrange and Act
    Geometry actualLinearizeResult = WKGUtils.linearize(MultiSurface.createEmpty(), 10.0d);

    // Assert
    assertTrue(actualLinearizeResult instanceof MultiPolygon);
    assertEquals(0, actualLinearizeResult.getNumberOfCoordinates());
    assertTrue(actualLinearizeResult.getCoordinates().isEmpty());
    assertTrue(((MultiPolygon) actualLinearizeResult).getPolygons().isEmpty());
    assertTrue(actualLinearizeResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry, double)} with {@code value}, {@code tolerance}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Geometry WKGUtils.linearize(Geometry, double)"})
  public void testLinearizeWithValueTolerance_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(WKGUtils.linearize(null, 10.0d));
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>Given createEmpty.
   *   <li>Then return InnerLinearRings size is one.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_givenCreateEmpty_thenReturnInnerLinearRingsSizeIsOne() {
    // Arrange
    ArrayList<Curve> innerCurves = new ArrayList<>();
    innerCurves.add(CircularString.createEmpty());
    CurvePolygon value = new CurvePolygon(CircularString.createEmpty(), innerCurves, Dimension.Two);

    // Act
    Object actualLinearizeResult = WKGUtils.linearize(value);

    // Assert
    assertTrue(actualLinearizeResult instanceof Polygon);
    List<LinearRing> innerLinearRings = ((Polygon) actualLinearizeResult).getInnerLinearRings();
    assertEquals(1, innerLinearRings.size());
    LinearRing getResult = innerLinearRings.get(0);
    assertNull(getResult.getData());
    assertNull(getResult.getSrid());
    assertEquals(0, getResult.getNumberOfCoordinates());
    assertEquals(Dimension.Two, getResult.getDimension());
    assertTrue(getResult.getCoordinates().isEmpty());
    assertTrue(getResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>Given createEmpty.
   *   <li>Then return InnerLinearRings size is two.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_givenCreateEmpty_thenReturnInnerLinearRingsSizeIsTwo() {
    // Arrange
    ArrayList<Curve> innerCurves = new ArrayList<>();
    innerCurves.add(CircularString.createEmpty());
    innerCurves.add(CircularString.createEmpty());
    CurvePolygon value = new CurvePolygon(CircularString.createEmpty(), innerCurves, Dimension.Two);

    // Act
    Object actualLinearizeResult = WKGUtils.linearize(value);

    // Assert
    assertTrue(actualLinearizeResult instanceof Polygon);
    List<LinearRing> innerLinearRings = ((Polygon) actualLinearizeResult).getInnerLinearRings();
    assertEquals(2, innerLinearRings.size());
    LinearRing getResult = innerLinearRings.get(1);
    assertNull(getResult.getData());
    assertNull(getResult.getSrid());
    assertEquals(0, getResult.getNumberOfCoordinates());
    assertEquals(Dimension.Two, getResult.getDimension());
    assertTrue(getResult.getCoordinates().isEmpty());
    assertTrue(getResult.isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_whenCreateEmpty_thenReturnLineString() {
    // Arrange and Act
    Object actualLinearizeResult = WKGUtils.linearize(CircularString.createEmpty());

    // Assert
    assertTrue(actualLinearizeResult instanceof LineString);
    assertEquals(0, ((LineString) actualLinearizeResult).getNumberOfCoordinates());
    assertTrue(((LineString) actualLinearizeResult).getCoordinates().isEmpty());
    assertTrue(((LineString) actualLinearizeResult).isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link LineString}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_whenCreateEmpty_thenReturnLineString2() {
    // Arrange and Act
    Object actualLinearizeResult = WKGUtils.linearize(CompoundCurve.createEmpty());

    // Assert
    assertTrue(actualLinearizeResult instanceof LineString);
    assertEquals(0, ((LineString) actualLinearizeResult).getNumberOfCoordinates());
    assertTrue(((LineString) actualLinearizeResult).getCoordinates().isEmpty());
    assertTrue(((LineString) actualLinearizeResult).isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link MultiLineString}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_whenCreateEmpty_thenReturnMultiLineString() {
    // Arrange and Act
    Object actualLinearizeResult = WKGUtils.linearize(MultiCurve.createEmpty());

    // Assert
    assertTrue(actualLinearizeResult instanceof MultiLineString);
    assertEquals(0, ((MultiLineString) actualLinearizeResult).getNumberOfCoordinates());
    assertTrue(((MultiLineString) actualLinearizeResult).getCoordinates().isEmpty());
    assertTrue(((MultiLineString) actualLinearizeResult).getLineStrings().isEmpty());
    assertTrue(((MultiLineString) actualLinearizeResult).isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return {@link MultiPolygon}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_whenCreateEmpty_thenReturnMultiPolygon() {
    // Arrange and Act
    Object actualLinearizeResult = WKGUtils.linearize(MultiSurface.createEmpty());

    // Assert
    assertTrue(actualLinearizeResult instanceof MultiPolygon);
    assertEquals(0, ((MultiPolygon) actualLinearizeResult).getNumberOfCoordinates());
    assertTrue(((MultiPolygon) actualLinearizeResult).getCoordinates().isEmpty());
    assertTrue(((MultiPolygon) actualLinearizeResult).getPolygons().isEmpty());
    assertTrue(((MultiPolygon) actualLinearizeResult).isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>When createEmpty.
   *   <li>Then return OuterLinearRing Data is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_whenCreateEmpty_thenReturnOuterLinearRingDataIsNull() {
    // Arrange and Act
    Object actualLinearizeResult = WKGUtils.linearize(CurvePolygon.createEmpty());

    // Assert
    assertTrue(actualLinearizeResult instanceof Polygon);
    LinearRing outerLinearRing = ((Polygon) actualLinearizeResult).getOuterLinearRing();
    assertNull(outerLinearRing.getData());
    assertNull(outerLinearRing.getSrid());
    assertEquals(0, outerLinearRing.getNumberOfCoordinates());
    assertEquals(0, ((Polygon) actualLinearizeResult).getNumberOfCoordinates());
    assertEquals(Dimension.Two, outerLinearRing.getDimension());
    assertTrue(outerLinearRing.getCoordinates().isEmpty());
    assertTrue(((Polygon) actualLinearizeResult).getCoordinates().isEmpty());
    assertTrue(((Polygon) actualLinearizeResult).getInnerLinearRings().isEmpty());
    assertTrue(outerLinearRing.isEmpty());
    assertTrue(((Polygon) actualLinearizeResult).isEmpty());
  }

  /**
   * Test {@link WKGUtils#linearize(Geometry)} with {@code value}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WKGUtils#linearize(Geometry)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object WKGUtils.linearize(Geometry)"})
  public void testLinearizeWithValue_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(WKGUtils.linearize(null));
  }
}
