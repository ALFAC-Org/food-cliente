package br.com.alfac.food.core.domain.pedido;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
    RECEBIDO("Recebido"),
    EM_PREPARACAO("Em preparação"),
    PRONTO("Pronto"),
    FINALIZADO("Finalizado");

    private final String descricao;
    private StatusPedido proximoStatus;

    StatusPedido(final String descricao) {
        this.descricao = descricao;
    }

    static {
        AGUARDANDO_PAGAMENTO.proximoStatus = RECEBIDO;
        RECEBIDO.proximoStatus = EM_PREPARACAO;
        EM_PREPARACAO.proximoStatus = PRONTO;
        PRONTO.proximoStatus = FINALIZADO;
        FINALIZADO.proximoStatus = FINALIZADO;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusPedido getProximoStatus() {
        return proximoStatus;
    }

}