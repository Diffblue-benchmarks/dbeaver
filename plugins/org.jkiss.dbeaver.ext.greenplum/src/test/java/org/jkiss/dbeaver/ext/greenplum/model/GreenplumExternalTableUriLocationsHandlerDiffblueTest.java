package org.jkiss.dbeaver.ext.greenplum.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GreenplumExternalTableUriLocationsHandlerDiffblueTest {
  /**
   * Test {@link
   * GreenplumExternalTableUriLocationsHandler#GreenplumExternalTableUriLocationsHandler(String,
   * char)}.
   *
   * <p>Method under test: {@link
   * GreenplumExternalTableUriLocationsHandler#GreenplumExternalTableUriLocationsHandler(String,
   * char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GreenplumExternalTableUriLocationsHandler.<init>(String, char)"})
  public void testNewGreenplumExternalTableUriLocationsHandler() {
    // Arrange and Act
    GreenplumExternalTableUriLocationsHandler actualGreenplumExternalTableUriLocationsHandler =
        new GreenplumExternalTableUriLocationsHandler("Uri Locations String", 'A');

    // Assert
    Stream<String> streamResult = actualGreenplumExternalTableUriLocationsHandler.stream();
    List<String> collectResult = streamResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertEquals("Uri Locations String", collectResult.get(0));
    assertEquals(
        "Uri Locations String",
        actualGreenplumExternalTableUriLocationsHandler.getCommaSeparatedList());
    assertEquals(
        "Uri Locations String",
        actualGreenplumExternalTableUriLocationsHandler.getLineFeedSeparatedList());
  }

  /**
   * Test {@link
   * GreenplumExternalTableUriLocationsHandler#GreenplumExternalTableUriLocationsHandler(String,
   * char)}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * GreenplumExternalTableUriLocationsHandler#GreenplumExternalTableUriLocationsHandler(String,
   * char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GreenplumExternalTableUriLocationsHandler.<init>(String, char)"})
  public void testNewGreenplumExternalTableUriLocationsHandler_whenEmptyString() {
    // Arrange and Act
    GreenplumExternalTableUriLocationsHandler actualGreenplumExternalTableUriLocationsHandler =
        new GreenplumExternalTableUriLocationsHandler("", 'A');

    // Assert
    assertEquals("", actualGreenplumExternalTableUriLocationsHandler.getCommaSeparatedList());
    assertEquals("", actualGreenplumExternalTableUriLocationsHandler.getLineFeedSeparatedList());
    Stream<String> streamResult = actualGreenplumExternalTableUriLocationsHandler.stream();
    assertTrue(streamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link
   * GreenplumExternalTableUriLocationsHandler#GreenplumExternalTableUriLocationsHandler(String,
   * char)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GreenplumExternalTableUriLocationsHandler#GreenplumExternalTableUriLocationsHandler(String,
   * char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GreenplumExternalTableUriLocationsHandler.<init>(String, char)"})
  public void testNewGreenplumExternalTableUriLocationsHandler_whenNull() {
    // Arrange and Act
    GreenplumExternalTableUriLocationsHandler actualGreenplumExternalTableUriLocationsHandler =
        new GreenplumExternalTableUriLocationsHandler(null, 'A');

    // Assert
    assertEquals("", actualGreenplumExternalTableUriLocationsHandler.getCommaSeparatedList());
    assertEquals("", actualGreenplumExternalTableUriLocationsHandler.getLineFeedSeparatedList());
    Stream<String> streamResult = actualGreenplumExternalTableUriLocationsHandler.stream();
    assertTrue(streamResult.limit(5).collect(Collectors.toList()).isEmpty());
  }

  /**
   * Test {@link GreenplumExternalTableUriLocationsHandler#getCommaSeparatedList()}.
   *
   * <ul>
   *   <li>Then return {@code Uri Loc,tions String}.
   * </ul>
   *
   * <p>Method under test: {@link GreenplumExternalTableUriLocationsHandler#getCommaSeparatedList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GreenplumExternalTableUriLocationsHandler.getCommaSeparatedList()"})
  public void testGetCommaSeparatedList_thenReturnUriLocTionsString() {
    // Arrange, Act and Assert
    assertEquals(
        "Uri Loc,tions String",
        new GreenplumExternalTableUriLocationsHandler("Uri Locations String", 'a')
            .getCommaSeparatedList());
  }

  /**
   * Test {@link GreenplumExternalTableUriLocationsHandler#getCommaSeparatedList()}.
   *
   * <ul>
   *   <li>Then return {@code Uri Locations String}.
   * </ul>
   *
   * <p>Method under test: {@link GreenplumExternalTableUriLocationsHandler#getCommaSeparatedList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GreenplumExternalTableUriLocationsHandler.getCommaSeparatedList()"})
  public void testGetCommaSeparatedList_thenReturnUriLocationsString() {
    // Arrange, Act and Assert
    assertEquals(
        "Uri Locations String",
        new GreenplumExternalTableUriLocationsHandler("Uri Locations String", 'A')
            .getCommaSeparatedList());
  }

  /**
   * Test {@link GreenplumExternalTableUriLocationsHandler#getLineFeedSeparatedList()}.
   *
   * <ul>
   *   <li>Then return {@code Uri Loc tions String}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GreenplumExternalTableUriLocationsHandler#getLineFeedSeparatedList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GreenplumExternalTableUriLocationsHandler.getLineFeedSeparatedList()"})
  public void testGetLineFeedSeparatedList_thenReturnUriLocTionsString() {
    // Arrange, Act and Assert
    assertEquals(
        "Uri Loc\ntions String",
        new GreenplumExternalTableUriLocationsHandler("Uri Locations String", 'a')
            .getLineFeedSeparatedList());
  }

  /**
   * Test {@link GreenplumExternalTableUriLocationsHandler#getLineFeedSeparatedList()}.
   *
   * <ul>
   *   <li>Then return {@code Uri Locations String}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GreenplumExternalTableUriLocationsHandler#getLineFeedSeparatedList()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String GreenplumExternalTableUriLocationsHandler.getLineFeedSeparatedList()"})
  public void testGetLineFeedSeparatedList_thenReturnUriLocationsString() {
    // Arrange, Act and Assert
    assertEquals(
        "Uri Locations String",
        new GreenplumExternalTableUriLocationsHandler("Uri Locations String", 'A')
            .getLineFeedSeparatedList());
  }

  /**
   * Test {@link GreenplumExternalTableUriLocationsHandler#stream()}.
   *
   * <p>Method under test: {@link GreenplumExternalTableUriLocationsHandler#stream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Stream GreenplumExternalTableUriLocationsHandler.stream()"})
  public void testStream() {
    // Arrange and Act
    Stream<String> actualStreamResult =
        new GreenplumExternalTableUriLocationsHandler("Uri Locations String", 'A').stream();

    // Assert
    List<String> collectResult = actualStreamResult.limit(5).collect(Collectors.toList());
    assertEquals(1, collectResult.size());
    assertEquals("Uri Locations String", collectResult.get(0));
  }
}
