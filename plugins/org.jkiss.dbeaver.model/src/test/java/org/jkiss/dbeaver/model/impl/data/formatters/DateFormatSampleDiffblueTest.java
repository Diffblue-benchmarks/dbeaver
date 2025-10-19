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

public class DateFormatSampleDiffblueTest {
  /**
   * Test {@link DateFormatSample#getDefaultProperties(Locale)}.
   *
   * <p>Method under test: {@link DateFormatSample#getDefaultProperties(Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DateFormatSample.getDefaultProperties(Locale)"})
  public void testGetDefaultProperties() {
    // Arrange and Act
    Map<String, Object> actualDefaultProperties =
        new DateFormatSample().getDefaultProperties(AbstractSQLDialect.DEF_LOCALE);

    // Assert
    assertEquals(1, actualDefaultProperties.size());
    assertEquals(
        DateFormatSample.DEFAULT_DATE_PATTERN,
        actualDefaultProperties.get(DateTimeDataFormatter.PROP_PATTERN));
  }
}
