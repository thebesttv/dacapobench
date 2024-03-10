/*
 * $Id: InstreamForeignObject.java,v 1.14.2.7 2003/04/11 00:24:38 pietsch Exp $
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
import org.apache.fop.fo.properties.*;
import org.apache.fop.layout.*;
import org.apache.fop.layout.inline.*;
import org.apache.fop.layout.BlockArea;
import org.apache.fop.apps.FOPException;

public class InstreamForeignObject extends FObj {

    /**
     * inner class for making SVG objects.
     */
    public static class Maker extends FObj.Maker {

        /**
         * make an SVG object.
         *
         * @param parent the parent formatting object
         * @param propertyList the explicit properties of this object
         *
         * @return the SVG object
         */
        public FObj make(FObj parent, PropertyList propertyList,
                         String systemId, int line, int column)
            throws FOPException {
            return new InstreamForeignObject(parent, propertyList,
                                             systemId, line, column);
        }

    }

    /**
     * returns the maker for this object.
     *
     * @return the maker for SVG objects
     */
    public static FObj.Maker maker() {
        return new InstreamForeignObject.Maker();
    }

    int breakBefore;
    int breakAfter;
    int scaling;
    int width;
    int height;
    int contwidth;
    int contheight;
    boolean wauto;
    boolean hauto;
    boolean cwauto;
    boolean chauto;
    int spaceBefore;
    int spaceAfter;
    int startIndent;
    int endIndent;

    ForeignObjectArea areaCurrent;

    /**
     * constructs an instream-foreign-object object (called by Maker).
     *
     * @param parent the parent formatting object
     * @param propertyList the explicit properties of this object
     */
    public InstreamForeignObject(FObj parent, PropertyList propertyList,
                                 String systemId, int line, int column) {
        super(parent, propertyList, systemId, line, column);
    }

    public String getName() {
        return "fo:instream-foreign-object";
    }

    /**
     * layout this formatting object.
     *
     * @param area the area to layout the object into
     *
     * @return the status of the layout
     */
    public int layout(Area area) throws FOPException {

        if (this.marker == BREAK_AFTER) {
            return Status.OK;
        }

        if (this.marker == START) {

            // Common Accessibility Properties
            AccessibilityProps mAccProps = propMgr.getAccessibilityProps();
        
            // Common Aural Properties
            AuralProps mAurProps = propMgr.getAuralProps();

            // Common Border, Padding, and Background Properties
            BorderAndPadding bap = propMgr.getBorderAndPadding();
            BackgroundProps bProps = propMgr.getBackgroundProps();

            // Common Margin Properties-Inline
            MarginInlineProps mProps = propMgr.getMarginInlineProps();

            // Common Relative Position Properties
            RelativePositionProps mRelProps = propMgr.getRelativePositionProps();

            // this.properties.get("alignment-adjust");
            // this.properties.get("alignment-baseline");
            // this.properties.get("baseline-shift");
            // this.properties.get("block-progression-dimension");
            // this.properties.get("content-height");
            // this.properties.get("content-type");
            // this.properties.get("content-width");
            // this.properties.get("display-align");
            // this.properties.get("dominant-baseline");
            // this.properties.get("height");  
            // this.properties.get("id");
            // this.properties.get("inline-progression-dimension");
            // this.properties.get("keep-with-next");
            // this.properties.get("keep-with-previous");
            // this.properties.get("line-height");
            // this.properties.get("line-height-shift-adjustment");
            // this.properties.get("overflow");
            // this.properties.get("scaling");
            // this.properties.get("scaling-method");
            // this.properties.get("text-align");
            // this.properties.get("width");

            /* retrieve properties */
            String id = this.properties.get("id").getString();
            int align = this.properties.get("text-align").getEnum();
            int valign = this.properties.get("vertical-align").getEnum();
            int overflow = this.properties.get("overflow").getEnum();

            this.breakBefore = this.properties.get("break-before").getEnum();
            this.breakAfter = this.properties.get("break-after").getEnum();
            this.width = this.properties.get("width").getLength().mvalue();
            this.height = this.properties.get("height").getLength().mvalue();
            this.contwidth =
                this.properties.get("content-width").getLength().mvalue();
            this.contheight =
                this.properties.get("content-height").getLength().mvalue();
            this.wauto = this.properties.get("width").getLength().isAuto();
            this.hauto = this.properties.get("height").getLength().isAuto();
            this.cwauto =
                this.properties.get("content-width").getLength().isAuto();
            this.chauto =
                this.properties.get("content-height").getLength().isAuto();

            this.startIndent =
                this.properties.get("start-indent").getLength().mvalue();
            this.endIndent =
                this.properties.get("end-indent").getLength().mvalue();
            this.spaceBefore =
                this.properties.get("space-before.optimum").getLength().mvalue();
            this.spaceAfter =
                this.properties.get("space-after.optimum").getLength().mvalue();

            this.scaling = this.properties.get("scaling").getEnum();

            try {
                area.getIDReferences().createID(id);
            }
            catch(FOPException e) {
                if (!e.isLocationSet()) {
                    e.setLocation(systemId, line, column);
                }
                throw e;
            }
            if (this.areaCurrent == null) {
                this.areaCurrent =
                    new ForeignObjectArea(propMgr.getFontState(area.getFontInfo()),
                                          area.getAllocationWidth());

                this.areaCurrent.start();
                areaCurrent.setWidth(this.width);
                areaCurrent.setHeight(this.height);
                areaCurrent.setContentWidth(this.contwidth);
                areaCurrent.setContentHeight(this.contheight);
                areaCurrent.setScaling(this.scaling);
                areaCurrent.setAlign(align);
                areaCurrent.setVerticalAlign(valign);
                areaCurrent.setOverflow(overflow);
                areaCurrent.setSizeAuto(wauto, hauto);
                areaCurrent.setContentSizeAuto(cwauto, chauto);

                // this means that children can get the fontstate
                areaCurrent.setPage(area.getPage());

                int numChildren = this.children.size();
                if (numChildren > 1) {
                    throw new FOPException("Only one child element is allowed in an instream-foreign-object", systemId, line, column);
                }
                /* layout foreign object */
                if (this.children.size() > 0) {
                    FONode fo = (FONode)children.get(0);
                    int status;
                    // currently FONode must be an SVG
                    if (Status.isIncomplete((status =
                                                 fo.layout(this.areaCurrent)))) {
                        return status;
                    }

                    /* finish off the foreign object area */
                    this.areaCurrent.end();
                }
            }

            this.marker = 0;

            if (breakBefore == BreakBefore.PAGE
                    || ((spaceBefore + areaCurrent.getEffectiveHeight())
                        > area.spaceLeft())) {
                return Status.FORCE_PAGE_BREAK;
            }

            if (breakBefore == BreakBefore.ODD_PAGE) {
                return Status.FORCE_PAGE_BREAK_ODD;
            }

            if (breakBefore == BreakBefore.EVEN_PAGE) {
                return Status.FORCE_PAGE_BREAK_EVEN;
            }
        }

        if (this.areaCurrent == null) {
            return Status.OK;
        }

        if (area instanceof BlockArea) {
            BlockArea ba = (BlockArea)area;
            LineArea la = ba.getCurrentLineArea();
            if (la == null) {
                return Status.AREA_FULL_NONE;
            }
            la.addPending();
            if (areaCurrent.getEffectiveWidth() > la.getRemainingWidth()) {
                la = ba.createNextLineArea();
                if (la == null) {
                    return Status.AREA_FULL_NONE;
                }
            }
            la.addInlineArea(areaCurrent, this.getLinkSet());
        } else {
            area.addChild(areaCurrent);
            area.increaseHeight(areaCurrent.getEffectiveHeight());
        }

        if (this.isInTableCell) {
            startIndent += forcedStartOffset;
            /*
             * endIndent = areaCurrent.getEffectiveWidth() - forcedWidth -
             * forcedStartOffset;
             */
        }

        areaCurrent.setStartIndent(startIndent);
        // areaCurrent.setEndIndent(endIndent);

        /* if there is a space-before */
        if (spaceBefore != 0) {
            /* add a display space */
            // area.addDisplaySpace(spaceBefore);
        }

        /* add the SVG area to the containing area */
        // area.addChild(areaCurrent);

        areaCurrent.setPage(area.getPage());

        /* increase the height of the containing area accordingly */
        // area.increaseHeight(areaCurrent.getEffectiveHeight());

        /* if there is a space-after */
        if (spaceAfter != 0) {
            /* add a display space */
            // area.addDisplaySpace(spaceAfter);
        }


        if (breakAfter == BreakAfter.PAGE) {
            this.marker = BREAK_AFTER;
            return Status.FORCE_PAGE_BREAK;
        }

        if (breakAfter == BreakAfter.ODD_PAGE) {
            this.marker = BREAK_AFTER;
            return Status.FORCE_PAGE_BREAK_ODD;
        }

        if (breakAfter == BreakAfter.EVEN_PAGE) {
            this.marker = BREAK_AFTER;
            return Status.FORCE_PAGE_BREAK_EVEN;
        }

        areaCurrent = null;
        /* return status */
        return Status.OK;
    }

}

