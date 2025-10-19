package org.jkiss.dbeaver.model.websocket.event.datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSDataSourceEventDiffblueTest {
  /**
   * Test {@link WSDataSourceEvent#create(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_datasource}.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#create(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.create(String, String, String, List, WSDataSourceProperty)"
  })
  public void testCreate_givenCbDatasource_whenArrayListAddCbDatasource() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource");
    datasourceIds.add(WSDataSourceEvent.CREATED);

    // Act
    WSDataSourceEvent actualCreateResult =
        WSDataSourceEvent.create(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_datasource", actualCreateResult.getTopicId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualCreateResult.getProperty());
    assertFalse(actualCreateResult.isForceProcessed());
    assertEquals(WSDataSourceEvent.CREATED, actualCreateResult.getId());
    assertSame(datasourceIds, actualCreateResult.getDataSourceIds());
  }

  /**
   * Test {@link WSDataSourceEvent#create(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>Given {@link WSDataSourceEvent#CREATED}.
   *   <li>Then return DataSourceIds is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#create(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.create(String, String, String, List, WSDataSourceProperty)"
  })
  public void testCreate_givenCreated_thenReturnDataSourceIdsIsArrayList() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add(WSDataSourceEvent.CREATED);

    // Act
    WSDataSourceEvent actualCreateResult =
        WSDataSourceEvent.create(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_datasource", actualCreateResult.getTopicId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualCreateResult.getProperty());
    assertFalse(actualCreateResult.isForceProcessed());
    assertEquals(WSDataSourceEvent.CREATED, actualCreateResult.getId());
    assertSame(datasourceIds, actualCreateResult.getDataSourceIds());
  }

  /**
   * Test {@link WSDataSourceEvent#create(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return DataSourceIds Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#create(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.create(String, String, String, List, WSDataSourceProperty)"
  })
  public void testCreate_whenArrayList_thenReturnDataSourceIdsEmpty() {
    // Arrange and Act
    WSDataSourceEvent actualCreateResult =
        WSDataSourceEvent.create(
            "42", "42", "myproject", new ArrayList<>(), WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_datasource", actualCreateResult.getTopicId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualCreateResult.getProperty());
    assertFalse(actualCreateResult.isForceProcessed());
    assertTrue(actualCreateResult.getDataSourceIds().isEmpty());
    assertEquals(WSDataSourceEvent.CREATED, actualCreateResult.getId());
  }

  /**
   * Test {@link WSDataSourceEvent#delete(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_datasource}.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#delete(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.delete(String, String, String, List, WSDataSourceProperty)"
  })
  public void testDelete_givenCbDatasource_whenArrayListAddCbDatasource() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource");
    datasourceIds.add(WSDataSourceEvent.DELETED);

    // Act
    WSDataSourceEvent actualDeleteResult =
        WSDataSourceEvent.delete(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_datasource", actualDeleteResult.getTopicId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualDeleteResult.getProperty());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertEquals(WSDataSourceEvent.DELETED, actualDeleteResult.getId());
    assertSame(datasourceIds, actualDeleteResult.getDataSourceIds());
  }

  /**
   * Test {@link WSDataSourceEvent#delete(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>Given {@link WSDataSourceEvent#DELETED}.
   *   <li>Then return DataSourceIds is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#delete(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.delete(String, String, String, List, WSDataSourceProperty)"
  })
  public void testDelete_givenDeleted_thenReturnDataSourceIdsIsArrayList() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add(WSDataSourceEvent.DELETED);

    // Act
    WSDataSourceEvent actualDeleteResult =
        WSDataSourceEvent.delete(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_datasource", actualDeleteResult.getTopicId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualDeleteResult.getProperty());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertEquals(WSDataSourceEvent.DELETED, actualDeleteResult.getId());
    assertSame(datasourceIds, actualDeleteResult.getDataSourceIds());
  }

  /**
   * Test {@link WSDataSourceEvent#delete(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return DataSourceIds Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#delete(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.delete(String, String, String, List, WSDataSourceProperty)"
  })
  public void testDelete_whenArrayList_thenReturnDataSourceIdsEmpty() {
    // Arrange and Act
    WSDataSourceEvent actualDeleteResult =
        WSDataSourceEvent.delete(
            "42", "42", "myproject", new ArrayList<>(), WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_datasource", actualDeleteResult.getTopicId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualDeleteResult.getProperty());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertTrue(actualDeleteResult.getDataSourceIds().isEmpty());
    assertEquals(WSDataSourceEvent.DELETED, actualDeleteResult.getId());
  }

  /**
   * Test {@link WSDataSourceEvent#update(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_datasource}.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#update(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.update(String, String, String, List, WSDataSourceProperty)"
  })
  public void testUpdate_givenCbDatasource_whenArrayListAddCbDatasource() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource");
    datasourceIds.add(WSDataSourceEvent.UPDATED);

    // Act
    WSDataSourceEvent actualUpdateResult =
        WSDataSourceEvent.update(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_datasource", actualUpdateResult.getTopicId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualUpdateResult.getProperty());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertEquals(WSDataSourceEvent.UPDATED, actualUpdateResult.getId());
    assertSame(datasourceIds, actualUpdateResult.getDataSourceIds());
  }

  /**
   * Test {@link WSDataSourceEvent#update(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>Given {@link WSDataSourceEvent#UPDATED}.
   *   <li>Then return DataSourceIds is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#update(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.update(String, String, String, List, WSDataSourceProperty)"
  })
  public void testUpdate_givenUpdated_thenReturnDataSourceIdsIsArrayList() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add(WSDataSourceEvent.UPDATED);

    // Act
    WSDataSourceEvent actualUpdateResult =
        WSDataSourceEvent.update(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_datasource", actualUpdateResult.getTopicId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualUpdateResult.getProperty());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertEquals(WSDataSourceEvent.UPDATED, actualUpdateResult.getId());
    assertSame(datasourceIds, actualUpdateResult.getDataSourceIds());
  }

  /**
   * Test {@link WSDataSourceEvent#update(String, String, String, List, WSDataSourceProperty)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return DataSourceIds Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSDataSourceEvent#update(String, String, String, List,
   * WSDataSourceProperty)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDataSourceEvent WSDataSourceEvent.update(String, String, String, List, WSDataSourceProperty)"
  })
  public void testUpdate_whenArrayList_thenReturnDataSourceIdsEmpty() {
    // Arrange and Act
    WSDataSourceEvent actualUpdateResult =
        WSDataSourceEvent.update(
            "42", "42", "myproject", new ArrayList<>(), WSDataSourceProperty.CONFIGURATION);

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_datasource", actualUpdateResult.getTopicId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertEquals(WSDataSourceProperty.CONFIGURATION, actualUpdateResult.getProperty());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertTrue(actualUpdateResult.getDataSourceIds().isEmpty());
    assertEquals(WSDataSourceEvent.UPDATED, actualUpdateResult.getId());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSDataSourceEvent#getDataSourceIds()}
   *   <li>{@link WSDataSourceEvent#getProperty()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List WSDataSourceEvent.getDataSourceIds()",
    "WSDataSourceProperty WSDataSourceEvent.getProperty()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    WSDataSourceEvent createResult =
        WSDataSourceEvent.create(
            "42", "42", "myproject", datasourceIds, WSDataSourceProperty.CONFIGURATION);

    // Act
    List<String> actualDataSourceIds = createResult.getDataSourceIds();

    // Assert
    assertEquals(WSDataSourceProperty.CONFIGURATION, createResult.getProperty());
    assertTrue(actualDataSourceIds.isEmpty());
    assertSame(datasourceIds, actualDataSourceIds);
  }
}
