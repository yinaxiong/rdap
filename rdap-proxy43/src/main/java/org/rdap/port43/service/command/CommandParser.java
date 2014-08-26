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
package org.rdap.port43.service.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;
import org.rdap.port43.service.ServiceException;

/**
 * 
 * @author jiashuo
 * 
 */
public class CommandParser {
    private static final String WHOIS_CMD_PREFIX = "whois";

    /**
     * 
     * parse command string to Command.
     * 
     * @param commandStr
     *            command string.
     * @return Command.
     * @throws ServiceException
     *             ServiceException.
     */
    public static Command parse(String commandStr) throws ServiceException {
        commandStr = validateAndFormatCommandStr(commandStr);
        String[] commandSplits = StringUtils.split(commandStr);
        Options supportedOptions = initSupportedOptions();
        CommandLine line = null;
        CommandLineParser parser = new GnuParser();
        try {
            line = parser.parse(supportedOptions, commandSplits, false);
        } catch (ParseException e) {
            throw new ServiceException("invalid command");
        }
        Option[] options = line.getOptions();
        Command command = null;
        for (Option option : options) {
            command = new Command(CommandOption.getByStr(option.getOpt()));
            if (null != command) {
                break;
            }
        }
        if (null == command) {
            command = new Command(CommandOption.IP_OR_DOMAIN_QUERY);
        }
        command.setArgumentList(line.getArgList());
        parseAllOptionsAndSetToCommand(options, command);
        return command;
    }

    /**
     * init supported options.
     * 
     * @return options.
     */
    private static Options initSupportedOptions() {
        Options supportedOptions = new Options();
        for (CommandOption option : CommandOption.values()) {
            supportedOptions.addOption(option.getOption(), option.getOption(),
                    option.isHasArg(), option.getDescription());
        }
        return supportedOptions;
    }

    /**
     * parse all options and set it to command.
     * 
     * @param options
     *            options.
     * @param command
     *            command.
     */
    private static void parseAllOptionsAndSetToCommand(Option[] options,
            Command command) {
        Map<String, String> allOptionsMap = new HashMap<String, String>();
        for (Option option : options) {
            allOptionsMap.put(option.getOpt(), option.getValue());
        }
        command.setAllOptionsMap(allOptionsMap);
    }

    /**
     * validate and format command.
     * 
     * @param commandStr
     *            commandStr.
     * @return formated command.
     * @throws ServiceException
     *             if validate error.
     */
    private static String validateAndFormatCommandStr(String commandStr)
            throws ServiceException {
        if (StringUtils.isBlank(commandStr)) {
            throw new ServiceException("invalid command");
        }
        commandStr = StringUtils.trim(commandStr);
        if (StringUtils.isBlank(commandStr)) {
            throw new ServiceException("invalid command");
        }
        return commandStr;
    }
}