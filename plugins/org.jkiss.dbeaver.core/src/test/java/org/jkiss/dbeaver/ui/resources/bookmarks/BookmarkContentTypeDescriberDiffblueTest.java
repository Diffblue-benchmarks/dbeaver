package org.jkiss.dbeaver.ui.resources.bookmarks;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.core.internal.content.ContentType;
import org.eclipse.core.internal.content.ContentTypeManager;
import org.eclipse.core.internal.content.DefaultDescription;
import org.eclipse.core.runtime.content.IContentDescription;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BookmarkContentTypeDescriberDiffblueTest {
  /**
   * Test {@link BookmarkContentTypeDescriber#describe(InputStream, IContentDescription)}.
   *
   * <p>Method under test: {@link BookmarkContentTypeDescriber#describe(InputStream,
   * IContentDescription)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int BookmarkContentTypeDescriber.describe(InputStream, IContentDescription)"})
  public void testDescribe() throws IOException {
    // Arrange
    BookmarkContentTypeDescriber bookmarkContentTypeDescriber = new BookmarkContentTypeDescriber();
    ByteArrayInputStream contents = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(
        1,
        bookmarkContentTypeDescriber.describe(
            contents, new DefaultDescription(new ContentType(ContentTypeManager.getInstance()))));
  }

  /**
   * Test {@link BookmarkContentTypeDescriber#getSupportedOptions()}.
   *
   * <p>Method under test: {@link BookmarkContentTypeDescriber#getSupportedOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.eclipse.core.runtime.QualifiedName[] BookmarkContentTypeDescriber.getSupportedOptions()"
  })
  public void testGetSupportedOptions() {
    // Arrange, Act and Assert
    assertEquals(0, new BookmarkContentTypeDescriber().getSupportedOptions().length);
  }

  /**
   * Test new {@link BookmarkContentTypeDescriber} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * BookmarkContentTypeDescriber}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BookmarkContentTypeDescriber.<init>()"})
  public void testNewBookmarkContentTypeDescriber() {
    // Arrange, Act and Assert
    assertEquals(0, new BookmarkContentTypeDescriber().getSupportedOptions().length);
  }
}
