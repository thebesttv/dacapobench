/*
 * $Id: UnknownXMLObj.java,v 1.2.2.5 2003/05/14 15:28:39 jeremias Exp $
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
package org.apache.fop.fo;

import org.apache.fop.layout.Area;
import org.apache.fop.apps.FOPException;

public class UnknownXMLObj extends XMLObj {
    String namespace;

    /**
     * inner class for making unknown xml objects.
     */
    public static class Maker extends FObj.Maker {
        String space;
        String tag;

        Maker(String sp, String t) {
            space = sp;
            tag = t;
        }

        /**
         * make an unknown xml object.
         *
         * @param parent the parent formatting object
         * @param propertyList the explicit properties of this object
         *
         * @return the unknown xml object
         */
        public FObj make(FObj parent, PropertyList propertyList,
                         String systemId, int line, int column)
            throws FOPException {
            return new UnknownXMLObj(parent, propertyList, space, tag,
                                     systemId, line, column);
        }
    }

    /**
     * returns the maker for this object.
     *
     * @return the maker for an unknown xml object
     */
    public static FObj.Maker maker(String space, String tag) {
        return new UnknownXMLObj.Maker(space, tag);
    }

    /**
     * constructs an unknown xml object (called by Maker).
     *
     * @param parent the parent formatting object
     * @param propertyList the explicit properties of this object
     */
    protected UnknownXMLObj(FObj parent, PropertyList propertyList,
                            String namespace, String name,
                            String systemId, int line, int column) {
        super(parent, propertyList, name, systemId, line, column);
        this.namespace = namespace;
    }

    public String getNameSpace() {
        return this.namespace;
    }

    protected void addChild(FONode child) {
        if(doc == null) {
            createBasicDocument();
        }
        super.addChild(child);
    }

    protected void addCharacters(char data[], int start, int length) {
        if(doc == null) {
            createBasicDocument();
        }
        super.addCharacters(data, start, length);
    }

    public int layout(Area area) throws FOPException {
        //if (!(area instanceof ForeignObjectArea)) {
            // this is an error
            //throw new FOPException("Foreign XML not in fo:instream-foreign-object");
        //}
        log.error("no handler defined for " + this.getName() + " foreign xml");

        /* return status */
        return Status.OK;
    }

    public String getName() {
        if (namespace == null || namespace.length() == 0) {
            return tagName;
        } else {
            return namespace + ":" + tagName;
        }
    }
}

