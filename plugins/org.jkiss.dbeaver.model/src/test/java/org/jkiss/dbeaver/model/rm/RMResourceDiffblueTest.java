package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMResourceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Name is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMResource#RMResource()}
   *   <li>{@link RMResource#setChanges(List)}
   *   <li>{@link RMResource#setFolder(boolean)}
   *   <li>{@link RMResource#setLastModified(Long)}
   *   <li>{@link RMResource#setLength(long)}
   *   <li>{@link RMResource#setProperties(Map)}
   *   <li>{@link RMResource#getChanges()}
   *   <li>{@link RMResource#getLastModified()}
   *   <li>{@link RMResource#getLength()}
   *   <li>{@link RMResource#isFolder()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMResource.<init>()",
    "void RMResource.<init>(String)",
    "void RMResource.<init>(String, boolean, long)",
    "List RMResource.getChanges()",
    "Long RMResource.getLastModified()",
    "long RMResource.getLength()",
    "boolean RMResource.isFolder()",
    "void RMResource.setChanges(List)",
    "void RMResource.setFolder(boolean)",
    "void RMResource.setLastModified(Long)",
    "void RMResource.setLength(long)",
    "void RMResource.setProperties(Map)"
  })
  public void testGettersAndSetters_thenReturnNameIsNull() {
    // Arrange and Act
    RMResource actualRmResource = new RMResource();
    ArrayList<RMResourceChange> changes = new ArrayList<>();
    actualRmResource.setChanges(changes);
    actualRmResource.setFolder(true);
    actualRmResource.setLastModified(1L);
    actualRmResource.setLength(3L);
    actualRmResource.setProperties(new HashMap<>());
    List<RMResourceChange> actualChanges = actualRmResource.getChanges();
    Long actualLastModified = actualRmResource.getLastModified();
    long actualLength = actualRmResource.getLength();
    boolean actualIsFolderResult = actualRmResource.isFolder();

    // Assert
    assertNull(actualRmResource.getName());
    assertNull(actualRmResource.getChildren());
    assertEquals(1L, actualLastModified.longValue());
    assertEquals(3L, actualLength);
    assertTrue(actualChanges.isEmpty());
    assertTrue(actualIsFolderResult);
    assertSame(changes, actualChanges);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMResource#RMResource(String)}
   *   <li>{@link RMResource#setChanges(List)}
   *   <li>{@link RMResource#setFolder(boolean)}
   *   <li>{@link RMResource#setLastModified(Long)}
   *   <li>{@link RMResource#setLength(long)}
   *   <li>{@link RMResource#setProperties(Map)}
   *   <li>{@link RMResource#getChanges()}
   *   <li>{@link RMResource#getLastModified()}
   *   <li>{@link RMResource#getLength()}
   *   <li>{@link RMResource#isFolder()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMResource.<init>()",
    "void RMResource.<init>(String)",
    "void RMResource.<init>(String, boolean, long)",
    "List RMResource.getChanges()",
    "Long RMResource.getLastModified()",
    "long RMResource.getLength()",
    "boolean RMResource.isFolder()",
    "void RMResource.setChanges(List)",
    "void RMResource.setFolder(boolean)",
    "void RMResource.setLastModified(Long)",
    "void RMResource.setLength(long)",
    "void RMResource.setProperties(Map)"
  })
  public void testGettersAndSetters_whenName_thenReturnName() {
    // Arrange and Act
    RMResource actualRmResource = new RMResource("Name");
    ArrayList<RMResourceChange> changes = new ArrayList<>();
    actualRmResource.setChanges(changes);
    actualRmResource.setFolder(true);
    actualRmResource.setLastModified(1L);
    actualRmResource.setLength(3L);
    actualRmResource.setProperties(new HashMap<>());
    List<RMResourceChange> actualChanges = actualRmResource.getChanges();
    Long actualLastModified = actualRmResource.getLastModified();
    long actualLength = actualRmResource.getLength();
    boolean actualIsFolderResult = actualRmResource.isFolder();

    // Assert
    assertEquals("Name", actualRmResource.getName());
    assertNull(actualRmResource.getChildren());
    assertEquals(1L, actualLastModified.longValue());
    assertEquals(3L, actualLength);
    assertTrue(actualChanges.isEmpty());
    assertTrue(actualIsFolderResult);
    assertSame(changes, actualChanges);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMResource#RMResource(String, boolean, long)}
   *   <li>{@link RMResource#setChanges(List)}
   *   <li>{@link RMResource#setFolder(boolean)}
   *   <li>{@link RMResource#setLastModified(Long)}
   *   <li>{@link RMResource#setLength(long)}
   *   <li>{@link RMResource#setProperties(Map)}
   *   <li>{@link RMResource#getChanges()}
   *   <li>{@link RMResource#getLastModified()}
   *   <li>{@link RMResource#getLength()}
   *   <li>{@link RMResource#isFolder()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMResource.<init>()",
    "void RMResource.<init>(String)",
    "void RMResource.<init>(String, boolean, long)",
    "List RMResource.getChanges()",
    "Long RMResource.getLastModified()",
    "long RMResource.getLength()",
    "boolean RMResource.isFolder()",
    "void RMResource.setChanges(List)",
    "void RMResource.setFolder(boolean)",
    "void RMResource.setLastModified(Long)",
    "void RMResource.setLength(long)",
    "void RMResource.setProperties(Map)"
  })
  public void testGettersAndSetters_whenTrue_thenReturnName() {
    // Arrange and Act
    RMResource actualRmResource = new RMResource("Name", true, 3L);
    ArrayList<RMResourceChange> changes = new ArrayList<>();
    actualRmResource.setChanges(changes);
    actualRmResource.setFolder(true);
    actualRmResource.setLastModified(1L);
    actualRmResource.setLength(3L);
    actualRmResource.setProperties(new HashMap<>());
    List<RMResourceChange> actualChanges = actualRmResource.getChanges();
    Long actualLastModified = actualRmResource.getLastModified();
    long actualLength = actualRmResource.getLength();
    boolean actualIsFolderResult = actualRmResource.isFolder();

    // Assert
    assertEquals("Name", actualRmResource.getName());
    assertNull(actualRmResource.getChildren());
    assertEquals(1L, actualLastModified.longValue());
    assertEquals(3L, actualLength);
    assertTrue(actualChanges.isEmpty());
    assertTrue(actualIsFolderResult);
    assertSame(changes, actualChanges);
  }

  /**
   * Test {@link RMResource#getProperties()}.
   *
   * <ul>
   *   <li>Given {@link RMResource#RMResource(String)} with {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link RMResource#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RMResource.getProperties()"})
  public void testGetProperties_givenRMResourceWithName() {
    // Arrange, Act and Assert
    assertTrue(new RMResource("Name").getProperties().isEmpty());
  }

  /**
   * Test {@link RMResource#getProperties()}.
   *
   * <ul>
   *   <li>Given {@link RMResource#RMResource(String)} with {@code Name} Properties is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link RMResource#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RMResource.getProperties()"})
  public void testGetProperties_givenRMResourceWithNamePropertiesIsHashMap() {
    // Arrange
    RMResource rmResource = new RMResource("Name");
    rmResource.setProperties(new HashMap<>());

    // Act and Assert
    assertTrue(rmResource.getProperties().isEmpty());
  }
}
