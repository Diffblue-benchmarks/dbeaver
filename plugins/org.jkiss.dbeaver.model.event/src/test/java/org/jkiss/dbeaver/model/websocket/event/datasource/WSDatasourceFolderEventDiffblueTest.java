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

public class WSDatasourceFolderEventDiffblueTest {
  /**
   * Test {@link WSDatasourceFolderEvent#create(String, String, String, List)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource_folder_created}.
   *   <li>Then return NodePaths is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#create(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.create(String, String, String, List)"
  })
  public void testCreate_givenCbDatasourceFolderCreated_thenReturnNodePathsIsArrayList() {
    // Arrange
    ArrayList<String> folderPaths = new ArrayList<>();
    folderPaths.add("cb_datasource_folder_created");

    // Act
    WSDatasourceFolderEvent actualCreateResult =
        WSDatasourceFolderEvent.create("42", "42", "myproject", folderPaths);

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_datasource_folder", actualCreateResult.getTopicId());
    assertEquals("cb_datasource_folder_created", actualCreateResult.getId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertFalse(actualCreateResult.isForceProcessed());
    assertSame(folderPaths, actualCreateResult.getNodePaths());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#create(String, String, String, List)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource_folder}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_datasource_folder}.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#create(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.create(String, String, String, List)"
  })
  public void testCreate_givenCbDatasourceFolder_whenArrayListAddCbDatasourceFolder() {
    // Arrange
    ArrayList<String> folderPaths = new ArrayList<>();
    folderPaths.add("cb_datasource_folder");
    folderPaths.add("cb_datasource_folder_created");

    // Act
    WSDatasourceFolderEvent actualCreateResult =
        WSDatasourceFolderEvent.create("42", "42", "myproject", folderPaths);

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_datasource_folder", actualCreateResult.getTopicId());
    assertEquals("cb_datasource_folder_created", actualCreateResult.getId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertFalse(actualCreateResult.isForceProcessed());
    assertSame(folderPaths, actualCreateResult.getNodePaths());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#create(String, String, String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return NodePaths Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#create(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.create(String, String, String, List)"
  })
  public void testCreate_whenArrayList_thenReturnNodePathsEmpty() {
    // Arrange and Act
    WSDatasourceFolderEvent actualCreateResult =
        WSDatasourceFolderEvent.create("42", "42", "myproject", new ArrayList<>());

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_datasource_folder", actualCreateResult.getTopicId());
    assertEquals("cb_datasource_folder_created", actualCreateResult.getId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertFalse(actualCreateResult.isForceProcessed());
    assertTrue(actualCreateResult.getNodePaths().isEmpty());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#delete(String, String, String, List)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource_folder_deleted}.
   *   <li>Then return NodePaths is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#delete(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.delete(String, String, String, List)"
  })
  public void testDelete_givenCbDatasourceFolderDeleted_thenReturnNodePathsIsArrayList() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource_folder_deleted");

    // Act
    WSDatasourceFolderEvent actualDeleteResult =
        WSDatasourceFolderEvent.delete("42", "42", "myproject", datasourceIds);

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_datasource_folder", actualDeleteResult.getTopicId());
    assertEquals("cb_datasource_folder_deleted", actualDeleteResult.getId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertSame(datasourceIds, actualDeleteResult.getNodePaths());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#delete(String, String, String, List)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource_folder}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_datasource_folder}.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#delete(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.delete(String, String, String, List)"
  })
  public void testDelete_givenCbDatasourceFolder_whenArrayListAddCbDatasourceFolder() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource_folder");
    datasourceIds.add("cb_datasource_folder_deleted");

    // Act
    WSDatasourceFolderEvent actualDeleteResult =
        WSDatasourceFolderEvent.delete("42", "42", "myproject", datasourceIds);

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_datasource_folder", actualDeleteResult.getTopicId());
    assertEquals("cb_datasource_folder_deleted", actualDeleteResult.getId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertSame(datasourceIds, actualDeleteResult.getNodePaths());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#delete(String, String, String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return NodePaths Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#delete(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.delete(String, String, String, List)"
  })
  public void testDelete_whenArrayList_thenReturnNodePathsEmpty() {
    // Arrange and Act
    WSDatasourceFolderEvent actualDeleteResult =
        WSDatasourceFolderEvent.delete("42", "42", "myproject", new ArrayList<>());

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_datasource_folder", actualDeleteResult.getTopicId());
    assertEquals("cb_datasource_folder_deleted", actualDeleteResult.getId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertTrue(actualDeleteResult.getNodePaths().isEmpty());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#update(String, String, String, List)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource_folder_updated}.
   *   <li>Then return NodePaths is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#update(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.update(String, String, String, List)"
  })
  public void testUpdate_givenCbDatasourceFolderUpdated_thenReturnNodePathsIsArrayList() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource_folder_updated");

    // Act
    WSDatasourceFolderEvent actualUpdateResult =
        WSDatasourceFolderEvent.update("42", "42", "myproject", datasourceIds);

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_datasource_folder", actualUpdateResult.getTopicId());
    assertEquals("cb_datasource_folder_updated", actualUpdateResult.getId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertSame(datasourceIds, actualUpdateResult.getNodePaths());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#update(String, String, String, List)}.
   *
   * <ul>
   *   <li>Given {@code cb_datasource_folder}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_datasource_folder}.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#update(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.update(String, String, String, List)"
  })
  public void testUpdate_givenCbDatasourceFolder_whenArrayListAddCbDatasourceFolder() {
    // Arrange
    ArrayList<String> datasourceIds = new ArrayList<>();
    datasourceIds.add("cb_datasource_folder");
    datasourceIds.add("cb_datasource_folder_updated");

    // Act
    WSDatasourceFolderEvent actualUpdateResult =
        WSDatasourceFolderEvent.update("42", "42", "myproject", datasourceIds);

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_datasource_folder", actualUpdateResult.getTopicId());
    assertEquals("cb_datasource_folder_updated", actualUpdateResult.getId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertSame(datasourceIds, actualUpdateResult.getNodePaths());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#update(String, String, String, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return NodePaths Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#update(String, String, String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSDatasourceFolderEvent WSDatasourceFolderEvent.update(String, String, String, List)"
  })
  public void testUpdate_whenArrayList_thenReturnNodePathsEmpty() {
    // Arrange and Act
    WSDatasourceFolderEvent actualUpdateResult =
        WSDatasourceFolderEvent.update("42", "42", "myproject", new ArrayList<>());

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_datasource_folder", actualUpdateResult.getTopicId());
    assertEquals("cb_datasource_folder_updated", actualUpdateResult.getId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertTrue(actualUpdateResult.getNodePaths().isEmpty());
  }

  /**
   * Test {@link WSDatasourceFolderEvent#getNodePaths()}.
   *
   * <p>Method under test: {@link WSDatasourceFolderEvent#getNodePaths()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List WSDatasourceFolderEvent.getNodePaths()"})
  public void testGetNodePaths() {
    // Arrange
    ArrayList<String> folderPaths = new ArrayList<>();
    WSDatasourceFolderEvent createResult =
        WSDatasourceFolderEvent.create("42", "42", "myproject", folderPaths);

    // Act
    List<String> actualNodePaths = createResult.getNodePaths();

    // Assert
    assertTrue(actualNodePaths.isEmpty());
    assertSame(folderPaths, actualNodePaths);
  }
}
