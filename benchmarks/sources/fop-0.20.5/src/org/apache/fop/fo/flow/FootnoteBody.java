/*
 * $Id: FootnoteBody.java,v 1.6.2.7 2003/04/11 00:24:38 pietsch Exp $
 * ============================================================================
 *                    The Apache Software License, Version 1.1
 * ============================================================================
 * 
 * Copyright (C) 1999-2003 The Apache Software Foundation. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modifica-
 * tion, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment: "This product includes software
 *    developed by the Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 * 
 * 4. The names "FOP" and "Apache Software Foundation" must not be used to
 *    endorse or promote products derived from this software without prior
 *    written permission. For written permission, please contact
 *    apache@apache.org.
 * 
 * 5. Products derived from this software may not be called "Apache", nor may
 *    "Apache" appear in their name, without prior written permission of the
 *    Apache Software Foundation.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * APACHE SOFTWARE FOUNDATION OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLU-
 * DING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ============================================================================
 * 
 * This software consists of voluntary contributions made by many individuals
 * on behalf of the Apache Software Foundation and was originally created by
 * James Tauber <jtauber@jtauber.com>. For more information on the Apache
 * Software Foundation, please see <http://www.apache.org/>.
 */ 
package org.apache.fop.fo.flow;

// FOP
import org.apache.fop.fo.*;
import org.apache.fop.layout.Area;
import org.apache.fop.layout.AreaClass;
import org.apache.fop.apps.FOPException;
import org.apache.fop.layout.*;

public class FootnoteBody extends FObj {

    int align;
    int alignLast;
    int lineHeight;
    int startIndent;
    int endIndent;
    int textIndent;

    public static class Maker extends FObj.Maker {
        public FObj make(FObj parent, PropertyList propertyList,
                         String systemId, int line, int column)
            throws FOPException {
            return new FootnoteBody(parent, propertyList,
                                    systemId, line, column);
        }

    }

    public static FObj.Maker maker() {
        return new FootnoteBody.Maker();
    }

    public FootnoteBody(FObj parent, PropertyList propertyList,
                        String systemId, int line, int column)
        throws FOPException {
        super(parent, propertyList, systemId, line, column);
        this.areaClass = AreaClass.setAreaClass(AreaClass.XSL_FOOTNOTE);
    }

    public String getName() {
        return "fo:footnote-body";
    }

    public int layout(Area area) throws FOPException {
        if (this.marker == START) {
            this.marker = 0;
        }
        BlockArea blockArea =
            new BlockArea(propMgr.getFontState(area.getFontInfo()),
                          area.getAllocationWidth(), area.spaceLeft(),
                          startIndent, endIndent, textIndent, align,
                          alignLast, lineHeight);
        blockArea.setGeneratedBy(this);
        blockArea.isFirst(true);
        blockArea.setParent(area);
        blockArea.setPage(area.getPage());
        blockArea.start();

        blockArea.setAbsoluteHeight(area.getAbsoluteHeight());
        blockArea.setIDReferences(area.getIDReferences());

        blockArea.setTableCellXOffset(area.getTableCellXOffset());

        int numChildren = this.children.size();
        for (int i = this.marker; i < numChildren; i++) {
            FONode fo = (FONode)children.get(i);
            int status;
            if (Status.isIncomplete((status = fo.layout(blockArea)))) {
                this.resetMarker();
                return status;
            }
        }
        blockArea.end();
        area.addChild(blockArea);
        area.increaseHeight(blockArea.getHeight());
        blockArea.isLast(true);
        return Status.OK;
    }

}
