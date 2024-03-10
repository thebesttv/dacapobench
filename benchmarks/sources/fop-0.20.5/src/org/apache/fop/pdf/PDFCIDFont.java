/*
 * $Id: PDFCIDFont.java,v 1.3.2.2 2003/02/25 14:29:37 jeremias Exp $
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
package org.apache.fop.pdf;

// Java
import java.io.UnsupportedEncodingException;

// based on work by Takayuki Takeuchi

/**
 * class representing a "character identifier" font (p 210 and onwards).
 */
public class PDFCIDFont extends PDFObject {

    public static final byte CID_TYPE0 = 0;
    public static final byte CID_TYPE2 = 1;
    protected static final String[] TYPE_NAMES = {
        "CIDFontType0", "CIDFontType2"
    };

    protected String basefont;

    protected String cidtype;
    protected Integer dw;
    protected PDFWArray w;
    protected int[] dw2;
    protected PDFWArray w2;
    protected PDFCIDSystemInfo systemInfo;
    protected PDFCIDFontDescriptor descriptor;
    protected PDFCMap cmap;

    /**
     * /CIDToGIDMap (only for CIDFontType2, see p 212)
     * can be either "Identity" (default) or a PDFStream
     */
    protected PDFStream cidMap;

    // compatibility with Takayuki Takeuchi

    /**
     * create the /Font object
     */
    public PDFCIDFont(int number, String basefont, byte cidtype, int dw,
                      int[] w, String registry, String ordering,
                      int supplement, PDFCIDFontDescriptor descriptor) {

        super(number);

        this.basefont = basefont;
        this.cidtype = TYPE_NAMES[(int)cidtype];
        this.dw = new Integer(dw);
        this.w = new PDFWArray();
        this.w.addEntry(0, w);
        this.dw2 = null;
        this.w2 = null;
        this.systemInfo = new PDFCIDSystemInfo(registry, ordering,
                                               supplement);
        this.descriptor = descriptor;
        this.cidMap = null;
        this.cmap = null;
    }

    /**
     * create the /Font object
     */
    public PDFCIDFont(int number, String basefont, byte cidtype, int dw,
                      PDFWArray w, PDFCIDSystemInfo systemInfo,
                      PDFCIDFontDescriptor descriptor) {

        super(number);

        this.basefont = basefont;
        this.cidtype = TYPE_NAMES[(int)cidtype];
        this.dw = new Integer(dw);
        this.w = w;
        this.dw2 = null;
        this.w2 = null;
        this.systemInfo = systemInfo;
        this.descriptor = descriptor;
        this.cidMap = null;
        this.cmap = null;
    }

    /**
     * set the /DW attribute
     */
    public void setDW(int dw) {
        this.dw = new Integer(dw);
    }

    /**
     * set the /W array
     */
    public void setW(PDFWArray w) {
        this.w = w;
    }

    /**
     * set the (two elements) /DW2 array
     */
    public void setDW2(int[] dw2) {
        this.dw2 = dw2;
    }

    /**
     * set the two elements of the /DW2 array
     */
    public void setDW2(int posY, int displacementY) {
        this.dw2 = new int[] {
            posY, displacementY
        };
    }

    /**
     * Set the CMap used as /ToUnicode cmap
     */
    public void setCMAP(PDFCMap cmap) {
        this.cmap = cmap;
    }

    /**
     * set the /W2 array
     */
    public void setW2(PDFWArray w2) {
        this.w2 = w2;
    }

    /**
     * set the /CIDToGIDMap (to be used only for CIDFontType2)
     */
    public void setCIDMap(PDFStream map) {
        this.cidMap = map;
    }

    /**
     * set the /CIDToGIDMap (to be used only for CIDFontType2) to "Identity"
     */
    public void setCIDMapIdentity() {
        this.cidMap = null;    // not an error here, simply use the default
    }

    /**
     * produce the PDF representation for the object
     *
     * @return the PDF
     */
    public byte[] toPDF() {
        try {
            return toPDFString().getBytes(PDFDocument.ENCODING);
        } catch (UnsupportedEncodingException ue) {
            return toPDFString().getBytes();
        }       
    }

    public String toPDFString() {
        StringBuffer p = new StringBuffer();
        p.append(this.number);
        p.append(" ");
        p.append(this.generation);
        p.append(" obj\n<< /Type /Font");
        p.append("\n/BaseFont /");
        p.append(this.basefont);
        if (cidMap != null) {
            p.append(" \n/CIDToGIDMap ");
            p.append(cidMap.referencePDF());
        }
        p.append(" \n/Subtype /");
        p.append(this.cidtype);
        p.append("\n");
        p.append(systemInfo.toPDFString());
        p.append("\n/FontDescriptor ");
        p.append(this.descriptor.referencePDF());

        if (cmap != null) {
            p.append("\n/ToUnicode ");
            p.append(cmap.referencePDF());
        }
        if (dw != null) {
            p.append("\n/DW ");
            p.append(this.dw);
        }
        if (w != null) {
            p.append("\n/W ");
            p.append(w.toPDFString());
        }
        if (dw2 != null) {
            p.append("\n/DW2 [");    // always two values, see p 211
            p.append(this.dw2[0]);
            p.append(this.dw2[1]);
            p.append("] \n>>\nendobj\n");
        }
        if (w2 != null) {
            p.append("\n/W2 ");
            p.append(w2.toPDFString());
            p.append(" \n>>\nendobj\n");
        }
        p.append(" \n>>\nendobj\n");
        return p.toString();
    }

}

