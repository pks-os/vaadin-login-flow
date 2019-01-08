package com.vaadin.flow.component.login.vaadincom;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.Login;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

@Route("vaadin-login")
public class LoginView extends DemoView {

    @Override
    protected void initView() {
        basicDemo();
        internationalization();
        overlay();
    }

    private void basicDemo() {
        // begin-source-example
        // source-example-heading: Basic Demo
        Login component = new Login();
        component.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                navigateToMainPage();
            } else {
                component.setError(true);
            }
        });
        // end-source-example

        addCard("Basic Demo", createLayout(component));
    }

    @SuppressWarnings("unused")
    private boolean authenticate(AbstractLogin.LoginEvent e) {
        return false;
    }

    private void navigateToMainPage() {

    }

    private void internationalization() {
        // begin-source-example
        // source-example-heading: Login with internationalization
        Login component = new Login();
        Button updateI18nButton = new Button("Switch to Brazilian Portuguese",
            event -> component.setI18n(createPortugueseI18n()));
        // end-source-example

        addCard("Login with internationalization", createLayout(component), updateI18nButton);
    }

    private void overlay() {
        // begin-source-example
        // source-example-heading: Login in an overlay
        LoginOverlay component = new LoginOverlay();
        component.addLoginListener(e -> component.close());
        Button open = new Button("Open login overlay",
            e -> component.setOpened(true));
        // end-source-example

        addCard("Login in an overlay", component, open);
    }

    private LoginI18n createPortugueseI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Nome do aplicativo");
        i18n.getHeader().setDescription("Descrição do aplicativo");
        i18n.getForm().setUsername("Usuário");
        i18n.getForm().setTitle("Acesse a sua conta");
        i18n.getForm().setSubmit("Entrar");
        i18n.getForm().setPassword("Senha");
        i18n.getForm().setForgotPassword("Esqueci minha senha");
        i18n.getErrorMessage().setTitle("Usuário/senha inválidos");
        i18n.getErrorMessage()
            .setMessage("Confira seu usuário e senha e tente novamente.");
        i18n.setAdditionalInformation(
            "Caso necessite apresentar alguma informação extra para o usuário"
                + " (como credenciais padrão), este é o lugar.");
        return i18n;
    }

    private Component createLayout(Login login) {
        VerticalLayout layout = new VerticalLayout();
        layout.add(login);

        layout.setSizeFull();
        layout.setPadding(true);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.getStyle().set("background", "var(--lumo-shade-5pct)");

        return layout;
    }
}
