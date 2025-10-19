package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSObjectStateDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSObjectState#DBSObjectState(String, DBPImage)}
   *   <li>{@link DBSObjectState#getOverlayImage()}
   *   <li>{@link DBSObjectState#getTitle()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSObjectState.<init>(String, DBPImage)",
    "DBPImage DBSObjectState.getOverlayImage()",
    "String DBSObjectState.getTitle()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPImage overlayImage = mock(DBPImage.class);

    // Act
    DBSObjectState actualDbsObjectState = new DBSObjectState("Dr", overlayImage);
    DBPImage actualOverlayImage = actualDbsObjectState.getOverlayImage();

    // Assert
    assertEquals("Dr", actualDbsObjectState.getTitle());
    assertSame(overlayImage, actualOverlayImage);
  }
}
