package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Locale;
import java.util.Map;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeFormatSampleDiffblueTest {
  /**
   * Test {@link TimeFormatSample#getDefaultProperties(Locale)}.
   *
   * <p>Method under test: {@link TimeFormatSample#getDefaultProperties(Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map TimeFormatSample.getDefaultProperties(Locale)"})
  public void testGetDefaultProperties() {
    // Arrange and Act
    Map<String, Object> actualDefaultProperties =
        new TimeFormatSample().getDefaultProperties(AbstractSQLDialect.DEF_LOCALE);

    // Assert
    assertEquals(1, actualDefaultProperties.size());
    assertEquals(
        TimeFormatSample.DEFAULT_TIME_PATTERN,
        actualDefaultProperties.get(DateTimeDataFormatter.PROP_PATTERN));
  }
}
