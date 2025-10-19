package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCScriptContext.VariableInfo;
import org.jkiss.dbeaver.model.exec.DBCScriptContext.VariableType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCScriptContextDiffblueTest {
  /**
   * Test VariableInfo {@link VariableInfo#VariableInfo(String, Object, VariableType)}.
   *
   * <p>Method under test: {@link VariableInfo#VariableInfo(String, Object, VariableType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VariableInfo.<init>(String, Object, VariableType)"})
  public void testVariableInfoNewVariableInfo() {
    // Arrange and Act
    VariableInfo actualVariableInfo =
        new VariableInfo("Name", DBPEvent.RENAME, VariableType.PARAMETER);

    // Assert
    assertEquals("Name", actualVariableInfo.name);
    assertEquals(VariableType.PARAMETER, actualVariableInfo.type);
  }

  /**
   * Test VariableType {@link VariableType#getTitle()}.
   *
   * <p>Method under test: {@link VariableType#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VariableType.getTitle()"})
  public void testVariableTypeGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Parameter", VariableType.valueOf("PARAMETER").getTitle());
  }
}
