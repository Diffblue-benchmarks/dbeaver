package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBIconCompositeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBIconComposite#DBIconComposite(DBPImage, boolean, DBPImage, DBPImage, DBPImage,
   *       DBPImage)}
   *   <li>{@link DBIconComposite#setBottomLeft(DBPImage)}
   *   <li>{@link DBIconComposite#setBottomRight(DBPImage)}
   *   <li>{@link DBIconComposite#setTopLeft(DBPImage)}
   *   <li>{@link DBIconComposite#setTopRight(DBPImage)}
   *   <li>{@link DBIconComposite#getBottomLeft()}
   *   <li>{@link DBIconComposite#getBottomRight()}
   *   <li>{@link DBIconComposite#getMain()}
   *   <li>{@link DBIconComposite#getTopLeft()}
   *   <li>{@link DBIconComposite#getTopRight()}
   *   <li>{@link DBIconComposite#isDisabled()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBIconComposite.<init>(DBPImage, boolean, DBPImage, DBPImage, DBPImage, DBPImage)",
    "DBPImage DBIconComposite.getBottomLeft()",
    "DBPImage DBIconComposite.getBottomRight()",
    "DBPImage DBIconComposite.getMain()",
    "DBPImage DBIconComposite.getTopLeft()",
    "DBPImage DBIconComposite.getTopRight()",
    "boolean DBIconComposite.isDisabled()",
    "void DBIconComposite.setBottomLeft(DBPImage)",
    "void DBIconComposite.setBottomRight(DBPImage)",
    "void DBIconComposite.setTopLeft(DBPImage)",
    "void DBIconComposite.setTopRight(DBPImage)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPImage main = mock(DBPImage.class);

    // Act
    DBIconComposite actualDbIconComposite =
        new DBIconComposite(
            main,
            true,
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class));
    DBPImage bottomLeft = mock(DBPImage.class);
    actualDbIconComposite.setBottomLeft(bottomLeft);
    DBPImage bottomRight = mock(DBPImage.class);
    actualDbIconComposite.setBottomRight(bottomRight);
    DBPImage topLeft = mock(DBPImage.class);
    actualDbIconComposite.setTopLeft(topLeft);
    DBPImage topRight = mock(DBPImage.class);
    actualDbIconComposite.setTopRight(topRight);
    DBPImage actualBottomLeft = actualDbIconComposite.getBottomLeft();
    DBPImage actualBottomRight = actualDbIconComposite.getBottomRight();
    DBPImage actualMain = actualDbIconComposite.getMain();
    DBPImage actualTopLeft = actualDbIconComposite.getTopLeft();
    DBPImage actualTopRight = actualDbIconComposite.getTopRight();

    // Assert
    assertTrue(actualDbIconComposite.isDisabled());
    assertSame(bottomLeft, actualBottomLeft);
    assertSame(bottomRight, actualBottomRight);
    assertSame(main, actualMain);
    assertSame(topLeft, actualTopLeft);
    assertSame(topRight, actualTopRight);
  }

  /**
   * Test {@link DBIconComposite#hasOverlays()}.
   *
   * <p>Method under test: {@link DBIconComposite#hasOverlays()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIconComposite.hasOverlays()"})
  public void testHasOverlays() {
    // Arrange, Act and Assert
    assertTrue(
        new DBIconComposite(
                mock(DBPImage.class),
                true,
                mock(DBPImage.class),
                mock(DBPImage.class),
                mock(DBPImage.class),
                mock(DBPImage.class))
            .hasOverlays());
  }

  /**
   * Test {@link DBIconComposite#hasOverlays()}.
   *
   * <p>Method under test: {@link DBIconComposite#hasOverlays()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIconComposite.hasOverlays()"})
  public void testHasOverlays2() {
    // Arrange
    DBIconComposite dbIconComposite =
        new DBIconComposite(mock(DBPImage.class), true, null, null, null, mock(DBPImage.class));

    // Act and Assert
    assertTrue(dbIconComposite.hasOverlays());
  }

  /**
   * Test {@link DBIconComposite#hasOverlays()}.
   *
   * <p>Method under test: {@link DBIconComposite#hasOverlays()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIconComposite.hasOverlays()"})
  public void testHasOverlays3() {
    // Arrange
    DBIconComposite dbIconComposite =
        new DBIconComposite(mock(DBPImage.class), true, null, null, mock(DBPImage.class), null);

    // Act and Assert
    assertTrue(dbIconComposite.hasOverlays());
  }

  /**
   * Test {@link DBIconComposite#hasOverlays()}.
   *
   * <p>Method under test: {@link DBIconComposite#hasOverlays()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIconComposite.hasOverlays()"})
  public void testHasOverlays4() {
    // Arrange, Act and Assert
    assertTrue(
        new DBIconComposite(mock(DBPImage.class), true, null, mock(DBPImage.class), null, null)
            .hasOverlays());
  }

  /**
   * Test {@link DBIconComposite#hasOverlays()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBIconComposite#hasOverlays()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIconComposite.hasOverlays()"})
  public void testHasOverlays_thenReturnFalse() {
    // Arrange
    DBIconComposite dbIconComposite =
        new DBIconComposite(mock(DBPImage.class), true, null, null, null, null);

    // Act and Assert
    assertFalse(dbIconComposite.hasOverlays());
  }

  /**
   * Test {@link DBIconComposite#getLocation()}.
   *
   * <p>Method under test: {@link DBIconComposite#getLocation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBIconComposite.getLocation()"})
  public void testGetLocation() {
    // Arrange
    DBPImage main = mock(DBPImage.class);
    when(main.getLocation()).thenReturn("Location");

    // Act
    String actualLocation =
        new DBIconComposite(
                main,
                true,
                mock(DBPImage.class),
                mock(DBPImage.class),
                mock(DBPImage.class),
                mock(DBPImage.class))
            .getLocation();

    // Assert
    verify(main).getLocation();
    assertEquals("Location", actualLocation);
  }
}
