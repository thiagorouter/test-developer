package br.netshoes.rest.resources.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 16/04/2015.
 */
public class BaseResponse implements Serializable {

        private List<String> erros = new ArrayList<String>();

        public List<String> getErros() {
                return erros;
        }

        public void adicionarMensagemDeErro(final String mensagemDeErro) {
                getErros().add(mensagemDeErro);
        }
}
