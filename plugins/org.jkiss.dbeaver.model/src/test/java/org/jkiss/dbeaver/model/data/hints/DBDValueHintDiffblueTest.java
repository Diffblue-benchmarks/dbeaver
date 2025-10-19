package org.jkiss.dbeaver.model.data.hints;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint.HintStyle;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDValueHintDiffblueTest {
  /**
   * Test {@link DBDValueHint#getHintStyle()}.
   *
   * <p>Method under test: {@link DBDValueHint#getHintStyle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HintStyle DBDValueHint.getHintStyle()"})
  public void testGetHintStyle() {
    // Arrange
    ValueHintText valueHintText =
        new ValueHintText(
            "Text", "The characteristics of someone or something", mock(DBPImage.class));

    // Act and Assert
    assertEquals(HintStyle.NORMAL, valueHintText.getHintStyle());
  }

  /**
   * Test {@link DBDValueHint#getHintOptions()}.
   *
   * <p>Method under test: {@link DBDValueHint#getHintOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBDValueHint.getHintOptions()"})
  public void testGetHintOptions() {
    // Arrange
    ValueHintText valueHintText =
        new ValueHintText(
            "Text", "The characteristics of someone or something", mock(DBPImage.class));

    // Act and Assert
    assertEquals(0, valueHintText.getHintOptions());
  }
}
