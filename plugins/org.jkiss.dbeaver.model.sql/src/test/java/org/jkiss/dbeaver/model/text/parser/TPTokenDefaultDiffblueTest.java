package org.jkiss.dbeaver.model.text.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TPTokenDefaultDiffblueTest {
  /**
   * Test {@link TPTokenDefault#TPTokenDefault(TPTokenType)}.
   *
   * <p>Method under test: {@link TPTokenDefault#TPTokenDefault(TPTokenType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TPTokenDefault.<init>(TPTokenType)"})
  public void testNewTPTokenDefault() {
    // Arrange and Act
    TPTokenDefault actualTpTokenDefault = new TPTokenDefault(SQLTokenType.T_BLOCK_BEGIN);

    // Assert
    TPTokenType data = actualTpTokenDefault.getData();
    assertTrue(data instanceof SQLTokenType);
    assertEquals(SQLTokenType.T_BLOCK_BEGIN, data);
    assertFalse(actualTpTokenDefault.isEOF());
    assertFalse(actualTpTokenDefault.isOther());
    assertFalse(actualTpTokenDefault.isUndefined());
    assertFalse(actualTpTokenDefault.isWhitespace());
  }
}
