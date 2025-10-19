package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBNUtilsDiffblueTest {
  /**
   * Test {@link DBNUtils#getNodeByObject(DBRProgressMonitor, DBSObject, boolean)} with {@code
   * monitor}, {@code object}, {@code addFiltered}.
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBRProgressMonitor, DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNDatabaseNode DBNUtils.getNodeByObject(DBRProgressMonitor, DBSObject, boolean)"
  })
  public void testGetNodeByObjectWithMonitorObjectAddFiltered() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    when(entity.getParentObject()).thenReturn(dbvEntity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBSDocumentContainer entity127 = mock(DBSDocumentContainer.class);
    when(entity127.getParentObject()).thenReturn(new DBSDocumentConstraint(entity126));
    when(entity127.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualNodeByObject =
        DBNUtils.getNodeByObject(monitor, new DBSDocumentConstraint(entity127), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(entity127).getDataSource();
    verify(entity127, atLeast(1)).getParentObject();
    verify(entity126, atLeast(1)).getParentObject();
    verify(entity125, atLeast(1)).getParentObject();
    verify(entity124, atLeast(1)).getParentObject();
    verify(entity123, atLeast(1)).getParentObject();
    verify(entity122, atLeast(1)).getParentObject();
    verify(entity121, atLeast(1)).getParentObject();
    verify(entity120, atLeast(1)).getParentObject();
    verify(entity119, atLeast(1)).getParentObject();
    verify(entity118, atLeast(1)).getParentObject();
    verify(entity117, atLeast(1)).getParentObject();
    verify(entity116, atLeast(1)).getParentObject();
    verify(entity115, atLeast(1)).getParentObject();
    verify(entity114, atLeast(1)).getParentObject();
    verify(entity113, atLeast(1)).getParentObject();
    verify(entity112, atLeast(1)).getParentObject();
    verify(entity111, atLeast(1)).getParentObject();
    verify(entity110, atLeast(1)).getParentObject();
    verify(entity109, atLeast(1)).getParentObject();
    verify(entity108, atLeast(1)).getParentObject();
    verify(entity107, atLeast(1)).getParentObject();
    verify(entity106, atLeast(1)).getParentObject();
    verify(entity105, atLeast(1)).getParentObject();
    verify(entity104, atLeast(1)).getParentObject();
    verify(entity103, atLeast(1)).getParentObject();
    verify(entity102, atLeast(1)).getParentObject();
    verify(entity101, atLeast(1)).getParentObject();
    verify(entity100, atLeast(1)).getParentObject();
    verify(entity99, atLeast(1)).getParentObject();
    verify(entity98, atLeast(1)).getParentObject();
    verify(entity97, atLeast(1)).getParentObject();
    verify(entity96, atLeast(1)).getParentObject();
    verify(entity95, atLeast(1)).getParentObject();
    verify(entity94, atLeast(1)).getParentObject();
    verify(entity93, atLeast(1)).getParentObject();
    verify(entity92, atLeast(1)).getParentObject();
    verify(entity91, atLeast(1)).getParentObject();
    verify(entity90, atLeast(1)).getParentObject();
    verify(entity89, atLeast(1)).getParentObject();
    verify(entity88, atLeast(1)).getParentObject();
    verify(entity87, atLeast(1)).getParentObject();
    verify(entity86, atLeast(1)).getParentObject();
    verify(entity85, atLeast(1)).getParentObject();
    verify(entity84, atLeast(1)).getParentObject();
    verify(entity83, atLeast(1)).getParentObject();
    verify(entity82, atLeast(1)).getParentObject();
    verify(entity81, atLeast(1)).getParentObject();
    verify(entity80, atLeast(1)).getParentObject();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBRProgressMonitor, DBSObject, boolean)} with {@code
   * monitor}, {@code object}, {@code addFiltered}.
   *
   * <ul>
   *   <li>Then calls {@link DBVEntity#getParentObject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBRProgressMonitor, DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNDatabaseNode DBNUtils.getNodeByObject(DBRProgressMonitor, DBSObject, boolean)"
  })
  public void testGetNodeByObjectWithMonitorObjectAddFiltered_thenCallsGetParentObject() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVEntity dbvEntity = mock(DBVEntity.class);
    when(dbvEntity.getParentObject()).thenReturn(new DBVContainer(null, "Name"));

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getParentObject()).thenReturn(dbvEntity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBSDocumentContainer entity127 = mock(DBSDocumentContainer.class);
    when(entity127.getParentObject()).thenReturn(new DBSDocumentConstraint(entity126));
    when(entity127.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualNodeByObject =
        DBNUtils.getNodeByObject(monitor, new DBSDocumentConstraint(entity127), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(entity127).getDataSource();
    verify(entity127, atLeast(1)).getParentObject();
    verify(entity126, atLeast(1)).getParentObject();
    verify(entity125, atLeast(1)).getParentObject();
    verify(entity124, atLeast(1)).getParentObject();
    verify(entity123, atLeast(1)).getParentObject();
    verify(entity122, atLeast(1)).getParentObject();
    verify(entity121, atLeast(1)).getParentObject();
    verify(entity120, atLeast(1)).getParentObject();
    verify(entity119, atLeast(1)).getParentObject();
    verify(entity118, atLeast(1)).getParentObject();
    verify(entity117, atLeast(1)).getParentObject();
    verify(entity116, atLeast(1)).getParentObject();
    verify(entity115, atLeast(1)).getParentObject();
    verify(entity114, atLeast(1)).getParentObject();
    verify(entity113, atLeast(1)).getParentObject();
    verify(entity112, atLeast(1)).getParentObject();
    verify(entity111, atLeast(1)).getParentObject();
    verify(entity110, atLeast(1)).getParentObject();
    verify(entity109, atLeast(1)).getParentObject();
    verify(entity108, atLeast(1)).getParentObject();
    verify(entity107, atLeast(1)).getParentObject();
    verify(entity106, atLeast(1)).getParentObject();
    verify(entity105, atLeast(1)).getParentObject();
    verify(entity104, atLeast(1)).getParentObject();
    verify(entity103, atLeast(1)).getParentObject();
    verify(entity102, atLeast(1)).getParentObject();
    verify(entity101, atLeast(1)).getParentObject();
    verify(entity100, atLeast(1)).getParentObject();
    verify(entity99, atLeast(1)).getParentObject();
    verify(entity98, atLeast(1)).getParentObject();
    verify(entity97, atLeast(1)).getParentObject();
    verify(entity96, atLeast(1)).getParentObject();
    verify(entity95, atLeast(1)).getParentObject();
    verify(entity94, atLeast(1)).getParentObject();
    verify(entity93, atLeast(1)).getParentObject();
    verify(entity92, atLeast(1)).getParentObject();
    verify(entity91, atLeast(1)).getParentObject();
    verify(entity90, atLeast(1)).getParentObject();
    verify(entity89, atLeast(1)).getParentObject();
    verify(entity88, atLeast(1)).getParentObject();
    verify(entity87, atLeast(1)).getParentObject();
    verify(entity86, atLeast(1)).getParentObject();
    verify(entity85, atLeast(1)).getParentObject();
    verify(entity84, atLeast(1)).getParentObject();
    verify(entity83, atLeast(1)).getParentObject();
    verify(entity82, atLeast(1)).getParentObject();
    verify(entity81, atLeast(1)).getParentObject();
    verify(entity80, atLeast(1)).getParentObject();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    verify(dbvEntity, atLeast(1)).getParentObject();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNUtils.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualNodeByObject =
        DBNUtils.getNodeByObject(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(entity).getDataSource();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNUtils.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject_given42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBNDatabaseNode actualNodeByObject = DBNUtils.getNodeByObject(new DBVEntityForeignKey(entity));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getRegistry()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNUtils.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject_givenDBPDataSourceContainerGetRegistryReturnNull() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualNodeByObject =
        DBNUtils.getNodeByObject(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(entity).getDataSource();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceRegistry} {@link DBPDataSourceRegistry#getProject()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNUtils.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject_givenDBPDataSourceRegistryGetProjectReturnNull() {
    // Arrange
    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualNodeByObject =
        DBNUtils.getNodeByObject(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(entity).getDataSource();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Given {@link DBPProject} {@link DBPProject#getNavigatorModel()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNUtils.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject_givenDBPProjectGetNavigatorModelReturnNull() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualNodeByObject =
        DBNUtils.getNodeByObject(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(entity).getDataSource();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNUtils.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBNDatabaseNode actualNodeByObject = DBNUtils.getNodeByObject(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(parent).getDataSource();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNUtils#getNodeChildrenFiltered(DBRProgressMonitor, DBNNode, boolean)}.
   *
   * <p>Method under test: {@link DBNUtils#getNodeChildrenFiltered(DBRProgressMonitor, DBNNode,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNNode[] DBNUtils.getNodeChildrenFiltered(DBRProgressMonitor, DBNNode, boolean)"
  })
  public void testGetNodeChildrenFiltered() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    DBNNode[] actualNodeChildrenFiltered =
        DBNUtils.getNodeChildrenFiltered(monitor, new DBNEmptyNode(), true);

    // Assert
    assertEquals(0, actualNodeChildrenFiltered.length);
  }

  /**
   * Test {@link DBNUtils#getNavigatorModel(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNavigatorModel(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNModel DBNUtils.getNavigatorModel(DBSObject)"})
  public void testGetNavigatorModel_given42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    DBNModel actualNavigatorModel = DBNUtils.getNavigatorModel(new DBVEntityForeignKey(entity));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualNavigatorModel);
  }

  /**
   * Test {@link DBNUtils#getNavigatorModel(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getRegistry()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNavigatorModel(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNModel DBNUtils.getNavigatorModel(DBSObject)"})
  public void testGetNavigatorModel_givenDBPDataSourceContainerGetRegistryReturnNull() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNModel actualNavigatorModel = DBNUtils.getNavigatorModel(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(entity).getDataSource();
    assertNull(actualNavigatorModel);
  }

  /**
   * Test {@link DBNUtils#getNavigatorModel(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceRegistry} {@link DBPDataSourceRegistry#getProject()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNavigatorModel(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNModel DBNUtils.getNavigatorModel(DBSObject)"})
  public void testGetNavigatorModel_givenDBPDataSourceRegistryGetProjectReturnNull() {
    // Arrange
    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNModel actualNavigatorModel = DBNUtils.getNavigatorModel(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(entity).getDataSource();
    assertNull(actualNavigatorModel);
  }

  /**
   * Test {@link DBNUtils#getNavigatorModel(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNavigatorModel(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNModel DBNUtils.getNavigatorModel(DBSObject)"})
  public void testGetNavigatorModel_thenCallsGetDataSource() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(null);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    DBNModel actualNavigatorModel = DBNUtils.getNavigatorModel(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(parent).getDataSource();
    assertNull(actualNavigatorModel);
  }

  /**
   * Test {@link DBNUtils#getNavigatorModel(DBSObject)}.
   *
   * <ul>
   *   <li>Then return ModelAuthContext is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getNavigatorModel(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNModel DBNUtils.getNavigatorModel(DBSObject)"})
  public void testGetNavigatorModel_thenReturnModelAuthContextIsNull() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNModel actualNavigatorModel = DBNUtils.getNavigatorModel(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(entity).getDataSource();
    assertNull(actualNavigatorModel.getModelAuthContext());
    assertNull(actualNavigatorModel.getRoot());
    assertFalse(actualNavigatorModel.isGlobal());
    assertTrue(actualNavigatorModel.getModelProjects().isEmpty());
  }

  /**
   * Test {@link DBNUtils#filterNavigableChildren(DBNNode[], boolean)}.
   *
   * <ul>
   *   <li>When empty array of {@link DBNNode}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#filterNavigableChildren(DBNNode[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode[] DBNUtils.filterNavigableChildren(DBNNode[], boolean)"})
  public void testFilterNavigableChildren_whenEmptyArrayOfDBNNode_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, DBNUtils.filterNavigableChildren(new DBNNode[] {}, true).length);
  }

  /**
   * Test {@link DBNUtils#isDefaultElement(Object)}.
   *
   * <p>Method under test: {@link DBNUtils#isDefaultElement(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNUtils.isDefaultElement(Object)"})
  public void testIsDefaultElement() {
    // Arrange, Act and Assert
    assertFalse(DBNUtils.isDefaultElement(DBNEvent.FORCE_REFRESH));
  }

  /**
   * Test {@link DBNUtils#getLastNodePathSegment(String)}.
   *
   * <p>Method under test: {@link DBNUtils#getLastNodePathSegment(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNUtils.getLastNodePathSegment(String)"})
  public void testGetLastNodePathSegment() {
    // Arrange, Act and Assert
    assertEquals("Path", DBNUtils.getLastNodePathSegment("Path"));
  }

  /**
   * Test {@link DBNUtils#isReadOnly(DBNNode)}.
   *
   * <p>Method under test: {@link DBNUtils#isReadOnly(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNUtils.isReadOnly(DBNNode)"})
  public void testIsReadOnly() {
    // Arrange, Act and Assert
    assertFalse(DBNUtils.isReadOnly(new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNUtils#isFolderNode(DBNNode)}.
   *
   * <p>Method under test: {@link DBNUtils#isFolderNode(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNUtils.isFolderNode(DBNNode)"})
  public void testIsFolderNode() {
    // Arrange, Act and Assert
    assertFalse(DBNUtils.isFolderNode(new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNUtils#getParentOfType(Class, DBNNode)}.
   *
   * <ul>
   *   <li>When {@link DBNEmptyNode} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getParentOfType(Class, DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBNUtils.getParentOfType(Class, DBNNode)"})
  public void testGetParentOfType_whenDBNEmptyNode() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(DBNUtils.getParentOfType(type, new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNUtils#getParentOfType(Class, DBNNode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getParentOfType(Class, DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBNUtils.getParentOfType(Class, DBNNode)"})
  public void testGetParentOfType_whenNull() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(DBNUtils.getParentOfType(type, null));
  }

  /**
   * Test {@link DBNUtils#encodeNodePath(String)}, and {@link DBNUtils#decodeNodePath(String)}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBNUtils#encodeNodePath(String)}
   *   <li>{@link DBNUtils#decodeNodePath(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBNUtils.encodeNodePath(String)",
    "String DBNUtils.decodeNodePath(String)"
  })
  public void testEncodeNodePathAndDecodeNodePath() {
    // Arrange, Act and Assert
    assertEquals("Path", DBNUtils.decodeNodePath(DBNUtils.encodeNodePath("Path")));
  }

  /**
   * Test {@link DBNUtils#getDefaultDatabaseNodeToOpen(DBRProgressMonitor, DBPDataSource)}.
   *
   * <p>Method under test: {@link DBNUtils#getDefaultDatabaseNodeToOpen(DBRProgressMonitor,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNDatabaseNode DBNUtils.getDefaultDatabaseNodeToOpen(DBRProgressMonitor, DBPDataSource)"
  })
  public void testGetDefaultDatabaseNodeToOpen() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getParentObject()).thenReturn(new DBVContainer(null, "Name"));

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getParentObject())
        .thenReturn(new DBSDocumentConstraint(entity126));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dataSource.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualDefaultDatabaseNodeToOpen =
        DBNUtils.getDefaultDatabaseNodeToOpen(monitor, dataSource);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(dataSource).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getParentObject();
    verify(entity126, atLeast(1)).getParentObject();
    verify(entity125, atLeast(1)).getParentObject();
    verify(entity124, atLeast(1)).getParentObject();
    verify(entity123, atLeast(1)).getParentObject();
    verify(entity122, atLeast(1)).getParentObject();
    verify(entity121, atLeast(1)).getParentObject();
    verify(entity120, atLeast(1)).getParentObject();
    verify(entity119, atLeast(1)).getParentObject();
    verify(entity118, atLeast(1)).getParentObject();
    verify(entity117, atLeast(1)).getParentObject();
    verify(entity116, atLeast(1)).getParentObject();
    verify(entity115, atLeast(1)).getParentObject();
    verify(entity114, atLeast(1)).getParentObject();
    verify(entity113, atLeast(1)).getParentObject();
    verify(entity112, atLeast(1)).getParentObject();
    verify(entity111, atLeast(1)).getParentObject();
    verify(entity110, atLeast(1)).getParentObject();
    verify(entity109, atLeast(1)).getParentObject();
    verify(entity108, atLeast(1)).getParentObject();
    verify(entity107, atLeast(1)).getParentObject();
    verify(entity106, atLeast(1)).getParentObject();
    verify(entity105, atLeast(1)).getParentObject();
    verify(entity104, atLeast(1)).getParentObject();
    verify(entity103, atLeast(1)).getParentObject();
    verify(entity102, atLeast(1)).getParentObject();
    verify(entity101, atLeast(1)).getParentObject();
    verify(entity100, atLeast(1)).getParentObject();
    verify(entity99, atLeast(1)).getParentObject();
    verify(entity98, atLeast(1)).getParentObject();
    verify(entity97, atLeast(1)).getParentObject();
    verify(entity96, atLeast(1)).getParentObject();
    verify(entity95, atLeast(1)).getParentObject();
    verify(entity94, atLeast(1)).getParentObject();
    verify(entity93, atLeast(1)).getParentObject();
    verify(entity92, atLeast(1)).getParentObject();
    verify(entity91, atLeast(1)).getParentObject();
    verify(entity90, atLeast(1)).getParentObject();
    verify(entity89, atLeast(1)).getParentObject();
    verify(entity88, atLeast(1)).getParentObject();
    verify(entity87, atLeast(1)).getParentObject();
    verify(entity86, atLeast(1)).getParentObject();
    verify(entity85, atLeast(1)).getParentObject();
    verify(entity84, atLeast(1)).getParentObject();
    verify(entity83, atLeast(1)).getParentObject();
    verify(entity82, atLeast(1)).getParentObject();
    verify(entity81, atLeast(1)).getParentObject();
    verify(entity80, atLeast(1)).getParentObject();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    assertNull(actualDefaultDatabaseNodeToOpen);
  }

  /**
   * Test {@link DBNUtils#getDefaultDatabaseNodeToOpen(DBRProgressMonitor, DBPDataSource)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getDefaultDatabaseNodeToOpen(DBRProgressMonitor,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNDatabaseNode DBNUtils.getDefaultDatabaseNodeToOpen(DBRProgressMonitor, DBPDataSource)"
  })
  public void testGetDefaultDatabaseNodeToOpen_givenDBVContainerWithParentIsNullAndName()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVEntity dbvEntity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    when(entity.getParentObject()).thenReturn(dbvEntity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getParentObject())
        .thenReturn(new DBSDocumentConstraint(entity126));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dataSource.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualDefaultDatabaseNodeToOpen =
        DBNUtils.getDefaultDatabaseNodeToOpen(monitor, dataSource);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(dataSource).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getParentObject();
    verify(entity126, atLeast(1)).getParentObject();
    verify(entity125, atLeast(1)).getParentObject();
    verify(entity124, atLeast(1)).getParentObject();
    verify(entity123, atLeast(1)).getParentObject();
    verify(entity122, atLeast(1)).getParentObject();
    verify(entity121, atLeast(1)).getParentObject();
    verify(entity120, atLeast(1)).getParentObject();
    verify(entity119, atLeast(1)).getParentObject();
    verify(entity118, atLeast(1)).getParentObject();
    verify(entity117, atLeast(1)).getParentObject();
    verify(entity116, atLeast(1)).getParentObject();
    verify(entity115, atLeast(1)).getParentObject();
    verify(entity114, atLeast(1)).getParentObject();
    verify(entity113, atLeast(1)).getParentObject();
    verify(entity112, atLeast(1)).getParentObject();
    verify(entity111, atLeast(1)).getParentObject();
    verify(entity110, atLeast(1)).getParentObject();
    verify(entity109, atLeast(1)).getParentObject();
    verify(entity108, atLeast(1)).getParentObject();
    verify(entity107, atLeast(1)).getParentObject();
    verify(entity106, atLeast(1)).getParentObject();
    verify(entity105, atLeast(1)).getParentObject();
    verify(entity104, atLeast(1)).getParentObject();
    verify(entity103, atLeast(1)).getParentObject();
    verify(entity102, atLeast(1)).getParentObject();
    verify(entity101, atLeast(1)).getParentObject();
    verify(entity100, atLeast(1)).getParentObject();
    verify(entity99, atLeast(1)).getParentObject();
    verify(entity98, atLeast(1)).getParentObject();
    verify(entity97, atLeast(1)).getParentObject();
    verify(entity96, atLeast(1)).getParentObject();
    verify(entity95, atLeast(1)).getParentObject();
    verify(entity94, atLeast(1)).getParentObject();
    verify(entity93, atLeast(1)).getParentObject();
    verify(entity92, atLeast(1)).getParentObject();
    verify(entity91, atLeast(1)).getParentObject();
    verify(entity90, atLeast(1)).getParentObject();
    verify(entity89, atLeast(1)).getParentObject();
    verify(entity88, atLeast(1)).getParentObject();
    verify(entity87, atLeast(1)).getParentObject();
    verify(entity86, atLeast(1)).getParentObject();
    verify(entity85, atLeast(1)).getParentObject();
    verify(entity84, atLeast(1)).getParentObject();
    verify(entity83, atLeast(1)).getParentObject();
    verify(entity82, atLeast(1)).getParentObject();
    verify(entity81, atLeast(1)).getParentObject();
    verify(entity80, atLeast(1)).getParentObject();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    assertNull(actualDefaultDatabaseNodeToOpen);
  }

  /**
   * Test {@link DBNUtils#getDefaultDatabaseNodeToOpen(DBRProgressMonitor, DBPDataSource)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVEntity#getParentObject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNUtils#getDefaultDatabaseNodeToOpen(DBRProgressMonitor,
   * DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNDatabaseNode DBNUtils.getDefaultDatabaseNodeToOpen(DBRProgressMonitor, DBPDataSource)"
  })
  public void testGetDefaultDatabaseNodeToOpen_thenCallsGetParentObject() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVEntity dbvEntity = mock(DBVEntity.class);
    when(dbvEntity.getParentObject()).thenReturn(new DBVContainer(null, "Name"));

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getParentObject()).thenReturn(dbvEntity);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getParentObject()).thenReturn(new DBSDocumentConstraint(entity));

    DBSDocumentContainer entity3 = mock(DBSDocumentContainer.class);
    when(entity3.getParentObject()).thenReturn(new DBSDocumentConstraint(entity2));

    DBSDocumentContainer entity4 = mock(DBSDocumentContainer.class);
    when(entity4.getParentObject()).thenReturn(new DBSDocumentConstraint(entity3));

    DBSDocumentContainer entity5 = mock(DBSDocumentContainer.class);
    when(entity5.getParentObject()).thenReturn(new DBSDocumentConstraint(entity4));

    DBSDocumentContainer entity6 = mock(DBSDocumentContainer.class);
    when(entity6.getParentObject()).thenReturn(new DBSDocumentConstraint(entity5));

    DBSDocumentContainer entity7 = mock(DBSDocumentContainer.class);
    when(entity7.getParentObject()).thenReturn(new DBSDocumentConstraint(entity6));

    DBSDocumentContainer entity8 = mock(DBSDocumentContainer.class);
    when(entity8.getParentObject()).thenReturn(new DBSDocumentConstraint(entity7));

    DBSDocumentContainer entity9 = mock(DBSDocumentContainer.class);
    when(entity9.getParentObject()).thenReturn(new DBSDocumentConstraint(entity8));

    DBSDocumentContainer entity10 = mock(DBSDocumentContainer.class);
    when(entity10.getParentObject()).thenReturn(new DBSDocumentConstraint(entity9));

    DBSDocumentContainer entity11 = mock(DBSDocumentContainer.class);
    when(entity11.getParentObject()).thenReturn(new DBSDocumentConstraint(entity10));

    DBSDocumentContainer entity12 = mock(DBSDocumentContainer.class);
    when(entity12.getParentObject()).thenReturn(new DBSDocumentConstraint(entity11));

    DBSDocumentContainer entity13 = mock(DBSDocumentContainer.class);
    when(entity13.getParentObject()).thenReturn(new DBSDocumentConstraint(entity12));

    DBSDocumentContainer entity14 = mock(DBSDocumentContainer.class);
    when(entity14.getParentObject()).thenReturn(new DBSDocumentConstraint(entity13));

    DBSDocumentContainer entity15 = mock(DBSDocumentContainer.class);
    when(entity15.getParentObject()).thenReturn(new DBSDocumentConstraint(entity14));

    DBSDocumentContainer entity16 = mock(DBSDocumentContainer.class);
    when(entity16.getParentObject()).thenReturn(new DBSDocumentConstraint(entity15));

    DBSDocumentContainer entity17 = mock(DBSDocumentContainer.class);
    when(entity17.getParentObject()).thenReturn(new DBSDocumentConstraint(entity16));

    DBSDocumentContainer entity18 = mock(DBSDocumentContainer.class);
    when(entity18.getParentObject()).thenReturn(new DBSDocumentConstraint(entity17));

    DBSDocumentContainer entity19 = mock(DBSDocumentContainer.class);
    when(entity19.getParentObject()).thenReturn(new DBSDocumentConstraint(entity18));

    DBSDocumentContainer entity20 = mock(DBSDocumentContainer.class);
    when(entity20.getParentObject()).thenReturn(new DBSDocumentConstraint(entity19));

    DBSDocumentContainer entity21 = mock(DBSDocumentContainer.class);
    when(entity21.getParentObject()).thenReturn(new DBSDocumentConstraint(entity20));

    DBSDocumentContainer entity22 = mock(DBSDocumentContainer.class);
    when(entity22.getParentObject()).thenReturn(new DBSDocumentConstraint(entity21));

    DBSDocumentContainer entity23 = mock(DBSDocumentContainer.class);
    when(entity23.getParentObject()).thenReturn(new DBSDocumentConstraint(entity22));

    DBSDocumentContainer entity24 = mock(DBSDocumentContainer.class);
    when(entity24.getParentObject()).thenReturn(new DBSDocumentConstraint(entity23));

    DBSDocumentContainer entity25 = mock(DBSDocumentContainer.class);
    when(entity25.getParentObject()).thenReturn(new DBSDocumentConstraint(entity24));

    DBSDocumentContainer entity26 = mock(DBSDocumentContainer.class);
    when(entity26.getParentObject()).thenReturn(new DBSDocumentConstraint(entity25));

    DBSDocumentContainer entity27 = mock(DBSDocumentContainer.class);
    when(entity27.getParentObject()).thenReturn(new DBSDocumentConstraint(entity26));

    DBSDocumentContainer entity28 = mock(DBSDocumentContainer.class);
    when(entity28.getParentObject()).thenReturn(new DBSDocumentConstraint(entity27));

    DBSDocumentContainer entity29 = mock(DBSDocumentContainer.class);
    when(entity29.getParentObject()).thenReturn(new DBSDocumentConstraint(entity28));

    DBSDocumentContainer entity30 = mock(DBSDocumentContainer.class);
    when(entity30.getParentObject()).thenReturn(new DBSDocumentConstraint(entity29));

    DBSDocumentContainer entity31 = mock(DBSDocumentContainer.class);
    when(entity31.getParentObject()).thenReturn(new DBSDocumentConstraint(entity30));

    DBSDocumentContainer entity32 = mock(DBSDocumentContainer.class);
    when(entity32.getParentObject()).thenReturn(new DBSDocumentConstraint(entity31));

    DBSDocumentContainer entity33 = mock(DBSDocumentContainer.class);
    when(entity33.getParentObject()).thenReturn(new DBSDocumentConstraint(entity32));

    DBSDocumentContainer entity34 = mock(DBSDocumentContainer.class);
    when(entity34.getParentObject()).thenReturn(new DBSDocumentConstraint(entity33));

    DBSDocumentContainer entity35 = mock(DBSDocumentContainer.class);
    when(entity35.getParentObject()).thenReturn(new DBSDocumentConstraint(entity34));

    DBSDocumentContainer entity36 = mock(DBSDocumentContainer.class);
    when(entity36.getParentObject()).thenReturn(new DBSDocumentConstraint(entity35));

    DBSDocumentContainer entity37 = mock(DBSDocumentContainer.class);
    when(entity37.getParentObject()).thenReturn(new DBSDocumentConstraint(entity36));

    DBSDocumentContainer entity38 = mock(DBSDocumentContainer.class);
    when(entity38.getParentObject()).thenReturn(new DBSDocumentConstraint(entity37));

    DBSDocumentContainer entity39 = mock(DBSDocumentContainer.class);
    when(entity39.getParentObject()).thenReturn(new DBSDocumentConstraint(entity38));

    DBSDocumentContainer entity40 = mock(DBSDocumentContainer.class);
    when(entity40.getParentObject()).thenReturn(new DBSDocumentConstraint(entity39));

    DBSDocumentContainer entity41 = mock(DBSDocumentContainer.class);
    when(entity41.getParentObject()).thenReturn(new DBSDocumentConstraint(entity40));

    DBSDocumentContainer entity42 = mock(DBSDocumentContainer.class);
    when(entity42.getParentObject()).thenReturn(new DBSDocumentConstraint(entity41));

    DBSDocumentContainer entity43 = mock(DBSDocumentContainer.class);
    when(entity43.getParentObject()).thenReturn(new DBSDocumentConstraint(entity42));

    DBSDocumentContainer entity44 = mock(DBSDocumentContainer.class);
    when(entity44.getParentObject()).thenReturn(new DBSDocumentConstraint(entity43));

    DBSDocumentContainer entity45 = mock(DBSDocumentContainer.class);
    when(entity45.getParentObject()).thenReturn(new DBSDocumentConstraint(entity44));

    DBSDocumentContainer entity46 = mock(DBSDocumentContainer.class);
    when(entity46.getParentObject()).thenReturn(new DBSDocumentConstraint(entity45));

    DBSDocumentContainer entity47 = mock(DBSDocumentContainer.class);
    when(entity47.getParentObject()).thenReturn(new DBSDocumentConstraint(entity46));

    DBSDocumentContainer entity48 = mock(DBSDocumentContainer.class);
    when(entity48.getParentObject()).thenReturn(new DBSDocumentConstraint(entity47));

    DBSDocumentContainer entity49 = mock(DBSDocumentContainer.class);
    when(entity49.getParentObject()).thenReturn(new DBSDocumentConstraint(entity48));

    DBSDocumentContainer entity50 = mock(DBSDocumentContainer.class);
    when(entity50.getParentObject()).thenReturn(new DBSDocumentConstraint(entity49));

    DBSDocumentContainer entity51 = mock(DBSDocumentContainer.class);
    when(entity51.getParentObject()).thenReturn(new DBSDocumentConstraint(entity50));

    DBSDocumentContainer entity52 = mock(DBSDocumentContainer.class);
    when(entity52.getParentObject()).thenReturn(new DBSDocumentConstraint(entity51));

    DBSDocumentContainer entity53 = mock(DBSDocumentContainer.class);
    when(entity53.getParentObject()).thenReturn(new DBSDocumentConstraint(entity52));

    DBSDocumentContainer entity54 = mock(DBSDocumentContainer.class);
    when(entity54.getParentObject()).thenReturn(new DBSDocumentConstraint(entity53));

    DBSDocumentContainer entity55 = mock(DBSDocumentContainer.class);
    when(entity55.getParentObject()).thenReturn(new DBSDocumentConstraint(entity54));

    DBSDocumentContainer entity56 = mock(DBSDocumentContainer.class);
    when(entity56.getParentObject()).thenReturn(new DBSDocumentConstraint(entity55));

    DBSDocumentContainer entity57 = mock(DBSDocumentContainer.class);
    when(entity57.getParentObject()).thenReturn(new DBSDocumentConstraint(entity56));

    DBSDocumentContainer entity58 = mock(DBSDocumentContainer.class);
    when(entity58.getParentObject()).thenReturn(new DBSDocumentConstraint(entity57));

    DBSDocumentContainer entity59 = mock(DBSDocumentContainer.class);
    when(entity59.getParentObject()).thenReturn(new DBSDocumentConstraint(entity58));

    DBSDocumentContainer entity60 = mock(DBSDocumentContainer.class);
    when(entity60.getParentObject()).thenReturn(new DBSDocumentConstraint(entity59));

    DBSDocumentContainer entity61 = mock(DBSDocumentContainer.class);
    when(entity61.getParentObject()).thenReturn(new DBSDocumentConstraint(entity60));

    DBSDocumentContainer entity62 = mock(DBSDocumentContainer.class);
    when(entity62.getParentObject()).thenReturn(new DBSDocumentConstraint(entity61));

    DBSDocumentContainer entity63 = mock(DBSDocumentContainer.class);
    when(entity63.getParentObject()).thenReturn(new DBSDocumentConstraint(entity62));

    DBSDocumentContainer entity64 = mock(DBSDocumentContainer.class);
    when(entity64.getParentObject()).thenReturn(new DBSDocumentConstraint(entity63));

    DBSDocumentContainer entity65 = mock(DBSDocumentContainer.class);
    when(entity65.getParentObject()).thenReturn(new DBSDocumentConstraint(entity64));

    DBSDocumentContainer entity66 = mock(DBSDocumentContainer.class);
    when(entity66.getParentObject()).thenReturn(new DBSDocumentConstraint(entity65));

    DBSDocumentContainer entity67 = mock(DBSDocumentContainer.class);
    when(entity67.getParentObject()).thenReturn(new DBSDocumentConstraint(entity66));

    DBSDocumentContainer entity68 = mock(DBSDocumentContainer.class);
    when(entity68.getParentObject()).thenReturn(new DBSDocumentConstraint(entity67));

    DBSDocumentContainer entity69 = mock(DBSDocumentContainer.class);
    when(entity69.getParentObject()).thenReturn(new DBSDocumentConstraint(entity68));

    DBSDocumentContainer entity70 = mock(DBSDocumentContainer.class);
    when(entity70.getParentObject()).thenReturn(new DBSDocumentConstraint(entity69));

    DBSDocumentContainer entity71 = mock(DBSDocumentContainer.class);
    when(entity71.getParentObject()).thenReturn(new DBSDocumentConstraint(entity70));

    DBSDocumentContainer entity72 = mock(DBSDocumentContainer.class);
    when(entity72.getParentObject()).thenReturn(new DBSDocumentConstraint(entity71));

    DBSDocumentContainer entity73 = mock(DBSDocumentContainer.class);
    when(entity73.getParentObject()).thenReturn(new DBSDocumentConstraint(entity72));

    DBSDocumentContainer entity74 = mock(DBSDocumentContainer.class);
    when(entity74.getParentObject()).thenReturn(new DBSDocumentConstraint(entity73));

    DBSDocumentContainer entity75 = mock(DBSDocumentContainer.class);
    when(entity75.getParentObject()).thenReturn(new DBSDocumentConstraint(entity74));

    DBSDocumentContainer entity76 = mock(DBSDocumentContainer.class);
    when(entity76.getParentObject()).thenReturn(new DBSDocumentConstraint(entity75));

    DBSDocumentContainer entity77 = mock(DBSDocumentContainer.class);
    when(entity77.getParentObject()).thenReturn(new DBSDocumentConstraint(entity76));

    DBSDocumentContainer entity78 = mock(DBSDocumentContainer.class);
    when(entity78.getParentObject()).thenReturn(new DBSDocumentConstraint(entity77));

    DBSDocumentContainer entity79 = mock(DBSDocumentContainer.class);
    when(entity79.getParentObject()).thenReturn(new DBSDocumentConstraint(entity78));

    DBSDocumentContainer entity80 = mock(DBSDocumentContainer.class);
    when(entity80.getParentObject()).thenReturn(new DBSDocumentConstraint(entity79));

    DBSDocumentContainer entity81 = mock(DBSDocumentContainer.class);
    when(entity81.getParentObject()).thenReturn(new DBSDocumentConstraint(entity80));

    DBSDocumentContainer entity82 = mock(DBSDocumentContainer.class);
    when(entity82.getParentObject()).thenReturn(new DBSDocumentConstraint(entity81));

    DBSDocumentContainer entity83 = mock(DBSDocumentContainer.class);
    when(entity83.getParentObject()).thenReturn(new DBSDocumentConstraint(entity82));

    DBSDocumentContainer entity84 = mock(DBSDocumentContainer.class);
    when(entity84.getParentObject()).thenReturn(new DBSDocumentConstraint(entity83));

    DBSDocumentContainer entity85 = mock(DBSDocumentContainer.class);
    when(entity85.getParentObject()).thenReturn(new DBSDocumentConstraint(entity84));

    DBSDocumentContainer entity86 = mock(DBSDocumentContainer.class);
    when(entity86.getParentObject()).thenReturn(new DBSDocumentConstraint(entity85));

    DBSDocumentContainer entity87 = mock(DBSDocumentContainer.class);
    when(entity87.getParentObject()).thenReturn(new DBSDocumentConstraint(entity86));

    DBSDocumentContainer entity88 = mock(DBSDocumentContainer.class);
    when(entity88.getParentObject()).thenReturn(new DBSDocumentConstraint(entity87));

    DBSDocumentContainer entity89 = mock(DBSDocumentContainer.class);
    when(entity89.getParentObject()).thenReturn(new DBSDocumentConstraint(entity88));

    DBSDocumentContainer entity90 = mock(DBSDocumentContainer.class);
    when(entity90.getParentObject()).thenReturn(new DBSDocumentConstraint(entity89));

    DBSDocumentContainer entity91 = mock(DBSDocumentContainer.class);
    when(entity91.getParentObject()).thenReturn(new DBSDocumentConstraint(entity90));

    DBSDocumentContainer entity92 = mock(DBSDocumentContainer.class);
    when(entity92.getParentObject()).thenReturn(new DBSDocumentConstraint(entity91));

    DBSDocumentContainer entity93 = mock(DBSDocumentContainer.class);
    when(entity93.getParentObject()).thenReturn(new DBSDocumentConstraint(entity92));

    DBSDocumentContainer entity94 = mock(DBSDocumentContainer.class);
    when(entity94.getParentObject()).thenReturn(new DBSDocumentConstraint(entity93));

    DBSDocumentContainer entity95 = mock(DBSDocumentContainer.class);
    when(entity95.getParentObject()).thenReturn(new DBSDocumentConstraint(entity94));

    DBSDocumentContainer entity96 = mock(DBSDocumentContainer.class);
    when(entity96.getParentObject()).thenReturn(new DBSDocumentConstraint(entity95));

    DBSDocumentContainer entity97 = mock(DBSDocumentContainer.class);
    when(entity97.getParentObject()).thenReturn(new DBSDocumentConstraint(entity96));

    DBSDocumentContainer entity98 = mock(DBSDocumentContainer.class);
    when(entity98.getParentObject()).thenReturn(new DBSDocumentConstraint(entity97));

    DBSDocumentContainer entity99 = mock(DBSDocumentContainer.class);
    when(entity99.getParentObject()).thenReturn(new DBSDocumentConstraint(entity98));

    DBSDocumentContainer entity100 = mock(DBSDocumentContainer.class);
    when(entity100.getParentObject()).thenReturn(new DBSDocumentConstraint(entity99));

    DBSDocumentContainer entity101 = mock(DBSDocumentContainer.class);
    when(entity101.getParentObject()).thenReturn(new DBSDocumentConstraint(entity100));

    DBSDocumentContainer entity102 = mock(DBSDocumentContainer.class);
    when(entity102.getParentObject()).thenReturn(new DBSDocumentConstraint(entity101));

    DBSDocumentContainer entity103 = mock(DBSDocumentContainer.class);
    when(entity103.getParentObject()).thenReturn(new DBSDocumentConstraint(entity102));

    DBSDocumentContainer entity104 = mock(DBSDocumentContainer.class);
    when(entity104.getParentObject()).thenReturn(new DBSDocumentConstraint(entity103));

    DBSDocumentContainer entity105 = mock(DBSDocumentContainer.class);
    when(entity105.getParentObject()).thenReturn(new DBSDocumentConstraint(entity104));

    DBSDocumentContainer entity106 = mock(DBSDocumentContainer.class);
    when(entity106.getParentObject()).thenReturn(new DBSDocumentConstraint(entity105));

    DBSDocumentContainer entity107 = mock(DBSDocumentContainer.class);
    when(entity107.getParentObject()).thenReturn(new DBSDocumentConstraint(entity106));

    DBSDocumentContainer entity108 = mock(DBSDocumentContainer.class);
    when(entity108.getParentObject()).thenReturn(new DBSDocumentConstraint(entity107));

    DBSDocumentContainer entity109 = mock(DBSDocumentContainer.class);
    when(entity109.getParentObject()).thenReturn(new DBSDocumentConstraint(entity108));

    DBSDocumentContainer entity110 = mock(DBSDocumentContainer.class);
    when(entity110.getParentObject()).thenReturn(new DBSDocumentConstraint(entity109));

    DBSDocumentContainer entity111 = mock(DBSDocumentContainer.class);
    when(entity111.getParentObject()).thenReturn(new DBSDocumentConstraint(entity110));

    DBSDocumentContainer entity112 = mock(DBSDocumentContainer.class);
    when(entity112.getParentObject()).thenReturn(new DBSDocumentConstraint(entity111));

    DBSDocumentContainer entity113 = mock(DBSDocumentContainer.class);
    when(entity113.getParentObject()).thenReturn(new DBSDocumentConstraint(entity112));

    DBSDocumentContainer entity114 = mock(DBSDocumentContainer.class);
    when(entity114.getParentObject()).thenReturn(new DBSDocumentConstraint(entity113));

    DBSDocumentContainer entity115 = mock(DBSDocumentContainer.class);
    when(entity115.getParentObject()).thenReturn(new DBSDocumentConstraint(entity114));

    DBSDocumentContainer entity116 = mock(DBSDocumentContainer.class);
    when(entity116.getParentObject()).thenReturn(new DBSDocumentConstraint(entity115));

    DBSDocumentContainer entity117 = mock(DBSDocumentContainer.class);
    when(entity117.getParentObject()).thenReturn(new DBSDocumentConstraint(entity116));

    DBSDocumentContainer entity118 = mock(DBSDocumentContainer.class);
    when(entity118.getParentObject()).thenReturn(new DBSDocumentConstraint(entity117));

    DBSDocumentContainer entity119 = mock(DBSDocumentContainer.class);
    when(entity119.getParentObject()).thenReturn(new DBSDocumentConstraint(entity118));

    DBSDocumentContainer entity120 = mock(DBSDocumentContainer.class);
    when(entity120.getParentObject()).thenReturn(new DBSDocumentConstraint(entity119));

    DBSDocumentContainer entity121 = mock(DBSDocumentContainer.class);
    when(entity121.getParentObject()).thenReturn(new DBSDocumentConstraint(entity120));

    DBSDocumentContainer entity122 = mock(DBSDocumentContainer.class);
    when(entity122.getParentObject()).thenReturn(new DBSDocumentConstraint(entity121));

    DBSDocumentContainer entity123 = mock(DBSDocumentContainer.class);
    when(entity123.getParentObject()).thenReturn(new DBSDocumentConstraint(entity122));

    DBSDocumentContainer entity124 = mock(DBSDocumentContainer.class);
    when(entity124.getParentObject()).thenReturn(new DBSDocumentConstraint(entity123));

    DBSDocumentContainer entity125 = mock(DBSDocumentContainer.class);
    when(entity125.getParentObject()).thenReturn(new DBSDocumentConstraint(entity124));

    DBSDocumentContainer entity126 = mock(DBSDocumentContainer.class);
    when(entity126.getParentObject()).thenReturn(new DBSDocumentConstraint(entity125));

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getParentObject())
        .thenReturn(new DBSDocumentConstraint(entity126));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer2);
    when(dataSource.getDataSource()).thenReturn(dbpDataSource);

    // Act
    DBNDatabaseNode actualDefaultDatabaseNodeToOpen =
        DBNUtils.getDefaultDatabaseNodeToOpen(monitor, dataSource);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(dataSource).getDataSource();
    verify(dbpDataSourceContainer2, atLeast(1)).getParentObject();
    verify(entity126, atLeast(1)).getParentObject();
    verify(entity125, atLeast(1)).getParentObject();
    verify(entity124, atLeast(1)).getParentObject();
    verify(entity123, atLeast(1)).getParentObject();
    verify(entity122, atLeast(1)).getParentObject();
    verify(entity121, atLeast(1)).getParentObject();
    verify(entity120, atLeast(1)).getParentObject();
    verify(entity119, atLeast(1)).getParentObject();
    verify(entity118, atLeast(1)).getParentObject();
    verify(entity117, atLeast(1)).getParentObject();
    verify(entity116, atLeast(1)).getParentObject();
    verify(entity115, atLeast(1)).getParentObject();
    verify(entity114, atLeast(1)).getParentObject();
    verify(entity113, atLeast(1)).getParentObject();
    verify(entity112, atLeast(1)).getParentObject();
    verify(entity111, atLeast(1)).getParentObject();
    verify(entity110, atLeast(1)).getParentObject();
    verify(entity109, atLeast(1)).getParentObject();
    verify(entity108, atLeast(1)).getParentObject();
    verify(entity107, atLeast(1)).getParentObject();
    verify(entity106, atLeast(1)).getParentObject();
    verify(entity105, atLeast(1)).getParentObject();
    verify(entity104, atLeast(1)).getParentObject();
    verify(entity103, atLeast(1)).getParentObject();
    verify(entity102, atLeast(1)).getParentObject();
    verify(entity101, atLeast(1)).getParentObject();
    verify(entity100, atLeast(1)).getParentObject();
    verify(entity99, atLeast(1)).getParentObject();
    verify(entity98, atLeast(1)).getParentObject();
    verify(entity97, atLeast(1)).getParentObject();
    verify(entity96, atLeast(1)).getParentObject();
    verify(entity95, atLeast(1)).getParentObject();
    verify(entity94, atLeast(1)).getParentObject();
    verify(entity93, atLeast(1)).getParentObject();
    verify(entity92, atLeast(1)).getParentObject();
    verify(entity91, atLeast(1)).getParentObject();
    verify(entity90, atLeast(1)).getParentObject();
    verify(entity89, atLeast(1)).getParentObject();
    verify(entity88, atLeast(1)).getParentObject();
    verify(entity87, atLeast(1)).getParentObject();
    verify(entity86, atLeast(1)).getParentObject();
    verify(entity85, atLeast(1)).getParentObject();
    verify(entity84, atLeast(1)).getParentObject();
    verify(entity83, atLeast(1)).getParentObject();
    verify(entity82, atLeast(1)).getParentObject();
    verify(entity81, atLeast(1)).getParentObject();
    verify(entity80, atLeast(1)).getParentObject();
    verify(entity79, atLeast(1)).getParentObject();
    verify(entity78, atLeast(1)).getParentObject();
    verify(entity77, atLeast(1)).getParentObject();
    verify(entity76, atLeast(1)).getParentObject();
    verify(entity75, atLeast(1)).getParentObject();
    verify(entity74, atLeast(1)).getParentObject();
    verify(entity73, atLeast(1)).getParentObject();
    verify(entity72, atLeast(1)).getParentObject();
    verify(entity71, atLeast(1)).getParentObject();
    verify(entity70, atLeast(1)).getParentObject();
    verify(entity69, atLeast(1)).getParentObject();
    verify(entity68, atLeast(1)).getParentObject();
    verify(entity67, atLeast(1)).getParentObject();
    verify(entity66, atLeast(1)).getParentObject();
    verify(entity65, atLeast(1)).getParentObject();
    verify(entity64, atLeast(1)).getParentObject();
    verify(entity63, atLeast(1)).getParentObject();
    verify(entity62, atLeast(1)).getParentObject();
    verify(entity61, atLeast(1)).getParentObject();
    verify(entity60, atLeast(1)).getParentObject();
    verify(entity59, atLeast(1)).getParentObject();
    verify(entity58, atLeast(1)).getParentObject();
    verify(entity57, atLeast(1)).getParentObject();
    verify(entity56, atLeast(1)).getParentObject();
    verify(entity55, atLeast(1)).getParentObject();
    verify(entity54, atLeast(1)).getParentObject();
    verify(entity53, atLeast(1)).getParentObject();
    verify(entity52, atLeast(1)).getParentObject();
    verify(entity51, atLeast(1)).getParentObject();
    verify(entity50, atLeast(1)).getParentObject();
    verify(entity49, atLeast(1)).getParentObject();
    verify(entity48, atLeast(1)).getParentObject();
    verify(entity47, atLeast(1)).getParentObject();
    verify(entity46, atLeast(1)).getParentObject();
    verify(entity45, atLeast(1)).getParentObject();
    verify(entity44, atLeast(1)).getParentObject();
    verify(entity43, atLeast(1)).getParentObject();
    verify(entity42, atLeast(1)).getParentObject();
    verify(entity41, atLeast(1)).getParentObject();
    verify(entity40, atLeast(1)).getParentObject();
    verify(entity39, atLeast(1)).getParentObject();
    verify(entity38, atLeast(1)).getParentObject();
    verify(entity37, atLeast(1)).getParentObject();
    verify(entity36, atLeast(1)).getParentObject();
    verify(entity35, atLeast(1)).getParentObject();
    verify(entity34, atLeast(1)).getParentObject();
    verify(entity33, atLeast(1)).getParentObject();
    verify(entity32, atLeast(1)).getParentObject();
    verify(entity31, atLeast(1)).getParentObject();
    verify(entity30, atLeast(1)).getParentObject();
    verify(entity29, atLeast(1)).getParentObject();
    verify(entity28, atLeast(1)).getParentObject();
    verify(entity27, atLeast(1)).getParentObject();
    verify(entity26, atLeast(1)).getParentObject();
    verify(entity25, atLeast(1)).getParentObject();
    verify(entity24, atLeast(1)).getParentObject();
    verify(entity23, atLeast(1)).getParentObject();
    verify(entity22, atLeast(1)).getParentObject();
    verify(entity21, atLeast(1)).getParentObject();
    verify(entity20, atLeast(1)).getParentObject();
    verify(entity19, atLeast(1)).getParentObject();
    verify(entity18, atLeast(1)).getParentObject();
    verify(entity17, atLeast(1)).getParentObject();
    verify(entity16, atLeast(1)).getParentObject();
    verify(entity15, atLeast(1)).getParentObject();
    verify(entity14, atLeast(1)).getParentObject();
    verify(entity13, atLeast(1)).getParentObject();
    verify(entity12, atLeast(1)).getParentObject();
    verify(entity11, atLeast(1)).getParentObject();
    verify(entity10, atLeast(1)).getParentObject();
    verify(entity9, atLeast(1)).getParentObject();
    verify(entity8, atLeast(1)).getParentObject();
    verify(entity7, atLeast(1)).getParentObject();
    verify(entity6, atLeast(1)).getParentObject();
    verify(entity5, atLeast(1)).getParentObject();
    verify(entity4, atLeast(1)).getParentObject();
    verify(entity3, atLeast(1)).getParentObject();
    verify(entity2, atLeast(1)).getParentObject();
    verify(entity, atLeast(1)).getParentObject();
    verify(dbvEntity, atLeast(1)).getParentObject();
    assertNull(actualDefaultDatabaseNodeToOpen);
  }
}
