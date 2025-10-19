package org.jkiss.dbeaver.model.gis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GisExpressionFunctionsDiffblueTest {
  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>Then return String is {@code POINT(Longitude Latitude)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_thenReturnStringIsPointLongitudeLatitude() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint("Longitude", "Latitude", "Srid");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(Longitude Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(Longitude Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When {@code 1}.
   *   <li>Then return SRID is one.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_when1_thenReturnSridIsOne() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, "Latitude", "1");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertEquals(1, ((DBGeometry) actualWktPointResult).getSRID());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return String is {@code POINT(65 Latitude)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenA_thenReturnStringIsPoint65Latitude() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint((byte) 'A', "Latitude", "Srid");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(65 Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(65 Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return String is {@code POINT(0.0 42)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenFortyTwo_thenReturnStringIsPoint0042() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, 42, null);

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(0.0 42)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(0.0 42)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenNull_thenReturnNull() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(null, null, null);

    // Assert
    assertNull(actualWktPointResult);
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenNull_thenReturnNull2() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, null, null);

    // Assert
    assertNull(actualWktPointResult);
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenNull_thenReturnNull3() {
    // Arrange, Act and Assert
    assertNull(GisExpressionFunctions.wktPoint(0.0d, 0.0d, null));
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return String is {@code POINT(0.0 Latitude)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenNull_thenReturnStringIsPoint00Latitude() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, "Latitude", null);

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When one.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenOne() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint("Longitude", "Latitude", 1);

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(Longitude Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(Longitude Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertEquals(1, ((DBGeometry) actualWktPointResult).getSRID());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object, Object)} with {@code longitude},
   * {@code latitude}, {@code srid}.
   *
   * <ul>
   *   <li>When space.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object, Object)"})
  public void testWktPointWithLongitudeLatitudeSrid_whenSpace() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, "Latitude", " ");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>Then return String is {@code POINT(Longitude Latitude)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_thenReturnStringIsPointLongitudeLatitude() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint("Longitude", "Latitude");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(Longitude Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(Longitude Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return String is {@code POINT(65 Latitude)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_whenA_thenReturnStringIsPoint65Latitude() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint((byte) 'A', "Latitude");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(65 Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(65 Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>When {@code Latitude}.
   *   <li>Then return String is {@code POINT(0.0 Latitude)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_whenLatitude_thenReturnStringIsPoint00Latitude() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, "Latitude");

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(0.0 Latitude)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GisExpressionFunctions.wktPoint(null, null));
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertNull(GisExpressionFunctions.wktPoint(0.0d, null));
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>When {@link GisConstants#SRID_4326}.
   *   <li>Then return String is {@code POINT(0.0 4326)}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_whenSrid_4326_thenReturnStringIsPoint004326() {
    // Arrange and Act
    Object actualWktPointResult = GisExpressionFunctions.wktPoint(0.0d, GisConstants.SRID_4326);

    // Assert
    assertTrue(actualWktPointResult instanceof DBGeometry);
    assertEquals("POINT(0.0 4326)", ((DBGeometry) actualWktPointResult).getString());
    assertEquals("POINT(0.0 4326)", ((DBGeometry) actualWktPointResult).getRawValue());
    assertNull(((DBGeometry) actualWktPointResult).getProperties());
    assertNull(((DBGeometry) actualWktPointResult).getGeometry());
    assertFalse(((DBGeometry) actualWktPointResult).isEmpty());
    assertFalse(((DBGeometry) actualWktPointResult).isModified());
    assertFalse(((DBGeometry) actualWktPointResult).isNull());
    assertEquals(GisConstants.SRID_4326, ((DBGeometry) actualWktPointResult).getSRID());
  }

  /**
   * Test {@link GisExpressionFunctions#wktPoint(Object, Object)} with {@code longitude}, {@code
   * latitude}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link GisExpressionFunctions#wktPoint(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object GisExpressionFunctions.wktPoint(Object, Object)"})
  public void testWktPointWithLongitudeLatitude_whenZero_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(GisExpressionFunctions.wktPoint(0.0d, 0.0d));
  }
}
