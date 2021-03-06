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
package org.restfulwhois.rdap.core.common.model.base;

/**
 * model type.
 * 
 * @author jiashuo
 * 
 */
public enum ModelType {
    BASE("base"),
    /**
     * model type.
     */
    SEARCH("search"), VARIANT("variant"), ERRORMESSAGE("errorMessage"),
    /**
     * 5 main object model.
     */
    DOMAIN("domain"), ENTITY("entity"), NAMESERVER("nameServer"), AUTNUM(
            "autnum"),
    /**
     * help ip event link publicId type.
     */
    HELP("help"), IP("ip"), EVENT("event"), LINK("link"), PUBLICID("publicId"),
    /**
     * remark notice secureDns type.
     */
    REMARK("remark"), NOTICE("notice"), SECUREDNS("secureDns"),
    /**
     * ds type.
     */
    DSDATA("dsData"), KEYDATA("keyData");
    /**
     * name of model type.
     */
    private String name;

    /**
     * constructor.
     * 
     * @param name
     *            name.
     */
    private ModelType(String name) {
        this.name = name;
    }

    /**
     * get model type be name.
     * 
     * @param name
     *            name.
     * @return model ModelType.
     */
    public static ModelType getModelType(String name) {
        ModelType[] modelTypes = ModelType.values();
        for (ModelType modelType : modelTypes) {
            if (modelType.getName().equals(name)) {
                return modelType;
            }
        }
        return null;
    }

    /**
     * get type name.
     * 
     * @return type name.
     */
    public String getName() {
        return name;
    }

    /**
     * set type name.
     * 
     * @param name
     *            type name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
