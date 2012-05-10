/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.scr.command;

import java.util.ArrayList;
import java.util.List;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.component.Reference;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.scr.ScrService;
import org.apache.karaf.scr.command.action.DeactivateAction;
import org.apache.karaf.scr.command.completer.DeactivateCompleter;
import org.apache.karaf.shell.console.CompletableFunction;
import org.apache.karaf.shell.console.Completer;

/**
 * Shell Command used to deactivate a Declarative Service Component.
 */
@Component(
        provide=CompletableFunction.class, 
        name = DeactivateCommandComponent.COMPONENT_NAME, 
        enabled = true, 
        immediate = true,
        properties={ 
            ScrCommandConstants.OSGI_COMMAND_SCOPE_KEY+"="+ScrCommandConstants.SCR_COMMAND, 
            ScrCommandConstants.OSGI_COMMAND_FUNCTION_KEY+"="+ScrCommandConstants.DEACTIVATE_FUNCTION})
public class DeactivateCommandComponent extends ScrCommandSupport {

    public static final String COMPONENT_NAME = "DeactivateCommand";

    public static final String COMPONENT_LABEL = "Apache Karaf SCR Deactivate Command";

    @Override
    public Class<? extends Action> getActionClass() {
        return DeactivateAction.class;
    }

    @Override
    public List<Class<? extends Completer>> getCompleterClasses() {
        List<Class<? extends Completer>> completers = new ArrayList<Class<? extends Completer>>();
        completers.add(DeactivateCompleter.class);
        return completers;
    }

    @Activate
    public void activate(){
        logger.info("Activating the " + COMPONENT_LABEL);
    }
    
    @Deactivate
    public void deactivate(){
        logger.info("Deactivating the " + COMPONENT_LABEL);
    }
    
    @Reference
    @Override
    public void setScrService(ScrService scrService) {
    	super.setScrService(scrService);
    }

}
