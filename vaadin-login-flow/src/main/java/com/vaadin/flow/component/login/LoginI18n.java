package com.vaadin.flow.component.login;

/*
 * #%L
 * Vaadin Login for Vaadin 10
 * %%
 * Copyright (C) 2018 Vaadin Ltd
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

import com.vaadin.flow.internal.JsonSerializer;
import elemental.json.JsonFactory;
import elemental.json.JsonValue;
import elemental.json.impl.JreJsonFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * Internationalization object for customizing the component UI texts.
 * An instance with the default messages can be obtained using {@link LoginI18n#createDefault()}
 *
 * @see Login#setI18n(LoginI18n)
 */
public class LoginI18n implements Serializable {

    private static final JsonValue DEFAULT_I18N;

    static {
        try {
            final JsonFactory JSON_FACTORY = new JreJsonFactory();
            DEFAULT_I18N = JSON_FACTORY.parse(IOUtils
                .toString(LoginI18n.class.getResource("/i18n.json"),
                    Charset.defaultCharset()));
        } catch (IOException e) {
            throw new IllegalStateException(
                "Cannot find the default i18n configuration");
        }
    }

    private Header header;
    private Form form;
    private ErrorMessage errorMessage;
    private String additionalInformation;

    /**
     * @return a new instance with the default messages
     */
    public static LoginI18n createDefault() {
        return JsonSerializer.toObject(LoginI18n.class, DEFAULT_I18N);
    }

    /**
     * @return {@link Header} object.
     */
    public Header getHeader() {
        return header;
    }

    /**
     * @param header {@link Header} object.
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * @return {@link Form} object.
     */
    public Form getForm() {
        return form;
    }

    /**
     * @param form {@link Form} object.
     */
    public void setForm(Form form) {
        this.form = form;
    }

    /**
     * @return {@link ErrorMessage} object.
     */
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage {@link ErrorMessage} object.
     */
    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return current value for the additional information property
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * @param additionalInformation new value for the additional information property.
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * I18n properties for the header.
     */
    public static class Header implements Serializable {
        private String title;
        private String description;

        /**
         * @return current value for the title property
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title new value for the title property.
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return current value for the description property
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description new value for the description property.
         */
        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * I18n properties for the form.
     */
    public static class Form implements Serializable {
        private String title;
        private String username;
        private String password;
        private String submit;
        private String forgotPassword;

        /**
         * @return current value for the title property
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title new value for the title property.
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return current value for the username property
         */
        public String getUsername() {
            return username;
        }

        /**
         * @param username new value for the username property.
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * @return current value for the password property
         */
        public String getPassword() {
            return password;
        }

        /**
         * @param password new value for the password property.
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * @return current value for the submit property
         */
        public String getSubmit() {
            return submit;
        }

        /**
         * @param submit new value for the submit property.
         */
        public void setSubmit(String submit) {
            this.submit = submit;
        }

        /**
         * @return current value for the forgotPassword property
         */
        public String getForgotPassword() {
            return forgotPassword;
        }

        /**
         * @param forgotPassword new value for the forgotPassword property.
         */
        public void setForgotPassword(String forgotPassword) {
            this.forgotPassword = forgotPassword;
        }
    }

    /**
     * I18n properties for the error message.
     */
    public static class ErrorMessage implements Serializable {
        private String title;
        private String message;

        /**
         * @return current value for the title property
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title new value for the title property.
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return current value for the message property
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message new value for the message property.
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }

}