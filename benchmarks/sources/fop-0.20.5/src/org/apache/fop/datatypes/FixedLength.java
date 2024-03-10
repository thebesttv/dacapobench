/*
 * $Id: FixedLength.java,v 1.1.2.1 2003/02/25 10:48:28 jeremias Exp $
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
package org.apache.fop.datatypes;

import org.apache.fop.fo.expr.Numeric;
import org.apache.fop.messaging.MessageHandler;

/**
 * a length quantity in XSL
 */
public class FixedLength extends Length {

    /**
     * Set the length given a number of relative units and the current
     * font size in base units.
     */
    public FixedLength(double numRelUnits, int iCurFontSize) {
        setComputedValue((int)(numRelUnits * (double)iCurFontSize));
    }

    /**
     * Set the length given a number of units and a unit name.
     */
    public FixedLength(double numUnits, String units) {
        convert(numUnits, units);
    }

    /**
     * set the length as a number of base units
     */
    public FixedLength(int baseUnits) {
        setComputedValue(baseUnits);
    }

    /**
     * Convert the given length to a dimensionless integer representing
     * a whole number of base units (milli-points).
     */
    protected void convert(double dvalue, String unit) {

        int assumed_resolution = 1;    // points/pixel

        if (unit.equals("in"))
            dvalue = dvalue * 72;
        else if (unit.equals("cm"))
            dvalue = dvalue * 28.3464567;
        else if (unit.equals("mm"))
            dvalue = dvalue * 2.83464567;
        else if (unit.equals("pt"))
            dvalue = dvalue;
        else if (unit.equals("pc"))
            dvalue = dvalue * 12;
            /*
             * else if (unit.equals("em"))
             * dvalue = dvalue * fontsize;
             */
        else if (unit.equals("px"))
            dvalue = dvalue * assumed_resolution;
        else {
            dvalue = 0;
            MessageHandler.errorln("unknown length unit '" + unit
                                   + "'");
        }
        setComputedValue((int)(dvalue * 1000));
    }

    public Numeric asNumeric() {
        return new Numeric(this);
    }
}
