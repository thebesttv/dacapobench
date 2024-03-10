/*
 * $Id: FopImage.java,v 1.8.2.2 2003/02/25 13:38:22 jeremias Exp $
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
package org.apache.fop.image;

import org.apache.fop.datatypes.ColorSpace;
import org.apache.fop.pdf.PDFColor;
import org.apache.fop.pdf.PDFFilter;

/**
 * Class which represents an image
 * @author Eric SCHAEFFER
 */
public interface FopImage {
    // Init the object.
    // If href protocol isn't file://, can load the entire image
    // and keep it in memory.
    // Should cache the input stream, and load data when needed.
    // public FopImage(URL href) throws FopImageException;

    // Get image general properties.
    // Methods throw exception because they can retrieve data
    // when needed.

    boolean invertImage();

    // Resource location
    String getURL();

    // image size
    int getWidth() throws FopImageException;
    int getHeight() throws FopImageException;

    // DeviceGray, DeviceRGB, or DeviceCMYK
    ColorSpace getColorSpace() throws FopImageException;

    // bits per pixel
    int getBitsPerPixel() throws FopImageException;

    // For transparent images
    boolean isTransparent() throws FopImageException;
    PDFColor getTransparentColor() throws FopImageException;

    // get the image bytes, and bytes properties

    // get uncompressed image bytes
    byte[] getBitmaps() throws FopImageException;
    // width * (bitsPerPixel / 8) * height, no ?
    int getBitmapsSize() throws FopImageException;

    // get compressed image bytes
    // I don't know if we really need it, nor if it
    // should be changed...
    byte[] getRessourceBytes() throws FopImageException;
    int getRessourceBytesSize() throws FopImageException;
    // return null if no corresponding PDFFilter
    PDFFilter getPDFFilter() throws FopImageException;

    // release memory
    void close();
}
