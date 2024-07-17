package br.com.alfac.food.database.pagamento.persistence;

import br.com.alfac.food.core.domain.pagamento.StatusPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class PagamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Id do pedido é obrigatório")
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status do item é obrigatório")
    private StatusPagamento status;

    @NotNull(message = "Data do pagamento é obrigatório")
    private LocalDateTime dataPagamento;

    @NotNull(message = "Data do atualização é obrigatório")
    private LocalDateTime dataAtualizacao;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(final Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(final StatusPagamento status) {
        this.status = status;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(final LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(final LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}