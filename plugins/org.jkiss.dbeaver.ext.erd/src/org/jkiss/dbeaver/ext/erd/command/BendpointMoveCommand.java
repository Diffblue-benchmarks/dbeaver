/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2016 Serge Rieder (serge@jkiss.org)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jkiss.dbeaver.ext.erd.command;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.jkiss.dbeaver.ext.erd.part.AssociationPart;

public class BendpointMoveCommand extends BendpointCommand {

    private Point location;
    private int bendpointIndex;

    private Bendpoint oldBendpoint;

    public BendpointMoveCommand(AssociationPart association, Point location, int bendpointIndex) {
        super(association);
        this.location = location;
        this.bendpointIndex = bendpointIndex;
    }

    @Override
    public void execute() {
        association.moveBendpoint(bendpointIndex, location);
/*
        WireBendpoint bp = new WireBendpoint();
        bp.setRelativeDimensions(getFirstRelativeDimension(),
            getSecondRelativeDimension());
        setOldBendpoint((Bendpoint) getWire().getBendpoints().get(getIndex()));
        getWire().setBendpoint(getIndex(), bp);
        super.execute();
*/
    }

    @Override
    public void undo() {
/*
        super.undo();
        getWire().setBendpoint(getIndex(), getOldBendpoint());
*/
    }

}


