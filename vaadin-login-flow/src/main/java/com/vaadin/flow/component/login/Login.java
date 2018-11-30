package com.vaadin.flow.component.login;

/*
 * #%L
 * Vaadin Login for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.dom.DisabledUpdateMode;
import com.vaadin.flow.internal.JsonSerializer;
import com.vaadin.flow.shared.Registration;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Server-side component for the {@code <vaadin-login>} component.
 * On {@link Login.LoginEvent} component becomes disabled.
 * Disabled component stops to process login events, however
 * the {@link Login.ForgotPasswordEvent} event is processed anyway.
 * To enable use the {@link com.vaadin.flow.component.HasEnabled#setEnabled(boolean)} method.
 *
 * @author Vaadin Ltd
 */
@Tag("vaadin-login")
@HtmlImport("frontend://bower_components/vaadin-login/src/vaadin-login.html")
public class Login extends Component implements HasEnabled {

    private static final String LOGIN_EVENT = "login";

    private final List<ComponentEventListener<LoginEvent>> loginListeners = new CopyOnWriteArrayList<>();

    /**
     * Initializes a new Login with default localization.
     */
    public Login() {
        this(LoginI18n.createDefault());
        getElement().synchronizeProperty("disabled", LOGIN_EVENT);
        ComponentUtil.addListener(this, LoginEvent.class, (ComponentEventListener)
                ((ComponentEventListener<LoginEvent>) e -> {
                    setEnabled(false);
                    loginListeners.forEach(listener -> listener.onComponentEvent(e));
                }));
    }

    /**
     * Initializes a new Login.
     *
     * @param i18n internationalized messages to be used by this instance.
     */
    public Login(LoginI18n i18n) {
        setI18n(i18n);
    }

    /**
     * Sets the path where to send the form-data when a form is submitted.
     * Once action is defined a {@link Login.LoginEvent} is not fired anymore.
     *
     * @see #getAction()
     */
    public void setAction(String action) {
        getElement().setProperty("action", action);
    }

     /**
     * Returns the action defined for a login form.
     *
     * @return the value of action property
     */
    @Synchronize("action-changed")
    public String getAction() {
        return getElement().getProperty("action");
    }

    /**
     * Sets the internationalized messages to be used by this instance.
     *
     * @param i18n the internationalized messages
     * @see LoginI18n#createDefault()
     */
    public void setI18n(LoginI18n i18n) {
        getElement().setPropertyJson("i18n", JsonSerializer.toJson(i18n));
    }

    /**
     * Adds `login` event listener
     * Event is fired only if no action is defined
     */
    public Registration addLoginListener(ComponentEventListener<LoginEvent> listener) {
        loginListeners.add(listener);
        return () -> loginListeners.remove(listener);
    }

    /**
     * Adds `forgotPassword` event listener. Event continues being process even if
     * the component is not {@link #isEnabled()}.
     */
    public Registration addForgotPasswordListener(
        ComponentEventListener<ForgotPasswordEvent> listener) {
        return ComponentUtil
            .addListener(this, ForgotPasswordEvent.class, listener,
                    domReg -> domReg.setDisabledUpdateMode(DisabledUpdateMode.ALWAYS));
    }

    /**
     * `login` is fired when the user either clicks Submit button or presses an Enter key.
     * Event is fired only if no action is set for login form and client-side validation passed.
     */
    @DomEvent(LOGIN_EVENT)
    public static class LoginEvent extends ComponentEvent<Login> {

        private String username;
        private String password;

        public LoginEvent(Login source, boolean fromClient,
                          @EventData("event.detail.username") String username,
                          @EventData("event.detail.password") String password) {
            super(source, fromClient);
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    /**
     * `forgot-password` is fired when the user clicks Forgot password button
     */
    @DomEvent("forgot-password")
    public static class ForgotPasswordEvent extends ComponentEvent<Login> {
        public ForgotPasswordEvent(Login source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    @Override
    public void onEnabledStateChanged(boolean enabled) {
        getElement().setProperty("disabled", !enabled);
    }

}
