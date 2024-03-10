/*
 * $Id: RepeatablePageMasterReference.java,v 1.3.2.6 2003/04/11 00:24:41 pietsch Exp $
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
package org.apache.fop.fo.pagination;

import org.apache.fop.fo.*;
import org.apache.fop.apps.FOPException;

/**
 * Class modeling the fo:repeatable-page-master-reference object.
 *
 * @see <a href="@XSLFO-STD@#fo_repeatable-page-master-reference"
       target="_xslfostd">@XSLFO-STDID@
 *     &para;6.4.9</a>
 */
public class RepeatablePageMasterReference extends PageMasterReference
    implements SubSequenceSpecifier {

    private static final int INFINITE = -1;

    public static class Maker extends FObj.Maker {
        public FObj make(FObj parent, PropertyList propertyList,
                        String systemId, int line, int column)
            throws FOPException {
            return new RepeatablePageMasterReference(parent, propertyList,
                                                     systemId, line, column);
        }

    }

    public static FObj.Maker maker() {
        return new RepeatablePageMasterReference.Maker();
    }

    private int maximumRepeats;
    private int numberConsumed = 0;

    public RepeatablePageMasterReference(FObj parent, PropertyList propertyList,
                                         String systemId, int line, int column)
        throws FOPException {
        super(parent, propertyList, systemId, line, column);
        if (getProperty("master-reference") != null) {
            this.masterName = getProperty("master-reference").getString();
            if (parent.getName().equals("fo:page-sequence-master")) {
                PageSequenceMaster pageSequenceMaster = (PageSequenceMaster)parent;
                pageSequenceMaster.addSubsequenceSpecifier(this);
            } else {
                throw new FOPException("A fo:repeatable-page-master-reference must be child of fo:page-sequence-master, not "
                                       + parent.getName(), systemId, line, column);
            }
        } else {
          log.warn("A fo:repeatable-page-master-reference does not have a master-reference and so is being ignored");
        }
        String mr = getProperty("maximum-repeats").getString();
        if (mr.equals("no-limit")) {
            this.maximumRepeats = INFINITE;
        } else {
            try {
                this.maximumRepeats = Integer.parseInt(mr);
                if (this.maximumRepeats < 0) {
                    log.debug("negative maximum-repeats: "+this.maximumRepeats);
                    this.maximumRepeats = 0;
                }
            } catch (NumberFormatException nfe) {
                throw new FOPException("Invalid number '" + mr
                                       + "'for 'maximum-repeats' property",
                                       systemId, line, column);
            }
        }
    }

    public String getName() {
        return "fo:repeatable-page-master-reference";
    }

    public String getNextPageMasterName(boolean isOddPage,
                                        boolean isFirstPage,
                                        boolean isEmptyPage) {
        if (maximumRepeats != INFINITE) {
            if (numberConsumed < maximumRepeats) {
                numberConsumed++;
            } else {
                return null;
            }
        }
        return getMasterName();
    }

    public void reset() {
        this.numberConsumed = 0;
    }

}
