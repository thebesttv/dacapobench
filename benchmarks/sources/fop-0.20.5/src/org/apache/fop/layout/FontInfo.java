/*
 * $Id: FontInfo.java,v 1.12.2.4 2003/02/25 14:07:03 jeremias Exp $
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
package org.apache.fop.layout;

import org.apache.fop.apps.FOPException;
import org.apache.fop.messaging.MessageHandler;

import java.util.HashMap;

public class FontInfo {
    HashMap usedFonts;
    HashMap triplets;    // look up a font-triplet to find a font-name
    HashMap fonts;    // look up a font-name to get a font (that implements FontMetric at least)

    public FontInfo() {
        this.triplets = new HashMap();
        this.fonts = new HashMap();
        this.usedFonts = new HashMap();
    }

    public void addFontProperties(String name, String family, String style,
                                  String weight) {
        /*
         * add the given family, style and weight as a lookup for the font
         * with the given name
         */

        String key = createFontKey(family, style, weight);
        this.triplets.put(key, name);
    }

    public void addMetrics(String name, FontMetric metrics) {
        // add the given metrics as a font with the given name

        this.fonts.put(name, metrics);
    }

    public String fontLookup(String family, String style,
                             String weight) throws FOPException {
        return fontLookup(createFontKey(family, style, weight));
    }

    public String fontLookup(String key) throws FOPException {

        String f = (String)this.triplets.get(key);
        if (f == null) {
            int i = key.indexOf(',');
            String s = "any" + key.substring(i);
            f = (String)this.triplets.get(s);
            if (f == null) {
                f = (String)this.triplets.get("any,normal,normal");
                if (f == null) {
                    throw new FOPException("no default font defined by OutputConverter");
                }
                MessageHandler.errorln("defaulted font to any,normal,normal");
            }
            MessageHandler.errorln("unknown font " + key
                                   + " so defaulted font to any");
        }

        usedFonts.put(f, fonts.get(f));
        return f;
    }

    public boolean hasFont(String family, String style, String weight) {
        String key = createFontKey(family, style, weight);
        return this.triplets.get(key) != null;
    }

    public boolean hasFont(String key) {
        return this.triplets.get(key) != null;
    }

    /**
     * Creates a key from the given strings
     */
    public static String createFontKey(String family, String style,
                                       String weight) {
        int i;

        try {
            i = Integer.parseInt(weight);
        } catch (NumberFormatException e) {
            i = 0;
        }

        if (i > 600)
            weight = "bold";
        else if (i > 0)
            weight = "normal";

        return family + "," + style + "," + weight;
    }

    public HashMap getFonts() {
        return this.fonts;
    }

    public HashMap getUsedFonts() {
        return this.usedFonts;
    }

    public FontMetric getMetricsFor(String fontName) throws FOPException {
        usedFonts.put(fontName, fonts.get(fontName));
        return (FontMetric)fonts.get(fontName);
    }

}
