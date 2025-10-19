package org.jkiss.dbeaver.model.gis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.cugos.wkg.Coordinate;
import org.cugos.wkg.Coordinate.Builder;
import org.cugos.wkg.Dimension;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CircularArcDiffblueTest {
  /**
   * Test {@link CircularArc#CircularArc(Coordinate[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#CircularArc(Coordinate[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CircularArc.<init>(Coordinate[])"})
  public void testNewCircularArc_thenThrowIllegalArgumentException() {
    // Arrange
    Coordinate[] controlPoints = new Coordinate[] {Coordinate.createEmpty()};

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CircularArc(controlPoints));
  }

  /**
   * Test {@link CircularArc#CircularArc(Coordinate[])}.
   *
   * <ul>
   *   <li>When {@link Builder} (default constructor) M is ten X is two Y is three Z is ten build.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#CircularArc(Coordinate[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CircularArc.<init>(Coordinate[])"})
  public void testNewCircularArc_whenBuilderMIsTenXIsTwoYIsThreeZIsTenBuild_thenDoesNotThrow() {
    // Arrange
    Coordinate coordinate = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate coordinate2 = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate coordinate3 = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    // Act
    new CircularArc(new Coordinate[] {coordinate, coordinate2, coordinate3});

    // Assert
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return first Empty.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnFirstEmpty() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(Double.NaN).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(0);
    assertTrue(getResult.isEmpty());
    Coordinate expectedGetResult = actualLinearizeResult.get(1);
    assertEquals(expectedGetResult, actualLinearizeResult.get(2));
    assertEquals(Double.NaN, getResult.getX(), 0.0);
    assertEquals(Double.NaN, getResult.getY(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return first M is ten.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnFirstMIsTen() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(0);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    assertEquals(getResult, actualLinearizeResult.get(1));
    assertEquals(getResult, actualLinearizeResult.get(2));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return first X is {@code 1.0E-12}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnFirstXIs10e12() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    assertEquals(1.0E-12d, actualLinearizeResult.get(0).getX(), 0.0);
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    assertEquals(getResult, actualLinearizeResult.get(2));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return first X is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnFirstXIsPositive_infinity() {
    // Arrange
    Coordinate start =
        new Builder().setM(10.0d).setX(Double.POSITIVE_INFINITY).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    assertEquals(getResult, actualLinearizeResult.get(2));
    assertEquals(Double.POSITIVE_INFINITY, actualLinearizeResult.get(0).getX(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return first Y is {@code 1.0E-12}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnFirstYIs10e12() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(1.0E-12d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    assertEquals(1.0E-12d, actualLinearizeResult.get(0).getY(), 0.0);
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    assertEquals(getResult, actualLinearizeResult.get(2));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return fourth X is {@code -0.002502927619489448}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnFourthXIs0002502927619489448() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate end =
        new Builder().setM(10.0d).setX(2.0d).setY(3.141592653589793d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(6, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(3);
    assertEquals(-0.002502927619489448d, getResult.getX(), 0.0);
    Coordinate getResult2 = actualLinearizeResult.get(1);
    assertEquals(1.0000000000004998d, getResult2.getX(), 0.0);
    Coordinate getResult3 = actualLinearizeResult.get(4);
    assertEquals(1.0000000000005d, getResult3.getX(), 0.0);
    assertEquals(2.068293399174907d, getResult2.getY(), 0.0);
    assertEquals(3.0707963267948966d, getResult.getY(), 0.0);
    assertEquals(3.141592653589793d, actualLinearizeResult.get(5).getY(), 0.0);
    assertEquals(4.073299254414886d, getResult3.getY(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return second X is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnSecondXIs05() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(0.5d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    assertEquals(0.5d, actualLinearizeResult.get(1).getX(), 0.0);
    Coordinate getResult = actualLinearizeResult.get(2);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return second X is {@code 1.0E-12}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnSecondXIs10e12() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(1.0E-12d, getResult.getX(), 0.0);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(2));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return second X is {@code 1.0000000000005}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnSecondXIs10000000000005() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(1.0E-12d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(5, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(1.0000000000005d, getResult.getX(), 0.0);
    Coordinate getResult2 = actualLinearizeResult.get(4);
    assertEquals(1.0E-12d, getResult2.getY(), 0.0);
    Coordinate getResult3 = actualLinearizeResult.get(3);
    assertEquals(1.5000000000005d, getResult3.getY(), 0.0);
    assertEquals(2.0d, getResult2.getX(), 0.0);
    assertEquals(2.802775637731801d, getResult3.getX(), 0.0);
    assertEquals(3.302775637731801d, getResult.getY(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return second X is {@code -0.11803398874805304}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnSecondXIs011803398874805304() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid =
        new Builder().setM(10.0d).setX(2.0d).setY(3.9999999999959996d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(6, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(-0.11803398874805304d, getResult.getX(), 0.0);
    Coordinate getResult2 = actualLinearizeResult.get(2);
    assertEquals(1.0000000000005d, getResult2.getX(), 0.0);
    Coordinate getResult3 = actualLinearizeResult.get(4);
    assertEquals(2.118033988749053d, getResult3.getX(), 0.0);
    assertEquals(3.499999999998d, getResult.getY(), 0.0);
    assertEquals(3.499999999998d, getResult3.getY(), 0.0);
    assertEquals(3.9999999999959996d, actualLinearizeResult.get(3).getY(), 0.0);
    assertEquals(4.618033988746553d, getResult2.getY(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return second Y is minus one.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnSecondYIsMinusOne() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(10.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(5, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(-1.0d, getResult.getY(), 0.0);
    Coordinate getResult2 = actualLinearizeResult.get(2);
    assertEquals(10.0d, getResult2.getX(), 0.0);
    assertEquals(2.999999999999999d, getResult2.getY(), 0.0);
    assertEquals(5.999999999999999d, getResult.getX(), 0.0);
    Coordinate getResult3 = actualLinearizeResult.get(3);
    assertEquals(6.000000000000001d, getResult3.getX(), 0.0);
    assertEquals(7.0d, getResult3.getY(), 0.0);
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(4));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return size is two hundred fifty-eight.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnSizeIsTwoHundredFiftyEight() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(20000.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(258, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(5);
    assertEquals(-1222.9231421009356d, getResult.getX(), 0.0);
    Coordinate getResult2 = actualLinearizeResult.get(1);
    assertEquals(-244.3754746137088d, getResult2.getX(), 0.0);
    Coordinate getResult3 = actualLinearizeResult.get(2);
    assertEquals(-489.6031442165646d, getResult3.getX(), 0.0);
    Coordinate getResult4 = actualLinearizeResult.get(3);
    assertEquals(-734.5352928301775d, getResult4.getX(), 0.0);
    Coordinate getResult5 = actualLinearizeResult.get(4);
    assertEquals(-979.0243824868256d, getResult5.getX(), 0.0);
    Coordinate getResult6 = actualLinearizeResult.get(252);
    assertEquals(1224.923142100812d, getResult6.getX(), 0.0);
    Coordinate getResult7 = actualLinearizeResult.get(255);
    assertEquals(15.043581185320363d, getResult7.getY(), 0.0);
    assertEquals(15.04358118532582d, getResult3.getY(), 0.0);
    Coordinate getResult8 = actualLinearizeResult.get(257);
    assertEquals(2.0d, getResult8.getX(), 0.0);
    Coordinate getResult9 = actualLinearizeResult.get(256);
    assertEquals(246.37547461358426d, getResult9.getX(), 0.0);
    assertEquals(3.0d, getResult8.getY(), 0.0);
    Coordinate getResult10 = actualLinearizeResult.get(254);
    assertEquals(30.091319026112615d, getResult10.getY(), 0.0);
    assertEquals(30.09131902612171d, getResult4.getY(), 0.0);
    assertEquals(491.60314421644017d, getResult7.getX(), 0.0);
    Coordinate getResult11 = actualLinearizeResult.get(253);
    assertEquals(51.14546060133944d, getResult11.getY(), 0.0);
    assertEquals(51.14546060135035d, getResult5.getY(), 0.0);
    assertEquals(6.011311273563479d, getResult9.getY(), 0.0);
    assertEquals(6.011311273567117d, getResult2.getY(), 0.0);
    assertEquals(736.5352928300532d, getResult10.getX(), 0.0);
    assertEquals(78.19332368337746d, getResult6.getY(), 0.0);
    assertEquals(78.19332368339201d, getResult.getY(), 0.0);
    assertEquals(981.0243824867016d, getResult11.getX(), 0.0);
    assertEquals(Dimension.Two, getResult6.getDimension());
    assertEquals(Dimension.Two, getResult11.getDimension());
    assertEquals(Dimension.Two, getResult10.getDimension());
    assertEquals(Dimension.Two, getResult7.getDimension());
    assertEquals(Dimension.Two, getResult9.getDimension());
    assertEquals(Dimension.Two, getResult8.getDimension());
    assertFalse(getResult6.isEmpty());
    assertFalse(getResult11.isEmpty());
    assertFalse(getResult10.isEmpty());
    assertFalse(getResult7.isEmpty());
    assertFalse(getResult9.isEmpty());
    assertFalse(getResult8.isEmpty());
    assertEquals(Double.NaN, getResult6.getM(), 0.0);
    assertEquals(Double.NaN, getResult11.getM(), 0.0);
    assertEquals(Double.NaN, getResult10.getM(), 0.0);
    assertEquals(Double.NaN, getResult7.getM(), 0.0);
    assertEquals(Double.NaN, getResult9.getM(), 0.0);
    assertEquals(Double.NaN, getResult8.getM(), 0.0);
    assertEquals(Double.NaN, getResult6.getZ(), 0.0);
    assertEquals(Double.NaN, getResult11.getZ(), 0.0);
    assertEquals(Double.NaN, getResult10.getZ(), 0.0);
    assertEquals(Double.NaN, getResult7.getZ(), 0.0);
    assertEquals(Double.NaN, getResult9.getZ(), 0.0);
    assertEquals(Double.NaN, getResult8.getZ(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return third X is {@code 1.0E-12}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnThirdXIs10e12() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(2);
    assertEquals(1.0E-12d, getResult.getX(), 0.0);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(1));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return third Y is {@code -0.3027756377308011}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnThirdYIs03027756377308011() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(1.0E-12d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(6, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(2);
    assertEquals(-0.3027756377308011d, getResult.getY(), 0.0);
    Coordinate getResult2 = actualLinearizeResult.get(1);
    assertEquals(-0.8027756377308011d, getResult2.getX(), 0.0);
    assertEquals(1.0000000000004998d, getResult.getX(), 0.0);
    assertEquals(1.0E-12d, actualLinearizeResult.get(3).getY(), 0.0);
    Coordinate getResult3 = actualLinearizeResult.get(4);
    assertEquals(1.5000000000004996d, getResult3.getY(), 0.0);
    assertEquals(1.5000000000005003d, getResult2.getY(), 0.0);
    assertEquals(2.802775637731801d, getResult3.getX(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>Then return third Y is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_thenReturnThirdYIsPositive_infinity() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end =
        new Builder().setM(10.0d).setX(2.0d).setY(Double.POSITIVE_INFINITY).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(10.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(2);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(1));
    assertEquals(Double.POSITIVE_INFINITY, getResult.getY(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return second X is {@code 1.0E-12}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_when05_thenReturnSecondXIs10e12() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(0.5d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(1.0E-12d, getResult.getX(), 0.0);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(2));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return size is seventeen.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_when05_thenReturnSizeIsSeventeen() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(10.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(0.5d);

    // Assert
    assertEquals(17, actualLinearizeResult.size());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(Short.SIZE));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_when05_thenThrowIllegalArgumentException() {
    // Arrange
    Coordinate start =
        new Builder().setM(10.0d).setX(Double.POSITIVE_INFINITY).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> circularArc.linearize(-0.5d));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>When {@code 1.0E-12}.
   *   <li>Then return second X is {@code 1.0E-12}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_when10e12_thenReturnSecondXIs10e12() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(1.0E-12d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(1.0E-12d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(1.0E-12d, getResult.getX(), 0.0);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(2));
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return first X is {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_whenZero_thenReturnFirstXIsPositive_infinity() {
    // Arrange
    Coordinate start =
        new Builder().setM(10.0d).setX(Double.POSITIVE_INFINITY).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(0.0d);

    // Assert
    assertEquals(3, actualLinearizeResult.size());
    Coordinate getResult = actualLinearizeResult.get(1);
    assertEquals(10.0d, getResult.getM(), 0.0);
    assertEquals(10.0d, getResult.getZ(), 0.0);
    assertEquals(2.0d, getResult.getX(), 0.0);
    assertEquals(3.0d, getResult.getY(), 0.0);
    assertEquals(Dimension.ThreeMeasured, getResult.getDimension());
    assertEquals(getResult, actualLinearizeResult.get(2));
    assertEquals(Double.POSITIVE_INFINITY, actualLinearizeResult.get(0).getX(), 0.0);
  }

  /**
   * Test {@link CircularArc#linearize(double)} with {@code tolerance}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return size is forty-nine.
   * </ul>
   *
   * <p>Method under test: {@link CircularArc#linearize(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List CircularArc.linearize(double)"})
  public void testLinearizeWithTolerance_whenZero_thenReturnSizeIsFortyNine() {
    // Arrange
    Coordinate start = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate mid = new Builder().setM(10.0d).setX(10.0d).setY(3.0d).setZ(10.0d).build();
    Coordinate end = new Builder().setM(10.0d).setX(2.0d).setY(3.0d).setZ(10.0d).build();

    CircularArc circularArc = new CircularArc(start, mid, end);

    // Act
    List<Coordinate> actualLinearizeResult = circularArc.linearize(0.0d);

    // Assert
    assertEquals(49, actualLinearizeResult.size());
    Coordinate expectedGetResult = actualLinearizeResult.get(0);
    assertEquals(expectedGetResult, actualLinearizeResult.get(48));
  }
}
