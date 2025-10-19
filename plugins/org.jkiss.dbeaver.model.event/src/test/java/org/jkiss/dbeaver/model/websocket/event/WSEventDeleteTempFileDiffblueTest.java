package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSEventDeleteTempFileDiffblueTest {
  /**
   * Test {@link WSEventDeleteTempFile#WSEventDeleteTempFile(String)}.
   *
   * <p>Method under test: {@link WSEventDeleteTempFile#WSEventDeleteTempFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSEventDeleteTempFile.<init>(String)"})
  public void testNewWSEventDeleteTempFile() {
    // Arrange and Act
    WSEventDeleteTempFile actualWsEventDeleteTempFile = new WSEventDeleteTempFile("42");

    // Assert
    assertEquals("42", actualWsEventDeleteTempFile.getSessionId());
    assertEquals("cb_delete_temp_folder", actualWsEventDeleteTempFile.getTopicId());
    assertEquals("cb_temp_folder_deleted", actualWsEventDeleteTempFile.getId());
    assertNull(actualWsEventDeleteTempFile.getUserId());
    assertFalse(actualWsEventDeleteTempFile.isForceProcessed());
  }

  /**
   * Test {@link WSEventDeleteTempFile#getSessionId()}.
   *
   * <p>Method under test: {@link WSEventDeleteTempFile#getSessionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSEventDeleteTempFile.getSessionId()"})
  public void testGetSessionId() {
    // Arrange, Act and Assert
    assertEquals("42", new WSEventDeleteTempFile("42").getSessionId());
  }
}
