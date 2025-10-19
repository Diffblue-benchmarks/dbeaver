package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.QualifiedName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLContentTypeDescriberDiffblueTest {
  /**
   * Test new {@link SQLContentTypeDescriber} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SQLContentTypeDescriber}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLContentTypeDescriber.<init>()"})
  public void testNewSQLContentTypeDescriber() {
    // Arrange, Act and Assert
    QualifiedName[] supportedOptions = new SQLContentTypeDescriber().getSupportedOptions();
    QualifiedName qualifiedName = supportedOptions[0];
    assertEquals("bom", qualifiedName.getLocalName());
    assertEquals("org.eclipse.core.runtime", qualifiedName.getQualifier());
    assertEquals(1, supportedOptions.length);
  }
}
