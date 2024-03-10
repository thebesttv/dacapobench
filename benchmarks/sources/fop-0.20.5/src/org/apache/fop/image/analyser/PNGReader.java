/*
 * $Id: PNGReader.java,v 1.3.2.1 2003/02/25 13:38:24 jeremias Exp $
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
package org.apache.fop.image.analyser;

// Java
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * ImageReader object for PNG image type.
 * @author Pankaj Narula
 */
public class PNGReader extends AbstractImageReader {
    protected static final int PNG_SIG_LENGTH = 24;
    protected byte[] header;

    public boolean verifySignature(String uri, BufferedInputStream fis)
            throws IOException {
        this.imageStream = fis;
        this.setDefaultHeader();
        boolean supported = ((header[0] == (byte)0x89)
                             && (header[1] == (byte)0x50)
                             && (header[2] == (byte)0x4e)
                             && (header[3] == (byte)0x47)
                             && (header[4] == (byte)0x0d)
                             && (header[5] == (byte)0x0a)
                             && (header[6] == (byte)0x1a)
                             && (header[7] == (byte)0x0a));
        if (supported) {
            setDimension();
            return true;
        } else
            return false;
    }

    public String getMimeType() {
        return "image/png";
    }

    protected void setDimension() {
        // png is always big endian
        int byte1 = header[16] & 0xff;
        int byte2 = header[17] & 0xff;
        int byte3 = header[18] & 0xff;
        int byte4 = header[19] & 0xff;
        long l = (long)((byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4);
        this.width = (int)(l);

        byte1 = header[20] & 0xff;
        byte2 = header[21] & 0xff;
        byte3 = header[22] & 0xff;
        byte4 = header[23] & 0xff;
        l = (long)((byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4);
        this.height = (int)(l);

    }

    protected void setDefaultHeader() throws IOException {
        this.header = new byte[PNG_SIG_LENGTH];
        try {
            this.imageStream.mark(PNG_SIG_LENGTH + 1);
            this.imageStream.read(header);
            this.imageStream.reset();
        } catch (IOException ex) {
            try {
                this.imageStream.reset();
            } catch (IOException exbis) {}
            throw ex;
        }
    }

}

