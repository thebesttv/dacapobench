/*
 * $Id: TimesBoldItalic.java,v 1.2.2.1 2003/02/25 14:39:49 jeremias Exp $
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
package org.apache.fop.render.mif.fonts;

import org.apache.fop.render.mif.Font;

public class TimesBoldItalic extends Font {
    private final static String fontName = "Times-BoldItalic";
    private final static String encoding = "WinAnsiEncoding";
    private final static int capHeight = 669;
    private final static int xHeight = 462;
    private final static int ascender = 699;
    private final static int descender = -205;
    private final static int firstChar = 32;
    private final static int lastChar = 255;
    private final static int[] width;

    static {
        width = new int[256];
        width[0x0041] = 667;
        width[0x00C6] = 944;
        width[0x00C1] = 667;
        width[0x00C2] = 667;
        width[0x00C4] = 667;
        width[0x00C0] = 667;
        width[0x00C5] = 667;
        width[0x00C3] = 667;
        width[0x0042] = 667;
        width[0x0043] = 667;
        width[0x00C7] = 667;
        width[0x0044] = 722;
        width[0x0045] = 667;
        width[0x00C9] = 667;
        width[0x00CA] = 667;
        width[0x00CB] = 667;
        width[0x00C8] = 667;
        width[0x00D0] = 722;
        width[0x0046] = 667;
        width[0x0047] = 722;
        width[0x0048] = 778;
        width[0x0049] = 389;
        width[0x00CD] = 389;
        width[0x00CE] = 389;
        width[0x00CF] = 389;
        width[0x00CC] = 389;
        width[0x004A] = 500;
        width[0x004B] = 667;
        width[0x004C] = 611;
        width[0x004D] = 889;
        width[0x004E] = 722;
        width[0x00D1] = 722;
        width[0x004F] = 722;
        width[0x008C] = 944;
        width[0x00D3] = 722;
        width[0x00D4] = 722;
        width[0x00D6] = 722;
        width[0x00D2] = 722;
        width[0x00D8] = 722;
        width[0x00D5] = 722;
        width[0x0050] = 611;
        width[0x0051] = 722;
        width[0x0052] = 667;
        width[0x0053] = 556;
        width[0x008A] = 556;
        width[0x0054] = 611;
        width[0x00DE] = 611;
        width[0x0055] = 722;
        width[0x00DA] = 722;
        width[0x00DB] = 722;
        width[0x00DC] = 722;
        width[0x00D9] = 722;
        width[0x0056] = 667;
        width[0x0057] = 889;
        width[0x0058] = 667;
        width[0x0059] = 611;
        width[0x00DD] = 611;
        width[0x009F] = 611;
        width[0x005A] = 611;
        width[0x0061] = 500;
        width[0x00E1] = 500;
        width[0x00E2] = 500;
        width[0x00B4] = 333;
        width[0x00E4] = 500;
        width[0x00E6] = 722;
        width[0x00E0] = 500;
        width[0x0026] = 778;
        width[0x00E5] = 500;
        width[0x005E] = 570;
        width[0x007E] = 570;
        width[0x002A] = 500;
        width[0x0040] = 832;
        width[0x00E3] = 500;
        width[0x0062] = 500;
        width[0x005C] = 278;
        width[0x007C] = 220;
        width[0x007B] = 348;
        width[0x007D] = 348;
        width[0x005B] = 333;
        width[0x005D] = 333;
        width[0x00A6] = 220;
        width[0x0095] = 350;
        width[0x0063] = 444;
        width[0x00E7] = 444;
        width[0x00B8] = 333;
        width[0x00A2] = 500;
        width[0x0088] = 333;
        width[0x003A] = 333;
        width[0x002C] = 250;
        width[0x00A9] = 747;
        width[0x00A4] = 500;
        width[0x0064] = 500;
        width[0x0086] = 500;
        width[0x0087] = 500;
        width[0x00B0] = 400;
        width[0x00A8] = 333;
        width[0x00F7] = 570;
        width[0x0024] = 500;
        width[0x0065] = 444;
        width[0x00E9] = 444;
        width[0x00EA] = 444;
        width[0x00EB] = 444;
        width[0x00E8] = 444;
        width[0x0038] = 500;
        width[0x0085] = 1000;
        width[0x0097] = 1000;
        width[0x0096] = 500;
        width[0x003D] = 570;
        width[0x00F0] = 500;
        width[0x0021] = 389;
        width[0x00A1] = 389;
        width[0x0066] = 333;
        width[0x0035] = 500;
        width[0x0083] = 500;
        width[0x0034] = 500;
        width[0xA4] = 167;
        width[0x0067] = 500;
        width[0x00DF] = 500;
        width[0x0060] = 333;
        width[0x003E] = 570;
        width[0x00AB] = 500;
        width[0x00BB] = 500;
        width[0x008B] = 333;
        width[0x009B] = 333;
        width[0x0068] = 556;
        width[0x002D] = 333;
        width[0x0069] = 278;
        width[0x00ED] = 278;
        width[0x00EE] = 278;
        width[0x00EF] = 278;
        width[0x00EC] = 278;
        width[0x006A] = 278;
        width[0x006B] = 500;
        width[0x006C] = 278;
        width[0x003C] = 570;
        width[0x00AC] = 606;
        width[0x006D] = 778;
        width[0x00AF] = 333;
        width[0x2D] = 606;
        width[0x00B5] = 576;
        width[0x00D7] = 570;
        width[0x006E] = 556;
        width[0x0039] = 500;
        width[0x00F1] = 556;
        width[0x0023] = 500;
        width[0x006F] = 500;
        width[0x00F3] = 500;
        width[0x00F4] = 500;
        width[0x00F6] = 500;
        width[0x009C] = 722;
        width[0x00F2] = 500;
        width[0x0031] = 500;
        width[0x00BD] = 750;
        width[0x00BC] = 750;
        width[0x00B9] = 300;
        width[0x00AA] = 266;
        width[0x00BA] = 300;
        width[0x00F8] = 500;
        width[0x00F5] = 500;
        width[0x0070] = 500;
        width[0x00B6] = 500;
        width[0x0028] = 333;
        width[0x0029] = 333;
        width[0x0025] = 833;
        width[0x002E] = 250;
        width[0x00B7] = 250;
        width[0x0089] = 1000;
        width[0x002B] = 570;
        width[0x00B1] = 570;
        width[0x0071] = 500;
        width[0x003F] = 500;
        width[0x00BF] = 500;
        width[0x0022] = 555;
        width[0x0084] = 500;
        width[0x0093] = 500;
        width[0x0094] = 500;
        width[0x0091] = 333;
        width[0x0092] = 333;
        width[0x0082] = 333;
        width[0x0027] = 278;
        width[0x0072] = 389;
        width[0x00AE] = 747;
        width[0x00B0] = 333;
        width[0x0073] = 389;
        width[0x009A] = 389;
        width[0x00A7] = 500;
        width[0x003B] = 333;
        width[0x0037] = 500;
        width[0x0036] = 500;
        width[0x002F] = 278;
        width[0x0020] = 250;
        width[0x00A0] = 250;
        width[0x00A3] = 500;
        width[0x0074] = 278;
        width[0x00FE] = 500;
        width[0x0033] = 500;
        width[0x00BE] = 750;
        width[0x00B3] = 300;
        width[0x0098] = 333;
        width[0x0099] = 1000;
        width[0x0032] = 500;
        width[0x00B2] = 300;
        width[0x0075] = 556;
        width[0x00FA] = 556;
        width[0x00FB] = 556;
        width[0x00FC] = 556;
        width[0x00F9] = 556;
        width[0x005F] = 500;
        width[0x0076] = 444;
        width[0x0077] = 667;
        width[0x0078] = 500;
        width[0x0079] = 444;
        width[0x00FD] = 444;
        width[0x00FF] = 444;
        width[0x00A5] = 500;
        width[0x007A] = 389;
        width[0x0030] = 500;

    }

    public String encoding() {
        return encoding;
    }

    public String fontName() {
        return fontName;
    }

    public int getAscender(int size) {
        return size * ascender;
    }

    public int getCapHeight(int size) {
        return size * capHeight;
    }

    public int getDescender(int size) {
        return size * descender;
    }

    public int getXHeight(int size) {
        return size * xHeight;
    }

    public int getFirstChar() {
        return firstChar;
    }

    public int getLastChar() {
        return lastChar;
    }

    public int width(int i, int size) {
        return size * width[i];
    }

    public int[] getWidths(int size) {
        int[] arr = new int[getLastChar() - getFirstChar() + 1];
        System.arraycopy(width, getFirstChar(), arr, 0,
                         getLastChar() - getFirstChar() + 1);
        for (int i = 0; i < arr.length; i++)
            arr[i] *= size;
        return arr;
    }

}

