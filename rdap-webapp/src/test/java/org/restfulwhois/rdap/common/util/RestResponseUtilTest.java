/*
 * Copyright (c) 2012 - 2015, Internet Corporation for Assigned Names and
 * Numbers (ICANN) and China Internet Network Information Center (CNNIC)
 * 
 * All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  
 * * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * * Neither the name of the ICANN, CNNIC nor the names of its contributors may
 *  be used to endorse or promote products derived from this software without
 *  specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL ICANN OR CNNIC BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package org.restfulwhois.rdap.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.restfulwhois.rdap.BaseTest;
import org.restfulwhois.rdap.core.common.util.RestResponseUtil;
import org.restfulwhois.rdap.core.domain.model.Domain;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * Test for RestResponseUtil.
 * 
 * @author jiashuo
 * 
 */
@SuppressWarnings("rawtypes")
public class RestResponseUtilTest extends BaseTest {

    /**
     * checkAllowOriginHeader.
     * 
     * @param result
     *            result.
     */
    private void checkAllowOriginHeader(ResponseEntity result) {
        assertTrue(result.getHeaders().containsKey(
                "Access-Control-Allow-Origin"));
        assertThat(result.getHeaders().get("Access-Control-Allow-Origin"),
                CoreMatchers.hasItems("*"));
    }

    /**
     * test create ResponseEntity with HTTP code 200.
     */
    @Test
    public void testCreateResponse200() {
        String domainName = "cnnic.cn";
        Domain domain = new Domain();
        domain.setLdhName(domainName);
        ResponseEntity<Domain> result =
                RestResponseUtil.createResponse200(domain);
        Assert.notNull(result);
        assertEquals(result.getBody().getLdhName(), domainName);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 400.
     */
    @Test
    public void testCreateResponse400() {
        ResponseEntity result = RestResponseUtil.createResponse400();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 401.
     */
    @Test
    public void testCreateResponse401() {
        ResponseEntity result = RestResponseUtil.createResponse401();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.UNAUTHORIZED);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 403.
     */
    @Test
    public void testCreateResponse403() {
        ResponseEntity result = RestResponseUtil.createResponse403();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.FORBIDDEN);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 404.
     */
    @Test
    public void testCreateResponse404() {
        ResponseEntity result = RestResponseUtil.createResponse404();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 405.
     */
    @Test
    public void testCreateResponse405() {
        ResponseEntity result = RestResponseUtil.createResponse405();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
        assertTrue(result.getHeaders().containsKey("Allow"));
        assertThat(result.getHeaders().get("Allow"),
                CoreMatchers.hasItems(HttpMethod.GET.toString()));
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 415.
     */
    @Test
    public void testCreateResponse415() {
        ResponseEntity result = RestResponseUtil.createResponse415();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 500.
     */
    @Test
    public void testCreateResponse500() {
        ResponseEntity result = RestResponseUtil.createResponse500();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 422.
     */
    @Test
    public void testCreateResponse422() {
        ResponseEntity result = RestResponseUtil.createResponse422();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 301.
     */
    @Test
    public void testCreateResponse301() {
        ResponseEntity result =
                RestResponseUtil.createResponse301("http://cnnic.cn");
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.MOVED_PERMANENTLY);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 429.
     */
    @Test
    public void testCreateResponse429() {
        ResponseEntity result = RestResponseUtil.createResponse429();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(), HttpStatus.TOO_MANY_REQUESTS);
        checkAllowOriginHeader(result);
    }

    /**
     * test create ResponseEntity with HTTP code 509.
     */
    @Test
    public void testCreateResponse509() {
        ResponseEntity result = RestResponseUtil.createResponse509();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(),
                HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        checkAllowOriginHeader(result);
    }
    /**
     * test create ResponseEntity with HTTP code 501.
     */
    @Test
    public void testCreateResponse501() {
        ResponseEntity result = RestResponseUtil.createResponse501();
        Assert.notNull(result);
        assertEquals(result.getStatusCode(),
                HttpStatus.NOT_IMPLEMENTED);
        checkAllowOriginHeader(result);
    }
}
