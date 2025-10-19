package org.jkiss.dbeaver.ext.hana.model.data.wkb;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

public class HANAWKBWriterDiffblueTest {
  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite() throws HANAWKBWriterException {
    // Arrange
    Point geometry = new Point(null, new PrecisionModel(), 1);

    // Act and Assert
    assertArrayEquals(
        new byte[] {
          1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -8, Byte.MAX_VALUE, 0, 0, 0, 0, 0, 0, -8, Byte.MAX_VALUE
        },
        HANAWKBWriter.write(geometry, XyzmMode.XY));
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite2() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3, 1, 1);
    LineString geometry = new LineString(points, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XYM);

    // Assert
    assertEquals((byte) -46, actualWriteResult[1]);
    assertEquals((byte) -8, actualWriteResult[79]);
    assertEquals((byte) 0, actualWriteResult[10]);
    assertEquals((byte) 0, actualWriteResult[14]);
    assertEquals((byte) 0, actualWriteResult[9]);
    assertEquals((byte) 3, actualWriteResult[5]);
    assertEquals((byte) 7, actualWriteResult[2]);
    assertEquals(81, actualWriteResult.length);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[56]);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[80]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return array of {@code byte} with one and minus forty-seven.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnArrayOfByteWithOneAndMinusFortySeven()
      throws HANAWKBWriterException {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point geometry = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertArrayEquals(
        new byte[] {
          1,
          -47,
          7,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          -8,
          Byte.MAX_VALUE
        },
        HANAWKBWriter.write(geometry, XyzmMode.XYM));
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return array of {@code byte} with one and minus twenty-three.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnArrayOfByteWithOneAndMinusTwentyThree()
      throws HANAWKBWriterException {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point geometry = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertArrayEquals(
        new byte[] {
          1,
          -23,
          3,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          -8,
          Byte.MAX_VALUE
        },
        HANAWKBWriter.write(geometry, XyzmMode.XYZ));
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return array of {@code byte} with one and one.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnArrayOfByteWithOneAndOne() throws HANAWKBWriterException {
    // Arrange
    Coordinate coordinate = new Coordinate();
    Point geometry = new Point(coordinate, new PrecisionModel(), 1);

    // Act and Assert
    assertArrayEquals(
        new byte[] {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        HANAWKBWriter.write(geometry, XyzmMode.XY));
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return array of {@code byte} with one and seven.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnArrayOfByteWithOneAndSeven() throws HANAWKBWriterException {
    // Arrange
    Polygon polygon = new Polygon(null, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act and Assert
    assertArrayEquals(
        new byte[] {
          1, 7, 0, 0, 0, 1, 0, 0, 0, 1, 6, 0, 0, 0, 1, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0
        },
        HANAWKBWriter.write(geometry, XyzmMode.XY));
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return array of {@code byte} with one and two.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnArrayOfByteWithOneAndTwo() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString geometry = new LineString(points, new GeometryFactory());

    // Act and Assert
    assertArrayEquals(
        new byte[] {
          1, 2, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        },
        HANAWKBWriter.write(geometry, XyzmMode.XY));
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return forty-second element is zero.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnFortySecondElementIsZero() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {lineString};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XY);

    // Assert
    assertEquals((byte) 0, actualWriteResult[41]);
    assertEquals((byte) 0, actualWriteResult[42]);
    assertEquals((byte) 0, actualWriteResult[43]);
    assertEquals((byte) 0, actualWriteResult[44]);
    assertEquals((byte) 2, actualWriteResult[10]);
    assertEquals((byte) 3, actualWriteResult[14]);
    assertEquals(66, actualWriteResult.length);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return forty-sixth element is zero.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnFortySixthElementIsZero() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {polygon};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XY);

    // Assert
    assertEquals((byte) 0, actualWriteResult[45]);
    assertEquals((byte) 0, actualWriteResult[46]);
    assertEquals((byte) 0, actualWriteResult[47]);
    assertEquals((byte) 0, actualWriteResult[48]);
    assertEquals((byte) 0, actualWriteResult[49]);
    assertEquals((byte) 3, actualWriteResult[10]);
    assertEquals((byte) 3, actualWriteResult[18]);
    assertEquals(70, actualWriteResult.length);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return one hundred second element is zero.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnOneHundredSecondElementIsZero() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    CoordinateArraySequence points2 = new CoordinateArraySequence(3);
    LinearRing linearRing = new LinearRing(points2, new GeometryFactory());
    LinearRing[] holes = new LinearRing[] {linearRing};

    Polygon polygon = new Polygon(shell, holes, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {polygon};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XY);

    // Assert
    assertEquals((byte) 0, actualWriteResult[101]);
    assertEquals((byte) 0, actualWriteResult[102]);
    assertEquals((byte) 0, actualWriteResult[103]);
    assertEquals((byte) 0, actualWriteResult[104]);
    assertEquals((byte) 0, actualWriteResult[105]);
    assertEquals((byte) 0, actualWriteResult[106]);
    assertEquals((byte) 0, actualWriteResult[107]);
    assertEquals((byte) 0, actualWriteResult[108]);
    assertEquals((byte) 0, actualWriteResult[109]);
    assertEquals((byte) 0, actualWriteResult[110]);
    assertEquals((byte) 0, actualWriteResult[111]);
    assertEquals((byte) 0, actualWriteResult[112]);
    assertEquals((byte) 0, actualWriteResult[113]);
    assertEquals((byte) 0, actualWriteResult[114]);
    assertEquals((byte) 0, actualWriteResult[115]);
    assertEquals((byte) 0, actualWriteResult[116]);
    assertEquals((byte) 0, actualWriteResult[117]);
    assertEquals((byte) 0, actualWriteResult[118]);
    assertEquals((byte) 0, actualWriteResult[119]);
    assertEquals((byte) 0, actualWriteResult[120]);
    assertEquals((byte) 0, actualWriteResult[121]);
    assertEquals(122, actualWriteResult.length);
    assertEquals((byte) 2, actualWriteResult[14]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return second element is minus forty-one.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnSecondElementIsMinusFortyOne() throws HANAWKBWriterException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getM(anyInt())).thenReturn(10.0d);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XYM);

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getM(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals((byte) -41, actualWriteResult[1]);
    assertEquals((byte) -42, actualWriteResult[10]);
    assertEquals((byte) -45, actualWriteResult[19]);
    assertEquals(103, actualWriteResult.length);
    assertEquals((byte) 7, actualWriteResult[11]);
    assertEquals((byte) 7, actualWriteResult[20]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return second element is minus forty-six.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnSecondElementIsMinusFortySix() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString geometry = new LineString(points, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XYM);

    // Assert
    assertEquals((byte) -46, actualWriteResult[1]);
    assertEquals((byte) -8, actualWriteResult[79]);
    assertEquals((byte) 0, actualWriteResult[10]);
    assertEquals((byte) 0, actualWriteResult[14]);
    assertEquals((byte) 0, actualWriteResult[9]);
    assertEquals((byte) 3, actualWriteResult[5]);
    assertEquals((byte) 7, actualWriteResult[2]);
    assertEquals(81, actualWriteResult.length);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[56]);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[80]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return second element is minus seventeen.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnSecondElementIsMinusSeventeen() throws HANAWKBWriterException {
    // Arrange
    CoordinateSequence points = mock(CoordinateSequence.class);
    when(points.getX(anyInt())).thenReturn(2.0d);
    when(points.getY(anyInt())).thenReturn(3.0d);
    when(points.getZ(anyInt())).thenReturn(10.0d);
    when(points.getCoordinate(anyInt())).thenReturn(new Coordinate());
    when(points.size()).thenReturn(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new PrecisionModel(), 1);
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XYZ);

    // Assert
    verify(points, atLeast(1)).getCoordinate(anyInt());
    verify(points, atLeast(1)).getX(anyInt());
    verify(points, atLeast(1)).getY(anyInt());
    verify(points, atLeast(1)).getZ(anyInt());
    verify(points, atLeast(1)).size();
    assertEquals((byte) -17, actualWriteResult[1]);
    assertEquals((byte) -18, actualWriteResult[10]);
    assertEquals((byte) -21, actualWriteResult[19]);
    assertEquals(103, actualWriteResult.length);
    assertEquals((byte) 3, actualWriteResult[11]);
    assertEquals((byte) 3, actualWriteResult[20]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return second element is minus twenty-two.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnSecondElementIsMinusTwentyTwo() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString geometry = new LineString(points, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XYZ);

    // Assert
    assertEquals((byte) -22, actualWriteResult[1]);
    assertEquals((byte) -8, actualWriteResult[79]);
    assertEquals((byte) 0, actualWriteResult[10]);
    assertEquals((byte) 0, actualWriteResult[14]);
    assertEquals((byte) 0, actualWriteResult[9]);
    assertEquals((byte) 3, actualWriteResult[2]);
    assertEquals((byte) 3, actualWriteResult[5]);
    assertEquals(81, actualWriteResult.length);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[56]);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[80]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return twentieth element is two.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnTwentiethElementIsTwo() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LineString lineString = new LineString(points, new GeometryFactory());
    LineString[] lineStrings = new LineString[] {lineString};
    MultiLineString multiLineString = new MultiLineString(lineStrings, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {multiLineString};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XY);

    // Assert
    assertEquals((byte) 2, actualWriteResult[19]);
    assertEquals((byte) 3, actualWriteResult[23]);
    assertEquals((byte) 5, actualWriteResult[10]);
    assertEquals(75, actualWriteResult.length);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>Then return twenty-fourth element is one.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_thenReturnTwentyFourthElementIsOne() throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3);
    LinearRing shell = new LinearRing(points, new GeometryFactory());
    Polygon polygon = new Polygon(shell, new PrecisionModel(), 1);
    Polygon[] polygons = new Polygon[] {polygon};
    MultiPolygon multiPolygon = new MultiPolygon(polygons, new GeometryFactory());
    Geometry[] geometries = new Geometry[] {multiPolygon};
    GeometryCollection geometry = new GeometryCollection(geometries, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XY);

    // Assert
    assertEquals((byte) 1, actualWriteResult[23]);
    assertEquals((byte) 3, actualWriteResult[19]);
    assertEquals((byte) 6, actualWriteResult[10]);
    assertEquals(79, actualWriteResult.length);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>When {@link CoordinateArraySequence#CoordinateArraySequence(int, int)} with size is three
   *       and dimension is one.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_whenCoordinateArraySequenceWithSizeIsThreeAndDimensionIsOne()
      throws HANAWKBWriterException {
    // Arrange
    CoordinateArraySequence points = new CoordinateArraySequence(3, 1);
    LineString geometry = new LineString(points, new GeometryFactory());

    // Act
    byte[] actualWriteResult = HANAWKBWriter.write(geometry, XyzmMode.XYZ);

    // Assert
    assertEquals((byte) -22, actualWriteResult[1]);
    assertEquals((byte) -8, actualWriteResult[79]);
    assertEquals((byte) 0, actualWriteResult[10]);
    assertEquals((byte) 0, actualWriteResult[14]);
    assertEquals((byte) 0, actualWriteResult[9]);
    assertEquals((byte) 3, actualWriteResult[2]);
    assertEquals((byte) 3, actualWriteResult[5]);
    assertEquals(81, actualWriteResult.length);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[56]);
    assertEquals(Byte.MAX_VALUE, actualWriteResult[80]);
  }

  /**
   * Test {@link HANAWKBWriter#write(Geometry, XyzmMode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link HANAWKBWriter#write(Geometry, XyzmMode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] HANAWKBWriter.write(Geometry, XyzmMode)"})
  public void testWrite_whenNull_thenReturnNull() throws HANAWKBWriterException {
    // Arrange, Act and Assert
    assertNull(HANAWKBWriter.write(null, XyzmMode.XY));
  }
}
