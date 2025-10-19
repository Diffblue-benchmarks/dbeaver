package org.jkiss.dbeaver.model.data.hints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ValueHintTextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ValueHintText#ValueHintText(String, String, DBPImage)}
   *   <li>{@link ValueHintText#getHintDescription()}
   *   <li>{@link ValueHintText#getHintIcon()}
   *   <li>{@link ValueHintText#getHintText()}
   *   <li>{@link ValueHintText#getHintType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ValueHintText.<init>(String, String, DBPImage)",
    "String ValueHintText.getHintDescription()",
    "DBPImage ValueHintText.getHintIcon()",
    "String ValueHintText.getHintText()",
    "HintType ValueHintText.getHintType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPImage icon = mock(DBPImage.class);

    // Act
    ValueHintText actualValueHintText =
        new ValueHintText("Text", "The characteristics of someone or something", icon);
    String actualHintDescription = actualValueHintText.getHintDescription();
    DBPImage actualHintIcon = actualValueHintText.getHintIcon();
    String actualHintText = actualValueHintText.getHintText();

    // Assert
    assertEquals("Text", actualHintText);
    assertEquals("The characteristics of someone or something", actualHintDescription);
    assertEquals(HintType.STRING, actualValueHintText.getHintType());
    assertSame(icon, actualHintIcon);
  }
}
