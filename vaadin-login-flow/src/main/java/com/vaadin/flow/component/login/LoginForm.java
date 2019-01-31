package com.vaadin.flow.component.login;

/*
 * #%L
 * Login for Vaadin Flow
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * Server-side component for the {@code <vaadin-login-form>} component.
 *
 * @author Vaadin Ltd
 */
@Tag("vaadin-login-form")
@HtmlImport("frontend://bower_components/vaadin-login/src/vaadin-login-form.html")
public class LoginForm extends AbstractLogin {

    public LoginForm() {
    }

    public LoginForm(LoginI18n i18n) {
        super(i18n);
    }

}