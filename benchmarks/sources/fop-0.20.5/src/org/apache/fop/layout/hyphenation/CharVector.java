/*
 * $Id: CharVector.java,v 1.3.2.1 2003/02/25 14:07:10 jeremias Exp $
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
package org.apache.fop.layout.hyphenation;

import java.io.Serializable;

/**
 * This class implements a simple char vector with access to the
 * underlying array.
 *
 * @author Carlos Villegas <cav@uniscope.co.jp>
 */
public class CharVector implements Cloneable, Serializable {

    /**
     * Capacity increment size
     */
    private static final int DEFAULT_BLOCK_SIZE = 2048;
    private int BLOCK_SIZE;

    /**
     * The encapsulated array
     */
    private char[] array;

    /**
     * Points to next free item
     */
    private int n;

    public CharVector() {
        this(DEFAULT_BLOCK_SIZE);
    }

    public CharVector(int capacity) {
        if (capacity > 0)
            BLOCK_SIZE = capacity;
        else
            BLOCK_SIZE = DEFAULT_BLOCK_SIZE;
        array = new char[BLOCK_SIZE];
        n = 0;
    }

    public CharVector(char[] a) {
        BLOCK_SIZE = DEFAULT_BLOCK_SIZE;
        array = a;
        n = a.length;
    }

    public CharVector(char[] a, int capacity) {
        if (capacity > 0)
            BLOCK_SIZE = capacity;
        else
            BLOCK_SIZE = DEFAULT_BLOCK_SIZE;
        array = a;
        n = a.length;
    }

    /**
     * Reset Vector but don't resize or clear elements
     */
    public void clear() {
        n = 0;
    }

    public Object clone() {
        CharVector cv = new CharVector((char[])array.clone(), BLOCK_SIZE);
        cv.n = this.n;
        return cv;
    }

    public char[] getArray() {
        return array;
    }

    /**
     * return number of items in array
     */
    public int length() {
        return n;
    }

    /**
     * returns current capacity of array
     */
    public int capacity() {
        return array.length;
    }

    public void put(int index, char val) {
        array[index] = val;
    }

    public char get(int index) {
        return array[index];
    }

    public int alloc(int size) {
        int index = n;
        int len = array.length;
        if (n + size >= len) {
            char[] aux = new char[len + BLOCK_SIZE];
            System.arraycopy(array, 0, aux, 0, len);
            array = aux;
        }
        n += size;
        return index;
    }

    public void trimToSize() {
        if (n < array.length) {
            char[] aux = new char[n];
            System.arraycopy(array, 0, aux, 0, n);
            array = aux;
        }
    }

}
