package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.impl.MessageChunk.Code;
import org.jkiss.dbeaver.model.ai.impl.MessageChunk.Link;
import org.jkiss.dbeaver.model.ai.impl.MessageChunk.Text;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithReturn;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MessageChunkDiffblueTest {
  /**
   * Test Code {@link Code#toRawString()}.
   *
   * <p>Method under test: {@link Code#toRawString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Code.toRawString()"})
  public void testCodeToRawString() {
    // Arrange, Act and Assert
    assertEquals("```en\nText\n```", new Code("Text", "en").toRawString());
  }

  /**
   * Test {@link MessageChunk#getCallback()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MessageChunk#getCallback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRRunnableWithReturn MessageChunk.getCallback()"})
  public void testGetCallback_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new Text("Text").getCallback());
  }

  /**
   * Test Link getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Link#getCallback()}
   *   <li>{@link Link#toRawString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBRRunnableWithReturn Link.getCallback()",
    "java.lang.String Link.toRawString()"
  })
  public void testLinkGettersAndSetters() {
    // Arrange
    Link link = new Link("Text", mock(DBRRunnableWithReturn.class));

    // Act
    link.getCallback();

    // Assert
    assertEquals("Text", link.toRawString());
  }

  /**
   * Test Text {@link Text#toRawString()}.
   *
   * <p>Method under test: {@link Text#toRawString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Text.toRawString()"})
  public void testTextToRawString() {
    // Arrange, Act and Assert
    assertEquals("Text", new Text("Text").toRawString());
  }
}
