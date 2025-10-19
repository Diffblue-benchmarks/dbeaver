package org.jkiss.dbeaver.ui.resources.bookmarks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPImage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BookmarkStorageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BookmarkStorage#BookmarkStorage(String, String, DBPImage, String, List)}
   *   <li>{@link BookmarkStorage#setImage(DBPImage)}
   *   <li>{@link BookmarkStorage#setTitle(String)}
   *   <li>{@link BookmarkStorage#getDataSourceId()}
   *   <li>{@link BookmarkStorage#getDataSourcePath()}
   *   <li>{@link BookmarkStorage#getDescription()}
   *   <li>{@link BookmarkStorage#getImage()}
   *   <li>{@link BookmarkStorage#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void BookmarkStorage.<init>(String, String, DBPImage, String, List)",
    "String BookmarkStorage.getDataSourceId()",
    "List BookmarkStorage.getDataSourcePath()",
    "String BookmarkStorage.getDescription()",
    "DBPImage BookmarkStorage.getImage()",
    "String BookmarkStorage.getTitle()",
    "void BookmarkStorage.setImage(DBPImage)",
    "void BookmarkStorage.setTitle(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPImage image = mock(DBPImage.class);
    ArrayList<String> dataSourcePath = new ArrayList<>();

    // Act
    BookmarkStorage actualBookmarkStorage =
        new BookmarkStorage(
            "Dr", "The characteristics of someone or something", image, "42", dataSourcePath);
    DBPImage image2 = mock(DBPImage.class);
    actualBookmarkStorage.setImage(image2);
    actualBookmarkStorage.setTitle("Dr");
    String actualDataSourceId = actualBookmarkStorage.getDataSourceId();
    List<String> actualDataSourcePath = actualBookmarkStorage.getDataSourcePath();
    String actualDescription = actualBookmarkStorage.getDescription();
    DBPImage actualImage = actualBookmarkStorage.getImage();

    // Assert
    assertEquals("42", actualDataSourceId);
    assertEquals("Dr", actualBookmarkStorage.getTitle());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(actualDataSourcePath.isEmpty());
    assertSame(dataSourcePath, actualDataSourcePath);
    assertSame(image2, actualImage);
  }
}
