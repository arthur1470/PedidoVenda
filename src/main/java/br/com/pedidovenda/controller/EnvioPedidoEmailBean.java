package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.util.jsf.FacesUtil;
import br.com.pedidovenda.util.mail.Mailer;
import br.com.pedidovenda.util.mail.VelocityTemplateUTF8;
import com.outjected.email.api.MailMessage;
import org.apache.velocity.tools.generic.NumberTool;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named("envioPedidoEmailBean")
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();

        message.to(this.pedido.getCliente().getEmail())
                .subject("Pedido " + this.pedido.getId())
                .bodyHtml(new VelocityTemplateUTF8(getClass().getResourceAsStream("/emails/pedido.template")))
                .put("pedido", this.pedido)
                .put("numberTool", new NumberTool())
                .put("locale", new Locale("pt", "BR"))
                .send();

        FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
    }
}
